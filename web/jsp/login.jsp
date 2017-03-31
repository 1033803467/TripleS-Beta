<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>TripleS会员管理系统</title>

    <!-- Bootstrap -->
    <link href="./vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="./vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="./vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="./vendors/animate.css/animate.min.css" rel="stylesheet">






    <script type="text/javascript" src="<%= path%>/vendors/jquery/dist/jquery.js"></script>
    <script type="text/javascript" src="<%= path%>/vendors/bootstrap/dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%= path%>/vendors/bootstrapvalidator/bootstrapValidator.js"></script>
    <script type="text/javascript" src="<%= path%>/vendors/bootstrapvalidator/zh_CN.js"></script>


    <!-- Custom Theme Style -->
    <link href="./build/css/custom.min.css" rel="stylesheet">


    <script>
        $(document).ready(function() {
            $('#signInForm')
                .bootstrapValidator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'fa fa-check',
                        invalid: 'fa fa-times',
                        validating: 'fa fa-refresh'
                    },
                    fields: {
                        adminTel: {
                            message: '手机号不正确',
                            validators: {
                                notEmpty: {
                                    message: '手机号不能为空'
                                },
                                regexp: {
                                    regexp: /^1[358]\d{9}$/,
                                    message: '请输入合法11位手机号'
                                },
                                remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                                    url: 'CheckAdminServlet',//验证地址
                                    message: '该账号不存在！请先注册',//提示消息
                                    delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                                    type: 'GET',//请求方式
                                    data: function(validator) {
                                        return {
                                            mobile: $('adminTel').val(),
                                            type: "signInCheck"
                                        };
                                    }
                                }
                            }
                            //  container:"#adminTelErrorContainer"
                        },

                        passwd: {
                            validators: {
                                notEmpty: {
                                    message: '密码不能为空'
                                },
                                stringLength: {
                                    min: 6,
                                    max: 20,
                                    message: '密码必须6-20字符'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z0-9]+$/,
                                    message: '密码只能包含数字，大小写字母'
                                },
                            }
                            //  container:"#passwdErrorContainer"
                        }
                    }
                });

        });




        $(document).ready(function() {
            $('#signUpForm')
                .bootstrapValidator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'fa fa-check',
                        invalid: 'fa fa-times',
                        validating: 'fa fa-refresh'
                    },
                    fields: {
                        name: {
                            message: '用户名不正确',
                            validators: {
                                notEmpty: {
                                    message: '用户名不能为空'
                                },
                                stringLength: {
                                    min: 2,
                                    max: 20,
                                    message: '用户名必须2-20字符'
                                },
                            }
                        },
                        adminTel: {
                            message: '手机号不正确',
                            validators: {
                                notEmpty: {
                                    message: '手机号不能为空'
                                },
                                regexp: {
                                    regexp: /^1[358]\d{9}$/,
                                    message: '请输入合法11位手机号'
                                },
                                remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                                    url: 'CheckAdminServlet',//验证地址
                                    message: '该账号已注册过！请直接登陆',//提示消息
                                    delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                                    type: 'GET',//请求方式

                                    data: function(validator) {
                                        return {
                                            mobile: $('mobile').val(),
                                            type: "signUpCheck"
                                        };
                                    }

                                }
                            }
                        },

                        password: {
                            validators: {
                                notEmpty: {
                                    message: '密码不能为空'
                                },
                                stringLength: {
                                    min: 6,
                                    max: 20,
                                    message: '密码必须6-20字符'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z0-9]+$/,
                                    message: '密码只能包含数字，大小写字母'
                                },
                            }
                        },

                        repassword: {
                            validators: {
                                notEmpty: {
                                    message: '密码不能为空'
                                },
                                stringLength: {
                                    min: 6,
                                    max: 20,
                                    message: '密码必须6-20字符'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z0-9]+$/,
                                    message: '密码只能包含数字，大小写字母'
                                },
                                identical: {
                                    field: 'password',
                                    message: '两次输入密码不一致'
                                }
                            }
                        }
                    }
                });

        });
    </script>




