<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	$(document)
			.ready(
					function() {
						$(document).ready(function() {
							// 서울 특별시를 선택할 때
							$(".province-select").change(function() {
								// 선택한 값을 가져옵니다.
								const selectedProvince = $(this).val();

								$(".district-select").hide();

								// 만약 선택한 값이 '서울 특별시'라면 district-1 select를 보이도록 합니다.
								if (selectedProvince === '서울 특별시') {
									$(".district-select#district-1").show();
								} else if (selectedProvince === '경기도') {
									$(".district-select#district-2").show();
								} else if (selectedProvince === '강원도') {
									$(".district-select#district-3").show();
								} else if (selectedProvince === '충청북도') {
									$(".district-select#district-4").show();
								} else if (selectedProvince === '충청남도') {
									$(".district-select#district-5").show();
								} else if (selectedProvince === '전라북도') {
									$(".district-select#district-6").show();
								} else if (selectedProvince === '전라남도') {
									$(".district-select#district-7").show();
								} else if (selectedProvince === '경상북도') {
									$(".district-select#district-8").show();
								} else if (selectedProvince === '경상남도') {
									$(".district-select#district-9").show();
								} else if (selectedProvince === '제주도') {
									$(".district-select#district-10").show();
								}
							});
						});
						$(document)
								.ready(
										function() {
											// district-select가 변경될 때 이벤트 처리
											$(".district-select")
													.change(
															function() {
																// 선택한 district-select 요소를 가져옵니다.
																const selectedDistrict = $(this);

																// 만약 선택한 district-select 요소의 값이 '--지역--'가 아니라면
																if (selectedDistrict
																		.val() !== '--지역--') {
																	// detail-location input을 활성화시킵니다.
																	selectedDistrict
																			.closest(
																					'.location-container')
																			.find(
																					'.detail-location')
																			.prop(
																					'disabled',
																					false);
																} else {
																	// 선택한 값이 '--지역--'이라면 detail-location input을 비활성화시킵니다.
																	selectedDistrict
																			.closest(
																					'.location-container')
																			.find(
																					'.detail-location')
																			.prop(
																					'disabled',
																					true);
																}
															});
										});
					})
	$(document).ready(
			function() {
				// district-select가 변경될 때 이벤트 처리
				$(".district-select").change(
						function() {
							// 선택한 district-select 요소를 가져옵니다.
							const selectedDistrict = $(this);

							// 만약 선택한 district-select 요소의 값이 '--지역--'가 아니라면
							if (selectedDistrict.val() !== '--지역--') {
								// detail-location input을 보이도록 만듭니다.
								selectedDistrict.closest('.location-container')
										.find('.detail-location').show();
							} else {
								// 선택한 값이 '--지역--'이라면 detail-location input을 숨깁니다.
								selectedDistrict.closest('.location-container')
										.find('.detail-location').hide();
							}
						});
			});
	var contentCount = 0;
	var imageCount = 0;

	function contentAdd() {
		scrollToBottom()
		const textarea = document.createElement('textarea');
		textarea.name = 'content' + contentCount;
		textarea.className = "form-control content-container";
		const contentContainer = document.querySelector('.content-container-class');
		contentContainer.appendChild(textarea);
		contentCount++;
	}

	function imageAdd() {
		scrollToBottom()
		const textarea = document.createElement('input');
		const imgTag = document.createElement("img");
		textarea.name = 'image' + imageCount;
		textarea.className = "form-control image-container";
		textarea.type = "file";
		textarea.id = "image" + imageCount;

		imgTag.name = "previewImage";
		imgTag.id = "previewImage";
		imgTag.alt = "미리보기 이미지";
		imgTag.style = "max-height: 500px; display: inline-block; left: 50%;position: relative;transform: translateX(-50%);margin-top: 30px; display: none;";

		const contentContainer = document.querySelector('.content-container-class');
		contentContainer.appendChild(textarea);
		contentContainer.appendChild(imgTag);

		imageCount++;

		// 이미지 파일 업로드가 변경될 때 미리보기 처리
		$(textarea).on('change', function(event) {
			handleImageUpload(this, imgTag);
		});
	}

	function handleImageUpload(input, imgTag) {
		const file = input.files[0];
		if (file) {
			const reader = new FileReader();

			reader.onload = function(e) {
				imgTag.style.display = 'block';
				imgTag.src = e.target.result;
			};

			reader.readAsDataURL(file);
		} else {
			imgTag.style.display = 'none';
			imgTag.src = '';
		}
	}
	$(document).ready(function () {
	    const btnContainer = $('#content-controll-container');
	    const windowHeight = $(window).height();
	    const containerHeight = btnContainer.outerHeight();

	    btnContainer.css({ 'position': 'fixed', 'bottom': '0' });

	    $('#btn').click(function () {
	        $('html, body').animate({ scrollTop: windowHeight + containerHeight }, 1000);
	    });
	});


	function scrollToBottom() {
	    const windowHeight = window.innerHeight;
	    const documentHeight = document.documentElement.scrollHeight;
	    const scrollTo = documentHeight - windowHeight;
	    
	    window.scrollTo({
	        top: scrollTo,
	        behavior: "smooth" // 애니메이션 효과를 부여합니다.
	    });
	}
