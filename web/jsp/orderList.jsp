
<%@page import="edu.ts.entity.OrderTableItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="edu.ts.service.impl.OrderServiceImpl"%>
<%@page import="edu.ts.service.OrderService"%>
<%@page import="java.util.*"%>
<%@page import="edu.ts.entity.Order"%>
<%@page import="edu.ts.entity.OrderDetail"%>
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
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
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
<!-- Datatables -->
<link
	href="../vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css"
	rel="stylesheet">
</head>

<body class="nav-md">


	<div class="container body">
		<div class="main_container">
			<%@ include file="navFragment.jsp" %>
			<%
				List<OrderTableItem> listOS = new ArrayList<OrderTableItem>();
				OrderService os = new OrderServiceImpl();
				listOS = os.getAllOrderTableItem();
				request.setAttribute("listOS", listOS);
			%>
			<!-- page content -->
			<div class="right_col" role="main">
			<div class=""><!--千万别删，不然会有css浮动-->
				<div class="col-md-12 col-sm-12 col-xs-12 col-lg-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>消费记录列表 </h2>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<table id="datatable-buttons-order"
								class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>订单编号</th>
										<th>客户编号</th>
										<th>客户名</th>
										<th>订单日期</th>
										<th>消费门店</th>
										<th>订单总额</th>
										<th>查看订单</th>
									</tr>
								</thead>
								<tbody>
									<!--<c:forEach var="list" items="${listOS}" varStatus="st">
										<tr>
											<td>${list.getoId()}</td>
											<td>${list.getcName()}</td>
											<td>${list.getoDate()}</td>
											<td>${list.getAddress()}</td>
											<td>${list.getAmount()}</td>
											<td><a class="btn btn-primary" href="../OrderDetailServlet?oid=${list.getoId()}"><i class="fa  fa-folder"></i> 查看</a>
											</td>
										</tr>
									</c:forEach>-->
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- /page content -->

			</div>
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
	<!-- validator -->
	<script src="../vendors/validator/validator.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="../build/js/custom.js"></script>
	<!-- Datatables -->
	<script src="../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script
		src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script
		src="../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
	<script
		src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
	<script
		src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
	<script
		src="../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
	<script src="../vendors/jszip/dist/jszip.min.js"></script>
	<script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
	<script src="../vendors/pdfmake/build/vfs_fonts.js"></script>
	<script type="text/javascript">

	</script>
</body>
</html>