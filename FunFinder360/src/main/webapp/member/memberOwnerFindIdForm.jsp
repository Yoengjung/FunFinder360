<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 아이디 찾기</title>

<style>
.container {
	position: absolute;
	width: 100%;
	height: 100%;
	padding: 0px;
	max-width: none;
	top:130px;
}

textarea {
	resize: none;
}

.search-container {
	width: 475px;
	height: 500px;
	position: absolute;
	left: 50%;
	top: 130px;
	transform: translateX(-50%);
	text-align: center;
}

.search-form h2 {
	text-align: center;
	margin-bottom: 20px;
}

.top-menu {
	height: 50px;
	text-align: center;
	display : flex;
	justify-content : center;
	
}

.top-menu ul {
	display: flex;
}

.top-menu li {
	position: relative;
}

.id-item {
	border-bottom: 1px solid black;
}

.password-item {
	border-bottom: 1px solid #CCCCCC;
}

.search-form ul li a {
	font-size: 25px;
	display: block;
	padding: 6px 50px;
	cursor: pointer;
	font-weight: 500;
}

.search-form {
	
	display: inline-block;
	position: absolute;
	width: 500px;
	heiht: 500px;
	left: 50%;
	transform: translateX(-50%);
}

.form-container span {

	display: block;
	text-align: center;
}

.title {
	margin: 30px 0;
}

.btn-secondary {
	margin-top: 30px;
	width: 100%;
}

.form-control {
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="search-container">
			<div class="search-form">
				<c:if test="${empty sessionScope.alertMessage}">
					<div class="alert alert-danger" style="display: none;">
						${sessionScope.alertMessage}</div>
				</c:if>
				<c:if test="${not empty sessionScope.alertMessage}">
					<div class="alert alert-danger" style="display: block;">
						${sessionScope.alertMessage}</div>
				</c:if>
				<h2>기업 아이디/비밀번호 찾기</h2>
				<div class="top-menu">
					<ul>
						<li class="id-item">
							<a href="<%=notWithFormTag%>OwnerFindId">아이디 찾기</a>
						</li>
						<li class="password-item">
							<a href="<%=notWithFormTag%>OwnerfindPassword">비밀번호 찾기</a>
						</li>
					</ul>
				</div>
				<form action="#" method="post" class="form-container">
					<div class="title">
						<span>가입 시 등록한 이메일로 아이디 찾기</span>
					</div>
					<input class="form-control" type="text" name="name" id="name" placeholder="이름" autocomplete="off">
					<input class="form-control" type="text" name="email" id="email" placeholder="이메일" autocomplete="off">
					<button type="submit" class="btn btn-secondary">아이디 찾기</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>