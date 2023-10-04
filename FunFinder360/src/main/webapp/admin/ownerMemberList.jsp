<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업주 회원 정보 관리</title>
<style>
.container {
	margin-top: 9%;
	width: 100%;
	height: 100%;
	max-width: 1460px;
}

.container h2 {
	text-align: center;
	margin-bottom: 40px;
}

#pagingStatus-span {
	position: relative;
	display: block;
	float: right;
	font-size: 20px;
	margin-bottom: 10px;
	border: 2px solid black;
	border-radius: 10px;
	width: 132px;
	padding: 0px 10px;
}

.form-select {
	width: 200px;
}

.search-form {
	display: flex;
	flex-direction: row;
	justify-content: center;
}

.keyword-input-box {
	margin: 0 20px;
}

.paging-container {
	margin-top: 20px;
}

.input-group {
	width: 300px;
}

.form-select {
	width: 200px;
}

</style>
</head>
<body>
	<div class="container">
		<h2>업주 회원 리스트</h2>
		<span id="pagingStatus-span">${requestScope.pageInfo.pagingStatus}</span>
		<table class="table talbe-hover">
			<thead class="table-dark">
				<tr>
					<th>아이디</th>
					<th>비밀 번호</th>
					<th>이름</th>
					<th>사업명</th>
					<th>사업구분</th>
					<th>사업등록번호</th>
					<th>전화번호</th>
					<th>이메일</th>
					<th>가입일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.datalist}">
					<tr>
						<td>${bean.userId}</td>
						<td>${bean.password}</td>
						<td>${bean.userName}</td>
						<td>${bean.businessName}</td>
						<td>${bean.businessType}</td>
						<td>${bean.businessNumber}</td>
						<td>${bean.phoneNumber}</td>
						<td>${bean.email}</td>
						<td>${bean.registrationDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="search-container">
			<div class="search-in-container">
				<form name="search-form" action="<%=withFormTag%>" method="get" class="search-form">
					<input type="hidden" name="command" value="ownerMemberList">
					<select id="mode" name="mode" class="form-select">
						<option value="all" selected="selected">--- 선택해 주세요 ---
						<option value="userId">아이디
						<option value="password">비밀번호
						<option value="username">이름
						<option value="BusinessName">사업명
						<option value="BusinessType">사업구분
						<option value="BusinessNumber">사업등록번호
						<option value="phoneNumber">전화번호
						<option value="email">이메일
						<option value="registrationDate">가입 일자
					</select>
					<div class="input-group">
						<input class="keyword-input-box form-control" type="text" name="keyword" id="keyword" placeholder="키워드 입력" autocomplete="off">
					</div>
					<button type="submit" class="btn btn-success form-control-sm search-btn" onclick="">검색</button>
				</form>
			</div>
		</div>
		
	</div>
	<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
</body>
</html>