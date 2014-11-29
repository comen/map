<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%-- Validate if user login successfully. --%>
<%@include file="validate.jsp" %>

<%-- check authority --%>
<%
	if (role != 1) {
%>
		<%@include file="noAuthorityError.jsp" %>
<%
		return;
	}
%>

<%
	String uid = (String)request.getParameter("uid");
%>

<form name="mainform" method="post" action="deleteUser" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	
	<div class="pageFormContent" layoutH="58">
		<input type="text" name="username" value="<%=uid%>" />
	</div>
	
	<script type="text/javascript">
		mainform.submit();
	</script>
	
</form>