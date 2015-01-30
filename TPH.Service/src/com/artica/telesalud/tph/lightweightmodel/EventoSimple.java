package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class EventoSimple implements Serializable{

	public static final String ESTADO_ACTIVO = "Activo";
	public static final String ESTADO_FINALIZADO = "Finalizado";
	public static final String ZONA_RURAL = "Rural";
	public static final String ZONA_URBANA = "Urbana";
	
	private Integer id;
	private String estado;
	private String direccion;
	private Integer departamento;
	private Integer ciudad;
	private String nombreCiudad;
	private String zona;
	private Integer causa;
	private String nombreCausa;
	private String nombreDepartamento;
	private String caso;
	private Date fechaHora;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setCiudad(Integer ciudad) {
		this.ciudad = ciudad;
	}

	public Integer getCiudad() {
		return ciudad;
	}

	public void setCausa(Integer causa) {
		this.causa = causa;
	}

	public Integer getCausa() {
		return causa;
	}

	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
	}

	public Integer getDepartamento() {
		return departamento;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getZona() {
		return zona;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setCaso(String caso) {
		this.caso = caso;
	}

	public String getCaso() {
		return caso;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public String getNombreCausa() {
		return nombreCausa;
	}

	public void setNombreCausa(String nombreCausa) {
		this.nombreCausa = nombreCausa;
	}

	public String getNombreDepartamento() {
		return nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
}
