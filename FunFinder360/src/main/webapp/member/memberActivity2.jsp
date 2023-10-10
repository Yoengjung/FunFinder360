<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminCSS/personalUserTotalDetailCSS.css">
<meta charset="UTF-8">
<title>내 활동</title>

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
	
function button_event(event) {
	  event.preventDefault();
	  const activityId = $("#activityId-tag").val();
	  if (confirm("정말 삭제하시겠습니까??")) {
	    var url = "<%=notWithFormTag%>
	ownerDeleteActivity&activityId="
					+ activityId;
			var xhr = new XMLHttpRequest();

			xhr.open("GET", url, true);

			xhr.onload = function() {
				if (xhr.status === 200) {
					window.location.reload();
				} else {
					console.error("요청 실패");
				}
			};

			xhr.send();
		} else {
			return;
		}
	}
</script>
</head>
<body>
	<div class="container1">
		<div class="container2" style="padding: 0px 150px">
			<h2>나의 활동</h2>
			<table class="table">
				<thead class="table-dark">
					<tr>
						<th class="table-head-box no-head" style="width: 250px">활동 제목</th>
						<th class="table-head-box title-head" style="width: 160px;">카테고리</th>
						<th class="table-head-box registrant-head" style="width: 150px;">위치</th>
						<th class="table-head-box posted-date-head" style="width: 220px">상세주소</th>
						<th class="table-head-box readhit-head" style="width: 70px;">조회수</th>
						<th class="table-head-box event-head" align="center">이벤트</th>
						<th class="table-head-box date-head" style="width: 175px;">등록일</th>
						<th class="table-head-box" style="width: 70px;">옵션</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ownerActivity" items="${requestScope.ownerActivityList}">
						<input type="hidden" value="${ownerActivity.activityId}" id="activityId-tag">
						<tr>
							<td class="table-body-box">
								<c:choose>
									<c:when test="${fn:length(ownerActivity.activitiyName) >= 10}">
										<a href="<%=notWithFormTag%>ownerMemberActivityDetail&activityId=${ownerActivity.activityId}" style="color: blue; text-decoration: underline;">${fn:substring(ownerActivity.activitiyName, 0, 10)}...</a>
									</c:when>
									<c:otherwise>
										<a href="<%=notWithFormTag%>ownerMemberActivityDetail&activityId=${ownerActivity.activityId}" style="color: blue; text-decoration: underline;">${ownerActivity.activitiyName}</a>
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
							<td class="table-body-box" align="center">${ownerActivity.readHit}</td>
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
							<td class="table-body-box">
								<button type="button" onclick="button_event(event);">삭제</button>
								<%-- <a href="<%=notWithFormTag%>personalDeleteActivity&activityId=${personalActivity.activityId}" onclick="return button_event()">삭제</a> --%>
							</td>
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
	</div>
	<%@ include file="../common/footer.jsp"%>