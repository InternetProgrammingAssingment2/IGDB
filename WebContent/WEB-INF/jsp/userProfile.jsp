<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Welcome!</title>
</head>
<body>
  <h1>Welcome! ${it.user}</h1>
  <p>
    items in your cart :<br />
    	userName = ${it.items[0]}<br />
    	email address = ${it.items[1]}<br />
    	first name  = ${it.items[2]}<br />
    	last name = ${it.items[3]}<br />
  </p>
</body>
</html>