package com.leweiyou.war.utils;

public class Commons {
	public final static String SessionAcount = "com.leweiyou.session.key";
	
	public final static int MenuType_tree = 0;		//定义菜单类型为主菜单
	public final static int MenuType_button = 1;	//定义菜单类型为按钮

	/**
	 * 定义jsp存放位置，和 springmvc 的 InternalResourceViewResolver 配置保持一致
	 */
	public static final String Path_JSP = "/jsp";

	/**
	 * 定义校验失败存入Request的map的兼职
	 */
	public static final String Key_Valid_Error_Map = "Valid_Error_Map";
	
	
}
