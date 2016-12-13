var categoryType = null;
var labelType = null;
var blockContent = null;
var counter = 0;

// 隐藏地址栏
function hideURLbar() {
	window.scrollTo(0, 1);
}

// home展开更多块信息
function showMoreAction() {

	// 获取所有p标签是class=adm的节点
	var divblocks = $("p.adm");
	// 通过个数判断到最大的位置
	var lastLocation = divblocks.length;
	// 获取所有other_row开头的div
	var divotherrow = $("div[id^='other_row']");
	// 新行为原来的行+1
	var newrow = divotherrow.length + 1;
	$.ajax({
		type : 'POST',
		url : '/KHome/article/nextrow',
		cache : false,
		data : JSON.stringify(lastLocation),
		dataType : "json", // 后台返回值类型
		contentType : "application/json",
		success : function(resultData) {
			var blockList = resultData.blockList;
			// 块数量
			var lengthInSide = blockList.length;

			if (lengthInSide > 0) {
				createLevelDiv();
				// 遍历块列表
				for (var i = 0; i < blockList.length; i++) {
					// 替换模板中需要动态变化的内容
					blockContent = divcontent.replace('${title}', blockList[i].title);
					blockContent = blockContent.replace('${summary}', blockList[i].summary);
					blockContent = blockContent.replace('${articleId}', blockList[i].articleId);
					blockContent = blockContent.replace('${category}', $("ul.cl-effect-15 li a#" + blockList[i].category.toLowerCase()).html());
					blockContent = blockContent.replace('${days}', blockList[i].days);
					blockContent = blockContent.replace('${readCount}', blockList[i].readCount);
					$("div#other_row" + newrow).prepend(blockContent);
				}

				if (lengthInSide > -1 && lengthInSide < 3) {
					var remain = Math.abs(lengthInSide - 3);
					for (var i = 0; i < remain + 1; i++) {
						$("div#other_row" + newrow).append(divwithoutcontent);
					}
					// 若显示的块没有达到完整的显示，则屏蔽ShowMore按钮
					// $("#ShowMore").hide();
				} else {
					$("div#other_row" + newrow).append(divwithoutcontent);
				}

				$("div#other_row" + newrow).append(
						"<div class='clearfix'></div>");

				// 重新获取行数并显示新内容块
				size_li = $("#myList li").size();
				x = (x + 1 <= size_li) ? x + 1 : size_li;
				$('#myList li:lt(' + x + ')').show();
			}
		}
	})
}

// 初始化主页面所有超链接的跳转详细页面
function initRefAction(selector) {
	// 通过选择器获取到所有a标签
	var aItems = $(selector + " a");
	for (var i = 0; i < aItems.length; i++) {
		// 通过遍历获取该a标签的id值
		var artId = aItems.eq(i).attr("id");
		// 若存在id值的a标签则初始化超链接click调用方法
		if (typeof (artId) != "undefined") {
			aItems.eq(i).click(function() {
				showDetailById(this.id);
			});
		}
	}
}

// 主内容区域创建层级层
function createLevelDiv() {
	// 获取以other_row开头的div节点
	var divotherrow = $("div[id^='other_row']");
	// 新行在原来行数的基础上+1
	var newrow = divotherrow.length + 1;
	// 新层级id数量进行替换
	var newLevel = levelContent.replace("${newrow}", newrow);
	// 追加在ul后面
	$("ul#myList").append(newLevel);
}