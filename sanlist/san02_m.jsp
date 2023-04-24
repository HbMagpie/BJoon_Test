<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_San"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%
	String usid = (String) session.getAttribute("idKey");
	int numb = Integer.parseInt(request.getParameter("numb"));
	Bean_San sanBean = moimMgr.getSan1(numb);    //입력자료 가져오기
	//session.setAttribute("bean", bookBean);       //입력자료 세션에 저장
%>
<html>
<head>
	<title>명산관리</title>
	<link href="../main/style.css" rel="stylesheet" type="text/css" >
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

<body bgcolor="#FFFFFF" onLoad="regFrm.s_code.focus()">
	<div align="center">
		<br /> <br />
		<form name="regFrm" method="post" action="san02_p.jsp" >
			<table align="center" cellpadding="5" >
				<tr>
					<td align="center" valign="middle" bgcolor="#FFFFFF">
						<table border="1" cellpadding="2" align="center" width="600">
							<tr align="center" bgcolor="SKYBLUE">
								<td colspan="3"><font color="#FFFFFF"><b>명산 자료 수정</b></font></td>
							</tr>
							<tr>
								<td width="20%">레코드번호</td>
								<td width="50%"><input name="numb" size="15"
									value="<%=sanBean.getNumb()%>" readonly></td>
								<td width="30%">레코드번호</td>
							</tr>
							<tr>
								<td>등록자 아이디</td>
								<td><input name="s_usid" size="15"
									value="<%=sanBean.getS_usid()%>"></td>
								<td>등록자 아이디 입력</td>
							</tr>
							<tr>
								<td>명산코드</td>
								<td><input name="s_code" size="30"
									value="<%=sanBean.getS_code()%>"></td>
								<td>명산코드 입력</td>
							</tr>
							<tr>
								<td>산 명칭</td>
								<td><input name="s_name" size="15"
									value="<%=sanBean.getS_name()%>"></td>
								<td>산 명칭 입력</td>
							</tr>
							<tr>
								<td>산 소재지</td>
								<td><input name="s_juso" size="30"
									value="<%=sanBean.getS_juso()%>"></td>
								<td>산 소재지 입력</td>
							</tr>
							<tr>
								<td>산 높이</td>
								<td><input name="s_high" size="15"
									value="<%=sanBean.getS_high()%>"></td>
								<td>산 높이 입력</td>
							</tr>
							<tr>
								<td>산 설명</td>
								<td><input name="s_levl" size="30"
									value="<%=sanBean.getS_levl()%>"></td>
								<td>산 높이 입력</td>
							</tr>
							<tr>
								<td>비고</td>
								<td><input name="s_bigo" size="30"
									value="<%=sanBean.getS_bigo()%>"></td>
								<td>비고 입력</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								<input type="submit" value="수정완료"> &nbsp; &nbsp; 
								<input type="reset" value="다시쓰기"> &nbsp; &nbsp; 
								<input type="button" value="자료삭제" onClick="location.href='san03.jsp?numb=<%=numb%>'"> &nbsp; &nbsp;
								<input type="button" value="신규명산" onClick="location.href='san01.jsp'"> &nbsp; &nbsp;
								<input type="button" value="명산목록" onClick="history.go(-1)"></td>						
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>