var consumptionOrderList = new Array();
var returnOrderList = new Array();
var visitOrderList = new Array();
// 当前订单号
var payOrderNo = null;
$(".o-nav").on("click", "a", function() {
	$(".o-content").hide();
	$(".o-nav > a").removeClass('s-color');
	if ($(this).attr("data-link") == 1) {
		$(this).addClass('s-color');
		$(".o-content").hide();
		$(".consume").show();
		pageSearch(1);// 点击消费订单查询
	} else if ($(this).attr("data-link") == 2) {
		$(this).addClass('s-color');
		$(".o-content").hide();
		$(".refund").show();
		returnPageSearch(1);// 退款订单载入

	} else {
		$(this).addClass('s-color');
		$(".bespeak").show();
		visitPageSearch(1);// 预约订单载入显示
	}
});
/* 服务项目、订单下拉列表显示与隐藏 */
$(".f-choose-list").find("dt,dd").hover(function() {
	if (this.nodeName == "DT") {
		$(".f-choose-list dt+dd").addClass("s-default");
	}
	$(this).parent().removeClass("s-choose-before").addClass("s-choose-after");
}, function() {
	if (this.nodeName == "DT") {
		$(".f-choose-list dt+dd").removeClass("s-default");
	}
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});

/* 选择日期 */
$(".time-choose").val(""); // 初始化日期
$(".time-choose").on("click", function(e) {
	$(this).blur(); // 去掉input的光标
	e.stopPropagation();
	$(this).lqdatetimepicker({
		css : 'datetime-day',
		dateType : 'D'
	});
});

/* 选择下拉列表选项 */
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
//	console.log(this);
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
//	console.log(this);
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

/* 分页 */
$("#order-paging").createPage({
	pageCount : 10,
	current : 1,
	backFn : function() {
	}
});

/** ****加载首页执行方法******* */
(function() {
	pageSearch(1);
})();

/** ******订单搜索按钮点击******* */
$(document).on('click', '#consumption-order', function() {
	pageSearch(1);
});

/** ******订单查询数据填充******** */
function appendConsumption(datas) {
	for (var i = 0; i < datas.length; i++) {
		var appendData = '<tr>' + '<td>' + $_getValString(datas[i].orderNo)
				+ '</td>' + '<td>' + $_getValString(datas[i].createTime)
				+ '</td>' + '<td>' + $_getValString(datas[i].serviceType)
				+ '</td>' + '<td>' + $_getValString(datas[i].userName)
				+ '</td>' + '<td>' + $_getValString(datas[i].price) + '</td>' +
				/* '<td>'+datas[i]+'</td>' + */
				'<td>' + $_getValString(datas[i].relPrice) + '</td>' + '<td>'
				+ $_getValString(datas[i].payWay) + '</td>' + '<td>'
				+ $_getValString(datas[i].tradeNo) + '</td>' + '<td>'
				+ $_getValString(datas[i].orgName) + '</td>' + '<td>'
				+ $_getValString(datas[i].serviceShop) + '</td>' + '<td>'
				+ $_getValString(datas[i].companyName) + '</td>' + '<td>'
				+ $_getValString(datas[i].serviceStateStr) + '</td>' + '<td>'
				+ $_getValString(datas[i].payOrderState) + '</td>'
				+ '<td><a class="consumptionOrder" alertId="' + i
				+ '">订单详情</a></td>' + '</tr>';
		$('#consumption-body').append(appendData);
	}

};

