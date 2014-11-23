<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上海</title>
<link href="./dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var xmlHttp;
	function createXmlHttp() {
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	function callBack() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var responseText = xmlHttp.responseText;
			var loginMsg = document.getElementById("loginmsg");
			if(responseText == "1") {
				window.location.href('index.jsp');
			} else {
				loginMsg.innerHTML = "用户名或密码错误！";
			}
			
		}
	}

	function login() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var role = document.getElementById("role").value;
		var formData = new FormData();
		formData.append("username", username);
		formData.append("password", password);
		formData.append("role", role);
		
		createXmlHttp();
		xmlHttp.onreadystatechange = callBack;
		xmlHttp.open("post", "login", true);
		xmlHttp.setRequestHeader("Content-Type",
				"multipart/form-data;boundary=map");
		xmlHttp.setRequestHeader("Cache-Control", "no-cache");
		xmlHttp.send(formData);
	}
</script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<img src="./dwz/themes/default/images/login_logo.gif" />
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="http://bbs.dwzjs.com">反馈</a></li>
						<li><a href="./dwz/doc/dwz-user-guide.pdf" target="_blank">帮助</a></li>
					</ul>
				</div>
				<h2 class="login_title"><img src="./dwz/themes/default/images/login_title.jpg" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="index.jsp">
					<p>
						<label>用户名：</label>
						<input type="text" name="username" id="username" size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="password" id="password" size="20" class="login_input" />
					</p>
					<p>
						<label>角色：</label>
						<select name="role" id="role">
							<option value="1">系统管理员</option>
							<option value="2">网格数据管理员</option>
							<option value="3">营销数据管理员</option>
							<option value="4">普通用户</option>
						</select>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " onclick="login()" />
					</div>
					<p id="loginmsg" style="color:red;text-align:center;display:none"></p>
				</form>
			</div>
			<div class="login_banner"><img src="./dwz/themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#">忘记密码怎么办？</a></li>
					<li><a href="#">为什么登录失败？</a></li>
				</ul>
				<div class="login_inner">
					<p>系统管理员：为其他用户开通系统账号，设置用户角色，及其他角色的所有权限。</p>
					<p>网格数据管理员：新装/修改/删除网格编号、网格名称、网格经理、网格对应地图地址，在显示地图上查看数据。</p>
					<p>营销数据管理员：上传营销数据，增加/调整/删除显示数据字段，设置警示阈值，在显示地图上查看数据。</p>
					<p>普通用户：在显示地图上查看数据。</p>
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2014 上海电信北区局 沪ICP备05019125号-10
		</div>
	</div>
</body>
</html>