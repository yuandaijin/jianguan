(function() {
	
    $.ajaxSetup({
    	async : false
    });

	// 加载数据
	function loadDatas(obj, url, params) {
		
		// 发送请求
		$.get(url, params, function(result) {
			if (result instanceof Object) {
				// 显示数据前,先清空原有的数据
				obj.find("tbody").empty();
				if (result.success) {
					var data = result.data;
					$("#page").attr("end", data.end);
					$("#page").attr("pgCt", data.pgCt);
					//在页面显示数据
					renderDatas(obj, data.datas);
				} else {
					$("#page").attr("end", 0);
					$("#page").attr("pgCt", 0);
					layer.msg(result.msg, {icon: 5, time: 2000});
				}
			} else {
				$("#page").attr("end", 0);
				$("#page").attr("pgCt", 0);
				layer.msg("请刷新页面...", {icon: 5, time: 2000});
			}
		});
	}
	// 在页面显示数据
	function renderDatas(obj, datas) {
		// 拿到表头
		var ths = obj.find("thead > tr > th");
		// 拿到显示数据的区域
		var tbody = obj.find("tbody");
		// 遍历需要加载的数据
		for (var i = 0; i < datas.length; i++) {
			var data = datas[i];
			// 创建一行
			var tr = $("<tr></tr>");
			// 遍历表头
			for (var j = 0; j < ths.length - 1; j++) {
				var d = data[$(ths[j]).attr("field")];
				if (d) {
					// 创建一个格子,并在中间添加数据
					var td = $("<td>" + d + "</td>");
					// 把格子添加到行里面
					td.appendTo(tr);
				} else {
					$("<td> </td>").appendTo(tr);
				}
			}
			var tdSelect = '<td class="last-td"><a class="personConfig" doctorId="' + data.id + '">配置</a></td>';
			$(tdSelect).appendTo(tr);
			// 把一行添加到tbody中
			tr.appendTo(tbody);
		}
	}
	//给jQuery的原型添加插件dataGrid
	$.fn.dataGrid = function(options) {
		var obj = $(this);
		//把传入的参数缓存到$(this)上面
		obj.data("options", options);
		loadDatas(obj, options.url, options.params);
	};
})();

