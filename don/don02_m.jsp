<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Moim"%>
<%@ page import="ch17_1.Bean_Don"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%
	String usid = (String) session.getAttribute("idKey");
	int numb = Integer.parseInt(request.getParameter("numb"));
	Bean_Don donBean = moimMgr.getDon(numb);      //입력자료 가져오기
%>
<html>
<head>
	<title>회비납부</title>
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

<body bgcolor="#FFFFFF" onLoad="regFrm.d_d01.focus()">
	<div align="center">
		<br /> <br />
		<form name="regFrm" method="post" action="don02_p.jsp" >
			<table align="center" cellpadding="5" >
				<tr>
					<td align="center" valign="middle" bgcolor="#FFFFFF">
						<table border="1" cellpadding="2" align="center" width="600">
							<tr align="center" bgcolor="SKYBLUE">
								<td colspan="3"><font color="#FFFFFF"><b>회비 납부 자료 수정</b></font></td>
							</tr>
							<tr>
								<td width="20%">레코드번호</td>
								<td width="50%"><input name="numb" size="15"
									value="<%=donBean.getNumb()%>" readonly></td>
								<td width="30%">DB에서 모임 구분 선택</td>
							</tr>
							<tr>
								<td>모임 식별코드</td>
								<td><input name="d_moim" size="15"
									value="<%=donBean.getD_moim()%>"></td>
								<td>모임 식별코드 입력</td>
							</tr>
							<tr>
								<td>회원 아이디</td>
								<td><input name="d_usid" size="30"
									value="<%=donBean.getD_usid()%>"></td>
								<td>회원 아이디</td>
							</tr>
							<tr>
								<td>납부 1회</td>
								<td><input name="d_d01" size="15"
									value="<%=donBean.getD_d01()%>"></td>
								<td>납부 1회 입력</td>
							</tr>
							<tr>
								<td>납부 2회</td>
								<td><input name="d_d02" size="15"
									value="<%=donBean.getD_d02()%>"></td>
								<td>납부 2회 입력</td>
							</tr>
							<tr>
								<td>납부 3회</td>
								<td><input name="d_d03" size="15"
									value="<%=donBean.getD_d03()%>"></td>
								<td>납부 3회 입력</td>
							</tr>
							<tr>
								<td>납부 4회</td>
								<td><input name="d_d04" size="15"
									value="<%=donBean.getD_d04()%>"></td>
								<td>납부 4회 입력</td>
							</tr>
							<tr>
								<td>납부 5회</td>
								<td><input name="d_d05" size="15"
									value="<%=donBean.getD_d05()%>"></td>
								<td>납부 5회 입력</td>
							</tr>
							<tr>
								<td>납부 합계</td>
								<td><input name="d_tot" size="15"
									value="<%=donBean.getD_tot()%>"></td>
								<td>납부 합계 입력</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								<input type="submit" value="수정완료"> &nbsp; &nbsp; 
								<input type="reset" value="다시쓰기"> &nbsp; &nbsp; 
								<input type="button" value="자료삭제" onClick="location.href='don03.jsp?numb=<%=numb%>'"> &nbsp; &nbsp;
								<input type="button" value="신규자료" onClick="location.href='don01.jsp'"> &nbsp; &nbsp;
								<input type="button" value="모임목록" onClick="history.go(-1)"></td>						
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>