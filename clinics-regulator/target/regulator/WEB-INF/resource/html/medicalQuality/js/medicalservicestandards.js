



/*******************文字切换效果结束****************************/
$(function(){
	var parentId="";
	//区域下拉框开始
	common.dropDownList();
	//区域下拉框结束

	//设置年限开始
	common.setYear();
	// common.setQuarter();
	common.setMonth();
	common.setDay();
	//设置年限结束
	changeMenu();

	$("ul.area li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
	});
    //区域确认开始
    $("#areaConfirm").on("click",function(){
		changeMenu()
	});
	//时间确认开始
    $(".chooseByTime").on("click",function(){
		changeMenu()
	});

	//年度季度月度的时间切换开始
	$(".chooseTime select").on("change",function(){
		if($(this).val() == 'year'){
			$('.yearY.nowTimeList').removeClass('hide').addClass('show').siblings('.nowTimeList').removeClass('show').addClass('hide');
		}else if($(this).val() == 'day'){
			$('.dayY.nowTimeList').removeClass('hide').addClass('show').siblings('.nowTimeList').removeClass('show').addClass('hide');
		}else if($(this).val() == 'month'){
			$('.monthY.nowTimeList').removeClass('hide').addClass('show').siblings('.nowTimeList').removeClass('show').addClass('hide');
		}
	})
	//年度季度月度的时间切换结束
	
	//菜单的切换开始
	$(".area>li").on("click",function(){
		$(".areaList").eq($(this).index()).removeClass('hide').addClass('show').siblings('.areaList').removeClass('show').addClass('hide')
		changeMenu()
	});
	function changeMenu(){
		$(".areaShow").html($("#areaChoose").val());
		var reportType = '';
		var report = $('.chooseTime select').val();
		var year = '';
		var month = '';
		if(report == 'year'){
			reportType = report;
			year = $('.yearYear').val();
			month = '0';
			$(".showTime").html(year+'年');
		}else if(report == 'day'){
			reportType = 'day';
			year = $('.dayYear').val();
			month = $('.dayMonth').val();
			day= $('.dayDay').val();
			$(".showTime").html(year+'年'+month+'月'+day+'日');
		}else if(report == 'month'){
			reportType = report;
			year = $('.monthYear').val();
			month = $('.monthMonth').val();
			$(".showTime").html(year+'年'+month+'月');
		}
		var chooseNum = 0;
		if($('.areaTotal').hasClass('active')){
			chooseNum = 0;
		}else if($('.areaNext').hasClass('active')){
			chooseNum = 1;
		}else if($('.areaInfo').hasClass('active')){
			chooseNum = 2;
		}
		parentId = $('#areaChoose option:selected').attr('data-id');
		if(chooseNum==0){
			if(parentId!=""){
				//诊所数量统计接口
				var url=path.path+"/MedicalServices/MedicalServices ";
				// var params={
				// 	"year": year,
				// 	"parentAreaCode":parentId,
				// 	"month":month,
				// 	"reportType":reportType
				// };
				var params={};
				if(reportType=="month"){
					params={
						"parentAreaCode":parentId,
						"year":year,
						"month":month,
						"areaFlag":"1",
						"reportType":reportType
					}
				}else if(reportType=="day"){
					params={
						"parentAreaCode":parentId,
						"year":year,
						"month":month,
						"day":day,
						"areaFlag":"0",
						"reportType":reportType
					}
				}else{
					params={
						"parentAreaCode":parentId,
						"year":year,
						"areaFlag":"2",
						"reportType":reportType
					}
				}
				$.ajax({
					type :"get",
					url : url,
					data :params,
					cache : false,
					async : true,
					success : function(res){
						if(res.code=="000000"){
							if(res.data.cases&&res.data.cases.now.now[0]!=null){
								var nowData=res.data.cases;
								//个体诊所电子病历书写合格率总计
								var topNode = document.getElementById('totalCases');

								var topTitleName = '总计：'+nowData.now.now[0].qty;
								var topRequireInfo = '个体诊所电子病历书写合格率总计';
								var topDataName = [];
								topDataName.push(nowData.now.nowTime)
								var topDataContent = [];
								topDataContent.push(nowData.now.now[0].qty.split("%")[0])
								var topMaxNum = 100;
								echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
								//个体诊所电子病历书写合格率总计

								//个个体诊所电子病历书写合格率环比
								var topNode = document.getElementById('caseBookChain');
								if(nowData.yearRatio.lastMonth.length == 0){
									topNode.innerHTML = '暂无数据';
								}else{
									var topTitleName = '环比增长率 '+common.anPercent(nowData.yearRatio.lastMonth[0].qty.split("%")[0],nowData.now.now[0].qty.split("%")[0],true);
									var topRequireInfo = '个体诊所电子病历书写合格率同比';
									var topDataName = [];
									topDataName.push(nowData.yearRatio.lastMonthTime);
									topDataName.push(nowData.now.nowTime);
									var topDataContent = [];
									topDataContent.push(nowData.yearRatio.lastMonth[0].qty.split("%")[0]);
									topDataContent.push(nowData.now.now[0].qty.split("%")[0]);
									var topMaxNum = 100;
									echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
								}
								//个体诊所电子病历书写合格率环比

								//个体诊所电子病历书写合格率同比
								var topNode = document.getElementById('caseBook');
								if(nowData.yearBasis.lastYear.length == 0){
									topNode.innerHTML = '暂无数据';
								}else{
									var topTitleName = '同比增长率 '+common.anPercent(nowData.yearBasis.lastYear[0].qty.split("%")[0],nowData.now.now[0].qty.split("%")[0],true);
									var topRequireInfo = '个体诊所电子病历书写合格率环比';
									var topDataName = [];
									topDataName.push(nowData.yearBasis.lastYearTime);
									topDataName.push(nowData.now.nowTime);
									var topDataContent = [];
									topDataContent.push(nowData.yearBasis.lastYear[0].qty.split("%")[0]);
									topDataContent.push(nowData.now.now[0].qty.split("%")[0]);
									var topMaxNum = 100;
									echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
								}

								//个体诊所电子病历书写合格率同比

								$("#bingHe").html(nowData.now.now[0].qty);
								if(nowData.yearRatio.lastMonth.length == 0){
									$("#bingHuan").html('暂无数据')
								}else{
									$("#bingHuan").html(common.anPercent(nowData.yearRatio.lastMonth[0].qty.split("%")[0],nowData.now.now[0].qty.split("%")[0]));
								}
								$("#bingTong").html(common.anPercent(nowData.yearBasis.lastYear[0].qty.split("%")[0],nowData.now.now[0].qty.split("%")[0]));

							}else{
								$("#bingHe").html("");
								$("#bingHuan").html("");
								$("#bingTong").html("");

								$("#totalCases").html("数据暂无");
								$("#caseBookChain").html("数据暂无");
								$("#caseBook").html("数据暂无");
							}
							

							if(res.data.recipe){
								var botData=res.data.recipe;
								//个体诊所电子病历书写合格率总计
								var topNode = document.getElementById('totalPrescription');
								var topTitleName = '';
								var topRequireInfo = '个体诊所电子处方开具合格率总计';
								var topDataName = [];
								topDataName.push(botData.now.nowTime);
								var topDataContent = [];
								topDataContent.push(botData.now.now.qty.split("%")[0])
								var topMaxNum = 100;
								echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
								//个体诊所电子病历书写合格率总计

								//个个体诊所电子病历书写合格率环比
								var topNode = document.getElementById('prescriptionRing');
								var topTitleName = '';
								var topRequireInfo = '个体诊所电子病历书写合格率环比';
								var topDataName = [];
								topDataName.push(botData.yearRatio.lastMonthTime);
								topDataName.push(botData.now.nowTime);
								var topDataContent = [];
								topDataContent.push(botData.yearRatio.lastMonth.qty.split("%")[0]);
								topDataContent.push(botData.now.now.qty.split("%")[0]);
								var topMaxNum = 100;
								echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
								//个体诊所电子病历书写合格率环比

								//个体诊所电子病历书写合格率同比
								var topNode = document.getElementById('prescriptionYear');
								var topTitleName = '';
								var topRequireInfo = '个体诊所电子病历书写合格率同比';
								var topDataName = [];
								topDataName.push(botData.yearBasis.lastYearTime);
								topDataName.push(botData.now.nowTime);
								var topDataContent = [];
								// console.log(botData.yearBasis.lastYear.qty)
								topDataContent.push(botData.yearBasis.lastYear.qty.split("%")[0]);
								topDataContent.push(botData.now.now.qty.split("%")[0]);
								var topMaxNum = 100;
								echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
								//个体诊所电子病历书写合格率同比


								//table填充开始
								$("#fangHe").html(botData.now.now.qty);
								$("#fangHuan").html(common.anPercent(botData.yearRatio.lastMonth.qty.split("%")[0],botData.now.now.qty.split("%")[0]));
								$("#fangTong").html(common.anPercent(botData.yearBasis.lastYear.qty.split("%")[0],botData.now.now.qty.split("%")[0]));
								//table填充结束
							}else{
								$("#fangHe").html("");
								$("#fangHuan").html("");
								$("#fangTong").html("");

								$("#totalPrescription").html("数据暂无");
								$("#prescriptionRing").html("数据暂无");
								$("#prescriptionYear").html("数据暂无");

							}
						}else{
							$("#bingHe").html("");
							$("#bingHuan").html("");
							$("#bingTong").html("");
							$("#fangHe").html("");
							$("#fangHuan").html("");
							$("#fangTong").html("");

							$("#totalCases").html("数据暂无");
							$("#caseBookChain").html("数据暂无");
							$("#caseBook").html("数据暂无");
							$("#totalPrescription").html("数据暂无");
							$("#prescriptionRing").html("数据暂无");
							$("#prescriptionYear").html("数据暂无");

						}
					}
				})
			}
		}
		if(chooseNum==1){
			if(parentId!=""){
				//诊所数量统计接口
				var url=path.path+"/MedicalServices/SummaryTable";
				// var params={
				// 	"year": year,
				// 	"parentAreaCode":parentId,
				// 	"month":month,
				// 	"reportType":reportType
				// };
				var params={};
				if(reportType=="month"){
					params={
						"parentAreaCode":parentId,
						"year":year,
						"month":month,
						"areaFlag":"1",
						"reportType":reportType
					}
				}else if(reportType=="day"){
					params={
						"parentAreaCode":parentId,
						"year":year,
						"month":month,
						"day":day,
						"areaFlag":"0",
						"reportType":reportType
					}
				}else{
					params={
						"parentAreaCode":parentId,
						"year":year,
						"areaFlag":"2",
						"reportType":reportType
					}
				}
				$.ajax({
					type :"get",
					url : url,
					data :params,
					cache : false,
					async : true,
					success : function(res){
						if(res.code=="000000"&&res.data.length){
							var infoData=res.data;
							//个各下级行政区划个体诊电子病历书写合格情况
							var topDataName = [];
							var topDataContent = [];
							for(var i=0; i<infoData.length; i++){
								topDataName.push(infoData[i].name);
								topDataContent.push(infoData[i].qty.split("%")[0]);
							}
							var topNode = document.getElementById('electronicMedicalRecord');
							var topTitleName = '';
							var topRequireInfo = '个各下级行政区划个体诊电子病历书写合格情况';

							var topMaxNum = 25;
							echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
							//各下级行政区划个体诊电子病历书写合格情况


							//个各下级行政区划个体诊电子病历书写合格情况
							var topDataName = [];
							var topDataContent = [];
							for(var i=0; i<infoData.length; i++){
								topDataName.push(infoData[i].name);
								topDataContent.push(infoData[i].type.split("%")[0]);
							}
							var topNode = document.getElementById('electronicPrescription');
							var topTitleName = '';
							var topRequireInfo = '个各下级行政区划个体诊电子病历书写合格情况';
							var topMaxNum = 25;
							echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
							//各下级行政区划个体诊电子病历书写合格情况

						}else{
							$("#electronicMedicalRecord").html("数据暂无");
							$("#electronicPrescription").html("数据暂无");
						}


					}
				})
			}
		}
		if(chooseNum==2){
			if(parentId!=""){
				//诊所数量统计接口
				var url=path.path+"/MedicalServices/lowerRegion";
				// var params={
				// 	"year": year,
				// 	"parentAreaCode":parentId,
				// 	"month":month,
				// 	"reportType":reportType
				// };
				var params={};
				if(reportType=="month"){
					params={
						"parentAreaCode":parentId,
						"year":year,
						"month":month,
						"areaFlag":"1",
						"reportType":reportType
					}
				}else if(reportType=="day"){
					params={
						"parentAreaCode":parentId,
						"year":year,
						"month":month,
						"day":day,
						"areaFlag":"0",
						"reportType":reportType
					}
				}else{
					params={
						"parentAreaCode":parentId,
						"year":year,
						"areaFlag":"2",
						"reportType":reportType
					}
				}
				$.ajax({
					type :"get",
					url : url,
					data :params,
					cache : false,
					async : true,
					success : function(res){
						if(res.code=="000000"&&res.data.cases.length&&res.data.recipe.length){
							var str='';
							var caseData=res.data.cases;
							var recipeData=res.data.recipe;
							for(var i=0; i<caseData.length; i++){
								str += '<tr><td>'+caseData[i].name+'</td><td>'+caseData[i].qty+'</td><td>'+recipeData[i].qty+'</td></tr>';
							}
							$("#infoTotal").empty().append(str);

						}else{
							$("#infoTotal").html("")
						}
					}
				})
			}
		}
	}
	//菜单的切换结束


});




