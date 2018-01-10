



$(function(){
	var parentId="";


	//区域下拉框开始
	common.dropDownList();
	//区域下拉框结束

	//设置年限开始
	common.setYear();
	common.setMonth();
	//设置年限结束
	changeChartNow();
	//时间概况，当前概况切换
	var clinicInfoTab = $('.clinicInfoTab li');
	clinicInfoTab.on('click', function () {
		$(this).addClass('active').siblings('li').removeClass('active');
	});
	//辖区、下级、信息切换tab
	var nowSituationLi = $('.nowSituation .area li');
	nowSituationLi.on('click',function(){
		changeTab(nowSituationLi,$(this))
	});


	//当前概况中时间方式的选择
	$("#timeStyle").change(function() {
		if($(this).val()=="month"){
			$(".nowSituationMonth").removeClass("hide").addClass("show");
		}else{
			$(".nowSituationMonth").removeClass("show").addClass("hide");
		}
	});


	//区域确认开始
	var numBol=true,relativeBol=true,compareBol=true;
	$("#areaConfirm").click(function(){
		if($('#nowCondition').hasClass('active')){
			changeChartNow()
		}else if($('#timeTrend').hasClass('active')){
			timeTrend()
		}
	});
	$('.query').on('click',function(){
		timeTrend()
	});
	//区域确认结束



	//当前状况与时间趋势的切换开始
	$("#nowCondition").click(function(){
		$("#condition").addClass("show").removeClass('hide');
		$("#trend").addClass("hide").removeClass("show");
	})


	$("#timeTrend").click(function(){
		$("#condition").addClass("hide").removeClass("show");
		$("#trend").addClass("show").removeClass('hide');
		//年趋势月趋势的切换开始
		$(".yearAndDay").change(function(){
			if($(this).val() == 'year'){
				$('.year.timeSelect').show().siblings('.timeSelect').hide();
			}else if($(this).val() == 'month'){
				$('.month.timeSelect').show().siblings('.timeSelect').hide();
			}
		});
		timeTrend();
	});
	$("#nowCondition").click(function(){
		timeTrend();
	});
	$('.queryByTrend').on('click', function () {
		timeTrend();
	});
	function timeTrend(){
		var reportType = $('.yearAndDay').val();
		var startYear = '';
		var endYear = '';
		var startMonth = '';
		var endMonth = '';
		if(reportType == 'year'){
			startYear = $('.yearBeginYear').val();
			endYear = $('.yearEndYear').val();
			$('.beginTitle').html(startYear+'年');
			$('.endTitle').html(endYear+'年');
		}else if(reportType == 'month'){
			startYear = $('.monthBeginYear').val();
			endYear = startYear;
			startMonth = $('.monthBeginMonth').val();
			endMonth = $('.monthEndMonth').val();
			$('.beginTitle').html(startYear+'年'+startMonth+'月');
			$('.endTitle').html(endYear+'年'+endMonth+'月');
		}
		var area = $("#areaChoose").val();
		$(".areaShow").html(area);

		parentId = $('#areaChoose option:selected').attr('data-id');
		if(parentId!=""){
			//诊所数量统计接口
			var url=path.path+"/ClinicResources/timeseriesPerson";
			// var params={
			// 	"startYear": startYear,
			// 	"endYear":endYear,
			// 	"startMonth":startMonth,
			// 	"endMonth":endMonth,
			// 	"reportType":reportType,
			// 	"parentAreaCode":parentId
			// };
			var params={};
			if(reportType=="year"){
				params={
					"startYear": startYear,
					"endYear":endYear,
					"reportType":reportType,
					"parentAreaCode":parentId,
					"areaFlag":"2"
				}
			}else if(reportType=="month"){
				params={
					"startYear": startYear,
					"endYear":endYear,
					"startMonth":startMonth,
					"endMonth":endMonth,
					"reportType":reportType,
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
					if(res.code=="000000"&&res.data){
						var infoData=res.data;
						//个体诊所人员构成概况分析表开始
						var trendXData=[];
						var doctorData=[];
						var nurseData=[];
						var otherData=[];
						for(var i=0; i<infoData.length; i++){
							trendXData.push(infoData[i].date);

							doctorData.push(infoData[i].doctor_qty);
							nurseData.push(infoData[i].nurse_qty);
							otherData.push(infoData[i].other_qty)
						}
						var trendData=[{
							name:'医生',
							data:doctorData
						},{
							name:'护士',
							data:nurseData
						},{
							name:'其他',
							data:otherData
						}]
						var trendNode=document.getElementById('trendChart');
						var trendTitleName='';
						var trendLegendData=['医生', '护士', '其他'];
						var trendYMax=10000;
						var trendSeriesInfo=echart.integrationData(trendData,false,true);
						echart.percentHistogram(trendNode,trendTitleName,trendLegendData,trendXData,trendYMax,trendSeriesInfo)
						//个体诊所人员构成概况分析表结束

					}else{
						$("#trendChart").html("数据暂无");
					}
				}
			})
		}
	}
	//当前状况与时间趋势的切换结束


	//当前概况子菜单的切换开始
	$(".area>li").click(function(){
		$(".areaList").eq($(this).index()).css({display:'block'}).siblings(".areaList").css({display:"none"});
		changeChartNow();

	});

	//按时间查询
	$('.queryByTime').on('click', function () {
		changeChartNow()
	});
	function changeChartNow(){
		$(".areaShow").html($("#areaChoose").val());
		$(".showYear").html($(".nowSituationYear").val()+'年');
		// $(".showMonth").html($(".nowSituationMonth").val()+'月');
		if($("#timeStyle").val()=="month"){
			$(".showMonth").html($(".nowSituationMonth").val()+'月');
		}else{
			$(".showMonth").html("");
		}
		var year = $('.nowSituationYear').val();
		var month = $('.nowSituationMonth').val();
		parentId = $('#areaChoose option:selected').attr('data-id');
		var chooseNum = 0;
		if($('.areaTotal').hasClass('active')){
			chooseNum = 0;
		}else if($('.areaNext').hasClass('active')){
			chooseNum = 1;
		}else if($('.areaAvg').hasClass('active')){
			chooseNum = 2;
		}else if($('.areaInfo').hasClass('active')){
			chooseNum = 3;
		}
		if(chooseNum==0){
			if(parentId!=""){
				//诊所数量统计接口
				var urlTop=path.path+"/ClinicResources/areaPopulationStation";
				var params={};
				if($("#timeStyle").val()=="year"){
					params={
						"year": year,
						"parentAreaCode":parentId,
						"areaFlag":"2"
					}
				}else{
					params={
						"year": year,
						"month":month,
						"parentAreaCode":parentId,
						"areaFlag":"1"
					}
				}
				$.ajax({
					type :"get",
					url : urlTop,
					data :params,
					cache : false,
					async : true,
					success : function(res){
						if(res.code=="000000"){
							if(res.data.nowmrc&&res.data.nowmrc.now){
								var nowData=res.data.nowmrc;
								//个体诊所人员数量总计表开始
								var situationData=[{
									name:'医生',
									data:[]
								},{
									name:'护士',
									data:[]
								},{
									name:'其他',
									data:[]
								}]
								situationData[0].data.push(nowData.now.dctorQty);
								situationData[1].data.push(nowData.now.nurseQty);
								situationData[2].data.push(nowData.now.otherQty);
								var situationNode=document.getElementById('situationNum');
								var totalNum=nowData.now.dctorQty+nowData.now.nurseQty+nowData.now.otherQty;
								var situationTitleName='总计：'+totalNum+'人';
								var situationLegendData=['医生', '护士', '其他'];
								var situationXData=[];
								if($("#timeStyle").val()=="year"){
									situationXData.push(nowData.nowTime.substring(0,5));                
								}else{
									situationXData.push(nowData.nowTime);      
								}
								var situationYMax=450000;
								var situationSeriesInfo=echart.integrationData(situationData,true,false)
								echart.percentHistogram(situationNode,situationTitleName,situationLegendData,situationXData,situationYMax,situationSeriesInfo)
								//个体诊所人员数量总计表结束

								$("#numPercent").html(parseInt((nowData.now.dctorQty)/parseInt(nowData.now.nurseQty)*10)/10+":1");
								$("#sumNumber").html(totalNum);
								$("#docNum").html(nowData.now.dctorQty);
							}else{

								$("#numPercent").html("");
								$("#sumNumber").html("");
								$("#docNum").html("");

								$("#situationNum").html("数据暂无");
							}
							

							if(res.data.nowmrc&&res.data.yearRatioMrc&&res.data.yearRatioMrc.now){
								var monthData=res.data.yearRatioMrc;
								//个体诊所人员环比总计表开始
								var relativeData=[{
								name:'医生',
									data:[]
								},{
									name:'护士',
									data:[]
								},{
									name:'其他',
									data:[]
								}]
								relativeData[0].data.push(monthData.lastMonth.dctorQty);
								relativeData[0].data.push(monthData.now.dctorQty);
								relativeData[1].data.push(monthData.lastMonth.nurseQty);
								relativeData[1].data.push(monthData.now.nurseQty);
								relativeData[2].data.push(monthData.lastMonth.otherQty);
								relativeData[2].data.push(monthData.now.otherQty);
								var relativeNode=document.getElementById('situationRelative');
								var nowRelative=[monthData.now.dctorQty,monthData.now.nurseQty,monthData.now.otherQty];
								var lastRelative=[monthData.lastMonth.dctorQty,monthData.lastMonth.nurseQty,monthData.lastMonth.otherQty];
								var relativeTitleName='环比增长率： '+common.relativePercent(lastRelative,nowRelative)+'%';
								var relativeLegendData=['医生', '护士', '其他'];
								var relativeXData=[];
								if($("#timeStyle").val()=="year"){
									relativeXData.push(monthData.lastMonthTime.substring(0,5));
									relativeXData.push(nowData.nowTime.substring(0,5));               
								}else{
									relativeXData.push(monthData.lastMonthTime);
									relativeXData.push(nowData.nowTime);    
								}
								relativeXData.push(monthData.lastMonthTime);
								relativeXData.push(nowData.nowTime);
								var relativeYMax=600000;
								var relativeSeriesInfo=echart.integrationData(relativeData,false,true);
								echart.percentHistogram(relativeNode,relativeTitleName,relativeLegendData,relativeXData,relativeYMax,relativeSeriesInfo)
								//个体诊所人员数量环比总计表结束

								$("#momAdd").html(common.relativePercent(lastRelative,nowRelative)+"%");
								$("#docMum").html(common.anPercent(monthData.lastMonth.dctorQty,nowData.now.dctorQty));
							}else{
								$("#momAdd").html("");
								$("#docMum").html("");

								$("#situationRelative").html("数据暂无");
							}
							

							if(res.data.nowmrc&&res.data.yearBasisMrc&&res.data.yearBasisMrc.now){
								var yearData=res.data.yearBasisMrc;
								//个体诊所人员同比总计表开始
								var compareData=[{
									name:'医生',
									data:[]
								},{
									name:'护士',
									data:[]
								},{
									name:'其他',
									data:[]
								}]
								compareData[0].data.push(yearData.lastYear.dctorQty);
								compareData[0].data.push(yearData.now.dctorQty);
								compareData[1].data.push(yearData.lastYear.nurseQty);
								compareData[1].data.push(yearData.now.nurseQty);
								compareData[2].data.push(yearData.lastYear.otherQty);
								compareData[2].data.push(yearData.now.otherQty);
								var compareNode=document.getElementById('situationCompare');
								var nowContent=[yearData.now.dctorQty,yearData.now.nurseQty,yearData.now.otherQty];
								var lastContent=[yearData.lastYear.dctorQty,yearData.lastYear.nurseQty,yearData.lastYear.otherQty]
								var compareTitleName='同比增长率： '+common.relativePercent(lastContent,nowContent)+'%';
								var compareLegendData=['医生', '护士', '其他'];
								var compareXData=[];
								if($("#timeStyle").val()=="year"){
									compareXData.push(yearData.lastYearTime.substring(0,5));
									compareXData.push(nowData.nowTime.substring(0,5));              
								}else{
									compareXData.push(yearData.lastYearTime);
									compareXData.push(nowData.nowTime);
								}
								
								var compareYMax=600000;
								var compareSeriesInfo=echart.integrationData(compareData,false,true);
								echart.percentHistogram(compareNode,compareTitleName,compareLegendData,compareXData,compareYMax,compareSeriesInfo)
								//个体诊所人员数量同比总计表结束

								$("#anAdd").html(common.relativePercent(lastContent,nowContent)+"%");
								$("#docAn").html(common.anPercent(yearData.lastYear.dctorQty,nowData.now.dctorQty))
							}else{
								$("#anAdd").html("");
								$("#docAn").html("");

								$("#situationCompare").html("数据暂无");
							}
						
						}else{
							$("#numPercent").html("");
							$("#sumNumber").html("");
							$("#momAdd").html("");
							$("#anAdd").html("");
							$("#docNum").html("");
							$("#docMum").html("");
							$("#docAn").html("");
							$("#situationNum").html("数据暂无");
							$("#situationRelative").html("数据暂无");
							$("#situationCompare").html("数据暂无");
						}


					}
				})

				var urlBottom=path.path+"/ClinicResources/areaDoctorTitle";
				$.ajax({
					type :"get",
					url : urlBottom,
					data :params,
					cache : false,
					async : true,
					success : function(res){
						if(res.code="000000"){
							if(res.data.now&&res.data.now.now.length){
								var nowData=res.data.now;
								//个体诊所医生数量总计表开始
								var proData=[{
									name:'初级职称',
									data:[]
								},{
									name:'中级职称',
									data:[]
								},{
									name:'高级职称',
									data:[]
								}]
								proData[0].data.push(parseInt(nowData.now[0].qty));
								proData[1].data.push(parseInt(nowData.now[1].qty));
								proData[2].data.push(parseInt(nowData.now[2].qty));
								var proNumNode=document.getElementById('professionNum');
								var totalNum=parseInt(nowData.now[0].qty)+parseInt(nowData.now[1].qty)+parseInt(nowData.now[2].qty);
								var proNumTitleName='总计：'+totalNum+'人';
								var proNumLegendData=['初级职称', '中级职称', '高级职称'];
								var proNumXData=[];
								if($("#timeStyle").val()=="year"){
									proNumXData.push(nowData.nowTime.substring(0,5));             
								}else{
									proNumXData.push(nowData.nowTime);
								}
								var proNumYMax=600000;
								var proNumSeriesInfo=echart.integrationData(proData,true,true);
								echart.percentHistogram(proNumNode,proNumTitleName,proNumLegendData,proNumXData,proNumYMax,proNumSeriesInfo)
								//个体诊所医生数量总计表结束
							}else{
								$("#professionNum").html("数据暂无");
							}
							

							if(res.data.yearRatioMap&&res.data.yearRatioMap.now.length){
								var monthData=res.data.yearRatioMap;
								//个体诊所医生数量环比总计表开始
								var proRelData=[{
									name:'初级职称',
									data:[]
								},{
									name:'中级职称',
									data:[]
								},{
									name:'高级职称',
									data:[]
								}]
								proRelData[0].data.push(monthData.lastMonth[0].qty);
								proRelData[0].data.push(monthData.now[0].qty);
								proRelData[1].data.push(monthData.lastMonth[1].qty);
								proRelData[1].data.push(monthData.now[1].qty);
								proRelData[2].data.push(monthData.lastMonth[2].qty);
								proRelData[2].data.push(monthData.now[2].qty);
								var proRelNode=document.getElementById('professionRelative');
								var nowContent=[monthData.now[0].qty,monthData.now[1].qty,monthData.now[2].qty];
								var lastContent=[monthData.lastMonth[0].qty,monthData.lastMonth[1].qty,monthData.lastMonth[2].qty];
								var proRelTitleName='环比增长率： '+common.relativePercent(lastContent,nowContent)+'%';
								var proRelLegendData=['初级职称', '中级职称', '高级职称'];
								var proRelXData=[];
								if($("#timeStyle").val()=="year"){
									proRelXData.push(monthData.lastMonthTime.substring(0,5));
									proRelXData.push(monthData.nowTime.substring(0,5));           
								}else{
									proRelXData.push(monthData.lastMonthTime);
									proRelXData.push(monthData.nowTime);
								}
								
								var proRelYMax=600000;
								var proRelSeriesInfo=echart.integrationData(proRelData,false,true);
								echart.percentHistogram(proRelNode,proRelTitleName,proRelLegendData,proRelXData,proRelYMax,proRelSeriesInfo)
								//个体诊所医生数量环比总计表结束
							}else{
								$("#professionRelative").html("数据暂无");
							}
							
							if(res.data.yearBasisMap&&res.data.yearBasisMap.now.length){
								var yearData=res.data.yearBasisMap;
								//个体诊所医生数量同比总计表开始
								var proRelData=[{
									name:'初级职称',
									data:[]
								},{
									name:'中级职称',
									data:[]
								},{
									name:'高级职称',
									data:[]
								}];
								var lastQtyZero = 0;
								if(!yearData.lastYear.length){
									lastQtyZero = 0
								}else{
									lastQtyZero = yearData.lastYear[0].qty
								}
								proRelData[0].data.push(lastQtyZero);
								var nowQtyZero = 0;
								if(!yearData.now.length){
									nowQtyZero = 0
								}else{
									nowQtyZero = yearData.now[0].qty
								}
								proRelData[0].data.push(yearData.now[0].qty);

								var lastYearQtyOne = 0;
								if(!yearData.lastYear.length){
									lastYearQtyOne = 0
								}else{
									lastYearQtyOne = yearData.lastYear[1].qty
								}
								proRelData[1].data.push(lastYearQtyOne);

								var nowYearQtyOne = 0;
								if(!yearData.now.length){
									nowYearQtyOne = 0
								}else{
									nowYearQtyOne = yearData.now[1].qty
								}
								proRelData[1].data.push(nowYearQtyOne);

								var lastYearQtyTwo = 0;
								if(!yearData.lastYear.length){
									lastYearQtyTwo = 0
								}else{
									lastYearQtyTwo = yearData.lastYear[2].qty
								}
								proRelData[2].data.push(lastYearQtyTwo);

								var nowYearQtyTwo = 0;
								if(!yearData.lastYear.length){
									nowYearQtyTwo = 0
								}else{
									nowYearQtyTwo = yearData.now[2].qty
								}
								proRelData[2].data.push(nowYearQtyTwo);
								var proComNode=document.getElementById('professionCompare');
								var nowContent=[nowQtyZero,nowYearQtyOne,nowYearQtyTwo];
								var lastContent=[lastQtyZero,lastYearQtyOne,lastYearQtyTwo];
								var proComTitleName='同比增长率： '+common.relativePercent(lastContent,nowContent)+'%';
								var proComLegendData=['初级职称', '中级职称', '高级职称'];
								var proComXData=[];
								if($("#timeStyle").val()=="year"){
									proComXData.push(yearData.lastYearTime.substring(0,5));
									proComXData.push(yearData.nowTime.substring(0,5));          
								}else{
									proComXData.push(yearData.lastYearTime);
									proComXData.push(yearData.nowTime);
								}
								var proComYMax=600000;
								var proComSeriesInfo=echart.integrationData(proRelData,false,true);
								echart.percentHistogram(proComNode,proComTitleName,proComLegendData,proComXData,proComYMax,proComSeriesInfo)
								//个体诊所医生数量同比总计表结束
							}else{
								$("#professionCompare").html("数据暂无");
							}
							
						}else{
							$("#professionNum").html("数据暂无");
							$("#professionRelative").html("数据暂无");
							$("#professionCompare").html("数据暂无");
						}



					}
				})




			}


		}


		if(chooseNum==1){
			if(parentId!=""){
				//各下级行政区划个体诊所人员岗位分布接口
				var urlTop=path.path+"/ClinicResources/PositionDistribution";
				var params={};
				if($("#timeStyle").val()=="year"){
					params={
						"year": year,
						"parentAreaCode":parentId,
						"areaFlag":"2"
					}
				}else{
					params={
						"year": year,
						"month":month,
						"parentAreaCode":parentId,
						"areaFlag":"1"
					}
				}
				$.ajax({
					type :"get",
					url : urlTop,
					data :params,
					cache : false,
					async : true,
					success : function(res){
						if(res.code="000000"&&res.data){
							//各下级行政区划个体诊所人员岗位分布表开始
							var infoData=res.data;
							var distributeXData=[];
							var doctorData=[];
							var nurseData=[];
							var otherData=[];
							for(var i=0; i<infoData.length; i++){
								distributeXData.push(infoData[i].area_name);
								doctorData.push(infoData[i].doctor);
								nurseData.push(infoData[i].nurse);
								otherData.push(infoData[i].other)
							}
							var distributeData=[{
								name:'医生',
								data:doctorData
							},{
								name:'护士',
								data:nurseData
							},{
								name:'其他',
								data:otherData
							}]
							var distributeLegendData=['医生', '护士', '其他'];
							var distributeNode=document.getElementById('distributeChart');
							var distributeTitleName='';
							var distributeYMax=10000;
							var distributeSeriesInfo=echart.integrationData(distributeData,false,true);
							echart.percentHistogram(distributeNode,distributeTitleName,distributeLegendData,distributeXData,distributeYMax,distributeSeriesInfo)
							//各下级行政区划个体诊所人员岗位分布表结束
						}else{
							$("#distributeChart").html("数据暂无");
						}
					}
				})

				//各下级行政区划个体诊所医生职称分布接口
				var urlBottom=path.path+"/ClinicResources/DoctorTitle";
				$.ajax({
					type :"get",
					url : urlBottom,
					data :params,
					cache : false,
					async : true,
					success : function(res){
						if(res.code=="000000"&&res.data){
							var infoData=res.data;
							//各下级行政区划个体诊所医生职称分布表开始
							var jobXData=[];
							var lowData=[];
							var midData=[];
							var highData=[];
							for(var i=0; i<infoData.length; i++){
								lowData.push(infoData[i].low);
								midData.push(infoData[i].medium);
								highData.push(infoData[i].high);
								jobXData.push(infoData[i].areaName);
							}
							var jobData=[{
								name:'初级职称',
								data:lowData
							},{
								name:'中级职称',
								data:midData
							},{
								name:'高级职称',
								data:highData
							}]
							var jobNode=document.getElementById('jobChart');
							var jobTitleName='';
							var jobLegendData=['初级职称', '中级职称', '高级职称'];
							var jobYMax=10000;
							var jobSeriesInfo=echart.integrationData(jobData,false,true);
							echart.percentHistogram(jobNode,jobTitleName,jobLegendData,jobXData,jobYMax,jobSeriesInfo)
							//各下级行政区划个体诊所医生职称分布表结束
						}else{
							$("#jobChart").html("数据暂无");
						}
					}
				})

			}


		}


		if(chooseNum==2){
			if(parentId!=""){
				//诊所数量统计接口
				var urlTop=path.path+"/ClinicResources/headcountAvg";
				var params={};
				if($("#timeStyle").val()=="year"){
					params={
						"year": year,
						"parentAreaCode":parentId,
						"areaFlag":"2"
					}
				}else{
					params={
						"year": year,
						"month":month,
						"parentAreaCode":parentId,
						"areaFlag":"1"
					}
				}
				$.ajax({
					type :"get",
					url : urlTop,
					data :params,
					cache : false,
					async : true,
					success : function(res){
						if(res.code=="000000"&&res.data){
							var infoData=res.data;
							//各下级行政区划个体诊所人员数量表开始
							var evenDataName=[];
							var evenDataContent=[];
							for(var i=0; i<infoData.length; i++){
								evenDataName.push(infoData[i].area_name);
								evenDataContent.push(infoData[i].avg);
							}
							var evenNode=document.getElementById('evenChart');
							var evenTitleName='';
							var evenRequireInfo='个体诊所人员数量的平均分布';
							var evenMaxNum=20;
							echart.histogram(evenNode,evenTitleName,evenRequireInfo,evenDataName,evenDataContent,evenMaxNum,false)
							//各下级行政区划个体诊所人员数量表结束

						}else{
							$("#evenChart").html("数据暂无");
						}
					}
				})

				//诊所数量统计接口
				var urlBottom=path.path+"/ClinicResources/doctorAbility";
				$.ajax({
					type :"get",
					url : urlBottom,
					data :params,
					cache : false,
					async : true,
					success : function(res){
						if(res.code=="000000"&&res.data){
							var infoData=res.data;
							//各下级行政区划个体诊所医生职称的平均分布表开始
							var juniorData=[];
							var midData=[];
							var highData=[];
							var levelXData=[];
							for(var i=0; i<infoData.length; i++){
								juniorData.push(infoData[i].low);
								midData.push(infoData[i].mid);
								highData.push(infoData[i].high);

								levelXData.push(infoData[i].area_name);
							}
							var levelData=[{
								name:'初级职称',
								data:juniorData
							},{
								name:'中级职称',
								data:midData
							},{
								name:'高级职称',
								data:highData
							}]
							var levelNode=document.getElementById('levelChart');
							var levelTitleName='';
							var levelLegendData=['初级职称', '中级职称', '高级职称'];
							var levelYMax=20;
							var levelSeriesInfo=echart.integrationData(levelData,false,true);
							echart.percentHistogram(levelNode,levelTitleName,levelLegendData,levelXData,levelYMax,levelSeriesInfo)
							//各下级行政区划个体诊所医生职称的平均分布表结束

						}else{
							$("#levelChart").html("数据暂无");
						}
					}
				})




			}




		}


		if(chooseNum==3){

			if(parentId!=""){
				//诊所数量统计接口
				var url=path.path+"/ClinicResources/PersonnelForm";
				var params={};
				if($("#timeStyle").val()=="year"){
					params={
						"year": year,
						"parentAreaCode":parentId,
						"areaFlag":"2"
					}
				}else{
					params={
						"year": year,
						"month":month,
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
						var str='<tr class="tableHead"> <td rowspan="2">行政区域规划</td> <td rowspan="2">医护比</td> <td rowspan="2">总人数（人）</td> <td colspan="3">岗位</td> <td colspan="3">职称</td> <td rowspan="2">平均人员数量</td> </tr> <tr class="tableHead"> <td>医生</td> <td>护士</td> <td>其他</td> <td>高级</td> <td>中级</td> <td>初级</td> </tr>';
						if(res.code=="000000"){
							$.each(res.data,function(){
								str+='<tr><td>'+this.area_name+'</td><td>'+this.mrc+'</td><td>'+this.count+'</td><td>'+this.doctor_qty+'</td><td>'+this.nurse_qty+'</td><td>'+this.other_qty+'</td><td>'+this.high+'</td><td>'+this.mid+'</td><td>'+this.low+'</td><td>'+this.avg+'</td></tr>'
							});
							$("#infoTotalTable").empty().append(str);
						}else{
							$("#infoTotalTable").html("")
						}
					}
				})
			}
		}
	}
})




