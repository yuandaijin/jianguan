<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.huatuo.clinics.cms.comm.ConfigProperites"%>
<!DOCTYPE html>
<%String path = request.getContextPath(); 
  String registerurl =ConfigProperites.getRegisterurl();
%>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <title>华佗在线后台管理系统</title>
    <!--[if lt IE 9]>
        <script src="<%=path %>/resource/js/plugins/html5shiv.js"></script>
    <![endif]-->
    <!--[if lt IE 9]>
    <script>
        alert('管理系统已不支持IE6-8，请使用谷歌、火狐等浏览器\n或360、QQ等国产浏览器的极速模式浏览本页面！');
    </script>
    <![endif]--> 
    <link href="<%=path %>/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/style.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/function.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/employeeList.css" rel="stylesheet">
	<style>
	.navbar-forms{
	padding-top:20px;
	padding-left:5px;
	width:23%;
	}
	tr.active>td{
	background-color:#DDDDDD !important;
	}
	.outerIn{
	border: 1px solid #2081c7;
	padding: 5px;
	overflow: hidden;
	margin: 5px;
	height: 98%;
	}
	.interLeft {
	border: 1px solid #2081c7;
	padding: 5px;
	margin: 5px;
	height: 98%;
	width: 20%;
	float: left;
	}
	.interRight{
	border: 1px solid #2081c7;
	padding: 5px;
	margin: 5px;
	width: 78%;
	height: 98%;
	overflow: auto;
	float: left;
	}
	.rightTopOuter{
	border: 1px solid #2081c7;
	padding: 5px;
	margin: 5px;
	width: 99%;
	height: 98%;
	}
	.text-center{
	text-align: center;
	}
	.font-weight{
	font-weight: bold;
	}
	.leftTable,.leftTableR{
	width: 100%;
	border: 1px solid #2081c7;
	text-align: center;
	}
	.leftTable tr,.leftTableR tr{
	line-height: 45px;
	border-top: 1px solid #2081c7;
	}
	.leftTable tbody tr:hover{
	cursor: pointer;
	background-color: #dfedfe;
	}
	.dis-inline{
	display: inline-block;
	}
	.formTopInput.regMemo{
	width: 75%;
	resize: none;
	}
	.margin-top{
	margin-top: 20px;
	}
	.rightTopOuter>.dis-inline.a{
	margin-right: 5%;
	}
	.middleTextArea{
	width: 95%;
	height: 100px;
	margin: 20px auto;
	}
	.middleTextArea textarea{
	resize: none;
	width: 100%;
	height: 100%;
	}
	.table-striped.s-table.leftTable td{
	height:45px;
	}
	</style>
