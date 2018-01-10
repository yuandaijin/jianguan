var G_dhzsjlb = new Array();//待回执时间列表保存
var G_yqfsjlb = new Array();//已清分时间列表保存
var clearing_state_type = 0;//清分是否回执的状态位
//待办理退款搜索条件
var isPayreturnSraech={
		serviceState:null,
		searchName:null,
		r_wait_startSear:null,
		r_wait_endSear:null,
		provinceCode:null,
		cityCode:null,
		countyCode:null,
		areaName:null,
		serviceStateName:null
	};
//已办理退款搜索条件
var AlreadyPayreturnSraech={
		searchName:null,
		r_wait_startSear:null,
		r_wait_endSear:null,
		provinceCode:null,
		cityCode:null,
		countyCode:null,
		areaName:null,
	};
var cacheNotDealStartTime = "";
var cacheNotDealEndTime = "";
var cacheNotDealSearchName = "";
var cacheIsDealStartTime = "";
var cacheIsDealEndTime = "";
var cacheIsDealSearchName = "";
var cacheIsDealServiceState = "";
$(function(){
		
		$('#air-local-selector').location([ {
			url : "../payReturn/queryProvince",
			id : 'provinceCode',
			name : 'provinceCode',
			strId : 'province',
			strName : 'province'
		},{
			url : "../payReturn/queryCity",
			id : 'cityCode',
			name : 'cityCode',
			strId : 'city',
			strName : 'city'
		}, {
			url : "../payReturn/queryCounty",
			id : 'countyCode',
			name : 'countyCode',
			strId : 'county',
			strName : 'county'
		},]);
	$('#air-local-selector1').location([ {
		url : "../payReturn/queryProvince",
		id : 'provinceCode1',
		name : 'provinceCode',
		strId : 'province1',
		strName : 'province'
	},{
		url : "../payReturn/queryCity",
		id : 'cityCode1',
		name : 'cityCode',
		strId : 'city1',
		strName : 'city'
	}, {
		url : "../payReturn/queryCounty",
		id : 'countyCode1',
		name : 'countyCode',
		strId : 'county1',
		strName : 'county'
	},]);
	var month=
	$("#r-wait-startSear").val(getMonthThereAgo());
	$("#r-wait-endSear").val(new Date().getFullYear()+"-"+getMonth()+"-"+new Date().getDate());
	$("#r-already-startSear").val(getMonthThereAgo());
	$("#r-already-endSear").val(new Date().getFullYear()+"-"+getMonth()+"-"+new Date().getDate());
	}()); 
//点击空白处时间选择关闭
$("body").on("click","#layui-layer1 .layui-layer-content",function(){
	$(".time-choose-warp").remove();
});
$(".type-btns").on("click", "a", function() {
	$(this).parents(".type-btns").find("a").removeClass("s-color");
	$(this).addClass("s-color");
	$(this).parents(".o-content").find(".t-content").addClass('hide');
	$($(this).parents(".o-content").find(".t-content").eq($(this).attr("data-link")-1)).removeClass('hide');
	
	if($(this).parents(".subnav").prev().length !=0) {
		$(this).parents(".subnav").prev().html($(this).attr("data-title"));
	} 
});
$(".refund-nav").on("click", "a", function() {
	$(".refund-nav a").removeClass("s-color");
	$(this).addClass("s-color");
if($(this).attr("data-link") == "1") {
	//待办理，搜索条件初始化
//	isPayreturnSraech.searchName=$(".pay_return-search").val();
//	isPayreturnSraech.serviceState=$(".pay_return_state dt").attr("option");
//	isPayreturnSraech.r_wait_startSear= $("#r-wait-startSear").val();
//	isPayreturnSraech.r_wait_endSear=$("#r-wait-endSear").val();
//	isPayreturnSraech.provinceCode= $("#air-local-selector").parent().find("#provinceCode").val();
//	isPayreturnSraech.cityCode=$("#air-local-selector").parent().find("#cityCode").val();
//	isPayreturnSraech.countyCode=$("#air-local-selector").parent().find("#countyCode").val();
//	isPayreturnSraech.areaName=$(".r-wait .text div").text();
	getPayReturn(1);
} else if($(this).attr("data-link") == "2") {
	//已办理，搜索条件初始化
	AlreadyPayreturnSraech.searchName=$(".pay_return-search2").val();
	AlreadyPayreturnSraech.r_wait_startSear= $("#r-already-startSear").val();
	AlreadyPayreturnSraech.r_wait_endSear=$("#r-already-endSear").val();
	AlreadyPayreturnSraech.provinceCode= $("#air-local-selecto1r").parent().find("#provinceCode1").val();
	AlreadyPayreturnSraech.cityCode=$("#air-local-selector1").parent().find("#cityCode1").val();
	AlreadyPayreturnSraech.countyCode=$("#air-local-selector1").parent().find("#countyCode1").val();
	AlreadyPayreturnSraech.areaName= $(".r-already .text div").text();
	getPayReturn(1);
}
});
/*点击导航条按钮*/

$(".service-nav").on("click", "a", function() {
	$(".o-content").addClass('hide');
	$(".service-nav a").removeClass("s-color");
	$(this).addClass("s-color");
	$("." + $(this).attr("data-link")).removeClass('hide');
	if($(this).attr("data-link") == "clear-server") {
		$("#mechanism-paging").addClass("hide");
	} else if($(this).attr("data-link") == "refund-server") {
		isPayreturnSraech.searchName=$(".pay_return-search").val();
		isPayreturnSraech.serviceState=$(".pay_return_state dt").attr("option");
		isPayreturnSraech.r_wait_startSear= $("#r-wait-startSear").val();
		isPayreturnSraech.r_wait_endSear=$("#r-wait-endSear").val();
		isPayreturnSraech.provinceCode= $("#air-local-selector").parent().find("#provinceCode").val();
		isPayreturnSraech.cityCode=$("#air-local-selector").parent().find("#cityCode").val();
		isPayreturnSraech.countyCode=$("#air-local-selector").parent().find("#countyCode").val();
		isPayreturnSraech.areaName=$(".r-wait .text div").text();
		isPayreturnSraech.serviceStateName=$(".pay_return_state dt").text();
		getPayReturn(1);
		$("#mechanism-paging").removeClass("hide");
		/*分页*/
/*		$("#mechanism-paging").createPage({
			pageCount : $("#pageTotal").val(),
			current : 1,
			backFn : function(p){
				getPayReturn(p);
			}
		});*/
	} else if($(this).attr("data-link") == "order-server") {
		$("#mechanism-paging").removeClass("hide");
		var date = new Date();
		$('#o-wait-startSear').val(getMonthThereAgo());
		$('#o-wait-endSear').val(date.getFullYear()+"-"+getMonth()+"-"+date.getDate());
		$('#not-deal-search-input').val("");
		cacheNotDealStartTime = $('#o-wait-startSear').val();
		cacheNotDealEndTime = $('#o-wait-endSear').val();
		cacheNotDealSearchName = $('#not-deal-search-input').val();
	} else if($(this).attr("data-link") == "4") {
		$(".abnormal-order").show();
		init_exceptionOrderList(1);
		init_exceptionComOrderList(1);
		$("#mechanism-paging").addClass("hide");
	}
});
$(".clear-nav").on("click", "a", function() {
		$(".c-clear").hide();
		$(".clear-nav a").removeClass("s-color");
		$(this).addClass("s-color");
	if($(this).attr("data-link") == "1") {
		$("#mechanism-paging").addClass("hide");
		$(".clearing-server").show();
	} else if($(this).attr("data-link") == "2") {
		$(".cleared-server").show();
	}
});

