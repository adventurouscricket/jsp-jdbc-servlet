package com.mrhenry.dao.impl;

import java.util.List;

import com.mrhenry.dao.INewsDAO;
import com.mrhenry.mapper.NewsMapper;
import com.mrhenry.model.News;
import com.mrhenry.paging.IPageble;

public class NewsDAO extends AbstractDAO<News> implements INewsDAO {

	@Override
	public List<News> findNewsByCategoryId(Long id) {

		String sql = "SELECT * FROM news Where categoryId = ?";
		return query(sql, new NewsMapper(), id);
	}

	@Override
	public Long save(News news) {

		String sql = "INSERT INTO news (title, thumbnail, shortdescription, content, "
				+ "categoryid, createddate, modifieddate, createdby, modifiedby) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		return insert(sql, news.getTitle(), news.getThumbnail(), news.getShortdescription(), news.getContent(),
				news.getCategoryId(), news.getCreatedDate(), news.getModifiedDate(), news.getCreatedBy(),
				news.getModifiedBy());
	}

	@Override
	public void update(News news) {
		String sql = "UPDATE news SET title = ?, thumbnail = ?, shortdescription = ?, content = ?, categoryid = ?, createddate = ?,"
				+ "modifieddate = ?, createdby = ?, modifiedby = ? WHERE id = ?";
		updateAndDelete(sql, news.getTitle(), news.getThumbnail(), news.getShortdescription(), news.getContent(),
				news.getCategoryId(), news.getCreatedDate(), news.getModifiedDate(), news.getCreatedBy(),
				news.getModifiedBy(), news.getId());
	}

	@Override
	public void delete(Long id) {
		String sqlComment = "DELETE FROM comment WHERE new_id = ?";
		updateAndDelete(sqlComment, id);

		String sqlNews = "DELETE FROM news WHERE id = ?";
		updateAndDelete(sqlNews, id);
	}

	@Override
	public News findOne(Long id) {
		String sql = "SELECT * FROM news Where id = ?";
		News news = queryFindOne(sql, new NewsMapper(), id);

		return news;
	}

//	@Override
//	public List<News> findAll() {
//		StringBuilder sql = new StringBuilder("SELECT * FROM news");
//		
//		return query(sql.toString(), new NewsMapper());
//	}

	@Override
//	public List<News> findAll(Integer offset, Integer limit, String orderName, String orderBy) {
	public List<News> findAll(IPageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news ");

//		if(orderName!=null) {
//			sql.append("ORDER BY "+orderName+" "+orderBy+" ");
//		}
//		
//		if (offset != null && limit != null) {
//			sql.append(" LIMIT " + offset + ", " + limit + "");
//		}

		if (pageble.getSorter() != null) {
			sql.append("ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + " ");
		}

		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}

		return query(sql.toString(), new NewsMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news";

		return count(sql);
	}

}
