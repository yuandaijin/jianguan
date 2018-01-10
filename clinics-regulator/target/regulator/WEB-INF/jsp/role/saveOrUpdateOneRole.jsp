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
	<form action="#" id="roleEdit">
	<div class="g-vertical-center m-role">
        <input type="hidden" id="roleId" value="${rolebean.id}">
        <input type="hidden" id="menus" value="${menus}">
        <div class="g-row f-clear">
          <div class="w100 f-fl">
            <label for="input10"><span style="color:red;">*</span>角色名称 :</label>
            <input type="text" class="form-control" id="input10" name="input10" value="${rolebean.rolName}" specialCharValidate="true">
		    <em class="errortips"></em>
          </div>
        </div>

        <div class="g-row f-clear">
          <div class="w100 f-fl">
            <label class="f-vt" for="input11"><span style="color:red;"></span>角色描述：</label>
            <textarea class="form-control" name="input11" id="input11">${rolebean.describe}</textarea>
		    <em class="errortips"></em>
          </div>
        </div>  
        <div class="g-row f-clear">
          <div class="w100 f-fl">
            <label class="f-vt" for="treeview2">权限管理：</label>
            	<div id="treeview2" class="test treeview">
                    <ul class="list-group rootRole">
	                </ul>
          		</div>
          </div>          
		</div>
		<div class="form-inline" role="form">
		   <div class="">
		      <div class="col-sm-offset-9 col-sm-10">
		      	 <c:if test="${rolebean.id > 0}">
		         	<button type="submit" class="btn btn-primary" id="back">返回列表</button>
		         </c:if>
		         <button type="button" class="btn btn-primary" id="save">保存</button>
		      </div>
		   </div>
		</div>
	</div>
	</form>
</div>
    <!-- 全局js -->
    <script src="<%=path %>/resource/js/plugins/jquery-1.11.1.min.js"></script>
    <script src="<%=path %>/resource/js/plugins/bootstrap.min.js"></script>
    <script src="<%=path %>/resource/js/plugins/layer/layer.js"></script>
    <script src="<%=path %>/resource/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="<%=path %>/resource/js/plugins/validate/messages_zh.min.js"></script>
