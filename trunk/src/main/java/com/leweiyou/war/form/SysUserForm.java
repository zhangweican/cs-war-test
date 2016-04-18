package com.leweiyou.war.form;

import org.hibernate.validator.constraints.NotEmpty;

public class SysUserForm {
	
	@NotEmpty(message = "{name.is.empty}")
	private String username;
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
