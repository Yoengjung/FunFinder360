<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/activityCSS/activitesListCSS.css">
<title>회원 활동 데이터</title>
<script type="text/javascript">
	$(document).ready(function() {
		console.log("<%=notWithFormTag%>favoritesInsert?userId=${sessionScope.loginfo.userId}")
		loadFavorites();
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
	
	function loadFavorites() {
	    $.ajax({
	        type: "GET",
	        url: "<%=notWithFormTag%>favoritesInsert",
	        data: 'userId=' + '${sessionScope.loginfo.userId}',
	        dataType: "json", // 응답 데이터 타입
	        success: function (result, status) {
	        	
	        },
	        error: function(xhr, status, error) {
	            console.error("서버 오류: " + error);
	        }
	    });
	}
	
	function addToFavorites(activityId, userId) {
	    $.ajax({
	        type: "POST",
	        url: '<%=notWithFormTag%>favoritesInsert', // 즐겨찾기 추가 엔드포인트 URL
	        data: { activityId: activityId, userId: userId, activityCheck: 0}, // 활동 ID 또는 필요한 정보를 전송
	        success: function(response, status, xhr) {
	            if (xhr.status === 200) {
	                // 즐겨찾기 추가가 성공한 경우
	                // 아이콘 색상 변경 등의 작업을 수행
	                
	                console.log($(`Favorites-icon-\${activityId}`));
	                $(`.Favorites-icon-\${activityId}`).css('color', 'yellow');
	            } else {
	                // 실패 처리
	                alert("즐겨찾기 추가 실패");
	            }
	        },
	        error: function(xhr, status, error) {
	            // 에러 처리
	            if (xhr.status === 500) {
	                alert("서버 오류");
	            } else {
	                alert("알 수 없는 오류 발생");
	            }
	        }
	    });
	}

</script>
</head>
<body>
	<div class="container">
		<h2>개인 활동</h2>
		<table>
			<thead></thead>
			<tbody>
				<c:set var="colsu" value="4"></c:set>
				<c:forEach var="bean" items="${requestScope.personalActivity}"
					varStatus="loopStatus">
					<c:if test="${loopStatus.index mod colsu == 0}">
						<tr>
					</c:if>
					<td>
						<div class="card">
							<div class="readHit-box">
								<span class="material-symbols-outlined readHit-icon">visibility</span>${bean.readHit}
								<c:if test="${not empty sessionScope.loginfo}">
									<span class="material-symbols-outlined Favorites-icon-${bean.activityId} Favorites-icon" onClick="addToFavorites(${bean.activityId}, '${sessionScope.loginfo.userId }')">star</span>
								</c:if>
							</div>
							<div class="card-header">${bean.activityName}</div>
							<div class="card-body">
								<a
									href="<%=notWithFormTag%>activityDetail&activityId=${bean.activityId}">
									<img alt="${bean.activityName}"
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
					<input type="hidden" name="command" value="activitesList">
					<select id="mode" name="mode" class="form-select">
						<option value="all" selected="selected">--- 선택해 주세요 ---
						<option value="activityName">활동 제목
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
						class="btn btn-success form-control-sm search-btn" onclick="">검색</button>
				</form>
			</div>
		</div>
		<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
	</div>
</body>
</html>
