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
import com.mrhenry.model.User;
import com.mrhenry.paging.IPageble;
import com.mrhenry.paging.PageRequest;
import com.mrhenry.service.IRoleService;
import com.mrhenry.service.IUserService;
import com.mrhenry.sort.Sorter;
import com.mrhenry.utils.FormUtil;

@WebServlet(urlPatterns= {"/admin-user"})
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	IUserService userService;
	
	@Inject
	IRoleService roleService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User model = FormUtil.tModel(User.class, request);
		String view ="";
		
		if(model.getType().equals(SystemConstant.LIST)) {
			IPageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
			
			model.setListResult(userService.findAll(pageble));
			model.setTotalItem(userService.getTotalItem());
			model.setTotalPage((int)(Math.ceil(model.getTotalItem() / model.getMaxPageItem())));
			
			view = "/views/admin/user/list.jsp";
		} else {
			if(model.getId() != null) {
				model = userService.findOne(model.getId());
			} else {
				model = new User();
			}
			
			request.setAttribute("roles", roleService.findAll(new PageRequest()));
			
			view= "/views/admin/user/edit.jsp";
		}
		
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
