package com.leweiyou.war.controller;


import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.leweiyou.tools.PageData;
import com.leweiyou.tools.UuidUtil;
import com.leweiyou.war.form.ValidErrorEntity;
import com.leweiyou.war.utils.CXT;
import com.leweiyou.war.utils.Commons;
import com.leweiyou.war.utils.Utils;

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
		
		return CXT.getRequest();
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletResponse getResponse() {
		
		return CXT.getResponse();
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
	
	public void setAttr(String name,Object value){
		getRequest().setAttribute(name, value);
	} 
	
	/**
	 * 存入Session
	 * @param key
	 * @param value
	 */
	public void setSessionAttr(Object key,Object value){
		//getRequest().setAttribute(key, value);
		CXT.getSession().setAttribute(key, value);
	} 
	public Object getSessionAttr(Object key){
		return CXT.getSession().getAttribute(key);
	}
	
	/**
	 * 获取对应的I18N的值
	 * @param key
	 * @return
	 */
	public String i18n(String key){
		return Utils.i18n(key);
	}
	
	/**
	 * 存入失败的map中,可以存入一个标志KEY中，再前台可以只取这个标志
	 * @param key
	 * @param value
	 */
	public synchronized void addValidError(String key,String value){
		addValidError(key, value,new String[]{});
	}
	/**
	 * 存入失败的map中,可以存入一个标志KEY中，再前台可以只取这个标志
	 * @param key
	 * @param value
	 */
	public synchronized void addValidError(String key,String value,String[] params){
		ValidErrorEntity map = CXT.getValidErrorMap();
		map.addValidError(key, value,params);
	}
	/**
	 * 存入一个错误的map中，可以在前台取值
	 * @param key
	 * @param value
	 */
	public synchronized void addValidError(String value){
		addValidError(value,new String[]{});
	}
	/**
	 * 存入一个错误的map中，可以在前台取值
	 * @param key
	 * @param value
	 */
	public synchronized void addValidError(String value,String[] params){
		ValidErrorEntity map = CXT.getValidErrorMap();
		map.addValidError(value);
	}
	
}
