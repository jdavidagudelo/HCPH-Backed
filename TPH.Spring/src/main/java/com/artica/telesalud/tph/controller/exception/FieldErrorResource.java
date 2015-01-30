package com.artica.telesalud.tph.controller.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResource {
    private String resource;
    private String code;
    private String message;

    public String getResource() { return resource; }

    public void setResource(String resource) { this.resource = resource; }

   
    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

	public FieldErrorResource(String resource, String code,
			String message) {
		super();
		this.resource = resource;
		this.code = code;
		this.message = message;
	}
}