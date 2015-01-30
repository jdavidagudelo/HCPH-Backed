package com.artica.telesalud.tph.controller.model.amp;
/**
 * Clase usada por Spring para mapear directamente los antecedentes AMP de un paciente a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class AntecedenteAmpSpringDto {
	/**
	 * Descripcion del antecedente.
	 */
	private String description;
	/**
	 * Fecha del antecedente.
	 */
	private String date;
	/**
	 * Nombre del tipo de antecedente.
	 */
	private String tipoAntecedente;
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the tipoAntecedente
	 */
	public String getTipoAntecedente() {
		return tipoAntecedente;
	}
	/**
	 * @param tipoAntecedente the tipoAntecedente to set
	 */
	public void setTipoAntecedente(String tipoAntecedente) {
		this.tipoAntecedente = tipoAntecedente;
	}
	public AntecedenteAmpSpringDto() {
		super();
	}
	
}
