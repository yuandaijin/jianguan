var parentId = "";
var areaBol = true;

//区域下拉框开始
common.dropDownList();
//区域下拉框结束

//设置年限开始
common.setYear();
common.setMonth('本月');
//设置年限结束

$(function() {
    $('#nowYear select').val('month');
    changeChoose();


    function getQueryPara() {
        var dateType = $('#nowYear select').val(),
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

    // //区域下拉框开始
    // common.dropDownList();
    // //区域下拉框结束

    //区域确认开始
    $("#areaConfirm").bind("click", function() {
        parentId = $('#areaChoose option:selected').attr('data-id');
        var chooseTab = $('.clinicInfoTab li.clinicInfoTabBTn').attr('data-id');
        if (chooseTab == 0) {
            changeChoose();
        } else if (chooseTab == 1) {
            timeTrend();
        }
    });
    $('.queryTrend').on('click', function() {
        timeTrend();
    });

    //当前概况的切换开始
    $("#nowCondition").bind("click", function() {
        $("#condition").removeClass("hide").addClass("show");
        $("#timeFoward").removeClass("show").addClass("hide");
    });
    //当前概况的切换结束
    /*******文字切换效果*****************/
    $("ul.clinicInfoTab .trendNow").click(function() {
        $(this).addClass("clinicInfoTabBTn").siblings('.trendNow').removeClass("clinicInfoTabBTn");
    });
    $("ul.area li").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
    });
    /**********文字切换效果********************/
    //时间趋势的切换开始
    $("#timeTrend").bind("click", function() {
        $("#condition").removeClass("show").addClass("hide");
        $("#timeFoward").removeClass("hide").addClass("show");
        timeTrend();
    });

    function timeTrend() {
        $(".areaShow").html($("#areaChoose").val());
        var report = $('#trendYear select').val();
        var startYear = '';
        var endYear = '';
        var startMonth = '';
        var endMonth = '';
        if (report == 'year') {
            startYear = $('.trendYearChooseStart').val();
            endYear = $('.trendYearChooseEnd').val();
            startMonth = '1';
            endMonth = '12';
            $(".showTime").html(startYear + '年至' + endYear + '年');
        } else if (report == 'month') {
            startYear = $('.trendMonthChooseYear').val();
            endYear = startYear;
            startMonth = $('.trendMonthChooseBegin').val();
            endMonth = $('.trendMonthChooseEnd').val();
            $(".showTime").html(startYear + '年' + startMonth + '月至' + endYear + '年' + endMonth + '月');
        }
        parentId = $('#areaChoose option:selected').attr('data-id');
        if (parentId != "") {
            //诊所数量统计接口
            var url = path.path + "/operate/queryTimeTrend";
            var paramsTop = {
                "startYear": startYear,
                "endYear": endYear,
                "startMonth": startMonth,
                "endMonth": endMonth,
                "parentAreaCode": parentId,
                "reportType": report,
                "sourceCode": "00044"
            };
            $.ajax({
                type: "get",
                url: url,
                data: paramsTop,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000" && res.list) {
                        var infoData = res.list;
                        //个体诊所初、复诊门诊量变化情况表开始
                        var firstData = [];
                        var secondData = [];
                        var trendXData = [];
                        for (var i = 0; i < infoData.length / 2; i++) {
                            firstData.push(infoData[i].qty);
                            secondData.push(infoData[infoData.length / 2 + i].qty);
                            trendXData.push(infoData[i].name)
                        }
                        var trendData = [{
                            name: '初诊量',
                            data: firstData
                        }, {
                            name: '复诊量',
                            data: secondData
                        }]
                        var trendNode = document.getElementById('firstAndSecond');
                        var trendTitleName = '';
                        var trendLegendData = ['初诊量', '复诊量'];
                        var trendYMax = 10000;
                        var trendSeriesInfo = echart.integrationData(trendData, false, true);
                        echart.percentHistogram(trendNode, trendTitleName, trendLegendData, trendXData, trendYMax, trendSeriesInfo)
                            //个体诊所初、复诊门诊量变化情况表结束
                    } else {
                        $("#firstAndSecond").html("数据暂无");
                    }

                }
            })

            var paramsBottom = {
                "startYear": startYear,
                "endYear": endYear,
                "startMonth": startMonth,
                "endMonth": endMonth,
                "parentAreaCode": parentId,
                "reportType": report,
                "sourceCode": "00051"
            };
            $.ajax({
                type: "get",
                url: url,
                data: paramsBottom,
                cache: false,
                async: true,
                success: function(res) {
                    console.log(res);
                    if (res.code == "000000" && res.list) {
                        var infoData = res.list;
                        //个体诊所中西医门诊量变化情况表开始
                        var firstData = [];
                        var secondData = [];
                        var trendXData = [];
                        for (var i = 0; i < infoData.length / 2; i++) {
                            firstData.push(infoData[infoData.length / 2 + i].qty);
                            secondData.push(infoData[i].qty);
                            trendXData.push(infoData[i].name)
                        }
                        var trendData = [{
                            name: '中医',
                            data: firstData
                        }, {
                            name: '西医',
                            data: secondData
                        }]
                        var trendNode = document.getElementById('eastAndWest');
                        var trendTitleName = '';
                        var trendLegendData = ['中医', '西医'];
                        var trendYMax = 10000;
                        var trendSeriesInfo = echart.integrationData(trendData, false, true);
                        echart.percentHistogram(trendNode, trendTitleName, trendLegendData, trendXData, trendYMax, trendSeriesInfo)
                            //个体诊所中西医门诊量变化情况表结束
                    } else {
                        $("#eastAndWest").html("数据暂无");
                    }

                }
            })

        }
    }
    //时间趋势的切换结束

    //当前概况下的时间切换开始
    $("#nowYear select").on("change", function() {
        if ($(this).val() == 'year') {
            $("#nowYearChoose").show().siblings('#nowMonthChoose').hide();
        } else if ($(this).val() == 'month') {
            $("#nowMonthChoose").show().siblings('#nowYearChoose').hide();
        }
    });
    //当前概况下的时间切换结束

    //时间趋势下的时间切换开始
    $("#trendYear select").on("change", function() {
        if ($(this).val() == 'year') {
            $("#trendYearChoose").show().siblings('#trendMonthChoose').hide();
        } else if ($(this).val() == 'month') {
            $("#trendMonthChoose").show().siblings('#trendYearChoose').hide();
        }
    });

    //当前概况下的时间选择
    $('.chooseByTime').on('click', function() {
        changeChoose();
    });
    //当前概况下的菜单切换开始
    $(".area>li").click(function() {
        $(".areaList").eq($(this).index()).addClass('show').removeClass('hide').siblings('.areaList').addClass('hide').removeClass('show');
        changeChoose();
    });

    function changeChoose() {
        $('#infoTotal').empty().append('<tr><td>暂无数据</td></tr>')
        $(".areaShow").html($("#areaChoose").val());
        var report = $('#nowYear select').val();
        var year = '';
        var month = '';
        if (report == 'year') {
            year = $('.nowChooseYear').val();
            month = '0';
            $(".showTime").html(year + '年');
        } else if (report == 'month') {
            year = $('.nowMonthChooseYear').val();
            month = $('.nowMonthChooseMonth').val();
            $(".showTime").html(year + '年' + month + '月');
        }
        var chooseNum = 0;
        if ($('.areaTotal').hasClass('active')) {
            chooseNum = 0;
        } else if ($('.areaNext').hasClass('active')) {
            chooseNum = 1;
        } else if ($('.areaAvg').hasClass('active')) {
            chooseNum = 2;
        } else if ($('.areaInfo').hasClass('active')) {
            chooseNum = 3;
        }
        //辖区总体概况模块开始
        if (chooseNum == 0) {
            parentId = $('#areaChoose option:selected').attr('data-id');
            if (parentId != "") {
                //诊所数量统计接口
                var url = path.path + "/operate/queryVisit";
                var paramsTop = {
                    "year": year,
                    "month": month,
                    "reportType": report,
                    "parentAreaCode": parentId,
                    "sourceCode": "00044"
                };
                $.ajax({
                    type: "get",
                    url: url,
                    data: paramsTop,
                    cache: false,
                    async: true,
                    success: function(res) {
                        if (res.code == "000000") {
                            if (res.list) {
                                var listData = res.list;
                                //个体诊所初、复诊门诊量总计表开始
                                var situationData = [];
                                var situationLegendData = [];
                                var situationXData = [];
                                var totalNum = 0;
                                for (var i = 0; i < listData.length; i++) {
                                    var obj = {};
                                    obj.name = listData[i].type;
                                    obj.data = [];
                                    obj.data.push(listData[i].qty);
                                    situationData.push(obj);

                                    situationLegendData.push(listData[i].type);

                                    totalNum += parseInt(listData[i].qty);

                                }
                                situationXData.push(listData[0].name);
                                var situationNode = document.getElementById('situationNum');
                                var situationTitleName = '总计：' + totalNum + '人';
                                var situationYMax = 450000;
                                var situationSeriesInfo = echart.integrationData(situationData, true, true);
                                echart.percentHistogram(situationNode, situationTitleName, situationLegendData, situationXData, situationYMax, situationSeriesInfo);
                                //个体诊所初、复诊门诊量总计表结束

                                $("#chuData").html(listData[0].qty);
                                $("#fuData").html(listData[1].qty);
                                $("#menTotal").html(totalNum);

                            } else {
                                $("#situationNum").html("数据暂无");

                                $("#chuData").html("");
                                $("#fuData").html("");
                                $("#menTotal").html("");
                            }


                            if (res.list && res.momlist) {
                                var momData = res.momlist;
                                //个体诊所初、复诊总门诊量环比表开始
                                var relativeData = [];
                                var relativeLegendData = [];
                                var relativeXData = [];
                                for (var i = 0; i < momData.length; i++) {
                                    var obj = {};
                                    obj.name = momData[i].type;
                                    obj.data = [];
                                    obj.data.push(momData[i].qty);
                                    obj.data.push(listData[i].qty);
                                    relativeData.push(obj);

                                    relativeLegendData.push(momData[i].type);

                                };
                                relativeXData.push(momData[0].name);
                                relativeXData.push(listData[0].name);
                                var relativeNode = document.getElementById('situationRelative');
                                var nowArr = [parseInt(listData[0].qty), parseInt(listData[1].qty)];
                                var momArr = [parseInt(momData[0].qty), parseInt(momData[1].qty)];
                                var relativeTitleName = '环比增长率： ' + common.relativePercent(momArr, nowArr) + '%';
                                var relativeYMax = 600000;
                                var relativeSeriesInfo = echart.integrationData(relativeData, false, true);
                                echart.percentHistogram(relativeNode, relativeTitleName, relativeLegendData, relativeXData, relativeYMax, relativeSeriesInfo)
                                    //个体诊所初、复诊总门诊量环比表结束

                                $("#huanData").html(common.relativePercent(momArr, nowArr) + '%');
                                $("#huanAdd").html(common.anPercent(momData[0].qty, listData[0].qty));
                                $("#lvAdd").html(common.anPercent(momData[1].qty, listData[1].qty));

                            } else {
                                $("#situationRelative").html("数据暂无");

                                $("#huanData").html("");
                                $("#huanAdd").html("");
                                $("#lvAdd").html("");
                            }


                            if (res.list && res.anlist) {
                                var anlistData = res.anlist;
                                var compareData = [];
                                var compareLegendData = [];
                                var compareXData = [];
                                for (var i = 0; i < anlistData.length; i++) {
                                    var obj = {};
                                    obj.name = anlistData[i].type;
                                    obj.data = [];
                                    obj.data.push(anlistData[i].qty);
                                    obj.data.push(listData[i].qty);
                                    compareData.push(obj);

                                    compareLegendData.push(anlistData[i].type);

                                };
                                compareXData.push(anlistData[0].name);
                                compareXData.push(listData[0].name);
                                var compareNode = document.getElementById('situationCompare');
                                var nowArr = [parseInt(listData[0].qty), parseInt(listData[1].qty)];
                                var anlistArr = [parseInt(anlistData[0].qty), parseInt(anlistData[1].qty)];
                                var compareTitleName = '同比增长率： ' + common.relativePercent(anlistArr, nowArr) + '%';
                                var compareYMax = 600000;
                                var compareSeriesInfo = echart.integrationData(compareData, false, true);
                                echart.percentHistogram(compareNode, compareTitleName, compareLegendData, compareXData, compareYMax, compareSeriesInfo)
                                    //个体诊所初、复诊总门诊量同比表结束

                                $("#tongData").html(common.relativePercent(anlistArr, nowArr) + '%');
                                $("#tongAdd").html(common.anPercent(anlistData[0].qty, listData[0].qty));
                                $("#zhangAdd").html(common.anPercent(anlistData[1].qty, listData[1].qty));

                            } else {
                                $("#situationCompare").html("数据暂无");

                                $("#tongData").html("");
                                $("#tongAdd").html("");
                                $("#zhangAdd").html("");
                            }


                        }


                    }
                })

                var paramsBottom = {
                    "year": year,
                    "month": month,
                    "parentAreaCode": parentId,
                    "reportType": report,
                    "sourceCode": "00051"
                };

                $.ajax({
                    type: "get",
                    url: url,
                    data: paramsBottom,
                    cache: false,
                    async: true,
                    success: function(res) {
                        if (res.code == "000000") {
                            if (res.list) {
                                var nowData = res.list;
                                //个体诊所中、西医门诊量总计表开始
                                var proData = [{
                                    name: '西医',
                                    data: [nowData[0].qty]
                                }, {
                                    name: '中医',
                                    data: [nowData[1].qty]
                                }]
                                var proNumNode = document.getElementById('professionNum');
                                var sumNum = parseInt(nowData[0].qty) + parseInt(nowData[1].qty);
                                var proNumTitleName = '总计：' + sumNum + '人';
                                var proNumLegendData = ['西医', '中医'];
                                var proNumXData = [nowData[0].name];
                                var proNumYMax = 600000;
                                var proNumSeriesInfo = echart.integrationData(proData, true, true);
                                echart.percentHistogram(proNumNode, proNumTitleName, proNumLegendData, proNumXData, proNumYMax, proNumSeriesInfo)
                                    //个体诊所中、西医门诊量总计表结束

                                $("#zhongData").html(nowData[1].qty);
                                $("#xiData").html(nowData[0].qty);

                            } else {
                                $("#professionNum").html("数据暂无");

                                $("#zhongData").html("");
                                $("#xiData").html("");
                            }


                            if (res.list && res.momlist) {
                                var momData = res.momlist;
                                //个体诊所中、西医总门诊量环比表开始
                                var proRelData = [{
                                    name: '西医',
                                    data: [momData[0].qty, nowData[0].qty]
                                }, {
                                    name: '中医',
                                    data: [momData[1].qty, nowData[1].qty]
                                }]
                                var proRelNode = document.getElementById('professionRelative');
                                var proRelTitleName = '环比增长率： ' + common.relativePercent([momData[0].qty, momData[1].qty], [nowData[0].qty, nowData[1].qty]) + '%';
                                var proRelLegendData = ['西医', '中医'];
                                var proRelXData = [momData[0].name, nowData[0].name];
                                var proRelYMax = 600000;
                                var proRelSeriesInfo = echart.integrationData(proRelData, false, true);
                                echart.percentHistogram(proRelNode, proRelTitleName, proRelLegendData, proRelXData, proRelYMax, proRelSeriesInfo)
                                    //个体诊所中、西医总门诊量环比表结束

                                $("#zhonghuan").html(common.anPercent(momData[1].qty, nowData[1].qty));
                                $("#xihuan").html(common.anPercent(momData[0].qty, nowData[0].qty));

                            } else {
                                $("#professionRelative").html("数据暂无");

                                $("#zhonghuan").html("");
                                $("#xihuan").html("");
                            }


                            if (res.list && res.anlist) {
                                var anData = res.anlist;
                                //个体诊所中、西医总门诊量同比表开始
                                var proRelData = [{
                                    name: '西医',
                                    data: [anData[0].qty, nowData[0].qty]
                                }, {
                                    name: '中医',
                                    data: [anData[1].qty, nowData[1].qty]
                                }]
                                var proComNode = document.getElementById('professionCompare');
                                var proComTitleName = '同比增长率： ' + common.relativePercent([anData[0].qty, anData[1].qty], [nowData[0].qty, nowData[1].qty]) + '%';
                                var proComLegendData = ['西医', '中医'];
                                var proComXData = [anData[0].name, nowData[0].name];
                                var proComYMax = 600000;
                                var proComSeriesInfo = echart.integrationData(proRelData, false, true);
                                echart.percentHistogram(proComNode, proComTitleName, proComLegendData, proComXData, proComYMax, proComSeriesInfo)
                                    //个体诊所中、西医总门诊量同比表结束

                                $("#zhongtong").html(common.anPercent(anData[1].qty, nowData[1].qty));
                                $("#xitong").html(common.anPercent(anData[0].qty, nowData[0].qty));

                            } else {
                                $("#professionCompare").html("数据暂无");

                                $("#zhongtong").html("");
                                $("#xitong").html("");
                            }



                        }



                    }
                })

            }
        }
        //辖区总体概况模块结束

        //下级区划概况开始
        if (chooseNum == 1) {

            parentId = $('#areaChoose option:selected').attr('data-id');
            if (parentId != "") {
                //诊所数量统计接口
                var url = path.path + "/operate/querySubordinate";
                var paramsTop = {
                    "year": year,
                    "month": month,
                    "reportType": report,
                    "parentAreaCode": parentId,
                    "sourceCode": "00044"
                };
                $.ajax({
                    type: "get",
                    url: url,
                    data: paramsTop,
                    cache: false,
                    async: true,
                    success: function(res) {
                        if (res.code == "000000" && res.list) {
                            var infoData = res.list;
                            //各下级行政区划个体诊所初、复诊门诊量情况表开始
                            var firstData = [];
                            var secondData = [];
                            var trendXData = [];
                            for (var i = 0; i < infoData.length / 2; i++) {
                                firstData.push(parseInt(infoData[i*2].qty));
                                secondData.push(parseInt(infoData[i*2+1].qty));
                                trendXData.push(infoData[i*2].name)
                            }
                            var trendData = [{
                                name: '初诊',
                                data: firstData
                            }, {
                                name: '复诊',
                                data: secondData
                            }]
                            var trendNode = document.getElementById('trendChart');
                            var trendTitleName = '';
                            var trendLegendData = ['初诊', '复诊'];
                            var trendYMax = 10000;
                            var trendSeriesInfo = echart.integrationData(trendData, false, true);
                            echart.percentHistogram(trendNode, trendTitleName, trendLegendData, trendXData, trendYMax, trendSeriesInfo)
                                //各下级行政区划个体诊所初、复诊门诊量情况表结束

                        } else {
                            $("#trendChart").html("数据暂无");
                        }
                    }
                })

                var paramsTop = {
                    "year": year,
                    "month": month,
                    "reportType": report,
                    "parentAreaCode": parentId,
                    "sourceCode": "00051"
                };
                $.ajax({
                    type: "get",
                    url: url,
                    data: paramsTop,
                    cache: false,
                    async: true,
                    success: function(res) {
                        //各下级行政区划个体诊所中、西医门诊量情况表开始
                        if (res.code == "000000" && res.list) {
                            var infoData = res.list;
                            var firstData = [];
                            var secondData = [];
                            var trendXData = [];
                            for (var i = 0; i < infoData.length / 2; i++) {
                                firstData.push(parseInt(infoData[i*2+1].qty));
                                secondData.push(parseInt(infoData[i*2].qty));
                                trendXData.push(infoData[i*2].name)
                            }
                            var trendData = [{
                                name: '中医',
                                data: firstData
                            }, {
                                name: '西医',
                                data: secondData
                            }]
                            var trendNode = document.getElementById('chinaAndForeign');
                            var trendTitleName = '';
                            var trendLegendData = ['中医', '西医'];
                            var trendYMax = 10000;
                            var trendSeriesInfo = echart.integrationData(trendData, false, true);
                            echart.percentHistogram(trendNode, trendTitleName, trendLegendData, trendXData, trendYMax, trendSeriesInfo)

                        } else {
                            $("#chinaAndForeign").html("数据暂无");
                        }
                        //各下级行政区划个体诊所中、西医门诊量情况表结束

                    }
                })

            }

        }
        //下级区划概况结束

        //平均门诊量概况开始
        if (chooseNum == 2) {
            parentId = $('#areaChoose option:selected').attr('data-id');

            if (parentId != "") {
                //诊所数量统计接口
                var url = path.path + "/operate/queryAveOutpatient";
                var paramsTop = {
                    "year": year,
                    "month": month,
                    "reportType": report,
                    "parentAreaCode": parentId
                };
                $.ajax({
                    type: "get",
                    url: url,
                    data: paramsTop,
                    cache: false,
                    async: true,
                    success: function(res) {
                        if (res.code == "000000" && res.list) {
                            var infoData = res.list;
                            //各下级行政区划个体诊所平均门诊量表开始
                            var numDataName = [];
                            var numDataContent = [];
                            for (var i = 0; i < infoData.length; i++) {
                                numDataName.push(infoData[i].name);
                                numDataContent.push(infoData[i].qty);
                            }
                            var numNode = document.getElementById('numDistribute');
                            var numTitleName = '';
                            var numRequireInfo = '各下级行政区划诊所数量分布';
                            var numMaxNum = 20;
                            echart.histogram(numNode, numTitleName, numRequireInfo, numDataName, numDataContent, numMaxNum, false)
                                //各下级行政区划个体诊所平均门诊量表结束

                        } else {
                            $("#numDistribute").html("数据暂无");
                        }
                    }
                })

            }

        }
        //平均门诊量概况结束

        //信息总表开始
        if (chooseNum == 3) {
            parentId = $('#areaChoose option:selected').attr('data-id');
            if (parentId != "") {
                //诊所数量统计接口
                var url = path.path + "/operate/queryTableInfo";
                var paramsTop = {
                    "year": year,
                    "month": month,
                    "reportType": report,
                    "parentAreaCode": parentId
                };
                $.ajax({
                    type: "get",
                    url: url,
                    data: paramsTop,
                    cache: false,
                    async: true,
                    success: function(res) {
                        console.log(res);
                        if (res.code == "000000" && res.resultList) {
                            var infoData = res.resultList;
                            var str = '	<tr class="tableHead"> <td rowspan="2">行政区域规划</td> <td rowspan="2">门诊总量(人次)</td> <td colspan="2">就诊类型</td> <td colspan="2">就诊类型</td> <td rowspan="2">平均人员数量</td> </tr> <tr class="tableHead"> <td>初诊</td> <td>复诊</td> <td>西医门诊</td> <td>中医门诊</td> </tr>';
                            for (var i = 0; i < infoData[0].length; i++) {
                                str += '<tr><td>' + infoData[0][i].name + '</td><td>' + infoData[1][i].qty + '</td><td>' + infoData[2][i].qty + '</td><td>' + infoData[3][i].qty + '</td>' +
                                    '<td>' + infoData[4][i].qty + '</td><td>' + infoData[5][i].qty + '</td><td>' + (infoData[0][i].qty || infoData[1][i].qty ? 0 : parseInt(infoData[1][i].qty / infoData[0][i].qty)) + '</td></tr>'
                            }
                            $("#infoTotal").empty().append(str);

                        } else {
                            $("#infoTotal").html("")
                        }
                    }
                })
            }

        }
        //信息总表结束

    }
    //当前概况下的菜单切换结束

})