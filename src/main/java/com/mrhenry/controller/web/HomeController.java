package com.mrhenry.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrhenry.constant.SystemConstant;
import com.mrhenry.model.News;
import com.mrhenry.model.User;
import com.mrhenry.paging.IPageble;
import com.mrhenry.paging.PageRequest;
import com.mrhenry.service.ICategoryService;
import com.mrhenry.service.INewsService;
import com.mrhenry.service.IUserService;
import com.mrhenry.sort.Sorter;
import com.mrhenry.utils.FormUtil;
import com.mrhenry.utils.SessionUtil;

@WebServlet(urlPatterns = { "/home", "/login", "/logout", "/signup" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

//	@Inject
//	private ICategoryService categoryService;

//	@Inject
//	private INewsService newsService;

	@Inject
	private IUserService userService;

	@Inject
	private ICategoryService categoryService;

	@Inject
	private INewsService newsService;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		Long id = 2L;
		// request.setAttribute("newses", newsService.findNewsByCategoryId(id));

//		News news = new News();
//		news.setId(21L);
//		news.setTitle("p1");
//		news.setContent("p1");
//		news.setCategoryId(id);

		// newsService.save(news);
		// newsService.update(news);
		// newsService.delete(21L);

//		request.setAttribute("categorys", categoryService.findAll());

		String action = request.getParameter("action");
		String view = "";

		if (action != null) {
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");

			if (action.equals("login")) {
//				String message = request.getParameter("message");
//				String alert = request.getParameter("alert");

				if (message != null && alert != null) {
					request.setAttribute("message", resourceBundle.getString(message));
					request.setAttribute("alert", alert);
				}

				RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
				rd.forward(request, response);

			} else if (action.equals("logout")) {
				SessionUtil.getInstance().removeValue(request, "USER");
				response.sendRedirect(request.getContextPath() + "/home");
			} else if (action.equals("signup")) {
				if (message != null && alert != null) {
					request.setAttribute("message", resourceBundle.getString(message));
					request.setAttribute("alert", alert);
				}

				RequestDispatcher rd = request.getRequestDispatcher("/views/signup.jsp");
				rd.forward(request, response);
			}
		} else {
//			request.setAttribute("categories", categoryService.findAll(new PageRequest()));
//			
//			request.setAttribute("newses", newsService.findAll(new PageRequest()));

			News model = FormUtil.tModel(News.class, request);

			if (model.getType() != null) {
				if (model.getType().equals(SystemConstant.LIST)) {
					IPageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
							new Sorter(model.getSortName(), model.getSortBy()));
					model.setListResult(newsService.findAll(pageble));
					model.setTotalItem(newsService.getTotalItem());
					model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));

					request.setAttribute("categories", categoryService.findAll(new PageRequest()));
					view = "/views/web/home.jsp";

				} else if (model.getType().equals(SystemConstant.DETAIL)) {
					model = newsService.findOne(model.getId());
					view = "/views/web/detail.jsp";
				}
			} else {
				model.setPage(1);
				model.setMaxPageItem(6);
				model.setSortName("title");
				model.setSortBy("desc");

				IPageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
						new Sorter(model.getSortName(), model.getSortBy()));
				model.setListResult(newsService.findAll(pageble));
				model.setTotalItem(newsService.getTotalItem());
				model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));

				request.setAttribute("categories", categoryService.findAll(new PageRequest()));
				view = "/views/web/home.jsp";
			}

			request.setAttribute(SystemConstant.MODEL, model);

			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("login")) {
				User model = FormUtil.tModel(User.class, request);
				model = userService.login(model.getUsername(), model.getPassword(), 1);
				if (model != null) {
					SessionUtil.getInstance().putValue(request, "USER", model);

					if (model.getRole().getCode().equals("USER")) {
						response.sendRedirect(request.getContextPath() + "/home");
					} else if (model.getRole().getCode().equals("ADMIN")) {
						response.sendRedirect(request.getContextPath() + "/admin-home");
					}
				} else {
					response.sendRedirect(request.getContextPath()
							+ "/login?action=login&message=username_password_invalid&alert=danger");
				}
			}
		}
	}
}
