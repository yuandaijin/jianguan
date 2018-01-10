var parentId = "";

//设置年限开始
common.setYear();
//设置年限结束
common.setMonth('本月');

//区域下拉框开始
common.dropDownList();
//区域下拉框结束

$(function() {

    $('#queryBy').val('month');
    $("#queryBy").bind("click", function() {
        query();
    });

    $('#queryBy').on('change', function() {
        var dateType = $(this).val();
        if (dateType === 'year') {
            $('.monthSelected').hide();
        } else if (dateType === 'quarter') {
            $('.quarterSelected').show();
        } else if (dateType === 'month') {
            $('.monthSelected').show();
        }
    });

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

    //表格初始化开始
    query();
    //表格初始化结束

    //区域确认开始
    $("#areaConfirm").bind("click", function() {
        query();
    });
    $(".queryByTime").bind("click", function() {
        query();
    });

    function setCurrentTimeText() {
        var dateType = $('#queryBy').val();
        if (dateType === 'year') {
            $(".yearShow").html($(".yearSelected").val() + "年");
        } else if (dateType === 'month') {
            $(".yearShow").html($(".yearSelected").val() + "年" + $(".monthSelected").val() + '月');
        }
    }

    function query() {
        $(".areaShow").html($("#areaChoose").val());
        setCurrentTimeText();

        var params = getQueryPara();
        if (params.parentAreaCode != "") {
            //多发病分析接口
            $.ajax({
                type: "get",
                url: path.path + "/residents/queryReIncidence",
                data: params,
                cache: false,
                async: true,
                success: function(res) {
                    console.log(res);
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


            //特种病种分析接口
            $.ajax({
                type: "get",
                url: path.path + "/residents/queryReGiven",
                data: params,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000" && res.aidslsit && res.hbvlist && res.hfmdlist) {

                        //特种病分析开始
                        var aidData = res.aidslsit;
                        var leftObj = document.getElementById('leftChart');
                        var leftYData = [];
                        leftYData.push(aidData[0].name);
                        var leftSeriesName = aidData[0].name;
                        var leftContentData = [];
                        leftContentData.push(aidData[0].qty);
                        $("#clinicA").html(aidData[0].name);
                        yHisto(leftObj, leftYData, leftSeriesName, leftContentData);

                        var hbvData = res.hbvlist;
                        var midObj = document.getElementById('midChart');
                        var midYData = [];
                        midYData.push(hbvData[0].name);
                        var midSeriesName = hbvData[0].name;
                        var midContentData = [];
                        midContentData.push(hbvData[0].qty);
                        $("#clinicB").html(hbvData[0].name);
                        yHisto(midObj, midYData, midSeriesName, midContentData);

                        var hfmData = res.hfmdlist;
                        var rightObj = document.getElementById('rightChart');
                        var rightYData = [];
                        rightYData.push(hfmData[0].name);
                        var rightSeriesName = hfmData[0].name;
                        var rightContentData = [];
                        rightContentData.push(hfmData[0].qty);
                        $("#clinicC").html(hfmData[0].name);
                        yHisto(rightObj, rightYData, rightSeriesName, rightContentData);
                        //特种病分析结束

                    } else {
                        $("#leftChart").html("数据暂无");
                        $("#midChart").html("数据暂无");
                        $("#rightChart").html("数据暂无");

                    }
                }
            })

        }
    }
    //区域确认结束

})



function yHisto(obj, yData, seriesName, contentData) {
    var myChart = echarts.init(obj);
    option = {
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            top: '6%',
            containLabel: true
        },
        xAxis: [{
            type: 'value'
        }],
        yAxis: [{
                type: 'category',
                data: yData,
                axisTick: {
                    alignWithLabel: true
                }
            }

        ],
        series: [{
            name: seriesName,
            type: 'bar',
            barWidth: '40%',
            data: contentData
        }]
    };

    myChart.setOption(option);
}

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