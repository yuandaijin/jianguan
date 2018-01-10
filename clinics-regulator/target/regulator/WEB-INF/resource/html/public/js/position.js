
$(".default-position").on('click',".JD-stock  li a",function(e){
	var dataArea = $(this).closest('div').attr('data-area');	
	if(dataArea){
 		if(dataArea==0){
 			$('#area-lbn-p').attr({'province':$(this).attr('data-value'),'city':'0','country':'0','road':'0'});
 			$('#area-lbn-p').attr({'p':$(this).text(),'c':'','co':'','r':''});
 		}
 		if(dataArea==1){
 			$('#area-lbn-p').attr({'city':$(this).attr('data-value'),'country':'0','road':'0'});
 			$('#area-lbn-p').attr({'c':$(this).text(),'co':'','r':''});
 		}
 		if(dataArea==2){
 			$('#area-lbn-p').attr({'country':$(this).attr('data-value'),'road':'0'});
 			$('#area-lbn-p').attr({'co':$(this).text(),'r':''});
 		}
		if(dataArea==3){
			$('#area-lbn-p').attr({'road':$(this).attr('data-value')});
			$('#area-lbn-p').attr({'r':$(this).text()});
		}
 	}
});