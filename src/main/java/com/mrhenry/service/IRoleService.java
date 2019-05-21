package com.mrhenry.service;

import java.util.List;

import com.mrhenry.model.Role;
import com.mrhenry.paging.IPageble;

public interface IRoleService {
	List<Role> findAll(IPageble pageble);
}
