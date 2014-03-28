<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	  <h1>Write a review for ${it.items[0]}!</h1>
	
	<FORM ACTION="dojo" METHOD="post">
		<label NAME="gameName" id="gameName" value=$it.items[0] ></label>
		<TEXTAREA NAME="comments" COLS=40 ROWS=6 id="comments" ></TEXTAREA>
		<P><INPUT TYPE="submit" VALUE="submit">
	</FORM>	
</body>
</html>