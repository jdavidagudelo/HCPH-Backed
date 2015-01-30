package com.artica.telesalud.client.ui;

import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ArticaMsgBox extends Composite {

	private static ArticaMsgBoxUiBinder uiBinder = GWT
			.create(ArticaMsgBoxUiBinder.class);

	interface ArticaMsgBoxUiBinder extends UiBinder<Widget, ArticaMsgBox> {
	}

	public ArticaMsgBox() {
		initWidget(uiBinder.createAndBindUi(this));
		hLocation = 1;
		vLocation = 1;
		index = 0;
		showing = false;
	}
	

	public static final int MARGIN = 10,
							OFFSET = 5;
	
	public static final int TIME_TO_APPEAR = 700,
	   TIME_TO_SHOW = 5000,
	   TIME_TO_FADE = 700;

	private boolean fading = false;
	private boolean showing = false;
	private Integer timeToShow;
	
	public static final int HORIZONTAL_LOCATION_LEFT = 0,
							HORIZONTAL_LOCATION_CENTER = 1,
							HORIZONTAL_LOCATION_RIGHT = 2,
							VERTICAL_LOCATION_TOP = 0,
							VERTICAL_LOCATION_CENTER = 1,
							VERTICAL_LOCATION_BOTTOM = 2;
	
	private int hLocation, vLocation, index;
	
	@UiField Label closeLink;
	@UiField InlineLabel msgLabel;
		
	public void showMessage(String msg, MessageBoxType type){
		timeToShow = TIME_TO_SHOW;
		if( type.equals(MessageBoxType.Error))
			timeToShow = null;
		this.showMessage(msg, type, TIME_TO_APPEAR, timeToShow, TIME_TO_FADE);
	}
	
	public void showMessage(String msg, MessageBoxType type, boolean closeable){
		timeToShow = TIME_TO_SHOW;
		if( type.equals(MessageBoxType.Error))
			timeToShow = null;
		this.showMessage(msg, type, TIME_TO_APPEAR, timeToShow, TIME_TO_FADE);
		closeLink.setVisible(closeable);
	}
	
	public void showMessage(String msg, MessageBoxType type, Integer timeToAppear, Integer timeToShow, final Integer timeToFade){
		//Setup
		setTitle(type.name());
		setupCloseLink();
		msgLabel.setText(msg);
		//RootPanel.get().add(this);
		addToRootPane();
		setStyleFor(type);
		closeLink.setVisible(true);
		fading = false;
		showing = true;
		relocate();
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
				//hide();
				removeFromParent();
				super.onComplete();
			}
		};
		//Add on click fade out
		closeLink.addDomHandler(new ClickHandler() {
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

	public void showWaiting(String msg){
		setTitle("Loading");
		msgLabel.setText(msg);
//		RootPanel.get().add(this);
		addToRootPane();
		setStyleName("loading");	
		closeLink.setVisible(false);
		timeToShow=TIME_TO_SHOW;
		relocate();
		
		//Start fading animation
		FadeInAnimation appearAnimation = new FadeInAnimation(this);		
		appearAnimation.run( TIME_TO_APPEAR );
	}
	
	public void hideWaiting(){
		final FadeOutAnimation fade = new FadeOutAnimation(this){
			@Override
			protected void onComplete() {
				//hide();
				if(timeToShow==null)
					return;
				
				removeFromParent();
				super.onComplete();
			}
		};
		
		if(timeToShow!=null)
			fade.run(TIME_TO_FADE);
	}
	
	public void hide(){
		System.out.println("Tratando de ocultar");
		if( !showing )
			return;
		
		final FadeOutAnimation fade = new FadeOutAnimation(this){
			@Override
			protected void onComplete() {
				removeFromParent();
				super.onComplete();
			}
		};
		
		fade.run(TIME_TO_FADE);
		System.out.println("Corriendo Fade");
	}
	
	private void setStyleFor(MessageBoxType type){
		setStyleName(type.name().toLowerCase());
	}
	
	private void setupCloseLink(){
		closeLink.setWidth("");
		closeLink.setStyleName("closeButton");
		closeLink.getElement().getStyle().setCursor(Cursor.POINTER);
	}
	private void addToRootPane(){
		RootPanel.get().add(this);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#removeFromParent()
	 */
	@Override
	public void removeFromParent() {
		showing = false;
		super.removeFromParent();
	}

	public void setLocation(int horizontal, int vertical){
		hLocation = horizontal;
		vLocation = vertical;
	}
	
	private void relocate(){
		int left = hLocation * ((Window.getClientWidth() - getOffsetWidth()) / 2) + getMargin(hLocation);
		int top = vLocation * ((Window.getClientHeight() - getOffsetHeight()) / 2) - index * (getOffsetHeight() + OFFSET) + getMargin(vLocation); 
		Element elem = getElement();
		elem.getStyle().setPropertyPx("left", left);
		elem.getStyle().setPropertyPx("top", top);
	}
	
	private int getMargin(int location){
		int margin = (1 - location) * MARGIN;
		
		return margin;
	}
	
//	private void center(){
//		int left = (Window.getClientWidth() - getOffsetWidth()) >> 1;
//	    int top = (Window.getClientHeight() - getOffsetHeight()) >> 1;
//		Element elem = getElement();
//		elem.getStyle().setPropertyPx("left", left);
//		elem.getStyle().setPropertyPx("top", top);	
//	}
}
