var math_Boolean = true;
var chinese_Boolean = true;
var english_Boolean = true;
var physical_Boolean = true;
var chemistry_Boolean = true;
var biology_Boolean = true;
var geography_Boolean = true;
var history_Boolean = true;
var politics_Boolean = true;
var sum_Boolean = false;

$('#txt_math').blur(function() {
    if((/^(0|[1-9]\d|100)$/).test($("#txt_math").val()) ) {
        $('.math_hint').html("✔").css("color", "green");
        sum_Boolean = true;
        math_Boolean = true;
    } else {
        $('.math_hint').html("×").css("color", "red");
        math_Boolean = false;
    }
});

$('#txt_chinese').blur(function() {
    if((/^(0|[1-9]\d|100)$/).test($("#txt_chinese").val())) {
        $('.chinese_hint').html("✔").css("color", "green");
        sum_Boolean = true;
        chinese_Boolean = true;
    } else {
        $('.chinese_hint').html("×").css("color", "red");
        chinese_Boolean = false;
    }
});

$('#txt_english').blur(function() {
    if((/^(0|[1-9]\d|100)$/).test($("#txt_english").val())) {
        $('.english_hint').html("✔").css("color", "green");
        sum_Boolean = true;
        english_Boolean = true;
    } else {
        $('.english_hint').html("×").css("color", "red");
        english_Boolean = false;
    }
});

$('#txt_physical').blur(function() {
    if((/^(0|[1-9]\d|100)$/).test($("#txt_physical").val())) {
        $('.physical_hint').html("✔").css("color", "green");
        sum_Boolean = true;
        physical_Boolean = true;
    } else {
        $('.physical_hint').html("×").css("color", "red");
        physical_Boolean = false;
    }
});

$('#txt_chemistry').blur(function() {
    if((/^(0|[1-9]\d|100)$/).test($("#txt_chemistry").val())) {
        $('.chemistry_hint').html("✔").css("color", "green");
        sum_Boolean = true;
        chemistry_Boolean = true;
    } else {
        $('.chemistry_hint').html("×").css("color", "red");
        chemistry_Boolean = false;
    }
});

$('#txt_biology').blur(function() {
    if((/^(0|[1-9]\d|100)$/).test($("#txt_biology").val())) {
        $('.biology_hint').html("✔").css("color", "green");
        sum_Boolean = true;
        biology_Boolean = true;
    } else {
        $('.biology_hint').html("×").css("color", "red");
        biology_Boolean = false;
    }
});

$('#txt_geography').blur(function() {
    if((/^(0|[1-9]\d|100)$/).test($("#txt_geography").val())) {
        $('.geography_hint').html("✔").css("color", "green");
        sum_Boolean = true;
        geography_Boolean = true;
    } else {
        $('.geography_hint').html("×").css("color", "red");
        geography_Boolean = false;
    }
});

$('#txt_history').blur(function() {
    if((/^(0|[1-9]\d|100)$/).test($("#txt_history").val())) {
        $('.history_hint').html("✔").css("color", "green");
        sum_Boolean = true;
        history_Boolean = true;
    } else {
        $('.history_hint').html("×").css("color", "red");
        history_Boolean = false;
    }
});

$('#txt_politics').blur(function() {
    if((/^(0|[1-9]\d|100)$/).test($("#txt_politics").val())) {
        $('.politics_hint').html("✔").css("color", "green");
        sum_Boolean = true;
        politics_Boolean = true;
    } else {
        $('.politics_hint').html("×").css("color", "red");
        politics_Boolean = false;
    }
});

function checkAllNone() {
    if ($("#txt_math").val().length < 1 &
        $("#txt_chinese").val().length <1 &
        $("#txt_english").val().length < 1 &
        $("#txt_physical").val().length < 1 &
        $("#txt_chemistry").val().length < 1 &
        $("#txt_biology").val().length <1 &
        $("#txt_geography").val().length <1 &
        $("#txt_history").val().length <1 &
        $("#txt_politics").val().length <1 == true){
        return false;
    }else{
        return true;
    }

}

function changeScore(){
    if (math_Boolean & chinese_Boolean & english_Boolean & physical_Boolean & chemistry_Boolean & biology_Boolean & geography_Boolean & history_Boolean & politics_Boolean & sum_Boolean & checkAllNone() == true){

        $.ajax({
            type: "POST",
            dataType: "text",
            url: "/addScore" ,
            data: { "math":$('#txt_math').val(),
                    "chinese":$("#txt_chinese").val(),
                    "english":$("#txt_english").val(),
                    "physical":$("#txt_physical").val(),
                    "chemistry":$("#txt_chemistry").val(),
                    "biology":$("#txt_biology").val(),
                    "geography":$("#txt_geography").val(),
                    "history":$("#txt_history").val(),
                    "politics":$("#txt_politics").val()
                    },
            success: function (result) {
                var json = eval("("+result+")");//将json类型字符串转换为json对象
                if (json.success){
                    alert("修改成功");
                    $("#myModal").modal('hide');
                }else{
                    document.getElementById("userspan").innerHTML = json.msg;
                }
                $("#myModal").modal('hide');
                // 清空文本框内容
                for(var i=0;i<document.getElementsByTagName("input").length;i++){
                    document.getElementsByTagName("input")[i].value="";
                }
            },
            error : function() {
                alert("网络原因,请重新登录!");
            }
        });
        // 清空文本框内容
        for(var i=0;i<document.getElementsByTagName("input").length;i++){
            document.getElementsByTagName("input")[i].value="";
        }
    }else if(sum_Boolean == false || checkAllNone() == false){
        alert("最少填入一项数据");
    } else {
        alert("请检查数据,每项数据不填或为大于0小于100的整数!")
    }
}