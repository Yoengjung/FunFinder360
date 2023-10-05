<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 유저 통합 상세 조회</title>
</head>
<style>
.container {
	margin-top: 9%;
	width: 100%;
	height: 100%;
}

.container h2 {
	margin-bottom: 65px;
	text-align: center;
}

.info-box {
	width: 830px;
	transform: translateX(-50%);
	left: 50%;
	position: relative;
	display: flex;
	flex-direction: row;
	margin-bottom: 10px;
}

.info-key {
	text-align: center;
	height: 40px;
	line-height: 36px;
	width: 150px;
	border: 1px solid black;
	border-radius: 10px;
	font-size: 18px;
	background-color: #CCCCCC;
}

.info-value {
	height: 40px;
	width: 521px;
	line-height: 36px;
	border: 1px solid black;
	border-radius: 10px;
	margin-left: 10px;
	text-align: center;
	margin-right: 10px;
	background-color: #eeeeee;
}

.backBtn {
	position: relative;
	left: 50%;
	transform: translateX(-50%);
	margin-top: 30px;
}

.info-value-change-input {
	height: 40px;
	width: 521px;
	line-height: 36px;
	border: 1px solid black;
	border-radius: 10px;
	margin-left: 10px;
	text-align: center;
	margin-right: 10px;
	display: none;
}

.info-value-change-input-newPassword {
	height: 40px;
	width: 521px;
	line-height: 36px;
	border: 1px solid black;
	border-radius: 10px;
	margin-left: 10px;
	text-align: center;
	margin-right: 10px;
}

.info-value-password {
	height: 40px;
	width: 521px;
	line-height: 36px;
	border: 1px solid black;
	border-radius: 10px;
	margin-left: 10px;
	text-align: center;
	margin-right: 10px;
}
</style>
<body>
	<div class="container">
		<h2>${requestScope.userData.username}회원정보</h2>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">아이디</span>
				<span class="info-value" id="info-value-userId">${requestScope.userData.userId}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">이름</span>
				<span class="info-value">${requestScope.userData.username}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">생년월일</span>
				<span class="info-value">${requestScope.userData.birth}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">전화번호</span>
				<span class="info-value" id="info-value-phone">${requestScope.userData.phoneNumber}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">이메일</span>
				<span class="info-value" id="info-value-email">${requestScope.userData.email}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">자기소개</span>
				<span class="info-value" id="info-value-bio">${requestScope.userData.bio}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">가입 일자</span>
				<span class="info-value">${requestScope.userData.registration_date}</span>
			</div>
		</div>
	</div>
</body>
</html>