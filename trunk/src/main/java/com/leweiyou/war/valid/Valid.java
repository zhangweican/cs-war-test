package com.leweiyou.war.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义校验的标志
 * @author Zhangweican
 *
 */
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 
public @interface Valid {
	/**
	 * 定义视图的名称
	 * @return
	 */
	String errorView() default "";
	
	/**
	 * 定义校验的方法
	 * @return
	 */
	String validFunction() default "";
	
	/**
	 * 定义校验参数所在的标号，默认是0，<br>
	 * 暂时没有实现对多个字段校验的情况
	 */
	int parameterPosition() default 0;
}
