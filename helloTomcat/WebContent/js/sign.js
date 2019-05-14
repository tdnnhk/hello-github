$(function() {
	
	signin = function(){
    	var url = '/helloTomcat/UserOperation';
    	var uesrname = $('#signin .uesrname').val();
    	var password = $('#signin .password').val();
    	var type = "signin";
    	
    	if(isnull(uesrname) || isnull(password)){
    		alert("请完整输入内容");
    	}else{
    		$.post(url, {'type':type,'uesrname':uesrname,'password':password},function(data){
    			if(data === "correct") {
    				alert("登录成功");
    				window.location.href="./user.html?username="+uesrname;
    			} else {
    				alert("登录失败");
    			}
    			
    		})
    	}
	}
	
	submitInfo  = function (){
    	var url = '/helloTomcat/UserOperation';
    	var uesrname = $('#signup .uesrname').val();
    	var password = $('#signup .password').val();
    	var passwordConfirm = $('#signup .passwordConfirm').val();
    	var type = "submitInfo";   	

    	if(isnull(uesrname) || isnull(password)|| isnull(passwordConfirm)){
    		alert("请完整输入内容");
    	}else if(password != passwordConfirm){
    		alert("两次密码不一致");
    	}else{
    		$.get(url, {'type':type,'uesrname':uesrname,'password':password},function(data){
        		//$('.food-form').html(data);
    			alert("注册成功");
    			window.location.href="./user.html";
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