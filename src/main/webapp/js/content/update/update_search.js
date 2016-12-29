// 生成初始数据
function generateUpdateList() {
	var updateFixed = "<div class='det_text'>"
			+ "<input id='inputInfo' style='width:100%;display:none;' onkeypress='return setupInfo(event)'/>"
			+ "<hr/>"
			+ "<h2 style='margin:10px 0;display:none;' class='rec_title'>本次更新</h2><ul id='recupdated'></ul>"
			+ "<hr/>" + "<div id='olderupdated'></div>" + "</div>";
	// 获取子节点内容
	var container = $("div.content div.container").children();
	// 若判断子节点class=load_more的话就将子节点内容复制并追加到hometmp节点后
	var className = container.eq(0).attr("class");
	if ("load_more" == className) {
		container.clone().appendTo("div#hometmp");
	}
	// 将主内容区域清空，并加载详细信息内容
	$("div.content div.container").empty();
	$("div.content div.container").append(updateFixed);

	ajax2Update();
}

// 异步查询
function ajax2Update() {
	$.ajax({
		type : 'POST',
		url : '/KHome/updated/lastestDayUpdateList',
		cache : false,
		dataType : "json", // 后台返回值类型
		contentType : "application/json",
		success : function(resultData) { // 获取后台返回json列表后，做字符串替换
			update4Integrate(resultData.updatedLIst);
		}
	});
}

// 展示上一次更新内容
function showNextDay() {
	var oneDay = $("h2.oneday");
	if (oneDay.length > 0) {
		var oldDay = oneDay[oneDay.length - 1].innerHTML;
		ajax2NextDay(oldDay);
	}
}

function ajax2NextDay(oldDay) {
	$.ajax({
		type : 'POST',
		url : '/KHome/updated/showNextDay',
		cache : false,
		data : oldDay,
		dataType : "json", // 后台返回值类型
		contentType : "application/json",
		success : function(resultData) { // 获取后台返回json列表后，做字符串替换
			update4Integrate(resultData.updatedLIst);
		}
	});
}

// 更新信息整合
function update4Integrate(rdList) {
	var updateVal = "";
	var updateInsideVal = "";

	var rdSize = rdList.length;
	if (rdSize > 0) {
		for (var i = 0; i < rdSize; i++) {
			if (i == 0) {
				var days = rdList[i].createTime;
				var date = new Date(days);
				var dateStr = date.getFullYear() + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate();
				updateVal = udpateDyncOutside.replace('${days}', dateStr);
			}
			updateInsideVal += udpateDyncInside.replace('${info}',
					rdList[i].updateInfo);
		}
		updateVal = updateVal.replace('${setinfo}', updateInsideVal);
		$("div#olderupdated").append(updateVal);
	}
}

// 更新版本信息输入
function change2Input() {
	$("ul.cl-effect-15 li a#update").hide();
	$("ul.cl-effect-15 li a#update").after(pwdInput);

}
function inputPrepare(obj) {
	$(obj).val("");
}
function cleanInput(obj) {
	var value = $(obj).val();
	if ("" == value) {
		$(obj).val("输入更新密码");
	}
}
// 输入密码
function inputPwd(e) {
	var keyCode = null;
	if (e.which)
		keyCode = e.which;
	else if (e.keyCode)
		keyCode = e.keyCode;

	if (keyCode == 13) {
		var updatepwd = $("#updatepwd").val();
		$.ajax({
			type : 'POST',
			url : '/KHome/updated/checkPwd',
			data : updatepwd,
			cache : false,
			dataType : "json", // 后台返回值类型
			contentType : "application/json",
			success : function(resultData) { // 获取后台返回json列表后，做字符串替换
				if (resultData.reVal == 'pass') {
					$("#updatepwd").remove();
					$("#inputInfo").show();
					$("ul.cl-effect-15 li a#update").show();
				} else {
					$("#updatepwd").remove();
					$("ul.cl-effect-15 li a#update").show();
				}
			}
		});
		return false;
	}
	return true;
}
