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

<script type="text/javascript">
	getFields();
	
	function getFields() {
		var formData = new FormData();
		formData.append("status", 0);
		
		$.ajax({
			url: "getSalesDataFields",
			type: "POST",
			data: formData,
			processData: false,  // 告诉jQuery不要去处理发送的数据
			contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
			success: function(responseText) {
				appendCheckboxGroup(responseText);
			}
		});
	}
	
	function appendCheckboxGroup(fieldsArray) {
		var $parentInUse = $("#groupInUse");
		var $parentNotInUse = $("#groupNotInUse");
		var fields = eval(fieldsArray);
		for (var i = 0; i < fields.length; i++) {
			if(fields[i].status > 0) {
				var $checkbox = $("<label><input type=\"checkbox\" class=\"c1\" name=\"c1\" value=\"" + fields[i].field + "\" />" + fields[i].description + "</label>");
				$parentInUse.append($checkbox);
			} else {
				var $checkbox = $("<label><input type=\"checkbox\" class=\"c2\" name=\"c2\" value=\"" + fields[i].field + "\" />" + fields[i].description + "</label>");
				$parentNotInUse.append($checkbox);
			}
		}
	}
	
	function deleteData() {
		$("#salesdatadelete_msg").text("");
		
		var formData = new FormData();
		var salesData = "";
		
		/* 营销数据日期 */
		if ($("#selecteddate").val() == "") {
			alert("请选择日期！");
			return;
		} else {
			formData.append("selectedDate", $("#selecteddate").val());
		}
		
		/* 营销数据类型 - 已启用字段 */
		$("input[type='checkbox'][name='c1']").each(function() {
            if ($(this).attr("checked") == "checked") {
            	if ($(this).attr("class") != "checkboxCtrl") {
            		salesData = salesData + $(this).val() + ",";
            	}
            }
        });
		
		/* 营销数据类型 - 未启用字段 */
		$("input[type='checkbox'][name='c2']").each(function() {
			if ($(this).attr("checked") == "checked") {
            	if ($(this).attr("class") != "checkboxCtrl") {
            		salesData = salesData + $(this).val() + ",";
            	}
            }
        });
		
		if (salesData == "") {
			alert("请选择营销数据类型！");
			return;
		} else {
			salesData = salesData.substring(0, salesData.length - 1);
			formData.append("salesData", salesData);
		}
		
		/* 日期类型 */
		$("input[type='radio'][name='r1']").each(function() {
            if ($(this).attr("checked") == "checked") {
            	formData.append("dateType", $(this).val());
            }
        });
		
		if (confirm('您确定要执行删除操作吗？') == true) {
			$.ajax({
				url: "deleteSalesData",
				type: "POST",
				data: formData,
				processData: false,  // 告诉jQuery不要去处理发送的数据
				contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
				success: function(responseText) {
					$("#salesdatadelete_msg").text(responseText);
				}
			});
		}
	}
</script>

<div class="pageContent" style="margin: 0 10px" layoutH="50">
	<br />
	<label>1. 请选择日期类型：</label>
	<label><input type="radio" value="uploadDate" name="r1" checked="checked" />上传日期</label>
	<label><input type="radio" value="completeDate" name="r1" />完工日期</label>
	<br /><br />
	<label>2. 请选择日期：</label>
	<input type="text" class="date" id="selecteddate" name="selecteddate" readonly="readonly" />
	<br /><br />
	<label>3. 请选择营销数据类型：</label>
	<br /><br />
	<div id="groupInUse">
		<span>已启用字段：</span>
		<label><input type="checkbox" class="checkboxCtrl" group="c1" />全选</label>&nbsp;|&nbsp;
	</div>
	<div class="divider"></div>
	<div id="groupNotInUse">
		<span>未启用字段：</span>
		<label><input type="checkbox" class="checkboxCtrl" group="c2" />全选</label>&nbsp;|&nbsp;
	</div>
	<br /><br />
	<div class="buttonActive">
		<div class="buttonContent">
			<button type="button" onclick="deleteData()">&nbsp;删除营销数据&nbsp;</button>
		</div>
	</div>
	<br /><br /><br />
	<span id="salesdatadelete_msg" style="color:red"></span>
	<br /><br /><br />
	<div>
		<h2>说明：</h2>
		<br />
		<p>1. 选中“上传日期”代表删除上传日期当天的营销数据，如：2014-12-31，表示删除2014年12月31日当天上传的营销数据（注意：重复上传的数据，系统将记录最后一次上传的日期作为该数据的“上传日期”）；</p>
		<br />
		<p>2. 选中“完工日期”代表删除完工日期当天的营销数据，如：2014-12-31，表示删除完工日期为2014年12月31日的营销数据；</p>
		<br />
		<p>3. 执行“删除营销数据”操作时，系统将清空所选日期下选中的营销字段数据。</p>
	</div>
</div>