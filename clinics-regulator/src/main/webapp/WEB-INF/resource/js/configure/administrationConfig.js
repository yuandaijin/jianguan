/*分页*/
$("#mechanism-paging").createPage({
	pageCount : 10,
	current : 1,
	backFn : function(){}
});


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
$(".o-table").on("click", "span.i-check", function() {
	console.log($(this).parentsUntil("tr").find("input"))
	var ischeck = !$(this).parentsUntil("tr").find("input").prop("checked");
	$(this).parentsUntil("tr").find("input").prop("checked", ischeck);
console.log(ischeck);
console.log($(this) == $($(".o-table span.i-check")[0]))
console.log($(this));
console.log($($("#check-all").find("span.i-check")[0]))
	if($(this) == $($("#check-all").find("span.i-check")[0])) { //若选中全选
		$(".o-table span.i-check").find("input").prop("checked", ischeck);
		if(ischeck) { //若选中全选
			$(".o-table span.i-check").find("i").addClass("i-checked");
		} else {
			$(".o-table span.i-check").find("i").removeClass("i-checked");
		}
	} else {
		if(ischeck) {
			$(this).parentsUntil("tr").find("i").addClass("i-checked");
		} else {
			$(this).parentsUntil("tr").find("i").removeClass("i-checked");
		}
	}
});
$("#check-all").on("click", "label", function() {
	var ischeck = !$("#choose-all").prop("checked");
	$(".o-table span.i-check").find("input").prop("checked", ischeck);
	if(ischeck) { //若选中全选
		$(".o-table span.i-check").find("i").addClass("i-checked");
	} else {
		$(".o-table span.i-check").find("i").removeClass("i-checked");
	}
});


/*弹窗*/
$("body").on("click", ".layui-layer-close", function() {
	$(".layui-layer-shade").remove();
	$(".layui-layer").empty().remove();
});

// 机构分成
var mechanismPopup = $("#mechanism-popup-warp").html();
// 个人分成
var personalPopup = $("#personal-popup-warp").html();
// 管理公司配置
var administrationPopup = $("#administration-company-warp").html();
$(".popup-inner").empty().remove();
$(".mechanism-btn").click(function() {
	layer.open({
		type: 1,
		title: '分成比例设置',
		fix: false, //不固定
		shadeClose: false, //开启遮罩关闭
		skin: 'layui-layer-rim', //加上边框
		area: ['757px', '440px'], //宽高
		// content: $("#update-popup-warp").html()
	});
	$(".layui-layer-content").html(mechanismPopup);
});

$(".personal-btn").on("click", function() {
	layer.open({
		type: 1,
		title: '分成比例设置',
		fix: false, //不固定
		shadeClose: false, //开启遮罩关闭
		skin: 'layui-layer-rim', //加上边框
		area: ['757px', '440px'], //宽高
		// content: $("#update-popup-warp").html()
	});
$(".layui-layer-content").html(personalPopup);
});

/*$(".config-btn").click(function() {
	layer.open({
		type: 1,
		title: '管理公司配置',
		fix: false, //不固定
		shadeClose: false, //开启遮罩关闭
		skin: 'layui-layer-rim', //加上边框
		area: ['757px', '440px'], //宽高
		// content: $("#update-popup-warp").html()
	});
	$(".layui-layer-content").html(administrationPopup);
});*/
/*****************配置弹框*********************/
$(document).on('click','.config-btn-one',function(){
	layer.open({
		type: 1,
		title: '管理公司配置',
		fix: false, //不固定
		shadeClose: false, //开启遮罩关闭
		skin: 'layui-layer-rim', //加上边框
		area: ['757px', '440px'], //宽高
		cancel : function(){
			searchComOrgList();
		}
		// content: $("#update-popup-warp").html()
	});
	$(".layui-layer-content").html(administrationPopup);
	appendPopTbody(this);
});
/*****************end配置弹框*********************/

