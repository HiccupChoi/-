window.onload = function() {
	var back = document.getElementById('back-top');
	var time;
	back.onclick = function() {
		
		//设置定时器
		time = setInterval(function() {
			var osTop = document.documentElement.scrollTop || document.body.scrollTop;
			//设置速度
			var ispeed =Math.ceil(osTop / 3);
			document.documentElement.scrollTop = document.body.scrollTop = osTop - ispeed;
			
		
			//清除定时器
			if(osTop == 0) {
				clearInterval(time);
			}
		}, 30)
	}

}