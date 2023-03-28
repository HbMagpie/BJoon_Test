<%@ page contentType="text/html; charset=UTF-8"%>
<%
	  request.setCharacterEncoding("UTF-8");
	  String id = (String)session.getAttribute("idKey");
%>
<html>
<head>
<title>로그인</title>
<Style>
@font-face {
    font-family: 'KIMM_Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2212@1.0/KIMM_Bold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

* {
	font-family : KIMM_Bold;
	color: #FFFFFF;
}
.box input[type = "text"],.box input[type = "password"] {
	border : 0;				/* border를 우선 0부터 주고 뒤에서 조절하는 이유 :
								아예 없앤다음(0) 다시 새롭게 주려고(?)*/
	background : none;		/* 뒤에 박스색(black)이 비치게 하기 위해 배경색 제거 */
	display : block;		/* block은 그 칸(?)을 온전히 자신이 차지하는 것 */
	margin : 0px auto;
	text-align : center;
	border : 2px solid #3498db;		/* 왜 border를 2번 줄까. */
	padding : 14px 10px;	/* padding & margin은 시계방향으로 설정값이 정해짐(상우하좌) */
	width : 150px;
	height: 50px;
	outline : none;			/* 커서 올렸을 때 테두리 나오는게 사라짐 */
	color : white;
	border-radius : 24px;	/* 테두리를 둥글게(원처럼) 만드는 것 */
	transition : 0.25s;		/* focus 모션 시간 */
}

.box input[type = "text"]:focus, .box input[type = "password"]:focus {
	border-color : #e96d7a;		/* 커서 올렸을 때 크기와 바깥선 색상 조정 */
	background : #fdc8d8;
	/* 앞에서 transition : 0.25s;를 줬기 때문에 0.25초 동안 크기와 색이 서서히 변함 */
}

.box input[type = "button"] {
	font-family : KIMM_Bold;
	border : 0;
	background : none;
	margin : 10px auto;
	text-align: center;
	width : 100px;
	height: 50px;
	text_align : center;
	border : 2px solid #3498db;
	padding : 14px 20px;
	outline : none;
	color : black;
	border-radius : 24px;
	transition : 0.25s;
	cursor: pointer;
}

.box input[type = "button"]:hover {
	font-family : KIMM_Bold;
	border : 2px solid #e96d7a;
	background : #fdc8d8;
	color: #FFFFFF;
}
</Style>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function loginCheck() {
		if (document.loginFrm.id.value == "") {
			alert("아이디를 입력해 주세요.");
			document.loginFrm.id.focus();
			return;
		}
		if (document.loginFrm.pwd.value == "") {
			alert("비밀번호를 입력해 주세요.");
			document.loginFrm.pwd.focus();
			return;
		}
		document.loginFrm.action = "member/loginProc.jsp";
		document.loginFrm.submit();
	}
	
	function memberForm(){
		document.loginFrm.target = "content";
		document.loginFrm.action = "member/member.jsp";
		document.loginFrm.submit();
	}
</script>
</head>
<body bgcolor="#FFFFFF">
<br/><br/>
<div class="box">
 <div align="center">
		<%
			if (id != null) {
		%>
		<b><%=id%></b>님 환영 합니다.
		<p>제한된 기능을 사용 할 수가 있습니다.
		<p>
			<a href="member/logout.jsp">로그아웃</a>
			<%} else {%>
		<form name="loginFrm" method="post" action="member/loginProc.jsp">
			<table>
				<tr>
					<td align="center" colspan="2"><h4>Login</h4></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;ID</td>
					<td><input type="text" name="id" value="" placeholder="아이디 입력"></td>
				</tr>
				<tr>
					<td>pwd</td>
					<td><input type="password" name="pwd" value="" placeholder="비밀번호 입력"></td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							<input type="button" value="로그인" onclick="loginCheck()">&nbsp;
							<input type="button" value="회원가입" onClick="memberForm()" >
						</div>
					</td>
				</tr>
			</table>
		</form>
		<%}%>
	</div>
	</div>
</body>
</html>