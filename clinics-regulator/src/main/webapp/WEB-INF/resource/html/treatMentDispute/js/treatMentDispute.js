/**
 * Created by hw on 2016/10/15.
 */
function dateForMate(dateTime){
    var dateReturn = dateTime.getFullYear()+'-'+(dateTime.getMonth()+1)+'-'+dateTime.getDate()+' '+dateTime.getHours()+':'+dateTime.getMinutes()+':'+dateTime.getSeconds();
    return dateReturn
}
var nowDateTime = dateForMate(new Date());
$('#djDate').val(nowDateTime);
//tab切换
$('.preSamTabHeader li').on('click',function(){
    $(this).addClass('active').siblings('li').removeClass('active');
    if($(this).hasClass('preSamOrderCheck')){
        $('.tabO').show().siblings('.tabT').hide();
        $('.treatStepOne').show().siblings('div').hide();
    }else{
        $('.tabT').show().siblings('.tabO').hide();
        $('.orderQueryTable').hide();
        $('.treatStepTwo.tabTwo.x').hide();
    }
});
//tab1函数
//时间时分秒
$(".ui_timepicker").datetimepicker({
    showSecond: true,
    timeFormat: 'hh:mm:00',
    timeText: '时间',
    stepHour: 1,
    stepMinute: 1,
    stepSecond: 1,
    hourText: '小时',
    minuteText: '分钟',
    secondText: '秒钟 ',
    currentText: '现在',
    closeText: '完成',
    maxDate:new Date()
});
//请求鉴定字典
var userNameL ='';
function queryDict(){
    var url = path.path+"/dispute/queryDict";
    $.ajax({
        type : "get",
        url : url,
        cache : false,
        async : false,
        success : function(res){
            if(res.code == '000000'){
                var str = '';
                var str2 = '';
                $.each(res.list,function(){
                    str+='<option value="'+this.dictItemCode+'">'+this.dictItemName+'</option>'
                });
                $('.classification').empty().append(str);
                $.each(res.list2,function(){
                    str2+='<option value="'+this.dictItemCode+'">'+this.dictItemName+'</option>'
                });
                $('.grade').empty().append(str2);
                userNameL = res.userName;
                $('.userName').val(userNameL);
            }
        }
    });
}
queryDict();
//请求地址
function queryAdress(){
    var url = path.path+"/dispute/queryAdress";
    $.ajax({
        type : "get",
        url : url,
        cache : false,
        async : false,
        success : function(res){
            if(res.code == '000000'){
                $('.belongAreaTre .provence').val(res.dto.province).attr('data-id',res.dto.provinceCode);
                $('.belongAreaTre .city').val(res.dto.city).attr('data-id',res.dto.cityCode);
                $('.belongAreaTre .county').val(res.dto.county).attr('data-id',res.dto.countyCode);
                $('.provenceOrder').val(res.dto.province).attr('data-id',res.dto.provinceCode);
                $('.cityOrder').val(res.dto.city).attr('data-id',res.dto.cityCode);
                $('.areaOrder').val(res.dto.county).attr('data-id',res.dto.countyCode);
                var str = '';
                $.each(res.list,function(){
                    str += '<option value="'+this.id+'" data-id="'+this.parentId+'">'+this.name+'</option>';
                });
                $('.belongAreaTre select').empty().append(str);
                $('.countyOrder').empty().append(str);
                getQueryOrgName();
                $(".areaShow").html(res.dto.county);
            }
        }
    });
}
queryAdress();
//tab1时间初始化
function dateRe(){
    //dateReduction($('#fsDate'));
    //dateReduction($('#djDate'));
    //dateReduction($('#czDate'));
}
//联系方式正则
$('.chargeMobile').on('blur',function(){
    checkPhone($(this),$(this).val())
});
$('.visitMobile').on('blur',function(){
    checkPhone($(this),$(this).val())
});
$('.kindredMobile').on('blur',function(){
    checkPhone($(this),$(this).val())
});
$('.userMobile').on('blur',function(){
    checkPhone($(this),$(this).val())
});

