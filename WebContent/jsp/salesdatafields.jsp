<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- Validate if user login successfully. --%>
<%@include file="validate.jsp"%>

<%-- check authority --%>
<%
	if (role != 1 && role != 3) {
%>
<%@include file="noAuthorityError.jsp"%>
<%
		return;
	}
%>

<script type="text/javascript">
	getFields();
	
	function getFields() {
		var formData = new FormData();
		formData.append("status", 0);

		$.ajax({
			url: "getSalesDataFields",
			type: "POST",
			data: formData,
			processData: false,  // 告诉jQuery不要去处理发送的数据
			contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
			success: function(responseText) {
				appendToTable(responseText);
			}
		});
	}
	
	function appendToTable(fieldListArray) {
		var $parent = $("#fieldList");
		var fieldList = eval(fieldListArray);
		for (var i = 0; i < fieldList.length; i++) {
			var $tr = $("#field_" + i);
			if ($tr) {
				$tr.attr("rel", fieldList[i].field);
				var index = 0;
				var $tr_children = $tr.children();
				$tr_children.each(function() {
					switch (index) {
					case 0:
						$(this).text(i+1);
						break;
					case 1:
						$(this).find("input").val(fieldList[i].description);
						break;
					case 2:
						if (fieldList[i].onlyDay > 0) {
							$(this).find("input").attr("checked", true);
						} else {
							$(this).find("input").attr("checked", false);
						}
						break;
					case 3:
						$(this).find("input").val(fieldList[i].jueduizhiThreshold);
						break;
					case 4:
						$(this).find("input").val(fieldList[i].huanbiThreshold);
						break;
					case 5:
						$(this).find("input").val(fieldList[i].tongbiThreshold);
						break;
					case 6:
						if (fieldList[i].category > 0) {
							$(this).find("select").val(1);
						} else {
							$(this).find("select").val(-1);
						}
						if (fieldList[i].onlyDay > 0) {
							$(this).find("select").attr("disabled", true);
						} else {
							$(this).find("select").attr("disabled", false);
						}
						break;
					case 7:
						break;
					}
					index = index + 1;
				});
			}
/* 			var $tr = $("<tr id=\"field_0\"></tr>");
			var $td1 = $("<td>1</td>");
			var $td2 = $("<td><input name=\"description\" style=\"text-align:center\" type=\"text\" size=\"27\" value=\"固话到达数\" /></td>");
			var $td3 = $("<td><input name=\"onlyday\" type=\"checkbox\" checked=\"checked\" /></td>");
			var $td4 = $("<td><input name=\"jueduizhiThreshold\" style=\"text-align: center\" type=\"text\" size=\"10\" value=\"*\" /></td>");
			var $td5 = $("<td><input name=\"huanbiThreshold\" style=\"text-align: center\" type=\"text\" size=\"10\" value=\"*\" /></td>");
			var $td6 = $("<td><input name=\"tongbiThreshold\" style=\"text-align: center\" type=\"text\" size=\"10\" value=\"*\" /></td>");
			var $td7 = $("<td>" + 
							"<select name=\"status\" disabled=\"disabled\">" +
								"<option value=\"1\">增值类</option>" +
								"<option value=\"-1\">减值类</option>" +
							"</select>" +
						"</td>");
			var $td8 = $("<td><a href=\"javascript:void(0)\" target=\"ajaxToDo\" title=\"确定要删除吗?\" onclick=\"changeState(this)\">正常</a></td>");
			$tr.append($td1);
			$tr.append($td2);
			$tr.append($td3);
			$tr.append($td4);
			$tr.append($td5);
			$tr.append($td6);
			$tr.append($td7);
			$tr.append($td8);
			$parent.append($tr); */
		}
	}
</script>

<div class="pageHeader">
	<p>说明：（1）日阈值是指按日显示时超出阈值会突出显示，环比阈值和同比阈值是指按周/月显示时环比/同比超出环比/同比阈值会突出显示；</p>
	<p>&nbsp;</p>
	<p>（2）若不需要超出阈值时突出显示的请在阈值处输入“*”；</p>
	<p>&nbsp;</p>
	<p>（3）“xx到达数”等只按日统计的数据请勾选“只按日显示”，不勾选“只按日显示”按日/周/月均可显示；当勾选“只按日显示”时，“类别”一列将无效；</p>
	<p>&nbsp;</p>
	<p>（4）“宽带新装”和“宽带移机（装）”需设置为增值类，“宽带拆机”和“宽带移机（拆）”需设置为减值类；</p>
	<p>&nbsp;</p>
	<p>（5）“删除”表示地图上不在显示该数据字段，“正常”表示在地图上显示该数据字段。</p>
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
		<tbody id="fieldList">
			<tr id="field_0" target="sid_field" rel="telephoneArrive">
				<td>1</td>
				<td><input name="description" style="text-align:center" type="text" size="27" value="固话到达数" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" target="ajaxToDo" title="确定要删除吗?" onmouseover="showTooltip(this)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_1" target="sid_field" rel="broadbandArrive">
				<td>2</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带到达数" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_2" target="sid_field" rel="broadbandNew">
				<td>3</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带新装" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_3" target="sid_field" rel="broadbandRemove">
				<td>4</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带拆机" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_4" target="sid_field" rel="broadbandMoveSetup">
				<td>5</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带移机（装）" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_5" target="sid_field" rel="broadbandMoveUnload">
				<td>6</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带移机（拆）" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_6" target="sid_field" rel="broadbandOrderInTransit">
				<td>7</td>
				<td><input style="text-align: center" type="text" size="27" value="宽带在途订单" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_7" target="sid_field" rel="additional_1">
				<td>8</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段1" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_8" target="sid_field" rel="additional_2">
				<td>9</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段2" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_9" target="sid_field" rel="additional_3">
				<td>10</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段3" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_10" target="sid_field" rel="additional_4">
				<td>11</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段4" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_11" target="sid_field" rel="additional_5">
				<td>12</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段5" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_12" target="sid_field" rel="additional_6">
				<td>13</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段6" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_13" target="sid_field" rel="additional_7">
				<td>14</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段7" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_14" target="sid_field" rel="additional_8">
				<td>15</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段8" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_15" target="sid_field" rel="additional_9">
				<td>16</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段9" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_16" target="sid_field" rel="additional_10">
				<td>17</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段10" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_17" target="sid_field" rel="additional_11">
				<td>18</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段11" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_18" target="sid_field" rel="additional_12">
				<td>19</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段12" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
			<tr id="field_19" target="sid_field" rel="additional_13">
				<td>20</td>
				<td><input style="text-align: center" type="text" size="27" value="附加字段13" /></td>
				<td><input name="onlyday" type="checkbox" checked="checked" /></td>
				<td><input name="jueduizhiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="huanbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td><input name="tongbiThreshold" style="text-align: center" type="text" size="10" value="*" /></td>
				<td>
					<select name="category" disabled="disabled">
						<option value="1">增值类</option>
						<option value="-1">减值类</option>
					</select>
				</td>
				<td><a href="javascript:void(0)" onclick="changeState(this)">正常</a></td>
			</tr>
		</tbody>
	</table>
</div>