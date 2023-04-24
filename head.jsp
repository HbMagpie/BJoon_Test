<%@ page contentType="text/html; charset=UTF-8" %>
<%
	  String id = (String) session.getAttribute("idKey");
	  String cPath = request.getContextPath();
	  String url = "member/member.jsp";
	  String label = "회원가입";
      if(id!=null){
        url = "member/memberUpdate.jsp";
        label = "회원수정";
      }
%>
<html>
<head>
<title>head</title>
<link href="style.css" rel="stylesheet" type="text/css">
<Style>
@font-face {
    font-family: 'KIMM_Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2212@1.0/KIMM_Bold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}
</style>
</head>
<body bgcolor="#000000;">
	<table  width="1280" cellpadding="0" cellspacing="0" >	
		<tr>
			<td height="100">
				<a href="<%=cPath%>/ch17_1/index.jsp" target="_parent" onFocus="this.blur();"
				style="font-family : KIMM_Bold; margin-left:140px; font-size:15px; color: #FFFFFF; text-decoration:none;">new world&nbsp;!&nbsp;!</a><br>
				<a href="<%=cPath%>/ch17_1/index.jsp" target="_parent" onFocus="this.blur();"
				style="font-family : KIMM_Bold; margin-left:80px; font-size:30px; color: skyblue; text-decoration:none;">
				JSP Home🏡</a>
			</td>
		</tr>
	</table>
</body>
</html>