</script>
<style>
.container {
	margin-top: 130px;
	width: 100%;
	height: 100%;
}

.container form {
	margin-top: 20px;
	border-top: 1px solid black;
}

.district-select {
	display: none;
}

.location-container {
	display: flex;
	flex-direction: row;
	border-bottom: 1px solid #999999;
}

.district-select {
	width: 150px;
	margin-left: 10px;
}

.province-select {
	width: 200px;
}

.detail-location {
	display: none;
}

.title-container {
	display: flex;
	border-bottom: 1px solid #999999;
}

.title-input {
	width: 81%;
}

#title-span {
	width: 100px;
	text-align: center;
	vertical-align: center;
	font-weight: bolder;
	position: relative;
	top: 6px;
}

.category-container {
	display: flex;
	border-bottom: 1px solid #999999;
}

.category-container span {
	width: 100px;
	text-align: center;
	vertical-align: center;
	font-weight: bolder;
	position: relative;
	top: 6px;
}

.category-select {
	width: 200px;
}

#location-span {
	width: 100px;
	text-align: center;
	font-size: 17px;
	font-weight: bolder;
	position: relative;
	top: 6px;
}

.detail-location {
	width: 500px;
	margin-left: 10px;
}

.time-container {
	display: flex;
	flex-direction: row;
	border-bottom: 1px solid #999999;
}

#time-span {
	width: 100px;
	text-align: center;
	font-size: 17px;
	font-weight: bolder;
	position: relative;
	top: 6px;
}

.hour-select {
	width: 100px;
}

.minute-select {
	width: 100px;
}

.minute-select {
	margin-left: 10px;
}

.activityNumber-container {
	display: flex;
	flex-direction: row;
	border-bottom: 1px solid #999999;
}

#activityNumber-span {
	width: 100px;
	text-align: center;
	font-size: 17px;
	font-weight: bolder;
	position: relative;
	top: 6px;
}

.activityNumber-input {
	width: 100px;
}

.rating-container {
	display: flex;
	flex-direction: row;
	border-bottom: 1px solid #999999;
}

#rating-span {
	width: 100px;
	text-align: center;
	font-size: 17px;
	font-weight: bolder;
	position: relative;
	top: 6px;
}

.rating-input {
	width: 100px;
}

form ul li {
	padding: 5px 0px;
	margin-bottom: 5px;
}

#hour-span {
	margin-left: 10px;
	position: relative;
	top: 6px;
}

#minute-span {
	margin-left: 10px;
	position: relative;
	top: 6px;
}

.content-controll-container {
	position: relative;
    text-align: center;
    top: 70px;
    margin-bottom: 100px;
}

.content-container {
	position: relative;
	margin-bottom: 10px;
	top: 50px;
	resize: none;
	width: 100%;
	height: 300px;
	padding: 10px;
	border-radius: 10px;
	border: 5px solid #6666FF;
	font-size: 20px;
}

input[type="file"] {
	margin-top: 70px;
}

.btn-success {
	margin-left: 10px;
}
</style>

