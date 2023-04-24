<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Code"%>
<jsp:useBean id="chulMgr" class="ch17_1.Manager_Chul"/>
<%	
    int totalRecord = 0; //전체레코드수
    int listSize = 0;    //현재 읽어온 자료의 수
	Vector<Bean_Code> vlist = null;
	
	String usid = (String) session.getAttribute("idKey");
	String check = request.getParameter("check");
	
	if (check == null) check = "S";
    Bean_Admin gBean = chulMgr.getMember(usid);   //회원자료 가져오기
    check = gBean.getGubn();
	int numb = 0;
    
	String keyWord = "", keyField = "";
	if (request.getParameter("keyWord") != null) {
		keyWord = request.getParameter("keyWord");
		keyField = request.getParameter("keyField");
		check = "B";
	}

	totalRecord = chulMgr.getCodeCount(check, keyWord, keyField, usid);
    
%>

<html>
<head>
<title>copy</title>
<link href="../main/style.css" rel="stylesheet" type="text/css" >
<Style>
@font-face {
    font-family: 'KIMM_Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2212@1.0/KIMM_Bold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

* {
	font-family: 'KIMM_Bold';
}
</style>
<script type="text/javascript">
	function code02_m(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="code02_m.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}

	function code03(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="code03.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}

	function check() {
	     if (document.searchFrm.keyWord.value == "") {
			alert("검색어를 입력하세요.");
			document.searchFrm.keyWord.focus();
			return;
	     }
	  document.searchFrm.action="code02.jsp";
      document.searchFrm.target="content";
	  document.searchFrm.submit();
	 }
</script>

</head>
<body leftmargin="0" topmargin="0" bgcolor="#FFFFFF">

<div align="center">
    <br/>
		<h2>코 드 등 록 내 역</h2>
    <br>
	<table align="center" width="800" border="1">
		<tr>
			<td>코드 자료수 : <%=totalRecord%></td>
		</tr>
	</table>
	<table align="center" width="800" cellpadding="3" border="1">
		<tr>
			<td align="center" colspan="3">
			<%
				  vlist = chulMgr.getCodeList(check, keyWord, keyField, usid);
				  listSize = vlist.size();           //브라우저 화면에 보여질 자료 수
				  if (vlist.isEmpty()) {
					out.println("등록된 자료가 없습니다.");
				  } else {
			%>
				  <table width="100%" cellpadding="2" cellspacing="0" border="1">
					<tr align="center" bgcolor="skyblue" height="120%">
						<td>순서</td>
						<td>사용자 아이디</td>
						<td>Code</td>
						<td>코드내용</td>
						<td>수 정</td>
						<td>삭 제</td>
					</tr>
					<%
						  for (int i = 0;i<listSize; i++) {
							if (i == listSize) break; 
							Bean_Code bean  = vlist.get(i);
							numb = bean.getNumb();
							String usid1 = bean.getUsid();
							String code = bean.getCode();
							String gubn = bean.getGubn();
					%>
					<tr>
						<td align="center">
 						   <a href="javascript:code02_m('<%=numb%>')" ><%=numb%></a>
						</td>
						<td align="center">
 						   <%=usid1%>
						</td>
						<td align="center">
 						   <%=code%>
						</td>
						<td align="center">
 						   <%=gubn%>
						</td>
						<td align="center">
						   <a href="javascript:code02_m('<%=numb%>')">수정</a>
						</td>
						<td align="center">
						   <a href="javascript:code03('<%=numb%>')">삭제</a>
						</td>
					</tr>
					<%}//for%>
				</table> <%
 			}//if
 		%>
			</td>
		</tr>
		</table>
		
	<form  name="searchFrm"  method="get" action="code2.jsp">
	<table width="600" cellpadding="4" cellspacing="0">
 		<tr>
  			<td align="center" valign="bottom">
   				<select name="keyField" size="1" >
    				<option value="usid"> 사용자 아이디</option>
    				<option value="code"> 등록코드</option>
    				<option value="gubn"> 코드내용</option>
   				</select>
   				<input size="16" name="keyWord">
   				<input type="button"  value="코드찾기" onClick="check()">
 		</tr>
	
	</table>
	</form>
		
	<form name="readFrm" method="get">
	    <br>
		<input type="button" value="코드등록" onClick="location.href='code01.jsp'"> &nbsp; &nbsp;
		<input type="button" value="전체코드" onClick="location.href='code02.jsp?check=S'"> &nbsp; &nbsp;
		<input type="hidden" name="numb"> 
	</form>
</div>
</body>
</html>