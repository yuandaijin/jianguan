/**
 * Created by hw on 2016/10/19.
 */
//格式化日期
function dateForMate(dateTime){
    var dateReturn = dateTime.getFullYear()+'-'+(dateTime.getMonth()+1)+'-'+dateTime.getDate()+' '+dateTime.getHours()+':'+dateTime.getMinutes()+':'+dateTime.getSeconds();
    return dateReturn
}
//切换年月日
$('.chooseByTimeSelect').on('change',function(){
    if($(this).val() == 'year'){
        $('.displayInB.year').show().siblings('.displayInB').hide();
    }else if($(this).val() == 'month'){
        $('.displayInB.month').show().siblings('.displayInB').hide();
    }else if($(this).val() == 'day'){
        $('.displayInB.day').show().siblings('.displayInB').hide();
    }
});
//点击查看
$(document).on('click','.orderChooseNo',function(){
    var showInfo = showDetailInfo[$(this).parents('tr').attr('data-id')];
    $('.orName').html(showInfo.orgName);
    $('.identifyStatus').val(showInfo.identifyStatus);
    $('.belongAT').html(showInfo.county);
    $('.identifyGradeName').html(showInfo.identifyGradeName);
    $('.bookedDate').html(new Date(showInfo.aroseDate).getFullYear()+'-'+(new Date(showInfo.aroseDate).getMonth()+1)+'-'+new Date(showInfo.aroseDate).getDate());
    $('.identifyTypeName').html(showInfo.identifyTypeName);
    $('.dengDateT').html(showInfo.bookedDate);
    $('.dispute').html(showInfo.dispute);
    $('.chargeName').html(showInfo.chargeName);
    $('.chargeTitle').val(showInfo.chargeTitle);
    $('.chargeMobile').html(showInfo.chargeMobile);
    $('.visitName').html(showInfo.visitName);
    $('.visitStatus').html(showInfo.visitStatus);
    $('.visitMobile').html(showInfo.visitMobile);
    $('.kindredMobile').html(showInfo.kindredMobile);
    $('.userName').html(showInfo.userName);
    $('.scrContact').html(showInfo.userMobile);
    $('.mercyStatus').val(showInfo.mercyStatus);
    $('.justiceStatus').val(showInfo.justiceStatus);
    $('.mercyData').html(showInfo.mercyData);
    $('.mercyUser').html(showInfo.mercyUser);
    $('.mercyMethod').html(showInfo.mercyMethod);
    $('.positionA').show();
});
//点击确定关闭弹窗
$('.next.tabTwo').on('click',function(){
    $('.positionA').hide();
});

