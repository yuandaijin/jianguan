/*选择日期*/
$("body").on("click", ".time-choose", function(e){
	$(this).blur(); // 去掉input的光标
    e.stopPropagation();
    $(this).lqdatetimepicker({
        css : 'datetime-day',
        dateType: 'D',
        selectback : function () { 
        	getNews(1);
        }
    });
});
/* 选择下拉列表选项 */
$(".consume-type-list").find("dt,dd").bind("click", function() {
	// console.log(this);
	$(".consume-type-list dt").html($(this).text());
	$(".consume-type-list dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
	getNews(1);
});
$(".consume-state-list").find("dt,dd").bind("click", function() {
	// console.log(this);
	$(".consume-state-list dt").html($(this).text());
	$(".consume-state-list dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
	getNews(1);
});
/* 下拉列表显示与隐藏 */
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
/* 选择下拉列表选项 */
$("body").on("click", ".update-type-list dt,.update-type-list dd", function() {
	// console.log(this);
	$(".update-type-list dt").html($(this).text());
	$(".update-type-list dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});


/* 全选或取消全选checkbox选择状态样式  allOrNone等于true时全选，等于false时取消全选*/
function checkedAll(allOrNone) {
	if(allOrNone) { //全选
		$(".o-table").find("input").each(function(i) {
			$(this).prop("checked", true);
			$(this).parents("tr").find("i").addClass("i-checked");
		});
	} else { //取消全选
		$(".o-table").find("input").each(function(i) {
			$(this).prop("checked", false);
			$(this).parents("tr").find("i").removeClass("i-checked");
		});
	}
}
$(".o-table").on("click", "#choose-all1", function(e) {
	e.stopPropagation();
	var ischeck = !$("#choose-all").prop("checked");
	$(this).prop("checked", ischeck);
	checkedAll(ischeck);
});
$(".o-table").on("click", "thead .i-check", function(e) {
	e.stopPropagation();
	var ischeck = !$("#choose-all").prop("checked");
	$(this).prop("checked", ischeck);
	checkedAll(ischeck);
});
$(".o-table").on("click", "tbody .i-check", function(e) {
	e.stopPropagation();
	var ischeck = !$(this).parentsUntil("tr").find("input").prop("checked");
	$(this).parentsUntil("tr").find("input").prop("checked", ischeck);
	$(this).parentsUntil("tr").find("i").toggleClass("i-checked");
});
//textarea文本域自适应高度[自动增加高度]
$.fn.autoHeight = function(){
	
	function autoHeight(elem){
		elem.style.height = 'auto';
		elem.scrollTop = 0; //防抖动
		elem.style.height = elem.scrollHeight + 'px';
	}
	
	this.each(function(){
		autoHeight(this);
		$(this).on('keyup', function(){
			autoHeight(this);
		});
	});
 
}
$(".news-warp").find("textarea").autoHeight();
/* 展示详情内容时候textarea不能输入 */
$("div.show-newsdetail").find("textarea").prop("disabled", true);
/* 点击修改按钮 */
$("#newsinput-btn").on("click", function() {
	$("#newssave-btn").show();
	$("div.news-warp").find("textarea").removeProp("disabled");
	$("#newsinput-btn").hide();
	$(".news-warp").removeClass("show-newsdetail");
});
/* 点击保存按钮 */
$("#newssave-btn").hide();
$("#newssave-btn").on("click", function() {
	/* 展示详情内容时候textarea不能输入 */
	$(".news-warp").addClass("show-newsdetail").find("textarea").prop("disabled", true);
	$("#newssave-btn").hide();
	$("#newsinput-btn").show();
	//$(".news-warp").addClass("show-newsdetail");
	var params = {'name' : $("#title").val(),
			  'description' : $("#summary").val(),
			  'content' :$("#content").val(),
			  "id":null,
			  "delType":$("#del_type").val(),
			  "classType":$("#class_type").val()
			  };
	if(params.name==""||params.createTime==""||params.description==""||params.content==""){
// /alert("全为必填信息");
	}
	if($("#mid").val()!=undefined){
		params.id=$("#mid").val();
	}
	$.ajax({
		url:'addedit',
		type:'POST',
		async:false,
		data:params,
		dataType:'html',
		success:function(date){
			alert(date);
			},
		error:function(){
			alert("保存失败");
		}
	});
});
/* 分页 */
$("#news-paging").createPage({
	pageCount : $("#pageTotal").val(),
	current : $("#pageCurrent").val(),
	backFn : function(p){
		getNews(p)
	}
});
function page(){
/* 分页 */
$("#news-paging").createPage({
	pageCount : $("#pageTotal").val(),
	current : $("#pageCurrent").val(),
	backFn : function(p){
		getNews(p)
	}
});
}
function getNews(p){
	var params = {'classType' : $(".consume-type-list .s-default").attr("option"),
			  'delType' : $(".consume-state-list .s-default").attr("option"),
			  'startTime' : $("#platform-account-startSear").val(),
			  'endTime' :$("#platform-account-endSear").val(),
			  'pageNo' : p
			  };
	$.ajax({
		url:'query',
		type:'POST',
		async:false,
		data:params,
		dataType:'html',
		success:function(date){
			$("#data_list").html(date);
			page();
			},
		error:function(){
			return ;
		}
	});
}
if($("#mid").val()==""){
	$("#newssave-btn").show();
	$("div.news-warp").find("textarea").removeProp("disabled");
	$("#newsinput-btn").hide();
	$(".news-warp").removeClass("show-newsdetail");
}
getNews(1);
/* 点击详情按钮 */
//$(".news-consult").find(".o-table").on("click", "a", function() {
//	window.open("edit?id="+ $(this).attr("data"));
//});

function del(){
	var ids="";
	var $elements = $('.i-checked');
	var len = $elements.length;
	$elements.each(function() {
	var $this = $(this);
	if($this.attr('data')!=undefined){
		ids=ids+($this.attr('data'))+",";
	}
	});
	$.ajax({
		url:'del',
		type:'POST',
		async:false,
		data:"ids="+ids,
		dataType:'html',
		success:function(date){
			getNews(1);
			},
		error:function(){
			return ;
		}
	});
}
$("#newscancel-btn").on("click", function() {
	$.ajax({
		url:'delflag',
		type:'POST',
		async:false,
		data:"id="+$("#mid").val(),
		dataType:'html',
		success:function(date){
			alert("作废成功");
			},
		error:function(){
			return ;
		}
	});
});
