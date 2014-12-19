<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- Validate if user login successfully. --%>
<%@include file="validate.jsp"%>

<%-- check authority --%>
<%
	if (role != 1) {
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
				<label>用户名：</label> <input id="adv_username" name="username" type="text" size="30" /> <span
					class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>姓名：</label> <input id="adv_realname" name="realname" type="text" size="30" /> <span
					class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>部门：</label> <input id="adv_department" name="department" type="text" size="30" /> <span
					class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>账户开通开始日期：</label> <input type="text" id="adv_startdate" name="startdate"
					class="date" maxDate="{%y}-%M-{%d}" size="30" readonly="readonly" /><a
					class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>账户开通结束日期：</label> <input type="text" id="adv_enddate" name="enddate"
					class="date" maxDate="{%y}-%M-{%d}" size="30" readonly="readonly" /><a
					class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="divider">divider</div>
			<div class="unit">
				<label>排序条件：</label>
				<select id="adv_sortuser" name="sortuser">
					<option value="u_asending">按用户名顺排</option>
					<option value="d_asending">按账户开通日期日期顺排</option>
					<option value="u_descending">按用户名倒排</option>
					<option value="d_descending">按账户开通日期日期倒排</option>
				</select>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="advSearchUser()">开始检索</button>
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
