<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function backPage() {
		window.history.back();
	}
</script>
<style>
.container {
	margin-top: 130px;
	width: 100%;
	height: 100%;
	background-color: red;
}
</style>
<body>
<div class="container">
	<div class="container-1">
		<p>${requestScope.commonQuestionData.title }
	</div>
	<button value="돌아가기" onclick="backPage();">돌아가기</button>
</div>
</body>
</html>