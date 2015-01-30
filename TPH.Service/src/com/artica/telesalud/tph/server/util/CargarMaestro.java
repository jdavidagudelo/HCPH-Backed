package com.artica.telesalud.tph.server.util;

import java.util.ArrayList;
import java.util.List;

import com.artica.telesalud.client.shared.ValuedItem;
import com.artica.telesalud.tph.lightweightmodel.Maestro;
import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.evento.Hospital;

public class CargarMaestro {
	
	public static ArrayList<Maestro> cargarDatos(List<Concept> conceptos, Boolean hasCode) {
		
		ArrayList<Maestro> maestros = new ArrayList<Maestro>();
		
		for(Concept concepto : conceptos) {
			
			Maestro data = new Maestro();
			data.setInactivo((int)concepto.getRetired());
			
//			if(hasCode) {
//				data.setCodigo(concepto.getShortName());
//				data.setNombre(concepto.getDescription());
//			} else {
				data.setNombre(concepto.getShortName());
				data.setDescripcion(concepto.getDescription());
//			}
			data.setId(concepto.getConceptId());
			
			maestros.add(data);
		}
		
		return maestros;	
	}

	public static ArrayList<Maestro> cargarDatosHospital(List<Hospital> hospitales) {
		
		ArrayList<Maestro> maestros = new ArrayList<Maestro>();
		
		for(Hospital hospital : hospitales) {
			
			Maestro data = new Maestro();
			data.setInactivo((int)hospital.getVoided());
			data.setNombre(hospital.getNombre());
			if(hospital.getNivelComplejidad() != null)
			data.setNivel(hospital.getNivelComplejidad().getConceptId());
			data.setDescripcion(hospital.getDireccion());
			data.setId(hospital.getHospitalId());
			
			maestros.add(data);
		}
		
		return maestros;	
	}
	
	public static ArrayList<ValuedItem<Integer, String>> cargarNiveles(List<Concept> conceptos) {

		ArrayList<ValuedItem<Integer, String>> niveles = new ArrayList<ValuedItem<Integer, String>>();
		for(Concept concepto : conceptos){
			niveles.add(new ValuedItem<Integer, String>(concepto.getConceptId(), concepto.getShortName()));
		}
		
		return niveles;
	}

}
