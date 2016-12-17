$(document).ready(function() {
	initLogsSideBar();
	initExperSideBar();
	initContentBlock();
	initRefAction("ul#myList");
	initHeaderOtherAction();

	$("a#home").click(function() {
		initHeaderHomeAction();
	});

	$("div.rinfor").click(function() {
		chick2ShowRecommend();
	});

	$("ul.cl-effect-15 li a").removeClass("active");
	$("#home").addClass("active");

	switchHeadType();
	initCloudLabel();
	pjaxInCloudLabel();
	moveJsonp();
});
