package com.leweiyou.war.form;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class SysUserForm {
	
	@NotEmpty(message = "{name.is.empty}")
	@Min(value = 2,message="用户名长度不能小于2")
	private String username;
	
	@NotEmpty(message = "密码不能为空")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
