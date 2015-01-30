package com.artica.telesalud.tph.controller.constants;

/**
 * Contiene las URIs de los servicios web RESTFul definidos en la clase LocationController.
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 *
 */
public interface LocationRestURIConstants {
	public static final String LOCATION_SERVICE_URI="LocationController";
	public static final String LOCATION_SERVICE_GET_STATES_URI="/getStates";
	public static final String LOCATION_SERVICE_GET_CITIES_BY_STATE_URI="/getCitiesByState/{stateId}";
	public static final String LOCATION_SERVICE_GET_CITIES_BY_STATE_STATE_PARAM_NAME="stateId";
	public static final String LOCATION_SERVICE_GET_STATE_BY_NAME_URI="/getStateByName/{stateName}/{countryName}";
	public static final String LOCATION_SERVICE_GET_STATE_BY_NAME_STATE_NAME_PARAM_NAME="stateName";
	public static final String LOCATION_SERVICE_GET_STATE_BY_NAME_COUNTRY_NAME_PARAM_NAME="countryName";
	public static final String LOCATION_SERVICE_GET_CITY_BY_NAME_URI="/getCityByName/{cityName}/{stateName}/{countryName}";
	public static final String LOCATION_SERVICE_GET_CITY_BY_NAME_STATE_NAME_PARAM_NAME="stateName";
	public static final String LOCATION_SERVICE_GET_CITY_BY_NAME_COUNTRY_NAME_PARAM_NAME="countryName";
	public static final String LOCATION_SERVICE_GET_CITY_BY_NAME_CITY_NAME_PARAM_NAME="cityName";
	public static final String LOCATION_SERVICE_GET_ALL_CITIES = "/getAllCities";
	public static final String LOCATION_ZONA_RURAL = "Rural";
	public static final String LOCATION_ZONA_URBANA="Urbana";

}
