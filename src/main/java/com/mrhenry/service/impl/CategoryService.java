package com.mrhenry.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.mrhenry.dao.ICategoryDAO;
import com.mrhenry.model.Category;
import com.mrhenry.paging.IPageble;
import com.mrhenry.service.ICategoryService;

public class CategoryService implements ICategoryService {

	@Inject
	ICategoryDAO categoryDao;

	public List<Category> findAll(IPageble pageble) {
		return categoryDao.findAll(pageble);
	}

	@Override
	public Category save(Category category) {

//		category.setCreatedBy("");
		category.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long id = categoryDao.save(category);

		return categoryDao.findOne(id);
	}

	@Override
	public Category update(Category category) {
		Category oldCategory = categoryDao.findOne(category.getId());
		category.setCreatedBy("");
		category.setCreatedDate(oldCategory.getCreatedDate());
//		category.setModifiedBy("");
		category.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		categoryDao.update(category);

		return categoryDao.findOne(category.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			categoryDao.delete(id);
		}
	}

	@Override
	public Category findOne(Long id) {

		return categoryDao.findOne(id);
	}

	@Override
	public int getTotalItem() {
		
		return categoryDao.getTotalItem();
	}

}
