package com.mrhenry.dao.impl;

import java.util.List;

import com.mrhenry.dao.ICategoryDAO;
import com.mrhenry.mapper.CategoryMapper;
import com.mrhenry.model.Category;
import com.mrhenry.paging.IPageble;

public class CategoryDAO extends AbstractDAO<Category> implements ICategoryDAO {

	@Override
	public List<Category> findAll(IPageble pageble) {

//		String sql = "SELECT * FROM category";
//		return query(sql, new CategoryMapper());
		
		StringBuilder sql = new StringBuilder("SELECT * FROM category ");
		if(pageble.getSorter() != null) {
			sql.append("ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+" ");
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null ) {
			sql.append("LIMIT "+pageble.getOffset()+","+pageble.getLimit());
		}
		return query(sql.toString(),new CategoryMapper());
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

	@Override
	public int getTotalItem() {
		String sql = "SELECT COUNT(*) FROM category";

		return count(sql);
	}

}
