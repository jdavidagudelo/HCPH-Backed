package com.artica.telesalud.tph.alerta;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.artica.telesalud.tph.util.JsonDateSerializer;

public class AlertaVigilante {
	
	private String idPaciente;
	private Date fechaAlerta;
	private Integer conceptoInformacionAlerta;
	
	
	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getFechaAlerta() {
		return fechaAlerta;
	}

	public void setFechaAlerta(Date fechaAlerta) {
		this.fechaAlerta = fechaAlerta;
	}

	public Integer getConceptoInformacionAlerta() {
		return conceptoInformacionAlerta;
	}

	public void setConceptoInformacionAlerta(Integer conceptoInformacionAlerta) {
		this.conceptoInformacionAlerta = conceptoInformacionAlerta;
	}

	
}
