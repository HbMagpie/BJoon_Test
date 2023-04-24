<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Code"%>
<jsp:useBean id="chulMgr" class="ch17_1.Manager_Chul"/>

<html>
<head>
<title>JSP Board</title>
<link href="../main/style.css" rel="stylesheet" type="text/css">
<%
	request.setCharacterEncoding("UTF-8");
	int numb = Integer.parseInt(request.getParameter("numb"));
    String cPath = request.getContextPath();

    Bean_Code  bBean = chulMgr.getCode1(numb);       //코드자료 가져오기
    String b_usid = bBean.getUsid();
    
    Bean_Admin gBean = chulMgr.getMember(b_usid);    //회원자료 가져오기

    if (request.getParameter("pass") != null) {
		String inPass = request.getParameter("pass");
		String dbPass = gBean.getUspw();
		if (inPass.equals(dbPass)) {
			chulMgr.deleteCode(numb);
			String url = "code02.jsp";
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
	<div align="center">
		<br/><br/>
		<table width="300" cellpadding="3">
			<tr>
				<td bgcolor=skyblue height="21" align="center">
					사용자의 비밀번호를 입력해주세요.
				</td>
			</tr>
		</table>
		<form name="delFrm" method="post" action="code03.jsp">
			<table width="300" cellpadding="2">
				<tr>
					<td align="center">
						<table>
							<tr>
								<td>레코드 번호</td>
								<td><%=bBean.getNumb()%></td>
							</tr>
							<tr>
								<td>사용자 아이디</td>
								<td><%=bBean.getUsid()%></td>
							</tr>
							<tr>
								<td>코드</td>
								<td><%=bBean.getCode()%></td>
							</tr>
							<tr>
								<td>코드내용</td>
								<td><%=bBean.getGubn()%></td>
							</tr>
							<tr>
								<td align="center">
									<input type="password" name="pass" size="17" maxlength="15">
								</td>
							</tr>
							<tr>
								<td><hr size="1" color="skyblue"/></td>
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