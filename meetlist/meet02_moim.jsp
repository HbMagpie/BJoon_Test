<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="ch17_1.Bean_Moim"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%
	String moim = request.getParameter("moim");

	Bean_Moim mBean = moimMgr.getMeet(moim);    // 모임 자료 가져오기
	String m_code = mBean.getM_code();          // 모임 식별코드 가져오기
	String m_name = mBean.getM_name();          // 모임 명칭 가져오기
	String m_jang = mBean.getM_jang();          // 모임 회장 가져오기
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
		out.println("모임코드 : " + m_code + "<BR>");
		out.println("모임명칭 : " + m_name + "<BR>");
		out.println("모임회장 : " + m_jang + "<BR>");
		// 종료
			
		%>
		<a href="#" onClick="self.close()">[닫기]</a>
	</div>
</body>
</html>