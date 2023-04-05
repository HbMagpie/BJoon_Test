<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="ch17_1.MemberBean"%>
<%@page import="java.util.Vector"%>
<jsp:useBean id="mMgr" class="ch17_1.MemberMgr"/>
<%	
	request.setCharacterEncoding("UTF-8");
	  
    int totalRecord=0; //전체레코드수
    int listSize=0;    //현재 읽어온 게시물의 수
	Vector<MemberBean> vlist = null;

	  String usid = (String) session.getAttribute("idKey");
	  String cPath = request.getContextPath();
	  
	  String url1 = "member/member.jsp";
	  String url2 = "member/member.jsp";
	  String url3 = "member/member.jsp";
	  String url4 = "member/member.jsp";
	  String label = "회원가입";
	  
      if(usid != null){
        url1 = "member/memberUpdate.jsp";
        url2 = "board/list.jsp";
        url3 = "poll/pollList.jsp";
        url4 = "hanrasan/h01.jsp";
        label = "회원수정";
      }

      totalRecord = mMgr.getTotalCount();
%>

<html>
<head>
<title>copy</title>
	<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function h02(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="hanrasan/h02.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}
</script>

</head>
<body leftmargin="0" topmargin="0" bgcolor="#D9E5FF">
	<jsp:include page="member/login.jsp"/>
	<hr>
<div align="center">
    <br>
	<font size="3"><a href="<%=url1%>" target="content"><b><%=label%></b></a></font><br><br>
	<font size="3"><a href="<%=url2%>" target="content"><b>게시판</b></a></font><br><br>
	<font size="3"><a href="<%=url3%>" target="content"><b>투표프로그램</b></a></font><br><br>
	<font size="3"><a href="<%=url4%>" target="content"><b>한라산[신규회원]</b></a></font><br><br>
</div>

<div align="center">
    <br>
	<table align="center" width="200">
		<tr>
			<td>회원수 : <%=totalRecord%></td>
		</tr>
	</table>
	<table align="center" width="200" cellpadding="3">
		<tr>
			<td align="center" colspan="3">
			<%
				  vlist = mMgr.getMemberList();
				  listSize = vlist.size();//브라우저 화면에 보여질 게시물 번호
				  if (vlist.isEmpty()) {
					out.println("등록된 게시물이 없습니다.");
				  } else {
			%>
				  <table width="100%" cellpadding="2" cellspacing="0">
					<tr align="center" bgcolor="#D0D0D0" height="120%">
						<td>번 호</td>
						<td>아이디</td>
						<td>이 름</td>
					</tr>
					<%
						  for (int i = 0;i<listSize; i++) {
							MemberBean bean = vlist.get(i);
							int numb = bean.getNumb();
							usid = bean.getUsid();
							String name = bean.getName();
					%>
					<tr>
						<td align="center">
						   <%=numb%>
						</td>
						<td>
 						   <a href="javascript:h02('<%=numb%>')"><%=usid%></a>
						</td>
						   <td align="center"><%=name%>
						</td>
					</tr>
					<%}//for%>
				</table> <%
 			}//if
 		%>
			</td>
		</tr>
		</table>
	<hr width="600"/>
	<form name="readFrm" method="get">
		<input type="hidden" name="numb"> 
	</form>
</div>
 
</body>
</html>