/*选择日期*/
//$("body").on("click", ".time-choose", function(e){
//	$(this).blur(); //去掉input的光标
//    e.stopPropagation();
//    $(this).lqdatetimepicker({
//        css : 'datetime-day',
//        dateType: 'D'
//    });
//});

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
$("#company-maintenance").createPage({
	pageCount : 10,
	current : 1,
	backFn : function(){}
});
/*弹窗*/
// 预约时间选择
(function() {
	$(".order-server-detail input").attr("disabled", "disabled");
		$(".order-server-detail select").attr("disabled", "disabled");
	$("body").on("click", ".change-server-time", function() {
		$(".order-server-detail select").removeAttr("disabled");
	});

	var date = new Date();
	var option1 = "";
	var option2 = "";
	var option3 = "";

	for(var i = 0; i < 7; i++) {
		var date1 = new Date();
		var date2 = new Date(date1);
		date2.setDate(date1.getDate()+ i+1);
		var dayDate = date2.getFullYear()+"-"+(date2.getMonth() + 1)+"-"+date2.getDate();
		option1 += "<option value=" + i + "> " + dayDate + " </option>";
	}
	$(".order-server-detail .nth2-td .date").html(option1);
	for(var i = 0; i < 24; i++) {
		var startTime = date.getHours();
		option2 += "<option value=" + i + "> " + (i+1) + ":00 </option>";
	}
	$(".order-server-detail .nth2-td .start-time").html(option2);
	for(var i = 0; i < 24; i++) {
		var endTime = date.getHours();
		option3 += "<option value=" + i + "> " + (i+1) + ":00 </option>";
	}
	$(".order-server-detail .nth2-td .end-time").html(option3);
}());
$("body").on("click", ".layui-layer-close", function() {
	$(".layui-layer-shade").remove();
	$(".layui-layer").empty().remove();
	$(".time-choose-warp").empty().remove();
});
$("body").on("click", ".change-doctor-btn", function(e) {
	e.stopPropagation();
	$(".server-doctor-list").slideDown();
});
$("body").on("click", ".layui-layer-content", function(e) {
	e.stopPropagation();
	if($(".server-doctor-list").css("display") != "none" ) {
		$(".server-doctor-list").hide();
	}
});
$("body").on("click", ".server-doctor-list", function(e) {
	e.stopPropagation();
});
$("body").on("click", ".server-doctor-list li", function() {
	if(visitBean.doctorId != $(this).find("input").val()){
		$("#change-visit").attr("class", "");
		$("#reliable").attr("class", "disabled");
		$("#cancelVisit").attr("class", "disabled");
		$(".reservation-time").val("");
		$("#server-doctor").val($($(this).find("span")[0]).html() + "-" + $($(this).find("span")[1]).html());
		$(".server-doctor-list").hide();
		doctorId = $(this).find("input").val();
		$("#server-doctor").attr("doctorId", $(this).find("input").val());
	}else{
		$("#change-visit").attr("class", "disabled");
		$("#cancelVisit").attr("class", "");
		$("#reliable").attr("class", "");
		$(".reservation-time").val(visitBean.rStartTime+" --- "+visitBean.rEndTime.substr(11, 18));
		$("#server-doctor").val($($(this).find("span")[0]).html() + "-" + $($(this).find("span")[1]).html());
		doctorId = visitBean.doctorId;
		$("#server-doctor").attr("doctorId", visitBean.doctorId);
		$(".server-doctor-list").hide();
	}
});
// 退款业务
var refundDetailContent = $("#refund-detail-warp").html();
// 预约业务
var orderServerContent = $("#order-server-warp").html();
$(".popup-warp").empty().remove();
// 查看退款详情
$(".refund-server .o-table").on("click", "a", function() {
	return;
	var id = $(this).attr("id");
	var returnNo = $(this).attr("returnno");
	orderNo=id;
	$.ajax({
		type : 'POST',
		url :"getPayReturnBean/" + returnNo,
		data : null,
		dataType:'json', 
		success : function(date) {
			$('#order_no').text(date.orderNo+"");
			$('#return_state').html(date.returnState);
			$('#return_no').text(date.returnNo+"");
			$('#return_no1').text(date.returnNo+"");
			$('#trade_no').text(date.tradeNo+"");
			$('#service-type').text(date.sericeType);
			if(date.userName!=null){
				$('#user').text(date.userName);
			}
			$('#price').text(date.price);
			$('#created-time').text(date.createTime);
			$('#reason').text(date.reason);
			if(date.returnState=="待审核"){
				//$("#auditing-btn").css('display','none');  
			}else{
				$("#auditing-btn").css('display','none');  
			}
		},
	})
	layer.open({
		type: 1,
		title: '退款订单详情',
		fix: false, //不固定
		shadeClose: false, //开启遮罩关闭
		skin: 'layui-layer-rim', //加上边框
		area: ['824px', '550px'] //宽高
	});
	$(".layui-layer-content").html(refundDetailContent);
});
// 查看预约详情  这里只需要点击操作才弹出操作层
//$(".order-server").on("click", ".yycz", function() {
//	/**
//	 * 来访就诊订单Id
//	 */
//	var visitId = $(this).closest("td").find("input[name='visitId']").val();
//	/**
//	 * 医生Id
//	 */
//	doctorId = $(this).closest("td").find("input[name='doctorId']").val();
//
//	/**
//	 * 医生服务设置Id
//	 */
//	var reservationId = $(this).closest("td").find("input[name='reservationId']").val();
//	/**
//	 * 医生服务机构
//	 */
//	var doctorOrgName = $(this).closest("tr").find("td[class='org-name']").html();
//	/**
//	 * 订单号
//	 */
//	var orderNumber = $(this).closest("tr").find("td[class='order-number']").html();
//	/**
//	 * 查询就诊访问订单信息
//	 */
//	$.ajax({
//		type : "get",
//		url : "../Visit/getVisitById",
//		dataType : "json",
//		contentType : "application/json",
//		data : {"visitId" : visitId,
//			"reservationId" : reservationId},
//		success : function(data) {
//			visitBean = data.visitBean;
//			visitBean["orderNumber"] = orderNumber;
//			reservationBean = data.reservationBean;
//			$(".reservation-time").val(data.visitBean.rStartTime+" --- "+data.visitBean.rEndTime.substr(11, 18));
//			serviceCast = data.visitBean.serviceCost;
//			$(".reservation-time").attr("reservation-id", reservationId).
//			attr("r-start-time", data.visitBean.rStartTime).attr("r-end-time", data.visitBean.rEndTime);
////			预约诊号
//			$("#reservation-mz-code").html(visitBean.reservationNum);
////			订单审核状态
//			switch (visitBean.reservationTask) {
//			case 0:
//				$("#reservation-order-state").html("待确认");
//				break;
//			case 1:
//				$("#reservation-order-state").html("已确认");
//				$(".change-doctor-btn").attr("class", "disabled");
//				$(".change-server-time").attr("class", "disabled");
//				$("#reliable").attr("class", "disabled");
//				$("#cancelVisit").attr("class", "disabled");
//				break;
//			default:
//				$("#reservation-order-state").html("已确认");
//				$(".change-doctor-btn").attr("class", "disabled");
//				$(".change-server-time").attr("class", "disabled");
//				$("#reliable").attr("class", "disabled");
//				$("#cancelVisit").attr("class", "disabled");
//				$("#reservation-order-state").html("已作废");
//				break;
//			}
//		}
//	});
//	
//	/**
//	 * 查询医生信息
//	 */
//	$.ajax({
//		type : "get",
//		url : "../Visit/getDoctorById",
//		dataType : "json",
//		contentType : "application/json",
//		data : {"doctorId" : doctorId},
//		success : function(data) {
//			doctorInfo = data.doctor;
//			$("#server-doctor").val(data.doctor.name+"  "+doctorOrgName);
//			$("#server-doctor").attr("doctorId", data.doctor.id);
//		}
//	});
//	
//	layer.open({
//		type: 1,
//		closeBtn : 0,
//		title: '预约订单详情',
//		fix: false, //不固定
//		shadeClose: false, //开启遮罩关闭
//		skin: 'layui-layer-rim', //加上边框
//		area: ['824px', '550px'] //宽高
//	});
//	$(".layui-layer-content").html(orderServerContent);
////	设置一些属性到隐藏域的自定义对象中
//	$(".reservation-pa").attr("user-mobile",$(this).closest("tr").find("td[class='user-mobile']").html());
//	
////	订单号动态显示
//	$("#reservation-order").html($(this).closest("tr").find("td:first").html());
//});
//点击变更专家时
//function findSameCastDoctor(){
//	$(".time-choose-warp").empty().remove();
//	if(visitBean.reservationTask ==1){
//		return;
//	}
//	var doctorSelect = $(".server-doctor-list");
//	doctorSelect.empty();
//	$.ajax({
//		type : "get",
//		url : "../Visit/getDoctorsForReservationAndServiceCast",
//		dataType : "json",
//		contentType : "application/json",
//		data : {"serviceCast" : serviceCast,
//					"doctorId" : doctorId},
//		success : function(data) {
//			doctors = data.list;
//			for ( var i = 0; i < doctors.length; i++) {
//				doctorSelect.append($('<li><img src="'+data.imgUrl+doctors[i].picture+'" /><input type = "hidden" value='+doctors[i].doctorId+'><span>'+doctors[i].name+'</span><span>'+doctors[i].orgName+'</span>       <span>'+doctors[i].dept+'</span></li>'));
//			}
//		}
//	});
//}

