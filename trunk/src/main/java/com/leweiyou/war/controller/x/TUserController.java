package com.leweiyou.war.controller.x;

import java.util.Calendar;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinanetcenter.itop.mapper.mybatis.entry.TUserLogin;
import com.chinanetcenter.itop.mapper.service.TUserLoginService;
import com.github.pagehelper.PageInfo;
import com.leweiyou.war.cfg.Cfg;
import com.leweiyou.war.controller.BaseController;

@Controller
@RequestMapping("/tUser")
public class TUserController extends BaseController{
	
	@Autowired
	private TUserLoginService tUserLoginService;
	


	@RequestMapping("/add")
	public void add(TUserLogin tUser){
		SecurityUtils.getSubject().checkPermission("/tUser/add");
		
		tUser.setLoginDate(Calendar.getInstance().getTime());
		
		tUserLoginService.insert(tUser);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	
	public PageInfo<TUserLogin> list(){
		
		SecurityUtils.getSubject().checkPermission("/tUser/list");
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
	@RequestMapping()
	public String index(){
		return "/tUser/index";
	}
	
}
