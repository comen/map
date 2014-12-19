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

<%
	String gid = (String)request.getParameter("gid");
%>

<form name="mainform" method="post" action="deleteGrid"
	class="pageForm required-validate"
	onsubmit="return validateCallback(this, navTabAjaxDone);">

	<div class="pageFormContent" layoutH="58">
		<input type="text" name="gridcode" value="<%=gid%>" />
	</div>

	<script type="text/javascript">
		mainform.submit();
	</script>

</form>