///**
// * 点击变更时间时
// */
//function findServiceSet(val){
//	if(visitBean.reservationTask ==1){
//		return;
//	}
//	var option = null;
//	$.ajax({ 
//		type : "get",
//		url : "../Visit/getDoctorReservationMenu",
//		dataType : "json",
//		contentType : "application/json",
//		data : {"doctorId" : doctorId},
//		success : function(temp) {
//			option = temp;
//			$(val).timeChoose(option, function(data) {
//				if(visitBean.reservationId != data.reservationId){
//					$("#change-visit").attr("class", "");
//					$("#reliable").attr("class", "disabled");
//					$("#cancelVisit").attr("class", "disabled");
//					var datas = data.getdata;
//					$(".reservation-time").val(datas[0] + "   " +datas[2]);
//					$(".reservation-time").attr("reservation-id", data.reservationId).
//					attr("r-start-time", data.getdata[0] +" "+ data.obj.startTime).attr("r-end-time", data.getdata[0] +" "+ data.obj.endTime);
////					预约诊号的计算
//					var dayService = data.list;
//					$("#reservation-mz-code").html(parseInt(data.yCount) - parseInt(data.obj.remainNumber) + 
//							parseInt(data.obj.improperVisitNumber) + 1);
//				}else{
//					$("#change-visit").attr("class", "disabled");
//					$("#cancelVisit").attr("class", "");
//					$("#reliable").attr("class", "");
//					$("#reservation-mz-code").html(visitBean.reservationNum);
//					$(".reservation-time").val(visitBean.rStartTime+" --- "+visitBean.rEndTime.substr(11, 18));
//				}
//			});	
//		}
//	});
//}

/**
 * 提交审核订单
 */
/**
 * 只允许提交一次的标志
 */
var auditFlag = true;
function auditOrder(val,temp){
	if($(temp).hasClass("disabled")){
		return;
	}
	if(visitBean.reservationTask ==1){
		layer.alert("该订单已确认,请手动刷新");
		return;
	}
	switch ($(temp).html()) {
	case "改签":
		visitBean["isChange"] = 1;
		visitBean["reservationTask"] = 2;
		break;
	case "取消订单":
		visitBean["reservationTask"] = 2;
		visitBean["isChange"] = 0;
		break;
	default:
		visitBean["reservationTask"] = 1;
		visitBean["isChange"] = 0;
		break;
	}
	visitBean["doctorId"] = $("#server-doctor").attr("doctorId");
	visitBean["reservationId"] = $(".reservation-time").attr("reservation-id");
	visitBean["rStartTime"] = $(".reservation-time").attr("r-start-time");
	visitBean["rEndTime"] = $(".reservation-time").attr("r-end-time");
	visitBean["reservationNum"] = $("#reservation-mz-code").html();
	if(!auditFlag){
		return;
	}else{
		auditFlag = false;
		$.ajax({
			type : "post",
			url : "../Visit/auditOrder",
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(visitBean),
			success : function(data) {
				if(data.code == "000000"){
					/**
					 * 关闭服务设置框
					 */
					$(".layui-layer-shade").remove();
					$(".layui-layer").empty().remove();
					layer.alert(data.message);
					bespoke_init(1,12);
				}else{
					$(".layui-layer-shade").remove();
					$(".layui-layer").empty().remove();
					layer.alert("网络繁忙");
					bespoke_init(1,12);
				}
				auditFlag = true;
			}
		});
	}
}
// 审核
$("body").on("click", "#auditing-btn", function(e) {
	e.stop
	$(".layui-layer-shade").remove();
	$(".layui-layer").empty().remove();
	layer.confirm('请选择你的审核意见！', {
	  btn: ['通过','打回'] //按钮
	}, function(){
		window.open($("#pay_back_url").val()+orderNo+"&task_user_id="+$("#taskuserid").val());
		$(".layui-layer-shade").remove();
		$(".layui-layer").empty().remove();
	}, function(){
		var okOrNo = false;
		layer.confirm('<p>请填写您的打回理由：</p><textarea id="pay-return-msg"></textarea><em class="errortips" style="display:block;color:#ff6d2d;"></em>', {
		  btn: ['确定'] //按钮
		}, function(){
			if($("#pay-return-msg").val().length > 100 || $("#pay-return-msg").val().length == 0) {
				$("#pay-return-msg").siblings(".errortips").html("<label>请输入1-100字符的打回理由</label>");
				okOrNo = false;
			} else {
				okOrNo = true;
			}
			
			$("#pay-return-msg")[0].oninput = function() {
				if(this.value.length > 100 || this.value.length == 0) {
					$(this).siblings(".errortips").html("<label>请输入1-100字符的打回理由</label>");
					okOrNo = false;
				} else {
					$(this).siblings(".errortips").html('');
					okOrNo = true;
				}
			};
			if(okOrNo) {
				$.ajax({
					type : 'POST',
					url :"failPayReturn/" + orderNo,
					data : "pay-return-msg="+$('#pay-return-msg').val(),
					dataType:'json', 
					success : function(date) {
						layer.alert(date.message);
					},
				});
				$(".layui-layer-shade").remove();
				$(".layui-layer").empty().remove();
			}
		});
	});
	$(".layui-layer-title").html("提现审核");
});

