package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.ArrayList;

import com.artica.telesalud.client.shared.ValuedItem;

@SuppressWarnings("serial")
public class HallazgoData implements Serializable{
	
	private Integer orden;
	private Double x, y;
	private Integer lesionadoId;
	private Integer hallazgoId;
	private ArrayList<Lesiones> lesiones;
	private ArrayList<ValuedItem<Integer, String>> procedimientos;
	
	public static enum Lesiones{
		abrasion,
		amputacion,
		aplastamiento,
		avulsion,
		contusion,
		dolor,
		esguince,
		fracturaAbierta,
		quemadura,
		herida,
		fracturaCerrada,
		heridaArmaDeFuego,
		heridaArmaBlanca,
		hemorragia,
		laceracion,
		mordida,
		picadura,
		puncion,
		traumaCerrado,
		traumaPenetrante,
		hematoma	
	}
	
	
	public HallazgoData(){
		lesiones = new ArrayList<HallazgoData.Lesiones>();
		procedimientos = new ArrayList<ValuedItem<Integer,String>>();
	}


	public ArrayList<Lesiones> getLesiones() {
		return lesiones;
	}


	public void setLesiones(ArrayList<Lesiones> lesiones) {
		this.lesiones = lesiones;
	}


	public ArrayList<ValuedItem<Integer, String>> getProcedimientos() {
		return procedimientos;
	}


	public void setProcedimientos(
			ArrayList<ValuedItem<Integer, String>> procedimientos) {
		this.procedimientos = procedimientos;
	}
	

	public Integer getOrden() {
		return orden;
	}


	public void setOrden(Integer orden) {
		this.orden = orden;
	}


	public Integer getLesionadoId() {
		return lesionadoId;
	}

	public void setLesionadoId(Integer lesionadoId) {
		this.lesionadoId = lesionadoId;
	}


	public Double getX() {
		return x;
	}


	public void setX(Double x) {
		this.x = x;
	}


	public Double getY() {
		return y;
	}


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
	 * @param hallazgoId the hallazgoId to set
	 */
	public void setHallazgoId(Integer hallazgoId) {
		this.hallazgoId = hallazgoId;
	}
	
	
	
}
