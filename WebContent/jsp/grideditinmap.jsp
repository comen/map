<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script type="text/javascript">
	var map = new BMap.Map("mapcontainer2");
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
	map.addEventListener("zoomend", function(event) {
		var zoomLevel = map.getZoom();
		if (zoomLevel > 10 && zoomLevel < 14) {
			map.addOverlay(polygon);
		} else {
			map.removeOverlay(polygon);
		}
	});
	
	var grids = new HashMap();
	var polygon = new BMap.Polygon([ new BMap.Point(121.40850, 31.34395),
			new BMap.Point(121.40584, 31.34146),
			new BMap.Point(121.41022, 31.32921),
			new BMap.Point(121.40756, 31.32298),
			new BMap.Point(121.40713, 31.31880),
			new BMap.Point(121.40756, 31.31308),
			new BMap.Point(121.40352, 31.30611),
			new BMap.Point(121.39515, 31.30956),
			new BMap.Point(121.38983, 31.30975),
			new BMap.Point(121.38790, 31.30813),
			new BMap.Point(121.38606, 31.30934),
			new BMap.Point(121.38159, 31.31000),
			new BMap.Point(121.37889, 31.30857),
			new BMap.Point(121.36799, 31.30876),
			new BMap.Point(121.36202, 31.30600),
			new BMap.Point(121.35353, 31.30780),
			new BMap.Point(121.35374, 31.30479),
			new BMap.Point(121.35014, 31.30468),
			new BMap.Point(121.34529, 31.29970),
			new BMap.Point(121.34110, 31.30028),
			new BMap.Point(121.34190, 31.29739),
			new BMap.Point(121.33334, 31.29502),
			new BMap.Point(121.34054, 31.28631),
			new BMap.Point(121.33998, 31.28439),
			new BMap.Point(121.33874, 31.28431),
			new BMap.Point(121.33830, 31.28395),
			new BMap.Point(121.34408, 31.28305),
			new BMap.Point(121.36689, 31.27729),
			new BMap.Point(121.36704, 31.27390),
			new BMap.Point(121.36638, 31.27081),
			new BMap.Point(121.38867, 31.26696),
			new BMap.Point(121.39211, 31.27010),
			new BMap.Point(121.39977, 31.27113),
			new BMap.Point(121.44875, 31.26181),
			new BMap.Point(121.47317, 31.25256),
			new BMap.Point(121.47724, 31.25359),
			new BMap.Point(121.47917, 31.24610),
			new BMap.Point(121.48261, 31.24753),
			new BMap.Point(121.48716, 31.24618),
			new BMap.Point(121.49312, 31.25018),
			new BMap.Point(121.50025, 31.24871),
			new BMap.Point(121.50269, 31.25084),
			new BMap.Point(121.50175, 31.25333),
			new BMap.Point(121.50050, 31.25417),
			new BMap.Point(121.49956, 31.25627),
			new BMap.Point(121.49857, 31.25700),
			new BMap.Point(121.49887, 31.25979),
			new BMap.Point(121.49862, 31.26232),
			new BMap.Point(121.50003, 31.26643),
			new BMap.Point(121.50119, 31.26757),
			new BMap.Point(121.50050, 31.26826),
			new BMap.Point(121.50128, 31.26973),
			new BMap.Point(121.50080, 31.27043),
			new BMap.Point(121.50119, 31.27120),
			new BMap.Point(121.50308, 31.27435),
			new BMap.Point(121.50587, 31.27387),
			new BMap.Point(121.50934, 31.28004),
			new BMap.Point(121.50578, 31.28235),
			new BMap.Point(121.49420, 31.28260),
			new BMap.Point(121.49008, 31.28103),
			new BMap.Point(121.48488, 31.28198),
			new BMap.Point(121.47651, 31.28587),
			new BMap.Point(121.46888, 31.28598),
			new BMap.Point(121.46832, 31.28807),
			new BMap.Point(121.46883, 31.29097),
			new BMap.Point(121.46797, 31.29485),
			new BMap.Point(121.46673, 31.29606),
			new BMap.Point(121.46978, 31.30021),
			new BMap.Point(121.47154, 31.30388),
			new BMap.Point(121.47063, 31.31077),
			new BMap.Point(121.47703, 31.31183),
			new BMap.Point(121.48390, 31.31799),
			new BMap.Point(121.48480, 31.32148),
			new BMap.Point(121.48209, 31.32804),
			new BMap.Point(121.47529, 31.32638),
			new BMap.Point(121.47433, 31.33493),
			new BMap.Point(121.46956, 31.33310),
			new BMap.Point(121.46042, 31.33288),
			new BMap.Point(121.45188, 31.32998),
			new BMap.Point(121.44351, 31.34890),
			new BMap.Point(121.43656, 31.34813),
			new BMap.Point(121.43321, 31.34450),
			new BMap.Point(121.42540, 31.34351),
			new BMap.Point(121.41889, 31.34498) ], {
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
		map.openInfoWindow(infoWindow, center);
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
</script>

<h2 class="contentTitle">请编辑网格在地图中的区域：</h2>

<form action="demo/database/db_attachmentBrightBack.html" method="post"
	enctype="multipart/form-data" class="pageForm required-validate"
	onsubmit="return iframeCallback(this, $.bringBack)">

	<div class="pageContent">
		<div class="pageFormContent" layoutH="97">
			<div id="mapcontainer2" style="width: 100%; height: 460px"></div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">确定</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button class="close" type="button">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</div>

</form>
