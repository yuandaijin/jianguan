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
	getaccount(1);
});
/**
 * 默认日期填充
 */
var times = new Date();
var year = times.getFullYear();
var startmonth = times.getMonth()+1;
var day=times.getDate();
var days = '01';
if(startmonth>9&&startmonth<=12){
	startmonth=startmonth;
}
else if(startmonth>12){
	startmonth="0"+1;
}
else{
	startmonth="0"+startmonth;
}
$("#platform-account-startSear").val(year+'-'+startmonth+'-'+days);
$("#platform-account-endSear").val(year+'-'+startmonth+'-'+day);
/*选择日期*/
$("body").on("click", ".time-choose", function(e){
	$(this).blur(); //去掉input的光标
    e.stopPropagation();
    $(this).lqdatetimepicker({
        css : 'datetime-day',
        dateType: 'D' ,
        selectback : function () { 
        	getaccount(1);
        }
    });
});
function page(){
	$("#platform-account-paging").createPage({
		pageCount : $("#total").val(),
		current : $("#CurrentPage").val(),
		backFn : function(p){
			getaccount(p);
		}
	});
}
/*分页*/
$("#platform-account-paging").createPage({
	pageCount : $("#total").val(),
	current : $("#CurrentPage").val(),
	backFn : function(p){
		getaccount(p);
	}
});
$('#payAccountRecord_order_no_se').on("click", function() {
	getaccount(1);
});
$('.tcdNumber').on("click", function() {
	getaccount(1);
});
function getaccount(p){
	var tets=$('#payAccountRecord_type dt');
	$.ajax({
		url:'accountReponse/',
		type:'POST',
		async:false,
		data:{"id":$('#payAccountRecord_id').val(),
			"type":$('#payAccountRecord_type dt').attr('option'),
			"statrTime":$('#platform-account-startSear').val(),
			"endTime":$('#platform-account-endSear').val(),
			"pageNo":p,
			},
		dataType:'html',
		success:function(date){
			$("#return-table").html(date);
			page();
			},
		error:function(){
			return ;
		}
	});
}
getaccount(1);