<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- Validate if user login successfully. --%>
<%@include file="validate.jsp"%>

<%-- check authority --%>
<%
	if (role != 1 && role != 2) {
%>
<%@include file="noAuthorityError.jsp"%>
<%
		return;
	}
%>

<%
	String gid = (String)request.getParameter("gid");
	if (gid == null) {
		gid = "";
	}
	out.println("<input type=\"hidden\" id=\"gid\" value=\"" + gid +"\">");
%>

<script type="text/javascript">
	var formData = new FormData();
	formData.append("username", $("#uid").val());
	
	$.ajax({
	  url: "searchGrid",
	  type: "POST",
	  data: formData,
	  processData: false,  // 告诉jQuery不要去处理发送的数据
	  contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
	  success: function(data) {
		  populateGridAttribute(data);
	  }
	});
	
	function populateGridAttribute(gridListArray) {
		var gridList = eval(gridListArray);
		for (var i = 0; i < gridList.length; i++) {
			if (gridList[i].GRID_CODE != $("#gid").val()) {
				continue;
			}
			$("#original_grid_code").val(gridList[i].GRID_CODE);
			$("#grid_code").val(gridList[i].GRID_CODE);
			$("#grid_name").val(gridList[i].GRID_NAME);
			$("#grid_manager").val(gridList[i].GRID_MANAGER);
			$("#grid_address").val(gridList[i].GRID_ADDRESS);
		}
	}
</script>

<div class="pageContent">
	<form method="post" action="editGrid" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" id="original_grid_code" name="original_grid_code" />
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label>网格编号：</label> <input id="grid_code" name="grid_code" class="required"
					type="text" size="30" />
			</div>
			<div class="unit">
				<label>网格名称：</label> <input id="grid_name" name="grid_name" class="required"
					type="text" size="30" />
			</div>
			<div class="unit">
				<label>网格经理：</label> <input id="grid_manager" name="grid_manager" class="required"
					type="text" size="30" />
			</div>
			<div class="unit">
				<label>网格地址：</label> <input id="grid_address" name="grid_address" class="required"
					type="text" size="50" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
