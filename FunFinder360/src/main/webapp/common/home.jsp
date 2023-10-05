<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
html, body {
	width: 100%;
	height: 2000px;
	margin: 0px;
	padding: 0px;
}

.container1 {
	position: absolute;
	width: 100%;
	height: 100vh;
	margin: 0px;
	padding: 0px;
	transition: background-color 0.5s; /* 배경 색상 전환 애니메이션을 추가합니다. */
    background-color: #f0f0f0;
}

.animation-active {
	animation: bounce 2.0s ease-in-out forwards;
}

/* 위로 통통 튀는 애니메이션 정의 */
@keyframes bounce {

	0%,
	20%,
	50%,
	80%,
	100% {
		transform: translateY(0);
	}

	40% {
		transform: translateY(-20px);
	}

	60% {
		transform: translateY(-10px);
	}
}

.info-1 {
	position: relative;
    display: block;
    top: 50%;
    width: 100%;
    text-align: center;
    height: 80px;
}

.info-1-box {
	width: 100%;
    position: relative;
    top: 50%;
    transform: translateY(-50%);
    font-size: 45px;
}


.info-1-box span {
	display: inline-block;
}

.info-1-box span:nth-child(10) {
	color: red;
}

.info-1-box span:nth-child(11) {
	color: red;
}

.info-1-box span:nth-child(12) {
	color: red;
}

.info-1-box span:nth-child(13) {
	color: red;
}

.info-1-box span:nth-child(14) {
	color: red;
}

.info-1-box span:nth-child(15) {
	color: red;
}

.info-1-box span:nth-child(16) {
	color: red;
}

.info-1-box span:nth-child(17) {
	color: red;
}

.info-1-box span:nth-child(18) {
	color: red;
}

.info-1-box span:nth-child(19) {
	color: red;
}

.info-1-box span:nth-child(20) {
	color: red;
}

.info-1-box span:nth-child(21) {
	color: red;
}


</style>

<script>
$(document).ready(function () {
	const spans = document.querySelectorAll('.info-1-box span');
	
	function resetAnimation() {
	    spans.forEach((span, index) => {
	        span.classList.remove('animation-active'); // 클래스 제거
	        setTimeout(() => {
	            span.classList.add('animation-active'); // 클래스 다시 추가하여 애니메이션 시작
	        }, index * 100); // 각 요소마다 0.1초씩 딜레이
	    });
	}
	
	resetAnimation();
	
	setInterval(() => {
		    resetAnimation();
		}, 5100);
	
	$(window).scroll(function() {
        // 현재 스크롤 위치 가져오기
        var scrollY = $(window).scrollTop();

        // 배경 색상을 스크롤 위치에 따라 변화시킵니다.
        var maxScroll = $(document).height() - $(window).height();
        var percentage = (scrollY / maxScroll) * 100;
        var color = 'rgb(' + percentage + ', ' + (100 - percentage) + ', 150)';
        $('.container').css('background-color', color);
      });
})


</script>
<body>
	<div class="container1">
		<div class="info-1">
			<div class="info-1-box">
				<span>놀</span><span>거</span><span>리</span><span>의 </span><span>
				</span><span>성</span><span>지</span><span>!</span> <span> </span><span>F</span><span>u</span><span>n</span><span>F</span><span>i</span><span>n</span><span>d</span><span>e</span><span>r</span><span>3</span><span>6</span><span>0</span><span>으</span><span>로</span>
				<span> </span><span>지</span><span>금</span> <span> </span><span>바</span><span>로</span>
				<span> </span><span>즐</span><span>길</span><span> </span><span>활</span><span>동</span><span>을</span>
				<span> </span><span>찾</span><span>아</span><span>보</span><span>세</span><span>요</span><span>.</span>
			</div>
		</div>
	</div>
</body>
</html>