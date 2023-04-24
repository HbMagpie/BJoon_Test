<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="chulMgr" class="ch17_1.Manager_Chul"/>
<%
	  String cPath = request.getContextPath();
	  String usid = request.getParameter("usid");
	  String uspw = request.getParameter("uspw");
	  String url = cPath+"/ch17_1/main/left.jsp";
	  String msg = "로그인에 실패 하였습니다.";

	  boolean result = chulMgr.loginMember(usid,uspw);
	  if(result){
	  	session.setAttribute("idKey",usid);
	  	msg = "로그인에 성공 하였습니다.";
	  }
%>
<script>
	alert("<%=msg%>");
	top.document.location.reload(); 
	location.href="<%=url%>";
</script>