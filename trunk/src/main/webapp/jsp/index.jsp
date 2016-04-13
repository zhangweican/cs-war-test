<html>
<body>
<h2>Add Book</h2>

<form method="post" action="<%=request.getContextPath() %>/security/login">
username:<input type="text" name="username" value="test1">
password:<input type="text" name="password" value="123456">
<input type="submit" value="Login">
</form>

<form method="post" action="<%=request.getContextPath() %>/security/logout">
<input type="submit" value="Logout">
</form>

<form method="post" action="<%=request.getContextPath() %>/tUser/list/?test=t">
<input type="submit" value="List">
</form>
	${msg}

</body>
</html>

