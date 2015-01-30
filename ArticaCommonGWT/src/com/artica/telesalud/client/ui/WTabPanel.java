package com.artica.telesalud.client.ui;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;




public class WTabPanel extends TabPanel {


	WTabItem parent;
	
	public void next() {
		
		if(parent!=null)
			parent.next();

	}
	
	public void back() {
		
		if(parent!=null)
			parent.back();

	}
	
	public WTabPanel() {
		super();
		
		this.addListener(Events.BeforeSelect, new Listener<TabPanelEvent>() {

			@Override
			public void handleEvent(TabPanelEvent be) {
				
				WTabItem item=(WTabItem)getSelectedItem();
				
				if(!guardarItem(item))
					be.setCancelled(true);
				
				
			}
		});
	}
	
	public boolean guardarItem(WTabItem wTabItem){
		
		boolean guardo=true;
		if(wTabItem!=null){
			
			if(wTabItem.getBody()==null)
				guardo=wTabItem.saveContent();
			else{
				guardo=guardarItem((WTabItem)wTabItem.getBody().getSelectedItem());
			}
		}
		
		return guardo;
	}
	
	
	public boolean puedeTerminar(){
		
		boolean puede = true;
		
		for(TabItem item : this.getItems()){
			
			WTabItem wItem = (WTabItem) item;
			
			if(wItem.getBody()!=null){
				if(!wItem.getBody().puedeTerminar())
					puede = false;
			}else{
				if(!wItem.valida())
					puede = false;
			}
		}
		return puede;
	}

	public void onNext(WTabItem item) {
		
		int idItem=0;
		
		for(TabItem tabItem: this.getItems()){
			
			
			if(tabItem==item)
				break;		
			
			idItem++;
		}
		
		if(idItem==this.getItems().size()-1){
			next();
			return;
		}

		WTabItem globalSelected=(WTabItem)this.getItem(idItem+1);
		
		this.setSelection(globalSelected);
		
	}
	
	public void onBack(WTabItem item) {
		
		int idItem=0;
		
		for(TabItem tabItem: this.getItems()){
			
			
			if(tabItem==item)
				break;		
			
			idItem++;
		}
		
		if(idItem==0){
			back();
			return;
		}

		WTabItem globalSelected=(WTabItem)this.getItem(idItem-1);
		
		this.setSelection(globalSelected);
		
	}


	@Override
	public boolean add(TabItem item) {
		
		if(item instanceof WTabItem){
			((WTabItem) item).setParent(this);
		}
		
		return super.add(item);
	}

	public void setParent(WTabItem parent) {
		this.parent = parent;
	}


}
