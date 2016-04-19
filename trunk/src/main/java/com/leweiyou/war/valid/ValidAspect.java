package com.leweiyou.war.valid;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.leweiyou.war.utils.CTX;
import com.leweiyou.war.utils.Commons;

/**
 * 定义AOP支持前台页面的校验，通过反射加载对应的校验方法。
 * @author Zhangweican
 *
 */
@Controller
@Aspect
public class ValidAspect {
	private static final String ValidPrix = "valid";
	@Autowired 
	private Validator validator;
	
	@Around("@annotation(com.leweiyou.war.valid.Valid)")
	public Object doTest(ProceedingJoinPoint pjp) throws Throwable{
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		Object[] args = pjp.getArgs();

		Valid valid = method.getAnnotation(Valid.class);
		boolean isJs = method.isAnnotationPresent(ResponseBody.class);
		String validFunction = valid.validFunction();
		String errorView = valid.errorView();
		
		if(StringUtils.isEmpty(validFunction)){
			String methodName = method.getName();
			validFunction = ValidPrix + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
			
		}
		
		if(StringUtils.isEmpty(errorView)){
			errorView = method.getAnnotation(RequestMapping.class).value()[0];
		}
		
		Object target = pjp.getTarget();
		Object isSuccess = true;
		for(Method m : target.getClass().getDeclaredMethods()){
			if(validFunction.equals(m.getName())){
				Object[] targetArgs = (args != null && args.length > 0) ? args : null;
				if(args != null && args.length > m.getParameterTypes().length){
					targetArgs = Arrays.copyOfRange(args, 0, m.getParameterTypes().length);
				}
				isSuccess = m.invoke(target, targetArgs);
			}
		}
		
		if(isSuccess instanceof Boolean && !Boolean.parseBoolean(isSuccess + "")){
			Class returnClass = method.getReturnType();
			if(isJs || returnClass == null){
				boolean isError = false;
				String json = "";
				Map<String,Set<String>> map = (Map<String, Set<String>>) CTX.getRequest().getAttribute(Commons.Key_Valid_Error_Map);
				if(map != null && map.size() > 0){
					isError = true;
				}
				json += "," + JSONArray.toJSONString(map);
				return "{isError:" + isError + json + "}";
			}else{
				Object o = returnClass.newInstance();
				if(o instanceof String){
					return new String(errorView);
				}else{
					return new ModelAndView(errorView);
				}
			}
		}
		return pjp.proceed();
	}

}
