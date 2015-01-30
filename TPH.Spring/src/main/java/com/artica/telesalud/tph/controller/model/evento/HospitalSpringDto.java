package com.artica.telesalud.tph.controller.model.evento;

import java.util.Date;

import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.controller.model.location.CitySpringDto;
import com.artica.telesalud.tph.model.evento.Hospital;
/**
 * Clase utilizada para mapear hospitales a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class HospitalSpringDto {
	private Integer hospitalId;
	private String nombre;
	private CitySpringDto ciudad;
	private String direccion;
	private ConceptSpringDto nivelComplejidad;	private Integer creator;
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
	
	public HospitalSpringDto() {
		super();
	}
	public HospitalSpringDto(Hospital hospital)
	{
		if(hospital == null){
			return;
		}
		hospitalId=hospital.getHospitalId();
		nombre=hospital.getNombre();
		if(hospital.getCiudad() != null)
		{
		
			ciudad=new CitySpringDto(hospital.getCiudad());
		}
		direccion=hospital.getDireccion();
		if(hospital.getNivelComplejidad() != null)
		{
			nivelComplejidad=new ConceptSpringDto(hospital.getNivelComplejidad()); 
		}
		creator = hospital.getCreator();
		dateCreated = hospital.getDateCreated();
	}
	/**
	 * @return the hospitalId
	 */
	public Integer getHospitalId() {
		return hospitalId;
	}
	/**
	 * @param hospitalId the hospitalId to set
	 */
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the ciudad
	 */
	public CitySpringDto getCiudad() {
		return ciudad;
	}
	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(CitySpringDto ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the nivelComplejidad
	 */
	public ConceptSpringDto getNivelComplejidad() {
		return nivelComplejidad;
	}
	/**
	 * @param nivelComplejidad the nivelComplejidad to set
	 */
	public void setNivelComplejidad(ConceptSpringDto nivelComplejidad) {
		this.nivelComplejidad = nivelComplejidad;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result
				+ ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result
				+ ((hospitalId == null) ? 0 : hospitalId.hashCode());
		result = prime
				* result
				+ ((nivelComplejidad == null) ? 0 : nivelComplejidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		if (!(obj instanceof HospitalSpringDto)) {
			return false;
		}
		HospitalSpringDto other = (HospitalSpringDto) obj;
		if (ciudad == null) {
			if (other.ciudad != null) {
				return false;
			}
		} else if (!ciudad.equals(other.ciudad)) {
			return false;
		}
		if (direccion == null) {
			if (other.direccion != null) {
				return false;
			}
		} else if (!direccion.equals(other.direccion)) {
			return false;
		}
		if (hospitalId == null) {
			if (other.hospitalId != null) {
				return false;
			}
		} else if (!hospitalId.equals(other.hospitalId)) {
			return false;
		}
		if (nivelComplejidad == null) {
			if (other.nivelComplejidad != null) {
				return false;
			}
		} else if (!nivelComplejidad.equals(other.nivelComplejidad)) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HospitalSpringDto [hospitalId=" + hospitalId + ", nombre="
				+ nombre + ", ciudad=" + ciudad + ", direccion=" + direccion
				+ ", nivelComplejidad=" + nivelComplejidad + "]";
	}
	
}
