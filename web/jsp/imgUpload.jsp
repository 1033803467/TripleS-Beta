<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<!-- Bootstrap -->
<link href="<%=basePath %>vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="<%=basePath %>vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="<%=basePath %>vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- Dropzone.js -->
<link href="<%=basePath %>vendors/dropzone/dist/dropzone.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="<%=basePath %>build/css/custom.min.css" rel="stylesheet">
<script type="text/javascript">
	function getflag(){
	var flag=${flag};
		if(!flag){
			alert("未能识别或者未能上传图片，请重新上传图片");
			window.location.href="./jsp/imgUpload.jsp";
			//document.getElementById("status-fail").style=false;
		}
	}
</script>
</head>

<body class="nav-md" onload="getflag()">
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
									<h2>会员识别</h2>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<div class="dropzone"></div>
									<form action="<%=basePath%>DetectFaceServlet" method="post">
										<button type="submit"
											class="btn btn-primary btn-lg btn-block" disabled="disabled" id="detect">开始识别</button>
									</form>
									<div class="alert alert-danger alert-dismissible fade in" role="alert" id="status-fail" style="display:none">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
                    </button>
                    <strong>识别失败！</strong>
                  </div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
			</div>
			</div>
			<!-- /page content -->
			<%@ include file="footer.jsp" %>
			<!-- jQuery -->
			<script src="<%=basePath %>vendors/jquery/dist/jquery.min.js"></script>
			<!-- Bootstrap -->
			<script src="<%=basePath %>vendors/bootstrap/dist/js/bootstrap.min.js"></script>
			<!-- FastClick -->
			<script src="<%=basePath %>vendors/fastclick/lib/fastclick.js"></script>
			<!-- NProgress -->
			<script src="<%=basePath %>vendors/nprogress/nprogress.js"></script>
			<!-- Dropzone.js -->
			<script src="<%=basePath %>vendors/dropzone/dist/min/dropzone.min.js"></script>

			<!-- Custom Theme Scripts -->
			<script src="<%=basePath %>build/js/custom.min.js"></script>
			<script type="text/javascript">
			Dropzone.options.myAwesomeDropzone = false;
			Dropzone.autoDiscover = false;
			$(".dropzone").dropzone({
				url: "../UploadServlet?type=3",
        		maxFiles: 1,
        		maxFilesize: 10,
        		acceptedFiles: ".jpg",
        		dictDefaultMessage: "请拖拽或单击窗口上传jpg格式的照片",
        		dictFallbackMessage: "您的浏览器不支持拖拽文件上传",
      			dictFileTooBig: "您上传的照片文件过大 ({{filesize}}MB). 最大文件大小: {{maxFilesize}}MB.",
      			dictInvalidFileType: "请上传jpg格式照片",
      			dictResponseError: "服务器连接错误：{{statusCode}}",
      			dictMaxFilesExceeded: "一次只能上传一张照片",
    			init: function() {
        			this.on("success", function(file) {
                		document.getElementById("detect").disabled=false;
            		});
    			}
			});
			
			</script>
</body>
</html>