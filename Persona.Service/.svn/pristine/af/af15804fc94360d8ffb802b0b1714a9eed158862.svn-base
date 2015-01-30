package com.artica.telesalud.persona.service.user;

import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.persona.model.impl.RoleDTO;

public class RoleIsParentException extends TelesaludException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoleDTO role;

	public RoleIsParentException(RoleDTO role) {
		super("Error");
		this.role = role;
	}

	public RoleDTO getRole() {
		return role;
	}
	
	
}
