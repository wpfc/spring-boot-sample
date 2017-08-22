<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hello WebSocket</title>  
<script src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.min.js"></script>  
<script src="http://cdn.bootcss.com/stomp.js/2.3.3/stomp.js"></script>  
<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script> 
</head>
<body>
<input type="text" name="userInfo" />
<button id="connect" onclick="connect();">连接</button>
<button id="disconnect" onclick="disconnect();">断开</button>
</body>
<script type="text/javascript">
var clinet = null;

function setConnected(connected) {  
    document.getElementById('connect').disabled = connected;  
    document.getElementById('disconnect').disabled = !connected;   
}  

function connect(){
	var userInfo = $("input[name=userInfo]").val();
	var socket = new SockJS("http://localhost:8000/wpfc");
	client = Stomp.over(socket);
	client.connect({}, function(frame) {  
        setConnected(true);  
        console.log('Connected: ' + frame);  
        stompClient.subscribe('/topic/greetings', function(greeting){  
            console.log(JSON.parse(greeting.body).content);  
        });  

        stompClient.subscribe('/topic/marco',function(greeting){  
        	console.log(JSON.parse(greeting.body).content);   
        });  
    });  
}
</script>
</html>