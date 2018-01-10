<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%String path = request.getContextPath(); %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <title>华佗在线后台管理系统</title>
    <link href="<%=path %>/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/bootstrap-multiselect.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/style.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/function.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/employeeList.css" rel="stylesheet">
    <style>
    </style>
</head>
<body>
<div class="g-bd">
	<div class="g-vertical-center ">
	<form action="<%=path%>/user/passwordUpdate" method="get" id="adduser"> 	
			<div class="form-inline">
				 <div class="form-group">
				    <span style="color:red;">*</span><label for="input1" style="letter-spacing:8px;">旧密码：</label>
				    <input type="password" class="form-control" id="input1" name="password1" required="true"  minlength="6" maxlength="16">
				    <em class="errortips"></em>
				  </div>
			</div>
			<div class="form-inline">
				 <div class="form-group">
				    <span style="color:red;">*</span><label for="input2" style="letter-spacing:8px;">新密码：</label>
				    <input type="password" class="form-control" id="input2" name="password2" required="true"  minlength="6" maxlength="16">
				    <em class="errortips"></em>
				  </div>
			</div>
			<div class="form-inline">
				 <div class="form-group">
				    <span style="color:red;">*</span><label for="input3">确  认 密  码 ：&nbsp;</label>
				    <input type="password" class="form-control" id="input3"  name="password3" required="true"  minlength="6" maxlength="16" ispassword="true">
				    <em class="errortips"></em>
				  </div>
			</div>
    </form>
		<div class="form-inline" role="form">
		   <div class="">
		      <div class="col-sm-offset-9 col-sm-10">
		         <button type="button" class="btn btn-primary" id="back">返回列表</button>
		         <button type="button" class="btn btn-primary" id="save">保存修改</button>
		      </div>
		   </div>
		</div>
	</div>
</div>
<script src="<%=path %>/resource/js/plugins/jquery-1.11.1.min.js"></script>
<script src="<%=path %>/resource/js/plugins/layer/layer.js"></script>
<script src="<%=path %>/resource/js/plugins/bootstrap.min.js"></script>
<script src="<%=path %>/resource/js/plugins/validate/jquery.validate.min.js"></script>
 <script src="<%=path %>/resource/js/plugins/validate/card.js"></script>
<script src="<%=path %>/resource/js/plugins/bootstrap-multiselect.js"></script>
<script src="<%=path %>/resource/js/plugins/validate/messages_zh.min.js"></script>
<script type="text/javascript">
$(function(){
	
	//返回列表
	$("#back").click(function(){
		console.log("xxxx");		
		window.location = "<%=path %>/user/personInfo";
	});
	/* 验证 */
	var updateOneUserReg = {
	    rules:{},
	    msg:{}
	};
	updateOneUserReg.rules[$("#input1").attr('name')] = {
			required:true,
			sensitiveCharacter: true,
			specialCharValidate: true
	    };
	updateOneUserReg.msg[$("#input1").attr('name')] = {
			required:'必填'
	    };
	updateOneUserReg.rules[$("#input2").attr('name')] = {
			required:true,
			sensitiveCharacter: true,
			specialCharValidate: true
	    };
	updateOneUserReg.msg[$("#input2").attr('name')] = {
			required:'必填'
	    };
	updateOneUserReg.rules[$("#input3").attr('name')] = {
			required:true,
			sensitiveCharacter: true,
			specialCharValidate: true
	    };
	updateOneUserReg.msg[$("#input3").attr('name')] = {
			required:'必填'
	    };
	
	$('#adduser').validate({
		rules:updateOneUserReg.rules,
		errorPlacement: function(error,element){
			 $(element).siblings('.errortips').html(error);
		},
	    onfocusout: function(element) { $(element).valid(); },
			 messages:updateOneUserReg.msg
	});
	jQuery.validator.addMethod("ispassword", function(value, element) {    
		  var tel = $("#input2").val();    
		  return this.optional(element) || (tel==value);    
		}, "两次密码不一致");
	
	//保存
	$("#save").click(function(){
		if(!$('#adduser').valid()) {
			return false;
		}
			 saveUser();
	});
	
	//新增
	function saveUser(){
		if($('#adduser').valid()) {
			var url = $("#adduser").attr("action");
			$.ajax({
				 type:"get",
				 url:url,
				 data: {"newPassword":$("#input2").val(),"password" :$("#input1").val()},
				 dataType:'json',
				 async: false,
				 success:function(data){
					 if(data.code == '000000'){
						 layer.alert('修改成功');
					 } else if(data.code =='100001') {
						 layer.alert('旧密码输入错误');
					 }else{
						 layer.alert('修改失败');
					 }
				 }
			});
		}
	}
	
});	
</script>
</body>    

</html>