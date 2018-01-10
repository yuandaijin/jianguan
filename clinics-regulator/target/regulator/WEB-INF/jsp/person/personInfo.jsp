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
	<div><input type="hidden" id="orgId" value="${rolebean.orgId}"></div>
	<div><input type="hidden" id="roleId" value="${rolebean.roleId}"></div>
	<div class="g-vertical-center ">
	<form action="<%=path%>/user/addUser" method="get" id="adduser"> 	
	<div><input type="hidden" id="flagId" value="${rolebean.id}" name="flagId"></div>
		<div class="form-inline">
		  <div class="form-group">
		    <span style="color:red;">*</span><label for="input1" style="letter-spacing:8px;">账户：</label>
		    <input type="text" class="form-control" id="input1" name="userName" required="true"  value="${rolebean.userName}">
		    <em class="errortips"></em>
		  </div>
		</div>
		<div class="form-inline">
			 <div class="form-group">
			    <span style="color:red;">*</span><label for="input3" style="letter-spacing:8px;">姓名：</label>
			    <input type="text" class="form-control" id="input3" name="userNames" required="true"  value="${rolebean.userNames}">
			    <em class="errortips"></em>
			  </div>
		</div>
		<div class="form-inline">
			 <div class="form-group">
			    <span style="color:red;">*</span><label for="input4">联系电话：</label>
			    <input type="text" class="form-control" id="input4" name="mobile" minlength="11"   istelphone1="true"  value="${rolebean.mobile}">
			    <em class="errortips"></em>
		  	</div>
		</div>
		<div class="form-inline">
			 <div class="form-group">
			    <span style="color:red;">*</span><label for="input5" style="letter-spacing:8px;">职位：</label>
			    <input type="text" class="form-control" id="input5" name="userType" required="true"  value="${rolebean.userType}">
			    <em class="errortips"></em>
		  	</div>
		</div>
        <div class="form-inline">
          <div class="form-group">
            <span style="color:red;">*</span><label for="input6">所属机构：</label>
            	<select class=form-control id="input6" name="companyId">
            	</select><em class="errortips"></em>
          </div>
        </div>
        <div class="form-inline">
        	<span style="color:red;">*</span><label for="input7">用户角色：</label>
            	<select class=form-control id="input7" name="roleId">
            	</select><em class="errortips"></em>
         </div>
        </form>
		<div class="form-inline" role="form">
		   <div class="">
		      <div class="col-sm-offset-9 col-sm-10">
		         <button type="button" class="btn btn-primary" id="updatePW">修改密码</button>
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
	updateOneUserReg.rules[$("#input3").attr('name')] = {
			required:true,
			sensitiveCharacter: true,
			specialCharValidate: true
	    };
	updateOneUserReg.msg[$("#input3").attr('name')] = {
			required:'必填'
	    };
	updateOneUserReg.rules[$("#input4").attr('name')] = {
			required:true,
			sensitiveCharacter: true,
			specialCharValidate: true
	    };
	updateOneUserReg.msg[$("#input4").attr('name')] = {
			required:'必填'
	    };
	updateOneUserReg.rules[$("#input5").attr('name')] = {
			required:true,
			sensitiveCharacter: true,
			specialCharValidate: true
	    };
	updateOneUserReg.msg[$("#input5").attr('name')] = {
			required:'必填'
	    };
	updateOneUserReg.rules[$("#input6").attr('name')] = {
			required:true,
			sensitiveCharacter: true,
			specialCharValidate: true
	    };
	updateOneUserReg.msg[$("#input6").attr('name')] = {
			required:'必填'
	    };
	updateOneUserReg.rules[$("#input7").attr('name')] = {
			required:true,
			sensitiveCharacter: true,
			specialCharValidate: true
	    };
	updateOneUserReg.msg[$("#input7").attr('name')] = {
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

	
	
	//查询角色列表
	getRoles();
	//查询机构列表
	findAllManageCompany();
	function getRoles(){
		var url = "<%=path%>/user/optionList";
		$.ajax({
			type:"get",
			url:url,
			dataType:'json',
			async: false,
			success:function(data){
				if(data.code=="000000"){
					var flagId=$("#flagId").val();
					var roleId=$("#roleId").val();
					var list=data.list;
				    var len=list.length;
				    var html = [];
				    html.push('<option value="" >--请选择--</option>');
				    for(var i=0;i<len;i++){
				    	if(flagId != ''){
				    		//修改默认选中角色
				    		if(roleId == list[i].id){
				    			html.push('<option selected="selected" value="'+list[i].id+'" >'+list[i].rolName+'</option>');
				    			if(flagId==1){
				    				$("#input7").attr('disabled',false);
				    			}else{
				    				$("#input7").attr('disabled',true);
				    			}
				    		}else{
				    			html.push('<option value="'+list[i].id+'" >'+list[i].rolName+'</option>');
				    		}
				    	}else{
				    		html.push('<option value="'+list[i].id+'" >'+list[i].rolName+'</option>');
				    	}
				    }
				    $("#input7").append(html.join(''));
				}else{
					layer.alert('角色列表查询失败');
				}
			}
		});
	}
	
	function findAllManageCompany(){
		var url = "<%=path%>/user/findAllManageCompany";
		$.ajax({
			type:"get",
			url:url,
			dataType:'json',
			async: false,
			success:function(data){
				if(data.code=="000000"){
					var flagId=$("#flagId").val();
					var orgId=$("#orgId").val();
					var list=data.list;
				    var len=list.length;
				    var html = [];
				    html.push('<option value="" >--请选择--</option>');
				    for(var i=0;i<len;i++){
				    	if(flagId != ''){
				    		//修改默认选中机构
				    		if(orgId == list[i].id){
				    			html.push('<option selected="selected" value="'+list[i].id+'" >'+list[i].orgName+'</option>');
				    			if(flagId==1){
				    				$("#input6").attr('disabled',false);
				    			}else{
				    				$("#input6").attr('disabled',true);
				    			}
				    		}else{
				    			html.push('<option  value="'+list[i].id+'" >'+list[i].orgName+'</option>');
				    		}
				    	}else{
				    		html.push('<option value="'+list[i].id+'" >'+list[i].orgName+'</option>');
				    	}
				    }
				    $("#input6").append(html.join(''));
				}else{
					layer.alert('机构列表查询失败');
				}
			}
		});
	}
	
	$("#save").click(function(){
		if(!$('#adduser').valid()) {
			return false;
		}
			updateUser();
	});
	
	
	//修改
	function updateUser(){
		var flagId=$("#flagId").val();
		$("#input6").attr('disabled',false);
		$("#input7").attr('disabled',false);
		if($('#adduser').valid()) {
			var url = $("#adduser").attr("action");
			$.ajax({
				 type:"post",
				 url:url,
				 data: $("#adduser").serialize(),
				 dataType:'json',
				 async: false,
				 success:function(data){
					 if(data.code == '000000'){
						 layer.alert('修改成功');
						 console.log(flagId+"xxxxxx");
						 if(flagId==1){
								 $("#input6").attr('disabled',false);
								 $("#input7").attr('disabled',false);
			    			}else{
			    				 $("#input6").attr('disabled',true);
								 $("#input7").attr('disabled',true);
			    			}
					 } else if(data.code =='100001') {
						 layer.alert('用户名重复');
					 }else{
						 layer.alert('修改失败');
					 }
				 }
			});
		}
	}
	
	//修改密码
	$(document).on("click","#updatePW",function(){
			var url = "<%=path%>/user/updatePassword";
		$("body").load(url);
		});
	
});
</script>
</body>    

</html>