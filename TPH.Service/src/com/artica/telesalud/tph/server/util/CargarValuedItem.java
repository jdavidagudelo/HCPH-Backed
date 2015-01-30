package com.artica.telesalud.tph.server.util;

import java.util.ArrayList;
import java.util.List;

import com.artica.telesalud.client.shared.ValuedItem;
import com.artica.telesalud.persona.model.impl.RoleDTO;
import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.evento.Tripulacion;
import com.artica.telesalud.tph.model.location.StateProvince;

public class CargarValuedItem {
	
	public static ArrayList<ValuedItem<Integer, String>> cargarDepartamentos(List<StateProvince> deptos){
		
		ArrayList<ValuedItem<Integer, String>> lista = new ArrayList<ValuedItem<Integer, String>>();
		for(StateProvince depto : deptos){
			lista.add(new ValuedItem<Integer, String>(depto.getStateProvinceId(), depto.getName()));
		}
		
		return lista;
	}
	
	public static ArrayList<ValuedItem<Integer, String>> cargarConceptos(List<Concept> conceptos){
		
		ArrayList<ValuedItem<Integer, String>> lista = new ArrayList<ValuedItem<Integer, String>>();
		for(Concept concept : conceptos){
			lista.add(new ValuedItem<Integer, String>(concept.getConceptId(), concept.getShortName()));
		}
		
		return lista;
	}
	
	public static ArrayList<ValuedItem<Integer, String>> cargarPlacas(List<Tripulacion> tripulaciones){
		
		
		ArrayList<ValuedItem<Integer, String>> lista = new ArrayList<ValuedItem<Integer, String>>();
		for(Tripulacion tripulacion : tripulaciones){
			lista.add(new ValuedItem<Integer, String>(tripulacion.getTripulacionId(), tripulacion.getPlaca()));
		}
				
		return lista;
	}
	
public static ArrayList<ValuedItem<Integer, String>> cargarRoles(List<RoleDTO> roles){
		
		
		ArrayList<ValuedItem<Integer, String>> lista = new ArrayList<ValuedItem<Integer, String>>();
		for(RoleDTO role : roles){
			lista.add(new ValuedItem<Integer, String>(role.getRoleId(), role.getName()));
		}
				
		return lista;
	}

}
