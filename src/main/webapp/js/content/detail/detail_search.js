// 打开详细页面方法
function showDetailById(id) {
	var imgVal = null;
	var detailVal = "";

	$.ajax({
		type : 'POST',
		url : '/KHome/article/detailinfo',
		data : id,
		cache : false,
		dataType : "json", // 后台返回值类型
		contentType : "application/json",
		success : function(resultData) {

			var bpvList = resultData.reMap.briefPicVOList;
			for (var i = 0; i < bpvList.length; i++) {
				imgVal = img.replace('${picId}', bpvList[i].picId);
				imgVal = imgVal.replace('${url}', bpvList[i].url);
				detailVal += detail.replace('${brief}', imgVal);
			}

			// 获取子节点内容
			var container = $("div.content div.container").children();
			// 若判断子节点class=load_more的话就将子节点内容复制并追加到hometmp节点后
			var className = container.eq(0).attr("class");
			if ("load_more" == className) {
				container.clone().appendTo("div#hometmp");
			}
			// 将主内容区域清空，并加载详细信息内容
			$("div.content div.container").empty();
			$("div.content div.container").append(detailVal);

			scrollOffset($("div.content div.container").offset());
		}
	});
}

function scrollOffset(scroll_offset) {
	$("body,html").animate({
		scrollTop : scroll_offset.top - 70
	}, 0);
}