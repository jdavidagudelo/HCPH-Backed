package com.artica.telesalud.tph.controller.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.artica.telesalud.persona.model.impl.UserDTO;
import com.artica.telesalud.persona.service.UserService;
import com.artica.telesalud.persona.service.user.PasswordDoesNotMatchException;
import com.artica.telesalud.persona.service.user.UserNotFoundException;
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.exception.DefaultAuthenticationException;
import com.artica.telesalud.tph.controller.user.SessionUserApp;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
/**
 * Clase usada para realizar la autenticacion del usuario con la informacion de la base de datos.
 * @author Juan David Agudelo. jdaaa2009@gmail.com.
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider, ServletContextAware, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        try{
        	final UserDTO user = userService.getValidUser(username, password);

            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new GrantedAuthority() {
			
				private static final long serialVersionUID = 1L;

				@Override
				public String getAuthority() {
					return SessionUserApp.getRoleSecurityName(user.getRoleId());
				}
			});
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), grantedAuths);
        }
        catch (PasswordDoesNotMatchException e ) {
        	throw new DefaultAuthenticationException("Usuario o clave invalida");
		
		}
		catch(UserNotFoundException exception)
		{
			throw new DefaultAuthenticationException("Usuario no existe en la base de datos");
		}
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
	@Override
	public void setServletContext(ServletContext servletContext) {
		HashMap<String, String>	params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				servletContext.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));
		userService = new UserService(
				servletContext
						.getInitParameter(HibernateProperties.PERSONA_DAO_CLASS_FACTORY_IMPL),
				params);

	}
}