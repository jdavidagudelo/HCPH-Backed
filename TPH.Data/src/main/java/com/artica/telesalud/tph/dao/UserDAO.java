package com.artica.telesalud.tph.dao;

import com.artica.telesalud.tph.model.user.User;


public interface UserDAO {
	
	public User getUserByPerson(Integer personId);
	public User getUser(Integer userId);
}
