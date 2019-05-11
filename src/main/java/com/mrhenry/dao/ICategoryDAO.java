package com.mrhenry.dao;

import java.util.List;

import com.mrhenry.model.Category;

public interface ICategoryDAO extends GenericDAO<Category> {
	List<Category> findAll();
	Long save(Category category);
	void update(Category category);
	void delete(Long id);
	Category findOne(Long id);
	Category findOneByCategoryCode(String code);
}
