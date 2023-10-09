<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<style>
.foot-container {
	width: 100%;
	height: 100px;
	background-color: gray;
	position:absolute;
	bottom: 0;
}
</style>
<div class="foot-container">
	<hr>
	<div class="company-info-container">
		<ul>
			<li><span>회사 이름:
					${applicationScope.companyInfoMap.companyName}</span> <span>회사 주소:
					${applicationScope.companyInfoMap.address}</span> <span>전화번호:
					${applicationScope.companyInfoMap.phoneNumber}</span></li>
			<li><span>이메일: ${applicationScope.companyInfoMap.email}</span> <span>설립일:
					${applicationScope.companyInfoMap.establishmentDate}</span> <span>사업자등록번호:
					${applicationScope.companyInfoMap.businessRegistrationNumber}</span></li>
		</ul>
	</div>
</div>
</body>
</html>