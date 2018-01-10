$(function() {
	//当前概况里面的时间切换开始
	$(".nowTimeChoose>li").bind("click", function() {
			$(".nowTimeList").eq($(this).index()).removeClass("hide").addClass("show").siblings('.nowTimeList').removeClass('show').addClass('hide');
		})
		//当前概况里面的时间切换结束
	//点击切换颜色
	$("ul.clinicInfoTab li").click(function() {
		$(this).addClass("clinicInfoTabBTn").siblings().removeClass("clinicInfoTabBTn");
	});
	//点击切换颜色
	
	
	//匹配区域
	var parentId="";

	//区域下拉框开始
	common.dropDownList();
});
