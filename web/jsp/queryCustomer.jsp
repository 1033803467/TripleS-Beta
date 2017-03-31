
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="edu.ts.service.impl.CustomerServiceImpl"%>
<%@page import="edu.ts.service.CustomerService"%>
<%@page import="java.util.*"%>
<%@page import="edu.ts.entity.Customer"%>
<%@page import="edu.ts.entity.Page"%>
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
		request.setAttribute("basePath",basePath);
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
<script type="text/javascript">
function del() {
        var msg = "您真的确定要删除吗？\n\n请确认！";
        if (confirm(msg)==true){
        return true;
        }else{
        return false;
        }
        }
</script>
<body class="nav-md">

	<div class="container body">
		<div class="main_container">
			<%@ include file="navFragment.jsp" %>
			<div class=""><!--千万别删，不然会有css浮动-->
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>会员列表</h2>
									<div class="clearfix"></div>
								</div>
								<%
									CustomerService custS=new CustomerServiceImpl();
								 				List<Customer> listSet=new ArrayList<Customer>(); 
								 				listSet=custS.getAll();//获取所有用户信息
								 				request.setAttribute("listSet", listSet);
								%>
								<div class="x_content">
									<table id="datatable-buttons-customer"
										class="table table-striped table-bordered bulk_action">
										<thead>
											<tr>
												<th>会员照片</th>
												<th>会员编号</th>
												<th>会员姓名</th>
												<th>会员生日</th>
												<th>会员性别</th>
												<th>会员手机</th>
												<th>会员邮箱</th>
												<th>会员积分</th>
												<th>编辑信息</th>
											</tr>
										</thead>
										<tbody>
										<!--<c:forEach var="list" items="${listSet}" varStatus="st">
											<c:choose>
												<c:when test="${list.getcGender() == 0}">
													<c:set value="男" var="gender"/>
												</c:when>
												<c:otherwise>
													<c:set value="女" var="gender"/>
												</c:otherwise>
											</c:choose>
												<tr>
												<td>
												<c:choose>
												<c:when test="${list.getcPic() == null}">
													<c:set value="images/user.png" var="pic"/>
												</c:when>
												<c:otherwise>
													<c:set value="${list.getcPic()}" var="pic"/>
												</c:otherwise>
												</c:choose>
													<img src="${pic}" class="avatar"
														alt="无"> 
												</td>
													<td>${list.getcId()}</td>
													<td>${list.getcName()}</td>
													<td>${list.getcBirth()}</td>
													<td>${gender}</td>
													<td>${list.getcTel()}</td>
													<td>${list.getcMail()}</td>
													<td>${list.getcScore()}</td>
													<td><a
															href="<%=basePath %>CustomerDetailServlet?cid=${list.getcId()}"
															class="btn btn-primary btn-xs">
															<i class="fa  fa-folder"></i> 查看
														</a> 
														<a href="<%=basePath %>jsp/modifyCustomer.jsp?cid=${list.getcId()}"
															class="btn btn-info btn-xs">
															<i class="fa  fa-pencil"></i> 修改
														</a>
														<a href="<%=basePath%>DelCustomerServlet?cid=${list.getcId()}"
															class="btn btn-danger btn-xs">
															<i class="fa  fa-trash-o"></i> 删除
														</a>
													</td>
												</tr>
										<script type="text/javascript">
								            function getUrl(){
								            return '<%=basePath%>DelCustomerServlet?cid=${list.getcId()}';
								            }
								
								       </script>
											</c:forEach>-->
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- /page content -->
						<!-- Modular -->
						<!--<div class="modal fade bs-example-modal-sm" tabindex="-1"
							role="dialog" aria-hidden="true">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">

									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
										<h4 class="modal-title" id="myModalLabel2">删除会员信息警告</h4>
									</div>
									<div class="modal-body">
										<h4 style="color:#F00">你确认要删除会员信息吗？</h4>
										<p style="color:#F00">会员信息将被删除，请谨慎操作！！！</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-danger"
											onclick="location=getUrl()">确认</button>
									</div>

								</div>
							</div>
						</div>-->
						<!-- /Modular-->

					</div>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp" %>
		<!-- jQuery -->
		<script src="../vendors/jquery/dist/jquery.js"></script>
		<!-- Bootstrap -->
		<script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- FastClick -->
		<script src="../vendors/fastclick/lib/fastclick.js"></script>
		<!-- NProgress -->
		<script src="../vendors/nprogress/nprogress.js"></script>
		<!-- validator -->
		<script src="../vendors/validator/validator.js"></script>
		<!-- datapicker-->
		<script
			src="../vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.js"></script>

		<script
			src="../vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.zh-CN.js"></script>
		<!-- Datatables -->
		<script src="../vendors/datatables.net/js/jquery.dataTables.js"></script>
		<script
			src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
		<script
			src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
		<script
			src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
		<script
			src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
		<script
			src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
		<script
			src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
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
		<!-- Custom Theme Scripts -->
		<script src="../build/js/custom.js"></script>
</body>
</html>