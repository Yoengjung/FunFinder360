<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script>

$(document).ready(function() {
})

function validation () {
	const id = $("#id").val();
	const password = $("#password").val();
	
	if(id == "") {
		alert("아이디가 입력되지 않았습니다.");
		$("#id").focus();
		return false;
	}
	
	if(password == "") {
		alert("비밀번호가 입력되지 않았습니다.");
		$("#password").focus();
		return false;
	}
	
}
</script>

<style>
.container {
	position: absolute;
	top: 130px;
	width: 100%;
	height: 100%;
	max-width: none;
}

.login-form-container {
	width: 450px;
	height: 500px;
	position: absolute;
	left: 50%;
	top: 130px;
	transform: translateX(-50%);
	text-align: center;
}

.login-form-container h2 {
	margin-bottom: 30px;
}

.btn-secondary {
	width: 100%;
	margin-top: 20px;
}

.form-type-checkbox {
	display: flex;
	justify-content: space-between;
	flex-wrap: wrap;
	align-items: center;
	flex-wrap: wrap;
	align-items: center;
}

.form_items {
	color: #A0A0A0;
}

.text-menu ul {
	display: flex;
}

.text-menu ul li a{
	margin: 0 5px;
	color: #A0A0A0;
}

.text-menu ul li span {
	margin-right: 3px;
}

</style>
</head>
<body>
	<div class="container">
		<div class="login-form-container">
			<h2>개인 로그인</h2>
			<form action="<%=withFormTag%>" method="post">
				<input type="hidden" name="command" value="PersonalLogin">
				<input type="text" class="form-control" name="id" id="id" placeholder="아이디 또는 이메일" autocomplete="off">
				<br>
				<input type="password" class="form-control" name="password" id="password" placeholder="비밀번호">
				<br>
				<div class="form-type-checkbox">
					<div class="form_items">
						<input type="checkbox">
						아이디 저장
					</div>
					<div class="text-menu">
						<ul>
							<li>
								<a href="<%=notWithFormTag%>findId">아이디 찾기</a>
								<span>|</span>
							</li>
							<li>
								<a href="<%=notWithFormTag%>findPassword">비밀번호 찾기</a>
								<span>|</span>
							</li>
							<li>
								<a href="<%=notWithFormTag%>join">회원가입</a>
							</li>
						</ul>
					</div>
				</div>
				<button type="submit" class="btn btn-secondary" onclick="return validation()">로그인</button>
			</form>
		</div>
	</div>
</body>
</html>