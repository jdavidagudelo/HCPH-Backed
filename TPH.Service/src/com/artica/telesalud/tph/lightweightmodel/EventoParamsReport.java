package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.Date;

/**
 * Esta clase representa la consulta que se hace para los eventos
 * 
 * @author Sebastian
 * 
 */
@SuppressWarnings("serial")
public class EventoParamsReport implements Serializable {
	private Date fechaDesde;
	private Date fechaHasta;
	private String numeroCaso;
	private String estado;
	private Integer departamento;
	private Integer ciudad;
	private Integer causa;

	/**
	 * constructor vacio
	 */
	public EventoParamsReport() {
	}

	/**
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param numeroCaso
	 * @param estado
	 * @param departamento
	 * @param ciudad
	 * @param causa
	 */
	public EventoParamsReport(Date fechaDesde, Date fechaHasta,
			String numeroCaso, String estado, Integer departamento,
			Integer ciudad, Integer causa) {
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.numeroCaso = numeroCaso;
		this.estado = estado;
		this.departamento = departamento;
		this.ciudad = ciudad;
		this.setCausa(causa);
	}

	/**
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta
	 *            the fechaHasta to set
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @return the numeroCaso
	 */
	public String getNumeroCaso() {
		return numeroCaso;
	}

	/**
	 * @param numeroCaso
	 *            the numeroCaso to set
	 */
	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the departamento
	 */
	public Integer getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento
	 *            the departamento to set
	 */
	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the ciudad
	 */
	public Integer getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad
	 *            the ciudad to set
	 */
	public void setCiudad(Integer ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the causa
	 */
	public Integer getCausa() {
		return causa;
	}

	/**
	 * @param causa
	 *            the causa to set
	 */
	public void setCausa(Integer causa) {
		this.causa = causa;
	}
}
