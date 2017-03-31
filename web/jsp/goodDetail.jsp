
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="edu.ts.entity.Good"%>
<%@page import="edu.ts.service.impl.GoodServiceImpl"%>
<%@page import="edu.ts.service.GoodService"%>
<%@page import="java.util.*"%>
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
	href="./vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="./vendors/nprogress/nprogress.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="./build/css/custom.min.css" rel="stylesheet">
<!-- Jsbarcode -->
<script src="./vendors/jsbar/JsBarcode.all.js"></script>
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
							<h3>商品详情</h3>
						</div>
					</div>

					<div class="clearfix"></div>
					<%
						Good good = (Good) request.getAttribute("good");
						request.setAttribute("good", good);
					%>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">

								<div class="x_content">

									<div class="col-md-5 col-sm-5 col-xs-12">
										<div class="product-image">
											<img src="${requestScope.good.getgPic()}" alt=".." />
											<!--${requestScope.good.getgPic()}-->
										</div>
									</div>

									<div class="col-md-7 col-sm-7 col-xs-12"
										style="border:0px solid #e5e5e5;">

										<h2 class="prod_title">商品名：${requestScope.good.getgName()}</h2>
										<p>品牌：${requestScope.good.getgBrand()}</p>
										<p>商品规格:${requestScope.good.getgSpecification()}</p>
										<p>商品描述:${requestScope.good.getgDesc()}</p>
										

										<div class="">
											<div class="product_price">
												<h1 class="price">
													<li class="glyphicon glyphicon-yen"></li>
													${requestScope.good.getgPrice()}
												</h1>
											</div>
										</div>

									</div>


									<div class="col-md-4">

										<!--有哪些客户购买过商品-->
										<img id="imgcode"  alt="jsbar"/>
										<script type="text/javascript">
											JsBarcode("#imgcode","690${good.gId}26436086")
											  .options({font: "OCR-B"}) // Will affect all barcodes
											  .blank(15) // Create space between the barcodes
											  .render();

										</script>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /page content -->
		</div>
	</div>

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