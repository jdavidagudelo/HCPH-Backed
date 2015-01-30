package com.artica.telesalud.tph.controller.model.observation;

import java.util.ArrayList;
import java.util.List;

import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.model.observation.ObservationData;
import com.artica.telesalud.tph.service.ConceptService;
/**
 * Clase utilizada para mapear evaluaciones a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class EvaluacionSpringDto {
    public static final Integer RESPUESTA_OCULAR1 = 1, RESPUESTA_OCULAR2 = 2, RESPUESTA_OCULAR3 = 3, RESPUESTA_OCULAR4 = 4;
    public static final  Integer RESPUESTA_VERBAL1 = 1, RESPUESTA_VERBAL2 = 2, RESPUESTA_VERBAL3 = 3, RESPUESTA_VERBAL4 = 4,
            RESPUESTA_VERBAL5 = 5;
    public static final Integer RESPUESTA_MOTORA1=1,RESPUESTA_MOTORA2=2,RESPUESTA_MOTORA3=3,RESPUESTA_MOTORA4 = 4,RESPUESTA_MOTORA5=5,
            RESPUESTA_MOTORA6 = 6;
    public static final Integer VIA_AEREA_PERMEABLE = 1, VIA_AEREA_OBSTRUIDA = 2,
            RESPIRACION_AUSENTE = 3, RESPIRACION_PRESENTE =4, ESTADO_RESPIRACION_DIFICULTOSA = 5,
            ESTADO_RESPIRACION_NORMAL = 6, ESTADO_RESPIRACION_SIMETRICA = 7,
            ESTADO_RESPIRACION_ASIMETRICA = 8, CIANOSIS_EXISTENTE=9,CIANOSIS_AUSENTE=10,
            SANGRADO_EXISTENTE = 11,SANGRADO_AUSENTE = 12,
            PULSO_AUSENTE = 13, PULSO_FUERTE = 14, PULSO_DEBIL = 15,
            UBICACION_PULSO_RADIAL = 16,
                    EMERGENCIA_PARO_CARDIACO = 17, EMERGENCIA_NEUROLOGIA = 18,
                            EMERGENCIA_ORGANOS_SENTIDOS = 19, EMERGENCIA_CARDIOVASCULAR = 20,
                            EMERGENCIA_GASTROINTESTINAL = 21, EMERGENCIA_GENITOURINARIO = 22,
                            EMERGENCIA_GINECOOBSTETRICO = 23, EMERGENCIA_SHOCK = 24,
                            EMERGENCIA_INTOXICACION = 25, EMERGENCIA_SIQUIATRICA = 26,
                            EMERGENCIA_OVACE = 27, EMERGENCIA_TERMICA = 28,
                            EMERGENCIA_ENFERMEDAD_COMUN = 29,
                                    PRIORIDAD_TRIAGE_ROJO = 30, PRIORIDAD_TRIAGE_AMARILLO = 31, 
                                    PRIORIDAD_TRIAGE_NEGRO = 32,
                                    PRIORIDAD_TRIAGE_VERDE = 33, PRIORIDAD_TRIAGE_BLANCO = 34,
                                    OTRA_EMERGENCIA=35, UBICACION_PULSO_CAROTIDEA = 36,
            LLENADO_CAPILAR_MENOR=37, LLENADO_CAPILAR_MAYOR=38,
            CAUSA_OBSTRUCCION_SANGRADO=39, CAUSA_OBSTRUCCION_CUERPO_EXTRANIO=40,
            CAUSA_OBSTRUCCION_SECRECIONES=41,CAUSA_OBSTRUCCION_OTRA=42;


	private Boolean pupilasNormales=Boolean.FALSE;
    private Boolean pupilasAnisocoricas=Boolean.FALSE;
    private Integer llenadoCapilar;
    private Integer ubicacionPulso;
    private Integer sangrado;
    private String otraEmergenciaMedica;
    private Integer estadoRespiracion;
    private Integer cianosis;
    private Boolean pielIcterica=Boolean.FALSE;
    private Boolean pielFria=Boolean.FALSE;
    private Boolean triageCadenaCustodia = Boolean.FALSE;

    private Boolean triageEntregadoEntidad = Boolean.FALSE;

    private String triageEntidadEntregaPacienteDifunto;

    private String triageNombrePersonaRecibeDifunto;

    private String triageRegistroPersonaRecibeDifunto;
	private ConceptSpringDto permeavilidadViaAerea;
	private String causaObstruccionVia;
	private ConceptSpringDto causaObstruccionViaAerea;
	private Integer respiracion;
	private Integer pulso;
	private Integer ritmoPulso;
	private Integer fuerzaPulso;
	private Integer nivelRespuesta;
	private Boolean pielNormal;
	private Boolean pielPalida;
	private Boolean pielCaliente;
	private Boolean pielCianotica;
	private Boolean pielHumeda;
	private Boolean pielSeca;
	private Boolean pupilasMioticas;
	private Boolean pupilasMidriaticas;
	private Boolean pupilasIsocoricas;
	private Boolean pupilasReactivas;
	private Boolean pupilasNoReactivas;
	private Boolean pupilasNoEvaluables;
	private Integer glasgowRO;
	private Integer glasgowRV;
	private Integer glasgowRM;
	private Integer tipoEmergencia;
	private Boolean politrauma;
	private Boolean tec;
	private Boolean maxilofacial;
	private Boolean raquimedular;
	private Boolean torax;
	private Boolean abdomen;
	private Boolean pelvico;
	private Boolean extremidadSuperior;
	private Boolean extremidadInferior;
	private Boolean tejidosBlandos;
	private Boolean osteomuscular;
	private Boolean organosSentidos;
	private Boolean otroTrauma;
	private String cualOtroTrauma;
	private Integer prioridadTriage;
    private Boolean triageViaAreaObstruida=Boolean.FALSE;
    private Boolean triageInsuficienciaRespiratoria=Boolean.FALSE;
    private Boolean triagePa90=Boolean.FALSE;
    private Boolean triageMultiplesHeridas=Boolean.FALSE;
    private Boolean triageHemorragiasNoControladas=Boolean.FALSE;
    private Boolean triageLesionCervicalIncompleta=Boolean.FALSE;
    private Boolean triageGlasgow4a8=Boolean.FALSE;
    private Boolean triageExcitacionPsicomotora=Boolean.FALSE;
    private Boolean triageAbdomenAgudo=Boolean.FALSE;
    private Boolean triageEvisceracion=Boolean.FALSE;
    private Boolean triageTrabajoPartoSangrado=Boolean.FALSE;
    private Boolean triageDolorToracico=Boolean.FALSE;
    private Boolean triageArritmias=Boolean.FALSE;
    private Boolean triageHemorragiasControladas=Boolean.FALSE;
    private Boolean triageLesionMedularDorsal=Boolean.FALSE;
    private Boolean triageGlasgow9a13=Boolean.FALSE;
    private Boolean triageAlteracionEstadoConciencia=Boolean.FALSE;
    private Boolean triageFracturasMayores=Boolean.FALSE;
    private Boolean triageQuemadurasModeradas=Boolean.FALSE;
    private Boolean triageIntoxicacion=Boolean.FALSE;
    private Boolean triageLesionMedularLumbar=Boolean.FALSE;
    private Boolean triageFracturasNoProximales=Boolean.FALSE;
    private Boolean triageLesionesSuperficiales=Boolean.FALSE;
    private Boolean triageQuemadurasPrimerGrado=Boolean.FALSE;
    private Boolean triageAfectados=Boolean.FALSE;
    private Boolean triageParoProlongado=Boolean.FALSE;
    private Boolean triageLesionCervicalCompleta=Boolean.FALSE;
    private Boolean triageGlasgow3=Boolean.FALSE;
    private Boolean triageExposicionMasaEncefalica=Boolean.FALSE;
    private Boolean triageLesionesImpidenRcp=Boolean.FALSE;
    private Boolean triageQuemadurasGraves=Boolean.FALSE;
    private Boolean triageGlasgow14o15=Boolean.FALSE;
    private String triageOtro;
	private ProcedimientosSpringDto procedimientos;
	private EvaluacionCompletaSpringDto evaluacion;
	private List<HallazgoSpringDto> hallazgos = new ArrayList<HallazgoSpringDto>();
	private List<InsumoSpringDto> insumos = new ArrayList<InsumoSpringDto>();
	private Boolean emergenciaSangrado = Boolean.FALSE;
	private Boolean emergenciaDolor = Boolean.FALSE;
	private Boolean emergenciaEdema = Boolean.FALSE;
	public EvaluacionSpringDto() {

	}
	
	
	/**
	 * @return the insumos
	 */
	public List<InsumoSpringDto> getInsumos() {
		return insumos;
	}


	/**
	 * @param insumos the insumos to set
	 */
	public void setInsumos(List<InsumoSpringDto> insumos) {
		this.insumos = insumos;
	}


	/**
	 * @return the causaObstruccionViaId
	 */
	public ConceptSpringDto getCausaObstruccionViaAerea() {
		return causaObstruccionViaAerea;
	}

	/**
	 * @param causaObstruccionViaId the causaObstruccionViaId to set
	 */
	public void setCausaObstruccionViaAerea(ConceptSpringDto causaObstruccionViaId) {
		this.causaObstruccionViaAerea = causaObstruccionViaId;
	}

	public EvaluacionSpringDto(List<ObservationData> datos, ConceptService conceptService) {
		ConceptsCode concepts = new ConceptsCode();
		
		ObservationData obsData = ObservationData.obtenerValor(datos,
				concepts.cPermeabilidadViaArea());
		
		if (obsData != null) {
			if (obsData.getValueCoded() != null) {
				setPermeavilidadViaAerea(new ConceptSpringDto(conceptService.obtenerConcepto(obsData.getValueCoded())));
			}
		}
		setCausaObstruccionVia((String) ObservationData.obtenerValorConcepto(
				datos, concepts.cCausaObstruccionViaAerea()));
		obsData = ObservationData.obtenerValor(datos,
				concepts.cCausaObstruccionViaAereaCompleta());
		if(obsData != null)
		{
			if(obsData.getValueCoded() != null)
			{
				setCausaObstruccionViaAerea(new ConceptSpringDto(conceptService.obtenerConcepto(obsData.getValueCoded())));
				/*
				if(obsData.getValueCoded().equals(concepts.cCausaObstruccionCuerpoExtranio()))
				{
					setCausaObstruccionViaAerea(CAUSA_OBSTRUCCION_CUERPO_EXTRANIO);
				}
				if(obsData.getValueCoded().equals(concepts.cCausaObstruccionOtra()))
				{
					setCausaObstruccionViaAerea(CAUSA_OBSTRUCCION_OTRA);
				}
				if(obsData.getValueCoded().equals(concepts.cCausaObstruccionSangrado()))
				{
					setCausaObstruccionViaAerea(CAUSA_OBSTRUCCION_SANGRADO);
				}
				if(obsData.getValueCoded().equals(concepts.cCausaObstruccionSecreciones()))
				{
					setCausaObstruccionViaAerea(CAUSA_OBSTRUCCION_SECRECIONES);
				}*/
			}
		}
		obsData = ObservationData.obtenerValor(datos,
				concepts.cRespiracion());

		if (obsData != null) {

			if (obsData.getValueCoded() != null) {
				if (obsData.getValueCoded().equals(
						concepts.cRespiracionAusente()))
					setRespiracion(RESPIRACION_AUSENTE);
				else if (obsData.getValueCoded().equals(
						concepts.cRespiracionPresente()))
					setRespiracion(RESPIRACION_PRESENTE);
			}
		}

		obsData = ObservationData.obtenerValor(datos,
				concepts.cValoracioPrimariaPulso());

		if (obsData != null) {
			if (obsData.getValueCoded() != null) {
				if (obsData.getValueCoded().equals(concepts.cPulsoAusente()))
					setPulso(PULSO_AUSENTE);
				
			}
		}



		obsData = ObservationData.obtenerValor(datos, concepts.cFuerzaPulso());

		if (obsData != null) {

			if (obsData.getValueCoded() != null) {
				if (obsData.getValueCoded().equals(concepts.cPulsoFuerte()))
					setPulso(PULSO_FUERTE);
				else if (obsData.getValueCoded().equals(concepts.cPulsoDebil()))
					setPulso(PULSO_DEBIL);
			}
		}



		setPielCaliente((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cPielCaliente()));
		setPielCianotica((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cPielCrianotica()));
		setPielHumeda((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cPielHumeda()));
		setPielNormal((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cPielNormal()));
		setPielPalida((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cPielPalidaFria()));
		setPielSeca((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cPielSeca()));

		setPupilasIsocoricas((Boolean) ObservationData.obtenerValorConcepto(
				datos, concepts.cPielPupilasIsocoricas()));
		setPupilasMidriaticas((Boolean) ObservationData.obtenerValorConcepto(
				datos, concepts.cPupilasMidriaticas()));
		setPupilasNoEvaluables((Boolean) ObservationData.obtenerValorConcepto(
				datos, concepts.cPupilasNoEvaluables()));
		setPupilasNoReactivas((Boolean) ObservationData.obtenerValorConcepto(
				datos, concepts.cPupilasNoReactivas()));
		setPupilasReactivas((Boolean) ObservationData.obtenerValorConcepto(
				datos, concepts.cPupilasReactivas()));
		setPupilasMioticas((Boolean) ObservationData.obtenerValorConcepto(
				datos, concepts.cPupilasMioticas()));

		Double valor = (Double) ObservationData.obtenerValorConcepto(datos,
				concepts.cGlasgowRM());
		if (valor != null)
			setGlasgowRM(valor.intValue());

		valor = (Double) ObservationData.obtenerValorConcepto(datos,
				concepts.cGlasgowRO());
		if (valor != null)
			setGlasgowRO(valor.intValue());

		valor = (Double) ObservationData.obtenerValorConcepto(datos,
				concepts.cGlasgowRV());
		if (valor != null)
			setGlasgowRV(valor.intValue());

		obsData = ObservationData.obtenerValor(datos,
				concepts.cTipoEmergenciaMedica());

		if (obsData != null) {

			if (obsData.getValueCoded() != null) {
				if (obsData.getValueCoded().equals(concepts.cNeurologia()))
					setTipoEmergencia(EMERGENCIA_NEUROLOGIA);
				else if (obsData.getValueCoded().equals(
						concepts.cParoCardiaco()))
					setTipoEmergencia(EMERGENCIA_PARO_CARDIACO);
				else if (obsData.getValueCoded().equals(
						concepts.cOrganosSentidos()))
					setTipoEmergencia(EMERGENCIA_ORGANOS_SENTIDOS);
				else if (obsData.getValueCoded().equals(
						concepts.cCardiovascular()))
					setTipoEmergencia(EMERGENCIA_CARDIOVASCULAR);
				else if (obsData.getValueCoded().equals(
						concepts.cGastrointestinal()))
					setTipoEmergencia(EMERGENCIA_GASTROINTESTINAL);
				else if (obsData.getValueCoded().equals(
						concepts.cGenitourinario()))
					setTipoEmergencia(EMERGENCIA_GENITOURINARIO);
				else if (obsData.getValueCoded().equals(
						concepts.cGnicoObstetrico()))
					setTipoEmergencia(EMERGENCIA_GINECOOBSTETRICO);
				else if (obsData.getValueCoded().equals(concepts.cShock()))
					setTipoEmergencia(EMERGENCIA_SHOCK);
				else if (obsData.getValueCoded().equals(
						concepts.cIntoxicacion()))
					setTipoEmergencia(EMERGENCIA_INTOXICACION);
				else if (obsData.getValueCoded().equals(
						concepts.cPsiquiatrica()))
					setTipoEmergencia(EMERGENCIA_SIQUIATRICA);
				else if (obsData.getValueCoded().equals(concepts.cOvace()))
					setTipoEmergencia(EMERGENCIA_OVACE);
				else if (obsData.getValueCoded().equals(concepts.cTermica()))
					setTipoEmergencia(EMERGENCIA_TERMICA);
				else if (obsData.getValueCoded().equals(
						concepts.cEnfermedadComun()))
					setTipoEmergencia(EMERGENCIA_ENFERMEDAD_COMUN);
				else if(obsData.getValueCoded().equals(concepts.cOtraEmergenciaMedica()))
						setTipoEmergencia(OTRA_EMERGENCIA);
					
			}

		}

		setPolitrauma((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cTraumaPolitrauma()));
		setTec((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cTraumaTEC()));
		setMaxilofacial((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cTraumaMaxilofacial()));
		setRaquimedular((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cTraumaRaquimedular()));
		setTorax((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cTraumaTorax()));
		setAbdomen((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cTraumaAbdomen()));
		setPelvico((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cTraumaPelvico()));
		setExtremidadSuperior((Boolean) ObservationData.obtenerValorConcepto(
				datos, concepts.cTraumaExtremidadSuperior()));
		setTejidosBlandos((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cTraumaTejidosBlandos()));
		setOsteomuscular((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cTraumaOsteomuscular()));
		setExtremidadInferior((Boolean) ObservationData.obtenerValorConcepto(
				datos, concepts.cTraumaExtremidadInferior()));
		setOrganosSentidos((Boolean) ObservationData.obtenerValorConcepto(
				datos, concepts.cTraumaOrganosSentidos()));
		setOtroTrauma((Boolean) ObservationData.obtenerValorConcepto(datos,
				concepts.cOtroTrauma()));
		setCualOtroTrauma((String) ObservationData.obtenerValorConcepto(datos,
				concepts.cCualOtroTrauma()));

		obsData = ObservationData.obtenerValor(datos,
				concepts.cPrioridadTriage());

		if (obsData != null) {

			if (obsData.getValueCoded() != null) {
				if (obsData.getValueCoded().equals(
						concepts.cPrioridadTriageAmarillo()))
					setPrioridadTriage(PRIORIDAD_TRIAGE_AMARILLO);
				else if (obsData.getValueCoded().equals(
						concepts.cPrioridadTriageBlanco()))
					setPrioridadTriage(PRIORIDAD_TRIAGE_BLANCO);
				else if (obsData.getValueCoded().equals(
						concepts.cPrioridadTriageNegro()))
					setPrioridadTriage(PRIORIDAD_TRIAGE_NEGRO);
				else if (obsData.getValueCoded().equals(
						concepts.cPrioridadTriageRojo()))
					setPrioridadTriage(PRIORIDAD_TRIAGE_ROJO);
				else if (obsData.getValueCoded().equals(
						concepts.cPrioridadTriageVerde()))
					setPrioridadTriage(PRIORIDAD_TRIAGE_VERDE);
			}
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cViaAereaObstruidaTriage());
		if(obsData != null)
		{
			setTriageViaAreaObstruida(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cInsuficienciaRespiratoriaTriage());
		if(obsData != null)
		{
			setTriageInsuficienciaRespiratoria(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cPaMenor90Triage());
		if(obsData != null)
		{
			setTriagePa90(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cMultiplesHeridasTriage());
		if(obsData != null)
		{
			setTriageMultiplesHeridas(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cHemorragiasNoControladasTriage());
		if(obsData != null)
		{
			setTriageHemorragiasNoControladas(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true); 
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cLesionCervicalIncompletaTriage());
		if(obsData != null)
		{
			setTriageLesionCervicalIncompleta(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cGlasgow48Triage());
		if(obsData != null)
		{
			setTriageGlasgow4a8(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cExcitacionPsicomotoraTriage());
		if(obsData != null)
		{
			setTriageExcitacionPsicomotora(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cAbdomenAgudoTriage());
		if(obsData != null)
		{
			setTriageAbdomenAgudo(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cEvisceracionTriage());
		if(obsData != null)
		{
			setTriageEvisceracion(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cTrabajoPartoSangradoTriage());
		if(obsData != null)
		{
			setTriageTrabajoPartoSangrado(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cDolorToracicoTriage());
		if(obsData != null)
		{
			setTriageDolorToracico(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cArritmiasTriage());
		if(obsData != null)
		{
			setTriageArritmias(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cHemorragiasControladasTriage());
		if(obsData != null)
		{
			setTriageHemorragiasControladas(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cLesionMedularDorsalTriage());
		if(obsData != null)
		{
			setTriageLesionMedularDorsal(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cGlasgow913Triage());
		if(obsData != null)
		{
			setTriageGlasgow9a13(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cAlteracionEstadoConcienciaTriage());
		if(obsData != null)
		{
			setTriageAlteracionEstadoConciencia(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cFracturasMayoresTriage());
		if(obsData != null)
		{
			setTriageFracturasMayores(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cQuemadurasModeradasTriage());
		if(obsData != null)
		{
			setTriageQuemadurasModeradas(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cIntoxicacionTriage());
		if(obsData != null)
		{
			setTriageIntoxicacion(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cLesionMedularLumbarTriage());
		if(obsData != null)
		{
			setTriageLesionMedularLumbar(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cGlasgow1415Triage());
		if(obsData != null)
		{
			setTriageGlasgow14o15(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cFracturasNoProximalesTriage());
		if(obsData != null)
		{
			setTriageFracturasNoProximales(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cLesionesSuperficialesTriage());
		if(obsData != null)
		{
			setTriageLesionesSuperficiales(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cQuemadurasGradoITriage());
		if(obsData != null)
		{
			setTriageQuemadurasPrimerGrado(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cAfectadosTriage());
		if(obsData != null)
		{
			setTriageAfectados(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cParoProlongadoTriage());
		if(obsData != null)
		{
			setTriageParoProlongado(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cLesionCervicalCompletaTriage());
		if(obsData != null)
		{
			setTriageLesionCervicalCompleta(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cGlasgow3Triage());
		if(obsData != null)
		{
			setTriageGlasgow3(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cExposicionMasaEncefalicaTriage());
		if(obsData != null)
		{
			setTriageExposicionMasaEncefalica(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cLesionesImpidenRCPTriage());
		if(obsData != null)
		{
			setTriageLesionesImpidenRcp(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cQuemadurasGravesTriage());
		if(obsData != null)
		{
			setTriageQuemadurasGraves(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cOtroTriage());
		if(obsData != null)
		{
			setTriageOtro(obsData.getValueText());
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cPupilasNormales());
		if(obsData != null)
		{
			setPupilasNormales(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cPupilasAnisocoricas());
		if(obsData != null)
		{
			setPupilasAnisocoricas(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cLlenadoCapilar());
		if(obsData != null)
		{
			if(obsData.getValueCoded().equals(concepts.cLlenadoCapilarMayor3()))
			{
				setLlenadoCapilar(LLENADO_CAPILAR_MAYOR);
			}
			if(obsData.getValueCoded().equals(concepts.cLlenadoCapilarMenor3()))
			{
				setLlenadoCapilar(LLENADO_CAPILAR_MENOR);
			}
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cUbicacionPulso());
		if(obsData != null)
		{
			if(obsData.getValueCoded().equals(concepts.cUbicacionPulsoCarotideo()))
			{
				setUbicacionPulso(UBICACION_PULSO_CAROTIDEA);
			}
			if(obsData.getValueCoded().equals(concepts.cUbicacionPulsoRadial()))
			{
				setUbicacionPulso(UBICACION_PULSO_RADIAL);
			}
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cSangrado());
		if(obsData != null)
		{
			if(obsData.getValueCoded() .equals(concepts.cSangradoExistente()))
			{
				setSangrado(SANGRADO_EXISTENTE);
			}
			if(obsData.getValueCoded().equals(concepts.cSangradoInexistente()))
			{
				setSangrado(SANGRADO_AUSENTE);
			}
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cOtraEmergenciaMedicaText());
		if(obsData != null)
		{
			if(obsData.getValueText() != null)
			{
				setOtraEmergenciaMedica(obsData.getValueText());
			}
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cEstadoRespiracion());
		if(obsData != null)
		{
			if(obsData.getValueCoded().equals(concepts.cEstadoRespiracionAsimetrica()))
			{
				setEstadoRespiracion(ESTADO_RESPIRACION_ASIMETRICA);
			}
			if(obsData.getValueCoded().equals(concepts.cEstadoRespiracionSimetrica()))
			{
				setEstadoRespiracion(ESTADO_RESPIRACION_SIMETRICA);
			}
			if(obsData.getValueCoded().equals(concepts.cEstadoRespiracionDificultosa()))
			{
				setEstadoRespiracion(ESTADO_RESPIRACION_DIFICULTOSA);
			}
			if(obsData.getValueCoded().equals(concepts.cEstadoRespiracionNormal()))
			{
				setEstadoRespiracion(ESTADO_RESPIRACION_NORMAL);
			}
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cCianosis());
		if(obsData != null)
		{
			if(obsData.getValueCoded().equals(concepts.cCianosisExistente()))
			{
				setCianosis(CIANOSIS_EXISTENTE);
			}
			if(obsData.getValueCoded().equals(concepts.cCianosisInexistente()))
			{
				setCianosis(CIANOSIS_AUSENTE);
			}
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cPielIcterica());
		if(obsData != null)
		{
			setPielIcterica(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cPielFria());
		if(obsData != null)
		{
			setPielFria(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
		obsData = ObservationData.obtenerValor(datos, concepts.cTriageCadenaCustodia());
		if(obsData != null)
		{
			setTriageCadenaCustodia(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
		}
	    obsData = ObservationData.obtenerValor(datos, concepts.cTriageEntregadoEntidad());
	    if(obsData != null)
	    {
	    	setTriageEntregadoEntidad(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
	    }
	    obsData = ObservationData.obtenerValor(datos, concepts.cTriageEntidadEntregaPacienteDifunto());
	    if(obsData != null)
	    {
	    	setTriageEntidadEntregaPacienteDifunto(obsData.getValueText());
	    }
	    obsData = ObservationData.obtenerValor(datos, concepts.cTriageNombrePersonaRecibeDifunto());
	    if(obsData != null)
	    {
	    	setTriageNombrePersonaRecibeDifunto(obsData.getValueText());
	    }
	    obsData = ObservationData.obtenerValor(datos, concepts.cTriageRegistroPersonaRecibeDifunto());
	    if(obsData != null)
	    {
	    	setTriageRegistroPersonaRecibeDifunto(obsData.getValueText());
	    }
	    obsData = ObservationData.obtenerValor(datos, concepts.cEmergenciaDolorGinecoObstetrico());
	    if(obsData != null)
	    {
	    	setEmergenciaDolor(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
	    }
	    obsData = ObservationData.obtenerValor(datos, concepts.cEmergenciaEdema());
	    if(obsData != null)
	    {
	    	setEmergenciaEdema(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
	    }
	    obsData = ObservationData.obtenerValor(datos, concepts.cEmergenciaSangrado());
	    if(obsData != null)
	    {
	    	setEmergenciaSangrado(obsData.getValueBoolean() != null && obsData.getValueBoolean()==0? false:true);
	    }
	}

	/**
	 * @return the emergenciaSangrado
	 */
	public Boolean getEmergenciaSangrado() {
		return emergenciaSangrado;
	}


	/**
	 * @param emergenciaSangrado the emergenciaSangrado to set
	 */
	public void setEmergenciaSangrado(Boolean emergenciaSangrado) {
		this.emergenciaSangrado = emergenciaSangrado;
	}


	/**
	 * @return the emergenciaDolor
	 */
	public Boolean getEmergenciaDolor() {
		return emergenciaDolor;
	}


	/**
	 * @param emergenciaDolor the emergenciaDolor to set
	 */
	public void setEmergenciaDolor(Boolean emergenciaDolor) {
		this.emergenciaDolor = emergenciaDolor;
	}


	/**
	 * @return the emergenciaEdema
	 */
	public Boolean getEmergenciaEdema() {
		return emergenciaEdema;
	}


	/**
	 * @param emergenciaEdema the emergenciaEdema to set
	 */
	public void setEmergenciaEdema(Boolean emergenciaEdema) {
		this.emergenciaEdema = emergenciaEdema;
	}


	/**
	 * @return the pupilasNormales
	 */
	public Boolean getPupilasNormales() {
		return pupilasNormales;
	}

	/**
	 * @param pupilasNormales the pupilasNormales to set
	 */
	public void setPupilasNormales(Boolean pupilasNormales) {
		this.pupilasNormales = pupilasNormales;
	}

	/**
	 * @return the pupilasAnisocoricas
	 */
	public Boolean getPupilasAnisocoricas() {
		return pupilasAnisocoricas;
	}

	/**
	 * @param pupilasAnisocoricas the pupilasAnisocoricas to set
	 */
	public void setPupilasAnisocoricas(Boolean pupilasAnisocoricas) {
		this.pupilasAnisocoricas = pupilasAnisocoricas;
	}

	/**
	 * @return the llenadoCapilar
	 */
	public Integer getLlenadoCapilar() {
		return llenadoCapilar;
	}

	/**
	 * @param llenadoCapilar the llenadoCapilar to set
	 */
	public void setLlenadoCapilar(Integer llenadoCapilar) {
		this.llenadoCapilar = llenadoCapilar;
	}

	/**
	 * @return the ubicacionPulso
	 */
	public Integer getUbicacionPulso() {
		return ubicacionPulso;
	}

	/**
	 * @param ubicacionPulso the ubicacionPulso to set
	 */
	public void setUbicacionPulso(Integer ubicacionPulso) {
		this.ubicacionPulso = ubicacionPulso;
	}

	/**
	 * @return the sangrado
	 */
	public Integer getSangrado() {
		return sangrado;
	}

	/**
	 * @param sangrado the sangrado to set
	 */
	public void setSangrado(Integer sangrado) {
		this.sangrado = sangrado;
	}

	/**
	 * @return the otraEmergenciaMedica
	 */
	public String getOtraEmergenciaMedica() {
		return otraEmergenciaMedica;
	}

	/**
	 * @param otraEmergenciaMedica the otraEmergenciaMedica to set
	 */
	public void setOtraEmergenciaMedica(String otraEmergenciaMedica) {
		this.otraEmergenciaMedica = otraEmergenciaMedica;
	}

	/**
	 * @return the estadoRespiracion
	 */
	public Integer getEstadoRespiracion() {
		return estadoRespiracion;
	}

	/**
	 * @param estadoRespiracion the estadoRespiracion to set
	 */
	public void setEstadoRespiracion(Integer estadoRespiracion) {
		this.estadoRespiracion = estadoRespiracion;
	}

	/**
	 * @return the cianosis
	 */
	public Integer getCianosis() {
		return cianosis;
	}

	/**
	 * @param cianosis the cianosis to set
	 */
	public void setCianosis(Integer cianosis) {
		this.cianosis = cianosis;
	}

	/**
	 * @return the pielIcterica
	 */
	public Boolean getPielIcterica() {
		return pielIcterica;
	}

	/**
	 * @param pielIcterica the pielIcterica to set
	 */
	public void setPielIcterica(Boolean pielIcterica) {
		this.pielIcterica = pielIcterica;
	}

	/**
	 * @return the pielFria
	 */
	public Boolean getPielFria() {
		return pielFria;
	}

	/**
	 * @param pielFria the pielFria to set
	 */
	public void setPielFria(Boolean pielFria) {
		this.pielFria = pielFria;
	}

	/**
	 * @return the triageCadenaCustodia
	 */
	public Boolean getTriageCadenaCustodia() {
		return triageCadenaCustodia;
	}

	/**
	 * @param triageCadenaCustodia the triageCadenaCustodia to set
	 */
	public void setTriageCadenaCustodia(Boolean triageCadenaCustodia) {
		this.triageCadenaCustodia = triageCadenaCustodia;
	}

	/**
	 * @return the triageEntregadoEntidad
	 */
	public Boolean getTriageEntregadoEntidad() {
		return triageEntregadoEntidad;
	}

	/**
	 * @param triageEntregadoEntidad the triageEntregadoEntidad to set
	 */
	public void setTriageEntregadoEntidad(Boolean triageEntregadoEntidad) {
		this.triageEntregadoEntidad = triageEntregadoEntidad;
	}

	/**
	 * @return the triageEntidadEntregaPacienteDifunto
	 */
	public String getTriageEntidadEntregaPacienteDifunto() {
		return triageEntidadEntregaPacienteDifunto;
	}

	/**
	 * @param triageEntidadEntregaPacienteDifunto the triageEntidadEntregaPacienteDifunto to set
	 */
	public void setTriageEntidadEntregaPacienteDifunto(
			String triageEntidadEntregaPacienteDifunto) {
		this.triageEntidadEntregaPacienteDifunto = triageEntidadEntregaPacienteDifunto;
	}

	/**
	 * @return the triageNombrePersonaRecibeDifunto
	 */
	public String getTriageNombrePersonaRecibeDifunto() {
		return triageNombrePersonaRecibeDifunto;
	}

	/**
	 * @param triageNombrePersonaRecibeDifunto the triageNombrePersonaRecibeDifunto to set
	 */
	public void setTriageNombrePersonaRecibeDifunto(
			String triageNombrePersonaRecibeDifunto) {
		this.triageNombrePersonaRecibeDifunto = triageNombrePersonaRecibeDifunto;
	}

	/**
	 * @return the triageRegistroPersonaRecibeDifunto
	 */
	public String getTriageRegistroPersonaRecibeDifunto() {
		return triageRegistroPersonaRecibeDifunto;
	}

	/**
	 * @param triageRegistroPersonaRecibeDifunto the triageRegistroPersonaRecibeDifunto to set
	 */
	public void setTriageRegistroPersonaRecibeDifunto(
			String triageRegistroPersonaRecibeDifunto) {
		this.triageRegistroPersonaRecibeDifunto = triageRegistroPersonaRecibeDifunto;
	}

	/**
	 * @return the triageGlasgow14o15
	 */
	public Boolean getTriageGlasgow14o15() {
		return triageGlasgow14o15;
	}

	/**
	 * @param triageGlasgow14o15 the triageGlasgow14o15 to set
	 */
	public void setTriageGlasgow14o15(Boolean triageGlasgow14o15) {
		this.triageGlasgow14o15 = triageGlasgow14o15;
	}

	/**
	 * @return the procedimientos
	 */
	public ProcedimientosSpringDto getProcedimientos() {
		return procedimientos;
	}

	/**
	 * @param procedimientos the procedimientos to set
	 */
	public void setProcedimientos(ProcedimientosSpringDto procedimientos) {
		this.procedimientos = procedimientos;
	}

	/**
	 * @return the evaluacion
	 */
	public EvaluacionCompletaSpringDto getEvaluacion() {
		return evaluacion;
	}

	/**
	 * @param evaluacion the evaluacion to set
	 */
	public void setEvaluacion(EvaluacionCompletaSpringDto evaluacion) {
		this.evaluacion = evaluacion;
	}

	

	public ConceptSpringDto getPermeavilidadViaAerea() {
		return permeavilidadViaAerea;
	}

	public void setPermeavilidadViaAerea(ConceptSpringDto permeavilidadViaAerea) {
		this.permeavilidadViaAerea = permeavilidadViaAerea;
	}

	public String getCausaObstruccionVia() {
		return causaObstruccionVia;
	}

	public void setCausaObstruccionVia(String causaObstruccionVia) {
		this.causaObstruccionVia = causaObstruccionVia;
	}

	public Integer getRespiracion() {
		return respiracion;
	}

	public void setRespiracion(Integer respiracion) {
		this.respiracion = respiracion;
	}

	public Integer getPulso() {
		return pulso;
	}

	public void setPulso(Integer pulso) {
		this.pulso = pulso;
	}

	public Integer getRitmoPulso() {
		return ritmoPulso;
	}

	public void setRitmoPulso(Integer ritmoPulso) {
		this.ritmoPulso = ritmoPulso;
	}

	public Integer getFuerzaPulso() {
		return fuerzaPulso;
	}

	public void setFuerzaPulso(Integer fuerzaPulso) {
		this.fuerzaPulso = fuerzaPulso;
	}

	public Integer getNivelRespuesta() {
		return nivelRespuesta;
	}

	public void setNivelRespuesta(Integer nivelRespuesta) {
		this.nivelRespuesta = nivelRespuesta;
	}

	public Boolean getPielNormal() {
		return pielNormal;
	}

	public void setPielNormal(Boolean pielNormal) {
		this.pielNormal = pielNormal;
	}

	public Boolean getPielPalida() {
		return pielPalida;
	}

	public void setPielPalida(Boolean pielPalida) {
		this.pielPalida = pielPalida;
	}

	public Boolean getPielCaliente() {
		return pielCaliente;
	}

	public void setPielCaliente(Boolean pielCaliente) {
		this.pielCaliente = pielCaliente;
	}

	public Boolean getPielCianotica() {
		return pielCianotica;
	}

	public void setPielCianotica(Boolean pielCianotica) {
		this.pielCianotica = pielCianotica;
	}

	public Boolean getPielHumeda() {
		return pielHumeda;
	}

	public void setPielHumeda(Boolean pielHumeda) {
		this.pielHumeda = pielHumeda;
	}

	
	public Boolean getPielSeca() {
		return pielSeca;
	}

	public void setPielSeca(Boolean pielSeca) {
		this.pielSeca = pielSeca;
	}

	public Boolean getPupilasMioticas() {
		return pupilasMioticas;
	}

	public void setPupilasMioticas(Boolean pupilasMioticas) {
		this.pupilasMioticas = pupilasMioticas;
	}

	public Boolean getPupilasMidriaticas() {
		return pupilasMidriaticas;
	}

	public void setPupilasMidriaticas(Boolean pupilasMidriaticas) {
		this.pupilasMidriaticas = pupilasMidriaticas;
	}

	

	public Boolean getPupilasIsocoricas() {
		return pupilasIsocoricas;
	}

	public void setPupilasIsocoricas(Boolean pupilasIsocoricas) {
		this.pupilasIsocoricas = pupilasIsocoricas;
	}

	public Boolean getPupilasReactivas() {
		return pupilasReactivas;
	}

	public void setPupilasReactivas(Boolean pupilasReactivas) {
		this.pupilasReactivas = pupilasReactivas;
	}

	public Boolean getPupilasNoReactivas() {
		return pupilasNoReactivas;
	}

	public void setPupilasNoReactivas(Boolean pupilasNoReactivas) {
		this.pupilasNoReactivas = pupilasNoReactivas;
	}

	public Boolean getPupilasNoEvaluables() {
		return pupilasNoEvaluables;
	}

	public void setPupilasNoEvaluables(Boolean pupilasNoEvaluables) {
		this.pupilasNoEvaluables = pupilasNoEvaluables;
	}

	

	public Integer getGlasgowRO() {
		return glasgowRO;
	}

	public void setGlasgowRO(Integer glasgowRO) {
		this.glasgowRO = glasgowRO;
	}

	public Integer getGlasgowRV() {
		return glasgowRV;
	}

	public void setGlasgowRV(Integer glasgowRV) {
		this.glasgowRV = glasgowRV;
	}

	public Integer getGlasgowRM() {
		return glasgowRM;
	}

	public void setGlasgowRM(Integer glasgowRM) {
		this.glasgowRM = glasgowRM;
	}

	public Integer getTipoEmergencia() {
		return tipoEmergencia;
	}

	public void setTipoEmergencia(Integer tipoEmergencia) {
		this.tipoEmergencia = tipoEmergencia;
	}

	public Boolean getPolitrauma() {
		return politrauma;
	}

	public void setPolitrauma(Boolean politrauma) {
		this.politrauma = politrauma;
	}

	public Boolean getTec() {
		return tec;
	}

	public void setTec(Boolean tec) {
		this.tec = tec;
	}

	public Boolean getMaxilofacial() {
		return maxilofacial;
	}

	public void setMaxilofacial(Boolean maxilofacial) {
		this.maxilofacial = maxilofacial;
	}

	public Boolean getRaquimedular() {
		return raquimedular;
	}

	public void setRaquimedular(Boolean raquimedular) {
		this.raquimedular = raquimedular;
	}

	public Boolean getTorax() {
		return torax;
	}

	public void setTorax(Boolean torax) {
		this.torax = torax;
	}

	public Boolean getAbdomen() {
		return abdomen;
	}

	public void setAbdomen(Boolean abdomen) {
		this.abdomen = abdomen;
	}

	public Boolean getPelvico() {
		return pelvico;
	}

	public void setPelvico(Boolean pelvico) {
		this.pelvico = pelvico;
	}

	public Boolean getExtremidadSuperior() {
		return extremidadSuperior;
	}

	public void setExtremidadSuperior(Boolean extremidadSuperior) {
		this.extremidadSuperior = extremidadSuperior;
	}

	public Boolean getExtremidadInferior() {
		return extremidadInferior;
	}

	public void setExtremidadInferior(Boolean extremidadInferior) {
		this.extremidadInferior = extremidadInferior;
	}

	public Boolean getTejidosBlandos() {
		return tejidosBlandos;
	}

	public void setTejidosBlandos(Boolean tejidosBlandos) {
		this.tejidosBlandos = tejidosBlandos;
	}

	public Boolean getOsteomuscular() {
		return osteomuscular;
	}

	public void setOsteomuscular(Boolean osteomuscular) {
		this.osteomuscular = osteomuscular;
	}

	public Boolean getOrganosSentidos() {
		return organosSentidos;
	}

	public void setOrganosSentidos(Boolean organosSentidos) {
		this.organosSentidos = organosSentidos;
	}

	public Boolean getOtroTrauma() {
		return otroTrauma;
	}

	public void setOtroTrauma(Boolean otroTrauma) {
		this.otroTrauma = otroTrauma;
	}

	public String getCualOtroTrauma() {
		return cualOtroTrauma;
	}

	public void setCualOtroTrauma(String cualOtroTrauma) {
		this.cualOtroTrauma = cualOtroTrauma;
	}

	public Integer getPrioridadTriage() {
		return prioridadTriage;
	}

	public void setPrioridadTriage(Integer prioridadTriage) {
		this.prioridadTriage = prioridadTriage;
	}

	
	/**
	 * @return the hallazgos
	 */
	public List<HallazgoSpringDto> getHallazgos() {
		return hallazgos;
	}

	/**
	 * @param hallazgos the hallazgos to set
	 */
	public void setHallazgos(List<HallazgoSpringDto> hallazgos) {
		this.hallazgos = hallazgos;
	}

	/**
	 * @return the triageViaAreaObstruida
	 */
	public Boolean getTriageViaAreaObstruida() {
		return triageViaAreaObstruida;
	}

	/**
	 * @param triageViaAreaObstruida the triageViaAreaObstruida to set
	 */
	public void setTriageViaAreaObstruida(Boolean triageViaAreaObstruida) {
		this.triageViaAreaObstruida = triageViaAreaObstruida;
	}

	/**
	 * @return the triageInsuficienciaRespiratoria
	 */
	public Boolean getTriageInsuficienciaRespiratoria() {
		return triageInsuficienciaRespiratoria;
	}

	/**
	 * @param triageInsuficienciaRespiratoria the triageInsuficienciaRespiratoria to set
	 */
	public void setTriageInsuficienciaRespiratoria(
			Boolean triageInsuficienciaRespiratoria) {
		this.triageInsuficienciaRespiratoria = triageInsuficienciaRespiratoria;
	}

	/**
	 * @return the triagePa90
	 */
	public Boolean getTriagePa90() {
		return triagePa90;
	}

	/**
	 * @param triagePa90 the triagePa90 to set
	 */
	public void setTriagePa90(Boolean triagePa90) {
		this.triagePa90 = triagePa90;
	}

	/**
	 * @return the triageMultiplesHeridas
	 */
	public Boolean getTriageMultiplesHeridas() {
		return triageMultiplesHeridas;
	}

	/**
	 * @param triageMultiplesHeridas the triageMultiplesHeridas to set
	 */
	public void setTriageMultiplesHeridas(Boolean triageMultiplesHeridas) {
		this.triageMultiplesHeridas = triageMultiplesHeridas;
	}

	/**
	 * @return the triageHemorragiasNoControladas
	 */
	public Boolean getTriageHemorragiasNoControladas() {
		return triageHemorragiasNoControladas;
	}

	/**
	 * @param triageHemorragiasNoControladas the triageHemorragiasNoControladas to set
	 */
	public void setTriageHemorragiasNoControladas(
			Boolean triageHemorragiasNoControladas) {
		this.triageHemorragiasNoControladas = triageHemorragiasNoControladas;
	}

	/**
	 * @return the triageLesionCervicalIncompleta
	 */
	public Boolean getTriageLesionCervicalIncompleta() {
		return triageLesionCervicalIncompleta;
	}

	/**
	 * @param triageLesionCervicalIncompleta the triageLesionCervicalIncompleta to set
	 */
	public void setTriageLesionCervicalIncompleta(
			Boolean triageLesionCervicalIncompleta) {
		this.triageLesionCervicalIncompleta = triageLesionCervicalIncompleta;
	}

	/**
	 * @return the triageGlasgow4a8
	 */
	public Boolean getTriageGlasgow4a8() {
		return triageGlasgow4a8;
	}

	/**
	 * @param triageGlasgow4a8 the triageGlasgow4a8 to set
	 */
	public void setTriageGlasgow4a8(Boolean triageGlasgow4a8) {
		this.triageGlasgow4a8 = triageGlasgow4a8;
	}

	/**
	 * @return the triageExcitacionPsicomotora
	 */
	public Boolean getTriageExcitacionPsicomotora() {
		return triageExcitacionPsicomotora;
	}

	/**
	 * @param triageExcitacionPsicomotora the triageExcitacionPsicomotora to set
	 */
	public void setTriageExcitacionPsicomotora(Boolean triageExcitacionPsicomotora) {
		this.triageExcitacionPsicomotora = triageExcitacionPsicomotora;
	}

	/**
	 * @return the triageAbdomenAgudo
	 */
	public Boolean getTriageAbdomenAgudo() {
		return triageAbdomenAgudo;
	}

	/**
	 * @param triageAbdomenAgudo the triageAbdomenAgudo to set
	 */
	public void setTriageAbdomenAgudo(Boolean triageAbdomenAgudo) {
		this.triageAbdomenAgudo = triageAbdomenAgudo;
	}

	/**
	 * @return the triageEvisceracion
	 */
	public Boolean getTriageEvisceracion() {
		return triageEvisceracion;
	}

	/**
	 * @param triageEvisceracion the triageEvisceracion to set
	 */
	public void setTriageEvisceracion(Boolean triageEvisceracion) {
		this.triageEvisceracion = triageEvisceracion;
	}

	/**
	 * @return the triageTrabajoPartoSangrado
	 */
	public Boolean getTriageTrabajoPartoSangrado() {
		return triageTrabajoPartoSangrado;
	}

	/**
	 * @param triageTrabajoPartoSangrado the triageTrabajoPartoSangrado to set
	 */
	public void setTriageTrabajoPartoSangrado(Boolean triageTrabajoPartoSangrado) {
		this.triageTrabajoPartoSangrado = triageTrabajoPartoSangrado;
	}

	/**
	 * @return the triageDolorToracico
	 */
	public Boolean getTriageDolorToracico() {
		return triageDolorToracico;
	}

	/**
	 * @param triageDolorToracico the triageDolorToracico to set
	 */
	public void setTriageDolorToracico(Boolean triageDolorToracico) {
		this.triageDolorToracico = triageDolorToracico;
	}

	/**
	 * @return the triageArritmias
	 */
	public Boolean getTriageArritmias() {
		return triageArritmias;
	}

	/**
	 * @param triageArritmias the triageArritmias to set
	 */
	public void setTriageArritmias(Boolean triageArritmias) {
		this.triageArritmias = triageArritmias;
	}

	/**
	 * @return the triageHemorragiasControladas
	 */
	public Boolean getTriageHemorragiasControladas() {
		return triageHemorragiasControladas;
	}

	/**
	 * @param triageHemorragiasControladas the triageHemorragiasControladas to set
	 */
	public void setTriageHemorragiasControladas(Boolean triageHemorragiasControladas) {
		this.triageHemorragiasControladas = triageHemorragiasControladas;
	}

	/**
	 * @return the triageLesionMedularDorsal
	 */
	public Boolean getTriageLesionMedularDorsal() {
		return triageLesionMedularDorsal;
	}

	/**
	 * @param triageLesionMedularDorsal the triageLesionMedularDorsal to set
	 */
	public void setTriageLesionMedularDorsal(Boolean triageLesionMedularDorsal) {
		this.triageLesionMedularDorsal = triageLesionMedularDorsal;
	}

	/**
	 * @return the triageGlasgow9a13
	 */
	public Boolean getTriageGlasgow9a13() {
		return triageGlasgow9a13;
	}

	/**
	 * @param triageGlasgow9a13 the triageGlasgow9a13 to set
	 */
	public void setTriageGlasgow9a13(Boolean triageGlasgow9a13) {
		this.triageGlasgow9a13 = triageGlasgow9a13;
	}

	/**
	 * @return the triageAlteracionEstadoConciencia
	 */
	public Boolean getTriageAlteracionEstadoConciencia() {
		return triageAlteracionEstadoConciencia;
	}

	/**
	 * @param triageAlteracionEstadoConciencia the triageAlteracionEstadoConciencia to set
	 */
	public void setTriageAlteracionEstadoConciencia(
			Boolean triageAlteracionEstadoConciencia) {
		this.triageAlteracionEstadoConciencia = triageAlteracionEstadoConciencia;
	}

	/**
	 * @return the triageFracturasMayores
	 */
	public Boolean getTriageFracturasMayores() {
		return triageFracturasMayores;
	}

	/**
	 * @param triageFracturasMayores the triageFracturasMayores to set
	 */
	public void setTriageFracturasMayores(Boolean triageFracturasMayores) {
		this.triageFracturasMayores = triageFracturasMayores;
	}

	/**
	 * @return the triageQuemadurasModeradas
	 */
	public Boolean getTriageQuemadurasModeradas() {
		return triageQuemadurasModeradas;
	}

	/**
	 * @param triageQuemadurasModeradas the triageQuemadurasModeradas to set
	 */
	public void setTriageQuemadurasModeradas(Boolean triageQuemadurasModeradas) {
		this.triageQuemadurasModeradas = triageQuemadurasModeradas;
	}

	/**
	 * @return the triageIntoxicacion
	 */
	public Boolean getTriageIntoxicacion() {
		return triageIntoxicacion;
	}

	/**
	 * @param triageIntoxicacion the triageIntoxicacion to set
	 */
	public void setTriageIntoxicacion(Boolean triageIntoxicacion) {
		this.triageIntoxicacion = triageIntoxicacion;
	}

	/**
	 * @return the triageLesionMedularLumbar
	 */
	public Boolean getTriageLesionMedularLumbar() {
		return triageLesionMedularLumbar;
	}

	/**
	 * @param triageLesionMedularLumbar the triageLesionMedularLumbar to set
	 */
	public void setTriageLesionMedularLumbar(Boolean triageLesionMedularLumbar) {
		this.triageLesionMedularLumbar = triageLesionMedularLumbar;
	}

	/**
	 * @return the triageFracturasNoProximales
	 */
	public Boolean getTriageFracturasNoProximales() {
		return triageFracturasNoProximales;
	}

	/**
	 * @param triageFracturasNoProximales the triageFracturasNoProximales to set
	 */
	public void setTriageFracturasNoProximales(Boolean triageFracturasNoProximales) {
		this.triageFracturasNoProximales = triageFracturasNoProximales;
	}

	/**
	 * @return the triageLesionesSuperficiales
	 */
	public Boolean getTriageLesionesSuperficiales() {
		return triageLesionesSuperficiales;
	}

	/**
	 * @param triageLesionesSuperficiales the triageLesionesSuperficiales to set
	 */
	public void setTriageLesionesSuperficiales(Boolean triageLesionesSuperficiales) {
		this.triageLesionesSuperficiales = triageLesionesSuperficiales;
	}

	/**
	 * @return the triageQuemadurasPrimerGrado
	 */
	public Boolean getTriageQuemadurasPrimerGrado() {
		return triageQuemadurasPrimerGrado;
	}

	/**
	 * @param triageQuemadurasPrimerGrado the triageQuemadurasPrimerGrado to set
	 */
	public void setTriageQuemadurasPrimerGrado(Boolean triageQuemadurasPrimerGrado) {
		this.triageQuemadurasPrimerGrado = triageQuemadurasPrimerGrado;
	}

	/**
	 * @return the triageAfectados
	 */
	public Boolean getTriageAfectados() {
		return triageAfectados;
	}

	/**
	 * @param triageAfectados the triageAfectados to set
	 */
	public void setTriageAfectados(Boolean triageAfectados) {
		this.triageAfectados = triageAfectados;
	}

	/**
	 * @return the triageParoProlongado
	 */
	public Boolean getTriageParoProlongado() {
		return triageParoProlongado;
	}

	/**
	 * @param triageParoProlongado the triageParoProlongado to set
	 */
	public void setTriageParoProlongado(Boolean triageParoProlongado) {
		this.triageParoProlongado = triageParoProlongado;
	}

	/**
	 * @return the triageLesionCervicalCompleta
	 */
	public Boolean getTriageLesionCervicalCompleta() {
		return triageLesionCervicalCompleta;
	}

	/**
	 * @param triageLesionCervicalCompleta the triageLesionCervicalCompleta to set
	 */
	public void setTriageLesionCervicalCompleta(Boolean triageLesionCervicalCompleta) {
		this.triageLesionCervicalCompleta = triageLesionCervicalCompleta;
	}

	/**
	 * @return the triageGlasgow3
	 */
	public Boolean getTriageGlasgow3() {
		return triageGlasgow3;
	}

	/**
	 * @param triageGlasgow3 the triageGlasgow3 to set
	 */
	public void setTriageGlasgow3(Boolean triageGlasgow3) {
		this.triageGlasgow3 = triageGlasgow3;
	}

	/**
	 * @return the triageExposicionMasaEncefalica
	 */
	public Boolean getTriageExposicionMasaEncefalica() {
		return triageExposicionMasaEncefalica;
	}

	/**
	 * @param triageExposicionMasaEncefalica the triageExposicionMasaEncefalica to set
	 */
	public void setTriageExposicionMasaEncefalica(
			Boolean triageExposicionMasaEncefalica) {
		this.triageExposicionMasaEncefalica = triageExposicionMasaEncefalica;
	}

	/**
	 * @return the triageLesionesImapidenRcp
	 */
	public Boolean getTriageLesionesImpidenRcp() {
		return triageLesionesImpidenRcp;
	}

	/**
	 * @param triageLesionesImapidenRcp the triageLesionesImapidenRcp to set
	 */
	public void setTriageLesionesImpidenRcp(Boolean triageLesionesImapidenRcp) {
		this.triageLesionesImpidenRcp = triageLesionesImapidenRcp;
	}

	/**
	 * @return the triageQuemadurasGraves
	 */
	public Boolean getTriageQuemadurasGraves() {
		return triageQuemadurasGraves;
	}

	/**
	 * @param triageQuemadurasGraves the triageQuemadurasGraves to set
	 */
	public void setTriageQuemadurasGraves(Boolean triageQuemadurasGraves) {
		this.triageQuemadurasGraves = triageQuemadurasGraves;
	}

	/**
	 * @return the triageOtro
	 */
	public String getTriageOtro() {
		return triageOtro;
	}

	/**
	 * @param triageOtro the triageOtro to set
	 */
	public void setTriageOtro(String triageOtro) {
		this.triageOtro = triageOtro;
	}


	
}
