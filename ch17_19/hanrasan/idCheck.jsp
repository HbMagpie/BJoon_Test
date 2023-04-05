<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="mMgr" class="ch17_1.MemberMgr" />
<%
	request.setCharacterEncoding("UTF-8");
	String usid = request.getParameter("usid");
	boolean result = mMgr.checkId(usid);
%>
<html>
<head>
<title>ID 중복체크</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFCC">
	<div align="center">
		<br /> <b><%=usid%></b>
		<%
			if (result) {
				out.println("는 이미 존재한는 ID입니다.<p>");
			} else {
				out.println("는 사용 가능 합니다.<p>");
			}
		%>
		<a href="#" onClick="self.close()">닫기</a>
	</div>
</body>
</html>