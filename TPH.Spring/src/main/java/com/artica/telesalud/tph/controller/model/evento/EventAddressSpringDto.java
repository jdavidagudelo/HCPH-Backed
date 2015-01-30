package com.artica.telesalud.tph.controller.model.evento;

import java.io.Serializable;

import com.artica.telesalud.tph.model.evento.EventAddress;
/**
 * Clase utilizada por Spring para mapear direcciones de eventos a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class EventAddressSpringDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -521051274661125348L;
	/**
	 * 
	 */
	private Integer eventAddressId;
	private String mainWay;
	private String mainWayNumber;
	private String mainWaySecondaryNumber;
	private String mainWayDirection;
	private String secondaryWayDirection;
	private String secondaryWayNumber;
	private String secondaryWaySecondaryNumber;
	private String wayNumberIdentifier;
	private String blockName;
	private String blockNumber;
	private String homeName;
	private String homeNumber;
	public EventAddressSpringDto()
	{
		
	}
	public EventAddressSpringDto(EventAddress eventAddress)
	{
		eventAddressId=eventAddress.getEventAddressId();
		mainWay=eventAddress.getMainWay();
		mainWayNumber=eventAddress.getMainWayNumber();
		mainWaySecondaryNumber=eventAddress.getMainWaySecondaryNumber();
		mainWayDirection=eventAddress.getMainWayDirection();
		secondaryWayDirection=eventAddress.getSecondaryWayDirection();
		secondaryWayNumber=eventAddress.getSecondaryWayNumber();
		secondaryWaySecondaryNumber=eventAddress.getSecondaryWaySecondaryNumber();
		wayNumberIdentifier=eventAddress.getWayNumberIdentifier();
		blockName=eventAddress.getBlockName();
		blockNumber=eventAddress.getBlockNumber();
		homeName = eventAddress.getHomeName();
		homeNumber=	eventAddress.getHomeNumber();
	}
	/**
	 * @return the eventAddressId
	 */
	public Integer getEventAddressId() {
		return eventAddressId;
	}
	/**
	 * @param eventAddressId the eventAddressId to set
	 */
	public void setEventAddressId(Integer eventAddressId) {
		this.eventAddressId = eventAddressId;
	}
	/**
	 * @return the mainWay
	 */
	public String getMainWay() {
		return mainWay;
	}
	/**
	 * @param mainWay the mainWay to set
	 */
	public void setMainWay(String mainWay) {
		this.mainWay = mainWay;
	}
	/**
	 * @return the mainWayNumber
	 */
	public String getMainWayNumber() {
		return mainWayNumber;
	}
	/**
	 * @param mainWayNumber the mainWayNumber to set
	 */
	public void setMainWayNumber(String mainWayNumber) {
		this.mainWayNumber = mainWayNumber;
	}
	/**
	 * @return the mainWaySecondaryNumber
	 */
	public String getMainWaySecondaryNumber() {
		return mainWaySecondaryNumber;
	}
	/**
	 * @param mainWaySecondaryNumber the mainWaySecondaryNumber to set
	 */
	public void setMainWaySecondaryNumber(String mainWaySecondaryNumber) {
		this.mainWaySecondaryNumber = mainWaySecondaryNumber;
	}
	/**
	 * @return the mainWayDirection
	 */
	public String getMainWayDirection() {
		return mainWayDirection;
	}
	/**
	 * @param mainWayDirection the mainWayDirection to set
	 */
	public void setMainWayDirection(String mainWayDirection) {
		this.mainWayDirection = mainWayDirection;
	}
	/**
	 * @return the secondaryWayDirection
	 */
	public String getSecondaryWayDirection() {
		return secondaryWayDirection;
	}
	/**
	 * @param secondaryWayDirection the secondaryWayDirection to set
	 */
	public void setSecondaryWayDirection(String secondaryWayDirection) {
		this.secondaryWayDirection = secondaryWayDirection;
	}
	/**
	 * @return the secondaryWayNumber
	 */
	public String getSecondaryWayNumber() {
		return secondaryWayNumber;
	}
	/**
	 * @param secondaryWayNumber the secondaryWayNumber to set
	 */
	public void setSecondaryWayNumber(String secondaryWayNumber) {
		this.secondaryWayNumber = secondaryWayNumber;
	}
	/**
	 * @return the secondaryWaySecondaryNumber
	 */
	public String getSecondaryWaySecondaryNumber() {
		return secondaryWaySecondaryNumber;
	}
	/**
	 * @param secondaryWaySecondaryNumber the secondaryWaySecondaryNumber to set
	 */
	public void setSecondaryWaySecondaryNumber(String secondaryWaySecondaryNumber) {
		this.secondaryWaySecondaryNumber = secondaryWaySecondaryNumber;
	}
	/**
	 * @return the wayNumberIdentifier
	 */
	public String getWayNumberIdentifier() {
		return wayNumberIdentifier;
	}
	/**
	 * @param wayNumberIdentifier the wayNumberIdentifier to set
	 */
	public void setWayNumberIdentifier(String wayNumberIdentifier) {
		this.wayNumberIdentifier = wayNumberIdentifier;
	}
	/**
	 * @return the blockName
	 */
	public String getBlockName() {
		return blockName;
	}
	/**
	 * @param blockName the blockName to set
	 */
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	/**
	 * @return the blockNumber
	 */
	public String getBlockNumber() {
		return blockNumber;
	}
	/**
	 * @param blockNumber the blockNumber to set
	 */
	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}
	/**
	 * @return the homeName
	 */
	public String getHomeName() {
		return homeName;
	}
	/**
	 * @param homeName the homeName to set
	 */
	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}
	/**
	 * @return the homeNumber
	 */
	public String getHomeNumber() {
		return homeNumber;
	}
	/**
	 * @param homeNumber the homeNumber to set
	 */
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	
}
