package com.artica.telesalud.tph.server.util;

import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.lightweightmodel.PersonaLesionada;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.service.PatientService;

public class CargarLesionado {

	public static PersonaLesionada cargarDatos(PatientService patientService, Lesionado lesionado){
		PersonaLesionada personaLesionada = new PersonaLesionada();
		ConceptsCode concepts = new ConceptsCode();
		
		if(lesionado.getAseguradora()!=null)
			personaLesionada.setAseguradora(lesionado.getAseguradora().getConceptId());
		if(lesionado.getEncuentro()!=null)
			personaLesionada.setEncuentro(lesionado.getEncuentro().getEncounterId());
		if(lesionado.getEntidadRecepcion()!=null)
			personaLesionada.setEntidadRecepcion(lesionado.getEntidadRecepcion().getHospitalId());
		if(lesionado.getEps()!=null)
			personaLesionada.setEps(lesionado.getEps().getConceptId());
		if(lesionado.getEvento()!=null)
			personaLesionada.setEvento(lesionado.getEvento().getEventoId());
		
		personaLesionada.setLesionadoId(lesionado.getLesionadoId());
		personaLesionada.setNiegaAtencion(lesionado.getNiegaAtencion());
		if(lesionado.getResultado()!=null){
			
			if(lesionado.getResultado().getConceptId().equals(concepts.cTransporteHospital()))
				personaLesionada.setResultado(PersonaLesionada.RESULTADO_TRANSPORTE_HOSPITAL);
			else if(lesionado.getResultado().getConceptId().equals(concepts.cDeAltaSitio()))
				personaLesionada.setResultado(PersonaLesionada.RESULTADO_ALTA_SITIO);
			else if(lesionado.getResultado().getConceptId().equals(concepts.cEntregaOtroArrib()))
				personaLesionada.setResultado(PersonaLesionada.RESULTADO_ENTREGA_OTRO);
			else if(lesionado.getResultado().getConceptId().equals(concepts.cRCCPExitosa()))
				personaLesionada.setResultado(PersonaLesionada.RESULTADO_RCCP_EXITOSA);
			else if(lesionado.getResultado().getConceptId().equals(concepts.cFalleceTraslado()))
				personaLesionada.setResultado(PersonaLesionada.RESULTADO_FALLECE_TRASLADO);
			else if(lesionado.getResultado().getConceptId().equals(concepts.cFalleceHospital()))
				personaLesionada.setResultado(PersonaLesionada.RESULTADO_FALLECE_HOSPITAL);
			else if(lesionado.getResultado().getConceptId().equals(concepts.cFalleceSitio()))
				personaLesionada.setResultado(PersonaLesionada.RESULTADO_FALLECE_SITIO);
			else if(lesionado.getResultado().getConceptId().equals(concepts.cSeNiegaAtencion()))
				personaLesionada.setResultado(PersonaLesionada.RESULTADO_NEGACION);

			personaLesionada.setNombreResultado(lesionado.getResultado().getShortName());
		}
		
		if(lesionado.getTipoNegacion()!=null){
			
			if(lesionado.getTipoNegacion().getConceptId().equals(concepts.cNiegaAtencion()))
				personaLesionada.setTipoNegacion(PersonaLesionada.NEGACION_ATENCION);
			else if(lesionado.getTipoNegacion().getConceptId().equals(concepts.cNiegaRemision()))
				personaLesionada.setTipoNegacion(PersonaLesionada.NEGACION_REMISION);

		}
		
		if(lesionado.getDispositivoTransporte()!=null){
			if(lesionado.getDispositivoTransporte().getConceptId().equals(concepts.cCamillaLona()))
				personaLesionada.setDispositivoTransporte(PersonaLesionada.DISPOSITIVO_CAMILLA_LONA);
			else if(lesionado.getDispositivoTransporte().getConceptId().equals(concepts.cCamillaMiller()))
				personaLesionada.setDispositivoTransporte(PersonaLesionada.DISPOSITIVO_CAMILLA_MILLER);
			else if(lesionado.getDispositivoTransporte().getConceptId().equals(concepts.cCamillaRigida()))
				personaLesionada.setDispositivoTransporte(PersonaLesionada.DISPOSITIVO_CAMILLA_RIGIDA);
			else if(lesionado.getDispositivoTransporte().getConceptId().equals(concepts.cCamillaScoop()))
				personaLesionada.setDispositivoTransporte(PersonaLesionada.DISPOSITIVO_CAMILLA_SCOOP);
		}
		personaLesionada.setObservacionNegacion(lesionado.getObservacionNegacion());
		
		if(lesionado.getTripulacion()!=null)
			personaLesionada.setPlacaTripulacion(lesionado.getTripulacion().getPlaca());
		if(lesionado.getPlanBeneficios()!=null)
			personaLesionada.setPlanBeneficios(lesionado.getPlanBeneficios().getConceptId());
		personaLesionada.setRecibidoPor(lesionado.getRecibidoPor());
		personaLesionada.setRegistroRecibe(lesionado.getRegistroRecibe());
		
		if(lesionado.getTripulacion()!=null)
		personaLesionada.setTripulacion(lesionado.getTripulacion().getTripulacionId());
		if(!(lesionado.getEncuentro()==null || lesionado.getEncuentro().getPatient()==null)){
		
			Patient paciente = patientService.buscarPatient(lesionado.getEncuentro().getPatient().getPatientId());
			String identifierType = "";
			if(paciente.getPreferredIdentifier() != null)
			{
				if(paciente.getPreferredIdentifier().getPatientIdentifierType() != null){
					identifierType = String.valueOf(paciente.getPreferredIdentifier().getPatientIdentifierType().getPatientIdentifierTypeId());
				}
			}
			paciente = patientService.buscarPatient(paciente.getPatientId());
			personaLesionada.setNombre(paciente.getPerson().getPreferredName().toString());
			personaLesionada.setIdentificacion(paciente.getPreferredIdentifier().getIdentifier());
			personaLesionada.setTipoIdentificacion(identifierType);
		}
		
		personaLesionada.setFechaCreacion(lesionado.getDateCreated());
		
		return personaLesionada;
	}
	
}
