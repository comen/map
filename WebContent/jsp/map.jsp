<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%-- Validate if user login successfully. --%>
<%@include file="validate.jsp"%>
	
<%
	String longitude = request.getParameter("longitude");
	String latitude = request.getParameter("latitude");
	
	boolean available = false;
	if (role == 1 || role == 2) {
		available = true;
	}
	
	Double lng = 0.0;
	Double lat = 0.0;
	if (longitude != null && latitude != null) {
		lng = Double.parseDouble(longitude);
		lat = Double.parseDouble(latitude);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>北区电信局</title>
<style type="text/css">
html {
	height: 100%;
}

body {
	height: 96%;
	margin: 0px;
	padding: 0px;
	font: 12px/16px Verdana, Helvetica, Arial, '微软雅黑';
}

label {
	margin-left: 30px;
	font-weight: bold;
}

#menu {
	height: 4%;
	text
}

#mapcontainer {
	height: 94%;
}

#status {
	height: 2%;
}
</style>
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<script type="text/javascript" src="../js/HashMap.js"></script>
<script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=msbblC5TGVpnQnafevVen547"></script>
<script>
	$(function() {
		$("#date").datepicker();
	});
</script>
</head>
<body>
	<div id="menu">
		<label>选择数据显示方式：</label>
		<input type="radio" name="mode" id ="day" value="day" checked="checked" />按天显示
		<input type="radio" name="mode" id ="week" value="week" />按周显示
		<input type="radio" name="mode" id ="month" value="month" />按月显示
		<label for="date">选择数据日期: </label>
		<input type="text" id="date" id="date" readonly="readonly">
		<input type="button" onclick="fetch()" value="重新获取数据">
	</div>
	<p></p>
	<div id="mapcontainer"></div>
	<div id="status"></div>
	<script type="text/javascript">
		var currAjax;
		var map = (function() {
			map = new BMap.Map("mapcontainer");
			var center = new BMap.Point(121.42000, 31.29336);
			var zoom = 13;
<%
	if (lng > 0 && lat > 0) {
%>
			center = new BMap.Point(<%=lng%>, <%=lat%>);
			zoom = 18;
<%
	}
%>
			map.centerAndZoom(center, zoom);
			map.addControl(new BMap.OverviewMapControl());
			map.addControl(new BMap.NavigationControl());
			map.addControl(new BMap.MapTypeControl());
			map.addControl(new BMap.ScaleControl());
			map.disableDoubleClickZoom();
			map.enableScrollWheelZoom();
			map.setCurrentCity("上海");
			map.addEventListener("mousemove", function(event) {
				var point = event.point;
				document.getElementById("status").innerHTML = "("
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
				document.getElementById("status").innerHTML = zoomLevel;
				if (zoomLevel > 11) {
					fetch();
				}
			});
			$("#date").val(getDate(1));
			fetch();
			return map;
		})();

		function fetch() {
			if (currAjax != null) {
				currAjax.abort();
			}
			map.closeInfoWindow();
			map.clearOverlays();
			
			var formData = new FormData();
			var mode = $('input[name="mode"]:checked').val();
			var date = $("#date").val();
			var bounds = map.getBounds();
			var sw = bounds.getSouthWest();
			var ne = bounds.getNorthEast();
			formData.append("zoom", map.getZoom());
			formData.append("mode", mode);
			formData.append("date", date);
			formData.append("swlng", sw.lng);
			formData.append("swlat", sw.lat);
			formData.append("nelng", ne.lng);
			formData.append("nelat", ne.lat);
			
			currAjax = $.ajax({
				url : 'fetch',
				type : 'POST',
				data : formData,
				timeout: 5000,
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
						switch (zoom) {
						case 13:
						case 14:
						case 15:
							if (code.length == 1) {
								var coordinates = grids[i].p;
								for (var j = 0; j < coordinates.length; j++) {
									var point = new BMap.Point(coordinates[j].o, coordinates[j].a);
									points.push(point);
								}
								var polygon = new BMap.Polygon(points, {
									strokeColor : getRandomColor(),
									strokeWeight : 2,
									strokeOpacity : 0.5,
									fillColor: grids[i].r,
									fillOpacity : 0.5,
								});
								polygons.put(code, polygon);
							} else {
								independ.put(code, grids[i].d);
							}
						case 16:
						case 17:
							independ.put(code, grids[i]);
							break;
						case 18:
						case 19:
							if (grids[i].p != null && grids[i].p.length > 2) {
								var coordinates = grids[i].p;
								for (var j = 0; j < coordinates.length; j++) {
									var point = new BMap.Point(coordinates[j].o, coordinates[j].a);
									points.push(point);
								}
								var polygon = new BMap.Polygon(points, {
									strokeColor : getRandomColor(),
									strokeWeight : 2,
									strokeOpacity : 0.5,
									fillColor: grids[i].r,
									fillOpacity : 0.5,
								});
								polygons.put(code, polygon);
							} else {
								independ.put(code, grids[i]);
							}
							break;
						default:
							break;
						}
					}
					
					independ.foreach(function(index, code, grid) {
						var address = grid.d;
						var geo = new BMap.Geocoder();
						geo.getPoint(address, function(point){
							if (point && map.getBounds().containsPoint(point)) {
								if (zoom > 17) {
									var polygon = new BMap.Polygon([new BMap.Point(point.lng - 0.0005, point.lat + 0.0003),
											new BMap.Point(point.lng - 0.0005, point.lat - 0.0003),
											new BMap.Point(point.lng + 0.0005, point.lat - 0.0003),
											new BMap.Point(point.lng + 0.0005, point.lat + 0.0003)], {
										strokeColor : getRandomColor(),
										strokeWeight : 2,
										strokeOpacity : 0.5,
										fillOpacity : 0.5,
									});
									map.addOverlay(polygon);
									var editable = false;
									
									var path = polygon.getPath();
									var coordinates = "[{LONGITUDE:" + path[0].lng.toFixed(5) + ",LATITUDE:" + path[0].lat.toFixed(5) + "}";
									for (var i = 1; i < path.length; i++) {
										var point = path[i];
										coordinates += ",{LONGITUDE:" + point.lng.toFixed(5) + ",LATITUDE:" + point.lat.toFixed(5) + "}";
									}
									coordinates += "]";
									
									var shape = new FormData();
									shape.append("GRID_CODE", code);
									shape.append("GRID_COORDINATES", coordinates);
									$.ajax({
										url : 'reshape',
										type : 'POST',
										data : shape,
										async : true,
										cache : true,
										contentType : false,
										processData : false,
									});
									
									function openInfo(event) {
										var message;
										var formData = new FormData();
										var mode = $('input[name="mode"]:checked').val();
										var date = $("#date").val();
										formData.append("zoom", map.getZoom());
										formData.append("mode", mode);
										formData.append("date", date);
										formData.append("longitude", event.point.lng.toFixed(5));
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
										
										var path = event.target.getPath();
										var lngsum = Number(path[0].lng.toFixed(5));
										var latsum = Number(path[0].lat.toFixed(5));
										for (var i = 1; i < path.length; i++) {
											var point = path[i];
											lngsum = lngsum + Number(point.lng.toFixed(5));
											latsum = latsum	+ Number(point.lat.toFixed(5));
										}
										lngsum = lngsum / path.length;
										latsum = latsum / path.length;
										
										map.openInfoWindow(new BMap.InfoWindow(message, {
											width : 200,
											height : 100,
											title : "<b>网格数据</b>",
											message : message,
											width : 0,
											height : 0,
											enableCloseOnClick : true
										}), new BMap.Point(lngsum,latsum));
										polygon.setStrokeWeight(3);
										polygon.setStrokeOpacity(0.8);
										polygon.setFillOpacity(0.8);
									}

									function switchEdit(event) {
										closeInfo(event);
									<%
										if (!available) {
									%>
											return;
									<%
										}
									%>	
										if (editable) {
											editable = false;
											event.target.disableEditing();
											
											var path = event.target.getPath();
											var coordinates = "[{LONGITUDE:" + path[0].lng.toFixed(5) + ",LATITUDE:" + path[0].lat.toFixed(5) + "}";
											for (var i = 1; i < path.length; i++) {
												var point = path[i];
												coordinates += ",{LONGITUDE:" + point.lng.toFixed(5) + ",LATITUDE:" + point.lat.toFixed(5) + "}";
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
												success : function(respText) {
													alert(respText);
													/*var grid = eval(respText);
													var points = new Array();
													var coordinates = grids[i].p;
													for (var j = 0; j < coordinates.length; j++) {
														var point = new BMap.Point(coordinates[j].o, coordinates[j].a);
														points.push(point);
													}
													event.target.setPath(points);*/
												}
											});
										} else {
											editable = true;
											event.target.enableEditing();
										}
									}

									event.target.addEventListener("click", openInfo);
									event.target.addEventListener("mouseover", openInfo);
									event.target.addEventListener("mouseout", closeInfo);
									event.target.addEventListener("rightclick", switchEdit);
								} else {
									var marker = new BMap.Marker(point);
									map.addOverlay(marker);
									var text = code + "<br/>" + address;
									var label = new BMap.Label(code,{offset:new BMap.Size(-10,-5)});
									label.setStyle({
										color : "#000000",
										borderColor : "#000000",
										backgroudColor : grid.r,
										fontSize : "12px"
									});
									marker.setLabel(label);
									
									function openLabel(event) {
										label.setContent(text);
										marker.setTop(true);
									}
									
									function closeLabel(event) {
										label.setContent(code);
										marker.setTop(false);
									}
									
									marker.addEventListener("mouseover", openLabel);
									marker.addEventListener("mouseout", closeLabel);
								}
							}
						}, "上海市");
					});

					polygons.foreach(function(index, code, polygon) {
						map.addOverlay(polygon);
						var editable = false;
						
						function openInfo(event) {
							var message;
							var formData = new FormData();
							var mode = $('input[name="mode"]:checked').val();
							var date = $("#date").val();
							formData.append("zoom", map.getZoom());
							formData.append("mode", mode);
							formData.append("date", date);
							formData.append("longitude", event.point.lng.toFixed(5));
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
							
							var path = event.target.getPath();
							var lngsum = Number(path[0].lng.toFixed(5));
							var latsum = Number(path[0].lat.toFixed(5));
							for (var i = 1; i < path.length; i++) {
								var point = path[i];
								lngsum = lngsum + Number(point.lng.toFixed(5));
								latsum = latsum	+ Number(point.lat.toFixed(5));
							}
							lngsum = lngsum / path.length;
							latsum = latsum / path.length;
							
							map.openInfoWindow(new BMap.InfoWindow(message, {
								width : 200,
								height : 100,
								title : "<b>网格数据</b>",
								width : 0,
								height : 0,
								message : message,
								enableCloseOnClick : true
							}), new BMap.Point(lngsum,latsum));
							polygon.setStrokeWeight(3);
							polygon.setStrokeOpacity(0.8);
							polygon.setFillOpacity(0.8);
						}
						
						function switchEdit(event) {
							closeInfo(event);
						<%
							if (!available) {
						%>
								return;
						<%
							}
						%>								
							if (editable) {
								editable = false;
								event.target.disableEditing();
								
								var path = event.target.getPath();
								var coordinates = "[{LONGITUDE:" + path[0].lng.toFixed(5) + ",LATITUDE:" + path[0].lat.toFixed(5) + "}";
								for (var i = 1; i < path.length; i++) {
									var point = path[i];
									coordinates += ",{LONGITUDE:" + point.lng.toFixed(5) + ",LATITUDE:" + point.lat.toFixed(5) + "}";
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
									success : function(respText) {
										alert(respText);
										/*var grid = eval(respText);
										var points = new Array();
										var coordinates = grids[i].p;
										for (var j = 0; j < coordinates.length; j++) {
											var point = new BMap.Point(coordinates[j].o, coordinates[j].a);
											points.push(point);
										}
										event.target.setPath(points);*/
									}
								});
							} else {
								editable = true;
								event.target.enableEditing();
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
		
		function getRandomColor() {    
			 return (function(m, s, c){
				 return (c ? arguments.callee(m, s,c - 1) : '#') + s[m.floor(m.random() * 16)]
			 })(Math, '0123456789abcdef', 5)
		}
		
		function getDate(offset) {
			var date = new Date();
			date.setDate(date.getDate() - offset);
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			if (day < 10) {
				day = '0' + day;
			}
			return (month + '/' + day + '/' + year);
		}
		
		function closeInfo(event) {
			map.closeInfoWindow();
			event.target.setStrokeWeight(2);
			event.target.setStrokeOpacity(0.5);
			event.target.setFillOpacity(0.5);
		}
	</script>
</body>
</html>
