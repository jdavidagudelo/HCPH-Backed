package com.artica.telesalud.tph.server.util;

import com.artica.telesalud.tph.lightweightmodel.Tripulacion;

public class CargarTripulacion {
	
	public static Tripulacion cargarDatos(com.artica.telesalud.tph.model.evento.Tripulacion tripulacion){
		
		Tripulacion trip = new Tripulacion();
		
		trip.setEvento(tripulacion.getEvento().getEventoId());
		trip.setHoraDespacho(tripulacion.getHoraDespacho());
		trip.setHoraRegreso(tripulacion.getHoraRegreso());
		trip.setHoraSitio(tripulacion.getHoraLlegada());
		trip.setPlaca(tripulacion.getPlaca());
		trip.setTripulacionId(tripulacion.getTripulacionId());
		
		return trip;
	}

}
