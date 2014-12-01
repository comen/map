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

	var formData = new FormData();
	formData.append("username", "");
	formData.append("role", 0);
	formData.append("realname", "");
	formData.append("department", "");
	formData.append("createdate", "");

 	$.ajax({
		url : 'searchUser',
		type : 'POST',
		data : formData,
		async : true,
		cache : false,
		contentType : false,
		processData : false,
		success : function(respText) {
			//var users = eval(respText);
			//document.write(respText);
		}
	});

</script>

<form id="pagerForm" method="post" action="userlist.jsp">
	<input type="hidden" name="status" value="${param.status}"> <input
		type="hidden" name="keywords" value="${param.keywords}" /> <input
		type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${model.numPerPage}" /> <input type="hidden"
		name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form action="userlist.jsp" method="post"
		onsubmit="return navTabSearch(this);">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>用户名：<input type="text" name="username" /></td>
					<td>
						<select class="combox" name="role">
							<option value="0">所有角色</option>
							<option value="1">系统管理员</option>
							<option value="2">网格数据管理员</option>
							<option value="3">营销数据管理员</option>
							<option value="4">普通用户</option>
						</select>
					</td>
					<td>账户开通日期：<input type="text" class="date" name="createdate" readonly="true" />
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="useradvsearch.jsp" target="dialog"
						height="360" mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="useradd.jsp"
				target="dialog" height="360"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="deleteUser?uid={sid_user}"
				target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a id="edit" class="edit" href="useredit.jsp?uid={sid_user}"
				target="dialog" height="360"><span>修改</span></a></li>
		</ul>
	</div>
	<table class="table" width="700" layoutH="138">
		<thead>
			<tr>
				<th align="center" width="150">用户名</th>
				<th align="center" width="120">角色</th>
				<th align="center" width="120">姓名</th>
				<th align="center" width="200">所属部门</th>
				<th align="center" width="110">账户开通日期</th>
			</tr>
		</thead>
		<tbody>
			<tr id="18900838601" target="sid_user" rel="18900838601"
				onclick="updateHref(this, 'edit')">
				<td id="username">18900838601</td>
				<td id="role">系统管理员</td>
				<td id="realname">陈奕迅</td>
				<td id="department">北区局</td>
				<td id="createdate">2014-10-21</td>
			</tr>
			<tr id="15900838379" target="sid_user" rel="15900838379"
				onclick="updateHref(this, 'edit')">
				<td id="username">15900838379</td>
				<td id="role">普通用户</td>
				<td id="realname">陈奕迅2</td>
				<td id="department">北区局2</td>
				<td id="createdate">2014-10-31</td>
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
