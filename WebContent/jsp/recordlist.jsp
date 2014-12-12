<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- Validate if user login successfully. --%>
<%@include file="validate.jsp"%>

<%
	Boolean firstLoad = Boolean.parseBoolean(request.getParameter("firstload"));
	String pageNum = request.getParameter("pageNum");
	String totalCount = request.getParameter("totalCount");
	String pageNumShown = request.getParameter("pageNumShown");
	String searchGridCode = request.getParameter("searchGridCode");
	String searchChangeDate = request.getParameter("searchChangeDate");
	
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
	if (searchChangeDate == null) {
		searchChangeDate = "";
	}
%>

<script type="text/javascript">

	getRecordList("<%=searchGridCode%>","<%=searchChangeDate%>");

	function searchRecord() {
		$("#recordPageNum").val("1");
		$("#searchGridCode").val($("#gridcode").val());
		$("#searchChangeDate").val($("#changedate").val());
		var params = $("recordPagerForm").serializeArray();
		navTab.reload("recordlist.jsp?firstload=true", {data: params, callback: null});
		return;
	}

	function getRecordList(gridCode, changeDate) {
		var formData = new FormData();
		formData.append("gridcode", gridCode);
		formData.append("changedate", changeDate);
		
		$.ajax({
			url: "searchRecord",
			type: "POST",
			data: formData,
			processData: false,  // 告诉jQuery不要去处理发送的数据
			contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
			success: function(responseText) {
				generateRecordList(responseText);
			}
		});
	}
	
 	function generateRecordList(recordListArray) {
 		resetRows();
		var recordList = eval(recordListArray);
		
		/* Set for hidden fields */
		if (recordList.length > 200) {
			$("#recordTotalCount").val(200);
			$("#recordPageNumShown").val(10);
		} else {
			$("#recordTotalCount").val(recordList.length);
			$("#recordPageNumShown").val(Math.ceil(recordList.length/20));
		}
		
		<%
			if (firstLoad) {
		%>
				var params = $("recordPagerForm").serializeArray();
				navTab.reload("recordlist.jsp", {data: params, callback: null});
				return;
		<%
			}
		%>
		
		/* Set for pager info */
		$("#recordTotalCountText").text("每页最多显示20条，共计" + recordList.length + "条网格区域变动数据");
		$("#recordListPager").attr("totalCount", recordList.length);
		$("#recordListPager").attr("pageNumShown", Math.ceil(recordList.length/20));
		
		var start = ($("#recordPageNum").val() - 1) * 20;
		for (var i = 0; i < recordList.length; i++) {
			if (i - start >= 20 ) {
				continue;
			}
			/* Modify rows */
			var $tr = $("#record_" + (i - start));
			if ($tr) {
				$tr.show();
				$tr.attr("rel", recordList[i].GRID_CODE);
				
				/* Format created date */
				var changeDate = new Date(recordList[i].GRID_ERASE_DATETIME.$date);
				var year = changeDate.getFullYear().toString();
				var month = (changeDate.getMonth() + 1).toString();
				var day = changeDate.getDate().toString();
				if (day < 10) {
					day = "0" + day;
				}
				var dateStr = year + "-" + month + "-" + day;
				
				var index = 0;
				var $tr_children = $tr.children();
				$tr_children.each(function() {
					switch (index) {
					case 0:
						$(this).text(recordList[i].GRID_CODE);
						break;
					case 1:
						$(this).text(recordList[i].GRID_NAME);
						break;
					case 2:
						$(this).text(recordList[i].GRID_MANAGER);
						break;
					case 3:
						$(this).text(recordList[i].GRID_ADDRESS);
						break;
					case 4:
						$(this).text(dateStr);
						break;
					}
					index = index + 1;
				});
			}
		}
	}
 	
 	function resetRows() {
 		for (var i = 0; i < 20; i++) {
			var $tr = $("#record_" + i);
			if ($tr) {
				$tr.attr("rel", i);
				var $tr_children = $tr.children();
				$tr_children.each(function(){
					$(this).text("");
				});
				$tr.hide();
			}
 		}
 	}
</script>

<form id="pagerForm" name="recordPagerForm" method="post" action="recordlist.jsp">
	<input type="hidden" id="recordPageNum" name="pageNum" value="<%=pageNum%>" />
	<input type="hidden" id="recordTotalCount" name="totalCount" value="<%=totalCount%>" />
	<input type="hidden" id="recordPageNumShown" name="pageNumShown" value="<%=pageNumShown%>" />
	<input type="hidden" id="searchGridCode" name="searchGridCode" value="<%=searchGridCode%>" />
	<input type="hidden" id="searchChangeDate" name="searchChangeDate" value="<%=searchChangeDate%>" />
</form>

<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>网格编号：<input type="text" name="gridcode" id="gridcode" value="<%=searchGridCode%>" /></td>
				<td>变动日期：<input type="text" class="date" id="changedate" name="changedate" value="<%=searchChangeDate%>" readonly="true" /></td>
				<td>说明：系统只记录网格的删除、在地图中区域的变化等操作；网格变动后的环比、同比等数据不再精确，仅供大致参考。</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="searchRecord()">检索</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a id="delete" class="delete" href="deleteRecord?rid={sid_record}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th align="center" width="100">网格编号</th>
				<th align="center" width="100">网格名称</th>
				<th align="center" width="100">网格经理</th>
				<th align="center" width="100">网格地址</th>
				<th align="center" width="100">变动日期</th>
			</tr>
		</thead>
		<tbody id="gridList">
			<tr id="record_0" target="sid_record" rel="0">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_1" target="sid_record" rel="1">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_2" target="sid_record" rel="2">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_3" target="sid_record" rel="3">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_4" target="sid_record" rel="4">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_5" target="sid_record" rel="5">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_6" target="sid_record" rel="6">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_7" target="sid_record" rel="7">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_8" target="sid_record" rel="8">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_9" target="sid_record" rel="9">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_10" target="sid_record" rel="10">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_11" target="sid_record" rel="11">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_12" target="sid_record" rel="12">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_13" target="sid_record" rel="13">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_14" target="sid_record" rel="14">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_15" target="sid_record" rel="15">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_16" target="sid_record" rel="16">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_17" target="sid_record" rel="17">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_18" target="sid_record" rel="18">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_19" target="sid_record" rel="19">
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
			<!-- <span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select> <span>条，共${totalCount}条</span> -->
			<span id="recordTotalCountText"></span>
		</div>

		<div id="recordListPager" class="pagination" targetType="navTab" totalCount="<%=totalCount%>"
			numPerPage="20" pageNumShown="<%=pageNumShown%>" currentPage="<%=pageNum%>"></div>

	</div>
</div>