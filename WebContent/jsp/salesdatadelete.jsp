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
		
		if ($("#completiondate").val() == "") {
			alert("请选择营销数据完工日期！");
			return;
		} else {
			formData.append("date", $("#completiondate").val());
		}
		
		$("input[type='checkbox'][name='c1']").each(function() {
            if ($(this).attr("checked") == "checked") {
            	if ($(this).attr("class") != "checkboxCtrl") {
            		salesData = salesData + $(this).val() + ",";
            	}
            }
        });
		
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
	<label for="completiondate">请选择营销数据完工日期：</label>
	<input type="text" class="date" id="completiondate" name="completiondate" readonly="readonly" />
	<br /><br />
	<label>请选择营销数据类型：</label>
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
		<p>1. 执行“删除营销数据”操作时，系统将清空所选完工日期当天所有选中的营销字段数据。</p>
	</div>
</div>