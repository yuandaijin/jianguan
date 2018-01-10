/**
 * Created by hw on 2016/11/7.
 */
$(function(){
	// $(".mapTab").css("height",(document.documentElement.clientHeight-140)+"px");
	$("#areaList").css("max-height",(document.documentElement.clientHeight-260)+"px");
	window.onresize=function(){
		$(".mapTab").css("height",(document.documentElement.clientHeight-140)+"px");
		$("#areaList").css("max-height",(document.documentElement.clientHeight-260)+"px");
	}
	
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
    $("#dateBegin").datepicker(datepickerObj);
    

    $("#startTime").datepicker(datepickerObj);
    $("#endTime").datepicker(datepickerObj);
    
    //加10分钟的函数
    function maxTen(beforeTen){
        var returnTen = new Date(beforeTen).getTime()+600000;
        return returnTen
    }
    //时间转换成2013-12-12 12:12:12
    function dateForMate(dateTime){
        var month,day,hours,minute,second;
        if(dateTime.getMonth()<9){
            month = '0'+(dateTime.getMonth()+1);
        }else{
            month = (dateTime.getMonth()+1);
        }
        if(dateTime.getDate()<10){
            day = '0'+dateTime.getDate();
        }else{
            day = dateTime.getDate();
        }
        if(dateTime.getHours()<10){
            hours = '0'+dateTime.getHours();
        }else{
            hours = dateTime.getHours();
        }
        if(dateTime.getMinutes()<10){
            minute = '0'+dateTime.getMinutes();
        }else{
            minute = dateTime.getMinutes();
        }
        if(dateTime.getSeconds()<10){
            second = '0'+dateTime.getSeconds();
        }else{
            second = dateTime.getSeconds();
        }
        var dateReturn = dateTime.getFullYear()+'-'+month+'-'+day+' '+hours+':'+minute+':'+second;
        return dateReturn
    }
    var countryArray = [];//右下角数组
    var countryArrayTen = [];//十分钟数组
    var countryArrayTop = [];//右边展示数组
    var totalMakerArr = [];//总的id数组
    var borderArea = '';//选择的地区
    var areaCode='';
    var refreshBeginDate = '';
    
    
    //实时历史tab
    $('.preSamTabHeader li').on('click',function(){
        $(this).addClass('active').siblings('li').removeClass('active');
        var changeId = $(this).attr('data-id');
        $('.orderQueryTablePrimary').hide();
        $('.orderQueryTableadd').hide();
        $('.content-box').hide();
        $('.treatStepTwo').hide();
        if(changeId == 1){
            $('.mapOuter').removeClass('pacityMap').siblings('div').hide();
        }else if(changeId == 2){
            $('.history').show().siblings('.mapOuter').addClass('pacityMap');
        }
    });
    $('.returnMap').on('click',function(){
        $('.nowCondition').addClass('active').siblings('li').removeClass('active');
        $('.mapOuter').removeClass('pacityMap').siblings('div').hide();
    });
    //关闭首次右下角弹窗
    $('.closeBottomPop').on('click',function(){
        $(this).parents('.rbMessage').hide();
    });
    //关闭十分钟弹窗
    $('.closeBottomPopTen').on('click',function(){
        $(this).parents('.rbMessageTen').hide();
    });
    //点击首次右下角弹窗关闭详情--table
    $('.bottomPopDatail').on('click',function(){
        if(countryArray.length == 0){
            layer.alert('无数据');
            return
        }
        $('.orderQueryTablePrimary').show();
        $('.orderQueryTableAdd').hide();
        $('.mapOuter').addClass('pacityMap');
        $(this).parents('.rbMessage').hide();
        var idIist = [];
        $.each(countryArray,function(){
            idIist.push(this.id)
        });
        getTypeMonitor(idIist,true);
        countryArray = [];
        mapMarker();
    });
    //点击十分钟弹窗弹窗关闭详情--table
    $('.bottomPopDatailTen').on('click',function(){
        if(countryArrayTen.length == 0){
            layer.alert('无数据');
            return
        }
        $(this).parents('.rbMessageTen').hide();
        $('.orderQueryTableAdd').show();
        $('.orderQueryTablePrimary').hide();
        $('.content-box').hide();
        $('.treatStepTwo').hide();
        $('.mapOuter').addClass('pacityMap');
        var idIist = [];
        $.each(countryArrayTen,function(){
            idIist.push(this.id)
        });
        getTypeMonitor(idIist,false);
        countryArrayTen = [];
        mapMarker();
    });
    //处方，争议详情back按钮
    $('.backToUp').on('click',function(){
        $(this).parents('.deBack').hide();
        if($(this).parents('.deBack').attr('data-id') == 'primary'){
            $('.orderQueryTablePrimary').show();
        }
        if($(this).parents('.deBack').attr('data-id') == 'add'){
            $('.orderQueryTableAdd').show();
        }
    });
    //处方电子病历切换
    $('.type-title span').on('click',function(){
        $(this).addClass('active').siblings().removeClass('active');
        if($(this).attr('data-id') == '1'){
            $('.sign.details-box').show().siblings('.case').hide();
        }else if($(this).attr('data-id') == '2'){
            $('.case.details-box').show().siblings('.sign').hide();
        }
    });
    //进入页面请求弹窗以及右侧数据
    function getFirstInfo(){
        countryArray = [];//右下角数组
        countryArrayTop = [];//右边展示数组
        //获取诊所的列表信息
        (function(){
        	var url = path.path+"/dynamic/queryAdress";
            $.ajax({
                type : "get",
                url : url,
                cache : false,
                async : false,
                success : function(res){
                	if(res.code == '000000'){
                		//获取当前区域
                        if(res.dto&&res.dto.county){
                        	borderArea=res.dto.county;
                        	areaCode=res.dto.countyCode;
                        	if(borderArea == '双流区')borderArea = '双流县';
                        	$('.areaShow').html(borderArea);
                        	$('.sureArea').html(borderArea);
                        }
                	}else{
                		alert('未能获取当前行政区域信息');
                	}
                }
            });
        })();
        

        var data = {
            addressCode:areaCode
        };
        var url = path.path+"/dynamic/query";
        $.ajax({
            type : "get",
            url : url,
            data : data,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    refreshBeginDate = res.endDate;
                    $('.popBeginTime').html(res.startDate);
                    $('.popEndTime').html(refreshBeginDate);
                    $('.rightTopTime').html(res.date);
                    var list = res.list;
                    var list2 = res.list2;
                    if(list!=null){
                        var totalNum = 0;
                        $.each(list,function(){
                            var listThis = this;
                            if(listThis!=null){
                                $.each(listThis,function(){
                                    var listDetailList = this;
                                    $.each($('.oneRightInfo'),function(){
                                        var _this = $(this);
                                        if(listDetailList.name == _this.attr('data-id')){
                                            if(listThis.number != 0){
                                                _this.removeClass('active')
                                            }
                                            _this.html(listThis.length)
                                        }
                                    });
                                    countryArrayTop.push({'address':listDetailList.address,'id':listDetailList.id,'name':listDetailList.name});
                                });
                                totalNum+=listThis.length
                            }else{
                                $.each($('.oneRightInfo'),function(){
                                    $(this).html('0');
                                    $(this).removeClass('active')
                                });
                                $('.oneRightInfoTotal').html('0').removeClass('active');
                            }
                        });
                        if(totalNum!=0){
                            $('.oneRightInfoTotal').removeClass('active')
                        }
                        $('.oneRightInfoTotal').html(totalNum);
                    }
                    if(list==null ||list.length == 0){
                         $.each($('.oneRightInfo'),function(){
                            $(this).html('0');
                             $(this).removeClass('active')
                         });
                        $('.oneRightInfoTotal').html('0').removeClass('active');
                    }

                    if(list2 != null){
                        var totalNumPop = 0;
                        $.each(list2,function(){
                            var listThis = this;
                            if(listThis!=null){
                                $.each(listThis,function(){
                                    var listDetail2 = this;
                                    $.each($('.popInfoRb'),function(){
                                        var _this = $(this);
                                        if(listDetail2.name == _this.attr('data-id')){
                                            _this.html(listThis.length)
                                        }
                                    });
                                    countryArray.push({'name':listDetail2.address,'id':listDetail2.id});
                                });
                                totalNumPop+=Number(listThis.length);
                            }else{
                                $.each($('.popInfoRb'),function(){
                                    $(this).html('0');
                                    $(this).removeClass('active')
                                });
                                $('.popInfoRbTotal').html('0').removeClass('active');
                            }
                        });
                        $('.popInfoRbTotal').html(totalNumPop);
                    }
                    if(list2==null ||list2.length == 0){
                        $.each($('.popInfoRb'),function(){
                            $(this).html('0')
                        });
                        $('.popInfoRbTotal').html('0');
                    }
                    if(res.flag == 0){
                        $('.rbMessage').show();
                    }
                }
            }
        });
    }
    getFirstInfo();

    //地图镇标注的点
    var localSearch = new BMap.LocalSearch(map);
    localSearch.enableAutoViewport(); //允许自动调节窗体大小
    //绘制边界
    function getBoundary(){
        var bdary = new BMap.Boundary();
        bdary.get(borderArea, function(rs){       //获取行政区域

            // TODO
            //map.clearOverlays();        //清除地图覆盖物
            var count = rs.boundaries.length; //行政区域的点有多少个
            if (count === 0) {
                alert('未能获取当前输入行政区域');
                return ;
            }
            var pointArray = [];
            for (var i = 0; i < count; i++) {
                var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "green", fillColor:"#c7d9ef",fillOpacity:".3"}); //建立多边形覆盖物
                map.addOverlay(ply);  //添加覆盖物
                pointArray = pointArray.concat(ply.getPath());
            }
            map.setViewport(pointArray);    //调整视野
            
            
      	  	
//            addlabel();
        });

    }
    getBoundary()
    
    
    
    function searchByStationName(keyword) {
        //map.clearOverlays();//清空原来的标注
        localSearch.setSearchCompleteCallback(function (searchResult) {
            var poi = searchResult.getPoi(0);
            //map.centerAndZoom(poi.point, 11);
            var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
            map.addOverlay(marker);
            //点击marker弹窗
            marker.addEventListener("click", function () { markerClick(searchResult.keyword); });
            marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            createLabel(searchResult.keyword,poi.point.lng, poi.point.lat)
        });
        localSearch.search(keyword);
    }
    //创建标注label
    function createLabel(keyword,x,y){
        var opts = {
            position : new BMap.Point(x, y),    // 指定文本标注所在的地理位置
            offset   : new BMap.Size(15,-25)    //设置文本偏移量
        };
        var label = new BMap.Label(keyword, opts);  // 创建文本标注对象
        label.setStyle({
            color : "black",
            fontSize : "14px",
            fontWeight : "bold",
            height : "25px",
            lineHeight : "25px",
            fontFamily:"微软雅黑",
            borderColor:"transparent",
            backgroundColor:'white'
        });
        map.addOverlay(label);
    }
    //循环数组，在地图上添加marker
    function mapMarker(){
        getBoundary();
        totalMakerArr = [];
        if(countryArray!=[]){
            $.each(countryArray,function(){
                totalMakerArr.push(this)
            });
        }
        if(countryArrayTen!=[]){
            $.each(countryArrayTen,function(){
                totalMakerArr.push(this)
            });
        }
        $.each(totalMakerArr,function(){
            searchByStationName(this.name);
        });
    }
    // mapMarker();

    //点击marker的调用的事件
    function markerClick(sendWord){
        var idArray = [];
        $.each(totalMakerArr,function(){
            if(this.name == sendWord){
                idArray.push(this.id)
            }
        });
        $('.orderQueryTablePrimary').show();
        $('.mapOuter').addClass('pacityMap');
        getTypeMonitor(idArray,true)

    }

    //十分钟刷新一次的函数
    var medicalDispute=0,infectious=0,antibiotic=0,totalTen=0;
    var medicalDisputeTen=0,infectiousTen=0,antibioticTen=0;
    function tenMRefresh(){
        var endDate = dateForMate(new Date(maxTen(refreshBeginDate)));
        var startDate = refreshBeginDate;
        var data = {
            addressCode:areaCode,
            startDate:startDate,
            endDate:endDate
        };
        var url = path.path+"/dynamic/dynamicEvent";
        $.ajax({
            type : "get",
            url : url,
            data : data,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    $('.popBeginTimeTen').html(startDate);
                    $('.popEndTimeTen').html(endDate);
                    var list2 = res.list;
                    refreshBeginDate = endDate;

                    if(list2.length>0){
                        var totalNumPop = 0;
                        $.each(list2,function(){
                            var listThis = this;
                            if(listThis!=null){
                                $.each(listThis,function(){
                                    var listDetail2 = this;
                                    if(listDetail2.name == '医疗争议'){
                                        medicalDispute = listThis.length;
                                    }
                                    if(listDetail2.name == '传染病'){
                                        infectious = listThis.length;
                                    }
                                    if(listDetail2.name == '抗生素'){
                                        antibiotic = listThis.length;
                                    }
                                    countryArrayTen.push({'name':listDetail2.address,'id':listDetail2.id})
                                });
                               /* if(listThis.length == 0){
                                    $.each($('.popInfoRbTen'),function(){
                                        $(this).html('0')
                                    })
                                }*/
                            }
                        });
                    }else{
                        medicalDispute=0;
                        infectious=0;
                        antibiotic=0;
                    }
                    medicalDisputeTen +=medicalDispute;
                    infectiousTen +=infectious;
                    antibioticTen +=antibiotic;
                    $.each($('.popInfoRbTen'),function(){
                        var _this = $(this);
                        if('医疗争议' == _this.attr('data-id')){
                            _this.html(medicalDisputeTen)
                        }
                        if('传染病' == _this.attr('data-id')){
                            _this.html(infectiousTen)
                        }
                        if('抗生素' == _this.attr('data-id')){
                            _this.html(antibioticTen)
                        }
                    });
                    totalTen = medicalDisputeTen + infectiousTen + antibioticTen;
                    $('.popInfoRbTotalTen').html(totalTen);
                    mapMarker();
                    if(res.flag == 0){
                        $('.rbMessageTen').show();
                    }
                }
            }
        });
    }
    setInterval(tenMRefresh,600000);
    //传id获得普通表格内容
    function getTypeMonitor(idList,isHistory){
        var data = idList;
        var url = path.path+"/dynamic/queryTypeMonitor";
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
                    if(isHistory == true){
                        var str = '';
                        $.each(res.list,function(){
                            str+='<tr data-id="'+this.typeId+'"> <td class="time" title="'+this.date.split('.')[0]+'">'+this.date.split('.')[0]+'</td> <td class="checkArea" title="'+this.address+'">'+this.address+'</td> <td class="type" title="'+this.type+'">'+this.type+'</td> <td title="'+this.orgName+'">'+this.orgName+'</td> <td title="'+this.addressDtl+'">'+this.addressDtl+'</td> <td class="detail" title="'+this.ebentDtl+'">'+this.ebentDtl+'</td> <td class="orderChooseNo">查看</td> </tr>'
                        });
                        $('.orderQueryTablePrimary tbody').empty().append(str);
                    }else{
                        var str2 = '';
                        $.each($('.orderQueryTableAdd tbody td'),function(){
                            $(this).removeClass('greenFont')
                        });
                        var hasStr = $('.orderQueryTableAdd tbody').html();
                        $.each(res.list,function(){
                            str2+='<tr data-id="'+this.typeId+'"> <td class="time" class="greenFont" title="'+this.date.split('.')[0]+'">'+this.date.split('.')[0]+'</td> <td class="greenFont checkArea" title="'+this.address+'">'+this.address+'</td> <td class="greenFont type" title="'+this.type+'">'+this.type+'</td> <td class="greenFont" title="'+this.orgName+'">'+this.orgName+'</td> <td class="greenFont" title="'+this.addressDtl+'">'+this.addressDtl+'</td> <td class="greenFont detail" title="'+this.ebentDtl+'">'+this.ebentDtl+'</td> <td class="orderChooseNo">查看</td> </tr>'
                        });
                        $('.orderQueryTableAdd tbody').html(str2+hasStr);
                    }

                }
            }
        });
    }
    //点击右边彩色按钮
    $('.oneRightInfo').on('click',function(){
        if($(this).hasClass('active')) return;
        $('.orderQueryTablePrimary').show();
        $('.mapOuter').addClass('pacityMap');
        var sendArr = [];
        var dataId = $(this).attr('data-id');
        $.each(countryArrayTop,function(){
            var _this = this;
            if(dataId == _this.name){
                sendArr.push( _this.id)
            }
        });
        getTypeMonitor(sendArr,true);
    });
    //右边彩色按钮总计
    $('.oneRightInfoTotal').on('click',function(){
        var sendArr = [];
        $('.orderQueryTablePrimary').show();
        $('.mapOuter').addClass('pacityMap');
        $.each(countryArrayTop,function(){
            var _this = this;
            sendArr.push(_this.id)
        });
        getTypeMonitor(sendArr,true);
    });

    //表格点击查看--医疗争议
    function queryDisputes(id,whichTable){
        var data = {
            id:id
        };
        var url = path.path+"/dynamic/queryDisputes";
        $.ajax({
            type : "get",
            url : url,
            data :data,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    var showInfo = res.dto;
                    $('.orName').html(showInfo.orgName);
                    $('.identifyStatus').val(showInfo.identifyStatus);
                    $('.belongAT').html(showInfo.city+showInfo.county);
                    $('.identifyGradeName').html(showInfo.identifyGradeName);
                    $('#happenTime').val(dateForMate(new Date(showInfo.aroseDate)));
                    $('.identifyTypeName').html(showInfo.identifyTypeName);
                    $('.dengDateT').html(dateForMate(new Date(showInfo.bookedDate)));
                    //$('.newDateT').html(dateForMate(showInfo.bookedDate));//最新修改时间
                    $('.argueRT').val(showInfo.dispute);
                    $('.zrNameT').val(showInfo.chargeName);
                    $('.chargeTitle').val(showInfo.chargeTitle);
                    $('.zrrContact').val(showInfo.chargeMobile);
                    $('.hzNameT').val(showInfo.visitName);
                    $('.hzXzT').val(showInfo.visitStatus);
                    $('.tabThzContact').val(showInfo.visitMobile);
                    $('.tabTqsContact').val(showInfo.kindredMobile);
                    $('.sbrNameT').html(showInfo.userName);
                    $('.scrContact').val(showInfo.userMobile);
                    $('.mercyStatus').val(showInfo.mercyStatus);
                    if(showInfo.justiceStatus == 1){
                        $('.result.n').removeClass("result-n");
                        $(".result-txt.n").text("是");
                    }else{
                        $('.result.n').addClass("result-n");
                        $(".result-txt.n").text("否");
                    }
                    $('#ManagementDate').val(showInfo.mercyData);
                    $('.czFzrT').val(showInfo.mercyUser);
                    $('.czMethod').val(showInfo.mercyMethod);
                    $('.popDetailTable').hide();
                    $('.treatStepTwo').attr('data-id',whichTable);
                    $('.treatStepTwo').show();
                }
            }
        });
    }
    //表格点击查看--传染病
    function queryVisitDtlList(id,whichTable,time,checkArea,detail){
        var sendListOrder = {};
        sendListOrder.list = [];
        var data = {
            dxId:id
        };
        var url = path.path+"/dynamic/newQueryVisitDtlList";
        $.ajax({
            type : "get",
            url : url,
            data :data,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    $('.getTime').html(time).attr('title',time);
                    $('.checkArea').html(checkArea);
                    $('.detailGet').html(detail).attr('title',detail);
                    $('.adress').html(res.dto.address);
                    $('.orgPhone').html(res.dto.orgPhone);
                    $('.doctorName').html(res.dto.doctorName);
                    var titlesClinical = '无';
                    if(res.dto.titlesClinical == '5'){
                        titlesClinical = '初级职称'
                    } else if(res.dto.titlesClinical == '6'){
                        titlesClinical = '中级职称'
                    } else if(res.dto.titlesClinical == '7'){
                        titlesClinical = '高级职称'
                    }
                    $('.titlesClinical').html(titlesClinical);
                    var doctorPhone = '';
                    if(res.dto.doctorPhone == null){
                        doctorPhone = ''
                    }else{
                        doctorPhone = res.dto.doctorPhone
                    }
                    $('.doctorPhone').html(doctorPhone);

                    $('.popDetailTable').hide();
                    $('.orgName').html(res.dto.orgName).attr('title',res.dto.orgName);
                    $('.ordDepName').html(res.dto.ordDepName).attr('title',res.dto.ordDepName);
                    $('.visitDate').html(dateForMate(new Date(res.dto.visitDate))).attr('title',dateForMate(new Date(res.dto.visitDate)));

                    //结束审核字段
                    var obj = {};
                    //患者
                    $('.patientName').html(res.dto.patientName).attr('title',res.dto.patientName);
                    $('.sexName').html(res.dto.sexName).attr('title',res.dto.sexName);
                    $('.age').html(res.dto.age).attr('title',res.dto.age);
                    $('.visitNo').html(res.dto.visitNo).attr('title',res.dto.visitNo);
                    $('.teleNum').html(res.dto.teleNum).attr('title',res.dto.teleNum);
                    $('.addr').html(res.dto.addr).attr('title',res.dto.addr);
                    $('.allergicDrugString').html(res.dto.allergicDrugString).attr('title',res.dto.allergicDrugString);

                    //临床诊断
                    var prescriptionList = res.dto.dxDTOs;
                    var strWest = '';
                    var strChina = '';
                    var str = '';
                    var strW = '';
                    var strC = '';
                    var chinaArray = [];
                    var westAray = [];
                    for(var i = 0;i<prescriptionList.length;i++){
                        if(prescriptionList[i].medicalSystem == '0'){
                            var Detail = '';
                            if(prescriptionList[i].additionNameNew!=''){
                                Detail = '('+prescriptionList[i].additionNameNew+')'
                            }
                            westAray.push(prescriptionList[i].icdNameNew+Detail);
                        }
                        if(prescriptionList[i].medicalSystem == '1'){
                            var Detail = '';
                            if(prescriptionList[i].additionNameNew!=''){
                                Detail = '('+prescriptionList[i].additionNameNew+')'
                            }
                            chinaArray.push(prescriptionList[i].icdNameNew+Detail);
                        }
                    }
                    for(var i = 0;i<chinaArray.length;i++){
                        strChina +=(Number(i)+1)+'.'+chinaArray[i]+';';
                    }
                    for(var i = 0;i<westAray.length;i++){
                        strWest +=(Number(i)+1)+'.'+westAray[i]+';';
                    }
                    var x='西医诊断:';
                    var b='中医诊断:';
                    if(chinaArray.length == 0){
                        b = ''
                    }
                    if(westAray.length == 0){
                        x = ''
                    }
                    str = b+strChina+x+strWest;
                    $('.dxType').html(str).attr('title',str);

                    //处方药品rp
                    var orderDTOs = res.dto.orderDTOs;
                    var strTd = '';
                    obj.vistId=orderDTOs[0].ordId;
                    obj.orderDate= dateForMate(new Date(orderDTOs[0].visitDate));
                    obj.checkParts= checkArea;
                    obj.orgId= res.dto.orgId;
                    obj.orgName= res.dto.orgName;
                    obj.doctorId= res.dto.doctorId;
                    obj.doctorName= res.dto.doctorName;
                    sendListOrder.list.push(obj);
                    if(orderDTOs[0].drugType == '20107-0003'){
                        strTd = '<tr class="prescribe-type"> <th>序号</th> <th>诊疗项目</th> <th>规格</th> <th>数量</th> <th>单位</th></tr>';
                        $.each(orderDTOs[0].orderDtlDTOs,function(i){
                            strTd+= '<tr> <td title="'+(i+1)+'">'+(i+1)+'</td> <td title="'+this.tradeName+'">'+this.tradeName+'</td> <td></td> <td title="'+this.buyQty+'">'+this.buyQty+'</td> <td title="'+this.buyUnitName+'">'+this.buyUnitName+'</td></tr>'
                        });
                        $('.orderDTOs').empty().html(strTd).css('width','96%');
                        $('.orderDTOsR').empty().html(strTd).hide();
                        $('.chinaM').hide();
                    }else if(orderDTOs[0].drugType == '20107-0001'){

                        var objectList = orderDTOs[0].orderDtlDTOs;
                        var newA = objectList.sort(function(a,b){
                            return Number(a.sortNo)-Number(b.sortNo)
                        });
                        newA.sort(function(a,b){
                            return Number(a.groupNo)-Number(b.groupNo)
                        });
                        strTd = '<tr class="prescribe-type"> <th>序号</th> <th style="text-align: left">药品名称</th> <th>规格</th> <th>剂量</th> <th>给药方式</th> <th>频率</th> <th>天数</th> <th>总量</th> <th>备注</th> </tr>'
                        var isGz = false;
                        var a='0px';
                        $.each(newA,function(i){
                            if(this.sortNo != 0){
                                this.groupNo = '';
                            }else{
                                if(this.groupNo == '0'){
                                    isGz = true;
                                }
                                if(isGz == true){
                                    this.groupNo = (Number(this.groupNo)+1);
                                }
                            }
                            if(this.groupNo == ''){
                                a='20px'
                            }else{
                                a='0px'
                            }

                            strTd+='<tr> <td title="'+this.groupNo+'">'+this.groupNo+'</td> <td  style="text-align: left;padding-left: '+a+'" title="'+this.tradeName+'">'+this.tradeName+'</td> <td title="'+this.clinicalItemSpec+'">'+this.clinicalItemSpec+'</td> <td title="'+this.defDoseQty+this.defDoseUnitName+'/'+this.dosage+this.dosageUnitName+'">'+this.defDoseQty+this.defDoseUnitName+'/'+this.dosage+this.dosageUnitName+'</td> <td title="'+this.routeName+'">'+this.routeName+'</td> <td title="'+this.freqName+'">'+this.freqName+'</td> <td title="'+this.days+'天">'+this.days+'天</td> <td title="'+this.buyQty+this.buyUnitName+'">'+this.buyQty+this.buyUnitName+'</td> <td title="'+this.orderMemo+'">'+this.orderMemo+'</td> </tr>'
                        });
                        $('.orderDTOs').empty().html(strTd).css('width','96%');
                        $('.orderDTOsR').empty().html(strTd).hide();
                        $('.chinaM').hide();
                    }
                    else if(orderDTOs[0].drugType == '20107-0002'){
                        strTd = ' <tr class="prescribe-type"> <th>序号</th> <th>药品名称</th><th>总量</th><th>给药方式</th> </tr>';
                        var strTdR = '<tr class="prescribe-type"> <th>序号</th> <th>药品名称</th><th>总量</th><th>给药方式</th> </tr>';
                        $.each(orderDTOs[0].orderDtlDTOs,function(i){
                            if(i%2==0){
                                strTdR+='<tr> <td title="'+(i+1)+'">'+(i+1)+'</td> <td title="'+this.tradeName+'">'+this.tradeName+'</td> <td title="'+this.buyQty+this.buyUnitName+'">'+this.buyQty+this.buyUnitName+'</td><td title="'+this.routeName+'">'+this.routeName+'</td> </tr>'
                            }else{
                                strTd+='<tr> <td title="'+(i+1)+'">'+(i+1)+'</td> <td title="'+this.tradeName+'">'+this.tradeName+'</td> <td title="'+this.buyQty+this.buyUnitName+'">'+this.buyQty+this.buyUnitName+'</td><td title="'+this.routeName+'">'+this.routeName+'</td> </tr>'
                            }
                        });
                        $('.chinaM .repetition').val(orderDTOs[0].repetition);
                        $('.chinaM .orderMemo').val(orderDTOs[0].orderMemo);
                        $('.orderDTOs').empty().html(strTdR).css('width','45%');
                        $('.orderDTOsR').empty().html(strTd).show().css('width','45%');
                        $('.chinaM').show();
                    }


                    //电子病历
                    var csOutpEmrDTO = res.dto.csOutpEmrDTO;
                    if(csOutpEmrDTO!=null){
                        $('.chiefComplaint').html(csOutpEmrDTO.chiefComplaint).attr('title',csOutpEmrDTO.chiefComplaint);
                        $('.presentIllness').html(csOutpEmrDTO.presentIllness).attr('title',csOutpEmrDTO.presentIllness);
                        $('.pastIllness').html(csOutpEmrDTO.pastIllness).attr('title',csOutpEmrDTO.pastIllness);
                        $('.familyIllness').html(csOutpEmrDTO.familyIllness).attr('title',csOutpEmrDTO.familyIllness);
                        $('.personalIllness').html(csOutpEmrDTO.personalIllness).attr('title',csOutpEmrDTO.personalIllness);
                        $('.allergicHistory').html(csOutpEmrDTO.allergicHistory).attr('title',csOutpEmrDTO.allergicHistory);
                        $('.bodyExam').html(csOutpEmrDTO.bodyExam).attr('title',csOutpEmrDTO.bodyExam);
                        $('.assistExam').html(csOutpEmrDTO.assistExam).attr('title',csOutpEmrDTO.assistExam);
                        $('.diagnoses').html(csOutpEmrDTO.diagnoses).attr('title',csOutpEmrDTO.diagnoses);
                        $('.suggestion').html(csOutpEmrDTO.suggestion).attr('title',csOutpEmrDTO.suggestion);
                        $('.treatSuggetion').html(csOutpEmrDTO.treatSuggetion).attr('title',csOutpEmrDTO.treatSuggetion);
                    }else {
                        $('.chiefComplaint').html('');
                        $('.presentIllness').html('');
                        $('.pastIllness').html('');
                        $('.familyIllness').html('');
                        $('.personalIllness').html('');
                        $('.allergicHistory').html(res.dto.allergicDrugString).attr('title', res.dto.allergicDrugString);
                        $('.bodyExam').html('');
                        $('.assistExam').html('');
                        $('.diagnoses').html(str);
                        $('.suggestion').html('');
                        $('.treatSuggetion').html('');
                    }

                    $('.details-box.case').show().siblings('.sign').hide();
                    $('.medicalRecord').addClass('active').siblings('span').removeClass('active');
                    $('.content-box').attr('data-id', whichTable);
                    $('.content-box').show()
                }
            }
        });
    }
    //表格点击查看--抗生素
    function queryOrder(id,whichTable,time,checkArea,detail){
        var sendListOrder = {};
        sendListOrder.list = [];
        var data = {
            orderId:id
        };
        var url = path.path+"/dynamic/queryOrder";
        $.ajax({
            type : "get",
            url : url,
            data :data,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    $('.getTime').html(time);
                    $('.checkArea').html(checkArea);
                    $('.detailGet').html(detail).attr('title',detail);
                    $('.adress').html(res.dto.address);
                    $('.orgPhone').html(res.dto.orgPhone);
                    $('.doctorName').html(res.dto.doctorName);
                    var titlesClinical = '无';
                    if(res.dto.titlesClinical == '5'){
                        titlesClinical = '初级职称'
                    } else if(res.dto.titlesClinical == '6'){
                        titlesClinical = '中级职称'
                    } else if(res.dto.titlesClinical == '7'){
                        titlesClinical = '高级职称'
                    }
                    $('.titlesClinical').html(titlesClinical);
                    var doctorPhone = '';
                    if(res.dto.doctorPhone == null){
                        doctorPhone = ''
                    }else{
                        doctorPhone = res.dto.doctorPhone
                    }
                    $('.doctorPhone').html(doctorPhone);

                    $('.popDetailTable').hide();
                    $('.orgName').html(res.dto.orgName).attr('title',res.dto.orgName);
                    $('.ordDepName').html(res.dto.ordDepName).attr('title',res.dto.ordDepName);
                    $('.visitDate').html(dateForMate(new Date(res.dto.visitDate))).attr('title',dateForMate(new Date(res.dto.visitDate)));

                    //结束审核字段
                    var obj = {};
                    //患者
                    $('.patientName').html(res.dto.patientName).attr('title',res.dto.patientName);
                    $('.sexName').html(res.dto.sexName).attr('title',res.dto.sexName);
                    $('.age').html(res.dto.age).attr('title',res.dto.age);
                    $('.visitNo').html(res.dto.visitNo).attr('title',res.dto.visitNo);
                    $('.teleNum').html(res.dto.teleNum).attr('title',res.dto.teleNum);
                    $('.addr').html(res.dto.addr).attr('title',res.dto.addr);
                    $('.allergicDrugString').html(res.dto.allergicDrugString).attr('title',res.dto.allergicDrugString);

                    //临床诊断
                    var prescriptionList = res.dto.dxDTOs;
                    var strWest = '';
                    var strChina = '';
                    var str = '';
                    var strW = '';
                    var strC = '';
                    var chinaArray = [];
                    var westAray = [];
                    for(var i = 0;i<prescriptionList.length;i++){
                        if(prescriptionList[i].medicalSystem == '0'){
                            var Detail = '';
                            if(prescriptionList[i].additionNameNew!=''){
                                Detail = '('+prescriptionList[i].additionNameNew+')'
                            }
                            westAray.push(prescriptionList[i].icdNameNew+Detail);
                        }
                        if(prescriptionList[i].medicalSystem == '1'){
                            var Detail = '';
                            if(prescriptionList[i].additionNameNew!=''){
                                Detail = '('+prescriptionList[i].additionNameNew+')'
                            }
                            chinaArray.push(prescriptionList[i].icdNameNew+Detail);
                        }
                    }
                    for(var i = 0;i<chinaArray.length;i++){
                        strChina +=(Number(i)+1)+'.'+chinaArray[i]+';';
                    }
                    for(var i = 0;i<westAray.length;i++){
                        strWest +=(Number(i)+1)+'.'+westAray[i]+';';
                    }
                    var x='西医诊断:';
                    var b='中医诊断:';
                    if(chinaArray.length == 0){
                        b = ''
                    }
                    if(westAray.length == 0){
                        x = ''
                    }
                    str = b+strChina+x+strWest;
                    $('.dxType').html(str).attr('title',str);

                    //处方药品rp
                    var orderDTOs = res.dto.orderDTOs;
                    var strTd = '';
                    obj.vistId=orderDTOs[0].ordId;
                    obj.orderDate= dateForMate(new Date(orderDTOs[0].visitDate));
                    obj.checkParts= checkArea;
                    obj.orgId= res.dto.orgId;
                    obj.orgName= res.dto.orgName;
                    obj.doctorId= res.dto.doctorId;
                    obj.doctorName= res.dto.doctorName;
                    sendListOrder.list.push(obj);
                    if(orderDTOs[0].drugType == '20107-0003'){
                        strTd = '<tr class="prescribe-type"> <th>序号</th> <th>诊疗项目</th> <th>规格</th> <th>数量</th> <th>单位</th></tr>';
                        $.each(orderDTOs[0].orderDtlDTOs,function(i){
                            strTd+= '<tr> <td title="'+(i+1)+'">'+(i+1)+'</td> <td title="'+this.tradeName+'">'+this.tradeName+'</td> <td></td> <td title="'+this.buyQty+'">'+this.buyQty+'</td> <td title="'+this.buyUnitName+'">'+this.buyUnitName+'</td></tr>'
                        });
                        $('.orderDTOs').empty().html(strTd).css('width','96%');
                        $('.orderDTOsR').empty().html(strTd).hide();
                        $('.chinaM').hide();
                    }else if(orderDTOs[0].drugType == '20107-0001'){

                        var objectList = orderDTOs[0].orderDtlDTOs;
                        var newA = objectList.sort(function(a,b){
                            return Number(a.sortNo)-Number(b.sortNo)
                        });
                        newA.sort(function(a,b){
                            return Number(a.groupNo)-Number(b.groupNo)
                        });
                        strTd = '<tr class="prescribe-type"> <th>序号</th> <th style="text-align: left">药品名称</th> <th>规格</th> <th>剂量</th> <th>给药方式</th> <th>频率</th> <th>天数</th> <th>总量</th> <th>备注</th> </tr>'
                        var isGz = false;
                        var a='0px';
                        $.each(newA,function(i){
                            if(this.sortNo != 0){
                                this.groupNo = '';
                            }else{
                                if(this.groupNo == '0'){
                                    isGz = true;
                                }
                                if(isGz == true){
                                    this.groupNo = (Number(this.groupNo)+1);
                                }
                            }
                            if(this.groupNo == ''){
                                a='20px'
                            }else{
                                a='0px'
                            }

                            strTd+='<tr> <td title="'+this.groupNo+'">'+this.groupNo+'</td> <td  style="text-align: left;padding-left: '+a+'" title="'+this.tradeName+'">'+this.tradeName+'</td> <td title="'+this.clinicalItemSpec+'">'+this.clinicalItemSpec+'</td> <td title="'+this.defDoseQty+this.defDoseUnitName+'/'+this.dosage+this.dosageUnitName+'">'+this.defDoseQty+this.defDoseUnitName+'/'+this.dosage+this.dosageUnitName+'</td> <td title="'+this.routeName+'">'+this.routeName+'</td> <td title="'+this.freqName+'">'+this.freqName+'</td> <td title="'+this.days+'天">'+this.days+'天</td> <td title="'+this.buyQty+this.buyUnitName+'">'+this.buyQty+this.buyUnitName+'</td> <td title="'+this.orderMemo+'">'+this.orderMemo+'</td> </tr>'
                        });
                        $('.orderDTOs').empty().html(strTd).css('width','96%');
                        $('.orderDTOsR').empty().html(strTd).hide();
                        $('.chinaM').hide();
                    }
                    else if(orderDTOs[0].drugType == '20107-0002'){
                        strTd = ' <tr class="prescribe-type"> <th>序号</th> <th>药品名称</th><th>总量</th><th>给药方式</th> </tr>';
                        var strTdR = '<tr class="prescribe-type"> <th>序号</th> <th>药品名称</th><th>总量</th><th>给药方式</th> </tr>';
                        $.each(orderDTOs[0].orderDtlDTOs,function(i){
                            if(i%2==0){
                                strTdR+='<tr> <td title="'+(i+1)+'">'+(i+1)+'</td> <td title="'+this.tradeName+'">'+this.tradeName+'</td> <td title="'+this.buyQty+this.buyUnitName+'">'+this.buyQty+this.buyUnitName+'</td><td title="'+this.routeName+'">'+this.routeName+'</td> </tr>'
                            }else{
                                strTd+='<tr> <td title="'+(i+1)+'">'+(i+1)+'</td> <td title="'+this.tradeName+'">'+this.tradeName+'</td> <td title="'+this.buyQty+this.buyUnitName+'">'+this.buyQty+this.buyUnitName+'</td><td title="'+this.routeName+'">'+this.routeName+'</td> </tr>'
                            }
                        });
                        $('.chinaM .repetition').val(orderDTOs[0].repetition);
                        $('.chinaM .orderMemo').val(orderDTOs[0].orderMemo);
                        $('.orderDTOs').empty().html(strTdR).css('width','45%');
                        $('.orderDTOsR').empty().html(strTd).show().css('width','45%');
                        $('.chinaM').show();
                    }


                    //电子病历
                    var csOutpEmrDTO = res.dto.csOutpEmrDTO;
                    if(csOutpEmrDTO!=null){
                        $('.chiefComplaint').html(csOutpEmrDTO.chiefComplaint).attr('title',csOutpEmrDTO.chiefComplaint);
                        $('.presentIllness').html(csOutpEmrDTO.presentIllness).attr('title',csOutpEmrDTO.presentIllness);
                        $('.pastIllness').html(csOutpEmrDTO.pastIllness).attr('title',csOutpEmrDTO.pastIllness);
                        $('.familyIllness').html(csOutpEmrDTO.familyIllness).attr('title',csOutpEmrDTO.familyIllness);
                        $('.personalIllness').html(csOutpEmrDTO.personalIllness).attr('title',csOutpEmrDTO.personalIllness);
                        $('.allergicHistory').html(csOutpEmrDTO.allergicHistory).attr('title',csOutpEmrDTO.allergicHistory);
                        $('.bodyExam').html(csOutpEmrDTO.bodyExam).attr('title',csOutpEmrDTO.bodyExam);
                        $('.assistExam').html(csOutpEmrDTO.assistExam).attr('title',csOutpEmrDTO.assistExam);
                        $('.diagnoses').html(csOutpEmrDTO.diagnoses).attr('title',csOutpEmrDTO.diagnoses);
                        $('.suggestion').html(csOutpEmrDTO.suggestion).attr('title',csOutpEmrDTO.suggestion);
                        $('.treatSuggetion').html(csOutpEmrDTO.treatSuggetion).attr('title',csOutpEmrDTO.treatSuggetion);
                    }else{
                        $('.chiefComplaint').html('');
                        $('.presentIllness').html('');
                        $('.pastIllness').html('');
                        $('.familyIllness').html('');
                        $('.personalIllness').html('');
                        $('.allergicHistory').html(res.dto.allergicDrugString).attr('title',res.dto.allergicDrugString);
                        $('.bodyExam').html('');
                        $('.assistExam').html('');
                        $('.diagnoses').html(str);
                        $('.suggestion').html('');
                        $('.treatSuggetion').html('');
                    }
                    $('.details-box.sign').show().siblings('.case').hide();
                    $('.medicalRecord').removeClass('active').siblings('span').addClass('active');
                    $('.content-box').attr('data-id', whichTable);
                    $('.content-box').show()

                }
            }
        });
    }

    //表格点击查看事件
    $(document).on('click','.orderChooseNo',function(){
        var type = $(this).siblings('.type').html();
        var id = $(this).parents('tr').attr('data-id');
        var time = $(this).siblings('.time').html();
        var detail = $(this).siblings('.detail').html();
        var whichTable = false;
        if($(this).parents('.popDetailTable').hasClass('orderQueryTablePrimary')){
            whichTable = 'primary';
        }else if($(this).parents('.popDetailTable').hasClass('orderQueryTableAdd')){
            whichTable = 'add';
        }
        if(type == '医疗争议'){
            queryDisputes(id,whichTable)
        }
        var checkArea = $(this).siblings('.checkArea').html();
        if(type == '抗生素'){
            queryOrder(id,whichTable,time,checkArea,detail);
        }
        if(type == '传染病'){
            queryVisitDtlList(id,whichTable,time,checkArea,detail);
        }
    });


    //历史记录
    //历史记录-请求地址
    function queryAdress(){
        var url = path.path+"/dynamic/queryAdress";
        $.ajax({
            type : "get",
            url : url,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    $('.provenceOrder').val(res.dto.province);
                    $('.cityOrder').val(res.dto.city);
                    $('.areaOrder').val(res.dto.county);
                    var str = '';
                    $.each(res.list,function(){
                        str += '<option value="'+this.id+'">'+this.name+'</option>'
                    });
                    $('.countyOrder').empty().append(str);
                }
            }
        });
    }
    queryAdress();
    //历史点击查询条件
    $('.preConditionQuery').on('click',function(){
        queryHistory()
    });
    function queryHistory(){
        var type = $('.chooseType').val();
        var name = $('.orderName').val();
        var date = $('#dateBegin').val();
        var addressId = $('.countyOrder').val();
        var data = {
            date:date,
            type:type,
            name:name,
            addressId:addressId
        };
        var url = path.path+"/dynamic/queryHistory";
        $.ajax({
            type : "get",
            url : url,
            data:data,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    var str = '';
                    if(res.list!=null){
                        $.each(res.list,function(){
                            if(this.orgName == null){this.orgName ='无';}
                            if(this.addressDtl == null){this.addressDtl ='无';}
                            if(this.ebentDtl == null){this.ebentDtl ='无';}
                            str+='<tr data-id="'+this.typeId+'"> <td class="time" title="'+this.date.split('.')[0]+'">'+this.date.split('.')[0]+'</td> <td class="checkArea" title="'+this.address+'">'+this.address+'</td> <td class="type" title="'+this.type+'">'+this.type+'</td> <td title="'+this.orgName+'">'+this.orgName+'</td> <td title="'+this.addressDtl+'">'+this.addressDtl+'</td> <td class="detail" title="'+this.ebentDtl+'">'+this.ebentDtl+'</td> <td class="orderChooseNo">查看</td> </tr>'
                        });
                    }
                    $('.orderQueryTablePrimary tbody').empty().append(str);
                    $('.orderQueryTablePrimary').show();
                    $('.content-box').hide();
                    $('.treatStepTwo').hide();
                }
            }
        });
    }
    
    
    //区域显示列表三级菜单管控
    $(".mayor-name").click(function(){
        if($(this).next('ul').css('display')=='none'){
            $(this).next('ul').slideDown(100);
            $(this).removeClass("show-off").addClass("show-on");
        }else{
            $(this).next('ul').slideUp(100);
            $(this).removeClass("show-on").addClass("show-off");
        }
    });
    
    //搜索按钮点击事件
    $("#searchBtn").click(function(){
    	setPosition($("#startTime").val(),$("#endTime").val());
    });
    
    //获取诊所的列表信息
    (function(){
    	var url = path.path+"/dynamic/queryAdress";
        $.ajax({
            type : "get",
            url : url,
            cache : false,
            async : true,
            success : function(res){
            	if(res.code == '000000'){                 
                    //填充区域列表
                    $("#areaName").html(borderArea);
                    if(res.list&&res.list.length){
                    	var info=res.list;
                    	for(var i=0; i<info.length; i++){
                    		$("#clinicsList").append($('<li class="clinics-area" isShow="no">'+'<p class="clinics-area-name show-off" infoId="'+info[i].id+'">'+info[i].name+'</p>'+
                    		'</li>'))
                    	}
                    }
                    //给乡镇菜单加点击事件
                    $("#clinicsList>li>p").bind("click",function(){
                    	if($(this).attr("infoId")&&$(this).parent("li").attr("isShow")=="no"){
                    		getAreaInfo($(this).parent("li"),$(this).attr("infoId"));
                    	}else{
                    		if($(this).next('ul').children().length>0){
                    			if($(this).next('ul').css('display')=='none'){
                        			$(this).next('ul').slideDown(100);
                        			$(this).removeClass("show-off").addClass("show-on");
                        		}else{
                        			$(this).next('ul').slideUp(100);
                        			$(this).removeClass("show-on").addClass("show-off");
                        		}
                    		}else{
                    			if($(this).hasClass("show-on")){
                    				$(this).removeClass("show-on").addClass("show-off");
                    			}else{
                    				$(this).removeClass("show-off").addClass("show-on");
                    			}
                    		}
                    	}
                    })
            	}else{
            		alert('未能获取当前行政区域信息');
            	}
            }
        });
    })();
    
    //获取乡镇下面的诊所列表
    function getAreaInfo(obj,infoId){
    	var url = path.path+"/dynamic/queryOrgByCode";
    	var data={
    		addressCode:infoId
    	}
        $.ajax({
            type : "get",
            url : url,
            data: data,
            cache : false,
            async : true,
            success : function(res){
            	if(res.code == '000000'){
            		if(res.orgList&&res.orgList.length>0){
            			var info=res.orgList;
            			var ulNode=$('<ul class="clinics-area-list" style="display: block"></ul>');
            			for(var i=0; i<info.length; i++){
            				ulNode.append($('<li latitude='+info[i].latitude+'; longitude='+info[i].longitude+'; orgId='+info[i].id+'>'+info[i].name+'</li>'))
            			}
            			obj.append(ulNode);
            			ulNode.children("li").bind("click",function(){
            				setPosition($("#startTime").val(),$("#endTime").val(),$(this).attr("orgid"));
            			})
            			obj.attr("isShow","yes");
            			obj.children("p").removeClass("show-off").addClass("show-on");
            		}else{
            			obj.attr("isShow","yes");
            			obj.children("p").removeClass("show-off").addClass("show-on");
            		}
            	}else{
            		alert("获取当前区域诊所信息失败！");
            	}
            }
        });
    };
    
    //诊所初始化定位
    var markerArr=[];
    function setPosition(startTime,endTime,orgId){
    	var url = path.path+"/dynamic/queryClinicsInMap";
    	if(orgId&&startTime&&endTime){
    		var data={
	    		orgId: orgId,
	    		startTime: startTime,
	    		endTime: endTime
	    	}
    	}else if(startTime&&endTime){
    		var data={
	    		orgId: "",
	    		startTime: startTime,
	    		endTime: endTime
	    	}
    	}else{
    		var data={
	    		orgId: "",
	    		startTime: "",
	    		endTime: ""
	    	}
    	}
    	
        $.ajax({
            type : "get",
            url : url,
            data: data,
            cache : false,
            async : true,
            success : function(res){
            	if(!$("#startTime").val()){
            		$("#startTime").val(res.currYearFirst);
            	}
            	if(!$("#endTime").val()){
            		$("#endTime").val(res.nowDate);
            	}
            	
            	var pointArr=[];
            	if(res.code=="000000"){
            		if(res.cmsOrgInfoDTOs&&res.cmsOrgInfoDTOs.length>0){
            			if(markerArr.length>0){
            				for(var j=0; j<markerArr.length; j++){
            					map.removeOverlay(markerArr[j]);
            				}
            				markerArr=[];
            			}
                        // markerArr=[];
                		var info=res.cmsOrgInfoDTOs;
                		for(var i=0; i<info.length; i++){
                			
                            if(info[i].longitude != 0) {
                                // 编写自定义函数,创建标注
                                var point = new BMap.Point(info[i].longitude, info[i].latitude);
                                // var point = new BMap.Point(103.40, 23.37);
                                pointArr[i]=point;
                                if(info[i].patrolStatus==1){
                                    if(info[i].expireStatus==2){
                                        var myIcon = new BMap.Icon("images/xunblue.png", new BMap.Size(35,50));
                                    }else if(info[i].expireStatus==1){
                                        var myIcon = new BMap.Icon("images/xunred.png", new BMap.Size(35,50));
                                    }else{
                                        var myIcon = new BMap.Icon("images/xungreen.png", new BMap.Size(35,50));
                                    }
                                }else{
                                    if(info[i].expireStatus==2){
                                        var myIcon = new BMap.Icon("images/blue.png", new BMap.Size(25,35));
                                    }else if(info[i].expireStatus==1){
                                        var myIcon = new BMap.Icon("images/red.png", new BMap.Size(25,35));
                                    }else{
                                        var myIcon = new BMap.Icon("images/green.png", new BMap.Size(25,35));
                                    }
                                }
                                var marker = new BMap.Marker(point,{icon:myIcon});
                                marker.orgId=info[i].orgId;
                                marker.longitude=info[i].longitude;
                                marker.latitude=info[i].latitude;
                                marker.orgName=info[i].orgName;
                                marker.addEventListener("click",attribute);
                                
                                marker.addEventListener("mouseover", tishi);
                                marker.addEventListener("mouseout", tishi);
                                markerArr.push(marker);
                                map.addOverlay(marker);                                
                            }

                		}
                		// map.setViewport(pointArr);
            		}else{
            			layer.alert("该地区没有诊所");
            		}
            	}else{
            		layer.alert("诊所信息初始化失败");
            	}
            }
        });
    }
    
    setPosition();
    
    
    // 复杂的自定义覆盖物
    function ComplexCustomOverlay(point, text){
      this._point = point;
      this._text = text;
    }
    ComplexCustomOverlay.prototype = new BMap.Overlay();
    ComplexCustomOverlay.prototype.initialize = function(map){
      this._map = map;
      var div = this._div = document.createElement("div");
      div.style.position = "absolute";
      div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
      div.style.backgroundColor = "#EE5D5B";
      div.style.border = "1px solid #BC3B3A";
      div.style.color = "white";
      div.style.height = "18px";
      div.style.padding = "2px";
      div.style.lineHeight = "18px";
      div.style.whiteSpace = "nowrap";
      div.style.MozUserSelect = "none";
      div.style.fontSize = "12px"
      var span = this._span = document.createElement("span");
      div.appendChild(span);
      span.appendChild(document.createTextNode(this._text));      
      var that = this;

      var arrow = this._arrow = document.createElement("div");
      arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
      arrow.style.position = "absolute";
      arrow.style.width = "11px";
      arrow.style.height = "10px";
      arrow.style.top = "22px";
      arrow.style.left = "10px";
      arrow.style.overflow = "hidden";
      div.appendChild(arrow);

      map.getPanes().labelPane.appendChild(div);
      
      return div;
    }
    ComplexCustomOverlay.prototype.draw = function(){
      var map = this._map;
      var pixel = map.pointToOverlayPixel(this._point);
      this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
      this._div.style.top  = pixel.y - 30 + "px";
    }
    
    
    
    //鼠标悬浮在marker上的提示事件
    function tishi(e){
    	var e=e||window.event;
    	var p = e.target||e.srcElement;
    	if(e.type=="onmouseover"){
    		p.myCompOverlay = new ComplexCustomOverlay(new BMap.Point(p.longitude, p.latitude), p.orgName);
            map.addOverlay(p.myCompOverlay);
    	}else if(e.type=="onmouseout"){
    		map.removeOverlay(p.myCompOverlay);
    	}
    	
    	
    }
    
    //点击地图上图标响应函数
    function attribute(e){
    	var p = e.target||e.srcElement; 
    	var url = path.path+"/dynamic/queryClinicsInfo";
		var data={
			orgId:p.orgId,
			startTime:$("#startTime").val(),
			endTime:$("#endTime").val()
		}
		$.ajax({
            type : "get",
            url : url,
            data: data,
            cache : false,
            async : true,
            success : function(res){
            	if(res.code=="000000"){
            		$(".is-search").css("display","none");
            		var info=res.cmsOrgInfoDTO;
            		$("#clinicsLinkman").html(info.linkMan);
            		$("#clinicsName").html(info.orgName);
            		$("#clinicsPhone").html(info.telephone);
                    $("#clinicsAddress").html(info.address);
            		$("#clinicsNo").html(info.orgCredentialsNo);
            		$("#orgApply").html(info.orgCredentialsApply);
            		$("#orgTime").html(info.orgCredentialsTime);
            		var station="";
            		if(info.expireStatus==0){
            			station="正常";
            		}else if(info.expireStatus==1){
            			station="已到期";
            		}else if(info.expireStatus==2){
            			station="即将到期";
            		}
            		$("#timeStation").html(station);
            		var patrol="";
            		if(info.patrolStatus==0){
            			patrol="未巡查";
            			$("#isPatrol").html(patrol);
            		}else if(info.patrolStatus==1){
            			patrol="已巡查";
            			$("#isPatrol").html(patrol);
            			$("#latestTime").html(info.lastPatrolTime);
            			$("#lastTime").html(info.latelyPatrolTime);
            			$(".is-search").css("display","block");
            		}
            		$("#isPatrol").attr("orgId",p.orgId)
            		
            		$("#clinicsDetail").css({"display": "block"});
                    startDrag('clinicsDetail','clinicsDetail')
            	}else{
            		alert("该诊所详细信息获取失败");
            	}	


            }
        });
	}	
    
    $("#closeClinicsDetail").click(function(){
    	$("#clinicsDetail").css({"display": "none"});
    })
    
    //录入巡查信息
    $("#isPatrol").click(function(){
    	$("#clinicsEntering").attr("orgId",$(this).attr("orgId"))
    	if($(this).html()=="未巡查"){
    		$("#enteringName").html($("#clinicsName").html());
    		$("#leadingMan").val($("#clinicsLinkman").html());
    		$("#clinicAddr").val($("#clinicsAddress").html());
    		$("#clinicsEntering").css({"display":"block"});
    	}
    })
    
    //录入信息的关闭按钮
    $(".cancel-btn").click(function(){
    	$("#clinicsEntering").css({"display": "none"});
    	$("#searchingMan").val("");
    	$("#searchingLeader").val("");
    	$("#searchingTime").val("");
    	$("#searchingPlan").val("");
    	$("#searchingResult").val("");
    })
    
    //保存录入信息
    $(".save-btn").on("click",saveInfo)
    
    function saveInfo(){
    	if($("#searchingMan").val().trim()&&$("#searchingLeader").val().trim()&&$("#searchingTime").val().trim()&&$("#searchingPlan").val().trim()&&$("#searchingResult").val().trim()){
    		$(".save-btn").off("click",saveInfo);
        	$(".save-btn").html("保存中...");
        	var url = path.path+"/dynamic/addPatrolInfo";
    		var data={
    			orgId:$("#clinicsEntering").attr("orgId"),
    			address:$("#clinicAddr").val(),
    			orgCredentialsPerson:$("#leadingMan").val(),
    			patrolMan:$("#searchingMan").val(),
    			patrolOfficer:$("#searchingLeader").val(),
    			patrolTime:$("#searchingTime").val(),
    			planTime:$("#searchingPlan").val(),
    			patrolResult:$("#searchingResult").val()
    		}
    		$.ajax({
                type : "post",
                url : url,
                data: data,
                cache : false,
                async : true,
                success : function(res){
                	if(res.code=="000000"){
                		$("#clinicsEntering").css("display","none");
                		$("#clinicsDetail").css("display","none");
                		$(".save-btn").html("保存");
                		$(".save-btn").on("click",saveInfo);
                		layer.alert('保存成功', {
            			  skin: 'layui-layer-lan' //样式类名
            			  ,closeBtn: 0
            			});
                		$("#searchingMan").val("");
                    	$("#searchingLeader").val("");
                    	$("#searchingTime").val("");
                    	$("#searchingPlan").val("");
                    	$("#searchingResult").val("");
                		setPosition($("#startTime").val(),$("#endTime").val());
                	}else{
                		$(".save-btn").html("保存");
                		$(".save-btn").on("click",saveInfo);
                		layer.alert('保存失败', {
              			  skin: 'layui-layer-lan' //样式类名
              			  ,closeBtn: 0
              			});
                		$("#searchingMan").val("");
                    	$("#searchingLeader").val("");
                    	$("#searchingTime").val("");
                    	$("#searchingPlan").val("");
                    	$("#searchingResult").val("");
                	}	
                }
            });
    	}else{
    		layer.alert('信息填写不完整', {
			  skin: 'layui-layer-lan' //样式类名
			  ,closeBtn: 0
			});
    	}
    	
    }
    var datepickerObj00 = {
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
    var datepickerObj01 = {
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
        minDate:new Date()
    };
    $("#searchingTime").datetimepicker(datepickerObj00);
    $("#searchingPlan").datetimepicker(datepickerObj01);
    
    
});
//点击显示和隐藏
$(".effect").click(function(){
  $(".area-time,.area-list").toggle(300);
});
$(".description").click(function(){
  $(".state").toggle(300);
});



