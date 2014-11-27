<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<form id="pagerForm" method="post" action="salesdatafields.jsp">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageContent">
	<table class="table" width="900" layoutH="138">
		<thead>
			<tr>
				<th align="center" width="100">编号</th>
				<th align="center" width="200">营销数据字段</th>
				<th align="center" width="200">环比阈值 (0 代表不设警示阈值)</th>
				<th align="center" width="200">同比阈值 (0 代表不设警示阈值)</th>
				<th align="center" width="100">状态</th>
				<th align="center" width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="field1">
				<td>1</td>
				<td>固话到达数</td>
				<td><input id="field1_threshold1" class="digits" style="text-align:center" type="text" size="5" value="0" /></td>
				<td><input id="field1_threshold2" class="digits" type="text" size="5" value="0" /></td>
				<td>正常</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="field2">
				<td>宽带到达数</td>
				<td>100</td>
				<td>正常</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="field3">
				<td>宽带到达数</td>
				<td>100</td>
				<td>正常</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="field4">
				<td>宽带新装</td>
				<td>100</td>
				<td>正常</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="field5">
				<td>宽带拆机</td>
				<td>100</td>
				<td>正常</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="field6">
				<td>宽带移机（装）</td>
				<td>100</td>
				<td>已删除</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="field7">
				<td>宽带移机（拆）</td>
				<td>100</td>
				<td>已删除</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="field8">
				<td>宽带在途订单</td>
				<td>100</td>
				<td>正常</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
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