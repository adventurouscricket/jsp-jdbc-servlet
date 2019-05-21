package com.mrhenry.service;

import java.util.List;

import com.mrhenry.model.User;
import com.mrhenry.paging.IPageble;

public interface IUserService {
	User login(String userName, String password, int status);
	List<User> findAll(IPageble pageble);
	User findOne(Long id);
	User save(User user);
	User update(User user);
	void delete(Long[] ids);
	int getTotalItem();
}
