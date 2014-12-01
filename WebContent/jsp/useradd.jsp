<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- Validate if user login successfully. --%>
<%@include file="validate.jsp"%>

<%-- check authority --%>
<%
	if (role != 1) {
%>
<%@include file="noAuthorityError.jsp"%>
<%
		return;
	}
%>

<div class="pageContent">
	<form method="post" action="addUser" class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名：</label> <input type="text" name="username"
					class="required digits" size="30" minlength="11" maxlength="11"
					alt="请输入手机号" />
			</p>
			<p>
				<label>角色：</label>
				<select class="combox" name="role">
					<option value="1">系统管理员</option>
					<option value="2">网格数据管理员</option>
					<option value="3">营销数据管理员</option>
					<option value="4">普通用户</option>
				</select>
			</p>
			<p>
				<label>初始密码：</label> <input type="text" name="password"
					class="required" size="30" minlength="6" maxlength="20"
					value="111111" alt="请输入6-20位初始密码" />
			</p>
			<p>
				<label>姓名：</label> <input type="text" name="realname" size="30" />
			</p>
			<p>
				<label>所属部门：</label> <input type="text" name="department" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div>
				</li>
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
