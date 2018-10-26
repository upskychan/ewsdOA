<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link href="<%=request.getContextPath()%>/Static/BJUI/themes/css/style.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/Static/BJUI/js/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath()%>/Static/BJUI/js/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/Static/js/jquery.md5.js"></script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" >
$(document).ready(function() {
    $(document).keydown(function(e) {
        if (e.keyCode == 13) {
            if(null!=$("#vcode").val() && ''!=$("#vcode").val() && null!=$("#account").val() && ''!=$("#account").val() && null!=$("#pwd").val() && ''!=$("#pwd").val() ){
                userLogin();
            }
        }
    });

    if (window != top)
        top.location.href = location.href;
});
$(function(){
	$("#message").text("");
})
function userLogin()
{//alert("${ctx}/login/loginsubmit");
	if($("#account").val()==""){
		$("#message").text("请输入账号");
		return;
	}
	$("#message").text("");
	if($("#pwd").val()==""){
		$("#message").text("请输入密码");
		return;
	}
	$("#message").text("")
	if($("#vcode").val()==""){
		$("#message").text("请输入验证码");
		return;
	}
	var md5Psw = $.md5('1234');
	$("#pwd").val(md5Psw);
	
	$("#message").text("")
	 $.ajax( {
		  url: '<%=request.getContextPath()%>/login/loginsubmit.do',
		  type:"post",
		  data :"account="+$("#account").val()+"&pwd="+$("#pwd").val()+"&vcode="+$("#vcode").val(),
		  timeout :20000,// 设置请求超时时间（毫秒）。
		  error : function() {// 请求失败时调用函数。
		  	alert("请求失败!");
		  },
		  success :function(data){//alert(data);
			 if(data=="0"){
				 window.location.href="<%=request.getContextPath()%>/index.jsp";
			 }else if(data=="1"){
				 $("#message").show();
				$("#message").text("您验证码输入错误，请重新输入");
			 }else if(data=="2"){
				 $("#message").show();
				 $("#message").text("您输入的账户名和密码不匹配，请重新输入");
			 }else if(data=="3"){
				 $("#message").show();
				 $("#message").text("您输入的账户名没有权限访问此系统，请重新输入");	
			 }else{
				 $("#message").show();
				 $("#message").text("其它异常错误！");
			 }
		  }
	  });
}
</script>
</head>
<body>
	<div class="form-group" align="center">
		<br><br><p style="font-weight:bold;font-size:30px;">管理平台登录页面</p><br><br><br><br>
		<form id="loginForm" method="post" action="<%=request.getContextPath()%>/login/loginsubmit">
            <input name="account" id="account" type="text" value="admin" placeholder="请输入帐号" ><br>
	  		<input name="pwd" type="password" id="pwd" placeholder="请输入密码" value="1"/><br>
            <input name="vcode" id="vcode" type="text" class="left" style="width:10%;margin: 0" placeholder="请输入验证码"><br>
            <img id="kaptchaImage" alt="验证码" class="left get_code" src="<%=request.getContextPath()%>/Kaptcha.jpg" style=""/><br>
            <input type="button" onclick="userLogin()" value="登&nbsp;&nbsp;&nbsp;&nbsp;录"/><br><br>
            <div id="message" style="color:red;"></div>
       		
        </form>
	</div>
</body>
<script type="text/javascript">  
    $(function() {
        $('#kaptchaImage').click(
                function() {
                    $(this).hide().attr('src','<%=request.getContextPath()%>/Kaptcha.jpg?' + Math.floor(Math.random() * 100)).fadeIn();
                });  
    });  
</script>
</html>