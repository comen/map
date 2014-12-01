<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="pageHeader">
	<p>说明：</p>
	<p>&nbsp;</p>
	<p>（1）日阈值是指按日显示时超出阈值会突出显示，环比阈值和同比阈值是指按周/月显示时环比/同比超出环比/同比阈值会突出显示；</p>
	<p>&nbsp;</p>
	<p>（2）若不需要超出阈值时突出显示的请在阈值处输入“*”；</p>
	<p>&nbsp;</p>
	<p>（3）如到达数等只按日统计的数据请勾选“只按日显示”，不勾选“只按日显示”按日/周/月均可显示；当勾选“只按日显示”时，“类别”一列不可用；</p>
	<p>&nbsp;</p>
	<p>（4）“宽带新装”和“宽带移机（装）”需设置为增值类，“宽带拆机”和“宽带移机（拆）”需设置为减值类；</p>
	<p>&nbsp;</p>
	<p>（5）“删除”表示地图上不在显示该数据字段，“正常”表示在地图上显示该数据字段。</p>
	<p>&nbsp;</p>
</div>
<div class="pageContent">
	<table class="table" width="900" layoutH="138">
		<thead>
			<tr>
				<th align="center" width="100">编号</th>
				<th align="center" width="200">营销数据字段</th>
				<th align="center" width="100">只按日显示</th>
				<th align="center" width="100">绝对值阈值</th>
				<th align="center" width="100">环比阈值（%）</th>
				<th align="center" width="100">同比阈值（%） </th>
				<th align="center" width="100">类别</th>
				<th align="center" width="100">状态</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="field1">
				<td>1</td>
				<td><input style="text-align:center" type="text" size="27" value="固话到达数" onchange="testAlert('test')" /></td>
				<td><input type="checkbox" checked="checked" /></td>
				<td><input id="field1_threshold0" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold1" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold2" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="role" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" target="ajaxToDo" title="确定要删除吗?" onmouseover="showTooltip(this)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr target="sid_user" rel="field2">
				<td>2</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带到达数" /></td>
				<td><input type="checkbox" checked="checked" /></td>
				<td><input id="field1_threshold0" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold1" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold2" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="role" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr target="sid_user" rel="field3">
				<td>3</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带新装" /></td>
				<td><input type="checkbox" /></td>
				<td><input id="field1_threshold0" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold1" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold2" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="role">
						<option value="1" selected="selected">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr target="sid_user" rel="field4">
				<td>4</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带拆机" /></td>
				<td><input type="checkbox" /></td>
				<td><input id="field1_threshold0" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold1" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold2" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="role">
						<option value="1">增值类</option>
						<option value="-1" selected="selected">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr target="sid_user" rel="field5">
				<td>5</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带移机（装）" /></td>
				<td><input type="checkbox" /></td>
				<td><input id="field1_threshold0" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold1" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold2" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="role">
						<option value="1" selected="selected">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr target="sid_user" rel="field6">
				<td>6</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带移机（拆）" /></td>
				<td><input type="checkbox"/></td>
				<td><input id="field1_threshold0" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold1" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold2" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="role">
						<option value="1">增值类</option>
						<option value="-1" selected="selected">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr target="sid_user" rel="field7">
				<td>7</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带在途订单" /></td>
				<td><input type="checkbox" checked="checked" /></td>
				<td><input id="field1_threshold0" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold1" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input id="field1_threshold2" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="role" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
		</tbody>
	</table>
</div>