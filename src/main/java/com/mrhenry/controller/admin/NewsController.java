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
import com.mrhenry.model.News;
import com.mrhenry.paging.IPageble;
import com.mrhenry.paging.PageRequest;
import com.mrhenry.service.ICategoryService;
import com.mrhenry.service.INewsService;
import com.mrhenry.sort.Sorter;
import com.mrhenry.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-news" })
public class NewsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	INewsService newsService;

	@Inject
	ICategoryService categoryService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		News model = new News();
//		model.setListResult(newsService.findAll());

		News model = FormUtil.tModel(News.class, request);
//		Integer offset = (model.getPage() - 1) * model.getMaxPageItem();

		String view = "";
		if (model.getType().equals(SystemConstant.LIST)) {

//		model.setListResult(newsService.findAll(offset,model.getMaxPageItem(), model.getSortName(), model.getSortBy()));
			IPageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
//		model.setListResult(newsService.findAll(offset,model.getMaxPageItem(), model.getSortName(), model.getSortBy()));
			model.setListResult(newsService.findAll(pageble));

			model.setTotalItem(newsService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));

//			request.setAttribute(SystemConstant.MODEL, model);

			view = "/views/admin/news/list.jsp";

		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = newsService.findOne(model.getId());
			} else {
				model = new News();	
			}
			
			request.setAttribute("categories", categoryService.findAll(new PageRequest()));
			
			view = "/views/admin/news/edit.jsp";
		}
		
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
