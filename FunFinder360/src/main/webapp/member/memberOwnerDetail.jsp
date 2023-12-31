<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 수정</title>
<style>
html, body {
	height: 100%;
}

.container1 {
	width: 100%;
	height: 100%;
	min-height: calc(100% - 100px);
	padding-top: 100px;
	position: relative;
	padding: 0px 150px;
}

.container1 h2 {
	margin-bottom: 65px;
	text-align: center;
}
.container2 {
	position: relative;
	top: 180px;
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

.deleteUserBtn {
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

.info-container-newPassword {
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

		$("#change-businessName-btn").click(function() {
			$("#info-value-businessName").css("display", "none");
			$("#info-value-change-input-businessName").css("display", "block");
			$("#info-value-change-input-businessName").focus();
			$("#change-businessName-btn").css("display", "none");
			$("#change-businessName-success-btn").css("display", "block");
		});
		
		$("#change-businessName-success-btn").click(function() {
			const userId = $("#info-value-userId-hidden").val();
			changeBusinessName(userId);
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
	        url: '<%=notWithFormTag%>ownerUserChangePassword',
	        data: { userId: userId, currentPassword: currentPassword, newPassword: newPassword}, 
	        success: function(response) {
	        	if (!passwordPattern.test(newPassword)) {
	                alert("새 비밀번호가 유효한 형식이 아닙니다. (특수문자 포함, 8글자 이상)");
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
	            if (xhr.status == 500) {
	                alert("서버 오류");
	            } else {
	                alert("알 수 없는 오류 발생");
	            }
	        }
	    });
	}
	
	
	function changeBusinessName(userId) {
		const newBusinessName = $("#info-value-change-input-businessName").val();
	    $.ajax({
	        type: "POST",
	        url: '<%=notWithFormTag%>ownerUserChangeBusinessName',
	        data: { userId: userId, newBusinessName: newBusinessName}, 
	        success: function(response, status, xhr) {
	        	
	        	if (newBusinessName.length > 100) {
	        		alert("기업이름은 100이하 입니다 ");
	            	return;
	        	}
	            if (xhr.status === 200) {
	        		alert("사업이름이 변경되었습니다.");
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
		const phoneRegex = /^\d{3}-\d{4}-\d{4}$/;
	    $.ajax({
	        type: "POST",
	        url: '<%=notWithFormTag%>ownerUserChangePhoneNumber',
	        data: { userId: userId, newPhoneNumber: newPhoneNumber}, 
	        success: function(response, status, xhr) {
	        	if (!phoneRegex.test(newPhoneNumber)) {
	                alert("전화번호의 유효한 형식이 아닙니다. ex) 000-0000-0000");
	                return; 
	            }
	        	
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
		const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	    $.ajax({
	        type: "POST",
	        url: '<%=notWithFormTag%>ownerUserChangeEmail',
	        data: { userId: userId, newEmail: newEmail}, 
	        success: function(response, status, xhr) {
	        	if (!emailPattern.test(newEmail)) {
	        		alert("이메일의 유효한 형식이 아닙니다.");
	                return; 
				}
	        	
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
	        url: '<%=notWithFormTag%>ownerUserChangeBio',
	        data: { userId: userId, newBio: newBio}, 
	        success: function(response, status, xhr) {
	        	if (newBio.length > 500) {
	        		alert("사업소개은 500자 이상은 불가능합니다. ");
	            	return;
	        	}
	        	
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
	function deleteUser(event) {
		event.preventDefault();
		  const userId = $("#info-value-userId-hidden").val();
		  if (confirm("정말 탈퇴하시겠습니까??")) {
		    var url = "<%=notWithFormTag%>deleteOwnerUser&userId=" + userId;
			var xhr = new XMLHttpRequest();

			xhr.open("POST", url, true);

			xhr.onload = function() {
				if (xhr.status === 200) {
					window.location.replace("<%=notWithFormTag%>home");
				} else {
					console.error("요청 실패");
				}
			};
			xhr.send();
		} else {
			return;
		}
	}
</script>
</head>
<body>
	<div class="container1">
		<div class="container2">
			<h2>회원정보</h2>
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
					<span class="info-value">${requestScope.bean.userName}</span>
				</div>
			</div>
			<div class="info-container">
				<div class="info-box">
					<span class="info-key">사업이름</span>
					<span class="info-value" id="info-value-businessName">${requestScope.bean.businessName}</span>
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
				</div>
			</div>
			<div class="info-container">
				<div class="info-box">
					<span class="info-key">사업자 등록번호</span>
					<span class="info-value">${requestScope.bean.businessNumber}</span>
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
					<span class="info-key">사업소개</span>
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
					<span class="info-value">${requestScope.bean.registrationDate}</span>
				</div>
			</div>

			<div id="backButton">
				<button class="btn btn btn-danger deleteUserBtn" onclick="return deleteUser(event);">탈퇴하기</button>
			</div>
		</div>
	</div>
<%@ include file="../common/footer.jsp" %>