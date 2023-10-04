<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 수정</title>
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
	background-color: #EEEEEE;
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

.info-value-change-input-businessName {
	height: 40px;
	width: 521px;
	line-height: 36px;
	border: 1px solid black;
	border-radius: 10px;
	margin-left: 10px;
	text-align: center;
	margin-right: 10px;
}

#change-id-delete-btn {
	margin-left: 5px;
	display: none;
}

#change-id-success-btn {
	display: none;
}

#change-businessName-delete-btn {
	margin-left: 5px;
	display: none;
}

#change-businessName-success-btn {
	display: none;
}
</style>

<script>
	$(document).ready(function() {
		$("#change-id-btn").click(function() {
	        $("#info-value-password").css("display", "none");
	        $(".info-value-change-input").css("display", "block");
	        $(".info-value-change-input").focus();
	       	$("#change-id-btn").css("display", "none");
	        $("#change-id-success-btn").css("display", "block");
	    });
		
		$("#change-businessName-btn").click(function() {
	        $("#change-businessName-btn").css("display", "none");
	        $(".change-businessName-success-btn").css("display", "block");
	        $(".info-value-change-input").focus();
	       	$("#change-businessName-btn").css("display", "none");
	        $("#change-id-success-btn").css("display", "block");
	    });
	})
</script>
</head>
<body>
	<div class="container">
		<h2>${requestScope.bean.userName}회원정보</h2>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">아이디</span>
				<span class="info-value">${requestScope.bean.userId}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">비밀번호</span>
				<span class="info-value-password" id="info-value-password">${requestScope.bean.password}</span>
				<input class="info-value-change-input" type="password">
				<button type="button" class="btn btn-dark" id="change-id-btn">수정</button>
				<button type="button" class="btn btn-primary" id="change-id-success-btn">완료</button>
				<button type="button" class="btn btn-danger" id="change-id-delete-btn">취소</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">이름</span>
				<span class="info-value">${requestScope.bean.userName}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">사업이름</span>
				<span class="info-value">${requestScope.bean.businessName}</span>
				<input class="info-value-change-input" id="info-value-change-input-businessName" type="text">
				<button type="button" class="btn btn-dark" id="change-businessName-btn">수정</button>
				<button type="button" class="btn btn-primary" id="change-businessName-success-btn">완료</button>
				<button type="button" class="btn btn-danger" id="change-businessName-delete-btn">취소</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">사업 구분</span>
				<span class="info-value">${requestScope.bean.businessType}</span>
				<button type="button" class="btn btn-dark">수정</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">사업자 등록번호</span>
				<span class="info-value">${requestScope.bean.businessNumber}</span>
				<button type="button" class="btn btn-dark">수정</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">전화번호</span>
				<span class="info-value">${requestScope.bean.phoneNumber}</span>
				<button type="button" class="btn btn-dark">수정</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">이메일</span>
				<span class="info-value">${requestScope.bean.businessType}</span>
				<button type="button" class="btn btn-dark">수정</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">자기소개</span>
				<span class="info-value">${requestScope.bean.bio}</span>
				<button type="button" class="btn btn-dark">수정</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">가입 일자</span>
				<span class="info-value">${requestScope.bean.registrationDate}</span>
			</div>
		</div>

		<div id="backButton">
			<button type="button" class="btn btn-primary backBtn" onclick="history.back();">돌아 가기</button>
		</div>
	</div>
</body>
</html>