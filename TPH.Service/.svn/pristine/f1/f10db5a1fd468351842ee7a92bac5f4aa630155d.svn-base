package com.artica.telesalud.tph.server.util;

import java.util.List;

import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.lightweightmodel.EvaluacionData;
import com.artica.telesalud.tph.model.observation.ObservationData;

public class CargarEvaluacion {

	public static EvaluacionData cargarEvaluacion(List<ObservationData> datos){
	
		
		ConceptsCode concepts = new ConceptsCode();
		
		EvaluacionData evaluacionData = new EvaluacionData();
		
		ObservationData obsData = ObservationData.obtenerValor(datos, concepts.cPermeabilidadViaArea());
		if(obsData != null){
			if(obsData.getValueCoded()!=null){
				if(obsData.getValueCoded().equals(concepts.cViaAereaObstruida()))
					evaluacionData.setPermeavilidadViaAerea(EvaluacionData.VIA_AEREA_OBSTRUIDA);
				else if(obsData.getValueCoded().equals(concepts.cViaAereaPermeable()))
					evaluacionData.setPermeavilidadViaAerea(EvaluacionData.VIA_AEREA_PERMEABLE);
				
			}
			
		}
		
		evaluacionData.setCausaObstruccionVia((String)ObservationData.obtenerValorConcepto(datos, concepts.cCausaObstruccionViaAerea()));
		
		obsData = ObservationData.obtenerValor(datos, concepts.cValoracionPrimariaRespiracion());
		
		if(obsData != null){
			
			if(obsData.getValueCoded() != null){
				if(obsData.getValueCoded().equals(concepts.cRespiracionAusente()))
					evaluacionData.setRespiracion(EvaluacionData.RESPIRACION_AUSENTE);
				else if(obsData.getValueCoded().equals(concepts.cRespiracionDificultosa()))
					evaluacionData.setRespiracion(EvaluacionData.RESPIRACION_DIFICULTOSA);
				else if(obsData.getValueCoded().equals(concepts.cRespiracionSuperficial()))
					evaluacionData.setRespiracion(EvaluacionData.RESPIRACION_SUPERFICIAL);
				else if(obsData.getValueCoded().equals(concepts.CRespiracionNormal()))
					evaluacionData.setRespiracion(EvaluacionData.RESPIRACION_NORMAL);
			}
		}
		
		obsData = ObservationData.obtenerValor(datos, concepts.cValoracioPrimariaPulso());
		
		if(obsData != null){
			if(obsData.getValueCoded() != null){
				if(obsData.getValueCoded().equals(concepts.cPulsoAusente()))
					evaluacionData.setPulso(EvaluacionData.PULSO_AUSENTE);
				else if(obsData.getValueCoded().equals(concepts.cPulsoPresente()))
					evaluacionData.setPulso(EvaluacionData.PULSO_PRESENTE);
			}
		}
		
		obsData = ObservationData.obtenerValor(datos, concepts.cRitmoPulso());
		
		if(obsData != null){
			
			if(obsData.getValueCoded() != null){
				if(obsData.getValueCoded().equals(concepts.cRitmoRitmico()))
					evaluacionData.setRitmoPulso(EvaluacionData.PULSO_RITMICO);
				else if(obsData.getValueCoded().equals(concepts.cPulsoAritmico()))
					evaluacionData.setRitmoPulso(EvaluacionData.PULSO_ARITMICO);
			}
		}
		
		obsData = ObservationData.obtenerValor(datos, concepts.cFuerzaPulso());
		
		if(obsData != null){
			
			if(obsData.getValueCoded() != null){
				if(obsData.getValueCoded().equals(concepts.cPulsoFuerte()))
					evaluacionData.setFuerzaPulso(EvaluacionData.PULSO_FUERTE);
				else if(obsData.getValueCoded().equals(concepts.cPulsoDebil()))
					evaluacionData.setFuerzaPulso(EvaluacionData.PULSO_DEBIL);
			}
		}
		
		obsData = ObservationData.obtenerValor(datos, concepts.cNivelRespuestaValoracionPrimaria());
		
		if(obsData != null){
			
			if(obsData.getValueCoded() != null){
				if(obsData.getValueCoded().equals(concepts.cNivelRespuestaAlerta()))
					evaluacionData.setNivelRespuesta(EvaluacionData.NIVEL_RESPUESTA_ALERTA);
				else if(obsData.getValueCoded().equals(concepts.cNivelRespuestaDolor()))
					evaluacionData.setNivelRespuesta(EvaluacionData.NIVEL_RESPUESTA_DOLOR);
				else if(obsData.getValueCoded().equals(concepts.cNivelRespuestaLlamado()))
					evaluacionData.setNivelRespuesta(EvaluacionData.NIVEL_RESPUESTA_LLAMADO);
				else if(obsData.getValueCoded().equals(concepts.cSinNivelRespuesta()))
					evaluacionData.setNivelRespuesta(EvaluacionData.NIVEL_RESPUESTA_SIN_RESPUESTA);
			}
			
		}
		
		evaluacionData.setPielCaliente((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPielCaliente()));
		evaluacionData.setPielCianotica((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPielCrianotica()));
		evaluacionData.setPielEnrojecida((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPielEnrojecida()));
		evaluacionData.setPielHumeda((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPielHumeda()));
		evaluacionData.setPielNormal((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPielNormal()));
		evaluacionData.setPielPalida((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPielPalidaFria()));
		evaluacionData.setPielSeca((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPielSeca()));
		
		evaluacionData.setPupilasArisocoricas((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPupilasArisocoricas()));
		evaluacionData.setPupilasIsocoricas((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPielPupilasIsocoricas()));
		evaluacionData.setPupilasMidriaticas((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPupilasMidriaticas()));
		evaluacionData.setPupilasNoEvaluables((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPupilasNoEvaluables()));
		evaluacionData.setPupilasNoReactivas((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPupilasNoReactivas()));
		evaluacionData.setPupilasProtesisDerecha((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPupilasProtesisDerecha()));
		evaluacionData.setPupilasProtesisIzquierda((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPupilasProtesisIzquierda()));
		evaluacionData.setPupilasReactivas((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPupilasReactivas()));
		evaluacionData.setPupilasMioticas((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cPupilasMioticas()));
		
		Double valor = (Double)ObservationData.obtenerValorConcepto(datos, concepts.cGlasgowRM());
		if(valor != null)
			evaluacionData.setGlasgowRM(valor.intValue());
		
		valor = (Double)ObservationData.obtenerValorConcepto(datos, concepts.cGlasgowRO());
		if(valor != null)
			evaluacionData.setGlasgowRO(valor.intValue());
		
		valor = (Double)ObservationData.obtenerValorConcepto(datos, concepts.cGlasgowRV());
		if(valor != null)
			evaluacionData.setGlasgowRV(valor.intValue());
		
		obsData = ObservationData.obtenerValor(datos, concepts.cTipoEmergenciaMedica());
		
		if(obsData != null){
			
			if(obsData.getValueCoded() != null){
				if(obsData.getValueCoded().equals(concepts.cNeurologia()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_NEUROLOGIA);
				else if(obsData.getValueCoded().equals(concepts.cParoCardiaco()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_PARO_CARDIACO);
				else if(obsData.getValueCoded().equals(concepts.cOrganosSentidos()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_ORGANOS_SENTIDOS);
				else if(obsData.getValueCoded().equals(concepts.cCardiovascular()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_CARDIOVASCULAR);
				else if(obsData.getValueCoded().equals(concepts.cGastrointestinal()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_GASTROINTESTINAL);
				else if(obsData.getValueCoded().equals(concepts.cGenitourinario()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_GENITOURINARIO);
				else if(obsData.getValueCoded().equals(concepts.cGnicoObstetrico()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_GINECOOBSTETRICO);
				else if(obsData.getValueCoded().equals(concepts.cShock()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_SHOCK);
				else if(obsData.getValueCoded().equals(concepts.cIntoxicacion()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_INTOXICACION);
				else if(obsData.getValueCoded().equals(concepts.cPsiquiatrica()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_SIQUIATRICA);
				else if(obsData.getValueCoded().equals(concepts.cOvace()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_OVACE);
				else if(obsData.getValueCoded().equals(concepts.cTermica()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_TERMICA);
				else if(obsData.getValueCoded().equals(concepts.cEnfermedadComun()))
					evaluacionData.setTipoEmergencia(EvaluacionData.EMERGENCIA_ENFERMEDAD_COMUN);
			}
			
		}
		
		evaluacionData.setPolitrauma((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaPolitrauma()));
		evaluacionData.setTec((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaTEC()));
		evaluacionData.setMaxilofacial((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaMaxilofacial()));
		evaluacionData.setRaquimedular((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaRaquimedular()));
		evaluacionData.setTorax((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaTorax()));
		evaluacionData.setAbdomen((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaAbdomen()));
		evaluacionData.setPelvico((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaPelvico()));
		evaluacionData.setExtremidadSuperior((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaExtremidadSuperior()));
		evaluacionData.setTejidosBlandos((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaTejidosBlandos()));
		evaluacionData.setOsteomuscular((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaOsteomuscular()));
		evaluacionData.setExtremidadInferior((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaExtremidadInferior()));
		evaluacionData.setOrganosSentidos((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cTraumaOrganosSentidos()));
		evaluacionData.setOtroTrauma((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cOtroTrauma()));
		evaluacionData.setCualOtroTrauma((String)ObservationData.obtenerValorConcepto(datos, concepts.cCualOtroTrauma()));
		
		obsData = ObservationData.obtenerValor(datos, concepts.cPrioridadTriage());
		
		if(obsData != null){
			
			if(obsData.getValueCoded() != null){
				if(obsData.getValueCoded().equals(concepts.cPrioridadTriageAmarillo()))
					evaluacionData.setPrioridadTriage(EvaluacionData.PRIORIDAD_TRIAGE_AMARILLO);
				else if(obsData.getValueCoded().equals(concepts.cPrioridadTriageBlanco()))
					evaluacionData.setPrioridadTriage(EvaluacionData.PRIORIDAD_TRIAGE_BLANCO);
				else if(obsData.getValueCoded().equals(concepts.cPrioridadTriageNegro()))
					evaluacionData.setPrioridadTriage(EvaluacionData.PRIORIDAD_TRIAGE_NEGRO);
				else if(obsData.getValueCoded().equals(concepts.cPrioridadTriageRojo()))
					evaluacionData.setPrioridadTriage(EvaluacionData.PRIORIDAD_TRIAGE_ROJO);
				else if(obsData.getValueCoded().equals(concepts.cPrioridadTriageVerde()))
					evaluacionData.setPrioridadTriage(EvaluacionData.PRIORIDAD_TRIAGE_VERDE);
			}
		}
		
		return evaluacionData;
		
	}
}