//头部下拉菜单请求地区
function dropDownList(){
    var url=path.path+"/comm/resultAddress";
    var params={"parentId":"33007"};
    $.ajax({
        type : "get",
        url : url,
        data :params,
        cache : false,
        async : false,
        success : function(res){
            if (res.code=="000000") {
                var areaData=res.list;
                var str = '';
                for(var i=0; i<areaData.length; i++){
                    if(areaData[i].name=="双流区"){
                        str +='<option selected data-id="'+areaData[i].id+'" value="'+areaData[i].name+'">'+areaData[i].name+'</option>'
                    }else{
                        str +='<option data-id="'+areaData[i].id+'" value="'+areaData[i].name+'">'+areaData[i].name+'</option>'
                    }
                }
                $("#areaChoose").empty().append(str);

            }
            $.each($("#areaChoose option"),function(){
                if($(this).val() == '双流县'){
                    $(this).attr('selected','selected')
                }
            })
        }
    });
}
dropDownList();
//请求报表接口1
function treDisQuery(){
    var url=path.path+"/disputeReport/query";
    $.ajax({
        type : "get",
        url : url,
        data :sendInfo(),
        cache : false,
        async : false,
        success : function(res){
            if (res.code=="000000") {
                var areaList = res.addList;
                var certified = res.certified;
                var unidentified = res.unidentified;
                var strHead = '<td title="'+'行政区划'+'">'+'行政区划'+'</td>';
                var unSureD = '<td title="'+'已鉴定数'+'">'+'已鉴定数'+'</td>';
                var SureD = '<td title="'+'未鉴定数'+'">'+'未鉴定数'+'</td>';
                var SureTotal = '<td title="'+'合计'+'">'+'合计'+'</td>';
                var unSureDA = [];
                var uSureDA = [];
                var SureTotalA = [];
                $.each(areaList,function(i){
                    strHead+='<td title="'+this.name+'">'+this.name+'</td> ';
                    unSureDA.push(0);
                    uSureDA.push(0);
                    SureTotalA.push(0);
                    var _this = this;
                    if(certified!=null){
                        $.each(certified,function(){
                            var inThis = this;
                            if(_this.name == inThis.name){
                                unSureDA.splice(i,1,inThis.certified);
                                SureTotalA.splice(i,1,(Number(inThis.certified)+Number(SureTotalA[i])));
                            }
                        });
                    }
                    if(unidentified!=null){
                        $.each(unidentified,function(){
                            var inThis = this;
                            if(_this.name == inThis.name){
                                uSureDA.splice(i,1,inThis.certified);
                                SureTotalA.splice(i,1,(Number(inThis.certified)+Number(SureTotalA[i])));
                            }
                        });
                    }
                });
                strHead+='<td title="'+$('#areaChoose').val()+'(合计)'+'">'+$('#areaChoose').val()+'(合计)'+'</td> ';
                $('.areaListHead').empty().append(strHead);
                var unTotal = 0;
                $.each(unSureDA,function(i){
                    unSureD+='<td>'+unSureDA[i]+'</td>';
                    unTotal+=Number(unSureDA[i]);
                });
                unSureD+='<td>'+unTotal+'</td>';
                $('.unSureD').empty().append(unSureD);
                var usureTotal = 0;
                $.each(uSureDA,function(i){
                    SureD+='<td>'+uSureDA[i]+'</td>';
                    usureTotal+=Number(uSureDA[i]);
                });
                SureD+='<td>'+usureTotal+'</td>';
                $('.SureD').empty().append(SureD);
                var sureTTotal = 0;
                $.each(SureTotalA,function(i){
                    SureTotal+='<td>'+SureTotalA[i]+'</td>';
                    sureTTotal+=Number(SureTotalA[i])
                });
                SureTotal+='<td>'+sureTTotal+'</td>';
                $('.SureTotal').empty().append(SureTotal);
            }
        }
    });
}
//请求报表接口2
var showDetailInfo = [];
function treDisQueryByReport(){
    var url=path.path+"/disputeReport/queryDisputesByReport";
    $.ajax({
        type : "get",
        url : url,
        data :sendInfo(),
        cache : false,
        async : false,
        success : function(res){
            if (res.code=="000000") {
                showDetailInfo = res.list;
                var str = '';
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
                    str+='  <tr data-id='+i+'> <td>'+this.county+'</td> <td>'+this.orgName+'</td> <td>'+dateForMate(new Date(this.bookedDate))+'</td> <td>'+this.identifyStatus+'</td> <td>'+this.identifyGradeName+'</td> <td>'+this.identifyTypeName+'</td> <td>'+this.dispute+'</td> <td>'+this.chargeName+'</td> <td>'+this.userName+'</td> <td>'+this.mercyStatus+'</td> <td>'+this.mercyMethod+'</td> <td class="orderChooseNo curPointer">查看</td> </tr>'
                });
                $('.infoTTable').empty().append(str);
            }
        }
    });
}
//查询表格的参数
function sendInfo(){
    var reportType = $('.borCcc.chooseByTimeSelect').val();
    var year = '';
    var year2 = '';
    var month = '';
    var month2 = '';
    var day = '';
    if(reportType == 'year'){
        year = $('.yYear').val();
        year2 = $('.yYear2').val();
        if(year>year2){
            layer.alert('时间选择不合理');
            return
        }
        $('.showTimeChoose').html(year+'年至'+year2+'年')
    }else if(reportType == 'month'){
        year = $('.mYear').val();
        month = $('.mMonth').val();
        month2 = $('.mMonth2').val();
        if(month>month2){
            layer.alert('时间选择不合理');
            return
        }
        $('.showTimeChoose').html(year+'年'+month+'月至'+month2+'月')
    }else if(reportType == 'day'){
        year = $('.dYear').val();
        month = $('.dMonth').val();
        day = $('.dDay').val();
        if(new Date(year+'-'+month+'-'+day)>new Date()){
            layer.alert('时间选择不合理');
            return
        }
        $('.showTimeChoose').html(year+'年'+month+'月'+day+'日')
    }
    var address = $('#areaChoose').val();
    $('.showAreaChoose').html(address);
    $.each($('#areaChoose option'),function(){
        if($(this).val() == address){
            address = $(this).attr('data-id')
        }
    });
    var params={
        "reportType":reportType,
        "year":year,
        "year2":year2,
        "month":month,
        "month2":month2,
        "day":day,
        "address":address
    };
    return params
}
//地址点击确认请求
$('#areaConfirm').on('click',function(){
    treDisQuery();
    treDisQueryByReport()
});
$('.treDisQuery').on('click',function(){
    treDisQuery();
    treDisQueryByReport()
});

