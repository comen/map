<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="ajaxDone.jsp"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<!-- 
			<div class="unit">
				<label>固话到达数：</label>
				<input id="telephoneArrive" class="digits" class="digits" type="text" size="23" alt="请设置阈值" />
				<label><input type="radio" name="r1" checked="checked" />正常</label>
				<label><input type="radio" name="r1" />删除</label>
			</div>
			<div class="unit">
				<label>宽带到达数：</label>
				<input id="broadbandArrive" class="digits" type="text" size="23" alt="请设置阈值" />
				<label><input type="radio" name="r2" checked="checked" />正常</label>
				<label><input type="radio" name="r2" />删除</label>
			</div>
			<div class="unit">
				<label>宽带新装：</label>
				<input id="broadbandNew" class="digits" type="text" size="23" alt="请设置阈值" />
				<label><input type="radio" name="r3" checked="checked" />正常</label>
				<label><input type="radio" name="r3" />删除</label>
			</div>
			<div class="unit">
				<label>宽带拆机：</label>
				<input id="broadbandRemove" class="digits" type="text" size="23" value="100" alt="请设置阈值" />
				<label><input type="radio" name="r4" checked="checked" />正常</label>
				<label><input type="radio" name="r4" />删除</label>
			</div>
			<div class="unit">
				<label>宽带移机（装）：</label>
				<input id="broadbandMoveSetup" class="digits" type="text" size="23" alt="请设置阈值" readonly="readonly" />
				<label><input type="radio" name="r5" />正常</label>
				<label><input type="radio" name="r5" checked="checked" />删除</label>
			</div>
			<div class="unit">
				<label>宽带移机（拆）：</label>
				<input id="broadbandMoveUnload" class="digits" type="text" size="23" alt="请设置阈值" readonly="readonly" />
				<label><input type="radio" name="r6" />正常</label>
				<label><input type="radio" name="r6" checked="checked" />删除</label>
			</div>
			 -->
			<div class="unit">
				<label>宽带在途订单：</label> <input id="broadbandOrderInTransit"
					class="digits" type="text" size="23" alt="请设置阈值" />
			</div>
			<div class="unit">
				<label><input type="radio" name="r7" checked="checked" />正常</label>
				<label><input type="radio" name="r7" />删除</label>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>