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

	$.ajax({
		type : 'POST',
		url : '/KHome/article/barragerMap',
		cache : true,
		async : false,
		dataType : "json",
		contentType : "application/json",
		success : function(resultData) {
			if (null != resultData) {
				dmMap = resultData.danmuMap;
				// var conTmp = "";
				// var titTmp = "";
				// for ( var key in dmMap) {
				// if (dmMap.hasOwnProperty(key)) {
				// conTmp = contentBlock
				// .replace('${conTitle}', dmMap[key]);
				// titTmp = titleBlock.replace('${newsTitle}', key);
				// break;
				// }
				// }
				// $("div.rinfor").append(titTmp);
				// $("div.rinfor").append(conTmp);
			}
		}
	});
}

function moveJsonp() {
	crawlData();
	$("div.newsTitle").hide();
	$("div.conTitle").hide();

	var bottom = $(window).scrollTop() + $(window).height() - 387;
	$("div.rinfor").animate({
		"top" : bottom + "px"
	}, 30);

}
