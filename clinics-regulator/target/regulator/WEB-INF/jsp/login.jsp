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
    <link rel="stylesheet" href="<%=path %>/resource/css/animate.min.css">
    <link rel="stylesheet" href="<%=path %>/resource/css/cube-min.css">
    <link rel="stylesheet" href="<%=path %>/resource/css/function.css">
    <link rel="stylesheet" href="<%=path %>/resource/css/login.css">
    <!--[if lt IE 9]>
        <script src="<%=path %>/resource/js/plugins/html5shiv.js"></script>
    <![endif]-->
    <script type="text/javascript">
	    if(document.location.href != window.top.location) {
			window.top.location = '<%=path%>/user';
		}
    </script>
    <style>
    	#login-content .errortips label {
    		display: inline-block;
    		position: static;
    		padding-left: 15px;
			background: url(<%=path %>/resource/imgs/i-error.png) no-repeat 0 7px;
    	}
    	.m-ipt {
    		margin: 0;
    	}
		body{
			background: url(<%=path %>/resource/imgs/bg-login.png) no-repeat 100% 20%;
			background-size:100%;
		}
		.warningText{
		    color: #738294;
		    margin-top: 10px
		}
		.f-tac{
		    color: #f2f2f2;
		}
		
    </style>
</head>

<body>
    <div class="g-wrap">
        <div class="g-mn f-clear animated fadeInDown">
            <div class="f-fl g-mnl">
                <div class="m-imgtxt">
                    <div class="img"><img src="<%=path %>/resource/imgs/loginlogo.png" alt=""></div>
                    <h3>个体诊所后台管理系统</h3>
                </div>
            </div>
            <div class="f-fr g-mnr clear">
				<div id="login-background" >

				</div>
                <form class="f-fr loginForm" id="login-content" action="<%=path %>/user/login">
                    <div class="m-ipt" style="margin-top: 50px">
                        <input type="text" id="name" name="name" placeholder="请输入用户名">
                        <i class="i-username"></i>
                        <label for="">请输入用户名/手机号/邮箱</label>
                        <p class="tips"></p>
                        <span style="color:red;" class="errortips errortips-first"></span>
                    </div>
                    <div class="m-ipt">
                        <input type="password" id="pwd" name="pwd" placeholder="请输入密码">
                        <i class="i-passwd"></i>
                        <label for="">请输入密码</label>
                        <p class="tips"></p>
                        <span style="color:red;" class="errortips"></span>
                    </div>       
                    <div class="dib-box">
                        <div class="dib">
                            <a class="u-btn btn btn-primary " href="javascript:void(0);">登&nbsp;录</a>
                        </div>
                        <!-- <div class="ml60 m-checkbox dib">
                            <input id="remeber-info" type="checkbox">
                            <label for="remeber-info">记住我的登录信息</label>
                        </div> -->
                    </div>
					<div class="warningText">温馨提示：忘记密码请与管理员联系！</div>
				</form>
            </div>
        </div>
    </div>    
    <div class="g-ft">
        <p class="f-tac"> Copyright © 2016 华佗快线. All rights reserved</p>
    </div>
<%-- 


    <div class="g-mn f-clear">
        <div class="f-fl">
            <h3><img src="" alt="">华佗在线</h3>
            <h3>欢迎登录华佗在线后台管理系统</h3>
        </div>
        <form class="f-fl" id="login-content" action="<%=path %>/user/login" >
            <div class="m-ipt">
                <input type="text" id="name" name="name">
                <i></i>
                <label for="">请输入用户名/手机号/邮箱</label>
            </div>
            <div class="m-ipt">
                <input type="text" id="pwd" name="pwd">
                <i></i>
                <label for="">请输入密码</label>
            </div>       
            <div class="dib-box">
                <div class="dib">
                    <a class="u-btn btn btn-primary " href="javascript:void(0);">登　录</a>
                </div>
                <div class="ml60 m-checkbox dib">
                    <input id="remeber-info" type="checkbox">
                    <label for="remeber-info">记住我的登录信息</label>
                </div>
            </div> 
        </form>
    </div>
    <div class="g-ft">
        <p class="f-tac">Copyright ©2015 Ruthless</p>
    </div> --%>
    <script src="<%=path %>/resource/js/plugins/jquery-1.11.1.min.js"></script>
    <script src="<%=path %>/resource/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="<%=path %>/resource/js/plugins/validate/messages_zh.min.js"></script>    
	<script src="<%=path %>/resource/js/plugins/layer/layer.js"></script>
    <script src="<%=path %>/resource/js/plugins/jQuery.md5.js"></script>

    <script type="text/javascript">
    
    
	
		$(function(){
			/* 登陆验证 */
			var loginReg = {
		        rules:{},
		        msg:{}
		    };
			loginReg.rules[$("#name").attr('name')] = {
		    		required:true
		        };
			loginReg.msg[$("#name").attr('name')] = {
		    		required:'请输入用户名'
		        };
			loginReg.rules[$("#pwd").attr('name')] = {
		    		required:true
		        };
			loginReg.msg[$("#pwd").attr('name')] = {
		    		required:'请输入密码'
		        };
		    $("#login-content").validate({
		        rules: loginReg.rules,
		        errorPlacement: function(error,element){
		            $(element).siblings('.errortips').html(error);
		        },
		        onfocusout: function(element) { $(element).valid(); },
		        messages: loginReg.msg		    	
		    });
		    
		    //点击登陆
    		$(".dib").bind('click',function(){
    			if($('#login-content').valid()) {
            		login();
    			}
        	});
		    
     		//回车登陆
    		$(window).bind('keydown',function(e){
    			if(e.keyCode == 13) {
        			if($('#login-content').valid()) {
                		login();
        			}
    			}
        	});
    	});
    	function login(){
			var name = $("#name").val();
			var pwd = $.md5($("#pwd").val());
			var params = {'name' : name , 'pwd' : pwd};
			var url = $("#login-content").attr('action');
			$.ajax({
				 type:"post",
				 url:url,
				 data: params,
				 dataType:'json',
				 cache : false,
				 async: false,
				 success:function(data){
					 if(data.code == '000000'){ // 登陆成功 
						 window.location.href = '<%=path %>/web/person';
					 } else {
// 						 layer.alert('账号或者密码错误');
						 $("#login-content .errortips-first").html("<label>账号或者密码错误</label>");
						 return ;
					 }
				 }
			});
    	}
	</script>
</body>

</html>