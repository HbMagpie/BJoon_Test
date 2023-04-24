<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="ch17_1.Bean_Admin"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%
	String usid = request.getParameter("usid");
	Bean_Admin gBean = moimMgr.getMember(usid);   // 회원 자료 가져오기
	String name = gBean.getName();                // 회원 이름 가져오기
	String telp = gBean.getTelp();                // 회원 전화번호 가져오기
	String jobb = gBean.getJobb();                // 회원 직업 가져오기
%>
<html>
<head>
	<title>모임관리</title>
	<link href="../main/style.css" rel="stylesheet" type="text/css">
	<Style>
@font-face {
    font-family: 'KIMM_Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2212@1.0/KIMM_Bold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

* {
	font-family: 'KIMM_Bold';
}
</style>
</head>
<body bgcolor="#FFFFFF">
	<div align="left">
		<%
		// 조건자료 출력하기 시작
		out.println("아이디 : " + usid + "<BR>");
		out.println("이  름 : " + name + "<BR>");
		out.println("연락처 : " + telp + "<BR>");
		out.println("직  업 : " + jobb + "<BR>");
		// 종료
			
		%>
		<a href="#" onClick="self.close()">[닫기]</a>
	</div>
</body>
</html>