/**
 * 异常订单详情页面
 */
$(function(){
	init_exceptionOrderDetails();
});

/**
 * 初始化数据
 */
function init_exceptionOrderDetails(){
	$.ajax({
		url:'../selectExceptionComDetails',
		type:'POST',
		data:{"orderNo":getOrderNo()},
		success:function(datas){
			var appendHtml = template('exception-order-com-details-template',datas);
			$('#showDetails').html(appendHtml);
			if(datas.msg.orderType == 0 && datas.msg.solveType == 2 ){/*普通取消订单*/
				$('.mormal-cancel').hide();
			}else if(datas.msg.orderType == 0 && datas.msg.solveType == 0 ){/*普通完成订单*/
				$('.mormal-com').hide();
			}else if(datas.msg.orderType == 1 && datas.msg.solveType == 0 ){
				$('.vis-com').hide();
			}else if(datas.msg.orderType == 1 && datas.msg.solveType == 2 ){
				$('.vis-cancel').hide();
			}else if(datas.msg.orderType == 1 && datas.msg.solveType == 1 ){
				$('.vis-change').hide();
			}
		},
		complete:function(){
			
		}
	});
}


/**
 * 通过地址栏获取orderNo
 * @returns
 */
function getOrderNo(){
	var url = window.location.pathname;
	var orderNo = url.substr(url.lastIndexOf('/')+1);
	return orderNo;
}