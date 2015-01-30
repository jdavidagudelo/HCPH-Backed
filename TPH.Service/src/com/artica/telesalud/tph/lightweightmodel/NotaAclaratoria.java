package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.Date;

public class NotaAclaratoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreCreador;
	private Date fechaCreacion;
	private String nota;
	
	public void setNombreCreador(String nombreCreador) {
		this.nombreCreador = nombreCreador;
	}
	public String getNombreCreador() {
		return nombreCreador;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getNota() {
		return nota;
	}
}
