<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Moim"%>
<%@ page import="ch17_1.Bean_Code"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%	
    int totalRecord = 0; //전체레코드수
    int listSize = 0;    //현재 읽어온 자료의 수
	Vector<Bean_Moim> vlist = null;
	String usid = (String) session.getAttribute("idKey");
    Bean_Admin gBean = moimMgr.getMember(usid);   //회원자료 가져오기
    String check = gBean.getGubn();
	int numb = 0;
    
	totalRecord = moimMgr.getMoimCount();
	
	// 계산 시작

	// 계산 종료
    
%>

<html>
<head>
	<title>모임등록관리</title>
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
	function moimlist02_m(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="moimlist02_m.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}

	function moimlist03(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="moimlist03.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}

	function check() {
	     if (document.searchFrm.keyWord.value == "") {
			alert("검색어를 입력하세요.");
			document.searchFrm.keyWord.focus();
			return;
	     }
	  document.searchFrm.action="moimlist02.jsp";
      document.searchFrm.target="content";
	  document.searchFrm.submit();
	 }
</script>

</head>
<body leftmargin="0" topmargin="0" bgcolor="#FFFFFF">

<div align="center">
    <br/>
		<h2>모임 등록 내역</h2>
    <br>
	<table align="center" width="800" border="1">
		<tr>
			<td>모임 내역 자료수 : <%=totalRecord%></td>
		</tr>
	</table>
	<table align="center" width="800" cellpadding="3" border="1">
		<tr>
			<td align="center" colspan="3">
			<%
				  vlist = moimMgr.getMoimList("XX");
				  listSize = vlist.size();           //브라우저 화면에 보여질 자료 수
				  if (vlist.isEmpty()) {
					out.println("등록된 자료가 없습니다.");
				  } else {
			%>
				  <table width="100%" cellpadding="2" cellspacing="0" border="1">
					<tr align="center" bgcolor="skyblue" height="120%">
						<td>순서</td>
						<td>모임구분</td>
						<td>모임코드</td>
						<td>모임명칭</td>
						<td>관리자(회장)</td>
						<td>연락처</td>
						<td>모임집결지</td>
						<td>수 정</td>
						<td>삭 제</td>
					</tr>
					<%
						  for (int i = 0;i<listSize; i++) {
							if (i == listSize) break; 
							Bean_Moim bean  = vlist.get(i);
							numb = bean.getNumb();
							String gubn = bean.getM_gubn();
							String code = bean.getM_code();
							String name = bean.getM_name();
							String jang = bean.getM_jang();
							String tel = bean.getM_tel();
							String jangso = bean.getM_jangso();
					%>
					<tr>
						<td align="center">
 						   <a href="javascript:moimlist02_m('<%=numb%>')" ><%=numb%></a>
						</td>
						<td align="center">
 						   <%=gubn%>
						</td>
						<td align="center">
 						   <%=code%>
						</td>
						<td align="center">
 						   <%=name%>
						</td>
						<td align="center">
 						   <%=jang%>
						</td>
						<td align="center">
 						   <%=tel%>
						</td>
						<td align="center">
 						   <%=jangso%>
						</td>
						<td align="center">
						   <a href="javascript:moimlist02_m('<%=numb%>')">수정</a>
						</td>
						<td align="center">
						   <a href="javascript:moimlist03('<%=numb%>')">삭제</a>
						</td>
					</tr>
					<%}//for%>
				</table> <%
 			}//if
 		%>
			</td>
		</tr>
		</table>
		
	<form name="readFrm" method="get">
	    <br>
		<input type="button" value="모임등록" onClick="location.href='moimlist01.jsp'"> &nbsp; &nbsp;
		<input type="button" value="전체자료" onClick="location.href='moimlist02.jsp?check=S'"> &nbsp; &nbsp;
		<input type="hidden" name="numb"> 
	</form>
</div>
</body>
</html>