var initFunction = (function(){
	var loadPage = 1;//执行判断第几个tab载入
	function loadPageOne(){
		var userType = $('.clearing-account-type dt').attr('option');
		var endTime = $('#clearing-server-endSear').val();
		if(endTime==null || endTime==''){
			return;
		}
		var obj = {
				'userType':userType,
				'endTime':endTime,
		}
		$.ajax({
			url:'../payClear/selectdblqf/432',
			type:'POST',
			async:false,
			data:obj,
			success:function(data){
				appendPageOne(data)
			},
			error:function(){
				layer.alert("网络出现故障");
			}
		});
	};
	function loadPageOneTwo(){
		var payClear = {
				'startTime':'2015',
				'endTime':'2017'
		}
		var payBonusCompany = {
				'userType':3,
				'uid':1,
		}
		var orderList = ['operatorTime'];
		var dataInBean = {
				'payBonusCompany':payBonusCompany,
				'payClear':payClear,
				'orderList':orderList,
				'betweenType':'operatorTime',
				'betweenBefore':'2015',
				'betweenAfter':'2017',
				'currentPage':1,
				'lineSize':10
		}
		$.ajax({
			url:'../payClear/selectChoosePayReceipt/432',
			type:'POST',
			async:false,
			data:{
				dataInBeanString:JSON.stringify(dataInBean)
			},
			success:function(data){
				//layer.msg(data.state, {icon: 1});
				appendPageOneTwo(data.msg)
			},
			error:function(){
				layer.alert("网络出现故障");
			}
		});
	};
	return {
		loadPageOne:loadPageOne,//清分页面第一个data载入查询
		loadPageOneTwo:loadPageOneTwo//清分页面第二个data载入
	}
})();
$(function(){
	$("#dhzlbsj").hide();
	initFunction.loadPageOne();
});
function appendPageOne(datas){
	$('#loadPageOneData').empty();
	if(datas[0] == null){
		return;
	}
	for(var i=0;i<datas.length;i++){
		var appendData = '';
		appendData = '<tr>'+
			'<td>'+datas[i].name+'</td>' +
            '<td>'+datas[i].totalPrice+'</td>' +
            '<td>'+datas[i].totalPrice+'</td>' +
            '<td><a>查看明细</a></td>' +
        	'</tr>';
		$('#loadPageOneData').append(appendData);
	}
};

function appendPageOneTwo(datas){
	$('#loadPageOneDataTwo').empty();
	for(var i=0;i<datas.length;i++){
		var appendData = '';
		appendData = '<tr>'+
			'<td>'+datas[i].name+'</td>' +
            '<td>'+datas[i].totalPrice+'</td>' +
            '<td>'+datas[i].totalPrice+'</td>' +
            '<td><a>查看明细</a></td>' +
        	'</tr>';
		$('#loadPageOneDataTwo').append(appendData);
	}
}

