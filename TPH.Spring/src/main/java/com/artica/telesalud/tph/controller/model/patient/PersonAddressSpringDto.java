package com.artica.telesalud.tph.controller.model.patient;

import java.util.Date;

import com.artica.telesalud.tph.controller.model.evento.EventAddressSpringDto;
import com.artica.telesalud.tph.model.person.PersonAddress;

/**
 * Clase utilizada para mapear la direccion de una persona a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class PersonAddressSpringDto {
	private Integer personAddressId;
	private Integer personId;
	private Boolean preferred;
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
	private EventAddressSpringDto completeAddress;
	
	
	/**
	 * @return the creator
	 */
	public Integer getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @return the completeAddress
	 */
	public EventAddressSpringDto getCompleteAddress() {
		return completeAddress;
	}

	/**
	 * @param completeAddress the completeAddress to set
	 */
	public void setCompleteAddress(EventAddressSpringDto completeAddress) {
		this.completeAddress = completeAddress;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public PersonAddressSpringDto() {
		super();
	}

	public PersonAddressSpringDto(PersonAddress personAddress){
		if(personAddress==null){
			return;
		}
		personAddressId = personAddress.getPersonAddressId();
		if(personAddress.getPerson() !=null)
		{
			personId = personAddress.getPerson().getPersonId();
		}
		preferred = personAddress.getPreferred() == null || personAddress.getPreferred().equals(0)?false:true;
		address = personAddress.getAddress();
		city = personAddress.getCity();
		neightborhoodCell = personAddress.getNeightborhoodCell();
		region = personAddress.getRegion();
		postalCode = personAddress.getPostalCode();
		latitude = personAddress.getLatitude();
		longitude = personAddress.getLongitude();
		phone1 = personAddress.getPhone1();
		phone2 = personAddress.getPhone2();
		email = personAddress.getEmail();
		creator = personAddress.getCreator();
		dateCreated = personAddress.getDateCreated();
		if(personAddress.getCompleteAddress() != null)
		{
			completeAddress = new EventAddressSpringDto(personAddress.getCompleteAddress());
		}
	}
	
	/**
	 * @return the personId
	 */
	public Integer getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	/**
	 * @return the personAddressId
	 */
	public Integer getPersonAddressId() {
		return personAddressId;
	}
	/**
	 * @param personAddressId the personAddressId to set
	 */
	public void setPersonAddressId(Integer personAddressId) {
		this.personAddressId = personAddressId;
	}

	/**
	 * @return the preferred
	 */
	public Boolean getPreferred() {
		return preferred;
	}
	/**
	 * @param preferred the preferred to set
	 */
	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public Integer getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(Integer city) {
		this.city = city;
	}
	/**
	 * @return the neightborhoodCell
	 */
	public String getNeightborhoodCell() {
		return neightborhoodCell;
	}
	/**
	 * @param neightborhoodCell the neightborhoodCell to set
	 */
	public void setNeightborhoodCell(String neightborhoodCell) {
		this.neightborhoodCell = neightborhoodCell;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}
	/**
	 * @param phone1 the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return phone2;
	}
	/**
	 * @param phone2 the phone2 to set
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		result = prime
				* result
				+ ((neightborhoodCell == null) ? 0 : neightborhoodCell
						.hashCode());
		result = prime * result
				+ ((personAddressId == null) ? 0 : personAddressId.hashCode());
		result = prime * result
				+ ((personId == null) ? 0 : personId.hashCode());
		result = prime * result + ((phone1 == null) ? 0 : phone1.hashCode());
		result = prime * result + ((phone2 == null) ? 0 : phone2.hashCode());
		result = prime * result
				+ ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result
				+ ((preferred == null) ? 0 : preferred.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PersonAddressSpringDto)) {
			return false;
		}
		PersonAddressSpringDto other = (PersonAddressSpringDto) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (latitude == null) {
			if (other.latitude != null) {
				return false;
			}
		} else if (!latitude.equals(other.latitude)) {
			return false;
		}
		if (longitude == null) {
			if (other.longitude != null) {
				return false;
			}
		} else if (!longitude.equals(other.longitude)) {
			return false;
		}
		if (neightborhoodCell == null) {
			if (other.neightborhoodCell != null) {
				return false;
			}
		} else if (!neightborhoodCell.equals(other.neightborhoodCell)) {
			return false;
		}
		if (personAddressId == null) {
			if (other.personAddressId != null) {
				return false;
			}
		} else if (!personAddressId.equals(other.personAddressId)) {
			return false;
		}
		if (personId == null) {
			if (other.personId != null) {
				return false;
			}
		} else if (!personId.equals(other.personId)) {
			return false;
		}
		if (phone1 == null) {
			if (other.phone1 != null) {
				return false;
			}
		} else if (!phone1.equals(other.phone1)) {
			return false;
		}
		if (phone2 == null) {
			if (other.phone2 != null) {
				return false;
			}
		} else if (!phone2.equals(other.phone2)) {
			return false;
		}
		if (postalCode == null) {
			if (other.postalCode != null) {
				return false;
			}
		} else if (!postalCode.equals(other.postalCode)) {
			return false;
		}
		if (preferred == null) {
			if (other.preferred != null) {
				return false;
			}
		} else if (!preferred.equals(other.preferred)) {
			return false;
		}
		if (region == null) {
			if (other.region != null) {
				return false;
			}
		} else if (!region.equals(other.region)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonAddressSpringDto [personAddressId=" + personAddressId
				+ ", personId=" + personId + ", preferred=" + preferred
				+ ", address=" + address + ", city=" + city
				+ ", neightborhoodCell=" + neightborhoodCell + ", region="
				+ region + ", postalCode=" + postalCode + ", latitude="
				+ latitude + ", longitude=" + longitude + ", phone1=" + phone1
				+ ", phone2=" + phone2 + ", email=" + email + ", creator="
				+ creator + ", dateCreated=" + dateCreated
				+ ", completeAddress=" + completeAddress + "]";
	}

	
}
