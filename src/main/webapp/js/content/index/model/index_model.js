var pwdInput = "<input id='updatepwd' style='width:100px;line-height:22px;margin-left:10px;color:#8da9b9;font-size:14px;text-align:center;' onkeypress='return inputPwd(event)' value='输入更新密码' onclick='inputPrepare(this)' onblur='cleanInput(this)'>";

var info = "{ label: '${keyword}', url: '/KHome/article/labelList/${keyword}', target: '_top' }";

//内容区域信息块html模板（有内容）
var divcontent = "<div class='col-md-3 integ'><div class='l_g_r'>"
		+ "<div class='integer'><h3>${title}</h3>"
		+ "<p class='adm'> <a href='#'>${category}</a> | ${days} 天前发布  | 查阅${readCount}次</p> "
		+ "<p>${summary}</p>"
		+ "<a href='#' id='${articleId}' class='link'>查看全文</a></div>"
		+ "</div></div>";

// 内容区域信息块html模板（没有内容）
var divwithoutcontent = "<div class='col-md-3 integ'><div class='l_g_r'></div></div>";

//层级html模板
var levelContent = "<li><div class='l_g' id='other_row${newrow}'></div></li>";

// 分类层级html模板
var cateContent = "<div class='biography'></div>";

//旁边快速定位栏模板
var sidebar = "<h6><a href='#' id='${articleId}'>${title}</a></h6>";
