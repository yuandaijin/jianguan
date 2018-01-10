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
    $(".chooseByYear select").val('month');
    //时间选择的切换开始
    $(".chooseByYear select").on("change", function() {
        if ($(this).val() == 'year') {
            $(".yearY").show().siblings('.timeList').hide();
        } else if ($(this).val() == 'quarter') {
            $(".quarterY").show().siblings('.timeList').hide();
        } else if ($(this).val() == 'month') {
            $(".monthY").show().siblings('.timeList').hide();
        } else if ($(this).val() == 'day') {
            $(".dayY").show().siblings('.timeList').hide();
        }
    });
    //时间选择的切换结束
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
            year = $('.quarterChooseYear').val();
            startMonth = quarter.split(',')[0];
            endMonth = quarter.split(',')[2];
            day = '';
        } else if (dateType === 'month') {
            day = '';
            quarter = '';
            endMonth = '';
            year = $('.monthChooseYear').val();
            startMonth = $('.monthChooseMonth').val();
        } else if (dateType === 'day') {
            quarter = '';
            endMonth = '';
            year = $('.dayChooseYear').val();
            startMonth = $(".dayChooseMonth").val();
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
    $("#areaConfirm").on("click", function() {
        changeTab();
    });
    $(".chooseByTime").on("click", function() {
        changeTab();
    });
    //区域确认结束




    //子菜单的切换开始
    $(".area>li").on("click", function() {
        $(".areaList").eq($(this).index()).removeClass("hide").addClass("show").siblings('.areaList').removeClass('show').addClass('hide');
        $(this).addClass('active').siblings('li').removeClass('active');
        parentId = $('#areaChoose option:selected').attr('data-id');
        changeTab()
    });

    function setCurrentTimeText() {
        var reportType = $('.chooseByYear select').val(),
            startMonth,
            year,
            day;
        if (reportType == 'year') {
            year = $('.yearChooseYear').val();
            $('.timeShow').html(year + '年');
        } else if (reportType == 'quarter') {
            year = $('.quarterChooseYear').val();
            $('.timeShow').html(year + '年' + $('.quarterChooseQuater').find("option:selected").text());
        } else if (reportType == 'month') {
            year = $('.monthChooseYear').val();
            startMonth = $('.monthChooseMonth').val();
            $('.timeShow').html(year + '年' + startMonth + '月');
        } else if (reportType == 'day') {
            year = $('.monthChooseYear').val();
            startMonth = $('.monthChooseMonth').val();
            day = $('.daySelected').val();
            $('.timeShow').html(year + '年' + startMonth + '月' + day + '日');
        }
    }

    function getAxisXLabels(list) {
        var result = [];
        for (var i = 0, ii = list.length; i < ii; i++) {
            if (result.indexOf(list[i].name) < 0) {
                result.push(list[i].name);
            }
        }
        return result;
    }

    function getCategories(list) {
        var result = [];
        for (var i = 0, ii = list.length; i < ii; i++) {
            if (result.indexOf(list[i].type) < 0) {
                result.push(list[i].type);
            }
        }
        return result;
    }

    function getSeriesData(list, totalList) {
        var result = [],
            item,
            labels = getAxisXLabels(list),
            categories = getCategories(list);
        for (var i = 0, ii = categories.length; i < ii; i++) {
            item = {
                name: categories[i] + '(%)',
                data: []
            }

            for (var j = 0, jj = labels.length; j < jj; j++) {
                var totalItem = $.grep(totalList, function(totalItem) { return totalItem.name === labels[j]; })[0];
                if (totalItem) {
                    var dataItem = $.grep(list, function(valueItem) { return valueItem.name === labels[j] && valueItem.type === categories[i]; })[0];
                    if (dataItem) {
                        item.data.push(danPercent(dataItem.qty, totalItem.qty))
                    } else {
                        item.data.push(0);
                    }

                }
            }
            result.push(item);
        }

        return result;
    }

    function changeTab() {
        $(".areaShow").html($("#areaChoose").val());
        parentId = $('#areaChoose option:selected').attr('data-id');

        setCurrentTimeText();
        var num = $('.nowSituation li.active').attr('data-id');
        if (num == 0) {
            if (parentId != "") {
                //诊所数量统计接口
                var url = path.path + "/residents/queryDiseases";
                var params = getQueryPara();
                $.ajax({
                    type: "get",
                    url: url,
                    data: params,
                    cache: false,
                    async: true,
                    success: function(res) {
                        if (res.code == "000000" && res.list) {
                            var infoData = res.list;
                            //个体诊所就诊疾病前十病种分布概况
                            var topDataName = [];
                            var topDataContent = [];
                            for (var i = 0; i < infoData.length; i++) {
                                topDataName.push(infoData[i].name);
                                topDataContent.push(danPercent(infoData[i].qty, res.sum));
                            }
                            var topNode = document.getElementById('topTen');
                            var topTitleName = '';
                            var topRequireInfo = '个体诊所就诊疾病前十病种分布概况';
                            var topMaxNum = 25;
                            echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
                                //个体诊所就诊疾病前十病种分布概况
                        } else {
                            $("#topTen").html("数据暂无");
                        }
                    }
                })
            }


        }
        if (num == 1) {
            if (parentId != "") {
                //诊所数量统计接口
                var urlTop = path.path + "/residents/queryFirstTen";
                var params = getQueryPara();
                $.ajax({
                    type: "get",
                    url: urlTop,
                    data: params,
                    cache: false,
                    async: true,
                    success: function(res) {
                        if (res.code == "000000" && res.list) {
                            var infoData = res.list;
                            var totalData = res.sumlist;
                            ///var num00 = infoData.length / 10;

                            //就诊疾病前十位病种表开始
                            var moreobj = document.getElementById("topTenChart");
                            // //var moreXData = [];
                            // var moreContent = [];
                            // // for (var i = 0; i < 10; i++) {
                            // //     moreXData.push(infoData[i * num00].type);
                            // // }
                            // for (var j = 0; j < num00; j++) {
                            //     var obj = {};
                            //     obj.name = infoData[j].name + '(%)';
                            //     obj.data = [];
                            //     obj.total = [];
                            //     for (var k = 0; k < 10; k++) {
                            //         //obj.data.push(infoData[k*num00+j].qty);
                            //         //obj.data.push(infoData[k*num00+j].qty);
                            //         //obj.total.push(totalData[k].qty);
                            //         obj.data.push(danPercent(infoData[k * num00 + j].qty, totalData[k].qty));
                            //     }
                            //     moreContent.push(obj);
                            // }
                            var moreData = moreBarSeries(getSeriesData(infoData, totalData));
                            //var moreData = echart.integrationData(moreContent, false, true);
                            moreBar(moreobj, getAxisXLabels(infoData), moreData);
                            //就诊疾病前十位病种表结束

                        } else {
                            $("#topTenChart").html("数据暂无");
                        }
                    }
                });


                var urlBottom = path.path + "/residents/queryFirstTenType";
                $.ajax({
                    type: "get",
                    url: urlBottom,
                    data: params,
                    cache: false,
                    async: true,
                    success: function(res) {
                        if (res.code == "000000" && res.sexList && res.ageList) {
                            var sexData = res.sexList;

                            var infoArr = [];
                            for (var j = 0; j < sexData.length / 2; j++) {
                                infoArr[j] = [];
                                infoArr[j].push(sexData[2 * j + 0]);
                                infoArr[j].push(sexData[2 * j + 1]);
                            }

                            var tmp; // 中间变量，用于交换位置时使用

                            // 冒泡排序
                            for (var l = 0; l < infoArr.length - 1; l++) { // 控制比较的趟数
                                for (var m = 0; m < infoArr.length - 1 - l; m++) { // 控制每趟的比较次数
                                    if ((parseInt(infoArr[m][0].qty) + parseInt(infoArr[m][1].qty)) < (parseInt(infoArr[m + 1][0].qty) + parseInt(infoArr[m + 1][1].qty))) { // 不满足升序，则交换位置
                                        tmp = infoArr[m];
                                        infoArr[m] = infoArr[m + 1];
                                        infoArr[m + 1] = tmp;
                                    }
                                }
                            }

                            //个体诊所就诊疾病前十病种性别分布
                            var compareXData = [];
                            var maleData = [];
                            var femaleData = [];
                            // var num1=sexData.length/2;
                            // for(var i=0; i<num1; i++){
                            //     compareXData.push(sexData[i].name);
                            //     maleData.push(sexData[num1+i].qty);
                            //     femaleData.push(sexData[i].qty);
                            // }

                            for (var i = 0; i < infoArr.length; i++) {
                                compareXData.push(infoArr[i][0].name);
                                maleData.push(infoArr[i][0].qty);
                                femaleData.push(infoArr[i][1].qty);
                            }

                            var compareData = [{
                                name: '男',
                                data: maleData
                            }, {
                                name: '女',
                                data: femaleData
                            }]
                            var compareLegendData = ['男', '女'];
                            var compareNode = document.getElementById('situationCompare');
                            var compareTitleName = '';
                            var compareYMax = 25000;
                            var compareSeriesInfo = echart.integrationData(compareData, false, true);
                            echart.percentHistogram(compareNode, compareTitleName, compareLegendData, compareXData, compareYMax, compareSeriesInfo)
                                //个体诊所就诊疾病前十病种性别分布


                            //个体诊所就诊疾病前十病种年龄分布
                            var ageData = res.ageList;

                            var ageArr = [];
                            for (var a = 0; a < ageData.length / 5; a++) {
                                ageArr[a] = [];
                                ageArr[a].push(ageData[5 * a + 0]);
                                ageArr[a].push(ageData[5 * a + 1]);
                                ageArr[a].push(ageData[5 * a + 2]);
                                ageArr[a].push(ageData[5 * a + 3]);
                                ageArr[a].push(ageData[5 * a + 4]);
                            }
                            // 冒泡排序
                            for (var b = 0; b < ageArr.length - 1; b++) { // 控制比较的趟数
                                for (var c = 0; c < ageArr.length - 1 - b; c++) { // 控制每趟的比较次数
                                    if (addArr([ageArr[c][0].qty, ageArr[c][1].qty, ageArr[c][2].qty, ageArr[c][3].qty, ageArr[c][4].qty]) < addArr([ageArr[c + 1][0].qty, ageArr[c + 1][1].qty, ageArr[c + 1][2].qty, ageArr[c + 1][3].qty, ageArr[c + 1][4].qty])) { // 不满足升序，则交换位置
                                        tmp = ageArr[c];
                                        ageArr[c] = ageArr[c + 1];
                                        ageArr[c + 1] = tmp;
                                    }
                                }
                            }
                            var compareXData = [];
                            var age0 = [],
                                age7 = [],
                                age17 = [],
                                age46 = [],
                                age60 = [];
                            var num2 = ageData.length / 5;
                            for (var k = 0; k < ageArr.length; k++) {
                                compareXData.push(ageArr[k][0].name);
                                age0.push(ageArr[k][0].qty);
                                age7.push(ageArr[k][1].qty);
                                age17.push(ageArr[k][2].qty);
                                age46.push(ageArr[k][3].qty);
                                age60.push(ageArr[k][4].qty);
                            }
                            var compareData = [{
                                name: '0-6,N(%)',
                                data: age0
                            }, {
                                name: '7-16,N(%)',
                                data: age7
                            }, {
                                name: '17-45,N(%)',
                                data: age17
                            }, {
                                name: '46-59,N(%)',
                                data: age46
                            }, {
                                name: '>=60,N(%)',
                                data: age60
                            }]
                            var compareNode = document.getElementById('diseaseAge');
                            var compareTitleName = '';
                            var compareLegendData = ['0-6,N(%)', '7-16,N(%)', '17-45,N(%)', '46-59,N(%)', '>=60,N(%)'];
                            var compareYMax = 25000;
                            var compareSeriesInfo = echart.integrationData(compareData, false, true);
                            echart.percentHistogram(compareNode, compareTitleName, compareLegendData, compareXData, compareYMax, compareSeriesInfo)
                                //个体诊所就诊疾病前十病种年龄分布


                        } else {
                            $("#situationCompare").html("数据暂无");
                            $("#diseaseAge").html("数据暂无");
                        }
                    }
                })



            }


        }
        if (num == 2) {
            if (parentId != "") {
                //诊所数量统计接口
                var areaData, ageData, sexData;
                var urlTop = path.path + "/residents/queryFirstTen";
                var params = getQueryPara();
                // $.ajax({
                //     type: "get",
                //     url: urlTop,
                //     data: params,
                //     cache: false,
                //     async: false,
                //     success: function(res) {
                //         if (res.code == "000000") {
                //             areaData = res.list;
                //         }
                //     }
                // });


                var urlBottom = path.path + "/residents/queryFirstTenType";
                $.ajax({
                    type: "get",
                    url: urlBottom,
                    data: params,
                    cache: false,
                    async: false,
                    success: function(res) {
                        if (res.code == "000000") {
                            ageData = res.ageList;
                            sexData = res.sexList;
                        }
                    }
                });


                if ( ageData && sexData) {
                    // var num=areaData.length;
                    // var str = '';
                    // for(var i=0; i<num; i++){
                    //     str+='<tr><td>'+areaData[i].type+'</td><td>'+(parseInt(sexData[i].qty)+parseInt(sexData[num+i].qty))+'</td><td>'+sexData[i].qty+'</td>' +
                    //         '<td>'+sexData[num+i].qty+'</td><td>'+ageData[i].qty+'</td><td>'+ageData[num+i].qty+'</td><td>'+ageData[num*2+i].qty+'</td>' +
                    //         '<td>'+ageData[num*3+i].qty+'</td><td>'+ageData[num*4+i].qty+'</td><td>'+areaData[i].name+'</td><td></td><td></td></tr>'
                    // }
                    // $("#infoTotal").empty().append(str);
                    console.log(ageData);
                    console.log(sexData);
                    // var num00 = areaData.length / 10;
                    var nodeArr = [];
                    $("#infoTotal").html("");
                    for (var i = 0; i < 10; i++) {
                        var trNode = $("<tr></tr>");
                        var binTd = $("<td></td>").html(sexData[2 * i].name);
                        var zongTd = $("<td></td>").html(parseInt(sexData[2 * i + 0].qty) + parseInt(sexData[2 * i + 1].qty));
                        var maleTd = $("<td></td>").html(sexData[2 * i + 0].qty);
                        var femaleTd = $("<td></td>").html(sexData[2 * i + 1].qty);
                        var td0 = $("<td></td>").html(ageData[5 * i + 0].qty);
                        var td7 = $("<td></td>").html(ageData[5 * i + 1].qty);
                        var td17 = $("<td></td>").html(ageData[5 * i + 2].qty);
                        var td46 = $("<td></td>").html(ageData[5 * i + 3].qty);
                        var td60 = $("<td></td>").html(ageData[5 * i + 4].qty);
                        // if (num00 == 3) {
                        //     var area1 = $("<td></td>").html(areaData[num00 * i + 0].name);
                        //     var area2 = $("<td></td>").html(areaData[num00 * i + 1].name);
                        //     var area3 = $("<td></td>").html(areaData[num00 * i + 2].name);
                        // } else if (num00 == 2) {
                        //     var area1 = $("<td></td>").html(areaData[num00 * i + 0].name);
                        //     var area2 = $("<td></td>").html(areaData[num00 * i + 1].name);
                        //     var area3 = $("<td></td>").html("");
                        // } else if (num00 == 1) {
                        //     var area1 = $("<td></td>").html(areaData[num00 * i + 0].name);
                        //     var area2 = $("<td></td>").html("");
                        //     var area3 = $("<td></td>").html("");
                        // } else {
                        //     var area1 = $("<td></td>").html("");
                        //     var area2 = $("<td></td>").html("");
                        //     var area3 = $("<td></td>").html("");
                        // }
                        trNode.append(binTd);
                        trNode.append(zongTd);
                        trNode.append(maleTd);
                        trNode.append(femaleTd);
                        trNode.append(td0);
                        trNode.append(td7);
                        trNode.append(td17);
                        trNode.append(td46);
                        trNode.append(td60);
                        // trNode.append(area1);
                        // trNode.append(area2);
                        // trNode.append(area3);

                        nodeArr.push(trNode);
                    }
                    // 冒泡排序
                    for (var b = 0; b < nodeArr.length - 1; b++) { // 控制比较的趟数
                        for (var c = 0; c < nodeArr.length - 1 - b; c++) { // 控制每趟的比较次数
                            if (Number(nodeArr[c][0].cells[1].innerHTML) < Number(nodeArr[c + 1][0].cells[1].innerHTML)) { // 不满足升序，则交换位置
                                tmp = nodeArr[c];
                                nodeArr[c] = nodeArr[c + 1];
                                nodeArr[c + 1] = tmp;
                            }
                        }
                    }
                    for (var d = 0; d < nodeArr.length; d++) {
                        $("#infoTotal").append(nodeArr[d]);
                    }

                } else {
                    $("#infoTotal").html("");
                }




            }


        }
    }
    //子菜单的切换结束
});




