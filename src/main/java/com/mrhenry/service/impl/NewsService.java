package com.mrhenry.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.mrhenry.dao.ICategoryDAO;
import com.mrhenry.dao.INewsDAO;
import com.mrhenry.model.Category;
import com.mrhenry.model.News;
import com.mrhenry.paging.IPageble;
import com.mrhenry.service.INewsService;

public class NewsService implements INewsService {

	@Inject
	INewsDAO newsDao;

	@Inject
	ICategoryDAO categoryDao;

	public List<News> findNewsByCategoryId(Long id) {
		return newsDao.findNewsByCategoryId(id);
	}

	@Override
	public News save(News news) {
		// not use for RESFULL
//		Long id = newsDao.save(news);
//		System.out.println(id);
//		return null;

		// update categoryId by categoryCode
		Category category = categoryDao.findOneByCategoryCode(news.getCategoryCode());
		news.setCategoryId(category.getId());

		// use for RESFULL
		news.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		// news.setCreatedBy("");

		Long id = newsDao.save(news);
		return newsDao.findOne(id);
	}

	@Override
	public News update(News updateNews) {

		News oldNews = newsDao.findOne(updateNews.getId());
		updateNews.setCreatedBy(oldNews.getCreatedBy());
		updateNews.setCreatedDate(oldNews.getCreatedDate());
		// updateNews.setModifiedBy("");
		updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));

		// update categoryId by categoryCode
		Category category = categoryDao.findOneByCategoryCode(updateNews.getCategoryCode());
		updateNews.setCategoryId(category.getId());

		newsDao.update(updateNews);
		return newsDao.findOne(updateNews.getId());
	}

	@Override
	public void delete(Long[] ids) {
		// newsDao.delete(id);
		for (Long id : ids) {
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

	@Override
	public News findOne(Long id) {
		News news = newsDao.findOne(id);
		Category category = categoryDao.findOne(news.getCategoryId());
		news.setCategoryCode(category.getCode());

		return news;
	}

}
