<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
		String path1 = request.getContextPath();
		String servletPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path1 + "/";
		String jspPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path1 + "/jsp/";
	%>
<div class="col-md-3 left_col">
	<div class="left_col scroll-view">
		<div class="navbar nav_title" style="border: 0;">
			<a href="<%=jspPath %>index.jsp" class="site_title"> <i class="fa fa-paw"></i>
				<span>TripleS CRM</span> </a>
		</div>
		<div class="clearfix"></div>
		<!-- menu profile quick info -->
		<div class="profile clearfix">
			<div class="profile_pic">
				<img src="<%=jspPath %>images/img.jpg" alt="..."
					class="img-circle profile_img">
			</div>
			<div class="profile_info">
				<span>欢迎使用,</span>
				<h2>${sessionScope.username}</h2>
			</div>
		</div>
		<!-- /menu profile quick info -->
		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<ul class="nav side-menu">
					<li><a><i class="fa fa-user"></i>会员管理<span
							class="fa fa-chevron-down"></span> </a>
						<ul class="nav child_menu">
							<li><a href="<%=jspPath %>imgUpload.jsp">识别会员</a></li>
							<li><a href="<%=jspPath %>queryCustomer.jsp">会员列表</a></li>
							<li><a href="<%=jspPath %>addCustomer.jsp">添加会员</a></li>
						</ul></li>
					<li><a><i class="fa fa-edit"></i>订单管理<span
							class="fa fa-chevron-down"></span> </a>
						<ul class="nav child_menu">
							<li><a href="<%=jspPath %>orderList.jsp">订单列表</a></li>
							<li><a href="<%=jspPath %>inputOrders.jsp">导入订单</a></li>
						</ul></li>
					<li><a><i class="fa fa-shopping-cart"></i>商品管理<span
							class="fa fa-chevron-down"></span> </a>
						<ul class="nav child_menu">
							<li><a href="<%=jspPath %>goodsList.jsp">商品列表</a></li>
							<li><a href="<%=jspPath %>inputGoods.jsp">导入商品</a></li>
						</ul></li>
					<li><a><i class="fa fa-comment"></i>反馈信息<span
							class="fa fa-chevron-down"></span> </a>
						<ul class="nav child_menu">
							<li><a href="<%=jspPath %>feedbacks.jsp">反馈列表</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="top_nav">
	<div class="nav_menu">
		<nav>
			<div class="nav toggle">
				<a id="menu_toggle"> <i class="fa fa-bars"></i> </a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class=""><a href="javascript:;"
					class="user-profile dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"> <img src="<%=jspPath %>images/img.jpg" alt="">${username}<span
						class=" fa fa-angle-down"></span> </a>
					<ul class="dropdown-menu dropdown-usermenu pull-right">
						<li>
							<a href="<%=servletPath%>LogoutServlet"><i class="fa fa-sign-out pull-right"></i> 注销</a>
						</li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>