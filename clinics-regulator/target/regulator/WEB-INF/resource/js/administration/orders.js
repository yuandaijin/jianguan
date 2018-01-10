/*服务项目、订单下拉列表显示与隐藏*/
$(".f-choose-list").find("dt,dd").hover(function() {
	if(this.nodeName == "DT") {
		$(".f-choose-list dt+dd").addClass("s-default");
	}
	$(this).parent().removeClass("s-choose-before").addClass("s-choose-after");
}, function() {
	if(this.nodeName == "DT") {
		$(".f-choose-list dt+dd").removeClass("s-default");
	}
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});

/*选择日期*/
$(".time-choose").val("2015-10-11"); //初始化日期
$(".time-choose").on("click",function(e){
	$(this).blur(); //去掉input的光标
    e.stopPropagation();
    $(this).lqdatetimepicker({
        css : 'datetime-day',
        dateType: 'D'
    });
});

/*选择下拉列表选项*/
$(".consume-type-list").find("dt,dd").bind("click", function() {
	// console.log(this);
	$(".consume-type-list dt").html($(this).text());
	$(".consume-type-list dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
$(".consume-state-list").find("dt,dd").bind("click", function() {
	// console.log(this);
	$(".consume-state-list dt").html($(this).text());
	$(".consume-state-list dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
$(".refund-type-list").find("dt,dd").bind("click", function() {
	console.log(this);
	$(".refund-type-list dt").html($(this).text());
	$(".refund-type-list dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
$(".refund-state-list").find("dt,dd").bind("click", function() {
	// console.log(this);
	$(".refund-state-list dt").html($(this).text());
	$(".refund-state-list dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
$(".bespeak-type-list").find("dt,dd").bind("click", function() {
	console.log(this);
	$(".bespeak-type-list dt").html($(this).text());
	$(".bespeak-type-list dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
$(".bespeak-state-list").find("dt,dd").bind("click", function() {
	// console.log(this);
	$(".bespeak-state-list dt").html($(this).text());
	$(".bespeak-state-list dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});

/*分页*/
$("#order-paging").createPage({
	pageCount : 10,
	current : 1,
	backFn : function(){}
});