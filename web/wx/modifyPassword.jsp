<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String openid = (String)request.getAttribute("openid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/jquery.mobile.flatui.css" />
    <script src="js/jquery.js"></script>
    <script src="js/jquery.mobile-1.4.0-rc.1.js"></script>
</head>
<!-- 验证js库 -->
<script src="./js/jquery.validate.js"></script>
<script src="./js/jquery.form.js"></script>
<body>
<div data-role="page">
    <div data-role="header" data-theme="b">
        <h1>修改密码</h1>
    </div>
    <div data-role="main" class="ui-content">
        <form method="post" id="modifypwdForm" name = "modifypwdForm" action="ModifyPasswordServlet">
        <div>
					<input type="hidden" name="openid" value=<%=openid%>>
		</div>
            <div class="ui-field-contain">
                <label for="old-password">旧密码：</label>
                <input type="password" name="oldpassword" id="oldpassword" placeholder="请输入旧密码">
                <br/>
                <label for="new-password">新密码：</label>
                <input type="password" name="newpassword" id="newpassword" placeholder="请输入新密码">
                <br/>
                <label for="confirm-password">确认密码：</label>
                <input type="password" name="confirmpassword" id="confirmpassword" placeholder="确认新密码">
            </div>
            <input type="submit" id="modifySubmit" data-inline="true" data-theme="a" value="确认修改">
        </form>
    </div>
</div>

<div data-role="page" data-dialog="true" id="dialog">
    <div data-role="main" class="ui-content">
        <p>修改成功</p>
        <a href="#pageone">哦</a>
    </div>
</div>

<script>
	$().ready(function(){
		$("#modifypwdForm").validate({
			rules : {
					oldpassword : {
						required : true,
						rangelength : [ 6, 20 ],
					},
					newpassword : {
						required : true,
						rangelength : [ 6, 20 ],
					},
					confirmpassword : {
						required : true,
						equalTo : "#newpassword"
					}
			},
			messages : {
					oldpassword : {
						required : "请输入旧密码",
						rangelength : "密码长度为6-20位"
					},
					newpassword : {
						required : "请输入新密码",
						rangelength : "密码长度为6-20位"
					},
					confirmpassword : {
						required : "请输入重复密码",
						equalTo : "两次输入密码不一致"
					}
			},
			submitHandler : function(modifypwdForm){
					var options = {
					    cache:false,
						success : function(res) {
						if(res=="0")
							{alert("修改失败"+res);
							$("#modifypwdForm")[0].reset();}
						else if(res == "1")
						    {alert("修改成功"+res);
						    $("#modifypwdForm")[0].reset();}
						else if(res == "2")
						    {alert("旧密码错误"+res);
						    $("#modifypwdForm")[0].reset();}
						},
						error : function() {
							alert("修改失败");
							$("#modifypwdForm")[0].reset();
						},
 					    beforeSubmit: function () {
                        $("#modifySubmit").attr('disabled',true);
                        //alert("正在注册");
                        },
                        complete:function(){//ajax请求完成时执行 
                        $("#modifySubmit").attr('disabled',false);
                    } 
					};
					// ajaxSubmit
					$(modifypwdForm).ajaxSubmit(options);
				},
				async: false
		});
	});
</script>
</body>
</html>
