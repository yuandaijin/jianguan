var parentId = "";
var areaBol = true;

//区域下拉框开始
common.dropDownList();
//区域下拉框结束

//设置年限开始
common.setYear();
//设置年限结束

//add by clark
common.setMonth('本月');

$(function() {

    $('#queryBy').val('month');
    numSubmit();

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

    //区域确认开始
    $("#areaConfirm").bind("click", function() {
        chooseActive();
    });
    $(".queryTime").bind("click", function() {
        chooseActive();
    });

    $('#queryBy').on('change', function() {
        var dateType = $(this).val();
        if (dateType === 'year') {
            $('.monthSelected').hide();
        } else if (dateType === 'month') {
            $('.monthSelected').show();
        }
    });

    function chooseActive() {
        var chooseNumber = 0;
        if ($('.totalNav').hasClass('active')) {
            if ($('#numSubmit').hasClass('active')) {
                chooseNumber = 1;
            } else if ($('#classSubmit').hasClass('active')) {
                chooseNumber = 2;
            }
        } else if ($('#infoTotal').hasClass('active')) {
            chooseNumber = 3;
        }
        if (chooseNumber == 1) {
            numSubmit();
        } else if (chooseNumber == 2) {
            classSubmit();
        } else if (chooseNumber == 3) {
            infoTotal();
        }
    }

    function showTimeArea() {
        $(".areaShow").html($("#areaChoose").val());
        if($('#queryBy').val() == 'month'){
            $('.showTime').html($('.yearSelected').val()+'年'+$('.monthSelected').val()+'月');
        }else{
            $('.showTime').html($('.yearSelected').val()+'年');
        }

    }
    /**********文字特效开始**************/
    $("ul.area li").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
    });
    $(".totalNav").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
    });
    $(".subordinate div").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
    });
    /**********文字特效结束**************/

    //辖区总体概况下的诊所数量统计开始
    $("#numSubmit").bind("click", function() {
        $(".areaList").eq(0).removeClass("hide").addClass("show").siblings(".areaList").removeClass('show').addClass('hide');
        numSubmit();
    });


    function numSubmit() {


        showTimeArea();
        var params = getQueryPara();
        if (!!params.parentAreaCode) {
            //诊所数量统计接口
            var url = path.path + "/cost/queryCostQty";

            $.ajax({
                type: "get",
                url: url,
                data: params,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000") {
                        var listData = res.list;
                        var momData = res.momlist;
                        var listDataName = '';
                        var listDataQty = 0;
                        if (listData) {
                            listDataName = listData[0].name;
                            listDataQty = listData[0].qty;
                        }

                        var momDataName = '';
                        var momDataQty = 0;
                        if (momData) {
                            momDataName = momData[0].name;
                            momDataQty = momData[0].qty;
                        }

                        //2016年人均就诊费用
                        var topNode = document.getElementById('proportionOfPrescriptions');
                        var topTitleName = '人均费用：' + listDataQty + '元';
                        var topRequireInfo = listDataName + '人均就诊费用';
                        var topDataName = [];
                        topDataName.push(listDataName);
                        var topDataContent = [];
                        topDataContent.push(listDataQty);
                        var topMaxNum = 30;
                        echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, false, false)
                            //2016年人均就诊费用
                            //人均就诊费用环比
                        var topNode = document.getElementById('timeChains');
                        var topTitleName = '环比增长率：' + common.anPercent(momDataQty, listDataQty);
                        var topRequireInfo = '人均就诊费用环比';
                        var topDataName = [momDataName, listDataName];
                        var topDataContent = [momDataQty, listDataQty];
                        var topMaxNum = 30;
                        echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, false, false)
                            //人均就诊费用环比


                        //table填充开始
                        $("#dayAvg").html(listDataQty);
                        $("#huanAdd").html(common.anPercent(momDataQty, listDataQty));
                        //table填充结束

                    } else {
                        $("#dayAvg").html("0");
                        $("#huanAdd").html("0");

                        $("#proportionOfPrescriptions").html("数据暂无");
                        $("#timeChains").html("数据暂无");
                    }
                }
            })
        }
    }
    //辖区总体概况下的诊所数量统计结束

    $("#classSubmit").bind("click", function() {
        $(".areaList").eq(1).removeClass("hide").addClass("show").siblings(".areaList").removeClass('show').addClass('hide');
        classSubmit()
    });

    function classSubmit() {
        showTimeArea();
        var params = getQueryPara();
        if (!!params.parentAreaCode) {
            //诊所数量统计接口
            var url = path.path + "/cost/queryCostType";
            $.ajax({
                type: "get",
                url: url,
                data: params,
                cache: false,
                async: true,
                success: function(res) {
                    if (res.code == "000000" && res.typelist && res.scalelist) {
                        var typeData = res.typelist;
                        //不同类型诊所人均就诊费用分布
                        var numDataName = [];
                        var numDataContent = [];
                        for (var i = 0; i < typeData.length; i++) {
                            numDataName.push(typeData[i].name);
                            numDataContent.push(typeData[i].qty);
                        }
                        var numNode = document.getElementById('numDistribute');
                        var numTitleName = '';
                        var numRequireInfo = '不同类型诊所人均就诊费用分布';
                        var numMaxNum = 25;
                        echart.histogram(numNode, numTitleName, numRequireInfo, numDataName, numDataContent, numMaxNum, false)
                            //不同类型诊所人均就诊费用分布

                        var scaleData = res.scalelist;
                        //不同规模诊所人均就诊费用分布
                        var topDataName = [];
                        var topDataContent = [];
                        for (var i = 0; i < scaleData.length; i++) {
                            topDataName.push(scaleData[i].name);
                            topDataContent.push(scaleData[i].qty)
                        }
                        var topNode = document.getElementById('distribution');
                        var topTitleName = '';
                        var topRequireInfo = '不同规模诊所人均就诊费用分布';
                        var topMaxNum = 25;
                        echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, false, false)
                            //不同规模诊所人均就诊费用分布

                        //table填充开始
                        $("#ordinaryData").html(typeData[0].qty);
                        $("#chinaData").html(typeData[1].qty);
                        $("#foreinData").html(typeData[2].qty);
                        $("#mouthData").html(typeData[3].qty);
                        $("#otherData").html(typeData[4].qty);
                        $("#smallData").html(scaleData[0].qty);
                        $("#midData").html(scaleData[1].qty);
                        $("#bigData").html(scaleData[2].qty);
                        //table填充结束

                    } else {
                        $("#ordinaryData").html("");
                        $("#chinaData").html("");
                        $("#foreinData").html("");
                        $("#mouthData").html("");
                        $("#otherData").html("");
                        $("#smallData").html("");
                        $("#midData").html("");
                        $("#bigData").html("");

                        $("#numDistribute").html("数据暂无");
                        $("#distribution").html("数据暂无");

                    }
                }
            })

        }
    }

    $("#infoTotal").bind("click", function() {
        $(".areaList").eq(2).removeClass("hide").addClass("show").siblings(".areaList").removeClass('show').addClass('hide');
        infoTotal()
    });

    function infoTotal() {
        showTimeArea();
        var params = getQueryPara();
        if (!!params.parentAreaCode) {

            var nowData, scaleData, typeData;

            //诊所数量统计接口
            var urlTop = path.path + "/cost/queryCostQty";
            $.ajax({
                type: "get",
                url: urlTop,
                data: params,
                cache: false,
                async: false,
                success: function(res) {
                    if (res.code == "000000") {
                        nowData = res.list;
                    }
                }
            })


            //诊所数量统计接口
            var urlBottom = path.path + "/cost/queryCostType";
            $.ajax({
                type: "get",
                url: urlBottom,
                data: params,
                cache: false,
                async: false,
                success: function(res) {
                    if (res.code == "000000") {
                        scaleData = res.scalelist;
                        typeData = res.typelist;
                    }
                }
            })

            if (nowData && scaleData && typeData) {
                //填充表格开始
                $("#renJun").html(nowData[0].qty);
                $("#puTong").html(typeData[0].qty);
                $("#zhongYi").html(typeData[1].qty);
                $("#zhongXi").html(typeData[2].qty);
                $("#kouQiang").html(typeData[3].qty);
                $("#qiTa").html(typeData[4].qty);
                $("#xiaoXing").html(scaleData[0].qty);
                $("#zhongXing").html(scaleData[1].qty);
                $("#daXing").html(scaleData[2].qty);
                //填充表格结束
            } else {
                $("#renJun").html("");
                $("#puTong").html("");
                $("#zhongYi").html("");
                $("#zhongXi").html("");
                $("#kouQiang").html("");
                $("#qiTa").html("");
                $("#xiaoXing").html("");
                $("#zhongXing").html("");
                $("#daXing").html("");
            }
        }
    }

    $(".totalNav").mouseenter(function() {
        $(".subordinate").css({
            display: 'block'
        })
    })
    $(".totalNav").mouseleave(function() {
        $(".subordinate").css({
            display: 'none'
        })
    })

});