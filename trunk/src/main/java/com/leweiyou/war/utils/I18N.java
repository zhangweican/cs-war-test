package com.leweiyou.war.utils;

import org.springframework.web.servlet.support.RequestContext;

/**
 * 定义I18N对应的资源文件的Key
 * @author Zhangweican
 *
 */
public class I18N {
	
	/**
	 * 获取Value值
	 * @param key
	 * @return
	 */
	public static String value(String key){
		RequestContext requestContext = new RequestContext(CXT.getRequest());
		return requestContext.getMessage(key);
	}
	/**
	 * 获取Value值，可以实现EL表达式传值
	 * @param key
	 * @return
	 */
	public static String value(String key,String... params){
		RequestContext requestContext = new RequestContext(CXT.getRequest());
		if(params == null || params.length == 0 ){
			return requestContext.getMessage(key);
		}
		return requestContext.getMessage(key, params);
	}
}
