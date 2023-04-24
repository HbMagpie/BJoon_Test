<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_San"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%	
    int totalRecord = 0; //전체레코드수
    int listSize = 0;    //현재 읽어온 자료의 수
	Vector<Bean_San> vlist = null;
	String usid = (String) session.getAttribute("idKey");
    Bean_Admin gBean = moimMgr.getMember(usid);   //회원자료 가져오기
	int numb = 0;
	totalRecord = moimMgr.getSanCount(usid);
    
%>

<html>
<head>
	<title>명산관리</title>
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
<script type="text/javascript">
	function san02_m(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="san02_m.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}

	function san03(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="san03.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}
</script>

</head>
<body leftmargin="0" topmargin="0" bgcolor="#FFFFFF">

<div align="center">
    <br/>
		<h2>명산 등록 내역</h2>
    <br>
	<table align="center" width="800" border="1">
		<tr>
			<td>명산 등록 자료수 : <%=totalRecord%></td>
		</tr>
	</table>
	<table align="center" width="800" cellpadding="3" border="1">
		<tr>
			<td align="center" colspan="3">
			<%
				  vlist = moimMgr.getSanList(usid);
				  listSize = vlist.size();           //브라우저 화면에 보여질 자료 수
				  if (vlist.isEmpty()) {
					out.println("등록된 자료가 없습니다.");
				  } else {
			%>
				  <table width="100%" cellpadding="2" cellspacing="0" border="1">
					<tr align="center" bgcolor="SKYBLUE" height="120%">
						<td>순서</td>
						<td>등록 아이디</td>
						<td>명산코드</td>
						<td>산명칭</td>
						<td>산소재지</td>
						<td>산높이</td>
						<td>산설명</td>
						<td>수 정</td>
						<td>삭 제</td>
					</tr>
					<%
						  for (int i = 0;i<listSize; i++) {
							Bean_San bean  = vlist.get(i);
							numb = bean.getNumb();
							String usid1 = bean.getS_usid();
							String code = bean.getS_code();
							String name = bean.getS_name();
							String juso = bean.getS_juso();
							String high = bean.getS_high();
							String levl = bean.getS_levl();
							
					%>
					<tr>
						<td align="center">
 						   <a href="javascript:san02_m('<%=numb%>')" ><%=numb%></a>
						</td>
						<td align="center">
 						   <%=usid1%>
						</td>
						<td align="center">
 						   <%=code%>
						</td>
						<td align="center">
 						   <%=name%>
						</td>
						<td align="center">
 						   <%=juso%>
						</td>
						<td align="center">
 						   <%=high%>
						</td>
						<td align="center">
 						   <%=levl%>
						</td>
						<td align="center">
						   <a href="javascript:san02_m('<%=numb%>')">수정</a>
						</td>
						<td align="center">
						   <a href="javascript:san03('<%=numb%>')">삭제</a>
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
		<input type="button" value="명산등록" onClick="location.href='san01.jsp'"> &nbsp; &nbsp;
		<input type="button" value="명산전체" onClick="location.href='san02.jsp'"> &nbsp; &nbsp;
		<input type="hidden" name="numb"> 
	</form>
</div>
</body>
</html>