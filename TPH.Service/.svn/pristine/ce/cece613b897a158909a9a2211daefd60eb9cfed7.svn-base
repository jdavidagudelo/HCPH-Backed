package com.artica.telesalud.tph.server.util;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.artica.telesalud.client.shared.ValuedItem;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;

public class PDFUtil {

	private Integer fontValue;
	private Integer paddingTables;
	private DecimalFormat nf;
	private Font fontTitle, fontTextBold, fontTextoNormal, fontHeader;

	public PDFUtil() {
		this.setFontValue(Font.HELVETICA);
		this.setPaddingTables(2);
		nf = (DecimalFormat) DecimalFormat.getInstance();
		nf.applyPattern("#,###,##0.##");
		fontTitle = new Font(fontValue, 11, Font.BOLD, Color.BLACK);
		fontTextBold = new Font(fontValue, 10, Font.BOLD, Color.BLACK);
		fontTextoNormal = new Font(fontValue, 10, Font.NORMAL, Color.BLACK);
		fontHeader = new Font(fontValue, 13, Font.BOLD, Color.BLACK);

	}

	/**
	 * método que crea una fuente para los titulos del pdf
	 * 
	 * @return
	 */
	public Font getFontTitle() {
		return this.fontTitle;
	}

	/**
	 * 
	 * @return
	 */
	public Font getFontTextBold() {
		return this.fontTextBold;
	}

	/**
	 * 
	 * @return
	 */
	public Font getFontTextNormal() {
		return this.fontTextoNormal;
	}

	/**
	 * 
	 * @return
	 */
	public Font getFontHeader() {
		return this.fontHeader;
	}

	/**
	 * @return the fontValue
	 */
	public Integer getFontValue() {
		return fontValue;
	}

	/**
	 * @param fontValue
	 *            the fontValue to set
	 */
	public void setFontValue(Integer fontValue) {
		this.fontValue = fontValue;
	}

	/**
	 * 
	 * @return
	 */
	public Color getBackground() {
		return Color.GRAY;
	}

	/**
	 * @return the paddingTables
	 */
	public Integer getPaddingTables() {
		return paddingTables;
	}

	/**
	 * @param paddingTables
	 *            the paddingTables to set
	 */
	public void setPaddingTables(Integer paddingTables) {
		this.paddingTables = paddingTables;
	}

	/**
	 * @return the nf
	 */
	public DecimalFormat getNf() {
		return nf;
	}

	/**
	 * @param nf
	 *            the nf to set
	 */
	public void setNf(DecimalFormat nf) {
		this.nf = nf;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public String formatDate(Date date) {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy h:mm a");
		return date == null ? "" : formatDate.format(date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public String formatDateOutHour(Date date) {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
		return date == null ? "" : formatDate.format(date);
	}

	/**
	 * Este m�todo recibe un double y lo convierte en String con el formato
	 * decimal
	 * 
	 * @param valor
	 * @return
	 */
	public String doubleToString(Double valor) {
		if (valor == null)
			return "";
		return nf.format(valor);
	}

	/**
	 * 
	 * @param cols
	 * @param border
	 * @param colorBorde
	 * @param widths
	 * @return
	 * @throws DocumentException
	 */
	public Table createTable(int cols, int border, Color colorBorde,
			int[] widths, int padding) throws DocumentException {
		Table tableItems = new Table(cols);
		tableItems.setWidth(100);
		tableItems.setPadding(padding);
		tableItems.setSpacing(0);
		tableItems.setWidths(widths);
		tableItems.setBorderWidth(border);
		tableItems.setBorderColor(colorBorde);
		tableItems.setBorder(border);
		return tableItems;
	}

	/**
	 * 
	 * @param texto
	 * @param colspan
	 * @return
	 * @throws BadElementException
	 */
	public Cell addCell(Paragraph texto, int colspan)
			throws BadElementException {

		Cell cell = new Cell(texto);
		cell.setBorder(0);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setLeading(14);
		cell.setColspan(colspan);
		return cell;
	}

	/**
	 * 
	 * @param texto
	 * @return
	 * @throws BadElementException
	 */
	public Cell addCell(Paragraph texto) throws BadElementException {
		return addCell(texto, 1);
	}

	/**
	 * 
	 * @param texto
	 * @return
	 * @throws BadElementException
	 */
	public Cell addCellHeader(Paragraph texto) throws BadElementException {
		Cell cell = new Cell();
		cell.setBackgroundColor(this.getBackground());
		cell.setColspan(1);
		cell.add(texto);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}

	/**
	 * 
	 * @param label
	 * @param valor
	 * @return
	 */
	public Paragraph adicionarTexto(String label, String valor) {
		Paragraph texto = new Paragraph();
		texto.setLeading(14);
		texto.add(new Chunk(label, this.getFontTextBold()));
		texto.add(new Chunk(": ", this.getFontTextNormal()));
		if (valor != null)
			texto.add(new Chunk(valor, this.getFontTextNormal()));

		return texto;
	}

	/**
	 * 
	 * @param toConcat
	 * @return
	 */
	public String concatString(String[] toConcat) {
		StringBuilder concat = new StringBuilder();
		for (String value : toConcat) {
			if (value != null) {
				if (!value.trim().equals(""))
					concat.append(value + " ");
			} else
				concat.append("");

		}
		return concat.toString().trim();
	}

	/**
	 * Busca la clave en un listado de value Item y lo devuelve en un String
	 * 
	 * @param option
	 * @param list
	 * @return
	 */
	public String getStringToListValue(Integer option,
			ArrayList<ValuedItem<Integer, String>> list) {
		if (option == null) {
			return "";
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCode().equals(option))
				return list.get(i).getLabel();
		}
		return null;
	}

	/**
	 * Convierte un listado en un String separado por renglones
	 * 
	 * @param value
	 * @return
	 */
	public String getStringListValuedItem(
			List<ValuedItem<Integer, String>> list, String separator) {
		StringBuilder value = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			value.append(i != list.size() - 1 ? list.get(i).getLabel()
					+ separator : list.get(i).getLabel());
		}
		return value.toString().trim();
	}

	/**
	 * 
	 * @param fechaNacimiento
	 * @param fechaPresente
	 * @return
	 */
	public Integer difAnios(Date fechaNacimiento, Date fechaPresente) { // fecha_nac
																		// debe
																		// tener
																		// el
																		// formato
																		// dd/MM/yyyy
		if (fechaPresente == null)
			return null;
		if (fechaNacimiento == null)
			return null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String hoy = formato.format(fechaPresente);
		String fechaNac = formato.format(fechaNacimiento);
		String[] dat1 = fechaNac.split("/");
		String[] dat2 = hoy.split("/");
		int anos = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
		int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
		if (mes < 0) {
			anos = anos - 1;
		} else if (mes == 0) {
			int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
			if (dia > 0) {
				anos = anos - 1;
			}
		}
		return anos;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public String nullToEmpty(String value) {
		return (value != null ? value : "");
	}

}
