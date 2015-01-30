package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.ArrayList;

import com.artica.telesalud.client.shared.ValuedItem;

public class AntecedenteData implements Serializable{

	private ArrayList<Antecedente> antecedentes;
	private ArrayList<ValuedItem<Integer, String>> tiposAntecedentes;
	
	public ArrayList<Antecedente> getAntecedentes() {
		return antecedentes;
	}
	public void setAntecedentes(ArrayList<Antecedente> antecedentes) {
		this.antecedentes = antecedentes;
	}
	public ArrayList<ValuedItem<Integer, String>> getTiposAntecedentes() {
		return tiposAntecedentes;
	}
	public void setTiposAntecedentes(ArrayList<ValuedItem<Integer, String>> tiposAntecedentes) {
		this.tiposAntecedentes = tiposAntecedentes;
	}
}