// 页面函数
$(function() {
	
	/******************administrationConfig.js******************/
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
	$("body").on("click", ".mechanism-company-list dt,.mechanism-company-list dd", function() {
		// console.log(this);
		$(".mechanism-company-list dt").html($(this).text());
		$(".mechanism-company-list dt").attr("option", $(this).attr("option"));
		$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
	});
	$("body").on("click", ".medical-mechanism-list dt,.medical-mechanism-list dd", function() {
		// console.log(this);
		$(".medical-mechanism-list dt").html($(this).text());
		$(".medical-mechanism-list dt").attr("option", $(this).attr("option"));
		$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
	});

	/*checkbox选择状态样式*/
	$(".o-table input").each(function(i) {
		if($($(".o-table input")[i]).prop("checked")) {
			$($(".o-table input")[i]).parentsUntil("tr").find("i").addClass("i-checked");
		} else {
			$($(".o-table input")[i]).parentsUntil("tr").find("i").removeClass("i-checked");
		}
	});
	$(".o-table").on("click", ".i-check", function() {
		var ischeck = !$(this).parentsUntil("tr").find("input").prop("checked");
		$(this).parentsUntil("tr").find("input").prop("checked", ischeck);
		if(ischeck) {
			$(this).parentsUntil("tr").find("i").addClass("i-checked");
		} else {
			$(this).parentsUntil("tr").find("i").removeClass("i-checked");
		}	
	});
	$(".o-table").on("click", "label", function() {
		if(!$(this).parentsUntil("tr").find("input").prop("checked")) {
			$(this).parentsUntil("tr").find("i").addClass("i-checked");
		} else {
			$(this).parentsUntil("tr").find("i").removeClass("i-checked");
		}
	});


	// 个人分成
	var personalPopup = $("#personal-popup-warp").html();
	$(".popup-inner").empty().remove();
	/******************administrationConfig.js******************/
	
	var path = $("#path").text();	// 项目地址
	
	var selectCompany = $("#selectCompany");	// 选择管理公司的下拉列表
	var selectOrg = $("#selectOrg");			// 选择组织机构的下拉列表
	var page = $("#page");			// 分页控件
	var refreshMsg = "请刷新页面..."; // 刷新页面提示
	
	// 当页面加载完成的时候，发送请求去加载管理公司选择列表
	$.get(path + "/configure/getCompanies", function(result) {
		if (result instanceof Object) {
			if (result.success) {
				var data = result.data;
				var dtf = '<dt option="' + data[0].companyId + '">' + data[0].companyName + '</dt>';
				var dds = '<dd class="divider" role="separator"></dd>';
				var ddf = '<dd option="' + data[0].companyId + '" class="s-default">' + data[0].companyName + '</dd>';
				selectCompany.append(dtf); 
				selectCompany.append(dds); 
				selectCompany.append(ddf);
				for (var i = 1; i < data.length; i++) {
					var dd = '<dd option="' + data[i].companyId + '">' + data[i].companyName + '</dd>';
					selectCompany.append(dd);
				}
				$("#companyText").html($("#selectCompany dt").html());
				loadOrg($("#selectCompany dt").attr("option"));
			}
		} else {
			layer.msg(refreshMsg, {icon: 5, time: 2000});
		}
	});
	
	// 管理公司发生改变，修改可选择的组织机构
	selectCompany.on("click", "dt,dd", function() {
		$("#keyword").val("");
		$("#companyText").html($(this).html());
		loadOrg($(this).attr("option"));
	});
	
	// 加载组织机构
	function loadOrg(companyId) {
		if(companyId != 0) {
			selectOrg.html("");
			$.get(path + "/configure/getOrgs", {"companyId" : companyId}, function(result) {
				if (result instanceof Object) {
					if (result.success) {
						var data = result.data;
						var dtf = '<dt option="' + data[0].id + '">' + data[0].name + '</dt>';
						var dds = '<dd class="divider" role="separator"></dd>';
						var ddf = '<dd option="' + data[0].id + '" class="s-default">' + data[0].name + '</dd>';
						selectOrg.append(dtf);
						selectOrg.append(dds); 
						selectOrg.append(ddf);
						for (var i = 1; i < data.length; i++) {
							var dd = '<dd option="' + data[i].id + '">' + data[i].name + '</dd>';
							selectOrg.append(dd);
						}
						// 加载医疗机构的时候加载表格数据
						loadTableData({"orgId": $("#selectOrg dt").attr("option")});
					} else {
						$("tbody").empty();
						$("#page").attr("end", 0);
						$("#page").attr("pgCt", 0);
						loadPage();
						layer.msg(result.msg, {icon: 5, time: 2000});
					}
					$("#orgText").html($("#selectOrg dt").html());
				} else {
					layer.msg(refreshMsg, {icon: 5, time: 2000});
				}
			});
		}
	}
	
	// 加载表格数据
	function loadTableData(params) {
		$("#dataTable").dataGrid({
			url: path + "/configure/getDataGrid",  	// 加载数据表格地址
			params: params
		});
		
		loadPage();	// 加载分页条
	}
	
	// 当医疗机构改变时
	selectOrg.on("click", "dt,dd" , function() {
		$("#keyword").val("");
		$("#orgText").html($(this).html());
		loadTableData({"orgId": $(this).attr("option")});
	})
	
	// 快速查询
	$("#search").on("click", function() {
		params = {};
		params.keyword = $("#keyword").val();	// 关键字
		var orgId = $("#selectOrg dt").attr("option");	// 组织机构id
		if (orgId != 0) {
			params.orgId = orgId;
		}
		loadTableData(params);
	});
	
	// 分页
	function loadPage() {
		page.createPage({
			pageCount: page.attr('end'),	// 总页数
			current: page.attr('pgCt'),	  	// 当前页
			//单击回调方法，pgCt是当前页码
			backFn: function(pgCt){
				params = {};
				params.pgCt = pgCt;
				params.keyword = $("#keyword").val();	// 关键字
				var orgId = $("#selectOrg dt").attr("option");	// 组织机构id
				if (orgId != 0) {
					params.orgId = orgId;
				}
				loadTableData(params);
			}
		});
	}
	
	// 管理公司分成比例配置
	$(".mechanism-btn").on("click", function() {
		$("#storesProportion").addClass("hide");
//		$("#doctorProportion").addClass("hide");
		var orgId = $("#selectOrg dt").attr("option");
		if (!orgId || orgId == "" || orgId == 0) {
			layer.msg("无组织机构..", {icon: 5, time: 2000});
			return;
		}
		// 弹窗
		layer.open({
			type: 1,
			title: '分成比例设置',
			fix: false, //不固定
			shadeClose: false, //开启遮罩关闭
			skin: 'layui-layer-rim', //加上边框
			area: ['757px', '440px'], //宽高
		});
		
		loadWindow();
	});
	
	// 个人分成比例配置
	$("#dataTable").on("click", ".personConfig", function() {
		$("#storesProportion").addClass("hide");
//		$("#doctorProportion").removeClass("hide");
		var orgId = $("#selectOrg dt").attr("option");
		if (!orgId || orgId == "" || orgId == 0) {
			layer.msg("无组织机构..", {icon: 5, time: 2000});
			return;
		}
		// 弹窗
		layer.open({
			type: 1,
			title: '医生分成比例设置',
			fix: false, //不固定
			shadeClose: false, //开启遮罩关闭
			skin: 'layui-layer-rim', //加上边框
			area: ['757px', '440px'], //宽高
		});
		
		loadWindow($(this));
	});
	
	// 选择用户类型
	$("body").on("click", "#personal-popup-inner a[userType]", function() {
		$("#personal-popup-inner a[userType]").removeClass("s-color");
		$(this).addClass("s-color");
		var userType = $(this).attr("userType");
		var form = $(this).closest("div").find("form");
		var formDiv = form.find("div");
		
		if (userType == 1) {
			$("#storesProportion").addClass("hide");
			formDiv.html($("#proportion-form").html());
		} else if(userType == 2) {
			$("#storesProportion").removeClass("hide");
			formDiv.html($("#proportion-form").html());
		}
		
		changeType($(this), form);
	});
	
	// 选择服务类型
	$("body").on("click", "#personal-popup-inner a[serviceType]", function() {
		$("#personal-popup-inner a[serviceType]").removeClass("s-color");
		$(this).addClass("s-color");
		
		changeType($(this), $(this).closest("div").find("form"));
	});
	
	var flag = true;	// 保存按钮标识
	// 保存按钮
	$("body").on("click", ".save_bt", function() {
		if (flag) {
			flag = false;
			var form = $(this).closest("div").find("form");
			var saveUrl =  path + "/configure/savePayBonus";
			var formData = form.serializeArray();
			var param = {};
			$.each(formData, function(i, n){
				var value = parseFloat(n.value);
				if (value) {
					param[n.name] = value;
				}
			});
			param.orgId = $("#selectOrg dt").attr("option");
			if (!param.orgId || param.orgId == "" || param.orgId == 0) {
				layer.msg("无组织机构..", {icon: 5, time: 2000});
				flag = true;
				return;
			}
			param.companyId = $("#selectCompany dt").attr("option");
			if (!param.companyId || param.companyId == "") {
				layer.msg("无管理公司..", {icon: 5, time: 2000});
				flag = true;
				return;
			}
			param.userType = $(this).closest("div").find("[userType].s-color").attr("userType");
			param.serviceType = $(this).closest("div").find("[serviceType].s-color").attr("serviceType");
			
			$.post(saveUrl, param, function(result) {
				if (result instanceof Object) {
					if (result.success) {
						layer.msg(result.msg, {icon: 6, time: 2000});
					} else {
						layer.msg(result.msg, {icon: 5, time: 2000});
					}
				} else {
					layer.msg(refreshMsg, {icon: 5, time: 2000});
				}
				flag = true;
			});
		}
	});
	
	// 初始化按钮
	$("body").on("click", ".default_bt", function() {
		var form = $(this).closest("div").find("form");
		var param = {};
		param.companyId = $("#selectCompany dt").attr("option");
		param.orgId = $("#selectOrg dt").attr("option");
		param.doctorUid = form.find("[name=doctorUid]").val();
		param.userType = $("#personal-popup-inner a[userType].s-color").attr("userType");
		param.serviceType = $("#personal-popup-inner a[serviceType].s-color").attr("serviceType");
		// 获取分成配置
		$.post(path + "/configure/getDefaultPayBonus", param, function(result) {
			if (result instanceof Object) {
				if (result.success) {
					var data = result.data;
					var proportions = form.find("[name]");
					$.each(proportions, function(i, v) {
						var value = data[v.name];
						if (i > 0) {
							if (value) {
								$(v).val((value * 100).toFixed());
							} else {
								$(v).val(0);
							}
						}
					});
				}
			} else {
				layer.msg(refreshMsg, {icon: 5, time: 2000});
			}
		});
	});
	
	// 获取分成比例,并填充到表单中
	function getPayBonus(form, param) {
		form[0].reset();
		param.companyId = $("#selectCompany dt").attr("option");
		param.orgId = $("#selectOrg dt").attr("option");
		
		// 获取分成配置
		$.post(path + "/configure/getPayBonus", param, function(result) {
			if (result instanceof Object) {
				if (result.success) {
					var data = result.data;
					var proportions = form.find("[name]");
					$.each(proportions, function(i, v) {
						var value = data[v.name];
						if (i > 0) {
							if (value) {
								$(v).val((value * 100).toFixed());
							} else {
								$(v).val(0);
							}
						}
					});
				}
			} else {
				layer.msg(refreshMsg, {icon: 5, time: 2000});
			}
		});
	}
	
	// 加载弹出窗口的数据
	function loadWindow(obj) {
		// 弹出的窗口加载内容
		$(".layui-layer-content").html(personalPopup);
		// 加载form表单的内容
		$("#person-form-t div").html($("#proportion-form").html());
		
		// 根据初始化条件加载分红比例数据
		var userType = $("#personal-popup-inner a[userType].s-color").attr("userType");
		var serviceType = $("#personal-popup-inner a[serviceType].s-color").attr("serviceType");
		
		var param = {};	// 查询条件
		if (obj) {
			var doctorUid = obj.attr("doctorId");
			$("#person-form-t [name=doctorUid]").val(doctorUid);
			param.doctorUid = doctorUid;	// 查询条件：医生id
		}
		param.userType = userType;	// 查询条件：用户类型
		param.serviceType = serviceType;	// 查询条件：服务类型
		
		getPayBonus($("#person-form-t"), param);
	}
	
	// 当改变服务和用户类型时
	function changeType(obj, form) {
		var doctorUid = form.find("[name=doctorUid]").val();
		var userType = obj.closest("div").find("[userType].s-color").attr("userType");
		var serviceType = obj.closest("div").find("[serviceType].s-color").attr("serviceType");
		
		var param = {};	// 查询条件
		param.doctorUid = doctorUid;	// 查询条件：医生id
		param.userType = userType;	// 查询条件：用户类型
		param.serviceType = serviceType;	// 查询条件：服务类型
		
		getPayBonus(form, param);
	}
});