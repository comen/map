<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
 <%@include file="validate.jsp" %>

<div class="pageContent">
	<form method="post" action="ajaxDone.jsp" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名：</label>
				<input id="username" class="required" type="text" size="30" minlength="11" maxlength="11" alt="请输入手机号" />
			</p>
			<p>
				<label>角色：</label>
				<select id="role" class="required combox">
					<option value="1">系统管理员</option>
					<option value="2" selected>网格数据管理员</option>
					<option value="3">营销数据管理员</option>
					<option value="4">普通用户</option>
				</select>
			</p>
			<p>
				<label>初始密码：</label>
				<input id="password" class="required" size="30" minlength="6" maxlength="20" value="111111" alt="请输入6-20位初始密码" />
			</p>
			<p>
				<label>姓名：</label>
				<input id="realname" type="text" size="30" alt="选填" />
			</p>
			<p>
				<label>性别：</label>
				<input id="gender_male" type="radio" name="gender" value="1" />男
				<input id="gender_female" type="radio" name="gender" value="2" />女
			</p>
			<p>
				<label>所属单位：</label>
			    <input id="office" type="text" size="30" alt="选填" />
			</p>
			<p>
				<label>电子邮箱：</label>
			    <input id="email" class="email" type="text" size="30" alt="选填" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
