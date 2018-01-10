/**
 * Created by hw on 2016/9/29.
 */
$(function() {
    function dateFormat(date){//格式化日期2010-09-09
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
    function closeLayer(){
        layer.closeAll();
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
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: true
    };
    //初始化
    var sendListOrder = {};
    sendListOrder.list = [];
    function loadOnce(){
        getAreaOut();
        getDict();
        //日期初始化
        newBTime();
    }
    loadOnce();
    //初始化日期
    function newBTime(){
        $( "#dateBegin" ).val(dateFormat(new Date()));
        $( "#dateEnd" ).val(dateFormat(new Date()));
        $( "#dateBeginR" ).val(dateFormat(new Date()));
        $( "#dateEndR" ).val(dateFormat(new Date()));
    }
    //处方数量最小为0
    $(document).on('keyup','.orderNumber',function(){
        if(isNaN($('.orderNumber').val())){
            $('.orderNumber').val('1')
        }else{
            if(Number($('.orderNumber').val())<1){
                $('.orderNumber').val('1')
            }
            if($('.orderNumber').val().split('.').length>1){
                $('.orderNumber').val('1')
            }
            if($('.orderNumber').val().length>1){
                if($('.orderNumber').val()[0]=='-'||$('.orderNumber').val()[0]=='0'){
                    $('.orderNumber').val('1')
                }
            }
        }
    });

    //比较日期大小
    function shareTime(a,b){
        if(a>b){
            layer.alert('结束时间不能小于开始时间！');
            newBTime()
        }
    }
    //日期转毫秒数
    function getDTime(a){
        return new Date(a).getTime();
    }
    $(document).on('change','#dateBeginR',function(){
        shareTime(getDTime($('#dateBeginR').val()),getDTime($('#dateEndR').val()));
    });
    $(document).on('change','#dateEndR',function(){
        shareTime(getDTime($('#dateBeginR').val()),getDTime($('#dateEndR').val()));
    });
    $(document).on('change','#dateBegin',function(){
        shareTime(getDTime($('#dateBegin').val()),getDTime($('#dateEnd').val()));
    });
    $(document).on('change','#dateEnd',function(){
        shareTime(getDTime($('#dateBegin').val()),getDTime($('#dateEnd').val()));
    });

    //tab切换
    $('.preSamTabHeader li').on('click',function(){
        $(this).addClass('active').siblings('li').removeClass('active');
        if($(this).hasClass('preSamOrderCheck')){
           $('#preSamOrderCheck').show();
           $('#preSamOrderRecord').hide();
        }else{
            $('#preSamOrderCheck').hide();
            $('#preSamOrderRecord').show();
        }
    });
//============================================ 处方抽查==============================================================
    //查询地区，省市县
    function getAreaOut(){
        var url=path.path+"/orderCheck/queryAdress";
        $.ajax({
            type : "get",
            url : url,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    $('.provenceOrder').val(res.dto.province).attr({'data-id':res.dto.provinceCode,'value':res.dto.province});
                    $('.cityOrder').val(res.dto.city).attr('data-id',res.dto.cityCode);
                    $('.areaOrder').val(res.dto.county).attr('data-id',res.dto.countyCode);
                    $('.provence.r').val(res.dto.province).attr({'data-id':res.dto.provinceCode,'value':res.dto.province});
                    $('.city.r').val(res.dto.city).attr('data-id',res.dto.cityCode);
                    $('.area.r').val(res.dto.county).attr('data-id',res.dto.countyCode);
                    var str = '';
                    $.each(res.list,function(){
                        str+='<option data-id="'+this.id+'" value="'+this.name+'" class="chooseDetailCountryOrder">'+this.name+'</option>'
                    });
                    $('.countyOrder').html(str);
                    $('.county.r').html(str);
                    $(".areaShow").html(res.dto.county)
                }
            }
        });
    }
    //请求处方类型
/*    var userId='';
    var userName='';*/
    function getDict(){
        var url=path.path+"/orderCheck/queryDict";
        var params={"code":"20107"};
        $.ajax({
            type : "get",
            url : url,
            data :params,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    $('.resultUserName').html(res.userName);
                    sendListOrder.userId = res.userId;
                    sendListOrder.userName = res.userName;
                    var str = '<option value="全部" data-id="" class="orderTypeOption">全部</option>';
                    $.each(res.list,function(){
                        str+='<option value="'+this.dictItemName+'" data-id="'+this.dictItemCode+'" class="orderTypeOption">'+this.dictItemName+'</option>'
                    });
                    $('.orderType').html(str);
                }
            }
        });
    }

    //日期
    $( "#dateBegin" ).datepicker(datepickerObj);
    $( "#dateEnd" ).datepicker(datepickerObj);

    //查询条件填充
    function showCondition(a,b){
        b.html(a);
    }
    //点击查询,显示查询条件
    $('.preConditionQuery').on('click',function(){
        if($(this).hasClass('disClick'))return;

        showCondition($('#dateBegin').val(),$('.resultBeginTime'));
        showCondition($('#dateEnd').val(),$('.resultEndTime'));
        showCondition($('.provenceOrder.borCcc').val(),$('.resultProvence'));
        showCondition($('.cityOrder.borCcc').val(),$('.resultCity'));
        showCondition($('.areaOrder.borCcc').val(),$('.resultArea'));
        showCondition($('.countyOrder.borCcc').val(),$('.resultCounty'));
        showCondition($('.orderNumber.borCcc').val(),$('.resultOrderNum'));
        showCondition($('.orderType.borCcc').val(),$('.resultOrderType'));
        showCondition($('.clinicDefinition.borCcc').val(),$('.resultClinicName'));
        showCondition(dateFormat(new Date()),$('.checkNowDate'));

        $('.preSamQueryResult').show();
    });

    //点击开始抽查
    var orderIdArray = [];
    var checkArea='';
    $('.preSamQuerySure').on('click',function(){

        var roadCode = '';
        var orgName = $('.resultClinicName').html();
        var startDate = $('.resultBeginTime.order').html();
        var endDate = $('.resultEndTime.order').html();
        var orderType = '';
        var checkNo = $('.resultOrderNum.order').html();
        checkArea = $('.resultArea').html()+$('.resultCounty').html();
        //结束审核字段
        sendListOrder.checkDate = $('.resultBeginTime').html()+'至'+$('.resultEndTime').html();
        sendListOrder.checkArea = checkArea;
        sendListOrder.checkCount = checkNo;

        $.each($('.chooseDetailCountryOrder'),function(){
            if($(this).attr('value')== $('.resultCounty.order').html()){
                roadCode = $(this).attr('data-id');
            }
        });
        $.each($('.orderTypeOption'),function(){
            if($(this).attr('value')== $('.resultOrderType.order').html()){
                orderType = $(this).attr('data-id');
                sendListOrder.orderType = orderType;
            }
        });
        var url=path.path+"/orderCheck/queryVisitIdList";
        var params={
            "roadCode":roadCode,
            "orgName":orgName,
            "startDate":startDate,
            "endDate":endDate,
            "orderType":orderType,
            "checkNo":checkNo
        };
        $.ajax({
            type : "get",
            url : url,
            data :params,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    if(res.str == null){
                        layer.alert('抽查数量不能大于处方数量,当前查询处方0条');
                    }else{
                        orderIdArray = res.str;
                        nextOrder(orderIdArray[0],0);
                        sendListOrder.orgId = res.orgId;
                        sendListOrder.orgName = res.orgName;
                    }
                }else{
                    layer.alert(res.message);
                }

            }
        });
    });
    //点击下一页请求
    var pageNum = 0;
    var isClick = false;
    $('.orderNextPage').on('click',function(){
        if(isClick == false){
            pageNum++;
            $('.currentPage').html(pageNum+1);
            if((pageNum+1)>orderIdArray.length){
                layer.alert('已经是最后一张处方！');
                $('.currentPage').html(pageNum);
                pageNum--;
                isClick = true;
                return
            }else{
                remarks();
                nextOrder(orderIdArray[pageNum],0);
                backResult();
            }
        }

    });
    function backResult(){
        $('.result.x').removeClass("result-n");
        $('.result-txt').html('合格');
        $('.reason-tr.x').hide();
        $('.other.x').hide();
        $('.edit.unOk').val('');
    }
    function remarks(){
        if($('.result.x').hasClass('result-n')){
            sendListOrder.list[pageNum-1].checkResult = 1;
            sendListOrder.list[pageNum-1].unqualifiedReason = $('.unOk.reason').val();
            sendListOrder.list[pageNum-1].remarks = $('.edit.unOk').val();
        }else{
            sendListOrder.list[pageNum-1].checkResult = 0;
            sendListOrder.list[pageNum-1].unqualifiedReason = '';
            sendListOrder.list[pageNum-1].remarks = '';
        }
    }
    function changeTabOrder(){
        $('.details-box.sign').show();
        $('.details-box.case').hide();
        var acSpan = $('.type-title span');
        $.each(acSpan,function(){
            if($(this).attr('data-id')==1){
                $(this).addClass('active').siblings('span').removeClass('active')
            }
        });
    }
    //根据id请求处方等详细信息
    function nextOrder(orderId,flag,id){
        changeTabOrder();
        var url=path.path+"/orderCheck/queryOrderDtl";
        var params={
            "orderId":orderId,
            "flag":flag,
            "id":id
        };
        $.ajax({
            type : "get",
            url : url,
            data :params,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    $('.totalPage').html(orderIdArray.length);
                    $('.preConditionQuery').addClass('disClick');
                    $('.preSamQueryResult').hide();
                    $('.content-box.tpl').removeClass('displayNo');
                    //结束审核字段
                    var obj = {};
                    //医生
                    $('.visitDate').html(dateFormat(new Date(res.dto.visitDate))).attr('title',dateFormat(new Date(res.dto.visitDate)));
                    $('.doctorName').html(res.dto.doctorName).attr('title',res.dto.doctorName);
                    $('.ordDepName').html(res.dto.ordDepName).attr('title',res.dto.ordDepName);
                    $('.orgName').html(res.dto.orgName).attr('title',res.dto.orgName);
                    var titlesClinical = '无';
                    if(res.dto.titlesClinical == '5'){
                        titlesClinical = '初级职称'
                    } else if(res.dto.titlesClinical == '6'){
                        titlesClinical = '中级职称'
                    } else if(res.dto.titlesClinical == '7'){
                        titlesClinical = '高级职称'
                    }
                    $('.titlesClinical').html(titlesClinical);

                    //患者
                    $('.patientName').html(res.dto.patientName).attr('title',res.dto.patientName);
                    $('.sexName').html(res.dto.sexName).attr('title',res.dto.sexName);
                    $('.age').html(res.dto.age).attr('title',res.dto.age);
                    $('.visitNo').html(res.dto.visitNo).attr('title',res.dto.visitNo);
                    //$('.ordDepName').html(res.dto.ordDepName);
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
                    obj.orderDate= dateFormat(new Date(orderDTOs[0].visitDate));
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


                    //不合格信息
                    if(res.list){
                        if(res.list[0].checkResult == 1){
                            $('.result.u.result-n').show();
                            $('.reason-tr.u').css('display','table-row');
                            $('.reason-tr.u td').html(res.list[0].unqualifiedReason);
                            $('.other.u').css('display','table-row');
                            $('.other.u textarea').html(res.list[0].remarks);
                            $('.result.l').hide();
                        }else if(res.list[0].checkResult == 0){
                            $('.result.u.result-n').hide();
                            $('.reason-tr,.other').css('display','none');
                            $('.result.l').show();
                        }
                    }

                }
                else{
                    layer.alert(res.message)
                }

            }
        });
    }

    //点击结束审核
    $('.preSamOver').on('click',function(){
        preSamExaError();
    });
    //处方审核情况警告弹窗
    function preSamExaError(){
        var html = $('#preSamExaError').html();
        layer.open({
            type: 1, //page层
            shadeClose: false,
            closeBtn: 0,
            area: ['570px', '330px'],
            title: false,
            shade: 0.4, //遮罩透明度
            shift: 0, //0-6的动画形式，-1不开启
            content: html,
            success:function(){
                if((pageNum+1)<orderIdArray.length){
                    $('.preSamExaError .tipText.n').css('display','block');
                    $('.preSamExaError .tipText.y').css('display','none');
                }else{
                    $('.preSamExaError .tipText.n').css('display','none');
                    $('.preSamExaError .tipText.y').css('display','block');
                }
            }
        });
        $('.preSamExaError .sure').on('click',function(){
            preSamExamineLayer()
        });
        $('.preSamExaError .refuse').on('click',function(){
            closeLayer()
        })
    }
    //处方审核情况弹窗
    function preSamExamineLayer(){
        if(pageNum<orderIdArray.length){
            pageNum++;
            remarks();
        }else{
            remarks();
        }
        var url=path.path+"/orderCheck/insertOrder";
        $.ajax({
            type : "post",
            url : url,
            dataType:"json",
            data :JSON.stringify(sendListOrder),
            contentType: "application/json; charset=utf-8",
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    var checkDto = res.checkDto;
                    pageNum = 0;
                    isClick = false;
                    $('.currentPage').html(pageNum+1);
                    backResult();
                    sendListOrder.list = [];
                    var html = $('#preSamExamineLayer').html();
                    layer.open({
                        type: 1, //page层
                        shadeClose: false,
                        closeBtn: 0,
                        area: ['685px', '460px'],
                        title: false,
                        shade: 0.4, //遮罩透明度
                        shift: 0, //0-6的动画形式，-1不开启
                        content: html,
                        success:function(){
                            $('.checkDateShow').html(checkDto.checkDate);
                            $('.checkAreaShow').html($('.provenceOrder').val()+$('.cityOrder').val()+checkDto.checkArea);
                            $('.checkTimeShow').html(checkDto.checkTime);
                            $('.qualifiedNoShow').html(checkDto.qualifiedNo);
                            $('.unqualifiedNoShow').html(checkDto.unqualifiedNo);
                            $('.checkCountShow').html(checkDto.checkCount);
                            $('.userNameShow').html(checkDto.userName);
                            $('.orderTypeShow').html(checkDto.orderTypeName);
                        }
                    });
                    $('.preSamExamineLayer .closeLayer').on('click',function(){
                        closeLayer();
                        preOverChange();
                    });
                    $('.preSamExamineLayer .preSamExamine').on('click',function(){
                        closeLayer();
                        preOverChange();
                    })
                }else{
                    sendListOrder.list = [];
                }
            }
        });

    }
    function preOverChange(){
        $('.content-box.tpl').addClass('displayNo');
        $('.currentPage').html('1');
        $('.preConditionQuery').removeClass('disClick');
    }


