<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%-- Validate if user login successfully. --%>
<%@include file="validate.jsp" %>

<%-- check authority --%>
<%
	if (role != 1) {
%>
		<%@include file="noAuthorityError.jsp" %>
<%
		return;
	}
%>

<%
	String roleSeleted = request.getParameter("role").toString();
	String roleDesc = "";
	switch (roleSeleted) {
	case "1":
		roleDesc = "系统管理员";
		break;
	case "2":
		roleDesc = "网格数据管理员";
		break;
	case "3":
		roleDesc = "营销数据管理员";
		break;
	case "4":
		roleDesc = "普通用户";
		break;
	default:
		roleDesc = "未知角色";
		break;
	}
%>

<div class="pageContent">
	<form method="post" action="addUser" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名：</label>
				<input type="text" name="username" class="required digits" size="30" minlength="11" maxlength="11" alt="请输入手机号" />
			</p>
			<p>
				<label>角色：</label>
				<input type="hidden" name="role" value="<%=roleSeleted%>" />
				<span><%=roleDesc%></span>
			</p>
			<p>
				<label>初始密码：</label>
				<input type="text" name="password" class="required" size="30" minlength="6" maxlength="20" value="111111" alt="请输入6-20位初始密码" />
			</p>
			<p>
				<label>姓名：</label>
				<input type="text" name="realname" size="30" />
			</p>
			<p>
				<label>所属部门：</label>
			    <input type="text" name="department" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
