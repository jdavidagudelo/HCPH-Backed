package com.artica.telesalud.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class StatefulRemoteServiceServlet extends RemoteServiceServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpSession getSession(){
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		
		return session;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getSessionValue(String key){
		HttpSession session = getSession();
		return (T)session.getAttribute(key);
	}
	
	public <T> void setSessionValue(String key, T value){
		HttpSession session = getSession();
		session.setAttribute(key, value);
	}
	
	public void removeSessionValue(String key){
		HttpSession session = getSession();
		session.setAttribute(key, null);
	}
}