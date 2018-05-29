
var password_Boolean = false;
var varconfirm_Boolean = false;
// password
$('.reg_password').blur(function() {
	if((/^[a-z0-9_-]{6,16}$/).test($(".reg_password").val())) {
		$('.password_hint').html("✔").css("color", "green");
		password_Boolean = true;
	} else {
		$('.password_hint').html("×").css("color", "red");
		password_Boolean = false;
	}
});
// password_confirm
$('.reg_confirm').blur(function() {
	if(($(".reg_password").val()) == ($(".reg_confirm").val())) {
		$('.confirm_hint').html("✔").css("color", "green");
		varconfirm_Boolean = true;
	} else {
		$('.confirm_hint').html("×").css("color", "red");
		varconfirm_Boolean = false;
	}
});

// click
$('.red_button').click(function() {
	if(password_Boolean && varconfirm_Boolean == true) {
		alert("注册成功");
	} else {
		alert("请完善信息");
		return false;
	}
});