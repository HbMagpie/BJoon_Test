<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Moim"%>
<%@ page import="ch17_1.Bean_San"%>
<%@ page import="ch17_1.Bean_Meet"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%
    int totalRecord = 0; //전체레코드수
    int listSize = 0;    //현재 읽어온 자료의 수
	Vector<Bean_Meet> vlist = null;
	String usid = (String) session.getAttribute("idKey");
    Bean_Admin gBean = moimMgr.getMember(usid);   //회원자료 가져오기
    String Moim = gBean.getMoim();
	int numb = 0;
	totalRecord = moimMgr.getMeetCount(usid);
%>

<html>
<head>
	<title>모임관리</title>
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
	function meet02_m(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="meet02_m.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}

	function meet03(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="meet03.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}
	
	function meet02_usid(usid){
		url = "meet02_usid.jsp?usid="  + usid;
		window.open(url, "Meet02_usid","width=300,height=300,scrollbars=yes");
	}
	
	function meet02_moim(moim){
		url = "meet02_moim.jsp?moim="  + moim;
		window.open(url, "Meet02_moim","width=300,height=300,scrollbars=yes");
	}
	
	function meet02_san(san){
		url = "meet02_san.jsp?san="  + san;
		window.open(url, "Meet02_san","width=300,height=300,scrollbars=yes");
	}
	
	function meet02_c(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="meet02_c.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}

	function meet02_list(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.numb1.value=0;
		document.readFrm.action="meet02_list.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}
</script>

</head>
<body leftmargin="0" topmargin="0" bgcolor="#FFFFFF">

<div align="center">
    <br/>
		<h2>모임(등산) 내역 등록</h2>
    <br>
	<table align="center" width="1000" border="1">
		<tr>
			<td>모임(등산) 횟수 : <%=totalRecord%></td>
		</tr>
	</table>
	<table align="center" width="1000" cellpadding="3" border="1">
		<tr>
			<td align="center" colspan="3">
			<%
				  vlist = moimMgr.getMeetList(usid);
				  listSize = vlist.size();           //브라우저 화면에 보여질 자료 수
				  if (vlist.isEmpty()) {
					out.println("등록된 자료가 없습니다.");
				  } else {
			%>
				  <table width="100%" cellpadding="2" cellspacing="0" border="1">
					<tr align="center" bgcolor="SKYBLUE" height="120%">
						<td>순서</td>
						<td>관리자</td>
						<td>모임코드</td>
						<td>모임명칭</td>
						<td>모임차수</td>
						<td>명산코드</td>
						<td>명산이름</td>
						<td>모임일자</td>
						<td>모임집결지</td>
						<td>모임내용</td>
						<td>명 단</td>
						<td>현 황</td>
						<td>수 정</td>
						<td>삭 제</td>
					</tr>
					<%
						  for (int i = 0;i<listSize; i++) {
							Bean_Meet bean  = vlist.get(i);
							numb = bean.getNumb();
							//String usid = bean.getM_gubn();
							String m_code = bean.getT_m_code();
							String cha = bean.getT_m_cha();
							String s_code = bean.getT_s_code();
							String date = bean.getT_m_date();
							String jangso = bean.getT_m_jangso();
							String bigo = bean.getT_m_bigo();

							Bean_Moim mBean = moimMgr.getMeet(m_code);   //모임자료 가져오기
						    String m_name = mBean.getM_name();
						    Bean_San sBean = moimMgr.getMeet1(s_code);   //명산자료 가져오기
						    String s_name = sBean.getS_name();
							
					%>
					<tr>
						<td align="center">
 						   <a href="javascript:meet02_m('<%=numb%>')" ><%=numb%></a>
						</td>
						<td align="center">
 						   <a href="javascript:meet02_usid('<%=usid%>')" ><%=usid%></a>
						</td>
						<td align="center">
 						   <a href="javascript:meet02_moim('<%=m_code%>')" ><%=m_code%></a>
						</td>
						<td align="center">
 						   <%=m_name%>
						</td>
						<td align="center">
 						   <%=cha%>
						</td>
						<td align="center">
 						   <a href="javascript:meet02_san('<%=s_code%>')" ><%=s_code%></a>
						</td>
						<td align="center">
 						   <%=s_name%>
						</td>
						<td align="center">
 						   <%=date%>
						</td>
						<td align="center">
 						   <%=jangso%>
						</td>
						<td align="center">
 						   <%=bigo%>
						</td>
						<td align="center">
						   <a href="javascript:meet02_c('<%=numb%>')">명단</a>
						</td>
						<td align="center">
						   <a href="javascript:meet02_list('<%=numb%>')">현황</a>
						</td>
						<td align="center">
						   <a href="javascript:meet02_m('<%=numb%>')">수정</a>
						</td>
						<td align="center">
						   <a href="javascript:meet03('<%=numb%>')">삭제</a>
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
		<input type="button" value="모임등록" onClick="location.href='meet01.jsp'"> &nbsp; &nbsp;
		<input type="button" value="전체자료" onClick="location.href='meet02.jsp?check=S'"> &nbsp; &nbsp;
		<input type="hidden" name="numb"> 
		<input type="hidden" name="numb1"> 
	</form>
</div>
</body>
</html>