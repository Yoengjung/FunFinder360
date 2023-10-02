<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<title>회원 활동 데이터</title>
<style>
.container {
	margin-top: 130px;
	width: 100%;
	height: 100%;
	max-width: 1460px;
}

.card-img-top {
	width: 100%;
	height: 100%;
}

.card-footer {
	display: flex;
	flex-direction: column;
}

.card {
	height: 400px;
	margin: 0px 10px 10px 10px;
}

img {
	width: 300px;
	height: 180px;
}

.paging-container {
	margin-top: 30px;
}

.input-group {
	width: 300px;
}

.form-select {
	width: 200px;
}

.search-form {
	display: flex;
	flex-direction: row;
	justify-content: center;
}

.keyword-input-box {
	margin: 0 20px;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		var questionOptionList = $('#mode option');
		for (var i = 0; i < questionOptionList.length; i++) {
			if (questionOptionList[i].value == '${requestScope.pageInfo.mode}') {
				questionOptionList[i].selected = true;
			}
		}
		$('#keyowrd').val('${requestScope.pageInfo.keyword}');

		$("#mode").change(function() {
			if ($(this).val() != 'all') {
				$('#keyword').attr('disabled', false);
			} else {
				$('#keyword').val('');
				$('#keyword').attr('disabled', true);
			}
		});
	});
</script>
<style type="text/css">
body {
	height: 100vh;
}

.container {
	position: absolute;
	width: 100%;
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
	text-align: center;
	margin-top: 30px;
	margin-bottom: 30px;
	padding: 50px 0 50px 0;
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
}

.back-btn {
	position: relative;
	left: 50%;
	transform: translateX(-50%);
}

.readHit-icon {
	position: relative;
	margin-right: 3px;
}

.readHit-box {
	display: flex;
	flex-direction: row;
	vertical-align: center;
	padding: 0 5px 0 5px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>회원 활동 데이터</h2>
		<table>
			<thead></thead>
			<tbody>
				<c:set var="colsu" value="4"></c:set>
				<c:forEach var="bean" items="${requestScope.personalActivity}" varStatus="loopStatus">
					<c:if test="${loopStatus.index mod colsu == 0}">
						<tr>
					</c:if>
					<td>
						<div class="card">
							<div class="readHit-box">
								<span class="material-symbols-outlined readHit-icon"> visibility </span>
								${bean.readHit}
							</div>
							<div class="card-header">${bean.activityName}</div>
							<div class="card-body">
								<a href="<%=notWithFormTag%>activityDetail&activityId=${bean.activityId}">
									<img alt="${bean.activityName}" src="${pageContext.request.contextPath}/upload/${bean.image}">
								</a>
							</div>
							<div class="card-footer">
								<span>등록자 : ${bean.userId}</span>
								<span>카테고리: ${bean.category}</span>
								<span>주소: ${bean.location}</span>
								상세 주소:
								<c:choose>
									<c:when test="${fn:length(bean.locationDetail) >= 10}">${fn:substring(bean.locationDetail, 0 , 10)}...</c:when>
									<c:otherwise>
                                          ${bean.locationDetail}
                                    </c:otherwise>
								</c:choose>
							</div>
						</div>
					</td>
				</c:forEach>
			</tbody>
		</table>
		<div class="search-container">
			<div class="search-in-container">
				<form name="search-form" action="<%=withFormTag%>" method="get" class="search-form">
					<input type="hidden" name="command" value="activitesList">
					<select id="mode" name="mode" class="form-select">
						<option value="all" selected="selected">--- 선택해 주세요 ---
						<option value="activityName">활동 제목
						<option value="category">카테고리
						<option value="location">주소
						<option value="locationdetail">상세주소
					</select>
					<div class="input-group">
						<input class="keyword-input-box form-control" type="text" name="keyword" id="keyword" placeholder="키워드 입력" autocomplete="off">
					</div>
					<button type="submit" class="btn btn-success form-control-sm search-btn" onclick="">검색</button>
				</form>
			</div>
		</div>
		<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
	</div>

</body>
</html>
