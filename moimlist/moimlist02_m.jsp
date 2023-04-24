<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Moim"%>
<%@ page import="ch17_1.Bean_Code"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%
	String usid = (String) session.getAttribute("idKey");
	int numb = Integer.parseInt(request.getParameter("numb"));
	Bean_Moim moimBean = moimMgr.getMoim(numb);     //입력자료 가져오기
%>
<html>
<head>
	<title>모임등록관리</title>
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
		<form name="regFrm" method="post" action="moimlist02_p.jsp" >
			<table align="center" cellpadding="5" >
				<tr>
					<td align="center" valign="middle" bgcolor="#FFFFFF">
						<table border="1" cellpadding="2" align="center" width="600">
							<tr align="center" bgcolor="skyblue">
								<td colspan="3"><font color="#FFFFFF"><b>모임 자료 수정</b></font></td>
							</tr>
							<tr>
								<td width="20%">레코드번호</td>
								<td width="50%"><input name="numb" size="15"
									value="<%=moimBean.getNumb()%>" readonly></td>
								<td width="30%">DB에서 모임 구분 선택</td>
							</tr>
							<tr>
								<td>DB 모임 구분</td>
								<td><select name=m_gubn>
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
											<option value="<%=code%>"> <%=gubn%>
										<% }  // for
				  					} %> 
								</select></td>
								<script>document.regFrm.m_gubn.value="<%=moimBean.getM_gubn()%>"</script>
								<td>DB에서 모임 구분 선택</td>
							</tr>
							<tr>
								<td>모임 식별코드</td>
								<td><input name="m_code" size="15"
									value="<%=moimBean.getM_code()%>"></td>
								<td>모임 식별코드 입력</td>
							</tr>
							<tr>
								<td>모임 명칭</td>
								<td><input name="m_name" size="30"
									value="<%=moimBean.getM_name()%>"></td>
								<td>모임 명칭 입력</td>
							</tr>
							<tr>
								<td>모임 관리자(회장)</td>
								<td><input name="m_jang" size="15"
									value="<%=moimBean.getM_jang()%>"></td>
								<td>모임 관리자(회장) 입력</td>
							</tr>
							<tr>
								<td>관리자 연락처</td>
								<td><input name="m_tel" size="15"
									value="<%=moimBean.getM_tel()%>"></td>
								<td>모임 관리자 연락처 입력</td>
							</tr>
							<tr>
								<td>모임 집결지</td>
								<td><input name="m_jangso" size="30"
									value="<%=moimBean.getM_jangso()%>"></td>
								<td>모임 집결지 입력</td>
							</tr>
							<tr>
								<td>비고</td>
								<td><input name="m_bigo" size="30"
									value="<%=moimBean.getM_bigo()%>"></td>
								<td>비고 입력</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								<input type="submit" value="수정완료"> &nbsp; &nbsp; 
								<input type="reset" value="다시쓰기"> &nbsp; &nbsp; 
								<input type="button" value="자료삭제" onClick="location.href='moimlist03.jsp?numb=<%=numb%>'"> &nbsp; &nbsp;
								<input type="button" value="신규자료" onClick="location.href='moimlist01.jsp'"> &nbsp; &nbsp;
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