</head>

<body class="login">
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form role="form" action="./LoginServlet" method="post" id="signInForm" class="form-horizontal">
                    <h1 >管理员账号登录</h1>


                    <div class="form-group " >
                        <div class="row">

                            <label class="control-label " for="adminTel"></label>

                            <div class="col-md-12 col-sm-12 col-xs-12" >
                                <input id="adminTel" name="adminTel" type="text" class="form-control col-md-10 col-sm-10 col-xs-10" placeholder="手机号"/>
                            </div>

                        </div>

                    </div>




                    <div class="form-group">
                        <div class="row">

                            <label class="control-label " for="passwd"></label>

                            <div class="col-md-12 col-sm-12 col-xs-12" >
                                <input id="passwd"  name="passwd" type="password" type="text" class="form-control col-md-10 col-sm-10 col-xs-10" placeholder="密码"/>
                            </div>

                        </div>
                    </div>



                    <div class="form-group">
                        <div class="row">
                            <div >
                                <input class="btn btn-success col-md-9 col-sm-9 col-xs-9" type="submit" value="登录"></input>
                            </div>
                        </div>
                    </div>




                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link">没有管理员账号?
                            <a href="#signup" class="to_register" style="color:#169f85"> 立即注册 </a>
                        </p>

                        <div class="clearfix"></div>
                        <br />

                        <div>
                            <h1><i class="fa fa-paw"></i> TripleS</h1>
                            <p>©2017 All Rights Reserved. TripleS Group  <a style="color:#169f85">Contact Us!   </a> <a style="color:#169f85">Privacy and Terms</a></p>

                        </div>
                    </div>
                </form>
            </section>
        </div>

        <div id="register" class="animate form registration_form">
            <section class="login_content">
                <form action="./RegisterServlet" method="post" id="signUpForm">
                    <h1>管理员账号注册</h1>


                    <div class="form-group" >
                        <div class="row">


                            <label class="control-label" for="name"></label>

                            <div class="col-md-12 col-sm-12 col-xs-12" >
                                <input id="name" name="name" type="text" class="form-control col-md-12 col-sm-12 col-xs-12"  placeholder="用户名"/>
                            </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="row">


                            <label class="control-label" for="adminTel"></label>

                            <div class="col-md-12 col-sm-12 col-xs-12" >
                                <input id="adminTel" type="text" class="form-control col-md-12 col-sm-12 col-xs-12" name="adminTel" placeholder="手机号"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">


                            <label class="control-label" for="passwd"></label>

                            <div class="col-md-12 col-sm-12 col-xs-12" >
                                <input id="passwd" type="password" class="form-control  col-md-12 col-sm-12 col-xs-12" name="password" placeholder="密码"/>
                            </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="row">
                            <label class="control-label" for="repasswd"></label>
                            <div class="col-md-12 col-sm-12 col-xs-12" >
                                <input id="repasswd" type="password" class="form-control col-md-12 col-sm-12 col-xs-12" name="repassword" placeholder="重复密码"/>
                            </div>
                        </div>
                    </div>



                    <div class="form-group">
                        <div class="row">
                            <input class="btn btn-success col-md-9 col-sm-9 col-xs-9"  type="submit" value="注册"></input>
                        </div>
                    </div>


                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link">已有管理员账号 ?
                            <a href="#signin" class="to_register" style="color:#169f85">立即登录 </a>
                        </p>

                        <div class="clearfix"></div>
                        <br />

                        <div>
                            <h1><i class="fa fa-paw"></i> TripleS</h1>
                            <p>©2017 All Rights Reserved. TripleS Group  <a style="color:#169f85">Contact Us!   </a> <a style="color:#169f85">Privacy and Terms</a></p>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
</body>
</html>
