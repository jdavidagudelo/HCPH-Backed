package com.artica.telesalud.persona.dao;

import java.util.List;

import com.artica.telesalud.common.data.DataConstraintViolation;
import com.artica.telesalud.persona.model.impl.UserDTO;


public interface UserDAO{
	public int count();
	public List<UserDTO> getAll();
	public List<UserDTO> getUsers(int limit, int offset);
	public UserDTO insert(UserDTO user);
	public UserDTO update(UserDTO user);
	public UserDTO newBlankUser();
	public UserDTO delete(UserDTO user);
	public List<DataConstraintViolation> validate(UserDTO user);
	public UserDTO findUserByName(String username);
	public UserDTO findUserById(Integer id);
	public UserDTO findUserByPersonId(Integer personId);
}