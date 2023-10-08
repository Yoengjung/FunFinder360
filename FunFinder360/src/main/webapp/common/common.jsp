<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./bootstrap.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&family=Noto+Sans+KR:wght@200;300;400&family=Sunflower:wght@300&display=swap"
	rel="stylesheet">
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/commonCSS/commonCSS.css">
</head>
<body>
<body>
	<div class="top-container">
		<div class="logo-box">
			<a href="<%=notWithFormTag%>home"> <img class="logoImage"
				alt="logo"
				src="${pageContext.request.contextPath}/common/image/FunFinder360Logo.png">
			</a>
		</div>

		<nav class="navbar navbar-expand-sm ">
			<div class="container-fluid">
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<ul class="navbar-nav">
						<li class="nav-item"><c:if
								test="${not empty sessionScope.loginfo}">
								<a class="nav-link" href="<%=notWithFormTag%>activityInsert">활동
									등록</a>
							</c:if> <c:if test="${not empty sessionScope.loginfoOwner}">
								<a class="nav-link"
									href="<%=notWithFormTag%>OwnerActivityInsert">활동 등록</a>
							</c:if></li>
						<c:if
							test="${empty sessionScope.loginfo && empty sessionScope.loginfoOwner}">
							<a class="nav-link" href="<%=notWithFormTag%>loginSelect">활동
								등록</a>
						</c:if>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">활동 목록</a>
							<ul class="dropdown-menu activites-menu">
								<li>
									<ul>
										<li><a class="dropdown-item"
											href="<%=notWithFormTag%>activitesList">개인 활동</a></li>
										<li><a class="dropdown-item"
											href="<%=notWithFormTag%>cultureActivitesList">문화 -
												엔터테인먼트</a></li>
										<li><a class="dropdown-item"
											href="<%=notWithFormTag%>foodActivitesList">음식 - 요리</a></li>
											<li><a class="dropdown-item"
											href="<%=notWithFormTag%>studyActivitesList">공부 - 학습</a></li>
											<li><a class="dropdown-item"
											href="<%=notWithFormTag%>travelActivitesList">여행 - 모험</a></li>
											<li><a class="dropdown-item"
											href="<%=notWithFormTag%>gameActivitesList">게임 - 취미</a></li>
									</ul>

								</li>
								<li><a class="dropdown-item"
									href="<%=notWithFormTag%>OwnerActivitesList">업주 활동</a></li>

							</ul></li>
						<c:if test="${loginSuccessCheck == 2}">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown">데이터 관리</a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>selectInfo">정보 관리</a></li>
								</ul></li>
						</c:if>
						<c:if test="${loginSuccessCheck != 2}">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown">마이페이지</a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>memberActivity">내 활동</a></li>
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>memberDetail">회원 정보</a></li>
								</ul></li>
						</c:if>
						<c:if test="${loginSuccessCheck == 2}">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown">문의</a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>commonQuestionsList">자주 묻는 질문</a></li>
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>questionsList">개인 문의</a></li>
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>commonQuestionsInsert">공통 질문 작성</a></li>
								</ul></li>
						</c:if>
						<c:if test="${loginSuccessCheck != 2}">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown">문의</a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>commonQuestionsList">자주 묻는 질문</a></li>
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>questionsList">개인 문의</a></li>
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>questionsInsert">문의 작성</a></li>
								</ul></li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>



		<div class="login-container">
			<c:if
				test="${empty sessionScope.loginfo && empty sessionScope.loginfoOwner}">
				<ul>
					<li><a href="<%=notWithFormTag%>loginSelect" class="a-tag">login</a>
					</li>
					<li><a href="<%=notWithFormTag%>joinSelect" class="a-tag">join</a>
					</li>
				</ul>
			</c:if>
			<c:if test="${not empty sessionScope.loginfoOwner}">
				<ul>
					<li><span id="login-message">환영합니다 (업주)</span> <span
						id="login-user">${sessionScope.loginfoOwner.userName}</span></li>
					<li><a href="<%=notWithFormTag%>logout" class="a-tag">logout</a>
					</li>
				</ul>
			</c:if>
			<c:if test="${not empty sessionScope.loginfo}">
				<ul>
					<li><span id="login-message">환영합니다 (개인)</span> <span
						id="login-user">${sessionScope.loginfo.username}님</span></li>
					<li><a href="<%=notWithFormTag%>logout" class="a-tag">logout</a>
					</li>
				</ul>
			</c:if>
		</div>
	</div>

</body>
</html>