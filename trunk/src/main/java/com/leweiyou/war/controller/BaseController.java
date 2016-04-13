package com.leweiyou.war.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.leweiyou.tools.PageData;
import com.leweiyou.tools.UuidUtil;

/**
 * 父类Controller，
 * 
 * @author Zhangweican
 *
 */
public abstract class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 得到PageData
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}

	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		
		return UuidUtil.get32UUID();
	}
	
	/**
	 * 得到分页列表的信息 
	 */
	public Page getPage(){
		return new Page();
	}
	
	public void setSessionAttr(Object key,Object value){
		//getRequest().setAttribute(key, value);
		SecurityUtils.getSubject().getSession().setAttribute(key, value);
	} 
	public Object getSessionAttr(Object key){
		return SecurityUtils.getSubject().getSession().getAttribute(key);
	} 
}
