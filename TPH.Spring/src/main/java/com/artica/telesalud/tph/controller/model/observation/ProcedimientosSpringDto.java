package com.artica.telesalud.tph.controller.model.observation;

import java.util.Date;
import java.util.List;

import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.observation.ObservationData;
/**
 * Clase utilizada para mapear procedimientos a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class ProcedimientosSpringDto {

	
    private EvaluacionCompletaSpringDto evaluacion;
    private Boolean masajeCardiaco = Boolean.FALSE;
    private Boolean accesoVenosoCentral = Boolean.FALSE;
    private Boolean puncionOsea = Boolean.FALSE;
    /////////////////////////////////////////////////////////////////7
	private Boolean aspiracionSecreciones = false;
	private Boolean canulaOrofaringea = false;
	private Boolean despejeManual = false;
	private Boolean collarCervical = false;
	private Boolean canulaNasofaringea = false;
	private Boolean reanimacionRespiratoria = false;
	private Boolean canulaNasal = false;
	private Boolean mascaraNoReinhalacion = false;
	private Boolean mascaraSimple = false;
	private Boolean bvm = false;
	private Boolean rccp = false;
	private Boolean hemostasia = false;
	private Boolean dea = false;
	private Boolean monitoreoSignosVitales = false;
	private Boolean dispositivoSupragliotico = false;
	private Boolean cricotiroidotomia = false;
	private Boolean intubacionOrotraqueal = false;
	private Boolean descompresionTorax = false;
	private Boolean ventilacionMecanica = false;
	private Boolean rehidratacionOral = false;
	private Boolean ssn09 = false;
	private Boolean accesoVenosoPeriferico = false;
	private Boolean destrosa = false;
	private Boolean punsionOsea = false;
	private Boolean coloides = false;
	private Boolean hartman = false;
	private Boolean exposicion = false;
	private Boolean movimientoBloque = false;
	private Boolean lavadoCuracion = false;
	private Boolean chalecoExtracionVehicular = false;
	private Boolean mantaTermica = false;
	private Boolean tablaEspinalLarga = false;
	private Boolean inmovilizacionFerulas = false;
	private Boolean tablaEspinalCorta = false;

    private Boolean atencionParto = Boolean.FALSE;
    private Boolean glucometria = Boolean.FALSE;
    private Boolean torniqueteControlado = Boolean.FALSE;
	private String otrosProcedimientos;
	private Integer creator;
	private Date dateCreated;
	private Boolean desfibrilacion = Boolean.FALSE;
	private Double joulesDesfibrilacion = null;
	
	/**
	 * @return the atencionParto
	 */
	public Boolean getAtencionParto() {
		return atencionParto;
	}



	/**
	 * @return the desfibrilacion
	 */
	public Boolean getDesfibrilacion() {
		return desfibrilacion;
	}



	/**
	 * @param desfibrilacion the desfibrilacion to set
	 */
	public void setDesfibrilacion(Boolean desfibrilacion) {
		this.desfibrilacion = desfibrilacion;
	}



	/**
	 * @return the joulesDesfibrilacion
	 */
	public Double getJoulesDesfibrilacion() {
		return joulesDesfibrilacion;
	}



	/**
	 * @param joulesDesfibrilacion the joulesDesfibrilacion to set
	 */
	public void setJoulesDesfibrilacion(Double joulesDesfibrilacion) {
		this.joulesDesfibrilacion = joulesDesfibrilacion;
	}



	/**
	 * @param atencionParto the atencionParto to set
	 */
	public void setAtencionParto(Boolean atencionParto) {
		this.atencionParto = atencionParto;
	}



	/**
	 * @return the glucometria
	 */
	public Boolean getGlucometria() {
		return glucometria;
	}



	/**
	 * @param glucometria the glucometria to set
	 */
	public void setGlucometria(Boolean glucometria) {
		this.glucometria = glucometria;
	}



	/**
	 * @return the torniqueteControlado
	 */
	public Boolean getTorniqueteControlado() {
		return torniqueteControlado;
	}



	/**
	 * @param torniqueteControlado the torniqueteControlado to set
	 */
	public void setTorniqueteControlado(Boolean torniqueteControlado) {
		this.torniqueteControlado = torniqueteControlado;
	}



	public ProcedimientosSpringDto() {
		super();
	}



	/**
	 * @return the creator
	 */
	public Integer getCreator() {
		return creator;
	}



	/**
	 * @param creator the creator to set
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}



	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}



	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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



	/**
	 * @return the masajeCardiaco
	 */
	public Boolean getMasajeCardiaco() {
		return masajeCardiaco;
	}



	/**
	 * @param masajeCardiaco the masajeCardiaco to set
	 */
	public void setMasajeCardiaco(Boolean masajeCardiaco) {
		this.masajeCardiaco = masajeCardiaco;
	}



	/**
	 * @return the accesoVenosoCentral
	 */
	public Boolean getAccesoVenosoCentral() {
		return accesoVenosoCentral;
	}



	/**
	 * @param accesoVenosoCentral the accesoVenosoCentral to set
	 */
	public void setAccesoVenosoCentral(Boolean accesoVenosoCentral) {
		this.accesoVenosoCentral = accesoVenosoCentral;
	}



	/**
	 * @return the puncionOsea
	 */
	public Boolean getPuncionOsea() {
		return puncionOsea;
	}



	/**
	 * @param puncionOsea the puncionOsea to set
	 */
	public void setPuncionOsea(Boolean puncionOsea) {
		this.puncionOsea = puncionOsea;
	}



	public ProcedimientosSpringDto(ObservationData od, List<ObservationData> datos, Encounter encounter, Obs obsParent)
	{
		ConceptsCode concepts = new ConceptsCode();
		if(obsParent != null && encounter != null)
		{
			//evaluacion = new EvaluacionCompletaSpringDto(obsParent, encounter);
		}
		creator = od.getCreator();
		dateCreated = od.getDateCreated();
		setAspiracionSecreciones((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cAspiracionSecreciones()));
		setCanulaOrofaringea((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cCanulaOrofaringea()));
		setDespejeManual((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cDespejeManual()));
		setCollarCervical((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cCollarCervical()));
		setCanulaNasofaringea((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cCanulaNasofaringea()));
		setReanimacionRespiratoria((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cReanimacionRespiratoria()));
		setCanulaNasal((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cCanulaNasal()));
		setMascaraNoReinhalacion((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cMascaraNoReinhalacion()));
		setMascaraSimple((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cMascaraSimple()));
		setBvm((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cBvm()));
		setRccp((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cRccp()));
		setHemostasia((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cHemostasia()));
		setDea((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cDea()));
		setMonitoreoSignosVitales((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cMonitoreoSignosVitales()));
		setDispositivoSupragliotico((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cDispositivoSupragliotico()));
		setCricotiroidotomia((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cCricotiroidotomia()));
		setIntubacionOrotraqueal((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cIntubacionOrotraqueal()));
		setDescompresionTorax((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cDescompresionTorax()));
		setVentilacionMecanica((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cVentilacionMecanica()));
		setRehidratacionOral((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cRehidratacionOral()));
		setSsn09((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cSsn09()));
		setAccesoVenosoPeriferico((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cAccesoVenosoPeriferico()));
		setDestrosa((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cDestrosa()));
		setPunsionOsea((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cPunsionOsea()));
		setColoides((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cColoides()));
		setHartman((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cHartman()));
		setExposicion((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cExposicion()));
		setMovimientoBloque((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cMovimientoBloque()));
		setLavadoCuracion((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cLavadoCuracion()));
		setChalecoExtracionVehicular((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cChalecoExtracionVehicular()));
		setMantaTermica((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cMantaTermica()));
		setTablaEspinalLarga((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cTablaEspinalLarga()));
		setInmovilizacionFerulas((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cInmovilizacionFerulas()));
		setTablaEspinalCorta((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cTablaEspinalCorta()));
		setOtrosProcedimientos((String) ObservationData.obtenerValorConcepto(datos, concepts.cOtrosProcedimientos()));
		setMasajeCardiaco((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cProcedimientoMasajeCardiaco()));
		setAccesoVenosoCentral((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cProcedimientoAccesoVenosoCentral()));
		setPuncionOsea((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cProcedimientoPuncionOsea()));
		setAtencionParto((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cProcedimientoAtencionParto()));
		setGlucometria((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cProcedimientoGlucometria()));
		setTorniqueteControlado((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cProcedimientoTorniqueteControlado()));
		setDesfibrilacion((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cProcedimientoDesfibrilacion()));
		setJoulesDesfibrilacion((Double)ObservationData.obtenerValorConcepto(datos, concepts.cJoulesDesfibrilacion()));
	}


	
	/**
	 * @return the aspiracionSecreciones
	 */
	public Boolean getAspiracionSecreciones() {
		return aspiracionSecreciones;
	}
	/**
	 * @param aspiracionSecreciones the aspiracionSecreciones to set
	 */
	public void setAspiracionSecreciones(Boolean aspiracionSecreciones) {
		this.aspiracionSecreciones = aspiracionSecreciones;
	}
	/**
	 * @return the canulaOrofaringea
	 */
	public Boolean getCanulaOrofaringea() {
		return canulaOrofaringea;
	}
	/**
	 * @param canulaOrofaringea the canulaOrofaringea to set
	 */
	public void setCanulaOrofaringea(Boolean canulaOrofaringea) {
		this.canulaOrofaringea = canulaOrofaringea;
	}
	/**
	 * @return the despejeManual
	 */
	public Boolean getDespejeManual() {
		return despejeManual;
	}
	/**
	 * @param despejeManual the despejeManual to set
	 */
	public void setDespejeManual(Boolean despejeManual) {
		this.despejeManual = despejeManual;
	}
	/**
	 * @return the collarCervical
	 */
	public Boolean getCollarCervical() {
		return collarCervical;
	}
	/**
	 * @param collarCervical the collarCervical to set
	 */
	public void setCollarCervical(Boolean collarCervical) {
		this.collarCervical = collarCervical;
	}
	/**
	 * @return the canulaNasofaringea
	 */
	public Boolean getCanulaNasofaringea() {
		return canulaNasofaringea;
	}
	/**
	 * @param canulaNasofaringea the canulaNasofaringea to set
	 */
	public void setCanulaNasofaringea(Boolean canulaNasofaringea) {
		this.canulaNasofaringea = canulaNasofaringea;
	}
	
	/**
	 * @return the reanimacionRespiratoria
	 */
	public Boolean getReanimacionRespiratoria() {
		return reanimacionRespiratoria;
	}
	/**
	 * @param reanimacionRespiratoria the reanimacionRespiratoria to set
	 */
	public void setReanimacionRespiratoria(Boolean reanimacionRespiratoria) {
		this.reanimacionRespiratoria = reanimacionRespiratoria;
	}
	/**
	 * @return the canulaNasal
	 */
	public Boolean getCanulaNasal() {
		return canulaNasal;
	}
	/**
	 * @param canulaNasal the canulaNasal to set
	 */
	public void setCanulaNasal(Boolean canulaNasal) {
		this.canulaNasal = canulaNasal;
	}
	/**
	 * @return the mascaraNoReinhalacion
	 */
	public Boolean getMascaraNoReinhalacion() {
		return mascaraNoReinhalacion;
	}
	/**
	 * @param mascaraNoReinhalacion the mascaraNoReinhalacion to set
	 */
	public void setMascaraNoReinhalacion(Boolean mascaraNoReinhalacion) {
		this.mascaraNoReinhalacion = mascaraNoReinhalacion;
	}
	/**
	 * @return the mascaraSimple
	 */
	public Boolean getMascaraSimple() {
		return mascaraSimple;
	}
	/**
	 * @param mascaraSimple the mascaraSimple to set
	 */
	public void setMascaraSimple(Boolean mascaraSimple) {
		this.mascaraSimple = mascaraSimple;
	}
	/**
	 * @return the bvm
	 */
	public Boolean getBvm() {
		return bvm;
	}
	/**
	 * @param bvm the bvm to set
	 */
	public void setBvm(Boolean bvm) {
		this.bvm = bvm;
	}
	
	/**
	 * @return the rccp
	 */
	public Boolean getRccp() {
		return rccp;
	}
	/**
	 * @param rccp the rccp to set
	 */
	public void setRccp(Boolean rccp) {
		this.rccp = rccp;
	}
	/**
	 * @return the hemostasia
	 */
	public Boolean getHemostasia() {
		return hemostasia;
	}
	/**
	 * @param hemostasia the hemostasia to set
	 */
	public void setHemostasia(Boolean hemostasia) {
		this.hemostasia = hemostasia;
	}
	/**
	 * @return the dea
	 */
	public Boolean getDea() {
		return dea;
	}
	/**
	 * @param dea the dea to set
	 */
	public void setDea(Boolean dea) {
		this.dea = dea;
	}
	/**
	 * @return the monitoreoSignosVitales
	 */
	public Boolean getMonitoreoSignosVitales() {
		return monitoreoSignosVitales;
	}
	/**
	 * @param monitoreoSignosVitales the monitoreoSignosVitales to set
	 */
	public void setMonitoreoSignosVitales(Boolean monitoreoSignosVitales) {
		this.monitoreoSignosVitales = monitoreoSignosVitales;
	}
	/**
	 * @return the dispositivoSupragliotico
	 */
	public Boolean getDispositivoSupragliotico() {
		return dispositivoSupragliotico;
	}
	/**
	 * @param dispositivoSupragliotico the dispositivoSupragliotico to set
	 */
	public void setDispositivoSupragliotico(Boolean dispositivoSupragliotico) {
		this.dispositivoSupragliotico = dispositivoSupragliotico;
	}
	/**
	 * @return the cricotiroidotomia
	 */
	public Boolean getCricotiroidotomia() {
		return cricotiroidotomia;
	}
	/**
	 * @param cricotiroidotomia the cricotiroidotomia to set
	 */
	public void setCricotiroidotomia(Boolean cricotiroidotomia) {
		this.cricotiroidotomia = cricotiroidotomia;
	}
	/**
	 * @return the intubacionOrotraqueal
	 */
	public Boolean getIntubacionOrotraqueal() {
		return intubacionOrotraqueal;
	}
	/**
	 * @param intubacionOrotraqueal the intubacionOrotraqueal to set
	 */
	public void setIntubacionOrotraqueal(Boolean intubacionOrotraqueal) {
		this.intubacionOrotraqueal = intubacionOrotraqueal;
	}
	/**
	 * @return the descompresionTorax
	 */
	public Boolean getDescompresionTorax() {
		return descompresionTorax;
	}
	/**
	 * @param descompresionTorax the descompresionTorax to set
	 */
	public void setDescompresionTorax(Boolean descompresionTorax) {
		this.descompresionTorax = descompresionTorax;
	}
	/**
	 * @return the ventilacionMecanica
	 */
	public Boolean getVentilacionMecanica() {
		return ventilacionMecanica;
	}
	/**
	 * @param ventilacionMecanica the ventilacionMecanica to set
	 */
	public void setVentilacionMecanica(Boolean ventilacionMecanica) {
		this.ventilacionMecanica = ventilacionMecanica;
	}
	/**
	 * @return the rehidratacionOral
	 */
	public Boolean getRehidratacionOral() {
		return rehidratacionOral;
	}
	/**
	 * @param rehidratacionOral the rehidratacionOral to set
	 */
	public void setRehidratacionOral(Boolean rehidratacionOral) {
		this.rehidratacionOral = rehidratacionOral;
	}
	/**
	 * @return the ssn09
	 */
	public Boolean getSsn09() {
		return ssn09;
	}
	/**
	 * @param ssn09 the ssn09 to set
	 */
	public void setSsn09(Boolean ssn09) {
		this.ssn09 = ssn09;
	}
	/**
	 * @return the accesoVenosoPeriferico
	 */
	public Boolean getAccesoVenosoPeriferico() {
		return accesoVenosoPeriferico;
	}
	/**
	 * @param accesoVenosoPeriferico the accesoVenosoPeriferico to set
	 */
	public void setAccesoVenosoPeriferico(Boolean accesoVenosoPeriferico) {
		this.accesoVenosoPeriferico = accesoVenosoPeriferico;
	}
	/**
	 * @return the destrosa
	 */
	public Boolean getDestrosa() {
		return destrosa;
	}
	/**
	 * @param destrosa the destrosa to set
	 */
	public void setDestrosa(Boolean destrosa) {
		this.destrosa = destrosa;
	}
	/**
	 * @return the punsionOsea
	 */
	public Boolean getPunsionOsea() {
		return punsionOsea;
	}
	/**
	 * @param punsionOsea the punsionOsea to set
	 */
	public void setPunsionOsea(Boolean punsionOsea) {
		this.punsionOsea = punsionOsea;
	}
	/**
	 * @return the coloides
	 */
	public Boolean getColoides() {
		return coloides;
	}
	/**
	 * @param coloides the coloides to set
	 */
	public void setColoides(Boolean coloides) {
		this.coloides = coloides;
	}
	/**
	 * @return the hartman
	 */
	public Boolean getHartman() {
		return hartman;
	}
	/**
	 * @param hartman the hartman to set
	 */
	public void setHartman(Boolean hartman) {
		this.hartman = hartman;
	}
	/**
	 * @return the exposicion
	 */
	public Boolean getExposicion() {
		return exposicion;
	}
	/**
	 * @param exposicion the exposicion to set
	 */
	public void setExposicion(Boolean exposicion) {
		this.exposicion = exposicion;
	}
	/**
	 * @return the movimientoBloque
	 */
	public Boolean getMovimientoBloque() {
		return movimientoBloque;
	}
	/**
	 * @param movimientoBloque the movimientoBloque to set
	 */
	public void setMovimientoBloque(Boolean movimientoBloque) {
		this.movimientoBloque = movimientoBloque;
	}
	/**
	 * @return the lavadoCuracion
	 */
	public Boolean getLavadoCuracion() {
		return lavadoCuracion;
	}
	/**
	 * @param lavadoCuracion the lavadoCuracion to set
	 */
	public void setLavadoCuracion(Boolean lavadoCuracion) {
		this.lavadoCuracion = lavadoCuracion;
	}
	/**
	 * @return the chalecoExtracionVehicular
	 */
	public Boolean getChalecoExtracionVehicular() {
		return chalecoExtracionVehicular;
	}
	/**
	 * @param chalecoExtracionVehicular the chalecoExtracionVehicular to set
	 */
	public void setChalecoExtracionVehicular(Boolean chalecoExtracionVehicular) {
		this.chalecoExtracionVehicular = chalecoExtracionVehicular;
	}
	/**
	 * @return the mantaTermica
	 */
	public Boolean getMantaTermica() {
		return mantaTermica;
	}
	/**
	 * @param mantaTermica the mantaTermica to set
	 */
	public void setMantaTermica(Boolean mantaTermica) {
		this.mantaTermica = mantaTermica;
	}
	/**
	 * @return the tablaEspinalLarga
	 */
	public Boolean getTablaEspinalLarga() {
		return tablaEspinalLarga;
	}
	/**
	 * @param tablaEspinalLarga the tablaEspinalLarga to set
	 */
	public void setTablaEspinalLarga(Boolean tablaEspinalLarga) {
		this.tablaEspinalLarga = tablaEspinalLarga;
	}
	/**
	 * @return the inmovilizacionFerulas
	 */
	public Boolean getInmovilizacionFerulas() {
		return inmovilizacionFerulas;
	}
	/**
	 * @param inmovilizacionFerulas the inmovilizacionFerulas to set
	 */
	public void setInmovilizacionFerulas(Boolean inmovilizacionFerulas) {
		this.inmovilizacionFerulas = inmovilizacionFerulas;
	}
	/**
	 * @return the tablaEspinalCorta
	 */
	public Boolean getTablaEspinalCorta() {
		return tablaEspinalCorta;
	}
	/**
	 * @param tablaEspinalCorta the tablaEspinalCorta to set
	 */
	public void setTablaEspinalCorta(Boolean tablaEspinalCorta) {
		this.tablaEspinalCorta = tablaEspinalCorta;
	}
	/**
	 * @return the otrosProcedimientos
	 */
	public String getOtrosProcedimientos() {
		return otrosProcedimientos;
	}
	/**
	 * @param otrosProcedimientos the otrosProcedimientos to set
	 */
	public void setOtrosProcedimientos(String otrosProcedimientos) {
		this.otrosProcedimientos = otrosProcedimientos;
	}

	
}
