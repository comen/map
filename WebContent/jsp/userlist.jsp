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

<script type="text/javascript">
	getUserList("", 0, "");	// Search all users
	
	function search() {
		getUserList($("#username").val(), $("#role").val(), $("#createdate").val());
	}

	function getUserList(userName, role, createDate) {
		var formData = new FormData();
		formData.append("username", userName);
		formData.append("role", role);
		formData.append("createdate", createDate);
		
		$.ajax({
			url: "searchUser",
			type: "POST",
			data: formData,
			processData: false,  // 告诉jQuery不要去处理发送的数据
			contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
			success: function(responseText) {
				generateUserList(responseText);
			}
		});
	}
	
 	function generateUserList(userListArray) {
		var $parent = $("#userList");
		var userList = eval(userListArray);
		for (var i = 0; i < 20; i++) {
			/* If userList.length is less than predefined 20 <tr>, reset the rest <tr> */
			if (i >= userList.length) {
				var $tr = $("#" + i);
				if ($tr) {
					$tr.attr("rel", i);
					var $tr_children = $tr.children();
					$tr_children.each(function(){
						$(this).text("");
					});
					$tr.hide();
				}
				continue;
			}
			/* Get role description */
			var role = userList[i].role;
			var roleDesc;
			switch(role) {
			case 1:
				roleDesc = "系统管理员";
				break;
			case 2:
				roleDesc = "网格数据管理员";
				break;
			case 3:
				roleDesc = "营销数据管理员";
				break;
			case 4:
				roleDesc = "普通用户";
				break;
			default:
				roleDesc = "未知角色";
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
			/* Modify cells */
			var $tr = $("#" + i);
			if ($tr) {
				$tr.attr("rel", userList[i].username);
				$tr.show();
				var $tr_children = $tr.children();
				var index = 0;
				$tr_children.each(function() {
					switch (index) {
					case 0:
						$(this).text(userList[i].username);
						break;
					case 1:
						$(this).text(roleDesc);
						break;
					case 2:
						$(this).text(userList[i].realname);
						break;
					case 3:
						$(this).text(userList[i].department);
						break;
					case 4:
						$(this).text(createDateStr);
						break;
					}
					index = index + 1;
				});
			}
		}
	}
</script>

<form id="pagerForm" method="post" action="userlist.jsp">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="username" value="${param.username}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<!-- <form action="userlist.jsp" method="post" onsubmit="search()"> -->
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>用户名：<input id="username" type="text" name="username" /></td>
					<td>
						<select class="combox" id="role" name="role">
							<option value="0">所有角色</option>
							<option value="1">系统管理员</option>
							<option value="2">网格数据管理员</option>
							<option value="3">营销数据管理员</option>
							<option value="4">普通用户</option>
						</select>
					</td>
					<td>账户开通日期：<input type="text" class="date" id="createdate" name="createdate" readonly="true" /></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="search()">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="useradvsearch.jsp" target="dialog"
						height="360" mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	<!-- </form>  -->
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="useradd.jsp"
				target="dialog" height="360"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a id="delete" class="delete" href="deleteUser?uid={sid_user}"
				target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a id="edit" class="edit" href="useredit.jsp?uid={sid_user}"
				target="dialog" height="360"><span>修改</span></a></li>
		</ul>
	</div>
	<table class="table" width="720" layoutH="138">
		<thead>
			<tr>
				<th align="center" width="150">用户名</th>
				<th align="center" width="150">角色</th>
				<th align="center" width="150">姓名</th>
				<th align="center" width="200">所属部门</th>
				<th align="center" width="120">账户开通日期</th>
			</tr>
		</thead>
		<tbody id="userList">
			<tr id="0" target="sid_user" rel="0">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="1" target="sid_user" rel="1">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="2" target="sid_user" rel="2">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="3" target="sid_user" rel="3">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="4" target="sid_user" rel="4">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="5" target="sid_user" rel="5">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="6" target="sid_user" rel="6">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="7" target="sid_user" rel="7">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="8" target="sid_user" rel="8">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="9" target="sid_user" rel="9">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="10" target="sid_user" rel="10">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="11" target="sid_user" rel="11">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="12" target="sid_user" rel="12">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="13" target="sid_user" rel="13">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="14" target="sid_user" rel="14">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="15" target="sid_user" rel="15">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="16" target="sid_user" rel="16">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="17" target="sid_user" rel="17">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="18" target="sid_user" rel="18">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="19" target="sid_user" rel="19">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select> <span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="200"
			numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
	
</div>