/** *********分页******** */
function pageSearch(currentPage) {
	var serviceType = $('#consumption-serviceType dt').attr('option');
	var payOrderState = $('#consumption-order-state dt').attr('option');
	var startTime = $('#consume-startSear').val();
	var endTime = $('#consume-endSear').val();
	var searchName = $('#consume-search').val();
	var provinceCode = $('#provinceCode').val();
	var cityCode = $('#cityCode').val();
	var countyCode = $('#countyCode').val();
	var payOrderSelectInString = {
		serviceType : serviceType,
		payOrderState : payOrderState,
		startTime : startTime,
		endTime : endTime,
		searchName : searchName,
		currentPage : currentPage,
		pageSize : 10,
		provinceCode : provinceCode,
		cityCode : cityCode,
		countyCode : countyCode
	}
	$.ajax({
		url : '../payOrder/selectCmsOrderList/' + getRondom(),
		type : 'POST',
		async : false,
		data : {
			payOrderSelectInString : JSON.stringify(payOrderSelectInString)
		},
		success : function(datas) {
			$('#consumption-body').empty();
			$("#order-paging").createPage({
				pageCount : datas.totalPage,
				current : datas.currentPage,
				backFn : function(p) {
					pageSearch(p);
				}
			});
			appendConsumption(datas.list);
			consumptionOrderList = datas.list;
		},
		error : function() {
			layer.alert("网络出现故障");
		}
	});
};

/** **********************退款订单查询页面******************************************************** */

$(document).on('click', '#return-search', function() {
	returnPageSearch(1);
});
/** *********退款页面分页搜索************* */
function returnPageSearch(currentPage) {
	var serviceType = $('#return-serviceType dt').attr('option');
	var returnState = $('#return-orderState dt').attr('option');
	var startTime = $('#refund-startSear').val();
	var endTime = $('#refund-endSear').val();
	var searchName = $('#return-searchName').val();
	var payReturnCmsInString = {
		serviceType : serviceType,
		returnState : returnState,
		startTime : startTime,
		endTime : endTime,
		searchName : searchName,
		currentPage : currentPage,
		pageSize : 10
	}
	$.ajax({
		url : '../payReturn/selectCmsReturnList/' + getRondom(),
		type : 'POST',
		async : false,
		data : {
			payReturnCmsInString : JSON.stringify(payReturnCmsInString)
		},
		success : function(datas) {
			$('#returnTbody').empty();
			$("#order-paging").createPage({
				pageCount : datas.totalPage,
				current : datas.currentPage,
				backFn : function(p) {
					returnPageSearch(p);
				}
			});
			appendReturnConsumption(datas.list);
			returnOrderList = datas.list;
		},
		error : function() {
			layer.alert("网络出现故障");
		}
	});
};
/** ******退款订单查询数据填充******** */
function appendReturnConsumption(datas) {
	for (var i = 0; i < datas.length; i++) {
		var appendData = '<tr>' + '<td>' + $_getValString(datas[i].returnNo)
				+ '</td>' + '<td>' + $_getValString(datas[i].createTime)
				+ '</td>' + '<td>' + $_getValString(datas[i].userName)
				+ '</td>' + '<td>' + $_getValString(datas[i].serviceType)
				+ '</td>' + '<td>' + $_getValString(datas[i].orderNo) + '</td>'
				+
				/* '<td>'+datas[i].returnDec+'</td>' + */// 因为只有一种退款方式,写死
				'<td>支付宝</td>' + '<td>' + $_getValString(datas[i].tradeNo)
				+ '</td>' + '<td>' + $_getValString(datas[i].returnPrice)
				+ '</td>' + '<td>' + $_getValString(datas[i].returnReason)
				+ '</td>' + '<td>' + $_getValString(datas[i].cmsName) + '</td>'
				+ '<td>' + $_getValString(datas[i].returnState) + '</td>'
				+ '<td><a class="returnOrder" alertId="' + i
				+ '">订单详情</a></td>' + '</tr>';
		$('#returnTbody').append(appendData);
	}

};

/** **********************预约订单查询开始************************************** */

function visitPageSearch(currentPage) {
	var orderState = $('#visit-order-state dt').attr('option');
	var startTime = $('#bespeak-startSear').val();
	var endTime = $('#bespeak-endSear').val();
	var searchName = $('#visit-search-name').val();
	var visitObject = {
		"orderState" : orderState,
		"startTime" : startTime,
		"endTime" : endTime,
		"searchName" : searchName,
		"currentPage" : currentPage,
		"pageSize" : 10
	}
	$.ajax({
		url : '../Visit/selectReservationForVisitSearch/',
		type : 'POST',
		async : false,
		data : visitObject,
		success : function(datas) {
			$('#visit-list').empty();
			if(!datas.list){
				return;
			}
			$("#order-paging").createPage({
				pageCount : datas.totalPage,
				current : datas.currentPage,
				backFn : function(p) {
					visitPageSearch(p);
				}
			});
			appendVisitConsumption(datas.list);
			visitOrderList = datas.list;
		},
		error : function() {
			layer.alert("网络出现故障");
		}
	});
};

