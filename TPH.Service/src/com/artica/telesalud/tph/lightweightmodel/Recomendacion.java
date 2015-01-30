package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Recomendacion implements Serializable {
	
	private Integer id;
	private Date fecha;
	private String nombreMedico;
	private String dxPpal;
	private String recomendacion;
	private List<String> dxSecundarios;
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}
	public String getNombreMedico() {
		return nombreMedico;
	}
	public void setDxPpal(String dxPpal) {
		this.dxPpal = dxPpal;
	}
	public String getDxPpal() {
		return dxPpal;
	}
	public void setRecomendacion(String recomendacion) {
		this.recomendacion = recomendacion;
	}
	public String getRecomendacion() {
		return recomendacion;
	}
	public void setDxSecundarios(List<String> dxSecundarios) {
		this.dxSecundarios = dxSecundarios;
	}
	public List<String> getDxSecundarios() {
		return dxSecundarios;
	}

}
