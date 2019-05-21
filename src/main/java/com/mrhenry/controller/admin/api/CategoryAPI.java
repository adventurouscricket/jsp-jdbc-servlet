package com.mrhenry.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrhenry.model.Category;
import com.mrhenry.model.User;
import com.mrhenry.service.ICategoryService;
import com.mrhenry.utils.HttpUtil;
import com.mrhenry.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-category"})
public class CategoryAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	ICategoryService categoryService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Category category = HttpUtil.of(request.getReader()).toModel(Category.class);
		category.setCreatedBy(((User)SessionUtil.getInstance().getValue(request, "USER")).getUsername());
		
		category = categoryService.save(category);

		HttpUtil.of(request.getReader()).toJson(response, category);

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Category category = HttpUtil.of(request.getReader()).toModel(Category.class);
		category.setModifiedBy(((User)SessionUtil.getInstance().getValue(request, "USER")).getUsername());
		category = categoryService.update(category);

		HttpUtil.of(request.getReader()).toJson(response, category);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Category category = HttpUtil.of(request.getReader()).toModel(Category.class);
		
		categoryService.delete(category.getIds());
	}
}
