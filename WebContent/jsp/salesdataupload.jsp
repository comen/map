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

<script type="text/javascript">
	function setFormData(obj) {
		$("#file_upload").uploadify("settings", "formData", { 'salesDataType': obj.value }); 
	}
</script>

<h2 class="contentTitle">通过Excel上传营销数据</h2>

<div class="pageContent" style="margin: 0 10px" layoutH="50">

	<input id="file_upload" type="file" name="image"
		uploaderOption="{
			swf:'../dwz/uploadify/scripts/uploadify.swf',
			uploader:'uploadSalesData',
			progressData:'speed',
			queueID:'fileQueue',
			buttonImage:'../dwz/uploadify/img/add.jpg',
			buttonClass:'my-uploadify-button',
			fileTypeDesc:'Excel file',
			fileTypeExts:'*.xls',
			width:102,
			auto:false,
			multi:true
		}" />

	<select class="combox" name="salesDataType" id="salesDataType" onchange="setFormData(this)">
		<option value="">请选择</option>
		<option value="telephoneArrive">固话到达</option>
		<option value="broadbandArrive">宽带到达</option>
		<option value="broadbandNew">宽带新装</option>
		<option value="broadbandRemove">宽带拆机</option>
		<option value="broadbandMoveSetup">宽带移机（装）</option>
		<option value="broadbandMoveUnload">宽带移机（拆）</option>
		<option value="broadbandOrderInTransit">宽带在途订单</option>
		<option value="additional_1">附加字段</option>
	</select>
	
	<span class="inputInfo">仅支持 Microsoft Excel 97-2003 Worksheet(.xls)</span>
	<div id="fileQueue" class="fileQueue"></div>

	<input type="image" src="../dwz/uploadify/img/upload.jpg"
		onclick="$('#file_upload').uploadify('upload', '*');" />
	<input type="image" src="../dwz/uploadify/img/cancel.jpg"
		onclick="$('#file_upload').uploadify('cancel', '*');" />

</div>