/** ******预约订单查询数据填充******** */
function appendVisitConsumption(datas) {
	for (var i = 0; i < datas.length; i++) {
		var goodsRemark=new Object();
		if(datas[i].goodsRemark!=null&&datas[i].goodsRemark!=""){
			try {
				goodsRemark=$.parseJSON(datas[i].goodsRemark);
			} catch (e) {
			}
		}else {
			goodsRemark.phone="";
		}
		var appendData = '<tr>' + '<td title= '+$_getValString(datas[i].orderNo)+'>' + $_getValString(datas[i].orderNo)
		+ '</td>' + '<td title= "'+ datas[i].reservationTime+'">' + $_getValString(datas[i].reservationTime)
		+ '</td>' + '<td>'
		+ $_getValString(datas[i].asreservationNum) + '</td>'
		+ '<td>' + $_getValString(datas[i].visitTypeName) + '</td>'
		+ '<td>' + $_getValString(datas[i].userName) + '</td>'
		+ '<td title= '+  $_getValString(datas[i].userMobile) +'>' + $_getValString(datas[i].userMobile) + '</td>'
		+ '<td>' + $_getValString(datas[i].orderPrice) + '</td>'
		+ '<td>' + $_getValString(datas[i].payPrice) + '</td>'
		+ '<td>' + $_getValString(datas[i].payWayName) + '</td>'
		+ '<td title= '+  $_getValString(datas[i].tradeNo) +'>' + $_getValString(datas[i].tradeNo) + '</td>'
		+ '<td title= '+  $_getValString(datas[i].orgName) +'>' + $_getValString(datas[i].orgName) + '</td>'
		+ '<td title= '+  $_getValString(datas[i].doctorName) +'>' + $_getValString(datas[i].doctorName) + '</td>'
		+ '<td title= '+  $_getValString(datas[i].doctorTel) +'>'
		+ $_getValString(datas[i].doctorTel)
		+ '</td>' + '<td title= '+ $_getValString(datas[i].companyName)+'>' + $_getValString(datas[i].companyName)
		+ '</td>' + '<td>'
		+ $_getValString(datas[i].serviceStateName) + '</td>'
		+ '<td>' + $_getValString(datas[i].orderStateName) + '</td>'
		+ '<td><a class="visitOrder" alertId="' + i
		+ '" orderNo = "' + $_getValString(datas[i].orderNo)
		+ '">订单详情</a></td>' + '</tr>';
		$('#visit-list').append(appendData);
	}

};

/** *****预约订单搜索******* */
$(document).on('click', '#visit-search', function() {
	visitPageSearch(1);
});

/** **********弹窗部分功能******************* */
/* 弹窗 */
function closePopup() { // 关闭弹窗
	$(".cancel-order").click(function() {
		$(".layui-layer-shade").remove();
		$(".layui-layer").empty().remove();
	});
}

// 消费订单
var consumeDetailPopup = $("#consume-detail-warp").html();
// 退款订单
var refundDetailPopup = $("#refund-detail-warp").html();
// 预约订单
var bespeakDetailPopup = $("#bespeak-detail-warp").html();
// 取消订单
var cancelOrderPopup = $("#cancel-order-warp").html();
// console.log(cancelOrderPopup)
$(".popup-warp").empty().remove();

$(".consume").on("click", ".consumptionOrder", function() {
	layer.open({
		type : 1,
		title : '消费订单详情',
		fix : false, // 不固定
		shadeClose : false, // 开启遮罩关闭
		skin : 'layui-layer-rim', // 加上边框
		area : [ '757px', '550px' ], // 宽高
	// content: $("#update-popup-warp").html()
	});
	var currentData = consumptionOrderList[$(this).attr('alertId')];
	$(".layui-layer-content").html(consumeDetailPopup);
	appendPopConsume(currentData);// 填充pop数据
	closePopup();
});

