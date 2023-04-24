<%@ page contentType="text/html; charset=UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="mMgr" class="ch17_1.MemberMgr"/>
<jsp:useBean id="mBean" class="ch17_1.MemberBean"/>
<jsp:setProperty  name="mBean" property="*"/>
<%
	  boolean result = mMgr.insertMember(mBean);
	  if(result){
%>
<script type="text/javascript">
		alert("등록이 완료되었습니다.");
		location.href="../main.jsp";
</script>
<% }else{%>
<script type="text/javascript">
		alert("등록이 실패 하였습니다.");
		history.back();
</script>
<%} %>