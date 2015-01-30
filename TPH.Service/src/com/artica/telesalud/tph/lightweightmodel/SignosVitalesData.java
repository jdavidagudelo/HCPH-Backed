package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.Date;

public class SignosVitalesData implements Serializable{

	private Date fecha;
	private Double respiracion;
	private Double paDiastolica;
	private Double paSistolica;
	private Double glicemia;
	private Double pulso;
	private Double temperatura;
	private Double spo2;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getRespiracion() {
		return respiracion;
	}
	public void setRespiracion(Double respiracion) {
		this.respiracion = respiracion;
	}
	public Double getPaDiastolica() {
		return paDiastolica;
	}
	public void setPaDiastolica(Double paDiastolica) {
		this.paDiastolica = paDiastolica;
	}
	public Double getPaSistolica() {
		return paSistolica;
	}
	public void setPaSistolica(Double paSistolica) {
		this.paSistolica = paSistolica;
	}
	public Double getGlicemia() {
		return glicemia;
	}
	public void setGlicemia(Double glicemia) {
		this.glicemia = glicemia;
	}
	public Double getPulso() {
		return pulso;
	}
	public void setPulso(Double pulso) {
		this.pulso = pulso;
	}
	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}
	public Double getTemperatura() {
		return temperatura;
	}
	public void setSpo2(Double spo2) {
		this.spo2 = spo2;
	}
	public Double getSpo2() {
		return spo2;
	}
}
