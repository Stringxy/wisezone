 //自定义的验证
$(function() {
	
    $('form').bootstrapValidator({            
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',  //验证成功显示的图标
                invalid: 'glyphicon glyphicon-remove', //验证失败图标
                validating: 'glyphicon glyphicon-refresh' //验证中...
            },
            //设定要验证的字段
            fields: {
            	
            	//角色名称
            	majorName: {
            		 
                    validators: {
                    	//非空验证
                        notEmpty: {
                            message: "专业名称不能为空"
                        },
                        
                        stringLength: {
                            min: 2,
                            max: 50,
                            message: "长度在2到50之间"
                        },
//                        remote: {                         	                        	
//                        	url: $("body").attr("data-url")+'/baseManager/checkname.action',
//                        	data:{"majorId": $("#majorId").val() || 0 },   //自定义数据
//                        	type:"post",
//                        	message: $("#majorName").parent().parent().find("label").html()+'已经存在' 
//                        }

                        
                    }
                }  
               
            }
        });
});
 