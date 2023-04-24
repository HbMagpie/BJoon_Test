<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Code"%>
<jsp:useBean id="chulMgr" class="ch17_1.Manager_Chul"/>
<jsp:useBean id="chulBean" class="ch17_1.Bean_Code"/>
<jsp:setProperty  name="chulBean" property="*"/>
<%
	  boolean result = chulMgr.updateCode(chulBean);
	  if(result){
%>
<script type="text/javascript">
		alert("코드정보 수정 하였습니다.");
		location.href="code02.jsp";
</script>
<% }else{%>
<script type="text/javascript">
		alert("코드정보 수정에 실패 하였습니다.");
		history.back();
</script>
<%} %>