<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="mMgr" class="ch17_1.MemberMgr"/>
<%
	  request.setCharacterEncoding("UTF-8");
	  String cPath = request.getContextPath();
	  String usid = request.getParameter("usid");
	  String uspw = request.getParameter("uspw");
	  String url = cPath+"/ch17/left.jsp";
	  String msg = "로그인에 실패 하였습니다.";
	  
	  boolean result = mMgr.loginMember(usid,uspw);
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