<html>
<body>
<h2>Add Book</h2>

<form method="post" action="<%=request.getContextPath() %>/tUser/add">
username:<input type="text" name="username">
password:<input type="text" name="password">
<input type="submit" value="Add">
</form>
<form method="post" action="<%=request.getContextPath() %>/tUser/list">
<input type="submit" value="List">
</form>
</body>
</html>