function insertPayBonusClear(){
	var userType = $('.clearing-account-type dt').attr('option');
	var endTime = $('#clearing-server-endSear').val();
	var obj = {
			'userType':userType,
			'endTime':endTime,
	}
	$.ajax({
		url:'../payClear/insertZxqf/432',
		type:'POST',
		async:false,
		data:obj,
		success:function(data){
			layer.msg(data.state, {icon: 1});
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}
/*清分*/
/*$(document).on('click','#qingfen',function(){
	insertPayBonusClear();
});*/
/*回执*/
/*$(document).on('click','#huizhi',function(){
	huizhiFunction();
});*/

/*选择下拉列表选项*/
$(".clearing-account-type").on("click", "dt,dd", function() {
//	 console.log(this);
	$(".clearing-account-type dt").html($(this).text());
	$(".clearing-account-type dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
/*$(".clearing-state-type").on("click", "dt,dd", function() {
	// console.log(this);
	
	if($(this).attr("option") == "2") {
		$("#dqflbsj").hide();
		$("#dhzlbsj").show();
		adddhzsjlb();
	} else if($(this).attr("option") == "1") {
		$("#dqflbsj").show();
		$("#dhzlbsj").hide();
	}
	$(".clearing-state-type dt").html($(this).text());
	$(".clearing-state-type dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});*/
/*清分搜索框*/
/*$(document).on('click','#loadPageOneSearch',function(){
	var clearing_state_type = $('.clearing-state-type dt').attr('option');
	if(clearing_state_type == 1){
		initFunction.loadPageOne();
	}else{
		loadPageOneSearchIsFinish();//待回执清分查询
	}
	
});*/
/*查询已清分,但是未回执的订单*/
function loadPageOneSearchIsFinish(){
	var userType = $('.clearing-account-type dt').attr('option');
	var selectedItem = $('#dhzlbsjlist option:selected').val();
	var startTime = G_dhzsjlb[selectedItem].startTime;
	var endTime = G_dhzsjlb[selectedItem].endTime;
	var obj = {
		'userType':userType,
		'startTime':startTime,
		'endTime':endTime
	};
	$.ajax({
		url:'../payClear/selectdhzqf/432',
		type:'POST',
		async:false,
		data:obj,
		success:function(datas){
			appendPageOne(datas);
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}


/*回执方法*/
function huizhiFunction(){
	var userType = $('.clearing-account-type dt').attr('option');
	var selectedItem = $('#dhzlbsjlist option:selected').val();
	var startTime = G_dhzsjlb[selectedItem].startTime;
	var endTime = G_dhzsjlb[selectedItem].endTime;
	var obj = {
		'userType':userType,
		'startTime':startTime,
		'endTime':endTime
	};
	$.ajax({
		url:'../payClear/updatedhzData/432',
		type:'POST',
		async:false,
		data:obj,
		success:function(data){
			layer.msg(data.state, {icon: 1});
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
};
/*待回执的时间列表填充*/
function adddhzsjlb(){
	$('#dhzlbsjlist').empty();//清空下拉列表,便于重新写数据
	$.ajax({
		url:'../payClear/selectdhzsjlb/432',
		type:'POST',
		async:false,
		data:{
		},
		success:function(datas){
			if(datas[0] == null){
				return;
			}
			G_dhzsjlb = datas;
			for(var i=0;i<datas.length;i++){
				$('#dhzlbsjlist').append('<option value ="'+i+'">'+datas[i].startTime+'--'+datas[i].endTime+'</option>');
			}
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
};
/*清除退款信息*/
function clearReturnInfo(){
	$("#return-table").html("");
	$("#return-table").append('<tr><th>订单号</th><th>退订时间</th><th>患者</th><th>退款方式</th><th>退款单号</th><th>金额</th><th>退款原因</th><th>实退金额</th><th>退款原因</th><th>经办人</th><th>经办时间</th><th>状态</th><th>操作</th>');
}
/*按退款状态搜索*/
$(".pay_return_state").on("click", "dt,dd", function() {
	$(".pay_return_state dt").html($(this).text());
	$(".pay_return_state dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
/*退款搜索*/
$("body").on("click","#pay_return-search", function() {
	isPayreturnSraech.searchName=$(".pay_return-search").val();
	isPayreturnSraech.serviceState=$(".pay_return_state dt").attr("option");
	isPayreturnSraech.r_wait_startSear= $("#r-wait-startSear").val();
	isPayreturnSraech.r_wait_endSear=$("#r-wait-endSear").val();
	isPayreturnSraech.provinceCode= $("#air-local-selector").parent().find("#provinceCode").val();
	isPayreturnSraech.cityCode=$("#air-local-selector").parent().find("#cityCode").val();
	isPayreturnSraech.countyCode=$("#air-local-selector").parent().find("#countyCode").val();
	isPayreturnSraech.areaName=$(".r-wait .text div").text();
	isPayreturnSraech.serviceStateName=$(".pay_return_state dt").text();
	getPayReturn(1);
});
/*已办理退款搜索*/
$("body").on("click","#pay_return-search2", function() {
	AlreadyPayreturnSraech.searchName=$(".pay_return-search2").val();
	AlreadyPayreturnSraech.r_wait_startSear= $("#r-already-startSear").val();
	AlreadyPayreturnSraech.r_wait_endSear=$("#r-already-endSear").val();
	AlreadyPayreturnSraech.provinceCode= $("#air-local-selector1").parent().find("#provinceCode1").val();
	AlreadyPayreturnSraech.cityCode=$("#air-local-selector1").parent().find("#cityCode1").val();
	AlreadyPayreturnSraech.countyCode=$("#air-local-selector1").parent().find("#countyCode1").val();
	AlreadyPayreturnSraech.areaName=$(".r-already .text div").text();
	getPayReturn(1);
});
function getPayReturn(pageNo){
	var params =null;
	if($('.refund-nav').find(".s-color").attr("data-link")=="1"){
		params = {'returnStates' : isPayreturnSraech.serviceState,
				  'starttime' :isPayreturnSraech.r_wait_startSear,
				  'endtime' : isPayreturnSraech.r_wait_endSear,
				  'pgCt' : pageNo,
				  'provinceCode' : isPayreturnSraech.provinceCode,
				  'cityCode' : isPayreturnSraech.cityCode,
				  'countyCode' : isPayreturnSraech.countyCode,
				  "search":isPayreturnSraech.searchName
				  };
		$("#r-wait-startSear").val(isPayreturnSraech.r_wait_startSear);
		$("#r-wait-endSear").val(isPayreturnSraech.r_wait_endSear);
		$(".pay_return_state dt").attr("option",isPayreturnSraech.serviceState);
		$(".pay_return_state dt").text(isPayreturnSraech.serviceStateName);
		$("#air-local-selector").parent().find("#provinceCode").val(isPayreturnSraech.provinceCode);
		 $("#air-local-selector").parent().find("#cityCode").val(isPayreturnSraech.cityCode);
		 $("#air-local-selector").parent().find("#countyCode").val(isPayreturnSraech.countyCode);
		 $(".text div").text(isPayreturnSraech.areaName);
		 $('.pay_return-search').val(isPayreturnSraech.searchName);
	}else{
		params = {'returnStates' : "3,4",
				  'starttime' : AlreadyPayreturnSraech.r_wait_startSear,
				  'endtime' : AlreadyPayreturnSraech.r_wait_endSear,
				  'pgCt' : pageNo,
				  'provinceCode' : AlreadyPayreturnSraech.provinceCode,
				  'cityCode' : AlreadyPayreturnSraech.cityCode,
				  'countyCode' :  AlreadyPayreturnSraech.countyCode,
				  "search":AlreadyPayreturnSraech.searchName
				  };
		$("#r-already-startSear").val(AlreadyPayreturnSraech.r_wait_startSear);
		$("#r-already-endSear").val(AlreadyPayreturnSraech.r_wait_endSear);
		$("#air-local-selector1").parent().find("#provinceCode").val(AlreadyPayreturnSraech.provinceCode);
		 $("#air-local-selector1").parent().find("#cityCode").val(AlreadyPayreturnSraech.cityCode);
		 $("#air-local-selector1").parent().find("#countyCode").val(AlreadyPayreturnSraech.countyCode);
		 $(".r-already .text div").text(AlreadyPayreturnSraech.areaName);
		 $('.pay_return-search2').val(AlreadyPayreturnSraech.searchName);
	}
	
	$.ajax({
		url:'getPayReturn',
		type:'POST',
		async:false,
		data:params,
		dataType:'json',
		success:function(date){
			$(".return-table").empty();
			/*分页*/
			$("#mechanism-paging").createPage({
				pageCount : date.end,
				current : date.pgCt,
				backFn : function(p){
					getPayReturn(p);
				}
			});
			if(date.datas[0] == null){
				return;
			}for(var i=0;i<date.datas.length;i++)
			{
				$(".return-table").append('<tr><td>'+date.datas[i].orderNo+
						'</td><td>'+date.datas[i].time+'</td><td>'
						+$_getValString(date.datas[i].patientName)
						+'</td><td>'+$_getValString(date.datas[i].serviceTypeStr)+
						'</td><td>'+$_getValString(date.datas[i].payTypeStr)+'</td><td>'
						+date.datas[i].returnNo+'</td><td>'+date.datas[i].price+
						'</td><td>'+$_getValString(date.datas[i].reason)+'</td><td>'+$_getValString(date.datas[i].taskName)
						+'</td><td>'+$_getValString(date.datas[i].auditTime)+'</td><td>'+$_getValString(date.datas[i].stateStr)
						+'</td><td><a  returnno="'+date.datas[i].returnNo+'" class="pay_return_a '+useable(date.datas[i].dealState)+'"  target="_blank"">'+isHandle()+'</a</td>');
			}
			
			$('a.usable').css("cursor","pointer");
			},
		error:function(){
			return ;
		}
	});
}
function isHandle(){
	if($('.refund-nav').find(".s-color").attr("data-link")=="1"){
		return '办理';
	}else {
		return '查看';
	}
}
/*已清分选择下拉列表选项 查看类型*/
$("#yqfcklx").on("click", "dt,dd", function() {
	// console.log(this);
	$("#yqfcklx dt").html($(this).text());
	$("#yqfcklx dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
/*已清分选择下拉列表选项  清分账户类型*/
$("#yqfzhlx").on("click", "dt,dd", function() {
	// console.log(this);
	$("#yqfzhlx dt").html($(this).text());
	$("#yqfzhlx dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
	F_yqfsjlbtc();
});

/*已办理清分时间选择控件*/
$("#query-date").on("click",function(e){
    e.stopPropagation();
    $(this).lqdatetimepicker({
    	css : 'datetime-day',
        dateType: 'D',
        selectback : function () { 
        	F_yqfsjlbtc();
        }
    });

});
function F_yqfsjlbtc(){
	//必须两个按钮连动
	var time = $("#query-date").val();
	var userType = $('#yqfzhlx dt').attr('option');
	if(time==undefined||userType==undefined){
		return;
	}
	var obj = {
			'clearTime':time,
			'userType':userType
	}
	$.ajax({
		url:'../payClear/getTimeByClaerTime',
		type:'POST',
		async:false,
		dataType : "json",
		data:obj,
		success:function(data){
			if(data == null){
				return;
			}
			var datas=[];
			datas.push(data);
			$('#yqfsjlb').html("");
			for(var i=0;i<=datas.length;i++){
				var S="<option start="+data[i].start_time+'" end="'+data[i].end_time+'">'+data[i].start_time+'--'+data[i].end_time+"</option>";
				$('#yqfsjlb').append('<option data="'+data[i].clear_id+'">'+data[i].start_time+'--'+data[i].end_time+'</option>');
			}
		},
		error:function(){
			$('#yqfsjlb').html("");
			return;
		}
	});
};
/*已清分列表查询*/
$(document).on('click','#yqflbss',function(){
//	var clearing_state_type = $('#yqfcklx dt').attr('option');
//	if(clearing_state_type == 1){
//		F_hzlb();
//	}else{
//		F_yblqflbxq();
//	}
	queryClearAll(1);
});
/*汇总列表查询*/
function F_hzlb(){
	var selectedItem = $('#yqfsjlb option:selected').val();
	var startTime = G_yqfsjlb[selectedItem].startTime;
	var endTime = G_yqfsjlb[selectedItem].endTime;
	var userType = $('#yqfzhlx dt').attr('option');
	var obj = {
			'userType':userType,
			'startTime':startTime,
			'endTime':endTime
	};
	$.ajax({
		url:'../payClear/selectyqfyhz/432',
		type:'POST',
		async:false,
		data:obj,
		success:function(datas){
			$('#yqfyhzlb').empty();
			if(datas[0] == null){
				return;
			}
			F_appendyqfyhz(datas);
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}

function F_appendyqfyhz(datas){
	var tableHead = '<tr>' + 
		'<th>机构名称</th>' + 
		'<th>清分金额</th>' + 
		'<th>账号类型</th>' + 
		'<th>账号</th>' + 
		'<th>操作</th>' + 
	'</tr>';
	for(var i=0;i<datas.length;i++){
		var data = '<tr>' + 
		'<th>'+datas[i].name+'</th>' + 
		'<th>'+datas[i].totalPrice+'</th>' + 
		'<th>'+datas[i].bank_card+'</th>' + 
		'<th>'+datas[i].pay_card+'</th>' + 
		'<th><a class="yblqcckxqlb">查看详细报表</a></th>' + 
		'</tr>';
		tableHead = tableHead +data;
	}
	$('#yqfyhzlb').empty();
	$('#yqfyhzlb').append(tableHead);
}
function F_appendyqfyhzxq(datas){
	var tableHead = '<tr>' + 
		'<th>清分账户名称</th>' + 
		'<th>类型</th>' + 
		'<th>业务金额</th>' + 
		'<th>收入</th>' + 
		'<th>订单号</th>' + 
		'<th>时间</th>' + 
		'<th>备注</th>' + 
	'</tr>';
	$('#yqfyhzlb').append(tableHead);
	var tableHead = '';
	for(var i=0;i<datas.length;i++){
		var data = '<tr>' + 
		'<th>'+datas[i].name+'</th>' + 
		'<th>消费</th>' + 
		'<th>'+datas[i].price+'</th>' + 
		'<th>'+datas[i].bonus_price+'</th>' + 
		'<th>'+datas[i].order_no+'</th>' + 
		'<th>'+datas[i].create_time+'</th>' + 
		'<th>'+datas[i].beizhu+'</th>' + 
		'</tr>';
		tableHead = tableHead + data;
	}
	$('#yqfyhzlb').append(tableHead);
	
}

/*已办理清分时间选择控件*/
$("#pay_return_date_start,#pay_return_date_end").on("click",function(e){
    e.stopPropagation();
    $(this).lqdatetimepicker({
    	css : 'datetime-day',
        dateType: 'D',
        selectback : function () { 
        	getPayReturn(1);
        }
    });

});
/*按时间获取退款信息*/
function getPayReturnBeanBydate(){
	getPayReturn(1);
}

/*已办理清分详情list*/
function F_yblqflbxq(){
	var selectedItem = $('#yqfsjlb option:selected').val();
	var startTime = G_yqfsjlb[selectedItem].startTime;
	var endTime = G_yqfsjlb[selectedItem].endTime;
	var userType = $('#yqfzhlx dt').attr('option');
	var obj = {
			'userType':userType,
			'startTime':startTime,
			'endTime':endTime
	};
	$.ajax({
		url:'../payClear/selectyqfyhcxqlb/432',
		type:'POST',
		async:false,
		data:obj,
		success:function(datas){
			$('#yqfyhzlb').empty();
			if(datas[0] == null){
				return;
			}
			F_appendyqfyhzxq(datas);
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}
$(document).on('click','.yblqcckxqlb',function(){
	F_yblqflbxq();
});

//预约业务
$("#reservation-handle").on("click", "a", function(){
	var a = $(this);
		//代办理
	a.addClass("s-color");
	if(a.attr("data-link") == 0){
		a.next("a").removeClass("s-color");
		$("#not-deal").show();
		$("#has-deal").hide();
		bespoke_init(1,12);
	}else{
		//已办理
		var date = new Date();
		$('#o-already-startSear').val(getMonthThereAgo());
		$('#o-already-endSear').val(date.getFullYear()+"-"+getMonth()+"-"+date.getDate());
		$('#deal-search-input').val("");
		a.prev("a").removeClass("s-color");
		$("#has-deal").show();
		$("#not-deal").hide();
		cacheIsDealStartTime = $('#o-already-startSear').val();
		cacheIsDealEndTime = $('#o-already-endSear').val();
		cacheIsDealSearchName = $('#deal-search-input').val();
		cacheIsDealServiceState = $("#reservation-states").find("dt").attr("option");
		bespoke_init(1,12);
	}
});

/*状态选择下拉列表选项*/
$("body").on("click", ".f-choose-list dt,.f-choose-list dd", function() {
    $(this).parents('.f-choose-list').find('dt').html($(this).text());
    $(this).parents('.f-choose-list').find('dt').attr("option", $(this).attr("option"));
    $(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});

/*预约状态下拉框*/
$("#yy_state_choose").on("click", "dt,dd", function() {
	// console.log(this);
	$("#yy_state_choose dt").html($(this).text());
	$("#yy_state_choose dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});

/*预约列表查询*/
$("#reservation-service-handle").on('click','.o-search',function(){
	cacheNotDealStartTime = $('#o-wait-startSear').val();
	cacheNotDealEndTime = $('#o-wait-endSear').val();
	cacheNotDealSearchName = $('#not-deal-search-input').val();
	cacheIsDealStartTime = $('#o-already-startSear').val();
	cacheIsDealEndTime = $('#o-already-endSear').val();
	cacheIsDealSearchName = $('#deal-search-input').val();
	cacheIsDealServiceState = $("#reservation-states").find("dt").attr("option");
	bespoke_init(1, 12);
});

/*预约列表刷新*/
$("#reservation-service-handle").on('click','.o-refresh',function(){
	var date = new Date();
	$('#o-wait-startSear').val(getMonthThereAgo());
	$('#o-wait-endSear').val(date.getFullYear()+"-"+getMonth()+"-"+date.getDate());
	$('#not-deal-search-input').val("");
	cacheNotDealStartTime = $('#o-wait-startSear').val();
	cacheNotDealEndTime = $('#o-wait-endSear').val();
	cacheNotDealSearchName = $('#not-deal-search-input').val();
	bespoke_init(1, 12);
});

function bespoke_init(currentPage, pageSize){
	var dealState = $("#reservation-handle").find("a.s-color").attr("data-link");
	var startTime = "";
	var endTime = "";
	var searchName = "";
	var serviceState = "";
	if(dealState == "0"){
		startTime = cacheNotDealStartTime;
		endTime = cacheNotDealEndTime;
		searchName = cacheNotDealSearchName;
	}else{
		startTime = cacheIsDealStartTime;
		endTime = cacheIsDealEndTime;
		searchName = cacheIsDealSearchName;
		serviceState = cacheIsDealServiceState;
	}
	
	var obj = {
		'startTime':startTime,
		'endTime':endTime,
		'searchName':searchName,
		'dealState' : dealState,
		'currentPage' : currentPage,
		'pageSize' : pageSize,
		'serviceState' : serviceState
	};
	$.ajax({
		url:'../Visit/selectCmsVisitList/432',
		type:'POST',
		async:false,
		data:obj,
		success:function(data){
			var arrays = data.list;
			$('.reservation-order-not-deal').empty();
			$(".reservation-order-deal").empty();
			/**
			 * 分页控件显示
			 */
			$("#mechanism-paging").removeClass("hide");
			/*分页*/
			$("#mechanism-paging").createPage({
				pageCount : data.totalPage,
				current : data.currentPage,
				backFn : function(currentPage){
					bespoke_init(currentPage,12);
				}
			});
			F_appendyycxlb(data.list);
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}

function F_appendyycxlb(datas){
	var dealState = $("#reservation-handle").find("a.s-color").attr("data-link");
	var tBody = null;
	var scanDetail = null;
	if(dealState == "0"){
		tBody = $(".reservation-order-not-deal");
		$("#not-deal-search-input").val(cacheNotDealSearchName);
		if(cacheNotDealStartTime != ""){
			$('#o-wait-startSear').val(cacheNotDealStartTime);
		}if(cacheNotDealEndTime != ""){
			$('#o-wait-endSear').val(cacheNotDealEndTime);
		}
	}else{
		tBody = $(".reservation-order-deal");
		$("#deal-search-input").val(cacheIsDealSearchName);
		if(cacheIsDealStartTime!= ""){
			$('#o-already-startSear').val(cacheIsDealStartTime);
		}if(cacheIsDealEndTime != ""){
			$('#o-already-endSear').val(cacheIsDealEndTime);
		}if(cacheIsDealServiceState != ""){
			$("#reservation-states").find("dd").each(function(){
				$("#reservation-states").find("dt").attr("option", cacheIsDealServiceState);
				if($(this).attr("option") == cacheIsDealServiceState){
					$("#reservation-states").find("dt").html($(this).html());
				}
			});
		}
	}
	if($("#reservation-handle").find("a.s-color").attr("data-link") == "0"){
		scanDetail = "办理"
	}else{
		scanDetail = "查看"
	}
	for(var i=0;i<datas.length;i++){
		var serviceState = null;
		switch (datas[i].serviceState) {
		case 1:
			serviceState = "待确认";
			break;
		case 4:
			serviceState = "待服务";
			break;
		case 7:
			serviceState = "服务中";
			break;
		case 9:
			serviceState = "已确认";
			break;
		case 10:
			serviceState = "已改签";
			break;
		default:
			serviceState = "已结束";
			break;
		}
		if(datas[i].visitDealState != 1){
			var data = '<tr>' +
			'<td>'+$_getValString(datas[i].orderNo)+'</td>' +
			'<td>'+$_getValString(datas[i].createTime)+'</td>' +
			'<td >'+$_getValString(datas[i].orgName)+'</td>' +
			'<td>'+$_getValString(datas[i].doctorName)+'</td>' +
			'<td >'+$_getValString(datas[i].payWay)+'</td>' +
			'<td>'+$_getValString(datas[i].tradeNo)+'</td>' +
			'<td>'+$_getValString(datas[i].companyName)+'</td>' +
			'<td>'+$_getValString(serviceState)+'</td>' +
			'<td>'+$_getValString(datas[i].taskPersonName)+'</td>' +
			'<td>'+$_getValString(datas[i].taskTime)+'</td>' +
			'<td><a class="reserve usable" target = "_blank"  order-no = "' + datas[i].orderNo + '" '+
			'visit-id = "' + datas[i].visitId + '" service-state = "' + datas[i].serviceState + '">' + scanDetail + '</a></td></tr>';
			tBody.append(data);
		}else{
			var data = '<tr>' +
			'<td>'+$_getValString(datas[i].orderNo)+'</td>' +
			'<td>'+$_getValString(datas[i].createTime)+'</td>' +
			'<td >'+$_getValString(datas[i].orgName)+'</td>' +
			'<td>'+$_getValString(datas[i].doctorName)+'</td>' +
			'<td >'+$_getValString(datas[i].payWay)+'</td>' +
			'<td>'+$_getValString(datas[i].tradeNo)+'</td>' +
			'<td>'+$_getValString(datas[i].companyName)+'</td>' +
			'<td>'+$_getValString(serviceState)+'</td>' +
			'<td>'+$_getValString(datas[i].taskPersonName)+'</td>' +
			'<td>'+$_getValString(datas[i].taskTime)+'</td>' +
			'<td><a>' + scanDetail + '</a></td></tr>';
			tBody.append(data);
		}
	}
};

/**
 * 列表办理按钮点击事件
 */
$("#reservation-service-handle").on("click",".reserve", function(){
	var visitId = $(this).attr("visit-id");
	var orderNo = $(this).attr("order-no");
	var serviceState = $(this).attr("service-state");
	var dealState = $("#reservation-handle").find("a.s-color").attr("data-link");
	var a = $(this);
	/**
	 * 验证订单状态是否为办理中
	 */
	$.ajax({
		url : '../Visit/detectionReservationVisitDealState',
		type : 'GET',
		async : false,
		data : {"orderNo" : orderNo, 
			"visitId" : visitId,
			"visitState" : serviceState
		},
		success : function(data){
			a.removeAttr("href");
			if(data.visitBean.dealState != $("#reservation-handle").find("a.s-color").attr("data-link")){
				layer.alert("订单办理完毕，点击确定刷新", function(index){
					layer.close(index);
					bespoke_init(1,12);
				});
			return;
			}else{
				if(data.code == "100001" ){
					if(data.visitBean.dealState == "2"){
						layer.alert("订单办理完毕，点击确定刷新", function(index){
							layer.close(index);
							bespoke_init(1,12);
						});
						return;
					}else{
						layer.alert("订单正在办理，点击确定刷新", function(index){
							layer.close(index);
							bespoke_init(1,12);
						});  
						return;
					}
				}else{
					if(orderNo){
						a.attr("href", "../Visit/reservationVisitDetail/"+ orderNo +"");
					}else{
						layer.alert("网络异常");
					}
				}
			}
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
});

/*已清分查询*/
function queryClearAll(pageno){
	var selectedItem = $('#yqfsjlb option:selected').val();
	var showType=$("#yqfcklx dt ").attr("option");
	var clearId =  $('#yqfsjlb option:selected').attr("data");
	var userType = $('#yqfzhlx dt').attr('option');
	var obj = {
			'showType':showType,
			'userType':userType,
			'clearId':clearId,
			'pageNo':pageno
	};
	if(obj.showType==null||typeof(obj.showType)==undefined){
		layer.alert("选择查看类型");
		return ;
	}
	if(obj.userType==null||typeof(obj.userType)==undefined){
		layer.alert("选择清分账户类型");
		return ;
	}
	if(obj.clearId==null||typeof(obj.clearId)==undefined){
		layer.alert("选择清分时间");
		return ;
	}
	$.ajax({
		url:'../payClear/queryClearAll',
		type:'POST',
		async:false,
		data:obj,
		success:function(datas){
			$('#yqfyhzlb').html("");
			if(datas[0] == null){
				return;
			}
			$('#yqfyhzlb').html(datas);
			$("#mechanism-paging").removeClass("hide");
			$("#mechanism-paging").createPage({
				pageCount : $("#pageTotal").val(),
				current : $("#pageCurrent").val(),
				backFn : function(p){
					queryClearAll(p);
				}
			});
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}
/*已清分详细查询*/
function queryAllDetile(uid,page){
	var showType=$("#yqfcklx dt ").attr("option");
	var clearId =  $('#yqfsjlb option:selected').attr("data");
	var userType = $('#yqfzhlx dt').attr('option');
	var obj = {
			'showType':1,
			'userType':userType,
			'clearId':clearId,
			'Uid':uid,
			'pageNo':page
	};
	$.ajax({
		url:'../payClear/queryClearAll',
		type:'POST',
		async:false,
		data:obj,
		success:function(datas){
			$('#yqfyhzlb').html("");
			if(datas[0] == null){
				return;
			}
			$('#yqfyhzlb').html(datas);
			$("#mechanism-paging").createPage({
				pageCount : $("#pageTotal").val(),
				current : $("#pageCurrent").val(),
				backFn : function(p){
					queryAllDetile($("#uid").val(),p);
				}
			});
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}
$(document).on('click','#bespokePageBtn',function(){
	bespoke_init(1,12);
});
//退款待办理刷新
$(document).on('click',"#pay_return-refresh",function(){
//	$("#r-wait-startSear").val(getMonthThereAgo());
//	$("#r-wait-endSear").val(new Date().getFullYear()+"-"+getMonth()+"-"+new Date().getDate());
//	$("#r-already-startSear").val(getMonthThereAgo());
//	$("#r-already-endSear").val(new Date().getFullYear()+"-"+getMonth()+"-"+new Date().getDate());
//	$(".pay_return_state dt").attr("option","1,2");
//	$(".pay_return_state dt").text("全部");
//	$("#air-local-selector").parent().find("#provinceCode").val("");
//	 $("#air-local-selector").parent().find("#cityCode").val("");
//	 $("#air-local-selector").parent().find("#countyCode").val("");
//	 $(".text div").text("--请选择--");
//	 $('.pay_return-search').val('');
	isPayreturnSraech.searchName="";
	isPayreturnSraech.serviceState="1,2";
	isPayreturnSraech.r_wait_startSear= getMonthThereAgo();
	isPayreturnSraech.r_wait_endSear=new Date().getFullYear()+"-"+getMonth()+"-"+new Date().getDate();
	isPayreturnSraech.provinceCode= "0";
	isPayreturnSraech.cityCode="0";
	isPayreturnSraech.countyCode="0";
	isPayreturnSraech.areaName="--请选择--";
	isPayreturnSraech.serviceStateName="全部";
	getPayReturn(1);
});
//退款a标签置灰
function disaable(id){
	$($('.return-table .'+id)[0]).addClass("hide");
	$($('.return-table .'+id)[1]).removeClass("hide");
}
//退款a标签点亮
function apply(id){
	$($('.return-table .'+id)[1]).addClass("hide");
	$($('.return-table .'+id)[0]).removeClass("hide");
}

/**
 * 监听点击详情
 */
$(document).on('click','.pay_return_a',function(){
	if(!$(this).hasClass("usable")){
		return;
	}
	var returnno=$(this).attr("returnno");
	if($('.refund-nav .s-color').attr("data-link")=='2'){
		window.open("payReturnDetail/"+returnno);
		return ;
	}
	$.ajax({
		url:'queryPayReturnDealState/'+$(this).attr("returnno"),
		type:'POST',
		async:false,
		data:{},
		success:function(datas){
			if(datas.code == "true"){
				window.open("payReturnDetail/"+returnno);
			}else{
				layer.alert(datas.message, function(index){
					layer.close(index);
					getPayReturn(1);
				});  
			}
		}
	});
});
//obj.attr("option");
function $_getValString(val){
	if(val){
		if(val == null || val == 'null'){
			return "";
		}
		return val;
	}else{
		return "";
	}
};
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
		return -1;
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
function getMonthThereAgo(){
	var d = new Date();
	d.setMonth(d.getMonth()-3);
	if((d.getMonth()+1)<10){
		return d.getFullYear()+"-0"+(d.getMonth()+1)+"-"+d.getDate();
	}
	return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
}
function getMonth(){
	var d = new Date();
	if((d.getMonth()+1)<10){
		return "0"+(d.getMonth()+1);
	}
	return d.getMonth()+1;
}

function aFn(str) {
	if(/\d/.test(str)) {
		if(!(str.length >= 11 && str.length <= 13)) {
			layer.alert("请输入正确位数的订单号");
			return true;
		}else{
			return false;
		}
	}else{
		return false;
	}

}


function useable(dealstate){
	//已办理所有可见
	if($('.refund-nav').find(".s-color").attr("data-link")=="2"){
		return "usable"
	}
	if(dealstate){
		if(dealstate="2"){
			return ""
		}
		return "usable"
	}else {
		return "usable"
	}
}
//清空搜索条件
function resetSearchCondition(){
	if($('.refund-nav').find(".s-color").attr("data-link")=="1"){
		$("#r-wait-startSear").val(getMonthThereAgo());
		$("#r-wait-endSear").val(new Date().getFullYear()+"-"+getMonth()+"-"+new Date().getDate());
		$(".pay_return_state dt").attr("option","1,2");
		$(".pay_return_state dt").text("全部");
		$("#air-local-selector").parent().find("#provinceCode").val("");
		 $("#air-local-selector").parent().find("#cityCode").val("");
		 $("#air-local-selector").parent().find("#countyCode").val("");
		 $(".text div").text("--请选择--");
		 $(".pay_return-search").val("");
	}else{
		$("#r-wait-startSear").val(getMonthThereAgo());
		$("#r-wait-endSear").val(new Date().getFullYear()+"-"+getMonth()+"-"+new Date().getDate());
		$("#r-already-startSear").val(getMonthThereAgo());
		$("#r-already-endSear").val(new Date().getFullYear()+"-"+getMonth()+"-"+new Date().getDate());
		$("#air-local-selector").parent().find("#provinceCode1").val("");
		 $("#air-local-selector").parent().find("#cityCode1").val("");
		 $("#air-local-selector").parent().find("#countyCode1").val("");
		 $(".text div").text("--请选择--");
		 $(".pay_return-search2").val("");
	}
}
//t