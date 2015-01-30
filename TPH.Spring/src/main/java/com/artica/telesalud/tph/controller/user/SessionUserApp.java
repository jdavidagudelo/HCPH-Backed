package com.artica.telesalud.tph.controller.user;

public class SessionUserApp {
	public enum Rol {
		ROLE_ADMIN(4, "ROLE_ADMIN", "Administrador"), ROLE_DIGITADOR(5,
				"ROLE_DIGITADOR", "Digitador"), ROLE_MEDICO_TELEASISTENCIA(6,
				"ROLE_MEDICO_TELEASISTENCIA", "Médico Telesasistencia"), ROLE_EQUIPO_INTERVENCION(
				7, "ROLE_EQUIPO_INTERVENCION", "Equipo de Intervención"), ROLE_MEDICO_DIGITADOR(
				8, "ROLE_MEDICO_DIGITADOR", "Médico-Digitador");
		private Integer index;
		private String name;
		private String description;

		private Rol(Integer index, String name, String description) {
			this.description = description;
			this.name = name;
			this.index = index;
		}

		/**
		 * @return the index
		 */
		public Integer getIndex() {
			return index;
		}

		/**
		 * @param index
		 *            the index to set
		 */
		public void setIndex(Integer index) {
			this.index = index;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @param description
		 *            the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}

	}

	public static boolean isAdmin(Integer userRole) {
		return userRole.equals(Rol.ROLE_ADMIN.getIndex());
	}

	public static boolean isDigitador(Integer userRole) {
		return userRole.equals(Rol.ROLE_DIGITADOR.getIndex());
	}

	public static boolean isMedicoTeleasistencia(Integer userRole) {
		return userRole.equals(Rol.ROLE_MEDICO_TELEASISTENCIA.getIndex());
	}

	public static boolean isEquipoIntervencion(Integer userRole) {
		return userRole.equals(Rol.ROLE_EQUIPO_INTERVENCION.getIndex());
	}

	public static boolean isMedicoDigitador(Integer userRole) {
		return userRole.equals(Rol.ROLE_MEDICO_DIGITADOR.getIndex());
	}

	public static String getRolName(Integer rolId) {
		if (rolId.equals(Rol.ROLE_ADMIN.getIndex())) {
			return Rol.ROLE_ADMIN.getDescription();
		}
		if (rolId.equals(Rol.ROLE_DIGITADOR.getIndex())) {
			return Rol.ROLE_DIGITADOR.getDescription();
		}
		if (rolId.equals(Rol.ROLE_EQUIPO_INTERVENCION.getIndex())) {
			return Rol.ROLE_EQUIPO_INTERVENCION.getDescription();
		}
		if (rolId.equals(Rol.ROLE_MEDICO_DIGITADOR.getIndex())) {
			return Rol.ROLE_MEDICO_DIGITADOR.getDescription();
		}
		if (rolId.equals(Rol.ROLE_MEDICO_TELEASISTENCIA.getIndex())) {
			return Rol.ROLE_MEDICO_TELEASISTENCIA.getDescription();
		}
		return "";
	}

	public static String getRoleSecurityName(Integer rolId) {
		if (rolId.equals(Rol.ROLE_ADMIN.getIndex())) {
			return Rol.ROLE_ADMIN.getName();
		}
		if (rolId.equals(Rol.ROLE_DIGITADOR.getIndex())) {
			return Rol.ROLE_DIGITADOR.getName();
		}
		if (rolId.equals(Rol.ROLE_EQUIPO_INTERVENCION.getIndex())) {
			return Rol.ROLE_EQUIPO_INTERVENCION.getName();
		}
		if (rolId.equals(Rol.ROLE_MEDICO_DIGITADOR.getIndex())) {
			return Rol.ROLE_MEDICO_DIGITADOR.getName();
		}
		if (rolId.equals(Rol.ROLE_MEDICO_TELEASISTENCIA.getIndex())) {
			return Rol.ROLE_MEDICO_TELEASISTENCIA.getName();
		}
		return "";

	}
}