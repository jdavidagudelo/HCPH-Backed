package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Usuario implements Serializable {

	private Integer userId;
	private String login;
	private String password;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String direccion;
	private String telefono;
	private String registro;
	private String email;
	private Integer rolId;
	private String rolName;
	private Integer controlAPHId;
	private String controlAPH;
	
	public String getControlAPH() {
		return controlAPH;
	}
	public void setControlAPH(String controlAPH) {
		this.controlAPH = controlAPH;
	}
	private ArchivoData archivoDataFirmaDigital;
	
	public ArchivoData getArchivoDataFirmaDigital() {
		return archivoDataFirmaDigital;
	}
	public void setArchivoDataFirmaDigital(ArchivoData archivoDataFirmaDigital) {
		this.archivoDataFirmaDigital = archivoDataFirmaDigital;
	}
	public Integer getControlAPHId() {
		return controlAPHId;
	}
	public void setControlAPHId(Integer controlAPHId) {
		this.controlAPHId = controlAPHId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLogin() {
		return login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getRegistro() {
		return registro;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}
	public Integer getRolId() {
		return rolId;
	}
	public void setRolName(String rolName) {
		this.rolName = rolName;
	}
	public String getRolName() {
		return rolName;
	}
	public String getNombreCompleto() {
		return null;
	}
}