//============================================ 抽查记录=======================================
    //日期
    $( "#dateBeginR" ).datepicker(datepickerObj);
    $( "#dateEndR" ).datepicker(datepickerObj);
    //点击查询
    $('.queryOrderTable').on('click',function(){
        //console.log(new Date($('#dateBeginR').val()))
        $('.orderQueryTable.total').show();
        $('.orderQueryTable.detail').hide();
        var url = path.path+"/orderCheck/queryOrderRecord";
        var data={
            startDate:$('#dateBeginR').val()+' 00:00:00',
            endDate:$('#dateEndR').val()+' 23:59:59',
            checkArea:$('.area.r').val()+$('.county.r').val(),
            orgName:$('.clinicDefinition.r').val()
        };
        $.ajax({
            type : "get",
            url : url,
            data :data,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    $('.showDetailTBodyF').empty();
                    var str = '';
                    $.each(res.list,function(i){
                        if(this.orderTypeName == null){
                            this.orderTypeName = ''
                        }
                        str+='<tr data-id="'+this.checkId+'"> <td>'+(i+1)+'</td> <td class="orderChooseDay" title="'+this.checkDate+'">'+this.checkDate+'</td> <td title="'+this.checkArea+'">'+this.checkArea+'</td> <td title="'+this.orgName+'">'+this.orgName+'</td> <td title="'+this.orderTypeName+'">'+this.orderTypeName+'</td> <td title="'+this.checkCount+'">'+this.checkCount+'</td> <td title="'+this.qualified+'">'+this.qualified+'</td> <td data-id="0"  class="orderChooseNo curPointer" title="'+this.qualifiedNo+'">'+this.qualifiedNo+'</td> <td class="orderChooseNo curPointer" data-id="1" title="'+this.unqualifiedNo+'">'+this.unqualifiedNo+'</td> <td title="'+this.userName+'">'+this.userName+'</td> <td title="'+this.checkTime+'">'+this.checkTime+'</td> </tr>'
                    });
                    $('.showDetailTBodyF').empty().append(str);

                }
            }
        });
    });
    //点击信息总表到详情表
    $('.orderQueryTable.total').on('click','.orderChooseNo',function(){
        if($(this).html()=='0'){//处方数量为0不能点击
            return
        }
        var orderChooseDay = $(this).siblings('td.orderChooseDay').html();
        var beginTime = orderChooseDay.split('至')[0];
        var endTime = orderChooseDay.split('至')[1];
        $('.orDetailTaStartTime').html(beginTime);
        $('.orDetailTaEndTime').html(endTime);
        $('.orderQueryTable.total').hide();
        $('.orderQueryTable.detail').show();
        var str = '';
        var checkId = $(this).parents('tr').attr('data-id');
        var flag = $(this).attr('data-id');
        if(flag == 1){
            $('.unUseOrder').html('不合格');
            str = '<thead><tr> <td>序号</td> <td>处方开具日期</td> <td>抽查地区</td> <td>诊所名称</td> <td>医生名称</td> <td>是否合格</td> <td>不合格原因</td> <td>备注</td> <td>操作</td> </tr></thead>'
        }else if(flag == 0){
            $('.unUseOrder').html('合格');
            str = '<thead><tr> <td>序号</td> <td>处方开具日期</td> <td>抽查地区</td> <td>诊所名称</td> <td>医生名称</td> <td>是否合格</td><td>操作</td> </tr></thead>'
        }
        var url = path.path+"/orderCheck/queryOrderQualified";
        var data={
            checkId:checkId,
            flag:flag
        };
        $.ajax({
            type : "get",
            url : url,
            data :data,
            cache : false,
            async : false,
            success : function(res){
                if(res.code == '000000'){
                    $('.showDetailS').empty();
                    $.each(res.list,function(i){
                        var a = '';
                        if(this.checkResult==1){
                            a = '不合格';
                            str +='<tr data-id="'+this.id+'"> <td>'+(i+1)+'</td> <td title="'+this.orderDate+'">'+this.orderDate+'</td> <td title="'+this.checkParts+'">'+this.checkParts+'</td> <td title="'+this.orgName+'">'+this.orgName+'</td> <td title="'+this.doctorName+'">'+this.doctorName+'</td> <td title="'+a+'">'+a+'</td> <td title="'+this.unqualifiedReason+'">'+this.unqualifiedReason+'</td> <td title="'+this.remarks+'">'+this.remarks+'</td> <td data-id="'+this.vistId+'" class="showDetail curPointer">查看</td> </tr>'
                        }else if(this.checkResult==0){
                            a = '合格';
                            str +='<tr data-id="'+this.id+'"> <td>'+(i+1)+'</td> <td title="'+this.orderDate+'">'+this.orderDate+'</td> <td title="'+this.checkParts+'">'+this.checkParts+'</td> <td title="'+this.orgName+'">'+this.orgName+'</td> <td title="'+this.doctorName+'">'+this.doctorName+'</td> <td title="'+a+'">'+a+'</td> <td data-id="'+this.vistId+'" class="showDetail curPointer">查看</td> </tr>'
                        }
                    });
                    $('.showDetailS').empty().append(str);

                }
            }
        });
    });
    //点击返回按钮返回信息总表
    $('.backToTotal').on('click',function(){
        $('.orderQueryTable.detail').hide();
        $('.orderQueryTable.total').show();
    });
    //点击详情表到处方弹窗
    $('.orderQueryTable.detail').on('click','.showDetail',function(){
        var orderId = $(this).attr('data-id');
        var id = $(this).parents('tr').attr('data-id');
        nextOrder(orderId,1,id);
        $('.orderQueryTable.detail').hide();
        $('.table-style.tplShow').addClass('displayNo');
        $('.table-style.tplHide').removeClass('displayNo');
        //$('.reason-tr,.other').css('display','table-row');

        $('.content-box.tpl').removeClass('displayNo');
        var orderDisDetail = $('.content-box.tpl').html();
        $('.showDetailOrderNo.content-box').empty().html(orderDisDetail);
        var html = $('#orderQueryTableDetail').html();


        layer.open({
            type: 1, //page层
            shadeClose: false,
            closeBtn: 0,
            area: ['1035px', '500px'],
            title: false,
            shade: 0.4, //遮罩透明度
            shift: 0, //0-6的动画形式，-1不开启
            content: html,
            success:function(){
                changeTabOrder();
                if(document.body.clientWidth>1349){
                    $('*').css('font-size','12px');
                    $('.orderQueryTableDetail .closeLayer').css('font-size','40px');
                }

            }
        });
        $('.orderQueryTableDetail .closeLayer').on('click',function(){
            closeLayer();
            if(document.body.clientWidth>1349){
                $('*').css('font-size','16px');
            }
            $('.table-style.tplShow').removeClass('displayNo');
            $('.table-style.tplHide').addClass('displayNo');
            $('.reason-tr,.other').css('display','none');
            $('.content-box.tpl').addClass('displayNo');
            $('.currentPage').html('1');
            isClick = false;
            pageNum = 0;
            sendListOrder.list = [];
            $('.orderQueryTable.detail').show();
            $('.preConditionQuery').removeClass('disClick');
        })
    });

    //老文
    $('.result').click(function(){
        $(".reason-tr").fadeToggle("slow");
        $(".other").fadeToggle("slow");
        if($(this).hasClass("result-n")){
            $(this).removeClass("result-n");
            $(".result-txt").text("合格")
        }else{
            $(this).addClass("result-n");
            $(".result-txt").text("不合格");
        }
    });
    $(document).on('click','.type-title span',function(){
        $(this).addClass('active').siblings().removeClass('active');
        var acSpan = $(this);
        if(acSpan.attr('data-id')==1){
            $('.details-box.sign').show();
            $('.details-box.case').hide();
        }else if(acSpan.attr('data-id')==2){
            $('.details-box.sign').hide();
            $('.details-box.case').show();
        }
    });
});