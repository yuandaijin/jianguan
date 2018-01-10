/**
 * 异常订单逻辑处理页面
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

/**
 * 搜索保存的变量
 */
var searchVar = {
	serviceState:null,
	searchName:null
};

/**
 * 异常订单完成页面搜索变量
 */
var comSearchVar = {
	serviceState:null,
	searchName:null
};

/**
 * 未完成订单初始化查询
 */
function init_exceptionOrderList(currentPage){
	if(searchVar.serviceState != $('#exception-serviceState > dt').attr("option")){
		$('#exception-serviceState > dt').attr("option",searchVar.serviceState);
	}
	if(searchVar.searchName != $('#exception-searchName').val()){
		$('#exception-searchName').val(searchVar.searchName);
	}
	var serviceExceptionQuery = {
		"orderType":$('#exception-orderType > dt').attr("option"),
		"serviceState":searchVar.serviceState,
		"keyword":searchVar.searchName,
		"pgCt":currentPage,
		"pgSz":12
	};
	$.ajax({
		url:'../serviceException/selectServiceExceptionList',
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
	searchVar.searchName = $('#exception-searchName').val();
	searchVar.serviceState = $('#exception-serviceState > dt').attr("option");
	init_exceptionOrderList(1);
});

/**
 * 未完成订单刷新
 */
$('#exception-refreshBtn').on('click',function(){
	searchVar = {
		serviceState:null,
		searchName:null
	};
	clearSearchForm();
	init_exceptionOrderList(1);
});


/**
 * 已完成订单初始化查询
 */
function init_exceptionComOrderList(currentPage){
	if(comSearchVar.serviceState != $('#exception-com-serviceState > dt').attr("option")){
		$('#exception-com-serviceState > dt').attr("option",comSearchVar.serviceState);
	}
	if(comSearchVar.searchName != $('#exception-com-searchName').val()){
		$('#exception-com-searchName').val(comSearchVar.searchName);
	}
	var serviceExceptionQuery = {
		"orderType":$('#exception-com-orderType > dt').attr("option"),
		"serviceState":comSearchVar.serviceState,
		"keyword":comSearchVar.searchName,
		"pgCt":currentPage,
		"pgSz":12
	};
	$.ajax({
		url:'../serviceException/selectServiceExceptionComList',
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
	comSearchVar.searchName = $('#exception-com-searchName').val();
	comSearchVar.serviceState = $('#exception-com-serviceState > dt').attr("option");
	init_exceptionComOrderList(1);
});

/**
 * 已完成订单刷新
 */
$('#exception-com-refreshBtn').on('click',function(){
	comSearchVar = {
		serviceState:null,
		searchName:null
	};
	clearComSearchForm();
	init_exceptionComOrderList(1);
});

/**
 * 监听点击详情
 */
$(document).on('click','.order-details',function(){
	var visitId = $(this).attr('visitId');
	var orderNo = $(this).attr('orderNo');
	$.ajax({
		url:'../serviceException/findExceptionOrderDetails',
		type:'POST',
		async:false,
		data:{"visitId":visitId,"orderNo":orderNo},
		success:function(datas){
			if(datas.state == true){
				window.open("../serviceException/serviceExceptionDetails/"+orderNo);
			}else{
				layer.alert(datas.msg, function(index){
					layer.close(index);
					init_exceptionOrderList(1);
				});
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
	window.open("../serviceException/serviceExceptionComDetails/"+orderNo);
});


/**
 * 未办理列表条件搜索清空
 */
function clearSearchForm(){
	$('#exception-orderType > dd:first').trigger('click');
	$('#exception-serviceState > dd:first').trigger('click');
	$('#exception-searchName').val('');
}

/**
 * 已办理列表条件搜索清空
 */
function clearComSearchForm(){
	$('#exception-com-orderType > dd:first').trigger('click');
	$('#exception-com-serviceState > dd:first').trigger('click');
	$('#exception-com-searchName').val('');
}

/**
 * 异常订单待办理订单类型切换
 */
$("body").on("click", "#exception-orderType dt,#exception-orderType dd", function() {
	init_exceptionOrderList(1);
});
/**
 * 异常订单已办理订单类型切换
 */
$("body").on("click", "#exception-com-orderType dt,#exception-com-orderType dd", function() {
	init_exceptionComOrderList(1);
});

/**
 * 异常订单tab切换
 */
$(document).on('click','#serviceException-wait',function(){
	init_exceptionOrderList(1);
});
/**
 * 异常订单tab切换
 */
$(document).on('click','#serviceException-com',function(){
	init_exceptionComOrderList(1);
});