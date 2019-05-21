package com.mrhenry.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mrhenry.model.Role;

public class RoleMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs) {

		Role role = new Role();
		try {
			role.setId(rs.getLong("id"));
			role.setName(rs.getString("name"));
			role.setCode(rs.getString("code"));
			role.setCreatedDate(rs.getTimestamp("createddate"));
			role.setModifiedDate(rs.getTimestamp("modifieddate"));
			role.setCreatedBy(rs.getString("createdby"));
			role.setModifiedBy(rs.getString("modifiedby"));
			
			return role;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
