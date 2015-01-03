<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String loginState = "F";
	String userName = "";
	int role = 0;
	String errMsg = "";
	
	/* Check if user click logout. */
	String status = request.getParameter("status");
	if (status != null && status.equals("logout")) {
		session.invalidate();
	} else {
		/* Validate if user login successfully. */
		try {
			loginState = (String)session.getAttribute("loginstate");
			if (loginState != null) {
				if (loginState.equals("S")) {
					/* Login successfully, go to index page */
					String path = "index.jsp";
					response.sendRedirect(path);
					return;
				} else {
					session.invalidate();
					errMsg = "用户名或密码错误！";
				}
			}
		} catch (Exception e) {
			System.out.println(e.getClass() + "\t:\t" + e.getMessage());
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国电信上海分公司北区局用户数据信息化系统</title>
<link type="image/x-icon" rel="shortcut icon" href="../favicon.ico" />
<link href="../dwz/themes/css/login.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script>
<script src="../js/Utility.js" type="text/javascript"></script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<img src="../dwz/themes/default/images/login_logo.gif" />
			</h1>
			<div class="login_headerContent">
 				<!-- <div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="javascript:;" onclick="window.external.AddFavorite(location.href,document.title)">加入收藏</a></li>
						<li><a href="http://bbs.dwzjs.com">反馈</a></li>
						<li><a href="../dwz/doc/dwz-user-guide.pdf" target="_blank">帮助</a></li>
					</ul>
				</div> -->
				<h2 class="login_title">
					<img src="../dwz/themes/default/images/login_title.jpg" />
				</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form method="post" action="login" class="pageForm"
					onsubmit="return validateForm(this)" enctype="multipart/form-data">
					<p>
						<label>用户名：</label> <input type="text" class="required"
							name="username" size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label> <input type="password" name="password"
							size="20" class="login_input" />
					</p>
					<!-- <p>
						<label>角色：</label> <select name="role" id="role">
							<option value="0">请选择</option>
							<option value="1">系统管理员</option>
							<option value="2">网格数据管理员</option>
							<option value="3">营销数据管理员</option>
							<option value="4">普通用户</option>
						</select>
					</p>  -->
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
					<p style="color: red; text-align: center"><%=errMsg%></p>
				</form>
			</div>
			<div class="login_banner">
				<img src="../dwz/themes/default/images/login_banner.jpg" />
			</div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#" onclick="alert('找系统管理员帮忙...')">忘记密码怎么办？</a></li>
					<!-- <li><a href="#" onclick="alert('小编正在疯狂编辑中...')">系统使用帮助</a></li> -->
					<li><a href="help.jsp" target="_blank">系统使用帮助</a></li>
				</ul>
				<div class="login_inner">
					<p>系统管理员：为其他用户开通系统账号，设置用户角色，及其他角色的所有权限。</p>
					<p>网格数据管理员：新增/修改/删除网格编号、网格名称、网格经理、网格对应地图地址；在显示地图上查看数据；查看网格编辑记录。</p>
					<p>营销数据管理员：上传营销数据，增加/调整/删除显示数据字段，设置警示阈值，在显示地图上查看数据；查看网格编辑记录。</p>
					<p>普通用户：在显示地图上查看数据；查看网格编辑记录。</p>
				</div>
			</div>
		</div>
		<div id="login_footer"></div>
		<script type="text/javascript">
			var today = new Date();
			var year = "" + today.getFullYear();
			$("#login_footer").text("Copyright © 2014-" + year + " 上海电信北区局");
		</script>
	</div>
</body>
</html>