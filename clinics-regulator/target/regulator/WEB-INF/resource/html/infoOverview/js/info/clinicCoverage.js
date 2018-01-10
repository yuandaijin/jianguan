


$(function(){

    var parentId="";

    //区域下拉框开始
    common.dropDownList();
    //区域下拉框结束

    //设置年限开始
    common.setYear();
    //设置年限结束

    //设置年限开始
    common.setMonth();
    //设置年限结束
    
    //当前概况中时间方式的选择
    $("#timeStyle").change(function() {
        if($(this).val()=="month"){
            $(".monthSelected").removeClass("hide").addClass("show");
        }else{
            $(".monthSelected").removeClass("show").addClass("hide");
        }
    });

    //显示选中时间地区
    function showAreaEtc(){
        var area = $("#areaChoose").val();
        var year = $(".yearSelected").val();
        var month = $(".monthSelected").val();
        $(".areaShow").html(area);
        $(".yearShow").html(year+'年');
        if($("#timeStyle").val()=="month"){
            $(".monthShow").html(month+'月');
        }else{
            $(".monthShow").html("");
        }
    }
    

    //区域确认开始
    $("#areaConfirm").click(function(){
        showAreaEtc();
        var yearControl=$(".yearSelected").val();
        var monthControl=$(".monthSelected").val();
        parentId = $('#areaChoose option:selected').attr('data-id');
        yearControl=$(".yearSelected").val();
        if(parentId!=""){
            //诊所医疗能力覆盖情况接口    
            var url=path.path+"/ClinicResources/CoverageJson";
            var params={};
            if($("#timeStyle").val()=="year"){
                var params={
                    "year": yearControl,
                    "parentAreaCode":parentId,
                    "areaFlag":"2"
                }
            }else{
                var params={
                    "year": yearControl,
                    "month":monthControl,
                    "parentAreaCode":parentId,
                    "areaFlag":"1"
                }
            }
            $.ajax({
                type :"get",
                url : url,
                data :params,
                cache : false,
                async : true,
                success : function(res){
                    console.log(res);
                    if(res.code="000000"&&res.data){
                        //填充信息表
                        var sumData=res.data;
                        $("#coverInfo").html("")
                        for(var i=0; i<sumData.length; i++){
                            var opNode=$("<tr style='height: 30px;'></tr>");
                            var areaTr=$("<td></td>").html(sumData[i].areaName);
                            var numTr=$("<td></td>").html(sumData[i].cliqty);
                            var ratioTr=$("<td></td>").html(sumData[i].mcr);
                            opNode.append(areaTr);
                            opNode.append(numTr);
                            opNode.append(ratioTr);
                            $("#coverInfo").append(opNode);
                        }



                        //各下级行政区划诊所数量分布表开始
                        var numDataName=[];
                        var numDataContent=[];
                        var totalNum=0;
                        for(var i=0; i<sumData.length; i++){
                            numDataName.push(sumData[i].areaName);
                            numDataContent.push(sumData[i].cliqty);

                            totalNum+=parseInt(sumData[i].cliqty);
                        };
                        $("#totalNum").html(totalNum);
                        var numNode=document.getElementById('number');
                        var numTitleName='';
                        var numRequireInfo='下级行政区划诊所数量';
                        var numMaxNum=100;

                        echart.histogram(numNode,numTitleName,numRequireInfo,numDataName,numDataContent,numMaxNum,false)
                        //各下级行政区划诊所数量分布表结束

                    }else{
                        $("#coverInfo").html("暂无数据");
                        $("#number").html("暂无数据");
                    }
                	
                }


            })



            
            // //诊所医疗能力覆盖情况接口    
            // var url=path.path+"/overview/clinicsQty";
            // var params={"parentAreaCode":parentId};
            // $.ajax({
            //     type :"get",
            //     url : url,
            //     data :params,
            //     cache : false,
            //     async : true,
            //     success : function(res){
            //         console.log(res);
            //         if (res.code=="000000") {
            //             var numData=res.list;
            //             //各下级行政区划诊所数量分布表开始
            //             var numDataName=[];
            //             var numDataContent=[];
            //             for(var i=0; i<numData.length; i++){
            //                 numDataName.push(numData[i].name);
            //                 numDataContent.push(numData[i].qty);
            //             };
            //             var numNode=document.getElementById('number');
            //             var numTitleName='';
            //             var numRequireInfo='下级行政区划诊所数量';
            //             var numMaxNum=100;

            //             echart.histogram(numNode,numTitleName,numRequireInfo,numDataName,numDataContent,numMaxNum,false)
            //             //各下级行政区划诊所数量分布表结束
            //         }
            //     }
            // })


        }
    })
    //区域确认结束

    //表格初始化开始
    $("#areaConfirm").click()
    //表格初始化结束

    //查询开始
    $("#searchInfo").bind("click",function(){
        $("#areaConfirm").click()
    })

    //查询结束


})
