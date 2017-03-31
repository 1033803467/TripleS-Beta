<%@page import="edu.ts.service.CustomerService"%>
<%@page import="edu.ts.service.impl.CustomerServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>  
<%@page import="edu.ts.entity.Customer"%>
<%@page import="edu.ts.dao.impl.CustomerDaoImpl"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
    <title>TripleS会员管理系统</title>
    <!-- bootstrap-daterangepicker -->
    <link href="../vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.js" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <!-- DatePicker -->
    <link href=" ../vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">

<div class="container body">
    <div class="main_container">
        <%@ include file="navFragment.jsp" %>
        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>修改会员信息</h3>
                    </div>

                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="查找...">
                                <span class="input-group-btn">
                              <button class="btn btn-default" type="button">开始!</button>
                          </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>

        <%
        String cid=request.getParameter("cid"); 
		request.setAttribute("cid",cid);
  		CustomerService custS = new CustomerServiceImpl();
  		List<Customer> listC=custS.getById(Integer.parseInt(cid));
  		request.setAttribute("customer", listC.get(0));
		%>
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>欢迎使用</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content" >

                                <form id="modifyForm" class="form-horizontal form-label-left"
									  action="../ModifyCustomerServlet?cid=${requestScope.cid}"
									  method="POST">

									<span class="section">请查看并修改用户信息</span>

									<div class="item form-group">
										<div class="row">
											<label class="control-label col-md-3 col-sm-3 col-xs-3"
												   for="userID">用户ID
											</label>
											<div class="col-md-4 col-sm-3 col-xs-3">
												<input name="userID" id="userID" class="form-control col-md-2 col-xs-2"
													   value="${requestScope.cid}"
													   type="text" readonly></input>

											</div>
											<div class="col-md-3 col-sm-3 col-xs-3"></div>
										</div>
									</div>


									<div class="item form-group">
										<div class="row">
											<label class="control-label col-md-3 col-sm-3 col-xs-3"
												   for="name">用户名
											</label>
											<div class="col-md-4 col-sm-3 col-xs-3">
												<input name="name" id="name" class="form-control col-md-2 col-xs-2"
													   value="${customer.getcName()}"
													   placeholder="请输入要修改的用户名" type="text"></input>
											</div>
											<div id="nameErrorContainer" class="col-md-3 col-sm-3 col-xs-3"></div>
										</div>
									</div>


									<div class="item form-group">
										<div class="row">
											<label class="control-label col-md-3 col-sm-3 col-xs-3"
												   for="tel">电话号
											</label>
											<div class="col-md-4 col-sm-3 col-xs-3">
												<input name="tel" id="tel" class="form-control col-md-2 col-xs-2"
													   value="${customer.getcTel()}"
													   placeholder="请输入要修改的手机号" type="text"></input>
											</div>
											<div id="telErrorContainer" class="col-md-3 col-sm-3 col-xs-3"></div>
										</div>
									</div>



									<div class="item form-group">
										<div class="row">
											<label class="control-label col-md-3 col-sm-3 col-xs-3"
												   for="email">邮箱
											</label>
											<div class="col-md-4 col-sm-3 col-xs-3">
												<input name="email" id="email" class="form-control col-md-2 col-xs-2"
													   value="${customer.getcMail()}"
													   placeholder="请输入修改的邮箱" type="text"></input>
											</div>
											<div id="emailErrorContainer" class="col-md-3 col-sm-3 col-xs-3"></div>
										</div>
									</div>




									<div class="form-group">
										<div class="row">
											<label class="control-label col-md-3 col-sm-3 col-xs-3"
												   for="birth">出生日期
											</label>
											<div class="col-md-4 col-sm-3 col-xs-3">
												<div class="input-group date form_date " data-date=""
													 data-date-format="dd MM yyyy" data-link-field="dtp_input2"
													 data-link-format="yyyy-mm-dd">
													<input  id="birth" class="form-control form-date col-md-2 col-xs-2" name="birth"
														   type="text" value="${customer.getcBirth()}"
														   readonly  placeholder="请选择修改的日期">
													<input
															type="hidden" id="dtp_input2" value="" />

												</div>
											</div>
										</div>
										<div class="ln_solid"></div>
										<div class="form-group">
											<div class="col-md-6 col-md-offset-3">
												<button id="quitModify"  class="btn btn-default">取消</button>
												<!-- <button type="reset" class="btn btn-success">重置</button> -->
												<button  type="submit" class="btn btn-warning">确认修改</button>
											</div>
										</div>
									</div>
								</form>
                            </div>

                        </div>
                     <div class="alert alert-info alert-dismissible fade in" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
                    </button>
                    <strong>修改会员信息</strong>修改会员信息后将会覆盖原来会员信息，请谨慎操作！！！
                  </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /page content -->

    </div>
</div>

<%@ include file="footer.jsp" %>
<!-- jQuery -->
<script src="../vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="../vendors/nprogress/nprogress.js"></script>
<!-- datapicker-->
<script src="../vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.js"></script>

<script src="../vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- Custom Theme Scripts -->
<script src="../build/js/custom.js"></script>
<script type="text/javascript" src="../vendors/bootstrapvalidator/bootstrapValidator.js"></script>
<script type="text/javascript" src="../vendors/bootstrapvalidator/zh_CN.js"></script>



<script type="text/javascript">
    $('.form-date').datetimepicker({
        format : 'yyyy-mm-dd',
        language : 'zh-CN',
        weekStart : 1,
        autoclose : 1,
        todayHighlight : 1,
        startView : 2,
        minView : 2,
        forceParse : 0
    });
</script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#modifyForm')
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
                            }
                        },
                        container:"#nameErrorContainer"
                    },

                    tel:{
                        message: '手机号不正确',
                        validators: {
                            notEmpty: {
                                message: '电话不能为空'
                            },
                            regexp: {
                                regexp: /^1[358]\d{9}$/,
                                message: '请输入11位手机号'
                            },
                            remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                                url: '../CheckModifyServlet',//验证地址
                                message: '手机号已注册',//提示消息
                                delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                                type: 'GET',//请求方式
                                data: function(validator) {
                                    return {
                                        tele: $("#tel").val(),
                                        cid: $("#userID").val()
                                    };
                                }
                            }

                        },
                        container:"#telErrorContainer"
                    },



                    email: {
                        validators: {
                            notEmpty: {
                                message: '邮箱不能为空'
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                                message: '邮箱不正确'
                            }
                        },
                        container:"#emailErrorContainer"
                    }
                }
            });

    });




    $("#quitModify").click(function(){

        window.history.back();

    });


</script>
	
</body>
</html>