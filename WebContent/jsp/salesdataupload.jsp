<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<style type="text/css" media="screen">
.my-uploadify-button {
	background: none;
	border: none;
	text-shadow: none;
	border-radius: 0;
}

.uploadify:hover .my-uploadify-button {
	background: none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>

<h2 class="contentTitle">通过Excel上传营销数据</h2>

<div class="pageContent" style="margin: 0 10px" layoutH="50">

	<input id="testFileInput2" type="file" name="image2"
		uploaderOption="{
			swf:'../dwz/uploadify/scripts/uploadify.swf',
			uploader:'../dwz/demo/common/ajaxDone.html',
			formData:{PHPSESSID:'xxx', ajax:1},
			queueID:'fileQueue',
			buttonImage:'../dwz/uploadify/img/add.jpg',
			buttonClass:'my-uploadify-button',
			fileTypeDesc:'Excel file',
			fileTypeExts:'*.xls;',
			width:102,
			auto:false,
			multi:true
		}" />
	<select class="combox" class="required" name="salesdatatype">
		<option value="0">请选择</option>
		<option value="1">固话到达</option>
		<option value="2">宽带到达</option>
		<option value="3">宽带新装</option>
		<option value="4">宽带拆机</option>
		<option value="5">宽带移机（装）</option>
		<option value="6">宽带移机（拆）</option>
		<option value="7">宽带在途订单</option>
	</select> <span class="inputInfo">仅支持 Microsoft Excel 97-2003 Worksheet
		(.xls)</span>

	<div id="fileQueue" class="fileQueue"></div>

	<input type="image" src="../dwz/uploadify/img/upload.jpg"
		onclick="$('#testFileInput2').uploadify('upload', '*');" /> <input
		type="image" src="../dwz/uploadify/img/cancel.jpg"
		onclick="$('#testFileInput2').uploadify('cancel', '*');" />

</div>