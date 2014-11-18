<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="CSS/style.css" />
<title>系统登陆</title>
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
			var message = document.getElementById("message");
			message.innerHTML = message.innerHTML + responseText;
			window.location.href(index.jsp);
		}
	}

	function login() {
		var username = document.getElementById("user_name").innerHTML;
		var password = document.getElementById("user_password").innerHTML;
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

 <div id="stylized" class="login">

  <form id="form" name="form" method="post">

   <h1>系统登陆</h1>

    <p>Access your account</p>

    <!-- Name / Email -->

	<label for="email" class="label">用户名：</label>

    <input type="text" class="input" name="user_name" id="user_name" />

	<!-- Password -->

    <label for="password" class="label">密码：</label>

    <input type="password" class="input" name="user_password" id="user_password" />
    
	<label for="role" class="label">角色：</label>
	
	<select name="role" id="role">
		<option value="1">系统管理员</option>
		<option value="2">网格数据管理员</option>
		<option value="3">营销数据管理员</option>
		<option value="4">普通用户</option>
	</select>
	
    <button type="submit" onclick="login()">登陆</button>
    
    <span id="message"></span>

   <div class="spacer"></div>

  </form>

 </div>
 
</body>
</html>