<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<title>TripleS会员管理系统</title>
<!-- bootstrap-daterangepicker -->
<link
	href="../vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.js"
	rel="stylesheet">
<!-- Bootstrap -->
<link href="../vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="../vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

<!-- DatePicker -->
<link
	href=" ../vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="../vendors/nprogress/nprogress.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="../build/css/custom.min.css" rel="stylesheet">


</head>

<body class="nav-md" onload="alertMessage()">


	<div class="container body">
		<div class="main_container">
		
		<%@ include file="navFragment.jsp" %> 
		
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>添加会员</h2>
									<div class="clearfix"></div>
								</div>
								<%
									String flag = request.getParameter("flag");
									if (flag != null) {
										request.setAttribute("flag", Boolean.valueOf(flag));
									} else {
										request.setAttribute("flag", "hell");
									}
								%>
								<div id="alertSuccess" style="display:none"
									class="alert alert-success alert-dismissible fade in"
									role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<strong>增加用户信息成功</strong> 您可以选择左边导航栏进入其他页面，也可以继续添加用户信息
								</div>

								<div id="alertFail" style="display:none"
									class="alert alert-warning alert-dismissible fade in"
									role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<strong>增加用户失败</strong> 请重新输入用户信息！！！
								</div>

								<script type="text/javascript">
									function alertMessage() {
										var flag = ${requestScope.flag};
										console.log(flag);
										if (flag == true) {
											document
													.getElementById("alertSuccess").style.display = 'block';
										} else {
											document
													.getElementById("alertFail").style.display = 'block';

										}
									}
								</script>
								<div class="x_content">

									<form class="form-horizontal form-label-left"
										action="../AddCustomerServlet" method="post" id="addForm">

										<span class="section">用户界面</span>

										<div class="item form-group">
										<div class="row">
											<label class="control-label col-md-3 col-sm-3 col-xs-3"
												for="name">用户名 
											</label>
											<div class="col-md-4 col-sm-3 col-xs-3">
												<input id="name"  class="form-control col-md-2 col-xs-2"
													name="name" placeholder="请输入用户名" 
													type="text">
											</div>
											<div id="nameErrorContainer" class="col-md-3 col-sm-3 col-xs-3"></div>
										</div>
										</div>

										<div class="item form-group">
											<label class="control-label col-md-3 col-sm-3 col-xs-12"
												for="name">性别
											</label>
											<div class="col-md-6 col-sm-6 col-xs-12">
												<label class="radio-inline"> <input type="radio"
													name="sex" id="optionsRadiosInline1" value="0" checked="checked">男
												</label> <label class="radio-inline"> <input type="radio"
													name="sex" id="optionsRadiosInline2" value="1">女
												</label>
											</div>
										</div>


										<div class="item form-group">
										<div class="row">
											<label class="control-label col-md-3 col-sm-3 col-xs-3"
												for="tel">电话 
											</label>
											<div class="col-md-4 col-sm-3 col-xs-3">
												<input id="tel" name="tel" class="form-control col-md-2 col-xs-2">
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
												<input id="email" name="email"
													class="form-control col-md-2 col-xs-2">
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
													<input id="birth" class="form-control form-date col-md-2 col-xs-2" name="birth" type="text" value="" readonly/>
													<input type="hidden" id="dtp_input2" value="" />

												</div>
											</div>
											</div>
										</div>
										<div class="ln_solid"></div>
										

										<div class="form-group">
											<div class="col-md-6 col-md-offset-3">
												<button id="quitModify"  class="btn btn-default">取消</button>
												<button  type="submit" class="btn btn-warning">确认添加</button>
											</div>
										</div>
													
									</form>
								</div>
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
	<script
		src="../vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.js"></script>

	<script
		src="../vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.zh-CN.js"></script>
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
		var name = new FormValidator({
			empty : '请输入合法日期'
		});
	</script>
	
	
	
	<script type="text/javascript">

		var date = new Date();
		var year = date.getFullYear();
		var month = parseInt(date.getMonth());
		var day = date.getDate();

        $("#birth").val(year+"-"+(month+1)+"-"+day);

	$(document).ready(function() {
	    $('#addForm')
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
	                         url: '../CheckAddServlet',//验证地址
	                         message: '手机号已注册',//提示消息
	                         delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
	                         type: 'GET',//请求方式
	                         data: function(validator) {
	                             return {
	                                 tel: $("#tel").val()
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
		
		window.location.href="./index.jsp";
		
	});
	</script>
</body>
</html>