<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<form id="pagerForm" method="post" action="gridlist.jsp">
	<input type="hidden" name="status" value="${param.status}"> <input
		type="hidden" name="keywords" value="${param.keywords}" /> <input
		type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${model.numPerPage}" /> <input type="hidden"
		name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="gridlist.jsp"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>网格编号：<input type="text" name="grid_code" />
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
					<li><a class="button" href="gridadvsearch.jsp" target="dialog"
						height="360" mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="gridadd.jsp" target="dialog"
				height="360"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="ajaxDone.jsp?gid={sid_grid}"
				target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="gridedit.jsp?gid={sid_grid}"
				target="dialog" height="360"><span>修改</span></a></li>
			<!--
			<li class="line">line</li>
			<li><a class="icon" href="./dwz/demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			 -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th align="center" width="100">网格编号</th>
				<th align="center" width="100">网格名称</th>
				<th align="center" width="100">网格经理</th>
				<th align="center" width="100">网格对应地图地址</th>
				<th align="center" width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_grid" rel="BQ-GQ-BZC-1000">
				<td>BQ-GQ-BZC-1000</td>
				<td>耀光国际</td>
				<td>李健</td>
				<td>祁连山南路2888号</td>
				<td><a href="grideditinmap.jsp" target="dialog" width="800"
					height="600" title="BQ-GQ-BZC-1000 耀光国际" rel="salesdatalist">{编辑地图区域}</a></td>
			</tr>
			<tr target="sid_grid" rel="BQ-GQ-BZC-2017">
				<td>BQ-GQ-BZC-2017</td>
				<td>大众物流</td>
				<td>李健</td>
				<td>绥德路二弄24号</td>
				<td><a href="grideditinmap.jsp" target="dialog" width="800"
					height="600" title="BQ-GQ-BZC-2017 大众物流" rel="salesdatalist">{编辑地图区域}</a></td>
			</tr>
			<tr target="sid_grid" rel="BQ-GQ-DAH-1003">
				<td>BQ-GQ-DAH-1003</td>
				<td>大华第一坊</td>
				<td>沈旭东</td>
				<td>大华二路316号</td>
				<td><a href="grideditinmap.jsp" target="dialog" width="800"
					height="600" title="BQ-GQ-DAH-1003 大华第一坊" rel="salesdatalist">{编辑地图区域}</a></td>
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