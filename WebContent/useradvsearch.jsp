<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="userlist.jsp" class="pageForm" onsubmit="return navTabSearch(this);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>用户名：</label>
				<input id="username" type="text" size="30" />
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>姓名：</label>
				<input id="realname" type="text" size="30" />
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>所属单位：</label>
				<input id="office" type="text" size="30" />
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>电子邮箱：</label>
				<input id="email" type="text" size="30" />
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>账户开通开始日期：</label>
				<input type="text" name="startDate" class="date" maxDate="{%y}-%M-{%d}" size="30" /><a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>账户开通结束日期：</label>
				<input type="text" name="endDate" class="date" maxDate="{%y}-%M-{%d}" size="30" /><a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="divider">divider</div>
			<div class="unit">
				<label>排序条件：</label>
				<select>
					<option value="u_asending">按用户名顺排</option>
					<option value="d_asending">按账户开通日期日期顺排</option>
					<option value="u_descending">按用户名倒排</option>
					<option value="d_descending">按账户开通日期日期倒排</option>
				</select>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">开始检索</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
