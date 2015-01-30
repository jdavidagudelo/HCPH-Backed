package com.artica.telesalud.tph.controller.model.amp;

import java.util.List;
/**
 * Clase usada por Spring para mapear directamente la informacion de un documento a objetos JSON.
 * @author  Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class SendDocumentInfo {
	private List<String> ehrs;
	private String encodedPdf;
	public SendDocumentInfo()
	{
	}
	public List<String> getEhrs() {
		return ehrs;
	}
	public void setEhrs(List<String> ehrs) {
		this.ehrs = ehrs;
	}
	public String getEncodedPdf() {
		return encodedPdf;
	}
	public void setEncodedPdf(String encodedPdf) {
		this.encodedPdf = encodedPdf;
	}
	
}
