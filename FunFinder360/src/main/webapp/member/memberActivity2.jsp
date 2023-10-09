<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminCSS/personalUserTotalDetailCSS.css">
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.container {
	margin-top: 130px;
	width: 100%;
	height: 100%;
}

.container h2 {
	margin-bottom: 30px;
}

.table-head-box {
	text-align: center;
}

.table-body-box {
	text-align: center;
}

.no-head {
	width: 15%;
}

.title-head {
	text-align: left;
	width: 15%; /* 50% -> 20%로 수정함 */
}

.registrant-head {
	width: 15%;
}

.posted-date-head {
	width: 15%;
}

.readhit-head {
	width: 10%;
}
.event-head {
	width: 15% 
}
.date-head {
	width: 25%
}

.title-box {
	text-align: left;
}

.title-box a {
	color: #3366FF;
}

.title-box a:hover {
	text-decoration: underline;
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
$(document).ready(
	function() {
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
</head>
<body>
	<div class="container">
		<h2>내가 올린 활동들 일까?(기업)</h2>
		<table class="table">
			<thead class="table-dark">
				<tr>
					<th class="table-head-box no-head">활동 제목</th>
					<th class="table-head-box title-head">카테고리</th>
					<th class="table-head-box registrant-head">위치</th>
					<th class="table-head-box posted-date-head">상세주소</th>
					<th class="table-head-box readhit-head">조회수</th>
					<th class="table-head-box event-head">이벤트</th>
					<th class="table-head-box date-head">등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ownerActivity"
					items="${requestScope.ownerActivityList}">
					<tr>
						<td class="table-body-box">
							<c:choose>
								<c:when test="${fn:length(ownerActivity.activitiyName) >= 10}">
				                    <a href="<%=notWithFormTag%>ownerMemberActivityDetail&activityId=${ownerActivity.activityId}">${fn:substring(ownerActivity.activitiyName, 0, 10)}...</a>
				                </c:when>
								<c:otherwise>
				                    <a href="<%=notWithFormTag%>ownerMemberActivityDetail&activityId=${ownerActivity.activityId}">${ownerActivity.activitiyName}</a>
				                </c:otherwise>
							</c:choose>
						</td>
						<td class="table-body-box title-box">${ownerActivity.category}</td>
						<td class="table-body-box">${ownerActivity.location}</td>
						<td class="table-body-box">
							<c:choose>
								<c:when test="${fn:length(ownerActivity.locationDetail) >= 10}">
				                    <a href="<%=notWithFormTag%>ownerMemberActivityDetail&activityId=${ownerActivity.activityId}">${fn:substring(ownerActivity.locationDetail, 0, 10)}...</a>
				                </c:when>
								<c:otherwise>
				                    <a href="<%=notWithFormTag%>ownerMemberActivityDetail&activityId=${ownerActivity.activityId}">${ownerActivity.locationDetail}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td class="table-body-box">${ownerActivity.readHit}</td>
						<td class="table-body-box">
							<c:choose>
								<c:when test="${fn:length(ownerActivity.event) >= 10}">
				                    <a href="<%=notWithFormTag%>ownerMemberActivityDetail&activityId=${ownerActivity.activityId}">${fn:substring(ownerActivity.event, 0, 10)}...</a>
				                </c:when>
								<c:otherwise>
				                    <a href="<%=notWithFormTag%>ownerMemberActivityDetail&activityId=${ownerActivity.activityId}">${ownerActivity.event}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td class="table-body-box">${ownerActivity.postedDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="search-container">
			<div class="search-in-container">
				<form name="search-form" action="<%=withFormTag%>" method="get" class="search-form">
					<input type="hidden" name="command" value="memberActivity">
					<select id="mode" name="mode" class="form-select">
						<option value="all" selected="selected">--- 선택해 주세요 ---
						<option value="activityName">활동 제목
						<option value="category">카테고리
						<option value="location">위치
						<option value="locationDetail">상세주소
						<option value="readHit">조회수
						<option value="event">이벤트
						<option value="postedDate">등록일
					</select>
					<div class="input-group">
						<input class="keyword-input-box form-control" type="text" name="keyword" id="keyword" placeholder="키워드 입력" autocomplete="off">
					</div>
					<button type="submit" class="btn btn-default form-control-sm search-btn" onclick="">검색</button>
				</form>
			</div>
		</div>
		<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
	</div>
</body>
</html>