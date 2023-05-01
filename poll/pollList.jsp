<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="ch17_1.*,java.util.Vector"%>
<jsp:useBean id="pMgr" class="ch17_1.PollMgr" />
<html>
<head>
<title>JSP Poll</title>
<link href="style.css" rel="stylesheet" type="text/css">
<Style>
@font-face {
    font-family: 'KIMM_Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2212@1.0/KIMM_Bold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

* {
	font-family : KIMM_Bold;
}
</style>
</head>
<body bgcolor="#000000">
	<div align="center">
		<br/>
		<h2 style="color:#FFFFFF;">투표프로그램</h2>
		<hr width="600"/>
		<b style="color:#FFFFFF;">설문폼</b>
		<!--폴 리스트 폼 자리-->
		<jsp:include page="pollForm.jsp" />
		<hr width="600"/>
		<b style="color:#FFFFFF;">설문리스트</b>
		<!--폴 리스트 테이블 자리-->
		<table>
			<tr>
				<td>
					<table width="500" border="1" style="background-color:#FFFFFF;">
						<tr>
							<td align="center"><b>번호</b></td>
							<td><b>제목</b></td>
							<td><b>시작일~종료일</b></td>
						</tr>
						<%
							  Vector<PollListBean> vlist = pMgr.getAllList();

							  int count = vlist.size();
							  for (int i = 0; i < vlist.size(); i++) {
								PollListBean plBean = vlist.get(i);
								int num = plBean.getNum();
								String question = plBean.getQuestion();
								String sdate = plBean.getSdate();
								String edate = plBean.getEdate();

								out.println("	<tr><td align='center'>" + count + "</td>");
								out.println("<td><a href='pollList.jsp?num=" + num + "'>"
										+ question + "</a></td>");
								out.println("<td>" + sdate +" ~ "+ edate + "</td></tr>");
								count = count - 1;
							}
						%>
					</table>
				</td>
			</tr>
			<tr>
				<td align="right"><a href="pollInsert.jsp">설문 작성하기</a></td>
			</tr>
		</table>
	</div>
</body>
</html>