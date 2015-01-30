package com.artica.telesalud.client.ui;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Hyperlink;

public abstract class WTabItem extends TabItem {
	
	private WTabPanel parent;
	private WTabPanel body;
	
	public void setParent(WTabPanel parent) {
		this.parent = parent;
	}

	public WTabItem() {
		super();
		
	}

	public WTabItem(String text) {
		super(text);
	}


	public LayoutContainer getBotones(String labelBack, String labelNext){
		
		Hyperlink backButton = new Hyperlink("", "");
		backButton.setTitle(labelBack);
		backButton.setText("");
		backButton.setStyleName("izquierda");
		
		Hyperlink nextButton = new Hyperlink("", "");
		nextButton.setTitle(labelNext);
		nextButton.setText("");
		nextButton.setStyleName("derecha");
				
		LayoutContainer lytButton=new LayoutContainer();
		TableLayout tlButton = new TableLayout(2);
		
		lytButton.setLayout(tlButton);
		lytButton.setWidth("100%");
		lytButton.setAutoWidth(true);
		
		tlButton.setWidth("100%");
		
		TableData tdBackButton = new TableData();
		tdBackButton.setPadding(3);
		tdBackButton.setHorizontalAlign(HorizontalAlignment.LEFT);
		lytButton.add(backButton, tdBackButton);
		
		TableData tdNextButton = new TableData();
		tdNextButton.setHorizontalAlign(HorizontalAlignment.RIGHT);
		lytButton.add(nextButton, tdNextButton);
		
		nextButton.addHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				next();
				
			}
		}, ClickEvent.getType());
		
		backButton.addHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				back();
				
			}
		}, ClickEvent.getType());
		
		if(labelBack==null)
			backButton.setVisible(false);
		
		if(labelNext==null)
			nextButton.setVisible(false);
		
		return lytButton;
	}

	public void next() {
		//vsaveContent();
		parent.onNext(this);
	}
	
	public void back() {
		//saveContent();
		parent.onBack(this);
	}
	
	public abstract boolean saveContent();
	
	public abstract boolean valida();
	
	public void setBody(WTabPanel panel){
		this.body = panel;
	}

	public WTabPanel getBody() {
		return body;
	}

}
