<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="ch17_1.Bean_Admin"%>
<%@ page import="ch17_1.Bean_Moim"%>
<%@ page import="ch17_1.Bean_Don"%>
<jsp:useBean id="moimMgr" class="ch17_1.Manager_Moim"/>
<%	
	DecimalFormat df = new DecimalFormat("###,###.#");
    int totalRecord = 0; //전체레코드수
    int listSize = 0;    //현재 읽어온 자료의 수
	String usid = (String) session.getAttribute("idKey");
    Bean_Admin gBean = moimMgr.getMember(usid);   //회원자료 가져오기
    String moim = gBean.getMoim();
	int numb = 0;
    
	//System.out.println("납부대상자 생성");
    Vector<Bean_Admin> vlist = null;
   	vlist = moimMgr.getAdminList(moim);
	listSize = vlist.size();           //처리 할 자료 수
    for (int i = 0;i<listSize; i++) {
    	Bean_Admin bean  = vlist.get(i);
		usid = bean.getUsid();
		//int numb = bean.getNumb();
		//String name = bean.getName();
		//String telp = bean.getTelp();
		
		boolean result = moimMgr.Don_List(moim, usid);
		if(result) {
			//System.out.println(" 존재하는 아이디 : " + usid);
		} else {
			moimMgr.Don_Insert(moim, usid);
		}
    }
	
	// 회비 합계 계산 시작
	Vector<Bean_Don> d_vlist = null;
	d_vlist = moimMgr.getDonList(moim);
	listSize = d_vlist.size();           //브라우저 화면에 보여질 자료 수
    for (int i = 0;i<listSize; i++) {
    	Bean_Don bean  = d_vlist.get(i);
		numb = bean.getNumb();
		usid = bean.getD_usid();
		int d_d01 = bean.getD_d01();
		int d_d02 = bean.getD_d02();
		int d_d03 = bean.getD_d03();
		int d_d04 = bean.getD_d04();
		int d_d05 = bean.getD_d05();
		int d_tot = d_d01 + d_d02 + d_d03 + d_d04 + d_d05 ;

		moimMgr.Don_Update(numb, d_tot);
    }

	// 계산 종료

    
%>

<html>
<head>
	<title>회비납부</title>
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
	function don02_m(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="don02_m.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}

	function don03(numb){
		document.readFrm.numb.value=numb;
		document.readFrm.action="don03.jsp";
		document.readFrm.target="content";
		document.readFrm.submit();
	}
</script>

</head>
<body leftmargin="0" topmargin="0" bgcolor="#FFFFFF">

<div align="center">
    <br/>
		<h2>회비 납부 내역</h2>
    <br>
	<table align="center" width="800" border="1">
		<tr>
			<td>회비 납부 대상자 자료수 : <%=listSize%></td>
		</tr>
	</table>
	<table align="center" width="800" cellpadding="3" border="1">
		<tr>
			<td align="center" colspan="3">
			<%
				  d_vlist = moimMgr.getDonList(moim);
				  listSize = d_vlist.size();           //브라우저 화면에 보여질 자료 수
				  if (d_vlist.isEmpty()) {
					out.println("등록된 자료가 없습니다.");
				  } else {
			%>
				  <table width="100%" cellpadding="2" cellspacing="0" border="1">
					<tr align="center" bgcolor="SKYBLUE" height="120%">
						<td>순서</td>
						<td>모임코드</td>
						<td>사용자</td>
						<td>사용자명</td>
						<td>1회</td>
						<td>2회</td>
						<td>3회</td>
						<td>4회</td>
						<td>5회</td>
						<td>합계</td>
						<td>수 정</td>
						<td>삭 제</td>
					</tr>
					<%
						  for (int i = 0;i<listSize; i++) {
							Bean_Don bean  = d_vlist.get(i);
							numb = bean.getNumb();
							moim  = bean.getD_moim();
							usid  = bean.getD_usid();
							int d_01  = bean.getD_d01();
							int d_02  = bean.getD_d02();
							int d_03  = bean.getD_d03();
							int d_04  = bean.getD_d04();
							int d_05  = bean.getD_d05();
							int d_tot = bean.getD_tot();
							
						    Bean_Admin aBean = moimMgr.getMember(usid);   //회원자료 가져오기
						    String name = aBean.getName();
						    // 금액에 컴마 삽입
						    String d_01_f = df.format(d_01);
						    String d_02_f = df.format(d_02);
						    String d_03_f = df.format(d_03);
						    String d_04_f = df.format(d_04);
						    String d_05_f = df.format(d_05);
						    String d_tot_f = df.format(d_tot);
						    
					%>
					<tr>
						<td align="center">
 						   <a href="javascript:don02_m('<%=numb%>')" ><%=numb%></a>
						</td>
						<td align="center">
 						   <%=moim%>
						</td>
						<td align="center">
 						   <%=usid%>
						</td>
						<td align="center">
 						   <%=name%>
						</td>
						<td align="right">
 						   <%=d_01_f%>원
						</td>
						<td align="right">
 						   <%=d_02_f%>원
						</td>
						<td align="right">
 						   <%=d_03_f%>원
						</td>
						<td align="right">
 						   <%=d_04_f%>원
						</td>
						<td align="right">
 						   <%=d_05_f%>원
						</td>
						<td align="right">
 						   <%=d_tot_f%>원
						</td>
						<td align="center">
						   <a href="javascript:don02_m('<%=numb%>')">수정</a>
						</td>
						<td align="center">
						   <a href="javascript:don03('<%=numb%>')">삭제</a>
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
		<input type="hidden" name="numb"> 
	</form>
</div>
</body>
</html>