<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 회원 정보 관리</title>
<style>
body {
	min-height: 800px;
}

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
	width: auto;
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

#userId-tag {
	color: blue;
	text-decoration: underline;
}

.page-link {
	background-color: white;
	color: black;
	border: 0px;
	margin: 0px 10px;
	border-radius: 0px;
}

.active {
	background-color: white;
	font-weight: 700;
} 

.pagination {
    --bs-pagination-padding-x: 0.75rem;
    --bs-pagination-padding-y: 0.375rem;
    --bs-pagination-font-size: 1rem;
    --bs-pagination-color: white;
    --bs-pagination-bg: var(--bs-body-bg);
    --bs-pagination-border-width: var(--bs-border-width);
    --bs-pagination-border-color: var(--bs-border-color);
    --bs-pagination-border-radius: var(--bs-border-radius);
    --bs-pagination-hover-color: white;
    --bs-pagination-hover-bg: var(--bs-tertiary-bg);
    --bs-pagination-hover-border-color: var(--bs-border-color);
    --bs-pagination-focus-color: var(--bs-link-hover-color);
    --bs-pagination-focus-bg: var(--bs-secondary-bg);
    --bs-pagination-focus-box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
    --bs-pagination-active-color: black;
    --bs-pagination-active-bg: white;
    --bs-pagination-active-border-color: #0d6efd;
    --bs-pagination-disabled-color: var(--bs-secondary-color);
    --bs-pagination-disabled-bg: var(--bs-secondary-bg);
    --bs-pagination-disabled-border-color: var(--bs-border-color);


</style>
</head>
<body>
	<div class="container">
		<h2>개인 회원 리스트</h2>
		<span id="pagingStatus-span">${requestScope.pageInfo.pagingStatus}</span>
		<table class="table talbe-hover">
			<thead class="table-dark">
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>생년월일</th>
					<th>전화번호</th>
					<th>이메일</th>
					<th>가입 일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.datalist}">
					<tr>
						<td><a href="<%=notWithFormTag%>personalUserTotalDetail&userId=${bean.userId}" id="userId-tag">${bean.userId}</a></td>
						<td>${bean.username}</td>
						<td>${bean.birth}</td>
						<td>${bean.phoneNumber}</td>
						<td>${bean.email}</td>
						<td>${bean.registration_date}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="search-container">
			<div class="search-in-container">
				<form name="search-form" action="<%=withFormTag%>" method="get" class="search-form">
					<input type="hidden" name="command" value="memberList">
					<select id="mode" name="mode" class="form-select">
						<option value="all" selected="selected">--- 선택해 주세요 ---
						<option value="userId">아이디
						<option value="password">비밀번호
						<option value="username">이름
						<option value="birth">생년월일
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