/** *******订单查详情pop窗口数据填充******* */
function appendPopConsume(data) {
	var head = '<li>订单号：<span>' + $_getValString(data.orderNo)
			+ '</span></li><li>订单状态：<span>'
			+ $_getValString(data.payOrderState) + '</span></li>';
	$('#consume-pop-head').html(head);
	var body = '<li>订单内容：</li>' + '<li>服务项目：<span>'
			+ $_getValString(data.serviceType) + '</span></li>'
			+ '<li>计费方式：<span>按次计费</span></li>' + '<li>服务原价：<span>￥'
			+ data.price + '</span></li>' + '<li>服务时长：<span>10-20分钟</span></li>'
			+ '<li>应付金额：<span>￥' + data.relPrice + '</span></li>'
			+ '<li>下单时间：<span>' + $_getValString(data.createTime)
			+ '</span></li>';
	$('#consume-pop-body').html(body);
	payOrderNo = data.orderNo;
	// 待付款
	if (data.orderState == 0) {
		order_close(data);
	}
	// 已付款
	if (data.orderState == 1) {
		pay_order_time(data.orderNo);
		$(".detail").removeClass("hide");
		pay_bonus_company(data);
	}
	// 已关闭
	if (data.orderState == 2) {
		$(".tips").text("因用户在下单45分钟后仍未支付，系统自动关闭该订单");
	}
	// 已取消
	if (data.orderState == 3) {
		$(".tips").text("用户下单后未支付就取消该笔订单");
	}
	// 待退款
	if (data.orderState == 4) {
		$(".tips").text("用户已经取消该服务，正在进行退款审核");
		pay_order_time(data.orderNo);
		cancel_order_time(data.orderNo);
		$(".refund-reason").removeClass("hide");
	}
	// 已退款
	if (data.orderState == 5) {
		$(".tips").text("已退款");
		pay_order_time(data.orderNo);
		cancel_order_time(data.orderNo);
		cancel_order_time2(data.orderNo);
		$(".refund-reason").removeClass("hide");
	}
	// 已完成
	if (data.orderState == 6) {
		pay_order_time(data.orderNo);
		$(".detail").removeClass("hide");
		pay_bonus_company(data);
	}
	doctor_dateil(data.orderNo);
	patient_dateil(data.orderNo);
	if (data.orderState != 1 && data.orderState != 6) {
		$(".detail").addClass("hide");
	}
	if (data.orderState != 4 && data.orderState != 5) {
		$(".refund-reason").addClass("hide");
	}
	if (data.orderState == 0 || data.orderState == 1) {
		$('.cancel-order-bth').on('click', function() {
			getPayBackUrl(data.orderNo, data.cid);
		});
	} else {
		$('.cancel-order-bth').remove();
	}
}

/**
 * 消费订单详情 订单关闭时间
 */
function order_close(data) {
	var date1 = new Date(data.createTime);
	var date2 = new Date();
	date1.setMinutes(date1.getMinutes() + 45, date1.getSeconds(), 0);
	var date4 = Math.abs(date1 - date2) / 1000 / 60;
	if (parseInt(date4) < 45) {
		$(".tips").text("离订单自动关闭还有" + parseInt(date4) + "分钟。。。。。。");
	} else {
		$(".tips").text("已过期");
	}
}
/**
 * 消费订单详情 医生信息
 */
function doctor_dateil(orderNo) {
	$.ajax({
		url : '../payOrder/getDoctorByorderNo',
		type : 'POST',
		async : false,
		data : {
			orderNo : orderNo
		},
		success : function(data) {
			$(".photo img").attr("src", $("#imgpath").val() + data.picture);
			$(".doctor_name").text(data.doctorName);
			$(".doctor_dept").text(data.dept);
			$(".doctor_language").text(data.language);
			$(".doctor_orgName").text(data.orgName);
			$(".doctor_goodAt").text(data.goodAtStr);
		},
		error : function() {
			layer.alert("网络出现故障");
		}
	});
}
/**
 * 消费订单详情 患者信息
 */
