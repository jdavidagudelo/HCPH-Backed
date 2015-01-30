package com.artica.telesalud.common.exception;

import java.util.List;

import com.artica.telesalud.common.data.DataConstraintViolation;

public class DataConstraintViolationException extends TelesaludException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4160897612588808983L;
	private List<DataConstraintViolation> violations;

	public DataConstraintViolationException(
			List<DataConstraintViolation> violations) {
		super("Error");
		this.violations = violations;
	}

	public List<DataConstraintViolation> getViolations() {
		return violations;
	}
	
	
}
