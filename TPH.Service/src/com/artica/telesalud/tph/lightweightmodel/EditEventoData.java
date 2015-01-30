package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.ArrayList;

import com.artica.telesalud.client.shared.ValuedItem;

@SuppressWarnings("serial")
public class EditEventoData implements Serializable{

	private EventoSimple evento;
	private ArrayList<ValuedItem<Integer, String>> departamentos;
	private ArrayList<ValuedItem<Integer, String>> causas;

	public EventoSimple getEvento() {
		return evento;
	}

	public void setEvento(EventoSimple evento) {
		this.evento = evento;
	}

	public ArrayList<ValuedItem<Integer, String>> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(ArrayList<ValuedItem<Integer, String>> departamentos) {
		this.departamentos = departamentos;
	}

	public ArrayList<ValuedItem<Integer, String>> getCausas() {
		return causas;
	}

	public void setCausas(ArrayList<ValuedItem<Integer, String>> causas) {
		this.causas = causas;
	}

	
}
