package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.ArrayList;

import com.artica.telesalud.client.shared.ValuedItem;

@SuppressWarnings("serial")
public class ReporteData implements Serializable {

	private ArrayList<ValuedItem<Integer, String>> tipoDocumentos;
	private ArrayList<ValuedItem<Integer, String>> departamentos;
	private ArrayList<ValuedItem<Integer, String>> tipoEventos;
	public void setTipoDocumentos(ArrayList<ValuedItem<Integer, String>> tipoDocumentos) {
		this.tipoDocumentos = tipoDocumentos;
	}
	public ArrayList<ValuedItem<Integer, String>> getTipoDocumentos() {
		return tipoDocumentos;
	}
	public void setDepartamentos(ArrayList<ValuedItem<Integer, String>> departamentos) {
		this.departamentos = departamentos;
	}
	public ArrayList<ValuedItem<Integer, String>> getDepartamentos() {
		return departamentos;
	}
	public void setTipoEventos(ArrayList<ValuedItem<Integer, String>> tipoEventos) {
		this.tipoEventos = tipoEventos;
	}
	public ArrayList<ValuedItem<Integer, String>> getTipoEventos() {
		return tipoEventos;
	}
}
