package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Maestro implements Serializable {

	private Integer id;
	private String codigo;
	private String nombre;
	private String descripcion;
	private Integer nivel;
	private Integer inactivo;
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setInactivo(Integer activo) {
		this.inactivo = activo;
	}
	public Integer getInactivo() {
		return inactivo;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public Integer getNivel() {
		return nivel;
	}
}
