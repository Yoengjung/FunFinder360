<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/activityCSS/personalActivityDetailFormCSS.css">
<title>활동 상세 보기</title>
<script>
	var tooltipTriggerList = [].slice.call(document
			.querySelectorAll('[data-bs-toggle="tooltip"]'))
	var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
		return new bootstrap.Tooltip(tooltipTriggerEl)
	})
	function backPage() {
		window.history.back();
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

	function validation() {
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
			<span id="category-tag">${requestScope.personalActivityData.category}</span>
			<h2 id="activityName-tag">${requestScope.personalActivityData.activityName}</h2>
			<div class="location-container" style="font-size: 17px;">
				<span>장소 : ${requestScope.personalActivityData.location} </span>
				<span> ${requestScope.personalActivityData.locationDetail}</span>
			</div>
			<div class="cost-container">
				<span>비용 : </span>
				<fmt:formatNumber value="${requestScope.personalActivityData.cost}" pattern="###,###" />
				원
			</div>
			<span id="activityNumber-tag">참가 인원 : ${requestScope.personalActivityData.activityNumber}명</span>
			<div class="rating-container">
				<div class=star-box>
					<c:forEach var="rating" begin="1" end="${requestScope.personalActivityData.rating}">
						<img src="${pageContext.request.contextPath}/upload/star.png" id="star-icon-tag">
					</c:forEach>
				</div>
			</div>
			<div class="userid-postedDate-container" style="font-size: 17px;">
				<span>${requestScope.personalActivityData.userId}</span>
				<span>${requestScope.personalActivityData.postedDate}</span>
				<span id="readHit-teg">조회수 : ${requestScope.personalActivityData.readHit}</span>
			</div>
		</div>
		<c:set var="index" value="0"></c:set>
		<c:set var="contentIndex" value="0"></c:set>
		<c:set var="imgIndex" value="0"></c:set>

		<div class="contents-container">
			<c:forEach var="loop" begin="0" end="${requestScope.personalActivityData.totalRacodeCount - 1}">
				<c:catch var="contentException">
					<c:if test="${not empty requestScope.personalActivityData.contentList}">
						<c:if test="${requestScope.personalActivityData.contentList.get(contentIndex).getOrder() == index}">
							<pre>${requestScope.personalActivityData.contentList.get(contentIndex).getContent()}</pre>
							<c:if test="${requestScope.personalActivityData.contentList.size() > contentIndex }">
								<c:set var="contentIndex" value="${contentIndex + 1}"></c:set>
							</c:if>
						</c:if>
					</c:if>
				</c:catch>
				<c:catch var="imageException">
					<c:if test="${not empty requestScope.personalActivityData.imageList}">
						<c:if test="${requestScope.personalActivityData.imageList.get(imgIndex).getOrder() == index}">
							<img class="contents-image" src="${pageContext.request.contextPath}/upload/${requestScope.personalActivityData.imageList.get(imgIndex).getImage()}">
							<c:if test="${requestScope.personalActivityData.imageList.size() > imgIndex }">
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
					<input type="hidden" id="activityId" name="activityId" value="${requestScope.personalActivityData.activityId}">
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
			<div class="review-box" style="left: 50%; transform: translate(-50%); position: relative; width: 70%;">
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