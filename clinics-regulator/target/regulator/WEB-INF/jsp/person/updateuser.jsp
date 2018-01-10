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
    <link href="<%=path %>/resource/css/function.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/employeeList.css" rel="stylesheet">
    <style>
		
    </style>
</head>
<body>
<div class="g-bd">
	<div class="g-vertical-center ">
	<form action="<%=path%>/user/updateOneUser" method="post" id="updateOneUser">
		<input type="hidden" id="uid" value="${uid}" name="uid">
		<input type="hidden" id="roleIds" value="${roleIds}">
		<div class="form-inline">
		  <div class="form-group">
		    <label for="input1">账号：</label>
		    <input type="text" class="form-control" id="input1" name="userName" value="${username}" readonly="readonly">
		  </div>
		  <div class="form-group">
		    <label for="password">密码：</label>
		    <input type="password" class="form-control" id="password" name="password">
		    <em class="errortips"></em>
		  </div>
		  <div class="form-group">
		    <label for="password2">确认密码：</label>
		    <input type="password" class="form-control" id="password2" name="password2">
		    <em class="errortips"></em>
		  </div>
		</div>
        <div class="form-inline">
          <div class="form-group">
            <label for="input6">员工角色：</label>
            <!-- Single button -->
            <select class="multiselect" multiple="multiple" name="roleId" id="roleId">
            </select>
          </div>
        </div>
        </form>
		<div class="form-inline" role="form">
		   <div class="">
		      <div class="col-sm-offset-9 col-sm-10">
		         <button type="submit" class="btn btn-primary" id="back">返回列表</button>
		         <button type="submit" class="btn btn-primary" id="update">修改</button>
		      </div>
		   </div>
		</div>
	</div>
</div>
<script src="<%=path %>/resource/js/plugins/jquery-1.11.1.min.js"></script>
<script src="<%=path %>/resource/js/plugins/layer/layer.js"></script>
<script src="<%=path %>/resource/js/plugins/bootstrap.min.js"></script>
<script src="<%=path %>/resource/js/plugins/bootstrap-multiselect.js"></script>
<script src="<%=path %>/resource/js/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=path %>/resource/js/plugins/validate/messages_zh.min.js"></script>
 <script src="<%=path %>/resource/js/plugins/validate/card.js"></script>
<script type="text/javascript">
$(function(){
getRoles();
function getRoles(){
	var url = "<%=path%>/user/optionList";
	$.ajax({
		type:"get",
		url:url,
		dataType:'html',
		async: false,
		success:function(data){
			$(".multiselect").html(data);
			var obj = $("#roleIds").val().split(",");
			for(var i = 0; i < obj.length; i++){
				obj[i]= parseInt(obj[i]);
				$('#roleId').multiselect('select', obj[i]);
			}
		}
	});
}

$("#back").click(function(){
	window.location = "<%=path%>/user/urlselete";
});

	/* 验证 */
var updateOneUserReg = {
    rules:{},
    msg:{}
};
updateOneUserReg.rules[$("#password").attr('name')] = {
		required:true,
		sensitiveCharacter: true,
		specialCharValidate: true
    };
updateOneUserReg.msg[$("#password").attr('name')] = {
		required:'请输入密码'
    };
updateOneUserReg.rules[$("#password2").attr('name')] = {
		required:true,
		equalTo:"#password"
    };
updateOneUserReg.msg[$("#password2").attr('name')] = {
		required:'请再次确认输入密码',
		equalTo: '两次输入密码不相同'
    };
$('#updateOneUser').validate({
    rules:updateOneUserReg.rules,
    errorPlacement: function(error,element){
        $(element).siblings('.errortips').html(error);
    },
    onfocusout: function(element) { $(element).valid(); },
    messages:updateOneUserReg.msg		    	
});
$("#update").click(function(){
	if(!$('#updateOneUser').valid()) {
		return false;
	}
	if($("#password").val() != $("#password2").val()) {
		layer.alert("密码和确认密码不一致");
		return false;
	}
	var url = $("#updateOneUser").attr("action");
	$.ajax({
		 type:"post",
		 url:url,
		 data: $("#updateOneUser").serialize(),
		 dataType:'json',
		 async: false,
		 success:function(data){
			 if(data.code == '000000'){
				 layer.alert('修改成功');
			 } else {
				 layer.alert('该修改失败');
			 }
		 }
	});
});
});
</script>
</body>    

</html>