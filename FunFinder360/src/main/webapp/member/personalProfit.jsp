<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminCSS/personalUserTotalDetailCSS.css">
<meta charset="UTF-8">
<title>개인 수익 활동</title>

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
	width: 20%;
}

.title-head {
	text-align: left;
	width: 15%; /* 50% -> 20%로 수정함 */
}

.registrant-head {
	width: 20%;
}

.posted-date-head {
	width: 20%;
}

.readhit-head {
	width: 10%;
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

#chartdiv {
	width: 100%;
	height: 500px;
}
</style>

<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>

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
	
$(document).ready(function() {
	const currentDate = new Date();

	// 각 날짜 계산
	const date7 = new Date(currentDate);
	date7.setDate(currentDate.getDate() - 1);
	const month7 = date7.getMonth() + 1;
	const day7 = date7.getDate();

	const date6 = new Date(currentDate);
	date6.setDate(currentDate.getDate() - 2); // 전전날
	const month6 = date6.getMonth() + 1;
	const day6 = date6.getDate();

	const date5 = new Date(currentDate);
	date5.setDate(currentDate.getDate() - 3);
	const month5 = date5.getMonth() + 1;
	const day5 = date5.getDate();

	const date4 = new Date(currentDate);
	date4.setDate(currentDate.getDate() - 4);
	const month4 = date4.getMonth() + 1;
	const day4 = date4.getDate();

	const date3 = new Date(currentDate);
	date3.setDate(currentDate.getDate() - 5);
	const month3 = date3.getMonth() + 1;
	const day3 = date3.getDate();

	const date2 = new Date(currentDate);
	date2.setDate(currentDate.getDate() - 6);
	const month2 = date2.getMonth() + 1;
	const day2 = date2.getDate();

	const date1 = new Date(currentDate);
	date1.setDate(currentDate.getDate() - 7); // 일주일 전
	const month1 = date1.getMonth() + 1;
	const day1 = date1.getDate();

	// 각 클래스에 텍스트 설정
	$(".day1").text(`\${month1}월 \${day1}일`);
	$(".day2").text(`\${month2}월 \${day2}일`);
	$(".day3").text(`\${month3}월 \${day3}일`);
	$(".day4").text(`\${month4}월 \${day4}일`);
	$(".day5").text(`\${month5}월 \${day5}일`);
	$(".day6").text(`\${month6}월 \${day6}일`);
	$(".day7").text(`\${month7}월 \${day7}일`);

	
	const graphData = [
	    ${requestScope.dateReadHitCount[0].readHit},
	    ${requestScope.dateReadHitCount[1].readHit},
	    ${requestScope.dateReadHitCount[2].readHit},
	    ${requestScope.dateReadHitCount[3].readHit},
	    ${requestScope.dateReadHitCount[4].readHit},
	    ${requestScope.dateReadHitCount[5].readHit},
	    ${requestScope.dateReadHitCount[6].readHit}
	];

	// 각 그래프에 대해 높이를 동적으로 설정합니다.
	$('.graph').each(function (index) {
	    const percentage = (graphData[index] / 1000) * 100;
	    const heightPercentage = percentage + '%';
	    $(this).find('.time').css('height', heightPercentage);
	});
})
</script>
<script>
am5.ready(function() {

	// Create root element
	// https://www.amcharts.com/docs/v5/getting-started/#Root_element
	var root = am5.Root.new("chartdiv");


	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	root.setThemes([
	  am5themes_Animated.new(root)
	]);


	// Create chart
	// https://www.amcharts.com/docs/v5/charts/xy-chart/
	var chart = root.container.children.push(am5xy.XYChart.new(root, {
	  panX: true,
	  panY: true,
	  wheelX: "panX",
	  wheelY: "zoomX",
	  pinchZoomX: true
	}));

	// Add cursor
	// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
	var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
	cursor.lineY.set("visible", false);


	// Create axes
	// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
	var xRenderer = am5xy.AxisRendererX.new(root, { minGridDistance: 30 });
	xRenderer.labels.template.setAll({
	  rotation: -90,
	  centerY: am5.p50,
	  centerX: am5.p100,
	  paddingRight: 15
	});

	xRenderer.grid.template.setAll({
	  location: 1
	})

	var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
	  maxDeviation: 0.3,
	  categoryField: "country",
	  renderer: xRenderer,
	  tooltip: am5.Tooltip.new(root, {})
	}));

	var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
	  maxDeviation: 0.3,
	  renderer: am5xy.AxisRendererY.new(root, {
	    strokeOpacity: 0.1
	  })
	}));


	// Create series
	// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
	var series = chart.series.push(am5xy.ColumnSeries.new(root, {
	  name: "Series 1",
	  xAxis: xAxis,
	  yAxis: yAxis,
	  valueYField: "value",
	  sequencedInterpolation: true,
	  categoryXField: "country",
	  tooltip: am5.Tooltip.new(root, {
	    labelText: "{valueY}"
	  })
	}));

	series.columns.template.setAll({ cornerRadiusTL: 5, cornerRadiusTR: 5, strokeOpacity: 0 });
	series.columns.template.adapters.add("fill", function(fill, target) {
	  return chart.get("colors").getIndex(series.columns.indexOf(target));
	});

	series.columns.template.adapters.add("stroke", function(stroke, target) {
	  return chart.get("colors").getIndex(series.columns.indexOf(target));
	});
	
	const graphData = [
	    ${requestScope.dateReadHitCount[0].readHit},
	    ${requestScope.dateReadHitCount[1].readHit},
	    ${requestScope.dateReadHitCount[2].readHit},
	    ${requestScope.dateReadHitCount[3].readHit},
	    ${requestScope.dateReadHitCount[4].readHit},
	    ${requestScope.dateReadHitCount[5].readHit},
	    ${requestScope.dateReadHitCount[6].readHit},
	    ${requestScope.dateReadHitCount[7].readHit},
	];
	
	const currentDate = new Date();

	// 각 날짜 계산
	const date8 = new Date(currentDate);
	date8.setDate(currentDate.getDate());
	const month8 = date8.getMonth() + 1;
	const day8 = date8.getDate();
	
	const date7 = new Date(currentDate);
	date7.setDate(currentDate.getDate() - 1);
	const month7 = date7.getMonth() + 1;
	const day7 = date7.getDate();

	const date6 = new Date(currentDate);
	date6.setDate(currentDate.getDate() - 2); // 전전날
	const month6 = date6.getMonth() + 1;
	const day6 = date6.getDate();

	const date5 = new Date(currentDate);
	date5.setDate(currentDate.getDate() - 3);
	const month5 = date5.getMonth() + 1;
	const day5 = date5.getDate();

	const date4 = new Date(currentDate);
	date4.setDate(currentDate.getDate() - 4);
	const month4 = date4.getMonth() + 1;
	const day4 = date4.getDate();

	const date3 = new Date(currentDate);
	date3.setDate(currentDate.getDate() - 5);
	const month3 = date3.getMonth() + 1;
	const day3 = date3.getDate();

	const date2 = new Date(currentDate);
	date2.setDate(currentDate.getDate() - 6);
	const month2 = date2.getMonth() + 1;
	const day2 = date2.getDate();

	const date1 = new Date(currentDate);
	date1.setDate(currentDate.getDate() - 7); // 일주일 전
	const month1 = date1.getMonth() + 1;
	const day1 = date1.getDate();

	// 각 클래스에 텍스트 설정
	
	$(".day1").text(`\${month1}월 \${day1}일`);
	$(".day2").text(`\${month2}월 \${day2}일`);
	$(".day3").text(`\${month3}월 \${day3}일`);
	$(".day4").text(`\${month4}월 \${day4}일`);
	$(".day5").text(`\${month5}월 \${day5}일`);
	$(".day6").text(`\${month6}월 \${day6}일`);
	$(".day7").text(`\${month7}월 \${day7}일`);
	$(".day8").text(`\${month8}월 \${day8}일`);
	// Set data
	var data = [{
	  country: `\${month1}월 \${day1}일`,
	  value: graphData[0]
	}, {
	  country: `\${month2}월 \${day2}일`,
	  value: graphData[1]
	}, {
	  country: `\${month3}월 \${day3}일`,
	  value: graphData[2]
	}, {
	  country: `\${month4}월 \${day4}일`,
	  value: graphData[3]
	}, {
	  country: `\${month5}월 \${day5}일`,
	  value: graphData[4]
	}, {
	  country: `\${month6}월 \${day6}일`,
	  value: graphData[5]
	}, {
	  country: `\${month7}월 \${day7}일`,
	  value: graphData[6]
	}, {
	  country: `\${month8}월 \${day8}일`,
	  value: graphData[7]
	}];

	xAxis.data.setAll(data);
	series.data.setAll(data);


	// Make stuff animate on load
	// https://www.amcharts.com/docs/v5/concepts/animations/
	series.appear(1000);
	chart.appear(1000, 100);

	}); // end am5.ready()


</script>
</head>
<body>
	<div class="container">
		<h2>내 수익 활동</h2>
		<div>
			<h4 style="margin-top: 50px;">총계</h4>
			<div class="total-container">
				<div class="totalReadHit-container">
					<div class="totalReadHit-box">
						<span>총 조회수</span> 
						<span>${requestScope.readHitTotalCount}</span>
					</div>
				</div>
				<div class='totalReviewCount-container'>
					<div class="totalReivewCount-box">
						<span>총 댓글수</span> 
						<span>${requestScope.reviewTotalCount}</span>
					</div>
				</div>
			</div>
		</div>
		<div id="chartdiv"></div>
	</div>
</body>
</html>