var idCardNoUtil = {
  
provinceAndCitys: {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",
31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",
45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",
65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"},
powers: ["7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2"],
parityBit: ["1","0","X","9","8","7","6","5","4","3","2"],
genders: {male:"男",female:"女"},
checkAddressCode: function(addressCode){
var check = /^[1-9]\d{5}$/.test(addressCode);
if(!check) return false;
if(idCardNoUtil.provinceAndCitys[parseInt(addressCode.substring(0,2))]){
return true;
}else{
return false;
}
},
checkBirthDayCode: function(birDayCode){
var check = /^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/.test(birDayCode);
if(!check) return false;
var yyyy = parseInt(birDayCode.substring(0,4),10);
var mm = parseInt(birDayCode.substring(4,6),10);
var dd = parseInt(birDayCode.substring(6),10);
var xdata = new Date(yyyy,mm-1,dd);
if(xdata > new Date()){
return false;//生日不能大于当前日期
}else if ( ( xdata.getFullYear() == yyyy ) && ( xdata.getMonth () == mm - 1 ) && ( xdata.getDate() == dd ) ){
return true;
}else{
return false;
}
},
getParityBit: function(idCardNo){
var id17 = idCardNo.substring(0,17);
  
var power = 0;
for(var i=0;i<17;i++){
power += parseInt(id17.charAt(i),10) * parseInt(idCardNoUtil.powers[i]);
}
  
var mod = power % 11;
return idCardNoUtil.parityBit[mod];
},
checkParityBit: function(idCardNo){
var parityBit = idCardNo.charAt(17).toUpperCase();
if(idCardNoUtil.getParityBit(idCardNo) == parityBit){
return true;
}else{
return false;
}
},
checkIdCardNo: function(idCardNo){
//15位和18位身份证号码的基本校验
var check = /^\d{15}|(\d{17}(\d|x|X))$/.test(idCardNo);
if(!check) return false;
//判断长度为15位或18位
if(idCardNo.length==15){
return idCardNoUtil.check15IdCardNo(idCardNo);
}else if(idCardNo.length==18){
return idCardNoUtil.check18IdCardNo(idCardNo);
}else{
return false;
}
},
  
//校验15位的身份证号码
check15IdCardNo: function(idCardNo){
//15位身份证号码的基本校验
var check = /^[1-9]\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}$/.test(idCardNo);
if(!check) return false;
//校验地址码
var addressCode = idCardNo.substring(0,6);
check = idCardNoUtil.checkAddressCode(addressCode);
if(!check) return false;
var birDayCode = '19' + idCardNo.substring(6,12);
//校验日期码
return idCardNoUtil.checkBirthDayCode(birDayCode);
},
  
//校验18位的身份证号码
check18IdCardNo: function(idCardNo){
//18位身份证号码的基本格式校验
var check = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}(\d|x|X)$/.test(idCardNo);
if(!check) return false;
//校验地址码
var addressCode = idCardNo.substring(0,6);
check = idCardNoUtil.checkAddressCode(addressCode);
if(!check) return false;
//校验日期码
var birDayCode = idCardNo.substring(6,14);
check = idCardNoUtil.checkBirthDayCode(birDayCode);
if(!check) return false;
//验证校检码
return idCardNoUtil.checkParityBit(idCardNo);
},
  
formateDateCN: function(day){
var yyyy =day.substring(0,4);
var mm = day.substring(4,6);
var dd = day.substring(6);
return yyyy + '-' + mm +'-' + dd;
},
  
//获取信息
getIdCardInfo: function(idCardNo){
var idCardInfo = {
gender:"", //性别
birthday:"" // 出生日期(yyyy-mm-dd)
};
if(idCardNo.length==15){
var aday = '19' + idCardNo.substring(6,12);
idCardInfo.birthday=idCardNoUtil.formateDateCN(aday);
if(parseInt(idCardNo.charAt(14))%2==0){
idCardInfo.gender=idCardNoUtil.genders.female;
}else{
idCardInfo.gender=idCardNoUtil.genders.male;
}
}else if(idCardNo.length==18){
var aday = idCardNo.substring(6,14);
idCardInfo.birthday=idCardNoUtil.formateDateCN(aday);
if(parseInt(idCardNo.charAt(16))%2==0){
idCardInfo.gender=idCardNoUtil.genders.female;
}else{
idCardInfo.gender=idCardNoUtil.genders.male;
}
  
}
return idCardInfo;
},
getId15:function(idCardNo){
if(idCardNo.length==15){
return idCardNo;
}else if(idCardNo.length==18){
return idCardNo.substring(0,6) + idCardNo.substring(8,17);
}else{
return null;
}
},
getId18: function(idCardNo){
if(idCardNo.length==15){
var id17 = idCardNo.substring(0,6) + '19' + idCardNo.substring(6);
var parityBit = idCardNoUtil.getParityBit(id17);
return id17 + parityBit;
}else if(idCardNo.length==18){
return idCardNo;
}else{
return null;
}
}
};
//验证护照是否正确
function checknumber(number){
var str=number;
//在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
var Expression=/(P\d{7})|(G\d{8})/;
var objExp=new RegExp(Expression);
if(objExp.test(str)==true){
return true;
}else{
return false;
}
};

