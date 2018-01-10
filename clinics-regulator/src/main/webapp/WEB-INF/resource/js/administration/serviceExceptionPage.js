/**
 * 
 */
/*下拉列表显示与隐藏*/
$("body").on("mouseover", ".f-choose-list dt,.f-choose-list dd", function() {
    if(this.nodeName == "DT") {
    	 $(this).parents('.f-choose-list').find('dt,dd').addClass("s-default");
    }
    $(this).parent().removeClass("s-choose-before").addClass("s-choose-after");
});
$("body").on("mouseout", ".f-choose-list dt,.f-choose-list dd", function() {
    if(this.nodeName == "DT") {
    	 $(this).parents('.f-choose-list').find('dt,dd').removeClass("s-default");
    }
    $(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
/*选择下拉列表选项*/
$("body").on("click", ".f-choose-list dt,.f-choose-list dd", function() {
    $(this).parents('.f-choose-list').find('dt').html($(this).text());
    $(this).parents('.f-choose-list').find('dt').attr("option", $(this).attr("option"));
    $(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
$(".type-btns").on("click", "a", function() {
	$(this).parents(".type-btns").find("a").removeClass("s-color");
	$(this).addClass("s-color");
	$(this).parents(".o-content").find(".t-content").hide();
	$($(this).parents(".o-content").find(".t-content").eq($(this).attr("data-link")-1)).show();
});
/**
 * 未完成订单初始化查询
 */

$(function(){
	init_exceptionOrderList(1);
	init_exceptionComOrderList(1);
	showTabPage();
});
function init_exceptionOrderList(currentPage){
	var serviceExceptionQuery = {
		"orderType":$('#exception-orderType > dt').attr("option"),
		"serviceState":$('#exception-serviceState > dt').attr("option"),
		"keyword":$('#exception-searchName').val(),
		"pgCt":currentPage,
		"pgSz":12
	};
	$.ajax({
		url:'../selectServiceExceptionList',
		type:'POST',
		async:false,
		data:{"serviceExceptionQueryString":JSON.stringify(serviceExceptionQuery)},
		success:function(datas){
			var appendHtml = template('exception-order-template',datas);
			$('#exception-order-list').html(appendHtml);
			$("#exception-page").createPage({
				pageCount : datas.pageCount,
				current : currentPage,
				backFn : function(p){
					init_exceptionOrderList(p);
				}
			});
		},
		complete:function(){
			
		}
	});
};

/**
 * 未完成订单搜索
 */
$('#exception-searchBtn').on('click',function(){
	init_exceptionOrderList(1);
});

/**
 * 未完成订单刷新
 */
$('#exception-refreshBtn').on('click',function(){
	init_exceptionOrderList($('#exception-page .current').html());
});


/**
 * 已完成订单初始化查询
 */
function init_exceptionComOrderList(currentPage){
	var serviceExceptionQuery = {
		"orderType":$('#exception-com-orderType > dt').attr("option"),
		"dealState":$('#exception-com-serviceState > dt').attr("option"),
		"keyword":$('#exception-com-searchName').val(),
		"pgCt":currentPage,
		"pgSz":12
	};
	$.ajax({
		url:'../selectServiceExceptionComList',
		type:'POST',
		async:false,
		data:{"serviceExceptionQueryString":JSON.stringify(serviceExceptionQuery)},
		success:function(datas){
			var appendHtml = template('exception-com-order-template',datas);
			$('#exception-com-order-list').html(appendHtml);
			$("#exception-com-page").createPage({
				pageCount : datas.pageCount,
				current : currentPage,
				backFn : function(p){
					init_exceptionComOrderList(p);
				}
			});
		},
		complete:function(){
			
		}
	});
};

/**
 * 已完成订单搜索
 */
$('#exception-com-searchBtn').on('click',function(){
	init_exceptionComOrderList(1);
});

/**
 * 已完成订单刷新
 */
$('#exception-com-refreshBtn').on('click',function(){
	init_exceptionComOrderList($('#exception-com-page .current').html());
});

/**
 * 监听点击详情
 */
$(document).on('click','.order-details',function(){
	var visitId = $(this).attr('visitId');
	var orderNo = $(this).attr('orderNo');
	$.ajax({
		url:'../findExceptionOrderDetails',
		type:'POST',
		async:false,
		data:{"visitId":visitId,"orderNo":orderNo},
		success:function(datas){
			if(datas.state == true){
				window.open("../serviceExceptionDetails/"+orderNo);
			}else{
				layer.msg(datas.msg);
			}
		},
		complete:function(){
			
		}
	});
	//alert($(this).attr('orderNo'));
});


/**
 * 已完成异常订单监听点击详情
 */
$(document).on('click','.order-com-details',function(){
	var orderNo = $(this).attr('orderNo');
	window.open("../serviceExceptionComDetails/"+orderNo);
});

function showTabPage(){
	if(getOperation() == 'finish'){
		$(".finish").trigger("click");
	}
}


/**
 * 通过地址栏获取operation
 * @returns wait finish
 */
function getOperation(){
	var url = window.location.pathname;
	var operation = url.substr(url.lastIndexOf('/')+1);
	return operation;
}