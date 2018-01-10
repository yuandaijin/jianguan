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
    <link href="<%=path %>/resource/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/style.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/function.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/employeeList.css" rel="stylesheet">
    <style>
		
    </style>
</head>
<body>
<nav class="g-hd navbar navbar-default">
	<!-- <div class="navbar-form pull-left btn-group">
	<button type="button" class="btn btn-info">删除</button>
	</div> -->		
	<form class="navbar-form pull-right" id="search-form" role="search">
          <div class="form-group">
           		<label for="input6">所属机构：</label>
           		<select class="form-control" id="input6" name="companyId"></select>
          </div>
		<div class="form-group">
		  <input type="text" class="form-control" id="search" name="search" placeholder="用户名称">
		</div>
		<button type="button" class="btn btn-primary">搜索</button>
	</form>
</nav>

<div class="g-bd">
    <div class="table-responsive">
        <table class="table table-striped s-table">
            <thead>
                <tr>
                    <th>账户名</th>
                    <th>姓名</th>
                    <th>联系方式</th>
                    <th>职位</th>
                    <th>所属机构</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
 <script src="<%=path %>/resource/js/plugins/jquery-1.11.1.min.js"></script>
 <script src="<%=path %>/resource/js/plugins/layer/layer.js"></script>
 <script src="<%=path %>/resource/js/plugins/validate/jquery.validate.min.js"></script>
 <script src="<%=path %>/resource/js/plugins/validate/card.js"></script>
 <script type="text/javascript">
 getAddress(33007);
 	$(function(){
 		sercahUser();          
 		/* 验证 */
 		var searchReg = {
 	        rules:{},
 	        msg:{}
 	    };
 		searchReg.rules[$("#search").attr('name')] = {
 	    		sensitiveCharacter: true,
 	    		specialCharValidate: true
 	        };
 		searchReg.msg[$("#search").attr('name')] = {
 	        };
 		$("#search-form").validate({
 	        rules:searchReg.rules,
 	        errorPlacement: function(error,element){
 	        	layer.tips($(error).html(), '#search', {
					tips: [4, '#f25d1c']
				});
 	        },
 	        onfocusout: function(element) { $(element).valid(); },
 	        messages:searchReg.msg		    	
 	    });
 		$(".btn-primary").click(function(){
			if(!$("#search-form").valid()) {
				return;
			}
 			sercahUserAndOrg();
 		});
 		$(document).on("click",".deleteUser",function(){
 			deleteUser($(this).attr("uid"));
 		}); 
 		$(document).on("click",".updateUser",function(){
 			var url = "<%=path%>/user/updateuser";
			$("body").load(url, {'uid': $(this).attr("uid"), 'username': $(this).attr("name")});
 		});
 		function sercahUser(){
			var url = "<%=path%>/user/query";
			$.ajax({
				 type:"get",
				 url:url,
				 data: {},
				 dataType:'html',
				 async: false,
				 success:function(data){
					 $("tbody").html(data);
				 }
			});
 		}
 		
 		
 		function sercahUserAndOrg(){
 			var userName=$("#search").val();
 			var orgId=$("#input6").val();
			var url = "<%=path%>/user/query";
			$.ajax({
				 type:"get",
				 url:url,
				 data: {"orgId" : orgId,"userName" :userName},
				 dataType:'html',
				 async: false,
				 success:function(data){
					 $("tbody").html(data);
				 }
			});
 		}
 		
 		function deleteUser(id){
 			params = {'uId' : id};
			var url = "<%=path%>/user/deleteUser";
			$.ajax({
				 type:"get",
				 url:url,
				 data: params,
				 dataType:'json',
				 async: false,
				 success:function(data){
					 if(data.code == '000000'){
						 layer.alert('删除成功');
						 sercahUser();
					 }else if(data.code == '000001'){
						 layer.alert('删除失败');
					 }else{
						 layer.alert('操作失败');
					 }
				 }
			});
 		}
 	});
 	
 	
 	function getAddress(parentId){
		var url = "<%=path%>/comm/resultAddress";
		$.ajax({
			type:"get",
			url:url,
			data:{"parentId" : parentId},
			dataType:'json',
			async: false,
			success:function(data){
				if(data.code=="000000"){
				    var list=data.list;
				    var len=list.length;
				    var html = [];
				    html.push('<option selected="selected" value="" class="local-selector1">成都市</option>');
				    for(var i=0;i<len;i++){
				    	html.push('<option value="'+list[i].id+'" class=local-selector1>'+list[i].name+'</option>');
				    }
				    $("#input6").append(html.join(''));
				  
				}else{
				    layer.alert('返回地址列表失败');
				}
			}
		});
	}
 	
 </script>
</body>    

</html>