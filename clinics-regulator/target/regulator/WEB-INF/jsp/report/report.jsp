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
		.navbar-forms{
 			padding-top:20px; 
 			padding-left:5px; 
		}
		tr.active>td{
			background-color:#DDDDDD !important;
		}
    </style>
</head>
<body>
<nav class="g-hd navbar navbar-default">
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
<div class="g-bd">
    <div class="table-responsive">
        <table class="table table-striped s-table">
            <thead>
                <tr>
                    <th>分类</th>
                    <th>样式</th>
                    <th>套打报表号</th>
                    <th>非套打报表号</th>
                    <th>说明</th>
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
 	$(function(){
 		sercahUser(); 
 		//删除
 		$(document).on("click",".delete",function(){
 			deleteReport();
 		});
 		
 		//编辑
 		$(document).on("click",".update",function(){
 			updateReport();
 		});
 		
 		//保存
 		$(document).on("click",".save",function(){
 			saveReport();
 		});
 		
 		
 		//查询
 		function sercahUser(){
			var url = "<%=path%>/report/queryReport";
			$.ajax({
				 type:"get",
				 url:url,
				 data: {},
				 dataType:'html',
				 async: false,
				 success:function(data){
					 $("tbody").html(data);
					 addclass();
				 }
			});
 		}
 		
 	function addclass(){
 		$(".table tr").click(function(){
	  		 $(this).addClass("active");
	  		 $(this).siblings().removeClass("active"); 
	  	     $(".btn").removeAttr("disabled");//将按钮可用	 
	  	     $(this).siblings().find(".checkOne,.checkTwo,.form-control").attr("disabled",true);
	 	});
 	}
 	
 	
 	function updateReport(){
 		var id=$('.table tr.active').attr('id');
 		$('.table tr.active').find(".form-control,.checkOne,.checkTwo").removeAttr("disabled");
 	}
 
 	function deleteReport(){
 		var id=$('.table tr.active').attr('id');
 		deleteUser(id);
 	}
 	
 	function deleteUser(id){
	 		layer.confirm('确定删除吗？', function(){  
	 			params = {'id' : id};
				var url = "<%=path%>/report/deleteReport";
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
							 $(".btn").attr("disabled",true);
						 }else if(data.code == '000001'){
							 layer.alert('删除失败');
						 }else{
							 layer.alert('操作失败');
						 }
					 }
				});
	 		});
		}
 	
 	function saveReport(){
 		var id=$('.table tr.active').attr('id');
 		var type=$('.table tr.active .type').text();
 		var checkOne=$('.table tr.active .checkOne').is(':checked');//判断是否选中
 		var print=null;
 		if(checkOne==true){
 		    print=0;
 		}else{
 			print=1;
 		}
 		var checkTwo=$('.table tr.active .checkTwo').is(':checked');//判断是否选中
 		var notPrint=null;
 		if(checkTwo==true){
 			notPrint=0;
 		}else{
 			notPrint=1;
 		}
 		var printName=$('.table tr.active #input1').val();
 		var notPrintName=$('.table tr.active #input2').val();
 		var memo=$('.table tr.active #input3').val();
 		var flg=$('.table tr.active #flg').val();
		var url = "<%=path%>/report/saveReport";
		var  params =JSON.stringify({"id" : id,"type" : type,"print" : print,"notPrint" : notPrint,"printName" : printName,"notPrintName" : notPrintName,"memo" : memo,"defaultType" : flg});
		$.ajax({
			 type:"post",
			 url:url,
			 data: params,
			 dataType:'json',
			 async: false,
			 contentType: "application/json; charset=utf-8", 
			 success:function(data){
				 if(data.code == '000000'){
					 layer.alert('保存成功');
					 $(".btn").attr("disabled",true);//将按钮置灰不可用
					 $('.table tr.active').find(".form-control,.checkOne,.checkTwo").attr("disabled",true);//文本框和checkbox不可修改
					 $('.table tr').removeClass("active");//移除样式
				 }else{
					 layer.alert('保存失败');
				 }
			 }
		});
 		
 	}
 	
 	});
 </script>
</body>    

</html>