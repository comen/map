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

<div class="pageContent">
	<form class="pageForm">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>网格编号：</label> <input id="adv_gridcode" name="gridcode" type="text" size="30" />
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>网格名称：</label> <input id="adv_gridname" name="gridname" type="text" size="30" />
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>网格经理：</label> <input id="adv_gridmanager" name="gridmanager" type="text" size="30" />
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>网格对应地图地址：</label> <input id="adv_gridaddress" name="gridaddress" type="text"
					size="50" /> <span class="inputInfo">关键字或全称</span>
			</div>
			<div class="divider">divider</div>
			<div class="unit">
				<label>排序条件：</label>
				<select id="adv_sortgrid" name="sortgrid">
					<option value="g_asending">按网格编号顺排</option>
					<option value="g_descending">按网格编号倒排</option>
				</select>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="advSearchGrid()">开始检索</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="reset">清空重输</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
