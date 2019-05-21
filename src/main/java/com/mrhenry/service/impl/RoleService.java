package com.mrhenry.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.mrhenry.dao.IRoleDAO;
import com.mrhenry.model.Role;
import com.mrhenry.paging.IPageble;
import com.mrhenry.service.IRoleService;

public class RoleService implements IRoleService{

	@Inject
	IRoleDAO roleDao;
	
	@Override
	public List<Role> findAll(IPageble pageble) {
		
		return roleDao.findAll(pageble);
	}
	
}
