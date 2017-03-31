<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String openid = (String)request.getAttribute("openid");
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="./css/jquery.mobile.flatui.css" />
<script src="./js/jquery.js"></script>
<script src="./js/jquery.mobile-1.4.0-rc.1.js"></script>
<!-- 验证js库 -->
<script src="./js/jquery.validate.js"></script>
<script src="./js/jquery.form.js"></script>

</head>
<body>
	<div data-role="page" id="pageone">
		<div data-role="header" data-theme="b">
			<h1>会员绑定</h1>
		</div>
		<div data-role="main" class="ui-content">
			<form method="post" action="BindCustomerServlet" id="bindForm">
				<div>
					<input type="hidden" name="openid" value=<%=openid%>>
				</div>
				<div class="ui-field-contain">
					<label for="tel-bind">手机号：</label> <input type="text" name="tel"
						id="tel_bind" placeholder="请输入预留手机号"> <br /> <label
						for="password-bind">密码：</label> <input type="password"
						name="password_bind" id="password_bind" placeholder="请输入预留密码">
				</div>
				<input id="BbtnAjaxSubmit" type="submit" data-inline="true"
					data-theme="a" value="提交"> <input type="reset"
					data-inline="true" data-theme="d" value="重置">
			</form>
		</div>
		<div data-role="footer" data-theme="b">
			<a href="#pagetwo" data-transition="flip"
				class="ui-btn ui-btn-right ui-mini ui-shadow ui-corner-all ui-icon-forward ui-btn-icon-right">不是老用户，我要注册</a>
		</div>
	</div>

	<div data-role="page" id="pagetwo">
		<div data-role="header" data-theme="b">
			<h1>会员注册</h1>
		</div>
		<div data-role="main" class="ui-content">
			<form id="signUpForm" method="post" action="RegisterCustomerServlet">
				<div>
					<input type="hidden" name="openid" value=<%=openid%>>
				</div>
				<div class="ui-field-contain">
					<label for="tel">姓名：</label> <input type="text" name="name"
						id="name" placeholder="请输入姓名"> <br /> <label for="gender">性别：</label>
					<fieldset data-role="controlgroup" id="gender"
						data-type="horizontal" data-theme="b">
						<input type="radio" name="gender" id="gender-male" value="0"
							checked="checked" /> <label for="gender-male">男</label> <input
							type="radio" name="gender" id="gender-female" value="1" /> <label
							for="gender-female">女</label>
					</fieldset>
					<br /> <label for="bday">生日：</label> <input type="date" name="bday"
						id="bday"> <br /> <label for="tel">手机号：</label> <input
						type="text" name="tel" id="tel" placeholder="请输入手机号"> <br />
					<label for="mail">e-mail：</label> <input type="text" name="mail"
						id="mail" placeholder="请输入e-mail"> <br /> <label
						for="password">密码：</label> <input type="password" name="password"
						id="password" placeholder="请输入密码"> <br /> <label
						for="password">重复密码：</label> <input type="password"
						name="repassword" id="repassword" placeholder="请再次输入密码"> <br />
				</div>
				<input type="submit" data-inline="true" data-theme="a" value="提交"
					id="RbtnAjaxSubmit"> <input type="reset" data-inline="true"
					data-theme="d" value="重置">
			</form>
		</div>
		<div data-role="footer" data-theme="b">
			<a href="#pageone" data-transition="flip" data-direction="reverse"
				class="ui-btn ui-btn-left ui-mini ui-shadow ui-corner-all ui-icon-back ui-btn-icon-left">我是老用户，我要绑定</a>
		</div>
	</div>


	<script>
   //绑定表单提交
     
  $.validator.addMethod("isMobile", function(value) {
		var length = value.length;
		var mobileReg = /^1[358]\d{9}$/;
		return (length == 11 && mobileReg.test(value));
	}, "请输入11位合法手机号码");
	
	$.validator.addMethod("isName", function(value){
		var nameRed = /^[\u4E00-\u9FA5]||[a-zA-Z0-9]$/;
		return (nameRed.test(value));
	},"会员用户名只能由字母，中文，数字组成");

	//老用户绑定
	 $().ready(function() {
		$("#bindForm").validate({

			rules : {
				tel : {
					required : true,
					isMobile : true
				},
				password_bind : {
					required:true,
					rangelength : [ 6, 20 ],
				}

			},
			messages : {
				tel : {
					required : "电话非空",
					isMobile : "请输入11位合法手机号码"
				},
				password_bind : {
					required : "请输入密码",
					rangelength:"密码长度为6-20位"
				}
			},
		submitHandler: function(bindForm){
			    var options = {
        success: function (res) {
					if(res =="1")
						{alert("绑定成功");
						$("#bindForm")[0].reset();
						}
					else if(res == "2")
						{alert("你不是老会员，请注册");
						$("#bindForm")[0].reset();}
					else if(res == "0")
						{alert("绑定失败");
						$("#bindForm")[0].reset();}
					else if(res == "3")
					{						
						alert("密码错误");
						$("#password_bind").val("");
					}
        },
        error:function () {
            alert("绑定失败");
            $("#bindForm")[0].reset();
        },
 		beforeSubmit: function () {
       			$("#BbtnAjaxSubmit").attr('disabled',true);
                //alert("正在注册");
                 },
        complete:function(){//ajax请求完成时执行 
                  $("#BbtnAjaxSubmit").attr('disabled',false);
                  } 
    };
            $(bindForm).ajaxSubmit(options);
			}
		});

	});
  //新用户注册
		$().ready(function() {
			$("#signUpForm").validate({

				rules : {
					name : {
						required : true,//非空
						rangelength : [ 2, 20 ],//输入用户名长度在6-20个字符之间
						isName : true
					},
					bday : {
						required : true,
						dateISO : true
					//自定义验证
					},
					tel : {
						required : true,
						isMobile : true,
						remote : {
							url : "CheckWxRegisterServlet",
							type : "GET",
							data : {
								tel : function() {
									return $("#tel").val();
								}
							}
						}
					//自定义验证
					},
					mail :{
						email:true,
						required : true
					},
					password : {
						required : true,
						rangelength : [ 6, 20 ]
					},
					repassword : {
						required : true,
						equalTo : "#password"
					}

				},
				messages : {
					name : {
						required : "用户名非空",
						rangelength : "用户名长度必须为6-20",
						isName : "会员用户名只能由字母，中文，数字组成"
					},
					bday : {
						required : "请输入生日",
						dateISO : "请输入合法生日日期"
					},
					mail: {
						required : "电话非空",
						email : "请输入合法的email"
					},
					tel : {
						required : "email非空",
						isMobile : "请输入11位合法手机号码",
						remote : "该号码已经注册过"
					},
					password : {
						required : "请输入密码",
						rangelength : "密码长度为6-20位"
					},
					repassword : {
						required : "请输入重复密码",
						equalTo : "两次输入密码不一致"
					}
				},
				submitHandler : function(signUpForm) {
					var options = {
					    cache:false,
						success : function(res) {
						if(res=="true")
							{alert("注册成功");
							$("#signUpForm")[0].reset();}
						else
						    {alert("注册失败");
						    $("#signUpForm")[0].reset();}
						},
						error : function() {
							alert("注册失败");
							$("#signUpForm")[0].reset();
						},
 					    beforeSubmit: function () {
                        $("#RbtnAjaxSubmit").attr('disabled',true);
                        //alert("正在注册");
                        },
                        complete:function(){//ajax请求完成时执行 
                        $("#RbtnAjaxSubmit").attr('disabled',false);
                    } 
					};
					// ajaxSubmit
					$(signUpForm).ajaxSubmit(options);
					//window.location.reload();
				},
				async: false
			});
		});
	</script>
</body>
</html>
