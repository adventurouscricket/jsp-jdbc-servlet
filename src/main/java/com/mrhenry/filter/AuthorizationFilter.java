package com.mrhenry.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrhenry.constant.SystemConstant;
import com.mrhenry.model.User;
import com.mrhenry.utils.SessionUtil;

public class AuthorizationFilter implements Filter {

	@SuppressWarnings("unused")
	private ServletContext context;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();
		if (url.startsWith("/jsp-jdbc-servlet/admin")) {
			User model = (User) SessionUtil.getInstance().getValue(request, "USER");
			if (model != null) {
				if (model.getRole().getCode().equals(SystemConstant.ADMIN)) {
					filterChain.doFilter(request, response);
				} else {
					response.sendRedirect(
							request.getContextPath() + "/login?action=login&message=not_permission&alert=danger");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/login?action=login&message=not_login&alert=danger");
			}
		} else {
			filterChain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {

	}

}
