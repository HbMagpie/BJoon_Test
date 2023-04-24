<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="ch17_1.Bean_San"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%
	String san = request.getParameter("san");
	Bean_San sBean = moimMgr.getMeet1(san);   //명산자료 가져오기
	String s_code = sBean.getS_code();
	String s_name = sBean.getS_name();
	String s_juso = sBean.getS_juso();
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
		out.println("명산코드 : " + s_code + "<BR>");
		out.println("명산이름 : " + s_name + "<BR>");
		out.println("소 재 지 : " + s_juso + "<BR>");
		// 종료
			
		%>
		<a href="#" onClick="self.close()">[닫기]</a>
	</div>
</body>
</html>