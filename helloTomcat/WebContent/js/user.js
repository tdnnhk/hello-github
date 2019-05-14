$(function() {
	console.log("begin");
	var url = '/helloTomcat/SessionTest';
	$.get(url, function(data){
		var jsonSet = $.parseJSON(data);
        var tbhtml = "<tr><th>名字</th><th>材料</th><th>做法</th></tr>";
        
        for(var i = 0;i<jsonSet.length;i++){
                tbhtml+="<tr><td>"+jsonSet[i].title+"</td><td>"+jsonSet[i].material+"</td><td>"+jsonSet[i].method+"</td></tr>";
        }

        $('table:eq(0)').html(tbhtml);

	})
	
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
        		//$('.food-form').html(data);
    			alert("输入成功");
    			window.location.reload();
        	})
    	}
    	
    }
    
    clearTextbox  = function (){
        $('.title').val('');
        $('.material').val('');
        $('.method').val('');
        $('.food-form').html('');
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