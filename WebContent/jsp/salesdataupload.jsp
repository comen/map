<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- Validate if user login successfully. --%>
<%@include file="validate.jsp"%>

<%-- check authority --%>
<%
	if (role != 1 && role != 3) {
%>
<%@include file="noAuthorityError.jsp"%>
<%
		return;
	}
%>

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
	function doUpload() {
		if ($("#salesDataType").val() == "请选择") {
			alert('请选择您要上传的营销数据类型！');
		} else {
			if (confirm('请务必保证营销数据类型与上传文件中的数据一致！\n\n您确定要上传吗？') == true) {
				$('#file_upload').uploadify('upload', '*');
			}
		}
	}
</script>

<script type="text/javascript">
	getFields();
	
	function getFields() {
		var formData = new FormData();
		formData.append("status", 1);

		$.ajax({
			url: "getSalesDataFields",
			type: "POST",
			timeout: 600000,
			data: formData,
			processData: false,  // 告诉jQuery不要去处理发送的数据
			contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
			success: function(responseText) {
				appendOptionToCombox(responseText);
			}
		});
	}
	
	function appendOptionToCombox(fieldsArray) {
		var $parent = $("#salesDataType");
		var fields = eval(fieldsArray);
		for (var i = 0; i < fields.length; i++) {
			var $option = $("<option value='" + fields[i].field + "'>" + fields[i].description + "</option>");
			$parent.append($option);
		}
	}
</script>

<h2 class="contentTitle">通过Excel上传营销数据</h2>

<div class="pageContent" style="margin: 0 10px" layoutH="50">

	<input id="file_upload" type="file" name="image"
		uploaderOption="{
			fileSizeLimit:'30MB',
			swf:'../dwz/uploadify/scripts/uploadify.swf',
			uploader:'uploadSalesData',
			successTimeout: 600,
			progressData:'percentage',
			queueID:'fileQueue',
			buttonImage:'../dwz/uploadify/img/add.jpg',
			buttonClass:'my-uploadify-button',
			fileTypeDesc:'Excel file',
			fileTypeExts:'*.xls;*.xlsx',
			width:102,
			auto:false,
			multi:true
		}" />

	<select id="salesDataType" onchange="setFormData(this)">
		<option value="请选择">请选择</option>
	</select>
	<p>&nbsp;</p>
	<!-- <span class="inputInfo">仅支持 Microsoft Excel 97-2003 Worksheet(.xls)</span> -->
	<div id="fileQueue" class="fileQueue"></div>

	<input type="image" src="../dwz/uploadify/img/upload.jpg"
		onclick="doUpload()" />
	<input type="image" src="../dwz/uploadify/img/cancel.jpg"
		onclick="$('#file_upload').uploadify('cancel', '*');" />
	
	<div>
		<br /><br /><br /><br /><br /><br /><br /><br />
		<h2>Excel文件要求：</h2>
		<br />
		<p>1. “<span style="color:red">行政分局</span>”列必需；</p>
		<br />
		<p>2. “<span style="color:red">网格编号</span>”或“<span style="color:red">安装地址</span>”至少存在一列；当“网格编号”存在时系统将根据编号匹配其所属的网格（执行效率高），“网格编号”不存在时根据“安装地址”查找其所属网格（执行效率低）；</p>
		<br />
		<p>3. “<span style="color:red">完工时间</span>”列可选；当“完工时间”存在时，系统将以该时间作为营销数据的生成时间，否则将以上传当天的日期作为营销数据的生成时间；</p>
	</div>

</div>

