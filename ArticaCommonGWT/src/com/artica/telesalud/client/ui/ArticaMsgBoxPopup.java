package com.artica.telesalud.client.ui;


import com.google.gwt.core.client.Duration;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class ArticaMsgBoxPopup extends PopupPanel {
	private static ArticaMsgBoxPopup instance = new ArticaMsgBoxPopup();
	
	public static final int TIME_TO_APPEAR = 700,
							   TIME_TO_SHOW = 5000,
							   TIME_TO_FADE = 700;
	
	private boolean fading = false;
	
	public static ArticaMsgBoxPopup getInstance(){
		return instance;
	}
	
	private ArticaMsgBoxPopup(){
		
	}
	
	public void showMessage(String msg, MessageBoxType type){
		Integer timeToShow = TIME_TO_SHOW;
		if( type == MessageBoxType.Error)
			timeToShow = null;
		this.showMessage(msg, type, TIME_TO_APPEAR, timeToShow, TIME_TO_FADE);
	}
	
	public void showMessage(String msg, MessageBoxType type, Integer timeToAppear, Integer timeToShow, final Integer timeToFade){
		//Setup
		setTitle(type.name());
		FlowPanel content = new FlowPanel();
		Widget closeBtn = getCloseButton(); 
		content.add(closeBtn);
		content.add(new Label(msg));
		setWidget(content);
		
		setStyleFor(type);	
		center();
		fading = false;
		
		//Start fading animation
		FadeInAnimation appearAnimation = new FadeInAnimation(this);		
		double startTime = Duration.currentTimeMillis();
		appearAnimation.run( timeToAppear );
		startTime += timeToAppear;
		
		//FadeOut
		final FadeOutAnimation fade = new FadeOutAnimation(this){
			@Override
			protected void onStart() {
				fading = true;
				super.onStart();
			}

			@Override
			protected void onComplete() {
				hide();
				super.onComplete();
			}
		};
		//Add on click fade out
		closeBtn.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//hide();
				if( !fading ){
					fade.run(timeToFade);
				}
			}
		}, ClickEvent.getType() );
		
		//If there's a programmed fade out
		if( timeToShow != null ){			
			startTime += timeToShow;
			fade.run( timeToFade , startTime);
		}
	}

	public void setStyleFor(MessageBoxType type){
		setStyleName(type.name().toLowerCase());
	}
	
	public Widget getCloseButton(){
		Label lbl = new Label("X");
		lbl.setWidth("");
		lbl.setStyleName("closeButton");
		lbl.getElement().getStyle().setCursor(Cursor.POINTER);
		return lbl;
	}
}