function patient_dateil(orderNo) {
	$.ajax({
		url : '../payOrder/getPatientByorderNo',
		type : 'POST',
		async : false,
		data : {
			orderNo : orderNo
		},
		success : function(data) {
			$(".patient-info_name").text(data.name);
			$(".patient-info_modile").text(data.relateTel);
		},
		error : function() {
			layer.alert("网络出现故障");
		}
	});
}
/**
 * 消费订单详情 分成信息
 */
function pay_bonus_company(payorder) {
	if (payorder.orderState == 1) {
		$(".detail caption").text("预计分成明细");
	} else {
		$(".detail caption").text("分成明细");
	}
	$.ajax({
		url : '../payOrder/getBonusByorderNo',
		type : 'POST',
		async : false,
		data : {
			orderNo : payorder.orderNo
		},
		success : function(datas) {
			$(".detail thead tr").html("<th>单位/个人</th>");
			$(".detail tbody tr").html("<td>预计分成</td>")
			for ( var data in datas) {
				$(".detail caption span").text(datas[data].operatorTime);
				$(".detail thead tr").append(
						"<th>" + datas[data].name + "</th>")
				$(".detail tbody tr").append(
						"<td>￥" + datas[data].bonusPrice + "</td>")
			}
		},
		error : function() {
			layer.alert("网络出现故障");
		}
	});
}
/**
 * 消费订单详情 退款原因
 */
function cancel_order_reason(orderNo) {
	$.ajax({
		url : '../payOrder/getReturnByorderNo',
		type : 'POST',
		async : false,
		data : {
			orderNo : orderNo,
		},
		success : function(datas) {
			$(".refund-reason span").text(datas.reason);
		},
		error : function() {
			layer.alert("网络出现故障");
		}
	});
}
/**
 * 消费订单详情 支付时间
 */
function pay_order_time(orderNo) {
	$.ajax({
		url : '../payOrder/getPayByorderNo',
		type : 'POST',
		async : false,
		data : {
			orderNo : orderNo,
		},
		success : function(data) {
			$('#consume-pop-body').append(
					'<li>实付金额：<span>￥' +  $_getValInt(data.price) + '</span></li>');
			$('#consume-pop-body').append(
					'<li>支付时间：<span>' + $_getValString(data.payTime)
							+ '</span></li>');
		},
		error : function() {
			layer.alert("网络出现故障");
		}
	});
}
/**
 * 消费订单详情 退款时间
 */
function cancel_order_time(orderNo) {
	$.ajax({
		url : '../payOrder/getReturnByorderNo',
		type : 'POST',
		async : false,
		data : {
			orderNo : orderNo,
		},
		success : function(data) {
			$('#consume-pop-body').append(
					'<li>退订时间：<span>' + $_getValString(data.createTime)
							+ '</span></li>');
			$('#consume-pop-body').append(
					'<li>应退金额：<span>￥' + $_getValInt(data.price)
							+ '</span></li>');
			$(".refund-reason span").text(data.reason);
		},
		error : function() {
			layer.alert("网络出现故障");
		}
	});
}
/**
 * 消费订单详情 实际退款时间
 */
function cancel_order_time2(orderNo) {
	$.ajax({
		url : '../payOrder/getReturnByorderNoREFUNDEF',
		type : 'POST',
		async : false,
		data : {
			orderNo : orderNo,
		},
		success : function(data) {
			$('#consume-pop-body').append(
					'<li>实退金额：<span>￥' + $_getValInt(data.price)
							+ '</span></li>');
			$('#consume-pop-body').append(
					'<li>退款时间：<span>' + $_getValString(data.notifyTime)
							+ '</span></li>');
			$(".refund-reason span").text(data.reason);
		},
		error : function() {
			layer.alert("网络出现故障");
		}
	});
}
$(".refund").on("click", ".returnOrder", function() {
	layer.open({
		type : 1,
		title : '退款订单详情',
		fix : false, // 不固定
		shadeClose : false, // 开启遮罩关闭
		skin : 'layui-layer-rim', // 加上边框
		area : [ '757px', '440px' ], // 宽高
	});
	var currentData = returnOrderList[$(this).attr('alertId')];
	$(".layui-layer-content").html(refundDetailPopup);
	appendPopReturn(currentData);
	closePopup();
});

