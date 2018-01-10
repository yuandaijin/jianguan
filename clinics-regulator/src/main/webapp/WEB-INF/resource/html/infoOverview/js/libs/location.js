!function ($) {
	/*
	*
	*用法： [url:接口地址,id:第一个input框id值,name第一个input框name值
			]
	*			    $('#local-selector').location([
		                                   {url:comm.path+"/comm/district/all",id:'id1',name:'name1',strId:'id11',strName:'name11'},
		                                   {url:comm.path+"/comm/district",id:'id2',name:'name2',strId:'id12',strName:'name12'},
		                                   {url:comm.path+"/comm/district",id:'id3',name:'name3',strId:'id13',strName:'name13'},
		                                   {url:comm.path+"/comm/district",id:'id4',name:'name4',strId:'id14',strName:'name14'},
		                                   ])
	*/
	/*
	*	tabs弹框初始化
	*/
	function initTabs(opts,_self){
		
		
		
		var tabs = '',tabsLi = '';
		if( !(opts instanceof Array) && (opts.length<1)){
			return false;
		}
		//遍历opts，创建input便于后台操作，统计并创建tab框
		var showStr = '';
		var showStrAry = [];
		var showCodeAry = [];
		for(var a in opts){
			if( (opts[a].id) && ($.trim(opts[a].id) !='' ) ){
				if(_self.attr(opts[a].name)) {
					showCodeAry.push(_self.attr(opts[a].id));
				}
				$(_self).before('<input class="data-text" type="text" id="'+opts[a].id+'" name="'+opts[a].name+'" value="'+ (_self.attr(opts[a].id)?_self.attr(opts[a].id):0) +'" style="width:0px;height:1px;padding:0;margin:0;border:0;"></input>')
			}
			if( (opts[a].strId) && ($.trim(opts[a].strId) !='' ) ){
				if(_self.attr(opts[a].strId)) {
					showStrAry.push(_self.attr(opts[a].strId));
				}
				showStr += _self.attr(opts[a].strId)?_self.attr(opts[a].strId):'';
				$(_self).before('<input class="data-code" type="text" id="'+opts[a].strId+'" name="'+opts[a].strName+'" value="'+ (_self.attr(opts[a].strId)?_self.attr(opts[a].strId):0) +'" style="width:0px;height:1px;padding:0;margin:0;border:0;"></input>')
			}
			if(a>0){
				tabsLi += '<li data-index="'+a+'" data-widget="tab-item" style="display:none;"><a href="#none" class="" style="margin-left:0;"><em>请选择</em><i></i></a></li>';
			}
			if(!opts[parseInt(a)+1])
				var dataUrl = '';
			else
				var dataUrl = opts[parseInt(a)+1].url;
			tabs += '<div data-area="'+a+'" data-widget="tab-content" data-url="'+dataUrl+'" class="stock-'+a+' mc"></div>';
		}
		//初始级联弹框
		initHtml = '<div class="content"><div data-widget="tabs" class="m JD-stock">'
										+'<div class="mt">'
										+'    <ul class="tab">'
										+'        <li data-index="0" data-widget="tab-item" class="curr"><a href="#none" style="margin-left:0;" class="hover"><em>请选择</em><i></i></a></li>'
										+tabsLi
										+'    </ul>'
										+'    <div class="stock-line"></div>'
										+'</div>'
										+tabs
										+'</div></div>';
		var storeId = 'store-'+new Date().getTime();
//		$(_self).hide().before('<div id="'+storeId+'" class="store-selector"><div class="text"><div>--请选择--</div><b></b></div><div class="close"></div></div>');
		$(_self).hide().before('<div id="'+storeId+'" class="store-selector"><div class="text"><div>'+ (showStr?showStr:"--请选择--") +'</div><b></b></div><div class="close"></div></div>');
		$("#"+storeId).find(".text").after(initHtml);
		//点击div弹框tabs框
		$("#"+storeId).find(".text").off().on("click",function(){
				$(this).addClass('active');
				$("#"+storeId).addClass('hover');
				$("#"+storeId).find(".content,.JD-stock").show();
			return false;
		});

		//点击div .close关闭tabs框
		$("#"+storeId).find(".close").off().on('click',function(e){
			$("#"+storeId).find(".text").removeClass('active');
			$("#"+storeId).removeClass('hover');
			return false;
		});					

		//点击tabs标签，样式tabs标签和正文样式变化
		var tabsContainer = $("#"+storeId).find('.JD-stock .tab').on('click','a',function(){
			$(this).parent("li").nextAll().find("em").html("请选择");
			$(this).parent().removeClass('curr');
			$(this).parent().addClass('curr').show().nextAll('li').hide();
			$("#"+storeId).find('.mc').hide();				
			$("#"+storeId).find('.mc[data-area='+$(this).parent().data('index')+']').show();			
		});
		
		//点击正文，样式和数据变化
		$('#'+storeId).find('.content .mc').on('click','a',function(e){
//			e.stopPropagation();
			if($(this).parents('div').is($("#"+storeId).find('.mc').first())) {
				$(opts).each(function() {
					$('#' + this.id).val(0);
					$('#' + this.strId).val('');
				});
			}
			var _self = this;
			if((opts[$(e.delegateTarget).data('area')].id ) && ( $.trim(opts[$(e.delegateTarget).data('area')].id) != '' )){
				$('#'+opts[$(e.delegateTarget).data('area')].id).val($(this).data('value'));
			}else{
				$('#'+opts[opts.length-1].id).val($(this).data('value'));
			}
			if((opts[$(e.delegateTarget).data('area')].strId ) && ( $.trim(opts[$(e.delegateTarget).data('area')].strId) != '' )){
				$('#'+opts[$(e.delegateTarget).data('area')].strId).val($(this).html());
			}else{
				$('#'+opts[opts.length-1].strId).val($(this).html());
			}
			$("#"+storeId).find('.mt .tab').find('li[data-index='+$(e.delegateTarget).data('area')+']').find('em').html($(this).html());
			$("#"+storeId).find('.text div').html(function(n,html){
				if(html == "--请选择--"){
					return $(_self).html();
				}else{
					var _divText = '';
					for (var i = 0; i < parseInt($(e.delegateTarget).data('area'))+1; i++) {
						_divText += $("#"+storeId).find('.mt .tab').find('li[data-index='+i+']').find('a em').html();
					};
					return _divText;
				}
			});
			
			if($(e.delegateTarget).data('url') == ''){
				$("#"+storeId).find(".close").trigger('click');
//				return false;
			}
			console.log($(e.delegateTarget).data('url'),$(this).data('value'))
			request($(e.delegateTarget).data('url'),$(this).data('value'))
			.done(function(){
				var data = this.data;
				if(this.code == 1){
					var resData = filterResJson(data);
					var htmlData = changeJsonToHtml(resData);
					$("#"+storeId).find('.stock-'+$(e.delegateTarget).next().data('area')).show().html(htmlData).siblings('.mc').hide();
					$("#"+storeId).find('.mt .tab').find('li[data-index='+$(e.delegateTarget).data('area')+']').removeClass('curr').next('li').addClass('curr').show();
				}else{
					$("#"+storeId).find(".close").trigger('click');
				}
			});
		});
		request(opts[0].url,'',opts[0].param)
		.done(function(){
			var data = this.data;
			var resData = filterResJson(data);
			var htmlData = changeJsonToHtml(resData);
			$("#"+storeId).find('.stock-0').html(htmlData);
		});
		
		
		// 回显
		if(showStrAry.length) {
			var tabs = $("#"+storeId).find(".tab").find('li').removeClass("curr");
			var lis = $("#"+storeId).find(".mc").hide();
			for(var i = 0; i < showStrAry.length+1; i++) {
				$(tabs[i]).find("em").html(showStrAry[i]);
				$(tabs[i]).show();
				request(opts[1].url, showCodeAry[i])
				.done(function(){
					var data = this.data;
					var resData = filterResJson(data);
					var htmlData = changeJsonToHtml(resData);
					if(i < showStrAry.length) {
						$("#"+storeId).find('.stock-' + (i+1)).html(htmlData);
					}
					
				});
				if(i == showStrAry.length) {
					$(lis[i]).show()
					$(tabs[i]).show().addClass("curr");
				}
			}
			
		}
	}

    /* 
    	请求数据并操作样式
	*	parentId：请求接口参数
	*	url：请求地址
	*/
	function request(url,parentId,special){
		return $.ajax({
					type : "get",
					url : url,
					data: {'parent_id' : parentId},
					dataType : 'json',
					cache : true,
					async : false
				})
				.done(function(data){
					if(data.code !=	'000000'){
						this.code = -1;
					}else{
						if(special && special!=''){
							if(data[special].length == 0){
								this.code = -1;
							}else{
								this.data = data[special];
								this.code = 1;
							}
						}else{
							if(data.list.length == 0){
								this.code = -1;
							}else{
								this.data = data.list;
								this.code = 1;
							}
						}
					}
				})
				.fail(function(){
					this.code = -1;
				});
	}

	/*
	*	过滤拼装成需要的格式数据
		data：源数据
	*/
	function filterResJson(data){
		var result = [];
		for(var o in data){
			result[o] = result[o] || [];
			result[o] = {};
			result[o].id = data[o].id;
			result[o].name = data[o].name;
		}
		return result;
	}
	/*
	*拼装数据成html
	*data：源数据
	*/
	function changeJsonToHtml(data){
		var html = '<ul class="area-list">';
		for(o in data){
			html += '<li><a href="#none" data-value='+data[o].id+'>'+data[o].name+'</a></li>'
		}
		return (html+'</ul>');
	}

	$.fn.extend({
		location:function(opts, callback){
			initTabs(opts,this);
			//回调
			if(typeof callback == "function"){
				callback();		
			}

		}
	})
}(jQuery);