//配合多柱图使用的函数开始
function moreBarSeries(arr) {
    var result = [];
    var color = ['#2a91cc', '#62c483', '#84a2d9', '#14c9c9', '#c298d4'];
    for (var i = 0; i < arr.length; i++) {
        var obj = {};
        obj.name = arr[i].name;
        obj.type = "bar";
        obj.data = arr[i].data;
        obj.barWidth = '50';
        obj.stack = '总量';
        obj.barMinHeight = 0;
        obj.barMinWidth = 0;
        obj.itemStyle = {
            normal: {

            }
        };
        obj.label = {
            normal: {
                show: true,
                position: 'inside',
                formatter: function(params) {
                    if (params) {
                        if (!params.value) {
                            return '';
                        }
                        return params.value + '%';
                    }
                },
                textStyle: {
                    color: '#000',
                    fontSize: 10
                }
            }
        }
        obj.itemStyle = {};
        obj.itemStyle.normal = {};
        obj.itemStyle.normal.color = color[i];
        result.push(obj);
    }
    return result;
}
// 配合多柱图使用的函数结束

// itemStyle: {
//                     normal: {
//                         barBorderColor: 'rgba(0,0,0,0)',
//                         color: 'blue'
//                     }
//                 },

//多柱表的封装开始
function moreBar(obj, xData, seriesData) {
    var myChart = echarts.init(obj);

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            },
            // add by clark 
            formatter: function(params) {
                var htmlArr = ['<div style="color:#fff;">' + params[0].name + '</div>'];
                if (params) {
                    for (var i = 0, ii = params.length; i < ii; i++) {
                        if (params[i].value) {
                            htmlArr.push('<div style="color:#fff;">' + params[i].seriesName + ':' + params[i].value + '%</div>');
                        }
                    }

                }
                return htmlArr.join('');
            }
        },
        grid: {
            left: '8%',
            right: '8%',
            bottom: '17%',
            top: '12%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: xData
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        series: seriesData
    };
    if (xData.length >= 10) {
        option.xAxis.axisLabel = { 'interval': 0, rotate: -45, }
    }

    myChart.setOption(option);

}
//多柱表的封装结束


//计算前一个数相对后一个数的百分比开始
function danPercent(num1, num2) {
    var num3 = Number(num1);
    var num4 = Number(num2);
    if (num4 == 0) {
        return "-"
    } else {
        return (((num3 / num4) * 10000) / 100).toFixed(1);
    }

}

//计算前一个数相对后一个数的百分比开始

//所有数组里面的数相加
function addArr(arr) {
    var totalNum = 0;
    for (var i = 0; i < arr.length; i++) {
        totalNum += Number(arr[i]);
    }
    return totalNum;
}