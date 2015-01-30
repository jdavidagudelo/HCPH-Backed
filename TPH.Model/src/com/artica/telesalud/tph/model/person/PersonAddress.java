package com.artica.telesalud.tph.model.person;

import java.io.Serializable;
import java.util.Date;


import com.artica.telesalud.tph.model.evento.EventAddress;

public class PersonAddress implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer personAddressId;
	private Person person;
	private Integer preferred;
	private String address;
	private Integer city;
	private String neightborhoodCell;
	private String region;
	private String postalCode;
	private String latitude;
	private String longitude;
	private String phone1;
	private String phone2;
	private String email;
	private Integer creator;
	private Date dateCreated;
	private Short voided;
	private Integer voidedBy;
	private Date dateVoided;
	private String voidReason;
	private String Uuid;
	private EventAddress completeAddress;
	
	
	public Integer getPersonAddressId() {
		return personAddressId;
	}
	public void setPersonAddressId(Integer personAddressId) {
		this.personAddressId = personAddressId;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Integer getPreferred() {
		return preferred;
	}
	public void setPreferred(Integer preferred) {
		this.preferred = preferred;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getNeightborhoodCell() {
		return neightborhoodCell;
	}
	public void setNeightborhoodCell(String neightborhoodCell) {
		this.neightborhoodCell = neightborhoodCell;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Short getVoided() {
		return voided;
	}
	public void setVoided(Short voided) {
		this.voided = voided;
	}
	public Integer getVoidedBy() {
		return voidedBy;
	}
	public void setVoidedBy(Integer voidedBy) {
		this.voidedBy = voidedBy;
	}
	public Date getDateVoided() {
		return dateVoided;
	}
	public void setDateVoided(Date dateVoided) {
		this.dateVoided = dateVoided;
	}
	public String getVoidReason() {
		return voidReason;
	}
	public void setVoidReason(String voidReason) {
		this.voidReason = voidReason;
	}
	public String getUuid() {
		return Uuid;
	}
	public void setUuid(String uuid) {
		Uuid = uuid;
	}
	/**
	 * @return the completeAddress
	 */
	public EventAddress getCompleteAddress() {
		return completeAddress;
	}
	/**
	 * @param completeAddress the completeAddress to set
	 */
	public void setCompleteAddress(EventAddress completeAddress) {
		this.completeAddress = completeAddress;
	}

}
