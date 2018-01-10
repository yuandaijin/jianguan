<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.huatuo.common.Config"%>
<%@ page import="com.huatuo.common.ConfigProperites"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<% String path = request.getContextPath(); 
   String[] imageType=Config.SUPPORTED_IMG_TYPES;
   String imageUrl =ConfigProperites.getImageUrl();
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
    <style type="text/css">
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
</style>
</head>
<body>
<div class="g-bd">
	<div><input type="hidden" id="flagId" value="${dto.id}"></div>
	<div><input type="hidden" id="provinceCode" value="${dto.provinceCode}"></div>
	<div><input type="hidden" id="cityCode" value="${dto.cityCode}"></div>
	<div><input type="hidden" id="countyCode" value="${dto.countyCode}"></div>
	<div><input type="hidden" id="antibioticLevelPolityId" value="${dto.antibioticLevelPolityId}"></div>
	<div class="g-vertical-center ">
	<form action="<%=path%>/org/addOneOrg" method="post" id="addOneOrg"  enctype="multipart/form-data">
		<div class="form-inline">
		  <div class="form-group">
		    <span style="color:red;">&emsp;&emsp;*</span><label for="name">机构名称：</label>
		    <input type="text" class="form-control" id="name" name="orgName" value="${dto.orgName}">
		    <em class="errortips"></em>
		  </div>
		</div>  
		<div class="form-inline">
		  <div class="form-group">
		    <span style="color:red;">&emsp;&emsp;*</span><label for="address">机构地址：</label>
		    <input type="text" class="form-control" id="address" name="orgAddress" value="${dto.address}">
		    <em class="errortips"></em>
		  </div>
		</div>
		<div class="form-inline">
		  <div class="form-group">
		    <span style="color:red;">&emsp;*</span><label for="url">机构联系人：</label>
		    <input type="text" class="form-control" id="url" name="userName" value="${dto.userName}" >
		    <em class="errortips"></em>
		  </div>
		</div>
		<div class="form-inline">
		  <div class="form-group">
		    <span style="color:red;">&emsp;*</span><label for="telephone">联系人电话：</label>
		    <input type="text" class="form-control" id="telephone" name="telNum" value="${dto.mobile}" minlength="11"  istelphone1="true">
		    <em class="errortips"></em>
		  </div>
		</div>
		<div class="form-inline">
		  <div class="form-group">
		    <span style="color:red;">&emsp;*</span><label for="local-antibiotic">抗生素规则：</label>
		    <select class="form-control" id="local-antibiotic" name="antibiotic"></select>
		    <em class="errortips"></em>
		  </div>
		</div>
		<div class="form-inline">
		  <div class="form-group">
		    <span style="color:red;">*</span><label for="local-selector">机构管辖区域：</label>
			<div class="form-group" id="org-selector-wrap">
			    <select class="form-control" id="local-selector1" name="provinceCode"></select>
			     <select class="form-control" id="local-selector2" name="cityCode"></select>
			     <select class="form-control" id="local-selector3" name="countyCode"></select>
			    <em class="errortips"></em>
			</div>
		 </div>
	   </div>	
     </form>
		<div class="form-inline" role="form">
		   <div class="">
		      <div class="col-sm-offset-9 col-sm-10">
		      		<c:if test="${dto.id != null}">
				         	<button type="submit" class="btn btn-primary" id="back">返回列表</button>
		        	 </c:if>
		        	<button type="submit" id="saveOrg" class="btn btn-primary">保存</button>
		      </div>
		   </div>
		</div>
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
	getAntibiotic();
	getAddress(-1,"local-selector1");
	getAddress(33006,"local-selector2");
	$('#local-selector1').change(function(){
		if($('#local-selector1').val()=="1"){
			 $("#local-selector2").append('<option selected="selected" value="1" class="local-selector1">全国</option>');
			 $("#local-selector3").append('<option selected="selected" value="1" class="local-selector1">全国</option>');
			 $("#local-selector2").attr('disabled',true)
			 $("#local-selector3").attr('disabled',true)
		}else{
			 $("#local-selector2").attr('disabled',false)
			 $("#local-selector3").attr('disabled',false)
			$("#local-selector2").html("");
			if($("#local-selector1").val()!=''){
				getAddress($("#local-selector1").val(),"local-selector2");
				$("#local-selector3").append('<option selected="selected" value="0" class="local-selector1"></option>');
			}
		}
	});
	
	$('#local-selector2').change(function(){
		$("#local-selector3").html("");
		if($("#local-selector2").val()!=''){
			getAddress($("#local-selector2").val(),"local-selector3");
		}
	});
	
	function getAddress(parentId,name){
		var url = "<%=path%>/comm/resultAddress";
		$.ajax({
			type:"get",
			url:url,
			data:{"parentId" : parentId},
			dataType:'json',
			async: false,
			success:function(data){
				var flagid=$("#flagId").val();
				var provinceCode=$("#provinceCode").val();
				var cityCode=$("#cityCode").val();
				var countyCode=$("#countyCode").val();
				if(data.code=="000000"){
				    var list=data.list;
				    var len=list.length;
				    var html = [];
				    html.push('<option selected="selected" value="" class="local-selector1">-- 请选择 --</option>');
				    for(var i=0;i<len;i++){
				    		if(flagid==''){
				    			//新增返回
				    			if(list[i].id=='33006'){//默认选中成都市
						    		html.push('<option selected="selected" value="'+list[i].id+'" class=local-selector1>'+list[i].name+'</option>');
						    	}else{
						    		html.push('<option value="'+list[i].id+'" class=local-selector1>'+list[i].name+'</option>');
						    	}
				    		}else{
				    			//修改返回
				    			if(list[i].id==provinceCode || list[i].id==cityCode || list[i].id==countyCode){
						    		html.push('<option selected="selected" value="'+list[i].id+'" class=local-selector1>'+list[i].name+'</option>');
						    	}else{
						    		html.push('<option value="'+list[i].id+'" class=local-selector1>'+list[i].name+'</option>');
						    	}
				    		}
				    }
				    $("#"+name+"").append(html.join(''));
				}else{
				    layer.alert('返回地址列表失败');
				}
			}
		});
	}
	
	
	function getAntibiotic(){
		var url = "<%=path%>/org/getAntibiotic";
		$.ajax({
			type:"get",
			url:url,
			dataType:'json',
			async: false,
			success:function(data){
				console.log(data);
				var flagid=$("#flagId").val();
				var polityId=$("#antibioticLevelPolityId").val();
				if(data.code=="000000"){
					  var list=data.list;
					  var len=list.length;
					  var html = [];
					  for(var i=0;i<len;i++){
				    		if(flagid==''){
						    		html.push('<option  value="'+list[i].id+'" class=local-antibiotic>'+list[i].name+'</option>');
				    		}else{
				    			//修改返回
				    			if(list[i].id==polityId){
						    		html.push('<option selected="selected" value="'+list[i].id+'" class=local-selector1>'+list[i].name+'</option>');
						    	}else{
						    		html.push('<option value="'+list[i].id+'" class=local-selector1>'+list[i].name+'</option>');
						    	}
				    		}
				    }
					$("#local-antibiotic").append(html.join(''));
				}else{
					layer.alert('抗生素规则查询失败');
				}
			}
		});
	}
	
	
	//进入修改页面默认选中下拉地址的信息
	selectedAddress();
	function selectedAddress(){
		var flagid=$("#flagId").val();
		if(flagid != ''){
			var provinceCode=$("#local-selector1").val();//取出第一个select的值
			getAddress(provinceCode,"local-selector2");
			var code=$("#local-selector2").val();//取出第二个select的值
			getAddress(code,"local-selector3");
		}
	};
	
	
	/* 验证 */
	var addOrgReg = {
	    rules:{},
	    msg:{}
	};

	//机构名称
	addOrgReg.rules[$("#name").attr('name')] = {
	    	required:true,
    		sensitiveCharacter:true,
	    	specialCharValidate: true
        };
	addOrgReg.msg[$("#name").attr('name')] = {
	    	required:'必填'
        };

	//详细地址
	addOrgReg.rules[$("#address").attr('name')] = {
	    	required:true,
    		sensitiveCharacter:true,
			specialCharValidateTextarea: true
        };
	addOrgReg.msg[$("#address").attr('name')] = {
	    	required: '必填'
        };
    
	//联系电话
	addOrgReg.rules[$("#telephone").attr('name')] = {
			required:true,
			sensitiveCharacter: true,
			specialCharValidate: true
	    };
	addOrgReg.msg[$("#telephone").attr('name')] = {
			required:'必填'
	    };
	//机构联系人
	addOrgReg.rules[$("#url").attr('name')] = {
			required:true
        };
	addOrgReg.msg[$("#url").attr('name')] = {
	    	required:'必填'
        };
	
	//地址1
	addOrgReg.rules[$("#local-selector1").attr('name')] = {
			required:true
        };
	addOrgReg.msg[$("#local-selector1").attr('name')] = {
	    	required:'必填'
        };
	//地址2
	addOrgReg.rules[$("#local-selector2").attr('name')] = {
			required:true
        };
	addOrgReg.msg[$("#local-selector2").attr('name')] = {
	    	required:'必填'
        };
	//地址3
	addOrgReg.rules[$("#local-selector3").attr('name')] = {
			required:true
        };
	addOrgReg.msg[$("#local-selector3").attr('name')] = {
	    	required:'必填'
        };
	//抗生素规则验证
	addOrgReg.rules[$("#local-antibiotic").attr('name')] = {
			required:true
        };
	addOrgReg.msg[$("#local-antibiotic").attr('name')] = {
	    	required:'必填'
        };
	
	$("#addOneOrg").validate({
        rules:addOrgReg.rules,
        errorPlacement: function(error,element){
            $(element).siblings('.errortips').html(error);
        },
        onfocusout: function(element) { $(element).valid(); },
        messages:addOrgReg.msg		    	
    });
	
	//返回列表
	$("#back").click(function(){
		window.location = "<%=path %>/org/orgupdate";
	});
	
	$("#saveOrg").click(function(){
		if(!$('#addOneOrg').valid()) {
			return false;
		}
		if($("#flagId").val() != ""){
			updateOrg();
		 }else{
			 saveOrg();
		 }
	});
	
	
	
	 
	//新增机构
	function saveOrg(){
		if($('#addOneOrg').valid()) {
			var flagid=$("#flagId").val();
			var name=$("#name").val();
			var address=$("#address").val();
			var userName=$("#url").val();
			var telephone=$("#telephone").val();
			var provinceCode=$("#local-selector1").val();
			var province=$("#local-selector1 option:selected").text();
			var cityCode=$("#local-selector2").val();
			var city=$("#local-selector2 option:selected").text();
			var countyCode=$("#local-selector3").val();
			var county=$("#local-selector3 option:selected").text();
			var antibiotic=$("#local-antibiotic").val();
			var url =$("#addOneOrg").attr('action');
			var  params =JSON.stringify({"id" : flagid,"orgName" : name,"orgAddress" : address,"userName" : userName,"mobile" : telephone,"provinceCode" : provinceCode,"province" : province,"cityCode" : cityCode,"city" : city,"countyCode" : countyCode,"county" : county,"antibioticLevelPolityId":antibiotic});
			$.ajax({
				 type:"post",
				 url:url,
				 data: params,
				 dataType:'json',
				 async: false,
				 contentType: "application/json; charset=utf-8", 
				 success:function(data){
					 if(data.code == '000000'){
						 layer.alert('增加成功');
					 } else if(data.code =='100001') {
						 layer.alert('机构名称重复');
					 }else{
						 layer.alert('增加失败');
					 }
				 }
			});
		}
	}
	
	//修改机构
	function updateOrg(){
		if($('#addOneOrg').valid()) {
			var flagid=$("#flagId").val();
			var name=$("#name").val();
			var address=$("#address").val();
			var userName=$("#url").val();
			var telephone=$("#telephone").val();
			var provinceCode=$("#local-selector1").val();
			var province=$("#local-selector1 option:selected").text();
			var cityCode=$("#local-selector2").val();
			var city=$("#local-selector2 option:selected").text();
			var countyCode=$("#local-selector3").val();
			var county=$("#local-selector3 option:selected").text();
			var antibiotic=$("#local-antibiotic").val();
			var url =$("#addOneOrg").attr('action');
			var  params =JSON.stringify({"id" : flagid,"orgName" : name,"orgAddress" : address,"userName" : userName,"mobile" : telephone,"provinceCode" : provinceCode,"province" : province,"cityCode" : cityCode,"city" : city,"countyCode" : countyCode,"county" : county,"antibioticLevelPolityId":antibiotic});
			$.ajax({
				 type:"post",
				 url:url,
				 data: params,
				 dataType:'json',
				 async: false,
				 contentType: "application/json; charset=utf-8", 
				 success:function(data){
					 if(data.code == '000000'){
						 layer.alert('修改成功');
					 } else if(data.code =='100001') {
						 layer.alert('机构名称重复');
					 }else{
						 layer.alert('修改失败');
					 }
				 }
			});
		}
	}
	
});
</script>
