<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./bootstrap.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
String appName = request.getContextPath();
String mappingName = "/FunFinder360";
String withFormTag = appName + mappingName;
String notWithFormTag = appName + mappingName + "?command=";
%>

<c:set var="loginSuccessCheck" value="0"></c:set>
<c:if test="${not empty sessionScope.loginfo}">
	<c:if test="${sessionScope.loginfo.userId == 'admin1'}">
		<c:set var="loginSuccessCheck" value="2"></c:set>
	</c:if>
	<c:if test="${sessionScope.loginfo.userId != 'admin1'}">
		<c:set var="loginSuccessCheck" value="1"></c:set>
	</c:if>
</c:if>
<c:if test="${not empty sessionScope.loginfoOwner}">
	<c:if test="${sessionScope.loginfoOwner.userId == 'admin1'}">
		<c:set var="loginSuccessCheck" value="2"></c:set>
	</c:if>
	<c:if test="${sessionScope.loginfoOwner.userId != 'admin1'}">
		<c:set var="loginSuccessCheck" value="1"></c:set>
	</c:if>
</c:if>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<meta charset="UTF-8">
<style>
body {
	margin: 0px;
	width: 100vw;
	font-family: 'Nanum Gothic Coding', cursive;
	font-family: 'Sunflower', sans-serif;
}

ul {
	margin: 0px;
	padding: 0px;
}

li {
	list-style: none;
}

a {
	text-decoration: none;
	color: black;
}

.top-container {
	display: block;
	position: absolute;
	width: 100%;
	height: 50px;
	top: 10px;
}

.login-container {
	display: inline-flex;
	position: relative;
	right: 50px;
	float: right;
	font-size: 20px;
	margin-bottom: 10px;
}

.login-container ul {
	display: flex;
}

.a-tag {
	margin: 0 20px;
	border: 1px solid #cccccc;
	border-radius: 5px;
	padding: 5px 10px;
	color: #cccccc;
}

.menu-container {
	width: 100%;
	height: 50px;
	position: relative;
	top: 60px;
}

.menu-container ul {
	justify-content: center;
	display: flex;
	float: right;
	right: 30px;
	position: relative;
	top: 9px;
}

.menu-a-tag {
	margin: 0 50px;
	font-size: 20px;
}

hr {
	width: 100%;
	margin: 0px;
}

#logo {
	font-size: 30px;
	position: absolute;
	left: 200px;
}

.menu-dropdown {
	position: relative;
}

.menu-a-tag {
	display: inline-block;
	padding: 3px 20px;
	text-decoration: none;
}

/* 드롭다운 내용 숨김 */
.dropdown-content {
	text-align: center;
	display: none;
	position: absolute;
	min-width: 210px;
	background-color: #FFFFFF;
	border: 1px solid #CCCCCC;
	z-index: 1;
	left: 9px;
}

/* 호버 시 드롭다운 내용 활성화 */
.menu-dropdown:hover .dropdown-content {
	display: block;
}

/* 드롭다운 내용 항목 스타일 */
.dropdown-content a {
	display: block;
	padding: 10px;
	text-decoration: none;
	color: black;
}

/* 드롭다운 내용 항목 호버 시 스타일 */
.dropdown-content a:hover {
	background-color: #ddd;
}

.a-tag:hover {
	color: black;
}

.company-info-container {
	position: absolute;
	margin-top: 20px;
	left: 50%;
	transform: translateX(-50%);
}

.company-info-container ul li span {
	margin: 0 20px;
}

.dropdown-1 {
	min-width: 170px;
}

.container-fluid {
	position: relative;
	font-size: 20px;
}

.navbar-expand-sm .navbar-nav {
	flex-direction: row;
	float: right;
	position: absolute;
	right: 70px;
}

.nav-link {
	margin: 0 20px;
}

.dropdown-item {
	text-align: center;
}

.navbar {
	top: 50px;
}

