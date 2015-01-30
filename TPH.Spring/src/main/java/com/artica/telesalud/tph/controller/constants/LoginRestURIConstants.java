package com.artica.telesalud.tph.controller.constants;
/**
 * Contiene las URIs de los servicios web RESTFul definidos en la clase LoginController.
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 *
 */
public interface LoginRestURIConstants {
	public static final String USER_SERVICE_LOGIN_URI= "/login/{username}/{password}";
	public static final String USER_SERVICE_URI = "LoginController";
	public static final String USER_SERVICE_LOGIN_USERNAME_PARAM="username";
	public static final String USER_SERVICE_LOGIN_PASSWORD_PARAM="password";
	
}
