package com.mrhenry.service;

import java.util.List;

import com.mrhenry.model.Category;

public interface ICategoryService {
	List<Category> findAll();
	Category save(Category category);
	Category update(Category category);
	void delete(Long[] ids);
	Category findOne(Long id);
}
