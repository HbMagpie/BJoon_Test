<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Moim"%>
<%@ page import="ch17_1.Bean_San"%>
<%@ page import="ch17_1.Bean_Meet"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%
	String usid = (String) session.getAttribute("idKey");
	int numb = Integer.parseInt(request.getParameter("numb"));
	Bean_Meet meetBean = moimMgr.getMeet(numb);   //입력자료 가져오기
	String m_code = meetBean.getT_m_code();   // 모임 식별코드 가져오기
	String s_code = meetBean.getT_s_code();   // 명산 식별코드 가져오기
	
    Bean_Admin gBean = moimMgr.getMember(usid);   //회원자료 가져오기
    String name = gBean.getName();                // 회원 이름 가져오기

    Bean_Moim mBean = moimMgr.getMeet(m_code);    //모임자료 가져오기
    String m_name = mBean.getM_name();
    
    Bean_San sBean = moimMgr.getMeet1(s_code);   //명산자료 가져오기
    String s_name = sBean.getS_name();
	
%>
<html>
<head>
	<title>모임관리</title>
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

<body bgcolor="#FFFFFF" onLoad="regFrm.t_m_cha.focus()">
	<div align="center">
		<br /> <br />
		<form name="regFrm" method="post" action="meet02_p.jsp" >
			<table align="center" cellpadding="5" >
				<tr>
					<td align="center" valign="middle" bgcolor="#FFFFFF">
						<table border="1" cellpadding="2" align="center" width="600">
							<tr align="center" bgcolor="SKYBLUE">
								<td colspan="3"><font color="#FFFFFF"><b>모임(등산) 내역 자료 수정</b></font></td>
							</tr>
							<tr>
								<td width="20%">레코드번호</td>
								<td width="50%"><input name="numb" size="15"
									value="<%=meetBean.getNumb()%>" readonly></td>
								<td width="30%">수정불가 : 레코드번호</td>
							</tr>
							<tr>
								<td>관리자 아이디</td>
								<td><input name="t_usid" size="15"
									value="<%=meetBean.getT_usid()%>" readonly><%=name%></td>
								<td>수정불가 : 관리자 아이디</td>
							</tr>
							<tr>
								<td>모임 식별 코드</td>
								<td><input name="t_m_code" size="15"
									value="<%=meetBean.getT_m_code()%>"  readonly><%=m_name%></td>
								<td>모임 식별 코드 입력</td>
							</tr>
							<tr>
								<td>모임 차수</td>
								<td><input name="t_m_cha" size="15"
									value="<%=meetBean.getT_m_cha()%>"></td>
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
											s_code = bean.getS_code();
											s_name = bean.getS_name(); %>
											<option value="<%=s_code%>"> <%=s_name%>
										<% }  // for
				  					} %> 
								</select></td>
								<script>document.regFrm.t_s_code.value="<%=meetBean.getT_s_code()%>"</script>
								<%=s_name%></td>
								<td>명산 식별 코드 입력</td>
							</tr>
							<tr>
								<td>모임(등산) 일자</td>
								<td><input type="date" name="t_m_date" size="15"
									value="<%=meetBean.getT_m_date()%>"></td>
								<td>모임(등산) 일자 입력</td>
							</tr>
							<tr>
								<td>모임 집결지</td>
								<td><input name="t_m_jangso" size="30"
									value="<%=meetBean.getT_m_jangso()%>"></td>
								<td>모임 집결지 입력</td>
							</tr>
							<tr>
								<td>비고</td>
								<td><input name="t_m_bigo" size="30"
									value="<%=meetBean.getT_m_bigo()%>"></td>
								<td>비고 입력</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								<input type="submit" value="수정완료"> &nbsp; &nbsp; 
								<input type="reset" value="다시쓰기"> &nbsp; &nbsp; 
								<input type="button" value="자료삭제" onClick="location.href='meet03.jsp?numb=<%=numb%>'"> &nbsp; &nbsp;
								<input type="button" value="신규자료" onClick="location.href='meet01.jsp'"> &nbsp; &nbsp;
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