<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/activityCSS/ownerActivityDetailFormCSS.css">
<title>활동 상세 보기</title>
<style>
body {
	margin: 0px;
	padding: 0px;
	height: 100vh;
}

.container {
	position: absolute;
	width: 100%;
	min-height: 100vh;
	margin: 0px;
	padding: 0px;
	top: 200px;
	left: 50%;
	transform: translateX(-50%);
}

.container-box {
	display: flex;
	flex-direction: column;
	font-size: 20px;
	margin-bottom: 20px;
	border-bottom: 2px solid black;
	padding: 20px;
	background-color: #F8F0E5;
	border: 3px solid #EADBC8;
	border-radius: 10px;
}

#category-tag {
	display: inline;
	margin: 0px;
	width: 300px;
}

.container-box h2 {
	margin-top: 20px;
	margin-bottom: 20px;
	font-size: 45px;
}

.star-icon-fav {
	color: black;
	font-size: 50px;
	margin: 0px !important;
	position: absolute;
	right: 10px;
	cursor: pointer;
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.heart-icon-fav {
	color: black;
	font-size: 45px;
	top: 28px;
	margin: 0px !important;
	position: absolute;
	right: 70px;
	cursor: pointer;
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.star-icon {
	color: #FFFF00;
}



.rating-container {
	margin-bottom: 30px;
}

.location-container {
	font-size: 15px;
}

.location-container span {
	display: inline-flex;
	flex-direction: row;
}

.container-box span {
	margin-bottom: 20px;
}

.cost-container {
	font-size: 17px;
	height: 24px;
}

.cost-container span {
	display: inline-flex;
	flex-direction: row;
}

.contents-image {
	position: relative;
	left: 50%;
	transform: translateX(-50%);
	margin-bottom: 50px;
	object-fit: none;
	height: 500px;
}

#activityNumber-tag {
	font-size: 17px;
}

pre {
	font-family: 'Nanum Gothic Coding', cursive;
	font-family: 'Noto Sans KR', sans-serif;
	font-family: 'Sunflower', sans-serif;
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 25px;
	margin-top: 30px;
	margin-bottom: 30px;
	padding: 50px 10px 50px 10px;
	background-color: #FFF5E0;
	border: 2px solid #D2E0FB;
	border-radius: 10px;
	width: 80%;
	position: relative;
	left: 50%;
	transform: translateX(-50%);
	white-space: pre-wrap;
}


.star-box {
	position: relative;
	display: inline-block;
}

#star-icon-tag {
	margin: 0px;
	left: 0px;
}

.contents-container {
	display: flex;
	flex-direction: column;
}

.userid-postedDate-container {
	display: flex;
	flex-direction: row;
	font-size: 20px;
}

.userid-postedDate-container span {
	margin: 0px;
}

.userid-postedDate-container span:first-child {
	margin-right: 20px;
}

#readHit-teg {
	position: absolute;
	display: block;
	right: 100px;
}

.back-btn-box {
	top: 20px;
	position: relative;
	margin-bottom: 50px;
}

.back-btn {
	position: relative;
	left: 50%;
	transform: translateX(-50%);
}

.form-container {
	position: relative;
	top: 30px;
	width: 80%;
	left: 50%;
	transform: translateX(-50%);
}

textarea {
	resize: none;
}

.input-group {
	width: 80%;
	left: 50%;
	transform: translateX(-50%);
	height: 100px;
}

.login-info-tag {
	position: relative;
	width: 80%;
	left: 50%;
	transform: translateX(-50%);
	margin-bottom: 10px;
}

.review-star-select-box {
	position: relative;
	left: 10%;
	margin-bottom: 10px;
}

#empty-star1,
#empty-star2,
#empty-star3,
#empty-star4,
#empty-star5 {
	width: 40px;
	cursor: pointer;
}

.submit-box {
	width: 100%;
}

.btn-dark {
	position: relative;
	left: 50%;
	transform: translateX(-50%);
	margin-bottom: 30px;
}

