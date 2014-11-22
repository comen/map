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
<script type="text/javascript" src="js/HashMap.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=1.5&ak=msbblC5TGVpnQnafevVen547"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<link rel="stylesheet"
	href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
</head>
<body onload="fetch()">
	<div id="container"></div>
	<script type="text/javascript">
		var xmlHttp;
		
		function fetch() {
			createXmlHttp();
			xmlHttp.onreadystatechange = callBack;
			xmlHttp.open("post", "fetch", true);
			xmlHttp.setRequestHeader("Content-Type",
					"multipart/form-data;boundary=map");
			
			var formData = new FormData();
			formData.append("grid_code", "0");
			xmlHttp.send(formData);
		}
		
		function createXmlHttp() {
			if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}

		function callBack() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var map = new BMap.Map("container");
				var center = new BMap.Point(121.42000, 31.29336);
				map.centerAndZoom(center, 13);
				map.addControl(new BMap.OverviewMapControl());
				map.addControl(new BMap.NavigationControl());
				map.addControl(new BMap.MapTypeControl());
				map.addControl(new BMap.ScaleControl());
				map.disableDoubleClickZoom();
				map.enableScrollWheelZoom();
				map.setCurrentCity("上海");

				map.addEventListener("mousemove", function(event) {
					var point = event.point;
					document.getElementById("info").innerHTML = "("
							+ point.lng.toFixed(5) + "," + point.lat.toFixed(5) + ")";
				});
				
				var grids = new HashMap();
				var points = new Array();
				<%
				if (session.getAttribute("longtitudes") != null) {
					double[] longtitudes = (double[]) session.getAttribute("longtitudes");
					double[] latitudes = (double[]) session.getAttribute("latitudes");
					for (int i = 0; i < latitudes.length; i++) {
				%>
						var coordinate = new BMap.Point(<%=longtitudes[i]%>, <%=latitudes[i]%>);
						points.push(coordinate);
				<%
					}
				}
				%>
				var polygon = new BMap.Polygon(points, {
					strokeColor : "blue",
					strokeWeight : 2,
					strokeOpacity : 0.5,
					fillOpacity : 0.5
				});
				grids.put("polygon", polygon);
				grids.foreach(function(index, key, value) {
					map.addOverlay(value);
				});

				var path = polygon.getPath();
				var point = path[0];
				var message = "Path:(" + point.lng.toFixed(5) + ","
						+ point.lat.toFixed(5) + ")";
				for (var i = 1; i < path.length; i++) {
					point = path[i];
					message = message + ",(" + point.lng.toFixed(5) + ","
							+ point.lat.toFixed(5) + ")";
				}

				var infoWindow = new BMap.InfoWindow(message, {
					width : 200,
					height : 100,
					title : "北区局",
					enableMessage : true,
					message : message,
					enableCloseOnClick : true
				});

				var editable = false;
				function openInfo(event) {
					if (editable)
						return;
					map.openInfoWindow(infoWindow, map.getCenter());
					polygon.setStrokeWeight(3);
					polygon.setStrokeOpacity(0.8);
					polygon.setFillOpacity(0.8);
				}
				function closeInfo(event) {
					map.closeInfoWindow();
					polygon.setStrokeWeight(2);
					polygon.setStrokeOpacity(0.5);
					polygon.setFillOpacity(0.5);
				}
				function switchEdit(event) {
					if (editable) {
						editable = false;
						polygon.disableEditing();
						polygon.addEventListener("click", openInfo);
						polygon.addEventListener("mouseover", openInfo);
						polygon.addEventListener("mouseout", closeInfo);
					} else {
						editable = true;
						polygon.enableEditing();
						polygon.removeEventListener("click", openInfo);
						polygon.removeEventListener("mouseover", openInfo);
						polygon.removeEventListener("mouseout", closeInfo);
					}
				}
				polygon.addEventListener("click", openInfo);
				polygon.addEventListener("mouseover", openInfo);
				polygon.addEventListener("mouseout", closeInfo);
				polygon.addEventListener("rightclick", switchEdit);
				map.addEventListener("zoomend", function(event) {
					var zoomLevel = map.getZoom();
					if (zoomLevel > 10 && zoomLevel < 14) {
						map.addOverlay(polygon);
					} else {
						map.removeOverlay(polygon);
					}
				});
			}
		}
	</script>
	<div style="width: 100%;" id="info"></div>
</body>
</html>
