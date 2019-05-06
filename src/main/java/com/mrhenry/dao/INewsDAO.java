package com.mrhenry.dao;

import java.util.List;

import com.mrhenry.model.News;
import com.mrhenry.paging.IPageble;

public interface INewsDAO extends GenericDAO<News> {
	List<News> findNewsByCategoryId(Long id);

	Long save(News news);

	void update(News news);

	void delete(Long id);

	News findOne(Long id);

	// List<News> findAll();
	//List<News> findAll(Integer offset, Integer limit, String orderName, String orderBy);
	List<News> findAll(IPageble pageble);
	int getTotalItem();
}
