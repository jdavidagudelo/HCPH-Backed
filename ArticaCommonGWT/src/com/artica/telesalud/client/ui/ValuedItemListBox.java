/**
 *   
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   MvpExample is an example guide to adopt the MVP Pattern
 *   Copyright (C) 2011  David López - coolcoder.ex@gmail.com    
*/

package com.artica.telesalud.client.ui;

import java.io.Serializable;
import java.util.List;

import com.artica.telesalud.client.shared.ValuedItem;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ListBox;


public class ValuedItemListBox<K extends Serializable> extends ListBox implements HasSelectedValuedItem<K>{
	
	private List<ValuedItem<K, String>> options;
	private boolean valueChangeHandlerInitialized = false;
	
	public ValuedItemListBox(){
		
	}

	@Override
	public K getValue() {
		return getSelectedValue();
	}

	@Override
	public void setValue(K value) {
		setValue(value, false);
	}

	@Override
	public void setValue(K value, boolean fireEvents) {
		K oldValue = value;
		setSelectedValue(value);
		
		if( fireEvents )
			ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<K> handler) {
		// Initialization code
		if (valueChangeHandlerInitialized)
			return addHandler(handler, ValueChangeEvent.getType());
		
		valueChangeHandlerInitialized = true;
		super.addChangeHandler(new ChangeHandler(){
			public void onChange(ChangeEvent event)
			{
				ValueChangeEvent.fire(ValuedItemListBox.this, getValue());
			}
		});
		
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public void setOptions(List<ValuedItem<K, String>> options) {
		this.clear();
		
		for(ValuedItem<K, String> item : options)
			this.addItem(item.getLabel(), item.getCode().toString());
		
		this.options = options;
	}

	@Override
	public void setSelectedValue(K selected) {
		if( selected == null ){
			for(int i = 0; i < options.size(); i++)
				if( options.get(i).getCode() == null ){
					setSelectedIndex(i);
					break;
				}
			return ;
		}
		
		for(int i = 0; i < options.size(); i++)
			if( selected.equals(options.get(i).getCode()) ){
				setSelectedIndex(i);
				return;
			}
		throw new IllegalArgumentException("No index found for value " + selected.toString());
	}

	@Override
	public K getSelectedValue() {
		if( getSelectedIndex() == -1 )
			return null;

		return options.get(getSelectedIndex()).getCode();
	}

}
