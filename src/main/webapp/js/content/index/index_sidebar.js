// logs模块初始化
function initLogsSideBar() {
	var sidebarVal = null;
	$.ajax({
		type : 'POST',
		url : '/KHome/article/logslist',
		async : false,
		cache : false,
		dataType : "json", // 后台返回值类型
		contentType : "application/json",
		success : function(resultData) {
			// 获取后台返回json列表
			var logsList = resultData.logsList;
			// 通过遍历替换掉模板中的动态信息后再追加在logs层的里面
			for (var i = 0; i < logsList.length; i++) {
				sidebarVal = sidebar.replace('${articleId}',
						logsList[i].articleId);
				sidebarVal = sidebarVal.replace('${title}', logsList[i].title);
				$("div.logs").append(sidebarVal);
			}

		}
	});
}

// Experiment模块初始化
function initExperSideBar() {
	var sidebarVal = null;
	$
			.ajax({
				type : 'POST',
				url : '/KHome/article/experlist',
				async : false,
				cache : false,
				dataType : "json", // 后台返回值类型
				contentType : "application/json",
				success : function(resultData) {
					var experList = resultData.experList;
					for (var i = 0; i < experList.length; i++) {
						sidebarVal = sidebar.replace('${articleId}',
								experList[i].articleId);
						sidebarVal = sidebarVal.replace('${title}',
								experList[i].title);
						$("div.experiment").append(sidebarVal);
					}
				}
			});
}

// 初始化标签云
function initCloudLabel() {
	$.ajax({
		type : 'POST',
		url : '/KHome/dicCommon/labelDicList',
		data : "labelKeyWord",
		cache : false,
		dataType : "json", // 后台返回值类型
		contentType : "application/json",
		success : function(resultData) { // 获取后台返回json列表后，做字符串替换
			var dicCommonLIst = resultData.dicCommonLIst;
			if (dicCommonLIst.length > 0) {
				$("#tag-cloud svg").remove();
				var arr = new Array(dicCommonLIst.length);

				for (var i = 0; i < dicCommonLIst.length; i++) {
					var keyword = dicCommonLIst[i].code;
					var changeInfo = info.replace('${keyword}', keyword);
					arr[i] = changeInfo.replace('${keyword}', keyword);
				}
				var entries = eval("[" + arr.join() + "]");
				$("#tag-cloud").svg3DTagCloud({
					entries : entries,
					fontFamily : 'Microsoft JhengHei',
					width : '200',
					height : '165',
					bgDraw : false,
					fontColor : '#556a7f',
					fontSize : '12',
					speed : '0.5',
					opacitySpeed : '35'
				});
			}
		}
	});
}

/**
 * 使用pjax来实现点击后局部刷新功能
 */
function pjaxInCloudLabel() {
	$(document).on('click', 'a[target=_top]', function(event) {
		$("ul.cl-effect-15 li a").removeClass("active");
		$("#label").addClass("active");

		labelType = event.target.innerHTML;
		event.preventDefault();
		var url = "/KHome/article/labelList/";
		// 创建分类div
		generateCategoryListByType(labelType, 'header', url);
	})

}