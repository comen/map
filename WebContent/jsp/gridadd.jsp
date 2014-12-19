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

<div class="pageContent">
	<form method="post" action="addGrid" class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label>网格编号：</label> <input name="grid_code" class="required"
					type="text" size="30" />
			</div>
			<div class="unit">
				<label>网格名称：</label> <input name="grid_name" class="required"
					type="text" size="30" />
			</div>
			<div class="unit">
				<label>网格经理：</label> <input name="grid_manager" class="required"
					type="text" size="30" />
			</div>
			<div class="unit">
				<label>网格地址：</label> <input name="grid_address" class="required"
					type="text" size="50" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
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