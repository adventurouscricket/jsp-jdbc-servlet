package com.mrhenry.controller.web.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrhenry.controller.web.HomeController;
import com.mrhenry.model.User;
import com.mrhenry.service.IUserService;
import com.mrhenry.utils.HttpUtil;

@WebServlet(urlPatterns= {"/api-web-user"})
public class UserAPI extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Inject
	IUserService userService;
	
	@Inject
	HomeController homeController;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		User user = HttpUtil.of(request.getReader()).toModel(User.class);
		user.setCreatedBy(user.getUsername());
		user.setRoleid(1L);
		
		userService.save(user);
		HttpUtil.of(request.getReader()).toJson(response, user);
		
		//response.sendRedirect(request.getContextPath() + "/home");
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/home?type=list&page=1&maxPageItem=6&sortName=title&sortBy=desc");
//		rd.forward(request, response);
	}
}
