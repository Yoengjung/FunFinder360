<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminCSS/selectInfoCSS.css">
<title>관리자 정보 조회</title>
</head>
<body>
	<div class="container">
		<div class="box-container">
			<div class="box-1">
				<div class="infoSelect-btn-1">
					<a href="<%=notWithFormTag%>memberList">개인 회원 정보 조회</a>
				</div>
				<div class="infoSelect-btn-2">
					<a href="<%=notWithFormTag%>ownerMemberList">업주 회원 정보 조회</a>
				</div>
			</div>
			<div class="box-2">
				<div class="infoSelect-btn-3">
					<a href="<%=notWithFormTag%>memberActivityList">개인 게시물 정보 조회</a>
				</div>
				<div class="infoSelect-btn-4">
					<a href="<%=notWithFormTag%>ownerMemberActivityList">업주 게시물 정보 조회</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>