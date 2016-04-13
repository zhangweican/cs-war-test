package com.leweiyou.war.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leweiyou.war.utils.Commons;

/**
 * 针对springmvc请求地址，不能处理/a/b.jsp这种情况，因为b.jsp 再/jsp/目录下，所以这里的做一次跳转处理
 * @author Zhangweican
 *
 */
public class JSPDispactFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		//HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();
		request.getRequestDispatcher(Commons.Path_JSP + path).forward(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
