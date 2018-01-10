$(function(){
	/**
	 * 缓存订单号
	 */
	var cacheOrderNo = $("#reservation-visit-order").html();
	/*
	 * 缓存业务单号
	 */
	var cacheVisitId = $("#visitId").val();
	/**
	 * 缓存业务状态
	 */
	var cacheVisitState = $("#visit-service-state").val();
	/**
	 * 如果为过时，则不显示
	 */
	$.ajax({
		type : "get",
		url :  "../checkVisitIsOut",
		dataType : "json",
		async: false,
		cache : false,
		contentType : "application/json",
		data : {"orderNo" : cacheOrderNo, 
			"visitId" : cacheVisitId, 
			"visitState" : cacheVisitState
		},
		success : function(data) {
			if(data.code == "100001"){
				if($("#visit-deal-state").val() == "0"){
					layer.alert("请求时间超时，请重新回到待办理列表", function(index){
						layer.close(index);
						window.close();
					});
					return;
				}else{
					layer.alert("请求时间超时，请重新回到已办理列表", function(index){
						layer.close(index);
						window.close();
					});
					return;
				}
			}
		}
	});
	/**
	 * 缓存医生信息对象
	 */
	var cacheInfoDoctor = null;
	/**
	 * 缓存金额
	 */
	var cacheServiceCost = $("#reservation-service-cost").html();
	
	/**
	 * 缓存服务设置Id
	 */
	var cacheReservationId = $("#reservationIdForVisit").val();
	
	/**
	 * 如果订单超时，
	 * 代办理的只能改签
	 * 已办理的只能查看
	 */
	
	if($("#visit-is-out-time").val() == "1"){
		if($("#visit-deal-state").val() == "0"){
			$("#reservation-handle-sure").html("改签");
		}else{
			$("#reservation-handle-sure").hide();
			$(".change-time-btn").hide();
			$(".change-doc-btn").hide();
		}
	}
	
	/**
	 * 如果是待服务的订单
	 * 则变为改签
	 */
	if(($("#visit-deal-state").val() == "2") && ($("#visit-service-state").val() == "4")){
		$("#reservation-handle-sure").html("改签");
	}
	
	/**
	 * 如果存在老的visitId，
	 * 表示已经办理改签过了的订单
	 */
	if($("#old-visit-id").val()){
		$("#reservation-handle-sure").html("改签");
	}
	/**
	 * 预约服务中的订单不能再办理业务
	 */
	
	if(($("#visit-service-state").val() == "7") || 
			($("#visit-service-state").val() == "20")){
		$("#reservation-handle-sure").hide();
		$(".change-time-btn").hide();
		$(".change-doc-btn").hide();
	}
	
	/**
	 * 还原表示样式到最初
	 */
	var clearTables = function(){
		/**
		 * 将所有的Tr删除掉
		 */
		$(".time-data").each(function(index, dom){
			$(this).find("tBody").find("tr").each(function(){
				$(this).remove();
			});
		});
		/**
		 * 再添加将所有table还原样式
		 */
		$(".time-data").each(function(index, dom){
			$(this).attr("style", "display : none");
			$(this).append('<tr class="usable" interval = "morning"><th >上午</th><td class="service-time"></td><td '+
					'class="remain-number"></td><td class="hander-button"><a><i class="i-radio"></i></a></td></tr><tr class'+
					'="usable" interval = "afternoon"><th>下午</th><td class="service-time"></td><td class="remain-number">'+
					'</td><td class="hander-button"><a><i class="i-radio"></i></a></td></tr><tr class="usable" interval = "evening">'+
					'<th>晚上</th><td class="service-time"></td><td class="remain-number"></td><td class="hander-button">'+
					'<a><i class="i-radio"></i></a></td></tr>');
		});
	};
	
	/**
	 * 得到医生服务设置的全部信息
	 * 并设置到表格
	 */
	var getDoctorReservation = function(currentDoctorId, visitId){
		/**
		 * 获取医生服务设置信息
		 */
		$.ajax({
			type : "get",
			url :  "../getDoctorReservationMenu",
			dataType : "json",
			async: true,
			cache : false,
			contentType : "application/json",
			data : {"doctorId" : currentDoctorId, 
				"visitId" : visitId},
			success : function(data) {
				orderTime(data);
			}
		});
	};
	
	/**
	 * 对医生服务设置号数的对象集合
	 * 整理成按每天为单位装入一个数组中
	 */
	function formatReservations(list){
		var arrayList = [];
		var arrays1 = [];
		var arrays2 = [];
		var arrays3 = [];
		var arrays4 = [];
		var arrays5 = [];
		var arrays6 = [];
		var arrays7 = [];
		for ( var i = 0; i < list.length; i++) {
			switch (list[i].week) {
			case 1:
				arrays1.push(list[i]);
				break;
			case 2:
				arrays2.push(list[i]);
				break;
			case 3:
				arrays3.push(list[i]);
				break;
			case 4:
				arrays4.push(list[i]);
				break;
			case 5:
				arrays5.push(list[i]);
				break;
			case 6:
				arrays6.push(list[i]);
				break;
			default:
				arrays7.push(list[i]);
				break;
			}
		}
		arrayList.push(arrays1);
		arrayList.push(arrays2);
		arrayList.push(arrays3);
		arrayList.push(arrays4);
		arrayList.push(arrays5);
		arrayList.push(arrays6);
		arrayList.push(arrays7);
		return arrayList;
	}
	
	/**
	 * 将每天的服务设置按
	 * 上午，下午，晚上划分为3个数组
	 */
	function formatByInterval(list){
		var arrayList = [];
		var morning = [];
		var afternoon = [];
		var evening = [];
		for ( var i = 0; i < list.length; i++) {
			if(list[i].remainNumber > 0){
				switch (list[i].interval) {
				case 1:
					morning.push(list[i]);
					break;
				case 2:
					afternoon.push(list[i]);
					break;
				default:
					evening.push(list[i]);
				break;
				}
			}
		}
		arrayList.push(morning);
		arrayList.push(afternoon);
		arrayList.push(evening);
		return arrayList;
	}
	
	/**
	 * 显示日期
	 */
	function getNowDayTime(dayCount){
		var date = new Date();
		var now = new Date(date.getFullYear(), date.getMonth(), date.getDate()+dayCount);
		var dayTime = "";
		var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六'); 
		var nowWeek=show_day[now.getDay()];
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var day = now.getDate();
	    if(month < 10){
	    	month  =  "0" + month; 
	    }if(day < 10){
	    	day  =  "0" + day; 
	    }
	    return year + "-" + month + "-" + day + " " + nowWeek;
	}
	
	/**
	 * 显示服务设置内容 
	 */
	var orderTime = function(data) {
		/**
		 * 7个table显示服务设置
		 */
		var tables = $(".time-data");
		/**
		 * 服务设置所有信息
		 */
		var arrayList = formatReservations(data.list);
		/**
		 * 7个列表的医生服务设置显示
		 */
		var date = new Date();
		for ( var i = 0; i < tables.length; i++) {
			if(date.getDay() + i + 1 >= 7){
				$(tables[i]).attr("week", date.getDay() + i +1 - 7);
			}else{
				$(tables[i]).attr("week", date.getDay() + i + 1);
			}
		}
		for ( var i = 0; i < arrayList.length; i++) {
			var arrays = arrayList[i];
			/**
			 * 添加行上日期
			 */
			$(tables[i]).find("caption").html(getNowDayTime(i + 1));
			/**
			 * 对于有设置号数的表格的显示设置操作
			 */
			for ( var j = 0; j < tables.length; j++) {
				if((arrays.length > 0) && ($(tables[j]).attr("week") == arrays[0].week)){
					var table = $(tables[j]);
					table.show();
					var tempArrays = formatByInterval(arrays);
					var morningArray = tempArrays[0];
					var afterNoonArray = tempArrays[1];
					var eveningArray = tempArrays[2];
					/**
					 * 上午
					 */
					var morningTr = table.find("tr[interval = morning]");
					if(morningArray.length > 0){
						morningTr.find("th").attr("rowspan", morningArray.length);
					}
					for ( var m = 0; m < morningArray.length; m++) {
						if(morningArray[m].remainNumber > 0){
							if(m == 0){
								morningTr.find("td[class = service-time]").html(
										morningArray[m].startTime + " -- " + morningArray[m].endTime);
								morningTr.find("td[class = remain-number]").html(morningArray[m].remainNumber);
								morningTr.find("a").attr("reservation_num",
										morningArray[m].yCount - morningArray[m].remainNumber + morningArray[m].improperVisitNumber + 1);
								morningTr.find("a").attr("reservation_id", morningArray[m].reservationId);
							}else{
								var tempTr = $('<tr class="usable" interval = "morning"><td class="service-time"></td>'+
								'<td class="remain-number"></td><td class="hander-button"><a><i class="i-radio"></i></a></td></tr>');
								tempTr.find("td[class = service-time]").html(
										morningArray[m].startTime + " -- " + morningArray[m].endTime);
								tempTr.find("td[class = remain-number]").html(morningArray[m].remainNumber);
								$(table.find("tr[interval = morning]")[table.find("tr[interval = morning]").length-1]).after(tempTr);
								tempTr.find("a").attr("reservation_num",
										morningArray[m].yCount - morningArray[m].remainNumber + morningArray[m].improperVisitNumber + 1);
								tempTr.find("a").attr("reservation_id", morningArray[m].reservationId);
							}
						}
					}
					/**
					 * 下午
					 */
					var afternoonTr = table.find("tr[interval = afternoon]");
					if(afterNoonArray.length > 0){
						afternoonTr.find("th").attr("rowspan", afterNoonArray.length);
					}
					for ( var a = 0; a < afterNoonArray.length; a++) {
						if(afterNoonArray[a].remainNumber > 0){
							if(a == 0){
								afternoonTr.find("td[class = service-time]").html(
										afterNoonArray[a].startTime + " -- " + afterNoonArray[a].endTime);
								afternoonTr.find("td[class = remain-number]").html(afterNoonArray[a].remainNumber);
								afternoonTr.find("a").attr("reservation_num",
										afterNoonArray[a].yCount - afterNoonArray[a].remainNumber + afterNoonArray[a].improperVisitNumber + 1);
								afternoonTr.find("a").attr("reservation_id", afterNoonArray[a].reservationId);
							}else{
								var tempTr = $('<tr class="usable" interval = "afternoon"><td class="service-time"></td>'+
								'<td class="remain-number"></td><td class="hander-button"><a><i class="i-radio"></i></a></td></tr>');
								tempTr.find("td[class = service-time]").html(
										afterNoonArray[a].startTime + " -- " + afterNoonArray[a].endTime);
								tempTr.find("td[class = remain-number]").html(afterNoonArray[a].remainNumber);
								$(table.find("tr[interval = afternoon]")[table.find("tr[interval = afternoon]").length-1]).after(tempTr);
								tempTr.find("a").attr("reservation_num",
										afterNoonArray[a].yCount - afterNoonArray[a].remainNumber + afterNoonArray[a].improperVisitNumber + 1);
								tempTr.find("a").attr("reservation_id", afterNoonArray[a].reservationId);
							}
						}
					}
					/**
					 * 晚上
					 */
					var eveningTr = table.find("tr[interval = evening]");
					if(eveningArray.length > 0){
						eveningTr.find("th").attr("rowspan", eveningArray.length);
					}
					for ( var e = 0; e < eveningArray.length; e++) {
						if(eveningArray[e].remainNumber > 0){
							if(e == 0){
								eveningTr.find("td[class = service-time]").html(
										eveningArray[e].startTime + " -- " + eveningArray[e].endTime);
								eveningTr.find("td[class = remain-number]").html(eveningArray[e].remainNumber);
								eveningTr.find("a").attr("reservation_num",
										eveningArray[e].yCount - eveningArray[e].remainNumber + eveningArray[e].improperVisitNumber + 1);
								eveningTr.find("a").attr("reservation_id", eveningArray[e].reservationId);
							}else{
								var tempTr = $('<tr class="usable" interval = "evening"><td class="service-time"></td>'+
								'<td class="remain-number"></td><td class="hander-button"><a><i class="i-radio"></i></a></td></tr>');
								tempTr.find("td[class = service-time]").html(
										eveningArray[e].startTime + " -- " + eveningArray[e].endTime);
								tempTr.find("td[class = remain-number]").html(eveningArray[e].remainNumber);
								$(table.find("tr[interval = evening]")[table.find("tr[interval = evening]").length-1]).after(tempTr);
								tempTr.find("a").attr("reservation_num",
										eveningArray[e].yCount - eveningArray[e].remainNumber + eveningArray[e].improperVisitNumber + 1);
								tempTr.find("a").attr("reservation_id", eveningArray[e].reservationId);
							}
						}
					}
					var emptyTrs = table.find("td:empty").closest("tr");
					/**
					 * 没有数据的地方直接移除
					 */
					emptyTrs.each(function(index, dom){
						$(this).remove();
					});
				}
			}
		}
	};

	/**
	 * 更换医生时的查询
	 * 	6条为一页
	 */
	var selectDoctorDetailList = function(serviceCast, doctorId, currentPage, pageSize){
		$.ajax({
			type : "get",
			url :  "../getDoctorsForReservationAndServiceCast",
			dataType : "json",
			async: true,
			cache : false,
			contentType : "application/json",
			data : {
				"serviceCast" : serviceCast, 
				"doctorId" : doctorId, 
				"currentPage" : currentPage, 
				"pageSize" : pageSize
				},
			success : function(data) {
				if(data.doctorPage == null){
					return;
				}
				listDoctor(data.doctorPage.list);
				/**
				 * 分页
				 */
				$("#doc-paging").createPage({
					pageCount : data.doctorPage.totalPage,
					current : data.doctorPage.currentPage,
					backFn : function(cp){
						selectDoct
						orDetailList(serviceCast, doctorId, cp, 6);
					}
				});
			}
		});
	};
	
	/**
	 * 展示医生的列表
	 */
	var listDoctor = function(arrays){
		var doctorTbody = $(".doctor-show-list");
		/**
		 * 清空上一次的信息
		 */
		doctorTbody.find("tr").remove();
		/**
		 * 列表布局
		 */
		for ( var i = 0; i < arrays.length; i++) {
			doctorTbody.append(' <tr><td>'+arrays[i].doctorName+'</td><td>'+
					arrays[i].titlesClinicalName+'</td><td>'+arrays[i].dept+'</td><td>'+arrays[i].orgName+'</td><td>'+
					arrays[i].mobile+'</td><td><input type = "hidden" doctor_uid = '+arrays[i].userId+' '+
					'doctor_id = '+arrays[i].doctorId+'></td></tr>');
		}
	};
	
	/**
	 * 根据服务设置的Id查询出预约诊号
	 */
	var selectReservationNumForVisit = function(reservationId){
		$.ajax({
			type : "get",
			url :  "../selectReservationNumForVisit",
			dataType : "json",
			async: false,
			cache : false,
			contentType : "application/json",
			data : {
				"reservationId" : reservationId, 
				},
			success : function(data) {
				if(data.code == "000000"){
					$("#reservationIdForVisit").val(reservationId);
					$("#reservation-num").html(data.reservationNum);
				}else{

				}
			}
		});
	};
	
	/**
	 * 业务办理提交
	 */
	var reservationHandelSubmit = function(orderNo, visitId, doctorId, reservationId, auditResult){
		var dealState = $("#visit-deal-state").val();
		var param = {"orderNo" : orderNo, 
				"visitId" : visitId, 
				"doctorId" : doctorId, 
				"reservationId" : reservationId, 
				"auditResult" : auditResult}; 
		$.ajax({
			type : "get",
			url :  "../auditOrder",
			dataType : "json",
			async: false,
			cache : false,
			contentType : "application/json",
			data : param,
			success : function(data) {
				if(data.code == "100001"){
					if(data.userId){
						if(dealState == "0"){
							layer.alert("请求时间超时，请重新回到待办理列表");
							return;
						}else{
							layer.alert("请求时间超时，请重新回到已办理列表");
							return;
						}
					}else{
						$(".change-time-btn").click();
						$(".errortips").show().fadeOut(10000);
						return;
					}
				}else{
					if(data.oldVisitId){
						/**
						 * 可查看详情
						 */
						$("#reservation-handle-sure").attr("href", "../afterDealVisitForReservation/"+data.oldVisitId+"/"+data.visitId);
					}else{
						layer.alert("操作成功", function(index){
							 layer.close(index);
									 window.open("about:blank","_self").close();
							});  
					}
				}
			}
		});
	};
	/**
	 * 预约时间弹窗
	 */
	var openLayer = {};
	$('.change-time-btn').click(function() {
		/**
		 * 先验证是否过期
		 */
		$.ajax({
			type : "get",
			url :  "../checkVisitForReservationIsExistFromRedis",
			dataType : "json",
			async: false,
			cache : false,
			contentType : "application/json",
			data : {"orderNo" : cacheOrderNo, 
				"visitId" : cacheVisitId, 
				"visitState" : cacheVisitState
			},
			success : function(data) {
				if(data.code == "100001"){
					if($("#visit-deal-state").val() == "0"){
						layer.alert("请求时间超时，请重新回到待办理列表", function(index){
							layer.close(index);
							window.close();
						});
						return;
					}else{
						layer.alert("请求时间超时，请重新回到已办理列表" , function(index){
							layer.close(index);
							window.close();
						});
						return;
					}
				}else{
					 openLayer.changeTime = layer.open({
					        type: 1,
					        title: '变更预约时间',
					        fix: false, //不固定
					        shadeClose: false, //开启遮罩关闭
					        skin: 'layui-layer-rim', //加上边框
					        area: ['720px', '520px'], //宽高
					        content: $(".w-order-time"),
					        cansel: function() {
					            content.find(".errortips").empty();
					        }
					    });
					 	$("#first-ot-sure").addClass("disabled");
					    clearTables();
					   /**
					    * 服务时段显示
					    */ 
					    getDoctorReservation($("#doctorId").val(), $("#visitId").val());
				}
			}
		});
	});

    /**
     * 关闭预约时段弹窗
     */
	$('body').on("click",".cancel-popup",function(){
	    layer.close(openLayer.changeTime);
	});

	/**
	 *选择预约时间
	 */ 
	$(".time-data").on("click", "tr.usable", function() {
		$("#first-ot-sure").removeClass("disabled");
	    $(".time-data").find("tr").removeClass("active");
	    $(this).addClass("active");
	    $(".time-putin").removeClass("disabled");
	});
	
	/*变更医生
	------------------------------*/
	$('.change-doc-btn').click(function() {
		/**
		 * 先验证是否过期
		 */
		$.ajax({
			type : "get",
			url :  "../checkVisitForReservationIsExistFromRedis",
			dataType : "json",
			async: false,
			cache : false,
			contentType : "application/json",
			data : {"orderNo" : cacheOrderNo, 
				"visitId" : cacheVisitId, 
				"visitState" : cacheVisitState
			},
			success : function(data) {
				if(data.code == "100001"){
					if($("#visit-deal-state").val() == "0"){
						layer.alert("请求时间超时，请重新回到待办理列表", function(index){
							layer.close(index);
							window.close();
						});
						return;
					}else{
						layer.alert("请求时间超时，请重新回到已办理列表", function(index){
							layer.close(index);
							window.close();
						});
						return;
					}
				}else{
					 openLayer.changeDoc = layer.open({
					        type: 1,
					        title: '变更医生',
					        fix: false, //不固定
					        shadeClose: false, //开启遮罩关闭
					        skin: 'layui-layer-rim', //加上边框
					        area: ['720px', '520px'], //宽高
					        content: $(".w-change-doc"),
					        cansel: function() {
					            content.find(".errortips").empty();
					        }
					    });
					    $(".doc-sure").addClass("disabled");
					    selectDoctorDetailList($("#reservation-service-cost").html(), $("#current-visit-doctor-id").val(), 1 ,6);
				}
			}
		});
	});
	
	/**
	 * 关闭医生选择窗口
	 */
	$('body').on("click",".cancel-doc",function(){
	    layer.close(openLayer.changeDoc);
	});
	
	/**
	 * 医生信息选择一行效果
	 */
	$('.doc-data').find('tr').hover(function() {
	    if(/\S/.test($(this).text())) {
	            $(this).css({
	                backgroundColor: '#edf6fc'
	            });
	    }
	}, function() {
	            $(this).css({
	                backgroundColor: ''
	            });
	});
	$('.change-doc').on('click', 'tr', function() {
	    if(/\S/.test($(this).text())) {
	        $('.doc-data tr').removeClass('active');
	        $(this).addClass('active');
	        $('.doc-sure').removeClass('disabled');
	    }
	});
	
	/**
	 * 变更医生确定按钮点击事件
	 */
	$("body").on("click", ".doc-sure", function(){
		/**
		 * 得到被选择的医生
		 */
		var chooseTr = null;
		$(".doctor-show-list").find("tr").each(function(index, dom){
			if($(this).hasClass("active")){
				chooseTr = $(this);
			}
		});
		if(chooseTr == null){
			return;
		}
		/**
		 * 先验证是否过期
		 */
		$.ajax({
			type : "get",
			url :  "../checkVisitForReservationIsExistFromRedis",
			dataType : "json",
			async: false,
			cache : false,
			contentType : "application/json",
			data : {"orderNo" : cacheOrderNo, 
				"visitId" : cacheVisitId, 
				"visitState" : cacheVisitState
			},
			success : function(data) {
				if(data.code == "100001"){
					if($("#visit-deal-state").val() == "0"){
						layer.alert("请求时间超时，请重新回到待办理列表", function(index){
							layer.close(index);
							window.close();
						});
						return;
					}else{
						layer.alert("请求时间超时，请重新回到已办理列表" , function(index){
							layer.close(index);
							window.close();
						});
						return;
					}
				}else{
					cacheInfoDoctor = chooseTr;
					/**
					 * 获取参数并弹窗
					 */
					var doctorId = chooseTr.find("input").attr("doctor_id");
					openLayer.changeTime = layer.open({
				        type: 1,
				        title: '变更预约时间',
				        fix: false, //不固定
				        shadeClose: false, //开启遮罩关闭
				        skin: 'layui-layer-rim', //加上边框
				        area: ['720px', '520px'], //宽高
				        content: $(".w-order-time"),
				        cansel: function() {
				            content.find(".errortips").empty();
				        }
				    });
					$("#first-ot-sure").addClass("disabled");
					clearTables();
					getDoctorReservation(doctorId, $("#visitId").val());
				}
			}
		});
	});
	
	/**
	 * 变更预约时间点击确定事件
	 */
	$("body").on("click", "#first-ot-sure", function(){
		if($(this).hasClass("disabled")){
			return;
		}
		/**
		 * 先验证是否过期
		 */
		$.ajax({
			type : "get",
			url :  "../checkVisitForReservationIsExistFromRedis",
			dataType : "json",
			async: false,
			cache : false,
			contentType : "application/json",
			data : {"orderNo" : cacheOrderNo, 
				"visitId" : cacheVisitId, 
				"visitState" : cacheVisitState
			},
			success : function(data) {
				if(data.code == "100001"){
					if($("#visit-deal-state").val() == "0"){
						layer.alert("请求时间超时，请重新回到待办理列表", function(index){
							layer.close(index);
							window.close();
						});
						return;
					}else{
						layer.alert("请求时间超时，请重新回到已办理列表" , function(index){
							layer.close(index);
							window.close();
						});
						return;
					}
				}else{
					var checkTr = null;
					var reservationId = null;
					var reservationNum = null;
					var doctorInfoTds = null;
					var reservationDayTime = null;
					
					/**
					 * 将提交按钮变为改签
					 */
					$(".reservation-handle-sure").html("改签");
					
					$(".time-data-wrap").find("tr").each(function(index, dom){
						if($(dom).hasClass("active")){
							checkTr = $(dom);
						}
					});
					
					reservationDayTime = checkTr.closest("table").find("caption").html();

					  /**
				     * 最新预约诊号查询并设置
				     */
				    reservationId = checkTr.find("a").attr("reservation_id");
				    selectReservationNumForVisit(reservationId);
				    /**
					 * 关闭所有弹框
					 */
					layer.close(openLayer.changeDoc);
				    if(openLayer.changeTime){
				    	layer.close(openLayer.changeTime);
				    }
				    
				    /**
				     *服务时段
				     */ 
				    $("#reservation-service-time").html(
				    		reservationDayTime.substring(0,10) + " " + checkTr.find("td.service-time").html() + " " + 
				    		reservationDayTime.substring(11)
				    );
				    if(cacheInfoDoctor){
				    	doctorInfoTds = cacheInfoDoctor.find("td");
				    	 /**
					     * 医生信息
					     */
					    $("#reservation-doctor-detail").html(
					    		$(doctorInfoTds[0]).html()+"（"+$(doctorInfoTds[1]).html()+"）"
					    );
					    /**
					     * 医生科室
					     */
					    $("#reservation-doctor-dept").html(
					    		$(doctorInfoTds[2]).html()
					    );
					    /**
					     * 医生电话
					     */
					    $("#reservation-doctor-phone").html(
					    		$(doctorInfoTds[4]).html()
					    );
					    /**
					     * 医生服务机构
					     */
					    $("#reservation-doctor-org-name").html(
					    		$(doctorInfoTds[3]).html()
					    );
					    /**
					     * 医生Id
					     */
					    $("#doctorId").val(cacheInfoDoctor.find("input").attr("doctor_id"));
				    }
				}
			}
		});
	});
	
	/**
	 * 预约业务办理提交
	 */
	$(".reservation-handle-sure").on("click", function(){
		/**
		 * 先验证是否过期
		 */
		$.ajax({
			type : "get",
			url :  "../checkVisitForReservationIsExistFromRedis",
			dataType : "json",
			async: false,
			cache : false,
			contentType : "application/json",
			data : {"orderNo" : cacheOrderNo, 
				"visitId" : cacheVisitId, 
				"visitState" : cacheVisitState
			},
			success : function(data) {
				if(data.code == "100001"){
					if($("#visit-deal-state").val() == "0"){
						layer.alert("请求时间超时，请重新回到待办理列表", function(index){
							layer.close(index);
							window.close();
						});
						return;
					}else{
						layer.alert("请求时间超时，请重新回到已办理列表", function(index){
							layer.close(index);
							window.close();
						});
						return;
					}
				}else{
					if(cacheOrderNo != $("#reservation-visit-order").html()){
						layer.alert("订单号不正确！");
						return;
					}if(cacheVisitId != $("#visitId").val()){
						layer.alert("业务单号不正确！");
						return;
					}if(cacheServiceCost != $("#reservation-service-cost").html()){
						layer.alert("订单金额不正确！");
						return;
					}if($("#old-visit-id").val() || ($("#visit-is-out-time").val() == "1") || 
							($("#visit-service-state").val() == "4")){
						if(cacheReservationId == $("#reservationIdForVisit").val()){
							$("#please-choose-reservation").show().css("display", "inline-block").fadeOut(10000);
							return;
						}
					}
					reservationHandelSubmit(cacheOrderNo, cacheVisitId,
							$("#doctorId").val(), $("#reservationIdForVisit").val(), 0);
				}
			}
		});
	});
});