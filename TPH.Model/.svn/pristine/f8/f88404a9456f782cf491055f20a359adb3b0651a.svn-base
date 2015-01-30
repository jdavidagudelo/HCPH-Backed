package com.artica.telesalud.tph.model.concept;

import java.io.Serializable;

public class DrugIngredientId implements Serializable {
	
	private static final long serialVersionUID = -7690051754804153095L;
	
	private Concept concept;
	private Concept ingredient;
	
	public Concept getConcept() {
		return concept;
	}
	
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	public Concept getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(Concept ingredient) {
		this.ingredient = ingredient;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concept == null) ? 0 : concept.hashCode());
		result = prime * result
				+ ((ingredient == null) ? 0 : ingredient.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DrugIngredientId other = (DrugIngredientId) obj;
		if (concept == null) {
			if (other.concept != null)
				return false;
		} else if (!concept.equals(other.concept))
			return false;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		return true;
	}
	
	

}