<script src="<%=path %>/resource/js/plugins/validate/card.js"></script>
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
				 dataType:'json',
				 async: false,
				 success:function(data){
					 var list= data.list;//主菜单
					 if(list != undefined){
							for(var i=0;i<list.length;i++){
								var dto=list[i];
								$(".rootRole").append('<li class="list-group-item active  node-treeview2 node-selected"  parentid ="'+dto.parentId+'" mid = "'+dto.mId+'" ><i class="glyphicon-plus"></i><input type="checkbox" class = "selectmenu" name="munu" value="'+dto.mId+'" parentid ="'+dto.parentId+'">'+dto.menuName+'</li>');
								var value=list[i].children;
								if( value != undefined){
									tree(value);
								};
							}
						}
						$('.rootRole li').each(function(){
							var _this=$(this)
							$('.rootRole li').each(function(){
								if($(this).attr('parentid') ==  _this.attr('mid')){
									$(this).hide()
								}
							})
						});
				 }
			});
			 if($("#roleId").val() != ""){
				 selectvalues();
			 }
		}
		function tree(list){
				for(var i=0;i<list.length;i++){
					var dto=list[i];
					$(".rootRole").append('<li class="list-group-item active  node-treeview2 node-selected"  parentid ="'+dto.parentId+'" mid = "'+dto.mId+'" ><i class="glyphicon-plus"></i><input type="checkbox" class = "selectmenu" name="munu" value="'+dto.mId+'" parentid ="'+dto.parentId+'">'+dto.menuName+'</li>');
					if(dto.children != undefined){
						tree(dto.children);
					}
				}
		}
	//选中或取消
	$('body').on("click",".rootRole li input",function(){
	var totalId = $(this).parent().attr('mid');
	var parentId = $(this).parent().attr('parentid');
	var chooseUp;
	var chooseDown = [];
	if($(this).is(":checked")){
	sureChecked(totalId,parentId);
	sureCheckedUp(totalId,parentId)
	}
	else{
	cancelChecked(totalId,parentId);
	cancelCheckedUp(totalId,parentId);
	}
	});
	//取消选中
	function cancelChecked(midId,parentId){
	$('.rootRole li').each(function(){
	var _this=$(this);
	if(_this.attr('parentid') == midId){//给下级取消选中
	_this.children('input').removeAttr('checked');
	var mid = _this.attr('mid');
	var parentid = _this.attr('parentid');
	cancelChecked(mid,parentid);
	}
	});
	}
	function cancelCheckedUp(midId,parentId){
	var isChecked = false;
	$('.rootRole li').each(function(){
	var _this=$(this);
	if(_this.attr('parentid') == parentId){//下级全部取消，上级也取消
	if($(this).children('input').prop('checked') == true){
	isChecked = true;
	}
	}
	});
	if(isChecked == false){
	$('.rootRole li').each(function(){
	var _this=$(this);
	if(_this.attr('mid') == parentId){//下级全部取消，上级也取消
	_this.children('input').removeAttr('checked');
	var mid = _this.attr('mid');
	var parentid = _this.attr('parentid');
	cancelCheckedUp(mid,parentid);
	}
	});
	}
	}
	//选中
	function sureChecked(mid,parentId){
	var midDown,parentIdDown;
	$('.rootRole li').each(function(){
	var _this=$(this);
	if(_this.attr('parentid') == mid){//给下级加选中
	_this.find('input').prop("checked",'checked');
	midDown= _this.attr('mid');
	parentIdDown = _this.attr('parentid');
	sureChecked(midDown,parentIdDown);
	}
	});
	}
	function sureCheckedUp(mid,parentId){
	var midDown,parentIdDown;
	$('.rootRole li').each(function(){
	var _this=$(this);
	if(_this.attr('mid') == parentId){//给上级加选中
	_this.find('input').prop("checked",'checked');
	midDown= _this.attr('mid');
	parentIdDown = _this.attr('parentid');
	sureCheckedUp(midDown,parentIdDown);
	}
	});
	}
	//点击图标收起或放开列表
	$(document).on("click",".rootRole li i",function(){
	var _this = $(this);
	var totalId = _this.parent().attr('mid');
	var parentId = _this.parent().attr('parentid');
	if(_this.hasClass("glyphicon-plus")){
	_this.removeClass("glyphicon-plus").addClass("glyphicon-minus");
	$('.rootRole li').each(function(){
	if($(this).attr('parentid') == totalId){
	$(this).show();
	if(_this.parents('li')[0].style.marginLeft == '60px'){
	$(this)[0].style.marginLeft = '120px'
	}else if(_this.parents('li')[0].style.marginLeft == '120px'){
	$(this)[0].style.marginLeft = '180px'
	}else if(_this.parents('li')[0].style.marginLeft == '180px'){
	$(this)[0].style.marginLeft = '260px'
	}else if(_this.parents('li')[0].style.marginLeft == '260px'){
	$(this)[0].style.marginLeft = '340px'
	}else{
	$(this)[0].style.marginLeft='60px'
	}
	}
	})
	}
	else{
	_this.removeClass("glyphicon-minus").addClass("glyphicon-plus");
	liHide(totalId);
	}
	});
	function liHide(mid){
	$('.rootRole li').each(function(){
	if($(this).attr('parentid') == mid){
	$(this).children('i').removeClass("glyphicon-minus").addClass("glyphicon-plus");
	$(this).hide();
	var hideMid = $(this).attr('mid');
	liHide(hideMid);
	}
	})
	}
		
