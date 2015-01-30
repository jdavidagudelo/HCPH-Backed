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
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.constants.LocationRestURIConstants;
import com.artica.telesalud.tph.controller.exception.FieldErrorResource;
import com.artica.telesalud.tph.controller.exception.InternalErrorException;
import com.artica.telesalud.tph.controller.exception.InvalidRequestException;
import com.artica.telesalud.tph.controller.model.location.CitySpringDto;
import com.artica.telesalud.tph.controller.model.location.StateProvinceSpringDto;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.location.City;
import com.artica.telesalud.tph.model.location.StateProvince;
import com.artica.telesalud.tph.service.LocationService;
/**
 * Permite obtener la informacion de estados y ciudades de la base de datos.
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 */
@Controller
@RequestMapping(value = LocationRestURIConstants.LOCATION_SERVICE_URI)
public class LocationController  extends AbstractController implements ServletContextAware{
	private LocationService locationService;
	private ServletContext context;
	public LocationController() {
		
	}
	/**
	 * Lista de estados.
	 * @return lista de estados.
	 */
	@RequestMapping(value=LocationRestURIConstants.LOCATION_SERVICE_GET_STATES_URI, method = RequestMethod.GET)
	public @ResponseBody List<StateProvinceSpringDto> getStates() {
		try
		{
			super.refreshHibernateSession(context);
		List<StateProvinceSpringDto> result = new ArrayList<StateProvinceSpringDto>();
		List<StateProvince> states = locationService.getStates();
		for(StateProvince state : states)
		{
			result.add(new StateProvinceSpringDto(state));
		}
		return result;
		}
		catch(Exception ex)
		{
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(LocationController.class.getCanonicalName(), 
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de ciudades del estado especificado como argumento.
	 * @param state id del estado para el que se desean obtener las ciudades.
	 * @return lista de ciudades del estado especificado como argumento.
	 */
	@RequestMapping(value=LocationRestURIConstants.LOCATION_SERVICE_GET_CITIES_BY_STATE_URI, method = RequestMethod.GET)
	public  @ResponseBody List<CitySpringDto> getCitiesByState(
			@PathVariable(value = LocationRestURIConstants.LOCATION_SERVICE_GET_CITIES_BY_STATE_STATE_PARAM_NAME) Integer state) {
		try
		{
			super.refreshHibernateSession(context);
		List<CitySpringDto> result = new ArrayList<CitySpringDto>();
		List<City> cities = locationService.getCitiesByState(state);
		for(City city : cities)
		{
			result.add(new CitySpringDto(city));
		}
		return result;
		}
		catch(Exception ex)
		{
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(LocationController.class.getCanonicalName(), 
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Permite obtener el estado con el nombre stateName en el pais countryName.
	 * @param stateName nombre del estado.
	 * @param countryName nombre del pais.
	 * @return estado con el nombre stateName en el pais countryName.
	 */
	@RequestMapping(value=LocationRestURIConstants.LOCATION_SERVICE_GET_STATE_BY_NAME_URI, method = RequestMethod.GET)
	public @ResponseBody StateProvinceSpringDto getStateByName(
		@PathVariable(value=LocationRestURIConstants.LOCATION_SERVICE_GET_STATE_BY_NAME_STATE_NAME_PARAM_NAME)String stateName,
		@PathVariable(value=LocationRestURIConstants.LOCATION_SERVICE_GET_STATE_BY_NAME_COUNTRY_NAME_PARAM_NAME)String countryName)
	{
		try
		{
			super.refreshHibernateSession(context);
		StateProvinceSpringDto result = new StateProvinceSpringDto(locationService.getStateByName(countryName, stateName));
		return result;
		}
		catch(Exception ex)
		{
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(LocationController.class.getCanonicalName(), 
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Permite obtener la ciudad con el nombre cityName en el estado stateName dentro del pais countryName.
	 * @param cityName nombre de la ciudad.
	 * @param stateName nombre del estado.
	 * @param countryName nombre del pais.
	 * @return ciudad con el nombre cityName en el estado stateName en el pais countryName.
	 */
	@RequestMapping(value=LocationRestURIConstants.LOCATION_SERVICE_GET_CITY_BY_NAME_URI, method = RequestMethod.GET)
	public @ResponseBody CitySpringDto getCityByName(
			
			@PathVariable(value=LocationRestURIConstants.LOCATION_SERVICE_GET_CITY_BY_NAME_CITY_NAME_PARAM_NAME)String cityName,
			@PathVariable(value=LocationRestURIConstants.LOCATION_SERVICE_GET_CITY_BY_NAME_STATE_NAME_PARAM_NAME)String stateName, 
			@PathVariable(value=LocationRestURIConstants.LOCATION_SERVICE_GET_CITY_BY_NAME_COUNTRY_NAME_PARAM_NAME)String countryName)
	{
		try
		{
			super.refreshHibernateSession(context);
			CitySpringDto result = new CitySpringDto(locationService.getCityByName(cityName, stateName, countryName));
			return result;
		}
		catch(Exception ex)
		{
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(LocationController.class.getCanonicalName(), 
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Permite obtener todas las ciudades de la base de datos.
	 * @return lista de todas las ciudades de la base de datos.
	 */
	@RequestMapping(value=LocationRestURIConstants.LOCATION_SERVICE_GET_ALL_CITIES, method=RequestMethod.GET)
	public @ResponseBody List<CitySpringDto> getAllCities()
	{
		super.refreshHibernateSession(context);
		List<CitySpringDto> result = new ArrayList<CitySpringDto>();
		List<City> cities = locationService.getAllCities();
		for(City city : cities)
		{
			result.add(new CitySpringDto(city));
		}
		return result;
	}
	@ExceptionHandler(InternalErrorException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	List<FieldErrorResource> handleException(InternalErrorException ex) {
		return ex.getErrors();
	}

	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public @ResponseBody
	List<FieldErrorResource> handleException(InvalidRequestException ex) {
		return ex.getErrors();
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		context = servletContext;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				servletContext.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory(servletContext.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));

		locationService = new LocationService(
				servletContext.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
	}
	
}
