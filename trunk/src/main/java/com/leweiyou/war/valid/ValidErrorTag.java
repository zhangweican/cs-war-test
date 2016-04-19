package com.leweiyou.war.valid;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.leweiyou.war.utils.CTX;
import com.leweiyou.war.utils.Commons;

/**
 * 定义前台校验的错误日志输出
 * @author Zhangweican
 *
 */
public class ValidErrorTag extends TagSupport {

	private PageContext pageContext;
	private String key;
	
	@Override
	public int doStartTag() throws JspException {
		Map<String,Set<String>> map = (Map<String, Set<String>>) CTX.getRequest().getAttribute(Commons.Key_Valid_Error_Map);
		if(map != null && map.size() > 0){
			JspWriter out = pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			if(StringUtils.isNotEmpty(key)){
				for(String value : map.get(key)){
					sb.append(value).append("<br>");
				}
			}else{
				for(Set<String> sets : map.values()){
					for(String value : sets){
						sb.append(value).append("<br>");
					}
				}
			}
			try {
				out.write(sb.toString());
			} catch (IOException e) {
			}
		}
		
		return super.doStartTag();
	}

	public void setPageContext(PageContext arg0) {
        this.pageContext = arg0;//PageContext获取用户request out 等对象
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
