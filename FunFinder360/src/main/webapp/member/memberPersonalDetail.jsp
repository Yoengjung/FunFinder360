<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 회원 정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberCSS/memberPersonalDetailCSS.css" type="text/css">
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

.info-container-newPassword {
	display: none;
}

button {
	left: 0px;
	transform: translateX(0%);
}

#change-id-delete-btn {
	margin-left: 5px;
	display: none;
}

#change-id-success-btn {
	display: none;
}

#info-value-change-input-phone {
	display: none;
}

#change-phone-success-btn {
	display: none;
}

#change-phone-delete-btn {
	display: none;
}

#info-value-change-input-email {
	display: none;
}

#change-email-success-btn {
	display: none;
}

#change-email-delete-btn {
	display: none;
}

#info-value-change-input-bio {
	display: none;
	height: 90px;
	resize: none;
}

#change-bio-success-btn {
	display: none;
}

#change-bio-delete-btn {
	display: none;
}
</style>
<script>
$(document).ready(function() {
	$("#change-id-btn").click(function() {
		$("#info-key-password").text("현재 비밀번호");
		$(".info-container-newPassword").css("display", "block");
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
    const currentPassword = $("#info-value-change-input-password").val();
    const newPassword = $("#info-value-change-input-newPassword").val();
    const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@#$%^&+=!])[A-Za-z\d@#$%^&+=!]{8,20}$/;
    
    $.ajax({
        type: "POST",
        url: '<%=notWithFormTag%>personalUserChangePassword',
        data: { userId: userId, currentPassword: currentPassword, newPassword: newPassword }, 
        success: function(response) {
        	console.log(response)
        	if (!passwordPattern.test(newPassword)) {
                alert("새 비밀번호가 유효한 형식이 아닙니다. ");
                return; 
            }
        	if (newPassword.length < 8) {
            	alert("비밀번호의 길이는 8자리 이상 입니다.");
            	return;
            }        	
       	    if (response == "fail") {
       	        alert("비밀번호 변경에 실패하셨습니다. 현재 비밀번호를 확인해주세요.");
       	    } else {
       	        alert("비밀번호가 변경되었습니다.");
       	    }
        	location.reload();
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
        	console.log(response)
            if (xhr.status === 200) {
            	var responseMessage = xhr.responseText; // 여기서 응답 메시지를 얻을 수 있습니다.
            	console.log(response);
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
			data : {
				userId : userId,
				newBio : newBio
			},
			success : function(response, status, xhr) {
				if (xhr.status === 200) {
					alert("자기소개가 변경되었습니다.");
					location.reload();
				} else {
					alert("오류가 발생했습니다.")
				}
			},
			error : function(xhr, status, error) {
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
<div id="check"></div>

	<div class="container">
		<h2>${requestScope.bean.username}회원정보</h2>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">아이디</span>
				<span class="info-value" id="info-value-userId">${requestScope.bean.userId}</span>
				<input type="hidden" id="info-value-userId-hidden" value="${requestScope.bean.userId}">
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key" id="info-key-password">비밀번호</span>
				<span class="info-value-password" id="info-value-password">---</span>
				<input class="info-value-change-input" id="info-value-change-input-password" type="password">
				<button type="button" class="btn btn-dark" id="change-id-btn">비밀번호 변경</button>
				<button type="button" class="btn btn-primary" id="change-id-success-btn">완료</button>
				<button type="button" class="btn btn-danger" id="change-id-delete-btn">취소</button>
			</div>
		</div>
		<div class="info-container-newPassword">
			<div class="info-box">
				<span class="info-key">새로운 비밀번호</span>
				<input class="info-value-change-input-newPassword" id="info-value-change-input-newPassword" type="password">
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">이름</span>
				<span class="info-value">${requestScope.bean.username}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">생년월일</span>
				<span class="info-value">${requestScope.bean.birth}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">전화번호</span>
				<span class="info-value" id="info-value-phone">${requestScope.bean.phoneNumber}</span>
				<input class="info-value-change-input" id="info-value-change-input-phone" type="text">
				<button type="button" class="btn btn-dark" id="change-phone-btn">수정</button>
				<button type="button" class="btn btn-primary" id="change-phone-success-btn">완료</button>
				<button type="button" class="btn btn-danger" id="change-phone-delete-btn">취소</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">이메일</span>
				<span class="info-value" id="info-value-email">${requestScope.bean.email}</span>
				<input class="info-value-change-input" id="info-value-change-input-email" type="text">
				<button type="button" class="btn btn-dark" id="change-email-btn">수정</button>
				<button type="button" class="btn btn-primary" id="change-email-success-btn">완료</button>
				<button type="button" class="btn btn-danger" id="change-email-delete-btn">취소</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">자기소개</span>
				<span class="info-value" id="info-value-bio">${requestScope.bean.bio}</span>
				<textarea class="info-value-change-input" id="info-value-change-input-bio"></textarea>
				<button type="button" class="btn btn-dark" id="change-bio-btn">수정</button>
				<button type="button" class="btn btn-primary" id="change-bio-success-btn">완료</button>
				<button type="button" class="btn btn-danger" id="change-bio-delete-btn">취소</button>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">가입 일자</span>
				<span class="info-value">${requestScope.bean.registration_date}</span>
			</div>
		</div>

		<div id="backButton">
			<button type="button" class="btn btn-primary backBtn" onclick="history.back();">돌아 가기</button>
		</div>
	</div>
</body>
</html>