<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String loginState = "F"; 	
	String userName = "";
	int role = 0;
	String roleText = "";
	
	/* Validate if user login successfully. */
	try {
		loginState = (String) session.getAttribute("loginstate");
		if (loginState != null && loginState.equals("S")) {
			userName = (String) session.getAttribute("username");
			role = Integer.parseInt((String)session.getAttribute("role"));
			switch (role) {
			case 1:
				roleText = "系统管理员";
				break;
			case 2:
				roleText = "网格数据管理员";
				break;
			case 3:
				roleText = "营销数据管理员";
				break;
			case 4:
				roleText = "普通用户";
				break;
			default:
				roleText = "未知角色";
				break;
			}
		} else {
			String path = "login.jsp";
			response.sendRedirect(path);
			return;
		}
	} catch (Exception e) {
		System.out.println(e.getClass() + "\t:\t" + e.getMessage());
		String path = "login.jsp";
		response.sendRedirect(path);
		return;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国电信上海分公司北区局用户数据信息化系统</title>
<link type="image/x-icon" rel="shortcut icon" href="../favicon.ico" />
<link href="../dwz/themes/default/style.css" rel="stylesheet"
	type="text/css" media="screen" />
<link href="../dwz/themes/css/core.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="../dwz/themes/css/print.css" rel="stylesheet"
	type="text/css" media="print" />
<link href="../dwz/uploadify/css/uploadify.css" rel="stylesheet"
	type="text/css" media="screen" />
<!--[if IE]>
<link href="../dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<!--[if lte IE 9]>
<script src="../dwz/js/speedup.js" type="text/javascript"></script>
<![endif]-->
<script src="../dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="../dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="../dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="../dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="../dwz/xheditor/xheditor-1.2.1.min.js"
	type="text/javascript"></script>
<script src="../dwz/xheditor/xheditor_lang/zh-cn.js"
	type="text/javascript"></script>
<script src="../dwz/uploadify/scripts/jquery.uploadify.js"
	type="text/javascript"></script>

<script src="../dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="../dwz/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="../dwz/bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="../dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("../dwz/dwz.frag.xml", {
		//loginUrl:"../dwz/login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"login.jsp",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:600001}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"../dwz/themes"}); // themeBase 相对于index页面的主题base路径
			//$("#sidebar .toggleCollapse div").trigger("click");
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<!-- <a class="logo" href="http://j-ui.com">标志</a> -->
				<img src="../dwz/themes/default/images/logo.png" />
				<h2>上海分公司北区局用户数据信息化系统</h2>
				<ul class="nav">
					<li><a style="text-decoration:none"><%=roleText%></a>
						<span style="color: red">&nbsp;<%=userName%>&nbsp;</span>
						<a style="text-decoration:none">, 欢迎你！</a>
					</li>
					<li><a href="changepwd.jsp" target="dialog" rel="changepwd" mask="true" 
						width="550" height="250">修改密码</a></li>
					<li><a href="login.jsp?status=logout">退出</a></li>
				</ul>
				
				<!-- <ul class="themeList" id="themeList">
					<li theme="default" class="default"><div class="selected">蓝色</div></li>
					<li theme="green" class="green"><div>绿色</div></li>
					<li theme="purple" class="purple"><div>紫色</div></li>
					<li theme="silver" class="silver"><div>银色</div></li>
					<li theme="azure" class="azure"><div>天蓝</div></li>
				</ul> -->
				 
			</div>

			<!-- navMenu -->

		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>主菜单</h2>
					<div>收缩</div>
				</div>

				<div class="accordion" fillSpace="sidebar">
					<%
						if (role == 1) {
					%>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>用户数据管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="userlist.jsp?firstload=true" target="navTab" rel="userlist" title="浏览用户">浏览用户</a></li>
							<li><a href="useradd.jsp" target="navTab" rel="useradd" title="添加用户">添加用户</a></li>
						</ul>
					</div>
					<%
						}
						if (role == 1 || role == 3) {
					%>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>营销数据管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="salesdataupload.jsp" target="navTab"
								rel="salesdataupload">营销数据上传</a></li>
							<li><a href="salesdatadelete.jsp" target="navTab"
								rel="salesdatadelete">营销数据删除</a></li>
							<li><a href="salesdatafields.jsp" target="navTab"
								rel="salesdatafields">营销字段管理</a></li>
						</ul>
					</div>
					<%
						}
						if (role == 1 || role == 2) {
					%>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>网格数据管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="gridlist.jsp?firstload=true" target="navTab" rel="gridlist" title="浏览网格">浏览网格</a></li>
						</ul>
					</div>
					<%
						}
					%>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>网格变动记录
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="recordlist.jsp?firstload=true" target="navTab"rel="recordlist">查询</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span
										class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<br />说明：(1) 北区局为顶级数据，显示整个北区局的用户数据；地图放大一层，可以看到各分局的用户数据；继续放大，则是各网格的用户数据。<br />
							<br />(2) [区局/分局级] 环比=(本周数据-上周数据)/上周数据*100% or (本月数据-上月数据)/上月数据*100%，同比=(本月数据-去年同期数据)/去年同期数据*100%；[网格级] 环比=本周数据-上周数据 or 本月数据-上月数据，同比=本月数据-去年同期数据。
						</div>
						<div class="pageFormContent" layoutH="80">
							<iframe src="map.jsp" style="width:100%;height:100%;" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer"></div>
	<script type="text/javascript">
		var today = new Date();
		var year = "" + today.getFullYear();
		$("#footer").text("Copyright © 2014-" + year + " 上海电信北区局");
	</script>
</body>
</html>