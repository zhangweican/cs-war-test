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
		RequestContext requestContext = new RequestContext(CTX.getRequest());
		return requestContext.getMessage(key);
	}
}
