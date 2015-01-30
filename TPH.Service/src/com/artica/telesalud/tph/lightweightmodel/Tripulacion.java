package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Tripulacion implements Serializable {
	
	private Integer evento;
	private Integer tripulacionId;
	private String placa;
	private Date horaDespacho;
	private Date horaSitio;
	private Date horaRegreso;
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getPlaca() {
		return placa;
	}
	public void setHoraDespacho(Date horaDespacho) {
		this.horaDespacho = horaDespacho;
	}
	public Date getHoraDespacho() {
		return horaDespacho;
	}
	public void setHoraSitio(Date horaSitio) {
		this.horaSitio = horaSitio;
	}
	public Date getHoraSitio() {
		return horaSitio;
	}
	public Integer getTripulacionId() {
		return tripulacionId;
	}
	public void setTripulacionId(Integer tripulacionId) {
		this.tripulacionId = tripulacionId;
	}
	public Date getHoraRegreso() {
		return horaRegreso;
	}
	public void setHoraRegreso(Date horaRegreso) {
		this.horaRegreso = horaRegreso;
	}
	public Integer getEvento() {
		return evento;
	}
	public void setEvento(Integer evento) {
		this.evento = evento;
	}

}
