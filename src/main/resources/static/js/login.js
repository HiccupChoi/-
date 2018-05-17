function verification(){
    var username = document.getElementById("uname").value;
    var userpwd = document.getElementById("upwd").value;
    if(username.trim() == ""){
        document.getElementById("userspan").innerHTML = "<span class=\"iconfont icontxt \">&#xe637;</span>用户名不能为空";
        return false;
    }else if(userpwd.trim() == ""){
        document.getElementById("userspan").innerHTML = "<span class=\"iconfont icontxt\">&#xe637;</span>密码不能为空";
        return false;
    }
    return true;
}