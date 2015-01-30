package com.artica.telesalud.tph.controller.model.amp;

import java.util.Date;

import com.artica.telesalud.patientmasterindex.model.Ehr;
/**
 * Clase utilizada por Spring para mapear entidad historia clinica a objeto JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class EhrSpringDto {
	private String uuid;
    private String name;
    private Date creationDate;
    public EhrSpringDto()
    {
    	
    }
    public EhrSpringDto(Ehr ehr)
    {
    	uuid = ehr.getUuid();
    	name = ehr.getName();
    	creationDate = ehr.getCreationDate();
    }
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
    
}
