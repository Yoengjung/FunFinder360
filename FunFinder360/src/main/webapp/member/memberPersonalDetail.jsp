<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 회원 정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberCSS/memberPersonalDetailCSS.css" type="text/css">
</head>
<script>
$(document).ready(function() {
	$("#change-id-btn").click(function() {
		$("#info-value-password").css("display", "none");
		$("#info-value-change-input-password").css("display", "block");
		$("#info-value-change-input-password").focus();
		$("#change-id-btn").css("display", "none");
		$("#change-id-success-btn").css("display", "block");
	});
	
	$("#change-id-success-btn").click(function() {
		const userId = $("#info-value-userId-hidden").val();
		changePassword(userId);
	})

	$("#change-phone-btn").click(function() {
		$("#info-value-phone").css("display", "none");
		$("#info-value-change-input-phone").css("display", "block");
		$("#info-value-change-input-phone").focus();
		$("#change-phone-btn").css("display", "none");
		$("#change-phone-success-btn").css("display", "block");
	});
	
	$("#change-phone-success-btn").click(function() {
		const userId = $("#info-value-userId-hidden").val();
		changePhoneNumber(userId);
	})

	$("#change-email-btn").click(function() {
		$("#info-value-email").css("display", "none");
		$("#info-value-change-input-email").css("display", "block");
		$("#info-value-change-input-email").focus();
		$("#change-email-btn").css("display", "none");
		$("#change-email-success-btn").css("display", "block");
	});

	$("#change-email-success-btn").click(function() {
		const userId = $("#info-value-userId-hidden").val();
		changeEmail(userId);
	})
	
	$("#change-bio-btn").click(function() {
		$("#info-value-bio").css("display", "none");
		$("#info-value-change-input-bio").css("display", "block");
		$("#info-value-change-input-bio").focus();
		$("#change-bio-btn").css("display", "none");
		$("#change-bio-success-btn").css("display", "block");
	});
	
	$("#change-bio-success-btn").click(function() {
		const userId = $("#info-value-userId-hidden").val();
		changeBio(userId);
	})
});

function changePassword(userId) {
	const newPassword = $("#info-value-change-input-password").val();
    $.ajax({
        type: "POST",
        url: '<%=notWithFormTag%>personalUserChangePassword',
        data: { userId: userId, newPassword: newPassword}, 
        success: function(response, status, xhr) {
            if (xhr.status === 200) {
        		alert("비밀번호가 변경되었습니다.");
        		location.reload();
            } else {
               alert("오류가 발생했습니다.")
            }
        },
        error: function(xhr, status, error) {
            // 에러 처리
            if (xhr.status === 500) {
                alert("서버 오류");
            } else {
                alert("알 수 없는 오류 발생");
            }
        }
    });
}
function changePhoneNumber(userId) {
	const newPhoneNumber = $("#info-value-change-input-phone").val();
    $.ajax({
        type: "POST",
        url: '<%=notWithFormTag%>personalUserChangePhoneNumber',
        data: { userId: userId, newPhoneNumber: newPhoneNumber}, 
        success: function(response, status, xhr) {
            if (xhr.status === 200) {
        		alert("전화번호가 변경되었습니다.");
        		location.reload();
            } else {
               alert("오류가 발생했습니다.")
            }
        },
        error: function(xhr, status, error) {
            // 에러 처리
            if (xhr.status === 500) {
                alert("서버 오류");
            } else {
                alert("알 수 없는 오류 발생");
            }
        }
    });
}
function changeEmail(userId) {
	const newEmail = $("#info-value-change-input-email").val();
    $.ajax({
        type: "POST",
        url: '<%=notWithFormTag%>personalUserChangeEmail',
        data: { userId: userId, newEmail: newEmail}, 
        success: function(response, status, xhr) {
            if (xhr.status === 200) {
        		alert("이메일이 변경되었습니다.");
        		location.reload();
            } else {
               alert("오류가 발생했습니다.")
            }
        },
        error: function(xhr, status, error) {
            // 에러 처리
            if (xhr.status === 500) {
                alert("서버 오류");
            } else {
                alert("알 수 없는 오류 발생");
            }
        }
    });
}
function changeBio(userId) {
	const newBio = $("#info-value-change-input-bio").val();
    $.ajax({
        type: "POST",
        url: '<%=notWithFormTag%>personalUserChangeBio',
        data: { userId: userId, newBio: newBio}, 
        success: function(response, status, xhr) {
            if (xhr.status === 200) {
        		alert("자기소개가 변경되었습니다.");
        		location.reload();
            } else {
               alert("오류가 발생했습니다.")
            }
        },
        error: function(xhr, status, error) {
            // 에러 처리
            if (xhr.status === 500) {
                alert("서버 오류");
            } else {
                alert("알 수 없는 오류 발생");
            }
        }
    });
}

</script>
<body>
	<div class="container">
		<h2>${requestScope.bean.username}회원정보</h2>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">아이디</span> <span class="info-value" id="info-value-userId">${requestScope.bean.userId}</span>
				<input type="hidden" id="info-value-userId-hidden" value="${requestScope.bean.userId}">
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">비밀번호</span> <span class="info-value-password"
					id="info-value-password">${requestScope.bean.password}</span> <input
					class="info-value-change-input" id="info-value-change-input-password"
					type="password">
				<button type="button" class="btn btn-dark" id="change-id-btn">수정</button>
				<button type="button" class="btn btn-primary"
					id="change-id-success-btn">완료</button>
				<button type="button" class="btn btn-danger"
					id="change-id-delete-btn">취소</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">이름</span> <span class="info-value">${requestScope.bean.username}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">생년월일</span> <span class="info-value">${requestScope.bean.birth}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">전화번호</span> <span class="info-value"
					id="info-value-phone">${requestScope.bean.phoneNumber}</span> <input
					class="info-value-change-input" id="info-value-change-input-phone"
					type="text">
				<button type="button" class="btn btn-dark" id="change-phone-btn">수정</button>
				<button type="button" class="btn btn-primary"
					id="change-phone-success-btn">완료</button>
				<button type="button" class="btn btn-danger"
					id="change-phone-delete-btn">취소</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">이메일</span> <span class="info-value"
					id="info-value-email">${requestScope.bean.email}</span> <input
					class="info-value-change-input" id="info-value-change-input-email"
					type="text">
				<button type="button" class="btn btn-dark" id="change-email-btn">수정</button>
				<button type="button" class="btn btn-primary"
					id="change-email-success-btn">완료</button>
				<button type="button" class="btn btn-danger"
					id="change-email-delete-btn">취소</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">자기소개</span> <span class="info-value"
					id="info-value-bio">${requestScope.bean.bio}</span>
				<textarea class="info-value-change-input"
					id="info-value-change-input-bio"></textarea>
				<button type="button" class="btn btn-dark" id="change-bio-btn">수정</button>
				<button type="button" class="btn btn-primary"
					id="change-bio-success-btn">완료</button>
				<button type="button" class="btn btn-danger"
					id="change-bio-delete-btn">취소</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">가입 일자</span> <span class="info-value">${requestScope.bean.registration_date}</span>
			</div>
		</div>

		<div id="backButton">
			<button type="button" class="btn btn-primary backBtn"
				onclick="history.back();">돌아 가기</button>
		</div>
	</div>
</body>
</html>