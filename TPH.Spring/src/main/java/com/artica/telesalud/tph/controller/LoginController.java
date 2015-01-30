package com.artica.telesalud.tph.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.ServletContextAware;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.persona.model.impl.UserDTO;
import com.artica.telesalud.persona.service.UserService;
import com.artica.telesalud.persona.service.user.PasswordDoesNotMatchException;
import com.artica.telesalud.persona.service.user.UserNotFoundException;
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.constants.LoginRestURIConstants;
import com.artica.telesalud.tph.controller.exception.FieldErrorResource;
import com.artica.telesalud.tph.controller.exception.InternalErrorException;
import com.artica.telesalud.tph.controller.exception.InvalidRequestException;
import com.artica.telesalud.tph.controller.model.user.UserSpringDto;
import com.artica.telesalud.tph.controller.user.SessionUserApp;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.service.PersonaService;
/**
 * Permite obtener la informacion de usuarios del sistema y validar la informacion.
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 *
 */
@Controller
@RequestMapping(value = LoginRestURIConstants.USER_SERVICE_URI)
public class LoginController  extends AbstractController implements ServletContextAware {
	private UserService userService;
	private PersonaService personService;
	private ServletContext context;
	/**
	 * Permite validar la identidad del usuario con nombre de usuario username.
	 * @param username nombre del usuario que se desea validar.
	 * @param password la clave del usuario en TEXTO PLANO.
	 * @return verifica si la clave del usuario corresponde a la almacenada en la base de datos, en caso
	 * exitoso retorna la informacion del usuario, en caso contrario se lanza un excepcion.
	 */
	@RequestMapping(value = LoginRestURIConstants.USER_SERVICE_LOGIN_URI, method = RequestMethod.POST)
	public @ResponseBody UserSpringDto validUser(
			@PathVariable(value = LoginRestURIConstants.USER_SERVICE_LOGIN_USERNAME_PARAM) String username,
			@PathVariable(value = LoginRestURIConstants.USER_SERVICE_LOGIN_PASSWORD_PARAM) String password) {
		super.refreshHibernateSession(context);
		UserSpringDto userDto = null;
		try {
			UserDTO user = userService.getValidUser(username, password);
			if (user != null) {
				Person person = personService.getPersonById(user.getPersonId());
				userDto = new UserSpringDto(user, person, SessionUserApp.getRolName(user.getRoleId()));
				
			}
		} catch (PasswordDoesNotMatchException e ) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(LoginController.class.getCanonicalName(), String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY), "Usuario o clave inválidos"));
			throw new InvalidRequestException("Usuario o clave no válidos", errors);
		}
		catch(UserNotFoundException exception)
		{
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(LoginController.class.getCanonicalName(), String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY), "Usuario o clave inválidos"));
			throw new InvalidRequestException("Usuario o clave no válidos", errors);
		}
		catch(Exception ex)
		{
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(LoginController.class.getCanonicalName(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
		return userDto;
	}
	@ExceptionHandler(InternalErrorException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody List<FieldErrorResource> handleException(InternalErrorException ex)
	{
		return ex.getErrors();
	}
	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	public @ResponseBody List<FieldErrorResource> handleException(InvalidRequestException ex)
	{
		return ex.getErrors();
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		context = servletContext;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				servletContext
						.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory(servletContext
				.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));

		personService = new PersonaService(
				servletContext.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);

		userService = new UserService(
				servletContext
						.getInitParameter(HibernateProperties.PERSONA_DAO_CLASS_FACTORY_IMPL),
				params);

	}
}
