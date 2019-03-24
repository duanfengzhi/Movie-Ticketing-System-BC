
/*----------------GuessLike----------------*/
var urlRoot;

$(ready);
function ready(){
	urlRoot = $("#urlRoot").val();
	if (urlRoot.indexOf(';') != -1) {
		urlRoot = urlRoot.substring(0, urlRoot.indexOf(';'));
	}

	var guessDiv = $("#guess_like");
	if (guessDiv.length == 0) return;   //对象不存在
	
	$.ajax({
        type     : "get",
        async    : true,
        url      : urlRoot + "guessCtrl",
        dataType : "text",
        cache    : false,
        success  : updateGuessInfo
    });
}

//返回值： img:lid\n img:lid\n img:lid\n img:lid
function updateGuessInfo(data){
	var msgs = data.trim().split('\n');
	for (var i = 0; i < msgs.length; i++) {
		var msg = msgs[i].split(':');
		
		var aUrl = urlRoot + "ListTicketsFromCtrl?starttid=" + msg[1];
	    $("#guessimg" + (i + 1) + ">a").attr({href : aUrl});
	    var imgUrl = "/EBP/images/userlmg" + msg[0];
	    $("#guessimg" + (i + 1) + ">a img").attr({src : imgUrl});
    }
}



/*-------------------AJAX------------------*/
function statusTicketChanging(data){
	var msg = data.trim().split(':');
    $("#ajaxTicketStatus"+ msg[0]).html(msg[1]);
    $("#ajaxTicketButton"+ msg[0]).html(msg[2]);
   
}


function statusUserChanging(data){
	var msg = data.trim().split(':');
	$("#ajaxUserStatus"+ msg[0]).html(msg[1]);
    $("#ajaxUserButton"+ msg[0]).html(msg[2]);
    var str=msg[3];
    var s="0";
    
   if(str==s){
	   $("#ajaxUserButton"+ msg[0]).attr("class","layui-btn layui-btn-normal layui-btn-sm");
    } else{
	   $("#ajaxUserButton"+ msg[0]).attr("class","layui-btn layui-btn-danger layui-btn-sm");
   }
    
}