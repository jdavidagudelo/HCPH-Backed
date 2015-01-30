package com.artica.telesalud.tph.controller.model.amp;

import java.util.ArrayList;
import java.util.List;

import com.artica.telesalud.message.service.dto.AntecedenteAmp;
import com.artica.telesalud.message.service.dto.AntecedentesAmp;
import com.artica.telesalud.tph.controller.model.evento.LesionadoSpringDto;
import com.artica.telesalud.tph.model.evento.Lesionado;

/**
 * Clase usada por Spring para mapear lista de antecedentes AMP a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class AntecedentesAmpSpringDto {

	/**
	 * 
	 */
	private String historiaClinicaUuid;
	private String historiaClinicaNombre;
	private List<AntecedenteAmpSpringDto> antecedentes = new ArrayList<AntecedenteAmpSpringDto>();
	private LesionadoSpringDto lesionado;
	public AntecedentesAmpSpringDto()
	{
	}
	public AntecedentesAmpSpringDto(AntecedentesAmp antecedente, Lesionado lesionado)
	{
		historiaClinicaUuid = antecedente.getHistoriaClinicaUuid();
		historiaClinicaNombre = antecedente.getHistoriaClinicaNombre();
		for(AntecedenteAmp amp : antecedente.getAntecedentes())
		{
			for(int i = 0; i < amp.getListaAntecedentes().size(); i++)
			{
				AntecedenteAmpSpringDto a = new AntecedenteAmpSpringDto();
				a.setDate(amp.getListaFechas().get(i));
				a.setDescription(amp.getListaAntecedentes().get(i));
				a.setTipoAntecedente(amp.getTipoAntecedente());
				antecedentes.add(a);
			}
		}
		if(lesionado != null)
		{
			this.lesionado = new LesionadoSpringDto(lesionado);
		}
	}
	
	/**
	 * @return the lesionado
	 */
	public LesionadoSpringDto getLesionado() {
		return lesionado;
	}
	/**
	 * @param lesionado the lesionado to set
	 */
	public void setLesionado(LesionadoSpringDto lesionado) {
		this.lesionado = lesionado;
	}
	/**
	 * @return the historiaClinicaUuid
	 */
	public String getHistoriaClinicaUuid() {
		return historiaClinicaUuid;
	}
	/**
	 * @param historiaClinicaUuid the historiaClinicaUuid to set
	 */
	public void setHistoriaClinicaUuid(String historiaClinicaUuid) {
		this.historiaClinicaUuid = historiaClinicaUuid;
	}
	/**
	 * @return the historiaClinicaNombre
	 */
	public String getHistoriaClinicaNombre() {
		return historiaClinicaNombre;
	}
	/**
	 * @param historiaClinicaNombre the historiaClinicaNombre to set
	 */
	public void setHistoriaClinicaNombre(String historiaClinicaNombre) {
		this.historiaClinicaNombre = historiaClinicaNombre;
	}

	/**
	 * @return the antecedentes
	 */
	public List<AntecedenteAmpSpringDto> getAntecedentes() {
		return antecedentes;
	}
	/**
	 * @param antecedentes the antecedentes to set
	 */
	public void setAntecedentes(List<AntecedenteAmpSpringDto> antecedentes) {
		this.antecedentes = antecedentes;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((antecedentes == null) ? 0 : antecedentes.hashCode());
		result = prime
				* result
				+ ((historiaClinicaNombre == null) ? 0 : historiaClinicaNombre
						.hashCode());
		result = prime
				* result
				+ ((historiaClinicaUuid == null) ? 0 : historiaClinicaUuid
						.hashCode());
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
		if (!(obj instanceof AntecedentesAmpSpringDto)) {
			return false;
		}
		AntecedentesAmpSpringDto other = (AntecedentesAmpSpringDto) obj;
		if (antecedentes == null) {
			if (other.antecedentes != null) {
				return false;
			}
		} else if (!antecedentes.equals(other.antecedentes)) {
			return false;
		}
		if (historiaClinicaNombre == null) {
			if (other.historiaClinicaNombre != null) {
				return false;
			}
		} else if (!historiaClinicaNombre.equals(other.historiaClinicaNombre)) {
			return false;
		}
		if (historiaClinicaUuid == null) {
			if (other.historiaClinicaUuid != null) {
				return false;
			}
		} else if (!historiaClinicaUuid.equals(other.historiaClinicaUuid)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AntecedentesAmpSpringDto [historiaClinicaUuid="
				+ historiaClinicaUuid + ", historiaClinicaNombre="
				+ historiaClinicaNombre + ", antecedentes=" + antecedentes
				+ "]";
	}
	
}
