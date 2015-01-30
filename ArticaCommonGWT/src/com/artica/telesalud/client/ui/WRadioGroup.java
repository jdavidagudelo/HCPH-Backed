package com.artica.telesalud.client.ui;

import com.extjs.gxt.ui.client.widget.form.RadioGroup;

public class WRadioGroup extends RadioGroup{
	
	public WRadioGroup(){
		super();
	}
	
	public WRadioGroup(String name){
		super(name);
	}

	public Integer getWValue(){
		
		return ((WRadio)this.getValue()).getConceptValue();
	}

	
	
	
}
