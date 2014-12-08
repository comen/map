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
	String longitude = "";
	String latitude = "";
	String address = request.getParameter("address");
	if (address == null || address.equals("")) {
		return;
	}
	out.println("<input type=\"hidden\" id=\"address\" value=\"" + address + "\">");
%>

<script type="text/javascript">
	getCoordinates($("#address").val());
	$("#address").remove();

	function getCoordinates(address) {
		var formData = new FormData();
		formData.append("address", address);
		
		$.ajax({
			url: "fetchCoordinates",
			type: "POST",
			data: formData,
			processData: false,  // 告诉jQuery不要去处理发送的数据
			contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
			success: function(responseText) {
				var $longitude = $("#longitude");
				var $latitude = $("#latitude");
				var coordinates = eval(responseText);
				if (coordinates.length > 0) {
					var src = "map.jsp?longitude=" + coordinates[0].longitude + "&latitude=" + coordinates[0].latitude;
					$(".pageFormContent").find("iframe").attr("src", src);
				}
			}
		});
	}
</script>

<h2 class="contentTitle">请编辑网格在地图中的区域：</h2>
<input type="hidden" id="longitude" value="">
<input type="hidden" id="latitude" value="">
<div class="pageContent">
	<div class="pageFormContent" layoutH="97">
		<iframe src="map.jsp" style="width:100%;height:100%;" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>
	</div>
	<div class="formBar">
		<ul>
			<!-- <li><div class="buttonActive">
					<div class="buttonContent">
						<button type="submit">确定</button>
					</div>
				</div></li> -->
			<li><div class="button">
					<div class="buttonContent">
						<button class="close" type="button">关闭</button>
					</div>
				</div></li>
		</ul>
	</div>
</div>