<body>
	<div class="container">
		<h2>활동 등록</h2>

		<form action="#" method="post">
			<div>
				<ul>
					<li class="title-container">
						<span id="title-span">제목</span>
						<input class="form-control title-input" type="text" name="title" id="title">
					</li>
					<li class="category-container">
						<span>카테고리</span>
						<select class="form-select category-select">
							<option>문화 - 엔터테인먼트</option>
							<option>음식 - 요리</option>
							<option>교육 - 학습</option>
							<option>여행 - 모험</option>
							<option>사회 - 봉사</option>
							<option>게임 - 취미</option>
							<option>뷰티 - 건강</option>
						</select>
					</li>
					<li class="location-container">
						<span id="location-span">장소</span>
						<select class="form-select province-select" name="province" id="province">
							<option>--지역--</option>
							<option>서울 특별시</option>
							<option>경기도</option>
							<option>강원도</option>
							<option>충청북도</option>
							<option>충청남도</option>
							<option>전라북도</option>
							<option>전라남도</option>
							<option>경상북도</option>
							<option>경상남도</option>
							<option>제주도</option>
						</select>
						<select class="form-select district-select" name="district-1" id="district-1">
							<option>--지역--</option>
							<option>종로구</option>
							<option>중구</option>
							<option>용산구</option>
							<option>성동구</option>
							<option>광진구</option>
							<option>동대문구</option>
							<option>중랑구</option>
							<option>성북구</option>
							<option>강북구</option>
							<option>도봉구</option>
							<option>노원구</option>
							<option>은평구</option>
							<option>서대문구</option>
							<option>마포구</option>
							<option>양천구</option>
							<option>강서구</option>
							<option>구로구</option>
							<option>금천구</option>
							<option>영등포구</option>
							<option>동작구</option>
							<option>관악구</option>
							<option>서초구</option>
							<option>강남구</option>
							<option>송파구</option>
							<option>강동구</option>
						</select>
						<select class="form-select district-select" name="district-2" id="district-2">
							<option>--지역--</option>
							<option>수원시</option>
							<option>성남시</option>
							<option>고양시</option>
							<option>용인시</option>
							<option>부천시</option>
							<option>안산시</option>
							<option>안양시</option>
							<option>평택시</option>
							<option>시흥시</option>
							<option>파주시</option>
							<option>이천시</option>
							<option>광주시</option>
							<option>양주시</option>
							<option>오산시</option>
							<option>화성시</option>
							<option>광명시</option>
						</select>
						<select class="form-select district-select" name="district-3" id="district-3">
							<option>--지역--</option>
							<option>춘천시</option>
							<option>원주시</option>
							<option>강릉시</option>
							<option>동해시</option>
							<option>태백시</option>
							<option>속초시</option>
							<option>삼척시</option>
						</select>
						<select class="form-select district-select" name="district-4" id="district-4">
							<option>--지역--</option>
							<option>청주시</option>
							<option>충주시</option>
							<option>제천시</option>
							<option>보은군</option>
							<option>옥천군</option>
						</select>
						<select class="form-select district-select" name="district-5" id="district-5">
							<option>--지역--</option>
							<option>천안시</option>
							<option>아산시</option>
							<option>공주시</option>
							<option>논산시</option>
							<option>계룡시</option>
						</select>
						<select class="form-select district-select" name="district-6" id="district-6">
							<option>--지역--</option>
							<option>전주시</option>
							<option>군산시</option>
							<option>익산시</option>
							<option>정읍시</option>
						</select>
						<select class="form-select district-select" name="district-7" id="district-7">
							<option>--지역--</option>
							<option>목포시</option>
							<option>순천시</option>
							<option>여수시</option>
							<option>나주시</option>
							<option>광양시</option>
							<option>담양군</option>
							<option>화순군</option>
						</select>
						<select class="form-select district-select" name="district-8" id="district-8">
							<option>--지역--</option>
							<option>포항시</option>
							<option>경주시</option>
							<option>안동시</option>
							<option>구미시</option>
							<option>영주시</option>
							<option>상주시</option>
							<option>김천시</option>
							<option>영천시</option>
						</select>
						<select class="form-select district-select" name="district-9" id="district-9">
							<option>--지역--</option>
							<option>부산광역시</option>
							<option>창원시</option>
							<option>김해시</option>
							<option>양산시</option>
							<option>진주시</option>
							<option>거제시</option>
							<option>통영시</option>
							<option>사천시</option>
							<option>김천시</option>
						</select>
						<select class="form-select district-select" name="district-10" id="district-10">
							<option>--지역--</option>
							<option>제주시</option>
							<option>서귀포시</option>
						</select>
						<input class="form-control detail-location" type="text" name="detail-location" id="detail-location" autocomplete="off" placeholder="상세 주소">
					</li>
					<li class="time-container">
						<span id="time-span">소요 시간</span>
						<select class="form-select hour-select" name="hour" id="hour">
							<option>0</option>
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
						</select>
						<span id="hour-span">시간</span>
						<select class="form-select minute-select" name="minute" id="minute">
							<option>5</option>
							<option>10</option>
							<option>15</option>
							<option>20</option>
							<option>25</option>
							<option>30</option>
							<option>35</option>
							<option>40</option>
							<option>45</option>
							<option>50</option>
							<option>55</option>
						</select>
						<span id="minute-span">분</span>
					</li>
					<li class="activityNumber-container">
						<span id="activityNumber-span">활동 인원</span>
						<input class="form-control activityNumber-input" type="number" name="activityNumber" id="activityNumber">
					</li>
					<li class="rating-container">
						<span id="rating-span">총점</span>
						<select class="form-select rating-input" name="rating" id="rating">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</li>
				</ul>
			</div>
			<div class="content-container-class">
			
			</div>
			<div class="content-controll-container">
				<button type="button" class="btn btn-primary" onclick="contentAdd()">
					<span class="material-symbols-outlined" style="vertical-align: middle;"> add_circle </span>
				</button>
				<button type="button" class="btn btn-primary" style="margin-left: 10px;" onclick="imageAdd()">
					<span class="material-symbols-outlined" style="vertical-align: middle;"> photo_camera </span>
				</button>
				<button type="submit" class="btn btn-success">저장</button>
			</div>

		</form>
	</div>
</body>
</html>