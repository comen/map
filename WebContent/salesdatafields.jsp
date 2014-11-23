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
	<table class="table" width="600" layoutH="138">
		<thead>
			<tr>
				<th align="center" width="200">营销数据字段</th>
				<th align="center" width="200">警示阈值 (0 代表不设警示阈值)</th>
				<th align="center" width="100">状态</th>
				<th align="center" width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="18900838601">
				<td>固话到达数</td>
				<td>0</td>
				<td>正常</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="18900838602">
				<td>宽带到达数</td>
				<td>100</td>
				<td>正常</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="18900838602">
				<td>宽带到达数</td>
				<td>100</td>
				<td>正常</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="18900838603">
				<td>宽带新装</td>
				<td>100</td>
				<td>正常</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="18900838604">
				<td>宽带拆机</td>
				<td>100</td>
				<td>正常</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="18900838605">
				<td>宽带移机（装）</td>
				<td>100</td>
				<td>已删除</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="18900838606">
				<td>宽带移机（拆）</td>
				<td>100</td>
				<td>已删除</td>
				<td>
					<a title="编辑" target="dialog" href="salesdatafieldsedit.jsp?id=xxx" width="550" height="200" class="btnEdit">编辑</a>
				</td>
			</tr>
			<tr target="sid_user" rel="1890083867">
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