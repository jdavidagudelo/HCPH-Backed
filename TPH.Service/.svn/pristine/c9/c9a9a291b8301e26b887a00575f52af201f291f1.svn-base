package com.artica.telesalud.tph.server.util;

import java.util.List;

import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.lightweightmodel.Procedimientos;
import com.artica.telesalud.tph.model.observation.ObservationData;

public class CargarObservaciones {

	public static Procedimientos cargarProcedimientos(List<ObservationData> datos){
	
		ConceptsCode concepts = new ConceptsCode();
		Procedimientos procedimientos = new Procedimientos();
		
		procedimientos.setAspiracionSecreciones((Boolean)ObservationData.obtenerValorConcepto(datos, concepts.cAspiracionSecreciones()));
		procedimientos.setCanulaOrofaringea((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cCanulaOrofaringea()));
		procedimientos.setDespejeManual((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cDespejeManual()));
		procedimientos.setCollarCervical((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cCollarCervical()));
		procedimientos.setCanulaNasofaringea((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cCanulaNasofaringea()));
		procedimientos.setEvaluacionB((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cEvaluacionB()));
		procedimientos.setReanimacionRespiratoria((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cReanimacionRespiratoria()));
		procedimientos.setCanulaNasal((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cCanulaNasal()));
		procedimientos.setMascaraNoReinhalacion((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cMascaraNoReinhalacion()));
		procedimientos.setMascaraSimple((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cMascaraSimple()));
		procedimientos.setBvm((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cBvm()));
		procedimientos.setEvaluacionC((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cEvaluacionC()));
		procedimientos.setRccp((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cRccp()));
		procedimientos.setHemostasia((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cHemostasia()));
		procedimientos.setDea((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cDea()));
		procedimientos.setMonitoreoSignosVitales((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cMonitoreoSignosVitales()));
		procedimientos.setDispositivoSupragliotico((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cDispositivoSupragliotico()));
		procedimientos.setCricotiroidotomia((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cCricotiroidotomia()));
		procedimientos.setIntubacionOrotraqueal((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cIntubacionOrotraqueal()));
		procedimientos.setDescompresionTorax((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cDescompresionTorax()));
		procedimientos.setVentilacionMecanica((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cVentilacionMecanica()));
		procedimientos.setRehidratacionOral((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cRehidratacionOral()));
		procedimientos.setSsn09((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cSsn09()));
		procedimientos.setAccesoVenosoPeriferico((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cAccesoVenosoPeriferico()));
		procedimientos.setDestrosa((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cDestrosa()));
		procedimientos.setPunsionOsea((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cPunsionOsea()));
		procedimientos.setColoides((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cColoides()));
		procedimientos.setHartman((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cHartman()));
		procedimientos.setExposicion((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cExposicion()));
		procedimientos.setMovimientoBloque((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cMovimientoBloque()));
		procedimientos.setLavadoCuracion((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cLavadoCuracion()));
		procedimientos.setChalecoExtracionVehicular((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cChalecoExtracionVehicular()));
		procedimientos.setMantaTermica((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cMantaTermica()));
		procedimientos.setTablaEspinalLarga((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cTablaEspinalLarga()));
		procedimientos.setInmovilizacionFerulas((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cInmovilizacionFerulas()));
		procedimientos.setTablaEspinalCorta((Boolean) ObservationData.obtenerValorConcepto(datos, concepts.cTablaEspinalCorta()));
		procedimientos.setOtrosProcedimientos((String) ObservationData.obtenerValorConcepto(datos, concepts.cOtrosProcedimientos()));
		
		return procedimientos;
		
	}
}
