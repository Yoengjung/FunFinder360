<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script>
	$(document).ready(function() {
	})

	function validation() {
		const id = $("#id").val();
		const password = $("#password").val();
		const passwordConfirm = $("#passwordConfirm").val();
		const username = $("#username").val();
		const businessName = $("#businessName").val();
		const businessType = $("input[name='businessType']:checked").val()
		const businessNumber = $("#businessNumber").val();
		const phoneNumber = $("#phoneNumber").val();
		const email = $("#email").val();
		const profileImage = $("#profileImage").val();

		const idPattern = /^[a-zA-Z0-9]{6,20}$/;
		const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@#$%^&+=!])[A-Za-z\d@#$%^&+=!]{8,20}$/;
		const businessPattern = /^\d{3}-\d{2}-\d{5}$/;
		const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		const phoneRegex = /^\d{3}-\d{4}-\d{4}$/;

		if (id == "") {
			var element = document.getElementById("id-empty-alert-message");
			element.style.display = "block";
			return false;
		} else if (!idPattern.test(id)) {
			var element = document.getElementById("id-alert-message-tag");
			element.style.display = "block";
			return false;
		} else {
			var element = document.getElementById("id-alert-message-tag");
			element.style.display = "none";
			var emptyElement = document
					.getElementById("id-empty-alert-message");
			emptyElement.style.display = "none";
		}
		if (password == "") {
			var emptyElement = document
					.getElementById("password-empty-alert-masseage-tag");
			emptyElement.style.display = "block";
			return false;
		} else {
			var emptyElement = document
					.getElementById("password-empty-alert-masseage-tag");
			emptyElement.style.display = "none";
		}

		if (!passwordPattern.test(password)) {
			var element = document.getElementById("password-alert-message-tag");
			element.style.display = "block";
			return false;
		} else {
			var element = document.getElementById("password-alert-message-tag");
			element.style.display = "none";
		}
		if (passwordConfirm == "") {
			var confirmElement = document
					.getElementById("passwordConfirm-alert-message-tag");
			confirmElement.style.display = "block";
			return false;
		} else {
			var confirmElement = document
					.getElementById("passwordConfirm-alert-message-tag");
			confirmElement.style.display = "none";
		}

		if (password !== passwordConfirm) {
			var confirmElement = document
					.getElementById("passwordConfirm-alert-message-tag");
			confirmElement.style.display = "block";
			return false;
		} else {
			var confirmElement = document
					.getElementById("passwordConfirm-alert-message-tag");
			confirmElement.style.display = "none";
		}

		if (username == "") {
			var emptyElement = document
					.getElementById("username-empty-alert-message");
			emptyElement.style.display = "block";
			return false;
		} else {
			var emptyElement = document
					.getElementById("username-empty-alert-message");
			emptyElement.style.display = "none";
		}
		if (username.length < 2) {
			var emptyElement = document
					.getElementById("username-alert-message-tag");
			emptyElement.style.display = "block";
			return false;
		} else {
			var emptyElement = document
					.getElementById("username-alert-message-tag");
			emptyElement.style.display = "none";
		}

		if (businessName == "") {
			var emptyElement = document
					.getElementById("business-name-empty-alert-message");
			emptyElement.style.display = "block";
			return false;
		} else {
			var emptyElement = document
					.getElementById("business-name-empty-alert-message");
			emptyElement.style.display = "none";
		}

		if (businessType == "") {
			var emptyElement = document
					.getElementById("businessType-empty-alert-message");
			emptyElement.style.display = "block";
			return false;
		} else {
			var emptyElement = document
					.getElementById("businessType-empty-alert-message");
			emptyElement.style.display = "none";
		}

		if (businessNumber == "") {
			var emptyElement = document
					.getElementById("businessNumber-empty-alert-message");
			emptyElement.style.display = "block";
			return false;
		} else {
			var emptyElement = document
					.getElementById("businessNumber-empty-alert-message");
			emptyElement.style.display = "none";
		}

		if (!businessPattern.test(businessNumber)) {
			var element = document
					.getElementById("businessNumber-alert-message-tag");
			element.style.display = "block";
			return false;
		} else {
			var element = document
					.getElementById("businessNumber-alert-message-tag");
			element.style.display = "none";
		}

		if (phoneNumber == "") {
			var emptyElement = document
					.getElementById("phoneNumber-empty-alert-message");
			emptyElement.style.display = "block";
			return false;
		} else {
			var emptyElement = document
					.getElementById("phoneNumber-empty-alert-message");
			emptyElement.style.display = "none";
		}

		if (!phoneRegex.test(phoneNumber)) {
			var element = document
					.getElementById("phoneNumber-alert-message-tag");
			element.style.display = "block";
			return false;
		} else {
			var element = document
					.getElementById("phoneNumber-alert-message-tag");
			element.style.display = "none";
		}

		if (email == "") {
			var emptyElement = document
					.getElementById("email-empty-alert-message-tag");
			emptyElement.style.display = "block";
			return false;
		} else {
			var emptyElement = document
					.getElementById("email-empty-alert-message-tag");
			emptyElement.style.display = "none";
		}

		if (!emailPattern.test(email)) {
			var invalidElement = document
					.getElementById("email-alert-message-tag");
			invalidElement.style.display = "block";
			return false;
		} else {
			var invalidElement = document
					.getElementById("email-alert-message-tag");
			invalidElement.style.display = "none";
		}
	}
