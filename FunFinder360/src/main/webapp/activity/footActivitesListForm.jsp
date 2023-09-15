<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>
.container {
	position: absolute;
	top: 130px;
	width: 100%;
	height: 200%;
	max-width: none;
	background-color: red;
}

.activites-container {
	width: 80%;
	height: 80%;
	position: absolute;
	top: 100px;
	left: 50%;
	transform: translateX(-50%);
	background-color: blue;
}

.activites-container h2 {
	text-align: center;
}

.activites-item-container {
	width: 100%;
	height: 100%;
	top: 10px;
	display: flex;
	position: relative;
	background-color: green;
	position: relative;
}

.activity-item {
	position: relative;
	width: 400px;
	height: 200px;
	border: 1px solid black;
	width: 400px;
}

tbody {
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
}
</style>
<body>
	<div class="container">
		<div class="activites-container">
			<h2>음식 - 요리</h2>
			<div class="activites-item-container">
				<table>
					<thead></thead>
					<tbody>
						<tr>
							<td>
								<div class="activity-item">asdf</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>