/** *******退款订单查详情pop窗口数据填充******* */
function appendPopReturn(data) {
	var head = '<li>订单号：<span>' + $_getValString(data.returnNo)
			+ '</span></li><li>订单状态：<span>' + $_getValString(data.returnState)
			+ '</span></li>';
	$('#return-pop-head').html(head);
	var body = '<li>订单内容：</li>' + '<li>原始业务单号：<span>'
			+ $_getValString(data.orderNo) + '</span></li>'
			+ '<li>原始回执单号：<span>' + $_getValString(data.tradeNo)
			+ '</span></li>' + '<li>服务项目：<span>'
			+ $_getValString(data.serviceType) + '</span></li>'
			+ '<li>用&nbsp;&nbsp;&nbsp;&nbsp;户：<span>'
			+ $_getValString(data.userName) + '</span></li>'
			+ '<li>退款金额：<span>' + $_getValInt(data.returnPrice)
			+ '</span></li>' + '<li>申请时间：<span>'
			+ $_getValString(data.createTime) + '</span></li>'
			+ '<li>退款原因：<span>' + $_getValString(data.returnReason)
			+ '</span></li>';
	$('#return-pop-body').html(body);
}

/** ******************预约订单详情查看***************************************** */
$(".bespeak").on("click", ".visitOrder", function() {
	layer.open({
		type : 1,
		title : '预约订单详情',
		fix : false, // 不固定
		shadeClose : false, // 开启遮罩关闭
		skin : 'layui-layer-rim', // 加上边框
		area : [ '757px', '440px' ], // 宽高
	// content: $("#update-popup-warp").html()
	});
	var orderNo = $(this).attr('orderNo');
	$(".layui-layer-content").html(bespeakDetailPopup);
	var data = visitOrderList[$(this).attr('alertId')];
	var body = '<li>订单内容：</li>' + '<li>服务项目：<span>'
	+ $_getValString(data.serviceType) + '</span></li>'
	+ '<li>计费方式：<span>按次计费</span></li>' + '<li>服务原价：<span>￥'
	+ $_getValInt(data.orderPrice) + '</span></li>' + '<li>服务时长：<span>10-20分钟</span></li>'
	+ '<li>应付金额：<span>￥' + $_getValInt(data.orderPrice) + '</span></li>'
	+ '<li>下单时间：<span>' + $_getValString(data.createTime)
	+ '</span></li>';
	$('#consume-pop-body').html(body);
	payOrderNo = data.orderNo;
	// 待付款
	if (data.orderState == 0) {
		order_close(data);
	}
	// 已付款
	if (data.orderState == 1) {
		pay_order_time(data.orderNo);
		$(".detail").removeClass("hide");
		pay_bonus_company(data);
	}
	// 已关闭
	if (data.orderState == 2) {
		$(".tips").text("因用户在下单45分钟后仍未支付，系统自动关闭该订单");
	}
	// 已取消
	if (data.orderState == 3) {
		$(".tips").text("用户下单后未支付就取消该笔订单");
	}
	// 待退款
	if (data.orderState == 4) {
		$(".tips").text("用户已经取消该服务，正在进行退款审核");
		pay_order_time(data.orderNo);
		cancel_order_time(data.orderNo);
		$(".refund-reason").removeClass("hide");
	}
	// 已退款
	if (data.orderState == 5) {
		$(".tips").text("已退款");
		pay_order_time(data.orderNo);
		cancel_order_time(data.orderNo);
		cancel_order_time2(data.orderNo);
		$(".refund-reason").removeClass("hide");
	}
	// 已完成
	if (data.orderState == 6) {
		pay_order_time(data.orderNo);
		$(".detail").removeClass("hide");
		pay_bonus_company(data);
	}
	doctor_dateil(data.orderNo);
	patient_dateil(data.orderNo);
	if (data.orderState != 1 && data.orderState != 6) {
		$(".detail").addClass("hide");
	}
	if (data.orderState != 4 && data.orderState != 5) {
		$(".refund-reason").addClass("hide");
	}
	closePopup();
});

