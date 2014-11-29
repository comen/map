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
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=1.5&ak=msbblC5TGVpnQnafevVen547"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<link rel="stylesheet"
	href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
</head>
<body>
	<div id="container"></div>
	<script type="text/javascript">
		var map = (function() {
			var center = new BMap.Point(121.42000, 31.29336);
			map = new BMap.Map("container");
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
						+ point.lng.toFixed(5) + "," + point.lat.toFixed(5)
						+ ")";
			});
			map.addEventListener("zoomend", function(event) {
				var zoomLevel = map.getZoom();
				document.getElementById("info").innerHTML = zoomLevel;
				map.closeInfoWindow();
				map.clearOverlays();
				if (zoomLevel > 11) {
					fetch();
				}
			});
			fetch();
			return map;
		})();

		function fetch() {
			var formData = new FormData();
			formData.append("zoom", map.getZoom());

			$.ajax({
				url : 'fetch',
				type : 'POST',
				data : formData,
				async : true,
				cache : false,
				contentType : false,
				processData : false,
				success : function(respText) {
					var grids = eval(respText);
					var polygons = new HashMap();
					for (var i = 0; i < grids.length; i++) {
						var code = grids[i].GRID_CODE;
						var points = new Array();
						if (grids[i].GRID_COORDINATES != null) {
							var coordinates = grids[i].GRID_COORDINATES;
							for (var j = 0; j < coordinates.length; j++) {
								var point = new BMap.Point(coordinates[j].LONGTITUDE, coordinates[j].LATITUDE);
								points.push(point);
							}
						}
						var polygon = new BMap.Polygon(points, {
							strokeColor : "blue",
							strokeWeight : 1,
							strokeOpacity : 0.5,
							fillOpacity : 0.5
						});
						polygons.put(code, polygon);
					}

					polygons.foreach(function(index, code, polygon) {
						map.addOverlay(polygon);
						var message = "";
						var editable = false;
						var path = polygon.getPath();
						var lngsum = Number(path[0].lng.toFixed(5));
						var latsum = Number(path[0].lat.toFixed(5));
						for (var i = 1; i < path.length; i++) {
							var point = path[i];
							lngsum = lngsum + Number(point.lng.toFixed(5));
							latsum = latsum	+ Number(point.lat.toFixed(5));
						}
						lngsum = lngsum / path.length;
						latsum = latsum / path.length;
						
						function openInfo(event) {
							var formData = new FormData();
							formData.append("zoom", map.getZoom());
							formData.append("longtitude", event.point.lng.toFixed(5));
							formData.append("latitude", event.point.lat.toFixed(5));
							$.ajax({
								url : 'info',
								type : 'POST',
								data : formData,
								async : false,
								cache : false,
								contentType : false,
								processData : false,
								success : function(respText) {
									message = respText;
								}
							});
							
							if (message.length == 0 || editable)
								return;
							
							map.openInfoWindow(new BMap.InfoWindow(message, {
								width : 200,
								height : 100,
								title : "<b>网格数据</b>",
								message : message,
								enableCloseOnClick : true
							}), new BMap.Point(lngsum,latsum));
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
							
	i					function switchEdit(event) {
							closeInfo(event);
							if (editable) {
								editable = false;
								polygon.disableEditing();
								polygon.addEventListener("click", openInfo);
								polygon.addEventListener("mouseover", openInfo);
								polygon.addEventListener("mouseout", closeInfo);
								
								var path = event.target.getPath();
								var coordinates = "[{LONGTITUDE:" + path[0].lng.toFixed(5) + ",LATITUDE:" + path[0].lat.toFixed(5) + "}";
								for (var i = 1; i < path.length; i++) {
									var point = path[i];
									coordinates += ",{LONGTITUDE:" + point.lng.toFixed(5) + ",LATITUDE:" + point.lat.toFixed(5) + "}";
								}
								coordinates += "]";
								
								var formData = new FormData();
								formData.append("GRID_CODE", code);
								formData.append("GRID_COORDINATES", coordinates);
								$.ajax({
									url : 'reshape',
									type : 'POST',
									data : formData,
									async : false,
									cache : false,
									contentType : false,
									processData : false,
									success : alert('成功修改网格区域！')
								});
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
					});
				}
			});
		}
	</script>
	<div style="width: 100%;" id="info"></div>
</body>
</html>
