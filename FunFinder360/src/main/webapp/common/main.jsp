<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainCSS/mainCSS.css">
 <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
<title>FunFinder360</title>
</head>
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script>
    AOS.init();  // 초기화 선언 필수
  </script>
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
	
	setInterval(() => {resetAnimation();}, 5100);
	
	const scrollImage = document.getElementById("scrollImage");
	
	window.addEventListener('scroll', () => {
	    document.body.style.setProperty('--scroll', window.pageYOffset / (document.body.offsetHeight - window.innerHeight));
	}, false);

	window.addEventListener("scroll", () => {
	    const scrollY = window.scrollY * 0.02;
	    scrollImage.style.transform = `scale(${1 + scrollY})`;
	});
	
	 $(window).scroll(function() {
	        const scrollY = $(this).scrollTop();
	        const windowHeight = $(this).height();
	        const imageHeight = $("#scrollImage").height();
	        const imageWidth = $("#scrollImage").width();

	        
	        const originalImageRatio = imageWidth / imageHeight;
	        
	        const newImageHeight = windowHeight * 0.8;
	        const newImageWidth = newImageHeight * originalImageRatio;
	        // 이미지 크기 조절
	        const newImageScale = (imageHeight + scrollY) / (windowHeight + imageHeight);

	        $("#scrollImage").css({
	            "transform": `scale(${newImageScale})`, // 이미지 스케일 조절
	            "height": newImageHeight + 300+ "px",
	            "width": newImageWidth + 400 + "px",
	        });
	    });

});
   

</script>
<body>
	<div class="container1">
		<div class="img-container">
			<img src="${pageContext.request.contextPath}/common/image/spring.jpg" class="spring-img">
			<img src="${pageContext.request.contextPath}/common/image/summer.jpg" class="summer-img">
			<img src="${pageContext.request.contextPath}/common/image/fall.jpg" class="fall-img">
			<img src="${pageContext.request.contextPath}/common/image/winter.jpg" class="winter-img">
			<div class="info-1">
				<div class="info-1-box">
					<span>놀</span>
					<span>거</span>
					<span>리</span>
					<span>의 </span>
					<span> </span>
					<span>성</span>
					<span>지</span>
					<span>!</span>
					<span> </span>
					<span>F</span>
					<span>u</span>
					<span>n</span>
					<span>F</span>
					<span>i</span>
					<span>n</span>
					<span>d</span>
					<span>e</span>
					<span>r</span>
					<span>3</span>
					<span>6</span>
					<span>0</span>
					<span>으</span>
					<span>로</span>
					<span> </span>
					<span>지</span>
					<span>금</span>
					<span> </span>
					<span>바</span>
					<span>로</span>
					<span> </span>
					<span>즐</span>
					<span>길</span>
					<span> </span>
					<span>활</span>
					<span>동</span>
					<span>을</span>
					<span> </span>
					<span>찾</span>
					<span>아</span>
					<span>보</span>
					<span>세</span>
					<span>요</span>
					<span>.</span>
				</div>
			</div>
		</div>
		<div class="info-2"  data-aos="fade-up"
		    data-aos-offset="200"
		    data-aos-delay="50"
		    data-aos-duration="1000"
		    data-aos-easing="ease-in-out"
		    data-aos-mirror="true"
		    data-aos-once="false"
		    data-aos-anchor-placement="top-center">
			<div class="circle-container">
				<img src="${pageContext.request.contextPath}/common/image/c4.jpg" id="scrollImage">
				<div class="info-2-box">
					<p class="category-tag">문화 엔터테인먼트</p>
					<div class="info-p-tag-box" style="font-size: 20px;">
						<p>당신의 문화적 탐험을 위한 멋진 순간을 찾아보세요.</p>
						<p>문화와 엔터테인먼트의 세계로 여행을 떠나세요!</p>
						<p>문화의 아름다움과 엔터테인먼트의 즐거움을 함께 누려보세요.</p>
						<p>풍부한 경험과 즐거운 시간이 기다리고 있어요!
					</div>
				</div>
			</div>
		</div>

		<%--
			
		<div class="info-4">
			<div class="circle-container-4">
				<div class="info-4-box">
					<p class="category-tag4">음식 요리</p>
					<div class="info-p-tag-box4">
						<p>맛있는 음식의 세계로 오신 것을 환영합니다!</p>
						<p>음식을 통해 새로운 맛과 경험을 만나보세요.</p>
						<p>음식의 기쁨과 만족을 느껴보세요.</p>
						<p>맛있는 음식의 세계가 여기 있습니다!</p>
					</div>
				</div>
				<img src="${pageContext.request.contextPath}/common/image/2.jpg">
			</div>
		</div>
		<div class="info-5">
			<div class="circle-container-5">
				<img src="${pageContext.request.contextPath}/common/image/3.jpg">
				<div class="info-5-box">
					<p class="category-tag5">교육 학습</p>
					<div class="info-p-tag-box5">
						<p>당신의 문화적 탐험을 위한 멋진 순간을 찾아보세요.</p>
						<p>문화와 엔터테인먼트의 세계로 여행을 떠나세요!</p>
						<p>문화의 아름다움과 엔터테인먼트의 즐거움을 함께 누려보세요.</p>
						<p>풍부한 경험과 즐거운 시간이 기다리고 있어요!
					</div>
				</div>
			</div>
		</div>
		<div class="info-6">
			<div class="circle-container-6">
				<div class="info-6-box">
					<p class="category-tag6">여행 모험</p>
					<div class="info-p-tag-box6">
						<p>세상을 탐험하며 새로운 경험을 쌓아보세요.</p>
						<p>어디든지 갈 수 있다면 어디로든 떠나보세요.</p>
						<p>모험을 떠나는 것을 미루지 마세요.</p>
						<p>모험은 일생 중에 가장 소중한 선물 중 하나입니다.</p>
					</div>
				</div>
				<img src="${pageContext.request.contextPath}/common/image/4.jpg">
			</div>
		</div>
		<div class="info-7">
			<div class="circle-container-7">
				<img src="${pageContext.request.contextPath}/common/image/5.jpg">
				<div class="info-7-box">
					<p class="category-tag7">게임 취미</p>
					<div class="info-p-tag-box7">
						<p>당신의 문화적 탐험을 위한 멋진 순간을 찾아보세요.</p>
						<p>문화와 엔터테인먼트의 세계로 여행을 떠나세요!</p>
						<p>문화의 아름다움과 엔터테인먼트의 즐거움을 함께 누려보세요.</p>
						<p>풍부한 경험과 즐거운 시간이 기다리고 있어요!
					</div>
				</div>
			</div>
		</div> --%>
	</div>
</body>
</html>
