<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%
	  String usid = (String)session.getAttribute("idKey");
%>
<html>
<head>
<title>Code</title>
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
<script type="text/javascript">
function code_inputCheck(){
	if(document.codeFrm.code.value==""){
		alert("CODE를 입력해 주세요.");
		document.codeFrm.code.focus();
		return;
	}
	if(document.codeFrm.gubn.value==""){
		alert("코드 내용을 입력해 주세요");
		document.codeFrm.gubn.focus();
		return;
	}

	document.codeFrm.action = "code01_p.jsp";
	document.codeFrm.submit();
}
</script>
</head>
<body bgcolor="#FFFFFF" onLoad="codeFrm.isbn.focus()">
	<div align="center">
		<br /><br />
		<form name="codeFrm" method="post" action="code01_p.jsp">
			<table cellpadding="5">
				<tr>
					<td bgcolor="#FFFFFF">
						<table border="1" cellspacing="0" cellpadding="2" width="700">
							<tr bgcolor="skyblue">
								<td align="center" colspan="3"><font color="#000000"><b>코드 등록</b></font></td>
							</tr>
							<tr>
								<td width="20%">사용자 아이디</td>
								<td width="50%">
									<input name="usid" size="15" value="<%=usid%>" readonly> 
								</td>
								<td width="30%">사용자 아이디 : 수정불가</td>
							</tr>
							<tr>
								<td>CODE</td>
								<td><input name="code" size="15"></td>
								<td width="30%">코드를 입력해 주세요.</td>
							</tr>
							<tr>
								<td>코드내용</td>
								<td><input name="gubn" size="30"></td>
								<td>코드내용을 입력해 주세요.</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								    <input type="button" value="코드등록" onclick="code_inputCheck()"> &nbsp; &nbsp; 
								    <input type="reset" value="다시쓰기"> &nbsp; &nbsp; 
  								    <input type="button" value="뒤로가기" onClick="history.go(-1)">						
								 </td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>