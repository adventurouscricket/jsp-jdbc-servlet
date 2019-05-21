package com.mrhenry.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.mrhenry.dao.IUserDAO;
import com.mrhenry.model.User;
import com.mrhenry.paging.IPageble;
import com.mrhenry.service.IUserService;

public class UserService implements IUserService {

	@Inject
	private IUserDAO userDao;
	
	@Override
	public User login(String userName, String password, int status) {
		return userDao.login(userName, password, status);
	}

	@Override
	public List<User> findAll(IPageble pageble) {
		
		return userDao.findAll(pageble);
	}

	@Override
	public User findOne(Long id) {
		
		return userDao.findOne(id);
	}

	@Override
	public User save(User user) {
		user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long id = userDao.save(user);
		return userDao.findOne(id);
	}

	@Override
	public User update(User user) {
		User oldUser = userDao.findOne(user.getId());
		user.setCreatedBy(oldUser.getCreatedBy());
		user.setCreatedDate(oldUser.getCreatedDate());
		user.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		userDao.update(user);
		
		return userDao.findOne(user.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			userDao.delete(id);
		}
	}

	@Override
	public int getTotalItem() {
		return userDao.getTotalItem();
	}

}