.review-box {
	border: 1px solid black;
	border-radius: 5px;
	margin-bottom: 10px;
	padding: 10px;
}

.review-head {
	margin-bottom: 5px;
	font-size: 20px;
	vertical-align: auto;
}

#review-star-icon-tag {
	width: 20px;
	position: relative;
	bottom: 3px;
}

#review-rating-tag {
	display: none;
	color: red;
}
#reviewContent-tag {
	display: none;
	color:red;
}
.review-box {
	margin-top: 10px;
}

.event-box {
	width: 100%;
	padding: 20px;
	border: 1px solid #A6F6FF;
	border-radius: 10px;
	background-color: #CDFCF6;
}

.avg-rating-box {
	display: flex;
	flex-direction: column;
	text-align: center;
    width: 200px;
    font-size: 30px;
    margin-right:30px;
}

#avg-value {
	text-align: center;
}

.totalRating-box {
	position: relative;
	display: inline-flex;
	left: 50%;
	transform: translateX(-50%);
	font-size: 20px;
}

#rating-score {
	margin-right: 10px;
}
.ratings-box {
	display:flex;
	flex-direction: column;	
}


</style>
<script>
	var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
	var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	  return new bootstrap.Tooltip(tooltipTriggerEl)
	})
	function backPage() {
		location.href = "<%=notWithFormTag%>OwnerActivitesList"
	}
	var check = false;
	$(document).ready(function() {
		$(".star-icon-fav").click(function() {
			if (!check) {
				$(this).css("color", "#FF9B50");
				check = true;
			} else {
				$(this).css("color", "black");
				check = false;
			}
		})
		$(".heart-icon-fav").click(function() {
			if (!check) {
				$(this).css("color", "#FF6969");
				check = true;
			} else {
				$(this).css("color", "black");
				check = false;
			}
		})
	});

	$(document).ready(function() {
		$("#empty-star1").click(function() {
			$("#empty-star1").attr("src", "./common/image/fullStar.png")
			$("#empty-star2").attr("src", "./common/image/emptyStar.png")
			$("#empty-star3").attr("src", "./common/image/emptyStar.png")
			$("#empty-star4").attr("src", "./common/image/emptyStar.png")
			$("#empty-star5").attr("src", "./common/image/emptyStar.png")

			$("#reviewRating").val(1)
		})
	})
	$(document).ready(function() {
		$("#empty-star2").click(function() {
			$("#empty-star1").attr("src", "./common/image/fullStar.png")
			$("#empty-star2").attr("src", "./common/image/fullStar.png")
			$("#empty-star3").attr("src", "./common/image/emptyStar.png")
			$("#empty-star4").attr("src", "./common/image/emptyStar.png")
			$("#empty-star5").attr("src", "./common/image/emptyStar.png")

			$("#reviewRating").val(2)
		})
	})
	$(document).ready(function() {
		$("#empty-star3").click(function() {
			$("#empty-star1").attr("src", "./common/image/fullStar.png")
			$("#empty-star2").attr("src", "./common/image/fullStar.png")
			$("#empty-star3").attr("src", "./common/image/fullStar.png")
			$("#empty-star4").attr("src", "./common/image/emptyStar.png")
			$("#empty-star5").attr("src", "./common/image/emptyStar.png")

			$("#reviewRating").val(3)
		})
	})
	$(document).ready(function() {
		$("#empty-star4").click(function() {
			$("#empty-star1").attr("src", "./common/image/fullStar.png")
			$("#empty-star2").attr("src", "./common/image/fullStar.png")
			$("#empty-star3").attr("src", "./common/image/fullStar.png")
			$("#empty-star4").attr("src", "./common/image/fullStar.png")
			$("#empty-star5").attr("src", "./common/image/emptyStar.png")

			$("#reviewRating").val(4)
		})
	})
	$(document).ready(function() {
		$("#empty-star5").click(function() {
			$("#empty-star1").attr("src", "./common/image/fullStar.png")
			$("#empty-star2").attr("src", "./common/image/fullStar.png")
			$("#empty-star3").attr("src", "./common/image/fullStar.png")
			$("#empty-star4").attr("src", "./common/image/fullStar.png")
			$("#empty-star5").attr("src", "./common/image/fullStar.png")

			$("#reviewRating").val(5)
		})
	})
	
	function validation () {
		var rating = $("#reviewRating").val()
		var reviewContent = $("#reviewContent").val()
		
		console.log(rating);
		console.log(reviewContent);
		
		if (rating == 0) {
			$("#review-rating-tag").css("display", "block");
			return false;
		}
		
		if (reviewContent == "") {
			$("#reviewContent-tag").css("display", "block");
			return false;
		}
	}
