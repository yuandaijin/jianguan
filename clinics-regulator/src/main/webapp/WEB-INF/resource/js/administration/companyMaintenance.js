var btn_ok = 1;
/*分页*/
$("#company-maintenance").createPage({
	pageCount : 1,
	current : 1,
	backFn : function(){}
});

/*弹窗*/
$("body").on("click", ".layui-layer-close", function() {
	$(".layui-layer-shade").remove();
	$(".layui-layer").empty().remove();
});
// var popupContent = $(".popup-inner").clone(true);
var popupContent = $("#update-popup-warp").html();
$(".popup-warp").empty();
$(".popup-warp").detach();
$(".update-company-btn").click(function() {
	edit_Company();
});
$(".add-company-btn").click(function() {
	layer.open({
		type: 1,
		title: '增加管理公司',
		fix: false, //不固定
		shadeClose: false, //开启遮罩关闭
		skin: 'layui-layer-rim', //加上边框
		area: ['824px', '550px'], //宽高
		// content: $("#update-popup-warp").html()
	});
	$(".popup-inner").empty().remove();
	$(".layui-layer-content").html(popupContent);
	init_dz();//地区框选择
	btn_ok = 1;
});
$(".delete-company-btn").click(function() {
	delete_companyC();
});
/*选择日期*/
$("body").on("click", ".time-choose", function(e){
	$(this).blur(); //去掉input的光标
    e.stopPropagation();
    $(this).lqdatetimepicker({
        css : 'datetime-day',
        dateType: 'D'
    });
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
/*选择下拉列表选项companyType*/
$("body").on("click", "#companyType dt,#companyType dd", function() {
	$("#companyType dt").html($(this).text());
	$("#companyType dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
/*选择下拉列表选项isAdmin*/
$("body").on("click", "#isAdmin dt,#isAdmin dd", function() {
	$("#isAdmin dt").html($(this).text());
	$("#isAdmin dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});
$(function(){
	init();
	init_dz();
});
/*首次加载方法  */
function init(){
	var orderList = ['createTime'];
	var xtCompany = {
			'uid':-1,
			'companyType':-1,
			'addressId':-1,
			'payType':-1,
			'isAdmin':-1,
			'cid':-1
	};
	var dataInBean = {
			'currentPage':1,
			'lineSize':10000,
			'orderList':orderList,
			'xtCompany':xtCompany
	};
	$.ajax({
		url:'../company/getXtCompanyByPageAnd/123',
		type:'POST',
		data:{
			dataInBeanString:JSON.stringify(dataInBean)
		},
		success:function(data){
			appendTbody(data.msg);
		}
	});
}
/*首次加载添加内容  */
function appendTbody(datas) {
	$('#tbody').empty();
    for(var i = 0;i<datas.length;i++){
    	var address = "";
    	if(datas[i].xtAddress){
    		if(datas[i].xtAddress.province!=0 && datas[i].xtAddress.province!=''){
    			address = address + datas[i].xtAddress.province
    		}
    		if(datas[i].xtAddress.city!=0 && datas[i].xtAddress.city!=''){
    			address = address + datas[i].xtAddress.city
    		}
    		if(datas[i].xtAddress.county!=0 && datas[i].xtAddress.county!=''){
    			address = address + datas[i].xtAddress.county
    		}
    		if(datas[i].xtAddress.road!=0 && datas[i].xtAddress.road!=''){
    			address = address + datas[i].xtAddress.road
    		}
    		address = address + datas[i].xtAddress.address;
    	}
        var tbody = '<tr>' +
            '<td>'+$_getValString(datas[i].companyName)+'</td> ' +
            '<td>'+$_getValString(datas[i].createTime)+'</td> ' +
            '<td>'+$_getValString(datas[i].tel)+'</td> ' +
            '<td>'+$_getValString(address)+'</td> ' +
            '<td>'+$_getValString(datas[i].corporation)+'</td> ' +
            '<td>'+$_getValString(datas[i].bankCard)+'</td> ' +
            '<td>'+$_getValString(datas[i].payCard)+'</td> ' +
            '<td><a class="add-enable">启用</a>&nbsp;|&nbsp;<a class="add-disable">禁用</a>'+
            '<input type="hidden" name="companyId" value="'+$_getValString(datas[i].companyId)+'">' + 
            '<input type="hidden" name="uid" value="'+$_getValString(datas[i].uid)+'"</td> </tr>'
        $('#tbody').append(tbody);
    }
};
function init_dz(){
	//机构地区
	$('#local-selector').location([
	                               {url:"../comm/district/all",id:'provinceCode',name:'provinceCode',strId:'province',strName:'province',param:'provinces'},
	                               {url:"../comm/district",id:'cityCode',name:'cityCode',strId:'city',strName:'city'},
	                               {url:"../comm/district",id:'countyCode',name:'countyCode',strId:'county',strName:'county'},
	                               {url:"../comm/district",id:'roadCode',name:'roadCode',strId:'road',strName:'road'},
	                               ]);
	//居住地址显示
	$('.store-selector .text > div').html($('#town').attr('homeDistrict'));
}	
$(document).on('click','#update-confirm',function(){
	if(btn_ok == 1){
		add_company();
	}else{
		update_company();
	}
	
});
/* 启用禁用操作 */
$(document).on('click','.add-enable',function(){
	$('.add-enable').each(function(){
		$(this).text("启用");
	});
	$(this).text("已启用");
});
/* $(document).on('click','.add-disable',function(){
	$(this).text("已启用");
}); */

/* 增加管理公司  */
function add_company(){
	if(!checkPassword()){
		layer.msg('两次密码不一样',{icon: 2});
		return;
	}
	if(!checkXtUserName()){
		return;
	}
	var xtAddress = {
			'provinceCode':$('#provinceCode').val(),
			'province':$('#province').val(),
			'cityCode':$('#cityCode').val(),
			'city':$('#city').val(),
			'countyCode':$('#countyCode').val(),
			'county':$('#county').val(),
			'roadCode':$('#roadCode').val(),
			'road':$('#road').val(),
			'address':$('input[name="address"]').val()
	};
	var xtUser = {
			'userName':$('#cmsUserName').val(),
			'pwd':$('#cmsUserPassword').val(),
			'name' : $('input[name="companyName"]').val()
	};
	var xtCompany = {
		'companyType':$('#companyType dt').attr('option'),	
		'isAdmin':$('#isAdmin dt').attr('option'),	
		'companyName':$('input[name="companyName"]').val(),
		'xtAddress':xtAddress,
		'tel':$('input[name="tel"]').val(),
		'corporation':$('input[name="corporation"]').val(),
		'bankCard':$('input[name="bankCard"]').val(),
		'payCard':$('input[name="payCard"]').val(),
		'createTime':$('input[name="createTime"]').val(),
		'xtUser':xtUser
	};
	var dataInBean = {
			'xtCompany':xtCompany
	}
	$.ajax({
		url:'../company/addCompany/' + getRondom(),
		type:'POST',
		data:{
			dataInBeanString:JSON.stringify(dataInBean)
		},
		success:function(data){
			close_alert();
			layer.msg(data.state,{icon: 1});
			init();
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}

//关闭添加或修改的弹出层
function close_alert(){
	$(".layui-layer-shade").detach();
	$(".layui-layer").empty();
	$(".layui-layer").detach();
};

function edit_Company(){
	var companyId = "";
	$('.add-enable').each(function(){
		if($(this).text()=="已启用"){
			companyId = $($(this).parent().find('input[name="companyId"]')).val();
			return false;
		}
	});
	if(companyId == ""){
		layer.msg("请先启用右侧编辑", {icon: 1});
		return;
	};
	var xtCompany = {
			'companyId':companyId
		};
	var dataInBean = {
			'xtCompany':xtCompany
	}
	$.ajax({
		url:'../company/getXtCompanyByPrimaryKey/' + getRondom(),
		type:'POST',
		data:{
			dataInBeanString:JSON.stringify(dataInBean)
		},
		success:function(data){
			open_edit_layer();
			$('input[name="tel"]').val();
			set_editMsg(data.msg);
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
	
}
//弹出编辑的显示层
function open_edit_layer(){
	layer.open({
		type: 1,
		title: '编辑管理公司',
		fix: false, //不固定
		shadeClose: false, //开启遮罩关闭
		skin: 'layui-layer-rim', //加上边框
		area: ['824px', '550px'], //宽高
		// content: $("#update-popup-warp").html()
	});
	$(".popup-inner").empty().remove();
	$(".layui-layer-content").html(popupContent);
	init_dz();//地区框选择
	btn_ok = 2;
}

//$(".update-type-list dt").attr("option", option值).html($().find($(".update-type-list dd").attr(option)));
var dtHtml = function(option,id) {
	for(var i = 0; i < $(id + " dd").length; i++) {
		if($($(id + " dd")[i]).attr("option") == option) {
			$(id + " dt").html($($(id + " dd")[i]).html());
			$(id + " dt").attr("option", option);
		}
	}
};
function set_editMsg(datas){
	var address = "";
	var address_b = "";
	if(datas.xtAddress != null){
		if(datas.xtAddress.province!=0 && datas.xtAddress.province!=''){
			address = address + datas.xtAddress.province
		}
		if(datas.xtAddress.city!=0 && datas.xtAddress.city!=''){
			address = address + datas.xtAddress.city
		}
		if(datas.xtAddress.county!=0 && datas.xtAddress.county!=''){
			address = address + datas.xtAddress.county
		}
		if(datas.xtAddress.road!=0 && datas.xtAddress.road!=''){
			address = address + datas.xtAddress.road
		}
		address_b = datas.xtAddress.address;
	}
	dtHtml(datas.companyType,'#companyType');//显示公司类型
	dtHtml(datas.isAdmin,'#isAdmin');//显示管理公司类型
	$('.store-selector .text > div').html($_getValString(address));//设置地址显示
	$('input[name="tel"]').val($_getValString(datas.tel));
	$('input[name="tel"]').val($_getValString(datas.tel));
	$('input[name="bankCard"]').val($_getValString(datas.bankCard));
	$('input[name="createTime"]').val($_getValString(datas.createTime));
	$('input[name="companyName"]').val($_getValString(datas.companyName));
	$('input[name="address"]').val($_getValString(address_b));
	$('input[name="corporation"]').val($_getValString(datas.corporation));
	$('input[name="payCard"]').val($_getValString(datas.payCard));
	/*  */
	$('input[name="edit_address_id"]').val($_getValString(datas.addressId));
	$(".is-admin").val(datas.isAdmin);
	$('.edithide').remove();
}
/* 编辑管理公司Controller  */
function update_company(){
	var xtAddress = {
			'provinceCode':$('#provinceCode').val(),
			'province':$('#province').val(),
			'cityCode':$('#cityCode').val(),
			'city':$('#city').val(),
			'countyCode':$('#countyCode').val(),
			'county':$('#county').val(),
			'roadCode':$('#roadCode').val(),
			'road':$('#road').val(),
			'address':$('input[name="address"]').val()
	};
	var companyId = "";
	$('.add-enable').each(function(){
		if($(this).text()=="已启用"){
			companyId = $($(this).parent().find('input[name="companyId"]')).val();
			return false;
		}
	});
	var xtCompany = {
		'companyId':companyId,
		'companyType':$('#companyType dt').attr('option'),	
		'isAdmin':$('#isAdmin dt').attr('option'),	
		'companyName':$('input[name="companyName"]').val(),
		'xtAddress':xtAddress,
		'addressId':$('input[name="edit_address_id"]').val(),
		'tel':$('input[name="tel"]').val(),
		'corporation':$('input[name="corporation"]').val(),
		'bankCard':$('input[name="bankCard"]').val(),
		'payCard':$('input[name="payCard"]').val(),
		'createTime':$('input[name="createTime"]').val(),
		'isAdmin' : $(".is-admin").val()
	};
	var dataInBean = {
			'xtCompany':xtCompany
	}
	$.ajax({
		url:'../company/editCompany/' + getRondom(),
		type:'POST',
		data:{
			dataInBeanString:JSON.stringify(dataInBean)
		},
		success:function(data){
			close_alert();
			layer.msg(data.state,{icon: 1});
			init();
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
}
function delete_companyC(){
	layer.msg("不能删除管理公司哦!", {icon: 2});
	return;
	var companyId = "";
	var uid = "";
	$('.add-enable').each(function(){
		if($(this).text()=="已启用"){
			companyId = $($(this).parent().find('input[name="companyId"]')).val();
			uid = $($(this).parent().find('input[name="uid"]')).val();
			return false;
		}
	});
	if(companyId == ""){
		layer.msg("请先启用右侧编辑", {icon: 1});
		return;
	};
	layer.confirm('是否确认删除？', {
        btn: ['确认','取消'] //按钮
    }, function(){
    	var xtCompany = {
    			'companyId':companyId,
    			'uid':uid
    		};
    		var dataInBean = {
    				'xtCompany':xtCompany
    		}
    		$.ajax({
    			url:'../company/deleteCompany/' + getRondom(),
    			type:'POST',
    			async:false,
    			data:{
    				dataInBeanString:JSON.stringify(dataInBean)
    			},
    			success:function(data){
    				layer.msg(data.state, {icon: 1});
    				init();
    			},
    			error:function(){
    				layer.alert("网络出现故障");
    			}
    		});
    }, function(){
    	layer.msg("取消成功", {icon: 1});
    });
};
/**
 * 判断值是否存在 返回数字
 * @param val 值
 * @returns
 */
function $_getValInt(val){
	if(val){
		return val;
	}else{
		return -1;
	}
};
function $_getValString(val){
	if(val){
		return val;
	}else{
		return "";
	}
};

/*密码校验*/
function checkPassword(){
	var cmsUserPassword = $('#cmsUserPassword').val();
	var cmsUserCheckPass = $('#cmsUserCheckPass').val();
	if(cmsUserPassword != cmsUserCheckPass || cmsUserPassword.length < 6){
		return false;
	}
	return true;
};

/**
 * 检查用户账号的重复
 */
function checkXtUserName(){
	var userName = $('#cmsUserName').val();
	var flg = false;
	$.ajax({
		url:'../user/selectRepeatUserName',
		type:'POST',
		async:false,
		data:{
			userName:userName
		},
		success:function(data){
			if(data.code == '100001'){
				layer.msg(data.message, {icon: 2});
				flg =  false;
				return;
			}else{
				flg = true;
			}
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
	return flg;
};

/************随机函数*************/
function getRondom(){
	return Math.floor(Math.random()*1000);
};
/************END***************/



