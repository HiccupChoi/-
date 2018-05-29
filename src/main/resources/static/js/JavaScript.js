
var password_Boolean = false;
var varconfirm_Boolean = false;
var user_Boolean = false;
var user_name_Boolean = false;
var user_code_Boolean = false;
// password
$('.reg_password').blur(function() {
	if((/^[a-zA-Z0-9_-]{6,16}$/).test($(".reg_password").val())) {
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

$('.reg_no').blur(function () {
    if((/^[0-9_-]{11}$/).test($(".reg_no").val())) {
        $('.user_no_hint').html("✔").css("color", "green");
        if (user_name_Boolean && user_code_Boolean == true){
            checkThreeCode();
        }
        user_Boolean = true;
    } else {
        $('.user_no_hint').html("×").css("color", "red");
        user_Boolean = false;
    }
});

$('.reg_name').blur(function () {
    if((/^[\u4e00-\u9fa5]{2,4}$/).test($(".reg_name").val())) {
        $('.user_name_hint').html("✔").css("color", "green");
        if (user_Boolean && user_code_Boolean == true){
            checkThreeCode();
        }
        user_name_Boolean = true;
    } else {
        user_name_Boolean = false;
    }
});

$('.reg_code').blur(function () {
    if((/^[a-zA-Z0-9_-]{12}$/).test($(".reg_code").val())) {
        $('.user_code_hint').html("✔").css("color", "green");
        if (user_Boolean && user_name_Boolean == true){
            checkThreeCode();
        }
        user_code_Boolean = true;
    } else {
        $('.user_code_hint').html("×").css("color", "red");
        user_code_Boolean = false;
    }
});

function checkThreeCode(){
    $.ajax({
        type: "POST",
        dataType: "text",
        url: "/checkThreeCode" ,
        data: $('#user_register').serialize(),
        success: function (result) {

        },
        error : function() {
            alert("网络原因,请重新登录!");
        }
    });
}

// click
$('.red_button').click(function() {
	if(password_Boolean && varconfirm_Boolean && user_code_Boolean && user_name_Boolean && user_Boolean == true) {

        $.ajax({
            type: "POST",
            dataType: "text",
            url: "/users/register" ,
            data: $('#user_register').serialize(),
            success: function (result) {
                var json = eval("("+result+")");//将json类型字符串转换为json对象
                if (json.success){
                    $('#container').load(location.href+" #container");
                }else{
                    document.getElementById("userspan").innerHTML = json.msg;
                }
                // 清空文本框内容
                for(var i=0;i<document.getElementsByTagName("input").length;i++){
                    document.getElementsByTagName("input")[i].value="";
                }
            },
            error : function() {
                alert("网络原因,请重新登录!");
            }
        });

        password_Boolean = false;
        varconfirm_Boolean = false;
        user_Boolean = false;
        user_name_Boolean = false;
        user_code_Boolean = false;
	} else {
		alert("请正确填写所有信息");
		return false;
	}
});