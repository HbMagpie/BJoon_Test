<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="ch17_1.ygBean"%>
<%@page import="java.util.Vector"%>
<jsp:useBean id="bMgr" class="ch17_1.ygMgr" />
<%	
	  request.setCharacterEncoding("UTF-8");
	  
      int totalRecord=0; //전체레코드수
	  int numPerPage=10; // 페이지당 레코드 수 
	  int pagePerBlock=15; //블럭당 페이지수 
	  
	  int totalPage=0; //전체 페이지 수
	  int totalBlock=0;  //전체 블럭수 

	  int nowPage=1; // 현재페이지
	  int nowBlock=1;  //현재블럭
	  
	  int start=0; //디비의 select 시작번호
	  int end=10; //시작번호로 부터 가져올 select 갯수
	  
	  int listSize=0; //현재 읽어온 게시물의 수

	String keyWord = "", keyField = "";
	Vector<ygBean> vlist = null;
	if (request.getParameter("keyWord") != null) {
		keyWord = request.getParameter("keyWord");
		keyField = request.getParameter("keyField");
	}
	if (request.getParameter("reload") != null){
		if(request.getParameter("reload").equals("true")) {
			keyWord = "";
			keyField = "";
		}
	}
	
	if (request.getParameter("nowPage") != null) {
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
	}
	 start = (nowPage * numPerPage)-numPerPage;
	 end = numPerPage;
	 
	totalRecord = bMgr.getTotalCount(keyField, keyWord);
	totalPage = (int)Math.ceil((double)totalRecord / numPerPage);  //전체페이지수
	nowBlock = (int)Math.ceil((double)nowPage/pagePerBlock); //현재블럭 계산
	  
	totalBlock = (int)Math.ceil((double)totalPage / pagePerBlock);  //전체블럭계산
%>
<html>
<head>
<title>JSP Board</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function list() {
		document.listFrm.action = "ygList.jsp";
		document.listFrm.submit();
	}
	
	function pageing(page) {
		document.readFrm.nowPage.value = page;
		document.readFrm.submit();
	}
	
	function block(value){
		 document.readFrm.nowPage.value=<%=pagePerBlock%>*(value-1)+1;
		 document.readFrm.submit();
	} 
	
	function read(num){
		document.readFrm.num.value=num;
		document.readFrm.action="read.jsp";
		document.readFrm.submit();
	}
	
	function check() {
	     if (document.searchFrm.keyWord.value == "") {
	   alert("검색어를 입력하세요.");
	   document.searchFrm.keyWord.focus();
	   return;
	     }
	  document.searchFrm.submit();
	 }
</script>
<Style>
@font-face {
    font-family: 'KIMM_Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2212@1.0/KIMM_Bold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

* {
	font-family : KIMM_Bold;
}
</style>

</head>
<body bgcolor="#000000">
<div align="center">
	<br/>
	<h2 style="color: #FFFFFF;">요금 계산자료 기초 등록 내역</h2>
	<br/>
	<table align="center" width="800">
			<tr>
				<td><font color="#FFFFFF">요금 계산 자료 수 : <%=totalRecord%></font><%-- <font color="pink">
				(<%=nowPage%>/<%=totalPage%>Pages)</font> --%></td>
			</tr>
	</table>
	<table align="center" width="800" cellpadding="3">
		<tr>
			<td align="center" colspan="2">
			<%
				  vlist = bMgr.getygList(keyField, keyWord, start, end);
				  listSize = vlist.size();//브라우저 화면에 보여질 게시물갯수
				  if (vlist.isEmpty()) {
					out.println("등록된 게시물이 없습니다.");
				  } else {
			%>
				  <table width="100%" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF" border : 1px solid;>
					<tr align="center"  height="120%" bgcolor="#e1e8ee">
						<td>순 서</td>
						<td>사용자</td>
						<td>요금종류</td>
						<!-- <td>계정 과목</td>
						<td>내용</td> -->				
						<td>전달계량값</td>
						<td>이번달계량값</td>
						<td>사용량</td>
						<td>사용 금액</td>
						<td>부가가치세</td>
						<td>납부금액</td>					
						<td>수 정</td>
						<td>삭 제</td>
					</tr>
					<%
						  for (int i = 0;i<numPerPage; i++) {
							if (i == listSize) break;
							ygBean bean = vlist.get(i);
							int num = bean.getNum();
							String id = bean.getId();
							String name = bean.getName();
							String part = bean.getPart();
							String content = bean.getContent();
							int plus = bean.getPlus();
							int minus = bean.getMinus();
							int total = bean.getTotal();
							int date = bean.getDate();
							int pos = bean.getPos();
							int ref = bean.getRef();
					%>
					<tr>
						<td align="center">
							<%=totalRecord-(numPerPage-2)+i%>
						</td>
						<td align="center"><%=id%></td>
						<td align="center"><%=name%></td>
						<%-- <td align="center"><%=part%></td> --%>					
						<%-- <td align="center"><%=content%></td> --%>
						<td align="center"><%=plus%></td>
						<td align="center"><%=minus%></td>
						<td align="center"><%=total%></td>
						<td align="center"><%=plus%></td>
						<td align="center"><%=minus%></td>
						<td align="center"><%=total%></td>
						<td align="center"><a style="color: red; text-decoration:none;" href="update.jsp?nowPage=<%=nowPage%>&num=<%=num%>">수 정</a></td>
						<td align="center"><a style="color: red; text-decoration:none;" href="delete.jsp?nowPage=<%=nowPage%>&num=<%=num%>">삭 제</a></td>
						</tr>
					<%}//for%>
				</table> <%
 			}//if
 		%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input style="width:150px; height:30px; margin-top:50px; margin-left:300px; border-radius : 12px; color:#000000; background-color:#FFFFFF;" type="button" value="사용량 등록">
			</td>
		</tr>	
		</table>
</div>
</body>
</html>