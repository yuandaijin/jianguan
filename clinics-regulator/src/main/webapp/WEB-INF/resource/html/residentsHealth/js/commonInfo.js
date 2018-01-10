var echart = {
    //百分比柱状图函数封装开始
    percentHistogram: function(obj, titleName, legendData, xData, yMax, seriesInfo) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(obj);

        // 指定图表的配置项和数据
        var option = {
            title: {
                // show:false
                text: titleName,
                textStyle: {
                    fontSize: 14
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                orient: 'horizonal',
                data: legendData,
                bottom: 28,
                left: 'center',
                width: 2,
                height: 2,
                selectedMode: false
            },
            grid: {
                left: '8%',
                right: '3%',
                bottom: '24%',
                top: '12%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                data: xData,
                axisLine: { show: false },
                axisTick: { show: false }
            },
            yAxis: {
                type: 'value',
                max: 'auto',
                min: 0,
                axisLine: { show: false },
                axisTick: { show: false }
            },
            series: seriesInfo
        };

        if (xData.length >= 8) {
            option.xAxis.axisLabel = { 'interval': 0, rotate: -45, }
        }
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    },
    //百分比柱状图函数封装开始

    //收取数据信息函数开始（与百分比柱状图函数结合使用）
    integrationData: function(data, isSingle, isPercent) {
        var resData = [];
        var color = ['#2a91cc', '#62c483', '#84a2d9', '#14c9c9', '#c298d4'];
        var numArr = [];
        for (var i = 0; i < data.length; i++) {
            var chartObj = {
                name: '',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'inside',
                        formatter: function(params) {
                            if (isPercent) {
                                var sum = 0;
                                for (var i = 0; i < data.length; i++) {
                                    sum += parseInt(data[i].data[params.dataIndex]);
                                }
                                if (sum == 0 && data[params.seriesIndex].data[params.dataIndex] == 0) {
                                    percent = 0;
                                    numArr.push(percent);
                                } else if (params.seriesIndex == (data.length - 1)) {
                                    var numTotal = 0;
                                    var num = (data[0].data.length) * 2;
                                    for (var i = 0; i < parseInt(numArr.length / num); i++) {
                                        var num00 = (i == 0) ? (params.dataIndex * 2) : (num * i + (params.dataIndex * 2));
                                        numTotal += Number(numArr[num00]);
                                    }
                                    percent = (100 - Number(numTotal)).toFixed(1);
                                } else {
                                    var percent = data[params.seriesIndex].data[params.dataIndex] / sum;
                                    percent = ((percent * 10000) / 100).toFixed(1);
                                    numArr.push(percent);
                                }
                                if (percent - 0 === 0) {
                                    return '';
                                }

                                return percent + '%'

                            }
                        },
                        textStyle: {
                            color: '#000',
                            fontSize: 10
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        barBorderColor: 'rgba(0,0,0,0)',
                        color: 'blue'
                    }
                },
                data: []
            }
            if (isSingle == true) {
                chartObj.barWidth = 100;
            } else {
                chartObj.barWidth = '50%';
            }
            chartObj.name = data[i].name;
            chartObj.data = data[i].data;
            chartObj.itemStyle.normal.color = color[i];
            resData.push(chartObj)
        }
        return resData
    },
    //收取数据信息函数结束

    //简单柱表函数封装开始
    histogram: function(obj, titleName, requireInfo, dataName, dataContent, maxNum, single, isYPercent, isDataPercent) {
        if (single) {
            wid = '30%'
        } else {
            wid = '40%'
        }
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(obj);

        var option = {
            title: {
                // show:false
                text: titleName,
                textStyle: {
                    fontSize: 12
                }
            },
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '8%',
                right: '4%',
                bottom: '12%',
                containLabel: true
            },
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            xAxis: {
                type: 'category',
                data: dataName,
                axisLine: { show: false },
                axisTick: { show: false }
            },
            yAxis: {
                type: 'value',
                max: 'auto',
                min: 0,
                axisLine: { show: false },
                axisTick: { show: false },
                axisLabel: {
                    show: true
                }

            },
            series: [{
                name: requireInfo,
                type: 'bar',
                stack: '总量',
                barWidth: wid,
                data: dataContent
            }]
        };

        if (isYPercent) {
            option.yAxis.axisLabel.formatter = '{value} %'
        }
        if (isDataPercent) {
            option.label.normal.formatter = '{c} %';
            option.tooltip.formatter = '{a} <br/>{b} : {c} %';
        }
        if (dataName.length < 7) {
            option.series[0].barWidth = "30%";
        }
        if (dataName.length >= 8) {
            option.xAxis.axisLabel = { 'interval': 0, rotate: -45, }
        }
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    },
    //简单柱表函数封装结束

    //简单饼状图函数创建开始
    pinChart: function(obj, legendData, seriesName, seriesData) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(obj);

        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'horizonal',
                data: legendData,
                bottom: 28,
                width: 2,
                height: 2,
                left: 'center'
            },
            series: [{
                name: seriesName,
                type: 'pie',
                radius: '70%',
                center: ['50%', '46%'],
                data: seriesData,
                label: {
                    normal: {
                        show: true,
                        position: 'inner',
                        formatter: ' {d}%'
                    }
                },
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    },
                    normal: {
                        color: function(params) {
                            // build a color map as your need.
                            var colorList = [
                                '#2a91cc', '#62c483', '#84a2d9', '#14c9c9', '#c298d4'
                            ];
                            return colorList[params.dataIndex]
                        }
                    }
                }
            }]
        };
        if (legendData.length >= 8) {
            option.xAxis.axisLabel = { 'interval': 0, rotate: -45, }
        }
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    },
    //简单饼状图函数创建完成


    //圆柱与线条结合的表格封装开始
    histoAndLine: function(obj, legendData, xData, objLeft, objRight) {
        var myChart = echarts.init(obj);
        option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: legendData,
                orient: 'horizonal',
                bottom: 0,
                left: 'center',
                width: 2,
                height: 2
            },
            grid: {
                left: '8%',
                right: '8%',
                bottom: '14%',
                top: '12%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                axisLine: { show: false },
                axisTick: { show: false },
                axisLabel: { 'interval': 0, rotate: -45, },
                data: xData,
            }],
            yAxis: [{
                    type: 'value',
                    axisLine: { show: false },
                    axisTick: { show: false },
                    name: '门诊总量',
                    min: 0,
                    max: 'auto',
                    axisLabel: {
                        formatter: '{value}'
                    }
                },
                {
                    type: 'value',
                    axisLine: { show: false },
                    axisTick: { show: false },
                    splitLine: { show: false },
                    name: '服务效率',
                    min: 0,
                    max: 'auto',
                    axisLabel: {
                        formatter: '{value}'
                    }
                }
            ],
            series: [{
                    name: objLeft.name,
                    type: 'bar',
                    data: objLeft.data,
                    barWidth: '40%',
                    label: {
                        normal: {
                            show: true,
                            position: 'inside',
                            textStyle: {
                                color: '#000',
                                fontSize: 12
                            }
                        }
                    },
                },
                {
                    name: objRight.name,
                    type: 'line',
                    yAxisIndex: 1,
                    data: objRight.data,
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    }
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    },
    //圆柱与线条结合的表格封装结束


    //有平均值的线条表开始
    avgLine: function(obj, legendData, xData, contentData) {
        var myChart = echarts.init(obj);
        option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                orient: 'horizonal',
                data: legendData,
                bottom: 28,
                left: 'center',
                width: 2,
                height: 2
            },
            grid: {
                left: '8%',
                right: '8%',
                bottom: '18%',
                top: '12%',
                containLabel: true
            },
            toolbox: {
                show: true,
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    dataView: { readOnly: false },
                    magicType: { type: ['line', 'bar'] },
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                axisLine: { show: false },
                axisTick: { show: false },
                axisLabel: { 'interval': 0, rotate: -45, },
                data: xData
            },
            yAxis: {
                type: 'value',
                axisLabel: {

                },
                max: 'auto',
                min: 0,
                axisLine: { show: false },
                axisTick: { show: false },
                axisLabel: {
                    show: true
                }
            },
            series: [{
                name: contentData.name,
                type: 'line',
                data: contentData.data,
                markLine: {
                    data: [
                        { type: 'average', name: '平均值' }
                    ]
                }
            }]
        };

        myChart.setOption(option);
    },
    //有平均值的线条表结束

    //多线条表封装开始
    moreLine: function(obj, legendData, xData, contentData) {
        var myChart = echarts.init(obj);
        option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: legendData
            },
            grid: {
                left: '8%',
                right: '8%',
                bottom: '18%',
                top: '12%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: { show: false },
                axisTick: { show: false },
                axisLabel: { 'interval': 0, rotate: -45, },
                data: xData
            },
            yAxis: {
                type: 'value'
            },
            series: contentData
        };
        myChart.setOption(option);
    },
    //多线条表封装结束

    //生成多线条表需要的数据格式，与多线条表配合使用
    getMoreLineData: function(arr) {
            var contentData = [];
            for (var i = 0; i < arr.length; i++) {
                var obj = {};
                obj.name = arr[i].name;
                obj.type = "line";
                obj.stack = "总量";
                obj.data = arr[i].data;
                contentData.push(obj);
            }
            return contentData;
        }
        //生成多线条表需要的数据格式，与多线条表配合使用


}