// 		$(document).on("click",".rootRole .icon",function(){
// 			var menuList = $(this).parent(".list-group-item");
// 			var mid = $(menuList).attr('mid');  // 当前目录id 
// 			var level = $(menuList).attr('level'); // 当前目录级别 
// 			var parentid = $(menuList).attr('parentid'); // 当前目录父目录id 
// 			var children = 0;
// 			var addlevel = 1 + Number(level);
// 			$(".rootRole li[level = '" + addlevel + "'][parentid = '" + mid + "']").each(function(index){
// 				children += 1;
// 			});
// 			if(children == 0){
//     			if($(this).find("i").hasClass("glyphicon-plus")){
//     				$(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus"); // 样式改为"-"
//     				// 隐藏与当前目录同父目录id的子目录(去除当前目录)
//     				$(".rootRole li[parentid = '" + parentid + "'][level = '" + level + "'][mid != '" + mid + "']").each(function(index){
//     					$(this).find("i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+" 
//     					hideids($(this).attr('mid'), (Number($(this).attr('level')) + 1));
//     				});
//     				getrolemenu(".list-group-item[mid = '" + $(menuList).attr("mid") + "']", $(menuList).attr("mid"), $(menuList).attr("level"));
//     			} else {
//     				$(this).find("i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+" 
//     			}
// 			} else {
// 				if($(this).find("i").hasClass("glyphicon-plus")){ // 点击 "+" 
// 					$(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus"); // 样式改为"-" 
// 					// 显示当前目录的子节点 
// 					$(".rootRole li[level = '" + addlevel + "'][parentid = '" + mid + "']").each(function(index){
// 						$(this).find("span i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+"
// 						$(this).show();
// 					});
// 					// 隐藏与当前目录同父目录id的子目录(去除当前目录) 
// 					$(".rootRole li[level = '" + level + "'][parentid = '" + parentid + "'][mid != '" + mid + "']").each(function(index){
// 						$(this).find("span i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+"
// 	    					hideids($(this).attr('mid'), (Number($(this).attr('level')) + 1));
// 	    			});
// 				} else if($(this).find("i").hasClass("glyphicon-minus")) { // 点击"-" 
// 					$(this).find("i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+"
// 					$(".rootRole li[parentid = '" + mid + "'][level = '" + addlevel + "']").each(function(index){
// 						$(this).find("span i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+"
// 	    					$(this).hide();
// 						hideids($(this).attr('mid'), (Number($(this).attr('level')) + 1));
// 	    			});
// 				}
// 			}
// 			if($(this).siblings(".selectmenu").prop("checked")) {
// 				$(".rootRole li[parentid = '" + mid + "']").find(".selectmenu").prop("checked", true);
// 			}
//  		});
// 		//递归隐藏 
// 		function hideids(id, level){
// 			$(".rootRole li[level = '" + level + "'][parentid = '" + id + "']").each(function(index){
// 				$(this).find("span i").removeClass("glyphicon-minus").addClass("glyphicon-plus"); // 样式改为"+" 
// 					$(this).hide();
// 					hideids($(this).attr("mid"), (Number($(this).attr("level")) + 1));
// 			});
// 		}
		// 复选框选中 
// 		$(document).on("click",".rootRole .selectmenu",function(){
// 			var result = $(this).is(":checked");
// 			$(this).prop('checked',result);
// 			var mid = $(this).val();  // 当前目录id 
// 			var level = $(this).attr('level'); // 当前目录级别 
// 			var parentid = $(this).attr('parentid'); // 当前目录父目录id 
// 			var children = 0;
// 			var addlevel = Number(level) + 1; // 下一级别 
// 			var prelevel = Number(level) - 1; // 上一级别 
// 			$(".rootRole li[level = '" + addlevel + "'][parentid = '" + mid + "']").each(function(index){
// 				children += 1;
// 			});
// 			//递归选中儿子 
// 			if(children > 0){
// 				selectchildmenu(mid, addlevel, result);
// 			}
// 			//递归选中父亲 
// 			if(result && prelevel >= 0){
// 				selectparentmenu(parentid, prelevel, result);
// 			}
// 			// 如果同一级一个未选中 
// 			if($(".rootRole .selectmenu[level = '" + level + "'][parentid = '" + parentid + "']:checked").length == 0){
// 				$(".rootRole .selectmenu[level = '" + prelevel + "'][value = '" + parentid + "']").each(function(index){
//     				$(this).prop('checked',false);
// 				});
// 				// selectparentmenu(parentid, prelevel, false);
// 			}
// 		});
		
// 		function selectchildmenu(id, level, result){
// 			$(".rootRole .selectmenu[level = '" + level + "'][parentid = '" + id + "']").each(function(index){
// 				$(this).prop('checked',result);
// 				selectchildmenu($(this).val(), (Number($(this).attr("level")) + 1), result);
// 			});
// 		}
		
