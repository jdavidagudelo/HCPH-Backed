package com.artica.telesalud.client.ui;

import java.util.Date;

import com.artica.telesalud.client.shared.Data;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;


public class TelesaludFields {
	
	public static <T extends ModelData> ComboBox<T> crearComboBoxData(ListStore<T> store, boolean requerido, int ancho){
		
		ComboBox<T> combo = new ComboBox<T>();
		combo.setEmptyText("");  
		combo.setDisplayField(Data.LABEL_FIELD);
		combo.setValueField(Data.VALUE_FIELD);
		combo.setWidth(ancho);  
		combo.setStore(store);
		combo.setTypeAhead(false);
		combo.setAllowBlank(!requerido);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setEditable(false);
		
		return combo;
	}
	
	public static TextField<String> crearCuadroTexto(boolean requerido, int maxLength, int ancho){
		
		TextField<String> cuadroTexto = new TextField<String>();
		cuadroTexto.setSelectOnFocus(true);
		cuadroTexto.setMaxLength(maxLength);
		cuadroTexto.setWidth(ancho);
		cuadroTexto.setAllowBlank(!requerido);
		
		return cuadroTexto;
	}
	
	public static NumberField crearCuadroNumero(boolean requerido, int maxLength, int ancho){
		
		NumberField txtNF = new NumberField();
		txtNF.setSelectOnFocus(true);
		txtNF.setMaxLength(maxLength);
		txtNF.setSize(String.valueOf(ancho), "");
		
		return txtNF;
	}
	
	
	public static DateField crearCuadroFecha(boolean requerido){
		DateField fecha = new DateField();
		
		fecha.setAllowBlank(!requerido);
		fecha.setMaxValue(new Date());
		
		return fecha;
	}
	
	public static TextArea crearTextArea(boolean requerido, boolean habilitado, int maxLength, int ancho, int alto){
		
		TextArea field = new TextArea();
		field.setSelectOnFocus(true);
		field.setHeight(alto + "px");
		field.setWidth(ancho + "px");
		
		field.setFieldLabel("");
		field.setValue("");
		field.setEnabled(habilitado);
		field.setAllowBlank(!requerido);
		
		if(maxLength!=0)
			field.setMaxLength(maxLength);
		
		return field;
	}

}
