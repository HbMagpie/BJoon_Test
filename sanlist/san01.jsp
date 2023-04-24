<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%
	  String usid = (String)session.getAttribute("idKey");
%>
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
<script type="text/javascript">
function san_inputCheck(){
	if(document.sanFrm.s_code.value==""){
		alert("명산 CODE를 입력해 주세요.");
		document.sanFrm.s_code.focus();
		return;
	}
	if(document.sanFrm.s_name.value==""){
		alert("산명칭을 입력해 주세요");
		document.sanFrm.s_name.focus();
		return;
	}

	document.sanFrm.action = "san01_p.jsp";
	document.sanFrm.submit();
}
</script>
</head>
<body bgcolor="#FFFFFF" onLoad="sanFrm.code.focus()">
	<div align="center">
		<br /><br />
		<form name="sanFrm" method="post" action="san01_p.jsp">
			<table cellpadding="5">
				<tr>
					<td bgcolor="#FFFFFF">
						<table border="1" cellspacing="0" cellpadding="2" width="600">
							<tr bgcolor="SKYBLUE">
								<td align="center" colspan="3"><font color="#FFFFFF"><b>명산 신규 등록</b></font></td>
							</tr>
							<tr>
								<td width="20%">등록자 아이디</td>
								<td width="50%">
									<input name="s_usid" size="15" value="<%=usid%>" readonly> 
								</td>
								<td width="30%">사용자 아이디 : 수정불가</td>
							</tr>
							<tr>
								<td>명산코드</td>
								<td><input name="s_code" size="15"></td>
								<td>명산코드 입력</td>
							</tr>
							<tr>
								<td>산 명칭</td>
								<td><input name="s_name" size="30"></td>
								<td>산 명칭 입력</td>
							</tr>
							<tr>
								<td>산 소재지</td>
								<td><input name="s_juso" size="30"></td>
								<td>산 소재지 입력</td>
							</tr>
							<tr>
								<td>산 높이</td>
								<td><input name="s_high" size="15"></td>
								<td>산 높이 입력</td>
							</tr>
							<tr>
								<td>산 설명</td>
								<td><input name="s_levl" size="30"></td>
								<td>산 높이 입력</td>
							</tr>
							<tr>
								<td>비고</td>
								<td><input name="s_bigo" size="30"></td>
								<td>비고 입력</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								    <input type="button" value="명산등록" onclick="san_inputCheck()"> &nbsp; &nbsp; 
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