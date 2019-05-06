package com.mrhenry.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mrhenry.model.Category;

public class CategoryMapper implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet resultSet) {

		try {
			Category category = new Category();
			category.setId(resultSet.getLong("id"));
			category.setName(resultSet.getString("name"));
			category.setCode(resultSet.getString("code"));
			category.setCreatedDate(resultSet.getTimestamp("createddate"));
			category.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			category.setCreatedBy(resultSet.getString("createdby"));
			category.setModifiedBy(resultSet.getString("modifiedby"));
			
			return category;
		} catch (SQLException e) {
			return null;
		}
	}

}
