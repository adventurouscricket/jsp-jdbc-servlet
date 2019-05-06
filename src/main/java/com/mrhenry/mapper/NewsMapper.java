package com.mrhenry.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mrhenry.model.News;

public class NewsMapper implements RowMapper<News> {

	@Override
	public News mapRow(ResultSet resultSet) {

		try {
			News news = new News();
			news.setId(resultSet.getLong("id"));
			news.setTitle(resultSet.getString("title"));
			news.setThumbnail(resultSet.getString("thumbnail"));
			news.setShortdescription(resultSet.getString("shortdescription"));
			news.setContent(resultSet.getString("content"));
			news.setCategoryId(resultSet.getLong("categoryid"));
			news.setCreatedDate(resultSet.getTimestamp("createddate"));
			news.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			news.setCreatedBy(resultSet.getString("createdby"));
			news.setModifiedBy(resultSet.getString("modifiedby"));
			
			return news;
		} catch (SQLException e) {
			return null;
		}
	}

}