</script>
</head>
<body>
	
	<div class="container">
		<div class="container-box">
			<span id="category-tag">${requestScope.ownerActivityData.category}</span>
			<span class="material-symbols-outlined heart-icon-fav" data-bs-toggle="tooltip" data-bs-placement="top" title="좋아요"> favorite </span>
			<span class="material-symbols-outlined star-icon-fav" data-bs-toggle="tooltip" data-bs-placement="top" title="즐겨찾기"> star </span>

			<h2 id="activityName-tag">${requestScope.ownerActivityData.activityName}</h2>
			
			<div class="location-container" style="font-size: 17px;">
				<span>장소 : ${requestScope.ownerActivityData.location} </span>
				<span> ${requestScope.ownerActivityData.locationDetail}</span>
			</div>
			<div class="price-container">
				<span>비용 : </span>
				<fmt:formatNumber value="${requestScope.ownerActivityData.price}" pattern="###,###" />
				원
			</div>
			<span id="activityNumber-tag">참여 인원 : ${requestScope.ownerActivityData.activityNumber}명</span>
			<div class="openTime-closeTime-box" style="font-size: 17px;">
				<span>오픈 시간 : </span>
				<span>${requestScope.ownerActivityData.openTime} ~ </span> 
				<span>${requestScope.ownerActivityData.closeTime}</span>
			</div>
			
			<div class="userid-postedDate-container" style="font-size: 17px;">
				<span>${requestScope.ownerActivityData.userId}</span>
				<span>${requestScope.ownerActivityData.postedDate}</span>
				<span id="readHit-teg">조회수 : ${requestScope.ownerActivityData.readHit}</span>
			</div>
		</div>
		<c:set var="index" value="0"></c:set>
		<c:set var="contentIndex" value="0"></c:set>
		<c:set var="imgIndex" value="0"></c:set>

		<div class="event-box">
			<h4 id="event-tag">이벤트 : ${requestScope.ownerActivityData.event}</h4>
		</div>
		<div class="contents-container">
			<c:forEach var="loop" begin="0" end="${requestScope.ownerActivityData.totalRacodeCount - 1}">
				<c:catch var="contentException">
					<c:if test="${not empty requestScope.ownerActivityData.contentList}">
						<c:if test="${requestScope.ownerActivityData.contentList.get(contentIndex).getOrder() == index}">
							<pre>${requestScope.ownerActivityData.contentList.get(contentIndex).getContent()}</pre>
							<c:if test="${requestScope.ownerActivityData.contentList.size() > contentIndex }">
								<c:set var="contentIndex" value="${contentIndex + 1}"></c:set>
							</c:if>
						</c:if>
					</c:if>
				</c:catch>
				<c:catch var="imageException">
					<c:if test="${not empty requestScope.ownerActivityData.imageList}">
						<c:if test="${requestScope.ownerActivityData.imageList.get(imgIndex).getOrder() == index}">
							<img class="contents-image" src="${pageContext.request.contextPath}/upload/${requestScope.ownerActivityData.imageList.get(imgIndex).getImage()}">
							<c:if test="${requestScope.ownerActivityData.imageList.size() > imgIndex }">
								<c:set var="imgIndex" value="${imgIndex + 1}"></c:set>
							</c:if>
						</c:if>
					</c:if>
				</c:catch>
				<c:if test="${not empty contentException}"></c:if>
				<c:if test="${not empty imageException}"></c:if>
				<c:set var="index" value="${index + 1}"></c:set>
			</c:forEach>
			<div class="bottom-content-container"></div>
		</div>
		<hr>
		<div class="back-btn-box">
			<button class="btn btn-secondary back-btn" value="돌아가기" onclick="backPage();">목록</button>
		</div>
		<div>
			<div class="totalRating-box">
				<c:set var="totalRatingCount" value="0"></c:set>
				<c:set var="totalScore" value="0"></c:set>
				<c:forEach var="ratingCount" items="${requestScope.totalRatings}">
					<c:set var="totalRatingCount" value="${totalRatingCount + ratingCount.totalRating}"></c:set>
					<c:set var="totalScore" value="${totalScore + (ratingCount.totalRating * ratingCount.rating)}"></c:set>
				</c:forEach>
				<div class="avg-rating-box">
					<c:set var="averageRating" value="${totalScore / totalRatingCount}" />
					<span id="avg-value"><fmt:formatNumber value="${averageRating}" maxFractionDigits="1" /></span>
					<div>
						<c:forEach var="rating" begin="1" end="${averageRating}">
							<img src="${pageContext.request.contextPath}/upload/star.png" id="star-icon-tag">
						</c:forEach>
					</div>
				</div>
				<div class="ratings-box">
					<c:forEach var="rating" items="${requestScope.totalRatings}">
						<div><span id="rating-score">${rating.rating}점</span>   <span>${rating.totalRating}개</span><br></div>
					</c:forEach>
				</div>
			</div>
		</div>
		<c:if test="${not empty sessionScope.loginfo || not empty sessionScope.loginfoOwner}">
			<div class="form-container">
				<form action="<%=withFormTag%>" method="post">
					<input type="hidden" id="command" name="command" value="reviewInsert">
					<input type="hidden" id="activityId" name="activityId" value="${requestScope.ownerActivityData.activityId}">
					<c:if test="${not empty sessionScope.loginfo}">
						<input class="form-control login-info-tag" type="text" value="${sessionScope.loginfo.username}" disabled>
						<input type="hidden" id="userName" name="userName" value="${sessionScope.loginfo.userId}">
					</c:if>
					<c:if test="${not empty sessionScope.loginfoOwner}">
						<input class="form-control login-info-tag" type="text" value="${sessionScope.loginfoOwner.userName}" disabled>
						<input type="hidden" id="userName" name="userName" value="${sessionScope.loginfoOwner.userId}">
					</c:if>
					<div class="review-star-select-box">
						<c:forEach var="loop" begin="1" end="5">
							<img id="empty-star${loop}" src="${pageContext.request.contextPath}/common/image/emptyStar.png">
						</c:forEach>
						<p id="review-rating-tag">리뷰 평점은 필수 입력 사항입니다.</p>
						<input type="hidden" id="reviewRating" name="reviewRating" value="">
					</div>
					<div class="input-group mb-3">
						<textarea class="form-control review-textarea-tag" id="reviewContent" name="reviewContent" placeholder="리뷰 작성 0자 ~ 300자"></textarea>
						<p id="reviewContent-tag">리뷰 내용은 필수 입력 사항입니다.</p>
					</div>
					<div class="submit-box">
						<button type="submit" class="btn btn-dark" onclick="return validation();">등록</button>
					</div>
				</form>
			</div>
		</c:if>


		<c:forEach var="reviewBean" items="${requestScope.reviewData}">
			<div class="review-box">
				<div class="review-head">
					<span>${reviewBean.userName}</span>
					<c:forEach var="rating" begin="1" end="${reviewBean.rating}">
						<img src="${pageContext.request.contextPath}/upload/star.png" id="review-star-icon-tag">
					</c:forEach>
					<span></span>
				</div>
				<p>${reviewBean.reviewContent}</p>
				<span>${reviewBean.postedDate}</span>
			</div>
		</c:forEach>
		
		<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
	</div>
</body>
</html>