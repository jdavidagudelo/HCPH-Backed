package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;

public class ResponsableAtencion implements Serializable {
	
	private Integer responsableAtencionId;
	private String registro;
	private String nombres;
	private String apellidos;
	private String controlAPH;
	private Integer controlAPHId;
	private Integer esExterno;
	
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getRegistro() {
		return registro;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getNombres() {
		return nombres;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setControlAPH(String controlAPH) {
		this.controlAPH = controlAPH;
	}
	public String getControlAPH() {
		return controlAPH;
	}
	public void setEsExterno(Integer esExterno) {
		this.esExterno = esExterno;
	}
	public Integer getEsExterno() {
		return esExterno;
	}
	public void setControlAPHId(Integer controlAPHId) {
		this.controlAPHId = controlAPHId;
	}
	public Integer getControlAPHId() {
		return controlAPHId;
	}
	public Integer getResponsableAtencionId() {
		return responsableAtencionId;
	}
	public void setResponsableAtencionId(Integer responsableAtencionId) {
		this.responsableAtencionId = responsableAtencionId;
	}

}
