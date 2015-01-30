package com.artica.telesalud.persona.model.impl;

import java.io.Serializable;
import java.util.Date;


public class UserDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String systemId;
	private String username;
	private String password;
	private String salt;
	private String secretQuestion;
	private String secretAnswer;
	private RoleDTO role;
	private Integer roleId;
	private Integer creator;
	private Date dateCreated;
	private Integer changedBy;
	private Date dateChanged;
	private Integer personId;
	private Boolean retired;
	private Integer retiredBy;
	private Date dateRetired;
	private String retireReason;
	private String uuid;
	
	/**
	 * @return the role
	 */
	public RoleDTO getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(RoleDTO role) {
		this.role = role;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the systemId
	 */
	public String getSystemId() {
		return systemId;
	}
	/**
	 * @param systemId the systemId to set
	 */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * @return the secretQuestion
	 */
	public String getSecretQuestion() {
		return secretQuestion;
	}
	/**
	 * @param secretQuestion the secretQuestion to set
	 */
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	/**
	 * @return the secretAnswer
	 */
	public String getSecretAnswer() {
		return secretAnswer;
	}
	/**
	 * @param secretAnswer the secretAnswer to set
	 */
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}
	/**
	 * @return the role
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * @param role the role to set
	 */
	public void setRoleId(Integer role) {
		this.roleId = role;
	}
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
	/**
	 * @return the changedBy
	 */
	public Integer getChangedBy() {
		return changedBy;
	}
	/**
	 * @param changedBy the changedBy to set
	 */
	public void setChangedBy(Integer changedBy) {
		this.changedBy = changedBy;
	}
	/**
	 * @return the dateChanged
	 */
	public Date getDateChanged() {
		return dateChanged;
	}
	/**
	 * @param dateChanged the dateChanged to set
	 */
	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
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
	 * @return the retired
	 */
	public Boolean getRetired() {
		return retired;
	}
	/**
	 * @param retired the retired to set
	 */
	public void setRetired(Boolean retired) {
		this.retired = retired;
	}
	/**
	 * @return the retiredBy
	 */
	public Integer getRetiredBy() {
		return retiredBy;
	}
	/**
	 * @param retiredBy the retiredBy to set
	 */
	public void setRetiredBy(Integer retiredBy) {
		this.retiredBy = retiredBy;
	}
	/**
	 * @return the dateRetired
	 */
	public Date getDateRetired() {
		return dateRetired;
	}
	/**
	 * @param dateRetired the dateRetired to set
	 */
	public void setDateRetired(Date dateRetired) {
		this.dateRetired = dateRetired;
	}
	/**
	 * @return the retireReason
	 */
	public String getRetireReason() {
		return retireReason;
	}
	/**
	 * @param retireReason the retireReason to set
	 */
	public void setRetireReason(String retireReason) {
		this.retireReason = retireReason;
	}
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}