</head>
<body>
	<div class="outerIn">
	<div class="interLeft">
	<nav class="g-hd navbar navbar-default">
	<div class="btn-group bet-left navbar-forms">
	<button type="button" class="btn btn-info add"  id="">新增</button>
	</div>
	<div class="btn-group bet-left navbar-forms">
	<button type="button" class="btn btn-info save" disabled="disabled" id="">保存</button>
	</div>
	<div class="btn-group bet-left navbar-forms">
	<button type="button" class="btn btn-info delete" disabled="disabled" id="">删除</button>
	</div>
	<div class="btn-group bet-left navbar-forms">
	<button type="button" class="btn btn-info update" disabled="disabled" id="">编辑</button>
	</div>
	</nav>
	<div class="table-responsive">
	<div class="text-center font-weight" style="font-size: 16px">
	报表列表
	</div>
	<table class="table table-striped s-table leftTable">
	<thead>
	<tr class="text-center">
	<th class="text-center">报表号</th>
	<th class="text-center">报表名</th>
	</tr>
	</thead>
	<tbody>
	</tbody>
	</table>
	</div>
	</div>
	<div class="interRight">
	<div class="g-bd">


	<form method="post" id="addOneReport"  enctype="multipart/form-data">
	<div class="rightTopOuter">
	<input type="hidden" value="" name='reportId' id="reportId"/>
	<div style="font-size: 18px;padding: 10px 0 0 25px">概述：</div>
	<div class="dis-inline">
	<div>
	<span style="color:red;">&emsp;&emsp;*</span><label for="name">类别：</label>
	<div class="dis-inline">
	<select class="form-control" style="width:140px;" id="reportType" name="provinceCode" disabled="disabled">
	<option value="挂号收据" selected="selected">挂号收据</option>
	<option value="处方笺">处方笺</option>
	<option value="收费票据">收费票据</option>
	</select>
	<em class="errortips"></em>
	</div>
	</div>
	</div>
	<div class="dis-inline">
	<div>
	<span style="color:red;">&emsp;*</span><label for="name">样式：</label>
	<div class="dis-inline">
	<select class="form-control" style="width:140px;" id="reportStyle" name="provinceCode" disabled="disabled">
	<option value="套打" selected="selected">套打</option>
	<option value="非套打">非套打</option>
	</select>
	<em class="errortips"></em>
	</div>
	</div>
	</div>
	<div class="dis-inline">
	<span style="color:red;">&emsp;*</span><label for="name">长：</label>
	<div class="dis-inline">
	<input type="text" class="form-control" id="length" name="length" value="" disabled="disabled" style="width:50px;"/>
	<em class="errortips"></em>
	</div>
	</div>
	<div class="dis-inline">
	<span style="color:red;">&emsp;*</span><label for="name">宽：</label>
	<div class="dis-inline">
	<input type="text" class="form-control" id="wide" name="wide" value="" disabled="disabled" style="width:50px;"/>
	<em class="errortips"></em>
	</div>
	</div>
	<div class="dis-inline">
	<span style="color:red;">&emsp;*</span><label for="name">分页大小：</label>
	<div class="dis-inline">
	<input type="text" class="form-control" id="pageSize" name="pageSize" value="" disabled="disabled" style="width:50px;"/>
	<em class="errortips"></em>
	</div>
	</div>
	<div class="form-inline margin-top">
	<div>
	<span style="color:red;">&emsp;&emsp;*</span><label for="name">报表号：</label>
	<textarea class="form-control formTopInput regMemo" id="reportNo" name="reportNo" disabled="disabled" style="height: 35px"></textarea>
	</div>
	<div style="margin-top:10px">
	<span style="color:red;">&emsp;&emsp;*</span><label for="name" style="word-spacing: 10px;">名 称：</label>
	<textarea class="form-control formTopInput regMemo" id="reportName" name="reportName" disabled="disabled" style="height: 35px"></textarea>
	<em class="errortips"></em>
	</div>
	</div>
	<div>
	<div class="form-inline" style="width:65.5%;display:inline-block">
	<div>
	<span style="color:red;">&emsp;&emsp;*</span><label for="name" style="word-spacing:10px">描 述：</label>
	<textarea class="form-control formTopInput regMemo" id="reportMemos" name="reportMemos" disabled="disabled"></textarea>
	<em class="errortips"></em>
	</div>
	</div>
	</div>

	</div>
	<div class="rightTopOuter">
	<div class="dis-inline a">
	<span style="color:red;">&emsp;&emsp;*</span><span>预览：html嵌入</span>
	</div>
	<div class="dis-inline a">

		<form method="POST" id="uploadForm" enctype="multipart/form-data" action="<%=registerurl %>/baseReport/uploadHTML">
			<label>导入html:</label><input id="upload" type="file" id="uploadfile" name="file" />
		</form>
	</div>
	<div class="dis-inline a">
	<input id="out-button" type="button" value="导出html" disabled="disabled">
	</div>
	<div class="dis-inline a">
	<input id="see-button" type="button" value="预览pdf" disabled="disabled">
	</div>
	<div class="middleTextArea margin-top">
	<textarea name="html" value="" id="html" disabled="disabled"></textarea>
	<em class="errortips"></em>
	</div>
	</div>
	<div class="rightTopOuter" style="overflow: hidden">
	<div class="interLeft" style="width: 58%">
	<div class="form-group">
	<span style="color:red;">&emsp;&emsp;</span><label for="local-selector">数据：</label>
	<div class="form-group">
	<input type="hidden" id="regId"/>
	<select class="form-control formTopInput" id="regName" name="regName" value="" disabled="disabled">
	</select>
	<em class="errortips"></em>
	</div>
	</div>
	<div class="form-inline">
		<div class="table-responsive">
			<table class="table table-striped d-table">
				<thead>
					<tr>
						<th>编号</th>
						<th>参数名</th>
						<th>类型</th>
						<th>值</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<div class="form-inline">
		<div>
			<span style="color:red;">&emsp;&emsp;*</span><label for="name">服务接口：</label>
			<input type="text" id="regUrl" name="regName" disabled="disabled"/>
		</div>
	</div>
	</div>
	<div class="interRight" style="width: 38%">
	<div class="form-inline" style="margin-bottom: 0">
	<div class="form-group" style="margin-right: 0">
	<span style="color:red;">&emsp;&emsp;*</span><label for="name">返回参数：</label>
	</div>
	</div>
	<div class="form-inline">
	<div class="form-group" style="width:100%;margin-right:0">
	<div class="form-group" style="padding: 0;width:100%">
	<textarea style="width:100%" rows="5" name="content" id="content" value="" disabled="disabled"></textarea>
	<em class="errortips"></em>
	</div>
	</div>
	</div>
	</div>


	<span ><i class="Hui-iconfont Hui-iconfont-add" style="display:none;"></i></span>
	<span><i class="Hui-iconfont Hui-iconfont-jianhao" style="display:none;"></i></span>
	</div>
	</form>
	</div>
	</div>
	</div>
 <script src="<%=path %>/resource/js/plugins/jquery-1.11.1.min.js"></script>
 <script src="<%=path %>/resource/js/plugins/layer/layer.js"></script>
 <script src="<%=path %>/resource/js/plugins/validate/jquery.validate.min.js"></script>
 <script src="<%=path %>/resource/js/plugins/validate/card.js"></script>
 <script type="text/javascript">
 	$(function(){
 		getAllReportData();
 		function getAllReportData(){
 			var url = "<%=registerurl %>/baseReport/listOnWork";
 			$.ajax({
 				type:"get",
 				url:url,
 				dataType:'json',
 				async: false,
 				success:function(data){
 					var len=data.dataMsg.length;
 					for(var i=0;i<len;i++){
 						$(".s-table tbody").append("<tr id="+data.dataMsg[i].id+"><td class='reportNo'>"+data.dataMsg[i].reportNo+"</td><td>"+data.dataMsg[i].reportName+"</td></tr>");
 					}
 				}
 			});
 		};
 		
 		//回显
 		$(".leftTable tbody tr").dblclick(function(){
 				 getById($(this).find(".reportNo").html());//
 				 $(".update,.save,.delete").attr('disabled',false);
 				getAllReg();
 		});
 		
 		function getById(reportNo){
 			var url = "<%=registerurl %>/baseReport/getByReportNo";
 			$.ajax({
 				type:"get",
 				url:url,
 				data: {"reportNo" :reportNo},
 				dataType:'json',
 				async: false,
 				success:function(data){
 					$("#reportId").val(data.dataMsg.id);
 					$("#reportType").val(data.dataMsg.reportType);
 					$("#reportStyle").val(data.dataMsg.reportStyle);
 					$("#length").val(data.dataMsg.length);
 					$("#wide").val(data.dataMsg.wide);
 					$("#pageSize").val(data.dataMsg.pageSize);
 					$("#reportNo").val(data.dataMsg.reportNo);
 					$("#reportName").val(data.dataMsg.reportName);
 					$("#reportMemos").val(data.dataMsg.reportMemos);
 					$("#regId").val(data.dataMsg.regId);
 					$("#html").val(data.dataMsg.html);
 					if(data.dataMsg.cmPerInterface!=null){
	 					$("#regUrl").val(data.dataMsg.cmPerInterface.url);
	 					$("#content").val(data.dataMsg.cmPerInterface.content);
						var str = '';
						str = "<option id='"+data.dataMsg.cmPerInterface.id+"' value='"+data.dataMsg.cmPerInterface.name+"' selected='selected'>"+data.dataMsg.cmPerInterface.name+"</option>";
						$("#regName").empty().append(str);
	 					$("#regName").val(data.dataMsg.cmPerInterface.name);
	 					var list;
	 					list = data.dataMsg.cmPerInterface.dtls;//接口参数
	 					str = '';
						for(var j=0;j<list.length;j++){
							var id=list[j].id;
							var paramNo=j+1;
							var paramName=list[j].paramName;
							var paramType=list[j].paramType;
							var paramValue=list[j].paramValue;
							str+="<tr id="+id+"><td><input type='text' class='par-table' id='paramNo'disabled='disabled' value="+paramNo+"></td><td><input  type='text' class='par-table' id='paramName' disabled='disabled' value="+paramName+"></td><td ><input type='text' class='par-table' id='paramType' disabled='disabled' value="+paramType+"></td><td><input disabled='disabled' class='par-table' type='text' id='paramValue' value="+paramValue+" ></td></tr>";
						}
						$(".d-table tbody").empty().append(str);
 					}
 				}
 			});
 		}
 		
 		//编辑
 		$(".update").click(function(){
			 $(".form-control,.in-table").attr('disabled',false);
			 $(".Hui-iconfont").show();
			 getAllReg();
	 	});
 		//删除
 		$(".delete").click(function(){
			layer.msg('确定删除？', {
				time: 0,
				btn: ['确定', '取消'],
				yes: function(index){
					var id = $("#reportId").val();
					deleteById(id);
					newBegin();
					$(".leftTable tbody tr").dblclick(function(){
						getById($(this).find(".reportNo").html());//
						$(".update,.save,.delete").attr('disabled',false);
					});
				 $(".form-control,.in-table").attr('disabled',true);
				 $(".Hui-iconfont").hide();
				}
			});

	 	});
		//新增
		$(".add").click(function(){
			$(".form-control,.in-table").attr('disabled',false);
			$(".Hui-iconfont").show();
			$(".save").attr('disabled',false);
			newBegin();
			getAllReg();
			$(".leftTable tbody tr").dblclick(function(){
				 getById($(this).find(".reportNo").html());//
				 $(".update,.save,.delete").attr('disabled',false);
				$(".form-control,.in-table").attr('disabled',true);
				$(".Hui-iconfont").hide();
			});
		});
		//保存
		$(".save").click(function(){
 			var reportId = $("#reportId").val();
			var reportType = $('#reportType').val();
			var reportStyle = $('#reportStyle').val();
			var length = $('#length').val();
			var wide = $('#wide').val();
			var pageSize = $('#pageSize').val();
			var reportNo = $('#reportNo').val();
			var reportName = $('#reportName').val();
			var reportMemos = $('#reportMemos').val();
			var html = $('#html').val();
			var regId = $('#regId').val();

			<%--if(reportNo == '')return;--%>
			<%--if(reportName == '')return;--%>
			<%--if(reportMemos == '')return;--%>
			var data = {
				id:reportId,
				reportType:reportType,
				reportStyle:reportStyle,
				length:length,
				wide:wide,
				pageSize:pageSize,
				reportNo:reportNo,
				reportName:reportName,
				reportMemos:reportMemos,
				html:html,
				regId:regId
			}
			var url = "<%=registerurl %>/baseReport/saveReport";
			$.ajax({
			type:"post",
			url:url,
			data: JSON.stringify(data),
			dataType:'json',
			contentType: "application/json; charset=utf-8",
			async: false,
			cache : false,
			success:function(data){
				layer.alert(data.stateString);
				newBegin();
				$(".leftTable tbody tr").dblclick(function(){
					 getById($(this).find(".reportNo").html());//
					 $(".update,.save,.delete").attr('disabled',false);
					 $(".form-control,.in-table").attr('disabled',true);
						$(".Hui-iconfont").hide();
				});
				}
			});
		});
			
		function newBegin(){
             $('.s-table tbody').empty();
             $('.interRight input').attr('disabled',false).val('');
             $('.interRight select').attr('disabled',false).val('');
             $('.interRight textarea').attr('disabled',false).val('');
             $("#in-button").val("导入html");
             $("#out-button").val("导出html");
             $("#see-button").val("预览pdf");
             getAllReportData();
         }
		function deleteById(id){
			var url = "<%=registerurl %>/baseReport/deleteById";
 			$.ajax({
 				type:"get",
 				url:url,
 				data: {"id" :id},
 				dataType:'json',
 				async: false,
 				success:function(data){
					layer.alert(data.stateString);
					newBegin();
 				}
 			});
		}
		
		//获取接口列表
		$("#regName").click(function(){
			getAllReg();
		});
		function getAllReg(){
			var url = "<%=registerurl %>/reportData/listOnWork";
 			$.ajax({
 				type:"get",
 				url:url,
 				dataType:'json',
 				async: false,
 				success:function(data){
 					var list = data.dataMsg;
 					var str = '';
 					var regId = $("#regName").children('option:selected').attr("id");
 					if(regId!=null&&regId!=''&&regId!=undefined){
	 					for(var i=0;i<list.length;i++){
	 						var id=list[i].id;
	 						var paramName=list[i].name;
	 						if(regId==id){
		 						str += "<option id='"+id+"' value='"+paramName+"' selected='selected'>"+paramName+"</option>"
	 						}else{
		 						str += "<option id='"+id+"' value='"+paramName+"'>"+paramName+"</option>"
	 						}
	 					}
	 					$("#regName").empty().append(str);
 					}else{
	 					for(var i=0;i<list.length;i++){
	 						var id=list[i].id;
	 						var paramName=list[i].name;
	 						str += "<option id='"+id+"' value='"+paramName+"'>"+paramName+"</option>"
	 					}
	 					$("#regName").empty().append(str);
 					}
 				}
 			});
		}
		
		
		//改变接口获得接口内容
		$("#regName").change(function(){
			var regId = $(this).children('option:selected').attr("id");
			getRegById(regId);
		});
		
		function getRegById(regId){
			var url = "<%=registerurl %>/reportData/getById";
 			$.ajax({
 				type:"get",
 				url:url,
 				data: {"id" :regId},
 				dataType:'json',
 				async: false,
 				success:function(data){
 					$("#regId").val(data.dataMsg.id);
 					$("#regUrl").val(data.dataMsg.url);
 					$("#content").val(data.dataMsg.content);
					var str = '';
					str = "<option id='"+data.dataMsg.id+"' value='"+data.dataMsg.name+"' selected='selected'>"+data.dataMsg.name+"</option>";
					$("#regName").empty().append(str);
 					$("#regName").val(data.dataMsg.name);
 					var list;
 					list = data.dataMsg.dtls;//接口参数
 					str = '';
					for(var j=0;j<list.length;j++){
						var id=list[j].id;
						var paramNo=j+1;
						var paramName=list[j].paramName;
						var paramType=list[j].paramType;
						var paramValue=list[j].paramValue;
						str+="<tr id="+id+"><td><input type='text' class='par-table' id='paramNo'disabled='disabled' value="+paramNo+"></td><td><input  type='text' class='par-table' id='paramName' disabled='disabled' value="+paramName+"></td><td ><input type='text' class='par-table' id='paramType' disabled='disabled' value="+paramType+"></td><td><input disabled='disabled' class='par-table' type='text' id='paramValue' value="+paramValue+" ></td></tr>";
					}
					$(".d-table tbody").empty().append(str);
 				}
 			});
		}
		
		$("#upload").change(function(){
			var url = "<%=registerurl %>/baseReport/uploadHTML";
			var formData = new FormData();  
// 			formData.append('file',$("#upload").get(0));
			formData.append("sbName","sbValue");
			formData.append( "CustomField1", "This is some extra data" );
			formData.append( "CustomField2", "This is some extra data" );
			formData.append( "CustomField3", "This is some extra data" );
			console.debug(formData);
			$.ajax({  
		          url: url,  
		          type: 'POST',  
		          data: formData,  
		          async: false,  
		          cache: false,  
		          contentType: false,  
		          processData: false,  
		          success: function (returndata) {  
		        	  layer.alert(returndata);  
		          },  
		          error: function (returndata) {  
		        	  layer.alert(returndata);  
		          }  
		     });  
		});
	$('#pageSize').on('change',function(){
		if($(this).val()>15){
			 layer.alert('分页数据不能大于15条');
		}
	})
 	});
 </script>
</body>    

</html>