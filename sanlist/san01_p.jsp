<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<jsp:useBean id="sanBean" class="ch17_1.Bean_San"/>
<jsp:setProperty  name="sanBean" property="*"/>
<%
	  boolean result = moimMgr.San_Insert(sanBean);
	  if(result) {
%>
<script type="text/javascript">
		alert("명산 등록을 하였습니다.");
		location.href="san02.jsp";
</script>
<% } else { %>
<script type="text/javascript">
		alert("명산 등록에 실패 하였습니다.");
		history.back();
</script>
<% } %>