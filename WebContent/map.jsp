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

#mapcontainer {
	height: 100%;
}
</style>
<script type="text/javascript" src="js/HashMap.js"></script>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=1.5&ak=msbblC5TGVpnQnafevVen547"></script>
</head>
<body>
	<div id="mapcontainer"></div>
	<script type="text/javascript">
		var currAjax;
		var map = (function() {
			var center = new BMap.Point(121.42000, 31.29336);
			map = new BMap.Map("mapcontainer");
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
			map.addEventListener("dragend", function(event) {
				fetch();
			});
			map.addEventListener("zoomend", function(event) {
				var zoomLevel = map.getZoom();
				switch (zoomLevel) {
				case 14:
				case 15:
					window.document.title = "分局信息";
					break;
				case 16:
				case 17:
				case 18:
				case 19:
					window.document.title = "网格信息";
					break;
				default:
					window.document.title = "北区局信息";
					break;
				}
				document.getElementById("info").innerHTML = zoomLevel;
				if (zoomLevel > 11) {
					fetch();
				}
			});
			fetch();
			return map;
		})();

		function fetch() {
			if (currAjax) {
				currAjax.abort();
			}
			map.closeInfoWindow();
			map.clearOverlays();
			
			var formData = new FormData();
			formData.append("zoom", map.getZoom());
			
			currAjax = $.ajax({
				url : 'fetch',
				type : 'POST',
				data : formData,
				timeout: 3000,
				async : true,
				cache : true,
				contentType : false,
				processData : false,
				success : function(respText) {
					var grids = eval(respText);
					var polygons = new HashMap();
					var independ = new HashMap();
					var zoom = map.getZoom();
					for (var i = 0; i < grids.length; i++) {
						var code = grids[i].c;
						var points = new Array();
						if (grids[i].p != null && grids[i].p.length > 2) {
							var coordinates = grids[i].p;
							for (var j = 0; j < coordinates.length; j++) {
								var point = new BMap.Point(coordinates[j].o, coordinates[j].a);
								points.push(point);
							}
							var polygon = new BMap.Polygon(points, {
								strokeColor : "blue",
								strokeWeight : 1,
								strokeOpacity : 0.5,
								fillOpacity : 0.5
							});
							polygons.put(code, polygon);
						} else if (zoom > 15) {
							independ.put(code, grids[i].d);
						}
					}
					
					independ.foreach(function(index, code, address) {
						var geo = new BMap.Geocoder();
						geo.getPoint(address, function(point){
							if (point && map.getBounds().containsPoint(point)) {
								var marker = new BMap.Marker(point);
								map.addOverlay(marker);
								var label = new BMap.Label(code,{offset:new BMap.Size(20,-10)});
								marker.setLabel(label);
							}
						}, "上海市");
					});

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
							
						function switchEdit(event) {
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
