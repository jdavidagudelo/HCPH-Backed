package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.artica.telesalud.client.shared.ValuedItem;

public class TeleasistenciaData implements Serializable {

	private Integer teleasistenciaId;
	private Date fecha;
	private String motivoSolicitud;
	private String detalles;
	private Integer medio;
	private ArrayList<NotaEvolucion> notasEvolucion;
	private ValuedItem<Integer, String> medicoSolicitante;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMotivoSolicitud() {
		return motivoSolicitud;
	}
	public void setMotivoSolicitud(String motivoSolicitud) {
		this.motivoSolicitud = motivoSolicitud;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	public Integer getMedio() {
		return medio;
	}
	public void setMedio(Integer medio) {
		this.medio = medio;
	}
	public ArrayList<NotaEvolucion> getNotasEvolucion() {
		return notasEvolucion;
	}
	public void setNotasEvolucion(ArrayList<NotaEvolucion> notasEvolucion) {
		this.notasEvolucion = notasEvolucion;
	}
	public Integer getTeleasistenciaId() {
		return teleasistenciaId;
	}
	public void setTeleasistenciaId(Integer teleasistenciaId) {
		this.teleasistenciaId = teleasistenciaId;
	}
	public ValuedItem<Integer, String> getMedicoSolicitante() {
		return medicoSolicitante;
	}
	public void setMedicoSolicitante(ValuedItem<Integer, String> medicoSolicitante) {
		this.medicoSolicitante = medicoSolicitante;
	}
}
