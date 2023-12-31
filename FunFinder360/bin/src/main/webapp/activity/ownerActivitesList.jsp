<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업주 활동</title>
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
	height: 475px;
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
		<table>
			<thead></thead>
			<tbody>
				<c:set var="colsu" value="4"></c:set>
				<c:forEach var="bean" items="${requestScope.activity}"
					varStatus="loopStatus">
					<c:if test="${loopStatus.index mod colsu == 0}">
						<tr>
					</c:if>

					<td>
						<div class="card">
							<div class="card-header">${bean.activityName}</div>
							<div class="card-body">
								<a href="#"> <img alt="${bean.activityName}"
									src="${pageContext.request.contextPath}/upload/${bean.image}">
								</a>
							</div>
							<div class="card-footer">
								<span>등록자 : ${bean.userId}</span> <span>카테고리:
									${bean.category}</span> <span>주소: ${bean.location}</span> 상세 주소:
								<c:choose>
									<c:when test="${fn:length(bean.locationDetail) >= 10}">${fn:substring(bean.locationDetail, 0 , 10)}...</c:when>
									<c:otherwise>
                                          ${bean.locationDetail}
                                    </c:otherwise>
								</c:choose>
								<span>가격 : ${bean.price}</span> <span>오픈 :
									${bean.openTime} ~ ${bean.closeTime}</span> <span>조회수:
									${bean.readHit}</span> <span>등록일 : ${bean.postedDate}</span>
							</div>
						</div>
					</td>
				</c:forEach>
			</tbody>
		</table>
		<div class="search-container">
			<div class="search-in-container">
				<form name="search-form" action="<%=withFormTag%>" method="get"
					class="search-form">
					<input type="hidden" name="command" value="OwnerActivitesList">
					<select id="mode" name="mode" class="form-select">
						<option value="all" selected="selected">--- 선택해 주세요 ---
						<option value="activityName">활동 제목
						<option value="userid">등록자 이름
						<option value="category">카테고리
						<option value="location">주소
						<option value="locationdetail">상세주소
					</select>
					<div class="input-group">
						<input class="keyword-input-box form-control" type="text"
							name="keyword" id="keyword" placeholder="키워드 입력"
							autocomplete="off">
					</div>
					<button type="submit"
						class="btn btn-default form-control-sm search-btn" onclick="">검색</button>
				</form>
			</div>
		</div>
	</div>
	<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
</body>
</html>
