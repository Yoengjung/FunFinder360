<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function backPage() {
	location.href = "<%=notWithFormTag%>findId"
}
</script>

<style>
.container {
	position: absolute;
	width: 100%;
	height: 100%;
	padding: 0px;
	max-width: none;
	top: 130px;
}


.search-container {
	width: 475px;
	height: 500px;
	position: absolute;
	left: 50%;
	top: 130px;
	transform: translateX(-50%);
	text-align: center;
}

.search-form h2 {
	text-align: center;
	margin-bottom: 20px;
}


</style>
</head>
<body>
	<div class="container">
		<div class="search-container">
			<div class="search-form">
				<h2>${name}의 아이디</h2>
				<div class="result">
					<p>
						찾은 아이디: <span id="foundId">${foundId.id}</span>
					</p>
					<div>
						<button class="back-btn" value="돌아가기" onclick="backPage();">돌아가기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>