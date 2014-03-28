<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="IGDB.stores.*" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>IGDB</title>
</head>
<body>

	<h1>IGDB</h1>
	<%
		System.out.println("In render");
		List<IGDBStore> lTweet = (List<IGDBStore>) request
				.getAttribute("Tweets");
		if (lTweet == null) {
	%>
	<p>No IGDB found</p>
	<%
		} else {
	%>


	<%
		Iterator<IGDBStore> iterator;

			iterator = lTweet.iterator();
			while (iterator.hasNext()) {
				IGDBStore ts = (IGDBStore) iterator.next();
	%>
	<a href="/ac32007examples/Tweet/<%=ts.getUser()%>"><%=ts.getTweet()%></a>
	<br />
	<%
		}
		}
	%>

</body>
</html>