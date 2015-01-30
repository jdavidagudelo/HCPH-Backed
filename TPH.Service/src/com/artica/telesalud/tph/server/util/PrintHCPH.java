package com.artica.telesalud.tph.server.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artica.telesalud.client.shared.ValuedItem;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.persona.dao.PersonaDAOFactory;
import com.artica.telesalud.persona.dao.UserDAO;
import com.artica.telesalud.persona.model.impl.UserDTO;
import com.artica.telesalud.shared.TelesaludGWTException;
import com.artica.telesalud.tph.client.hcp.i18n.FieldsNameHCP;
import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.dao.PersonDAO;
import com.artica.telesalud.tph.dao.TPHDAOFactory;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.lightweightmodel.Antecedente;
import com.artica.telesalud.tph.lightweightmodel.AntecedenteData;
import com.artica.telesalud.tph.lightweightmodel.ArchivoData;
import com.artica.telesalud.tph.lightweightmodel.CierreAtencion;
import com.artica.telesalud.tph.lightweightmodel.EvaluacionData;
import com.artica.telesalud.tph.lightweightmodel.HallazgoData;
import com.artica.telesalud.tph.lightweightmodel.HallazgoData.Lesiones;
import com.artica.telesalud.tph.lightweightmodel.InformacionEventoData;
import com.artica.telesalud.tph.lightweightmodel.LesionadoCompletoData;
import com.artica.telesalud.tph.lightweightmodel.NotaAclaratoria;
import com.artica.telesalud.tph.lightweightmodel.NotaEvolucion;
import com.artica.telesalud.tph.lightweightmodel.PacienteCompletoData;
import com.artica.telesalud.tph.lightweightmodel.PersonaLesionada;
import com.artica.telesalud.tph.lightweightmodel.Procedimientos;
import com.artica.telesalud.tph.lightweightmodel.ResponsableAtencion;
import com.artica.telesalud.tph.lightweightmodel.ResultadoData;
import com.artica.telesalud.tph.lightweightmodel.ResultadosCompleteData;
import com.artica.telesalud.tph.lightweightmodel.SignosVitalesData;
import com.artica.telesalud.tph.lightweightmodel.TeleasistenciaCompleteData;
import com.artica.telesalud.tph.lightweightmodel.TeleasistenciaData;
import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.evento.PrimerRespondiente;
import com.artica.telesalud.tph.model.evento.Tripulacion;
import com.artica.telesalud.tph.model.location.City;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.observation.ObservationData;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.server.FieldsNameHCPImpl;
import com.artica.telesalud.tph.service.ConceptService;
import com.artica.telesalud.tph.service.EncounterService;
import com.artica.telesalud.tph.service.HospitalService;
import com.artica.telesalud.tph.service.LesionadoService;
import com.artica.telesalud.tph.service.LocationService;
import com.artica.telesalud.tph.service.ObsService;
import com.artica.telesalud.tph.service.PatientService;
import com.artica.telesalud.tph.service.PersonService;
import com.artica.telesalud.tph.service.ResponsableAtencionService;
import com.artica.telesalud.tph.service.TripulacionService;
import com.ibm.icu.util.StringTokenizer;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class PrintHCPH {

	private Font fontTitulo, fontTextoNegrita, fontTextoNormal, fontHeader;
	private OutputStream file = null;
	private FieldsNameHCP fieldsName;
	private LesionadoService lesionadoService;
	private ConceptService conceptService;
	private PatientService patientService;
	private TripulacionService tripulacionService;
	private LocationService locationService;
	private ResponsableAtencionService responsableAtencionService;
	private ObsService obsService;
	private HospitalService hospitalService;
	private EncounterService encounterService;
	private PersonService personService;
	private Integer paddingTables;
	private ProcesarImagen procesarImagen;
	private UserDAO userDao;
	private PersonDAO personDAO;
	private PDFUtil pdfUtil;
	private String urlBody = CONFIGURATION_RESOURCES.getString("body.image.file.path");
	public static final ResourceBundle CONFIGURATION_RESOURCES = ResourceBundle
			.getBundle("com.artica.telesalud.tph.server.util.PrintHCPH");
	public PrintHCPH() {
		pdfUtil = new PDFUtil();
		fontTitulo = pdfUtil.getFontTitle();
		fontTextoNegrita = pdfUtil.getFontTextBold();
		fontTextoNormal = pdfUtil.getFontTextNormal();
		fontHeader = pdfUtil.getFontHeader();
		fieldsName = new FieldsNameHCPImpl();
		paddingTables = pdfUtil.getPaddingTables();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				"hibernate-riesgo.cfg.xml");
		conceptService = new ConceptService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		lesionadoService = new LesionadoService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		patientService = new PatientService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		tripulacionService = new TripulacionService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		locationService = new LocationService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		obsService = new ObsService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		responsableAtencionService = new ResponsableAtencionService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		hospitalService = new HospitalService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		encounterService = new EncounterService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		personService = new PersonService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		userDao = DAOFactoryInstantiator
				.instantiateDaoFactory(
						PersonaDAOFactory.class,
						"com.artica.telesalud.persona.dao.hibernate.HibernatePersonaDAOFactory",
						params).getUserDao();
		personDAO = DAOFactoryInstantiator
				.instantiateDaoFactory(
						TPHDAOFactory.class,
						"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
						params).getPersonDAO();
		procesarImagen = new ProcesarImagen();
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer lesionadoId = Integer.parseInt(request
					.getParameter("number"));
			LesionadoCompletoData lesionadoCompletoData = obtenerInformacionCompletaLesionado(lesionadoId);
			// en caso de llegar un objeto nulo no se ejecuta el reporte
			file = response.getOutputStream();
			response.setContentType("application/pdf");

			response.setHeader("Content-Disposition",
					"attachment; filename=\"HCPH"
							+ lesionadoCompletoData.getPacienteCompletoData()
									.getPacienteData().getIdentificacon()
							+ ".pdf\"");
			response.setHeader("Cache-Control", "private");
			response.setHeader("Pragma", "cache");
			response.setHeader("Expires", "0");
			// agregar en el segundo parametro un listado de municipios
			createHCPHPrintPDF(lesionadoCompletoData,
					obtenerMunicipios(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getDepartamento()),
					obtenerSignosVitales(lesionadoId),
					obtenerNotasAclaratorias(lesionadoId),
					obtenerMunicipios(lesionadoCompletoData
							.getInformacionEventoData().getEvento()
							.getDepartamento()), lesionadoId);
			file.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public byte[] createPdfFile(Integer lesionadoId, String filePath)
			throws IOException {
		LesionadoCompletoData lesionadoCompletoData = obtenerInformacionCompletaLesionado(lesionadoId);
		File archive = new File(filePath);
		archive.createNewFile();
		file = new FileOutputStream(archive);
		createHCPHPrintPDF(lesionadoCompletoData,
				obtenerMunicipios(lesionadoCompletoData
						.getPacienteCompletoData().getPacienteData()
						.getDepartamento()), obtenerSignosVitales(lesionadoId),
				obtenerNotasAclaratorias(lesionadoId),
				obtenerMunicipios(lesionadoCompletoData
						.getInformacionEventoData().getEvento()
						.getDepartamento()), lesionadoId);
		FileInputStream fis = null;
		byte[] bFile = new byte[(int) archive.length()];
		// convert file into array of bytes
		fis = new FileInputStream(archive);
		fis.read(bFile);
		fis.close();
		file.close();
		return bFile;
	}

	/**
	 * Este m�todo crea el reporte
	 */
	private void createHCPHPrintPDF(
			LesionadoCompletoData lesionadoCompletoData,
			ArrayList<ValuedItem<Integer, String>> municipios,
			ArrayList<SignosVitalesData> signosVitales,
			ArrayList<NotaAclaratoria> notasAclaratorias,
			ArrayList<ValuedItem<Integer, String>> municipiosEvt, Integer id) {
		if (lesionadoCompletoData == null)
			return;
		Document document = new Document(PageSize.LETTER, 40, 40, 75, 50);
		com.artica.telesalud.tph.lightweightmodel.Tripulacion trip = obtenerTripulacion(lesionadoCompletoData
				.getInformacionEventoData().getLesionado().getTripulacion());
		try {
			PdfWriter.getInstance(document, file).setPageEvent(
					new PdfPageEventHelper());

			document.open();
			document.setPageCount(0);
			document.newPage();
			//
			// agrego el nombre del paciente
			String[] namePatient = {
					lesionadoCompletoData.getPacienteCompletoData()
							.getPacienteData().getPrimerNombre(),
					lesionadoCompletoData.getPacienteCompletoData()
							.getPacienteData().getSegundoNombre(),
					lesionadoCompletoData.getPacienteCompletoData()
							.getPacienteData().getPrimerApellido(),
					lesionadoCompletoData.getPacienteCompletoData()
							.getPacienteData().getSegundoApellido() };
			document.add(new Paragraph(fieldsName.rptHeader() + " "
					+ pdfUtil.nullToEmpty(id + ""), fontHeader));
			document.add(new Paragraph(fieldsName.rptFechaCreacion()
					+ " "
					+ pdfUtil.nullToEmpty(pdfUtil
							.formatDate(lesionadoCompletoData
									.getInformacionEventoData().getLesionado()
									.getFechaCreacion())), fontHeader));
			document.add(Chunk.NEWLINE);
			// carga la informaci�n del paciente
			/******* Inicia informaci�n general *************************/
			document.add(new Paragraph(fieldsName.rptInfoPacienteTitle(),
					fontTitulo));
			document.add(Chunk.NEWLINE);
			Table tableInfoPaciente = pdfUtil.createTable(4, 0, Color.WHITE,
					new int[] { 30, 30, 30, 30 }, 0);
			// // Titulo para la informaci�n del paciente
			// tableInfoPaciente.addCell(addCell(
			// new Paragraph(fieldsName.rptInfoPacienteTitle(),
			// fontTextoNegrita), 4));
			// agrego el label del nombre del paciente
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptNombrePaciente(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.concatString(namePatient), fontTextoNormal)));
			// agrego el encabezado contacto de emergencia
			tableInfoPaciente.addCell(pdfUtil.addCell(
					new Paragraph(fieldsName.rptContactoEmergenciaTitle(),
							fontTextoNegrita), 2));
			// Agrego la identificaci�n del paciente
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptIdentificacionPaciente(), fontTextoNegrita)));
			String[] identificacion = {
					pdfUtil.getStringToListValue(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getTipoIdentificacion(), lesionadoCompletoData
							.getPacienteCompletoData().getTiposIdentificacion()),
					lesionadoCompletoData.getPacienteCompletoData()
							.getPacienteData().getIdentificacon() };
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.concatString(identificacion), fontTextoNormal)));
			// Agrego el nombre del contacto de emergencia
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptNombreContacto(), fontTextoNegrita)));
			String[] nombreContacto = {
					lesionadoCompletoData.getPacienteCompletoData()
							.getPacienteData().getNombreContacto(),
					lesionadoCompletoData.getPacienteCompletoData()
							.getPacienteData().getApellidoContacto() };
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.concatString(nombreContacto), fontTextoNormal)));
			// Agrego el genero del paciente
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptGenero(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getGenero()), fontTextoNormal)));
			// Agrego el n�mero telefonico del contacto
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTelefono(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getTelefonoContacto1()), fontTextoNormal)));
			// Agrego el estado civil del paciente
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptEstadoCivil(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.getStringToListValue(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getEstadoCivil(), lesionadoCompletoData
							.getPacienteCompletoData().getEstadosCiviles()),
					fontTextoNormal)));
			// Agrego el n�mero telefonico 2 del contacto de emergencia
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTelefono2(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getTelefonoContacto2()), fontTextoNormal)));
			// Agrego la fecha de nacimiento y la edad del paciente
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptFechaNacimiento(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.formatDateOutHour(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getFechaNacimiento()), fontTextoNormal)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph("",
					fontTextoNegrita), 2));
			// Agrego la edad
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptEdad(), fontTextoNegrita)));

			tableInfoPaciente
					.addCell(pdfUtil
							.addCell(new Paragraph(
									pdfUtil.nullToEmpty(
											lesionadoCompletoData
													.getInformacionEventoData()
													.getEvento().getFechaHora() != null
													&& lesionadoCompletoData
															.getPacienteCompletoData()
															.getPacienteData()
															.getFechaNacimiento() != null ? pdfUtil
													.difAnios(
															lesionadoCompletoData
																	.getPacienteCompletoData()
																	.getPacienteData()
																	.getFechaNacimiento(),
															lesionadoCompletoData
																	.getInformacionEventoData()
																	.getEvento()
																	.getFechaHora())
													+ ""
													: "").equals("") ? ""
											: pdfUtil.nullToEmpty(pdfUtil
													.difAnios(
															lesionadoCompletoData
																	.getPacienteCompletoData()
																	.getPacienteData()
																	.getFechaNacimiento(),
															lesionadoCompletoData
																	.getInformacionEventoData()
																	.getEvento()
																	.getFechaHora())
													+ "")
													+ fieldsName.rptAnios(),
									fontTextoNormal)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph("",
					fontTextoNegrita), 2));
			// Agrego la ocupaci�n del paciente
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptOcupacion(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getOcupacion()), fontTextoNormal)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph("",
					fontTextoNormal), 2));
			// Direcci�n de residencia
			String[] direccionResidencia = {
					pdfUtil.nullToEmpty(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getDireccionDomicilio()),
					lesionadoCompletoData.getPacienteCompletoData()
							.getPacienteData().getDireccionDomicilio() == null ? ""
							: ".",
					pdfUtil.getStringToListValue(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getCiudad(), municipios),
					lesionadoCompletoData.getPacienteCompletoData()
							.getPacienteData().getCiudad() == null ? "" : ".",
					pdfUtil.getStringToListValue(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getDepartamento(), lesionadoCompletoData
							.getPacienteCompletoData().getDepartamentos()),
					lesionadoCompletoData.getPacienteCompletoData()
							.getPacienteData().getDepartamento() == null ? ""
							: ".",
					pdfUtil.nullToEmpty(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getZona()) };
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptDireccionResidencia(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(pdfUtil.concatString(direccionResidencia)),
					fontTextoNormal)));
			// T�tulo informaci�n acompa�ante
			tableInfoPaciente.addCell(pdfUtil.addCell(
					new Paragraph(fieldsName.rptInfoAcomp(), fontTextoNegrita),
					2));
			// Tel�fono residencia
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTelefonoResidencia(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getTelefonoDomicilio()), fontTextoNormal)));
			// nombre acompa�ante
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptNombreAcompanante(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getNombreAcompanante()), fontTextoNormal)));
			// Aseguradora
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptAseguradora(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.getStringToListValue(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getAseguradora(), lesionadoCompletoData
							.getPacienteCompletoData().getAseguradoras()),
					fontTextoNormal)));
			// Parentesco acompa�ante
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptParentesco(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getParentescoAcompanante()), fontTextoNormal)));
			// EPS
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptEPS(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.getStringToListValue(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getEps(), lesionadoCompletoData
							.getPacienteCompletoData().getEpss()),
					fontTextoNormal)));
			// Telefono 1 acompa�ante
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTelefono(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getTelefonoAcompanante()), fontTextoNormal)));
			// Plan beneficios
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptPlanBeneficios(), fontTextoNegrita)));
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.getStringToListValue(lesionadoCompletoData
							.getPacienteCompletoData().getPacienteData()
							.getPlanBeneficios(), lesionadoCompletoData
							.getPacienteCompletoData().getPlanesBeneficios()),
					fontTextoNormal)));
			// espacio
			tableInfoPaciente.addCell(pdfUtil.addCell(new Paragraph("",
					fontTextoNegrita), 2));
			document.add(tableInfoPaciente);
			document.add(new Paragraph());
			/******* Finaliza informaci�n general *************************/
			/***** Informaci�n de la atenci�n **************************/
			document.add(new Paragraph("", fontTitulo));
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph(fieldsName.rptTitleInfoAtencion(),
					fontTitulo));
			document.add(Chunk.NEWLINE);
			// resultadop, entidad recepción y triage
			if (lesionadoCompletoData.getEvaluacion() != null) {
				document.add(pdfUtil.adicionarTexto(fieldsName
						.rptTitleEmergenciaMedica(),
						pushEmergenciaMedica(lesionadoCompletoData
								.getEvaluacion())));
			}
			document.add(pdfUtil.adicionarTexto(fieldsName.rptTitleResultado(),
					pushResultado(lesionadoCompletoData
							.getInformacionEventoData().getLesionado())));
			if (lesionadoCompletoData.getInformacionEventoData().getLesionado()
					.getEntidadRecepcion() != null) {
				String[] recepcion = {
						pdfUtil.getStringToListValue(lesionadoCompletoData
								.getInformacionEventoData().getLesionado()
								.getEntidadRecepcion(), lesionadoCompletoData
								.getResultados().getEntidadesRecepcion())
								+ ". ",
						lesionadoCompletoData.getInformacionEventoData()
								.getLesionado().getRecibidoPor() == null ? ""
								: lesionadoCompletoData
										.getInformacionEventoData()
										.getLesionado().getRecibidoPor()
										+ ". ",
						lesionadoCompletoData.getInformacionEventoData()
								.getLesionado().getRegistroRecibe() == null ? ""
								: lesionadoCompletoData
										.getInformacionEventoData()
										.getLesionado().getRegistroRecibe() };
				document.add(pdfUtil.adicionarTexto(
						fieldsName.rptResultadoEntidadRecepcion(),
						pdfUtil.concatString(recepcion)));
			}
			// prioridad en el triage
			if (lesionadoCompletoData.getEvaluacion().getPrioridadTriage() != null) {
				document.add(pdfUtil.adicionarTexto(fieldsName
						.rptPrioridadTriage(),
						getStringTriage(lesionadoCompletoData.getEvaluacion()
								.getPrioridadTriage())));
			}
			document.add(new Paragraph());
			// Tabla y titulo de hallazgos y procedimientos. Tabla para signos
			// vitales
			Table tableHallImpr = pdfUtil.createTable(2, 0, Color.WHITE,
					new int[] { 60, 60 }, paddingTables);
			// Titulo hallazgos clinicos e impresi�n diagnostica
			tableHallImpr.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptHallazgosClinicos(), fontTextoNegrita)));
			tableHallImpr.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptImpresionDX(), fontTextoNegrita)));
			// valores hallazgos clinicos e impresi�n diagnostica
			tableHallImpr.addCell(pdfUtil.addCell(new Paragraph(
					lesionadoCompletoData.getResultados().getResultado()
							.getHallazgosClinicos(), fontTextoNormal)));
			tableHallImpr.addCell(pdfUtil.addCell(new Paragraph(
					lesionadoCompletoData.getResultados().getResultado()
							.getImpresionDiagnostica(), fontTextoNormal)));
			document.add(tableHallImpr);
			document.add(Chunk.NEWLINE);
			if (lesionadoCompletoData.getHallazgos().size() > 0) {
				// Pinta el muñeco
				Image image = Image
						.getInstance(procesarImagen
								.processImage(
										lesionadoCompletoData.getHallazgos(),
										urlBody)
								.getAbsolutePath());
				image.setAlignment(Image.MIDDLE);
				document.newPage();
				document.add(image);
				document.add(new Paragraph());
				document.add(Chunk.NEWLINE);
				document.add(new Paragraph(fieldsName.rptHallazgosProc(),
						fontTitulo));
				Table hallProc = pdfUtil.createTable(3, 0, Color.BLACK,
						new int[] { 10, 45, 45 }, paddingTables);
				hallProc.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
						.rptTitleZona(), fontTextoNegrita)));
				hallProc.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
						.rptTitleHallazgos(), fontTextoNegrita)));
				hallProc.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
						.rptTitleProcedimientos(), fontTextoNegrita)));
				// carga los hallazgos
				for (HallazgoData hallazgo : lesionadoCompletoData
						.getHallazgos()) {
					hallProc.addCell(new Paragraph(pdfUtil.nullToEmpty(hallazgo
							.getOrden() + ""), fontTextoNormal));
					hallProc.addCell(new Paragraph(getLesiones(
							hallazgo.getLesiones(), ", "), fontTextoNormal));
					hallProc.addCell(new Paragraph(pdfUtil
							.getStringListValuedItem(
									hallazgo.getProcedimientos(), ", "),
							fontTextoNormal));
				}
				document.add(hallProc);
			}
			// signos vitales
			if (signosVitales.size() > 0) {
				document.add(new Paragraph(fieldsName
						.rptEvolucionSignosVitales(), fontTitulo));
				Table evSignosVitales = pdfUtil.createTable(5, 0, Color.BLACK,
						new int[] { 20, 20, 20, 20, 20 }, paddingTables);
				evSignosVitales.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleFechaSV(), fontTextoNegrita)));
				evSignosVitales.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleFC(), fontTextoNegrita)));
				evSignosVitales.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleFR(), fontTextoNegrita)));
				evSignosVitales.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitlePA(), fontTextoNegrita)));
				evSignosVitales.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleDM(), fontTextoNegrita)));
				// carga evolucion Signos vitales
				for (SignosVitalesData signoVital : signosVitales) {
					evSignosVitales
							.addCell(new Paragraph(pdfUtil
									.formatDate(signoVital.getFecha()),
									fontTextoNormal));
					evSignosVitales.addCell(new Paragraph(pdfUtil
							.nullToEmpty(pdfUtil.doubleToString(signoVital
									.getPulso())), fontTextoNormal));
					evSignosVitales.addCell(new Paragraph(pdfUtil
							.nullToEmpty(pdfUtil.doubleToString(signoVital
									.getRespiracion())), fontTextoNormal));
					if (signoVital.getPaDiastolica() != null) {
						evSignosVitales.addCell(new Paragraph(pdfUtil
								.doubleToString(signoVital.getPaSistolica())
								+ "/"
								+ pdfUtil.doubleToString(signoVital
										.getPaDiastolica()), fontTextoNormal));
					} else {
						evSignosVitales.addCell(new Paragraph("",
								fontTextoNormal));
					}
					evSignosVitales.addCell(new Paragraph(pdfUtil
							.nullToEmpty(pdfUtil.doubleToString(signoVital
									.getGlicemia())), fontTextoNormal));
				}
				document.add(evSignosVitales);
			}
			document.add(new Paragraph());
			document.add(Chunk.NEWLINE);
			// Responsables de la atenci�n
			if (lesionadoCompletoData.getResultados().getResponsablesAtencion()
					.size() > 0) {
				document.add(new Paragraph(fieldsName.rptTitleResponsable(),
						fontTitulo));
				Table tableResponsables = pdfUtil.createTable(5, 1,
						Color.BLACK, new int[] { 13, 13, 13, 13, 8 },
						paddingTables);
				tableResponsables.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleResponsableRegistro(),
						fontTextoNegrita)));
				tableResponsables.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleResponsableNombres(),
						fontTextoNegrita)));
				tableResponsables.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleResponsableApellidos(),
						fontTextoNegrita)));
				tableResponsables.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleResponsableControlAph(),
						fontTextoNegrita)));
				tableResponsables.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleResponsableExterno(),
						fontTextoNegrita)));
				for (ResponsableAtencion responsableAtencion : lesionadoCompletoData
						.getResultados().getResponsablesAtencion()) {
					tableResponsables.addCell(new Paragraph(pdfUtil
							.nullToEmpty(responsableAtencion.getRegistro()),
							fontTextoNormal));
					tableResponsables.addCell(new Paragraph(pdfUtil
							.nullToEmpty(responsableAtencion.getNombres()),
							fontTextoNormal));
					tableResponsables.addCell(new Paragraph(pdfUtil
							.nullToEmpty(responsableAtencion.getApellidos()),
							fontTextoNormal));

					tableResponsables.addCell(new Paragraph(responsableAtencion
							.getControlAPH(), fontTextoNormal));
					if (responsableAtencion.getEsExterno() != 0)
						tableResponsables.addCell(new Paragraph(pdfUtil
								.nullToEmpty("No"), fontTextoNormal));
					else
						tableResponsables.addCell(new Paragraph(pdfUtil
								.nullToEmpty("Si"), fontTextoNormal));

				}
				document.add(tableResponsables);
				document.add(new Paragraph());
			}
			// evaluci�n y procedimientos
			document.add(Chunk.NEWLINE);
			Table tableevoProc = pdfUtil.createTable(4, 0, Color.WHITE,
					new int[] { 6, 18, 18, 18 }, paddingTables);
			tableevoProc.addCell(pdfUtil.addCell(new Paragraph("",
					fontTextoNegrita)));
			tableevoProc.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
					.rptTitleValorPrimaria(), fontTextoNegrita)));
			tableevoProc.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
					.rptTitleValorSecundaria(), fontTextoNegrita)));
			tableevoProc.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
					.rptTitleProcs(), fontTextoNegrita)));
			// A
			tableevoProc.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
					.rptTitleA(), fontTextoNegrita)));
			tableevoProc
					.insertTable(createTablePermeabilidad(lesionadoCompletoData));
			tableevoProc.addCell(pdfUtil.addCell(new Paragraph("",
					fontTextoNegrita)));
			tableevoProc.insertTable(createTableProcA(lesionadoCompletoData));
			// B
			tableevoProc.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
					.rptTitleB(), fontTextoNegrita)));
			tableevoProc
					.insertTable(createTableRespiracion(lesionadoCompletoData));
			tableevoProc.addCell(pdfUtil.addCell(new Paragraph("",
					fontTextoNegrita)));
			tableevoProc.insertTable(createTableProcB(lesionadoCompletoData));
			// C
			tableevoProc.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
					.rptTitleC(), fontTextoNegrita)));
			tableevoProc.insertTable(createTablePulso(lesionadoCompletoData));
			tableevoProc.addCell(pdfUtil.addCell(new Paragraph("",
					fontTextoNegrita)));
			tableevoProc.insertTable(createTableProcC(lesionadoCompletoData));
			// D
			tableevoProc.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
					.rptTitleD(), fontTextoNegrita)));
			tableevoProc
					.insertTable(createTableNivelRespuesta(lesionadoCompletoData));
			tableevoProc.insertTable(createTablePupilas(lesionadoCompletoData));
			tableevoProc.insertTable(createTableProcD(lesionadoCompletoData));
			// E
			tableevoProc.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
					.rptTitleE(), fontTextoNegrita)));
			tableevoProc.insertTable(createTablePiel(lesionadoCompletoData));
			tableevoProc.insertTable(createTableGalsgow(lesionadoCompletoData));
			tableevoProc.insertTable(createTableProcE(lesionadoCompletoData));
			// Otros
			if (lesionadoCompletoData.getProcedimientos()
					.getOtrosProcedimientos() != null) {
				tableevoProc.addCell(pdfUtil.addCell(new Paragraph("",
						fontTextoNegrita), 3));
				tableevoProc.addCell(pdfUtil.adicionarTexto(fieldsName
						.rptOtros(), lesionadoCompletoData.getProcedimientos()
						.getOtrosProcedimientos()));
			}
			document.add(tableevoProc);
			document.add(new Paragraph());
			document.add(Chunk.NEWLINE);
			// Antecedentes
			if (lesionadoCompletoData.getAntecedenteData().getAntecedentes()
					.size() > 0) {
				document.add(new Paragraph(fieldsName.rptTitleAntecedentes(),
						fontTitulo));
				Table tableAntecedentes = pdfUtil.createTable(3, 1,
						Color.BLACK, new int[] { 15, 25, 60 }, paddingTables);
				tableAntecedentes
						.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
								.rptTitleAntecedenteAnio(), fontTextoNegrita)));
				tableAntecedentes
						.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
								.rptTitleAntecedenteTipo(), fontTextoNegrita)));
				tableAntecedentes.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleAntecedenteObservacion(),
						fontTextoNegrita)));
				for (Antecedente antecedente : lesionadoCompletoData
						.getAntecedenteData().getAntecedentes()) {
					tableAntecedentes.addCell(new Paragraph(pdfUtil
							.nullToEmpty(antecedente.getAnio() + ""),
							fontTextoNormal));
					tableAntecedentes.addCell(new Paragraph(pdfUtil
							.nullToEmpty(pdfUtil.getStringToListValue(
									antecedente.getIdTipoAntecedente(),
									lesionadoCompletoData.getAntecedenteData()
											.getTiposAntecedentes())),
							fontTextoNormal));
					tableAntecedentes.addCell(new Paragraph(pdfUtil
							.nullToEmpty(antecedente.getObservacion()),
							fontTextoNormal));
				}
				document.add(tableAntecedentes);
				document.add(new Paragraph());
				document.add(Chunk.NEWLINE);
			}
			// Notas aclaratorias
			if (notasAclaratorias.size() > 0) {
				document.add(new Paragraph("Notas aclaratorias", fontTitulo));
				Table tableNotasAcla = pdfUtil.createTable(3, 0, Color.BLACK,
						new int[] { 15, 15, 30 }, paddingTables);
				tableNotasAcla.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleFecha(), fontTextoNegrita)));
				tableNotasAcla
						.addCell(pdfUtil.addCellHeader(new Paragraph(fieldsName
								.rptTitleResponsableAval(), fontTextoNegrita)));
				tableNotasAcla.addCell(pdfUtil.addCellHeader(new Paragraph(
						fieldsName.rptTitleNota(), fontTextoNegrita)));
				for (NotaAclaratoria notaAclaratoria : notasAclaratorias) {
					tableNotasAcla.addCell(new Paragraph(pdfUtil
							.nullToEmpty(pdfUtil.formatDate(notaAclaratoria
									.getFechaCreacion())), fontTextoNormal));
					tableNotasAcla.addCell(new Paragraph(pdfUtil
							.nullToEmpty(notaAclaratoria.getNombreCreador()),
							fontTextoNormal));
					tableNotasAcla.addCell(new Paragraph(pdfUtil
							.nullToEmpty(notaAclaratoria.getNota()),
							fontTextoNormal));
				}
				document.add(tableNotasAcla);
				document.add(new Paragraph());
				document.add(Chunk.NEWLINE);
			}
			// Teleasistencia
			// Titulo teleasistencia
			if (lesionadoCompletoData.getTeleasistencia().getTeleasistencias()
					.size() > 0) {
				document.add(new Paragraph(fieldsName.rptTitleTeleasistencia(),
						fontTitulo));
				document.add(Chunk.NEWLINE);
				Table tableNotasEvo;
				for (TeleasistenciaData teleasistenciaData : lesionadoCompletoData
						.getTeleasistencia().getTeleasistencias()) {
					// Solicitud de teleasistencia
					document.add(pdfUtil.adicionarTexto(fieldsName
							.rptTitleFecha(), pdfUtil.nullToEmpty(pdfUtil
							.formatDate(teleasistenciaData.getFecha()))));
					document.add(pdfUtil.adicionarTexto(fieldsName
							.rptTitleSolicitante(), pdfUtil
							.nullToEmpty(teleasistenciaData
									.getMedicoSolicitante().getLabel())));
					document.add(pdfUtil.adicionarTexto(fieldsName
							.rptTitleMotivoSolicitud(), pdfUtil
							.nullToEmpty(teleasistenciaData
									.getMotivoSolicitud())));
					document.add(pdfUtil.adicionarTexto(fieldsName
							.rptTitleDetalle(), pdfUtil
							.nullToEmpty(teleasistenciaData.getDetalles())));
					document.add(pdfUtil.adicionarTexto(fieldsName
							.rptTitleTeleasistencia(), pdfUtil
							.nullToEmpty(pdfUtil.getStringToListValue(
									teleasistenciaData.getMedio(),
									lesionadoCompletoData.getTeleasistencia()
											.getMedios()))));
					document.add(new Paragraph("", fontTextoNormal));
					// Respuestas
					if (teleasistenciaData.getNotasEvolucion().size() > 0) {
						tableNotasEvo = pdfUtil
								.createTable(5, 1, Color.BLACK, new int[] { 10,
										10, 10, 10, 20 }, paddingTables);
						tableNotasEvo.addCell(pdfUtil
								.addCellHeader(new Paragraph(fieldsName
										.rptTitleFecha(), fontTextoNegrita)));
						tableNotasEvo.addCell(pdfUtil
								.addCellHeader(new Paragraph(fieldsName
										.rptTitleMedicoTratante(),
										fontTextoNegrita)));
						tableNotasEvo.addCell(pdfUtil
								.addCellHeader(new Paragraph(fieldsName
										.rptTitleDxPpal(), fontTextoNegrita)));
						tableNotasEvo.addCell(pdfUtil
								.addCellHeader(new Paragraph(fieldsName
										.rptTitleDxSecundarios(),
										fontTextoNegrita)));
						tableNotasEvo.addCell(pdfUtil
								.addCellHeader(new Paragraph(fieldsName
										.rptTitleRecomendacion(),
										fontTextoNegrita)));
						for (NotaEvolucion notaEvolucion : teleasistenciaData
								.getNotasEvolucion()) {
							tableNotasEvo.addCell(new Paragraph(pdfUtil
									.formatDate(notaEvolucion.getFecha()),
									fontTextoNormal));
							tableNotasEvo
									.addCell(new Paragraph(
											notaEvolucion.getMedicoTratante() != null ? notaEvolucion
													.getMedicoTratante()
													.getLabel() : "",
											fontTextoNormal));
							tableNotasEvo
									.addCell(new Paragraph(
											pdfUtil.nullToEmpty(notaEvolucion
													.getDxPrincipal() != null ? notaEvolucion
													.getDxPrincipal()
													.getLabel() : ""),
											fontTextoNormal));
							tableNotasEvo.addCell(new Paragraph(pdfUtil
									.getStringListValuedItem(
											notaEvolucion.getDxSecundarios(),
											"\n"), fontTextoNormal));
							tableNotasEvo.addCell(new Paragraph(pdfUtil
									.nullToEmpty(notaEvolucion
											.getRecomendaciones()),
									fontTextoNormal));
						}
						document.add(tableNotasEvo);
						document.add(new Paragraph());
						document.add(Chunk.NEWLINE);
					}
				}
			}

			/********** fin informaci�n de la atenci�n *********************/
			/** Informaci�n del evento ***************/
			document.add(new Paragraph(fieldsName.rptTitleInfoEvento(),
					fontTitulo));
			document.add(Chunk.NEWLINE);
			Table tableInfoEvt = pdfUtil.createTable(4, 0, Color.WHITE,
					new int[] { 10, 20, 10, 20 }, 1);
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTitleEvtDireccion(), fontTextoNegrita)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getInformacionEventoData().getEvento()
							.getDireccion()), fontTextoNormal)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTitleEvtNumeroCaso(), fontTextoNegrita)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getInformacionEventoData().getEvento().getCaso()),
					fontTextoNormal)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTitleEvtDepto(), fontTextoNegrita)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(pdfUtil.getStringToListValue(
							lesionadoCompletoData.getInformacionEventoData()
									.getEvento().getDepartamento(),
							lesionadoCompletoData.getPacienteCompletoData()
									.getDepartamentos())), fontTextoNormal)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTitleEvtPlaca(), fontTextoNegrita)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(pdfUtil.getStringToListValue(
							lesionadoCompletoData.getInformacionEventoData()
									.getLesionado().getTripulacion(),
							lesionadoCompletoData.getInformacionEventoData()
									.getPlacas())), fontTextoNormal)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTitleEvtMunicipio(), fontTextoNegrita)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(pdfUtil.getStringToListValue(
							lesionadoCompletoData.getInformacionEventoData()
									.getEvento().getCiudad(), municipiosEvt)),
					fontTextoNormal)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTitleEvtHoraDespacho(), fontTextoNegrita)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(pdfUtil.formatDate(lesionadoCompletoData
							.getInformacionEventoData().getFechaDespacho())),
					fontTextoNormal)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTitleEvtZona(), fontTextoNegrita)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getInformacionEventoData().getEvento().getZona()),
					fontTextoNormal)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTitleEvtHoraLLegada(), fontTextoNegrita)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(pdfUtil.formatDate(lesionadoCompletoData
							.getInformacionEventoData().getFechaLLegada())),
					fontTextoNormal)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTitleEvtCausaOrigen(), fontTextoNegrita)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(lesionadoCompletoData
							.getInformacionEventoData().getEvento()
							.getNombreCausa()), fontTextoNormal)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTitleEvtHoraRegreso(), fontTextoNegrita)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(pdfUtil.formatDate(trip.getHoraRegreso())),
					fontTextoNormal)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(fieldsName
					.rptTitleEvtHoraLLamada(), fontTextoNegrita)));
			tableInfoEvt.addCell(pdfUtil.addCell(new Paragraph(pdfUtil
					.nullToEmpty(pdfUtil.formatDate(lesionadoCompletoData
							.getInformacionEventoData().getEvento()
							.getFechaHora())), fontTextoNormal)));
			document.add(tableInfoEvt);
			document.add(new Paragraph());
			/** Fin Informaci�n del evento ***************/
			/************* Aval Historia **************/
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph(fieldsName.rptTitleAval(), fontTitulo));
			document.add(Chunk.NEWLINE);
			document.add(pdfUtil.adicionarTexto(
					fieldsName.rptTitleResponsableAval(),
					lesionadoCompletoData.getDatosCierre().getUsuarioCierra() != null ? lesionadoCompletoData
							.getDatosCierre().getUsuarioCierra().getLabel()
							: ""));
			document.add(pdfUtil.adicionarTexto(
					fieldsName.rptTitleResponsableRegistro(), ""));// falta el
																	// n�merod
																	// e
																	// registro
			if (lesionadoCompletoData.getDatosCierre().getTipoCierre() != null) {
				if (lesionadoCompletoData.getDatosCierre().getTipoCierre()
						.equals(CierreAtencion.TIPO_CIERRE_ANULACION)) {
					document.add(pdfUtil.adicionarTexto(
							fieldsName.rptTitleResultadoCierre(),
							fieldsName.rptTitleAnulada()));
				} else {
					if (lesionadoCompletoData.getDatosCierre().getTipoCierre()
							.equals(CierreAtencion.TIPO_CIERRE_APROBACION)) {
						document.add(pdfUtil.adicionarTexto(
								fieldsName.rptTitleResultadoCierre(),
								fieldsName.rptTitleAvala()));
					} else {
						if (lesionadoCompletoData
								.getDatosCierre()
								.getTipoCierre()
								.equals(CierreAtencion.TIPO_CIERRE_NOAPROBACION)) {
							document.add(pdfUtil.adicionarTexto(
									fieldsName.rptTitleResultadoCierre(),
									fieldsName.rptTitleNoAvala()));
						}
					}
				}
				if (lesionadoCompletoData.getDatosCierre().getNota() != null) {
					document.add(pdfUtil.adicionarTexto(
							fieldsName.rptTitleNota(), lesionadoCompletoData
									.getDatosCierre().getNota().trim()));
				}

				ArchivoData x = getArchivoDataFirmaDigital(lesionadoCompletoData
						.getDatosCierre().getUsuarioCierra().getCode());
				if (x != null) {
					Image firma = Image.getInstance("/opt/tomcat7/files/"
							+ x.getNombreOriginalArchivo());
					firma.setAlignment(Image.MIDDLE);
					document.newPage();
					document.add(firma);
				}
			}

			/************* Fin Aval Historia **************/
			// cierra el documento
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	public String getUrlBody() {
		return urlBody;
	}

	public void setUrlBody(String urlBody) {
		this.urlBody = urlBody;
	}

	/**
	 * 
	 * @param prioridad
	 * @return
	 */
	public String getStringTriage(Integer prioridad) {
		if (prioridad == null)
			return "";
		if (prioridad.equals(EvaluacionData.PRIORIDAD_TRIAGE_ROJO))
			return fieldsName.rptPrioridadTriageRojo();
		if (prioridad.equals(EvaluacionData.PRIORIDAD_TRIAGE_AMARILLO))
			return fieldsName.rptPrioridadTriageAmarillo();
		if (prioridad.equals(EvaluacionData.PRIORIDAD_TRIAGE_BLANCO))
			return fieldsName.rptPrioridadTriageBlanco();
		if (prioridad.equals(EvaluacionData.PRIORIDAD_TRIAGE_NEGRO))
			return fieldsName.rptPrioridadTriageNegro();
		if (prioridad.equals(EvaluacionData.PRIORIDAD_TRIAGE_VERDE))
			return fieldsName.rptPrioridadTriageVerde();
		return "";
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTablePermeabilidad(
			LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 0, Color.BLACK, new int[] { 18 },
				0);
		// table.addCell(new
		// Paragraph(fieldsName.rptTitlePermeabilidadViaAerea(),
		// fontTextoNegrita));
		StringBuilder sb = new StringBuilder();
		if (lesionadoCompletoData.getEvaluacion().getPermeavilidadViaAerea() != null) {
			if (lesionadoCompletoData.getEvaluacion()
					.getPermeavilidadViaAerea().equals(EvaluacionData.VIA_AEREA_PERMEABLE))
				sb.append(fieldsName.rptPermeable());
			else {
				if (lesionadoCompletoData.getEvaluacion()
						.getPermeavilidadViaAerea().equals(EvaluacionData.VIA_AEREA_OBSTRUIDA)) {
					sb.append(fieldsName.rptObstruida());
					sb.append("\n"
							+ fieldsName.rptCausaObstruccion()
							+ pdfUtil.nullToEmpty(lesionadoCompletoData
									.getEvaluacion().getCausaObstruccionVia()));
				}
			}
		}
		table.addCell(pdfUtil.adicionarTexto(
				fieldsName.rptTitlePermeabilidadViaAerea(), sb.toString()));
		return table;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTableRespiracion(
			LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 0, Color.BLACK, new int[] { 18 },
				0);
		// table.addCell(new Paragraph(fieldsName.rptTitleRespiracion(),
		// fontTextoNegrita));
		StringBuilder sb = new StringBuilder();
		Integer respiracion = lesionadoCompletoData.getEvaluacion()
				.getRespiracion();
		if (respiracion != null) {
			if (respiracion.equals(EvaluacionData.RESPIRACION_AUSENTE)) {
				sb.append(fieldsName.rptAusente());
			} else {
				if (respiracion.equals(EvaluacionData.RESPIRACION_DIFICULTOSA)) {
					sb.append(fieldsName.rptDificultosa());
				} else {
					if (respiracion.equals(EvaluacionData.RESPIRACION_NORMAL)) {
						sb.append(fieldsName.rptNormal());
					} else {
						if (respiracion
								.equals(EvaluacionData.RESPIRACION_SUPERFICIAL)) {
							sb.append(fieldsName.rptSuperficial());
						}
					}
				}
			}
		}
		table.addCell(pdfUtil.adicionarTexto(fieldsName.rptTitleRespiracion(),
				sb.toString()));
		return table;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTablePulso(LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 0, Color.BLACK, new int[] { 18 },
				0);
		StringBuilder sb = new StringBuilder();
		Integer pulso = lesionadoCompletoData.getEvaluacion().getPulso();
		Integer fuerzaPulso = lesionadoCompletoData.getEvaluacion()
				.getFuerzaPulso();
		Integer ritmoPulso = lesionadoCompletoData.getEvaluacion()
				.getRitmoPulso();
		if (pulso != null) {
			if (pulso.equals(EvaluacionData.PULSO_PRESENTE)) {
				// fuerza del pulso
				if (fuerzaPulso != null) {
					if (fuerzaPulso.equals(EvaluacionData.PULSO_FUERTE)) {
						sb.append(fieldsName.rptFuerte() + "\n");
					} else {
						if (fuerzaPulso.equals(EvaluacionData.PULSO_DEBIL)) {
							sb.append(fieldsName.rptDebil() + "\n");
						}
					}
				}
				// ritmo del pulso
				if (ritmoPulso != null) {
					if (ritmoPulso.equals(EvaluacionData.PULSO_RITMICO)) {
						sb.append(fieldsName.rptRitmico() + "\n");
					} else {
						if (ritmoPulso.equals(EvaluacionData.PULSO_ARITMICO)) {
							sb.append(fieldsName.rptArritmico() + "\n");
						}
					}
				}
			} else {
				// pulso ausente
				if (pulso.equals(EvaluacionData.PULSO_AUSENTE)) {
					sb.append(fieldsName.rptAusente());
				}
			}
		}
		table.addCell(pdfUtil.adicionarTexto(fieldsName.rptTitlePulso(),
				sb.toString()));
		return table;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTableNivelRespuesta(
			LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 0, Color.BLACK, new int[] { 18 },
				0);
		StringBuilder sb = new StringBuilder();
		Integer nivelRespuesta = lesionadoCompletoData.getEvaluacion()
				.getNivelRespuesta();
		if (nivelRespuesta != null) {
			if (nivelRespuesta.equals(EvaluacionData.NIVEL_RESPUESTA_ALERTA)) {
				sb.append(fieldsName.rptAlerta());
			} else {
				if (nivelRespuesta.equals(EvaluacionData.NIVEL_RESPUESTA_DOLOR)) {
					sb.append(fieldsName.rptDolor());
				} else {
					if (nivelRespuesta
							.equals(EvaluacionData.NIVEL_RESPUESTA_LLAMADO)) {
						sb.append(fieldsName.rptLlamado());
					} else {
						if (nivelRespuesta
								.equals(EvaluacionData.NIVEL_RESPUESTA_SIN_RESPUESTA)) {
							sb.append(fieldsName.rptSinRespuesta());
						}
					}
				}
			}
		}
		table.addCell(pdfUtil.adicionarTexto(
				fieldsName.rptTitleNivelRespuesta(), sb.toString()));
		return table;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTablePiel(LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 0, Color.BLACK, new int[] { 18 },
				0);
		StringBuilder sb = new StringBuilder();
		if (lesionadoCompletoData.getEvaluacion().getPielNormal() != null
				&& lesionadoCompletoData.getEvaluacion().getPielNormal()) {
			sb.append(fieldsName.rptNormal() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPielPalida() != null
				&& lesionadoCompletoData.getEvaluacion().getPielPalida()) {
			sb.append(fieldsName.rptPalida() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPielCaliente() != null
				&& lesionadoCompletoData.getEvaluacion().getPielCaliente()) {
			sb.append(fieldsName.rptCaliente() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPielCianotica() != null
				&& lesionadoCompletoData.getEvaluacion().getPielCianotica()) {
			sb.append(fieldsName.rptCianotica() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPielHumeda() != null
				&& lesionadoCompletoData.getEvaluacion().getPielHumeda()) {
			sb.append(fieldsName.rptHumeda() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPielEnrojecida() != null
				&& lesionadoCompletoData.getEvaluacion().getPielEnrojecida()) {
			sb.append(fieldsName.rptEnrojecida() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPielSeca() != null
				&& lesionadoCompletoData.getEvaluacion().getPielSeca()) {
			sb.append(fieldsName.rptSeca() + "\n");
		}
		table.addCell(pdfUtil.adicionarTexto(fieldsName.rptTitlePiel(),
				sb.toString()));
		return table;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTablePupilas(LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 0, Color.BLACK, new int[] { 18 },
				0);
		StringBuilder sb = new StringBuilder();
		if (lesionadoCompletoData.getEvaluacion().getPupilasMioticas() != null
				&& lesionadoCompletoData.getEvaluacion().getPupilasMioticas()) {
			sb.append(fieldsName.rptMioticas() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPupilasMidriaticas() != null
				&& lesionadoCompletoData.getEvaluacion()
						.getPupilasMidriaticas()) {
			sb.append(fieldsName.rptMidriaticas() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPupilasArisocoricas() != null
				&& lesionadoCompletoData.getEvaluacion()
						.getPupilasArisocoricas()) {
			sb.append(fieldsName.rptArisocoricas() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPupilasIsocoricas() != null
				&& lesionadoCompletoData.getEvaluacion().getPupilasIsocoricas()) {
			sb.append(fieldsName.rptIsocoricas() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPupilasReactivas() != null
				&& lesionadoCompletoData.getEvaluacion().getPupilasReactivas()) {
			sb.append(fieldsName.rptReactivas() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPupilasNoReactivas() != null
				&& lesionadoCompletoData.getEvaluacion()
						.getPupilasNoReactivas()) {
			sb.append(fieldsName.rptNoReactivas() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPupilasNoEvaluables() != null
				&& lesionadoCompletoData.getEvaluacion()
						.getPupilasNoEvaluables()) {
			sb.append(fieldsName.rptNoEvaluables() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPupilasProtesisIzquierda() != null
				&& lesionadoCompletoData.getEvaluacion()
						.getPupilasProtesisIzquierda()) {
			sb.append(fieldsName.rptProtIzq() + "\n");
		}
		if (lesionadoCompletoData.getEvaluacion().getPupilasProtesisDerecha() != null
				&& lesionadoCompletoData.getEvaluacion()
						.getPupilasProtesisDerecha()) {
			sb.append(fieldsName.rptProtDer() + "\n");
		}
		table.addCell(pdfUtil.adicionarTexto(fieldsName.rptTitlePupilas(),
				sb.toString()));
		return table;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTableGalsgow(LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 0, Color.BLACK, new int[] { 18 },
				0);
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append(lesionadoCompletoData.getEvaluacion().getGlasgowRO() != null ? fieldsName
				.rptRO()
				+ " "
				+ pdfUtil.doubleToString((double) lesionadoCompletoData
						.getEvaluacion().getGlasgowRO()) + "\n" : fieldsName
				.rptRO() + "\n");
		sb.append(lesionadoCompletoData.getEvaluacion().getGlasgowRV() != null ? fieldsName
				.rptRV()
				+ " "
				+ pdfUtil.nullToEmpty(pdfUtil
						.doubleToString((double) lesionadoCompletoData
								.getEvaluacion().getGlasgowRV())) + "\n"
				: fieldsName.rptRV() + "\n");
		sb.append(lesionadoCompletoData.getEvaluacion().getGlasgowRM() != null ? fieldsName
				.rptRM()
				+ " "
				+ pdfUtil.nullToEmpty(pdfUtil
						.doubleToString((double) lesionadoCompletoData
								.getEvaluacion().getGlasgowRM())) + "\n"
				: fieldsName.rptRM() + "\n");
		if (lesionadoCompletoData.getEvaluacion().getGlasgowRO() != null
				&& lesionadoCompletoData.getEvaluacion().getGlasgowRV() != null
				&& lesionadoCompletoData.getEvaluacion().getGlasgowRM() != null)
			sb.append(fieldsName.rptTotal()
					+ " "
					+ (lesionadoCompletoData.getEvaluacion().getGlasgowRO()
							+ lesionadoCompletoData.getEvaluacion()
									.getGlasgowRV() + lesionadoCompletoData
							.getEvaluacion().getGlasgowRM()));
		else
			sb.append(fieldsName.rptTotal());
		table.addCell(pdfUtil.adicionarTexto(fieldsName.rptTitleGlasgow(),
				sb.toString()));
		return table;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTableProcA(LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 1, Color.BLACK, new int[] { 18 },
				0);
		StringBuilder sb = new StringBuilder();
		if (lesionadoCompletoData.getProcedimientos()
				.getAspiracionSecreciones() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getAspiracionSecreciones()) {
			sb.append(fieldsName.rptAspiracionSecreciones() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getCanulaOrofaringea() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getCanulaOrofaringea()) {
			sb.append(fieldsName.rptCanulaOrofaringea() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getDespejeManual() != null
				&& lesionadoCompletoData.getProcedimientos().getDespejeManual()) {
			sb.append(fieldsName.rptDespejeManual() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getCollarCervical() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getCollarCervical()) {
			sb.append(fieldsName.rptCollarCervical() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getCanulaNasofaringea() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getCanulaNasofaringea()) {
			sb.append(fieldsName.rptCanulaNasoFaringea() + "\n");
		}

		// A - Soporte vitál avanzado
		if (lesionadoCompletoData.getProcedimientos()
				.getDispositivoSupragliotico() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getDispositivoSupragliotico()) {
			sb.append(fieldsName.rptDispositivoSupraglotico() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getCricotiroidotomia() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getCricotiroidotomia()) {
			sb.append(fieldsName.rptCricoroidotomia() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos()
				.getIntubacionOrotraqueal() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getIntubacionOrotraqueal()) {
			sb.append(fieldsName.rptIntubacionOrotraqueal() + "\n");
		}
		table.addCell(new Paragraph(sb.toString(), fontTextoNormal));
		return table;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTableProcB(LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 1, Color.BLACK, new int[] { 18 },
				0);
		StringBuilder sb = new StringBuilder();
		if (lesionadoCompletoData.getProcedimientos().getEvaluacionB() != null
				&& lesionadoCompletoData.getProcedimientos().getEvaluacionB()) {
			sb.append(fieldsName.rptEvaluacion() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos()
				.getReanimacionRespiratoria() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getReanimacionRespiratoria()) {
			sb.append(fieldsName.rptReanimacionRespiratoria() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getCanulaNasal() != null
				&& lesionadoCompletoData.getProcedimientos().getCanulaNasal()) {
			sb.append(fieldsName.rptCanulaNasal() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos()
				.getMascaraNoReinhalacion() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getMascaraNoReinhalacion()) {
			sb.append(fieldsName.rptMascaraNoReinhalacion() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getMascaraSimple() != null
				&& lesionadoCompletoData.getProcedimientos().getMascaraSimple()) {
			sb.append(fieldsName.rptMascaraSimple() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getBvm() != null
				&& lesionadoCompletoData.getProcedimientos().getBvm()) {
			sb.append(fieldsName.rptBVM() + "\n");
		}
		// B - Soporte vitál avanzado
		if (lesionadoCompletoData.getProcedimientos().getDescompresionTorax() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getDescompresionTorax()) {
			sb.append(fieldsName.rptDescompresionTorax() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getVentilacionMecanica() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getVentilacionMecanica()) {
			sb.append(fieldsName.rptVentilacionMecanica() + "\n");
		}
		table.addCell(new Paragraph(sb.toString(), fontTextoNormal));
		return table;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTableProcC(LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 1, Color.BLACK, new int[] { 18 },
				0);
		StringBuilder sb = new StringBuilder();
		if (lesionadoCompletoData.getProcedimientos().getEvaluacionC() != null
				&& lesionadoCompletoData.getProcedimientos().getEvaluacionC()) {
			sb.append(fieldsName.rptEvaluacion() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getRccp() != null
				&& lesionadoCompletoData.getProcedimientos().getRccp()) {
			sb.append(fieldsName.rptRCP() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getHemostasia() != null
				&& lesionadoCompletoData.getProcedimientos().getHemostasia()) {
			sb.append(fieldsName.rptHemostacia() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getDea() != null
				&& lesionadoCompletoData.getProcedimientos().getDea()) {
			sb.append(fieldsName.rptDEA() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos()
				.getMonitoreoSignosVitales() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getMonitoreoSignosVitales()) {
			sb.append(fieldsName.rptMonitoreoSignosVitales() + "\n");
		}

		// C - Soporte vitál avanzado
		if (lesionadoCompletoData.getProcedimientos().getRehidratacionOral() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getRehidratacionOral()) {
			sb.append(fieldsName.rptRehidratacionOral() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getSsn09() != null
				&& lesionadoCompletoData.getProcedimientos().getSsn09()) {
			sb.append(fieldsName.rptSSN() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos()
				.getAccesoVenosoPeriferico() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getAccesoVenosoPeriferico()) {
			sb.append(fieldsName.rptAccesoVenosoPeriferico() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getDestrosa() != null
				&& lesionadoCompletoData.getProcedimientos().getDestrosa()) {
			sb.append(fieldsName.rptDestrosa() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getPunsionOsea() != null
				&& lesionadoCompletoData.getProcedimientos().getPunsionOsea()) {
			sb.append(fieldsName.rptPuncionOsea() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getColoides() != null
				&& lesionadoCompletoData.getProcedimientos().getColoides()) {
			sb.append(fieldsName.rptColoides() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getHartman() != null
				&& lesionadoCompletoData.getProcedimientos().getHartman()) {
			sb.append(fieldsName.rptHartman() + "\n");
		}
		table.addCell(new Paragraph(sb.toString(), fontTextoNormal));
		return table;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTableProcD(LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 1, Color.BLACK, new int[] { 18 },
				0);
		table.addCell(new Paragraph("", fontTextoNormal));
		return table;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public Table createTableProcE(LesionadoCompletoData lesionadoCompletoData)
			throws DocumentException {
		Table table = pdfUtil.createTable(1, 1, Color.BLACK, new int[] { 18 },
				0);
		StringBuilder sb = new StringBuilder();
		if (lesionadoCompletoData.getProcedimientos().getExposicion() != null
				&& lesionadoCompletoData.getProcedimientos().getExposicion()) {
			sb.append(fieldsName.rptExposicion() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getMovimientoBloque() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getMovimientoBloque()) {
			sb.append(fieldsName.rptMovimientoBloque() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getLavadoCuracion() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getLavadoCuracion()) {
			sb.append(fieldsName.rptLavado() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos()
				.getChalecoExtracionVehicular() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getChalecoExtracionVehicular()) {
			sb.append(fieldsName.rptExtricacion() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getMantaTermica() != null
				&& lesionadoCompletoData.getProcedimientos().getMantaTermica()) {
			sb.append(fieldsName.rptManta() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getTablaEspinalLarga() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getTablaEspinalLarga()) {
			sb.append(fieldsName.rptTablaEspinalLarga() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos()
				.getInmovilizacionFerulas() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getInmovilizacionFerulas()) {
			sb.append(fieldsName.rptInmovilizacion() + "\n");
		}
		if (lesionadoCompletoData.getProcedimientos().getTablaEspinalCorta() != null
				&& lesionadoCompletoData.getProcedimientos()
						.getTablaEspinalCorta()) {
			sb.append(fieldsName.rptTablaEspinalCorta() + "\n");
		}
		table.addCell(new Paragraph(sb.toString(), fontTextoNormal));
		return table;
	}

	/**
	 * Convierte un listado en un String separado por renglones
	 * 
	 * @param value
	 * @return
	 */
	public String getLesiones(ArrayList<Lesiones> list, String separator) {
		StringBuilder value = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			value.append(i != list.size() - 1 ? list.get(i) + separator : list
					.get(i));
		}
		return value.toString().trim();
	}

	/**
	 * carga el resultado obtenido y lo pone enla vista
	 */
	public String pushResultado(PersonaLesionada personaLesionada) {
		Integer value = personaLesionada.getResultado();
		if (value == null) {
			return "";
		}
		if (value == PersonaLesionada.RESULTADO_TRANSPORTE_HOSPITAL) {
			return fieldsName.rptResultadoTranspHosp();
		}
		if (value == PersonaLesionada.RESULTADO_TRANSPORTE_SECUNDARIO) {
			return fieldsName.rptResultadoTrnspSecundario();
		}
		if (value == PersonaLesionada.RESULTADO_ALTA_SITIO) {
			return fieldsName.rptResultadoAltaSitio();
		}
		if (value == PersonaLesionada.RESULTADO_ENTREGA_OTRO) {
			return fieldsName.rptResultadoEntregaOtro();
		}
		if (value == PersonaLesionada.RESULTADO_RCCP_EXITOSA) {
			return fieldsName.rptResultadoRCCPExitosa();
		}
		if (value == PersonaLesionada.RESULTADO_FALLECE_TRASLADO) {
			return fieldsName.rptResultadoFalleceTraslado();
		}
		if (value == PersonaLesionada.RESULTADO_FALLECE_HOSPITAL) {
			return fieldsName.rptResultadoFalleceHosp();
		}
		if (value == PersonaLesionada.RESULTADO_FALLECE_SITIO) {
			return fieldsName.rptResultadoFalleceSitio();
		}
		if (value == PersonaLesionada.RESULTADO_NEGACION) {
			if (personaLesionada.getTipoNegacion().equals(PersonaLesionada.NEGACION_ATENCION))
				return fieldsName.rptResultadoNegacionAtencion() + ". "
						+ personaLesionada.getObservacionNegacion();
			if (personaLesionada.getTipoNegacion().equals( PersonaLesionada.NEGACION_REMISION))
				return fieldsName.rptResultadoNegacionRemision() + ". "
						+ personaLesionada.getObservacionNegacion();
		}
		return "";
	}

	/**
	 * 
	 */
	public String pushEmergenciaMedica(EvaluacionData evaluacionData) {
		Integer emergenciamedica = evaluacionData.getTipoEmergencia();
		if (emergenciamedica != null) {
			if (emergenciamedica
					.equals(EvaluacionData.EMERGENCIA_PARO_CARDIACO)) {
				return fieldsName.rptParoCardiaco();
			} else {
				if (emergenciamedica
						.equals(EvaluacionData.EMERGENCIA_NEUROLOGIA)) {
					return fieldsName.rptNeurologica();
				} else {
					if (emergenciamedica
							.equals(EvaluacionData.EMERGENCIA_ORGANOS_SENTIDOS)) {
						return fieldsName.rptOrgSentidos();
					} else {
						if (emergenciamedica
								.equals(EvaluacionData.EMERGENCIA_CARDIOVASCULAR)) {
							return fieldsName.rptCardiovascular();
						} else {
							if (emergenciamedica
									.equals(EvaluacionData.EMERGENCIA_GASTROINTESTINAL)) {
								return fieldsName.rptGastrointestinal();
							} else {
								if (emergenciamedica
										.equals(EvaluacionData.EMERGENCIA_GENITOURINARIO)) {
									return fieldsName.rptGenitourinario();
								} else {
									if (emergenciamedica
											.equals(EvaluacionData.EMERGENCIA_GINECOOBSTETRICO)) {
										return fieldsName.rptGinecoObstetrico();
									} else {
										if (emergenciamedica
												.equals(EvaluacionData.EMERGENCIA_SHOCK)) {
											return fieldsName.rptShock();
										} else {
											if (emergenciamedica
													.equals(EvaluacionData.EMERGENCIA_INTOXICACION)) {
												return fieldsName
														.rptIntoxicacion();
											} else {
												if (emergenciamedica
														.equals(EvaluacionData.EMERGENCIA_SIQUIATRICA)) {
													return fieldsName
															.rptPsiquiatrica();
												} else {
													if (emergenciamedica
															.equals(EvaluacionData.EMERGENCIA_OVACE)) {
														return fieldsName
																.rptOVACE();
													} else {
														if (emergenciamedica
																.equals(EvaluacionData.EMERGENCIA_TERMICA)) {
															return fieldsName
																	.rptTermica();
														} else {
															if (emergenciamedica
																	.equals(EvaluacionData.EMERGENCIA_ENFERMEDAD_COMUN)) {
																return fieldsName
																		.rptEnfermedadComun();
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return "";
	}

	// lo del servicio
	/****
	 * *******************SIGNOS VITALES
	 */
	public ArrayList<SignosVitalesData> obtenerSignosVitales(Integer lesionadoId)
			throws TelesaludGWTException {

		ArrayList<SignosVitalesData> lista = null;
		// assertAccess(RESOURCE_NAME, OPERACION_LEER);

		try {

			ConceptsCode concepts = new ConceptsCode();
			lista = new ArrayList<SignosVitalesData>();

			if (lesionadoId != null) {
				Lesionado lesionado = lesionadoService
						.obtenerLesionado(lesionadoId);
				for (ObservationData sv : obsService
						.obtenerSignosVitales(lesionado.getEncuentro())) {

					SignosVitalesData signosVitalesData = new SignosVitalesData();

					signosVitalesData.setFecha((Date) ObservationData
							.obtenerValorConcepto(sv.getSons(),
									concepts.cFechaSignoVital()));
					signosVitalesData.setGlicemia((Double) ObservationData
							.obtenerValorConcepto(sv.getSons(),
									concepts.cGlicemia()));
					signosVitalesData.setPaDiastolica((Double) ObservationData
							.obtenerValorConcepto(sv.getSons(),
									concepts.cPresionArterialDiastolica()));
					signosVitalesData.setPaSistolica((Double) ObservationData
							.obtenerValorConcepto(sv.getSons(),
									concepts.cPresionArterialSistolica()));
					signosVitalesData.setPulso((Double) ObservationData
							.obtenerValorConcepto(sv.getSons(),
									concepts.cFrecuenciaPulso()));
					signosVitalesData.setRespiracion((Double) ObservationData
							.obtenerValorConcepto(sv.getSons(),
									concepts.cRespiracion()));

					lista.add(signosVitalesData);
				}
			}

			return lista;

		} catch (TelesaludException e) {
			throw new TelesaludGWTException(e);
		}
	}

	/***************************************
	 * CARGA DE MUNICIPIOS
	 */
	public ArrayList<ValuedItem<Integer, String>> obtenerMunicipios(
			Integer departamento) throws TelesaludGWTException {

		try {
			ArrayList<ValuedItem<Integer, String>> lista = new ArrayList<ValuedItem<Integer, String>>();

			for (City city : locationService.getCitiesByState(departamento)) {
				lista.add(new ValuedItem<Integer, String>(city.getCityId(),
						city.getName()));
			}

			return lista;
		} catch (TelesaludException e) {
			throw new TelesaludGWTException(e);
		}

	}

	/***************
	 * NOTAS ACLARATORIAS
	 */
	public ArrayList<NotaAclaratoria> obtenerNotasAclaratorias(
			Integer lesionadoId) throws TelesaludGWTException {
		ArrayList<NotaAclaratoria> lista = null;

		try {

			// //assertAccess(RESOURCE_NAME, OPERACION_ACTUALIZAR);
			ConceptsCode concepts = new ConceptsCode();
			lista = new ArrayList<NotaAclaratoria>();
			Lesionado lesionado = lesionadoService
					.obtenerLesionado(lesionadoId);

			for (ObservationData sv : obsService
					.obtenerNotasAclaratorias(lesionado.getEncuentro())) {

				NotaAclaratoria notaAclaratoria = new NotaAclaratoria();

				notaAclaratoria.setFechaCreacion((Date) ObservationData
						.obtenerValorConcepto(sv.getSons(),
								concepts.cFechaNotaAclaratoria()));
				notaAclaratoria.setNota((String) ObservationData
						.obtenerValorConcepto(sv.getSons(),
								concepts.cTextoNotaAclaratoria()));
				String usuario = (String) ObservationData.obtenerValorConcepto(
						sv.getSons(), concepts.cUsuarioNotaAclaratoria());

				if (usuario != null && !usuario.equals("null")) {

					Person p = personService.obtenerPersona(Integer
							.parseInt(usuario));
					notaAclaratoria.setNombreCreador(p.getPreferredName()
							.toString());
				}

				lista.add(notaAclaratoria);

			}

			return lista;

		} catch (TelesaludException e) {
			throw new TelesaludGWTException(e);
		}
	}

	/**************
	 * TRIPULACIONES
	 */
	public com.artica.telesalud.tph.lightweightmodel.Tripulacion obtenerTripulacion(
			Integer tripulacion) throws TelesaludGWTException {

		try {

			// assertAccess(RESOURCE_NAME, OPERACION_LEER);
			com.artica.telesalud.tph.lightweightmodel.Tripulacion tripulacionLight = new com.artica.telesalud.tph.lightweightmodel.Tripulacion();

			if (tripulacion == null)
				tripulacionLight = new com.artica.telesalud.tph.lightweightmodel.Tripulacion();
			else {
				tripulacionLight = CargarTripulacion
						.cargarDatos(tripulacionService
								.obtenerTripulacion(tripulacion));
			}

			return tripulacionLight;
		} catch (TelesaludException e) {
			throw new TelesaludGWTException(e);
		}
	}

	/********************************************
	 * LESIONADOgwtservuice
	 * 
	 */

	public Integer obtenerLesionado(Integer eventoId, Integer lesionadoId)
			throws TelesaludGWTException {

		try {

			Integer idLesionado = null;
			// Usuario que crea el lesionado
			Integer usuario = 2;

			if (lesionadoId == null) {
				idLesionado = lesionadoService.crearNuevoLesionado(eventoId,
						usuario).getLesionadoId();
			} else {
				idLesionado = lesionadoId;
			}

			return idLesionado;

		} catch (TelesaludException e) {
			throw new TelesaludGWTException(e);
		}
	}

	public LesionadoCompletoData obtenerInformacionCompletaLesionado(
			Integer lesionadoId) throws TelesaludGWTException {

		try {
			// assertAccess(RESOURCE_NAME, OPERACION_LEER);
			LesionadoCompletoData lesionadoCompleto = new LesionadoCompletoData();

			if (lesionadoId != null) {
				Lesionado lesionado = lesionadoService
						.obtenerLesionado(lesionadoId);
				Integer departamento = null;
				City ciudad = null;

				if (lesionado != null) {

					Evento evento = lesionado.getEvento();

					Patient paciente = patientService.buscarPatient(lesionado
							.getEncuentro().getPatient().getPatientId());
					if (paciente.getPerson().getPreferredAddress().getCity() != null) {
						ciudad = locationService.obtenerCity(paciente
								.getPerson().getPreferredAddress().getCity());
						departamento = ciudad.getStateProvince()
								.getStateProvinceId();
					}

					lesionadoCompleto
							.setInformacionEventoData(cargarInformacionCompleta(
									evento, lesionado));
					lesionadoCompleto.getInformacionEventoData().setLesionado(
							CargarLesionado.cargarDatos(patientService,
									lesionado));

					lesionadoCompleto
							.setPacienteCompletoData(new PacienteCompletoData());

					lesionadoCompleto.getPacienteCompletoData()
							.setPacienteData(
									CargarPaciente.cargarDatos(lesionado,
											paciente, departamento));

					lesionadoCompleto
							.getPacienteCompletoData()
							.setTiposIdentificacion(
									CargarPaciente
											.cargarTiposIdentificacion(patientService
													.getPatientIdentifierTypes()));
					lesionadoCompleto.getPacienteCompletoData()
							.setEstadosCiviles(
									CargarValuedItem
											.cargarConceptos(conceptService
													.obtenerEstadosCiviles()));
					lesionadoCompleto
							.getPacienteCompletoData()
							.setDepartamentos(
									CargarValuedItem
											.cargarDepartamentos(locationService
													.getStates()));
					lesionadoCompleto
							.getPacienteCompletoData()
							.setAseguradoras(
									CargarValuedItem.cargarConceptos(conceptService
											.obtenerAseguradorasActive()));
					lesionadoCompleto.getPacienteCompletoData().setEpss(
							CargarValuedItem.cargarConceptos(conceptService
									.obtenerEpssActive()));
					lesionadoCompleto
							.getPacienteCompletoData()
							.setPlanesBeneficios(
									CargarValuedItem.cargarConceptos(conceptService
											.obtenerPlanesBeneficiosActive()));

					lesionadoCompleto.setAntecedenteData(new AntecedenteData());
					lesionadoCompleto.getAntecedenteData().setAntecedentes(
							cargarAntecedentes(paciente));
					lesionadoCompleto
							.getAntecedenteData()
							.setTiposAntecedentes(
									CargarValuedItem.cargarConceptos(conceptService
											.obtenerTiposAntecedentes()));

					lesionadoCompleto
							.setProcedimientos(cargarProcedimientos(lesionado
									.getEncuentro()));

					lesionadoCompleto
							.setResultados(new ResultadosCompleteData());

					lesionadoCompleto.getResultados().setResultado(
							cargarResultados(lesionado));
					lesionadoCompleto.getResultados().setArchivos(
							cargarArchivos(lesionado));
					lesionadoCompleto.getResultados().setEntidadesRecepcion(
							CargarHospital.cargarHospitales(hospitalService
									.obtenerHospitales()));
					lesionadoCompleto
							.getResultados()
							.setResponsablesAtencion(
									cargarResponsablesAtencion(responsableAtencionService
											.obtenerResponsablesAtencion(lesionado)));
					lesionadoCompleto.getResultados().setControlesAph(
							CargarValuedItem.cargarConceptos(conceptService
									.obtenerControlesAPH()));

					lesionadoCompleto
							.setTeleasistencia(new TeleasistenciaCompleteData());
					lesionadoCompleto.getTeleasistencia().setMedios(
							CargarValuedItem.cargarConceptos(conceptService
									.obtenerMediosSolicitudTeleasistencia()));
					lesionadoCompleto.getTeleasistencia().setTeleasistencias(
							cargarTeleasistencias(lesionado));

					lesionadoCompleto.setEvaluacion(cargarEvaluacion(lesionado
							.getEncuentro()));
					lesionadoCompleto.setHallazgos(cargarHallazgos(lesionado
							.getEncuentro()));

					lesionadoCompleto
							.setDatosCierre(cargarDatosCierre(lesionado));

				} else {
					new TelesaludGWTException(
							String.format(
									"No existe un lesionado con el identificador %1$s en el sistema",
									lesionadoId));
				}

			} else {
				new TelesaludGWTException(
						"El identificador del lesionado no puede ser nulo");
			}

			return lesionadoCompleto;

		} catch (TelesaludException e) {
			throw new TelesaludGWTException(e);
		}
	}

	public void establecerTripulacion(Integer lesionadoId, Integer tripulacionId)
			throws TelesaludGWTException {
		try {
			// assertAccess(RESOURCE_NAME, OPERACION_ACTUALIZAR);
			Integer usuario = 2;
			Lesionado lesionado = lesionadoService
					.obtenerLesionado(lesionadoId);
			Tripulacion tripulacion = tripulacionService
					.obtenerTripulacion(tripulacionId);

			lesionado.setTripulacion(tripulacion);
			lesionado.setChangedBy(usuario);

			lesionadoService.actualizarLesionado(lesionado);

		} catch (TelesaludException e) {
			throw new TelesaludGWTException(e);
		}
	}

	public void guardarResultados(PersonaLesionada personaLesionada,
			ResultadoData resultadoData, ArrayList<ArchivoData> archivos)
			throws TelesaludGWTException {

		try {
			// assertAccess(RESOURCE_NAME, OPERACION_ACTUALIZAR);

			Integer usuario = 2;

			ConceptsCode concepts = new ConceptsCode();
			Lesionado lesionado = lesionadoService
					.obtenerLesionado(personaLesionada.getLesionadoId());

			if (personaLesionada.getAseguradora() != null)
				lesionado.setAseguradora(conceptService
						.obtenerConcepto(personaLesionada.getAseguradora()));

			if (personaLesionada.getDispositivoTransporte() != null) {

				if (personaLesionada.getDispositivoTransporte().equals(
						PersonaLesionada.DISPOSITIVO_CAMILLA_LONA))
					lesionado.setDispositivoTransporte(conceptService
							.obtenerConcepto(concepts.cCamillaLona()));
				else if (personaLesionada.getDispositivoTransporte().equals(
						PersonaLesionada.DISPOSITIVO_CAMILLA_MILLER))
					lesionado.setDispositivoTransporte(conceptService
							.obtenerConcepto(concepts.cCamillaMiller()));
				else if (personaLesionada.getDispositivoTransporte().equals(
						PersonaLesionada.DISPOSITIVO_CAMILLA_RIGIDA))
					lesionado.setDispositivoTransporte(conceptService
							.obtenerConcepto(concepts.cCamillaRigida()));
				else if (personaLesionada.getDispositivoTransporte().equals(
						PersonaLesionada.DISPOSITIVO_CAMILLA_SCOOP))
					lesionado.setDispositivoTransporte(conceptService
							.obtenerConcepto(concepts.cCamillaScoop()));

			}

			if (personaLesionada.getEntidadRecepcion() != null)
				lesionado.setEntidadRecepcion(hospitalService
						.obtener(personaLesionada.getEntidadRecepcion()));

			if (personaLesionada.getEps() != null)
				lesionado.setEps(conceptService
						.obtenerConcepto(personaLesionada.getEps()));

			lesionado.setObservacionNegacion(personaLesionada
					.getObservacionNegacion());

			if (personaLesionada.getPlanBeneficios() != null)
				lesionado.setPlanBeneficios(conceptService
						.obtenerConcepto(personaLesionada.getPlanBeneficios()));

			lesionado.setRecibidoPor(personaLesionada.getRecibidoPor());
			lesionado.setRegistroRecibe(personaLesionada.getRegistroRecibe());

			if (personaLesionada.getResultado() != null) {

				if (personaLesionada.getResultado().equals(
						PersonaLesionada.RESULTADO_ALTA_SITIO))
					lesionado.setResultado(conceptService
							.obtenerConcepto(concepts.cDeAltaSitio()));
				else if (personaLesionada.getResultado().equals(
						PersonaLesionada.RESULTADO_ENTREGA_OTRO))
					lesionado.setResultado(conceptService
							.obtenerConcepto(concepts.cEntregaOtroArrib()));
				else if (personaLesionada.getResultado().equals(
						PersonaLesionada.RESULTADO_FALLECE_HOSPITAL))
					lesionado.setResultado(conceptService
							.obtenerConcepto(concepts.cFalleceHospital()));
				else if (personaLesionada.getResultado().equals(
						PersonaLesionada.RESULTADO_FALLECE_SITIO))
					lesionado.setResultado(conceptService
							.obtenerConcepto(concepts.cFalleceSitio()));
				else if (personaLesionada.getResultado().equals(
						PersonaLesionada.RESULTADO_FALLECE_TRASLADO))
					lesionado.setResultado(conceptService
							.obtenerConcepto(concepts.cFalleceTraslado()));
				else if (personaLesionada.getResultado().equals(
						PersonaLesionada.RESULTADO_NEGACION))
					lesionado.setResultado(conceptService
							.obtenerConcepto(concepts.cSeNiegaAtencion()));
				else if (personaLesionada.getResultado().equals(
						PersonaLesionada.RESULTADO_RCCP_EXITOSA))
					lesionado.setResultado(conceptService
							.obtenerConcepto(concepts.cRCCPExitosa()));
				else if (personaLesionada.getResultado().equals(
						PersonaLesionada.RESULTADO_TRANSPORTE_HOSPITAL))
					lesionado.setResultado(conceptService
							.obtenerConcepto(concepts.cTransporteHospital()));
				else if (personaLesionada.getResultado().equals(
						PersonaLesionada.RESULTADO_TRANSPORTE_SECUNDARIO))
					lesionado.setResultado(conceptService
							.obtenerConcepto(concepts.cTransporteSecundario()));

			}

			if (personaLesionada.getTipoNegacion() != null) {

				if (personaLesionada.getTipoNegacion().equals(
						PersonaLesionada.NEGACION_ATENCION))
					lesionado.setTipoNegacion(conceptService
							.obtenerConcepto(concepts.cNiegaAtencion()));
				else if (personaLesionada.getTipoNegacion().equals(
						PersonaLesionada.NEGACION_REMISION))
					lesionado.setTipoNegacion(conceptService
							.obtenerConcepto(concepts.cNiegaRemision()));
			}

			lesionado.setChangedBy(usuario);
			lesionadoService.actualizarLesionado(lesionado);

			Obs obsHC = obsService.obtenerObservacionPorConcepto(
					lesionado.getEncuentro(), concepts.cHallazgosClinicos());
			if (obsHC == null)
				obsService.guardar(lesionado.getEncuentro(),
						concepts.cHallazgosClinicos(),
						resultadoData.getHallazgosClinicos(), usuario);
			else if (obsHC.getValueText() == null) {
				obsHC.setValueText(resultadoData.getHallazgosClinicos());
				obsService.update(obsHC);
			}

			Obs obsID = obsService.obtenerObservacionPorConcepto(
					lesionado.getEncuentro(), concepts.cImpresionDiagnostica());
			if (obsID == null)
				obsService.guardar(lesionado.getEncuentro(),
						concepts.cImpresionDiagnostica(),
						resultadoData.getImpresionDiagnostica(), usuario);
			else if (obsID.getValueText() == null) {
				obsID.setValueText(resultadoData.getImpresionDiagnostica());
				obsService.update(obsID);
			}

			if (resultadoData.getRespondienteBombero())
				if (!lesionadoService.existePrimerRespondiente(lesionado,
						concepts.cBomberos())) {
					Concept concept = conceptService.obtenerConcepto(concepts
							.cBomberos());
					lesionadoService.guardarPrimerRespondiente(lesionado,
							concept, usuario);
				}

			if (resultadoData.getRespondienteCuidadano())
				if (!lesionadoService.existePrimerRespondiente(lesionado,
						concepts.cCuidadanos())) {
					Concept concept = conceptService.obtenerConcepto(concepts
							.cCuidadanos());
					lesionadoService.guardarPrimerRespondiente(lesionado,
							concept, usuario);
				}

			if (resultadoData.getRespondienteFamiliar())
				if (!lesionadoService.existePrimerRespondiente(lesionado,
						concepts.cFamiliares())) {
					Concept concept = conceptService.obtenerConcepto(concepts
							.cFamiliares());
					lesionadoService.guardarPrimerRespondiente(lesionado,
							concept, usuario);
				}

			if (resultadoData.getRespondienteMedico())
				if (!lesionadoService.existePrimerRespondiente(lesionado,
						concepts.cMedicos())) {
					Concept concept = conceptService.obtenerConcepto(concepts
							.cMedicos());
					lesionadoService.guardarPrimerRespondiente(lesionado,
							concept, usuario);
				}

			if (resultadoData.getRespondientePolicia())
				if (!lesionadoService.existePrimerRespondiente(lesionado,
						concepts.cPolicia())) {
					Concept concept = conceptService.obtenerConcepto(concepts
							.cPolicia());
					lesionadoService.guardarPrimerRespondiente(lesionado,
							concept, usuario);
				}

			if (resultadoData.getRespondienteSocorrista())
				if (!lesionadoService.existePrimerRespondiente(lesionado,
						concepts.cSocorristas())) {
					Concept concept = conceptService.obtenerConcepto(concepts
							.cSocorristas());
					lesionadoService.guardarPrimerRespondiente(lesionado,
							concept, usuario);
				}

			if (resultadoData.getRespondienteTransito())
				if (!lesionadoService.existePrimerRespondiente(lesionado,
						concepts.cTransito())) {
					Concept concept = conceptService.obtenerConcepto(concepts
							.cTransito());
					lesionadoService.guardarPrimerRespondiente(lesionado,
							concept, usuario);
				}

			for (ArchivoData archivo : archivos) {
				obsService.guardarArchivo(lesionado.getEncuentro(),
						archivo.getNombreOriginalArchivo(),
						archivo.getNombreArchivo(), archivo.getTipoContenido(),
						archivo.getArchivoDataId(), usuario);
			}

		} catch (TelesaludException e) {
			throw new TelesaludGWTException(e);
		}
	}

	public void cerrarAtencion(Integer lesionadoId, Integer tipoCierre,
			String notaCierre) throws TelesaludGWTException {

		try {
			// assertAccess(RESOURCE_NAME, OPERACION_ACTUALIZAR);

			ConceptsCode concepts = new ConceptsCode();
			Lesionado lesionado = lesionadoService
					.obtenerLesionado(lesionadoId);

			Integer usuario = 2;
			Integer conceptTipoCierre = null;

			if (tipoCierre.equals(CierreAtencion.TIPO_CIERRE_ANULACION))
				conceptTipoCierre = concepts.cCierreAtencionAnulada();
			else if (tipoCierre.equals(CierreAtencion.TIPO_CIERRE_APROBACION))
				conceptTipoCierre = concepts.cCierreAtencionAprobada();
			else if (tipoCierre.equals(CierreAtencion.TIPO_CIERRE_NOAPROBACION))
				conceptTipoCierre = concepts.cCierreAtencionNoAprobada();

			obsService.guardarDatosCierreAtencion(lesionado.getEncuentro(),
					conceptTipoCierre, notaCierre, usuario, null);

			encounterService.cerrarEncuentro(lesionado.getEncuentro(), usuario);

		} catch (TelesaludException e) {
			throw new TelesaludGWTException(e);
		}

	}

	private InformacionEventoData cargarInformacionCompleta(Evento evento,
			Lesionado lesionado) {
		InformacionEventoData informacionEventoData = new InformacionEventoData();

		List<Tripulacion> tripulaciones;

		informacionEventoData.setEvento(CargarEvento.cargarDatos(evento));
		tripulaciones = tripulacionService.obtenerTripulaciones(evento);

		informacionEventoData.setPlacas(CargarValuedItem
				.cargarPlacas(tripulaciones));
		if (lesionado.getTripulacion() != null) {
			informacionEventoData.setFechaDespacho(lesionado.getTripulacion()
					.getHoraDespacho());
			informacionEventoData.setFechaLLegada(lesionado.getTripulacion()
					.getHoraLlegada());
		}

		return informacionEventoData;
	}

	private ArrayList<Antecedente> cargarAntecedentes(Patient paciente) {

		ConceptsCode concepts = new ConceptsCode();

		ArrayList<Antecedente> antecedentes = new ArrayList<Antecedente>();
		for (ObservationData od : obsService.obtenerAntecedentes(paciente)) {
			Antecedente antecedente = new Antecedente();

			for (ObservationData odL : od.getSons()) {

				if (odL.getConceptId().equals(concepts.cAnioAntecedente())) {
					antecedente.setAnio(odL.getValueNumeric().intValue());
				}

				if (odL.getConceptId().equals(concepts.cTipoAntecedente())) {
					antecedente.setIdTipoAntecedente(odL.getValueCoded());

					Concept c = conceptService.obtenerConcepto(antecedente
							.getIdTipoAntecedente());
					if (c != null)
						antecedente.setTipoAntecedente(c.getShortName());
				}

				if (odL.getConceptId().equals(
						concepts.cObservacionAntecedente()))
					antecedente.setObservacion(odL.getValueText());

			}

			antecedentes.add(antecedente);

		}

		return antecedentes;

	}

	private Procedimientos cargarProcedimientos(Encounter encuentro) {

		Procedimientos procedimientos = CargarObservaciones
				.cargarProcedimientos(obsService.obtenerProcedimientos(
						encuentro).getSons());
		return procedimientos;

	}

	private EvaluacionData cargarEvaluacion(Encounter encuentro) {

		EvaluacionData evaluacion = CargarEvaluacion
				.cargarEvaluacion(obsService.obtenerEvaluacion(encuentro)
						.getSons());
		return evaluacion;

	}

	private ResultadoData cargarResultados(Lesionado lesionado) {

		ResultadoData resultadoData = new ResultadoData();
		ConceptsCode concepts = new ConceptsCode();

		Obs observacion = null;

		observacion = obsService.obtenerObservacionPorConcepto(
				lesionado.getEncuentro(), concepts.cHallazgosClinicos());
		if (observacion != null)
			resultadoData.setHallazgosClinicos(observacion.getValueText());

		observacion = obsService.obtenerObservacionPorConcepto(
				lesionado.getEncuentro(), concepts.cImpresionDiagnostica());
		if (observacion != null)
			resultadoData.setImpresionDiagnostica(observacion.getValueText());

		for (PrimerRespondiente pr : lesionadoService
				.obtenerPrimerosRespondientes(lesionado)) {

			if (pr.getPrimerRespondiente().getConceptId()
					.equals(concepts.cPolicia()))
				resultadoData.setRespondientePolicia(true);
			else if (pr.getPrimerRespondiente().getConceptId()
					.equals(concepts.cCuidadanos()))
				resultadoData.setRespondienteCuidadano(true);
			else if (pr.getPrimerRespondiente().getConceptId()
					.equals(concepts.cSocorristas()))
				resultadoData.setRespondienteSocorrista(true);
			else if (pr.getPrimerRespondiente().getConceptId()
					.equals(concepts.cMedicos()))
				resultadoData.setRespondienteMedico(true);
			else if (pr.getPrimerRespondiente().getConceptId()
					.equals(concepts.cBomberos()))
				resultadoData.setRespondienteBombero(true);
			else if (pr.getPrimerRespondiente().getConceptId()
					.equals(concepts.cTransito()))
				resultadoData.setRespondienteTransito(true);
			else if (pr.getPrimerRespondiente().getConceptId()
					.equals(concepts.cFamiliares()))
				resultadoData.setRespondienteFamiliar(true);

		}
		return resultadoData;
	}

	private ArrayList<ArchivoData> cargarArchivos(Lesionado lesionado) {

		ConceptsCode concepts = new ConceptsCode();
		ArrayList<ArchivoData> archivos = new ArrayList<ArchivoData>();
		for (ObservationData observationData : obsService
				.obtenerArchivos(lesionado.getEncuentro())) {

			ArchivoData archivoData = new ArchivoData();
			archivoData.setArchivoDataId(observationData.getObsId());

			archivoData.setNombreArchivo((String) ObservationData
					.obtenerValorConcepto(observationData.getSons(),
							concepts.cNombreOriginalArchivo()));
			archivoData.setNombreOriginalArchivo((String) ObservationData
					.obtenerValorConcepto(observationData.getSons(),
							concepts.cNombreOriginalArchivo()));
			archivoData.setTipoContenido((String) ObservationData
					.obtenerValorConcepto(observationData.getSons(),
							concepts.cTipoContenidoArchivos()));

			archivos.add(archivoData);
		}

		return archivos;

	}

	private ArrayList<ResponsableAtencion> cargarResponsablesAtencion(
			List<com.artica.telesalud.tph.model.evento.ResponsableAtencion> responsables) {

		String nombres = "";
		String apellidos = "";
		ArrayList<ResponsableAtencion> lista = new ArrayList<ResponsableAtencion>();
		for (com.artica.telesalud.tph.model.evento.ResponsableAtencion ra : responsables) {

			nombres = "";
			apellidos = "";

			ResponsableAtencion responsableAtencion = new ResponsableAtencion();

			responsableAtencion.setResponsableAtencionId(ra
					.getResponsableAtencionId());

			if (ra.getPerson().getPreferredName().getFamilyName() != null)
				apellidos += ra.getPerson().getPreferredName().getFamilyName();

			if (ra.getPerson().getPreferredName().getFamilyName2() != null)
				apellidos += " "
						+ ra.getPerson().getPreferredName().getFamilyName2();

			responsableAtencion.setApellidos(apellidos);

			if (ra.getPerson().getPreferredName().getGivenName() != null)
				nombres += ra.getPerson().getPreferredName().getGivenName();

			if (ra.getPerson().getPreferredName().getMiddleName() != null)
				nombres += " "
						+ ra.getPerson().getPreferredName().getMiddleName();

			responsableAtencion.setNombres(nombres);
			if (ra.getPerson().getControlAph() != null) {
				responsableAtencion.setControlAPHId(ra.getPerson()
						.getControlAph().getConceptId());
				responsableAtencion.setControlAPH(ra.getPerson()
						.getControlAph().getShortName());
			}
			responsableAtencion.setEsExterno(ra.getExterno());
			responsableAtencion.setRegistro(ra.getPerson().getRegistro());

			lista.add(responsableAtencion);

		}
		return lista;
	}

	private ArrayList<TeleasistenciaData> cargarTeleasistencias(
			Lesionado lesionado) {

		ArrayList<TeleasistenciaData> lista = new ArrayList<TeleasistenciaData>();
		ConceptsCode concepts = new ConceptsCode();
		String idMedico = null;
		for (ObservationData od : obsService
				.obtenerSolicitudesTeleasistencia(lesionado.getEncuentro())) {

			TeleasistenciaData teleasistenciaData = new TeleasistenciaData();
			teleasistenciaData.setTeleasistenciaId(od.getObsId());
			teleasistenciaData.setMotivoSolicitud((String) ObservationData
					.obtenerValorConcepto(od.getSons(),
							concepts.cMotivoSolicitudTeleasistencia()));
			teleasistenciaData.setDetalles((String) ObservationData
					.obtenerValorConcepto(od.getSons(),
							concepts.cDetalleSolicitudTeleasistencia()));
			teleasistenciaData.setFecha((Date) ObservationData
					.obtenerValorConcepto(od.getSons(),
							concepts.cFechaSolicitudTeleasistencia()));
			teleasistenciaData.setMedio((Integer) ObservationData
					.obtenerValorConcepto(od.getSons(),
							concepts.cMedioSolicitudTeleasistencia()));
			teleasistenciaData
					.setNotasEvolucion(new ArrayList<NotaEvolucion>());

			idMedico = (String) ObservationData.obtenerValorConcepto(
					od.getSons(), concepts.cUsuarioSolicitaTeleasistencia());

			if (idMedico != null) {
				ValuedItem<Integer, String> medicoSolicitante = new ValuedItem<Integer, String>();

				Integer concept = Integer.parseInt(idMedico);

				medicoSolicitante.setCode(concept);
				medicoSolicitante.setLabel(personService
						.obtenerPorUsuario(concept).getPreferredName()
						.toString());
				teleasistenciaData.setMedicoSolicitante(medicoSolicitante);
			}

			for (ObservationData odM : ObservationData.obtenerListaValores(
					od.getSons(), concepts.cNotaEvolucion())) {

				NotaEvolucion ne = new NotaEvolucion();

				Integer concept = (Integer) ObservationData
						.obtenerValorConcepto(odM.getSons(),
								concepts.cDiagnosticoPrincipalNotaEvolucion());
				if (concept != null) {
					ValuedItem<Integer, String> dxPrincipal = new ValuedItem<Integer, String>();

					dxPrincipal.setCode(concept);
					dxPrincipal.setLabel(conceptService
							.obtenerConcepto(concept).getShortName()
							+ " - "
							+ conceptService.obtenerConcepto(concept)
									.getDescription());
					ne.setDxPrincipal(dxPrincipal);
				}

				idMedico = (String) ObservationData.obtenerValorConcepto(
						odM.getSons(), concepts.cMedicoTratanteNotaEvolucion());

				// El medico

				if (idMedico != null) {
					ValuedItem<Integer, String> medicoTratante = new ValuedItem<Integer, String>();

					concept = Integer.parseInt(idMedico);

					medicoTratante.setCode(concept);
					medicoTratante.setLabel(personService
							.obtenerPorUsuario(concept).getPreferredName()
							.toString());
					ne.setMedicoTratante(medicoTratante);
				}

				ne.setFecha((Date) ObservationData.obtenerValorConcepto(
						odM.getSons(), concepts.cFechaNotaEvolucion()));
				ne.setRecomendaciones((String) ObservationData
						.obtenerValorConcepto(odM.getSons(),
								concepts.cRecomendacionesNotaEvolucion()));

				ne.setDxSecundarios(new ArrayList<ValuedItem<Integer, String>>());

				for (ObservationData odMD : ObservationData
						.obtenerListaValores(odM.getSons(),
								concepts.cDiagnosticoSecundarioNotaEvolucion())) {

					// concept =
					// (Integer)ObservationData.obtenerValorConcepto(odMD.getSons(),
					// concepts.cDiagnosticoSecundarioNotaEvolucion());
					concept = odMD.getValueCoded();
					if (concept != null) {
						ValuedItem<Integer, String> dxSecundario = new ValuedItem<Integer, String>();

						dxSecundario.setCode(concept);
						dxSecundario.setLabel(conceptService.obtenerConcepto(
								concept).getShortName()
								+ " - "
								+ conceptService.obtenerConcepto(concept)
										.getDescription());

						ne.getDxSecundarios().add(dxSecundario);
					}
				}

				teleasistenciaData.getNotasEvolucion().add(ne);
			}
			lista.add(teleasistenciaData);
		}

		return lista;
	}

	private CierreAtencion cargarDatosCierre(Lesionado lesionado) {

		CierreAtencion cierre = new CierreAtencion();
		ConceptsCode concepts = new ConceptsCode();
		String idMedico = null;

		ObservationData od = obsService.obtenerDatosCierre(lesionado
				.getEncuentro());

		cierre.setNota((String) ObservationData.obtenerValorConcepto(
				od.getSons(), concepts.cNotaAclaratoriaCierre()));
		Integer idTipoCierre = (Integer) ObservationData.obtenerValorConcepto(
				od.getSons(), concepts.cTipoCierreAtencion());

		if (idTipoCierre != null) {

			if (idTipoCierre.equals(concepts.cCierreAtencionAnulada()))
				cierre.setTipoCierre(CierreAtencion.TIPO_CIERRE_ANULACION);
			else if (idTipoCierre.equals(concepts.cCierreAtencionAprobada()))
				cierre.setTipoCierre(CierreAtencion.TIPO_CIERRE_APROBACION);
			else if (idTipoCierre.equals(concepts.cCierreAtencionNoAprobada()))
				cierre.setTipoCierre(CierreAtencion.TIPO_CIERRE_NOAPROBACION);
		}

		idMedico = (String) ObservationData.obtenerValorConcepto(od.getSons(),
				concepts.cUsuarioCierraAtencion());

		if (idMedico != null) {
			ValuedItem<Integer, String> medicoSolicitante = new ValuedItem<Integer, String>();

			Integer concept = Integer.parseInt(idMedico);

			medicoSolicitante.setCode(concept);
			medicoSolicitante.setLabel(personService.obtenerPorUsuario(concept)
					.getPreferredName().toString());
			cierre.setUsuarioCierra(medicoSolicitante);
		}

		return cierre;

	}

	private ArrayList<HallazgoData> cargarHallazgos(Encounter encuentro) {

		ArrayList<HallazgoData> lista = new ArrayList<HallazgoData>();
		ArrayList<Lesiones> listaLesiones = new ArrayList<Lesiones>();
		ConceptsCode concepts = new ConceptsCode();

		Boolean datoLesion = false;

		for (ObservationData od : obsService.obtenerHallazgos(encuentro)) {

			HallazgoData hallazgo = new HallazgoData();
			listaLesiones = new ArrayList<Lesiones>();
			hallazgo.setHallazgoId(od.getObsId());
			Double orden = (Double) ObservationData.obtenerValorConcepto(
					od.getSons(), concepts.cHallazgoOrden());
			if (orden != null)
				hallazgo.setOrden(orden.intValue());

			Double x = (Double) ObservationData.obtenerValorConcepto(
					od.getSons(), concepts.cHallazgoPosicionX());
			if (orden != null)
				hallazgo.setX(x);

			Double y = (Double) ObservationData.obtenerValorConcepto(
					od.getSons(), concepts.cHallazgoPosicionY());
			if (orden != null)
				hallazgo.setY(y);

			ArrayList<ValuedItem<Integer, String>> listaProc = new ArrayList<ValuedItem<Integer, String>>();
			for (ObservationData odP : ObservationData.obtenerListaValores(
					od.getSons(), concepts.cProcedimientoHallazgo())) {

				Integer concept = odP.getValueCoded();
				if (concept != null) {
					ValuedItem<Integer, String> dx = new ValuedItem<Integer, String>();

					dx.setCode(concept);
					dx.setLabel(conceptService.obtenerConcepto(concept)
							.getShortName());
					listaProc.add(dx);
				}
			}

			hallazgo.setProcedimientos(listaProc);

			HashMap<Integer, Lesiones> mapaLesiones = getCMapaLesiones();

			for (Integer concept : getConceptosLesiones()) {
				datoLesion = (Boolean) ObservationData.obtenerValorConcepto(
						od.getSons(), concept);
				if (datoLesion != null && datoLesion)
					listaLesiones.add(mapaLesiones.get(concept));
			}

			hallazgo.setLesiones(listaLesiones);

			lista.add(hallazgo);

		}

		return lista;
	}

	private List<Integer> getConceptosLesiones() {

		ConceptsCode concepts = new ConceptsCode();
		List<Integer> lista = new ArrayList<Integer>();

		lista.add(concepts.cLesionAbrasion());
		lista.add(concepts.cLesionAmputacion());
		lista.add(concepts.cLesionAplastamiento());
		lista.add(concepts.cLesionAvulsion());
		lista.add(concepts.cLesionContusion());
		lista.add(concepts.cLesionDolor());
		lista.add(concepts.cLesionEsguince());
		lista.add(concepts.cLesionFracturaAbierta());
		lista.add(concepts.cLesionFracturaCerrada());
		lista.add(concepts.cLesionHematoma());
		lista.add(concepts.cLesionHemorragia());
		lista.add(concepts.cLesionHerida());
		lista.add(concepts.cLesionHeridaArmaBlanca());
		lista.add(concepts.cLesionHeridaArmaFuego());
		lista.add(concepts.cLesionLaceracion());
		lista.add(concepts.cLesionMordida());
		lista.add(concepts.cLesionPicadura());
		lista.add(concepts.cLesionPuncion());
		lista.add(concepts.cLesionQuemadura());
		lista.add(concepts.cLesionTraumaCerrado());
		lista.add(concepts.cLesionTraumaPenetrante());

		return lista;
	}

	private HashMap<Integer, Lesiones> getCMapaLesiones() {

		ConceptsCode concepts = new ConceptsCode();
		HashMap<Integer, Lesiones> lista = new HashMap<Integer, HallazgoData.Lesiones>();

		lista.put(concepts.cLesionAbrasion(), Lesiones.abrasion);
		lista.put(concepts.cLesionAmputacion(), Lesiones.amputacion);
		lista.put(concepts.cLesionAplastamiento(), Lesiones.aplastamiento);
		lista.put(concepts.cLesionAvulsion(), Lesiones.avulsion);
		lista.put(concepts.cLesionContusion(), Lesiones.contusion);
		lista.put(concepts.cLesionDolor(), Lesiones.dolor);
		lista.put(concepts.cLesionEsguince(), Lesiones.esguince);
		lista.put(concepts.cLesionFracturaAbierta(), Lesiones.fracturaAbierta);
		lista.put(concepts.cLesionFracturaCerrada(), Lesiones.fracturaCerrada);
		lista.put(concepts.cLesionHematoma(), Lesiones.hematoma);
		lista.put(concepts.cLesionHemorragia(), Lesiones.hemorragia);
		lista.put(concepts.cLesionHerida(), Lesiones.herida);
		lista.put(concepts.cLesionHeridaArmaBlanca(), Lesiones.heridaArmaBlanca);
		lista.put(concepts.cLesionHeridaArmaFuego(), Lesiones.heridaArmaDeFuego);
		lista.put(concepts.cLesionLaceracion(), Lesiones.laceracion);
		lista.put(concepts.cLesionMordida(), Lesiones.mordida);
		lista.put(concepts.cLesionPicadura(), Lesiones.picadura);
		lista.put(concepts.cLesionPuncion(), Lesiones.puncion);
		lista.put(concepts.cLesionQuemadura(), Lesiones.quemadura);
		lista.put(concepts.cLesionTraumaCerrado(), Lesiones.traumaCerrado);
		lista.put(concepts.cLesionTraumaPenetrante(), Lesiones.traumaPenetrante);

		return lista;
	}

	/*********
	 * usuario Firma
	 */
	public ArchivoData getArchivoDataFirmaDigital(Integer usuarioId)
			throws TelesaludGWTException {
		try {
			List<UserDTO> users = readAll();
			for (UserDTO user : users) {
				Person person = getPersonById(user.getPersonId());

				if (user.getUserId().equals(usuarioId)) {
					return firmaToArchivoData(person.getFirma());
				}
			}
			return null;
		} catch (Exception e) {
			throw new TelesaludGWTException(e);
		}
	}

	public List<UserDTO> readAll() {
		return userDao.getAll();
	}

	public Person getPersonById(Integer personId) throws TelesaludException {
		return personDAO.findPerson(personId);
	}

	private ArchivoData firmaToArchivoData(String firma) {
		ArchivoData archivoData = new ArchivoData();

		if (firma != null) {
			StringTokenizer st = new StringTokenizer(firma, "(!|!)");
			archivoData.setTipoContenido(st.nextToken());
			archivoData.setNombreOriginalArchivo(st.nextToken());
			archivoData.setNombreArchivo(st.nextToken());
			return archivoData;
		}
		return null;
	}
}
