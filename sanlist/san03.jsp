<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_San"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>

<html>
<head>
	<title>명산관리</title>
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
<%
	request.setCharacterEncoding("UTF-8");
	String usid = (String) session.getAttribute("idKey");
	int numb = Integer.parseInt(request.getParameter("numb"));
    String cPath = request.getContextPath();

    Bean_San  bBean = moimMgr.getSan1(numb);       //명산자료 가져오기
    
    Bean_Admin gBean = moimMgr.getMember(usid);    //회원자료 가져오기

    if (request.getParameter("pass") != null) {
		String inPass = request.getParameter("pass");
		String dbPass = gBean.getUspw();
		if (inPass.equals(dbPass)) {
			moimMgr.deleteCode(numb);
			String url = "san02.jsp";
			response.sendRedirect(url);
		} else {
%>
<script type="text/javascript">
	alert("입력하신 비밀번호가 아닙니다.");
	history.back();
</script>
<%}
	} else {
%>
<script type="text/javascript">
	function check() {
		if (document.delFrm.pass.value == "") {
			alert("패스워드를 입력하세요.");
			document.delFrm.pass.focus();
			return false;
		}
		document.delFrm.submit();
	}
</script>
</head>
<body bgcolor="#FFFFFF">
	<div align="center">
		<br/><br/>
		<table width="300" cellpadding="3">
			<tr>
				<td bgcolor=SKYBLUE height="21" align="center">
					사용자의 비밀번호를 입력해주세요.
				</td>
			</tr>
		</table>
		<form name="delFrm" method="post" action="san03.jsp">
			<table width="500" cellpadding="2">
				<tr>
					<td align="center">
						<table>
							<tr>
								<td>레코드 번호</td>
								<td><%=bBean.getNumb()%></td>
							</tr>
							<tr>
								<td>산 명칭</td>
								<td><%=bBean.getS_name()%></td>
							</tr>
							<tr>
								<td>산 소재지</td>
								<td><%=bBean.getS_juso()%></td>
							</tr>
							<tr>
								<td>산높이</td>
								<td><%=bBean.getS_high()%></td>
							</tr>
							<tr>
								<td align="center">
									<input type="password" name="pass" size="17" maxlength="15">
								</td>
							</tr>
							<tr>
								<td><hr size="1" color="#eeeeee"/></td>
							</tr>
							<tr>
								<td align="center">
									<input type="button" value="삭제완료" onClick="check()"> 
									<input type="reset" value="다시쓰기">
									<input type="button" value="뒤로" onClick="history.go(-1)">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" name="numb" value="<%=numb%>">
		</form>
	</div>
	<%}%>
</body>
</html>