<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.huatuo.clinics.cms.comm.ConfigProperites"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<% String path = request.getContextPath(); 
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
    <link href="<%=path %>/resource/css/bootstrap-multiselect.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/style.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/component.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/function.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/employeeList.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/location.css" rel="stylesheet" >
    <link href="<%=path %>/resource/css/jquery.Jcrop.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/font/iconfont.css" rel="stylesheet"type="text/css"/>
	<style type="text/css">
	*{
	font-family: '微软雅黑';
	}
	.jcrop-holder #preview-pane {
	display: block;
	position: absolute;
	z-index: 2000;
	top: 10px;
	right: -175px;
	padding: 6px;
	border: 1px rgba(0,0,0,.4) solid;
	background-color: white;

	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;

	-webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	}

	#preview-pane .preview-container {
	width: 150px;
	height: 150px;
	overflow: hidden;
	}
	#imgdiv{
	margin-left: 20px;
	}
	.clear::after {
	clear: both;
	content: "";
	display: table;
	}
	.clear {
	overflow: visible;
	}
	#imgClick{
	display:inline-block;
	width:100px;
	height:100px;
	}
	.default-position .JD-stock .tab li:first-child {
	position: relative;
	}

	.default-position .JD-stock .tab li > p {
	bottom: 0;
	cursor: auto;
	left: 0;
	position: absolute;
	right: 0;
	top: 0;
	}
	.store-selector .close {
	left: 390px;
	top: 14px;
	}
	.JD-stock .area-list li {
	width: 100px;
	}
	.JD-stock .tab li > p {
	bottom: 0;
	cursor: auto;
	left: 0;
	position: absolute;
	right: 0;
	top: 0;
	}
	.navbar-forms{
	padding-top:20px;
	padding-left:5px;
	width: 23%;
	}
	.par-table{
	border:1px gray solid;
	width:100px;
	height:20px;

	}
	tr.active>td{
	background-color:#dfedfe !important;
	}
	body>div>.inter{
	float: left;
	}
	.outerIn{
	border: 1px solid #2081c7;
	padding: 5px;
	overflow: hidden;
	margin: 5px;
	height: 98%;
	}
	.interLeft{
	border: 1px solid #2081c7;
	padding: 5px;
	margin: 5px;
	height: 98%;
	width: 20%;
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
	.interRight{
	border: 1px solid #2081c7;
	padding: 5px;
	margin: 5px;
	width: 78%;
	height: 98%;
	}
	.formTop{
	width: 99%;
	border: 1px solid #2081c7;
	padding: 5px;
	margin: 5px;
	}
	.formTopHead{
	font-size: 18px;
	padding-left: 5px;
	}
	.formTopInput{
	border: 1px solid #2081c7;
	}
	.formTop .form-inline{
	margin-top: 10px;
	width: 100%;
	}
	.formTop .form-inline .form-group{
	width: 100%;
	}
	.formTopInput.regName{
	width: 60%;
	}
	.formTopInput.regUrl{
	width: 75%;
	}
	.formTopInput.regMemo{
	width: 75%;
	resize: none;
	}
	.leftTableR input{
	width: 90%;
	height: 80%;
	padding-left: 3px;
	}
	.cursorP{
	cursor: pointer;
	}
	.ten.leftTable td{
	height:45px;
	}
	</style>
</head>
<body>
	<div class="outerIn">
	<div class="interLeft inter">
	<div><input type="hidden" id="flagId" value="${dto.id}"></div>
	<div><input type="hidden" id="provinceCode" value="${dto.provinceCode}"></div>
	<div><input type="hidden" id="cityCode" value="${dto.cityCode}"></div>
	<div><input type="hidden" id="countyCode" value="${dto.countyCode}"></div>
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
	<div class="g-table">
	<table class="ten leftTable">
	<thead>
	<tr>
	<td>接口列表</td>
	</tr>
	</thead>
	<tbody>

	</tbody>

	</table>
	</div>
	</div>
	<div class="interRight inter g-vertical-center ">
	<form method="post" id="addOneReport"  enctype="multipart/form-data">
	<div class="formTop">
	<div class="formTopHead">
	概述：
	</div>
	<input type="hidden" value="" name='regid' id="regid"/>
	<div class="form-inline" style="overflow: hidden">
	<div class="form-group"  style="float: left;width: 40%">
	<span style="color:red;">&emsp;&emsp;*</span><label for="name">名称：</label>
	<input type="text" class="form-control formTopInput regName" id="regName" name="regName" value="" disabled="disabled">
	<em class="errortips"></em>
	</div>
	<div style="float: left;margin-right: 27px;">
	<span style="color:red;">&emsp;&emsp;*</span><label for="local-selector">接口类型：</label>
	<div class="form-group" id="org-selector-wrap-Type" style="width: auto">
	<select class="form-control formTopInput" id="regValidFlgType" name="provinceCode" disabled="disabled" style="width: 135%">
		<option value="POST">POST</option>
		<option value="GET">GET</option>
	</select>
	<em class="errortips"></em>
	</div>
	</div>
	</div>
	<div class="form-inline">
	<div class="form-group">
	<span style="color:red;">&emsp;&emsp;*</span><label for="name">地址：</label>
	<input type="text" class="form-control formTopInput regUrl" id="regUrl" name="regUrl" value="" disabled="disabled">
	<em class="errortips"></em>
	</div>
	</div>
	<div class="form-inline">
	<div class="form-group">
	<span">&emsp;&emsp; </span><label for="name">描述：</label>
	<textarea type="text" class="form-control formTopInput regMemo" id="regMemo" name="regMemo" value="" disabled="disabled"></textarea>
	<em class="errortips"></em>
	</div>
	</div>

	</div>

	<div class="formTop" style="overflow: hidden">
	<div style="float: left;width: 45%">
	<span class="formTopHead">入参结构：</span>
	<span ><i class="Hui-iconfont Hui-iconfont-add cursorP" style="display:none;"></i></span>
	<span><i class="Hui-iconfont Hui-iconfont-jianhao cursorP" style="display:none;"></i></span>
	<table class="paramstable leftTableR">
	<thead>
	<tr>
	<td class="par-table">参数名称</td>
	<td class="par-table">参数类型</td>
	<td class="par-table">参数值</td>
	</tr>
	</thead>
	<tbody></tbody>
	</table>
	</div>


	<div style="float: right;width: 45%">
	<span style="color:red;">*</span><span class="formTopHead">数据结构：</span>
	<textarea style="width: 100%" type="text" class="form-control formTopInput regMemo" id="regContent" name="regContent" value="" disabled="disabled"></textarea>
	<em class="errortips"></em>
	</div>
	</div>
	</form>

	</div>
	</div>
</body> 

<script src="<%=path %>/resource/js/plugins/jquery-1.11.1.min.js"></script>
<script src="<%=path %>/resource/js/plugins/layer/layer.js"></script>
<script src="<%=path %>/resource/js/libs/ajaxfileupload.js"></script>
<script src="<%=path %>/resource/js/libs/jquery.Jcrop.js"></script>
<script src="<%=path %>/resource/js/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=path %>/resource/js/plugins/validate/messages_zh.min.js"></script>
<script src="<%=path %>/resource/js/plugins/validate/card.js"></script>
<script src="<%=path %>/resource/js/libs/location.js"></script>
<script src="<%=path %>/resource/js/position.js"></script>
<script type="text/javascript">
$(function(){
	getAllReportData();
	function getAllReportData(){
		var url = "<%=registerurl %>/reportData/listOnWork";
		$.ajax({
			type:"get",
			url:url,
			dataType:'json',
			async: false,
			success:function(data){
				var len=data.dataMsg.length;
				var str = '';
				for(var i=0;i<len;i++){
				str+="<tr id="+data.dataMsg[i].id+"><td>"+data.dataMsg[i].name+"</td></tr>";
				}
				$(".ten tbody").empty().append(str);
				
			}
		});
	};
	
 		$(".ten tbody tr").dblclick(function(){
 			 getById($(this).attr("id"));//
 			 $(".update,.save,.delete").attr('disabled',false);
 			 $(".form-control,.in-table").attr('disabled',true);
			 $(".Hui-iconfont").hide();
	 	});
 		
 		//编辑
 		$(".update").click(function(){
			 $(".form-control,.in-table").attr('disabled',false);
			 $(".Hui-iconfont").show();
	 	});


 		//删除
 		$(".delete").click(function(){
			layer.msg('确定删除？', {
				time: 0,
				btn: ['确定', '取消'],
				yes: function(index){
					layer.close(index);
					var id = $("#regid").val();
					deleteById(id);
					newBegin();
					$(".ten tbody tr").dblclick(function(){
						getById($(this).attr("id"));//
						$(".update,.save,.delete").attr('disabled',false);
						$(".form-control,.in-table").attr('disabled',true);
						$(".Hui-iconfont").hide();
					});
				}
			});
		});
		//新增
		$(".add").click(function(){
			$(".form-control,.in-table").attr('disabled',false);
			$(".Hui-iconfont").show();
			$(".save").attr('disabled',false);
			newBegin();
			$(".ten tbody tr").dblclick(function(){
	 			 getById($(this).attr("id"));//
	 			 $(".update,.save,.delete").attr('disabled',false);
		 	});
		});
	//保存
	$(".save").click(function(){
		var name = $('#regName').val();
		var type = $('#regValidFlgType').val();
		var regUrl = $('#regUrl').val();
		var validFlg = $('#regValidFlg').val();
		var memo = $('#regMemo').val();
		var content = $('#regContent').val();
		var dtls = [];
		var sendId = $('#regid').val();
		$.each($('.paramstable tbody tr'),function(){
		var obj = {};
		obj.id = $(this).attr('id');
		obj.dtiId = sendId;
		obj.paramName = $(this).find('#paramName').val();
		obj.paramType = $(this).find('#paramType').val();
		obj.paramValue = $(this).find('#paramValue').val();
		dtls.push(obj);
		console.log($(this).find('#paramName'))
		});
		var data = {
		id:sendId,
		name:name,
		type:type,
		url:regUrl,
		validFlg:validFlg,
		memo:memo,
		content:content,
		dtls:dtls
	
		};
		var url = "<%=registerurl %>/reportData/saveReportData";
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
			$(".ten tbody tr").dblclick(function(){
	 			 getById($(this).attr("id"));//
	 			 $(".update,.save,.delete").attr('disabled',false);
		 	});
		}
	});

	});
 		
 		function getById(id){
 			var url = "<%=registerurl %>/reportData/getById";
 			$.ajax({
 				type:"get",
 				url:url,
 				data: {"id" :id},
 				dataType:'json',
 				async: false,
 				success:function(data){
 					$("#regid").val(data.dataMsg.id);
 					document.getElementById('regName').value =data.dataMsg.name;
 					document.getElementById('regMemo').value =data.dataMsg.memo;
 					document.getElementById('regUrl').value =data.dataMsg.url;
 					document.getElementById('regContent').value =data.dataMsg.content;
 					document.getElementById('regValidFlgType').value =data.dataMsg.type;
 					var list=data.dataMsg.dtls;  //接口参数
					var str = '';
					for(var j=0;j<list.length;j++){
					var id=list[j].id;
					var paramName=list[j].paramName;
					var paramType=list[j].paramType;
					var paramValue=list[j].paramValue;
					str+="<tr id="+id+"><td><input  type='text' class='par-table in-table' id='paramName' disabled='disabled' value="+paramName+"></td><td ><input type='text' class='par-table in-table' id='paramType' disabled='disabled' value="+paramType+"></td><td><input disabled='disabled' class='par-table in-table' type='text' id='paramValue' value="+paramValue+" ></td></tr>";
					}
					$(".paramstable tbody").empty().append(str);
 				}
 			});
 		}
 		
 		function deleteById(id){
			var url = "<%=registerurl %>/reportData/deleteById";
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
 		
 		//增加参数
 		$(".Hui-iconfont-add").click(function(){
 			$(".paramstable tbody").append("<tr><td><input type='hidden' value='' name='dtlId'><input  type='text' class='par-table in-table' id='paramName' value=''></td><td ><input type='text' class='par-table in-table' id='paramType' value=''></td><td><input  class='par-table in-table' type='text' id='paramValue' value='' ></td></tr>");
 		});
 		
 		//增加参数table点击高亮效果
 		$(document).on("click",".paramstable tbody tr",function(){
 			$(this).addClass("active");
	  		$(this).siblings().removeClass("active");
 		});
 		
 		
 		
 		//删除参数
 		$(".Hui-iconfont-jianhao").click(function(){
 			var id=$('.paramstable tr.active').attr('id');
 			if(id !=''){
				$('.paramstable tr.active').remove();
				deleteUser(id);
			}else{
				$('.paramstable tr.active').remove();
				layer.alert("删除成功");
			}


 		});
 		
 		
 		function deleteUser(id){
	 		layer.confirm('确定删除吗？', function(){  
	 			params = {'dtlId' : id};
				var url = "<%=registerurl %>/reportData/deleteByDtlId";
				$.ajax({
					 type:"get",
					 url:url,
					 data: params,
					 dataType:'json',
					 async: false,
					 success:function(data){
						 layer.alert(data.stateString);
						 newBegin();
						 $(".ten tbody tr").dblclick(function(){
				 			 getById($(this).attr("id"));//
				 			 $(".update,.save,.delete").attr('disabled',false);
					 	});
					 }
				});
	 		});
		}
 		
 		function newBegin(){
             $('.paramstable tbody').empty();
             $('.interRight input').attr('disabled',false).val('');
             $('.interRight select').attr('disabled',false).val('');
             $('.interRight textarea').attr('disabled',false).val('');
             getAllReportData();
         }

 		
});
</script>
