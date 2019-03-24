package com.hgd.ebp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hgd.ebp.domain.User;

public class UserLoginFilter implements Filter {
	private static final String LOGIN_PREFIX = "/user/",
            LOGIN_KEYWORD = "Login",
            LOGIN_PAGE = "Login.jsp";
	public static final String ATTR_USER = "User";
	
	public void init(FilterConfig filterConfig) {}
	
	public void doFilter(ServletRequest request, ServletResponse response, 
		FilterChain chain) throws ServletException, IOException {
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();
			
			String requestURI = req.getRequestURI();
			String uri = requestURI.substring(requestURI.indexOf(LOGIN_PREFIX));
			if (uri.indexOf(LOGIN_KEYWORD) == -1) {
				User user = (User)session.getAttribute(ATTR_USER);
			if (user == null) {
				String loginUri = ".." + LOGIN_PREFIX + LOGIN_PAGE;
				((HttpServletResponse)response).sendRedirect(loginUri);
				return;               
			}
		}		
		chain.doFilter(request, response);
	}
	
	public void destroy() {}
}
