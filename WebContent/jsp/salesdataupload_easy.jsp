<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

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

		xmlHttp.open("post", "uploadSalesData", true);
		xmlHttp.setRequestHeader("Content-Type",
				"multipart/form-data;boundary=index");
		xmlHttp.send(formData);
		
	}
	
</script>

<h2 class="contentTitle">通过Excel上传营销数据</h2>

<div class="pageContent" style="margin: 0 10px" layoutH="50">
	<select class="combox" class="required" name="field">
		<option value="0">请选择</option>
		<option value="1">固话到达</option>
		<option value="2">宽带到达</option>
		<option value="3">宽带新装</option>
		<option value="4">宽带拆机</option>
		<option value="5">宽带移机（装）</option>
		<option value="6">宽带移机（拆）</option>
		<option value="7">宽带在途订单</option>
	</select>
	<form method="post" action="uploadSalesData" enctype="multipart/form-data">
		<input type="file" id="uploadfile" name="uploadfile" />
		<input type="button" value="上传" onclick="upload()" />
		<span id="message"></span>
	</form>
</div>