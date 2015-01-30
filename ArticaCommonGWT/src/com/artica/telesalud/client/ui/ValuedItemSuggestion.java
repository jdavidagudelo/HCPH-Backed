package com.artica.telesalud.client.ui;

import java.io.Serializable;

import com.artica.telesalud.client.shared.ValuedItem;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

@SuppressWarnings("serial")
public class ValuedItemSuggestion extends ValuedItem<Integer, String> implements Suggestion, Serializable{

	public ValuedItemSuggestion() {
		super();
	}

	public ValuedItemSuggestion(Integer key, String value) {
		super(key, value);
	}

	@Override
	public String getDisplayString() {
		return getLabel();
	}

	@Override
	public String getReplacementString() {
		return getLabel();
	}

}
