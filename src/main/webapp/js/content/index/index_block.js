// 初始化内容区域查询记录
function initContentBlock() {
	var blockContent = null;
	$
			.ajax({
				type : 'POST',
				url : '/KHome/article/blocklist',
				async : false,
				cache : false,
				dataType : "json", // 后台返回值类型
				contentType : "application/json",
				success : function(resultData) {
					// 计数器
					var counter = 1;
					// 返回块内容列表
					var blockList = resultData.blockList;
					// 块数量
					var lengthInSide = blockList.length;
					// 遍历块列表
					for (var i = 0; i < blockList.length; i++) {
						// 替换模板中需要动态变化的内容
						blockContent = divcontent.replace('${title}',
								blockList[i].title);
						blockContent = blockContent.replace('${summary}',
								blockList[i].summary);
						blockContent = blockContent.replace('${articleId}',
								blockList[i].articleId);
						blockContent = blockContent.replace('${category}', $(
								"ul.cl-effect-15 li a#"
										+ blockList[i].category.toLowerCase())
								.html());
						blockContent = blockContent.replace('${days}',
								blockList[i].days);
						blockContent = blockContent.replace('${readCount}',
								blockList[i].readCount);
						// 若循环小于3次
						if (i < 3) {
							// 将内容加载到first_row层中
							$("div#first_row").prepend(blockContent);
						} else if (i < 6) {
							// 若少于6次，则加载到second_row层中
							$("div#second_row").prepend(blockContent);
						} else if (i < 9) {
							// 若少于9次，则加载到third_row层中
							$("div#third_row").prepend(blockContent);
						} else {
							// 若大于9次后则自动创建层
							if (i % 3 == 0 && i < 12) {
								createLevelDiv();
							}
							// 加载到动态层中
							if ((i + 1) % 3 == 0 && lengthInSide % 3 != 0) {
								$("div#other_row" + counter).prepend(
										divwithoutcontent);
								counter++;
							} else {
								$("div#other_row" + counter).prepend(
										blockContent);
							}
						}
					}
					// 循环结束后还要检查一下有没有需要留白的区域
					if (lengthInSide > -1 && lengthInSide < 3) {
						// 若块数不够第一行的时候需要填上空白信息到first_row
						var firstRemain = Math.abs(lengthInSide - 3);
						for (var t = 0; t < firstRemain; t++) {
							if (firstRemain == 3) {
								$("div#first_row").prepend(divwithoutcontent);
							} else {
								$("div#first_row .integ").eq(2 - firstRemain)
										.after(divwithoutcontent);
							}
							if (t == 0) {
								for (var z = 0; z < 3; z++) {
									$("div#second_row").prepend(
											divwithoutcontent);
									$("div#third_row").prepend(
											divwithoutcontent);
								}
							}
						}
					} else if (lengthInSide > 2 && lengthInSide < 6) {
						// 若块数不够第二行的时候需要填上空白信息到second_row
						var secondRemain = Math.abs(lengthInSide - 6);
						for (var v = 0; v < secondRemain; v++) {
							if (secondRemain == 3) {
								$("div#second_row").prepend(divwithoutcontent);
							} else {
								$("div#second_row .integ").eq(2 - secondRemain)
										.after(divwithoutcontent);
							}
							if (v == 0) {
								for (var u = 0; u < 3; u++) {
									$("div#third_row").prepend(
											divwithoutcontent);
								}
							}
						}
					} else if (lengthInSide > 5 && lengthInSide < 9) {
						// 若块数不够第二行的时候需要填上空白信息到third_row
						var thirdRemain = Math.abs(lengthInSide - 9);
						for (var j = 0; j < thirdRemain; j++) {
							if (thirdRemain == 3) {
								$("div#third_row").prepend(divwithoutcontent);
							} else {
								$("div#third_row .integ").eq(2 - thirdRemain)
										.after(divwithoutcontent);
							}
						}
					} else {
						// 通过取模的方式看有多少空的区域
						var remains = 3 - (lengthInSide % 3);
						// 若存在，则再计算定位层级
						if (remains > 0 && remains < 3) {
							for (var b = 0; b < remain; b++) {
								$("div#other_row1").append(divwithoutcontent);
							}
						} else {
							$("div#other_row1").append(divwithoutcontent);
						}
						$("div#other_row1").append(
								"<div class='clearfix'></div>");
					}
					// 将追加的层级显示
					$('#myList li:lt(' + x + ')').show();

				}
			});
}
