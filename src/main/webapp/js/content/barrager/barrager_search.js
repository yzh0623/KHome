// 发送弹幕
function runDanmu() {
	if (danmuArray.length > 0) {
		var heights = $(window).height();
		var danmu = danmuArray[Math.floor(Math.random() * danmuArray.length)];
		var danmuParts = danmu.split("@#@");
		var title = danmuParts[0];
		var href = danmuParts[1];
		var item = {
			'info' : title,
			'href' : href,
			'bottom' : heights - 115,
			'speed' : 10
		};
		$("div.header").barrager(item);
	}
	return false;
}

// 获取数据
function crawlData() {
	var type = typeArray[Math.floor(Math.random() * typeArray.length)];

	$.ajax({
		type : 'POST',
		url : '/KHome/article/barragerMap',
		cache : true,
		async : false,
		data : type,
		dataType : "json",
		contentType : "application/json",
		success : function(resultData) {
			if (null != resultData) {
				var dmMap = resultData.danmuMap;
				var libTmp = "";
				for ( var key in dmMap) {
					if (dmMap.hasOwnProperty(key)) {
						var libVal = liblock.replace('${url}', dmMap[key]);
						libVal = libVal.replace('${title}', key);
						libTmp += libVal;
					}
				}
				var ul = $(ulblock).append(libTmp);
				$("div.rinfor").append(ul);

			}
		}
	});
}

function moveJsonp() {
	crawlData();
	$("ul.list").hide();
	$("div.rinfor").animate({
		"top" : $(window).scrollTop() + $(window).height() - 223 + "px"
	}, 30);
	setInterval('autoScroll(".rinfor")', 4000);
}

function autoScroll(obj) {
	$(obj).find(".list").animate({
		marginTop : "-45px"
	}, 700, function() {
		$(this).css({
			"marginTop" : "1px",
			"color" : "#fff"
		}).find("li:first").appendTo(this);
	})
}
