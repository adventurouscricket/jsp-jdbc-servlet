package com.mrhenry.dao.impl;

import java.util.List;

import com.mrhenry.dao.IRoleDAO;
import com.mrhenry.mapper.RoleMapper;
import com.mrhenry.model.Role;
import com.mrhenry.paging.IPageble;

public class RoleDAO extends AbstractDAO<Role> implements IRoleDAO{

	@Override
	public List<Role> findAll(IPageble pageble) {
		String sql = "SELECT * FROM role";
		return query(sql, new RoleMapper());
	}
	
}
