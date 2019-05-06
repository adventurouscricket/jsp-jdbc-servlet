package com.mrhenry.dao.impl;

import java.util.List;

import com.mrhenry.dao.IUserDAO;
import com.mrhenry.mapper.UserMapper;
import com.mrhenry.model.User;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

	@Override
	public User login(String userName, String password, int status) {
		StringBuilder sql =new StringBuilder("SELECT * FROM user AS u ");
		sql.append("INNER JOIN role AS r ON r.id = u.roleid ");
		sql.append("WHERE username = ? AND password = ? AND status = ?");
		
		List<User> users = query(sql.toString(), new UserMapper(), userName, password, status);
		return users.isEmpty() ? null : users.get(0);
	}

}
