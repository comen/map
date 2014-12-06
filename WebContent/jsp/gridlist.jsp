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

<script type="text/javascript">
	getGridList("");	// Search all grids

	function search() {
		getGridList($("#gridcode").val());
	}

	function getGridList(gridCode) {
		var formData = new FormData();
		formData.append("gridcode", gridCode);
		
		$.ajax({
			url: "searchGrid",
			type: "POST",
			data: formData,
			processData: false,  // 告诉jQuery不要去处理发送的数据
			contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
			success: function(responseText) {
				generateGridList(responseText);
			}
		});
	}
	
 	function generateGridList(gridListArray) {
 		resetRows();
		var gridList = eval(gridListArray);
		for (var i = 0; i < gridList.length; i++) {
			/* Modify rows */
			var $tr = $("#gird_" + i);
			if ($tr) {
				$tr.show();
				$tr.attr("rel", gridList[i].GRID_CODE);
				
				var index = 0;
				var $tr_children = $tr.children();
				$tr_children.each(function() {
					switch (index) {
					case 0:
						$(this).text(gridList[i].GRID_CODE);
						break;
					case 1:
						$(this).text(gridList[i].GRID_NAME);
						break;
					case 2:
						$(this).text(gridList[i].GRID_MANAGER);
						break;
					case 3:
						$(this).text(gridList[i].GRID_ADDRESS);
						break;
					case 4:
						var href = $(this).find('a').attr("href");
						$(this).find('a').attr("href", href + gridList[i].GRID_ADDRESS);
						var title = gridList[i].GRID_CODE + " " + gridList[i].GRID_NAME;
						$(this).find('a').attr("title", title);
						break;
					}
					index = index + 1;
				});
			}
		}
	}
 	
 	function resetRows() {
 		for (var i = 0; i < 20; i++) {
			var $tr = $("#gird_" + i);
			if ($tr) {
				$tr.attr("rel", i);
				var $tr_children = $tr.children();
				var index = 0;
				$tr_children.each(function(){
					switch (index) {
					case 0:
					case 1:
					case 2:
					case 3:
						$(this).text("");
						break;
					case 4:
						$(this).find('a').attr("href", "grideditinmap.jsp?address=");
						$(this).find('a').attr("title", "");
						break;
					}
					index = index + 1;
				});
				$tr.hide();
			}
 		}
 	}
</script>

<form id="pagerForm" method="post" action="gridlist.jsp">
	<input type="hidden" name="status" value="${param.status}" />
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<!-- <form onsubmit="return navTabSearch(this);" action="gridlist.jsp"
		method="post"> -->
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>网格编号：<input type="text" name="gridcode" id="gridcode" /></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="search()">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="gridadvsearch.jsp" target="dialog" height="360" mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	<!-- </form> -->
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="gridadd.jsp" target="dialog" title="添加网格"
				height="300"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="deleteGrid?gid={sid_grid}"
				target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="gridedit.jsp?gid={sid_grid}"
				target="dialog" height="300"><span>修改</span></a></li>
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
				<th align="center" width="100">网格地址</th>
				<th align="center" width="100">操作</th>
			</tr>
		</thead>
		<tbody id="gridList">
			<tr id="gird_0" target="sid_grid" rel="0">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_1" target="sid_grid" rel="1">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_2" target="sid_grid" rel="2">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_3" target="sid_grid" rel="3">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_4" target="sid_grid" rel="4">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_5" target="sid_grid" rel="5">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_6" target="sid_grid" rel="6">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_7" target="sid_grid" rel="7">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_8" target="sid_grid" rel="8">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_9" target="sid_grid" rel="9">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_10" target="sid_grid" rel="10">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_11" target="sid_grid" rel="11">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_12" target="sid_grid" rel="12">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_13" target="sid_grid" rel="13">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_14" target="sid_grid" rel="14">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_15" target="sid_grid" rel="15">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_16" target="sid_grid" rel="16">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_17" target="sid_grid" rel="17">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_18" target="sid_grid" rel="18">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
			</tr>
			<tr id="gird_19" target="sid_grid" rel="19">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="grideditinmap.jsp?address=" target="dialog" width="800" height="600" title="">{编辑地图区域}</a></td>
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