</script>
<style>
.container {
	position: absolute;
	top: 130px;
	width: 100%;
	height: 150%;
	padding: 0px;
	max-width: none;
}

textarea {
	resize: none;
}

.join-container {
	display: block;
	position: absolute;
	width: 100%;
	height: 100%;
	justify-content: center;
	align-items: center;
}

.join-form {
	display: inline-block;
	position: absolute;
	width: 450px;
	heiht: 700px;
	left: 50%;
	transform: translateX(-50%);
	top: 50px;
}

.join-form h2 {
	text-align: center;
}

.join-form form ul li {
	margin-bottom: 7px;
}

.join-form form ul li span {
	font-size: 17px;
}

#password {
	margin-bottom: 5px;
}

.btn-secondary {
	width: 100%;
	margin-top: 10px;
}

.form-control {
	margin-top: 7px;
}

#id-alert-message-tag {
	display: none;
	color: red;
}

#id-empty-alert-message {
	display: none;
	color: red;
}

#password-empty-alert-masseage-tag {
	display: none;
	color: red;
}

#password-alert-message-tag {
	display: none;
	color: red;
}

#passwordConfirm-alert-message-tag {
	display: none;
	color: red;
}

#email-empty-alert-message-tag {
	display: none;
	color: red;
}

#username-alert-message-tag {
	display: none;
	color: red;
}

#username-empty-alert-message {
	display: none;
	color: red;
}

#email-alert-message-tag {
	display: none;
	color: red;
}

#phoneNumber-alert-message-tag {
	display: none;
	color: red;
}

#phoneNumber-empty-alert-message {
	display: none;
	color: red;
}

#business-name-empty-alert-message {
	display: none;
	color: red;
}

#businessType-empty-alert-message {
	display: none;
	color: red;
}

#businessNumber-alert-message-tag {
	display: none;
	color: red;
}

#businessNumber-empty-alert-message {
	display: none;
	color: red;
}

