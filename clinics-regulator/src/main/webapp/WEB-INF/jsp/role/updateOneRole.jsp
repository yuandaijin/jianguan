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
    <link href="<%=path %>/resource/css/style.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/reset.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/function.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/employeeList.css" rel="stylesheet">
</head>

<body>

<div class="g-bd">
	<div class="g-vertical-center m-role">
        <input type="hidden" id="roleId" value="${rolebean.id}">
        <input type="hidden" id="menus" value="${menus}">
        <div class="g-row f-clear">
          <div class="w100 f-fl">
            <label for="input10">角色名称：</label>
            <input type="text" class="form-control" id="input10" value="${rolebean.rolName}">
          </div>
        </div>

        <div class="g-row f-clear">
          <div class="w100 f-fl">
            <label class="f-vt" for="input11">角色描述：</label>
            <textarea class="form-control" name="" id="input11">${rolebean.describe}</textarea>
          </div>
        </div>  
        <div class="g-row f-clear">
          <div class="w100 f-fl">
            <label class="f-vt" for="input11">权限管理：</label>
            	<div id="treeview2" class="test treeview">
                    <ul class="list-group rootRole">
	                <!-- <a class="list-group-item active" href="#">
	                    <input id="checkbox1" type="checkbox">权限一
	                </a>
	                <div class="list-group-item">
	                     <a class="list-group-item active" href="#">
	                        <label for="checkbox2"><input id="checkbox2" type="checkbox">权限一.1</label>
	                     </a>
	                    <div class="list-group-item">
		                    <a class="list-group-item" href="#">
		                       <label for="checkbox3"><input id="checkbox3" type="checkbox">权限一.1.1</label>
		                    </a>
	                	</div>
	                </div> -->
	                </ul>
          		</div>
        	</div>          
		</div>
		<div class="form-inline" role="form">
		   <div class="">
		      <div class="col-sm-offset-9 col-sm-10">
		         <button type="submit" class="btn btn-primary">保存</button>
		      </div>
		   </div>
		</div>
	</div>
