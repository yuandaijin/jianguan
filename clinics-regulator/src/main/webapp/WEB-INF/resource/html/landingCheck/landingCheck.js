//时间控件
var start = {
	elem: '#start',
	min: '1930-01-01', // 设定最小日期为当前日期 
	max: laydate.now(), // 最大日期 
	istime: false,
	istoday: true,
	format: 'YYYY-MM-DD',
	choose: function(datas) {
		end.min = datas; //开始日选好后，重置结束日的最小日期
		end.start = datas //将结束日的初始值设定为开始日
	}
}
var end = {
	elem: '#end',
	min: '1930-01-01', // 设定最小日期为当前日期 
	max: laydate.now(), // 最大日期 
	istime: false,
	istoday: false,
	format: 'YYYY-MM-DD',
	choose: function(datas) {
		start.max = datas; //结束日选好后，重置开始日的最大日期
	}
}
laydate(start);
laydate(end);
//日期显示
//开始时间
function startly() {
	var mydate = new Date();
	var year = mydate.getFullYear();
	var month = mydate.getMonth();
	var leng = (month.toString()).length;
	var monh;
	if(leng == 1) {
		monh = "0" + (mydate.getMonth() + 1);
	} else {
		monh = mydate.getMonth() + 1;
	}
	var daydate = "01";
	$("#start").val(year + "-" + monh + "-" + daydate);
};

//结束时间
function endly() {
	var mydate = new Date();
	var year = mydate.getFullYear();
	var month = mydate.getMonth() + 1;
	var daydate = mydate.getDate();
	var mon;
	var day;
	var monleng = (month.toString()).length;
	var dayleng = (daydate.toString()).length;
	if(monleng == 1) {
		mon = "0" + (mydate.getMonth() + 1);
	} else {
		mon = mydate.getMonth() + 1;
	};
	if(dayleng == 1) {
		day = "0" + mydate.getDate();
	} else {
		day = mydate.getDate();
	}
	$("#end").val(year + "-" + mon + "-" + day);
};
//核查地区显示
function leftShift() {
	var url = path.path + '/dispute/queryAdress';
	$.ajax({
		type: "get",
		url: url,
		dataType: "json",
		async: false,
		success: function(data) {
			$("#province").val(data.dto.province);
			$("#city").val(data.dto.city);
			$("#area").val(data.dto.county);
		}
	});
}
//登录记录显示
var pageNO;
var currentPage = 1;

