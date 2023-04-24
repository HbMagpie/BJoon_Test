<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="chulMgr" class="ch17_1.Manager_Chul"/>
<jsp:useBean id="codeBean" class="ch17_1.Bean_Code"/>
<jsp:setProperty  name="codeBean" property="*"/>
<%
	  boolean result = chulMgr.Code_Insert(codeBean);
	  if(result) {
%>
<script type="text/javascript">
		alert("코드 등록을 하였습니다.");
		location.href="code02.jsp";
</script>
<% } else { %>
<script type="text/javascript">
		alert("코드 등록에 실패 하였습니다.");
		history.back();
</script>
<% } %>