package com.mrhenry.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrhenry.constant.SystemConstant;
import com.mrhenry.dao.ICategoryDAO;
import com.mrhenry.model.Category;
import com.mrhenry.paging.IPageble;
import com.mrhenry.paging.PageRequest;
import com.mrhenry.sort.Sorter;
import com.mrhenry.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-category" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	ICategoryDAO categoryDao;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category model = FormUtil.tModel(Category.class, request);
		String view = "";

		if (model.getType().contentEquals(SystemConstant.LIST)) {
			IPageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(categoryDao.findAll(pageble));
			model.setTotalItem(categoryDao.getTotalItem());
			model.setTotalPage((int)Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/admin/category/list.jsp";
		} else if(model.getType().equals(SystemConstant.EDIT)) {
			if(model.getId() != null) {
				model = categoryDao.findOne(model.getId());
			} else {
				model = new Category();
			}
			view = "/views/admin/category/edit.jsp";
		}
		
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
