package com.artica.telesalud.tph.server.util;

import java.util.ArrayList;
import java.util.List;

import com.artica.telesalud.client.shared.ValuedItem;
import com.artica.telesalud.tph.lightweightmodel.PacienteData;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.patient.PatientIdentifierType;

public class CargarPaciente {
	
	public static PacienteData cargarDatos(Lesionado lesionado, Patient paciente, Integer idDepartamento){
		
		PacienteData pacienteData = new PacienteData();
		
		pacienteData.setPacienteId(paciente.getPatientId());
		if(paciente.getPreferredIdentifier().getPatientIdentifierType()!=null)
			pacienteData.setTipoIdentificacion(paciente.getPreferredIdentifier().getPatientIdentifierType().getPatientIdentifierTypeId());
		pacienteData.setIdentificacon(paciente.getPreferredIdentifier().getIdentifier());
		pacienteData.setGenero(paciente.getPerson().getGender());
		pacienteData.setPrimerNombre(paciente.getPerson().getPreferredName().getGivenName());
		pacienteData.setSegundoNombre(paciente.getPerson().getPreferredName().getMiddleName());
		pacienteData.setPrimerApellido(paciente.getPerson().getPreferredName().getFamilyName());
		pacienteData.setSegundoApellido(paciente.getPerson().getPreferredName().getFamilyName2());
		pacienteData.setFechaNacimiento(paciente.getPerson().getBirthdate());
		if(paciente.getMaritalStatus()!=null)
			pacienteData.setEstadoCivil(paciente.getMaritalStatus().getConceptId());
		pacienteData.setOcupacion(paciente.getOcupation());
		pacienteData.setCiudad(paciente.getPerson().getPreferredAddress().getCity());
		pacienteData.setDepartamento(idDepartamento);
		pacienteData.setZona(paciente.getPerson().getPreferredAddress().getRegion());
		pacienteData.setTelefonoDomicilio(paciente.getPerson().getPreferredAddress().getPhone1());
		pacienteData.setDireccionDomicilio(paciente.getPerson().getPreferredAddress().getAddress());
		pacienteData.setNombreAcompanante(paciente.getAttendatName());
		pacienteData.setApellidoAcompanante(paciente.getAttendatFamily());
		pacienteData.setTelefonoAcompanante(paciente.getAttendatPhone());
		pacienteData.setParentescoAcompanante(paciente.getAttendatKin());
		pacienteData.setNombreContacto(paciente.getResponsableName());
		pacienteData.setApellidoContacto(paciente.getResponsableFamily());
		pacienteData.setTelefonoContacto1(paciente.getResponsablePhone());
		pacienteData.setTelefonoContacto2(paciente.getResponsablePhone2());
		if(lesionado.getAseguradora()!=null)
			pacienteData.setAseguradora(lesionado.getAseguradora().getConceptId());
		if(lesionado.getEps()!=null)
			pacienteData.setEps(lesionado.getEps().getConceptId());
		if(lesionado.getPlanBeneficios()!=null)
			pacienteData.setPlanBeneficios(lesionado.getPlanBeneficios().getConceptId());
		
		return pacienteData;
		
	}
	
	public static ArrayList<ValuedItem<Integer, String>> cargarTiposIdentificacion(List<PatientIdentifierType> tiposIdentificacion){
	
		ArrayList<ValuedItem<Integer, String>> lista = new ArrayList<ValuedItem<Integer,String>>();
		
		for(PatientIdentifierType pit : tiposIdentificacion)
			lista.add(new ValuedItem<Integer, String>(pit.getPatientIdentifierTypeId(), pit.getName()));
		
		return lista;
	}

}
