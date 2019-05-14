$(function() {
	
	//暂时先不合并servlet，到时候应该会有不同的处理，如列表返回json
	foodMenu  = function (){  
        var url = "/helloTomcat/FoodFormRead";
        $.get(url ,function(data){
            $('.food-form').html(data);
        })
    }  
	
	randomFood  = function (){  
        var url = "/helloTomcat/FoodMenuOperation";
        $.get(url, {'type':"randomFood"}, function(data){
            $('.food-form').html(data);
        })
    }  
});