//三页之间切换
$('.treatStepOne .next.tabOne').on('click',function(){
    var isNull = false;
    $.each($('.treatStepOne input'),function(){
        if($(this).val()== ''){
            isNull = true
        }
    });
    if($('.orgName').val()== null){
        isNull = true
    }
    if($('.treatStepOne textarea').val()==''){
        isNull = true
    }
    if(isNull == true){
        layer.alert('登记记录不完整！');
        return
    }
    if(new Date($('#fsDate').val()).getTime()>new Date($('#djDate').val()).getTime()){
        layer.alert('登记日期不能小于发生日期！');
        $('#fsDate').val('');
        $('#djDate').val(nowDateTime);
        return
    }
    $('.treatStepOne').hide();
    $('.treatStepTwo').show();
    $('.treatStepThree').hide();
});
$('.treatStepTwo .next.tabOne').on('click',function(){
    var isNull = false;
    $.each($('.treatStepTwo.xs .txs'),function(){
        if($(this).val()== ''){
            isNull = true
        }
    });
    if(isNull == true){
        layer.alert('登记记录不完整！');
        return
    }
    $('.treatStepOne').hide();
    $('.treatStepTwo').hide();
    $('.treatStepThree').show();
});
$('.treatStepThree .next.tabOne').on('click',function(){
    if(new Date($('#djDate').val()).getTime()>new Date($('#czDate').val()+'23:59:59').getTime()){
        layer.alert('处置日期不能小于登记日期！');
        $('#czDate').val('');
        return
    }
    /*var isNull = false;
    $.each($('.treatStepThree input'),function(){
        if($(this).val()== ''){
            isNull = true
        }
    });
    if($('.treatStepThree textarea').val()==''){
        isNull = true
    }
    if(isNull == true){
        layer.alert('登记记录不完整！');
        return
    }*/
    var orgName = '';
    var orgId = $('.orgName').val();
    $.each($('.orgName option'),function(){
        if($(this).val() == orgId){
            orgName = $(this).html();
        }
    });
    var countryCode =  $('.belongAreaTre select').val();
    var cityCode = '';
    var city = '';
    var country = '';
    $.each($('.belongAreaTre select option'),function(){
        if($(this).val() == countryCode){
            country = $(this).text();
            cityCode = $(this).attr('data-id');
        }
    });
    $.each($('.belongAreaTre input'),function(){
        if($(this).attr('data-id') == cityCode){
            city = $(this).val()
        }
    });
    var identifyStatus = Number($('.identifyStatus.y').val());
    var identifyType = $('.classification.y').val();
    var identifyGrade = $('.grade.y').val();
    var aroseDate = new Date($('#fsDate').val());
    //var bookedDate = $('#djDate').val();
    var dispute = $('.dispute').val();
    var chargeName = $('.chargeName').val();
    var chargeTitle = Number($('.chargeTitle.y').val());
    var chargeMobile = $('.chargeMobile').val();
    var visitName = $('.visitName').val();
    var visitStatus = $('.visitStatus').val();
    var visitMobile = $('.visitMobile').val();
    var kindredMobile = $('.kindredMobile').val();
    var userName = $('.userName').val();
    var userMobile = $('.userMobile').val();
    var mercyStatus = Number($('.mercyStatus.y').val());
    var justiceStatus = '';
    if($('.result-txt.x').html() == '合格'){
        justiceStatus = 1;
    }else{
        justiceStatus = 0;
    }
    var mercyData = $('#czDate').val();
    var mercyUser = $('.mercyUser').val();
    var mercyMethod = $('.mercyMethod').val();
    var url = path.path+"/dispute/saveDispute";
    var data = {
        orgName:orgName,
        orgId:orgId,
        countyCode:countryCode,
        cityCode:cityCode,
        city:city,
        county:country,
        identifyStatus:identifyStatus,
        identifyType:identifyType,
        identifyGrade:identifyGrade,
        aroseDate:aroseDate,
        //bookedDate:bookedDate,
        dispute:dispute,
        chargeName:chargeName,
        chargeTitle:chargeTitle,
        chargeMobile:chargeMobile,
        visitName:visitName,
        visitStatus:visitStatus,
        visitMobile:visitMobile,
        kindredMobile:kindredMobile,
        userName:userName,
        userMobile:userMobile,
        mercyStatus:mercyStatus,
        justiceStatus:justiceStatus,
        mercyData:mercyData,
        mercyUser:mercyUser,
        mercyMethod:mercyMethod
    };
    $.ajax({
        type : "post",
        url : url,
        dataType:"json",
        data :JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        cache : false,
        async : false,
        success : function(res){
            if(res.code == '000000'){
                layer.alert('医疗争议登记成功！');
                $('.treatStepOne').show();
                $('.treatStepTwo').hide();
                $('.treatStepThree').hide();
                inputReduction();
                dateRe();
            }
        }
    });

});




