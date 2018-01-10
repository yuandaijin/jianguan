



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
	showAreaEtc();

	$("#searchInfo").bind("click",function(){
		query();
	})

    //区域确认开始
    $("#areaConfirm").bind("click",function(){
		query();
    });

	function query(){
		showAreaEtc();
		$(".areaShow").html($("#areaChoose").val());
		parentId = $('#areaChoose option:selected').attr('data-id');

		var yearControl=$(".yearSelected").val();
		var monthControl=$(".monthSelected").val();
		var dayControl=$(".daySelected").val();
		if(parentId!=""){
			//6个月内到期诊所数量接口
			var urlNum=path.path+"/QualitySupervision/expire";
			var params={
				"year": yearControl,
				"parentAreaCode":parentId,
				"month":monthControl
			};
			var params={};
			if($("#timeStyle").val()=="month"){
				params={
					"parentAreaCode":parentId,
					"year":yearControl,
					"month":monthControl,
					"areaFlag":"1"
				}
			}else if($("#timeStyle").val()=="day"){
				params={
					"parentAreaCode":parentId,
					"year":yearControl,
					"month":monthControl,
					"day":dayControl,
					"areaFlag":"0"
				}
			}else{
				params={
					"parentAreaCode":parentId,
					"year":yearControl,
					"areaFlag":"2"
				}
			}
			$.ajax({
				type :"get",
				url : urlNum,
				data :params,
				cache : false,
				async : true,
				success : function(res){
					if(res.code=="000000"&&res.data){
						$("#clinicsNum").html(res.data.qty);
					}else{
						$("#clinicsNum").html("");
					}
				}
			})

			//6个月内到期诊所名单接口
			var urlList=path.path+"/QualitySupervision/listTimeClinics";
			$.ajax({
				type :"get",
				url : urlList,
				data :params,
				cache : false,
				async : true,
				success : function(res){
					if(res.code=="000000"&&res.data){
						var infoData=res.data;
						$("#infoTable").html("");
						for(var i=0; i<infoData.length; i++){
							var trNode=$("<tr></tr>");	
							trNode.append("<td>"+infoData[i].area+"</td>");
							trNode.append("<td>"+infoData[i].name+"</td>");
							trNode.append("<td>"+infoData[i].createTime+"</td>");
							trNode.append("<td>"+infoData[i].out_time+"</td>");
							trNode.append("<td>"+infoData[i].legalPerson+"</td>");
							trNode.append("<td>"+infoData[i].Telephone+"</td>");
							trNode.append("<td>"+infoData[i].address+"</td>");
							trNode.append("<td>"+infoData[i].notice+"</td>");
							$("#infoTable").append(trNode);
						}
						
					}
				}
			})

		}
	}
    //区域确认结束


    


})