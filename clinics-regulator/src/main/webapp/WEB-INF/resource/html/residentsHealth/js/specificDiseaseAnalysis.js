var parentId = "";
var areaBol = true;

//区域下拉框开始
common.dropDownList();
//区域下拉框结束

//设置年限开始
common.setYear();
common.setMonth('本月');
common.setQuarter();
common.setDays();
//设置年限结束



$(function() {
    $(".nowTimeChoose select").val('month');

    function chooseTimeNow() {
        var choosed = $(".nowTimeChoose select").val();
        if (choosed == 'year') {
            $(".yearY").show().siblings('.nowTimeList').hide();
        } else if (choosed == 'quarter') {
            $(".quarterY").show().siblings('.nowTimeList').hide();
        } else if (choosed == 'month') {
            $(".monthY").show().siblings('.nowTimeList').hide();
        } else if (choosed == 'day') {
            $(".dayY").show().siblings('.nowTimeList').hide();
        }
    }

    function getQueryPara() {
        var dateType = $('.chooseByYear select').val(),
            parentId = $('#areaChoose option:selected').attr('data-id'),
            year = $(".yearSelected").val(),
            quarter = $(".quarterSelected").val(),
            startMonth = $(".monthSelected").val(),
            endMonth = '',
            day = $(".daySelected").val();

        if (dateType === 'year') {
            startMonth = '';
            endMonth = '';
            quarter = '';
            day = '';
        } else if (dateType === 'quarter') {
            startMonth = quarter.split(',')[0];
            endMonth = quarter.split(',')[2];
            day = '';
            year = $('.quarterChooseYear').val();
        } else if (dateType === 'month') {
            day = '';
            quarter = '';
            endMonth = '';
            year = $('.monthChooseYear').val();
            startMonth = $('.monthChooseMonth').val();
        } else if (dateType === 'day') {
            year = $('.dayChooseYear').val();
            startMonth = $('.dayChooseMonth').val();
            quarter = '';
            endMonth = '';
        }

        return {
            reportType: dateType,
            parentAreaCode: parentId,
            year: year,
            startMonth: startMonth,
            endMonth: endMonth,
            day: day
        };
    }

    changeTab();

    //区域确认开始
    $("#areaConfirm").bind("click", function() {
        var chooseTrend = $('.chooseTrend li.active').attr('data-id');
        if (chooseTrend == 0) {
            changeTab();
        } else if (chooseTrend == 1) {
            timeTrend();
        }

    });
    //区域确认结束

    //tab样式切换
    $('.clinicInfoTab li').on('click', function() {
        $(this).addClass('active').siblings('li').removeClass('active');
    });
    $('.area li').on('click', function() {
        $(this).addClass('active').siblings('li').removeClass('active');
    });
    //当前概况的切换开始
    $("#nowCondition").bind("click", function() {
        $("#nowSituation").removeClass("hide").addClass("show");
        $("#timeForward").removeClass("show").addClass("hide");
        chooseTimeNow();
        changeTab();
    });
    //当前概况的切换结束

    //当前概况里面的时间切换开始
    $(".nowTimeChoose select").bind("change", function() {
        chooseTimeNow()
    });


    //当前概况里面的时间切换结束

    //时间趋势里面的时间选择切换开始
    $(".trendTimeChoose select").bind("change", function() {
        chooseTimeTrend()
    });

    function chooseTimeTrend() {
        var choosed = $(".trendTimeChoose select").val();
        if (choosed == 'year') {
            $('.yearY').show().siblings('.trendTimeList').hide();
        } else if (choosed == 'month') {
            $('.monthY').show().siblings('.trendTimeList').hide();
        } else if (choosed == 'day') {
            $('.dayY').show().siblings('.trendTimeList').hide();
        }
    }
    //时间趋势里面的时间选择切换结束
    //时间趋势的切换开始
    $("#timeTrend").bind("click", function() {
        $("#nowSituation").removeClass("show").addClass("hide");
        $("#timeForward").removeClass("hide").addClass("show");
        chooseTimeTrend();
        timeTrend();
    });
    $('.queryByNow').on('click', function() {
        changeTab()
    });
    $('.queryByTrend').on('click', function() {
        timeTrend()
    });



    function getTrendQueryPara() {
        var dateType = $('.trendTimeChoose select').val(),
            parentId = $('#areaChoose option:selected').attr('data-id'),
            startYear = $(".trendByYearBegin").val(),
            endYear = $('.trendByYearEnd').val(),
            startMonth = $(".trendByMonthBegin").val(),
            endMonth = $('.trendByMonthEnd').val(),
            startDay = $(".trendStartDay").val(),
            endDay = $(".trendEndDay").val();

        if (dateType === 'year') {
            startMonth = '';
            endMonth = '';
            startDay = '';
            endDay = '';
        } else if (dateType === 'month') {
            startYear = $('.monthY .trendByYear').val();
            endYear = '';
            startDay = '';
            endDay = '';
        } else if (dateType === 'day') {
            quarter = '';
            endMonth = '';
            startYear = $('.dayY .trendByYear').val();
            endYear = '';
            startMonth = $(".trendStartMonth").val()
        }

        return {
            reportType: dateType,
            parentAreaCode: parentId,
            endYear: endYear,
            startYear: startYear,
            startMonth: startMonth,
            endMonth: endMonth,
            startDay: startDay,
            endDay: endDay
        };
    }




    function timeTrend() {
        $(".areaShow").html($("#areaChoose").val());
        var diseaseType = $(".diseaseTrend li.active").html();
        $(".diseaseType").html(diseaseType);
        var reportType = $('.trendTimeChoose select').val();
        var chooseDisease = $('.diseaseTrend li.active');
        var startYear = '';
        var endYear = '';
        var startMonth = '';
        var endMonth = '';
        var endDay = '';
        var startDay = '';
        var code = chooseDisease.attr('data-id');
        if (reportType == 'year') {
            startYear = $('.trendByYearBegin').val();
            endYear = $('.trendByYearEnd').val();
            $('.timeShow').html(startYear + '年至' + endYear + '年');
        } else if (reportType == 'month') {
            startYear = $('.trendByYear').val();
            startMonth = $('.trendByMonthBegin').val();
            endMonth = $('.trendByMonthEnd').val();
            $('.timeShow').html(startYear + '年' + startMonth + '月至' + endMonth + '月')
        } else if (reportType == 'day') {
            startYear = $('.trendByYear').val();
            startMonth = $('.monthSelected').val();
            endDay = $('.trendEndDay').val();
            startDay = $('.trendStartDay').val();
            $('.timeShow').html(startYear + '年' + startMonth + '月' + startDay + '日至' + startDay + '日')
        }
        parentId = $('#areaChoose option:selected').attr('data-id');
        if (parentId != "") {
            //诊所数量统计接口
            var url = path.path + "/given/queryGivenTimeTrend";
            var params = getTrendQueryPara();
            params.code = code;

            $.ajax({
                type: "get",
                url: url,
                data: params,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000" && res.list && res.list.length) {
                        var infoData = res.list;
                        // 个体诊所A病种发病变化情况
                        var scaleObj = document.getElementById("changeSituation");
                        var scaleLegendData = ['']
                        var scaleXData = [];
                        var scaleData = [{
                            name: diseaseType + '种发病人数',
                            data: []
                        }]
                        for (var i = 0; i < infoData.length; i++) {
                            scaleXData.push(infoData[i].name);
                            scaleData[0].data.push(infoData[i].qty);
                        }
                        var scaleContentData = echart.getMoreLineData(scaleData);
                        echart.moreLine(scaleObj, scaleLegendData, scaleXData, scaleContentData);
                        // 个体诊所A病种发病变化情况
                    } else {
                        $("#changeSituation").html("数据暂无");
                    }
                }
            })
        }
    }
    //时间趋势的切换结束


    //当前概况里面子菜单的切换开始
    $(".area>li").bind("click", function() {
        $(".areaList").eq($(this).index()).removeClass("hide").addClass("show").siblings('.areaList').removeClass('show').addClass('hide');

        changeTab()
    });


    function setCurrentTimeText() {
        var reportType = $('.chooseByYear select').val();
        if (reportType == 'year') {
            year = $('.yearByYear').val();
            $('.timeShow').html(year + '年');
        } else if (reportType == 'quarter') {
            var year = $('.quarterChooseYear').val();
            $('.timeShow').html(year + '年' + $('.quarterChooseQuater').find("option:selected").text());
        } else if (reportType == 'month') {
            year = $('.monthChooseYear').val();
            startMonth = $('.monthChooseMonth').val();
            $('.timeShow').html(year + '年' + startMonth + '月');
        } else if (reportType == 'day') {
            year = $('.dayChooseYear').val();
            startMonth = $('.dayChooseMonth').val();
            day = $('.daySelected').val();
            $('.timeShow').html(year + '年' + startMonth + '月' + day + '日');
        }
    }

    function changeTab() {
        $(".areaShow").html($("#areaChoose").val());
        var diseaseType = $(".diseaseNow li.active").html();
        $(".diseaseType").html(diseaseType);

        var chooseDisease = $('.diseaseNow li.active');
        var code = chooseDisease.attr('data-id');
        var code2 = chooseDisease.attr('data-code');

        setCurrentTimeText();

        parentId = $('#areaChoose option:selected').attr('data-id');
        var num = $('.diseaseTab li.active').attr('data-id');
        if (num == 0) {
            if (parentId != "") {
                //诊所数量统计接口
                var url = path.path + "/given/queryGivenSummary";
                var params = getQueryPara();
                params.code = code;

                $.ajax({
                    type: "get",
                    url: url,
                    data: params,
                    cache: false,
                    async: true,
                    success: function(res) {
                        if (res.code == "000000") {
                            if (res.list) {
                                var nowData = res.list;
                                //个体诊所A病种发病总量
                                var topNode = document.getElementById('proportionOfPrescriptions');
                                var topTitleName = '总计：' + nowData[0].qty;
                                var topRequireInfo = nowData[0].type;
                                var topDataName = [];
                                topDataName.push(nowData[0].name);
                                var topDataContent = [];
                                topDataContent.push(nowData[0].qty)
                                var topMaxNum = 350;
                                echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, false, false)
                                    //个体诊所A病种发病总量

                                $("#zongShu").html(nowData[0].qty);
                            } else {
                                $("#proportionOfPrescriptions").html("数据暂无");
                                $("#zongShu").html("");
                            }

                            if (res.list && res.momList) {
                                var momData = res.momList;
                                //个体诊所A病种发病环比
                                var topNode = document.getElementById('prescriptionRing');
                                var topTitleName = '环比增长率：' + common.anPercent(momData[0].qty, nowData[0].qty);
                                var topRequireInfo = nowData[0].type;
                                var topDataName = [];
                                topDataName.push(momData[0].name);
                                topDataName.push(nowData[0].name);
                                var topDataContent = [];
                                topDataContent.push(momData[0].qty);
                                topDataContent.push(nowData[0].qty);
                                var topMaxNum = 350;
                                echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, false, false)
                                    //个体诊所A病种发病环比
                                $("#tongBi").html(common.anPercent(momData[0].qty, nowData[0].qty));
                            } else {
                                $("#prescriptionRing").html("数据暂无");
                                $("#tongBi").html("");
                            }

                            if (res.list && res.anList) {
                                var anData = res.anList;
                                //个体诊所A病种发病同比
                                var topNode = document.getElementById('prescriptionYear');
                                var topTitleName = '同比增长率：' + common.anPercent(anData[0].qty, nowData[0].qty);
                                var topRequireInfo = nowData[0].type;
                                var topDataName = [];
                                topDataName.push(anData[0].name);
                                topDataName.push(nowData[0].name);
                                var topDataContent = [];
                                topDataContent.push(anData[0].qty);
                                topDataContent.push(nowData[0].qty);
                                var topMaxNum = 450;
                                echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, false, false)
                                    //个体诊所A病种发病同比

                                $("#huanBi").html(common.anPercent(anData[0].qty, nowData[0].qty));
                            } else {
                                $("#prescriptionYear").html("数据暂无");
                                $("#huanBi").html("");
                            }

                        }


                    }
                })
            }


        }
        if (num == 1) {
            if (parentId != "") {
                //诊所数量统计接口
                var urlTop = path.path + "/given/queryGivenFeatures";
                var paramsTop = getQueryPara();
                paramsTop.code = code;

                $.ajax({
                    type: "get",
                    url: urlTop,
                    data: paramsTop,
                    cache: false,
                    async: true,
                    success: function(res) {
                        if (res.code == "000000" && res.list) {
                            var infoData = res.list;
                            //A病种发病高发地区分布
                            var numNode = document.getElementById('numDistribute');
                            var numTitleName = '';
                            var numRequireInfo = diseaseType + '种发病高发地区分布';
                            var numDataName = [];
                            var numDataContent = [];
                            for (var i = 0; i < infoData.length; i++) {
                                numDataName.push(infoData[i].name);
                                numDataContent.push(infoData[i].qty);
                            }
                            var numMaxNum = 35;
                            echart.histogram(numNode, numTitleName, numRequireInfo, numDataName, numDataContent, numMaxNum, false)
                                //A病种发病高发地区分布
                        } else {
                            $("#numDistribute").html("数据暂无")
                        }
                    }
                })

                var urlBottom = path.path + "/given/queryGivenSexOrAge";
                var paramsBottom = getQueryPara();
                paramsBottom.code2 = code2;
                paramsBottom.code1 = code;

                $.ajax({
                    type: "get",
                    url: urlBottom,
                    data: paramsBottom,
                    cache: false,
                    async: true,
                    success: function(res) {
                        if (res.code == "000000" && res.sexList && res.ageList) {
                            var sexData = res.sexList;
                            //个体诊所A病种发病性别分布
                            var gradeNode = document.getElementById('gender');
                            var gradeLegendData = ['男性', '女性'];
                            var gradeSeriesName = '个体诊所' + diseaseType + '种发病性别分布';
                            var gradeSeriesData = [{
                                name: '男性'
                            }, {
                                name: '女性'
                            }]
                            gradeSeriesData[0].value = sexData[0].qty;
                            gradeSeriesData[1].value = sexData[1].qty;
                            echart.pinChart(gradeNode, gradeLegendData, gradeSeriesName, gradeSeriesData);
                            //个体诊所A病种发病性别分布


                            var ageData = res.ageList;
                            //个体诊所A病种发病年龄分布
                            var gradeNode = document.getElementById('age');
                            var gradeLegendData = ['0-6', '7-16', '17-45', '45-60', '>=60'];
                            var gradeSeriesName = '个体诊所评级情况';
                            var gradeSeriesData = [{
                                name: '0-6'
                            }, {
                                name: '7-16'
                            }, {
                                name: '17-45'
                            }, {
                                name: '45-59'
                            }, {
                                name: '>=60'
                            }]
                            for (var i = 0; i < ageData.length; i++) {
                                gradeSeriesData[i].value = ageData[i].qty;
                            }
                            echart.pinChart(gradeNode, gradeLegendData, gradeSeriesName, gradeSeriesData);
                            //个体诊所A病种发病年龄分布

                        } else {
                            $("#gender").html("数据暂无");
                            $("#age").html("数据暂无");
                        }
                    }
                });
            }
        }
        if (num == 2) {
            if (parentId != "") {
                var listData, anData, momData, ageData, sexData, areaData;
                //诊所数量统计接口
                var urlTop = path.path + "/given/queryGivenSummary";
                var paramsTop = getQueryPara();
                paramsTop.code = code;
                $.ajax({
                    type: "get",
                    url: urlTop,
                    data: paramsTop,
                    cache: false,
                    async: false,
                    success: function(res) {
                        if (res.code == "000000") {
                            listData = res.list;
                            anData = res.anList;
                            momData = res.momList;
                        }
                    }
                })


                var urlBottom = path.path + "/given/queryGivenSexOrAge";
                var paramsBottom = getQueryPara();
                paramsBottom.code2 = code2;
                paramsBottom.code1 = code;

                $.ajax({
                    type: "get",
                    url: urlBottom,
                    data: paramsBottom,
                    cache: false,
                    async: false,
                    success: function(res) {
                        if (res.code == "000000") {
                            ageData = res.ageList;
                            sexData = res.sexList;
                        }
                    }
                });

                //诊所数量统计接口
                var urlMid = path.path + "/given/queryGivenFeatures";
                var paramsMid = getQueryPara();
                paramsMid.code = code;
                $.ajax({
                    type: "get",
                    url: urlMid,
                    data: paramsMid,
                    cache: false,
                    async: false,
                    success: function(res) {
                        if (res.code == "000000") {
                            areaData = res.list;
                        }
                    }
                });


                //信息总表填充开始
                $("#infoTotal").html("")
                var trNode = $("<tr></tr>");
                if (listData) {
                    var aTd = $("<td></td>").html(listData[0].type);
                    var totalTd = $("<td></td>").html(listData[0].qty);
                } else {
                    var aTd = $("<td></td>").html("");
                    var totalTd = $("<td></td>").html("");
                }

                if (listData && momData) {
                    var huanTd = $("<td></td>").html(common.sinPercent(listData[0].qty - momData[0].qty,momData[0].qty));
                } else {
                    var huanTd = $("<td></td>").html("无");
                }

                if (anData && listData) {
                    var tongTd = $("<td></td>").html(common.sinPercent(listData[0].qty - anData[0].qty,anData[0].qty));
                } else {
                    var tongTd = $("<td></td>").html("无");
                }

                if (sexData) {
                    var maleTd = $("<td></td>").html(sexData[0].qty);
                    var femaleTd = $("<td></td>").html(sexData[1].qty);
                } else {
                    var maleTd = $("<td></td>").html("");
                    var femaleTd = $("<td></td>").html("");
                }

                if (ageData) {
                    var td0 = $("<td></td>").html(ageData[0].qty);
                    var td7 = $("<td></td>").html(ageData[1].qty);
                    var td17 = $("<td></td>").html(ageData[2].qty);
                    var td46 = $("<td></td>").html(ageData[3].qty);
                    var td60 = $("<td></td>").html(ageData[4].qty);
                } else {
                    var td0 = $("<td></td>").html("");
                    var td7 = $("<td></td>").html("");
                    var td17 = $("<td></td>").html("");
                    var td46 = $("<td></td>").html("");
                    var td60 = $("<td></td>").html("");
                }


                if (areaData) {
                    var tmp; // 中间变量，用于交换位置时使用
                    // 冒泡排序
                    for (var j = 0; j < areaData.length - 1; j++) { // 控制比较的趟数
                        for (var i = 0; i < areaData.length - 1 - j; i++) { // 控制每趟的比较次数
                            if (Number(areaData[i].qty) < Number(areaData[i + 1].qty)) { // 不满足升序，则交换位置
                                tmp = areaData[i];
                                areaData[i] = areaData[i + 1];
                                areaData[i + 1] = tmp;
                            }
                        }
                    }
                    var tdA = $("<td></td>").html(areaData[0].name);
                    // var tdB = $("<td></td>").html(areaData[1].name);
                    // var tdC = $("<td></td>").html(areaData[2].name);
                } else {
                    var tdA = $("<td></td>").html("");
                    // var tdB = $("<td></td>").html("");
                    // var tdC = $("<td></td>").html("");
                }

                trNode.append(aTd);
                trNode.append(totalTd);
                trNode.append(huanTd);
                trNode.append(tongTd);
                trNode.append(maleTd);
                trNode.append(femaleTd);
                trNode.append(td0);
                trNode.append(td7);
                trNode.append(td17);
                trNode.append(td46);
                trNode.append(td60);
                trNode.append(tdA);
                // trNode.append(tdB);
                // trNode.append(tdC);
                $("#infoTotal").append(trNode);
                //信息总表填充结束



            }


        }
    }
    //当前概况里面子菜单的切换结束

});