<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ page import="java.util.Vector"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Moim"%>
<%@ page import="ch17_1.Bean_Code"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%
	String usid = (String) session.getAttribute("idKey");
%>
<html>
<head>
	<title>모임등록관리</title>
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
function moim_inputCheck(){
	if(document.moimFrm.m_code.value==""){
		alert("모임 코드를 입력해 주세요.");
		document.moimFrm.m_code.focus();
		return;
	}

	document.moimFrm.action = "moimlist01_p.jsp";
	document.moimFrm.submit();
}
</script>
</head>
<body bgcolor="#FFFFFF" onLoad="moimFrm.m_code.focus()">
	<div align="center">
		<br /><br />
		<form name="moimFrm" method="post" action="moimlist01_p.jsp">
			<table cellpadding="5">
				<tr>
					<td bgcolor="#FFFFFF">
						<table border="1" cellspacing="0" cellpadding="2" width="800">
							<tr bgcolor="skyblue">
								<td align="center" colspan="3"><font color="#FFFFFF"><b>모임 리스트 등록(관리자 전용)</b></font></td>
							</tr>
							<tr>
								<td width="20%">DB 모임 구분</td>
								<td width="50%"><select name=m_gubn>
										<option value="-" selected>선택하세요.
								<%
                  					Vector<Bean_Code> vlist = null;
                  					int listSize = 0;    //현재 읽어온 자료의 수
				  					vlist = moimMgr.getCodeList("A");
				  					listSize = vlist.size();     //브라우저 화면에 보여질 자료 수
				  					if (vlist.isEmpty()) {
										out.println("등록된 자료가 없습니다.");
				  					} else {
						  				for (int i = 0;i<listSize; i++) {
											Bean_Code bean  = vlist.get(i);
											String gubn = bean.getGubn();
											String code = bean.getCode(); %>
											<option value="<%=code%>"> <%=code%>_<%=gubn%>
										<% }  // for
				  					} %> 
								</select></td>
								<td width="30%">DB에서 모임 구분 선택</td>
							</tr>
							<tr>
								<td>모임 식별코드</td>
								<td><input name="m_code" size="15"></td>
								<td>모임 식별코드 입력</td>
							</tr>
							<tr>
								<td>모임 명칭</td>
								<td><input name="m_name" size="30"></td>
								<td>모임 명칭 입력</td>
							</tr>
							<tr>
								<td>모임 관리자(회장)</td>
								<td><input name="m_jang" size="15"></td>
								<td>모임 관리자(회장) 입력</td>
							</tr>
							<tr>
								<td>관리자 연락처</td>
								<td><input name="m_tel" size="15"></td>
								<td>모임 관리자 연락처 입력</td>
							</tr>
							<tr>
								<td>모임 집결지</td>
								<td><input name="m_jangso" size="30"></td>
								<td>모임 집결지 입력</td>
							</tr>
							<tr>
								<td>비고</td>
								<td><input name="bigo" size="30"></td>
								<td>비고 입력</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								    <input type="button" value="모임등록" onclick="moim_inputCheck()"> &nbsp; &nbsp; 
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