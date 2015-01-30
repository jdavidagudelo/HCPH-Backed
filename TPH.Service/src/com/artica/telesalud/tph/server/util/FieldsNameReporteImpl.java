package com.artica.telesalud.tph.server.util;

import java.io.IOException;
import java.util.Properties;

import com.artica.telesalud.tph.client.reporte.i18n.FieldsNameReporte;

public class FieldsNameReporteImpl implements FieldsNameReporte {

	private Properties prop;

	public FieldsNameReporteImpl() {
		prop = new Properties();
		try {
			prop.load(FieldsNameReporte.class
					.getResourceAsStream("FieldsNameReporte.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String legendHistoriaClinica() {

		return prop.getProperty("legendHistoriaClinica");
	}

	@Override
	public String legendInfoPaciente() {

		return prop.getProperty("legendInfoPaciente");
	}

	@Override
	public String labelTipoDocumento() {

		return prop.getProperty("labelTipoDocumento");
	}

	@Override
	public String labelDocumento() {

		return prop.getProperty("labelDocumento");
	}

	@Override
	public String btnReporte() {

		return prop.getProperty("btnReporte");
	}

	@Override
	public String legendResponsable() {

		return prop.getProperty("legendResponsable");
	}

	@Override
	public String labelRegistro() {

		return prop.getProperty("labelRegistro");
	}

	@Override
	public String legendEvento() {

		return prop.getProperty("legendEvento");
	}

	@Override
	public String labelFechaOcurrencia() {

		return prop.getProperty("labelFechaOcurrencia");
	}

	@Override
	public String labelFechaDesde() {

		return prop.getProperty("labelFechaDesde");
	}

	@Override
	public String labelFechaHasta() {

		return prop.getProperty("labelFechaHasta");
	}

	@Override
	public String labelNumeroEmergencia() {

		return prop.getProperty("labelNumeroEmergencia");
	}

	@Override
	public String labelDepartamento() {

		return prop.getProperty("labelDepartamento");
	}

	@Override
	public String labelMunicipio() {

		return prop.getProperty("labelMunicipio");
	}

	@Override
	public String labelTipoEvento() {

		return prop.getProperty("labelTipoEvento");
	}

	@Override
	public String labelEstado() {

		return prop.getProperty("labelEstado");
	}

	@Override
	public String radioActivo() {

		return prop.getProperty("radioActivo");
	}

	@Override
	public String radioFinalizado() {

		return prop.getProperty("radioFinalizado");
	}

	@Override
	public String columnaDocumento() {

		return prop.getProperty("columnaDocumento");
	}

	@Override
	public String columnaNombre() {

		return prop.getProperty("columnaNombre");
	}

	@Override
	public String columnaImprimir() {

		return prop.getProperty("columnaImprimir");
	}

	@Override
	public String btnVolver() {

		return prop.getProperty("btnVolver");
	}

	@Override
	public String columnaFecha() {

		return prop.getProperty("columnaFecha");
	}

	@Override
	public String columnaEstado() {

		return prop.getProperty("columnaEstado");
	}

	@Override
	public String columnaDireccion() {

		return prop.getProperty("columnaDireccion");
	}

	@Override
	public String columnaCiudad() {

		return prop.getProperty("columnaCiudad");
	}

	@Override
	public String columnaCausa() {

		return prop.getProperty("columnaCausa");
	}

	@Override
	public String columnaNumeroEmergencia() {

		return prop.getProperty("columnaNumeroEmergencia");
	}

	@Override
	public String columnaVer() {

		return prop.getProperty("columnaVer");
	}

	@Override
	public String btnGrafica() {

		return prop.getProperty("btnGrafica");
	}

	@Override
	public String errorFecha() {

		return prop.getProperty("errorFecha");
	}

	@Override
	public String errorNumeroCasoNoNumerico() {

		return prop.getProperty("errorNumeroCasoNoNumerico");
	}

	@Override
	public String errorNumeroCaso() {

		return prop.getProperty("errorNumeroCaso");
	}

	@Override
	public String errorFechaMayor() {

		return prop.getProperty("errorFechaMayor");
	}

	@Override
	public String rptTitle() {

		return prop.getProperty("rptTitle");
	}

	@Override
	public String rptDepto() {

		return prop.getProperty("rptDepto");
	}

	@Override
	public String rptMunicipio() {

		return prop.getProperty("rptMunicipio");
	}

	@Override
	public String rptDireccion() {

		return prop.getProperty("rptDireccion");
	}

	@Override
	public String rptZona() {

		return prop.getProperty("rptZona");
	}

	@Override
	public String rptCausaOrigen() {

		return prop.getProperty("rptCausaOrigen");
	}

	@Override
	public String rptNumeroCaso() {

		return prop.getProperty("rptNumeroCaso");
	}

	@Override
	public String rptHoraLLamada() {

		return prop.getProperty("rptHoraLLamada");
	}

	@Override
	public String rptPlaca() {

		return prop.getProperty("rptPlaca");
	}

	@Override
	public String rptHoraDespacho() {

		return prop.getProperty("rptHoraDespacho");
	}

	@Override
	public String rptHoraLlegadaSitio() {

		return prop.getProperty("rptHoraLlegadaSitio");
	}

	@Override
	public String rptHoraRegreso() {

		return prop.getProperty("rptHoraRegreso");
	}

	@Override
	public String rptTipoDocumento() {

		return prop.getProperty("rptTipoDocumento");
	}

	@Override
	public String rptDocumento() {

		return prop.getProperty("rptDocumento");
	}

	@Override
	public String rptNombreCompleto() {

		return prop.getProperty("rptNombreCompleto");
	}

	@Override
	public String rptPacientesAt() {

		return prop.getProperty("rptPacientesAt");
	}

}
