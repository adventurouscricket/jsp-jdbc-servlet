package com.mrhenry.service.impl;

import javax.inject.Inject;

import com.mrhenry.dao.IUserDAO;
import com.mrhenry.model.User;
import com.mrhenry.service.IUserService;

public class UserService implements IUserService {

	@Inject
	private IUserDAO userDao;
	
	@Override
	public User login(String userName, String password, int status) {
		// TODO Auto-generated method stub
		return userDao.login(userName, password, status);
	}

}
