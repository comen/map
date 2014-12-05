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
	out.println("<input type=\"hidden\" id=\"uid\" value=\"" + uid +"\">");
%>

<script type="text/javascript">
	var formData = new FormData();
	formData.append("username", $("#uid").val());
	
	$.ajax({
	  url: "searchUser",
	  type: "POST",
	  data: formData,
	  processData: false,  // 告诉jQuery不要去处理发送的数据
	  contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
	  success: function(data) {
		  populateUserAttribute(data);
	  }
	});
	
	function populateUserAttribute(userListArray) {
		var userList = eval(userListArray);
		for (var i = 0; i < userList.length; i++) {
			if (userList[i].username != $("#uid").val()) {
				continue;
			}
			/* Format created date */
			var createDate = new Date(userList[i].createdate);
			var year = createDate.getFullYear().toString();
			var month = (createDate.getMonth() + 1).toString();
			var day = createDate.getDate();
			if (day < 10) {
				day = "0" + day;
			}
			var createDateStr = year + "-" + month + "-" + day;
			$("#userCreateDate").val(createDateStr);
			$("#realname").val(userList[i].realname);
			$("#department").val(userList[i].department);
			if (userList[i].role == null) {
				/* Sometimes role attribute will be removed because of no reason, 
				 * if so it will be set default as normal user. */
				$("#userRole").val("4");
			} else {
				$("#userRole").val(userList[i].role.toString());
			}
		}
	}
	
	function save() {
		var formData = new FormData();
		formData.append("username", $("#uid").val());
		formData.append("role", $("#role").val());
		formData.append("resetpassword", $("#resetpassword").val());
		formData.append("realname", $("#realname").val());
		formData.append("department", $("#department").val());
		
		$.ajax({
		  url: "editUser",
		  type: "POST",
		  data: formData,
		  processData: false,  // 告诉jQuery不要去处理发送的数据
		  contentType: false   // 告诉jQuery不要去设置Content-Type请求头
		});
	}
</script>

<div class="pageContent">
	<form method="post" action="editUser" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名：</label> <input name="username" class="required"
					type="text" size="30" minlength="11" maxlength="11" alt="请输入手机号"
					value="<%=uid%>" readonly="readonly" />
			</p>
			<p>
				<label>角色：</label> <select name="role" id="userRole">
					<option value="1">系统管理员</option>
					<option value="2">网格数据管理员</option>
					<option value="3">营销数据管理员</option>
					<option value="4">普通用户</option>
				</select>
			</p>
			<p>
				<label>重置密码为：</label> <input name="resetpassword" id="resetpassword" class="required"
					size="30" minlength="6" maxlength="20" value="111111"
					alt="请输入6-20位初始密码" />
			</p>
			<p>
				<label>姓名：</label> <input name="realname" id="realname" type="text" size="30"
					value="" />
			</p>
			<p>
				<label>所属部门：</label> <input name="department" id="department" type="text" size="30"
					value="" />
			</p>
			<p>
				<label>账户开通日期：</label> <input name="createdate" id="userCreateDate" type="text"
					size="30" value="" readonly="true" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="save()">保存</button>
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
