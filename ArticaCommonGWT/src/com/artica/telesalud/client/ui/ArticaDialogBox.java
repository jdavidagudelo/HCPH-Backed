package com.artica.telesalud.client.ui;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;

public class ArticaDialogBox extends DialogBox {

	@Override
	public void show() {
		RootPanel.get("fondoDialogBox").setHeight("2000px");
		super.show();
	}

	@Override
	public void hide() {
		RootPanel.get("fondoDialogBox").setHeight("0%");
		super.hide();
	}

}
