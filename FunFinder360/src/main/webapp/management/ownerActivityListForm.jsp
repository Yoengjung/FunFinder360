<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/questionCSS/questionListFormCSS.css" type="text/css">
<meta charset="UTF-8">
<title>업주 활동 데이터</title>
<style>
.container {
	margin-top: 130px;
	width: 100%;
	height: 100%;
	max-width: 1460px;
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
	$(document).ready(function(){
		var questionOptionList = $('#mode option');
		for (var i = 0; i < questionOptionList.length; i++) {
			if (questionOptionList[i].value == '${requestScope.pageInfo.mode}'){
				questionOptionList[i].selected = true;
			}
		}
		$('#keyowrd').val('${requestScope.pageInfo.keyword}');
		
		$("#mode").change(function(){
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
		<h2>업주 활동 데이터</h2>
		<table class="table">
			<thead class="table-dark">
				<tr>
					<th class="table-head-box activityId-head">순번</th>
					<th class="table-head-box activitiyName-head">활동 제목</th>
					<th class="table-head-box userid-head">등록자명</th>
					<th class="table-head-box postedDate-head">등록일자</th>
					<th class="table-head-box readhit-head">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.ownerActivity}">
					<tr>
						<td class="table-body-box">${bean.activityId}</td>
						<td class="table-body-box title-box">
							<a href="<%=notWithFormTag%>ownerActivityDetail&activityId=${bean.activityId}">${bean.activitiyName}</a>
						</td>
						<td class="table-body-box">${bean.userid}</td>
						<td class="table-body-box">${bean.postedDate}</td>
						<td class="table-body-box">${bean.readHit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="search-container">
			<div class="search-in-container">
				<form name="search-form" action="<%=withFormTag%>" method="get" class="search-form">
					<input type="hidden" name="command" value=ownerActivityList>
					<select id="mode" name="mode" class="form-select">
						<option value="all" selected="selected">--- 선택해 주세요 ---
						<option value="activitiyName">활동 제목
						<option value="userid">등록자 이름
					</select>
					<div class="input-group">
						<input class="keyword-input-box form-control" type="text" name="keyword" id="keyword" placeholder="키워드 입력" autocomplete="off">
					</div>
					<button type="submit" class="btn btn-success form-control-sm search-btn" onclick="">검색</button>
				</form>
			</div>
		</div>
	</div>
	<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
</body>
</html>