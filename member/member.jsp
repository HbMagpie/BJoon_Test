<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>회원가입</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript">
	function idCheck(id) {
		frm = document.regFrm;
		if (id == "") {
			alert("아이디를 입력해 주세요.");
			frm.id.focus();
			return;
		}
		url = "idCheck.jsp?id=" + id;
		window.open(url, "IDCheck", "width=300,height=150");
	}

	function zipCheck() {
		url = "zipSearch.jsp?search=n";
		window.open(url, "ZipCodeSearch","width=500,height=300,scrollbars=yes");
	}
</script>
<Style>
@font-face {
    font-family: 'KIMM_Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2212@1.0/KIMM_Bold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}
</style>
</head>
<body bgcolor="#000000" onLoad="regFrm.id.focus()">
	<div align="center">
		<br /><br />
		<form name="regFrm" method="post" action="memberProc.jsp">
			<table align="center" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td align="center" valign="middle" bgcolor="#FFFFFF">
						<table style="font-family : KIMM_Bold; border-color: #d0dbdf;"border="1" cellspacing="0" cellpadding="2" align="center" width="600" height="300">
							<tr align="center" bgcolor="#e1e8ee">
								<td colspan="3"><b>회원 가입 🛠</b></td>
							</tr>
							<tr>
								<td width="20%" style="font-family : KIMM_Bold; text-align:center;">아이디</td>
								<td width="50%" style="font-family : KIMM_Bold;"><input name="id" size="15"
									value=""> <input type="button" style="font-family : KIMM_Bold; border-radius : 24px; border : 2px solid #3498db;" value="ID중복확인"
									onClick="idCheck(this.form.id.value)"></td>
								<td width="30%" style="font-family : KIMM_Bold;">아이디를 적어주세요.</td>
							</tr>
							<tr>
								<td style="font-family : KIMM_Bold; text-align:center;">패스워드</td>
								<td><input type="password" name="pwd" size="15" value=""></td>
								<td style="font-family : KIMM_Bold;">패스워드를 적어주세요.</td>
							</tr>
							<tr>
								<td style="font-family : KIMM_Bold; text-align:center;">패스워드 확인</td>
								<td><input type="password" name="repwd" size="15" value=""></td>
								<td style="font-family : KIMM_Bold;">패스워드를 확인합니다.</td>
							</tr>
							<tr>
								<td style="font-family : KIMM_Bold; text-align:center;">등급</td>
								<td><input style="font-family : KIMM_Bold; color:red;" name="lv" size="15" value="A" readonly="readonly">
								</td>
								<td style="font-family : KIMM_Bold; color:red;">수정 불가</td>
							</tr>
							<tr>
								<td style="font-family : KIMM_Bold; text-align:center;">승인 여부</td>
								<td><input style="font-family : KIMM_Bold; color:red;" name="sign" size="15" value="미승인" readonly="readonly">
								</td>
								<td style="font-family : KIMM_Bold; color:red;">수정 불가</td>
							</tr>
							<tr>
								<td style="font-family : KIMM_Bold; text-align:center;">이름</td>
								<td><input name="name" size="15" value="">
								</td>
								<td style="font-family : KIMM_Bold;">이름을 적어주세요.</td>
							</tr>
							<!-- <tr>
								<td style="font-family : KIMM_Bold; text-align:center;">성별</td>
								<td style="font-family : KIMM_Bold;">남<input type="radio" name="gender" value="1"
									checked="checked"> 여<input type="radio" name="gender"
									value="2">
								</td>
								<td style="font-family : KIMM_Bold;">성별을 선택하세요.</td>
							</tr> -->
							<tr>
								<td style="font-family : KIMM_Bold; text-align:center;">생년월일</td>
								<td style="font-family : KIMM_Bold;"><input name="birthday" size="6" value="">
									ex)990000</td>
								<td style="font-family : KIMM_Bold;">생년월일를 적어주세요.</td>
							</tr>
							<tr>
								<td style="font-family : KIMM_Bold; text-align:center;">Email</td>
								<td><input name="email" size="30" value="">
								</td>
								<td style="font-family : KIMM_Bold;">이메일를 적어주세요.</td>
							</tr>
							<tr>
								<td style="font-family : KIMM_Bold; text-align:center;">우편번호</td>
								<td><input name="zipcode" size="5" readonly>
									<input style="font-family : KIMM_Bold; border-radius : 24px; border : 2px solid #3498db;" type="button" value="우편번호찾기" onClick="zipCheck()">
								</td>
								<td style="font-family : KIMM_Bold;">우편번호를 검색하세요.</td>
							</tr>
							<tr>
								<td style="font-family : KIMM_Bold; text-align:center;">주소</td>
								<td><input name="address" size="45"></td>
								<td style="font-family : KIMM_Bold;">주소를 적어주세요.</td>
							</tr>
							<tr>
								<td style="font-family : KIMM_Bold; text-align:center;">취미</td>
								<td style="font-family : KIMM_Bold;">인터넷<input type="checkbox" name="hobby" value="인터넷">
									여행<input type="checkbox" name="hobby" value="여행"> 게임<input
									type="checkbox" name="hobby" value="게임"> 영화<input
									type="checkbox" name="hobby" value="영화"> 운동<input
									type="checkbox" name="hobby" value="운동">
								</td>
								<td style="font-family : KIMM_Bold;">취미를 선택하세요.</td>
							</tr>
							<tr>
								<td style="font-family : KIMM_Bold; text-align:center;">직업</td>
								<td style="font-family : KIMM_Bold;"><select name=job>
										<option value="0" selected>선택하세요.
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
								</select></td>
								<td style="font-family : KIMM_Bold;">직업을 선택하세요.</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
									<input style="font-family : KIMM_Bold;" type="button"value="회원가입" onClick="inputCheck()"> &nbsp; &nbsp; 
									<input style="font-family : KIMM_Bold;" type="reset" value="다시쓰기"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
