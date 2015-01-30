package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Hospital implements Serializable {

	private Integer id;
	private String nombre;
	private String direccion;
	private Integer nivelComplejidad;
	private Boolean activo;
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setNivelComplejidad(Integer nivelComplejidad) {
		this.nivelComplejidad = nivelComplejidad;
	}
	public Integer getNivelComplejidad() {
		return nivelComplejidad;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public Boolean getActivo() {
		return activo;
	}
}
