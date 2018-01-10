var parentId = "";
var areaBol = true;


//区域下拉框开始
common.dropDownList();
//区域下拉框结束

//设置年限开始
common.setYear();

//add by clark
common.setMonth('本月');

$(function() {
    $('#queryBy').val('month');

    chooseActive();


    //下拉菜单的实现开始
    var totalNav = $(".totalNav");
    var subordinate = $(".subordinate");
    totalNav.mouseenter(function() {
        subordinate.show();
    });
    totalNav.mouseleave(function() {
        subordinate.hide();
    });
    //下拉菜单的实现结束
    function getQueryPara() {
        var dateType = $('#queryBy').val(),
            parentId = $('#areaChoose option:selected').attr('data-id'),
            year = $(".yearSelected").val(),
            month = $(".monthSelected").val();

        if (dateType === 'year') {
            month = '';
        }

        return {
            reportType: dateType,
            parentAreaCode: parentId,
            year: year,
            month: month
        };
    }

    $('#searchInfo').on('click', function() {
        chooseActive();
    })

    $('#queryBy').on('change', function() {
        var dateType = $(this).val();
        if (dateType === 'year') {
            $('.monthSelected').hide();
        } else if (dateType === 'month') {
            $('.monthSelected').show();
        }
    });

    //区域确认开始
    $("#areaConfirm").bind("click", function() {
        chooseActive();
    });
    //时间查询
    $('.queryTime').on('click', function() {
        chooseActive();
    });

    function chooseActive() {
        parentId = $('#areaChoose option:selected').attr('data-id');
        var chooseNumber = 0;
        if ($('.totalNav').hasClass('active')) {
            if ($('#numSubmit').hasClass('active')) {
                chooseNumber = 1;
            } else if ($('#classSubmit').hasClass('active')) {
                chooseNumber = 2;
            }
        } else if ($('#lowerLevel').hasClass('active')) {
            chooseNumber = 3;
        } else if ($('#infoTotal').hasClass('active')) {
            chooseNumber = 4;
        }
        if (chooseNumber == 1) {
            numSubmit();
        } else if (chooseNumber == 2) {
            classSubmit();
        } else if (chooseNumber == 3) {
            lowerLevel();
        } else if (chooseNumber == 4) {
            infoTotal();
        }
    }
    //显示选中时间地区
    function showAreaEtc() {
        var area = $("#areaChoose").val();
        var year = $(".yearSelected").val();
        var month = $(".monthSelected").val();
        var type = $('#queryBy').val();
        $(".areaShow").html(area);
        if (type == 'year') {
            $(".showTime").html(year + '年');
        } else if (type == 'month') {
            $(".showTime").html(year + '年' + month + '月');
        }

    }
    //区域确认结束
    /********点击切换文字特效*********/
    $("ul.clinicInfoTab li").click(function() {
        $(this).addClass("clinicInfoTabBTn").siblings().removeClass("clinicInfoTabBTn");
    });
    $("ul.area li").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
    });
    totalNav.click(function() {
        $(this).addClass("active").siblings().removeClass("active");
    });
    $(".subordinate div").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
    });
    /*********点击切换文字特效********/
    //时间年度的切换开始
    $("#nowYear").bind("click", function() {
        $("#nowYearChoose").removeClass("hide").addClass("show");
        $("#nowMonthChoose").removeClass("show").addClass("hide");
    });

    $("#nowMonth").bind("click", function() {
        $("#nowYearChoose").removeClass("show").addClass("hide");
        $("#nowMonthChoose").removeClass("hide").addClass("show");
    });
    //时间年度的切换结束

    //诊所数量统计开始
    $("#numSubmit").bind("click", function() {
        $(".areaList").eq(0).removeClass("hide").addClass("show").siblings(".areaList").removeClass('show').addClass('hide');
        parentId = $('#areaChoose option:selected').attr('data-id');
        numSubmit();
    });

    function numSubmit() {

        showAreaEtc();

        var params = getQueryPara();
        if (!!params.parentAreaCode) {
            //诊所数量统计接口
            var url = path.path + "/efficiency/queryServiceQty";

            $.ajax({
                type: "get",
                url: url,
                data: params,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000") {
                        var infoData = res.list;
                        if (!infoData) return;

                        var momData = res.momlist;
                        var infoDataName = '';
                        var infoDataQty = 0;
                        if (infoData.length) {
                            infoDataName = infoData[0].name;
                            infoDataQty = infoData[0].qty;
                        }
                        if (infoData) {
                            //2016年日人均服务人次
                            var topDataName = [];
                            var topDataContent = [];
                            for (var i = 0; i < infoData.length; i++) {
                                topDataName.push(infoData[i].name);
                                topDataContent.push(infoData[i].qty);
                            }
                            var topNode = document.getElementById('proportionOfPrescriptions');
                            var topTitleName = '日人均服务人次：' + infoDataQty + '次';
                            var topRequireInfo = infoDataName + '日人均服务人次';
                            var topMaxNum = 10;
                            echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, false, false)
                                //2016年日人均服务人次
                        }
                        var momDataName = '';
                        var momDataQty = 0;
                        if (momData) {
                            momDataName = momData[0].name;
                            momDataQty = momData[0].qty;
                        }


                        //日人均服务人次环比
                        var topNode = document.getElementById('timeChains');
                        var topTitleName = '环比增长率：' + common.anPercent(momDataQty, infoDataQty, true);
                        var topRequireInfo = '日人均服务人次环比';
                        var topDataName = [momDataName, infoDataName];
                        var topDataContent = [momDataQty, infoDataQty];
                        var topMaxNum = 10;
                        echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, false, false);
                        //日人均服务人次环比

                        //table表格的填充开始
                        $("#avgDayData").html(infoDataQty);
                        $("#avgHuanData").html(common.anPercent(momDataQty, infoDataQty, true));

                        //table表格的填充结束
                    } else {
                        $("#avgDayData").html("0");
                        $("#avgHuanData").html("0");

                        $("#proportionOfPrescriptions").html("数据暂无");
                        $("#timeChains").html("数据暂无");
                    }
                }
            })
        }
    }
    //诊所数量统计结束

    //诊所类型统计开始
    $("#classSubmit").bind("click", function() {
        $(".areaList").eq(1).removeClass("hide").addClass("show").siblings(".areaList").removeClass('show').addClass('hide');
        parentId = $('#areaChoose option:selected').attr('data-id');
        classSubmit();
    });

    $("#searchInfo").on("click", function() {
        classSubmit();
    });

    function classSubmit() {
        showAreaEtc();
        var params = getQueryPara();
        if (params.parentAreaCode != "") {
            //诊所数量统计接口
            var url = path.path + "/efficiency/queryServiceEff";

            $.ajax({
                type: "get",
                url: url,
                data: params,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000" && res.typelist && res.scalelist) {

                        var typeData = res.typelist;
                        var numDataName = [];
                        var numDataContent = [];
                        for (var i = 0; i < typeData.length; i++) {
                            numDataName.push(typeData[i].name);
                            numDataContent.push(typeData[i].qty);
                        }
                        //不同类型诊所日人均服务人次分布
                        var numNode = document.getElementById('numDistribute');
                        var numTitleName = '';
                        var numRequireInfo = '不同类型诊所日人均服务人次分布';
                        var numMaxNum = 25;
                        echart.histogram(numNode, numTitleName, numRequireInfo, numDataName, numDataContent, numMaxNum, false)
                            //不同类型诊所日人均服务人次分布

                        var scaleData = res.scalelist;
                        //不同规模诊所日人均服务人次分布
                        var topDataName = [];
                        var topDataContent = [];
                        for (var i = 0; i < scaleData.length; i++) {
                            topDataName.push(scaleData[i].name + "诊所");
                            topDataContent.push(parseInt(scaleData[i].qty))
                        }
                        var topNode = document.getElementById('distribution');
                        var topTitleName = '';
                        var topRequireInfo = '不同规模诊所日人均服务人次分布';
                        var topMaxNum = 25;
                        echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, false, false)
                            //不同规模诊所日人均服务人次分布

                        //表格数据填充开始
                        $("#ordinaryData").html(typeData[0].qty);
                        $("#chinaDoc").html(typeData[1].qty);
                        $("#foreinDoc").html(typeData[2].qty);
                        $("#mouthDoc").html(typeData[3].qty);
                        $("#otherDoc").html(typeData[4].qty);
                        $("#smallClinic").html(scaleData[0].qty);
                        $("#midClinic").html(scaleData[1].qty);
                        $("#bigClinic").html(scaleData[2].qty);
                        //表格数据填充结束

                    } else {
                        $("#ordinaryData").html("");
                        $("#chinaDoc").html("");
                        $("#foreinDoc").html("");
                        $("#mouthDoc").html("");
                        $("#otherDoc").html("");
                        $("#smallClinic").html("");
                        $("#midClinic").html("");
                        $("#bigClinic").html("");

                        $("#numDistribute").html("数据暂无");
                        $("#distribution").html("数据暂无");
                    }



                }
            })
        }
    }
    // 诊所类型统计结束

    // 下级区划概况开始
    $("#lowerLevel").on("click", function() {
        $(".areaList").eq(2).removeClass("hide").addClass("show").siblings(".areaList").removeClass('show').addClass('hide');
        parentId = $('#areaChoose option:selected').attr('data-id');
        lowerLevel();
    });

    function lowerLevel() {
        showAreaEtc();
        var params = getQueryPara();
        params.code = '00064';
        if (params.parentAreaCode != "") {
            //诊所服务效率综合概况接口   对应第一个表单
            var urlTop = path.path + "/efficiency/queryJuniorOne";
            $.ajax({
                type: "get",
                url: urlTop,
                data: params,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000" && res.list) {
                        var infoData = res.list;
                        // 各下级行政区划个体诊所服务效率综合概况表开始
                        var serviceXData = [];
                        var numData = [];
                        for (var i = 0; i < infoData.length; i++) {
                            serviceXData.push(infoData[i].name);
                            numData.push(infoData[i].qty);
                        }
                        var serviceObj = document.getElementById("serviceChart");
                        var serviceLegendData = ['各下级区划个体诊所服务效率']

                        var contentData = {
                            name: '各下级区划个体诊所服务效率',
                            data: numData
                        };
                        echart.avgLine(serviceObj, serviceLegendData, serviceXData, contentData);
                        // 各下级行政区划个体诊所服务效率综合概况表结束
                    } else {
                        $("#serviceChart").html("数据暂无");
                    }
                }
            });

            //下级区域概括接口   对应中间的表单
            var urlMid = path.path + "/efficiency/queryJunior";
            var paramsMid = {
                "reportType": params.reportType,
                "month": params.month,
                "year": params.year,
                "parentAreaCode": params.parentAreaCode,
                "code": "00064"
            };
            $.ajax({
                type: "get",
                url: urlMid,
                data: paramsMid,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000" && res.list) {
                        var infoData = res.list;
                        var scaleXData = [];

                        var ordinaryData = [];
                        var chinaData = [];
                        var chinaAndForein = [];
                        var mouthData = [];
                        var otherData = [];
                        for (var i = 1; i < infoData.length / 5; i++) {
                            scaleXData.push(infoData[(i - 1) * 5].name);
                            mouthData.push(infoData[(i * 5) - 5].qty);
                            ordinaryData.push(infoData[(i * 5) - 4].qty);
                            otherData.push(infoData[(i * 5) - 3].qty);
                            chinaAndForein.push(infoData[(i * 5) - 2].qty);
                            chinaData.push(infoData[(i * 5) - 1].qty);
                        }
                        
                        // 各下级行政区划不同规模个体诊所服务效率概况表开始
                        var scaleObj = document.getElementById("classChart");
                        var scaleLegendData = ['普通', '中医', '中西医结合', '口腔', '其他'];
                        var scaleData = [{
                            name: '普通',
                            data: ordinaryData
                        }, {
                            name: '中医',
                            data: chinaData
                        }, {
                            name: '中西医结合',
                            data: chinaAndForein
                        }, {
                            name: '口腔',
                            data: mouthData
                        }, {
                            name: '其他',
                            data: otherData
                        }];
                        var scaleContentData = echart.getMoreLineData(scaleData);
                        echart.moreLine(scaleObj, scaleLegendData, scaleXData, scaleContentData);
                        // 各下级行政区划不同规模个体诊所服务效率概况表结束

                    } else {
                        $("#classChart").html("暂无数据")
                    }
                }
            });

            //下级区域概括接口   对应下面的表单
            var paramsBottom = {
                "reportType": params.reportType,
                "month": params.month,
                "year": params.year,
                "parentAreaCode": params.parentAreaCode,
                "code": "00065"
            };
            $.ajax({
                type: "get",
                url: urlMid,
                data: paramsBottom,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000" && res.list) {
                        var infoData = res.list;
                        // 各下级行政区划不同类型个体诊所服务效率概况表开始
                        var classXData = [];
                        var smallData = [];
                        var midData = [];
                        var bigData = [];
                        for (var i = 1; i < infoData.length / 3; i++) {
                            classXData.push(infoData[(i - 1) * 3].name);

                            smallData.push(infoData[(i * 3) - 2].qty);
                            midData.push(infoData[(i * 3) - 1].qty);
                            bigData.push(infoData[(i * 3) - 3].qty)
                        }
                        var classObj = document.getElementById("scaleChart");
                        var classLegendData = ['小型诊所', '中型诊所', '大型诊所'];

                        var classData = [{
                            name: '小型诊所',
                            data: smallData
                        }, {
                            name: '中型诊所',
                            data: midData
                        }, {
                            name: '大型诊所',
                            data: bigData
                        }];
                        var classContentData = echart.getMoreLineData(classData);
                        echart.moreLine(classObj, classLegendData, classXData, classContentData);
                        // 各下级行政区划不同类型个体诊所服务效率概况表结束

                    } else {
                        $("#scaleChart").html("数据暂无");
                    }
                }
            })

        }
    }
    //下级区划概况结束

    //信息总表开始
    $("#infoTotal").bind("click", function() {
        $(".areaList").eq(3).removeClass("hide").addClass("show").siblings(".areaList").removeClass('show').addClass('hide');
        parentId = $('#areaChoose option:selected').attr('data-id');
        infoTotal();
    });

    function infoTotal() {

        showAreaEtc();
        //var year = $(".yearSelected").val();
        var params = getQueryPara();
        if (!!params.parentAreaCode) {
            //取上一个页面的三个接口
            //诊所服务效率综合概况接口   对应第一个表单
            var efficiencyData = [];
            var classData = [];
            var scaleData = [];

            var urlTop = path.path + "/efficiency/queryJuniorOne";
            var paramsTop = {
                "reportType": params.reportType,
                "month": params.month,
                "year": params.year,
                "parentAreaCode": params.parentAreaCode,
                "code": "00064"
            };
            $.ajax({
                type: "get",
                url: urlTop,
                data: paramsTop,
                cache: false,
                async: false,
                success: function(res) {
                    if (res.code == "000000") {
                        efficiencyData = res.list;
                    }
                }
            });

            //下级区域概括接口   对应中间的表单
            var urlMid = path.path + "/efficiency/queryJunior";
            var paramsMid = {
                "reportType": params.reportType,
                "month": params.month,
                "year": params.year,
                "parentAreaCode": params.parentAreaCode,
                "code": "00064"
            };
            $.ajax({
                type: "get",
                url: urlMid,
                data: paramsMid,
                cache: false,
                async: false,
                success: function(res) {
                    if (res.code == "000000") {
                        classData = res.list;
                    }
                }
            });

            //下级区域概括接口   对应下面的表单
            var paramsBottom = {
                "reportType": params.reportType,
                "month": params.month,
                "year": params.year,
                "parentAreaCode": params.parentAreaCode,
                "code": "00065"
            };
            $.ajax({
                type: "get",
                url: urlMid,
                data: paramsBottom,
                cache: false,
                async: false,
                success: function(res) {
                    if (res.code == "000000") {
                        scaleData = res.list;
                    }
                }
            });

            if (efficiencyData && classData && scaleData) {
                var sumNum = efficiencyData.length;
                var str = '<tr class="tableHead"> <td rowspan="2">行政区域规划</td> <td rowspan="2">区域平均服务人(人)</td> <td colspan="5">诊所类型</td> <td colspan="3">诊所规模</td> </tr> <tr class="tableHead"> <td>普通</td> <td>中医</td> <td>中西医结合</td> <td>口腔</td> <td>其他</td> <td>小型诊所</td> <td>中型诊所</td> <td>大型诊所</td> </tr>';
                for (var i = 0; i < sumNum; i++) {
                    str += '<tr><td>' + efficiencyData[i].name + '</td><td>' + efficiencyData[i].qty + '</td><td>' + classData[5*i + 1].qty + '</td><td>' + classData[5*i +4].qty + '</td>' +
                        '<td>' + classData[5*i +3].qty + '</td><td>' + classData[5*i +0].qty + '</td><td>' + classData[5*i+2].qty + '</td><td>' + scaleData[3*i+1].qty + '</td>' +
                        '<td>' + scaleData[3*i+2].qty + '</td><td>' + scaleData[3*i+0].qty + '</td></tr>';
                }
                $("#totalTable").empty().append(str);
            } else {
                $('#totalTable').empty().append('<tr><td>暂无数据</td></tr>');
            }

        }
    }
    //信息总表结束

});