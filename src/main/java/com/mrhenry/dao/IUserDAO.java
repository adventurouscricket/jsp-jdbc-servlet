package com.mrhenry.dao;

import com.mrhenry.model.User;

public interface IUserDAO extends GenericDAO<User> {
	User login(String userName, String password, int status);
}