// 		function selectparentmenu(id, level, result){
// 			$(".rootRole .selectmenu[level = '" + level + "'][value = '" + id + "']").each(function(index){
// 				$(this).prop('checked',result);
// 				if((Number($(this).attr("level")) - 1) >= 0 && result){// 只递归选中父亲，不递归取消父亲 
// 					selectparentmenu($(this).attr("parentid"), (Number($(this).attr("level")) - 1), result);
// 				}
// 			});
// 		}

		/* 验证 */
		var roleEdit = {
		    rules:{},
		    msg:{}
		};
		
		/*添加角色：*/
		//角色名称
		roleEdit.rules[$("#input10").attr('name')] = {
	    		sensitiveCharacter:true,
		    	specialCharValidate: true,
		    	required:true
	        };
		roleEdit.msg[$("#input10").attr('name')] = {
		    	sensitiveCharacter:"请不要输入敏感字符",
		    	specialCharValidate:"请不要输入特殊字符",
				required:"必填"
	        };
		
// 		//角色描述
// 		roleEdit.rules[$("#input11").attr('name')] = {
// 		    	required:true,
// 	    		sensitiveCharacter:true,
// 				specialCharValidateTextarea: true
// 	        };
// 		roleEdit.msg[$("#input11").attr('name')] = {
// 		    	required: '必填',
// 		    	sensitiveCharacter:'请不要输入敏感字符',
// 				specialCharValidateTextarea:'请不要使用  @,$,&....等特殊符号'
// 	        };
		$("#roleEdit").validate({
	        rules:roleEdit.rules,
	        errorPlacement: function(error,element){
	            $(element).siblings('.errortips').html(error);
	        },
	        onfocusout: function(element) { $(element).valid(); },
	        messages:roleEdit.msg		    	
	    });
		
		$("#save").click(function(){
			if(!$('#roleEdit').valid()) {
				return false;
			}
			if($("#roleId").val() != ""){
				updatetosaveRole();
			 }else{
				 saveRole();
			 }
		});
		
		$("#back").click(function(){
			window.location = "<%=path %>/role/queryrole";
		});
		
		function updatetosaveRole(){
			var allmenuids = "";
			var selectmenuids = "";
			$(".rootRole input").each(function(index){
				allmenuids += $(this).val() + ',';
				if($(this).is(":checked")){
					selectmenuids += $(this).val() + ',';
				}
			});
			if(allmenuids.length > 0){ //如果获取到		
				allmenuids = allmenuids.substring(0, allmenuids.length - 1); //把最后一个逗号去掉 
			}
			if(selectmenuids.length > 0){ //如果获取到		
				selectmenuids = selectmenuids.substring(0, selectmenuids.length - 1); //把最后一个逗号去掉 
			}
			var url = '<%=path%>/role/updatetosaveRole';
			var params = {'roleId' : $("#roleId").val(), 'roleName' : $("#input10").val(), 'describe' :  $("#input11").val(), 'delMenus' : allmenuids, 'addMenus' : selectmenuids};
			$.ajax({
				 type:"post",
				 url:url,
				 data: params,
				 dataType:'json',
				 async: false,
				 success:function(data){
					 if(data.code == '000000'){
						 layer.alert('修改成功');
					 } else if (data.code == '100001'){
						 layer.alert('角色名重复');
					 }else{
						 layer.alert('修改失败');
					 }
				 }
			});
		}
		
		function saveRole(){
			var menuids = "";
			$(".rootRole input:checked").each(function(index){
				menuids += $(this).val() + ',';
			});
			if(menuids.length > 0){ //如果获取到		
				menuids = menuids.substring(0, menuids.length - 1); //把最后一个逗号去掉 
			}
			var url ='<%=path%>/role/saveRole';
			var params = {'roleName' : $("#input10").val(), 'describe' :  $("#input11").val(), 'menuId' : menuids};
			$.ajax({
				 type:"post",
				 url:url,
				 data: params,
				 dataType:'json',
				 async: false,
				 success:function(data){
					 if(data.code == '000000'){
						 layer.alert('增加成功');
					 } else if(data.code == '100001'){
						 layer.alert('角色名重复');
					 }else{
						 layer.alert('增加失败');
					 }
				 }
			});
		}
		
		function selectvalues(){
			var menus = $("#menus").val().split(","); // 在每个逗号(,)处进行分解 
			$(".rootRole>li").each(function(index){
				for(var i=0; i<menus.length;i++){
					if($(this).children(".selectmenu").val() == menus[i]){
						$(this).children('input').prop('checked','checked');
					}
				}
			});
		}
		
    });
    </script>
</body>    

</html>