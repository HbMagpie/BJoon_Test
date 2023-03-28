<%@ page contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="bMgr" class="ch15.BoardMgr"/>
<%
	  bMgr.insertBoard(request);
	  response.sendRedirect("list.jsp");
%>