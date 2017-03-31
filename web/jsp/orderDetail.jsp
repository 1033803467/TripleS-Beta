<%@page import="java.util.ArrayList"%>
<%@page import="edu.ts.entity.Customer"%>
<%@page import="edu.ts.entity.OrderTableItemDetail"%>
<%@page import="edu.ts.dao.OrderDao"%>
<%@page import="edu.ts.dao.impl.OrderDaoImpl"%>
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

<title>TripleS会员管理系统</title>
<!-- bootstrap-daterangepicker -->
<link
	href="./vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.js"
	rel="stylesheet">
<!-- Bootstrap -->
<link href="./vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="./vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

<!-- DatePicker -->
<link
	href=" ./vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="./vendors/nprogress/nprogress.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="./build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
	%>

	<div class="container body">
		<div class="main_container">
			<%@ include file="navFragment.jsp" %>
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>消费明细</h3>
						</div>

						<div class="title_right">

						</div>
					</div>

					<div class="clearfix"></div>
					<%
						//String odate = request.getParameter("odate");
						String oid = request.getParameter("oid");
						OrderDao od = new OrderDaoImpl();
						String odate = od.getByOrderId(Integer.parseInt(oid)).get(0).getoDate();
						request.setAttribute("oid", oid);
						//System.out.println(odate);
					%>
					<div class="row">
						<div class="col-md-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>订单信息详细描述</h2>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">

									<section class="content invoice"> <!-- title row -->
									<div class="row">
										<div class="col-xs-12 invoice-header">
											<h1>
												#${oid} <small class="pull-right">日期：${date}</small>



											</h1>
										</div>
										<!-- /.col -->
									</div>
									<!-- info row -->
									<div class="row invoice-info">
										<div class="col-sm-4 invoice-col">

											<address>
												<strong>会员名<i class="glyphicon glyphicon-user"></i>:${customer.cName }</strong>

												<br> 邮箱:<i class="fa fa-envelope-o"></i>:${customer.cMail}
												<br>手机号:<i class="glyphicon glyphicon-earphone"></i>:${customer.cTel }
											</address>
										</div>
									</div>
									<!-- /.row --> <!-- Table row -->

									<div class="row">
										<div class="col-xs-12 table">
											<table class="table table-striped">
												<thead>
													<tr>
														<th>商品数量</th>
														<th>商品名</th>
														<th>商品类别</th>
														<th>购买时间</th>
														<th>商品规格</th>

														<th>小计</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="list" items="${listOrderTID}"
														varStatus="st">
														<tr>
															<td>${list.quantity}</td>
															<td>${list.gName}</td>
															<td>${list.gCatagory}</td>
															<td>${requestScope.odate}</td>

															<td>${list.gSpecification}</td>

															<td>${list.itemsAmount}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<!-- /.col -->
									</div>
									<!-- /.row -->

									<div class="row">
										<!-- accepted payments column -->
										<!-- /.col -->

										<!-- /.col -->
									</div>
									<!-- /.row --> <!-- this row will not appear when printing -->

									</section>
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
	<script src="./vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="./vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="./vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="./vendors/nprogress/nprogress.js"></script>
	<!-- validator -->
	<script src="./vendors/validator/validator.js"></script>
	<!-- datapicker-->
	<script
		src="./vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.js"></script>

	<script
		src="./vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.zh-CN.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="./build/js/custom.min.js"></script>

</body>
</html>