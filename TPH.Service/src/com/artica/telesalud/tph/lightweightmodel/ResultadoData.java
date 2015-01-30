package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;

public class ResultadoData implements Serializable{
	
	private String hallazgosClinicos;
	private String impresionDiagnostica;
	private Boolean respondienteCuidadano;
	private Boolean respondienteSocorrista;
	private Boolean respondienteMedico;
	private Boolean respondientePolicia;
	private Boolean respondienteBombero;
	private Boolean respondienteTransito;
	private Boolean respondienteFamiliar;
	
	
	public String getHallazgosClinicos() {
		return hallazgosClinicos;
	}
	public void setHallazgosClinicos(String hallazgosClinicos) {
		this.hallazgosClinicos = hallazgosClinicos;
	}
	public String getImpresionDiagnostica() {
		return impresionDiagnostica;
	}
	public void setImpresionDiagnostica(String impresionDiagnostica) {
		this.impresionDiagnostica = impresionDiagnostica;
	}
	public Boolean getRespondienteCuidadano() {
		return respondienteCuidadano;
	}
	public void setRespondienteCuidadano(Boolean respondienteCuidadano) {
		this.respondienteCuidadano = respondienteCuidadano;
	}
	public Boolean getRespondienteSocorrista() {
		return respondienteSocorrista;
	}
	public void setRespondienteSocorrista(Boolean respondienteSocorrista) {
		this.respondienteSocorrista = respondienteSocorrista;
	}
	public Boolean getRespondienteMedico() {
		return respondienteMedico;
	}
	public void setRespondienteMedico(Boolean respondienteMedico) {
		this.respondienteMedico = respondienteMedico;
	}
	public Boolean getRespondientePolicia() {
		return respondientePolicia;
	}
	public void setRespondientePolicia(Boolean respondientePolicia) {
		this.respondientePolicia = respondientePolicia;
	}
	public Boolean getRespondienteBombero() {
		return respondienteBombero;
	}
	public void setRespondienteBombero(Boolean respondienteBombero) {
		this.respondienteBombero = respondienteBombero;
	}
	public Boolean getRespondienteTransito() {
		return respondienteTransito;
	}
	public void setRespondienteTransito(Boolean respondienteTransito) {
		this.respondienteTransito = respondienteTransito;
	}
	public Boolean getRespondienteFamiliar() {
		return respondienteFamiliar;
	}
	public void setRespondienteFamiliar(Boolean respondienteFamiliar) {
		this.respondienteFamiliar = respondienteFamiliar;
	}
}
