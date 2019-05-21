package com.mrhenry.dao;

import java.util.List;

import com.mrhenry.model.Role;
import com.mrhenry.paging.IPageble;

public interface IRoleDAO extends GenericDAO<Role>{
	List<Role> findAll(IPageble pageble);
}
