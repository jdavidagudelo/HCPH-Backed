package com.artica.telesalud.tph.model.person;

public class AttributeTypeOptions {
	
	private Integer attributeTypeOptionsId;
	private PersonAttributeType personAttributeType;
	private String name;
	private Integer defaultValue;
	
	public Integer getAttributeTypeOptionsId() {
		return attributeTypeOptionsId;
	}
	public void setAttributeTypeOptionsId(Integer attributeTypeOptionsId) {
		this.attributeTypeOptionsId = attributeTypeOptionsId;
	}
	public PersonAttributeType getPersonAttributeType() {
		return personAttributeType;
	}
	public void setPersonAttributeType(PersonAttributeType personAttributeType) {
		this.personAttributeType = personAttributeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Integer defaultValue) {
		this.defaultValue = defaultValue;
	}

}
