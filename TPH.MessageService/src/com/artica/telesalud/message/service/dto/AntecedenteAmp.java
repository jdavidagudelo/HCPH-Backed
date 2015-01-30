package com.artica.telesalud.message.service.dto;

import java.io.Serializable;
import java.util.List;
/**
 * Clase utilizada para almacenar los antecedentes de un paciente 
 * obtenidos a partir de una historia clínica externa.
 * @author Juan David Agudelo Alvarez jdaaa2009@gmail.com
 *
 */
public class AntecedenteAmp implements Serializable{
	/**
	 * Id utilizado para la serializacion.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * El tipo de antecedente. Puede ser una alergia, patología, medicamento, etc. 
	 */
	public String tipoAntecedente;
	/**
	 * Lista de descripciones de cada uno de los antecedentes del tipo especificado en el atributo 
	 * tipoAntecedente.
	 */
	public List<String> listaAntecedentes;
	/**
	 * Lista de las fechas de los antecedentes
	 */
	public List<String> listaFechas; 
	
	
	public String getTipoAntecedente() {
		return tipoAntecedente;
	}
	public void setTipoAntecedente(String tipoAntecedente) {
		this.tipoAntecedente = tipoAntecedente;
	}
	public List<String> getListaAntecedentes() {
		return listaAntecedentes;
	}
	public void setListaAntecedentes(List<String> listaAntecedentes) {
		this.listaAntecedentes = listaAntecedentes;
	}
	public List<String> getListaFechas() {
		return listaFechas;
	}
	public void setListaFechas(List<String> listaFechas) {
		this.listaFechas = listaFechas;
	}
	
}
