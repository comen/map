<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- Validate if user login successfully. --%>
<%@include file="validate.jsp"%>

<%
	Boolean firstLoad = Boolean.parseBoolean(request.getParameter("firstload"));
	String pageNum = request.getParameter("pageNum");
	String totalCount = request.getParameter("totalCount");
	String pageNumShown = request.getParameter("pageNumShown");
	String r_searchGridCode = request.getParameter("r_searchGridCode");
	String r_searchChangeDate = request.getParameter("r_searchChangeDate");
	
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
	if (r_searchGridCode == null) {
		r_searchGridCode = "";
	}
	if (r_searchChangeDate == null) {
		r_searchChangeDate = "";
	}
%>

<script type="text/javascript">

	getRecordList("<%=r_searchGridCode%>","<%=r_searchChangeDate%>");

	function searchRecord() {
		$("#recordPageNum").val("1");
		$("#r_searchGridCode").val($("#r_gridcode").val());
		$("#r_searchChangeDate").val($("#r_changedate").val());
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
				var changeDate = new Date(recordList[i].GRID_DATETIME.$date);
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
 	
 	function addDateToURLParam(tr) {
 		var $tr = $(tr);
 		var href ="deleteRecord?rid={sid_record}&date=";
 		var index = 0;
		var $tr_children = $tr.children();
		$tr_children.each(function() {
			switch (index) {
			case 4:
				href = href + $(this).text();
				break;
			}
			index = index + 1;
		});
 		$("#deleteRecord").attr("href", href);
 	}
</script>

<form id="pagerForm" name="recordPagerForm" method="post" action="recordlist.jsp">
	<input type="hidden" id="recordPageNum" name="pageNum" value="<%=pageNum%>" />
	<input type="hidden" id="recordTotalCount" name="totalCount" value="<%=totalCount%>" />
	<input type="hidden" id="recordPageNumShown" name="pageNumShown" value="<%=pageNumShown%>" />
	<input type="hidden" id="r_searchGridCode" name="r_searchGridCode" value="<%=r_searchGridCode%>" />
	<input type="hidden" id="r_searchChangeDate" name="r_searchChangeDate" value="<%=r_searchChangeDate%>" />
</form>

<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<%-- <td>网格编号：<input type="text" name="r_gridcode" id="r_gridcode" value="<%=r_searchGridCode%>" /></td>
				<td>变动日期：<input type="text" class="date" id="r_changedate" name="r_changedate" value="<%=r_searchChangeDate%>" readonly="true" /></td> --%>
				<td>
					<p>说明：（1） 系统只记录网格的删除、在地图中区域的变化等操作；网格变动后的环比、同比等数据不再精确，仅供大致参考。</p><br />
					<p>（2）选中某条记录并删除时，将删除该网格编号在变动日期当天的所有变动记录。 </p><br />
				</td>
			</tr>
		</table>
		<!-- <div class="subBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="searchRecord()">检索</button>
						</div>
					</div>
				</li>
			</ul>
		</div> -->
	</div>
</div>
<div class="pageContent">
	<div class="panelBar">
		<%
			if (role == 1 || role == 2) {
		%>
		<ul class="toolBar">
			<li><a id="deleteRecord" class="delete" href="deleteRecord?rid={sid_record}" disabled="disabled" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
		<%
			}
		%>
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
		<tbody id="recordList">
			<tr id="record_0" target="sid_record" rel="0" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_1" target="sid_record" rel="1" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_2" target="sid_record" rel="2" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_3" target="sid_record" rel="3" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_4" target="sid_record" rel="4" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_5" target="sid_record" rel="5" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_6" target="sid_record" rel="6" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_7" target="sid_record" rel="7" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_8" target="sid_record" rel="8" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_9" target="sid_record" rel="9" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_10" target="sid_record" rel="10" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_11" target="sid_record" rel="11" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_12" target="sid_record" rel="12" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_13" target="sid_record" rel="13" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_14" target="sid_record" rel="14" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_15" target="sid_record" rel="15" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_16" target="sid_record" rel="16" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_17" target="sid_record" rel="17" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_18" target="sid_record" rel="18" onclick="addDateToURLParam(this)">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="record_19" target="sid_record" rel="19" onclick="addDateToURLParam(this)">
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