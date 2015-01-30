package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.ArrayList;

import com.artica.telesalud.client.shared.ValuedItem;

public class TeleasistenciaCompleteData implements Serializable{

	private ArrayList<TeleasistenciaData> teleasistencias;
	private ArrayList<ValuedItem<Integer, String>> medios;
	
	
	public ArrayList<TeleasistenciaData> getTeleasistencias() {
		return teleasistencias;
	}
	public void setTeleasistencias(ArrayList<TeleasistenciaData> teleasistencias) {
		this.teleasistencias = teleasistencias;
	}
	public ArrayList<ValuedItem<Integer, String>> getMedios() {
		return medios;
	}
	public void setMedios(ArrayList<ValuedItem<Integer, String>> medios) {
		this.medios = medios;
	}
	
	
	
	
}
