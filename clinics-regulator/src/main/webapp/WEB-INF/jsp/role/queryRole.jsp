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
	<form class="navbar-form pull-right" role="search">
		<div class="form-group">
		  <input type="text" class="form-control" placeholder="角色名">
		</div>
		<button type="button" class="btn btn-primary">搜索</button>
	</form>
</nav>

<div class="g-bd">
    <div class="table-responsive">
        <table class="table table-striped s-table">
            <thead>
                <tr>
                    <!-- <th><input id="selAll" type="checkbox" class="i-checks" name="input[]"><label for="selAll">全选</label></th> -->
                    <th>角色名</th>
                    <th>角色描述</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
	            <%-- <c:forEach items="${personList}" var="user" step="1">
	                <tr>
	                    <td>
	                        <input type="checkbox" class="i-checks" name="input[]">
	                    </td>
	                    <td>${user.userName}</td>
	                    <td>${user.createTime}</td>
	                    <td><a href="javascript:void(0);">删除</a><span>
	                    </td>
	                </tr>
	            </c:forEach> --%>
            </tbody>
        </table>
    </div>
</div>
 <script src="<%=path %>/resource/js/plugins/jquery-1.11.1.min.js"></script>
 <script src="<%=path %>/resource/js/plugins/layer/layer.js"></script>
 <script type="text/javascript">
 	$(function(){
 		sercahRole("");
 		//查询角色 
 		$(".btn-primary").click(function(){
 			sercahRole($.trim($(".form-control").val()));
 		});
 		//删除角色 
 		$(document).on("click",".deleteRole",function(){
 			deleteRole($(this).attr("roleid"));
 		}); 
 		//修改角色 
 		$(document).on("click",".updateRole",function(){
 			var url = "<%=path%>/role/updateonerole";
			$("body").load(url, {'roleId': $(this).attr("roleid")});
 		});
 		//查询角色 
 		function sercahRole(name){
 			var params = {};
 			if(name != ""){
 				params = {'roleName' : name};
 			}
			var url = "<%=path%>/role/query";
			$.ajax({
				 type:"get",
				 url:url,
				 data: params,
				 dataType:'html',
				 async: false,
				 success:function(data){
					 $("tbody").html(data);
				 }
			});
 		}
 		
 		//删除角色 
 		function deleteRole(id){
 			params = {'roleId' : id};
			var url = "<%=path%>/role/deleteonerole";
			$.ajax({
				 type:"get",
				 url:url,
				 data: params,
				 dataType:'json',
				 async: false,
				 success:function(data){
					 if(data.code == '000000'){
						 layer.alert('删除成功');
						 sercahRole('');
					 } else {
						 layer.alert('该角色还有其它用户使用，暂无法删除');
					 }
				 }
			});
 		}
 	});
 </script>
</body>    

</html>