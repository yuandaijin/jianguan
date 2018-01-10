(function($) {
	$.fn.extend({
		timeChoose : function(options, callback) {
			var input = $(this);
      		
      		// 返回的对象
			var data = {
				getdata: [], //日期，时间片段、星期
				reservationId: 0,
				remainNumber: 0,
				obj: {},
				list: [],
				yCount : 0
			};

			if($(".time-choose-warp").length == 1) {return;}
			$('<div class="time-choose-warp"><div>').html('<div class="time-choose-title"><a class="tmchose-prev-btn disable">&lt;</a><span class="date-choosed"></span><span class="week"></span><a class="tmchose-next-btn">&gt;</a></div><table class="time-choose-content"><col class="col1"/><col class="col2"/><col class="col3"/><col class="col4" /><thead><tr><th></th><th>服务时段</th><th>余号数</th><th>是否预约</th></tr></thead><tbody><tr><th>上午</th><td colspan="3"><table class="inner am-inner"></table></td></tr><tr><th><label for="time-fragment2">下午<label></th><td colspan="3"><table class="inner pm-inner"></table></td></tr><tr><th><label for="time-fragment2">晚上<label></th><td colspan="3"><table class="inner night-inner"></table></td></tr></tbody></table>').css({
				top: input.offset().top + input.outerHeight() + 5,
				left: input.offset().left
			}).appendTo($("body"));

			var date1 = new Date();
			var num = 0;
			//  当日日期
			var nowDate = parseInt(date(num).join(""));
			//  最大日期
			var maxDate = parseInt(date(6).join(""));
			var nowDay = date1.getDay();
			// 设置当前星期
			getWeek(date, num);
			$(".time-choose-title .date-choosed").html(date(num).join("-"));
			$("a.tmchose-prev-btn").bind("click", function(e) {
				e.stopPropagation();
				var newDate = parseInt($(".date-choosed").html().split("-").join(""));
				if(newDate > nowDate && newDate <= maxDate) {
					// console.log(nowDate , newDate , maxDate, num)
					if(newDate > nowDate) {
						$(".time-choose-warp .tmchose-next-btn").removeClass("disable");
					}
					num--;
					$(".time-choose-warp .tmchose-prev-btn").removeClass("disable");

					getWeek(date, num);
					// 下限
					if(nowDate == date(num).join("")) {
						$(".time-choose-warp .tmchose-prev-btn").addClass("disable");
					}
					showServiceTime();
				}
			});
			$("a.tmchose-next-btn").bind("click", function(e) {
				e.stopPropagation();
				var newDate = parseInt($(".date-choosed").html().split("-").join(""));
				if(newDate >= nowDate && newDate < maxDate) {
					// console.log(nowDate , newDate , maxDate, num)
					if(newDate >= nowDate) {
						$(".time-choose-warp .tmchose-prev-btn").removeClass("disable");
					}
					num++;
					$(".time-choose-warp .tmchose-next-btn").removeClass("disable");

					getWeek(date, num);
					// 上限
					if(maxDate == date(num).join("")) {
						$(".time-choose-warp .tmchose-next-btn").addClass("disable");
					}
					showServiceTime();
				} 
			});
			$("table.inner").on("click", "tr", function() {
				if(!$(this).find("input").prop("disabled")) {
					$(this).find("input").prop({checked: true});
				} else {
					return;
				}
				data.getdata = [$(".date-choosed").html(), $(".week").html(), $(this).find(".service-time-title").html()];
				data.reservationId = $(this).find("input").attr("reservation-id");
				$(options.list).each(function() {
					if(this.reservationId ==  data.reservationId) {
						data.obj = this;
					}
				});
				data.yCount = $(this).find("input").attr("y-count");
				if(callback) {
					callback(data);
				}
				$(".time-choose-warp").empty().remove();
			});

			// 时间格式化
			function dateFormat(time) {
				return time < 10 ? (Array(2).join(0)+time).slice(-2) : time;
			}
			// 年月日
			function date(dayCount) {
				var date2 = new Date(date1);
				date2.setDate(date1.getDate() + dayCount);
				return [date2.getFullYear(), dateFormat(date2.getMonth() + 1), dateFormat(date2.getDate())];
				
			}
			// 获取星期
			function getWeek(date, num) {
				var dt = new Date((date(num)[0]), +date(num)[1]-1, +date(num)[2]);
				var weekDay = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
				$(".time-choose-warp .week").html(weekDay[dt.getDay()]).attr("week", dt.getDay());
				$(".time-choose-title .date-choosed").html(date(num).join("-"));
			}
			// 展示服务时间片段
			showServiceTime();
			function showServiceTime() {
				data.list = [];
				$(".am-inner").empty();
				$(".pm-inner").empty();
				$(".night-inner").empty();
				if(options.code == "000000") {
					for(var i = 0; i < options.list.length; i++) {
						if(options.list[i].week == $("div.time-choose-warp span.week").attr("week")){
							data.list.push(options.list[i]);
							if(parseInt(options.list[i].remainNumber) <= 0) {
							var tr = $('<tr class="disabled">').html('<td class="service-time-title">'+ options.list[i].serviceTime +'</td><td>'+ options.list[i].remainNumber +'</td><td><input name="service-time-check" remain-number = "'+ options.list[i].remainNumber +'" reservation-id="'+ options.list[i].reservationId +'" y-count="'+ options.list[i].yCount +'" type="radio" disabled="true" /></td>');

						} else {
							var tr = $('<tr class="usable">').html('<td class="service-time-title">'+ options.list[i].serviceTime +'</td><td>'+ options.list[i].remainNumber +'</td><td><input name="service-time-check" remain-number = "'+ options.list[i].remainNumber +'" reservation-id="'+ options.list[i].reservationId +'" y-count="'+ options.list[i].yCount +'" type="radio" /></td>');
						}
							switch(options.list[i].interval){
								case 1:
									tr.appendTo($(".am-inner"));
									continue;
								case 2:
									tr.appendTo($(".pm-inner"));
									continue;
								case 4:
									tr.appendTo($(".night-inner"));
									continue;
							}
						}
					}
				}
			}
		}
	});
}(jQuery));









