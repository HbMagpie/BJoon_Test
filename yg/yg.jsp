<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>차량 유류</title>
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
</head>
<body bgcolor="#000000" onLoad="regFrm.id.focus()">
	<div align="center">
		<br /><br />
		<form name="regFrm" method="post" action="memberProc.jsp">
			<table align="center" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td align="center" valign="middle" bgcolor="#FFFFFF">
						<table border="1" cellspacing="0" cellpadding="2" align="center" width="600">
							<tr align="center" bgcolor="#e1e8ee">
								<td colspan="3"><b>차량 유류🚐</b></td>
							</tr>
							<tr>
								<td width="20%">아이디</td>
								<td width="40%"><input name="id" size="15" value="">
								</td>
								<td width="40%">아이디를 적어 주세요.</td>
							</tr>
							<tr>
								<td>입력일자</td>
								<td><input name="date" size="6" value="">
									ex)2023-03-31</td>
								<td>입력일자를 적어 주세요.</td>
							</tr>
							<tr>
								<td>주유장소</td>
								<td><input name="space" size="15" value="">
								</td>
								<td>주유장소를 적어주세요.</td>
							</tr>
							<tr>
								<td>연료종류</td>
								<td><select name=oil>
										<option value="0" selected>선택하세요.
										<option value="휘발유">휘발유
										<option value="경유">경유									
								</select></td>
								<td>연료종류를 선택 하세요.</td>
							</tr>														
							<tr>
								<td>주유금액</td>
								<td><input name="m" value=""></td>
								<td>주유금액을 적어 주세요.</td>
							</tr>
							<tr>
								<td>주유량</td>
								<td><input name="y" value=""></td>
								<td>주유량을 적어 주세요.</td>
							</tr>
							<tr>
								<td>누적주행거리</td>
								<td><input name="p" value=""></td>
								<td>누적주행거리를 적어 주세요.</td>
							</tr>
							<tr>
								<td>비고</td>
								<td><input name="p" value=""></td>
								<td>비고를 적어 주세요.</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
									<input type="button"value="자료입력" onClick="inputCheck()"> &nbsp; 
									<input type="reset" value="다시쓰기"> &nbsp; 
									<input type="reset" value="주문내역"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