</div>
    <!-- 全局js -->
    <script src="<%=path %>/resource/js/plugins/jquery-1.11.1.min.js"></script>
    <script src="<%=path %>/resource/js/plugins/bootstrap.min.js"></script>
    <script src="<%=path %>/resource/js/plugins/layer/layer.js"></script>
    <script type="text/javascript">
    	$(function(){
    		getrolemenu(".rootRole", 0 , 0);
    		
    		function getrolemenu(cname, mid, level){
     			params = {'m_parentId' : mid, 'level' : level};
    			var url = "<%=path%>/role/listmenu";
    			$.ajax({
    				 type:"get",
    				 url:url,
    				 data: params,
    				 dataType:'html',
    				 async: false,
    				 success:function(data){
    					 if(level == 0){
    					 	$(cname).html(data);
    					 } else {
    						 $(cname).after(data);
    					 }
    					 selectvalues(Number(level) + 1);
    				 }
    			});
    		}
    		
    		$(document).on("click",".rootRole .icon",function(){
    			var menuList = $(this).parent(".list-group-item");
    			var mid = $(menuList).attr('mid');  // 当前目录id 
    			var level = $(menuList).attr('level'); // 当前目录级别 
    			var parentid = $(menuList).attr('parentid'); // 当前目录父目录id 
    			var children = 0;
    			var addlevel = 1 + Number(level);
    			$(".rootRole li[level = '" + addlevel + "'][parentid = '" + mid + "']").each(function(index){
    				children += 1;
    			});
    			if(children == 0){
	    			if($(this).find("i").hasClass("glyphicon-plus")){
	    				$(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus"); // 样式改为"-"
	    				// 隐藏与当前目录同父目录id的子目录(去除当前目录)
	    				$(".rootRole li[parentid = '" + parentid + "'][level = '" + level + "'][mid != '" + mid + "']").each(function(index){
	    					$(this).find("i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+" 
	    					hideids($(this).attr('mid'), (Number($(this).attr('level')) + 1));
	    				});
	    				getrolemenu(".list-group-item[mid = '" + $(menuList).attr("mid") + "']", $(menuList).attr("mid"), $(menuList).attr("level"));
	    			} else {
	    				$(this).find("i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+" 
	    			}
    			} else {
    				if($(this).find("i").hasClass("glyphicon-plus")){ // 点击 "+" 
    					$(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus"); // 样式改为"-" 
    					// 显示当前目录的子节点 
    					$(".rootRole li[level = '" + addlevel + "'][parentid = '" + mid + "']").each(function(index){
    						$(this).find("span i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+"
    						$(this).show();
    					});
    					// 隐藏与当前目录同父目录id的子目录(去除当前目录) 
    					$(".rootRole li[level = '" + level + "'][parentid = '" + parentid + "'][mid != '" + mid + "']").each(function(index){
    						$(this).find("span i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+"
   	    					hideids($(this).attr('mid'), (Number($(this).attr('level')) + 1));
    	    			});
    				} else if($(this).find("i").hasClass("glyphicon-minus")) { // 点击"-" 
    					$(this).find("i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+"
    					$(".rootRole li[parentid = '" + mid + "'][level = '" + addlevel + "']").each(function(index){
    						$(this).find("span i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+"
   	    					$(this).hide();
    						hideids($(this).attr('mid'), (Number($(this).attr('level')) + 1));
    	    			});
    				}
    			}
     		});
    		// 递归隐藏 
    		function hideids(id, level){
    			$(".rootRole li[level = '" + level + "'][parentid = '" + id + "']").each(function(index){
    				$(this).find("span i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+" 
   					$(this).hide();
   					hideids($(this).attr("mid"), (Number($(this).attr("level")) + 1));
    			});
    		}
    		// 复选框选中 
    		$(document).on("click",".rootRole .selectmenu",function(){
    			var result = $(this).is(":checked");
    			$(this).prop('checked',result);
    			var mid = $(this).val();  // 当前目录id 
    			var level = $(this).attr('level'); // 当前目录级别 
    			var parentid = $(this).attr('parentid'); // 当前目录父目录id 
    			var children = 0;
    			var addlevel = Number(level) + 1; // 下一级别 
    			var prelevel = Number(level) - 1; // 上一级别 
    			$(".rootRole li[level = '" + addlevel + "'][parentid = '" + mid + "']").each(function(index){
    				children += 1;
    			});
    			//递归选中儿子 
    			if(children > 0){
    				selectchildmenu(mid, addlevel, result);
    			}
    			//递归选中父亲 
    			if(result && prelevel >= 0){
    				selectparentmenu(parentid, prelevel, result);
    			}
    			// 如果同一级一个未选中 
    			if($(".rootRole .selectmenu[level = '" + level + "'][parentid = '" + parentid + "']:checked").length == 0){
    				$(".rootRole .selectmenu[level = '" + prelevel + "'][value = '" + parentid + "']").each(function(index){
        				$(this).prop('checked',false);
    				});
    				// selectparentmenu(parentid, prelevel, false);
    			}
    		});
    		
    		function selectchildmenu(id, level, result){
    			$(".rootRole .selectmenu[level = '" + level + "'][parentid = '" + id + "']").each(function(index){
    				$(this).prop('checked',result);
    				selectchildmenu($(this).val(), (Number($(this).attr("level")) + 1), result);
    			});
    		}
    		
    		function selectparentmenu(id, level, result){
    			$(".rootRole .selectmenu[level = '" + level + "'][value = '" + id + "']").each(function(index){
    				$(this).prop('checked',result);
    				if((Number($(this).attr("level")) - 1) >= 0 && result){// 只递归选中父亲，不递归取消父亲 
    					selectparentmenu($(this).attr("parentid"), (Number($(this).attr("level")) - 1), result);
    				}
    			});
    		}

    		
    		$(".btn-primary").click(function(){
    			if($("#roleEdit").valid()) {
    				return false;
    			}
    			var allmenuids = "";
    			var selectmenuids = "";
    			$(".rootRole input").each(function(index){
    				allmenuids += $(this).val() + ',';
    				if($(this).is(":checked")){
    					selectmenuids += $(this).val() + ',';
    				}
    			});
    			/* $(".rootRole input:checked").each(function(index){
    				selectmenuids += $(this).val() + ',';
    			}); */
    			if(allmenuids.length > 0){ //如果获取到		
    				allmenuids = allmenuids.substring(0, allmenuids.length - 1); //把最后一个逗号去掉 
    			}
    			if(selectmenuids.length > 0){ //如果获取到		
    				selectmenuids = selectmenuids.substring(0, selectmenuids.length - 1); //把最后一个逗号去掉 
    			}
    			var url = '<%=path%>/role/updatetosaveRole';
				var params = {'roleId' : $("#roleId").val(), 'roleName' : $("#input10").val(), 'describe' :  $("#input11").val(), 'delMenus' : allmenuids, 'addMenus' : selectmenuids};
    			$.ajax({
    				 type:"get",
    				 url:url,
    				 data: params,
    				 dataType:'json',
    				 async: false,
    				 success:function(data){
    					 if(data.code == '000000'){
    						 layer.alert('修改成功');
    					 } else {
    						 layer.alert('该用户已经存在');
    					 }
    				 }
    			});
    		});
    		
    		function selectvalues(level){
    			var menus = $("#menus").val().split(","); // 在每个逗号(,)处进行分解 
    			$(".rootRole .selectmenu[level = '" + level + "']").each(function(index){
    				if(menus.indexOf($(this).val()) > -1){
    					$(this).prop('checked',true);
    				}
    			});
    		}
    	});
    </script>
</body>    

</html>