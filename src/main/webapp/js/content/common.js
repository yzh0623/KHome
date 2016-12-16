﻿// 回车触发事件
function setupInfo(e) {
	var keyCode = null;
	if (e.which)
		keyCode = e.which;
	else if (e.keyCode)
		keyCode = e.keyCode;

	if (keyCode == 13) {
		showAndSave();
		return false;
	}
	return true;
}

// 滚动条下拉到最后自动加载
function switchHeadType() {

	/*
	 * 监听滚动条，本来不想用jQuery但是发现js里面监听滚动条的事件不好添加，这边就引用了Jquery的$(obj).scroll();这个方法了
	 */
	$(window)
			.scroll(
					function() {
						var urls;
						var content = $("li a.active").attr("id");
						if ($(window).scrollTop() + $(window).height() == $(
								document).height()) {
							if (content == 'home') {
								showMoreAction();
							} else if (content == 'logs'
									|| content == 'experiment') {
								urls = "/KHome/article/categorylist/";
								generateCategoryListByType(categoryType,
										'button', urls);
							} else if (content == 'update') {
								showNextDay();
							} else if (content == 'label') {
								urls = "/KHome/article/labelList/";
								generateCategoryListByType(labelType, 'button',
										urls);
							}
						}

						var bottom = $(window).scrollTop() + $(window).height()
								- 223;
						$("div.rinfor").animate({
							"top" : bottom + "px"
						}, 30);
					})
}

// 点击后打开推送信息
function chick2ShowRecommend() {
	var width = parseInt($("div.rinfor").width());
	if (width < 100) {
		$("div.rinfor").css('width', 500);
		$("#intitle").hide();
		$("ul.list").show();
	} else {
		$("div.rinfor").css('width', 80);
		$("#intitle").show();
		$("ul.list").hide();
	}
}

function stopBubble(e) { 
	if (e && e.stopPropagation) {//非IE浏览器 
	　　e.stopPropagation(); 
	} else {//IE浏览器 
		window.event.cancelBubble = true; 
	}
}
