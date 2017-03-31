<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="edu.ts.entity.Good"%>
<%@page import="edu.ts.service.impl.GoodServiceImpl"%>
<%@page import="edu.ts.service.GoodService"%>
<%@page import="java.util.*"%>
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
	%>
<title>TripleS会员管理系统</title>
<!-- bootstrap-daterangepicker -->
<link
	href="<%=basePath%>/vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.js"
	rel="stylesheet">
<!-- Bootstrap -->
<link href="<%=basePath%>/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="<%=basePath%>/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

<!-- DatePicker -->
<link
	href=" <%=basePath%>/vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="<%=basePath%>/vendors/nprogress/nprogress.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="<%=basePath%>/build/css/custom.min.css" rel="stylesheet">

<script language="javascript" type="text/javascript" charset="UTF-8"> 
	function search(){
	
	var content = document.getElementById("content");
	if(content.value){
	window.location.href="<%=basePath%>SearchGoodServlet?content="+content.value;
	
	}
}
</script>
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
							<h3>商品列表</h3>
						</div>

						<div class="title_right">
							<div
								class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
								<div class="input-group">
									<input id="content" type="text" class="form-control" placeholder="查找...">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="search()" >开始!</button> </span>
								</div>
							</div>
						</div>
					</div>

					<div class="clearfix"></div>
					<%
					String content =request.getParameter("content");
					String currentPage = request.getParameter("currentPage");
						if(content==null){
						int pageSize = 9;
						GoodService gService = new GoodServiceImpl();
						List<Good> listg = new ArrayList();
						Object[] obj = gService.queryByPage(currentPage, pageSize);

						listg = (List<Good>) obj[0];
						int record = listg.size();
						request.setAttribute("listg", listg);
						request.setAttribute("page", (Page) obj[1]);
						}else{
							content=new String(content.getBytes("ISO-8859-1"),"utf-8");
						}
						request.setAttribute("content", content);
					%>
					<div class="row">
						<div class="col-md-12">
							<div class="x_panel">
								<div class="x_content">
									<div class="row">
									 <div class="col-md-12 col-sm-12 col-xs-12 text-center"></div>
									 <div class="clearfix"></div>
										<c:forEach var="list" items="${listg}" varStatus="st">
											<div class="col-md-4 col-sm-4 col-xs-12 profile_details">
												<div class="well profile_view">
													<div class="col-sm-12">
														<h4 class="brief">
															<i>商品名:${list.getgName()}<!-- 获取商品名--> </i>
														</h4>
														<div class="left col-xs-7">
															<p>
																<strong>商品描述 </strong> ${list.getgDesc()}
															</p>
															<ul class="list-unstyled">
																<li>
																	<i class="fa fa-building"></i>
																	商品类别：${list.getgCatagory()}
																</li>
																<li>
																	<i class="glyphicon glyphicon-yen"></i>
																	:${list.getgPrice()}
																</li>
															</ul>
														</div>
														<div class="right col-xs-5 text-center">
															<img src="${list.getgPic()}" alt="商品图片"
																class="img-responsive">
															<!-- 获取商品图片-->
														</div>
													</div>
													<div class="col-xs-12 bottom text-center">
														<div class="col-xs-12 col-sm-6 emphasis">
															<p class="ratings">
																<a>4.0</a>
																<a href="#">
																	<span class="fa fa-star"></span>
																</a>
																<a href="#">
																	<span class="fa fa-star"></span>
																</a>
																<a href="#">
																	<span class="fa fa-star"></span>
																</a>
																<a href="#">
																	<span class="fa fa-star"></span>
																</a>
																<a href="#">
																	<span class="fa fa-star-o"></span>
																</a>
															</p>
														</div>
														<div class="col-xs-12 col-sm-6 emphasis">
															<a class="btn btn-primary"
																href="<%=basePath%>GoodDetailServlet?gid=${list.gId}"><i class="fa  fa-folder"></i> 查看</a>
															<!-- href表示跳转到goodDetail.jsp -->
														</div>
													</div>
												</div>
											</div>
										</c:forEach>

									</div>
								</div>
							</div>
						</div>
					</div>

					<div style="font-size:14px;" align="center">
						<c:choose>
						   <c:when test="${not empty content}"> 
						   <a id="first" href="<%=basePath%>SearchGoodServlet?currentPage=1&content=${content}">首页</a> 
						    </c:when>
						   	<c:otherwise> 
						   	<a id="first" href="<%=basePath%>PageServlet?type=3&currentPage=1">首页</a>
							</c:otherwise>
						</c:choose>
						
						<!--./loginSuccess.jsp-->
						<c:if test="${requestScope.page.currentPage>1}">
							<c:choose>
							   <c:when test="${not empty content}">  
							   <a href="<%=basePath%>SearchGoodServlet?currentPage=${requestScope.page.currentPage-1}&content=${content}">上一页</a>      
							   </c:when>
							   <c:otherwise> 
							   <a href="<%=basePath%>PageServlet?type=3&currentPage=${requestScope.page.currentPage-1}">上一页</a>
							   </c:otherwise>
							</c:choose>
							
						</c:if>
						<c:if test="${requestScope.page.currentPage<=requestScope.page.pageNumber-1}">
							<c:choose>
							   <c:when test="${not empty content}">  
							   <a href="<%=basePath%>SearchGoodServlet?currentPage=${requestScope.page.currentPage+1}&content=${content}">下一页</a>      
							   </c:when>
							   <c:otherwise> 
							   <a href="<%=basePath%>PageServlet?type=3&currentPage=${requestScope.page.currentPage+1}">下一页</a>
							   </c:otherwise>
							</c:choose>
							
						</c:if>
						
						<c:choose>
						   <c:when test="${not empty content}"> 
						   <a id="end" href="<%=basePath%>SearchGoodServlet?currentPage=${requestScope.page.pageNumber}&content=${content}">尾页 </a>
						    </c:when>
						   	<c:otherwise> 
						   	<a id="end" href="<%=basePath%>PageServlet?type=3&currentPage=${requestScope.page.pageNumber}">尾页</a>
							</c:otherwise>
						</c:choose>
						<br /> <br />
						<a>当前页：${requestScope.page.currentPage}</a>
						<a>总页数：${requestScope.page.pageNumber}</a>
					</div>

				</div>
			</div>
			<!-- /page content -->
		</div>
	</div>
<%@ include file="footer.jsp" %>
	<!-- jQuery -->
	<script src="<%=basePath%>/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="<%=basePath%>/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="<%=basePath%>/vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="<%=basePath%>/vendors/nprogress/nprogress.js"></script>
	<!-- validator -->
	<script src="<%=basePath%>/vendors/validator/validator.js"></script>
	<!-- datapicker-->
	<script
		src="<%=basePath%>/vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.js"></script>

	<script
		src="<%=basePath%>/vendors/bootstrap-daterangepicker/bootstrap-datetimepicker.zh-CN.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="<%=basePath%>/build/js/custom.min.js"></script>

</body>
</html>