<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String loginState = "F"; 	
	String userName = "";
	int role = 0;
	String roleText = "";
	
	/* Validate if user login successfully. */
	try {
		loginState = (String)session.getAttribute("loginstate");
		if (loginState.equals("S")) {
			userName = (String)session.getAttribute("username");
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
		}
	} catch (Exception e) {
		System.out.println(e.getClass() + "\t:\t" + e.getMessage());
		String path = "login.jsp";
		response.sendRedirect(path);
	}
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国电信上海分公司北区局用户数据信息化系统</title>

<link href="../dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="../dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="../dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="../dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
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
<script src="../dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="../dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="../dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

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

<script src="../js/Utility.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("../dwz/dwz.frag.xml", {
		//loginUrl:"../dwz/login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"login.jsp",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
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
				<ul class="nav">
					<li><span style="color:#ddd"><%=roleText%></span><span style="color:red">&nbsp;<%=userName%>&nbsp;</span><span style="color:#ddd">, 欢迎你！</span></a></li>
					<li><a href="changepwd.jsp" target="dialog" width="550" height="250">修改密码</a></li>
					<li><a href="login.jsp?status=logout">退出</a></li>
				</ul>
				<!-- 
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
				 -->
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>用户数据管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a>系统管理员</a>
								<ul>
									<li><a href="userlist.jsp?role=1" target="navTab" rel="userlist1" title="系统管理员">浏览</a></li>
									<li><a href="useradd.jsp?role=1" target="navTab" rel="useradd1" title="系统管理员">新建</a></li>
								</ul>
							</li>
							<li><a>网格数据管理员</a>
								<ul>
									<li><a href="userlist.jsp?role=2" target="navTab" rel="userlist2" title="网格数据管理员">浏览</a></li>
									<li><a href="useradd.jsp?role=2" target="navTab" rel="useradd2" title="网格数据管理员">新建</a></li>
								</ul>
							</li>
							<li><a>营销数据管理员</a>
								<ul>
									<li><a href="userlist.jsp?role=3" target="navTab" rel="userlist3" title="营销数据管理员">浏览</a></li>
									<li><a href="useradd.jsp?role=3" target="navTab" rel="useradd3" title="营销数据管理员">新建</a></li>
								</ul>
							</li>
							<li><a>普通用户</a>
								<ul>
									<li><a href="userlist.jsp?role=4" target="navTab" rel="userlist4" title="普通用户">浏览</a></li>
									<li><a href="useradd.jsp?role=4" target="navTab" rel="useradd4" title="普通用户">新建</a></li>
								</ul>
							</li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>网格数据管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="gridlist.jsp" target="navTab" rel="gridlist">浏览</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>营销数据管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="salesdataupload.jsp" target="navTab" rel="salesdataupload">通过Excel文件上传</a></li>
							<li><a>按日显示</a>
								<ul>
									<li><a href="salesdatafields.jsp" target="navTab" rel="day_office" title="按日 北区局级">北区局级</a></li>
									<li><a href="salesdatafields.jsp" target="navTab" rel="day_suboffice" title="按日 分局级">分局级</a></li>
									<li><a href="salesdatafields.jsp" target="navTab" rel="day_grid" title="按日 网格级">网格级</a></li>
								</ul>
							</li>
							<li><a>按周显示</a>
								<ul>
									<li><a href="salesdatafields.jsp" target="navTab" rel="week_office" title="按周 北区局级">北区局级</a></li>
									<li><a href="salesdatafields.jsp" target="navTab" rel="week_suboffice" title="按周 分局级">分局级</a></li>
									<li><a href="salesdatafields.jsp" target="navTab" rel="week_grid" title="按周 网格级">网格级</a></li>
								</ul>
							</li>
							<li><a>按月显示</a>
								<ul>
									<li><a href="salesdatafields.jsp" target="navTab" rel="month_office" title="按月 北区局级">北区局级</a></li>
									<li><a href="salesdatafields.jsp" target="navTab" rel="month_suboffice" title="按月 分局级">分局级</a></li>
									<li><a href="salesdatafields.jsp" target="navTab" rel="month_grid" title="按月 网格级">网格级</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<p>这里用来实时地图参数信息...</p>
						</div>
						
						<!-- 导入百度地图 -->
						<div id="baidumap" style="height:600px"><jsp:include page="map_original.jsp" /></div>
						
						<div style="width:230px;position: absolute;top:60px;right:0" layoutH="80">
							<iframe width="100%" height="430" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?width=0&height=430&fansRow=2&ptype=1&skin=1&isTitle=0&noborder=1&isWeibo=1&isFans=0&uid=1739071261&verifier=c683dfe7"></iframe>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2014 上海电信北区局 沪ICP备05019125号-10</div>

</body>
</html>