//只能使用中文标点符号——多行文本输入
//敏感字符限制
jQuery.validator.addMethod("sensitiveCharacter", function(value, element) { 
	var pattern = new RegExp("(script|javascript|function|eval\\(|this\\.|this\\[|self\\.|self\\[|parent\\.|parent\\[|window\\.|window\\[|document\\.|document\\[|location\\.|location\\[|iframe|alert\\(|confirm\\(|prompt\\(|onMouseOver|Content\\-Type|onerror|var |try\\{|catch\\(|finally\\{|break\\;|continue\\;|file\\:|select |insert |delete |update |create |destory |drop |alter |exec | count\\(| chr\\(| mid\\(|master |truncate |char |declare )"); 
	return this.optional(element)||!pattern.test(value) ; 
},"请不要输入敏感字符（如script、javascript等）");  

jQuery.validator.addMethod("specialCharValidateTextarea", function(value, element) { 
//	var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',\\[\\]<>/?\\.；：%……+￥（）【】]"); 
//	return this.optional(element)||!pattern.test(value) ; 
	return true; 
},"请不要使用  @,$,&....等特殊符号");  
jQuery.validator.addMethod("specialCharValidate", function(value, element) { 
//	var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，？]"); 
//return this.optional(element)||!pattern.test(value) ;
	return true; 
},"请不要输入特殊字符");  
// 手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请输入正确的手机号码");
jQuery.validator.addMethod("isIdCardNo", function(value, element) {
return this.optional(element) || idCardNoUtil.checkIdCardNo(value);
}, "请输入正确的身份证号码");
//出生日期 
jQuery.validator.addMethod("birthday", 		
	function(value, element) {
	function parseISO8601(dateStringInRange) {  
		   var isoExp = /^\s*(\d{4})-(\d\d)-(\d\d)\s*$/,  
		       date = new Date(NaN), month,  
		       parts = isoExp.exec(dateStringInRange);  
		  
		   if(parts) {  
		     month = +parts[2];  
		     date.setFullYear(parts[1], month - 1, parts[3]);  
		     if(month != date.getMonth() + 1) {  
		       date.setTime(NaN);  
		     }  
		   }  
		   return date;  
		 }	
	function dateCompare(date1){
		var time1 = parseISO8601(date1);
		var time2 = new Date();
		if(time1 < time2){
			return true;
		}else if(time1 == time2){
			return true;
		}else{
			return false;
		}
	}
	return dateCompare(value);
}, "请输入小于当前时间的日期!"); 
//ajax用户名是否存在
jQuery.validator.addMethod("existName", function(value, element,param) {
	var flag = true;
	var ajaxPara = {name:value};
	if($.trim(param.doctorId) != ""){
		ajaxPara.doctorId = param.doctorId; 
	};
	$.ajax({
		 type:"get",
		 url:param.url,
		 data: ajaxPara,
		 dataType:'json',
		 cache : false,
		 async: false,
		 success:function(data){
			 if(data&&data.code != '000000'){//已经存在用户名
				 flag = false;
			 }else{
				 flag = true;
			 }
			 
		 }
	});
	return flag;
}, "用户名已经存在!"); 
//ajax图形验证是否存在
jQuery.validator.addMethod("existImgCode", function(value, element,param) {
	var flag = true;
	$.ajax({
		 type:"get",
		 url:param.url,
		 data: {img_code:value},
		 dataType:'json',
		 cache : false,
		 async: false,
		 success:function(data){
			 if(data&&data.code != '000000'){//已经存在用户名
				 flag = false;
			 }else{
				 flag = true;
			 }
			 
		 }
	});
	return flag;
}, "图形验证错误!"); 
jQuery.validator.addMethod("compareDate", function(value, element,param) {
    //var startDate = jQuery(param).val() + ":00";补全yyyy-MM-dd HH:mm:ss格式
    //value = value + ":00";
	if($(param).val() == '' || value == '') return true;
    var startDate = jQuery(param).val();
    var date1 = new Date(Date.parse(startDate));
    var date2 = new Date(Date.parse(value));
    return date1 <= date2;
});
//字母+数字
jQuery.validator.addMethod("alnum", function(value, element){
return this.optional(element) ||/(?!^\d+$)(?!^[a-zA-Z]+$)^[0-9a-zA-Z]{4,23}$/.test(value);
}, "只能包括英文字母和数字");
jQuery.validator.addMethod("compareDate1", function(value, element,param) {
    //var startDate = jQuery(param).val() + ":00";补全yyyy-MM-dd HH:mm:ss格式
    //value = value + ":00";
	if($(param).val() == '' || value == '') return true;
    var startDate = jQuery(param).val();
    var date1 = new Date(Date.parse(startDate));
    var date2 = new Date(Date.parse(value));   
    return date1 >= date2;
});
//不相等
jQuery.validator.addMethod("noEqual", function(value, element,param) { 
    return !(parseInt(value) == parseInt(param));
});
//不为空
jQuery.validator.addMethod("noBlank", function(value, element,param) { 
    return !((value).trim() == "");
});
