package com.artica.telesalud.tph.model.concept;

public class Interaction {

	private Integer interactionId;
	private InteractionType interactionsType;
	private Concept drugPpal;
	private Concept drugSec;
	
	public Integer getInteractionId() {
		return interactionId;
	}
	public void setInteractionId(Integer interactionId) {
		this.interactionId = interactionId;
	}
	public InteractionType getInteractionsType() {
		return interactionsType;
	}
	public void setInteractionsType(InteractionType interactionsType) {
		this.interactionsType = interactionsType;
	}
	public Concept getDrugPpal() {
		return drugPpal;
	}
	public void setDrugPpal(Concept drugPpal) {
		this.drugPpal = drugPpal;
	}
	public Concept getDrugSec() {
		return drugSec;
	}
	public void setDrugSec(Concept drugSec) {
		this.drugSec = drugSec;
	}
}
