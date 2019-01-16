$(function() {
	console.log("input");
	
	testDoc  = function (){  
        var url = "/helloTomcat/FoodFormRead";
        $.get(url ,function(data){
            $('.food-form').html(data);
        })
    }  
});