$(function(){  
      
    //富文本编辑器  
    UE.getEditor('myEditor')  
      
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;  
    UE.Editor.prototype.getActionUrl = function(action){  
        if(action == '/comm/uploadImages'){  
            return basePath+'comm/uploadImages';  
        }else if(action == '/comm/uploadAttachments'){  
            return basePath+'comm/uploadAttachments';  
        }else{  
            return this._bkGetActionUrl.call(this, action);  
        }  
    }  
});  