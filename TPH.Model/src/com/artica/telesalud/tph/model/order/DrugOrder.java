package com.artica.telesalud.tph.model.order;

import java.io.Serializable;

public class DrugOrder implements Serializable{

	private Integer order;
	private Double amount;
	private Integer administrationVia;
	private Integer frequency;
	private Integer units;
	private Double duration;
	
	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getAdministrationVia() {
		return administrationVia;
	}
	public void setAdministrationVia(Integer administrationVia) {
		this.administrationVia = administrationVia;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	
	
}
