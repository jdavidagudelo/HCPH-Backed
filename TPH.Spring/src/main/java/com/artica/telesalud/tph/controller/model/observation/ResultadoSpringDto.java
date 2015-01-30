package com.artica.telesalud.tph.controller.model.observation;

import com.artica.telesalud.tph.controller.model.evento.LesionadoSpringDto;
/**
 * Clase utilizada para mapear resultados a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class ResultadoSpringDto {
	private String hallazgosClinicos;
	private String impresionDiagnostica;
	private Boolean respondienteCuidadano;
	private Boolean respondienteSocorrista;
	private Boolean respondienteMedico;
	private Boolean respondientePolicia;
	private Boolean respondienteBombero;
	private Boolean respondienteTransito;
	private Boolean respondienteFamiliar;
	private LesionadoSpringDto lesionado;
	public ResultadoSpringDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the lesionado
	 */
	public LesionadoSpringDto getLesionado() {
		return lesionado;
	}

	/**
	 * @param lesionado the lesionado to set
	 */
	public void setLesionado(LesionadoSpringDto lesionado) {
		this.lesionado = lesionado;
	}

	/**
	 * @return the hallazgosClinicos
	 */
	public String getHallazgosClinicos() {
		return hallazgosClinicos;
	}
	/**
	 * @param hallazgosClinicos the hallazgosClinicos to set
	 */
	public void setHallazgosClinicos(String hallazgosClinicos) {
		this.hallazgosClinicos = hallazgosClinicos;
	}
	/**
	 * @return the impresionDiagnostica
	 */
	public String getImpresionDiagnostica() {
		return impresionDiagnostica;
	}
	/**
	 * @param impresionDiagnostica the impresionDiagnostica to set
	 */
	public void setImpresionDiagnostica(String impresionDiagnostica) {
		this.impresionDiagnostica = impresionDiagnostica;
	}
	/**
	 * @return the respondienteCuidadano
	 */
	public Boolean getRespondienteCuidadano() {
		return respondienteCuidadano;
	}
	/**
	 * @param respondienteCuidadano the respondienteCuidadano to set
	 */
	public void setRespondienteCuidadano(Boolean respondienteCuidadano) {
		this.respondienteCuidadano = respondienteCuidadano;
	}
	/**
	 * @return the respondienteSocorrista
	 */
	public Boolean getRespondienteSocorrista() {
		return respondienteSocorrista;
	}
	/**
	 * @param respondienteSocorrista the respondienteSocorrista to set
	 */
	public void setRespondienteSocorrista(Boolean respondienteSocorrista) {
		this.respondienteSocorrista = respondienteSocorrista;
	}
	/**
	 * @return the respondienteMedico
	 */
	public Boolean getRespondienteMedico() {
		return respondienteMedico;
	}
	/**
	 * @param respondienteMedico the respondienteMedico to set
	 */
	public void setRespondienteMedico(Boolean respondienteMedico) {
		this.respondienteMedico = respondienteMedico;
	}
	/**
	 * @return the respondientePolicia
	 */
	public Boolean getRespondientePolicia() {
		return respondientePolicia;
	}
	/**
	 * @param respondientePolicia the respondientePolicia to set
	 */
	public void setRespondientePolicia(Boolean respondientePolicia) {
		this.respondientePolicia = respondientePolicia;
	}
	/**
	 * @return the respondienteBombero
	 */
	public Boolean getRespondienteBombero() {
		return respondienteBombero;
	}
	/**
	 * @param respondienteBombero the respondienteBombero to set
	 */
	public void setRespondienteBombero(Boolean respondienteBombero) {
		this.respondienteBombero = respondienteBombero;
	}
	/**
	 * @return the respondienteTransito
	 */
	public Boolean getRespondienteTransito() {
		return respondienteTransito;
	}
	/**
	 * @param respondienteTransito the respondienteTransito to set
	 */
	public void setRespondienteTransito(Boolean respondienteTransito) {
		this.respondienteTransito = respondienteTransito;
	}
	/**
	 * @return the respondienteFamiliar
	 */
	public Boolean getRespondienteFamiliar() {
		return respondienteFamiliar;
	}
	/**
	 * @param respondienteFamiliar the respondienteFamiliar to set
	 */
	public void setRespondienteFamiliar(Boolean respondienteFamiliar) {
		this.respondienteFamiliar = respondienteFamiliar;
	}
	
}
