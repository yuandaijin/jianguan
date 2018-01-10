


$(function(){
	var parentId="";

	//区域下拉框开始
	common.dropDownList();
	//区域下拉框结束

	//设置年限开始
	common.setYear();
	common.setMonth();
	common.setDay();
	//设置年限结束

	query();

	//当前概况中时间方式的选择
	$("#timeStyle").change(function() {
		if($(this).val()=="month"){
			$(".monthSelected").removeClass("hide").addClass("show");
			$(".daySelected").removeClass("show").addClass("hide");
		}else if($(this).val()=="day"){
			$(".monthSelected").removeClass("hide").addClass("show");
			$(".daySelected").removeClass("hide").addClass("show");
		}else{
			$(".monthSelected").removeClass("show").addClass("hide");
			$(".daySelected").removeClass("show").addClass("hide");
		}
	});

	//显示选中时间地区
	function showAreaEtc(){
		var area = $("#areaChoose").val();
		var year = $(".yearSelected").val();
		var month = $(".monthSelected").val();
		var day = $(".daySelected").val();
		$(".areaShow").html(area);
		$(".yearShow").html(year+'年');
		$(".monthShow").html(month+'月');
		$(".dayShow").html(day+'日');
		if($("#timeStyle").val()=="month"){
			$(".monthShow").html(month+'月');
			$(".dayShow").html("");
		}else if($("#timeStyle").val()=="day"){
			$(".monthShow").html(month+'月');
			$(".dayShow").html(day+'日');
		}else{
			$(".monthShow").html("");
			$(".dayShow").html("");
		}
		
	}

    //区域确认开始
    $("#areaConfirm").bind("click",function(){
		query()
	});

	//查询点击开始
    $("#searchInfo").bind("click",function(){
		query()
	});

    //区域确认结束
	function query(){
		showAreaEtc();
		parentId = $('#areaChoose option:selected').attr('data-id');
		if(parentId!=""){
			//双流县个体诊所医疗质量情况概览接口
			var urlTop=path.path+"/overview/OverviewClinicMedicalQuality";
			var params={};
			if($("#timeStyle").val()=="month"){
				params={
					"parentAreaCode":parentId,
					"year":$(".yearSelected").val(),
					"month":$(".monthSelected").val(),
					"areaFlag":"1"
				}
			}else if($("#timeStyle").val()=="day"){
				params={
					"parentAreaCode":parentId,
					"year":$(".yearSelected").val(),
					"month":$(".monthSelected").val(),
					"day":$(".daySelected").val(),
					"areaFlag":"0"
				}
			}else{
				params={
					"parentAreaCode":parentId,
					"year":$(".yearSelected").val(),
					"areaFlag":"2"
				}
			}
			
			$.ajax({
				type :"get",
				url : urlTop,
				data :params,
				cache : false,
				async : true,
				success : function(res){
					console.log(res);
					if(res.code=="000000"&&res.data){
						$("#passPerrcent").html("");
						$("#accidentStatic").html("");
						$("#satisfidPercent").html("");
						$("#daoQi").html("");
						$("#heGe").html("");
						$("#zhanBi").html("");
						$("#shuBi").html("");
						var infoData=res.data;
						//个体诊所医疗质量情况概览表开始
						var topDataName = [];
						var topDataContent = [];
						for(var i=0; i<infoData.length; i++){
							if(infoData[i].type=="电子处方合格率"){
								$("#passPerrcent").html(infoData[i].qty+"%");
							}else if(infoData[i].type=="医疗事故统计"){
								$("#accidentStatic").html(infoData[i].qty);
							}else if(infoData[i].type=="服务满意度"){
								$("#satisfidPercent").html(infoData[i].qty+"%");
							}else if(infoData[i].type=="诊所执业许可证到期数量"){
								$("#daoQi").html(infoData[i].qty);
							}else if(infoData[i].type=="电子病历合格率"){
								$("#heGe").html(infoData[i].qty+"%");
							}else if(infoData[0]&&infoData[2]&&infoData[1]){
								var a = parseFloat(infoData[2].qty);
								if(a == "0"){
									$("#zhanBi").html("0")
								}else{
									$("#zhanBi").html(Math.round((parseFloat(infoData[0].qty)/parseFloat(infoData[2].qty)*10000)/100)+"%");
								}
//								$("#zhanBi").html(Math.round((parseFloat(infoData[0].qty)/parseFloat(infoData[2].qty)*10000)/100)+"%");
								$("#shuBi").html(Math.round((parseFloat(infoData[1].qty)/parseFloat(infoData[2].qty)*10000)/100)+"%");
							}
						}
						//个体诊所医疗质量情况概览表结束
					}else{
						$("#passPerrcent").html("");
						$("#accidentStatic").html("");
						$("#satisfidPercent").html("");
						$("#daoQi").html("");
						$("#heGe").html("");
						$("#zhanBi").html("");
						$("#shuBi").html("");
					}


				}
			})


			//双流县个体诊所医疗质量情况概览表单接口
			var urlBottom=path.path+"/overview/ListOverviewClinicMedicalQuality ";
			$.ajax({
				type :"get",
				url : urlBottom,
				data :params,
				cache : false,
				async : true,
				success : function(res){
					if(res.code="000000"&&res.data){
						$("#infoTotal").html("");
						var infoData=res.data;

						var areaTr=$("<tr></tr>");
						areaTr.append($("<td></td>").html("行政区划"));

						var chufangTr=$("<tr></tr>");
						chufangTr.append($("<td></td>").html("电子处方合格率"));

						var bingliTr=$("<tr></tr>");
						bingliTr.append($("<td></td>").html("电子病历合格率"));

						var shiguTr=$("<tr></tr>");
						shiguTr.append($("<td></td>").html("医疗事故发生数"));

						var manyiTr=$("<tr></tr>");
						manyiTr.append($("<td></td>").html("服务满意度合格率"));

						var kangshengTr=$("<tr></tr>");
						kangshengTr.append($("<td></td>").html("抗生素使用占比"));

						var shuyeTr=$("<tr></tr>");
						shuyeTr.append($("<td></td>").html("输液处方占比"));

						var areaTr=$("<tr></tr>");
						areaTr.append($("<td></td>").html("行政区划"));
						for(var i=0; i<infoData.length; i++){
							areaTr.append($("<td></td>").html(infoData[i].name));
							chufangTr.append($("<td></td>").html(infoData[i].recipeFPY+"%"));
							bingliTr.append($("<td></td>").html(infoData[i].caseFPY+"%"));
							shiguTr.append($("<td></td>").html(infoData[i].malaPraxisTotal));
							manyiTr.append($("<td></td>").html(infoData[i].serviceSatisfaction+"%"));
							kangshengTr.append($("<td></td>").html(common.sinPercent(infoData[i].antibioticRecipeTotal,infoData[i].recipeTotal)));
							shuyeTr.append($("<td></td>").html(common.sinPercent(infoData[i].infusionRecipeTotal,infoData[i].recipeTotal)));
						}
						$("#infoTotal").append(areaTr);
						$("#infoTotal").append(chufangTr);
						$("#infoTotal").append(bingliTr);
						$("#infoTotal").append(shiguTr);
						$("#infoTotal").append(manyiTr);
						$("#infoTotal").append(kangshengTr);
						$("#infoTotal").append(shuyeTr);
					}else{
						$("#infoTotal").html("");
					}


				}
			})




		}
	}

});