var common = {

    //计算前后数组总数比开始
    relativePercent: function(lastArr, nowArr) {
        var lastSum = 0,
            nowSum = 0;
        for (var i = 0; i < lastArr.length; i++) {
            lastSum += Number(lastArr[i]);
            nowSum += Number(nowArr[i]);
        }
        if (lastSum == 0) {
            return "-"
        } else {
            return (((nowSum - lastSum) / lastSum * 10000) / 100).toFixed(1)
        }

    },
    //计算前后数组总数比结束


    //头部下拉菜单开始
    dropDownList: function() {
        var url = path.path + "/comm/resultAddress";
        var params = { "parentId": cityCode };
        $.ajax({
            type: "get",
            url: url,
            data: params,
            cache: false,
            async: false,
            success: function(res) {
                if (res.code == "000000") {
                    var areaData = res.list;
                    var str = '';
                    for(var i = 0; i < areaData.length; i++) {
						if(areaData[i].id == countyId) {
							str += '<option selected data-id="' + areaData[i].id + '" value="' + areaData[i].name + '">' + areaData[i].name + '</option>'
						}else{
							str += '<option  data-id="' + areaData[i].id + '" value="' + areaData[i].name + '">' + areaData[i].name + '</option>'
						} 
					}
                    $("#areaChoose").empty().append(str);

                }
            }
        });
    },
    //头部下拉菜单结束

    //计算后面一个数大于前面一个数的百分比开始
    numPercent: function(num1, num2) {
        var numLast = Number(num1);
        var num = Number(num2);
        if (num == 0) {
            return '-'
        } else {
            return (numLast / num * 100).toFixed(1);
        }
    },
    //计算后面一个数大于前面一个数的百分比开始

    //计算前一个数相对后一个数的百分比开始
    sinPercent: function(num1, num2) {
        var num3 = Number(num1);
        var num4 = Number(num2);
        if (num4 == 0) {
            return "- %"
        } else {
            return (((num3 / num4) * 10000) / 100).toFixed(1) + "%";
        }

    },
    //计算前一个数相对后一个数的百分比开始

    //计算环比开始
    anPercent: function(num1, num2, isMinus) {
        var num1 = Number(num1);
        var num2 = Number(num2);
        if (num1 == 0) {
            return "- %";
        } else {
            var returnNum = ((((num2 - num1) / num1) * 10000) / 100).toFixed(1);
            if (isMinus) {
                if (returnNum >= 0) {
                    return "+" + returnNum + "%";
                } else {
                    return returnNum + "%";
                }
            } else {
                return returnNum + "%";
            }
        }

    },
    //计算环比结束

    moreAnPercnet: function() {

    },

    //设置年限开始
    setYear: function() {
        $(".yearSelected").html("");
        var myDate = new Date();
        for (var j = 2010; j <= myDate.getFullYear(); j++) {
            if (j == myDate.getFullYear()) {
                var opNode = $("<option></option>").html(j + "年").attr({ "selected": "selected", "value": j });
            } else {
                var opNode = $("<option></option>").html(j + "年").attr({ "value": j });
            }

            $(".yearSelected").append(opNode);
        }
    },
    //设置年限结束

    //设置月份开始
    setMonth: function(currentMonthName) {
        $(".monthSelected").html("");
        var myDate = new Date();
        for (var j = 0; j < 12; j++) {
            if (j == myDate.getMonth()) {
                var opNode = $("<option></option>").html((currentMonthName || ((j + 1)) + "月")).attr({ "selected": "selected", "value": (j + 1) });
            } else {
                var opNode = $("<option></option>").html((j + 1) + "月").attr({ "value": (j + 1) });
            }

            $(".monthSelected").append(opNode);
        }
    },
    //设置月份结束

    //设置季度开始
    setQuarter: function() {
        $(".quarterSelected").html("");
        var myDate = new Date();
        var quaNode1 = (myDate.getMonth() >= 1 && myDate.getMonth() <= 3) ? ($("<option></option>").html("本季度").attr({ "value": "1,2,3", "selected": "selected" })) : ($("<option></option>").html("第一季").attr({ "value": "1,2,3" }));
        var quaNode2 = (myDate.getMonth() >= 4 && myDate.getMonth() <= 6) ? ($("<option></option>").html("本季度").attr({ "value": "4,5,6", "selected": "selected" })) : ($("<option></option>").html("第二季").attr({ "value": "4,5,6" }));
        var quaNode3 = (myDate.getMonth() >= 7 && myDate.getMonth() <= 9) ? ($("<option></option>").html("本季度").attr({ "value": "7,8,9", "selected": "selected" })) : ($("<option></option>").html("第三季").attr({ "value": "7,8,9" }));
        var quaNode4 = (myDate.getMonth() >= 10 && myDate.getMonth() <= 12) ? ($("<option></option>").html("本季度").attr({ "value": "10,11,12", "selected": "selected" })) : ($("<option></option>").html("第四季").attr({ "value": "10,11,12" }));
        $(".quarterSelected").append(quaNode1);
        $(".quarterSelected").append(quaNode2);
        $(".quarterSelected").append(quaNode3);
        $(".quarterSelected").append(quaNode4);
    },
    //设置季度结束
    setDays: function() {
        $(".daySelected").html("");
        var myDate = new Date();
        //myDate.getMonth()+1代表下个月，月份索引从0开始，即当前月为6月时，getMonth()返回值为5，创建日期时同理
        //此处构造的日期为下个月的第0天，天数索引从1开始，第0天即代表上个月的最后一天
        var curMonthDays = new Date(myDate.getFullYear(), (myDate.getMonth() + 1), 0).getDate();
        for (var j = 0; j < curMonthDays; j++) {
            if (j == myDate.getDate() - 1) {
                var opNode = $("<option></option>").html((j + 1) + "日").attr({ "selected": "selected", "value": (j + 1) });
            } else {
                var opNode = $("<option></option>").html((j + 1) + "日").attr({ "value": (j + 1) });
            }
            $(".daySelected").append(opNode);
        }
    }
}
//获取登陆人员的所在的地区
var countyId;
var cityCode
function queryAdress() {
	var url = path.path + "/dispute/queryAdress";
	$.ajax({
		type: "get",
		url: url,
		cache: false,
		async: false,
		success: function(res) {
			if(res.code == '000000') {
				cityCode=res.dto.cityCode;
				countyId = res.dto.countyCode;
			}
		}
	});
}
queryAdress();
//切换选项卡
function changeTab(change, _this) {
    change.removeClass('active');
    _this.addClass('active');
}