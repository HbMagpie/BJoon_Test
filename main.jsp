<%-- <%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF">
<!-- <img src="images/universe.jpg" border=0> -->
</body>
</html>
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% String src_url = "images/universe.jpg"; %>
<html>
<head>
	<title>Main</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF">
	<img src="<%=src_url%>" width="900" border=0>
</body>
</html>