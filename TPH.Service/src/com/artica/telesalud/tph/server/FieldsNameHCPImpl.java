package com.artica.telesalud.tph.server;

import java.io.IOException;
import java.util.Properties;

import com.artica.telesalud.tph.client.hcp.i18n.FieldsNameHCP;

public class FieldsNameHCPImpl implements FieldsNameHCP {

	private Properties prop;

	public FieldsNameHCPImpl() {
		prop = new Properties();
		try {
			prop.load(FieldsNameHCP.class
					.getResourceAsStream("FieldsNameHCP.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String lblInformacionEvento() {
		return prop.getProperty("lblInformacionEvento");
	}

	@Override
	public String lblInformacionPaciente() {
		return prop.getProperty("lblInformacionPaciente");
	}

	@Override
	public String lblEvaluacion() {
		return prop.getProperty("lblEvaluacion");
	}

	@Override
	public String lblAntecedentes() {
		return prop.getProperty("lblAntecedentes");
	}

	@Override
	public String lblProcedimientos() {
		return prop.getProperty("lblProcedimientos");
	}

	@Override
	public String lblResultados() {
		return prop.getProperty("lblResultados");
	}

	@Override
	public String lblTeleasistencia() {
		return prop.getProperty("lblTeleasistencia");
	}

	@Override
	public String toolTipCerrarHCP() {
		return prop.getProperty("toolTipCerrarHCP");
	}

	@Override
	public String toolTipGuardarHCP() {
		return prop.getProperty("toolTipGuardarHCP");
	}

	@Override
	public String toolTipListadoEventos() {
		return prop.getProperty("toolTipListadoEventos");
	}

	@Override
	public String toolTipSignosVitales() {
		return prop.getProperty("toolTipSignosVitales");
	}

	@Override
	public String toolTipNotaAclaratoria() {
		return prop.getProperty("toolTipNotaAclaratoria");
	}

	@Override
	public String toolTipImprmir() {
		return prop.getProperty("toolTipImprmir");
	}

	@Override
	public String lblTituloAplicacion() {
		return prop.getProperty("lblTituloAplicacion");
	}

	@Override
	public String lblFooterAplicacion() {
		return prop.getProperty("lblFooterAplicacion");
	}

	@Override
	public String rptHeader() {
		return prop.getProperty("rptHeader");
	}

	@Override
	public String rptInfoPacienteTitle() {
		return prop.getProperty("rptInfoPacienteTitle");
	}

	@Override
	public String rptNombrePaciente() {
		return prop.getProperty("rptNombrePaciente");
	}

	@Override
	public String rptContactoEmergenciaTitle() {
		return prop.getProperty("rptContactoEmergenciaTitle");
	}

	@Override
	public String rptIdentificacionPaciente() {
		return prop.getProperty("rptIdentificacionPaciente");
	}

	@Override
	public String rptNombreContacto() {
		return prop.getProperty("rptNombreContacto");
	}

	@Override
	public String rptGenero() {
		return prop.getProperty("rptGenero");
	}

	@Override
	public String rptTelefono() {
		return prop.getProperty("rptTelefono");
	}

	@Override
	public String rptTelefono2() {
		return prop.getProperty("rptTelefono2");
	}

	@Override
	public String rptEstadoCivil() {
		return prop.getProperty("rptEstadoCivil");
	}

	@Override
	public String rptFechaNacimiento() {
		return prop.getProperty("rptFechaNacimiento");
	}

	@Override
	public String rptEdad() {
		return prop.getProperty("rptEdad");
	}

	@Override
	public String rptAnios() {
		return prop.getProperty("rptAnios");
	}

	@Override
	public String rptOcupacion() {
		return prop.getProperty("rptOcupacion");
	}

	@Override
	public String rptInfoAcomp() {
		return prop.getProperty("rptInfoAcomp");
	}

	@Override
	public String rptDireccionResidencia() {
		return prop.getProperty("rptDireccionResidencia");
	}

	@Override
	public String rptTelefonoResidencia() {
		return prop.getProperty("rptTelefonoResidencia");
	}

	@Override
	public String rptNombreAcompanante() {
		return prop.getProperty("rptNombreAcompanante");
	}

	@Override
	public String rptAseguradora() {
		return prop.getProperty("rptAseguradora");
	}

	@Override
	public String rptParentesco() {
		return prop.getProperty("rptParentesco");
	}

	@Override
	public String rptEPS() {
		return prop.getProperty("rptEPS");
	}

	@Override
	public String rptPlanBeneficios() {
		return prop.getProperty("rptPlanBeneficios");
	}

	@Override
	public String rptTitleInfoAtencion() {
		return prop.getProperty("rptTitleInfoAtencion");
	}

	@Override
	public String rptHallazgosClinicos() {
		return prop.getProperty("rptHallazgosClinicos");
	}

	@Override
	public String rptImpresionDX() {
		return prop.getProperty("rptImpresionDX");
	}

	@Override
	public String rptHallazgosProc() {
		return prop.getProperty("rptHallazgosProc");
	}

	@Override
	public String rptEvolucionSignosVitales() {
		return prop.getProperty("rptEvolucionSignosVitales");
	}

	@Override
	public String rptTitleZona() {
		return prop.getProperty("rptTitleZona");
	}

	@Override
	public String rptTitleHallazgos() {
		return prop.getProperty("rptTitleHallazgos");
	}

	@Override
	public String rptTitleProcedimientos() {
		return prop.getProperty("rptTitleProcedimientos");
	}

	@Override
	public String rptTitleFechaSV() {
		return prop.getProperty("rptTitleFechaSV");
	}

	@Override
	public String rptTitleFC() {
		return prop.getProperty("rptTitleFC");
	}

	@Override
	public String rptTitleFR() {
		return prop.getProperty("rptTitleFR");
	}

	@Override
	public String rptTitlePA() {
		return prop.getProperty("rptTitlePA");
	}

	@Override
	public String rptTitleDM() {
		return prop.getProperty("rptTitleDM");
	}

	@Override
	public String rptTitleResultado() {
		return prop.getProperty("rptTitleResultado");
	}

	@Override
	public String rptResultadoTranspHosp() {
		return prop.getProperty("rptResultadoTranspHosp");
	}

	@Override
	public String rptResultadoTrnspSecundario() {
		return prop.getProperty("rptResultadoTrnspSecundario");
	}

	@Override
	public String rptResultadoAltaSitio() {
		return prop.getProperty("rptResultadoAltaSitio");
	}

	@Override
	public String rptResultadoEntregaOtro() {
		return prop.getProperty("rptResultadoEntregaOtro");
	}

	@Override
	public String rptResultadoRCCPExitosa() {
		return prop.getProperty("rptResultadoRCCPExitosa");
	}

	@Override
	public String rptResultadoFalleceTraslado() {
		return prop.getProperty("rptResultadoFalleceTraslado");
	}

	@Override
	public String rptResultadoFalleceHosp() {
		return prop.getProperty("rptResultadoFalleceHosp");
	}

	@Override
	public String rptResultadoFalleceSitio() {
		return prop.getProperty("rptResultadoFalleceSitio");
	}

	@Override
	public String rptResultadoNegacionAtencion() {
		return prop.getProperty("rptResultadoNegacionAtencion");
	}

	@Override
	public String rptResultadoNegacionRemision() {
		return prop.getProperty("rptResultadoNegacionRemision");
	}

	@Override
	public String rptResultadoEntidadRecepcion() {
		return prop.getProperty("rptResultadoEntidadRecepcion");
	}

	@Override
	public String rptRecibido() {
		return prop.getProperty("rptRecibido");
	}

	@Override
	public String rptTitleResponsableRegistro() {
		return prop.getProperty("rptTitleResponsableRegistro");
	}

	@Override
	public String rptTitleResponsableNombres() {
		return prop.getProperty("rptTitleResponsableNombres");
	}

	@Override
	public String rptTitleResponsableApellidos() {
		return prop.getProperty("rptTitleResponsableApellidos");
	}

	@Override
	public String rptTitleResponsableControlAph() {
		return prop.getProperty("rptTitleResponsableControlAph");
	}

	@Override
	public String rptTitleResponsableExterno() {
		return prop.getProperty("rptTitleResponsableExterno");
	}

	@Override
	public String rptTitleA() {
		return prop.getProperty("rptTitleA");
	}

	@Override
	public String rptTitleB() {
		return prop.getProperty("rptTitleB");
	}

	@Override
	public String rptTitleC() {
		return prop.getProperty("rptTitleC");
	}

	@Override
	public String rptTitleD() {
		return prop.getProperty("rptTitleD");
	}

	@Override
	public String rptTitleE() {
		return prop.getProperty("rptTitleE");
	}

	@Override
	public String rptTitleValorPrimaria() {
		return prop.getProperty("rptTitleValorPrimaria");
	}

	@Override
	public String rptTitleValorSecundaria() {
		return prop.getProperty("rptTitleValorSecundaria");
	}

	@Override
	public String rptTitleProcs() {
		return prop.getProperty("rptTitleProcs");
	}

	@Override
	public String rptTitlePermeabilidadViaAerea() {
		return prop.getProperty("rptTitlePermeabilidadViaAerea");
	}

	@Override
	public String rptTitleRespiracion() {
		return prop.getProperty("rptTitleRespiracion");
	}

	@Override
	public String rptTitlePulso() {
		return prop.getProperty("rptTitlePulso");
	}

	@Override
	public String rptTitleNivelRespuesta() {
		return prop.getProperty("rptTitleNivelRespuesta");
	}

	@Override
	public String rptTitlePiel() {
		return prop.getProperty("rptTitlePiel");
	}

	@Override
	public String rptTitlePupilas() {
		return prop.getProperty("rptTitlePupilas");
	}

	@Override
	public String rptTitleGlasgow() {
		return prop.getProperty("rptTitleGlasgow");
	}

	@Override
	public String rptOtros() {
		return prop.getProperty("rptOtros");
	}

	@Override
	public String rptPermeable() {
		return prop.getProperty("rptPermeable");
	}

	@Override
	public String rptObstruida() {
		return prop.getProperty("rptObstruida");
	}

	@Override
	public String rptCausaObstruccion() {
		return prop.getProperty("rptCausaObstruccion");
	}

	@Override
	public String rptAusente() {
		return prop.getProperty("rptAusente");
	}

	@Override
	public String rptDificultosa() {
		return prop.getProperty("rptDificultosa");
	}

	@Override
	public String rptNormal() {
		return prop.getProperty("rptNormal");
	}

	@Override
	public String rptSuperficial() {
		return prop.getProperty("rptSuperficial");
	}

	@Override
	public String rptRitmico() {
		return prop.getProperty("rptRitmico");
	}

	@Override
	public String rptArritmico() {
		return prop.getProperty("rptArritmico");
	}

	@Override
	public String rptFuerte() {
		return prop.getProperty("rptFuerte");
	}

	@Override
	public String rptDebil() {
		return prop.getProperty("rptDebil");
	}

	@Override
	public String rptAlerta() {
		return prop.getProperty("rptAlerta");
	}

	@Override
	public String rptLlamado() {
		return prop.getProperty("rptLlamado");
	}

	@Override
	public String rptDolor() {
		return prop.getProperty("rptDolor");
	}

	@Override
	public String rptSinRespuesta() {
		return prop.getProperty("rptSinRespuesta");
	}

	@Override
	public String rptPalida() {
		return prop.getProperty("rptPalida");
	}

	@Override
	public String rptCaliente() {
		return prop.getProperty("rptCaliente");
	}

	@Override
	public String rptCianotica() {
		return prop.getProperty("rptCianotica");
	}

	@Override
	public String rptHumeda() {
		return prop.getProperty("rptHumeda");
	}

	@Override
	public String rptEnrojecida() {
		return prop.getProperty("rptEnrojecida");
	}

	@Override
	public String rptSeca() {
		return prop.getProperty("rptSeca");
	}

	@Override
	public String rptMioticas() {
		return prop.getProperty("rptMioticas");
	}

	@Override
	public String rptMidriaticas() {
		return prop.getProperty("rptMidriaticas");
	}

	@Override
	public String rptNoReactivas() {
		return prop.getProperty("rptNoReactivas");
	}

	@Override
	public String rptReactivas() {
		return prop.getProperty("rptReactivas");
	}

	@Override
	public String rptArisocoricas() {
		return prop.getProperty("rptArisocoricas");
	}

	@Override
	public String rptIsocoricas() {
		return prop.getProperty("rptIsocoricas");
	}

	@Override
	public String rptNoEvaluables() {
		return prop.getProperty("rptNoEvaluables");
	}

	@Override
	public String rptProtDer() {
		return prop.getProperty("rptProtDer");
	}

	@Override
	public String rptProtIzq() {
		return prop.getProperty("rptProtIzq");
	}

	@Override
	public String rptRO() {
		return prop.getProperty("rptRO");
	}

	@Override
	public String rptRV() {
		return prop.getProperty("rptRV");
	}

	@Override
	public String rptRM() {
		return prop.getProperty("rptRM");
	}

	@Override
	public String rptTotal() {
		return prop.getProperty("rptTotal");
	}

	@Override
	public String rptParoCardiaco() {
		return prop.getProperty("rptParoCardiaco");
	}

	@Override
	public String rptNeurologica() {
		return prop.getProperty("rptNeurologica");
	}

	@Override
	public String rptOrgSentidos() {
		return prop.getProperty("rptOrgSentidos");
	}

	@Override
	public String rptCardiovascular() {
		return prop.getProperty("rptCardiovascular");
	}

	@Override
	public String rptGastrointestinal() {
		return prop.getProperty("rptGastrointestinal");
	}

	@Override
	public String rptGenitourinario() {
		return prop.getProperty("rptGenitourinario");
	}

	@Override
	public String rptGinecoObstetrico() {
		return prop.getProperty("rptGinecoObstetrico");
	}

	@Override
	public String rptShock() {
		return prop.getProperty("rptShock");
	}

	@Override
	public String rptIntoxicacion() {
		return prop.getProperty("rptIntoxicacion");
	}

	@Override
	public String rptPsiquiatrica() {
		return prop.getProperty("rptPsiquiatrica");
	}

	@Override
	public String rptOVACE() {
		return prop.getProperty("rptOVACE");
	}

	@Override
	public String rptTermica() {
		return prop.getProperty("rptTermica");
	}

	@Override
	public String rptEnfermedadComun() {
		return prop.getProperty("rptEnfermedadComun");
	}

	@Override
	public String rptPolitrauma() {
		return prop.getProperty("rptPolitrauma");
	}

	@Override
	public String rptTEC() {
		return prop.getProperty("rptTEC");
	}

	@Override
	public String rptMaxilofacial() {
		return prop.getProperty("rptMaxilofacial");
	}

	@Override
	public String rptRaquimedular() {
		return prop.getProperty("rptRaquimedular");
	}

	@Override
	public String rptTorax() {
		return prop.getProperty("rptTorax");
	}

	@Override
	public String rptAbdomen() {
		return prop.getProperty("rptAbdomen");
	}

	@Override
	public String rptPelvico() {
		return prop.getProperty("rptPelvico");
	}

	@Override
	public String rptExtremidadSuperior() {
		return prop.getProperty("rptExtremidadSuperior");
	}

	@Override
	public String rptExtremidadInferior() {
		return prop.getProperty("rptExtremidadInferior");
	}

	@Override
	public String rptTejidosBlandos() {
		return prop.getProperty("rptTejidosBlandos");
	}

	@Override
	public String rptOsteomuscular() {
		return prop.getProperty("rptOsteomuscular");
	}

	@Override
	public String rptOtroTrauma() {
		return prop.getProperty("rptOtroTrauma");
	}

	@Override
	public String rptCual() {
		return prop.getProperty("rptCual");
	}

	@Override
	public String rptAspiracionSecreciones() {
		return prop.getProperty("rptAspiracionSecreciones");
	}

	@Override
	public String rptDespejeManual() {
		return prop.getProperty("rptDespejeManual");
	}

	@Override
	public String rptCanulaNasoFaringea() {
		return prop.getProperty("rptCanulaNasoFaringea");
	}

	@Override
	public String rptCanulaOrofaringea() {
		return prop.getProperty("rptCanulaOrofaringea");
	}

	@Override
	public String rptCollarCervical() {
		return prop.getProperty("rptCollarCervical");
	}

	@Override
	public String rptDispositivoSupraglotico() {
		return prop.getProperty("rptDispositivoSupraglotico");
	}

	@Override
	public String rptCricoroidotomia() {
		return prop.getProperty("rptCricoroidotomia");
	}

	@Override
	public String rptIntubacionOrotraqueal() {
		return prop.getProperty("rptIntubacionOrotraqueal");
	}

	@Override
	public String rptEvaluacion() {
		return prop.getProperty("rptEvaluacion");
	}

	@Override
	public String rptCanulaNasal() {
		return prop.getProperty("rptCanulaNasal");
	}

	@Override
	public String rptMascaraSimple() {
		return prop.getProperty("rptMascaraSimple");
	}

	@Override
	public String rptReanimacionRespiratoria() {
		return prop.getProperty("rptReanimacionRespiratoria");
	}

	@Override
	public String rptMascaraNoReinhalacion() {
		return prop.getProperty("rptMascaraNoReinhalacion");
	}

	@Override
	public String rptBVM() {
		return prop.getProperty("rptBVM");
	}

	@Override
	public String rptDescompresionTorax() {
		return prop.getProperty("rptDescompresionTorax");
	}

	@Override
	public String rptVentilacionMecanica() {
		return prop.getProperty("rptVentilacionMecanica");
	}

	@Override
	public String rptHemostacia() {
		return prop.getProperty("rptHemostacia");
	}

	@Override
	public String rptMonitoreoSignosVitales() {
		return prop.getProperty("rptMonitoreoSignosVitales");
	}

	@Override
	public String rptRCP() {
		return prop.getProperty("rptRCP");
	}

	@Override
	public String rptDEA() {
		return prop.getProperty("rptDEA");
	}

	@Override
	public String rptRehidratacionOral() {
		return prop.getProperty("rptRehidratacionOral");
	}

	@Override
	public String rptAccesoVenosoPeriferico() {
		return prop.getProperty("rptAccesoVenosoPeriferico");
	}

	@Override
	public String rptPuncionOsea() {
		return prop.getProperty("rptPuncionOsea");
	}

	@Override
	public String rptHartman() {
		return prop.getProperty("rptHartman");
	}

	@Override
	public String rptSSN() {
		return prop.getProperty("rptSSN");
	}

	@Override
	public String rptDestrosa() {
		return prop.getProperty("rptDestrosa");
	}

	@Override
	public String rptColoides() {
		return prop.getProperty("rptColoides");
	}

	@Override
	public String rptExposicion() {
		return prop.getProperty("rptExposicion");
	}

	@Override
	public String rptLavado() {
		return prop.getProperty("rptLavado");
	}

	@Override
	public String rptManta() {
		return prop.getProperty("rptManta");
	}

	@Override
	public String rptInmovilizacion() {
		return prop.getProperty("rptInmovilizacion");
	}

	@Override
	public String rptMovimientoBloque() {
		return prop.getProperty("rptMovimientoBloque");
	}

	@Override
	public String rptChaleco() {
		return prop.getProperty("rptChaleco");
	}

	@Override
	public String rptTablaEspinalLarga() {
		return prop.getProperty("rptTablaEspinalLarga");
	}

	@Override
	public String rptTablaEspinalCorta() {
		return prop.getProperty("rptTablaEspinalCorta");
	}

	@Override
	public String rptExtricacion() {
		return prop.getProperty("rptExtricacion");
	}

	@Override
	public String rptTitleFecha() {
		return prop.getProperty("rptTitleFecha");
	}

	@Override
	public String rptTitleResponsable() {
		return prop.getProperty("rptTitleResponsable");
	}

	@Override
	public String rptTitleNota() {
		return prop.getProperty("rptTitleNota");
	}

	@Override
	public String rptTitleInfoEvento() {
		return prop.getProperty("rptTitleInfoEvento");
	}

	@Override
	public String rptTitleAval() {
		return prop.getProperty("rptTitleAval");
	}

	@Override
	public String rptTitleEvtDireccion() {
		return prop.getProperty("rptTitleEvtDireccion");
	}

	@Override
	public String rptTitleEvtNumeroCaso() {
		return prop.getProperty("rptTitleEvtNumeroCaso");
	}

	@Override
	public String rptTitleEvtDepto() {
		return prop.getProperty("rptTitleEvtDepto");
	}

	@Override
	public String rptTitleEvtPlaca() {
		return prop.getProperty("rptTitleEvtPlaca");
	}

	@Override
	public String rptTitleEvtMunicipio() {
		return prop.getProperty("rptTitleEvtMunicipio");
	}

	@Override
	public String rptTitleEvtHoraDespacho() {
		return prop.getProperty("rptTitleEvtHoraDespacho");
	}

	@Override
	public String rptTitleEvtZona() {
		return prop.getProperty("rptTitleEvtZona");
	}

	@Override
	public String rptTitleEvtHoraLLegada() {
		return prop.getProperty("rptTitleEvtHoraLLegada");
	}

	@Override
	public String rptTitleEvtCausaOrigen() {
		return prop.getProperty("rptTitleEvtCausaOrigen");
	}

	@Override
	public String rptTitleEvtHoraRegreso() {
		return prop.getProperty("rptTitleEvtHoraRegreso");
	}

	@Override
	public String rptTitleEvtHoraLLamada() {
		return prop.getProperty("rptTitleEvtHoraLLamada");
	}

	@Override
	public String rptTitleEmergenciaMedica() {
		return prop.getProperty("rptTitleEmergenciaMedica");
	}

	@Override
	public String rptTitleTeleasistencia() {
		return prop.getProperty("rptTitleTeleasistencia");
	}

	@Override
	public String rptTitleMotivoSolicitud() {
		return prop.getProperty("rptTitleMotivoSolicitud");
	}

	@Override
	public String rptTitleDetalle() {
		return prop.getProperty("rptTitleDetalle");
	}

	@Override
	public String rptTitleMedio() {
		return prop.getProperty("rptTitleMedio");
	}

	@Override
	public String rptTitleSolicitante() {
		return prop.getProperty("rptTitleSolicitante");
	}

	@Override
	public String rptTitleMedicoTratante() {
		return prop.getProperty("rptTitleMedicoTratante");
	}

	@Override
	public String rptTitleDxPpal() {
		return prop.getProperty("rptTitleDxPpal");
	}

	@Override
	public String rptTitleDxSecundarios() {
		return prop.getProperty("rptTitleDxSecundarios");
	}

	@Override
	public String rptTitleRecomendacion() {
		return prop.getProperty("rptTitleRecomendacion");
	}

	@Override
	public String rptTitleAnulada() {
		return prop.getProperty("rptTitleAnulada");
	}

	@Override
	public String rptTitleAvala() {
		return prop.getProperty("rptTitleAvala");
	}

	@Override
	public String rptTitleNoAvala() {
		return prop.getProperty("rptTitleNoAvala");
	}

	@Override
	public String rptTitleResponsableAval() {
		return prop.getProperty("rptTitleResponsableAval");
	}

	@Override
	public String rptTitleResultadoCierre() {
		return prop.getProperty("rptTitleResultadoCierre");
	}

	@Override
	public String rptTitleAntecedenteAnio() {
		// TODO Auto-generated method stub
		return prop.getProperty("rptTitleAntecedenteAnio");
	}

	@Override
	public String rptTitleAntecedenteTipo() {
		// TODO Auto-generated method stub
		return prop.getProperty("rptTitleAntecedenteTipo");
	}

	@Override
	public String rptTitleAntecedenteObservacion() {
		// TODO Auto-generated method stub
		return prop.getProperty("rptTitleAntecedenteObservacion");
	}

	@Override
	public String rptTitleAntecedentes() {
		// TODO Auto-generated method stub
		return prop.getProperty("rptTitleAntecedentes");
	}

	@Override
	public String rptFechaCreacion() {
		// TODO Auto-generated method stub
		return prop.getProperty("rptFechaCreacion");
	}

	@Override
	public String rptPrioridadTriage() {
		return prop.getProperty("rptPrioridadTriage");
	}

	@Override
	public String rptPrioridadTriageRojo() {
		return prop.getProperty("rptPrioridadTriageRojo");
	}

	@Override
	public String rptPrioridadTriageNegro() {
		return prop.getProperty("rptPrioridadTriageNegro");
	}

	@Override
	public String rptPrioridadTriageVerde() {
		return prop.getProperty("rptPrioridadTriageVerde");
	}

	@Override
	public String rptPrioridadTriageAmarillo() {
		return prop.getProperty("rptPrioridadTriageAmarillo");
	}

	@Override
	public String rptPrioridadTriageBlanco() {
		return prop.getProperty("rptPrioridadTriageBlanco");
	}

	@Override
	public String toolTipVolverEvento() {
		// TODO Auto-generated method stub
		return prop.getProperty("toolTipVolverEvento");
	}

	@Override
	public String toolTipEvoSignosVitales() {
		// TODO Auto-generated method stub
		return prop.getProperty("toolTipEvoSignosVitales");
	}

	@Override
	public String toolTipVolverAlListadoDeHCPConsultado() {
		// TODO Auto-generated method stub
		return prop.getProperty("toolTipVolverAlListadoDeHCPConsultado");
	}
}