/******管理公司配置数据填充只能一条进行*********/
function appendPopTbody(obj){
	var orgId = $($(obj).parent().find('input')[0]).val();
	var companyId = $($(obj).parent().find('input')[1]).val();
	var orgName = $($(obj).parent().parent().find('td')[1]).html();
	$('#pop-tbody').empty();
	$('#pop-tbody').append('<tr><td><input type="hidden" value="'+orgId+'"></input>'+orgName+'</td></tr>');
	$.ajax({
		url:'../company/selectAllCompany/'+getRondom(),
		type:'POST',
		data:{},
		success:function(datas){
			if(datas[0] == null || datas.length == 0){
				return;
			}
			$('#choose-company').empty();
			for(var i=0;i<datas.length;i++){
				var data = '<option value="'+datas[i].companyId+'">'+datas[i].companyName+'</option>';
				$('#choose-company').append(data);
			}
			$('#choose-company').val(companyId);
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}
/******end管理公司配置数据填充*********/

/********确定更新按钮********/
$(document).on('click','#update-org-companyId',function(){
	updateOrgCompanyId();
});
/********end确定更新按钮********/

/**********更新org的管理公司***********/
function updateOrgCompanyId(){
	var orgIds = new Array();
	$('#pop-tbody').find('tr').each(function(){
		$(this).find('td').each(function(){
			orgIds.push($(this).find('input').val())
		});
	});
	var companyId = $('#choose-company').val();
	$.ajax({
		url:'../company/updateOrgCompanyId/'+getRondom(),
		traditional:true,
		type:'POST',
		data:{
			orgIds:orgIds,
			companyId:companyId
		},
		success:function(datas){
			layer.msg('更新成功!', {time: 2000, icon:1});
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
	//orgIds.push();
	
}
/**********end更新org的管理公司***********/

/*****************首次加载页面填充下拉管理公司选择框***************/
(function(){
	$.ajax({
		url:'../company/selectAllCompany/'+getRondom(),
		type:'POST',
		data:{},
		success:function(datas){
			if(datas[0] == null || datas.length == 0){
				return;
			}
			$('#mechanism-company-list').empty();
			$('#mechanism-company-list').append('<dt option="0">全部</dt> <dd option="0" class="s-default">全部</dd><dd option="-1" >无管理公司</dd>');
			for(var i=0;i<datas.length;i++){
				$('#mechanism-company-list').append('<dd option="'+datas[i].companyId+'">'+datas[i].companyName+'</dd>');
			}
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
})();
/*******************************END*****************************/



/*******************************日期选择*****************************/
$("body").on("click", ".time-choose", function(e){
	$(this).blur(); //去掉input的光标
    e.stopPropagation();
    $(this).lqdatetimepicker({
        css : 'datetime-day',
        dateType: 'D'
    });
});
/*******************************END*****************************/

/*******************************所属区域选择*****************************/
(function(){
	$('#air-local-selector').location([
	                               {url:"../comm/district/all",id:'provinceCode',name:'provinceCode',strId:'province',strName:'province',param:'provinces'},
	                               {url:"../comm/district",id:'cityCode',name:'cityCode',strId:'city',strName:'city'},
	                               {url:"../comm/district",id:'countyCode',name:'countyCode',strId:'county',strName:'county'},
	                               {url:"../comm/district",id:'roadCode',name:'roadCode',strId:'road',strName:'road'},
	                               ]);
	
	//机构地区去除全国
	var dataVal = $("#company-selector-wrap .JD-stock div[data-area='0'] ul li").first().find("a").attr("data-value");
	if(dataVal){
		if(dataVal==1){
			$("#company-selector-wrap .JD-stock div[data-area='0'] ul li").first().remove();
		}
	}
	//居住地址显示
	$('.store-selector .text > div').html($('#air-town').attr('homeDistrict'));
})();
/*******************************END*****************************/
/*******************************搜索框搜索*****************************/
$("body").on('click','.o-search',function(){
	searchComOrgList();
});

/*******************************END*****************************/



/*******************************载入数据查询方法*****************************/
function searchComOrgList(){
	var provinceCode = $('#provinceCode').val();
	var cityCode = $('#cityCode').val();
	var countyCode = $('#countyCode').val();
	var roadCode = $('#roadCode').val();
	var companyId = $('#mechanism-company-list dt').attr('option');//公司类型
	var startTime = $('#join-start-time').val();//加入开始时间
	var endTime = $('#join-end-time').val();//加入结束时间
	var searchName = $('.search-input').val();//搜索框内容
	var obj = {
		'companyId':companyId,
		'provinceCode':provinceCode,
		'cityCode':cityCode,
		'countyCode':countyCode,
		'roadCode':roadCode,
		'startTime':startTime,
		'endTime':endTime,
		'searchName':searchName,
		'currentPage':1,
		'pageSize':10
	};
	$.ajax({
		url:'../company/selectOrgToComList/'+getRondom(),
		type:'POST',
		data:{
			orgCompanyPojoInString:JSON.stringify(obj)
		},
		success:function(datas){
			appendComOrglist(datas.list);
			$("#mechanism-paging").createPage({
				pageCount : datas.totalPage,
				current : datas.currentPage,
				backFn : function(p){
					paging(p);
				}
			});
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}
/*******************************END*****************************/

/*******************************首次加载列表数据*****************************/
(function(){
	searchComOrgList();
})();
/*******************************END*****************************/

/*******************************填充查询数据方法*****************************/
function appendComOrglist(datas){
	$('#com-list-body').empty();
	for(var i=0;i<datas.length;i++){
		var data = '<tr><td><div><input type="checkbox" /></div><span class="i-check"><i ></i></span></td> ' +
	    '<td>'+datas[i].orgName+'</td> ' +
	    '<td>'+datas[i].airLoaction+'</td> ' +
	    '<td>'+datas[i].addressDetail+'</td> ' +
	    '<td>'+datas[i].orgTelephone+'</td> ' +
	    '<td>'+datas[i].createTime+'</td> ' +
	    '<td>'+datas[i].companyName+'</td> ' +
	    '<td class="last-td"><input type="hidden" value="'+datas[i].orgId+'"><input type="hidden" value="'+datas[i].companyId+'"><a class="usable config-btn config-btn-one">配置</a></td></tr>';
		$('#com-list-body').append(data);
	}
}
/*******************************END*****************************/


/************分页函数*************/
function paging(currentPage){
	var provinceCode = $('#provinceCode').val();
	var cityCode = $('#cityCode').val();
	var countyCode = $('#countyCode').val();
	var roadCode = $('#roadCode').val();
	var companyId = $('#mechanism-company-list dt').attr('option');//公司类型
	var startTime = $('#join-start-time').val();//加入开始时间
	var endTime = $('#join-end-time').val();//加入结束时间
	var searchName = $('.search-input').val();//搜索框内容
	var obj = {
		'companyId':companyId,
		'provinceCode':provinceCode,
		'cityCode':cityCode,
		'countyCode':countyCode,
		'roadCode':roadCode,
		'startTime':startTime,
		'endTime':endTime,
		'searchName':searchName,
		'currentPage':currentPage,
		'pageSize':10
	};
	$.ajax({
		url:'../company/selectOrgToComList/'+getRondom(),
		type:'POST',
		data:{
			orgCompanyPojoInString:JSON.stringify(obj)
		},
		success:function(datas){
			appendComOrglist(datas.list);
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}

/************end分页函数*************/

/*************多选配置***************/
$('body').on('click','#all-update-org-companyId',function(){
	var orgIds = new Array();
	var orgNames = new Array();
	$('#com-list-body').find('tr').each(function(){
		var $tds = $(this).find('td');
		if($($tds[0]).find('span').find('i').hasClass('i-checked')){
			orgNames.push($($tds[1]).html());
			orgIds.push($($($tds[7]).find('input')[0]).val());
		}
	});
	if(orgIds.length == 0){
		layer.msg('请选择需要修改的组织机构', {icon: 3});
		return;
	}
	layer.open({
		type: 1,
		title: '管理公司配置',
		fix: false, //不固定
		shadeClose: false, //开启遮罩关闭
		skin: 'layui-layer-rim', //加上边框
		area: ['757px', '440px'], //宽高
		// content: $("#update-popup-warp").html()
	});
	$(".layui-layer-content").html(administrationPopup);
	appendManyOrdCompanyId(orgIds,orgNames);
})
/*************end***************/

/**********多个组织进行配置的弹出层配置************/
function appendManyOrdCompanyId(orgIds,orgNames){
	$('#pop-tbody').empty();
	var appendData = '';
	for(var i=0;i<orgIds.length;i++){
		if(i!=0 && i%2 == 1){
			appendData = appendData + '<td><input type="hidden" value="'+orgIds[i]+'"></input>'+orgNames[i]+'</td></tr>';
			$('#pop-tbody').append(appendData);
			console.log(appendData);
		}else{
			appendData = '<tr><td><input type="hidden" value="'+orgIds[i]+'"></input>'+orgNames[i]+'</td>';
		}
		if(i+1 == orgIds.length && orgIds.length%2 == 1){
			appendData = '<tr><td><input type="hidden" value="'+orgIds[i]+'"></input>'+orgNames[i]+'</td></tr>';
			$('#pop-tbody').append(appendData);
		}
	}
	$.ajax({
		url:'../company/selectAllCompany/'+getRondom(),
		type:'POST',
		data:{},
		success:function(datas){
			if(datas[0] == null || datas.length == 0){
				return;
			}
			$('#choose-company').empty();	
			for(var i=0;i<datas.length;i++){
				var data = '<option value="'+datas[i].companyId+'">'+datas[i].companyName+'</option>';
				$('#choose-company').append(data);
			}
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
};
/**********end多个组织进行配置的弹出层配置************/

/************随机函数*************/
function getRondom(){
	return Math.floor(Math.random()*1000);
};
/*******************************END*****************************/



