package com.mrhenry.dao;

import java.util.List;

import com.mrhenry.model.User;
import com.mrhenry.paging.IPageble;

public interface IUserDAO extends GenericDAO<User> {
	User login(String userName, String password, int status);
	List<User> findAll(IPageble pageble);
	User findOne(Long id);
	Long save(User user);
	void update(User user);
	void delete(Long id);
	int getTotalItem();
}
