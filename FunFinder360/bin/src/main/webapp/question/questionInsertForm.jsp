<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/questionCSS/questionInsertFormCSS.css" type="text/css">
<title>문의 작성</title>
<script type="text/javascript">
		$(document).ready(function(){
	  		$('#postedDate').datepicker({dateFormat: "yy/mm/dd"});   	  	 	
		});
		
		function validCheck(){
			var title = $('#title').val();
  			if(title.length < 2 || title.length > 400){
  				alert('제목은 2글자 이상 400글자 이하이어야 합니다.');
  				$('#title').focus() ;
  				return false ;
  			}
  			
  			var content = $('#content').val();
  			if(content.length < 5 || content.length > 3000){
  				alert('설명은 5글자 이상 3000글자 이하이어야 합니다.');
  				$('#content').focus() ;
  				return false ;
  			}
  			
			var postedDate = $('#postedDate').val();
			var regex = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/ ;
			var result = regex.test(postedDate);
			
			if(result == false){
				swal('등록일은 반드시 yyyy/mm/dd 형식으로 입력해 주세요.');  				
				return false ;
			}   			
		}
  	</script>

</head>
<body>
	<div class="container">
		<h2>문의 작성</h2>
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="questionsInsert">
			<div class="input-group">
				<span class="input-group-text">제목</span>
				<input class="form-control" type="text" id="title" name="title" placeholder="2자 이상 400자 이하">
			</div>
			<div class="input-group">
				<span class="input-group-text">등록자명</span>
				<c:if test="${sessionScope.loginfo.username != null}">
					<input class="form-control" type="text" id="fakeuserId" name="fakeuserId" disabled="disabled" value="${sessionScope.loginfo.username}">
					<input type="hidden" id="userId" name="userId" value="${sessionScope.loginfo.username}">
				</c:if>
				<c:if test="${sessionScope.loginfoOwner.userName != null}">
					<input class="form-control" type="text" id="fakeuserId" name="fakeuserId" disabled="disabled" value="${sessionScope.loginfoOwner.userName}">
					<input type="hidden" id="userId" name="userId" value="${sessionScope.loginfoOwner.userName}">
				</c:if>
			</div>
			<div class="input-group content-container">
				<span class="input-group-text">설명</span>
				<textarea class="form-control" id="content" name="content" placeholder="설명 입력"></textarea>
			</div>

			<br />
			<div class="btn-container">
				<div class="btn-box">
					<div id="">
						<button type="submit" class="btn btn-primary" onclick="return validCheck();">등록</button>
					</div>
					<div id="reset-btn">
						<button type="reset" class="btn btn-danger">초기화</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
