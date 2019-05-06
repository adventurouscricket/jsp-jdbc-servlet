package com.mrhenry.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mrhenry.model.Role;
import com.mrhenry.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet resultSet) {

		try {
			User user = new User();
			user.setId(resultSet.getLong("id"));
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setFullname(resultSet.getString("fullname"));
			user.setStatus(resultSet.getInt("status"));
			user.setRoleid(resultSet.getLong("roleid"));
			user.setCreatedDate(resultSet.getTimestamp("createddate"));
			user.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			user.setCreatedBy(resultSet.getString("createdby"));
			user.setModifiedBy(resultSet.getString("modifiedby"));
			
			Role role = new Role();
			role.setCode(resultSet.getString("code"));
			role.setName(resultSet.getString("name"));
			user.setRole(role);
			
			return user;
		} catch (SQLException e) {
			return null;
		}
	}

}
