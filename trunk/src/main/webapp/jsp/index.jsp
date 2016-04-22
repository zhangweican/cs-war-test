<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<%@include file="common/head.jsp"%>
<body>
<h2>主页Index</h2>

<form method="post" action="${cxt}/security/login">
<fmt:message key="name.label" /><input type="text" name="username" value="test1"><lwy:validError key="username"/>
<br>
<fmt:message key="password.label" /><input type="text" name="password" value="123456"><lwy:validError key="password"/>
<br>
<input type="submit" value="Login">
</form>

<form method="post" action="${cxt}/security/logout">
<input type="submit" value="Logout">
</form>

<form method="post" action="${cxt}/tUser/list/?test=t">
<input type="submit" value="List">
</form>
	<lwy:validError key="username"/>
	${error }
	
	<hr>
	fmt:<fmt:message key="name.label" /><br>
	spring: <spring:message code="name.label" />
	
	
<fmt:message key="select.locale.label"/>
<select onchange="changeLocal(this)">
	<option value="" selected="selected">请选着语言</option>
	<option value="zh_CN">简体中文</option>
	<option value="en_US">English</option>
</select>

<script type="text/javascript">
	var changeLocal = function(obj){
		var value = obj.options[obj.selectedIndex].value;
		if(value != ""){
			document.location.href = "${cxt}/security/changeLocal?locale=" + value;
		}
	};
</script>
</body>
</html>
