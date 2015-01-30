package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;

public class Procedimientos implements Serializable{

	private Boolean aspiracionSecreciones = false;
	private Boolean canulaOrofaringea = false;
	private Boolean despejeManual = false;
	private Boolean collarCervical = false;
	private Boolean canulaNasofaringea = false;
	private Boolean evaluacionB = false;
	private Boolean reanimacionRespiratoria = false;
	private Boolean canulaNasal = false;
	private Boolean mascaraNoReinhalacion = false;
	private Boolean mascaraSimple = false;
	private Boolean bvm = false;
	private Boolean evaluacionC = false;
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
	private String otrosProcedimientos;
	
	public Boolean getAspiracionSecreciones() {
		return aspiracionSecreciones;
	}
	public void setAspiracionSecreciones(Boolean aspiracionSecreciones) {
		this.aspiracionSecreciones = aspiracionSecreciones;
	}
	public Boolean getCanulaOrofaringea() {
		return canulaOrofaringea;
	}
	public void setCanulaOrofaringea(Boolean canulaOrofaringea) {
		this.canulaOrofaringea = canulaOrofaringea;
	}
	public Boolean getDespejeManual() {
		return despejeManual;
	}
	public void setDespejeManual(Boolean despejeManual) {
		this.despejeManual = despejeManual;
	}
	public Boolean getCollarCervical() {
		return collarCervical;
	}
	public void setCollarCervical(Boolean collarCervical) {
		this.collarCervical = collarCervical;
	}
	public Boolean getCanulaNasofaringea() {
		return canulaNasofaringea;
	}
	public void setCanulaNasofaringea(Boolean canulaNasofaringea) {
		this.canulaNasofaringea = canulaNasofaringea;
	}
	public Boolean getEvaluacionB() {
		return evaluacionB;
	}
	public void setEvaluacionB(Boolean evaluacionB) {
		this.evaluacionB = evaluacionB;
	}
	public Boolean getReanimacionRespiratoria() {
		return reanimacionRespiratoria;
	}
	public void setReanimacionRespiratoria(Boolean reanimacionRespiratoria) {
		this.reanimacionRespiratoria = reanimacionRespiratoria;
	}
	public Boolean getCanulaNasal() {
		return canulaNasal;
	}
	public void setCanulaNasal(Boolean canulaNasal) {
		this.canulaNasal = canulaNasal;
	}
	public Boolean getMascaraSimple() {
		return mascaraSimple;
	}
	public void setMascaraSimple(Boolean mascaraSimple) {
		this.mascaraSimple = mascaraSimple;
	}
	public Boolean getBvm() {
		return bvm;
	}
	public void setBvm(Boolean bvm) {
		this.bvm = bvm;
	}
	public Boolean getEvaluacionC() {
		return evaluacionC;
	}
	public void setEvaluacionC(Boolean evaluacionC) {
		this.evaluacionC = evaluacionC;
	}
	public Boolean getRccp() {
		return rccp;
	}
	public void setRccp(Boolean rccp) {
		this.rccp = rccp;
	}
	public Boolean getHemostasia() {
		return hemostasia;
	}
	public void setHemostasia(Boolean hemostasia) {
		this.hemostasia = hemostasia;
	}
	public Boolean getDea() {
		return dea;
	}
	public void setDea(Boolean dea) {
		this.dea = dea;
	}
	public Boolean getMonitoreoSignosVitales() {
		return monitoreoSignosVitales;
	}
	public void setMonitoreoSignosVitales(Boolean monitoreoSignosVitales) {
		this.monitoreoSignosVitales = monitoreoSignosVitales;
	}
	public Boolean getDispositivoSupragliotico() {
		return dispositivoSupragliotico;
	}
	public void setDispositivoSupragliotico(Boolean dispositivoSupragliotico) {
		this.dispositivoSupragliotico = dispositivoSupragliotico;
	}
	public Boolean getIntubacionOrotraqueal() {
		return intubacionOrotraqueal;
	}
	public void setIntubacionOrotraqueal(Boolean intubacionOrotraqueal) {
		this.intubacionOrotraqueal = intubacionOrotraqueal;
	}
	public Boolean getDescompresionTorax() {
		return descompresionTorax;
	}
	public void setDescompresionTorax(Boolean descompresionTorax) {
		this.descompresionTorax = descompresionTorax;
	}
	public Boolean getVentilacionMecanica() {
		return ventilacionMecanica;
	}
	public void setVentilacionMecanica(Boolean ventilacionMecanica) {
		this.ventilacionMecanica = ventilacionMecanica;
	}
	public Boolean getSsn09() {
		return ssn09;
	}
	public void setSsn09(Boolean ssn09) {
		this.ssn09 = ssn09;
	}
	public Boolean getAccesoVenosoPeriferico() {
		return accesoVenosoPeriferico;
	}
	public void setAccesoVenosoPeriferico(Boolean accesoVenosoPeriferico) {
		this.accesoVenosoPeriferico = accesoVenosoPeriferico;
	}
	public Boolean getDestrosa() {
		return destrosa;
	}
	public void setDestrosa(Boolean destrosa) {
		this.destrosa = destrosa;
	}
	public Boolean getPunsionOsea() {
		return punsionOsea;
	}
	public void setPunsionOsea(Boolean punsionOsea) {
		this.punsionOsea = punsionOsea;
	}
	public Boolean getColoides() {
		return coloides;
	}
	public void setColoides(Boolean coloides) {
		this.coloides = coloides;
	}
	public Boolean getHartman() {
		return hartman;
	}
	public void setHartman(Boolean hartman) {
		this.hartman = hartman;
	}
	public Boolean getExposicion() {
		return exposicion;
	}
	public void setExposicion(Boolean exposicion) {
		this.exposicion = exposicion;
	}
	public Boolean getMovimientoBloque() {
		return movimientoBloque;
	}
	public void setMovimientoBloque(Boolean movimientoBloque) {
		this.movimientoBloque = movimientoBloque;
	}
	public Boolean getLavadoCuracion() {
		return lavadoCuracion;
	}
	public void setLavadoCuracion(Boolean lavadoCuracion) {
		this.lavadoCuracion = lavadoCuracion;
	}
	public Boolean getChalecoExtracionVehicular() {
		return chalecoExtracionVehicular;
	}
	public void setChalecoExtracionVehicular(Boolean chalecoExtracionVehicular) {
		this.chalecoExtracionVehicular = chalecoExtracionVehicular;
	}
	public Boolean getMantaTermica() {
		return mantaTermica;
	}
	public void setMantaTermica(Boolean mantaTermica) {
		this.mantaTermica = mantaTermica;
	}
	public Boolean getTablaEspinalLarga() {
		return tablaEspinalLarga;
	}
	public void setTablaEspinalLarga(Boolean tablaEspinalLarga) {
		this.tablaEspinalLarga = tablaEspinalLarga;
	}
	public Boolean getInmovilizacionFerulas() {
		return inmovilizacionFerulas;
	}
	public void setInmovilizacionFerulas(Boolean inmovilizacionFerulas) {
		this.inmovilizacionFerulas = inmovilizacionFerulas;
	}
	public Boolean getTablaEspinalCorta() {
		return tablaEspinalCorta;
	}
	public void setTablaEspinalCorta(Boolean tablaEspinalCorta) {
		this.tablaEspinalCorta = tablaEspinalCorta;
	}
	public String getOtrosProcedimientos() {
		return otrosProcedimientos;
	}
	public void setOtrosProcedimientos(String otrosProcedimientos) {
		this.otrosProcedimientos = otrosProcedimientos;
	}
	public Boolean getMascaraNoReinhalacion() {
		return mascaraNoReinhalacion;
	}
	public void setMascaraNoReinhalacion(Boolean mascaraNoReinhalacion) {
		this.mascaraNoReinhalacion = mascaraNoReinhalacion;
	}
	public Boolean getCricotiroidotomia() {
		return cricotiroidotomia;
	}
	public void setCricotiroidotomia(Boolean cricotiroidotomia) {
		this.cricotiroidotomia = cricotiroidotomia;
	}
	public Boolean getRehidratacionOral() {
		return rehidratacionOral;
	}
	public void setRehidratacionOral(Boolean rehidratacionOral) {
		this.rehidratacionOral = rehidratacionOral;
	}
}
