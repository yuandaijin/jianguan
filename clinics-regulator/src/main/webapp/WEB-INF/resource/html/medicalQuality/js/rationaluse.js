var parentId = "";
var areaBol = true;

//设置年限开始
common.setYear();
common.setMonth();
common.setDay();
//设置年限结束


$(function() {
	//区域下拉框开始
	common.dropDownList();
	//区域下拉框结束
	useCondition();
	var totalNav = $(".totalNav");
	totalNav.mouseenter(function() {
		$(".subordinate").css({
			display: 'block'
		})
	});
	totalNav.mouseleave(function() {
		$(".subordinate").css({
			display: 'none'
		})
	});



	//区域确认开始
	$("#areaConfirm").bind("click", function() {
		$(".areaShow").html($("#areaChoose").val());
		parentId = $('#areaChoose option:selected').attr('data-id');
		var chooseTab = $('.chooseTrend li.clinicInfoTabBTn').attr('data-id');
		if(chooseTab == 0){
			changeChoose();
		}else if(chooseTab == 1){
			timeTrend();
		}

	});
	function changeChoose(){
		var chooseNum = 0;
		if($('.totalNav').hasClass('active')){
			if($('#useCondition').hasClass('active')){
				chooseNum = 0
			}else if($('#transfusion').hasClass('active')){
				chooseNum = 1
			}
		}else if($('#lowerLevel').hasClass('active')){
			chooseNum = 2
		}else if($('#infoTotal').hasClass('active')){
			chooseNum = 3
		}
		if(chooseNum == 0){
			useCondition()
		}else if(chooseNum == 1){
			transfusion();
		}else if(chooseNum == 2){
			lowerLevel();
		}else if(chooseNum == 3){
			infoTotal();
		}
	}
	function showTimeArea(){
		$(".areaShow").html($("#areaChoose").val());
		var chooseTab = $('.chooseTrend li.clinicInfoTabBTn').attr('data-id');
		if(chooseTab == 0){
			var report = $('.nowTimeChoose select').val();
			var year = '';
			var month = '';
			if(report == 'year'){
				year = $('.nowChooseYear').val();
				$(".showTime ").html(year+'年');
			}else if(report == 'month'){
				year = $('.nowMonthChooseYear').val();
				month = $('.nowMonthChooseMonth').val();
				$(".showTime").html(year+'年'+month+'月');
			}else{
				year = $('.nowDayChooseYear').val();
				month = $('.nowDayChooseMonth').val();
				day= $('.nowDayChooseDay').val();
				$(".showTime").html(year+'年'+month+'月'+day+'天');
			}
		}else if(chooseTab == 1){
			var report = $('.timeTrendChoose li.clinicInfoTabBTn').attr('data-id');
			var startYear = '';
			var endYear = '';
			var startMonth = '';
			var endMonth = '';
			if(report == 'year'){
				startYear = $('.trendYearChooseStart').val();
				endYear = $('.trendYearChooseEnd').val();
				startMonth = '1';
				endMonth = '12';
				$(".showTime").html(startYear+'年至'+endYear+'年');
			}else if(report == 'month'){
				startYear = $('.trendMonthChooseYear').val();
				endYear = startYear;
				startMonth = $('.trendMonthChooseBegin').val();
				endMonth = $('.trendMonthChooseEnd').val();
				$(".showTime").html(startYear+'年'+startMonth+'月至'+endYear+'年'+endMonth+'月');
			}
		}

	}
	$('.nowChooseYearByTime').on('click',function(){
		changeChoose();
	});
	$('.timeTrendChooseByTime').on('click',function(){
		timeTrend();
	});
	//区域确认结束
	//点击后的文字效果
	$("ul.chooseTrend li").click(function() {
		$(this).addClass("clinicInfoTabBTn").siblings().removeClass("clinicInfoTabBTn");
	});
	/************************************************/
	$("ul.area li").click(function() {
		$(this).addClass("active").siblings().removeClass("active");
	});
	totalNav.click(function() {
		$(this).addClass("active").siblings().removeClass("active");
	});
	$("#useCondition").click(function() {
		$(this).addClass("active").siblings().removeClass("active");
	});
	$("#transfusion").click(function() {
		$(this).addClass("active").siblings().removeClass("active");
	});
	$(".subordinate div").click(function() {
		$(this).addClass("active").siblings().removeClass("active");
	});
	/************************************************/
	//点击后的文字效果
	//当前状况与时间趋势的切换开始
	$("#nowCondition").click(function() {
		$("#condition").addClass("show").removeClass('hide');
		$("#trend").addClass("hide").removeClass("show");
		useCondition();
	});

	$("#timeTrend").click(function() {
		$("#condition").addClass("hide").removeClass("show");
		$("#trend").addClass("show").removeClass('hide');
		timeTrend()
	});
	function timeTrend(){
		showTimeArea();
		var report = $('.timeTrendChoose select').val();
		var year = '';
		var startYear = '';
		var endYear = '';
		var startMonth = '';
		var endMonth = '';
		var month='';
		var startDay='';
		var endDay='';
		if(report == 'year'){
			startYear = $('.trendYearChooseStart').val();
			endYear = $('.trendYearChooseEnd').val();
			$(".startTime").html(startYear+"年");
			$(".endTime").html(endYear+"年");
		}else if(report == 'month'){
			year = $('.trendMonthChooseYear').val();
			startMonth = $('.trendMonthChooseBegin').val();
			endMonth = $('.trendMonthChooseEnd').val();
			$(".startTime").html(year+"年"+startMonth+"月");
			$(".endTime").html(year+"年"+endMonth+"月");
		}else if(report == 'day'){
			year = $('.trendDayChooseYear').val();
			month= $('.trendDayChooseMonth').val();
			startDay = $('.trendDayChooseStartDay').val();
			endDay = $('.trendDayChooseEndDay').val();
			$(".startTime").html(year+"年"+month+"月"+startDay+"日");
			$(".endTime").html(year+"年"+month+"月"+endDay+"日");
		}
		parentId = $('#areaChoose option:selected').attr('data-id');
		if (parentId != "") {
			//抗生素使用占比变化情况接口
			var urlTop = path.path + "/medicalQuality/antibioticTransfusionUse";
			// var params = {
			// 	"year": year,
			// 	"parentAreaCode": parentId,
			// 	"reportType": report,
			// 	"startMonth": startMonth,
			// 	"endMonth": endMonth,
			// 	"startYear": startYear,
			// 	"endYear": endYear
			// };
			var params={};
			if(report=="year"){
				params = {
					"parentAreaCode": parentId,
					"reportType": report,
					"startYear": startYear,
					"endYear": endYear,
					"areaFlag": "2"
				};
			}else if(report=="month"){
				params = {
					"year": year,
					"parentAreaCode": parentId,
					"reportType": report,
					"startMonth": startMonth,
					"endMonth": endMonth,
					"areaFlag":"1"
				};
			}else if(report=="day"){
				params = {
					"year": year,
					"parentAreaCode": parentId,
					"reportType": report,
					"month":month,
					"startDay": startDay,
					"endDay": endDay,
					"areaFlag":"0"
				};
			}
			$.ajax({
				type: "get",
				url: urlTop,
				data: params,
				cache: false,
				async: true,
				success: function(res) {
					if (res.code == "000000"&&res.data.length) {
						var infoData=res.data;
						// 抗生素使用占比变化情况表开始
						var scaleObj = document.getElementById("topChart");
						var scaleLegendData = [''];
						var scaleXData = [];
						var scaleData = [{
							name: '抗生素占比',
							data: []
						}];
						for(var i=0; i<infoData.length; i++){
							scaleXData.push(infoData[i].name);
							scaleData[0].data.push(infoData[i].qty.split("%")[0])
						}
						var scaleContentData = echart.getMoreLineData(scaleData);
						echart.moreLine(scaleObj, scaleLegendData, scaleXData, scaleContentData,true,true);
						// 抗生素使用占比变化情况表结束
					}else{
						$("#topChart").html("数据暂无");
					}
				}
			})

			//抗生素使用占比变化情况接口
			var urlTop = path.path + "/medicalQuality/TransfusionUseCg";
			// var params = {
			// 	"year": year,
			// 	"parentAreaCode": parentId,
			// 	"reportType": report,
			// 	"startMonth": startMonth,
			// 	"endMonth": endMonth,
			// 	"startYear": startYear,
			// 	"endYear": endYear
			// };
			$.ajax({
				type: "get",
				url: urlTop,
				data: params,
				cache: false,
				async: true,
				success: function(res) {
					if (res.code == "000000"&&res.data.length) {
						var infoData=res.data;
						// 个体诊所A病种发病变化情况
						var scaleObj = document.getElementById("bottomChart");
						var scaleLegendData = [''];
						var scaleXData = [];
						var scaleData = [{
							name: '输液处方占比',
							data: []
						}]
						for(var i=0; i<infoData.length; i++){
							scaleXData.push(infoData[i].name);
							scaleData[0].data.push(infoData[i].qty.split("%")[0])
						}
						var scaleContentData = echart.getMoreLineData(scaleData);
						echart.moreLine(scaleObj, scaleLegendData, scaleXData, scaleContentData,true,true);
						// 个体诊所A病种发病变化情况
					}else{
						$("#bottomChart").html("数据暂无");
					}
				}
			})

		}
	}
	//当前状况与时间趋势的切换结束

	//时间趋势的时间切换开始
	$(".timeTrendChoose select").on("change", function() {
		if($(this).val() == 'year'){
			$('.yearY.timeTrendList').show().siblings('.timeTrendList').hide();
		}else if($(this).val() == 'month'){
			$('.monthY.timeTrendList').show().siblings('.timeTrendList').hide();
		}else{
			$('.dayY.timeTrendList').show().siblings('.timeTrendList').hide();
		}
	});
	//时间趋势的时间切换结束

	//当前概况的时间切换开始
	$(".nowTimeChoose select").on("change", function() {
		if($(this).val() == 'year'){
			$('.yearY.nowTimeList').show().siblings('.nowTimeList').hide();
		}else if($(this).val() == 'month'){
			$('.monthY.nowTimeList').show().siblings('.nowTimeList').hide();
		}else{
			$('.dayY.nowTimeList').show().siblings('.nowTimeList').hide();
		}
	});
	//当前概况的时间切换结束

	//抗生素使用情况开始
	$("#useCondition").bind("click", function() {
		$(".areaList").eq(0).removeClass("hide").addClass("show").siblings('.areaList').removeClass('show').addClass('hide');
		useCondition();
	});
	function useCondition(){
		showTimeArea();
		var report = $('.nowTimeChoose select').val();
		var year = '';
		var month = '';
		var day='';
		if(report == 'year'){
			year = $('.nowChooseYear').val();
			month = '8';
		}else if(report == 'month'){
			year = $('.nowMonthChooseYear').val();
			month = $('.nowMonthChooseMonth').val();
		}else if(report=="day"){
			year = $('.nowDayChooseYear').val();
			month = $('.nowDayChooseMonth').val();
			day=$('.nowDayChooseDay').val();
		}
		parentId = $('#areaChoose option:selected').attr('data-id');
		if (parentId != "") {
			//合理用药-抗生素使用情况分析接口
			var url = path.path + "/medicalQuality/antibioticUse";
			// var params = {
			// 	"year": year,
			// 	"reportType": report,
			// 	"month": month,
			// 	"parentAreaCode": parentId
			// };
			var params={};
			if(report == 'year'){
				params={
					"year": year,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"2"
				}
			}else if(report == 'month'){
				params={
					"year": year,
					"month": month,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"1"
				}
			}else if(report=="day"){
				params={
					"year": year,
					"month": month,
					"day": day,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"0"
				}
			}
			$.ajax({
				type: "get",
				url: url,
				data: params,
				cache: false,
				async: true,
				success: function(res) {
					if (res.code == "000000") {
						if(res.data.now&&res.data.now.length){
							var nowData = res.data.now;
							//个体诊所抗生素处方占比
							var relativeData = [{
								name: '其他处方',
								data: []
							}, {
								name: '抗生素处方',
								data: []
							}]
							relativeData[0].data.push(parseInt(nowData[0].qty) - parseInt(nowData[1].qty));
							relativeData[1].data.push(parseInt(nowData[1].qty));
							var relativeNode = document.getElementById('proportionOfPrescriptions');
							var relativeTitleName = '总计'+nowData[1].qty+'张';
							var relativeLegendData = ['其他处方', '抗生素处方'];
							var relativeXData = [];
							relativeXData.push(nowData[0].datas);
							var relativeYMax = 1200000;
							var relativeSeriesInfo = echart.integrationData(relativeData, true, true);
							echart.percentHistogram(relativeNode, relativeTitleName, relativeLegendData, relativeXData, relativeYMax, relativeSeriesInfo)
							//个体诊所人员数量环比总计表结束

							$("#chuZong").html(nowData[0].qty);
							$("#kangZong").html(nowData[1].qty);
							$("#kangBi").html(common.sinPercent(nowData[1].qty,nowData[0].qty));

						}else{
							$("#chuZong").html("");
							$("#kangZong").html("");
							$("#kangBi").html("");

							$("#proportionOfPrescriptions").html("暂无数据");
						}
						
						if(res.data.now&&res.data.now.length&&res.data.lastMonth&&res.data.lastMonth.length){
							var monthData = res.data.lastMonth;
							//个体诊所抗生素处方占比环比
							var topNode = document.getElementById('prescriptionRing');
							var topTitleName = '环比增长率：'+common.anPercent(danPercent(monthData[0].qty-monthData[1].qty, monthData[0].qty),danPercent(nowData[0].qty-nowData[1].qty, nowData[0].qty),true);
							var topRequireInfo = '个体诊所抗生素处方占比环比';
							var topDataName = [];
							topDataName.push(monthData[0].datas);
							topDataName.push(nowData[0].datas);
							var topDataContent = [];
							topDataContent.push(danPercent(monthData[0].qty-monthData[1].qty, monthData[0].qty));
							topDataContent.push(danPercent(nowData[0].qty-nowData[1].qty, nowData[0].qty));
							var topMaxNum = 100;
							echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
							//个体诊所抗生素处方占比环比

							$("#kangHuan").html(common.anPercent(danPercent(monthData[0].qty-monthData[1].qty, monthData[0].qty),danPercent(nowData[0].qty-nowData[1].qty, nowData[0].qty),true));

						}else{

							$("#kangHuan").html("");

							$("#prescriptionRing").html("暂无数据");
						}
						
						if(res.data.now&&res.data.now.length&&res.data.lastYear&&res.data.lastYear.length){
							var yearData = res.data.lastYear;
							//个体诊所抗生素处方占比同比
							var topNode = document.getElementById('prescriptionYear');
							var topTitleName = '同比增长率：'+common.anPercent(danPercent(yearData[0].qty-yearData[1].qty, yearData[0].qty),danPercent(nowData[0].qty-nowData[1].qty, nowData[0].qty));
							var topRequireInfo = '个体诊所抗生素处方占比同比';
							var topDataName = [];
							topDataName.push(yearData[0].datas);
							topDataName.push(nowData[0].datas);
							var topDataContent = [];
							topDataContent.push(danPercent(yearData[0].qty-yearData[1].qty, yearData[0].qty));
							topDataContent.push(danPercent(nowData[0].qty-nowData[1].qty, nowData[0].qty));
							var topMaxNum = 100;
							echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
							//个体诊所抗生素处方占比同比

							$("#kangTong").html(common.anPercent(danPercent(yearData[0].qty-yearData[1].qty, yearData[0].qty),danPercent(nowData[0].qty-nowData[1].qty, nowData[0].qty)));
						}else{

							$("#kangTong").html("");

							$("#prescriptionYear").html("暂无数据");

						}
						


					}else{
						$("#chuZong").html("");
						$("#kangZong").html("");
						$("#kangBi").html("");
						$("#kangHuan").html("");
						$("#kangTong").html("");

						$("#proportionOfPrescriptions").html("暂无数据");
						$("#prescriptionRing").html("暂无数据");
						$("#prescriptionYear").html("暂无数据");
					}
				}
			})
		}
	}
	//抗生素使用情况结束

	//输液情况开始
	$("#transfusion").bind("click", function() {
		$(".areaList").eq(1).removeClass("hide").addClass("show").siblings('.areaList').removeClass('show').addClass('hide');
		transfusion();
	});
	function transfusion(){
		showTimeArea();
		parentId = $('#areaChoose option:selected').attr('data-id');
		var report = $('.nowTimeChoose select').val();
		var year = '';
		var month = '';
		var day='';
		if(report == 'year'){
			year = $('.nowChooseYear').val();
			month = '8';
		}else if(report == 'month'){
			year = $('.nowMonthChooseYear').val();
			month = $('.nowMonthChooseMonth').val();
		}else if(report=="day"){
			year = $('.nowDayChooseYear').val();
			month = $('.nowDayChooseMonth').val();
			day=$('.nowDayChooseDay').val();
		}
		if (parentId != "") {
			//合理用药-抗生素使用情况分析接口
			var url = path.path + "/medicalQuality/transfusion";
			// var params = {
			// 	"year": year,
			// 	"reportType": report,
			// 	"month": month,
			// 	"parentAreaCode": parentId
			// };
			var params={};
			if(report == 'year'){
				params={
					"year": year,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"2"
				}
			}else if(report == 'month'){
				params={
					"year": year,
					"month": month,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"1"
				}
			}else if(report=="day"){
				params={
					"year": year,
					"month": month,
					"day": day,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"0"
				}
			}
			$.ajax({
				type: "get",
				url: url,
				data: params,
				cache: false,
				async: true,
				success: function(res) {
					if (res.code == "000000") {
						console.log(res.data.now);
						if(res.data.now.length){
							var nowData = res.data.now;
							//个体诊所抗生素处方占比
							var relativeData = [{
								name: '其他处方',
								data: []
							}, {
								name: '输液处方',
								data: []
							}];
							relativeData[0].data.push(nowData[1].qty - nowData[2].qty);
							relativeData[1].data.push(nowData[2].qty);
							var relativeNode = document.getElementById('proportionOfInfusion');
							var relativeTitleName = '总计'+nowData[2].qty;
							var relativeLegendData = ['其他处方', '输液处方'];
							var relativeXData = [];
							relativeXData.push(nowData[0].date);
							var relativeYMax = 1200000;
							var relativeSeriesInfo = echart.integrationData(relativeData, true, true);
							echart.percentHistogram(relativeNode, relativeTitleName, relativeLegendData, relativeXData, relativeYMax, relativeSeriesInfo)
							//个体诊所抗生素处方占比

							//处方总量输液占比
							var gradeNode = document.getElementById('infusion');
							var gradeLegendData = ['其他治疗方式', '输液'];
							var gradeSeriesName = '处方总量输液占比';
							var gradeSeriesData = [{
								// value: 71,
								name: '其他治疗方式'
							}, {
								// value: 29,
								name: '输液'
							}];
							gradeSeriesData[0].value = Math.abs(nowData[1].qty - nowData[2].qty);
							gradeSeriesData[1].value = Math.abs(nowData[2].qty)
							echart.pinChart(gradeNode, gradeLegendData, gradeSeriesName, gradeSeriesData);
							//处方总量输液占比

							//输液处方抗生素占比
							var gradeNode = document.getElementById('antibiotic');
							var gradeLegendData = ['抗生素输液', '其他输液'];
							var gradeSeriesName = '输液处方抗生素占比';
							var gradeSeriesData = [{
								// value: 38,
								name: '抗生素输液'
							}, {
								// value: 62,
								name: '其他输液'
							}];
							gradeSeriesData[0].value = Math.abs(nowData[0].qty);
							gradeSeriesData[1].value = Math.abs(nowData[2].qty - nowData[0].qty)
							echart.pinChart(gradeNode, gradeLegendData, gradeSeriesName, gradeSeriesData);
							//输液处方抗生素占比

							$("#allChu").html(nowData[1].qty);
							$("#shuZong").html(nowData[2].qty);
							$("#shuBi").html(common.sinPercent(nowData[2].qty,nowData[1].qty));

						}else{
							$("#proportionOfInfusion").html("数据暂无");
							$("#infusion").html("数据暂无");
							$("#antibiotic").html("数据暂无");

							$("#allChu").html("");
							$("#shuZong").html("");
							$("#shuBi").html("");
						}
						
						if(res.data.now.length&&res.data.lastMonth.length){
							var monthData = res.data.lastMonth;
							//个体诊所抗生素处方占比环比
							var topNode = document.getElementById('fluidInfusion');
							var topTitleName = '环比增长率：'+common.anPercent(common.numPercent(monthData[2].qty, monthData[1].qty),common.numPercent(nowData[2].qty, nowData[1].qty));
							var topRequireInfo = '个体诊所抗生素处方占比';
							var topDataName = [];
							topDataName.push(monthData[0].date);
							topDataName.push(nowData[0].date);
							var topDataContent = [];
							topDataContent.push(common.numPercent(monthData[2].qty, monthData[1].qty));
							topDataContent.push(common.numPercent(nowData[2].qty, nowData[1].qty));
							var topMaxNum = 100;
							echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
							//个体诊所抗生素处方占比环比
							$("#shuHuan").html(common.anPercent(common.numPercent(monthData[2].qty, monthData[1].qty),common.numPercent(nowData[2].qty, nowData[1].qty)));
						}else{
							$("#fluidInfusion").html("数据暂无");
							$("#shuHuan").html("");
						}
						
						if(res.data.now.length&&res.data.lastYear.length){
							var yearData = res.data.lastYear;
							//个体诊所抗生素处方占比同比
							var topNode = document.getElementById('infusionYear');
							var topTitleName = '同比增长率：'+common.anPercent(common.numPercent(yearData[2].qty, monthData[1].qty),common.numPercent(nowData[2].qty, nowData[1].qty));
							var topRequireInfo = '个体诊所抗生素处方占比';
							var topDataName = [];
							topDataName.push(yearData[0].date);
							topDataName.push(nowData[0].date);
							var topDataContent = [];
							topDataContent.push(common.numPercent(yearData[2].qty, monthData[1].qty));
							topDataContent.push(common.numPercent(nowData[2].qty, nowData[1].qty));
							var topMaxNum = 100;
							echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
							//个体诊所抗生素处方占比同比

							//table填充开始
							$("#shuTong").html(common.anPercent(common.numPercent(yearData[2].qty, monthData[1].qty),common.numPercent(nowData[2].qty, nowData[1].qty)));
							//table填充结束
						}else{
							$("#infusionYear").html("数据暂无");

							$("#shuTong").html("");
						}
							

					}else{
						$("#allChu").html("");
						$("#shuZong").html("");
						$("#shuBi").html("");
						$("#shuHuan").html("");
						$("#shuTong").html("");

						$("#proportionOfInfusion").html("数据暂无");
						$("#fluidInfusion").html("数据暂无");
						$("#infusionYear").html("数据暂无");
						$("#infusion").html("数据暂无");
						$("#antibiotic").html("数据暂无");
					}
				}
			})
		}
	}
	//输液情况结束

	//下级区划概况开始
	$("#lowerLevel").bind("click", function() {
		$(".areaList").eq(2).removeClass("hide").addClass("show").siblings('.areaList').removeClass('show').addClass('hide');
		lowerLevel()
	});
	function lowerLevel(){
		showTimeArea();
		parentId = $('#areaChoose option:selected').attr('data-id');
		var report = $('.nowTimeChoose select').val();
		var year = '';
		var month = '';
		var day='';
		if(report == 'year'){
			year = $('.nowChooseYear').val();
			month = '8';
		}else if(report == 'month'){
			year = $('.nowMonthChooseYear').val();
			month = $('.nowMonthChooseMonth').val();
		}else if(report=="day"){
			year = $('.nowDayChooseYear').val();
			month = $('.nowDayChooseMonth').val();
			day=$('.nowDayChooseDay').val();
		}
		if (parentId != "") {
			//诊所数量统计接口
			var urlTop = path.path + "/medicalQuality/roportionAntibioticUse";
			// var params = {
			// 	"year": year,
			// 	"reportType": report,
			// 	"month": month,
			// 	"parentAreaCode": parentId
			// };
			var params={};
			if(report == 'year'){
				params={
					"year": year,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"2"
				}
			}else if(report == 'month'){
				params={
					"year": year,
					"month": month,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"1"
				}
			}else if(report=="day"){
				params={
					"year": year,
					"month": month,
					"day": day,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"0"
				}
			}
			$.ajax({
				type: "get",
				url: urlTop,
				data: params,
				cache: false,
				async: true,
				success: function(res) {
					if (res.code == "000000"&&res.data.length) {
						var infoData = res.data;
						//各下级行政区划个体诊抗生素占比合格情况
						var topDataName = [];
						var topDataContent = [];
						for (var i = 0; i < infoData.length; i++) {
							topDataName.push(infoData[i].name);
							topDataContent.push(infoData[i].qty.split("%")[0])
						}
						var topNode = document.getElementById('electronicMedicalRecord');
						var topTitleName = '';
						var topRequireInfo = '各下级行政区划个体诊抗生素占比合格情况';
						var topMaxNum = 25;
						echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
						//各下级行政区划个体诊抗生素占比合格情况

					}else{
						$("#electronicMedicalRecord").html("数据暂无");
					}
				}
			});

			var urlBottom = path.path + "/medicalQuality/transfusionPrescriptions ";
			$.ajax({
				type: "get",
				url: urlBottom,
				data: params,
				cache: false,
				async: true,
				success: function(res) {
					if (res.code == "000000"&&res.data.length) {
						var infoData = res.data;
						//各下级行政区划个体诊输液占比合格情况
						var topDataName = [];
						var topDataContent = [];
						for (var i = 0; i < infoData.length; i++) {
							topDataName.push(infoData[i].name);
							topDataContent.push(infoData[i].qty.split("%")[0])
						}
						var topNode = document.getElementById('electronicPrescription');
						var topTitleName = '';
						var topRequireInfo = '各下级行政区划个体诊输液占比合格情况';
						var topMaxNum = 25;
						echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
						//各下级行政区划个体诊输液占比合格情况
					}else{
						$("#electronicPrescription").html("数据暂无")
					}
				}
			})

		}
	}
	//下级区划概况结束

	//下级区划概况开始
	$("#infoTotal").bind("click", function() {
		$(".areaList").eq(3).removeClass("hide").addClass("show").siblings('.areaList').removeClass('show').addClass('hide');
		infoTotal();
	});
	function infoTotal(){
		showTimeArea();
		parentId = $('#areaChoose option:selected').attr('data-id');
		var report = $('.nowTimeChoose select').val();
		var year = '';
		var month = '';
		var day='';
		if(report == 'year'){
			year = $('.nowChooseYear').val();
			month = '8';
		}else if(report == 'month'){
			year = $('.nowMonthChooseYear').val();
			month = $('.nowMonthChooseMonth').val();
		}else if(report=="day"){
			year = $('.nowDayChooseYear').val();
			month = $('.nowDayChooseMonth').val();
			day=$('.nowDayChooseDay').val();
		}
		if (parentId != "") {
			//诊所数量统计接口
			var url = path.path + "/medicalQuality/RationalForm";
			// var params = {
			// 	"year": year,
			// 	"reportType": report,
			// 	"month": month,
			// 	"parentAreaCode": parentId
			// };
			var params={};
			if(report == 'year'){
				params={
					"year": year,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"2"
				}
			}else if(report == 'month'){
				params={
					"year": year,
					"month": month,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"1"
				}
			}else if(report=="day"){
				params={
					"year": year,
					"month": month,
					"day": day,
					"reportType": report,
					"parentAreaCode": parentId,
					"areaFlag":"0"
				}
			}
			$.ajax({
				type: "get",
				url: url,
				data: params,
				cache: false,
				async: true,
				success: function(res) {
					if (res.code == "000000"&&res.data.length) {
						var infoData = res.data;
						var dataLength = infoData.length / 4;
						var str='';
						for (var i = 0; i < dataLength; i++) {
							str +='<tr><td>'+infoData[i*4].name+'</td>' +
								'<td>'+infoData[i*4+3].qty+'</td>' +  //处方总量
								'<td>'+infoData[i*4+2].qty+'</td>' +  //抗生素处方
								'<td>'+common.sinPercent(infoData[i*4+2].qty, infoData[i*4 + 3].qty)+'</td>' + //抗生素站总处方比例
								'<td>'+infoData[i*4].qty+'</td>' + //输液处方(张)
								'<td>'+common.sinPercent(infoData[i*4].qty, infoData[i*4 + 3].qty)+'</td>' + //输液站总处方比例
								'<td>'+infoData[i*4 + 1].qty+'</td>' + //抗生素输液处方数
								'<td>'+common.sinPercent(infoData[i*4 + 1].qty, infoData[i*4 + 3].qty)+'</td></tr>'; //抗生素占输液数比例
						}
						$("#totalTable").empty().append(str);

					}else{
						$("#totalTable").html("")
					}
				}
			})
		}
	}
	//下级区划概况结束


});




//计算前一个数相对后一个数的百分比开始
function danPercent(num1,num2){
	var num3=Number(num1);
	var num4=Number(num2);
	if(num4==0){
		return "-"
	}else{
		return (((num3/num4)*10000)/100).toFixed(1);
	}

}
//计算前一个数相对后一个数的百分比开始