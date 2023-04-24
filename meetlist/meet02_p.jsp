<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Moim"%>
<%@ page import="ch17_1.Bean_San"%>
<%@ page import="ch17_1.Bean_Meet"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<jsp:useBean id="meetBean" class="ch17_1.Bean_Meet"/>
<jsp:setProperty  name="meetBean" property="*"/>
<%
	  boolean result = moimMgr.updateMeet(meetBean);
	  if(result){
%>
<script type="text/javascript">
		alert("모임(등산) 등록 정보를 수정 하였습니다.");
		location.href="meet02.jsp";
</script>
<% } else {%>
<script type="text/javascript">
		alert("모임(등산) 등록 정보 수정에 실패 하였습니다.");
		history.back();
</script>
<%} %>