package com.artica.telesalud.tph.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**
 * Clase usada como punto de acceso para autenticar un usuario.
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 *
 */
@Component( "restAuthenticationEntryPoint" )
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
 
   @Override
   public void commence( HttpServletRequest request, HttpServletResponse response, 
    AuthenticationException authException ) throws IOException{
      response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
   }
}
