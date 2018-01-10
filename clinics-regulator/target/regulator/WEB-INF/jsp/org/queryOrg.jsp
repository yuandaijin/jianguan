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
    <link rel="stylesheet" href="<%=path %>/resource/css/pageCode.css">
    <style>
		
    </style>
</head>
<body>
<nav class="g-hd navbar navbar-default">
	<form class="navbar-form pull-right" role="search">
		<div class="form-group">
		    <span style="color:red;">*</span><label for="local-selector">管辖区域：</label>
			<div class="form-group" id="org-selector-wrap">
			    <select class="form-control" id="local-selector1" name="provinceCode"></select>
			     <select class="form-control" id="local-selector2" name="cityCode"></select>
			     <select class="form-control" id="local-selector3" name="countyCode"></select>
			    <em class="errortips"></em>
			</div>
		</div>
		<button type="button"  class="btn btn-primary">搜索</button>
	</form>
</nav>

<div class="g-bd">
    <div class="table-responsive">
        <table class="table table-striped s-table">
            <thead>
                <tr>
                    <th>机构名称</th>
                    <th>机构地址</th>
                    <th>机构联系人</th>
                    <th>联系人电话</th>
                    <th>抗生素规则</th>
                    <th>机构管辖区域</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
<div class="tcdPageCode" total="" current="" id="drugPage"></div>
 <script src="<%=path %>/resource/js/plugins/jquery-1.11.1.min.js"></script>
 <script src="<%=path %>/resource/js/plugins/layer/layer.js"></script>
 <script src="<%=path %>/resource/js/libs/jquery.page.js"></script>
 <script type="text/javascript">
$(function(){
	//加载机构信息
	getAllOrg(null,null,null);
	//加载地址select框
	getAddress(-1,"local-selector1");
	$('#local-selector1').change(function(){
		$("#local-selector2").html("");
		if($("#local-selector1").val()!=''){
			getAddress($("#local-selector1").val(),"local-selector2");
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
				if(data.code=="000000"){
				    var list=data.list;
				    var len=list.length;
				    var html = [];
				    html.push('<option selected="selected" value="" class="local-selector1">--请选择--</option>');
				    for(var i=0;i<len;i++){
				            html.push('<option value="'+list[i].id+'" class=local-selector1>'+list[i].name+'</option>');
				    }
				    $("#"+name+"").append(html.join(''));
				}else{
				    layer.alert('返回地址列表失败');
				}
			}
		});
	}
	//搜索按钮触发事件	
	$(".btn-primary").click(function(){
		var province=$("#local-selector1 option:selected").text();
		var city=$("#local-selector2 option:selected").text();
		var county=$("#local-selector3 option:selected").text();
		if(province=='--请选择--'){
			province='';
		}
		if(city=='--请选择--'){
			city='';
		}
		if(county=='--请选择--'){
			county='';
		}
		getAllOrg(province,city,county);
	});
	function getAllOrg(province,city,county){
	
		var params = {'province' : province,'city' : city,'county' : county};
		var url = "<%=path%>/org/queryAllOrg";
		$.ajax({
			 type : "post",
			 url : url,
			 data :params,
			 dataType : 'html',
			 cache : false,
			 async : false,
			 success : function(data){
				 //将查询的结果显示到页面 
				 $("tbody").html(data);
			 }
		});
	}
	//删除操作
	$(document).on("click",".disable",function(){
			deleteUser($(this).attr("id"));
		}); 
	
	function deleteUser(id){
		params = {'id' : id};
		var url = "<%=path%>/org/deleteOrg";
		$.ajax({
			 type:"get",
			 url:url,
			 data: params,
			 dataType:'json',
			 async: false,
			 success:function(data){
				 if(data.code == '000000'){
					 layer.alert('删除成功');
					 getAllOrg(null,null,null);
				 }else if(data.code == '100001'){
					 layer.alert('删除失败');
				 }else{
					 layer.alert('操作失败');
				 }
			 }
		});
		}
	//修改机构信息	
	$(document).on("click",".updateOneOrg",function(){
			var url = "<%=path%>/org/updateOneOrg";
		$("body").load(url, {'id': $(this).attr("id")});
		});
		
	});
 </script>
</body>    

</html>