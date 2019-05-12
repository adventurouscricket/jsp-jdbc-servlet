package com.mrhenry.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrhenry.model.News;
import com.mrhenry.model.User;
import com.mrhenry.service.INewsService;
import com.mrhenry.utils.HttpUtil;
import com.mrhenry.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-news" })
public class NewsAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private INewsService newService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // để nhận định dạng UTF-8 khi client gửi lên
		response.setContentType("application/json"); // trả về định dạng JSON để cho client hiểu
		// HttpUtil.of(request.getReader()); // convert JSON --> String
		News news = HttpUtil.of(request.getReader()).toModel(News.class); // Mapper String --> New model
		news.setCreatedBy(((User)SessionUtil.getInstance().getValue(request, "USER")).getUsername());
		news = newService.save(news);

		HttpUtil.of(request.getReader()).toJson(response, news);
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // để nhận định dạng UTF-8 khi client gửi lên
		response.setContentType("application/json"); // trả về định dạng JSON để cho client hiểu
		// HttpUtil.of(request.getReader()); // convert JSON --> String
		News news = HttpUtil.of(request.getReader()).toModel(News.class); // Mapper String --> New model
		news.setModifiedBy(((User) SessionUtil.getInstance().getValue(request, "USER")).getUsername());
		news = newService.update(news);

		HttpUtil.of(request.getReader()).toJson(response, news);

		System.out.println(news.getContent());
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // để nhận định dạng UTF-8 khi client gửi lên
		response.setContentType("application/json"); // trả về định dạng JSON để cho client hiểu
		// HttpUtil.of(request.getReader()); // convert JSON --> String
		News news = HttpUtil.of(request.getReader()).toModel(News.class); // Mapper String --> New model
		newService.delete(news.getIds());

	}
}
