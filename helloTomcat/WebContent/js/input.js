$(function() {
	
	
	submitMenu  = function (){
    	var url = '/helloTomcat/FoodMenuOperation';
    	var title = $('.title').val();
    	var material = $('.material').val();
    	var method = $('.method').val();
    	var type = "AddFood";
    	if(isnull(title) && isnull(material)){
    		alert("请输入正确内容");
    	}else{
    		$.get(url, {'type':type,'cook-name':title,'material':material,'method':method},function(data){
        		$('.food-form').html(data);
        	})
    	}
    	
    }
    
    clearTextbox  = function (){
        $('.title').val('');
        $('.material').val('');
        $('.method').val('');
        $('.food-form').html('');
    }
    
    deleteFood  = function (){
        var url = '/helloTomcat/FoodMenuOperation';
        var deleteTitle = $('.deleteFood').val();
        var type = "DeleteFood";
        if(isnull(deleteTitle)){
    		alert("请输入正确内容");
    	}else{
    		$.get(url, {'type':type,'cook-name':deleteTitle},function(data){
                alert(data);
            })
    	}
        
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