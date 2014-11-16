<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>添加多边形区域覆盖物</title>
<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
}

* {
	margin: 0px;
	padding: 0px;
}

body {
	font: 12px/16px Verdana, Helvetica, Arial, '微软雅黑';
}
</style>
<script charset="utf-8"
	src="http://map.qq.com/api/js?v=2.exp&key=PZIBZ-6DVRF-OVUJB-JLZVT-5IGKQ-HAFN4"></script>
<script>
	var init = function() {
		var center = new qq.maps.LatLng(31.24869, 121.46660);
		var map = new qq.maps.Map(document.getElementById('container'), {
			center : center,
			zoom : 16
		});
		var path1 = [ new qq.maps.LatLng(31.25214, 121.46386),
				new qq.maps.LatLng(31.25313, 121.46892),
				new qq.maps.LatLng(31.24678, 121.47051),
				new qq.maps.LatLng(31.24425, 121.46686),
				new qq.maps.LatLng(31.24515, 121.46255) ];
		var polygon = new qq.maps.Polygon({
			path : path1,
			map : map
		});
		var infoWin = new qq.maps.InfoWindow({
			content : '网格信息',
			position : center,
			map : map
		});
		infoWin.open('网格信息');
		qq.maps.event.addListener(map, 'mousemove', function(event) {
			var lat = event.latLng.getLat().toFixed(5), lng = event.latLng
					.getLng().toFixed(5);
			document.getElementById("info").innerHTML = lat + ',' + lng;
		});
	}
</script>
</head>
<body onload="init()">
	<div style="width: 100%; height: 95%" id="container"></div>
	<div style="width: 100%;" id="info"></div>
</body>
</html>