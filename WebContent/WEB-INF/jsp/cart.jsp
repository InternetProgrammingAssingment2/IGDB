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
  <h1>Welcome ${it.user}!</h1>
  <p>
    items in your cart :<br />
    <c:forEach var="item" items="${it.items}">
        ${item}<br />
    </c:forEach>
  </p>
</body>
</html>