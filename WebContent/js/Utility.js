/**
 * 
 */

function testAlert(txt) {
	alert(txt);
}

function updateHref(thisRow, anchorId) {
	var href = document.getElementById(anchorId).href;
	var cells = thisRow.getElementsByTagName("td");
	for (var i = 0; i < cells.length; i++){
		switch (i) {
		case 1:
			href = href + "&roledesc=" + cells[i].textContent;
			break;
		case 2:
			href = href + "&realname=" + cells[i].textContent;
			break;
		case 3:
			href = href + "&department=" + cells[i].textContent;
			break;
		case 4:
			href = href + "&createdate=" + cells[i].textContent;
			break;
		}
	}
	document.getElementById(anchorId).href = href;
}

function validateForm(thisForm) {
	with (thisForm) {
		if (validateRequired(username, "请输入用户名！") == false) {
			username.focus();
			return false;
		}
		if (validateRequired(password, "请输入密码！") == false) {
			password.focus();
			return false;
		}
		if (validateSelected(role, "请选择角色！") == false) {
			role.focus();
			return false;
		}
	}
}

function validateRequired(field, alertTxt) {
	with (field) {
		if (value == null || value == "") {
			alert(alertTxt);
			return false;
		}
		else {
			return true;
		}
	}
}

function validateSelected(field, alertTxt) {
	with (field) {
		if (value == null || value == "0") {
			alert(alertTxt);
			return false;
		}
		else {
			return true;
		}
	}
}