function loadVisitOrderDetail(orderNo) {
	$.ajax({
		url : '../Visit/selectCmsVisitDetail',
		type : 'POST',
		async : false,
		data : {
			orderNo : orderNo
		},
		success : function(datas) {
			appendVisitOrderDetail(datas);
		},
		error : function() {
			layer.alert("网络出现故障");
		}
	});
};

// 取消订单
$("body").on("click", "a.cancel-order-bth", function() {
	var cancel = layer.confirm(cancelOrderPopup, {
		btn : [ '确定取消', '暂不取消' ]
	// 按钮
	}, function() {
		if($("#f-cancel").val().length == 0) {
			$("#f-cancel").parent().siblings(".errortips").html("<label>请选择取消原因</label>");
			return false;
		}
		$.ajax({
			url : '../payReturn/cancelOrder',
			type : 'POST',
			async : false,
			data : {
				orderNo : payOrderNo,
				cancelReason : $(".cancelReason").val()
			},
			success : function(datas) {
				layer.msg('取消订单成功', {
					icon : 1
				});
				$(".layui-layer-shade").remove();
				$(".layui-layer").empty().remove();// 弹出层关闭
				$('.o-nav').find('a').each(function(index, element) {
					if ($(element).hasClass('s-color')) {
						if ($(element).attr('data-link') == 1) {
							pageSearch(1);// 点击消费订单查询
						} else if ($(element).attr('data-link') == 3) {
							visitPageSearch(1);// 预约订单载入显示
						}
					}
				});
			},
			error : function() {
				layer.msg("网络出现故障", {
					icon : 2
				});
			}
		});
		// 确定取消的回调函数

	}, function() {
		layer.close(cancel);
	});
	$("#f-cancel").bind("focus, click", function() {
		$(".datalist ul").show();
	});
	$("#f-cancel").bind("blur", function() {
		setTimeout(function() {
			$(".datalist ul").hide();
		}, 200);
	});
	$(".datalist").on("click", "li", function() {
		$(this).parents(".datalist").siblings(".errortips").html("");
		$("#f-cancel").attr("data-code", $(this).attr("data-code"));
		$("#f-cancel").val($(this).html());
	});

});
function appendVisitOrderDetail(data) {
	$('#visit-pop-head').html(
			'<li>订单号：<span>' + $_getValString(data.orderNo)
					+ '</span></li><li>订单状态：<span>'
					+ $_getValString(data.orderStateName) + '</span></li>');
	var left = '<li>订单内容：</li>' + '<li>服务项目：<span>'
			+ $_getValString(data.serviceTypeName) + '</span></li>'
			+ '<li>预约时段：<span>' + $_getValString(data.visitTime)
			+ '</span></li>' + '<li>预约诊号：<span>'
			+ $_getValString(data.reservationNum) + '</span></li>'
			+ '<li>付费方式：<span>' + $_getValString(data.payType) + '</span></li>'
			+ '<li>服务价格：<span>' + $_getValString(data.orderPrice)
			+ '</span></li>' +
			/* '<li>服务时长：<span>'+data.createTime+'</span></li>' + */// 现在不用
			'<li>服应付金额：<span>' + $_getValString(data.payPrice) + '</span></li>'
			+ '<li>下单时间：<span>' + $_getValString(data.orderCreateTime)
			+ '</span></li>';
	var right_top = '<li>订专家信息：</li>' + '<li>专家姓名：<span>'
			+ $_getValString(data.doctorName) + '</span></li>'
			+ '<li>所属科室：<span>' + $_getValString(data.doctorDept)
			+ '</span></li>' + '<li>临床职称：<span>'
			+ $_getValString(data.doctorTitlesClinical) + '</span></li>'
			+ '<li>擅长：<span>' + $_getValString(data.doctorGoodAt)
			+ '</span></li>';
	var right_but = '<li>就诊人信息：</li>' + '<li>患者姓名：<span>'
			+ $_getValString(data.patientName) + '</span></li>'
			+ '<li>联系电话：<span>' + $_getValString(data.patientTel)
			+ '</span></li>' + '<li>家庭住址：<span>'
			+ $_getValString(data.patientAddress) + '</span></li>';
	/* '<li>就诊地点：<span>'+data.relPrice+'</span></li>'; */// 现在不用
	$('#visit-pop-left').html(left);
	$('#visit-pop-right-top').html(right_top);
	$('#visit-pop-right-bottom').html(right_but);
	/** 订单取消点击判断 */
	if (data.orderState < 4) {
		$('#b-cancel').on('click', function() {
			getPayBackUrl(data.orderNo, data.cid);
		});
	} else {
		$('#b-cancel').remove();
	}
}

