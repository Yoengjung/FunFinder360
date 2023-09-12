<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./bootstrap.jsp"%>
<%
String appName = request.getContextPath();
String mappingName = "/FunFinder360";
String withFormTag = appName + mappingName;
String notWithFormTag = appName + mappingName + "?command=";
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<meta charset="UTF-8">
<style>
body {
	margin: 0px;
	width: 100vw;
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
	right: 0;
	top: 60px;
}

.menu-container ul {
	justify-content: center;
	display: flex;
	float: right;
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
	left: 30px;
}

.category-menu-dropdown {
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
}

/* 호버 시 드롭다운 내용 활성화 */
.category-menu-dropdown:hover .dropdown-content {
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
</style>
</head>
<body>
<body>
	<div class="top-container">
		<div class="login-container">
			<ul>
				<li>
					<a href="<%=notWithFormTag%>loginSelect" class="a-tag">login</a>
				</li>
				<li>
					<a href="<%=notWithFormTag%>joinSelect" class="a-tag">join</a>
				</li>
			</ul>
		</div>
		<hr>
	</div>
	<div class="menu-container">
		<a href="<%=notWithFormTag%>home" id="logo">FunFinder360</a>
		<nav>
			<ul>
				<div class="category-menu-dropdown">
					<a href="#" class="menu-a-tag">카테고리</a>
					<div class="dropdown-content">
						<a href="#">스포츠 - 야외활동</a>
						<a href="#">문화 - 엔터테인먼트</a>
						<a href="#">음식과 요리</a>
						<a href="#">교육 - 학습</a>
						<a href="#">여행 - 모험</a>
						<a href="#">사회 - 봉사활동</a>
						<a href="#">게임 - 취미</a>
						<a href="#">뷰티 - 건강</a>
					</div>
				</div>
				<li>
					<a href="#" class="menu-a-tag">검색</a>
				</li>
				<li>
					<a href="#" class="menu-a-tag">마이페이지</a>
				</li>
				<li>
					<a href="#" class="menu-a-tag">문의</a>
				</li>
			</ul>
		</nav>
	</div>

	
</body>
</html>