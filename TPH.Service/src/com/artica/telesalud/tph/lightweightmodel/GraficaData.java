package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.ArrayList;

import com.artica.telesalud.client.shared.ValuedItem;

@SuppressWarnings("serial")
public class GraficaData implements Serializable {

	private String nombreCausa;
	private ArrayList<ValuedItem<Integer, String>> datosCausa;
	public void setNombreCausa(String nombreCausa) {
		this.nombreCausa = nombreCausa;
	}
	public String getNombreCausa() {
		return nombreCausa;
	}
	public void setDatosCausa(ArrayList<ValuedItem<Integer, String>> datosCausa) {
		this.datosCausa = datosCausa;
	}
	public ArrayList<ValuedItem<Integer, String>> getDatosCausa() {
		return datosCausa;
	}
}
