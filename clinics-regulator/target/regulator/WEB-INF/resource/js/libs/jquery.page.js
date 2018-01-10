//分页插件
/**
2014-08-05 ch
**/
(function($){
	var ms = {
		init:function(obj,args){
			return (function(){
				ms.fillHtml(obj,args);
				ms.bindEvent(obj,args);
			})();
		},
		//填充html
		fillHtml:function(obj,args){
			return (function(){
				obj.empty();
				//&lt;
				if(parseInt(args.current) > 1){
					obj.append('<a href="javascript:;" class="prevPage">&lt;</a>');
				}else{
					obj.remove('.prevPage');
					obj.append('<span class="disabled">&lt;</span>');
				}
				//中间页码
				if(parseInt(args.current) != 1 && parseInt(args.current) >= 4 && parseInt(args.pageCount) != 4){
					obj.append('<a href="javascript:;" class="tcdNumber">'+1+'</a>');
				}
				if(parseInt(args.current)-2 > 2 && parseInt(args.current) <= parseInt(args.pageCount) && parseInt(args.pageCount) > 5){
					obj.append('<span>...</span>');
				}
				var start = parseInt(args.current) -2,end = parseInt(args.current)+2;
				if((start > 1 && parseInt(args.current) < 4)||parseInt(args.current) == 1){
					end++;
				}
				if(parseInt(args.current) > parseInt(args.pageCount)-4 && parseInt(args.current) >= parseInt(args.pageCount)){
					start--;
				}
				for (;start <= end; start++) {
					if(start <= parseInt(args.pageCount) && start >= 1){
						if(start != parseInt(args.current)){
							obj.append('<a href="javascript:;" class="tcdNumber">'+ start +'</a>');
						}else{
							obj.append('<span class="current">'+ start +'</span>');
						}
					}
				}

				if(parseInt(args.current) + 2 < parseInt(args.pageCount) - 1 && parseInt(args.current) >= 1 && parseInt(args.pageCount) > 5){
				
					obj.append('<span>...</span>');
				}
				if(parseInt(args.current) != parseInt(args.pageCount) && parseInt(args.current) < parseInt(args.pageCount) -2  && parseInt(args.pageCount) != 4){
					obj.append('<a href="javascript:;" class="tcdNumber">'+args.pageCount+'</a>');
				}
				//&gt;
				if(parseInt(args.current) < parseInt(args.pageCount)){
					obj.append('<a href="javascript:;" class="nextPage">&gt;</a>');
				}else{
					obj.remove('.nextPage');
					obj.append('<span class="disabled">&gt;</span>');
				}
			})();
		},
		//绑定事件
		bindEvent:function(obj,args){
			return (function(){
				obj.off("click","a.tcdNumber").on("click","a.tcdNumber",function(){
					var current = parseInt($(this).text());
					ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current);
					
					}
				});
				//&lt;
				obj.off("click","a.prevPage").on("click","a.prevPage",function(){
					var current = parseInt(obj.children("span.current").text());
					ms.fillHtml(obj,{"current":current-1,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current-1);
					}
				});
				//&gt;
				obj.off("click","a.nextPage").on("click","a.nextPage",function(){
					var current = parseInt(obj.children("span.current").text());
					ms.fillHtml(obj,{"current":current+1,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current+1);
					}
				});
			})();
		}
	};
	$.fn.createPage = function(options){
		var args = $.extend({
			pageCount : 10,
			current : 1,
			backFn : function(){}
		},options);
		ms.init(this,args);
	};
})(jQuery);