package com.mrhenry.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.mrhenry.dao.INewsDAO;
import com.mrhenry.model.News;
import com.mrhenry.paging.IPageble;
import com.mrhenry.service.INewsService;

public class NewsService implements INewsService {

	@Inject
	INewsDAO newsDao;

	public List<News> findNewsByCategoryId(Long id) {
		return newsDao.findNewsByCategoryId(id);
	}

	@Override
	public News save(News news) {
		// not use for RESFULL
//		Long id = newsDao.save(news);
//		System.out.println(id);
//		return null;

		// use for RESFULL
		news.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		news.setCreatedBy("");
		Long id = newsDao.save(news);
		return newsDao.findOne(id);
	}

	@Override
	public News update(News updateNews) {
		
		News oldNews = newsDao.findOne(updateNews.getId());
		updateNews.setCreatedBy(oldNews.getCreatedBy());
		updateNews.setCreatedDate(oldNews.getCreatedDate());
		updateNews.setModifiedBy("");
		updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		newsDao.update(updateNews);
		return newsDao.findOne(updateNews.getId());
	}

	@Override
	public void delete(Long[] ids) {
		//newsDao.delete(id);
		for(Long id: ids) {
			newsDao.delete(id);
		}
	}

//	@Override
//	public List<News> findAll() {
//		return newsDao.findAll();
//	}
	
	@Override
//	public List<News> findAll(Integer offset, Integer limit, String orderName, String orderBy) {
	public List<News> findAll(IPageble pageble) {
//		return newsDao.findAll(offset, limit,orderName,orderBy);
		
		return newsDao.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		
		return newsDao.getTotalItem();
	}

}
