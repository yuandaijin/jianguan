// var parentId = "";
// var yearControl;
// var month = '';

//区域下拉框开始
common.dropDownList();
//区域下拉框结束

//设置年限开始
common.setYear();
//设置年限结束

//add by clark
common.setMonth('本月');

$(function() {
    //区域确认开始
    $("#areaConfirm").bind("click", function() {
        query();
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

    function query() {
        $(".areaShow").html($("#areaChoose").val());
        var queryPara = getQueryPara();
        if (!!queryPara.parentAreaCode) {
            //诊所数量统计接口
            var urlTop = path.path + "/operate/queryOperate";
            $.ajax({
                type: "get",
                url: urlTop,
                data: queryPara,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000" && res.efficiencylist && res.totallist) {
                        var efficData = res.efficiencylist;
                        var totalData = res.totallist;
                        //圆柱与线条结合的表格封装开始
                        var obj = document.getElementById("operate")
                        var legendData = ['门诊总量', '服务效率'];
                        var xData = [];
                        var leftData = [];
                        var rightData = [];
                        for (var i = 0; i < efficData.length; i++) {
                            xData.push(efficData[i].name);
                            leftData.push(totalData[i].qty);
                            rightData.push(efficData[i].qty);
                        }
                        var objLeft = {
                            name: "门诊总量",
                            data: leftData
                        };
                        var objRight = {
                            name: "服务效率",
                            data: rightData
                        };
                        echart.histoAndLine(obj, legendData, xData, objLeft, objRight);
                        //圆柱与线条结合的表格封装结束
                    } else {
                        $("#operate").html("数据暂无");
                    }
                }
            });
            //诊所数量统计接口
            var urlBottom = path.path + "/operate/queryOperatetable";
            $.ajax({
                type: "get",
                url: urlBottom,
                data: queryPara,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000" && res.efficiencylist && res.totallist) {
                        $("#infoHead").html("");
                        $("#infoBody").html("");
                        var feeData = res.costlist;
                        var effiData = res.efficiencylist;
                        var totalData = res.totallist;
                        //运营情况总表开始
                        var headTr = $("<tr></tr>");
                        var headTitle = $("<td></td>").html("行政区划");
                        headTr.append(headTitle);

                        var totalTr = $("<tr></tr>");
                        var totalTitle = $("<td></td>").html("服务总量");
                        totalTr.append(totalTitle);

                        var effiTr = $("<tr></tr>");
                        var effiTitle = $("<td></td>").html("服务效率");
                        effiTr.append(effiTitle);

                        var feeTr = $("<tr></tr>");
                        var feeTitle = $("<td></td>").html("服务费用");
                        feeTr.append(feeTitle);

                        for (var i = 0; i < feeData.length; i++) {
                            var tdNode = $("<td></td>").html(feeData[i].name);
                            headTr.append(tdNode);

                            var totalTd = $("<td></td>").html(totalData[i].qty);
                            totalTr.append(totalTd);

                            var effiTd = $("<td></td>").html(effiData[i].qty);
                            effiTr.append(effiTd);

                            var feeTd = $("<td></td>").html(feeData[i].qty);
                            feeTr.append(feeTd);

                        }
                        $("#infoHead").append(headTr);

                        $("#infoBody").append(totalTr);
                        $("#infoBody").append(effiTr);
                        $("#infoBody").append(feeTr);
                        //运营情况总表结束
                    } else {
                        $("#infoHead").html("");

                        $("#infoBody").html("");
                    }
                }
            })
        }
    }

    function initQuery() {
        $('#queryBy').val('month');
        query();
    }
    initQuery();

    $('#queryBy').on('change', function() {
        var dateType = $(this).val();
        if (dateType === 'year') {
            $('.monthSelected').hide();
        } else if (dateType === 'month') {
            $('.monthSelected').show();
        }
    });


    //区域确认结束


    //查询功能开始
    $("#searchInfo").bind("click", function() {
        query();
    });

    //查询功能结束

})