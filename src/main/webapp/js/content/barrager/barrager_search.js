var danmuArray = new Array();

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
		data : type,
		dataType : "json",
		contentType : "application/json",
		success : function(resultData) {
			if (null != resultData) {
				var dmMap = resultData.danmuMap;
				for ( var key in dmMap) {
					danmuArray.push(key + "@#@" + dmMap[key]);
				}
			}
		}
	});
}

function initBarrager() {
	crawlData();
	setInterval(crawlData, 500000);

	runDanmu();
	setInterval(runDanmu, 10000);

}