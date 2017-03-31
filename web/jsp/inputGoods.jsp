
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
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String flag = String.valueOf(request.getAttribute("goodflag"));
 %>
<title>TripleS会员管理系统</title>
<!-- Bootstrap -->
<link href="<%=path %>/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="<%=path %>/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="<%=path %>/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- Dropzone.js -->
<link href="<%=path %>/vendors/dropzone/dist/min/dropzone.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="<%=path %>/build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
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
									<h2>导入商品</h2>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<div class="dropzone"></div>
									<form action="<%=basePath%>ImportFileServlet?type=2" method="post">
										<button type="submit"
											class="btn btn-primary btn-lg btn-block" disabled="disabled" id="detect">开始导入商品</button>
									</form>
					<div class="alert alert-success alert-dismissible fade in" role="alert" id="status-success" style="display:none">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
                    </button>
                    <strong>导入成功！</strong>
                  </div>
                  <div class="alert alert-danger alert-dismissible fade in" role="alert" id="status-fail" style="display:none">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
                    </button>
                    <strong>导入失败！请重新导入。</strong>
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
<%@ include file="footer.jsp" %>
	<!-- jQuery -->
	<script src="<%=path %>/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="<%=path %>/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="<%=path %>/vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="<%=path %>/vendors/nprogress/nprogress.js"></script>
	<!-- Dropzone.js -->
	<script src="<%=path %>/vendors/dropzone/dist/min/dropzone.min.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="<%=path %>/build/js/custom.min.js"></script>
	<script type="text/javascript">
			Dropzone.options.myAwesomeDropzone = false;
			Dropzone.autoDiscover = false;
			$(".dropzone").dropzone({
				url: "<%=path %>/UploadServlet?type=2",
        		maxFiles: 1,
        		maxFilesize: 100,
        		acceptedFiles: ".csv",
        		dictDefaultMessage: "请拖拽或单击窗口上传csv格式的商品信息文件",
        		dictFallbackMessage: "您的浏览器不支持拖拽文件上传",
      			dictFileTooBig: "您上传的照片文件过大 ({{filesize}}MB). 最大文件大小: {{maxFilesize}}MB.",
      			dictInvalidFileType: "请上传csv格式文件",
      			dictResponseError: "服务器连接错误：{{statusCode}}",
      			dictMaxFilesExceeded: "一次只能上传一个文件",
    			init: function() {
        			//this.on("success", function(file) {
                	//document.getElementById("status-success").style=false;
            		//});
            		this.on("success", function(file) {
                		document.getElementById("detect").disabled=false;
            		});
    			}
			});
			var flag = <%=flag%>;
			$(document).ready(function() {
				if(flag==true){
					document.getElementById("status-success").style=false;
				}
				if(flag==false){
					document.getElementById("status-fail").style=false;
				}
			});
	</script>
</body>
</html>