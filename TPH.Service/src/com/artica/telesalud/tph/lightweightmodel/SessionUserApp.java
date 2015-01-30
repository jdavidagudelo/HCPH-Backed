package com.artica.telesalud.tph.lightweightmodel;

public class SessionUserApp {
	public static final Integer ROLE_ADMIN = 4,
			  ROLE_DIGITADOR = 5,
			  ROLE_MEDICO_TELEASISTENCIA = 6,
			  ROLE_EQUIPO_INTERVENCION = 7,
			  ROLE_MEDICO_DIGITADOR = 8;
				
	
	public static boolean isAdmin(Integer userRole){
		return userRole.equals(ROLE_ADMIN);
	}
	
	public static boolean isDigitador(Integer userRole){
		return userRole.equals(ROLE_DIGITADOR);
	}
	
	public static boolean isMedicoTeleasistencia(Integer userRole){
		return userRole.equals(ROLE_MEDICO_TELEASISTENCIA);
	}
	
	public static boolean isEquipoIntervencion(Integer userRole){
		return userRole.equals(ROLE_EQUIPO_INTERVENCION);
	}
	
	public static boolean isMedicoDigitador(Integer userRole){
		return userRole.equals(ROLE_MEDICO_DIGITADOR);
	}
	
	public static String getRolName(Integer rolId){
		if(rolId.equals(ROLE_ADMIN)){
			return "Administrador";
		}
		if(rolId.equals(ROLE_DIGITADOR)){
			return "Digitador";
		}
		if(rolId.equals(ROLE_EQUIPO_INTERVENCION)){
			return "Equipo de intervención";
		}
		if(rolId.equals(ROLE_MEDICO_DIGITADOR)){
			return "Medico-Digitador";
		}
		if(rolId.equals(ROLE_MEDICO_TELEASISTENCIA)){
			return "Médico Teleasistencia";
		}
		return "";
	}
}
