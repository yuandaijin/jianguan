/****************点击文字特效******************/
$("ul.clinicInfoTab li").click(function() {
	$(this).addClass("clinicInfoTabBTn").siblings().removeClass("clinicInfoTabBTn");
});
$(function(){
    $("ul.area li").click(function(){
        $(this).addClass("selected").siblings().removeClass("selected");
    });
});
/****************点击文字特效***************/
//个体诊所总体服务满意度得分情况
var gradeNode = document.getElementById('status');
var gradeLegendData = ['一分', '二分', '三分', '四分', '五分'];
var gradeSeriesName = '个体诊所总体服务满意度得分情况';
var gradeSeriesData = [{
	value: 10,
	name: '一分'
}, {
	value: 25,
	name: '二分'
}, {
	value: 30,
	name: '三分'
}, {
	value: 30,
	name: '四分'
}, {
	value: 5,
	name: '五分'
}]
echart.pinChart(gradeNode, gradeLegendData, gradeSeriesName, gradeSeriesData);
//个体诊所总体服务满意度得分情况

var myChart = echarts.init(document.getElementById("passChart"));
option = {
	tooltip: {},
	radar: {
		// shape: 'circle',
		indicator: [{
			name: '诊所环境设施',
			max: 5
		}, {
			name: '诊所收费合理性',
			max: 5
		}, {
			name: '诊所疗效服务效果',
			max: 5
		}, {
			name: '诊所服务效率',
			max: 5
		}, {
			name: '医务人员读物态度',
			max: 5
		}]
	},
	series: [{
		name: '预算',
		type: 'radar',
		// areaStyle: {normal: {}},
		data: [{
			value: [2.1, 2.2, 2.5, 3.1, 4.4],
			name: '个体诊所服务满意度各维度平均得分'
		}]
	}]
};
myChart.setOption(option);

// 各下级行政区划个体诊所服务效率综合概况表开始
var serviceObj = document.getElementById("serviceChart");
var serviceLegendData = ['各下级区划个体诊所服务效率']
var serviceXData = ["a", "b", "c", "d"];
var contentData = {
	name: '各下级区划个体诊所服务效率',
	data: [12, 3, 5, 90]
}
echart.avgLine(serviceObj, serviceLegendData, serviceXData, contentData);
// 各下级行政区划个体诊所服务效率综合概况表结束

$(function() {

	//当前概况的时间切换开始
	$(".nowTimeChoose>li").bind("click", function() {
			$(".nowTimeList").eq($(this).index()).removeClass("hide").addClass("show").siblings('.nowTimeList').removeClass('show').addClass('hide');
		})
		//当前概况的时间切换结束

	//子菜单切换开始
	$(".area>li").bind("click", function() {
		$(".areaList").eq($(this).index()).removeClass("hide").addClass("show").siblings('.areaList').removeClass('show').addClass('hide');
	})

	//子菜单切换结束

})
	//匹配区域
	var parentId="";

	//区域下拉框开始
	common.dropDownList();
