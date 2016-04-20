package com.leweiyou.war.controller.sys;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.leweiyou.service.mybatis.entry.sys.SysMenu;
import com.leweiyou.service.mybatis.entry.sys.SysRoleMenu;
import com.leweiyou.service.mybatis.entry.sys.SysRoleMenuExample;
import com.leweiyou.service.mybatis.entry.sys.SysUser;
import com.leweiyou.service.mybatis.entry.sys.SysUserExample;
import com.leweiyou.service.mybatis.entry.sys.SysUserRole;
import com.leweiyou.service.mybatis.entry.sys.SysUserRoleExample;
import com.leweiyou.service.service.sys.SysMenuService;
import com.leweiyou.service.service.sys.SysRoleMenuService;
import com.leweiyou.service.service.sys.SysRoleService;
import com.leweiyou.service.service.sys.SysUserRoleService;
import com.leweiyou.service.service.sys.SysUserService;
import com.leweiyou.war.controller.BaseController;
import com.leweiyou.war.form.SessionUser;
import com.leweiyou.war.form.SysUserForm;
import com.leweiyou.war.utils.Commons;
import com.leweiyou.war.valid.Valid;

@Controller
@RequestMapping("/security")
public class SecurityController extends BaseController{

	@Autowired  
    private LocaleResolver localeResolver;
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 用户登录
	 */
	@RequestMapping("/login")
	@Valid(errorView="/index")
	public String login(SysUserForm form,String usern){
		try {
			//使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
			SecurityUtils.getSubject().login(new UsernamePasswordToken(form.getUsername(), form.getPassword()));
		} catch (AuthenticationException e) {
			setSessionAttr("msg", "用户名或密码错误。");
			return index();
		}catch (Exception e) {
			setSessionAttr("msg", "用户名或密码错误。");
			return index();
		}
		return main();
	}
	
	/**
	 * 用户登出
	 */
	@RequestMapping("/logout")
	public String logout(){
		SecurityUtils.getSubject().logout();
		return index();
	}
	
	/**
	 * 进入登录后的主界面
	 */
	@RequestMapping("/main")
	public String main(){
		SessionUser sessionUser = new SessionUser();
		
		//获取用户信息
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		SysUserExample example = new SysUserExample();
		example.createCriteria().andAccountEqualTo(username);
		SysUser user = sysUserService.selectOne(example);
		sessionUser.setUserId(user.getUserId());
		sessionUser.setAccount(user.getAccount());
		
		SysUserRoleExample e1 = new SysUserRoleExample();
		e1.createCriteria().andUserIdEqualTo(user.getUserId());
		List<SysUserRole> surs = sysUserRoleService.selectByExample(e1);
		
		Set<SysMenu> menus = new HashSet<SysMenu>();
		Set<SysMenu> rights = new HashSet<SysMenu>();
		if(surs != null){
			for(SysUserRole sur : surs){
				sessionUser.addRoleId(sur.getRoleId());
				
				SysRoleMenuExample e2 = new SysRoleMenuExample();
				e1.createCriteria().andRoleIdEqualTo(sur.getRoleId());
				List<SysRoleMenu> sums = sysRoleMenuService.selectByExample(e2);
				
				if(sums != null){
					for(SysRoleMenu sum : sums){
						SysMenu menu = sysMenuService.selectByPrimaryKey(sum.getMenuId());
						if(Commons.MenuType_tree == menu.getType()){
							menus.add(menu);
						}else if(Commons.MenuType_button == menu.getType()){
							rights.add(menu);
						}
					}
				}
			}
		}
		
		sessionUser.setRightsBySysMenu(rights);
		sessionUser.setTree(menus);
		setSessionAttr(Commons.SessionAcount, sessionUser);
		
		return "/security/main";
	}
	
	/**
	 * 跳转到工程主页，就是登录页
	 */
	@RequestMapping("/index")
	public String index(){
		
		return "index";
	}
	
	/**
	 * 无权限页面
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized(){
		
		return "/common/403";
	}
	
	/**
	 * 
	 * 改变国际化
	 */
	@RequestMapping("/changeLocal")
	public String changeLocal(String locale){
		if(StringUtils.isNotEmpty(locale)){
			Locale l = new Locale(locale);  
	        localeResolver.setLocale(getRequest(), getResponse(), l);
		}
		return "redirect:index";
	}
	
	protected void loginValid(SysUserForm form){
		if(StringUtils.isEmpty(form.getUsername())){
			addValidError("name.is.empty");
		}
		if(StringUtils.isEmpty(form.getPassword())){
			addValidError("password.is.empty");
		}
		if(form.getUsername().length() > 5){
			addValidError("username", "username.too.long",new String[]{"5"});
		}
	}
}
