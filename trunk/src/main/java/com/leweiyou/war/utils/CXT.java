package com.leweiyou.war.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.leweiyou.war.form.ValidErrorEntity;

/**
 * 环境变量的获取
 * @author Zhangweican
 *
 */
public class CXT {
	/**
	 * 获取Request
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
	}
	
	/**
	 * 获取Session(该Session是shiro的Session)
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 获取Response
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	/**
	 * 获取存校验失败信息的ValidErrorEntity(已经存入回话中，无需再存)
	 * @return
	 */
	public static synchronized ValidErrorEntity getValidErrorMap(){
		ValidErrorEntity e = (ValidErrorEntity) getRequest().getAttribute(Commons.Key_Valid_Error_Map);
		if(e == null){
			e = new ValidErrorEntity();
		}
		getRequest().setAttribute(Commons.Key_Valid_Error_Map, e);
		return e;
	}
}
