<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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

<%
	String uid = (String)request.getParameter("uid");
	String roleDesc = (String)request.getParameter("roledesc");
	String realName = (String)request.getParameter("realname");
	String department = (String)request.getParameter("department");
	String createDate = (String)request.getParameter("createdate");
%>

<div class="pageContent">
	<form method="post" action="editUser"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名：</label> <input name="username" class="required"
					type="text" size="30" minlength="11" maxlength="11" alt="请输入手机号"
					value="<%=uid%>" readonly="readonly" />
			</p>
			<p>
				<label>角色：</label> <select name="role" class="required combox">
					<option value="1"
						<% if(roleDesc.equals("系统管理员")) {out.print("selected");} %>>系统管理员</option>
					<option value="2"
						<% if(roleDesc.equals("网格数据管理员")) {out.print("selected");} %>>网格数据管理员</option>
					<option value="3"
						<% if(roleDesc.equals("营销数据管理员")) {out.print("selected");} %>>营销数据管理员</option>
					<option value="4"
						<% if(roleDesc.equals("普通用户")) {out.print("selected");} %>>普通用户</option>
				</select>
			</p>
			<p>
				<label>重置密码为：</label> <input name="resetpassword" class="required"
					size="30" minlength="6" maxlength="20" value="111111"
					alt="请输入6-20位初始密码" />
			</p>
			<p>
				<label>姓名：</label> <input name="realname" type="text" size="30"
					value="<%=realName%>" />
			</p>
			<p>
				<label>所属部门：</label> <input name="department" type="text" size="30"
					value="<%=department%>" />
			</p>
			<p>
				<label>账户开通日期：</label> <input name="createdate" type="text"
					size="30" value="<%=createDate%>" readonly="true" />
			</p>
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
