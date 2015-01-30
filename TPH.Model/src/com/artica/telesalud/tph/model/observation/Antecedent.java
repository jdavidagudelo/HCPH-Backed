package com.artica.telesalud.tph.model.observation;

public class Antecedent{
	
	private Integer ano;
	private Integer IdTipoAntecedente;
	private String observacion;
	private Integer idParentesco;
	private Integer nuevo;
	
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getAno() {
		return ano;
	}
	public void setIdTipoAntecedente(Integer idTipoAntecedente) {
		IdTipoAntecedente = idTipoAntecedente;
	}
	public Integer getIdTipoAntecedente() {
		return IdTipoAntecedente;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setIdParentesco(Integer idParentesco) {
		this.idParentesco = idParentesco;
	}
	public Integer getIdParentesco() {
		return idParentesco;
	}
	public void setNuevo(Integer nuevo) {
		this.nuevo = nuevo;
	}
	public Integer getNuevo() {
		return nuevo;
	}
	
	
}
