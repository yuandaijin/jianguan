var parentId = "";

//区域下拉框开始
common.dropDownList();
//区域下拉框结束

$(function() {
	// var lasMonths = new Date().getMonth()+'月';
	// $('.lastMonths').html(lasMonths);

	//个体诊所主办单位分类表开始
	var gradeNode = document.getElementById('grade');
	var gradeLegendData = ['一星级', '二星级', '三星级', '四星级', '五星级'];
	var gradeSeriesName = '个体诊所评级情况';
	var gradeSeriesData = [{
		value: 10,
		name: '一星级'
	}, {
		value: 25,
		name: '二星级'
	}, {
		value: 30,
		name: '三星级'
	}, {
		value: 30,
		name: '四星级'
	}, {
		value: 5,
		name: '五星级'
	}]
	echart.pinChart(gradeNode, gradeLegendData, gradeSeriesName, gradeSeriesData);
	//个体诊所主办单位分类表结束
	//初始化表格开始
	query();
	//初始化表格结束


	//区域确认开始
	$("#areaConfirm").click(function() {
		query();
	});
	function query(){
		parentId = $('#areaChoose option:selected').attr('data-id');
		$(".areaShow").html($("#areaChoose").val());

		if (parentId != "") {

			//诊所医疗能力覆盖情况接口
			var url = path.path + "/overview/clinicsQty";
			var params = {
				"parentAreaCode": parentId
			};
			$.ajax({
				type: "get",
				url: url,
				data: params,
				cache: false,
				async: true,
				success: function(res) {
					if (res.code == "000000"&&res.list) {
						var numData = res.list;
						//各下级行政区划诊所数量分布表开始
						var numDataName = [];
						var numDataContent = [];
						var allNum=0;
						for (var i = 0; i < numData.length; i++) {
							numDataName.push(numData[i].name);
							numDataContent.push(numData[i].qty);

							allNum+=parseInt(numData[i].qty);
						};
						var numNode = document.getElementById('numDistribute');
						var numTitleName = '';
						var numRequireInfo = '下级行政区划诊所数量';
						var numMaxNum = 100;

						$("#allNum").html(allNum);
						echart.histogram(numNode, numTitleName, numRequireInfo, numDataName, numDataContent, numMaxNum, false)
						//各下级行政区划诊所数量分布表结束
					}else{
						$("#allNum").html("");
						$("#numDistribute").html("数据暂无");
					}
				}
			})

			//诊所运营综合情况接口
			var url = path.path + "/overview/queryOperate";
			var params = {
				"parentAreaCode": parentId
			};
			$.ajax({
				type: "get",
				url: url,
				data: params,
				cache: false,
				async: true,
				success: function(res) {
					if(res.code=="000000"&&res.totallist&&res.efficiencylist){
						var totalData=res.totallist;
						var effiData=res.efficiencylist;
						//个体诊所医疗质量情况概览表开始
						var obj=document.getElementById("operate")
						var legendData=['门诊总量','服务效率'];
						var xData=[];
						var totalNum=[];
						var effiNum=[];
						var avgNumTotal=0;
						var avgNumEffi=0;


						for(var i=0; i<totalData.length; i++){
							xData.push(totalData[i].name);
							totalNum.push(totalData[i].qty);
							effiNum.push(effiData[i].qty);
							avgNumTotal+=parseInt(totalData[i].qty);
							avgNumEffi+=parseInt(effiData[i].qty);
						}
						avgNumTotal=avgNumTotal;
						avgNumEffi=parseInt(avgNumEffi/totalData.length*100)/100;
						$("#avgNum").html(avgNumTotal);
						$("#avgNumEffi").html(avgNumEffi);
						var objLeft={
							name:'门诊总量',
							data:totalNum
						}
						var objRight={
							name:'服务效率',
							data:effiNum
						}
						echart.histoAndLine(obj,legendData,xData,objLeft,objRight);
						//个体诊所医疗质量情况概览表结束
					}else{
						$("#avgNum").html("");
						$("#avgNumEffi").html("");
						$("#operate").html("数据暂无");
					}
				}
			})




			//双流县个体诊所医疗质量情况概览接口
			var urlTop=path.path+"/overview/OverviewClinicMedicalQualityIndex";
			var params={
				"parentAreaCode":parentId,
			};
			$.ajax({
				type :"get",
				url : urlTop,
				data :params,
				cache : false,
				async : true,
				success : function(res){
					console.log(res);
					if(res.code=="000000"&&res.data){
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
							}else if(infoData[i].type=="处方总量"){
								var sumNum=parseInt(infoData[i].qty);
							}else if(infoData[i].type=="抗生素处方数量"){
								var kangNum=parseInt(infoData[i].qty);
							}else if(infoData[i].type=="输液处方数量"){
								var shuNum=parseInt(infoData[i].qty);
							}
							if(kangNum!=null&&sumNum!=null&&sumNum!=0){
								$("#zhanBi").html(Math.round(kangNum/sumNum*100)+"%");
							}else{
								$("#zhanBi").html("0%")
							}
							if(shuNum!=null&&sumNum!=null&&sumNum!=0){
								$("#shuBi").html(Math.round(shuNum/sumNum*100)+"%");
							}else{
								$("#shuBi").html("0%")
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





			//居民多发疾病综合情况接口
			var url = path.path + "/overview/queryIncidence";
			var params = {
				"parentAreaCode": parentId
			};
			$.ajax({
				type: "get",
				url: url,
				data: params,
				cache: false,
				async: true,
				success: function(res) {
					if (res.code == "000000"&&res.list) {
						var topData = res.list;
						//个体诊所就诊疾病前十病种分布表开始
						var topDataName = [];
						var topDataContent = [];
						for (var i = 0; i < topData.length; i++) {
							topDataName.push(topData[i].name);
							topDataContent.push(danPercent(topData[i].qty,res.sum));
						}
						var topNode = document.getElementById('topTen');
						var topTitleName = '';
						var topRequireInfo = '个体诊所就诊疾病前十病种分布';
						var topMaxNum = 25;
						echart.histogram(topNode, topTitleName, topRequireInfo, topDataName, topDataContent, topMaxNum, false, true, true)
						//个体诊所就诊疾病前十病种分布表结束
					}else{
						$("#topTen").html("数据暂无");
					}
				}
			})

		}
	}
	//区域确认结束




})



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