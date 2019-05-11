package com.mrhenry.service;

import java.util.List;

import com.mrhenry.model.News;
import com.mrhenry.paging.IPageble;

public interface INewsService{
	List<News> findNewsByCategoryId(Long id);
	News save(News news );
	News update(News updateNews );
	void delete(Long[] ids );
//	List<News> findAll();
//	List<News> findAll(Integer offset, Integer limit, String orderName, String orderBy);
	List<News> findAll(IPageble pageble);
	int getTotalItem();
	News findOne(Long id);
}
