// 保存并将内容放在最新更新栏目里面
function showAndSave() {
	var updateInfo = $("#inputInfo").val();
	$(".rec_title").show();
	// save方法
	saveUpdated(updateInfo);
}

// 保存更新信息
function saveUpdated(updateInfo) {
	$.ajax({
		type : 'POST',
		url : '/KHome/updated/saveUpdated',
		data : updateInfo,
		cache : false,
		dataType : "json", // 后台返回值类型
		contentType : "application/json",
		success : function(resultData) { // 获取后台返回json列表后，做字符串替换
			if (resultData == '1') {
				var info = udpateDyncInside.replace('${info}', updateInfo);
				$("ul#recupdated").append(info);
				$("#inputInfo").val("");
			}
		}
	});
}