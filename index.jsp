<%@ page contentType="text/html; charset=UTF-8" %>
<%
      String strTitle = "JSP Home";
	  String cPath = request.getContextPath();
%>
<html>
<head>
<title><%=strTitle%></title>
</head>
<%-- <frameset frameborder="0" framespacing="0" border="0" rows="100,*" >
	<frame  frameborder="0" scrolling="NO" noresize name="head" src="<%=cPath%>/ch17_1/head.jsp"> --%>
	<frameset name="body" frameborder="0" framespacing="0" border="0" rows="*,20"> <!-- 240,* -->		
        <frameset name="main" frameborder="0" framespacing="0" border="0" cols="350,*"> <!-- *,0,37,12 -->
    			<frame name="left" marginwidth="0" marginheight="0" frameborder="0" scrolling="NO" resize="NO" src="<%=cPath%>/ch17_1/left.jsp">
    			<frame name="content" src="<%=cPath%>/ch17_1/main.jsp" scrolling="YES" marginwidth="0" marginheight="0" frameborder="0" noresize>
        </frameset>
		<frame name="copy" src="<%=cPath%>/ch17_1/copy.jsp" scrolling="NO" marginwidth="0" marginheight="0" frameborder="0" noresize>        
	</frameset>
</frameset>
</html>