//设置年限开始
function setYear(){
    $(".yearSelected").html("");
    var myDate=new Date();
    for(var j=2010; j<=myDate.getFullYear(); j++){
        if(j==myDate.getFullYear()){
            var opNode=$("<option></option>").html(j+"年").attr({"selected":"selected","value":j});
        }else{
            var opNode=$("<option></option>").html(j+"年").attr({"value":j});
        }

        $(".yearSelected").append(opNode);
    }
}
setYear();
//设置年限结束

//设置月份开始
function setMonth(){
    $(".monthSelected").html("");
    var myDate=new Date();
    for(var j=0; j<12; j++){
        if(j==myDate.getMonth()){
            var opNode=$("<option></option>").html((j+1)+"月").attr({"selected":"selected","value":(j+1)});
        }else{
            var opNode=$("<option></option>").html((j+1)+"月").attr({"value":(j+1)});
        }

        $(".monthSelected").append(opNode);
    }
}
setMonth();
//设置天数
function setDay(dayNum){
    $(".daySelected").html("");
    var myDate=new Date();
    for(var j=0; j<dayNum; j++){
        if(j==myDate.getDate()){
            var opNode=$("<option></option>").html((j+1)+"日").attr({"selected":"selected","value":(j+1)});
        }else{
            var opNode=$("<option></option>").html((j+1)+"日").attr({"value":(j+1)});
        }

        $(".daySelected").append(opNode);
    }
}
//判断每个月的天数
function SureDayNum(mon,yea){
    var monthNum = mon;
    var dayNum = 31;
    if(monthNum ==1||monthNum ==3||monthNum ==5||monthNum ==7||monthNum ==8||monthNum ==10||monthNum ==12){
        dayNum = 31;
    }else if(monthNum ==4||monthNum ==6||monthNum ==9||monthNum ==11){
        dayNum = 30;
    }else if(monthNum ==2){
        var pYear = yea;
        if(isLeapYear(pYear)){
            dayNum = 29;
        }else{
            dayNum = 28;
        }
    }
    return dayNum
}
//判断是否闰年
function isLeapYear(pYear){
    var isLPYear = false;
    if((pYear%4==0 && pYear%100!=0)||(pYear%100==0 && pYear%400==0))
        isLPYear = true;
    else
        isLPYear = false;
    return isLPYear
}
//天数函数
setDay(SureDayNum($('.dMonth').val(),$('.dYear').val()));

$('.dMonth').on('change',function(){
    setDay(SureDayNum($('.dMonth').val(),$('.dYear').val()));
});
$('.dYear').on('change',function(){
    setDay(SureDayNum($('.dMonth').val(),$('.dYear').val()));
});