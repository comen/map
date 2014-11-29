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

<form id="pagerForm" method="post" action="userlist.jsp?role=<%=roleSeleted%>">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form action="userlist.jsp?role=<%=roleSeleted%>" method="post" onsubmit="return navTabSearch(this);">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户名：<input type="text" name="username" />
				</td>
				<!-- <td>
					<select class="combox" name="office">
						<option value="all">所有单位</option>
						<option value="central_office">北区局</option>
						<option value="suboffice_one">北区一分局</option>
						<option value="suboffice_two">北区二分局</option>
						<option value="suboffice_three">北区三分局</option>
					</select>
				</td> -->
				<td>
					账户开通日期：<input type="text" class="date" name="createdate"  readonly="true" />
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="useradvsearch.jsp" target="dialog" height="360" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="useradd.jsp?role=<%=roleSeleted%>" target="dialog" height="360" ><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="deleteUser?uid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a id="edit" class="edit" href="useredit.jsp?uid={sid_user}" target="dialog" height="360"><span>修改</span></a></li>
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
			<tr id="18900838601" target="sid_user" rel="18900838601" onclick="updateHref(this, 'edit')">
				<td id="username">18900838601</td>
				<td id="role">系统管理员</td>
				<td id="realname">陈奕迅</td>
				<td id="department">北区局</td>
				<td id="createdate">2014-10-21</td>
			</tr>
			<tr id="15900838379" target="sid_user" rel="15900838379" onclick="updateHref(this, 'edit')">
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
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>
