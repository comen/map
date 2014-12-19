/**
 * 
 */

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
//		if (validateSelected(role, "请选择角色！") == false) {
//			role.focus();
//			return false;
//		}
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
