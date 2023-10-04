<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="./../common/bootstrap.jsp" %>
<%@ include file="../common/common.jsp"%>




<%
	MemberPersonalUserDao dao = new MemberPersonalUserDao();
	List<MemberDetail> ls = MemberPersonalUserDao.listAll() ; 
%>
	




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 </title>
<style>
.container {
	margin-top: 130px;
	width: 100%;
	height: 100%;
	background-color: red;
}
</style>
</head>
<body>
<h3> 회원 데이터 목록</h3>
<table border="1">
<% if(list.size() == 0){%>
		<tr><td>목록이 없습니다.</td></tr>
<%} else {%>
	<tr><th>아이디</th><th>비밀 번호</th><th>이름</th><th>생년월일</th><th>전화번호</th><th>이메일</th></tr>
	<% for(MemberPersonalUser member : ls) {%>
	<tr>
		<td><%=request.getAttribute()  %></td><td><%= %></td><td><%= %></td><td><%= %></td><td><%= %></td><td><%= %></td>
	</tr>
<% } 
 }
%>	
</table>	
</body>
</html>