package com.artica.telesalud.tph.controller.model.user;

import java.io.Serializable;

import com.artica.telesalud.persona.model.impl.UserDTO;
import com.artica.telesalud.tph.controller.model.patient.PersonSpringDto;
import com.artica.telesalud.tph.model.person.Person;
/**
 * Clase utilizada para mapear usuarios a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class UserSpringDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userName;
	private String personName;
	private String roleName;
	private Integer role;
	private PersonSpringDto person;
	
	
	
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
	public UserSpringDto() {
		super();
	}
	public UserSpringDto(UserDTO user, Person person, String roleName)
	{
		if(user == null || person == null )
		{
			return;
		}
		this.person = new PersonSpringDto(person);
		this.setPersonName(person.getPreferredName().toString());
		this.setRole(user.getRoleId());
		this.setRoleName(roleName);
		this.setUserId(user.getUserId());
		this.setUserName(user.getUsername());
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the personName
	 */
	public String getPersonName() {
		return personName;
	}
	/**
	 * @param personName the personName to set
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the role
	 */
	public Integer getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Integer role) {
		this.role = role;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((personName == null) ? 0 : personName.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		if (!(obj instanceof UserSpringDto)) {
			return false;
		}
		UserSpringDto other = (UserSpringDto) obj;
		if (personName == null) {
			if (other.personName != null) {
				return false;
			}
		} else if (!personName.equals(other.personName)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		if (roleName == null) {
			if (other.roleName != null) {
				return false;
			}
		} else if (!roleName.equals(other.roleName)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserSpringDto [userId=" + userId + ", userName=" + userName
				+ ", personName=" + personName + ", roleName=" + roleName
				+ ", role=" + role + "]";
	}

	
}
