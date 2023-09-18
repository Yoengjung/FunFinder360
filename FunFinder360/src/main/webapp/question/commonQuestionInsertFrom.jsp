<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.container {
	margin-top: 130px;
	width: 100%;
	height: 100%;

}
</style>

</head>
<body>
	<div class="container">
		<h2>공통 질문 작성</h2>
		<p>관리자가 공통 질문 작성하는 페이지입니다.</p>
<%-- 		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data"> --%>
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="commonQuestionsInsert">
			<div class="input-group">
				<span class="input-group-text">제목</span>
				<input class="form-control" type="text" id="title" name="title">
			</div>
			<div class="input-group">
				<span class="input-group-text">등록자명</span>
				<input class="form-control" type="text" id="userId" name="userId">
			</div>
			<div class="input-group">
				<span class="input-group-text">설명</span>
				<input class="form-control" type="text" id="content" name="content">
			</div>
			<div class="input-group">
				<span class="input-group-text">등록일</span>
				<input class="form-control" type="datetime" id="postedDate" name="postedDate">
			</div>
			<div class="input-group">
				<button type="submit" class="btn btn-primary" onclick="return validCheck();">등록</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-primary">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>