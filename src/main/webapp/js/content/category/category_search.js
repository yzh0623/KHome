// 初始化都为0行
var newLine = 0;
var categoryVal = null;
var controls = null;

// 通过类型查询列表
function generateCategoryListByType(cateType, _control, urls) {
	var bioDiv = $("div.biographys");
	controls = _control;
	// 若通过查看更多触发的，就数清楚div个数
	if ("header" != controls) {
		newLine = bioDiv.length + 1;
	}
	// 使用jersey将请求拼接url
	var cateUrl = urls + cateType + '/' + newLine;
	ajax2Search(cateUrl);

}

// ajax查询
function ajax2Search(cateUrl) {
	$.ajax({
		type : 'POST',
		url : cateUrl,
		async : false,
		cache : false,
		dataType : "json", // 后台返回值类型
		contentType : "application/json",
		success : function(resultData) {
			// 若点击头部初始化
			if (newLine == 0) {
				initHeadClick();
			}

			var cateList = resultData.cateList;
			cate4Integrate(cateList);

			// 初始化分类列表页面的超链接click事件
			initRefAction("div.biography");
		}
	});
}

// 初始化头信息
function initHeadClick() {
	// 复制home区域内容
	var container = $("div.content div.container").children();
	var className = container.eq(0).attr("class");
	if ("load_more" == className) {
		container.clone().appendTo("div#hometmp");
	}
	// 创建分类div
	createCategoryDiv();
}

// cate整合
function cate4Integrate(cateList) {
	// 遍历分类列表
	for (var i = 0; i < cateList.length; i++) {
		categoryVal = category.replace('${title}', cateList[i].title);
		categoryVal = categoryVal.replace('${summary}', cateList[i].summary);
		categoryVal = categoryVal
				.replace('${articleId}', cateList[i].articleId);
		categoryVal = categoryVal
				.replace('${readCount}', cateList[i].readCount);
		categoryVal = categoryVal.replace('${days}', cateList[i].days);

		// 若是点击“查看更多”按钮
		if ("header" != controls) {
			// 将最新内容加载到最后
			var lastP = $("div.biographys").length - 1;
			$(categoryVal).insertAfter($("div.biographys").eq(lastP));
		} else {
			// 若是初始化列表，使用倒序展示
			$("div.biography").prepend(categoryVal);
		}
	}
}

// 其他类型创建的时候必须加一层div
function createCategoryDiv() {
	// 如果div container存在其他div的子节点
	if ($("div.content div.container:has(div)").length != 0) {
		// 将其清空
		$("div.content div.container").empty();
		// 追加cateContent层级
		$("div.content div.container").append(cateContent);
	}
}