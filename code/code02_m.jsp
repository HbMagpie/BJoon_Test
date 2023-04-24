<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Code"%>
<jsp:useBean id="chulMgr" class="ch17_1.Manager_Chul"/>
<%
	String usid = (String) session.getAttribute("idKey");
	int numb = Integer.parseInt(request.getParameter("numb"));
	Bean_Code codeBean = chulMgr.getCode1(numb);    //입력자료 가져오기
	//session.setAttribute("bean", bookBean);       //입력자료 세션에 저장
%>
<html>
<head>
<title>도서등록</title>
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

<body bgcolor="#FFFFFF" onLoad="regFrm.code.focus()">
	<div align="center">
		<br /> <br />
		<form name="regFrm" method="post" action="code02_p.jsp" >
			<table align="center" cellpadding="5" >
				<tr>
					<td align="center" valign="middle" bgcolor="#FFFFFF">
						<table border="1" cellpadding="2" align="center" width="600">
							<tr align="center" bgcolor="skyblue">
								<td colspan="3"><font color="#000000"><b>코드 자료 수정</b></font></td>
							</tr>
							<tr>
								<td width="20%">레코드번호</td>
								<td width="50%">
									<input name="numb" size="15" 
									value="<%=codeBean.getNumb()%>" readonly></td>
							</tr>
							<tr>
								<td>사용자 아이디</td>
								<td><input name="usid" size="15"
									value="<%=codeBean.getUsid()%>" readonly></td>
							</tr>
							<tr>
								<td>CODE</td>
								<td><input name="code" size="30"
									value="<%=codeBean.getCode()%>"></td>
							</tr>
							<tr>
								<td>코드내용</td>
								<td><input name="gubn" size="15"
									value="<%=codeBean.getGubn()%>"></td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								<input type="submit" value="수정완료"> &nbsp; &nbsp; 
								<input type="reset" value="다시쓰기"> &nbsp; &nbsp; 
								<input type="button" value="자료삭제" onClick="location.href='code03.jsp?numb=<%=numb%>'"> &nbsp; &nbsp;
								<input type="button" value="신규코드" onClick="location.href='code01.jsp'"> &nbsp; &nbsp;
								<input type="button" value="코드목록" onClick="history.go(-1)"></td>						
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>