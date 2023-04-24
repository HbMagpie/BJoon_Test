<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="java.util.Vector"%>
<jsp:useBean id="chulMgr" class="ch17_1.Manager_Chul"/>
<%	
	String usid = (String) session.getAttribute("idKey");
	String cPath = request.getContextPath();
	String url1 = "admin/h01.jsp";
	String url2 = "main.jsp";
	String url3 = "main.jsp";
	String url4 = "main.jsp";
	String url5 = "main.jsp";
	String url6 = "main.jsp";
	String url7 = "main.jsp";
	String label = "회원가입";

    if (usid == null) {
      label = "회원가입";
    } else if (usid.equals("user00")) {
      url1 = "admin/h02.jsp?numb=0";
      url2 = "moimlist/moimlist02.jsp";
      url3 = "code/code02.jsp"; 
  	  url4 = "sanlist/san02.jsp";
	  url5 = "meetlist/meet02.jsp";
	  url6 = "don/don02.jsp";
	  url7 = "chulnab/chul02.jsp";
      label = "전체회원";
    } else if (usid != null) {
      url1 = "admin/h02_m.jsp?numb=0";
      url2 = "moimlist/moimlist02.jsp";
  	  url3 = "code/code02.jsp";
  	  url4 = "sanlist/san02.jsp";
  	  url5 = "meetlist/meet02.jsp";
	  url6 = "don/don02.jsp";
	  url7 = "chulnab/chul02.jsp";
      label = "회원수정";	
    }
%>

<html>
<head>
	<title>Left</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
	<body leftmargin="0" topmargin="0" bgcolor="#FFFFFF">
	<jsp:include page="login/login.jsp"/>
	<hr width="200px;">
	<div align="center">
    	<br>
		<font size="4"><a href="<%=url1%>" target="content" style="color: skyblue; text-decoration:none;"><b><%=label%></b></a></font><br><br>
		<font size="4"><a href="<%=url2%>" target="content" style="color: skyblue; text-decoration:none;"><b>모임관리</b></a></font><br><br>
		<font size="4"><a href="<%=url3%>" target="content" style="color: skyblue; text-decoration:none;"><b>코드관리</b></a></font><br><br>
		<font size="4"><a href="<%=url4%>" target="content" style="color: skyblue; text-decoration:none;"><b>명산정보</b></a></font><br><br>
		<font size="4"><a href="<%=url5%>" target="content" style="color: skyblue; text-decoration:none;"><b>모임내역</b></a></font><br><br>
		<font size="4"><a href="<%=url6%>" target="content" style="color: skyblue; text-decoration:none;"><b>회비납부</b></a></font><br><br>
		<font size="4"><a href="<%=url7%>" target="content" style="color: skyblue; text-decoration:none;"><b>출납내역</b></a></font><br><br>
	</div>
	<%-- <jsp:include page="member/login.jsp"/>
	<hr>
	<div align="center">
    	<br>
		<font size="4"><a href="<%=url1%>" target="content"><b><%=label%></b></a></font><br><br>
		<font size="4"><a href="<%=url2%>" target="content"><b>코드관리</b></a></font><br><br>
		<font size="4"><a href="<%=url3%>" target="content"><b>도서 등록</b></a></font><br><br>
		<font size="4"><a href="<%=url4%>" target="content"><b>대출 반납</b></a></font><br><br>
	</div> --%>
</body>
</html>