//tab2函数
//tab1时间初始化
function dateReT(){
    dateReduction($('#dateBegin'));
    dateReduction($('#dateEnd'));
    dateReduction($('#happenTime'));
    dateReduction($('#ManagementDate'));
}
dateReT();
//联系方式正则
$('.zrrContact').on('blur',function(){
    checkPhone($(this),$(this).val())
});
$('.tabThzContact').on('blur',function(){
    checkPhone($(this),$(this).val())
});
$('.tabTqsContact').on('blur',function(){
    checkPhone($(this),$(this).val())
});
//点击查询
var list = [];
var userId = '';
$('.preConditionQuery').on('click',function(){
    preConditionQuery()
});
function preConditionQuery(){
    $('.orderQueryTable').show();
    $('.treatStepTwo.tabTwo').hide();
    var url = path.path+"/dispute/queryDisputes";
    var data = {
        key:$('.orderNumber').val(),
        beginDate:$('#dateBegin').val(),
        endDate:$('#dateEnd').val(),
        countyCode:$('.chooseArea select').val()
    };
    $.ajax({
        type : "get",
        url : url,
        data:data,
        cache : false,
        async : false,
        success : function(res){
            if(res.code == '000000'){
                $('.treatStepTwo.tabTwo.x').hide();
                var str = '';
                list = res.list;
                userId = res.userId;
                $.each(res.list,function(i){
                    if(this.identifyStatus == 0){
                        this.identifyStatus = '未鉴定'
                    }else{
                        this.identifyStatus = '已鉴定'
                    }
                    if(this.mercyStatus == 0){
                        this.mercyStatus = '待处置'
                    }else if(this.mercyStatus == 1){
                        this.mercyStatus = '处置中'
                    }else if(this.mercyStatus == 2){
                        this.mercyStatus = '已处置'
                    }
                    str += '<tr> <td class="orderNum">'+(i+1)+'</td>' +
                        ' <td>'+this.city+this.county+'</td>' +
                        ' <td>'+dateFormat(new Date(this.bookedDate))+'</td> ' +
                        '<td>'+this.orgName+'</td> ' +
                        '<td>'+this.dispute+'</td>' +
                        ' <td>'+this.identifyStatus+'</td>' +
                        ' <td>'+this.mercyStatus+'</td>' +
                        ' <td>'+this.mercyMethod+'</td> ' +
                        '<td>'+this.userName+'</td>' +
                        ' <td class="orderChooseNo">查看</td> </tr>';
                });
                $('.orderQueryTable tbody').empty().append(str);
            }
        }
    });
}
//点击查看
$(document).on('click','.orderChooseNo',function(){
    var num = Number($(this).siblings('.orderNum').html())-1;
    $('.orName').html(list[num].orgName).attr('data-id',list[num].id);
    $.each($('.identifyStatus.x option'),function(){
        if($(this).val()== list[num].identifyStatus){
            $(this).attr('selected','selected')
        }
    });
    $.each($('.grade.x option'),function(){
        if($(this).val()== list[num].identifyGrade){
            $(this).attr('selected','selected')
        }
    });
    $.each($('.classification.x option'),function(){
        if($(this).val()== list[num].identifyType){
            $(this).attr('selected','selected')
        }
    });
    $('#happenTime').val(new Date(list[num].aroseDate).getFullYear()+'-'+(new Date(list[num].aroseDate).getMonth()+1)+'-'+new Date(list[num].aroseDate).getDate());
    $('.belongAT').html(list[num].city+list[num].county);
    $('.dengDateT').html(dateForMate(new Date(list[num].bookedDate)));
    $('.newDateT').html(new Date(list[num].createDate).getFullYear()+'-'+(new Date(list[num].createDate).getMonth()+1)+'-'+new Date(list[num].createDate).getDate());
    $('.argueRT').html(list[num].dispute);
    $('.zrNameT').val(list[num].chargeName);
    $('.zrrContact').val(list[num].chargeMobile);
    $.each($('.chargeTitle.x option'),function(){
        if($(this).val()== list[num].chargeTitle){
            $(this).attr('selected','selected')
        }
    });
    $('.hzNameT').val(list[num].visitName);
    $('.hzXzT').val(list[num].visitStatus);
    $('.tabThzContact').val(list[num].visitMobile);
    $('.tabTqsContact').val(list[num].kindredMobile);
    $('.sbrNameT').html(list[num].userName);
    $('.scrContact').html(list[num].userMobile);
    $.each($('.mercyStatus.x option'),function(){
        if($(this).val()== list[num].mercyStatus){
            $(this).attr('selected','selected')
        }
    });
    $('#ManagementDate').val(list[num].mercyData);
    $('.czFzrT').val(list[num].mercyUser);
    $('.czMethod').val(list[num].mercyMethod);
    if(list[num].justiceStatus == 1){
        $('.result.n').removeClass("result-n");
        $(".result-txt.n").text("是");
    }else{
        $('.result.n').addClass("result-n");
        $(".result-txt.n").text("否");
    }
    $('.result.n').css('cursor','not-allowed');
    isEdit = false;
    if(userId == list[num].userId){
        $('.next.tabTwo.dis').show();
    }else{
        $('.next.tabTwo.dis').hide();
    }
    $(this).parents('.orderQueryTable').hide();
    $('.tabTwo.treatStepTwo').show();
});
//点击编辑
$('.next.tabTwo.dis').on('click',function(){
    $('.treatStepTwo.tabTwo').removeClass('x');
    removeReadOnly();
    $('.result.n').css('cursor','pointer');
    isEdit = true;
    $(this).hide();
});
//点击确定
$('.next.tabTwo.sure').on('click',function(){
    if(new Date($('#happenTime').val()).getTime()>new Date($('.dengDateT').html()).getTime()){
        layer.alert('发生日期不能大于登记日期！');
        $('#happenTime').val('');
        return
    }
    if(new Date($('#ManagementDate').val()+'23:59:59').getTime()<new Date($('.dengDateT').html()).getTime()){
        layer.alert('处置日期不能小于登记日期！');
        $('#ManagementDate').val('');
        return
    }
    var isNull = false;
    $.each($('.treatStepTwo.tabTwo .tab2'),function(){
        if($(this).val()==''){
            isNull = true;
        }
    });
  /*  $.each($('.treatStepTwo.tabTwo textarea'),function(){
        if($(this).val()==''){
            isNull = true;
        }
    });*/
    if(isNull == true){
        layer.alert('登记不完整');
        return
    }
    var id = $('.orName').attr('data-id');
    var identifyStatus = Number($('.identifyStatus.x').val());
    var identifyType = $('.classification.x').val();
    var identifyGrade = $('.grade.x').val();
    var aroseDate = new Date($('#happenTime').val());
    var dispute = $('.argueRT').val();
    var chargeName = $('.zrNameT').val();
    var chargeTitle = Number($('.chargeTitle.x').val());
    var chargeMobile = $('.zrrContact').val();
    var visitName = $('.hzNameT').val();
    var visitStatus = $('.hzXzT').val();
    var visitMobile = $('.tabThzContact').val();
    var kindredMobile = $('.tabTqsContact').val();
    var mercyStatus = Number($('.mercyStatus.x').val());
    var justiceStatus = '';
    if($('.result-txt.n').html() == '合格'){
        justiceStatus = 1;
    }else{
        justiceStatus = 0;
    }
    var mercyData = $('#ManagementDate').val();
    var mercyUser = $('.czFzrT').val();
    var mercyMethod = $('.czMethod').val();
    var url = path.path+"/dispute/updateDispute";
    var data = {
        id:id,
        orgName:null,
        countyCode:null,
        cityCode:null,
        city:null,
        county:null,
        identifyStatus:identifyStatus,
        identifyType:identifyType,
        identifyGrade:identifyGrade,
        aroseDate:aroseDate,
        bookedDate:null,
        dispute:dispute,
        chargeName:chargeName,
        chargeTitle:chargeTitle,
        chargeMobile:chargeMobile,
        visitName:visitName,
        visitStatus:visitStatus,
        visitMobile:visitMobile,
        kindredMobile:kindredMobile,
        userName:null,
        userMobile:null,
        mercyStatus:mercyStatus,
        justiceStatus:justiceStatus,
        mercyData:mercyData,
        mercyUser:mercyUser,
        mercyMethod:mercyMethod
    };
    $.ajax({
        type : "post",
        url : url,
        dataType:"json",
        data :JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        cache : false,
        async : false,
        success : function(res){
            if(res.code == '000000'){
                preConditionQuery();
                addReadOnly();
            }
        }
    });
    $('.orderQueryTable').show();
    $('.tabTwo.treatStepTwo').hide();
    $('.treatStepTwo.tabTwo').addClass('x');
    $('.next.tabTwo.dis').show();
});



