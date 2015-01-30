

package com.artica.telesalud.tph.model.concept;

import java.io.Serializable;

public class ConceptNameTagMapId implements Serializable {

	private static final long serialVersionUID = -8456329718242197681L;
	
	private ConceptName conceptName;
	private ConceptNameTag conceptNameTag;
	
	public ConceptName getConceptName() {
		return conceptName;
	}
	public void setConceptName(ConceptName conceptName) {
		this.conceptName = conceptName;
	}
	public ConceptNameTag getConceptNameTag() {
		return conceptNameTag;
	}
	public void setConceptNameTag(ConceptNameTag conceptNameTag) {
		this.conceptNameTag = conceptNameTag;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((conceptName == null) ? 0 : conceptName.hashCode());
		result = prime * result
				+ ((conceptNameTag == null) ? 0 : conceptNameTag.hashCode());
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
		ConceptNameTagMapId other = (ConceptNameTagMapId) obj;
		if (conceptName == null) {
			if (other.conceptName != null)
				return false;
		} else if (!conceptName.equals(other.conceptName))
			return false;
		if (conceptNameTag == null) {
			if (other.conceptNameTag != null)
				return false;
		} else if (!conceptNameTag.equals(other.conceptNameTag))
			return false;
		return true;
	}
}
