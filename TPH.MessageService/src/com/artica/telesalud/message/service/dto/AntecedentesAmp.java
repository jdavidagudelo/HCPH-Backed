package com.artica.telesalud.message.service.dto;

import java.io.Serializable;
import java.util.List;
/**
 * Esta clase corresponde a la lista de antecedentes obtenidos a partir de una historia clínica electronica externa.
 * @author Juan David Agudelo Alvarez jdaaa2009@gmail.com
 *
 */
public class AntecedentesAmp implements Serializable{
	/**
	 * Identificador utilizado para la serializacion.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Identificado Universal de la historia clínica que contiene los antecedentes en el índice maestro de pacientes.
	 */
	private String historiaClinicaUuid;
	/**
	 * Nombre de la historia clínica que contiene los antecedentes.
	 */
	private String historiaClinicaNombre;
	/**
	 * Lista de los antecedentes de cada tipo existentes en una historia clinica externa.
	 */
	private List<AntecedenteAmp> antecedentes;
	public String getHistoriaClinicaUuid() {
		return historiaClinicaUuid;
	}
	public void setHistoriaClinicaUuid(String historiaClinicaUuid) {
		this.historiaClinicaUuid = historiaClinicaUuid;
	}
	public String getHistoriaClinicaNombre() {
		return historiaClinicaNombre;
	}
	public void setHistoriaClinicaNombre(String historiaClinicaNombre) {
		this.historiaClinicaNombre = historiaClinicaNombre;
	}
	public List<AntecedenteAmp> getAntecedentes() {
		return antecedentes;
	}
	public void setAntecedentes(List<AntecedenteAmp> antecedentes) {
		this.antecedentes = antecedentes;
	}
	
}