var datepickerObj = {
    dateFormat:'yy-mm-dd',
    changeMonth: true,
    changeYear: true,
    showButtonPanel:false,//是否显示按钮面板
    clearText:"清除",//清除日期的按钮名称
    closeText:"关闭",//关闭选择框的按钮名称
    prevText: '<上月',
    nextText: '下月>',
    currentText: '今天',
    monthNames: ['一月','二月','三月','四月','五月','六月',
        '七月','八月','九月','十月','十一月','十二月'],
    monthNamesShort: ['01','02','03','04','05','06',
        '07','08','09','10','11','12'],
    dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
    dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
    dayNamesMin: ['日','一','二','三','四','五','六'],
    weekHeader: '周',
    maxDate:new Date(),
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: true,
    timeText: '时间',
    hourText: '小时',
    minuteText: '分钟'
};
//日期
$( "#dateBegin" ).datepicker(datepickerObj);
$( "#dateEnd" ).datepicker(datepickerObj);
//$("#happenTime").datepicker(datepickerObj);
$("#ManagementDate").datepicker(datepickerObj);
//$( "#fsDate" ).datepicker(datepickerObj);
//$( "#djDate" ).datepicker(datepickerObj);
$( "#czDate" ).datepicker(datepickerObj);

var datePiObj = {
    showSecond: true,
    timeFormat: 'hh:mm:ss',
    stepHour: 1,
    stepMinute: 1,
    stepSecond: 1,
    timeText: '时间',
    hourText: '小时',
    secondText: '秒',
    minuteText: '分钟',
    closeText:"关闭",//关闭选择框的按钮名称
    prevText: '<上月',
    nextText: '下月>',
    currentText: '今天',
    maxDate:new Date()
};
$("#fsDate").datetimepicker(datePiObj);
$("#happenTime").datetimepicker(datePiObj);

