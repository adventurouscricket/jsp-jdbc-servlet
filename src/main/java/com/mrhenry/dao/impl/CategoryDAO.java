package com.mrhenry.dao.impl;

import java.util.List;

import com.mrhenry.dao.ICategoryDAO;
import com.mrhenry.mapper.CategoryMapper;
import com.mrhenry.model.Category;

public class CategoryDAO extends AbstractDAO<Category> implements ICategoryDAO {

	@Override
	public List<Category> findAll() {

		String sql = "SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public Long save(Category category) {
		String sql = "INSERT INTO category (name, code, createddate, modifieddate, createdby, modifiedby) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		return insert(sql, category.getName(), category.getCode(), category.getCreatedDate(),
				category.getModifiedDate(), category.getCreatedBy(), category.getModifiedBy());

	}

	@Override
	public void update(Category category) {
		String sql = "UPDATE category SET name=?, code=?, createddate=?, modifieddate=?, createdby=?, modifiedby=? WHERE id =?";
		updateAndDelete(sql, category.getName(), category.getCode(), category.getCreatedDate(),
				category.getModifiedDate(), category.getCreatedBy(), category.getModifiedBy(), category.getId());

	}

	@Override
	public void delete(Long id) {
		String sqlNews = "UPDATE news SET categoryid = null WHERE categoryid=?";
		updateAndDelete(sqlNews, id);
		
		String sql = "DELETE FROM category WHERE id=?";
		updateAndDelete(sql, id);

	}

	@Override
	public Category findOne(Long id) {

		String sql = "SELECT * FROM category WHERE id =?";
		Category category = queryFindOne(sql,new CategoryMapper(), id);
				
		return category;
	}

	@Override
	public Category findOneByCategoryCode(String code) {

		String sql = "SELECT * FROM category WHERE code =?";
		List<Category> categories = query(sql,new CategoryMapper(), code);
				
		return categories.isEmpty() ? null : categories.get(0);
	}

}
