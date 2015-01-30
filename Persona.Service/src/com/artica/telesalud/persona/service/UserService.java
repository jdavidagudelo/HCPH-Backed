package com.artica.telesalud.persona.service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.common.service.EncryptionService;
import com.artica.telesalud.persona.dao.PersonaDAOFactory;
import com.artica.telesalud.persona.dao.UserDAO;
import com.artica.telesalud.persona.model.impl.UserDTO;
import com.artica.telesalud.persona.service.user.PasswordDoesNotMatchException;
import com.artica.telesalud.persona.service.user.UserNotFoundException;

public class UserService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ENC_ALGORITM_NAME = "MD5";
	private static final String DEFAULT_ERROR = "The password does not match";
	private UserDAO userDao;

	public UserService(String daoClassName, HashMap<String, String> params){
		 userDao = DAOFactoryInstantiator.instantiateDaoFactory(PersonaDAOFactory.class, 
			daoClassName, params).getUserDao();
	}
	
	public UserDTO getValidUser(String username, String password) throws UserNotFoundException, PasswordDoesNotMatchException{
		
		UserDTO user = userDao.findUserByName(username);
		if( user == null){
			throw new UserNotFoundException(username);}
		String enc = encryptPassword(user.getSalt(), password);
		if( !enc.equals(user.getPassword()) ){
			throw new PasswordDoesNotMatchException(DEFAULT_ERROR+"::"+enc+"!="+user.getPassword()+"::"+user.getSalt()+", "+password);
		}
		return user;
	}
	
	public UserDTO findUserByName(String username){
		return userDao.findUserByName(username);
	}
	
	public UserDTO registerUser(String name, String password, String salt){
		
		UserDTO user = userDao.newBlankUser();
		
		user.setUsername(name);
		user.setSalt(salt);
		user.setPassword(password);
		
		return registerUser(user);
		
	}
	
	public  static String encryptPassword(String salt, String password){
		EncryptionService encryptionService = new EncryptionService();
		
		String enc = encryptionService.encrypt(password + salt, ENC_ALGORITM_NAME );
		
		return enc;
	}
	

	
	public UserDTO changePassword(UserDTO user, String newPassword){
		
		String enc = encryptPassword(user.getSalt(), newPassword);
		user.setPassword(enc);
		userDao.update(user);
		
		return user;
	}

	public List<UserDTO> readAll() {
		return userDao.getAll();
	}

	public UserDTO newBlankUser() {
		return userDao.newBlankUser();
	}

	public UserDTO deleteUser(UserDTO selectedUser) {
		return userDao.delete(selectedUser);
	}

	public UserDTO updateUser(UserDTO selectedUser) {
		return userDao.update(selectedUser);
	}

	public UserDTO registerUser(UserDTO user) {
		
		if( user.getPersonId() == null )
		{
			user.setPersonId(0);
		}
		user.setUuid(UUID.randomUUID().toString());
		
		if( user.getRole() == null )
		{
			user.setRoleId(0);
		}
		if( user.getSalt() == null )
		{
			user.setSalt(generateUserSalt());
		}
		String enc = encryptPassword(user.getSalt(), user.getPassword());
		user.setPassword(enc);
		
		if( user.getSystemId() == null )
		{
			user.setSystemId("DefaultSystemId");
		}
		
		user.setRetired(false);
		user.setDateCreated(new Date());
		
		userDao.insert(user);
		
		return user;
	}
	private String generateUserSalt()
	{
		return UUID.randomUUID().toString();
	}
	public UserDTO findUserById(Integer id){
		return userDao.findUserById(id);
	}
	
	public UserDTO findUserByPersonId(Integer personId){
		return userDao.findUserByPersonId(personId);
	}
	
	public List<UserDTO> getUsers(int limit, int offset){
		return userDao.getUsers(limit, offset);
	}
	
	public int countUsers(){
		return userDao.count();
	}
	
}
