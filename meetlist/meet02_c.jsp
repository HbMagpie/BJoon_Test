<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Moim"%>
<%@ page import="ch17_1.Bean_Meet"%>
<%@ page import="ch17_1.Bean_List"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>

<%
	int totalRecord = 0; //전체레코드수
	int listSize = 0;    //현재 읽어온 자료의 수
	Vector<Bean_List> vlist = null;
	request.setCharacterEncoding("UTF-8");
	String usid = (String) session.getAttribute("idKey");
	//Bean_Admin adminBean = moimMgr.getMember(usid);  //회원자료 가져오기

	int numb = Integer.parseInt(request.getParameter("numb"));
    Bean_Meet mBean = moimMgr.getMeet(numb);    //모임자료 가져오기
    String moim = mBean.getT_m_code();
    String cha = mBean.getT_m_cha();

	//System.out.println("참석대상자 생성");
    Vector<Bean_Admin> vlist_a = null;
   	vlist_a = moimMgr.getAdminList(moim);
	listSize = vlist_a.size();           //처리 할 자료 수
    for (int i = 0;i<listSize; i++) {
    	Bean_Admin bean  = vlist_a.get(i);
		usid = bean.getUsid();
		//int numb = bean.getNumb();
		String name = bean.getName();
		String telp = bean.getTelp();
		
		boolean result = moimMgr.Cham_List(moim, cha, usid);
		if(result) {
			//System.out.println(" 존재하는 아이디 : " + usid);
		} else {
			moimMgr.List_Insert(moim, cha, usid, name, telp);
		}
    }
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
</head>
<body leftmargin="0" topmargin="0" bgcolor="#FFFFFF">

<div align="center">
    <br/>
		<h2>참석자 명단</h2>
    <br>
	<table align="center" width="600" border="1">
		<tr>
			<td>회원수 : <%=listSize%></td>
		</tr>
	</table>
	<table align="center" width="600" cellpadding="3" border="1">
		<tr>
			<td align="center" colspan="3">
			<%
				  vlist = moimMgr.getChamList(moim, cha); 
				  listSize = vlist.size();//브라우저 화면에 보여질 게시물 번호
				  if (vlist.isEmpty()) {
					out.println("등록된 게시물이 없습니다.");
				  } else {
			%>
				  <table width="100%" cellpadding="2" cellspacing="0" border="1">
					<tr align="center" bgcolor="skyblue" height="120%">
						<td>번 호</td>
						<td>모임코드</td>
						<td>모임차수</td>
						<td>아이디</td>
						<td>회원이름</td>
						<td>전화번호</td>
						<td>참석여부</td>
					</tr>
					<%
						  for (int i = 0;i<listSize; i++) {
							Bean_List bean = vlist.get(i);
							numb = bean.getNumb();
							moim = bean.getL_moim();
							cha = bean.getL_cha();
							usid = bean.getL_usid();
							String name = bean.getL_name();
							String telp = bean.getL_telp();
							String cham = bean.getL_cham();
					%>
					<tr>
						<td align="center">
						   <%=numb%>
						</td>
						<td align="center">
						   <%=moim%>
						</td>
						<td align="center">
						   <%=cha%>
						</td>
						<td align="center">
						   <%=usid%>
						</td>
						<td align="center">
						   <%=name%>
						</td>
						<td align="center">
						   <%=telp%>
						</td>
						<td align="center">
						   <%=cham%>
						</td>
					</tr>
					<%}//for%>
				</table> <%
 			}//if
 		%>
			</td>
		</tr>
		</table>
	<form name="readFrm" method="get">
	    <br>
		<input type="hidden" name="numb">
		<input type="button" value="뒤로" onClick="history.go(-1)">		
	</form>
</div>
</body>
</html>