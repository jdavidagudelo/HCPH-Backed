package com.artica.telesalud.client.ui;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.google.gwt.user.client.ui.Hyperlink;

public class Navegacion {
	
	private LayoutContainer container;
	private LayoutContainer lytToolbar;
	//private HashMap<String, Hyperlink> buttons;
	
	private Hyperlink lnkSalida, lnkGestionarUsuarios;
	
	public Navegacion(LayoutContainer contenedor, LayoutContainer lytBotones){
		this.lytToolbar = lytBotones;
		this.container=contenedor;
		
		//buttons =  new HashMap<String, Hyperlink>();
		createToolbar();
	}
	
	public void sendTo(Component newContainer){
		
		this.container.removeAll();
		this.container.add(newContainer, new RowData(1.0, Style.DEFAULT, new Margins(0,0,0,0)));
		this.container.repaint();
		
		
		
		
	}

	private void createToolbar(){

		/*
		TableData tdGuardar = new TableData();
		tdGuardar.setStyleName("guardar");
		
		Hyperlink lnkGuardar = new Hyperlink("", "");
		lnkGuardar.setTitle("Guardar");
		lnkGuardar.setText("");
		
		//buttons.put("guardar", lnkGuardar);
		lytToolbar.add(lnkGuardar, tdGuardar);
		*/
		
		/*
		TableData tdTerminar = new TableData();
		tdTerminar.setStyleName("terminar");
		
		Hyperlink lnkTerminar = new Hyperlink("", "");
		lnkTerminar.setTitle("Terminar Consulta");
		lnkTerminar.setText("");
		
		buttons.put("terminar", lnkTerminar);
		lytToolbar.add(lnkTerminar, tdTerminar);
		
		TableData tdAyuda = new TableData();
		tdAyuda.setStyleName("ayuda");
		
		Hyperlink lnkAyuda = new Hyperlink("", "");
		lnkAyuda.setTitle("Ayuda");
		lnkAyuda.setText("");
		
		buttons.put("ayuda", lnkAyuda);
		lytToolbar.add(lnkAyuda, tdAyuda);
		*/
		
		
		//addLinkManejarUsuarios();
		//addLinkSalida();
		
		lytToolbar.repaint();
	}
	
	public void addLinkSalida(){
		TableData tdSalida = new TableData();
		tdSalida.setStyleName("salida");
		
		lnkSalida = new Hyperlink("", "");
		lnkSalida.setTitle("Salida");
		lnkSalida.setText("");
		//buttons.put("salida", lnkSalida);
		lytToolbar.add(lnkSalida, tdSalida);
	}
	
	public void addLinkManejarUsuarios(){
		TableData tdNuevo = new TableData();
		tdNuevo.setStyleName("nuevo");
		
		lnkGestionarUsuarios = new Hyperlink("", "");
		lnkGestionarUsuarios.setTitle("Manejar Usuarios");
		lnkGestionarUsuarios.setText("");
		lnkGestionarUsuarios.setStyleName("usuarios");
		lnkGestionarUsuarios.setVisible(true);
		
		//buttons.put("nuevo", lnkNuevo);
		lytToolbar.add(lnkGestionarUsuarios, tdNuevo);
	}
	
	public Hyperlink getLinkManejarUsuarios(){
		return lnkGestionarUsuarios;
	}
	
	public Hyperlink getLinkSalida(){
		return lnkSalida;
	}
}
