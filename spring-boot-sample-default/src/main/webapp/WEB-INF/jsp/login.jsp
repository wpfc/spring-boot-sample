<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="<%=request.getContextPath()%>"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云后台管理系统</title>
<link rel="stylesheet" href="${root }/static/login/register-login.css">
<link rel="stylesheet" href="${root }/static/login/layer.css">
<link rel="stylesheet" href="${root }/static/login/sweetalert.css">


	</head>

	<body>
		 <div id="box"><canvas class="particles-js-canvas-el" width="1903" height="278" style="width: 100%; height: 100%;"></canvas></div>
    <div class="cent-box">
        <div class="cent-box-header">
            <h1 class="main-title" style="width:300px; color: #0f88eb; font-size: 40px; font-family: 'Microsoft YaHei'; ">云后台管理系统</h1>
            <h2 class="sub-title">用户登陆</h2>
        </div>

        <div class="cont-main clearfix">
            <div class="login form">
                <div class="group">
                    <div class="group-ipt email">
                        <input type="text" name="username" id="username" class="ipt" placeholder="输入您的账号" required="">
                    </div>
                    <div class="group-ipt password">
                        <input type="password" name="password" id="password" class="ipt" placeholder="输入您的登录密码" required="">
                    </div>
                    <div class="group-ipt verify">
                        <input type="text" name="verify" id="verify" class="ipt" placeholder="输入验证码" required="">
                        <img id="randomCode" class="imgcode" src="${root }/random.do" 
		         alt="验证码" title="点击这里换一个" onclick="this.src=this.src+'?'+Math.random()">
		         
		         
                    </div>
                </div>
            </div>

            <div class="button">
                <button type="submit" class="login-btn register-btn" id="button">登录</button>
            </div>

            
        </div>
    </div>

    <div class="footer">
        <p>Copyright &copy; 重庆市嘟旗科技有限公司版权所有 </p>
        <p>网站备案号：渝ICP备14000653号-3</p>
    </div>

	</body>
	<script src="${root }/static/login/js/particles.js"></script>
<script src="${root }/static/login/js/background.js"></script>

<script src="${root }/static/jQuery/jquery-2.2.3.min.js"></script>

<script src="${root }/static/layer/layer.js"></script>
<script src="${root }/static/login/js/sweetalert.min.js"></script>
<script src="${root }/static/login/js/basejs.js"></script>

<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.min.js"></script>  

	
<script type="text/javascript">

$(function() {
	/* $(document).ready(function(e) {
		$(this).keydown(function (e){
			if(e.which == "13"){
			    $('#submit').click();
			}


		})

	});
	 
	$('#submit').click(function() {
		var pwd  = md5($('#password').val());
		$.post('${root}/login', {
			randomCode:$('#randomCode').val(),
			employeeNo: $('#username').val(),
			password: pwd
		}, function(r) {
			if (r.code == 200) {
				window.location.href = '${root}/employee/home';
			} else {
				alert(r.msg);
			}
		});
	}); */
	 var cookieUserName = Util.GetCookie("SKW.UserName");
     $("#username").val(cookieUserName);


 $("#button").click(function () {
     var $UserName = $("#username");
     var $Password = $("#password");
     var $code = $("#verify");
     if ($UserName.val() == "") {
         $UserName.focus();
         return false;
     } else if ($Password.val() == "") {
         $Password.focus();
         return false;
     } else if ($code.val() == "") {
         $code.focus();
         return false;
     } else {
         $.ajax({
             url: "${root}/login",
             type: "post",
             data: { employeeNo: $UserName.val(), password: md5($Password.val()), randomCode: $code.val() },
             success: function (data) {
            		 layer.msg('登录成功，正在为您跳转到管理页面', { icon: 16, shade: [0.5, '#393D49'] });

                     if ($("#remember-me").is(":checked")) {
                         Util.SetCookie("SKW.UserName", $UserName.val(), 2000);
                     }
                     else {
                         Util.SetCookie("SKW.UserName", "", 2000);
                     }
                     window.location.href = "${root}/";
                 
             },
             complete: function () {
                 //兼容提示错误时关闭Loading层
                 layer.closeAll(loading);
             }
         });
     }
 });

	
	  $('.imgcode').hover(function () {
          layer.tips("看不清？点击更换", '.verify', {
              time: 6000,
              tips: [2, "#3c3c3c"]
          })
      }, function () {
          layer.closeAll('tips');
      }).click(function () {
      });

      $("#remember-me").click(function () {
          var n = document.getElementById("remember-me").checked;
          if (n) {
              $(".zt").show();
          } else {
              $(".zt").hide();
          }
      });

      document.onkeydown = function (event_e) {
          if (window.event)
              event_e = window.event;
          var int_keycode = event_e.charCode || event_e.keyCode;
          if (int_keycode == 13) {
              $('#button').click();
          }
      }


});
</script>
</html>