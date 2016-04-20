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
	 * 对一些需要访问jsp的情况，因为直接访问jsp在spring处理时候，不会注入I18N资源文件，所以对jsp的访问，所使用的后缀变成jspp
	 */
	public static final String JSPP = ".jspp";
	
	/**
	 * 定义校验失败存入Request的map的兼职
	 */
	public static final String Key_Valid_Error_Map = "Valid_Error_Map";
	
	
}
