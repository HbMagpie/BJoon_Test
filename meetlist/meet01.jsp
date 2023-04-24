<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ page import="java.util.Vector"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Moim"%>
<%@ page import="ch17_1.Bean_San"%>
<%@ page import="ch17_1.Bean_Meet"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%
	String usid = (String) session.getAttribute("idKey");
    Bean_Admin gBean = moimMgr.getMember(usid);   //회원자료 가져오기
    String t_code = gBean.getMoim();

    Bean_Moim mBean = moimMgr.getMeet(t_code);   //모임자료 가져오기
    String m_name = mBean.getM_name();
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
<script type="text/javascript">
function meet_inputCheck(){
	if(document.meetFrm.t_m_code.value==""){
		alert("모임 코드를 입력해 주세요.");
		document.meetFrm.t_m_code.focus();
		return;
	}

	document.meetFrm.action = "meet01_p.jsp";
	document.meetFrm.submit();
}
</script>
</head>
<body bgcolor="#FFFFFF" onLoad="meetFrm.t_m_code.focus()">
	<div align="center">
		<br /><br />
		<form name="meetFrm" method="post" action="meet01_p.jsp">
			<table cellpadding="5">
				<tr>
					<td bgcolor="#FFFFFF">
						<table border="1" cellspacing="0" cellpadding="2" width="600">
							<tr bgcolor="SKYBLUE">
								<td align="center" colspan="3"><font color="#FFFFFF"><b>모임(등산) 리스트 등록</b></font></td>
							</tr>
							<tr>
								<td width="20%">관리자 아이디</td>
								<td width="50%"><input name="t_usid" size="15"
								                       value="<%=usid%>" readonly></td>
								<td width="30%">수정불가 : 관리자 아이디</td>
							</tr>
							<tr>
								<td>모임 식별 코드</td>
								<td><input name="t_m_code" size="15"
									value="<%=gBean.getMoim()%>" readonly>
									<%=m_name%></td>
								<td>모임 식별 코드 입력</td>
							</tr>
							<tr>
								<td>모임 차수</td>
								<td><input name="t_m_cha" size="15"></td>
								<td>모임 차수 입력</td>
							</tr>
							<tr>
								<td>명산 식별 코드</td>
								<td><select name="t_s_code">
										<option value="0" selected disabled>선택하세요.
								<%
                  					Vector<Bean_San> vlist = null;
                  					int listSize = 0;    //현재 읽어온 자료의 수
                  					//System.out.println("구분값2 : " + m_gubn_y);
                  					//System.out.println("구분값3 : " + m_gubn_y.substring(0,2));
                  					//System.out.println(m_gubn_y.substring(0,2));
				  					vlist = moimMgr.getSanList(usid);
				  					listSize = vlist.size();      //브라우저 화면에 보여질 자료 수
				  					if (vlist.isEmpty()) {
										out.println("등록된 자료가 없습니다.");
				  					} else {
						  				for (int i = 0;i<listSize; i++) {
						  					Bean_San bean  = vlist.get(i);
											String s_code = bean.getS_code();
											String s_name = bean.getS_name(); %>
											<option value="<%=s_code%>"> <%=s_name%>
										<% }  // for
				  					} %> 
								</select></td>
								<td>명산 식별 코드 입력</td>
							</tr>
							<tr>
								<td>모임(등산) 일자</td>
								<td><input type="date" name="t_m_date" size="15"></td>
								<td>모임(등산) 일자 입력</td>
							</tr>
							<tr>
								<td>모임 집결지</td>
								<td><input name="t_m_jangso" size="30"></td>
								<td>모임 집결지 입력</td>
							</tr>
							<tr>
								<td>비고</td>
								<td><input name="t_m_bigo" size="30"></td>
								<td>비고 입력</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								    <input type="button" value="모임등록" onclick="meet_inputCheck()"> &nbsp; &nbsp; 
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