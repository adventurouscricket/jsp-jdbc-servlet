package com.mrhenry.service;

import com.mrhenry.model.User;

public interface IUserService {
	User login(String userName, String password, int status);
}
