package com.leweiyou.itop.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leweiyou.itop.cfg.Cfg;
import com.leweiyou.service.mybatis.entry.TUserLogin;
import com.leweiyou.service.service.TUserLoginService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/tUser")
public class TUserController {

	@Autowired
	private TUserLoginService tUserLoginService;
	
	@RequestMapping("/add")
	public String add(TUserLogin tUser){
		tUser.setLoginDate(Calendar.getInstance().getTime());
		
		tUserLoginService.insert(tUser);
		
		return "tUser/list";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<TUserLogin> list(){
		String testName = Cfg.cfg.getString("testName");
		
		System.out.println("testName: " + testName);
		
		int count = tUserLoginService.countByExample(null);
		PageInfo<TUserLogin> p = tUserLoginService.selectByExample(null,count - 3,10);
		
		System.out.println("page:" + p.getTotal() + " " + p.getPageNum() + " " + p.getPages());
		
		for(TUserLogin tu : p.getList()){
			System.out.println("Username: " + tu.getUsername() + " " + tu.getPassword() + " " + tu.getLoginDate());
			
		}
		
		return p;
	}
	
}
