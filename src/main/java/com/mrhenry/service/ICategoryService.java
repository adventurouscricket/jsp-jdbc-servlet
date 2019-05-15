package com.mrhenry.service;

import java.util.List;

import com.mrhenry.model.Category;
import com.mrhenry.paging.IPageble;

public interface ICategoryService {
	List<Category> findAll(IPageble pageble);
	Category save(Category category);
	Category update(Category category);
	void delete(Long[] ids);
	Category findOne(Long id);
	int getTotalItem();
}
