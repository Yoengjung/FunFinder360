<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
<script>
	
</script>
<style>
.container {
	position: absolute;
	top: 130px;
	width: 100%;
	height: 100%;
	max-width: none;
}

.slider {
	width: 100%;
	max-width: 600px; /* 슬라이더의 최대 너비 지정 */
	overflow: hidden; /* 넘치는 이미지 숨김 */
	position: relative; /* 상대 위치 설정 */
}

.slide {
	width: 100%;
	display: none; /* 이미지 숨김 */
	text-align: center;
}

.slide img {
	max-width: 100%;
	height: auto;
}

/* 첫 번째 슬라이드를 보이게 함 */
.slide:first-child {
	display: block;
}

/* 슬라이드 전환 애니메이션 */
.slide {
	animation: fade 3s linear infinite;
}

@keyframes fade {
  0%, 100% {
    opacity: 0;
  }
  25%, 75% {
    opacity: 1;
  }
}

</style>
</head>
<body>
	<div class="container">
		<h2>home</h2>
		asdlfkjaskdlfjkl
		<div class="slider">
			<div class="slide">
				<img src="${pageContext.request.contextPath}/common/image/1.jpeg" alt="Image 1">
			</div>
			<div class="slide">
				<img src="${pageContext.request.contextPath}/common/image/2.jpeg" alt="Image 2">
			</div>
			<div class="slide">
				<img src="${pageContext.request.contextPath}/common/image/3.jpeg" alt="Image 3">
			</div>
		</div>
	</div>
</body>
</html>
