<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="ch17_1.MemberBean"%>
<jsp:useBean id="bMgr" class="ch17_1.MemberMgr" />
<%
	  request.setCharacterEncoding("UTF-8");
	  String usid = request.getParameter("usid");
	    System.out.println("자료확인");
	    System.out.println(usid);
	  
%>
<html>
<head>
<title>JSP Board</title>
</head>
<body bgcolor="#FFFFCC" onLoad="regFrm.usid.focus()">
</body>
</html>