function detailed() {
	var orgId = $("#clinic").find("option:selected").val();
	var beginDate = $("#start").val() + " 00:00:00";
	var endTime = $("#end").val();
	var endDate
	if(endTime == "") {
		var endDate = endTime
	} else {
		var endDate = endTime + " 23:59:59"
	}
	var url = path.path + '/loginLog/loginLogList';
	var params = {
		beginDate: beginDate,
		endDate: endDate,
		orgId: orgId,
		currentPage: currentPage,
	}
	$.ajax({
		type: "get",
		url: url,
		data: params,
		dataType: "json",
		async: false,
		success: function(data) {
			if(data.code == "000000") {
				pageNO = parseInt(data.resul / 10) + 1
				for(var i = 0; i < data.logs.length; i++) {
					var tableHtml = "";
					tableHtml += '<tr class="remo">' +
						'<td class="frame">' + data.logs[i].loginOrgName + '</td>' +
						'<td class="frame">' + data.logs[i].loginUserName + '</td>' +
						'<td class="frame loginUserSex">' + data.logs[i].loginUserSex + '</td>' +
						'<td class="frame">' + data.logs[i].loginUserAge + '</td>' +
						'<td class="frame depart">' + data.logs[i].loginUserDept + '</td>' +
						'<td class="frame">' + data.logs[i].loginTime + '</td>' +
						'<td class="frame hide">' + data.logs[i].loginPhotoUrl + '</td>' +
						'<td class="viewPhotos">' + "查看照片" + '</td>' +
						'</tr>';
					$("#addTr").append(tableHtml);
					$(".loginUserSex").each(function(i, item) {
						var loginUserSex = $(this).text();
						if(loginUserSex == "1") {
							$(this).text("女")
						} else if(loginUserSex == "0") {
							$(this).text("男")
						}
					});
					$(".depart").each(function(i, item) {
						var depart = $(this).text();
						if(depart === "null") {
							$(this).text("")
						};
					});
				}
			} else {
				layer.alert('查询失败');
			}
		}
	});
};
//按条件点击查询点击查询
$("#query").on('click', function() {
	var a = $("#start").val();
	var b = $("#end").val();
	var a1 = a.substr(0, 4);
	var a2 = a.substr(5, 2);
	var a3 = a.substr(8, 2);
	var a4 = Number(a1 + a2 + a3);
	var b1 = b.substr(0, 4);
	var b2 = b.substr(5, 2);
	var b3 = b.substr(8, 2);
	var b4 = Number(b1 + b2 + b3);
	if(b4 < a4) {
		layer.msg('结束时间不能小于开始时间');
		return;
	} else {
		$(".remo").remove();
		$("#drugPage").removeClass("hide");
		currentPage = 1;
		detailed();
		pageNow();
	}
});
//点击显示图片
$(document).on("click", ".viewPhotos", function() {
	var url = $(this).prev().text();
	if(url === "null") {
		layer.msg('暂无图片');
	} else {
		layer.open({
			title: '',
			type: 1,
			skin: '', // 加上边框
			area: ['400px', '400px'], // 宽高
			content: $("#picture"),
			fix: false,
			move: false,
			closeBtn: 2,
			skin: 'demo-class'
		});
		$("#imgFile").attr('src', url);
	}
});
//乡镇显示
function region() {
	var url = path.path + '/dispute/queryAdress';
	$.ajax({
		type: "get",
		url: url,
		dataType: "json",
		async: false,
		success: function(data) {
			if(data.code == "000000") {
				for(var i = 0; i < data.list.length; i++) {
					var region = $("#region");
					region.append("<option value=" + data.list[i].id + ">" + data.list[i].name + "</option>"); //添加option
				};
			} else {
				layer.alert("查询失败");
			}
		}
	});
};
//核查诊所查询
function clinic() {
	var url = path.path + '/dispute/queryOrgName';
	var roadCode = $("#region").find("option:selected").val();
	var params = {
		roadCode: roadCode,
	}
	$.ajax({
		type: "get",
		url: url,
		data: params,
		dataType: "json",
		async: false,
		success: function(data) {
			if(data.code == "000000") {
				for(var i = 0; i < data.orgList.length; i++) {
					var clinic = $("#clinic");
					clinic.append("<option value=" + data.orgList[i].id + ">" + data.orgList[i].name + "</option>"); //添加option
				};
			} else {
				layer.alert("查询失败");
			}
		}
	});
};
//乡镇改变
$("#region").on("click", function() {
	$("#clinic option").remove();
	clinic();
});
//核查诊所改变
$("#clinic").on("click", function() {
	$(".remo").remove();
	$("#drugPage").addClass("hide");
});
//页码
function pageNow() {
	$("#drugPage").createPage({
		pageCount: pageNO,
		current: 1,
		backFn: function(p) {
			//单击回调方法，p是当前页码 
			currentPage = p;
			$(".remo").remove();
			detailed();
		}
	});
	if($("#pageTotal").val() == 0) { // 修复收索不存在的bug 
		$("#drugPage").hide();
		$(".page").hide();
	}
	if($("#count").val() == 0) {
		$("#pageDiv").hide();
	} else {
		$("#drugPage").show();
		$("#pageDiv").show();
	}
}
//跳转
//$("#toIndexPage").click(function() {
//	var index = $('#pageIndex').val();
//	detailed(index)
//	pageNow();
//});
// 输入页码校正
//$("#pageIndex").off("keyup").on("keyup", function() {
//	var value = parseInt($(this).val());
//	var end = parseInt($("#pageTotal").val());
//	if(!value || value == 0 || value > end) {
//		$(this).val("");
//	} else {
//		$(this).val(value);
//	}
//})
$(function() {
	startly();
	endly();
	leftShift();
	region();
	clinic();
});