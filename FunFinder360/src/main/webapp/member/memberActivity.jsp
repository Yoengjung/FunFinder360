<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminCSS/personalUserTotalDetailCSS.css">
<meta charset="UTF-8">
<title>개인 활동</title>

<script type="text/javascript">
	$(document)
			.ready(
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
		    var url = "<%=notWithFormTag%>personalDeleteActivity&activityId="+ activityId;
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
						<th class="table-head-box no-head">활동 제목</th>
						<th class="table-head-box title-head" align="center">카테고리</th>
						<th class="table-head-box registrant-head">위치</th>
						<th class="table-head-box posted-date-head">상세주소</th>
						<th class="table-head-box readhit-head" style="width: 70px;">조회수</th>
						<th class="table-head-box">등록일</th>
						<th class="table-head-box">옵션</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="personalActivity" items="${requestScope.personalActivityList}">
						<input type="hidden" value="${personalActivity.activityId}" id="activityId-tag">
						<tr>
							<td class="table-body-box">
								<c:choose>
									<c:when test="${fn:length(personalActivity.activityName) >= 10}">
										<a class="a-tag-1" href="<%=notWithFormTag%>personalMemberActivityDetail&activityId=${personalActivity.activityId}">${fn:substring(personalActivity.activityName, 0, 10)}...</a>
									</c:when>
									<c:otherwise>
										<a class="a-tag-1" href="<%=notWithFormTag%>personalMemberActivityDetail&activityId=${personalActivity.activityId}">${personalActivity.activityName}</a>
									</c:otherwise>
								</c:choose>
							</td>
							<td class="table-body-box title-box">${personalActivity.category}</td>
							<td class="table-body-box">${personalActivity.location}</td>
							<td class="table-body-box">
								<c:choose>
									<c:when test="${fn:length(personalActivity.locationDetail) >= 10}">
										<a href="<%=notWithFormTag%>personalMemberActivityDetail&activityId=${personalActivity.activityId}">${fn:substring(personalActivity.locationDetail, 0, 10)}...</a>
									</c:when>
									<c:otherwise>
										<a href="<%=notWithFormTag%>personalMemberActivityDetail&activityId=${personalActivity.activityId}">${personalActivity.locationDetail}</a>
									</c:otherwise>
								</c:choose>
							</td>
							<td class="table-body-box">${personalActivity.readHit}</td>
							<td class="table-body-box">${personalActivity.postedDate}</td>
							<td class="table-body-box">
								<button type="button" onclick="button_event(event);">삭제</button>
								<a href="<%=notWithFormTag%>updatePersonalActivity&activityId=${personalActivity.activityId}"><button type="Button">수정</button></a>
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
							<option value="postedDate">등록일
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
	</div>