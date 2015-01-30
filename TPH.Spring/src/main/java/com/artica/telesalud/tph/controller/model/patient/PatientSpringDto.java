package com.artica.telesalud.tph.controller.model.patient;

import java.util.Date;

import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.model.patient.Patient;
/**
 * Clase utilizada para mapear pacientes a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class PatientSpringDto {
	private Integer patientId;
	private PersonSpringDto person;
	private ConceptSpringDto maritalStatus;
	private String ocupation;
	private ConceptSpringDto userType;
	private ConceptSpringDto afiliateType;
	private ConceptSpringDto externalCause;
	private String momId;
	private String momName;
	private String dadId;
	private String dadName;
	private String responsableName;
	private String responsableFamily;
	private String responsablePhone;
	private String responsablePhone2;
	private String responsableKin;
	private Boolean hasAttendat;
	private String attendatName;
	private String attendatFamily;
	private String attendatPhone;
	private String attendatKin;
	private PatientIdentifierSpringDto preferredIdentifier;
	private Integer creator;
	private Date dateCreated;
	
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
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public PatientSpringDto() {
		super();
	}
	public PatientSpringDto(Patient patient)
	{
		if(patient == null){
			return;
		}
		patientId=patient.getPatientId();
		if(patient.getPerson() != null)
		{
		person= new PersonSpringDto(patient.getPerson());
		}
		if(patient.getMaritalStatus() != null)
		{
		maritalStatus = new ConceptSpringDto(patient.getMaritalStatus());
		}
		ocupation=patient.getOcupation();
		if(patient.getUserType() != null)
		{
		userType=new ConceptSpringDto(patient.getUserType());
		}
		if(patient.getAfiliateType() != null)
		{
		afiliateType=new ConceptSpringDto(patient.getAfiliateType());
		}
		if(patient.getExternalCause() != null)
		{
		externalCause=new ConceptSpringDto(patient.getExternalCause());
		}
		momId=patient.getMomId();
		momName=patient.getMomName();
		dadId=patient.getDadId();
		dadName=patient.getDadName();
		responsableName=patient.getResponsableName();
		responsableFamily=patient.getResponsableFamily();
		responsablePhone=patient.getResponsablePhone();
		responsablePhone2=patient.getResponsablePhone2();
		responsableKin=patient.getResponsableKin();
		hasAttendat=Integer.valueOf(0).equals(patient.getHasAttendat())?false:true;
		attendatName=patient.getAttendatName();
		attendatFamily=patient.getAttendatFamily();
		attendatPhone=patient.getAttendatPhone();
		attendatKin=patient.getAttendatKin();
		if(patient.getPreferredIdentifier() != null)
		{
		preferredIdentifier = new PatientIdentifierSpringDto(patient.getPreferredIdentifier());
		}
		creator = patient.getCreator();
		dateCreated = patient.getDateCreated();
	}
	/**
	 * @return the patientId
	 */
	public Integer getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the person
	 */
	public PersonSpringDto getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(PersonSpringDto person) {
		this.person = person;
	}
	/**
	 * @return the maritalStatus
	 */
	public ConceptSpringDto getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(ConceptSpringDto maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * @return the ocupation
	 */
	public String getOcupation() {
		return ocupation;
	}
	/**
	 * @param ocupation the ocupation to set
	 */
	public void setOcupation(String ocupation) {
		this.ocupation = ocupation;
	}
	/**
	 * @return the userType
	 */
	public ConceptSpringDto getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(ConceptSpringDto userType) {
		this.userType = userType;
	}
	/**
	 * @return the afiliateType
	 */
	public ConceptSpringDto getAfiliateType() {
		return afiliateType;
	}
	/**
	 * @param afiliateType the afiliateType to set
	 */
	public void setAfiliateType(ConceptSpringDto afiliateType) {
		this.afiliateType = afiliateType;
	}
	/**
	 * @return the externalCause
	 */
	public ConceptSpringDto getExternalCause() {
		return externalCause;
	}
	/**
	 * @param externalCause the externalCause to set
	 */
	public void setExternalCause(ConceptSpringDto externalCause) {
		this.externalCause = externalCause;
	}
	/**
	 * @return the momId
	 */
	public String getMomId() {
		return momId;
	}
	/**
	 * @param momId the momId to set
	 */
	public void setMomId(String momId) {
		this.momId = momId;
	}
	/**
	 * @return the momName
	 */
	public String getMomName() {
		return momName;
	}
	/**
	 * @param momName the momName to set
	 */
	public void setMomName(String momName) {
		this.momName = momName;
	}
	/**
	 * @return the dadId
	 */
	public String getDadId() {
		return dadId;
	}
	/**
	 * @param dadId the dadId to set
	 */
	public void setDadId(String dadId) {
		this.dadId = dadId;
	}
	/**
	 * @return the dadName
	 */
	public String getDadName() {
		return dadName;
	}
	/**
	 * @param dadName the dadName to set
	 */
	public void setDadName(String dadName) {
		this.dadName = dadName;
	}
	/**
	 * @return the responsableName
	 */
	public String getResponsableName() {
		return responsableName;
	}
	/**
	 * @param responsableName the responsableName to set
	 */
	public void setResponsableName(String responsableName) {
		this.responsableName = responsableName;
	}
	/**
	 * @return the responsableFamily
	 */
	public String getResponsableFamily() {
		return responsableFamily;
	}
	/**
	 * @param responsableFamily the responsableFamily to set
	 */
	public void setResponsableFamily(String responsableFamily) {
		this.responsableFamily = responsableFamily;
	}
	/**
	 * @return the responsablePhone
	 */
	public String getResponsablePhone() {
		return responsablePhone;
	}
	/**
	 * @param responsablePhone the responsablePhone to set
	 */
	public void setResponsablePhone(String responsablePhone) {
		this.responsablePhone = responsablePhone;
	}
	/**
	 * @return the responsablePhone2
	 */
	public String getResponsablePhone2() {
		return responsablePhone2;
	}
	/**
	 * @param responsablePhone2 the responsablePhone2 to set
	 */
	public void setResponsablePhone2(String responsablePhone2) {
		this.responsablePhone2 = responsablePhone2;
	}
	/**
	 * @return the responsableKin
	 */
	public String getResponsableKin() {
		return responsableKin;
	}
	/**
	 * @param responsableKin the responsableKin to set
	 */
	public void setResponsableKin(String responsableKin) {
		this.responsableKin = responsableKin;
	}
	/**
	 * @return the hasAttendat
	 */
	public Boolean getHasAttendat() {
		return hasAttendat;
	}
	/**
	 * @param hasAttendat the hasAttendat to set
	 */
	public void setHasAttendat(Boolean hasAttendat) {
		this.hasAttendat = hasAttendat;
	}
	/**
	 * @return the attendatName
	 */
	public String getAttendatName() {
		return attendatName;
	}
	/**
	 * @param attendatName the attendatName to set
	 */
	public void setAttendatName(String attendatName) {
		this.attendatName = attendatName;
	}
	/**
	 * @return the attendatFamily
	 */
	public String getAttendatFamily() {
		return attendatFamily;
	}
	/**
	 * @param attendatFamily the attendatFamily to set
	 */
	public void setAttendatFamily(String attendatFamily) {
		this.attendatFamily = attendatFamily;
	}
	/**
	 * @return the attendatPhone
	 */
	public String getAttendatPhone() {
		return attendatPhone;
	}
	/**
	 * @param attendatPhone the attendatPhone to set
	 */
	public void setAttendatPhone(String attendatPhone) {
		this.attendatPhone = attendatPhone;
	}
	/**
	 * @return the attendatKin
	 */
	public String getAttendatKin() {
		return attendatKin;
	}
	/**
	 * @param attendatKin the attendatKin to set
	 */
	public void setAttendatKin(String attendatKin) {
		this.attendatKin = attendatKin;
	}
	/**
	 * @return the preferredIdentifier
	 */
	public PatientIdentifierSpringDto getPreferredIdentifier() {
		return preferredIdentifier;
	}
	/**
	 * @param preferredIdentifier the preferredIdentifier to set
	 */
	public void setPreferredIdentifier(
			PatientIdentifierSpringDto preferredIdentifier) {
		this.preferredIdentifier = preferredIdentifier;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((afiliateType == null) ? 0 : afiliateType.hashCode());
		result = prime * result
				+ ((attendatFamily == null) ? 0 : attendatFamily.hashCode());
		result = prime * result
				+ ((attendatKin == null) ? 0 : attendatKin.hashCode());
		result = prime * result
				+ ((attendatName == null) ? 0 : attendatName.hashCode());
		result = prime * result
				+ ((attendatPhone == null) ? 0 : attendatPhone.hashCode());
		result = prime * result + ((dadId == null) ? 0 : dadId.hashCode());
		result = prime * result + ((dadName == null) ? 0 : dadName.hashCode());
		result = prime * result
				+ ((externalCause == null) ? 0 : externalCause.hashCode());
		result = prime * result
				+ ((hasAttendat == null) ? 0 : hasAttendat.hashCode());
		result = prime * result
				+ ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((momId == null) ? 0 : momId.hashCode());
		result = prime * result + ((momName == null) ? 0 : momName.hashCode());
		result = prime * result
				+ ((ocupation == null) ? 0 : ocupation.hashCode());
		result = prime * result
				+ ((patientId == null) ? 0 : patientId.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime
				* result
				+ ((preferredIdentifier == null) ? 0 : preferredIdentifier
						.hashCode());
		result = prime
				* result
				+ ((responsableFamily == null) ? 0 : responsableFamily
						.hashCode());
		result = prime * result
				+ ((responsableKin == null) ? 0 : responsableKin.hashCode());
		result = prime * result
				+ ((responsableName == null) ? 0 : responsableName.hashCode());
		result = prime
				* result
				+ ((responsablePhone == null) ? 0 : responsablePhone.hashCode());
		result = prime
				* result
				+ ((responsablePhone2 == null) ? 0 : responsablePhone2
						.hashCode());
		result = prime * result
				+ ((userType == null) ? 0 : userType.hashCode());
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
		if (!(obj instanceof PatientSpringDto)) {
			return false;
		}
		PatientSpringDto other = (PatientSpringDto) obj;
		if (afiliateType == null) {
			if (other.afiliateType != null) {
				return false;
			}
		} else if (!afiliateType.equals(other.afiliateType)) {
			return false;
		}
		if (attendatFamily == null) {
			if (other.attendatFamily != null) {
				return false;
			}
		} else if (!attendatFamily.equals(other.attendatFamily)) {
			return false;
		}
		if (attendatKin == null) {
			if (other.attendatKin != null) {
				return false;
			}
		} else if (!attendatKin.equals(other.attendatKin)) {
			return false;
		}
		if (attendatName == null) {
			if (other.attendatName != null) {
				return false;
			}
		} else if (!attendatName.equals(other.attendatName)) {
			return false;
		}
		if (attendatPhone == null) {
			if (other.attendatPhone != null) {
				return false;
			}
		} else if (!attendatPhone.equals(other.attendatPhone)) {
			return false;
		}
		if (dadId == null) {
			if (other.dadId != null) {
				return false;
			}
		} else if (!dadId.equals(other.dadId)) {
			return false;
		}
		if (dadName == null) {
			if (other.dadName != null) {
				return false;
			}
		} else if (!dadName.equals(other.dadName)) {
			return false;
		}
		if (externalCause == null) {
			if (other.externalCause != null) {
				return false;
			}
		} else if (!externalCause.equals(other.externalCause)) {
			return false;
		}
		if (hasAttendat == null) {
			if (other.hasAttendat != null) {
				return false;
			}
		} else if (!hasAttendat.equals(other.hasAttendat)) {
			return false;
		}
		if (maritalStatus == null) {
			if (other.maritalStatus != null) {
				return false;
			}
		} else if (!maritalStatus.equals(other.maritalStatus)) {
			return false;
		}
		if (momId == null) {
			if (other.momId != null) {
				return false;
			}
		} else if (!momId.equals(other.momId)) {
			return false;
		}
		if (momName == null) {
			if (other.momName != null) {
				return false;
			}
		} else if (!momName.equals(other.momName)) {
			return false;
		}
		if (ocupation == null) {
			if (other.ocupation != null) {
				return false;
			}
		} else if (!ocupation.equals(other.ocupation)) {
			return false;
		}
		if (patientId == null) {
			if (other.patientId != null) {
				return false;
			}
		} else if (!patientId.equals(other.patientId)) {
			return false;
		}
		if (person == null) {
			if (other.person != null) {
				return false;
			}
		} else if (!person.equals(other.person)) {
			return false;
		}
		if (preferredIdentifier == null) {
			if (other.preferredIdentifier != null) {
				return false;
			}
		} else if (!preferredIdentifier.equals(other.preferredIdentifier)) {
			return false;
		}
		if (responsableFamily == null) {
			if (other.responsableFamily != null) {
				return false;
			}
		} else if (!responsableFamily.equals(other.responsableFamily)) {
			return false;
		}
		if (responsableKin == null) {
			if (other.responsableKin != null) {
				return false;
			}
		} else if (!responsableKin.equals(other.responsableKin)) {
			return false;
		}
		if (responsableName == null) {
			if (other.responsableName != null) {
				return false;
			}
		} else if (!responsableName.equals(other.responsableName)) {
			return false;
		}
		if (responsablePhone == null) {
			if (other.responsablePhone != null) {
				return false;
			}
		} else if (!responsablePhone.equals(other.responsablePhone)) {
			return false;
		}
		if (responsablePhone2 == null) {
			if (other.responsablePhone2 != null) {
				return false;
			}
		} else if (!responsablePhone2.equals(other.responsablePhone2)) {
			return false;
		}
		if (userType == null) {
			if (other.userType != null) {
				return false;
			}
		} else if (!userType.equals(other.userType)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PatientSpringDto [patientId=" + patientId + ", person="
				+ person + ", maritalStatus=" + maritalStatus + ", ocupation="
				+ ocupation + ", userType=" + userType + ", afiliateType="
				+ afiliateType + ", externalCause=" + externalCause
				+ ", momId=" + momId + ", momName=" + momName + ", dadId="
				+ dadId + ", dadName=" + dadName + ", responsableName="
				+ responsableName + ", responsableFamily=" + responsableFamily
				+ ", responsablePhone=" + responsablePhone
				+ ", responsablePhone2=" + responsablePhone2
				+ ", responsableKin=" + responsableKin + ", hasAttendat="
				+ hasAttendat + ", attendatName=" + attendatName
				+ ", attendatFamily=" + attendatFamily + ", attendatPhone="
				+ attendatPhone + ", attendatKin=" + attendatKin
				+ ", preferredIdentifier=" + preferredIdentifier + "]";
	}
	
}
