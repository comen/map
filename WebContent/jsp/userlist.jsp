<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<form id="pagerForm" method="post" action="userlist.jsp">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="userlist.jsp" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户名：<input type="text" name="username" />
				</td>
				<td>
					<select class="combox" name="office">
						<option value="all">所有单位</option>
						<option value="central_office">北区局</option>
						<option value="suboffice_one">北区一分局</option>
						<option value="suboffice_two">北区二分局</option>
						<option value="suboffice_three">北区三分局</option>
						<option value="suboffice_four">北区四分局</option>
						<option value="suboffice_five">北区五分局</option>
					</select>
				</td>
				<td>
					账户开通日期：<input type="text" class="date" readonly="true" />
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
			<li><a class="add" href="useradd.jsp" target="dialog" height="360" ><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="ajaxDone.jsp?uid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="useredit.jsp?uid={sid_user}" target="dialog" height="360"><span>修改</span></a></li>
			<!--
			<li class="line">line</li>
			<li><a class="icon" href="./dwz/demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			 -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th align="center" width="90">用户名</th>
				<th align="center" width="100">角色</th>
				<th align="center" width="100">姓名</th>
				<th align="center" width="60">性别</th>
				<th align="center" width="150">所属单位</th>
				<th align="center" width="100">电子邮箱</th>
				<th align="center" width="80">账户开通日期</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="18900838601">
				<td>18900838601</td>
				<td>系统管理员</td>
				<td>陈奕迅</td>
				<td>男</td>
				<td>北区局</td>
				<td>eason.chan@outlook.com</td>
				<td>2014-10-21</td>
			</tr>
			<tr target="sid_user" rel="18900838602">
				<td>18900838602</td>
				<td>网格数据管理员</td>
				<td>周杰伦</td>
				<td>男</td>
				<td>北区二分局</td>
				<td>jay.chou@yahoo.com</td>
				<td>2014-11-22</td>
			</tr>
			<tr target="sid_user" rel="18900838603">
				<td>18900838603</td>
				<td>营销数据管理员</td>
				<td>谢娜</td>
				<td>女</td>
				<td>北区一分局</td>
				<td>xiena2014@gmail.com</td>
				<td>2014-11-11</td>
			</tr>
			<tr target="sid_user" rel="18900838604">
				<td>18900838604</td>
				<td>普通用户</td>
				<td>张杰</td>
				<td>男</td>
				<td>北区二分局</td>
				<td>1234567890@qq.com</td>
				<td>2014-11-22</td>
			</tr>
			<tr target="sid_user" rel="18900838605">
				<td>18900838605</td>
				<td>普通用户</td>
				<td>郭晶晶</td>
				<td>女</td>
				<td>北区二分局</td>
				<td>18900838605@189.com</td>
				<td>2014-11-22</td>
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
