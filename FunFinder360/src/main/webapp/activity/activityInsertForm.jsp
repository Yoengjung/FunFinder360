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
		const contentContainer = document
				.querySelector('.content-container-class');
		contentContainer.appendChild(textarea);
		contentCount++;
		document.getElementById("contentCountInput").value = contentCount;
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

		const contentContainer = document
				.querySelector('.content-container-class');
		contentContainer.appendChild(textarea);
		contentContainer.appendChild(imgTag);

		imageCount++;
		document.getElementById("imageCountInput").value = imageCount;

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
	$(document).ready(function() {
		const btnContainer = $('#content-controll-container');
		const windowHeight = $(window).height();
		const containerHeight = btnContainer.outerHeight();

		btnContainer.css({
			'position' : 'fixed',
			'bottom' : '0'
		});

		$('#btn').click(function() {
			$('html, body').animate({
				scrollTop : windowHeight + containerHeight
			}, 1000);
		});
	});

	function scrollToBottom() {
		const windowHeight = window.innerHeight;
		const documentHeight = document.documentElement.scrollHeight;
		const scrollTo = documentHeight - windowHeight;

		window.scrollTo({
			top : scrollTo,
			behavior : "smooth" // 애니메이션 효과를 부여합니다.
		});
	}

	function validation() {
		const title = $('#title').val()
		const category = $("#category").val()
		const province = $('#province').val()
		const district1 = $('#district-1').val()
		const district2 = $('#district-2').val()
		const district3 = $('#district-3').val()
		const district4 = $('#district-4').val()
		const district5 = $('#district-5').val()
		const district6 = $('#district-6').val()
		const district7 = $('#district-7').val()
		const district8 = $('#district-8').val()
		const district9 = $('#district-9').val()
		const district10 = $('#district-10').val()
		const hour = $('#hour').val()
		const minute = $('#minute').val()
		const rating = $("#rating").val()

		var districtCheck = 0;

		console.log(title);
		console.log(category);

		if (title === "") {
			alert("제목은 필수 입력 사항입니다.")
			$('#title').focus();
			return false;
		}
		if (category == "-") {
			alert("카테고리는 필수 선택 사항입니다.")
			$('#category').focus();
			return false;
		}
		if (province == "--지역--") {
			alert("장소는 필수 선택 사항입니다.")
			$('#province').focus();
			return false;
		}
		if (district1 != "지역") {
			districtCheck = 1;
		} else if (district2 != "지역") {
			districtCheck = 1;
		} else if (district3 != "지역") {
			districtCheck = 1;
		} else if (district4 != "지역") {
			districtCheck = 1;
		} else if (district5 != "지역") {
			districtCheck = 1;
		} else if (district6 != "지역") {
			districtCheck = 1;
		} else if (district7 != "지역") {
			districtCheck = 1;
		} else if (district8 != "지역") {
			districtCheck = 1;
		} else if (district9 != "지역") {
			districtCheck = 1;
		} else if (district10 != "지역") {
			districtCheck = 1;
		} else if (districtCheck == 0) {
			alert("장소는 필수 선택 사항입니다.");
			return false;
		}

		if (hour == "-") {
			alert("소요시간은 필수 선택 사항입니다.")
			$('#hour').focus();
			return false;
		}
		if (minute == "-") {
			alert("소요시간은 필수 선택 사항입니다.")
			$('#minute').focus();
			return false;
		}
		if (rating == "-") {
			alert("총점은 필수 입력 사항입니다.")
			$('#rating').focus();
			return false;
		}

		var contentCount = $("#contentCountInput").val()
		var imageCount = $('#imageCountInput').val();
		if (contentCount == 0) {
			alert("내용은 필수 입력 사항입니다. 버튼을 클릭하여 내용을 작성해주세요.")
			return false;
		}
		if (imageCount == 0) {
			alert("이미지는 필수 입력 사항입니다. 버튼을 클릭하여 내용을 작성해주세요.")
			return false;
		}

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

.cost-container {
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

#cost-span {
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

.cost-input {
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
	top: 70px;
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
		<div class="alert alert-warning">
			<strong>${sessionScope.alertMessage}</strong>
		</div>
		<h2>활동 등록</h2>

		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="command" value="activityInsert">
			<div>
				<ul>
					<li class="title-container">
						<span id="title-span">제목</span>
						<input class="form-control title-input" type="text" name="title" id="title">
					</li>
					<li class="category-container">
						<span>카테고리</span>
						<select class="form-select category-select" id="category" name="category">
							<option value="-">카테고리</option>
							<option value="문화 - 엔터테인먼트">문화 - 엔터테인먼트</option>
							<option value="음식 - 요리">음식 - 요리</option>
							<option value="교육 - 학습">교육 - 학습</option>
							<option value="여행 - 모험">여행 - 모험</option>
							<option value="사회 - 봉사">사회 - 봉사</option>
							<option value="게임 - 취미">게임 - 취미</option>
							<option value="뷰티 - 건강">뷰티 - 건강</option>
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
						<select class="form-select district-select" name="district" id="district-1">
							<option value="지역">--지역--</option>
							<option value="종로구">종로구</option>
							<option value="중구">중구</option>
							<option value="용산구">용산구</option>
							<option value="성동구">성동구</option>
							<option value="광진구">광진구</option>
							<option value="동대문구">동대문구</option>
							<option value="중랑구">중랑구</option>
							<option value="성북구">성북구</option>
							<option value="강북구">강북구</option>
							<option value="도봉구">도봉구</option>
							<option value="노원구">노원구</option>
							<option value="은평구">은평구</option>
							<option value="서대문구">서대문구</option>
							<option value="마포구">마포구</option>
							<option value="양천구">양천구</option>
							<option value="강서구">강서구</option>
							<option value="구로구">구로구</option>
							<option value="금천구">금천구</option>
							<option value="영등포구">영등포구</option>
							<option value="동작구">동작구</option>
							<option value="관악구">관악구</option>
							<option value="서초구">서초구</option>
							<option value="강남구">강남구</option>
							<option value="송파구">송파구</option>
							<option value="강동구">강동구</option>
						</select>
						<select class="form-select district-select" name="district" id="district-2">
							<option value="지역">--지역--</option>
							<option value="수원시">수원시</option>
							<option value="성남시">성남시</option>
							<option value="고양시">고양시</option>
							<option value="용인시">용인시</option>
							<option value="부천시">부천시</option>
							<option value="안산시">안산시</option>
							<option value="안양시">안양시</option>
							<option value="평택시">평택시</option>
							<option value="시흥시">시흥시</option>
							<option value="파주시">파주시</option>
							<option value="이천시">이천시</option>
							<option value="광주시">광주시</option>
							<option value="양주시">양주시</option>
							<option value="오산시">오산시</option>
							<option value="화성시">화성시</option>
							<option value="광명시">광명시</option>
						</select>
						<select class="form-select district-select" name="district" id="district-3">
							<option value="지역">--지역--</option>
							<option value="춘천시">춘천시</option>
							<option value="원주시">원주시</option>
							<option value="강릉시">강릉시</option>
							<option value="동해시">동해시</option>
							<option value="태백시">태백시</option>
							<option value="속초시">속초시</option>
							<option value="삼척시">삼척시</option>
						</select>
						<select class="form-select district-select" name="district" id="district-4">
							<option value="지역">--지역--</option>
							<option value="청주시">청주시</option>
							<option value="충주시">충주시</option>
							<option value="제천시">제천시</option>
							<option value="보은군">보은군</option>
							<option value="옥천군">옥천군</option>
						</select>
						<select class="form-select district-select" name="district" id="district-5">
							<option value="지역">--지역--</option>
							<option value="천안시">천안시</option>
							<option value="아산시">아산시</option>
							<option value="공주시">공주시</option>
							<option value="논산시">논산시</option>
							<option value="계룡시">계룡시</option>
						</select>
						<select class="form-select district-select" name="district" id="district-6">
							<option value="지역">--지역--</option>
							<option value="전주시">전주시</option>
							<option value="군산시">군산시</option>
							<option value="익산시">익산시</option>
							<option value="정읍시">정읍시</option>
						</select>
						<select class="form-select district-select" name="district" id="district-7">
							<option value="지역">--지역--</option>
							<option value="목포시">목포시</option>
							<option value="순천시">순천시</option>
							<option value="여수시">여수시</option>
							<option value="나주시">나주시</option>
							<option value="광양시">광양시</option>
							<option value="담양군">담양군</option>
							<option value="화순군">화순군</option>
						</select>
						<select class="form-select district-select" name="district" id="district-8">
							<option value="지역">--지역--</option>
							<option value="포항시">포항시</option>
							<option value="경주시">경주시</option>
							<option value="안동시">안동시</option>
							<option value="구미시">구미시</option>
							<option value="영주시">영주시</option>
							<option value="상주시">상주시</option>
							<option value="김천시">김천시</option>
							<option value="영천시">영천시</option>
						</select>
						<select class="form-select district-select" name="district" id="district-9">
							<option value="지역">--지역--</option>
							<option value="부산광역시">부산광역시</option>
							<option value="창원시">창원시</option>
							<option value="김해시">김해시</option>
							<option value="양산시">양산시</option>
							<option value="진주시">진주시</option>
							<option value="거제시">거제시</option>
							<option value="통영시">통영시</option>
							<option value="사천시">사천시</option>
							<option value="김천시">김천시</option>
						</select>
						<select class="form-select district-select" name="district" id="district-10">
							<option value="지역">--지역--</option>
							<option value="제주시">제주시</option>
							<option value="서귀포시">서귀포시</option>
						</select>
						<input class="form-control detail-location" type="text" name="detail-location" id="detail-location" autocomplete="off" placeholder="상세 주소">
					</li>
					<li class="time-container">
						<span id="time-span">소요 시간</span>
						<select class="form-select hour-select" name="hour" id="hour">
							<option value="-">시간</option>
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
						<span id="hour-span">시간</span>
						<select class="form-select minute-select" name="minute" id="minute">
							<option value="-">분</option>
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="20">20</option>
							<option value="25">25</option>
							<option value="30">30</option>
							<option value="35">35</option>
							<option value="40">40</option>
							<option value="45">45</option>
							<option value="50">50</option>
							<option value="55">55</option>
						</select>
						<span id="minute-span">분</span>
					</li>
					<li class="cost-container">
						<span id="cost-span">비용</span>
						<input class="form-control cost-input" type="number" name="cost" id="cost">
					</li>
					<li class="activityNumber-container">
						<span id="activityNumber-span">활동 인원</span>
						<input class="form-control activityNumber-input" type="number" name="activityNumber" id="activityNumber">
					</li>
					<li class="rating-container">
						<span id="rating-span">총점</span>
						<select class="form-select rating-input" name="rating" id="rating">
							<option value="-">총점</option>
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</li>
				</ul>
			</div>
			<div class="content-container-class"></div>
			<input type="hidden" name="contentCount" id="contentCountInput" value="">
			<input type="hidden" name="imageCount" id="imageCountInput" value="">

			<div class="content-controll-container">
				<button type="button" class="btn btn-primary" onclick="contentAdd()">
					<span class="material-symbols-outlined" style="vertical-align: middle;"> add_circle </span>
				</button>
				<button type="button" class="btn btn-primary" style="margin-left: 10px;" onclick="imageAdd()">
					<span class="material-symbols-outlined" style="vertical-align: middle;"> photo_camera </span>
				</button>
				<button type="submit" class="btn btn-success" onclick="return validation()">저장</button>
			</div>

		</form>
	</div>
</body>
</html>