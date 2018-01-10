/**
 * 
 */
var clearTimeConf;
var payClearWaitForRec = [];//待回执的对象
(function(){
	$.ajax({
		url:'../payClear/selectClearTimeConf',
		type:'POST',
		async:false,
		data:{},
		success:function(data){
			clearTimeConf = data;
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
})();

$(function(){
	selectClearLastTime(1);//载入就加载
	init_btn();//清分回执按钮控制
});

/*清分时候的最后的时间的查询显示在页面 下拉框的事件   或者是待回执时候改变时候的待回执时间列表的变化*/
$('#payClear-userType-choose > dd').on('click',function(){
	if($('.clearing-state-type > dt').attr('option') == 1){//未清分
		selectClearLastTime($(this).attr('option'));
	}else{//待回执
		selectWaitForRecTime($(this).attr('option'));
	}
});

/*查询最后一次进行清分的时间*/
function selectClearLastTime(userType){
	$.ajax({
		url:'../payClear/selectClearLastTime',
		type:'POST',
		async:false,
		data:{
			userType:userType
		},
		success:function(data){
			if(data.msgCode == 500){
				layer.msg(data.msg, {icon: 1});
				return;
			}
			$('#clearing-server-startSear').val(data.msg);
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
};

/*清分 回执搜索框*/
$(document).on('click','#loadPageOneSearch',function(){
	var clearing_state_type = $('.clearing-state-type dt').attr('option');//判断是否是待清分还是待回执
	if(clearing_state_type == 1){
		selectWaitForClear();
	}else{
		selectWaitForRec();//待回执查询
	}
	
});

/*执行待清分的查询*/
function selectWaitForClear(){
	var userType = $('#payClear-userType-choose dt').attr('option');
	var endTime = $('#clearing-server-endSear').val();
	if(endTime == ''){
		layer.msg('请选择清分结束时间', {icon: 2});
		return;
	}
	if(endTime < $('#clearing-server-startSear').val()){
		layer.msg('选择的清分时间小于开始时间', {icon: 2});
		return;
	}
	$.ajax({
		url:'../payClear/selectWaitForClear',
		type:'POST',
		async:false,
		data:{
			userType:userType,
			endTime:endTime
		},
		success:function(data){
			if(data.msgCode == 500 || !data.msg){
				layer.msg(data.msg, {icon: 1});
				return;
			}
			appendPageWaitForClear(data.msg);
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
};

/**显示方法,待清分和待回执复用**/
function appendPageWaitForClear(datas){
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

/*清分开始执行*/
$(document).on('click','#qingfen',function(){
	layer.confirm('是否进行清分？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			insertPayBonusClear();
		}, function(){
			layer.msg('取消成功', {icon: 2});
		}
	);
});

/*清分开始执行*/
function insertPayBonusClear(){
	var userType = $('#payClear-userType-choose dt').attr('option');
	var endTime = $('#clearing-server-endSear').val();
	if(endTime == ''){
		layer.msg('请选择清分结束时间', {icon: 2});
		return;
	}
	$.ajax({
		url:'../payClear/insertPayBonusClear',
		type:'POST',
		async:false,
		data:{
			userType:userType,
			endTime:endTime
		},
		success:function(data){
			if(data.msgCode == 500 || !data.msg){
				layer.msg(data.msg, {icon: 1});
				return;
			}
			layer.msg("清分成功", {icon: 1});
			$('#loadPageOneData').empty();
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
};

/**清分和回执的时间选择框的事件**/
$(".clearing-state-type").on("click", "dt,dd", function() {
	
	if($(this).attr("option") == "2") {
		$("#dqflbsj").hide();
		$("#dhzlbsj").show();
		
		$('#qingfen').hide();
		$('#huizhi').show();
		
		selectWaitForRecTime($('#payClear-userType-choose dt').attr('option'));//首次加载
		
	} else if($(this).attr("option") == "1") {
		$("#dqflbsj").show();
		$("#dhzlbsj").hide();
		
		$('#qingfen').show();
		$('#huizhi').hide();
	}
	$(".clearing-state-type dt").html($(this).text());
	$(".clearing-state-type dt").attr("option", $(this).attr("option"));
	$(this).parent().removeClass("s-choose-after").addClass("s-choose-before");
});


/******查询待回执的时间列表********/
function selectWaitForRecTime(userType){
	//var userType = $('#payClear-userType-choose dt').attr('option');
	$.ajax({
		url:'../payClear/selectWaitForRecTime',
		type:'POST',
		async:false,
		data:{
			userType:userType
		},
		success:function(data){
			$('#dhzlbsjlist').html('');
			if(data.msgCode == 500 || !data.msg){
				payClearWaitForRec = [];
				layer.msg(data.msg, {icon: 2});
				return;
			}
			payClearWaitForRec = data.msg;
			for(var i=0;i<data.msg.length;i++){
				$('#dhzlbsjlist').append('<option value ="'+i+'">'+data.msg[i].startTime+'--'+data.msg[i].endTime+'</option>');
			}
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
};


/*执行待回执的查询*/
function selectWaitForRec(){
	var userType = $('#payClear-userType-choose dt').attr('option');
	var selectedItem = $('#dhzlbsjlist option:selected').val();
	if(payClearWaitForRec.length == 0 || !selectedItem){
		layer.msg('待回执时间有误', {icon: 2});
		return;
	}
	var startTime = payClearWaitForRec[selectedItem].startTime;
	var endTime = payClearWaitForRec[selectedItem].endTime;
	$.ajax({
		url:'../payClear/selectWaitForRec',
		type:'POST',
		async:false,
		data:{
			userType:userType,
			startTime:startTime,
			endTime:endTime
		},
		success:function(data){
			if(data.msgCode == 500 || !data.msg){
				layer.msg(data.msg, {icon: 1});
				return;
			}
			appendPageWaitForClear(data.msg);
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
};
/***回执开始***/
$(document).on('click','#huizhi',function(){
	layer.confirm('是否进行回执？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			updatePayBonusClearRec();
		}, function(){
			layer.msg('取消成功', {icon: 2});
		}
	);
});


/*回执开始执行*/
function updatePayBonusClearRec(){
	var userType = $('#payClear-userType-choose dt').attr('option');
	var selectedItem = $('#dhzlbsjlist option:selected').val();
	if(payClearWaitForRec.length == 0 || !selectedItem){
		layer.msg('待回执时间有误', {icon: 2});
		return;
	}
	var startTime = payClearWaitForRec[selectedItem].startTime;
	var endTime = payClearWaitForRec[selectedItem].endTime;
	$.ajax({
		url:'../payClear/updatePayBonusClearRec',
		type:'POST',
		async:false,
		data:{
			userType:userType,
			startTime:startTime,
			endTime:endTime
		},
		success:function(data){
			if(data.msgCode == 500 || !data.msg){
				layer.msg(data.msg, {icon: 1});
				return;
			}
			layer.msg("回执成功", {icon: 1});
			$('#loadPageOneData').empty();
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
};

/*清分 和回执按钮的显示控制*/
function init_btn(){
	if($('.clearing-state-type > dt').attr('option') == 1){
		$('#qingfen').show();
		$('#huizhi').hide();
	}else if($('.clearing-state-type > dt').attr('option') == 2){
		$('#qingfen').hide();
		$('#huizhi').show();
	}
};

var clear_endTime_btn = {
	    elem: '#clearing-server-endSear',
	    format: 'YYYY-MM-DD hh:mm:ss',
	    max: laydate.now(clearTimeConf), //最大日期
	    istime: true,
	    istoday: false,
	    choose: function(datas){
	    }
};

laydate(clear_endTime_btn);

/**
 * 导出Excel按钮
 */
$(".operate-btns").on("click","a[data-operate=1]", function(){
	var infoTr = $("#loadPageOneData").find("tr");
	if(infoTr.length == 0){
		layer.alert("有效数据为空<br/>请查询出相关数据后再进行此操作");
		return;
	}
	$("#userType").val($('#payClear-userType-choose dt').attr('option'));
	$("#endTime").val($('#clearing-server-endSear').val());
	if(endTime == ''){
		layer.msg('请选择清分结束时间', {icon: 2});
		return;
	}
	if(endTime < $('#clearing-server-startSear').val()){
		layer.msg('选择的清分时间小于开始时间', {icon: 2});
		return;
	}
	$("#downloadExcel").submit();
	return;
	$.ajax({
		url:'../payClear/downloadExcel',
		type:'POST',
		async:false,
		data:{
			userType:userType,
			endTime:endTime
		},
		success:function(data){
			if(data.msgCode == 500 || !data.msg){
				layer.alert(data.msg);
				return;
			}
			appendPageWaitForClear(data.msg);
		},
		error:function(){
			layer.alert("网络出现故障");
		}
	});
});
