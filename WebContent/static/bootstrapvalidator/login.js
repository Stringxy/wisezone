 
$(function() {
	
	

	
	//正定义验证
    $('form').bootstrapValidator({            
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',  //验证成功显示的图标
                invalid: 'glyphicon glyphicon-remove', //验证失败图标
                validating: 'glyphicon glyphicon-refresh' //验证中...
            },
            //设定要验证的字段
            fields: {
            	
            	//角户名称
            	username: {
            		 
                    validators: {
                    	//非空验证
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        
                        stringLength: {
                            min: 4,
                            max: 30,
                            message: '长度范围在4到 30字符之间'
                        }  
                    }
                } ,
                //密码
                password:{
                	validators:{
                		//非空验证
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        
                        stringLength: {
                            min: 4,
                            max: 30,
                            message: '长度范围在4到 30字符之间'
                        }  
                	}
                },
                //验证码
                verifycode:{
                	validators:{
                		//非空验证
                        notEmpty: {
                            message: '验证码不能为空'
                        },
                        regexp: {
                        	regexp:/^\w{4}$/,
                            message: '长度是4位'
                        } 
                	}
                }
               
            }
        });
    
});

function change(obj){
	

	obj.src=$("body").attr("data-url")+"/verify/image.action?r="+new Date().getTime();
	
}
	

 