/**
 * 纯js实现多div拖拽
 * @param bar, 拖拽触柄
 * @param target, 可拖动窗口
 * @param inWindow, 为true时只能在屏幕范围内拖拽
 * @param callback, 拖拽时执行的回调函数。包含两个参数，target的left和top
 * @returns {*}
 * @private
 */
var startDrag = function(bar, target, /* optional */inWindow, /* optional */callback) {
    (function(bar, target, callback) {
        var D = document,
            DB = document.body,
            params = {
                left: 0,
                top: 0,
                currentX: 0,
                currentY: 0
            };
        if(typeof bar == "string") {
            bar = D.getElementById(bar);
        }
        if(typeof target == "string") {
            target = D.getElementById(target);
        }
        bar.style.cursor="move";
        bindHandler(bar, "mousedown", function(e) {
            e.preventDefault();
            params.left = target.offsetLeft;
            params.top = target.offsetTop;
            if(!e){
                e = window.event;
                bar.onselectstart = function(){
                    return false;
                }
            }
            params.currentX = e.clientX;
            params.currentY = e.clientY;
            
            var stopDrag = function() {
                removeHandler(DB, "mousemove", beginDrag);
                removeHandler(DB, "mouseup", stopDrag);
            }, beginDrag = function(e) {
                var evt = e ? e: window.event,
                    nowX = evt.clientX, nowY = evt.clientY,
                    disX = nowX - params.currentX, disY = nowY - params.currentY,
                    left = parseInt(params.left) + disX,
                    top = parseInt(params.top) + disY;
                if(inWindow) {
                    var maxTop = DB.offsetHeight - target.offsetHeight,
                        maxLeft = DB.offsetWidth - target.offsetWidth;
                    if(top < 0) top = 0;
                    if(top > maxTop) top = maxTop;
                    if(left < 0) left = 0;
                    if(left > maxLeft) left = maxLeft;
                }
                target.style.left = left + "px";
                target.style.top = top + "px";

                if (typeof callback == "function") {
                    callback(left, top);
                }
            };
            
            bindHandler(DB, "mouseup", stopDrag);
            bindHandler(DB, "mousemove", beginDrag);
        });
        
        function bindHandler(elem, type, handler) {
            if (window.addEventListener) {
                //false表示在冒泡阶段调用事件处理程序
                elem.addEventListener(type, handler, false);
            } else if (window.attachEvent) {
                // IE浏览器
                elem.attachEvent("on" + type, handler);
            }
        }

        function removeHandler(elem, type, handler) {
            // 标准浏览器
            if (window.removeEventListener) {
                elem.removeEventListener(type, handler, false);
            } else if (window.detachEvent) {
                // IE浏览器
                elem.detachEvent("on" + type, handler);
            }
        }
        
    })(bar, target, inWindow, callback);
};