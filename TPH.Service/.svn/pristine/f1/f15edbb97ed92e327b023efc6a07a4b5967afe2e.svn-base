package com.artica.telesalud.tph.server.util;

import java.util.ArrayList;
import java.util.List;

import com.artica.telesalud.client.shared.ValuedItem;
import com.artica.telesalud.tph.model.evento.Hospital;

public class CargarHospital {

	public static ArrayList<ValuedItem<Integer, String>> cargarHospitales(List<Hospital> hospitales){
		
		ArrayList<ValuedItem<Integer, String>> lista = new ArrayList<ValuedItem<Integer,String>>();
		
		for(Hospital hospital : hospitales){
			lista.add(new ValuedItem<Integer, String>(hospital.getHospitalId(), hospital.getNombre()));
		}
		
		return lista;
		
	}
	
}
