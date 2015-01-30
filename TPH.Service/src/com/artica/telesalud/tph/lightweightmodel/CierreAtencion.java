package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;

import com.artica.telesalud.client.shared.ValuedItem;

public class CierreAtencion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer TIPO_CIERRE_APROBACION = 1;
	public static final Integer TIPO_CIERRE_NOAPROBACION = 2;
	public static final Integer TIPO_CIERRE_ANULACION = 3;
	
	private Integer tipoCierre;
	private String nota;
	private ValuedItem<Integer, String> usuarioCierra;
	
	
	public Integer getTipoCierre() {
		return tipoCierre;
	}
	public void setTipoCierre(Integer tipoCierre) {
		this.tipoCierre = tipoCierre;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public ValuedItem<Integer, String> getUsuarioCierra() {
		return usuarioCierra;
	}
	public void setUsuarioCierra(ValuedItem<Integer, String> usuarioCierra) {
		this.usuarioCierra = usuarioCierra;
	}
}
