package com.artica.telesalud.tph.model.form;

import java.io.Serializable;

import com.artica.telesalud.tph.model.concept.Concept;

public class FieldAnswerId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Concept answer;
	private Field field;
	
	public Concept getAnswer() {
		return answer;
	}
	public void setAnswer(Concept answer) {
		this.answer = answer;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FieldAnswerId)) {
			return false;
		}
		FieldAnswerId other = (FieldAnswerId) obj;
		if (answer == null) {
			if (other.answer != null) {
				return false;
			}
		} else if (!answer.equals(other.answer)) {
			return false;
		}
		if (field == null) {
			if (other.field != null) {
				return false;
			}
		} else if (!field.equals(other.field)) {
			return false;
		}
		return true;
	}

}
