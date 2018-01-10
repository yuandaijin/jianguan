





$(function(){
	var parentId="";
	//区域下拉框开始
	common.dropDownList();
	//区域下拉框结束

	//设置年限开始
	common.setYear();
	common.setMonth();
	//设置年限结束
	numStat();
	
	//当前概况中时间方式的选择
	$("#timeStyle").change(function() {
		if($(this).val()=="month"){
			$(".nowProfileMonth").removeClass("hide").addClass("show");
		}else{
			$(".nowProfileMonth").removeClass("show").addClass("hide");
		}
	});

	//显示选中时间地区
	function showAreaEtc(){
		var area = $("#areaChoose").val();
		var year = $(".nowProfileYear").val();
		var month = $(".nowProfileMonth").val();
		$(".areaShow").html(area);
		$(".yearShow").html(year+'年');
		if($("#timeStyle").val()=="month"){
			$(".monthShow").html(month+'月');
		}else{
			$(".monthShow").html("");
		}
		
	}

	function showTimeEtc(){
		var area = $("#areaChoose").val();
		var yearY = $(".yearBeginYear").val();
		var monthY = $(".yearEndYear").val();
		var yearM = $(".monthBeginYear").val();
		var yearMY = $(".monthBeginMonth").val();
		var monthMM = $(".monthEndMonth").val();
		$(".areaShow").html(area);
		var reportType = $('.yearAndDay').val();
		if(reportType == 'year'){
			$('.beginTitle').html(yearY+'年');
			$('.endTitle').html(monthY+'年');
		}else if(reportType == 'month'){
			$('.beginTitle').html(yearM+'年'+yearMY+'月');
			$('.endTitle').html(yearM+'年'+monthMM+'月');
		}
	}

    //区域确认开始
    $("#areaConfirm").click(function(){
		var conditonNum = 0;
		if($("#nowCondition").hasClass('active')){
			conditonNum = 1;
		}else if($("#timeTrend").hasClass('active')){
			conditonNum = 2;
		}
		if(conditonNum == 1){
			chooseActive();
		}else if(conditonNum == 2){
			timeTrend()
		}
    });

	//时间确认
	$(".nowProfileTime").click(function(){
		chooseActive();
    });

    function chooseActive(){
		var chooseNumber = 0;
		if($('.totalCondition').hasClass('active')){
			if($('.numStat').hasClass('active')){
				chooseNumber = 1;
			}else if($('.classStat').hasClass('active')){
				chooseNumber = 2;
			}
		}else if($('.lowerLocation').hasClass('active')){
			chooseNumber = 3;
		}else if($('.infoTotal').hasClass('active')){
			chooseNumber = 4;
		}
		if(chooseNumber == 1){
			numStat();
		}else if(chooseNumber == 2){
			classStat();
		}else if(chooseNumber == 3){
			lowerLocation();
		}else if(chooseNumber == 4){
			infoTotal();
		}
	}



	//当前状况与时间趋势的切换开始
	$("#nowCondition").click(function(){
		$("#condition").addClass("show").removeClass('hide');
		$("#trend").addClass("hide").removeClass("show");
	});


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
		//年趋势月趋势的切换结束
		timeTrend();
	});
	
	$('.timeQuery').on('click',function(){
		timeTrend();
	});
	function timeTrend(){
		parentId = $('#areaChoose option:selected').attr('data-id');
		showTimeEtc();
		var reportType = $('.yearAndDay').val();
		var startYear = '';
		var endYear = '';
		var startMonth = '';
		var endMonth = '';
		if(reportType == 'year'){
			startYear = $('.yearBeginYear').val();
			endYear = $('.yearEndYear').val();
		}else if(reportType == 'month'){
			startYear = $('.monthBeginYear').val();
			endYear = $('.monthBeginYear').val();
			startMonth = $('.monthBeginMonth').val();
			endMonth = $('.monthEndMonth').val();
		}
		if(parentId!=""){
			//诊所数量统计接口
			var url=path.path+"/ClinicResources/timeseries";
			// var params={
			// 	"startYear":startYear,
			// 	"endYear":endYear,
			// 	"startMonth": startMonth,
			// 	"endMonth":endMonth,
			// 	"parentAreaCode":parentId,
			// 	"reportType":reportType
			// };
			var params={};
			if(reportType=="year"){
				params={
					"startYear":startYear,
					"endYear":endYear,
					"parentAreaCode":parentId,
					"reportType":reportType,
					"areaFlag":"2"
				}
			}else if(reportType=="month"){
				params={
					"startYear":startYear,
					"endYear":endYear,
					"startMonth": startMonth,
					"endMonth":endMonth,
					"parentAreaCode":parentId,
					"reportType":reportType,
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
					if(res.code == '000000'){
						var numRelativeNode=document.getElementById('numChart');
						var numRelativeTitleName='';
						var numRelativeRequireInfo='个体诊所数量变化情况';
						var numRelativeDataName=[];
						var numRelativeDataContent=[];
						var numRelativeMaxNum=18000;
						$.each(res.data,function(){
							numRelativeDataName.push(this.date);
							numRelativeDataContent.push(this.qty);
						})
						echart.histogram(numRelativeNode,numRelativeTitleName,numRelativeRequireInfo,numRelativeDataName,numRelativeDataContent,false)
						//个体诊所数量变化趋势分析表结束
					}else{
						$("#numChart").html("数据暂无");
					}

				}
			})
		}
	}
	//当前状况与时间趋势的切换结束

	//辖区总体情况菜单的下拉与收取开始
	$(".totalCondition").mouseenter(function(){
		$(".totalNav").removeClass("hide").addClass('show')
	});
	$(".totalCondition").mouseleave(function(){
		$(".totalNav").removeClass("show").addClass('hide')
	});
	//辖区总体情况菜单的下拉与收取结束

	//辖区、下级、信息切换tab
	var nowSituationLi = $('.nowSituation .area li');
	nowSituationLi.on('click',function(){
		changeTab(nowSituationLi,$(this))
	});
	//辖区hidedivTab
	var totalConditionDiv = $('.area .totalCondition .totalNav div');
	totalConditionDiv.on('click', function () {
		changeTab(totalConditionDiv,$(this))
	});
	//时间概况，当前概况切换
	var clinicInfoTab = $('.clinicInfoTab li');
	clinicInfoTab.on('click', function () {
		changeTab(clinicInfoTab,$(this))
	});



	//诊所数量统计绑定click事件开始
	$(".numStat").bind("click",function(){
		$(".areaList").eq(0).css({display:'block'}).siblings(".areaList").css({display:"none"});

		numStat();

	});
	function numStat(){
		showAreaEtc();
		var nowProfileYear = $('.nowProfileYear').val();
		var nowProfileMonth = $('.nowProfileMonth').val();
		parentId = $('#areaChoose option:selected').attr('data-id');
		if(parentId!=""){
			//诊所数量统计接口
			var url=path.path+"/ClinicResources/ClinicQty ";
			var params={};
			if($("#timeStyle").val()=="year"){
				var params={
					"year": nowProfileYear,
					"parentAreaCode":parentId,
					"areaFlag":"2"
				}
			}else{
				var params={
					"year": nowProfileYear,
					"month":nowProfileMonth,
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
					if(res.code="000000"&&res.data){
						var numData=res.data;
						//个体诊所数量总计表开始
						var clinicsTotalNumNode=document.getElementById('clinicsTotalNum');
						var clinicsTotalNumTitleName='总计：'+numData[0].now+'个';
						var clinicsTotalNumRequireInfo='个体诊所数量';
						var clinicsTotalNumDataName=[];
						if($("#timeStyle").val()=="year"){
							clinicsTotalNumDataName.push(numData[0].nowTime.substring(0,5));
						}else{
							clinicsTotalNumDataName.push(numData[0].nowTime);
						}
						var clinicsTotalNumDataContent=[];
						clinicsTotalNumDataContent.push(numData[0].now);
						var clinicsTotalNumMaxNum=18000;
						echart.histogram(clinicsTotalNumNode,clinicsTotalNumTitleName,clinicsTotalNumRequireInfo,clinicsTotalNumDataName,clinicsTotalNumDataContent,clinicsTotalNumMaxNum,true);
						$("#numAll").html(numData[0].now);
						//个体诊所数量总计表结束


						//个体诊所数量环比表开始
						var clinicsNumRelativeNode=document.getElementById('clinicsNumRelative');
						var clinicsNumRelativeTitleName='环比增长率：'+common.anPercent(numData[1].CliYearRatioResult.lastMonth,numData[1].CliYearRatioResult.now,true);
						var clinicsNumRelativeRequireInfo='个体诊所数量环比';
						var clinicsNumRelativeDataName=[];
						if($("#timeStyle").val()=="year"){
							clinicsNumRelativeDataName.push(numData[1].CliYearRatioResult.lastMonthTime.substring(0,5));
							clinicsNumRelativeDataName.push(numData[1].CliYearRatioResult.nowTime.substring(0,5));
						}else{
							clinicsNumRelativeDataName.push(numData[1].CliYearRatioResult.lastMonthTime);
							clinicsNumRelativeDataName.push(numData[1].CliYearRatioResult.nowTime);
						}
						clinicsNumRelativeDataName.push(numData[1].CliYearRatioResult.lastMonthTime);
						clinicsNumRelativeDataName.push(numData[1].CliYearRatioResult.nowTime);
						var clinicsNumRelativeDataContent=[];
						clinicsNumRelativeDataContent.push(numData[1].CliYearRatioResult.lastMonth);
						clinicsNumRelativeDataContent.push(numData[1].CliYearRatioResult.now);
						var clinicsNumRelativeMaxNum=18000;
						echart.histogram(clinicsNumRelativeNode,clinicsNumRelativeTitleName,clinicsNumRelativeRequireInfo,clinicsNumRelativeDataName,clinicsNumRelativeDataContent,clinicsNumRelativeMaxNum,false);
						$("#anbi").html(common.anPercent(numData[1].CliYearRatioResult.lastMonth,numData[1].CliYearRatioResult.now));
						//个体诊所数量环比表结束


						//个体诊所数量同比表开始
						var clinicsNumCompareNode=document.getElementById('clinicsNumCompare');
						var clinicsNumCompareTitleName='同比增长率：'+common.anPercent(numData[2].CliYearBasisResult.lastYear,numData[2].CliYearBasisResult.now,true);
						var clinicsNumCompareRequireInfo='个体诊所数量同比';
						var clinicsNumCompareDataName=[];
						if($("#timeStyle").val()=="year"){
							clinicsNumCompareDataName.push(numData[2].CliYearBasisResult.lastYearTime.substring(0,5));
							clinicsNumCompareDataName.push(numData[2].CliYearBasisResult.nowTime.substring(0,5));
						}else{
							clinicsNumCompareDataName.push(numData[2].CliYearBasisResult.lastYearTime);
							clinicsNumCompareDataName.push(numData[2].CliYearBasisResult.nowTime);
						}
						var clinicsNumCompareDataContent=[];
						clinicsNumCompareDataContent.push(numData[2].CliYearBasisResult.lastYear);
						clinicsNumCompareDataContent.push(numData[2].CliYearBasisResult.now);
						var clinicsNumCompareMaxNum=18000;
						echart.histogram(clinicsNumCompareNode,clinicsNumCompareTitleName,clinicsNumCompareRequireInfo,clinicsNumCompareDataName,clinicsNumCompareDataContent,clinicsNumCompareMaxNum,false);
						$("#mombi").html(common.anPercent(numData[2].CliYearBasisResult.lastYear,numData[2].CliYearBasisResult.now))
						//个体诊所数量同比表结束
					}else{
						$("#clinicsTotalNum").html("数据暂无");
						$("#clinicsNumRelative").html("数据暂无");
						$("#clinicsNumCompare").html("数据暂无");
					}



				}
			})
		}
	}
	//诊所数量统计绑定click事件结束




	//诊所类型统计绑定click事件开始
	$(".classStat").bind("click",function(){
		$(".areaList").eq(1).css({display:'block'}).siblings(".areaList").css({display:"none"});
		classStat();
	});
	function classStat(){
		parentId = $('#areaChoose option:selected').attr('data-id');
		showAreaEtc();
		var nowProfileYear = $('.nowProfileYear').val();
		var nowProfileMonth = $('.nowProfileMonth').val();
		var organizerBol=true,clinicsBol=true,levelBol=true,scaleBol=true;
		if(parentId!=""){
			//诊所数量统计接口
			var url=path.path+"/ClinicResources/ClinicType ";
			var params={};
			if($("#timeStyle").val()=="year"){
				var params={
					"year": nowProfileYear,
					"parentAreaCode":parentId,
					"areaFlag":"2"
				}
			}else{
				var params={
					"year": nowProfileYear,
					"month":nowProfileMonth,
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
					if(res.code=="000000"){
						// 个体诊所主办单位分类表开始
						var unitsData=res.data.units;
						var organizerClassesNode=document.getElementById('organizerClasses');
						// var organizerClassesLegendData=['社会办','个人办','其他'];
						// var organizerClassesSeriesData=[{value:4000, name:'社会办'},{value:10000, name:'个人办'},{value:3000, name:'其他'}];
						var organizerClassesLegendData=[];
						var organizerClassesSeriesData=[];
						for(var i=0; i<unitsData.length; i++){
							organizerClassesLegendData.push(unitsData[i].name);
							var obj={};
							obj.value=unitsData[i].qty;
							obj.name=unitsData[i].name;
							organizerClassesSeriesData.push(obj);
						}
						var organizerClassesSeriesName='个体诊所数量总计';
						echart.pinChart(organizerClassesNode,organizerClassesLegendData,organizerClassesSeriesName,organizerClassesSeriesData);
						
						//个体诊所主办单位分类表结束

						//个体诊所诊所类型分类表开始
						var typesData=res.data.types;
						var clinicsClassesNode=document.getElementById('clinicsClasses');
						// var clinicsClassesLegendData=['普通','中医','中西医','口腔','其他'];
						// var clinicsClassesSeriesData=[{value:8000, name:'普通'},{value:8000, name:'中医'},{value:2000, name:'中西医'},{value:2000, name:'口腔'},{value:2000, name:'其他'}];
						var clinicsClassesLegendData=[];
						var clinicsClassesSeriesData=[];
						for(var i=0; i<typesData.length; i++){
							clinicsClassesLegendData.push(typesData[i].name);
							var obj={};
							obj.value=typesData[i].qty;
							obj.name=typesData[i].name;
							clinicsClassesSeriesData.push(obj);
						}
						var clinicsClassesSeriesName='个体诊所诊所类型分类';
						echart.pinChart(clinicsClassesNode,clinicsClassesLegendData,clinicsClassesSeriesName,clinicsClassesSeriesData);
						
						//个体诊所诊所类型分类表结束

						// 个体诊所级别分类表开始
						var levelsData=res.data.levels;
						var levelClassesNode=document.getElementById('levelClasses');
						// var levelClassesLegendData=['一星','二星','三星','四星','五星'];
						// var levelClassesSeriesData=[{value:2000, name:'一星'},{value:2000, name:'二星'},{value:2000, name:'三星'},{value:2000, name:'四星'},{value:2000, name:'五星'}];
						var levelClassesLegendData=[];
						var levelClassesSeriesData=[];
						for(var i=0; i<levelsData.length; i++){
							levelClassesLegendData.push(levelsData[i].name);
							var obj={};
							obj.value=levelsData[i].qty;
							obj.name=levelsData[i].name;
							levelClassesSeriesData.push(obj);
						}
						var levelClassesSeriesName='个体诊所级别分类';
						echart.pinChart(levelClassesNode,levelClassesLegendData,levelClassesSeriesName,levelClassesSeriesData);
						
						//个体诊所级别分类表结束

						//个体诊所规模分类表开始
						var scalesData=res.data.scales;
						var scaleClassesNode=document.getElementById('scaleClasses');
						// var scaleClassesLegendData=['小型<3人','中型3-5人','大型>5人'];
						// var scaleClassesSeriesData=[{value:6000, name:'小型<3人'},{value:7000, name:'中型3-5人'},{value:4000, name:'大型>5人'}];
						var scaleClassesLegendData=[];
						var scaleClassesSeriesData=[];
						for(var i=0; i<scalesData.length; i++){
							scaleClassesLegendData.push(scalesData[i].name);
							var obj={};
							obj.value=scalesData[i].qty;
							obj.name=scalesData[i].name;
							scaleClassesSeriesData.push(obj);
						}
						var scaleClassesSeriesName='个体诊所规模分类';
						echart.pinChart(scaleClassesNode,scaleClassesLegendData,scaleClassesSeriesName,scaleClassesSeriesData);
							
						//个体诊所规模分类表结束
					}else{
						$("#organizerClasses").html("数据暂无");
						$("#clinicsClasses").html("数据暂无");
						$("#levelClasses").html("数据暂无");
						$("#scaleClasses").html("数据暂无");
					}

				}
			})
		}

		if(parentId!=""){
			//诊所数量统计接口
			var url=path.path+"/ClinicResources/Information";
			var params={};
			if($("#timeStyle").val()=="year"){
				var params={
					"year": nowProfileYear,
					"parentAreaCode":parentId,
					"areaFlag":"2"
				}
			}else{
				var params={
					"year": nowProfileYear,
					"month":nowProfileMonth,
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
						var sumData=res.data;
					/*	var str='<tr class="tableHead"> <td rowspan="2">行政区划</td> <td rowspan="2">总数</td> <td colspan="3">主办单位</td> <td colspan="5">诊所类别</td> <td colspan="4">诊所级别</td> <td colspan="3">诊所规模</td> </tr> <tr class="tableHead"> <td>社会办</td> <td>个人办</td> <td>其他</td> <td>普通</td> <td>中医</td> <td>中西医口腔结合</td> <td>口腔</td> <td>其他</td> <td>五星级</td> <td>四星级</td> <td>三星级</td> <td>未评级</td> <td>小型<3人</td> <td>中型3-5人</td> <td>大型>5人</td> </tr>'
						$.each(sumData,function(){
							str+='<tr><td>'+this.areaName+'</td><td>'+this.cliQty+'</td><td>'+this.social+'</td><td>'+this.personal+'</td><td>'+this.foundOther+'</td><td>'+this.ordinary+'</td><td>'+this.chinaDoctor+'</td>'+
								'<td>'+this.medicine+'</td><td>'+this.mouth+'</td><td>'+this.otherType+'</td><td>'+this.fiveStars+'</td><td>'+this.fourStars+'</td><td>'+this.thereStars+'</td><td>'+this.rateD+'</td>'+
								'<td>'+this.small+'</td><td>'+this.midsize+'</td><td>'+this.big+'</td></tr>'
						});
						$("#totalTable").empty().append(str);*/
						var cliQty=0;
						var social=0;
						var personal=0;
						var foundOther=0;
						var ordinary=0;
						var chinaDoctor=0;
						var medicine=0;
						var mouth=0;
						var otherType=0;
						var fiveStars=0;
						var fourStars=0;
						var thereStars=0;
						var rateD=0;
						var small=0;
						var midsize=0;
						var big=0;
						$.each(sumData,function(){
							cliQty += Number(this.cliQty);
							social += Number(this.social);
							personal += Number(this.personal);
							foundOther += Number(this.foundOther);
							ordinary += Number(this.ordinary);
							chinaDoctor += Number(this.chinaDoctor);
							medicine += Number(this.medicine);
							mouth += Number(this.mouth);
							otherType += Number(this.otherType);
							fiveStars += Number(this.fiveStars);
							fourStars += Number(this.fourStars);
							thereStars += Number(this.thereStars);
							rateD += Number(this.rateD);
							small += Number(this.small);
							midsize += Number(this.midsize);
							big += Number(this.big);
						});
						$('.cliQty').html(cliQty);
						$('.social').html(social);
						$('.personal').html(personal);
						$('.foundOther').html(foundOther);
						$('.ordinary').html(ordinary);
						$('.chinaDoctor').html(chinaDoctor);
						$('.medicine').html(medicine);
						$('.mouth').html(mouth);
						$('.otherType').html(otherType);
						$('.fiveStars').html(fiveStars);
						$('.fourStars').html(fourStars);
						$('.thereStars').html(thereStars);
						$('.rateD').html(rateD);
						$('.small').html(small);
						$('.midsize').html(midsize);
						$('.big').html(big);
					}else{
						$("#totalTable").html("数据暂无")
					}
				}
			})
		}
	}
	//诊所类型统计绑定click事件结束



	//下级区划概况信息开始
	$(".lowerLocation").bind("click",function(){
		$(".areaList").eq(2).css({display:'block'}).siblings(".areaList").css({display:"none"});
		lowerLocation();
	});
	function lowerLocation(){
		parentId = $('#areaChoose option:selected').attr('data-id');
		showAreaEtc();
		var nowProfileYear = $('.nowProfileYear').val();
		var nowProfileMonth = $('.nowProfileMonth').val();
		var lowerLevalBol=true;
		if(parentId!=""){
			//诊所数量统计接口
			var url=path.path+"/ClinicResources/Information";
			var params={};
			if($("#timeStyle").val()=="year"){
				var params={
					"year": nowProfileYear,
					"parentAreaCode":parentId,
					"areaFlag":"2"
				}
			}else{
				var params={
					"year": nowProfileYear,
					"month":nowProfileMonth,
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
					if(res.code=="000000"&&res.data){
						var infoData=res.data;
						//各下级行政区划个体诊所数量分布表开始
						if(lowerLevalBol==true){
							// var lowerLevelCompareDataName=['A镇','A镇','A镇','A镇','A镇','A镇','A镇','A镇','A镇','A镇','A镇','A镇'];
							// var lowerLevelCompareDataContent=[1000,160,600,600,600,1000,600,400,600,800,700,800];
							var lowerLevelCompareDataName=[];
							var lowerLevelCompareDataContent=[];
							for(var i=0; i<infoData.length; i++){
								lowerLevelCompareDataName.push(infoData[i].areaName);
								lowerLevelCompareDataContent.push(infoData[i].cliQty);
							}
							var lowerLevelCompareNode=document.getElementById('lowerLevelChart');
							var lowerLevelCompareTitleName='';
							var lowerLevelCompareRequireInfo='个体诊所数量';

							var lowerLevelCompareMaxNum=1200;
							echart.histogram(lowerLevelCompareNode,lowerLevelCompareTitleName,lowerLevelCompareRequireInfo,lowerLevelCompareDataName,lowerLevelCompareDataContent,lowerLevelCompareMaxNum,false);
							lowerLevalBol=false;
						}
						//各下级行政区划个体诊所数量分布表结束
					}else{
						$("#lowerLevelChart").html("数据暂无")
					}
				}
			})
		}
	}
	//下级区划概况信息结束



	// 信息总表开始
	$(".infoTotal").bind("click",function(){
		$(".areaList").eq(3).css({display:'block'}).siblings(".areaList").css({display:"none"});
		infoTotal();
	});
	function infoTotal(){
		parentId = $('#areaChoose option:selected').attr('data-id');
		showAreaEtc();
		var nowProfileYear = $('.nowProfileYear').val();
		var nowProfileMonth = $('.nowProfileMonth').val();
		if(parentId!=""){
			//诊所数量统计接口
			var url=path.path+"/ClinicResources/Information";
			var params={};
			if($("#timeStyle").val()=="year"){
				var params={
					"year": nowProfileYear,
					"parentAreaCode":parentId,
					"areaFlag":"2"
				}
			}else{
				var params={
					"year": nowProfileYear,
					"month":nowProfileMonth,
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
					if(res.code="000000"&&res.data){
						var sumData=res.data;
						var str='<tr class="tableHead"><td rowspan="2">行政区划</td><td rowspan="2">总数</td><td colspan="3">主办单位</td><td colspan="5">诊所类别</td> <td colspan="4">诊所级别</td> <td colspan="3">诊所规模</td> </tr> <tr class="tableHead"> <td>社会办</td> <td>个人办</td> <td>其他</td> <td>普通</td> <td>中医</td> <td>中西医结合</td> <td>口腔</td> <td>其他</td> <td>五星级</td> <td>四星级</td> <td>三星级</td> <td>未评级</td> <td>小型<3人</td> <td>中型3-5人</td> <td>大型>5人</td> </tr>';
						$.each(sumData,function(){
							str+='<tr><td>'+this.areaName+'</td><td>'+this.cliQty+'</td><td>'+this.social+'</td><td>'+this.personal+'</td><td>'+this.foundOther+'</td><td>'+this.ordinary+'</td><td>'+this.chinaDoctor+'</td>'+
								'<td>'+this.medicine+'</td><td>'+this.mouth+'</td><td>'+this.otherType+'</td><td>'+this.fiveStars+'</td><td>'+this.fourStars+'</td><td>'+this.thereStars+'</td><td>'+this.rateD+'</td>'+
								'<td>'+this.small+'</td><td>'+this.midsize+'</td><td>'+this.big+'</td></tr>'
						});
						$("#infoTable").empty().append(str);
					}else{
						$("#infoTable").html("数据暂无");
					}
				}
			})
		}
	}
	// 信息总表结束


});






