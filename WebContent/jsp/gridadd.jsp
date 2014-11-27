<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="ajaxDone.jsp" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label>网格编号：</label>
				<input id="grid_code" class="required" type="text" size="30" />
			</div>
			<div class="unit">
				<label>网格名称：</label>
				<input id="grid_name" class="required" type="text" size="30" />
			</div>
			<div class="unit">
				<label>网格经理：</label>
				<input id="grid_manager" class="required" type="text" size="30" />
			</div>
			<div class="unit">
				<label>网格对应地图地址：</label>
				<input id="grid_address" class="required" type="text" size="50" />
				<span><a href="grideditinmap.jsp" target="dialog" width="800" height="600" title="BQ-GQ-BZC-1000 耀光国际" rel="salesdatalist">{编辑地图区域}</a></span>
			</div>
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