.foot-container {
	position: absolute;
	bottom: 100px;
	width: 100%;
}
</style>
</head>
<body>
	<div class="container">
		<div class="join-container">
			<div class="join-form">
				<h2>업주 회원가입</h2>
				<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
					<input type="hidden" name="command" value="join">
					<ul>
						<li>
							<span>아이디</span>
							<span style="color: red">*</span>
							<input class="form-control" type="text" id="id" name="id" autocomplete="off" placeholder="영문, 숫자, 6 ~ 20자">
							<p id="id-alert-message-tag">아이디는 영문, 숫자로 구성되어야 하며 6 ~ 20자 이어야 합니다.</p>
							<p id="id-empty-alert-message">아이디는 필수 입력 사항입니다.</p>
						</li>
						<li>
							<span>비밀번호</span>
							<span style="color: red">*</span>
							<input class="form-control" type="password" id="password" name="password" placeholder="비밀번호(영문 대/소문자, 숫자, 특수문자 중 2가지 조합, 8~20)">
							<p id="password-alert-message-tag">비밀번호는 영문 대/소문자, 숫자, 특수문자 중 2가지 이상 조합으로 8 ~ 20자 이어야 합니다.</p>
							<p id="password-empty-alert-masseage-tag">비밀번호는 필수 입력 사항입니다.</p>
							<input class="form-control" type="password" id="passwordConfirm" name="passwordConfirm" placeholder="비밀번호 확인">
							<p id="passwordConfirm-alert-message-tag">비밀번호가 일치하지 않습니다.</p>
						</li>
						<li>
							<span>이름</span>
							<span style="color: red">*</span>
							<input class="form-control" type="text" id="username" name="username" autocomplete="off" placeholder="이름 입력">
							<p id="username-alert-message-tag">허용되지 않는 문자가 있거나 최소 2글자이어야 합니다.</p>
							<p id="username-empty-alert-message">이름은 필수 입력 사항입니다.</p>
						</li>
						<li>
							<span>사업이름</span>
							<span style="color: red">*</span>
							<input class="form-control" type="text" id="businessName" name="businessName" autocomplete="off" placeholder="사업이름 입력">
							<p id="business-name-empty-alert-message">사업이름은 필수 입력 사항입니다.</p>
						</li>

						<li>
							<span>사업 구분</span>
							<span style="color: red">*</span>
							<div class="form-check">
								<input type="radio" class="form-check-input" id="businessTypeRadio1" name="businessType" value="personalBusiness" checked>
								개인 사업자 <label class="form-check-label" for="radio1"></label>
							</div>
							<div class="form-check">
								<input type="radio" class="form-check-input" id="businessTypeRadio2" name="businessType" value="corporateBusiness">
								법인 <label class="form-check-label" for="radio2"></label>
							</div>
							<p id="businessType-empty-alert-message">사업 구분은 필수 선택 사항입니다.</p>
						</li>

						<li>
							<span>사업자등록번호</span>
							<span style="color: red">*</span>
							<input class="form-control" type="text" id="businessNumber" name="businessNumber" autocomplete="off" placeholder="사업자등록번호 입력">
							<p id="businessNumber-alert-message-tag">유효하지 않는 사업자등록번호입니다. 000-00-00000 형식으로 입력해야 합니다.</p>
							<p id="businessNumber-empty-alert-message">사업자등록번호는 필수 입력 사항입니다.</p>
						</li>

						<li>
							<span>전화번호</span>
							<span style="color: red">*</span>
							<input class="form-control" type="text" id="phoneNumber" name="phoneNumber" autocomplete="off" placeholder="전화번호 입력">
							<p id="phoneNumber-alert-message-tag">유효하지 않는 전화번호입니다. 000-0000-0000 형식으로 입력해야 합니다.</p>
							<p id="phoneNumber-empty-alert-message">전화번호는 필수 입력 사항입니다.</p>
						</li>


						<li>
							<span>이메일주소</span>
							<span style="color: red">*</span>
							<input class="form-control" type="email" id="email" name="email" placeholder="이메일 주소입력" autocomplete="off">
							<p id="email-empty-alert-message-tag">이메일은 필수 입력 사항입니다.</p>
							<p id="email-alert-message-tag">유효한 이메일 주소를 입력해주세요</p>
						</li>
						<li>
							<span>자기소개</span>
							<textarea class="form-control" id="introduction" name="introduction" placeholder="간단한 자기소개를 입력해주세요."></textarea>
						</li>
						<li>
							<span>프로필 이미지</span>
							<input type="file" class="form-control" id="profileImage" name="profileImage">
						</li>
						<li>
							<button class="btn btn-secondary" type="submit" onclick="return validation()">회원가입</button>
						</li>
					</ul>
				</form>
			</div>
		</div>
		<div class="foot-container">
			<hr>
			<div class="company-info-container">
				<ul>
					<li>
						<span>회사 이름: ${applicationScope.companyInfoMap.companyName}</span>
						<span>회사 주소: ${applicationScope.companyInfoMap.address}</span>
						<span>전화번호: ${applicationScope.companyInfoMap.phoneNumber}</span>
					</li>
					<li>
						<span>이메일: ${applicationScope.companyInfoMap.email}</span>
						<span>설립일: ${applicationScope.companyInfoMap.establishmentDate}</span>
						<span>사업자등록번호: ${applicationScope.companyInfoMap.businessRegistrationNumber}</span>
					</li>
				</ul>
			</div>

		</div>
	</div>

</body>
</html>