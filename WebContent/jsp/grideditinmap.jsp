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

<h2 class="contentTitle">请编辑网格在地图中的区域：</h2>

<div class="pageContent">
	<div class="pageFormContent" layoutH="97">
		<iframe src="map.jsp?gridcode=${param.gridcode}" style="width:100%;height:100%;" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>
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
