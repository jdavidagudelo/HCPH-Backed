package com.artica.telesalud.client.ui;

import com.extjs.gxt.ui.client.widget.form.Radio;

public class WRadio extends Radio {
	
	private Integer conceptValue;
	
	public WRadio(){
		super();
	}
	
	public WRadio(String label){
		super();
		
		this.setBoxLabel(label);
		
	}

	public Integer getConceptValue() {
		return conceptValue;
	}

	public void setConceptValue(Integer conceptValue) {
		this.conceptValue = conceptValue;
	}
	
	
}