.navbar-brand {
	font-size: 30px;
	left: 30px;
}
</style>
</head>
<body>
<body>
	<div class="top-container">
		<div class="login-container">
			<c:if test="${empty sessionScope.loginfo && empty sessionScope.loginfoOwner}">
				<ul>
					<li>
						<a href="<%=notWithFormTag%>loginSelect" class="a-tag">login</a>
					</li>
					<li>
						<a href="<%=notWithFormTag%>joinSelect" class="a-tag">join</a>
					</li>
				</ul>
			</c:if>
			<c:if test="${not empty sessionScope.loginfoOwner}">
				<ul>
					<li>
						<span>${sessionScope.loginfoOwner.userName}</span>
					</li>
					<li>
						<a href="<%=notWithFormTag%>logout" class="a-tag">logout</a>
					</li>
				</ul>
			</c:if>
			<c:if test="${not empty sessionScope.loginfo}">
				<ul>
					<li>
						<span>${sessionScope.loginfo.username}</span>
					</li>
					<li>
						<a href="<%=notWithFormTag%>logout" class="a-tag">logout</a>
					</li>
				</ul>
			</c:if>
		</div>
		<hr>
	</div>
	<nav class="navbar navbar-expand-sm ">
		<div class="container-fluid">
			<a class="navbar-brand" href="<%=notWithFormTag%>home">FunFinder360</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="<%=notWithFormTag%>activityInsert">활동 등록</a>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">활동 목록</a>
						<ul class="dropdown-menu">
							<li>
								<a class="dropdown-item" href="<%=notWithFormTag%>activitesList">개인 활동</a>
							</li>
							<li>
								<a class="dropdown-item" href="<%=notWithFormTag%>OwnerActivitesList">업주 활동</a>
							</li>
						</ul>
					</li>
					<c:if test="${loginSuccessCheck == 2}">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">데이터 관리</a>
							<ul class="dropdown-menu">
								<li>
									<a class="dropdown-item" href="<%=notWithFormTag%>memberList">회원 데이터</a>
								</li>
								<li>
									<a class="dropdown-item" href="#">회원별 수익 데이터</a>
								</li>
								<li>
									<a class="dropdown-item" href="<%=notWithFormTag%>memberActivitySelect">회원 활동 데이터</a>
								</li>
							</ul>
						</li>
					</c:if>
					<c:if test="${loginSuccessCheck != 2}">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">마이페이지</a>
							<ul class="dropdown-menu">
								<li>
									<a class="dropdown-item" href="<%=notWithFormTag%>memberActivity">내 활동</a>
								</li>
								<li>
									<a class="dropdown-item" href="#">게시물 수익</a>
								</li>
								<li>
									<a class="dropdown-item" href="#">즐겨찾기 목록</a>
								</li>
								<li>
									<a class="dropdown-item" href="<%=notWithFormTag%>memberDetail">회원 정보</a>
								</li>
							</ul>
						</li>
					</c:if>
					<c:if test="${loginSuccessCheck == 2}">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">문의</a>
							<ul class="dropdown-menu">
								<li>
									<a class="dropdown-item" href="<%=notWithFormTag%>commonQuestionsList">자주 묻는 질문</a>
								</li>
								<li>
									<a class="dropdown-item" href="<%=notWithFormTag%>questionsList">개인 문의</a>
								</li>
								<li>
									<a class="dropdown-item" href="<%=notWithFormTag%>commonQuestionsInsert">공통 질문 작성</a>
								</li>
							</ul>
						</li>
					</c:if>
					<c:if test="${loginSuccessCheck != 2}">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">문의</a>
							<ul class="dropdown-menu">
								<li>
									<a class="dropdown-item" href="<%=notWithFormTag%>commonQuestionsList">자주 묻는 질문</a>
								</li>
								<li>
									<a class="dropdown-item" href="<%=notWithFormTag%>questionsList">개인 문의</a>
								</li>
								<li>
									<a class="dropdown-item" href="<%=notWithFormTag%>questionsInsert">문의 작성</a>
								</li>
							</ul>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>