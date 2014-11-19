<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>北区局</title>
<style type="text/css">
html {
	height: 100%;
}

body {
	height: 97%;
	margin: 0px;
	padding: 0px;
	font: 12px/16px Verdana, Helvetica, Arial, '微软雅黑';
}

#container {
	height: 100%;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=1.5&ak=msbblC5TGVpnQnafevVen547"></script>
</head>
<body>
	<div id="container"></div>
	<script type="text/javascript">
		var map = new BMap.Map("container");
		var center = new BMap.Point(121.42000, 31.29336);
		map.centerAndZoom(center, 13);
		map.addControl(new BMap.NavigationControl());
		map.addControl(new BMap.ScaleControl());
		map.addControl(new BMap.OverviewMapControl());
		map.addControl(new BMap.MapTypeControl());
		map.enableScrollWheelZoom();
		map.setCurrentCity("上海");

		map.addEventListener("mousemove", function(event) {
			var point = event.point;
			document.getElementById("info").innerHTML = "("
					+ point.lng.toFixed(5) + "," + point.lat.toFixed(5) + ")";
		});

		var polygon = new BMap.Polygon([ new BMap.Point(121.40150, 31.33795),
				new BMap.Point(121.39884, 31.33546),
				new BMap.Point(121.40322, 31.32321),
				new BMap.Point(121.40056, 31.31698),
				new BMap.Point(121.40013, 31.31280),
				new BMap.Point(121.40056, 31.30708),
				new BMap.Point(121.39652, 31.30011),
				new BMap.Point(121.38815, 31.30356),
				new BMap.Point(121.38283, 31.30375),
				new BMap.Point(121.38090, 31.30213),
				new BMap.Point(121.37906, 31.30334),
				new BMap.Point(121.37459, 31.30400),
				new BMap.Point(121.37189, 31.30257),
				new BMap.Point(121.36099, 31.30276),
				new BMap.Point(121.35502, 31.30000),
				new BMap.Point(121.34653, 31.30180),
				new BMap.Point(121.34674, 31.29879),
				new BMap.Point(121.34314, 31.29868),
				new BMap.Point(121.33829, 31.29370),
				new BMap.Point(121.33410, 31.29428),
				new BMap.Point(121.33490, 31.29139),
				new BMap.Point(121.32634, 31.28902),
				new BMap.Point(121.33354, 31.28031),
				new BMap.Point(121.33298, 31.27839),
				new BMap.Point(121.33174, 31.27831),
				new BMap.Point(121.33130, 31.27795),
				new BMap.Point(121.33708, 31.27705),
				new BMap.Point(121.35989, 31.27129),
				new BMap.Point(121.36004, 31.26790),
				new BMap.Point(121.35938, 31.26481),
				new BMap.Point(121.38167, 31.26096),
				new BMap.Point(121.38511, 31.26410),
				new BMap.Point(121.39277, 31.26513),
				new BMap.Point(121.44175, 31.25581),
				new BMap.Point(121.46617, 31.24656),
				new BMap.Point(121.47024, 31.24759),
				new BMap.Point(121.47217, 31.24010),
				new BMap.Point(121.47561, 31.24153),
				new BMap.Point(121.48016, 31.24018),
				new BMap.Point(121.48612, 31.24418),
				new BMap.Point(121.49325, 31.24271),
				new BMap.Point(121.49569, 31.24484),
				new BMap.Point(121.49475, 31.24733),
				new BMap.Point(121.49350, 31.24817),
				new BMap.Point(121.49256, 31.25027),
				new BMap.Point(121.49157, 31.25100),
				new BMap.Point(121.49187, 31.25379),
				new BMap.Point(121.49162, 31.25632),
				new BMap.Point(121.49303, 31.26043),
				new BMap.Point(121.49419, 31.26157),
				new BMap.Point(121.49350, 31.26226),
				new BMap.Point(121.49428, 31.26373),
				new BMap.Point(121.49380, 31.26443),
				new BMap.Point(121.49419, 31.26520),
				new BMap.Point(121.49608, 31.26835),
				new BMap.Point(121.49887, 31.26787),
				new BMap.Point(121.50234, 31.27404),
				new BMap.Point(121.49878, 31.27635),
				new BMap.Point(121.48720, 31.27660),
				new BMap.Point(121.48308, 31.27503),
				new BMap.Point(121.47788, 31.27598),
				new BMap.Point(121.46951, 31.27987),
				new BMap.Point(121.46188, 31.27998),
				new BMap.Point(121.46132, 31.28207),
				new BMap.Point(121.46183, 31.28497),
				new BMap.Point(121.46097, 31.28885),
				new BMap.Point(121.45973, 31.29006),
				new BMap.Point(121.46278, 31.29421),
				new BMap.Point(121.46454, 31.29788),
				new BMap.Point(121.46363, 31.30477),
				new BMap.Point(121.47003, 31.30583),
				new BMap.Point(121.47690, 31.31199),
				new BMap.Point(121.47780, 31.31548),
				new BMap.Point(121.47509, 31.32204),
				new BMap.Point(121.46829, 31.32038),
				new BMap.Point(121.46733, 31.32893),
				new BMap.Point(121.46256, 31.32710),
				new BMap.Point(121.45342, 31.32688),
				new BMap.Point(121.44488, 31.32398),
				new BMap.Point(121.43651, 31.34290),
				new BMap.Point(121.42956, 31.34213),
				new BMap.Point(121.42621, 31.33850),
				new BMap.Point(121.41840, 31.33751),
				new BMap.Point(121.41189, 31.33898) ], {
			strokeColor : "blue",
			strokeWeight : 2,
			strokeOpacity : 0.5,
			enableEditing : true
		});
		map.addOverlay(polygon);

		var infoWindow = new BMap.InfoWindow("经纬度：(" + center.lng + ","
				+ center.lat + ")", {
			width : 200,
			height : 100,
			title : "北区局",
			enableMessage : true,
			message : "经纬度：(" + center.lng + "," + center.lat + ")"
		});
		polygon.addEventListener("mouseover", function(event) {
			map.openInfoWindow(infoWindow, center);
		});
		polygon.addEventListener("mouseout", function(event) {
			map.closeInfoWindow();
		});
		polygon.addEventListener("rightclick", function(event) {
			var path = polygon.getPath();
			var fso = new ActiveXObject("Scripting.FileSystemObject");
			var file = fso.OpenTextFile("C:/path.txt", 8, true);
			for (var i = 1; i < path.length; i++) {
				var point = path[i];
				file.WriteLine("(" + point.lng.toFixed(5) + ","
						+ point.lat.toFixed(5) + ")");
			}
			file.Close();
		});
		map.addEventListener("zoomend", function(event) {
			var zoomLevel = map.getZoom();
			if (zoomLevel == 13 || zoomLevel == 14) {
				map.addOverlay(polygon);
			} else {
				map.removeOverlay(polygon);
			}
		});
	</script>
	<div style="width: 100%;" id="info"></div>
</body>
</html>