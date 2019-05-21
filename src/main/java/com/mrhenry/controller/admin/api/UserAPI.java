package com.mrhenry.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrhenry.model.News;
import com.mrhenry.model.User;
import com.mrhenry.service.IUserService;
import com.mrhenry.utils.HttpUtil;
import com.mrhenry.utils.SessionUtil;

@WebServlet(urlPatterns={"/api-admin-user"})
public class UserAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	IUserService userService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		User user = HttpUtil.of(request.getReader()).toModel(User.class);
		user.setCreatedBy(((User) SessionUtil.getInstance().getValue(request, "USER")).getUsername());
		userService.save(user);
		
		HttpUtil.of(request.getReader()).toJson(response, user);
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		User user = HttpUtil.of(request.getReader()).toModel(User.class);
		user.setModifiedBy(((User) SessionUtil.getInstance().getValue(request, "USER")).getUsername());
		userService.update(user);
		HttpUtil.of(request.getReader()).toJson(response, user);
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // để nhận định dạng UTF-8 khi client gửi lên
		response.setContentType("application/json"); // trả về định dạng JSON để cho client hiểu
		// HttpUtil.of(request.getReader()); // convert JSON --> String
		User user = HttpUtil.of(request.getReader()).toModel(User.class); // Mapper String --> New model
		userService.delete(user.getIds());

	}
}
