$(function() {
	
	signin = function(){
    	var url = '/helloTomcat/UserOperation';
    	var uesrname = $('.uesrname').val();
    	var password = $('.password').val();
    	var type = "signin";
    	
    	if(isnull(uesrname) || isnull(password)){
    		alert("请完整输入内容");
    	}else{
    		$.get(url, {'type':type,'uesrname':uesrname,'password':password},function(data){
    			alert("登录成功");
    		})
    	}
	}
	
	submitInfo  = function (){
    	var url = '/helloTomcat/UserOperation';
    	var uesrname = $('.uesrname').val();
    	var password = $('.password').val();
    	var passwordConfirm = $('.passwordConfirm').val();
    	var type = "submitInfo";   	

    	if(isnull(uesrname) || isnull(password)|| isnull(passwordConfirm)){
    		alert("请完整输入内容");
    	}else if(password != passwordConfirm){
    		alert("两次密码不一致");
    	}else{
    		$.get(url, {'type':type,'uesrname':uesrname,'password':password},function(data){
        		//$('.food-form').html(data);
    			alert("注册成功");
    		})
    	}
    	
    }
    
    clearTextbox  = function (){
        $('.uesrname').val('');
        $('.password').val('');
        $('.passwordConfirm').val('');
    }
    
   
    
    function isnull(val) {
        var str = val.replace(/(^\s*)|(\s*$)/g, '');//去除空格;

        if (str == '' || str == undefined || str == null) {        	
        	return true;            
        } else {
            return false;
        }
    }
 
	
});