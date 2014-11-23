<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上传数据</title>
<script type="text/javascript">
	var xmlHttp;
	function createXmlHttp() {
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	function callBack() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var responseText = xmlHttp.responseText;
			var usernameMsg = document.getElementById("message");
			usernameMsg.innerHTML = responseText;
		}
	}

	function upload() {
		createXmlHttp();
		xmlHttp.onreadystatechange = callBack;

		var formData = new FormData();
		var file = document.getElementById("uploadfile");
		formData.append("file", file.files[0]);

		xmlHttp.open("post", "upload", true);
		xmlHttp.setRequestHeader("Content-Type",
				"multipart/form-data;boundary=map");
		xmlHttp.send(formData);
	}
</script>
</head>
<body>
	<form method="post" action="upload" enctype="multipart/form-data">
		<input type="file" id="uploadfile" name="uploadfile" />
		<input type="button" value="上传" onclick="upload()" />
		<a href="map.jsp">地图</a><br /><span id="message"></span>
	</form>
</body>
</html>