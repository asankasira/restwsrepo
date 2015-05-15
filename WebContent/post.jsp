<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>REST Post</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/api/v1/create" method="post">
First name:
<input type="text" name="firstname" value="">
<br>
Last name:
<input type="text" name="lastname" value="">
<br>
Email: 
<input type="text" name="email" value="">
<br>
Telephone:
<input type="text" name="telephone" value="">
<br>
<br/><br/>
<input type="submit" value="Submit">
</form> 

</body>
</html>