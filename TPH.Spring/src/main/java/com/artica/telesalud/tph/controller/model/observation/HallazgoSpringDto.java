package com.artica.telesalud.tph.controller.model.observation;

import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.observation.ObservationData;
/**
 * Clase utilizada para mapear hallazgos a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class HallazgoSpringDto {
	private Integer orden;
	private Double x, y;
	private Integer hallazgoId;
	private EvaluacionCompletaSpringDto evaluacion;
	private Boolean cuerpoExtranio = Boolean.FALSE;
	private Boolean abrasion = Boolean.FALSE;
	private Boolean amputacion=Boolean.FALSE;
	private Boolean aplastamiento=Boolean.FALSE;
	private Boolean avulsion=Boolean.FALSE;
	private Boolean contusion=Boolean.FALSE;
	private Boolean dolor=Boolean.FALSE;
	private Boolean esguince=Boolean.FALSE;
	private Boolean fracturaAbierta=Boolean.FALSE;
	private Boolean quemadura=Boolean.FALSE;
	private Boolean herida=Boolean.FALSE;
	private Boolean fracturaCerrada=Boolean.FALSE;
	private Boolean heridaArmaDeFuego=Boolean.FALSE;
	private Boolean heridaArmaBlanca=Boolean.FALSE;
	private Boolean hemorragia=Boolean.FALSE;
	private Boolean laceracion=Boolean.FALSE;
	private Boolean mordida=Boolean.FALSE;
	private Boolean picadura=Boolean.FALSE;
	private Boolean puncion=Boolean.FALSE;
	private Boolean traumaCerrado=Boolean.FALSE;
	private Boolean traumaPenetrante=Boolean.FALSE;
	private Boolean hematoma=Boolean.FALSE;
	private Boolean crepitacion = Boolean.FALSE;
	

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

	public HallazgoSpringDto() {

	}
	
	/**
	 * @return the cuerpoExtranio
	 */
	public Boolean getCuerpoExtranio() {
		return cuerpoExtranio;
	}

	/**
	 * @param cuerpoExtranio the cuerpoExtranio to set
	 */
	public void setCuerpoExtranio(Boolean cuerpoExtranio) {
		this.cuerpoExtranio = cuerpoExtranio;
	}

	public HallazgoSpringDto(ObservationData od, Encounter encounter, Obs obsParent) {
		if(obsParent != null && encounter != null)
		{
			//evaluacion = new EvaluacionCompletaSpringDto(obsParent, encounter);
		}
		ConceptsCode concepts = new ConceptsCode();
		setHallazgoId(od.getObsId());
		Object ordenObject =  ObservationData.obtenerValorConcepto(
				od.getSons(), concepts.cHallazgoOrden());

		Integer orden = null;
		if(ordenObject instanceof Number)
		{
			orden = ((Number)ordenObject).intValue();
		}
		if (orden != null) {
			setOrden(orden.intValue());
		}
		Object xObject = ObservationData.obtenerValorConcepto(od.getSons(),
				concepts.cHallazgoPosicionX());
		Double x = null ;
		if(xObject instanceof Number)
		{
			x = ((Number)xObject).doubleValue();
		}
		if (x != null) {
			setX(x);
		}
		Object yObject = ObservationData.obtenerValorConcepto(od.getSons(),
				concepts.cHallazgoPosicionY());
		Double y = null ;
		if(yObject instanceof Number)
		{
			y = ((Number)yObject).doubleValue();
		}
		
		if (orden != null) {
			setY(y);
		}
		cuerpoExtranio = (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionCuerpoExtranio());
		 abrasion = (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionAbrasion());
		 amputacion= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionAmputacion());
		 aplastamiento= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionAplastamiento());
		 avulsion= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionAvulsion());
		 contusion= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionContusion());
		 dolor= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionDolor());
		 esguince= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionEsguince());
		 fracturaAbierta= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionFracturaAbierta());
		 quemadura= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionQuemadura());
		 herida= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionHerida());
		 fracturaCerrada= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionFracturaCerrada());
		 heridaArmaDeFuego= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionHeridaArmaFuego());
		 heridaArmaBlanca= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionHeridaArmaBlanca());
		 hemorragia= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionHemorragia());
		 laceracion= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionLaceracion());
		 mordida= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionMordida());
		 picadura= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionPicadura());
		 puncion= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionPuncion());
		 traumaCerrado= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionTraumaCerrado());
		 traumaPenetrante= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionTraumaPenetrante());
		 hematoma= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionHematoma());
		 crepitacion= (Boolean)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cLesionCrepitacion());
		
	}

	

	/**
	 * @return the crepitacion
	 */
	public Boolean getCrepitacion() {
		return crepitacion;
	}

	/**
	 * @param crepitacion the crepitacion to set
	 */
	public void setCrepitacion(Boolean crepitacion) {
		this.crepitacion = crepitacion;
	}

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	/**
	 * @return the x
	 */
	public Double getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(Double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public Double getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(Double y) {
		this.y = y;
	}





	/**
	 * @return the hallazgoId
	 */
	public Integer getHallazgoId() {
		return hallazgoId;
	}

	/**
	 * @param hallazgoId
	 *            the hallazgoId to set
	 */
	public void setHallazgoId(Integer hallazgoId) {
		this.hallazgoId = hallazgoId;
	}

	

	/**
	 * @return the abrasion
	 */
	public Boolean getAbrasion() {
		return abrasion;
	}

	/**
	 * @param abrasion the abrasion to set
	 */
	public void setAbrasion(Boolean abrasion) {
		this.abrasion = abrasion;
	}

	/**
	 * @return the amputacion
	 */
	public Boolean getAmputacion() {
		return amputacion;
	}

	/**
	 * @param amputacion the amputacion to set
	 */
	public void setAmputacion(Boolean amputacion) {
		this.amputacion = amputacion;
	}

	/**
	 * @return the aplastamiento
	 */
	public Boolean getAplastamiento() {
		return aplastamiento;
	}

	/**
	 * @param aplastamiento the aplastamiento to set
	 */
	public void setAplastamiento(Boolean aplastamiento) {
		this.aplastamiento = aplastamiento;
	}

	/**
	 * @return the avulsion
	 */
	public Boolean getAvulsion() {
		return avulsion;
	}

	/**
	 * @param avulsion the avulsion to set
	 */
	public void setAvulsion(Boolean avulsion) {
		this.avulsion = avulsion;
	}

	/**
	 * @return the contusion
	 */
	public Boolean getContusion() {
		return contusion;
	}

	/**
	 * @param contusion the contusion to set
	 */
	public void setContusion(Boolean contusion) {
		this.contusion = contusion;
	}

	/**
	 * @return the dolor
	 */
	public Boolean getDolor() {
		return dolor;
	}

	/**
	 * @param dolor the dolor to set
	 */
	public void setDolor(Boolean dolor) {
		this.dolor = dolor;
	}

	/**
	 * @return the esguince
	 */
	public Boolean getEsguince() {
		return esguince;
	}

	/**
	 * @param esguince the esguince to set
	 */
	public void setEsguince(Boolean esguince) {
		this.esguince = esguince;
	}

	/**
	 * @return the fracturaAbierta
	 */
	public Boolean getFracturaAbierta() {
		return fracturaAbierta;
	}

	/**
	 * @param fracturaAbierta the fracturaAbierta to set
	 */
	public void setFracturaAbierta(Boolean fracturaAbierta) {
		this.fracturaAbierta = fracturaAbierta;
	}

	/**
	 * @return the quemadura
	 */
	public Boolean getQuemadura() {
		return quemadura;
	}

	/**
	 * @param quemadura the quemadura to set
	 */
	public void setQuemadura(Boolean quemadura) {
		this.quemadura = quemadura;
	}

	/**
	 * @return the herida
	 */
	public Boolean getHerida() {
		return herida;
	}

	/**
	 * @param herida the herida to set
	 */
	public void setHerida(Boolean herida) {
		this.herida = herida;
	}

	/**
	 * @return the fracturaCerrada
	 */
	public Boolean getFracturaCerrada() {
		return fracturaCerrada;
	}

	/**
	 * @param fracturaCerrada the fracturaCerrada to set
	 */
	public void setFracturaCerrada(Boolean fracturaCerrada) {
		this.fracturaCerrada = fracturaCerrada;
	}

	/**
	 * @return the heridaArmaDeFuego
	 */
	public Boolean getHeridaArmaDeFuego() {
		return heridaArmaDeFuego;
	}

	/**
	 * @param heridaArmaDeFuego the heridaArmaDeFuego to set
	 */
	public void setHeridaArmaDeFuego(Boolean heridaArmaDeFuego) {
		this.heridaArmaDeFuego = heridaArmaDeFuego;
	}

	/**
	 * @return the heridaArmaBlanca
	 */
	public Boolean getHeridaArmaBlanca() {
		return heridaArmaBlanca;
	}

	/**
	 * @param heridaArmaBlanca the heridaArmaBlanca to set
	 */
	public void setHeridaArmaBlanca(Boolean heridaArmaBlanca) {
		this.heridaArmaBlanca = heridaArmaBlanca;
	}

	/**
	 * @return the hemorragia
	 */
	public Boolean getHemorragia() {
		return hemorragia;
	}

	/**
	 * @param hemorragia the hemorragia to set
	 */
	public void setHemorragia(Boolean hemorragia) {
		this.hemorragia = hemorragia;
	}

	/**
	 * @return the laceracion
	 */
	public Boolean getLaceracion() {
		return laceracion;
	}

	/**
	 * @param laceracion the laceracion to set
	 */
	public void setLaceracion(Boolean laceracion) {
		this.laceracion = laceracion;
	}

	/**
	 * @return the mordida
	 */
	public Boolean getMordida() {
		return mordida;
	}

	/**
	 * @param mordida the mordida to set
	 */
	public void setMordida(Boolean mordida) {
		this.mordida = mordida;
	}

	/**
	 * @return the picadura
	 */
	public Boolean getPicadura() {
		return picadura;
	}

	/**
	 * @param picadura the picadura to set
	 */
	public void setPicadura(Boolean picadura) {
		this.picadura = picadura;
	}

	/**
	 * @return the puncion
	 */
	public Boolean getPuncion() {
		return puncion;
	}

	/**
	 * @param puncion the puncion to set
	 */
	public void setPuncion(Boolean puncion) {
		this.puncion = puncion;
	}

	/**
	 * @return the traumaCerrado
	 */
	public Boolean getTraumaCerrado() {
		return traumaCerrado;
	}

	/**
	 * @param traumaCerrado the traumaCerrado to set
	 */
	public void setTraumaCerrado(Boolean traumaCerrado) {
		this.traumaCerrado = traumaCerrado;
	}

	/**
	 * @return the traumaPenetrante
	 */
	public Boolean getTraumaPenetrante() {
		return traumaPenetrante;
	}

	/**
	 * @param traumaPenetrante the traumaPenetrante to set
	 */
	public void setTraumaPenetrante(Boolean traumaPenetrante) {
		this.traumaPenetrante = traumaPenetrante;
	}

	/**
	 * @return the hematoma
	 */
	public Boolean getHematoma() {
		return hematoma;
	}

	/**
	 * @param hematoma the hematoma to set
	 */
	public void setHematoma(Boolean hematoma) {
		this.hematoma = hematoma;
	}


	
}
