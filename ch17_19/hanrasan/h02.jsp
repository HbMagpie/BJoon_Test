<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="ch17_1.MemberBean"%>
<jsp:useBean id="mMgr" class="ch17_1.MemberMgr" />
<%
    int numb = Integer.parseInt(request.getParameter("numb"));
	MemberBean gBean = mMgr.getMember2(numb);  //회원자료 가져오기
%>
<html>
<head>
<title>회원가입</title>
	<link href="style.css" rel="stylesheet" type="text/css" >
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
		<form name="regFrm" method="post" action="h02_p.jsp" >
			<table align="center" cellpadding="5" >
				<tr>
					<td align="center" valign="middle" bgcolor="#FFFFCC">
						<table border="1" cellpadding="2" align="center" width="600">
							<tr align="center" bgcolor="#996600">
								<td colspan="3"><font color="#FFFFFF"><b>회원 수정[한라산]</b></font></td>
							</tr>
							<tr>
								<td width="20%">아이디</td>
								<td width="80%"><input name="usid" size="15"
									value="<%=gBean.getUsid()%>" readonly></td>
							</tr>
							<tr>
								<td>레코드번호</td>
								<td><input name="numb" size="15"
									value="<%=gBean.getNumb()%>" readonly></td>
							</tr>
							<tr>
								<td>패스워드</td>
								<td><input type="password" name="uspw" size="15"
									value="<%=gBean.getUspw()%>"></td>
							</tr>
							<tr>
								<td>이름</td>
								<td><input name="name" size="15"
									value="<%=gBean.getName()%>"></td>
							</tr>
							<tr>
								<td>성별</td>
								<td>남<input type="radio" name="gend" value="1"
									<%=gBean.getGend().equals("1") ? "checked" : ""%>> 
									여<input type="radio" name="gend" value="2"
									<%=gBean.getGend().equals("2") ? "checked" : ""%>>
								</td>
							</tr>
							<tr>
								<td>생년월일</td>
								<td><input name="brth" size="6"
									value="<%=gBean.getBrth()%>"> ex)830815</td>
							</tr>
							<tr>
								<td>Email</td>
								<td><input name="mail" size="30"
									value="<%=gBean.getMail()%>"></td>
							</tr>
							<tr>
								<td>우편번호</td>
								<td><input name="post" size="5"
									value="<%=gBean.getPost()%>" readonly> <input
									type="button" value="우편번호찾기" onClick="zipCheck()"></td>
							</tr>
							<tr>
								<td>주소</td>
								<td><input name="addr" size="45" 
								           value="<%=gBean.getAddr()%>"></td>
							</tr>
							<tr>
								<td>취미</td>
								<td>
									<%
										String list[] = { "인터넷", "여행", "게임", "영화", "운동" };
										String hobby[] = gBean.getHobb();
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
								<script>document.regFrm.jobb.value="<%=gBean.getJobb()%>"</script>
								</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								<input type="submit" value="수정완료"> &nbsp; &nbsp; 
								<input type="reset" value="다시쓰기"> &nbsp; &nbsp; 
								<input type="button" value="자료삭제" onClick="location.href='h03.jsp?numb=<%=numb%>'"> &nbsp; &nbsp;
								<input type="button" value="신규회원" onClick="location.href='h01.jsp'"></td>
						</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>