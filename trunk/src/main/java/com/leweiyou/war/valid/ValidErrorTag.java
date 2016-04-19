package com.leweiyou.war.valid;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.leweiyou.war.form.ValidErrorEntity;
import com.leweiyou.war.utils.CXT;
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
		ValidErrorEntity e = CXT.getValidErrorMap();
		if(e.isHaveError()){
			JspWriter out = pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			if(StringUtils.isNotEmpty(key)){
				for(String value : e.get(key)){
					sb.append(value).append("<br>");
				}
			}else{
				for(Set<String> sets : e.values()){
					for(String value : sets){
						sb.append(value).append("<br>");
					}
				}
			}
			try {
				out.write(sb.toString());
			} catch (IOException e1) {
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
