
// 初始化头部区域超链接（home除外）
function initHeaderOtherAction() {
	var aItems = $("div.banner a");
	for (var i = 0; i < aItems.length; i++) {
		var cateId = aItems.eq(i).attr("id");
		if (cateId != "home") {
			aItems.eq(i).click(function() {
				var content = this.id.toUpperCase();
				if (this.id == "logs" || this.id == "experiment") {
					categoryType = content;
					headerOtherCategoryAction();

				} else if (this.id == "update") {
					headerUpdateAction();
				}
				$("ul.cl-effect-15 li a").removeClass("active");
				$("#" + this.id).addClass("active");
			});
		}

		if (cateId == "update") {
			aItems.eq(i).dblclick(function() {
				change2Input();
			});
		}
	}
}

// 初始化头部区域home节点的动作事件
function initHeaderHomeAction() {
	// 判断交换节点div id=hometmp是否存在div的子节点
	if ($("#hometmp:has(div)").length != 0) {
		// 如果存在，将div container内内容清空
		$("div.content div.container").empty();

		// 将div id=hometmp中的内容复制一份并加载到div container的子节点中
		$("#hometmp").children().clone().appendTo("div.content div.container");
		// 清空div id=hometmp节点，避免重复
		$("#hometmp").empty();

		var divotherrow = $("div[id^='other_row']");
		for (var i = 0; i < divotherrow.length; i++) {
			divotherrow.eq(i).parent().remove();
		}

		// 清楚block块内容
		var divItems = $("div.col-md-3 div.l_g_r");
		for (var i = 0; i < divItems.length; i++) {
			var classChild = divItems.eq(i).children().attr("class");
			if ("integer" == classChild) {
				divItems.eq(i).remove();
			}
		}

		// 重新加载block块中的内容
		initContentBlock();
		initCloudLabel();
		// 内容加载完毕后重新初始化超链接click事件
		initRefAction("ul#myList");

		$("ul.cl-effect-15 li a").removeClass("active");
		$("#home").addClass("active");
	}
}

// 头部其他类型点击触发事件
function headerOtherCategoryAction() {
	var urls = "/KHome/article/categorylist/";
	// 生成分类列表方法，传入的是分类和控件类型
	generateCategoryListByType(categoryType, 'header', urls);
}

// 头部更新信息点击触发事件
function headerUpdateAction() {
	generateUpdateList();
}

