<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>添加多边形区域覆盖物</title>
<style type="text/css">
html,body{
    width:100%;
    height:100%;
}
*{
    margin:0px;
    padding:0px;
}
body{
    font: 12px/16px Verdana, Helvetica, Arial, '微软雅黑';
}
</style>
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=PZIBZ-6DVRF-OVUJB-JLZVT-5IGKQ-HAFN4"></script>
<script>
var init = function() {
    var center = new qq.maps.LatLng(31.29336,121.42000);
    var map = new qq.maps.Map(document.getElementById('container'),{
        center: center,
        zoom: 13
    });
    var initPath=[
        new qq.maps.LatLng(31.33795,121.40150),
        new qq.maps.LatLng(31.33546,121.39884),
        new qq.maps.LatLng(31.32321,121.40322),
        new qq.maps.LatLng(31.31698,121.40056),
        new qq.maps.LatLng(31.31280,121.40013),
        new qq.maps.LatLng(31.30708,121.40056),
        new qq.maps.LatLng(31.30011,121.39652),
        new qq.maps.LatLng(31.30356,121.38815),
        new qq.maps.LatLng(31.30375,121.38283),
        new qq.maps.LatLng(31.30213,121.38090),
        new qq.maps.LatLng(31.30334,121.37906),
        new qq.maps.LatLng(31.30400,121.37459),
        new qq.maps.LatLng(31.30257,121.37189),
        new qq.maps.LatLng(31.30276,121.36099),
        new qq.maps.LatLng(31.30000,121.35502),
        new qq.maps.LatLng(31.30180,121.34653),
        new qq.maps.LatLng(31.29879,121.34674),
        new qq.maps.LatLng(31.29868,121.34314),
        new qq.maps.LatLng(31.29370,121.33829),
        new qq.maps.LatLng(31.29428,121.33410),
        new qq.maps.LatLng(31.29139,121.33490),
        new qq.maps.LatLng(31.28902,121.32634),
        new qq.maps.LatLng(31.28031,121.33354),
        new qq.maps.LatLng(31.27839,121.33298),
        new qq.maps.LatLng(31.27831,121.33174),
        new qq.maps.LatLng(31.27795,121.33130),
        new qq.maps.LatLng(31.27705,121.33708),
        new qq.maps.LatLng(31.27129,121.35989),
        new qq.maps.LatLng(31.26790,121.36004),
        new qq.maps.LatLng(31.26481,121.35938),
        new qq.maps.LatLng(31.26096,121.38167),
        new qq.maps.LatLng(31.26410,121.38511),
        new qq.maps.LatLng(31.26513,121.39277),
        new qq.maps.LatLng(31.25581,121.44175),
        new qq.maps.LatLng(31.24656,121.46617),
        new qq.maps.LatLng(31.24759,121.47024),
        new qq.maps.LatLng(31.24010,121.47217),
        new qq.maps.LatLng(31.24153,121.47561),
        new qq.maps.LatLng(31.24018,121.48016),
        new qq.maps.LatLng(31.24418,121.48612),
        new qq.maps.LatLng(31.24271,121.49325),
        new qq.maps.LatLng(31.24484,121.49569),
        new qq.maps.LatLng(31.24733,121.49475),
        new qq.maps.LatLng(31.24817,121.49350),
        new qq.maps.LatLng(31.25027,121.49256),
        new qq.maps.LatLng(31.25100,121.49157),
        new qq.maps.LatLng(31.25379,121.49187),
        new qq.maps.LatLng(31.25632,121.49162),
        new qq.maps.LatLng(31.26043,121.49303),
        new qq.maps.LatLng(31.26157,121.49419),
        new qq.maps.LatLng(31.26226,121.49350),
        new qq.maps.LatLng(31.26373,121.49428),
        new qq.maps.LatLng(31.26443,121.49380),
        new qq.maps.LatLng(31.26520,121.49419),
        new qq.maps.LatLng(31.26835,121.49608),
        new qq.maps.LatLng(31.26787,121.49887),
        new qq.maps.LatLng(31.27404,121.50234),
        new qq.maps.LatLng(31.27635,121.49878),
        new qq.maps.LatLng(31.27660,121.48720),
        new qq.maps.LatLng(31.27503,121.48308),
        new qq.maps.LatLng(31.27598,121.47788),
        new qq.maps.LatLng(31.27987,121.46951),
        new qq.maps.LatLng(31.27998,121.46188),
        new qq.maps.LatLng(31.28207,121.46132),
        new qq.maps.LatLng(31.28497,121.46183),
        new qq.maps.LatLng(31.28885,121.46097),
        new qq.maps.LatLng(31.29006,121.45973),
        new qq.maps.LatLng(31.29421,121.46278),
        new qq.maps.LatLng(31.29788,121.46454),
        new qq.maps.LatLng(31.30477,121.46363),
        new qq.maps.LatLng(31.30583,121.47003),
        new qq.maps.LatLng(31.31199,121.47690),
        new qq.maps.LatLng(31.31548,121.47780),
        new qq.maps.LatLng(31.32204,121.47509),
        new qq.maps.LatLng(31.32038,121.46829),
        new qq.maps.LatLng(31.32893,121.46733),
        new qq.maps.LatLng(31.32710,121.46256),
        new qq.maps.LatLng(31.32688,121.45342),
        new qq.maps.LatLng(31.32398,121.44488),
        new qq.maps.LatLng(31.34290,121.43651),
        new qq.maps.LatLng(31.34213,121.42956),
        new qq.maps.LatLng(31.33850,121.42621),
        new qq.maps.LatLng(31.33751,121.41840),
        new qq.maps.LatLng(31.33898,121.41189)
    ];
    var polygon = new qq.maps.Polygon({
        path:initPath,
        map: map,
        editable: true
    });
    var infoWin = new qq.maps.InfoWindow({
        content: '北区局',
        position: center,
        map: map
    });
    infoWin.open('北区局');
    qq.maps.event.addListener(polygon,'rightclick',function(event) {
        var latLngs = polygon.getPath();
        var fso = new ActiveXObject("Scripting.FileSystemObject");
        var file = fso.OpenTextFile("C:/path.txt",8,true);
        var length = latLngs.getLength();
        for (var i = 0; i < length - 1; i++) {
            var latLng = latLngs.getAt(i);
            var lat = latLng.getLat().toFixed(5),
                lng = latLng.getLng().toFixed(5);
            file.WriteLine(lat+','+lng);
            file.Close();
        };
    });
    qq.maps.event.addListener(map,'mousemove',function(event) {
        var lat = event.latLng.getLat().toFixed(5),
            lng = event.latLng.getLng().toFixed(5);
        document.getElementById("info").innerHTML = lat+','+lng;
    });
    qq.maps.event.addListener(map,'click',function(event) {
        var lat = event.latLng.getLat().toFixed(5),
            lng = event.latLng.getLng().toFixed(5);
        var fso = new ActiveXObject("Scripting.FileSystemObject");
        var file = fso.OpenTextFile("C:/click.txt",8,true);
        file.WriteLine(lat+','+lng);
        file.Close();
        document.getElementById("info").innerHTML = lat+','+lng;
    });
}
</script>
</head>
<body onload="init()">
<div style="width:100%;height:95%" id="container"></div>
<div style="width:100%;" id="info"></div>
</body>
</html>
