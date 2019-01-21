$(function() {
	console.log("input");
	
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