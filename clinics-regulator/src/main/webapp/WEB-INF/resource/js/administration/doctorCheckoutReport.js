/*下拉列表显示与隐藏*/
$("body").on("mouseover", ".f-choose-list dt,.f-choose-list dd", function() {
	if(this.nodeName == "DT") {
		$(".f-choose-list dt+dd").addClass("s-default");
	}
	$(this).parent().removeClass("s-choose-before").addClass("s-choose-after");
});
$("body").on("mouseout", ".f-choose-list dt,.f-choose-list dd", function() {
	if(this.nodeName == "DT") {
		$(".f-choose-list dt+dd").removeClass("s-default");
	}
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
/*选择下拉列表选项*/
$("body").on("click", ".update-type-list dt,.update-type-list dd", function() {
	// console.log(this);
	$(".update-type-list dt").html($(this).text());
	$(".update-type-list dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});

/*分页*/
$("#doctor-heckout-paging").createPage({
	pageCount : 10,
	current : 1,
	backFn : function(){}
});