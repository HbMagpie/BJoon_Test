<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="ch17_1.MemberBean"%>
<jsp:useBean id="mMgr" class="ch17_1.MemberMgr" />
<%
	String usid = (String) session.getAttribute("idKey");

    //int numb = Integer.parseInt(request.getParameter("numb"));
    //usid = request.getParameter("usid");
	MemberBean mBean = mMgr.getMember(usid);    //회원자료 가져오기
	//MemberBean mBean = mMgr.getMember(numb);  //회원자료 가져오기
	int numb = mBean.getNumb(); 
    String uspw = mBean.getUspw();
	String name = mBean.getName();
	String addr = mBean.getAddr();
	session.setAttribute("bean", mBean);       //회원자료 세션에 저장

	//System.out.println("자료확인");
    //System.out.println(usid);
    //System.out.println(numb); 
%>
<html>
<head>
<title>회원가입</title>
	<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script1.js"></script>
<script type="text/javascript">
	function zipCheck() {
		url = "zipSearch.jsp?search=n";
		window.open(url, "ZipCodeSearch","width=500,height=300,scrollbars=yes");
	}
</script>
</head>

<body bgcolor="#FFFFCC" onLoad="regFrm.usid.focus()">
	<div align="center">
		<br /> <br />
		<form name="regFrm" method="post" action="memberUpdateProc.jsp">
			<table align="center" cellpadding="5" >
				<tr>
					<td align="center" valign="middle" bgcolor="#FFFFCC">
						<table border="1" cellpadding="2" align="center" width="600">
							<tr align="center" bgcolor="#996600">
								<td colspan="3"><font color="#FFFFFF"><b>회원 수정1</b></font></td>
							</tr>
							<tr>
								<td width="20%">아이디</td>
								<td width="80%"><input name="usid" size="15"
									value="<%=usid%>" readonly></td>
							</tr>
							<tr>
								<td>레코드번호</td>
								<td><input name="numb" size="15"
									value="<%=mBean.getNumb()%>" readonly></td>
							</tr>
							<tr>
								<td>패스워드</td>
								<td><input type="password" name="uspw" size="15"
									value="<%=mBean.getUspw()%>"></td>
							</tr>
							<tr>
								<td>이름</td>
								<td><input name="name" size="15"
									value="<%=mBean.getName()%>"></td>
							</tr>
							<tr>
								<td>성별</td>
								<td>남<input type="radio" name="gend" value="1"
									<%=mBean.getGend().equals("1") ? "checked" : ""%>> 
									여<input type="radio" name="gend" value="2"
									<%=mBean.getGend().equals("2") ? "checked" : ""%>>
								</td>
							</tr>
							<tr>
								<td>생년월일</td>
								<td><input name="brth" size="6"
									value="<%=mBean.getBrth()%>"> ex)830815</td>
							</tr>
							<tr>
								<td>Email</td>
								<td><input name="mail" size="30"
									value="<%=mBean.getMail()%>"></td>
							</tr>
							<tr>
								<td>우편번호</td>
								<td><input name="post" size="5"
									value="<%=mBean.getPost()%>" readonly> <input
									type="button" value="우편번호찾기" onClick="zipCheck()"></td>
							</tr>
							<tr>
								<td>주소</td>
								<td><input name="addr" size="45" 
								           value="<%=mBean.getAddr()%>"></td>
							</tr>
							<tr>
								<td>취미</td>
								<td>
									<%
										String list[] = { "인터넷", "여행", "게임", "영화", "운동" };
										String hobby[] = mBean.getHobb();
										for (int i = 0; i < list.length; i++) {
											out.println(list[i]);
											out.println("<input type=checkbox name=hobb ");
											out.println("value=" + list[i] + " "
											+ (hobby[i].equals("1") ? "checked" : "") + ">");
										}
									%>
								</td>
							</tr>
							<tr>
								<td>직업</td>
								<td><select name=jobb>
										<option value="0">선택하세요.
										<option value="회사원">회사원
										<option value="연구전문직">연구전문직
										<option value="교수학생">교수학생
										<option value="일반자영업">일반자영업
										<option value="공무원">공무원
										<option value="의료인">의료인
										<option value="법조인">법조인
										<option value="종교,언론,에술인">종교.언론/예술인
										<option value="농,축,수산,광업인">농/축/수산/광업인
										<option value="주부">주부
										<option value="무직">무직
										<option value="기타">기타
								</select>
								<script>document.regFrm.jobb.value="<%=mBean.getJobb()%>"</script>
								</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								<input type="submit" value="수정완료"> &nbsp; &nbsp; 
								<input type="reset" value="다시쓰기"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>