$("#happenTime").on('mousedown',function(Event)
{
    if(isEdit == false){
        if (0 === Event.button) Event.preventDefault()
    }
});
$("#ManagementDate").on('mousedown',function(Event)
{
    if(isEdit == false){
        if (0 === Event.button) Event.preventDefault()
    }
});

//合格不合格的切换
$('.result.x').click(function(){
    if($(this).hasClass("result-n")){
        $(this).removeClass("result-n");
        $(".result-txt.x").text("是")
    }else{
        $(this).addClass("result-n");
        $(".result-txt.x").text("否");
    }
});
var isEdit = true;
$('.result.n').click(function(){
    if(isEdit == false){
        return
    }
    if($(this).hasClass("result-n")){
        $(this).removeClass("result-n");
        $(".result-txt.n").text("是")
    }else{
        $(this).addClass("result-n");
        $(".result-txt.n").text("否");
    }
});

//input不可编辑
function addReadOnly(){
    $('.treatStepTwo.tabTwo').addClass('x');
    $('.treatStepTwo.tabTwo.x input').attr('readOnly','readOnly');
    $('.treatStepTwo.tabTwo.x select').attr({'disabled':'disabled','readOnly':'readOnly'});
    $('.treatStepTwo.tabTwo.x textarea').attr('readOnly','readOnly');
}
addReadOnly();
//input可编辑
function removeReadOnly(){
    $('.treatStepTwo.tabTwo input').removeAttr('readOnly');
    $('.treatStepTwo.tabTwo select').removeAttr('disabled');
    $('.treatStepTwo.tabTwo textarea').removeAttr('readOnly');
}

//电话号码正则
function checkPhone(dom,phone){
    if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){
        layer.alert("手机号码有误，请重填");
        dom.val('');
        return false;
    }
}

//input 初始状态
function inputReduction(){
    $('.tabO input:not(".borCcc.x")').val('');
    $('.tabO textarea').val('');
}
//日期初始化
function dateReduction(dom){
    dom.val(dateFormat(new Date()))
}
//格式化日期2010-09-09
function dateFormat(date){
    var m='';
    var d='';
    if(date.getMonth()<9){
        m='0'
    }
    if(date.getDate()<10){
        d='0'
    }
    var formatDate = date.getFullYear()+'-'+m+(date.getMonth()+1)+'-'+d+date.getDate();
    return formatDate
}

//下拉选框的到诊所信息
$('#changeSelect').on('change',function(){
    getQueryOrgName();
});
//请求诊所信息
function getQueryOrgName(){
    var url = path.path+"/dispute/queryOrgName";
    var data = {
        roadCode:$('#changeSelect').val()
    };
    $.ajax({
        type : "get",
        url : url,
        data : data,
        cache : false,
        async : false,
        success : function(res){
            if(res.code == '000000'){
                var str = '';
                if(res.orgList!=null){
                    $.each(res.orgList,function(){
                        str+='<option value="'+this.id+'">'+this.name+'</option>'
                    });
                }
                $('.orgName').empty().append(str);
            }
        }
    });
}