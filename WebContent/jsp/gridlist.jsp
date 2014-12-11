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
	
	Boolean firstLoad = Boolean.parseBoolean(request.getParameter("firstload"));
	String pageNum = request.getParameter("pageNum");
	String totalCount = request.getParameter("totalCount");
	String pageNumShown = request.getParameter("pageNumShown");
	String searchGridCode = request.getParameter("searchGridCode");
	
	if (firstLoad == null) {
		firstLoad = false;
	}
	if (pageNum == null) {
		pageNum = "1";
	}
	if (totalCount == null) {
		totalCount = "";
	}
	if (pageNumShown == null) {
		pageNumShown = "";
	}
	if (searchGridCode == null) {
		searchGridCode = "";
	}
%>

<script type="text/javascript">

	getGridList("<%=searchGridCode%>");

	function searchGrid() {
		$("#gridPageNum").val("1");
		$("#searchGridCode").val($("#gridcode").val());
		var params = $("gridPagerForm").serializeArray();
		navTab.reload("gridlist.jsp?firstload=true", {data: params, callback: null});
		return;
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
		
		/* Set for hidden fields */
		if (gridList.length > 200) {
			$("#gridTotalCount").val(200);
			$("#gridPageNumShown").val(10);
		} else {
			$("#gridTotalCount").val(gridList.length);
			$("#gridPageNumShown").val(Math.ceil(gridList.length/20));
		}
		
		<%
			if (firstLoad) {
		%>
				var params = $("gridPagerForm").serializeArray();
				navTab.reload("gridlist.jsp", {data: params, callback: null});
				return;
		<%
			}
		%>
		
		/* Set for pager info */
		$("#gridTotalCountText").text("每页最多显示20条，共计" + gridList.length + "条网格数据");
		$("#gridListPager").attr("totalCount", gridList.length);
		$("#gridListPager").attr("pageNumShown", Math.ceil(gridList.length/20));
		
		var start = ($("#gridPageNum").val() - 1) * 20;
		for (var i = 0; i < gridList.length; i++) {
			if (i - start >= 20 ) {
				continue;
			}
			/* Modify rows */
			var $tr = $("#gird_" + (i - start));
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

<form id="pagerForm" name="gridPagerForm" method="post" action="gridlist.jsp">
	<input type="hidden" id="gridPageNum" name="pageNum" value="<%=pageNum%>" />
	<input type="hidden" id="gridTotalCount" name="totalCount" value="<%=totalCount%>" />
	<input type="hidden" id="gridPageNumShown" name="pageNumShown" value="<%=pageNumShown%>" />
	<input type="hidden" id="searchGridCode" name="searchGridCode" value="<%=searchGridCode%>" />
</form>

<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>网格编号：<input type="text" name="gridcode" id="gridcode" value="<%=searchGridCode%>" /></td>
				<td>说明：编辑网格的地图区域时，第一次右键点击地图表示进入编辑状态，再次右键点击地图表示保存编辑后的数据，期间拖动地图或刷新页面表示撤销编辑。</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="searchGrid()">检索</button>
						</div>
					</div></li>
				<li><a class="button" href="gridadvsearch.jsp" target="dialog" height="360" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
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
			<!-- <span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select> <span>条，共${totalCount}条</span> -->
			<span id="gridTotalCountText"></span>
		</div>

		<div id="gridListPager" class="pagination" targetType="navTab" totalCount="<%=totalCount%>"
			numPerPage="20" pageNumShown="<%=pageNumShown%>" currentPage="<%=pageNum%>"></div>

	</div>
</div>