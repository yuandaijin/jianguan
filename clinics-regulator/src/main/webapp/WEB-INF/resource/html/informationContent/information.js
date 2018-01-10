var contentId;
var releaseTime;
//数据拼接
var packJson = {
	"contentId": "", //资讯ID
	"columnId": "", //所属栏目id
	"columnNamne": "", //所属栏目名称
	"channelId": "", //所属频道ID
	"channelName": "", //频道名称
	"mainTitle": "", //主标题	
	"subTitle": "", //副标题
	"sortNo": "", //排序号
	"isRecommend": "", //是否推荐
	"contentSource": "", //咨询来源
	"regionId": "", //地区ID
	"isRelease": "", //是否发布
	"releaseUser": "", //发布用户
	"releaseOrg": "", //发布机构
	"releaseOrgId": "", //发布机构ID
	"releaseTime": "", //发布时间
	"imgageUrl": "", //主题图URL
	"createTime": "", //创建时间
	"updateTime": "", //更新时间
	"createUserId": "", //创建人
	"contentDtl": "" //编辑器
};
//编辑器
var ue = UE.getEditor('container');
//地区控件
//$(function() {
//	//机构地区
//	$('#local-selector').location([{
//		url: path.path + "/comm/resultAddress/all",
//		id: 'provinceCode',
//		name: 'provinceCode',
//		strId: 'province',
//		strName: 'province',
//		param: 'provinces'
//	}, {
//		url: path.path + "comm/resultAddress",
//		id: 'cityCode',
//		name: 'cityCode',
//		strId: 'city',
//		strName: 'city'
//	}, {
//		url: path.path + "comm/resultAddress",
//		id: 'countyCode',
//		name: 'countyCode',
//		strId: 'county',
//		strName: 'county'
//	}, {
//		url: path.path + "/comm/district",
//		id: 'roadCode',
//		name: 'roadCode',
//		strId: 'road',
//		strName: 'road'
//	}, ]);
//});
//验证
var remoteDistance = {
	rules: {},
	msg: {}
};
remoteDistance.rules[$("#titlely").attr('name')] = {
	required: true,
};
remoteDistance.msg[$("#titlely").attr('name')] = {
	required: '此项不能为空',
};
//
//remoteDistance.rules[$("#subtitle").attr('name')] = {
//	required: true
//};
//remoteDistance.msg[$("#subtitle").attr('name')] = {
//	required: '此项不能为空',
//};
//
remoteDistance.rules[$("#sort").attr('name')] = {
	required: true,
	number: true
};
remoteDistance.msg[$("#sort").attr('name')] = {
	required: '此项不能为空',
	number: '只能为数字'
};
$("#setMenu").validate({
	rules: remoteDistance.rules,
	errorPlacement: function(error, element) {
		$(element).parent().siblings('.errortips').html(error);
		$(element).siblings('.errortips').html(error);
	},
	onfocusout: function(element) {
		$(element).valid();
		setTimeout(function() {
			$(element).valid()
		}, 500);
	},
	messages: remoteDistance.msg
});
////图片切割
//var imgType = 1;
//var qualificationImg = false;
//var t = 2500;
//var addFlag = true;
//var imgSizeError = "上传失败，请重新上传小于2M图片！";
//var imgNameError = "上传失败，文件名有特殊字符！";
//var imgFormat = "上传失败，请重新选择图片格式文件！";
//$(function() {
//	formevt();
//	$('#submit-avatar').parent('div').hide();
//	/**
//	 *头像上传
//	 */
//	function formevt() {
//		//头像
//		$("#imgFile").change(function() {
//			var ua = window.navigator.userAgent;
//			if(ua.indexOf("MSIE") >= 1) {
//
//			} else {
//				if($(this)[0].files[0].size > 1024 * 1024 * 2) {
//					layer.msg(imgSizeError, {
//						icon: 2,
//						time: t
//					});
//					$("#imgFile").val("");
//					return false;
//				}
//			}
//			var imgn = $(this).val();
//			if(isFnInvalid(imgn.substring(imgn.lastIndexOf("\\") + 1))) {
//				layer.msg(imgNameError, {
//					icon: 2,
//					time: t
//				});
//				$("#imgFile").val("");
//				return false;
//			}
//			var format = imgn.substr(imgn.indexOf(".")).toLowerCase();
//			//			var imageType;
//			//			if(jQuery.inArray(format, imageType) == '-1') {
//			//				layer.msg(imgFormat, {
//			//					icon: 2,
//			//					time: t
//			//				});
//			//				$("#imgFile").val("");
//			//				return false;
//			//			}
//			var objUrl = getObjectURL(this.files[0]);
//			if(objUrl) {
//				$("#sourceImg").attr("src", objUrl);
//				$("#cutImg").attr("src", objUrl);
//				jcrop_api.setImage(objUrl);
//			}
//			uploadimg();
//			$('#preview-pane .preview-container img').css({
//				width: "",
//				height: "",
//				marginLeft: "",
//				marginTop: ""
//			});
//			//		$(this).parents("form").submit();
//			$("#imgClick").siblings(".myerror").html("");
//			$("#imgClick").closest('ul').find('em').html("");
//			$('#imgClick img').attr('src', $('#cutImg').attr('src'));
//			$('#imgClick img').css('width', $('#cutImg').css('width'));
//			$('#imgClick img').css('height', $('#cutImg').css('height'));
//			$('#imgClick').css('overflow', 'hidden');
//			$('#imgClick img').css('margin-left', $('#cutImg').css('margin-left'));
//			$('#imgClick img').css('margin-top', $('#cutImg').css('margin-top'));
//		});
//		$('#submit-avatar').click(function(e) {
//			e.preventDefault();
//			var form = $("#crop_form");
//			var myImg = new Image();
//			myImg.setAttribute("src", $('#sourceImg').attr('src')); //此处会加载图片
//			var ow = myImg.width;
//			var oh = myImg.height;
//			var w = $('#sourceImg').width();
//			var h = $('#sourceImg').height();
//			var lw = ow / w;
//			var lh = oh / h;
//			$(this).parents("form").submit();
//			return false;
//		});
//
//		//建立一個可存取到該file的url
//		function getObjectURL(file) {
//			var url = null;
//			if(window.createObjectURL != undefined) { // basic
//				url = window.createObjectURL(file);
//			} else if(window.URL != undefined) { // mozilla(firefox)
//				url = window.URL.createObjectURL(file);
//			} else if(window.webkitURL != undefined) { // webkit or chrome
//				url = window.webkitURL.createObjectURL(file);
//			}
//			return url;
//		}
//		var jcrop_api,
//			boundx,
//			boundy,
//
//			$preview = $('#preview-pane'),
//			$pcnt = $('#preview-pane .preview-container'),
//			$pimg = $('#preview-pane .preview-container img'),
//			xsize = $pcnt.width(),
//			ysize = $pcnt.height();
//		$('#sourceImg').Jcrop({
//			onChange: showCoords,
//			onSelect: showCoords,
//			aspectRatio: xsize / ysize
//		}, function() {
//			var bounds = this.getBounds();
//			boundx = bounds[0];
//			boundy = bounds[1];
//			jcrop_api = this;
//			$preview.appendTo(jcrop_api.ui.holder);
//		});
//		//简单的事件处理程序，响应自onChange,onSelect事件，按照上面的Jcrop调用
//		function showCoords(c) {
//			if(parseInt(c.w) > 0) {
//				var rx = xsize / c.w;
//				var ry = ysize / c.h;
//				$pimg.css({
//					width: Math.round(rx * boundx) + 'px',
//					height: Math.round(ry * boundy) + 'px',
//					marginLeft: '-' + Math.round(rx * c.x) + 'px',
//					marginTop: '-' + Math.round(ry * c.y) + 'px'
//				});
//
//				if(imgType == 1) {
//					$("#x").val(parseInt(c.x));
//					$("#y").val(parseInt(c.y));
//					$("#w").val(parseInt(c.w));
//					$("#h").val(parseInt(c.h));
//				}
//			}
//
//		}
//		//校验文件名,true:有非法字符
//		function isFnInvalid(fileName) {
//			var cs = [",", "&", "?", "=", "|"];
//			for(var i = 0; i < cs.length; i++) {
//				if(fileName.indexOf(cs[i]) != -1) {
//					return true;
//				}
//			}
//			return false;
//		}
//	}
//
//	$('#submit-avatar').parent().hide();
//	$('#imgClick').bind("click", function() {
//		imgType = 1;
//		$('#preview-pane').prev('img').attr('src', $('#imgClick img').attr('src'));
//		$('#cutImg').attr('src', $('#imgClick img').attr('src'));
//		$('#imgFile').click();
//	});
//	$(document).on("click", "#checkOk", function() {
//		$('#imgClick img').attr('src', $('#cutImg').attr('src'));
//		$('#imgClick img').css('width', $('#cutImg').css('width'));
//		$('#imgClick img').css('height', $('#cutImg').css('height'));
//		$('#imgClick').css('overflow', 'hidden');
//		$('#imgClick img').css('margin-left', $('#cutImg').css('margin-left'));
//		$('#imgClick img').css('margin-top', $('#cutImg').css('margin-top'));
//
//		function getBase64Image(img) {
//			var canvas = document.createElement("canvas");
//			canvas.width = img.width;
//			canvas.height = img.height;
//			var ctx = canvas.getContext("2d");
//			ctx.drawImage(img, 0, 0, img.width, img.height);
//			var ext = img.src.substring(img.src.lastIndexOf(".") + 1).toLowerCase();
//			var dataURL = canvas.toDataURL("image/" + ext);
//			return dataURL;
//		}
//		var params = {}
//		var img = $('#imgClick img').attr("src");
//		var image = new Image();
//		image.src = img;
//		image.onload = function() {
//			params.upfile = getBase64Image(image);
//			var url = path.path + '/comm/uploadImagesBase64';
//			$.ajax({
//				type: "post",
//				url: url,
//				data: params,
//				dataType: "json",
//				success: function(data) {
//					packJson.imgageUrl = (data.url)
//				},
//				error: function() {
//					layer.alert('图片保存失败');
//				}
//			});
//			layer.close(uploadImg);
//		};
//	});
//	//头像图片取消  
//	$(document).on("click", "#checkNo", function() {
//		//$("#imgFile").val("");	
//		$("#x").val('0');
//		$("#y").val('0');
//		$("#w").val('0');
//		$("#h").val('0');
//		layer.close(uploadImg);
//	});
//	/* 上传图片函数 */
//	function uploadimg() {
//		uploadImg = layer.open({
//			title: false,
//			type: 1,
//			area: ['730px', '410px'],
//			content: $("#div-upload-pop"), //捕获的元素
//			fix: false,
//			move: false,
//			closeBtn: 0
//		});
//	}
//
//});
/***************************************************************************/
//选择栏目项目添加
function column() {
	var url = path.path + '/imArticleContent/selectColumn';
	$.ajax({
		type: "get",
		url: url,
		dataType: "json",
		async: false,
		success: function(data) {
			if(data.code == "000000") {
				for(var i = 0; i < data.columns.length; i++) {
					var selDom = $("#column");
					selDom.append("<option value=" + data.columns[i].columnId + ">" + data.columns[i].columnNamne + "</option>"); //添加option
				};
			} else {
				layer.alert("查询失败");
			}
		}
	});
};
//根据所属栏目筛选所属频道
function channel() {
	var columnId = $("#column").find("option:selected").val();
	var url = path.path + '/imArticleContent/selectChannel';
	var params = {
		columnId: columnId
	}
	$.ajax({
		type: "get",
		url: url,
		dataType: "json",
		data: params,
		async: false,
		success: function(data) {
			if(data.code == "000000") {
				for(var i = 0; i < data.channels.length; i++) {
					var selDom = $("#channel");
					selDom.append("<option value=" + data.channels[i].channelId + ">" + data.channels[i].channelName + "</option>"); //添加option
				};
			} else {
				layer.alert("查询失败");
			}
		}
	});
};
//点击新增
$(".add_to").click(function() {
	var columnly = $("#column").find("option:selected").text();
	$("#columnly").val(columnly);
	$(".whole").addClass("hide");
	$(".specific").removeClass("hide");
	$(".text div").html("--请选择--");
	$("#userPhoto").attr('src', "../imgs/i-select-large.png");
	ue.ready(function() {
		ue.setContent("");
	});
	channel();
});
$(".cancel").click(function() {
	$(".whole").removeClass("hide");
	$(".specific").addClass("hide");
	$("#titlely").val("")
	$("#subtitle").val("")
	$("#sort").val("")
})
//点击保存
$(".preservation").click(function() {
	if(!$('#setMenu').valid()) {
		return;
	};
	if($("#imgClick").find("img").attr("src") == "../imgs/i-select-large.png" || $("#imgClick").find("img").attr("src") == "<%=imageUrl %>") {
		$("#imgFile").val("");
		layer.msg("请上传主题图片！", {
			icon: 2,
			time: 1500
		});
		return;
	};
	var source = $('input:radio[name="source"]:checked').val();
	if(source == "2" && $(".store-selector .text > div").html() == "--请选择--") {
		layer.msg('请先选中区域', {
			time: 1000, //20s后自动关闭
		});
		return;
	};
	//编辑器验证
	UE.getEditor('container').addListener('blur keyup change', function(editor) {
		var content = UE.getEditor('container').getPlainTxt();
		var contentLength = content.trim().length;
		if(content && contentLength > 0) {
			$("#container").siblings('.errortips').css({
				"background": "",
				"padding-left": ""
			});
			$("#container").siblings('.errortips').html("");
		} else {
			$("#container").siblings('.errortips').html("");
			$("#container").siblings('.errortips').css({
				"background": "",
				"padding-left": ""
			});
			var node = $("<label></label>");
			node.addClass("error");
			node.html("请输入内容");
			$("#container").siblings('.errortips').append(node);
		}
	});
	if(UE.getEditor('container').getPlainTxt() && UE.getEditor('container').getPlainTxt().trim().length == 0) {
		$("#container").siblings('.errortips').html("");
		$("#container").siblings('.errortips').css({
			"background": "",
			"padding-left": ""
		});
		var node = $("<label></label>");
		node.addClass("error");
		node.html("请输入内容");
		$("#container").siblings('.errortips').append(node);
		return;
	};
	//数据添加
	packJson.contentId = contentId;
	packJson.columnId = $("#column").find("option:selected").val();
	packJson.columnNamne = $("#column").find("option:selected").text();
	packJson.channelId = $("#channel").find("option:selected").val();
	packJson.channelName = $("#channel").find("option:selected").text();
	packJson.mainTitle = $("#titlely").val();
	packJson.subTitle = $("#subtitle").val();
	packJson.sortNo = $("#sort").val();
	packJson.releaseTime = releaseTime;
	if($('#recommend').is(':checked') == true) {
		packJson.isRecommend = "1"
	} else {
		packJson.isRecommend = "0"
	};
	packJson.sortNo = $("#sort").val();
	packJson.contentSource = $("input[name='source']:checked").val();
	if($("#roadCode").val() != "0") {
		packJson.regionId = $("#roadCode").val();
	} else if($("#countyCode").val() != "0") {
		packJson.regionId = $("#countyCode").val();
	} else if($("#cityCode").val() != "0") {
		packJson.regionId = $("#cityCode").val();
	} else {
		packJson.regionId = $("#provinceCode").val();
	};
	packJson.region = $(".text div").html();
	packJson.contentDtl = ue.getContent();
	packJson.imgFile = $("#aaa").val();
	var url = path.path + '/imArticleContent/saveOrUpdateContent';
	$.ajax({
		type: "post",
		url: url,
		data: JSON.stringify(packJson),
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success: function() {
			layer.alert('保存成功', {
					closeBtn: 0
				},
				function(index) {
					layer.closeAll();
					window.location.reload();
				}
			);
		},
		error: function() {
			layer.alert('保存协议失败');
		}
	});
});
//页面展示
function exhibition() {
	var columnId = $("#column").find("option:selected").val();
	var url = path.path + '/imArticleContent/contentList';
	var params = {
		columnId: columnId
	}
	$.ajax({
		type: "get",
		url: url,
		data: params,
		dataType: "json",
		async: false,
		success: function(data) {
			$(".remo").remove();
			if(data.code == "000000") {
				for(var i = 0; i < data.contents.length; i++) {
					var tableHtml = "";
					tableHtml += '<tr class="remo">' +
						'<td>' + data.contents[i].mainTitle + '</td>' +
						'<td>' + data.contents[i].subTitle + '</td>' +
						'<td>' + data.contents[i].columnNamne + '</td>' +
						'<td>' + data.contents[i].channelName + '</td>' +
						'<td class="releaseTime">' + data.contents[i].releaseTime + '</td>' +
						//						'<td>' + data.contents[i].contentSource + '</td>' +
						'<td>' +
						'<span  class="unified Release">发布</span> |' +
						'<span  class="unified delete">删除</span> |' +
						'<span  class="unified edit">编辑</span>' +
						'</td>' +
						'<td class="hide">' + data.contents[i].contentId + '</td>' +
						'<td class="hide islease">' + data.contents[i].isRelease + '</td>' +
						'</tr>';
					$("#addTr").append(tableHtml);
					console.log(data.contents[i].isRelease)
					$(".islease").each(function() {
						var txt = $(this).text(); //这句获取当前遍历到的option的值\
						if(txt == "1") {
							console.log(txt)
							$(this).prev().prev().children().siblings('.Release').addClass('disable-del')
						}
					});
					$(".releaseTime").each(function(i, item) {
						var releaseTime = $(this).text();
						if(releaseTime === "null") {
							$(this).text("")
						}
					});
				};
			} else {
				layer.alert("查询失败");
			}
		}
	});
};
//选择栏目更改
$("#column").change(function() {
	$(".remo").remove();
	exhibition();
})
//发布
$(document).on("click", ".Release", function() {
	var cont = $(this).parent().next().text(); //这条资讯的ID
	var a;
	layer.confirm('是否发布此资讯', {
		btn: ['确认', '取消'],
	}, function(index, layero) {
		var url = path.path + '/imArticleContent/releaseById';
		params = {
			'contentId': cont
		};
		$.ajax({
			type: "get",
			url: url,
			data: params,
			dataType: 'json',
			async: false,
			success: function(data) {
				$(".remo").remove();
				exhibition();
				layer.closeAll();
			}
		});
	}, function(index) {
		layer.closeAll();
	});
});
//删除
$(document).on("click", ".delete", function() {
	var channelId = $(this).parent().next().text(); //这条资讯的ID
	layer.confirm('是否删除此条资讯', {
		btn: ['确认', '取消'],
	}, function(index, layero) {
		var url = path.path + '/imArticleContent/deleteById';
		params = {
			'contentId': channelId
		};
		$.ajax({
			type: "get",
			url: url,
			data: params,
			dataType: 'json',
			async: false,
			success: function(data) {
				$(".remo").remove();
				exhibition();
				layer.closeAll();
			}
		});
	}, function(index) {
		layer.closeAll();
	});
});
//编辑
$(document).on("click", ".edit", function() {
	var cont;
	$(".whole").addClass("hide");
	$(".specific").removeClass("hide");
	channel();

	contentId = $(this).parent().next().text();
	var url = path.path + '/imArticleContent/getById';
	params = {
		'contentId': contentId
	};
	$.ajax({
		type: "get",
		url: url,
		data: params,
		dataType: 'json',
		async: false,
		success: function(data) {
			if(data.code == "000000") {
				cont = data.content.contentDtl;
				$("#columnly").val(data.content.columnNamne);
				$("#titlely").val(data.content.mainTitle);
				$("#subtitle").val(data.content.subTitle);
				$("#sort").val(data.content.sortNo);
				$(".text div").html(data.content.region);
				releaseTime = data.content.releaseTime;
				$("#userPhoto").attr('src', data.content.imgageUrl);
				if(data.content.isRecommend == "1") {
					$("#recommend").attr("checked", "checked");
				}
				if(data.content.region == "0") {
					$("#huaTuo").attr("checked", "checked");
					$("#extension").removeAttr("checked");
					$("#region").removeAttr("checked");
				} else if(data.content.region == "1") {
					$("#huaTuo").removeAttr("checked");
					$("#extension").attr("checked", "checked");
					$("#region").removeAttr("checked");
				} else {
					$("#huaTuo").removeAttr("checked");
					$("#extension").removeAttr("checked");
					$("#region").attr("checked", "checked");
				};
			} else {
				layer.alert('查询失败');
			}
		}
	});
	ue.ready(function() {
		ue.setContent(cont);
	});
});
$(function() {
	UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	UE.Editor.prototype.getActionUrl = function(action) {
		if(action == '/comm/uploadImages') {
			return path.path + 'comm/uploadImages';
		} else if(action == '/comm/uploadAttachments') {
			return path.path + '/comm/uploadAttachments';
		} else {
			return this._bkGetActionUrl.call(this, action);
		}
	}
	column();
	exhibition();
});