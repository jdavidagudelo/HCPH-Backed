package com.artica.telesalud.tph.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.ServletContextAware;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.controller.constants.ConceptRestURIConstants;
import com.artica.telesalud.tph.controller.constants.HibernateProperties;
import com.artica.telesalud.tph.controller.exception.FieldErrorResource;
import com.artica.telesalud.tph.controller.exception.InternalErrorException;
import com.artica.telesalud.tph.controller.exception.InvalidRequestException;
import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.service.ConceptService;
/**
 * Clase usada para acceder a los conceptos clinicos de la aplicacion.
 * @author Juan David Agudelo Alvarez. jdaaa2009@gmail.com
 *
 */
@Controller
@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_URI)
public class ConceptController  extends AbstractController implements ServletContextAware {
	private ConceptService conceptService;
	private ServletContext context;
	public ConceptController() {
	}

	/**
	 * Método que permite obtener la lista de causas de eventos definidas en la base de datos del sistemas.
	 * 
	 * @return lista de causas de eventos de emergencias.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_OBTENER_CAUSAS_URI, method = RequestMethod.GET)
	public @ResponseBody
	List<ConceptSpringDto> obtenerCausasAtencion() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService
					.obtenerCausasAtencionActive();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	/**
	 * Método que permite obtener la lista de los estados civiles de la base de datos.
	 * @return lista de estados civiles.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_ESTADOS_CIVILES_URI)
	public @ResponseBody
	List<ConceptSpringDto> getEstadosCiviles() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.obtenerEstadosCiviles();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Metodo que permite obtener la lista de respuestas oculares de un paciente.
	 * @return lista de las respuestas oculares de un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_RESPUESTAS_OCULARES_URI)
	public @ResponseBody
	List<ConceptSpringDto> getRespuestasOculares() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getRespuestasOculares();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de las exposiciones de un paciente.
	 * @return lista de las exposiciones de un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_EXPOSICIONES_URI)
	public @ResponseBody
	List<ConceptSpringDto> getExposiciones() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getExposiciones();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de tipos de cierre de la atencion de un paciente.
	 * @return lista de tipos de cierre de la atencion de un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_TIPOS_CIERRE_URI)
	public @ResponseBody
	List<ConceptSpringDto> getTiposCierre() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getTiposCierre();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de otros procedimientos realizados a un paciente.
	 * @return lista de otros procedimientos realizados a un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_PROCEDIMIENTOS_OTROS_URI)
	public @ResponseBody
	List<ConceptSpringDto> getProcedimientosOtros() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getProcedimientosOtros();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de procedimientos de soporte vital avanzado.
	 * @return lista de procedimientos de soporte vital avanzado.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_PROCEDIMIENTOS_SOPORTE_VITAL_AVANZADO_URI)
	public @ResponseBody
	List<ConceptSpringDto> getProcedimientosSoporteVitalAvanzado() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getProcedimientosSoporteVitalAvanzado();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de procedimiento de soporte vital basico.
	 * @return lista de procedimientos de soporte vital basico.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_PROCEDIMIENTOS_SOPORTE_VITAL_BASICO_URI)
	public @ResponseBody
	List<ConceptSpringDto> getProcedimientosSoporteVitalBasico() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getProcedimientosSoporteVitalBasico();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de vias de administracion de insumos de un paciente.
	 * @return lista de vias de administracion de insumos de un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_VIAS_ADMINISTRACION_URI)
	public @ResponseBody
	List<ConceptSpringDto> getListaViasAdministracion() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getViasAdministracion();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de unidades de volumen usadas para medir un insumo.
	 * @return lista de unidades de medida usadas para medir un insumo.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_UNIDADES_VOLUMEN_URI)
	public @ResponseBody
	List<ConceptSpringDto> getUnidadesVolumen() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getUnidadesVolumen();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de insumos que se pueden suministrar a un paciente.
	 * @return lista de insumos que se pueden suministrar a un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_INSUMOS_URI)
	public @ResponseBody
	List<ConceptSpringDto> getListaInsumos() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getInsumos();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de aseguradoras de un paciente.
	 * @return lista de aseguradoras de un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_ASEGURADORAS_URI)
	public @ResponseBody
	List<ConceptSpringDto> getAseguradoras() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.obtenerAseguradorasActive();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de resultados de atencion de un paciente.
	 * @return lista de resultados de atencion de un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_RESULTADOS_ATENCION_URI)
	public @ResponseBody
	List<ConceptSpringDto> getResultadosAtencion() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getResultadosAtencion();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de respondientes de la atencion de un paciente.
	 * @return lista de respondientes de la atencion de un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_RESPONDIENTES_URI)
	public @ResponseBody
	List<ConceptSpringDto> getRespondientes() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getRespondientes();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de tipos de negaciones de un paciente.
	 * @return lista de tipos de negaciones de un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_NEGACIONES_PACIENTE_URI)
	public @ResponseBody
	List<ConceptSpringDto> getNegacionesPaciente() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getNegacionesPaciente();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de eps.
	 * @return lista de eps.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_EPSS_URI)
	public @ResponseBody
	List<ConceptSpringDto> getEpss() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.obtenerEpssActive();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de estados de la respiracion.
	 * @return lista de estados de la respiracion.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_ESTADOS_RESPIRACION_URI)
	public @ResponseBody
	List<ConceptSpringDto> getEstadosRespiracion() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getEstadosRespiracion();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}	
	/**
	 * Lista de presencias de respiracion de un paciente.
	 * @return lista de presencias de respiracion de un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_PRESENCIAS_RESPIRACION_URI)
	public @ResponseBody
	List<ConceptSpringDto> getPresenciasRespiracion() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getPresenciasRespiracion();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}	
	/**
	 * Lista de estados de la cianosis de un paciente.
	 * @return lista de estados de la cianosis de un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_CIANOSIS_RESPIRACION_URI)
	public @ResponseBody
	List<ConceptSpringDto> getCianosisRespiracion() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getCianosisRespiracion();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de planes de beneficios de un paciente.
	 * @return lista de planes de beneficios de un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_PLANES_BENEFICIOS_URI)
	public @ResponseBody
	List<ConceptSpringDto> getPlanesBeneficios() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService
					.obtenerPlanesBeneficiosActive();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de lesiones que puede tener un paciente.
	 * @return lista de lesiones que puede tener un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_LESIONES_URI)
	public @ResponseBody
	List<ConceptSpringDto> getLesiones() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getLesiones();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de tipos de antecedentes que puede tener un paciente.
	 * @return lista de tipos de antecedentes que puede tener un paciente.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_TIPOS_ANTECEDENTES_URI)
	public @ResponseBody
	List<ConceptSpringDto> getTiposAntecedentes() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.obtenerTiposAntecedentes();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de tipos de emergencias.
	 * @return lista de tipos de tipos de emergencias.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_TIPO_EMERGENCIAS_URI)
	public @ResponseBody
	List<ConceptSpringDto> getTipoEmergencias() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getTipoEmergencias();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de permeabilidades de via aerea.
	 * @return lista de permeabilidades de via aerea.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_PERMEABILIDADES_VIA_AEREA_URI)
	public @ResponseBody
	List<ConceptSpringDto> getPermeabilidadesViaAerea() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getPermeabilidadesViaAerea();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}
	/**
	 * Lista de causas de obstruccion de la via aerea.
	 * @return lista de causas de obstruccion de la via aerea.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_CAUSAS_OBSTRUCCION_VIA_AEREA_URI)
	public @ResponseBody
	List<ConceptSpringDto> getCausasObstruccion() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.getCausasObstruccionViaAerea();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	/**
	 * Lista de controles APH.
	 * @return lista de controles APH.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_CONTROLES_APH_URI)
	public @ResponseBody
	List<ConceptSpringDto> getControlesAph() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.obtenerControlesAPH();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	/**
	 * Lista de medios de solicitud de teleasistencia.
	 * @return lista de medios de solicitud de teleasistencia.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_MEDIOS_SOLICITUD_TELEASISTENCIA_URI)
	public @ResponseBody
	List<ConceptSpringDto> getMediosSolicitudTeleasistencia() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService
					.obtenerMediosSolicitudTeleasistencia();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	/**
	 * Lista de zonas de un evento de emergencias.
	 * @return
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_ZONAS_URI)
	public @ResponseBody
	List<ConceptSpringDto> getZonas() {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.obtenerZonas();
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	/**
	 * Lista de diagnosticos posibles a un paciente.
	 * @param query parte del nombre de un diagnostico.
	 * @return lista de nombres que contienen el string especificado como argumento.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_DIAGNOSTICOS_URI)
	public @ResponseBody
	List<ConceptSpringDto> getDiagnosticos(
			@PathVariable(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_DIAGNOSTICOS_QUERY_PARAM_NAME_URI) String query) {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService.obtenerDiagnosticos(query);
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	/**
	 * Lista de procedimientos.
	 * @param query parte del nombre de un procedimiento.
	 * @return procedimiento cuyo nombre contiene el string especificado como argumento.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_PROCEDIMIENTOS_URI)
	public @ResponseBody
	List<ConceptSpringDto> getProcedimientos(
			@PathVariable(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_PROCEDIMIENTOS_QUERY_PARAM_NAME) String query) {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService
					.obtenerProcedimientos(query);
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	/**
	 * Lista de procedimientos de hallazgos.
	 * @param query parte del nombre de un procedimiento de un hallazgo.
	 * @return lista de procedimientos de un hallazgo.
	 */
	@RequestMapping(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_PROCEDIMIENTOS_HALLAZGOS_URI)
	public @ResponseBody
	List<ConceptSpringDto> getProcedimientosHallazgos(
			@PathVariable(value = ConceptRestURIConstants.CONCEPT_SERVICE_GET_PROCEDIMIENTOS_HALLAZGOS_QUERY_PARAM_NAME) String query) {
		try {
			super.refreshHibernateSession(context);
			List<ConceptSpringDto> result = new ArrayList<ConceptSpringDto>();
			List<Concept> concepts = conceptService
					.obtenerProcedimientosHallazgos(query);
			for (Concept concept : concepts) {
				result.add(new ConceptSpringDto(concept));
			}
			return result;
		} catch (Exception ex) {
			List<FieldErrorResource> errors = new ArrayList<FieldErrorResource>();
			errors.add(new FieldErrorResource(ConceptController.class
					.getCanonicalName(), String
					.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage()));
			throw new InternalErrorException(ex.getMessage(), errors);
		}
	}

	
	@ExceptionHandler(InternalErrorException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	List<FieldErrorResource> handleException(InternalErrorException ex) {
		return ex.getErrors();
	}

	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public @ResponseBody
	List<FieldErrorResource> handleException(InvalidRequestException ex) {
		return ex.getErrors();
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		context = servletContext;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				servletContext
						.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory(servletContext
				.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));

		conceptService = new ConceptService(
				servletContext
						.getInitParameter(HibernateProperties.TPH_DAO_CLASS_FACTORY_IMPL),
				params);
	}

}
