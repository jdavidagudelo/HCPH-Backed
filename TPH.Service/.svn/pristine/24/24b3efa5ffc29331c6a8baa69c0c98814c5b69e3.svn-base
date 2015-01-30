package com.artica.telesalud.tph.server.util;

import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.lightweightmodel.EventoSimple;
import com.artica.telesalud.tph.model.evento.Evento;

public class CargarEvento {
	
	public static EventoSimple cargarDatos(Evento evento){
		
		EventoSimple eventoSimple = new EventoSimple();
		ConceptsCode conceptos = new ConceptsCode();
		
		eventoSimple.setCaso(evento.getNumeroCaso());
		
		if(evento.getCausaAtencion()!=null){
			eventoSimple.setCausa(evento.getCausaAtencion().getConceptId());
			eventoSimple.setNombreCausa(evento.getCausaAtencion().getShortName());
		}
		
		if(evento.getZona()!=null){
			if(evento.getZona().getConceptId().equals(conceptos.cZonaRural()))
				eventoSimple.setZona(EventoSimple.ZONA_RURAL);
			else if(evento.getZona().getConceptId().equals(conceptos.cZonaUrbana()))
				eventoSimple.setZona(EventoSimple.ZONA_URBANA);
		}
		
		if(evento.getCiudad()!=null){
			
			eventoSimple.setCiudad(evento.getCiudad().getCityId());
			eventoSimple.setDepartamento(evento.getCiudad().getStateProvince().getStateProvinceId());
			eventoSimple.setNombreDepartamento(evento.getCiudad().getStateProvince().getName());
			eventoSimple.setNombreCiudad(evento.getCiudad().getName());
		}
		
		eventoSimple.setDireccion(evento.getDireccion());
		eventoSimple.setEstado(evento.getEstado());
		eventoSimple.setFechaHora(evento.getFechaLlamada());
		eventoSimple.setId(evento.getEventoId());
		
		return eventoSimple;
		
	}

}