/** ***********退款地址方法***************** */
function getPayBackUrl(orderNo, cid) {
	layer.confirm('是否取消订单？', {
		btn : [ '是的', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			url : '../payReturn/cancelOrder',
			type : 'POST',
			async : false,
			data : {
				orderNo : orderNo,
				cancelReason : cid
			},
			success : function(datas) {
				layer.msg('取消订单成功', {
					icon : 1
				});
				$(".layui-layer-shade").remove();
				$(".layui-layer").empty().remove();// 弹出层关闭
				$('.o-nav').find('a').each(function(index, element) {
					if ($(element).hasClass('s-color')) {
						if ($(element).attr('data-link') == 1) {
							pageSearch(1);// 点击消费订单查询
						} else if ($(element).attr('data-link') == 3) {
							visitPageSearch(1);// 预约订单载入显示
						}
					}
				});
			},
			error : function() {
				layer.msg("网络出现故障", {
					icon : 2
				});
			}
		});
	}, function() {
		layer.msg('操作已取消', {
			icon : 1
		});
	});
}

/** **********随机函数************ */
function getRondom() {
	return Math.floor(Math.random() * 1000);
};
/** **********END************** */
/**
 * 判断值是否存在 返回数字
 * 
 * @param val
 *            值
 * @returns
 */
function $_getValInt(val) {
	if (val) {
		return val;
	} else {
		return 0;
	}
};
function $_getValString(val) {
	if (val) {
		if (val == null || val == 'null') {
			return "";
		}
		return val;
	} else {
		return "";
	}
};
/** *****************************所属区域选择**************************** */
(function() {
	$('#air-local-selector').location([ {
		url : "../payOrder/companyAddress",
		id : 'cityCode',
		name : 'cityCode',
		strId : 'city',
		strName : 'city'
	}, {
		url : "../comm/district",
		id : 'countyCode',
		name : 'countyCode',
		strId : 'county',
		strName : 'county'
	},]);
	// 居住地址显示
	$('.store-selector .text > div').html($('#air-town').attr('homeDistrict'));
})();
/** *****************************END**************************** */
/**
 * 默认日期填充
 */
var times = new Date();
var year = times.getFullYear();
var endyear = times.getFullYear();
var startmonth = times.getMonth() - 2;
var endmonth = times.getMonth() + 1;
var days = times.getDate();
if (startmonth > 9 && startmonth <= 12) {
	startmonth = startmonth;
} else if (startmonth > 12) {
	startmonth = "0" + 1;
} else {
	startmonth = "0" + startmonth;
}
if (endmonth > 9 && endmonth <= 12) {
	endmonth = endmonth;
} else if (endmonth > 12) {
	endmonth = "0" + 1;
	endyear = endyear + 1;
} else {
	endmonth = "0" + (endmonth);
}

$("#consume-startSear").val(getMonthBefore(3));
$("#consume-endSear").val(endyear + '-' + endmonth + '-' + days);
$("#bespeak-startSear").val(getMonthBefore(3));
$("#bespeak-endSear").val(endyear + '-' + endmonth + '-' + days);
/**
 * 得到几个月前的时间
 * @param num
 * @returns {String}
 */
function getMonthBefore(num){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1-num;
	if(month<=0){
		month +=12;
		year-=1;
	}
	month = month<10 ? '0'+month :month;
	var day = date.getDate();
	if(month==2 &&day >=28){
		if((year%4==0 && year%100 != 0) || year%400 ==0){
			day = 29;
		}else{
			day=28;
		}
	}else if((month==4 ||month==6 ||month==9 ||month==11) && day>=30){
		day=30;
	}
	
	
	
	day = day<10 ? '0'+day : day;
	return year+'-'+month+'-'+day;
}
