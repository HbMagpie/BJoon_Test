<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Don"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<jsp:useBean id="donBean" class="ch17_1.Bean_Don"/>
<jsp:setProperty  name="donBean" property="*"/>
<%
	  boolean result = moimMgr.updateDon(donBean);
	  if(result){
%>
<script type="text/javascript">
		alert("회비납부 정보를 수정 하였습니다.");
		location.href="don02.jsp";
</script>
<% } else {%>
<script type="text/javascript">
		alert("회비납부 정보 수정에 실패 하였습니다.");
		history.back();
</script>
<% } %>