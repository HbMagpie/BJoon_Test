<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<jsp:useBean id="moimBean" class="ch17_1.Bean_Moim"/>
<jsp:setProperty  name="moimBean" property="*"/>
<%
	  boolean result = moimMgr.Moim_Insert(moimBean);
	  if(result) {
%>
<script type="text/javascript">
		alert("모임내역을 등록 하였습니다.");
		location.href="moimlist02.jsp";
</script>
<% } else { %>
<script type="text/javascript">
		alert("모임내역 등록에 실패 하였습니다.");
		history.back();
</script>
<% } %>