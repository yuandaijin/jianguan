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
    <link href="<%=path %>/resource/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/style.min.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/function.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/index.css" rel="stylesheet">
    <link href="<%=path %>/resource/css/font/iconfont.css" rel="stylesheet"type="text/css"/>
</head>

<body class="fixed-sidebar full-height-layout gray-bg skin-1">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element" style="width: 128px;margin: 0 auto;background-color:white;border-radius: 50%;">
                            <span><img alt="image" width="128px" height="128px" class="img-circle" src="<%=path %>/resource/imgs/logo02.png" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
<%--                                <c:if test="${person != null}"> --%>
<%--                                <span class="block m-t-xs"><strong class="font-bold">${person.userName}</strong></span> --%>
<%--                                </c:if> --%>
                               <!--  <span class="text-muted text-xs block">超级管理员<b class="caret"></b></span> -->
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
                                </li>
                                <li><a class="J_menuItem" href="profile.html">个人资料</a>
                                </li>
                                <li><a class="J_menuItem" href="contacts.html">联系我们</a>
                                </li>
                                <li><a class="J_menuItem" href="mailbox.html">信箱</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="login.html">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">
                        </div>
                    </li>
                    <c:if test="${list != null}">
                    <c:forEach items="${list}" var="Menu" step="1" varStatus="num">
                    <li>
                        <a href="javascript:void(0);" huatuo_url="${Menu.menuUrl}" parentId="${Menu.parentId}" mId="${Menu.mId}" class="toOperate" level="0"><i class="fa fa-edit"></i> <span class="nav-label">${Menu.menuName}</span><span class="fa arrow"></span></a>
                    </li>
                    </c:forEach>
                    </c:if>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                     <h3 class="navbar-brand">欢迎使用个体诊所监管后台管理系统</h3>
                    </div>
                    <div class="divclass">
                    	<ul class="userinfo" baseurl="<%=path %>"  >
							      <li><i class="Hui-iconfont Hui-iconfont-user" ></i>
							      <a class="J_menuItem" id="personInfo" href="/user/personInfo" huatuo_url="/user/personInfo" parentid="8" mid="11" level="2" data-index="4">${person.userName}
							      </a>
							     </li> 
						</ul>
                    <span class="xtsz"><i class="Hui-iconfont Hui-iconfont-system" ></i><a>系统设置</a></span>
                    </div>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight right"><i class="fa fa-forward"></i>
                </button>
                <button class="roll-nav roll-right dropdown J_tabClose"><span class="dropdown-toggle" data-toggle="dropdown">关闭操作<span class="caret"></span></span>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </button>
                <a href=javascript:void(0); id="allExit" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="" frameborder="0" data-id="index_v1.html" style="background-color:white" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">Copyright ©2016 Ruthless<a href="/" target="_blank"></a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>

    <!-- 全局js -->
    <script src="<%=path %>/resource/js/plugins/jquery-1.11.1.min.js"></script>
    <script src="<%=path %>/resource/js/plugins/bootstrap.min.js"></script>
    <script src="<%=path %>/resource/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=path %>/resource/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
 	<script src="<%=path %>/resource/js/plugins/layer/layer.js"></script>

    <!-- 自定义js -->
    <script src="<%=path %>/resource/js/hplus.min.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/js/contabs.min.js"></script>
	<script type="text/javascript">
		$(function(){
			//退出 
			$('#allExit').click(function(){
				var url = "<%=path %>/user/login/out";
				$.ajax({
					 type:"get",
					 url:url,
					 data: null,
					 dataType:'json',
					 cache : false,
					 async: false,
					 success:function(data){
						window.parent.location="<%=path%>/user";
					 }
				});
			})
			$(".toOperate").parent().delegate('a', 'click', function(){
				initToOperate($(this));
			}); 
			 $('.toOperate[level="0"]').trigger('click');
			 
		});
		function initToOperate($this){
			
// 				
// 				$(this).parent().hide();
				/* var mId = $(this).attr("mId");
				$(this).parent().parent().find('.toOperate > ul').not($('[mId="'+ mId +'"]')).hide(); */
				if($this.attr("huatuo_url") == "#" || $this.attr("huatuo_url") == ""){
					if($this.parent().has('ul').length == 0){
						serch($this.attr("mId"),$this.attr("level"));
					}
					
				} else {
					// alert(11);
					//getUrl($(this).attr("huatuo_url"));
					//return false;
					//alert('有url');
				//	return false;
				}
				$this.triggerHandler('click');
				initContabs();
				//   $(this).parents('ul').find('li ul').hide();
// 				$(this).show();
				//  $(this).find('ul').show();
				//$(this).parent().show();
						
		}
		function serch(id,level){
			var url = '<%=path%>/user/menu/list';
			var params = {'m_id' : id, 'level' : level};
			$.ajax({
				 type:"get",
				 url:url,
				 data: params,
				 dataType:'html',
				 async: false,
				 success:function(data){
					if(data.indexOf("baseUrl") == -1) {
						window.top.location = '<%=path%>/user';
						return false;
					}
					 var ulClass;
					 (level == 0) && (ulClass = "nav-second-level");
					 (level == 1) && (ulClass = "nav-third-level");
					 var dataStr = data.substring(0,data.indexOf('class="'))+data.substring(data.indexOf('class="'),data.indexOf('nav'))+ulClass+" "+data.substring(data.indexOf('nav'));
					 $(".toOperate[mId='"+ id +"']").parent().append(dataStr);
					 $('#side-menu').metisMenu();
				 }
			});
		}
		
		window.onbeforeunload = function() {    
			var url = "<%=path %>/user/updateUserDate";
			$.ajax({
				 type:"get",
				 url:url,
				 data: null,
				 dataType:'json',
				 cache : false,
				 async: false,
				 success:function(data){
					 if(data.code =='100001'){
						 layer.alert('修改失败');
					 }
				 }
			});
		}
	</script>
</body>

</html>