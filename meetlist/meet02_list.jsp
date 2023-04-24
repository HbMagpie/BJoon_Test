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
	String usid = (String) session.getAttribute("idKey");
	Bean_Admin adminBean = moimMgr.getMember(usid);  //회원자료 가져오기

	int recnum = Integer.parseInt(request.getParameter("numb1"));
	int numb = Integer.parseInt(request.getParameter("numb"));
    Bean_Meet mBean = moimMgr.getMeet(numb);    //모임자료 가져오기
    String moim = mBean.getT_m_code();
    String cha = mBean.getT_m_cha();
	//System.out.println("numb : " + numb);
	//System.out.println("recnum : " + recnum);

	String check = request.getParameter("check");
	String perm1 = request.getParameter("perm1");
	String perm2 = request.getParameter("perm2");
	if (check == null) check = "N";  // 구분값
	if (perm1 == null) perm1 = "Z";  // 참석여부 값
	if (perm2 == null) perm2 = "Z";  // 클릭 여부
	//System.out.println(numb + " : " + recnum + " : " + perm1 + " : " + perm2);
	if (perm2.equals("GG")) {
		moimMgr.updateCham(recnum, perm1);
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
<script type="text/javascript">
function meet02_list_s(numb, numb1, cham){
	document.readFrm.numb.value=numb;
	document.readFrm.numb1.value=numb1;
	document.readFrm.perm1.value=cham;
	document.readFrm.perm2.value="GG";
	document.readFrm.action="meet02_list.jsp";
	document.readFrm.target="content";
	document.readFrm.submit();

}
</script>

</head>
<body leftmargin="0" topmargin="0" bgcolor="#FFFFFF">

<div align="center">
    <br/>
		<h2>참석자 명단</h2>
    <br>
	<table align="center" width="700" border="1">
		<tr>
			<td>회원수 : <%=listSize%></td>
		</tr>
	</table>
	<table align="center" width="700" cellpadding="3" border="1">
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
							if (i == listSize) break;
							Bean_List bean = vlist.get(i);
							int numb1 = bean.getNumb();
							moim = bean.getL_moim();
							cha = bean.getL_cha();
							usid = bean.getL_usid();
							String name = bean.getL_name();
							String telp = bean.getL_telp();
							String cham = bean.getL_cham();
					%>
					<tr>
						<td align="center">
						   <%=numb1%>
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
 						   <a href="javascript:meet02_list_s('<%=numb%>','<%=numb1%>', '<%=cham%>')" ><%=cham%></a>
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
		<input type="button" value="뒤로" onClick="history.go(-1)">		
		<input type="hidden" name="numb"> 
		<input type="hidden" name="numb1"> 
		<input type="hidden" name="perm1"> 
		<input type="hidden" name="perm2"> 
	</form>
</div>
</body>
</html>