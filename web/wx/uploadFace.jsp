<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String openid = (String)request.getAttribute("openid");
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/jquery.mobile.flatui.css" />
    <!-- Font Awesome -->
    <link href="js/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="js/nprogress/nprogress.css" rel="stylesheet">
    <!-- Dropzone.js -->
    <link href="js/dropzone/dist/min/dropzone.min.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/jquery.mobile-1.4.0-rc.1.js"></script>
    <!-- FastClick -->
    <script src="js/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="js/nprogress/nprogress.js"></script>
    <!-- Dropzone.js -->
    <script src="js/dropzone/dist/min/dropzone.min.js"></script>
</head>
<body>
    <div data-role="header" data-theme="b">
        <a href="#pagetwo" data-transition="flip" class="ui-btn" data-rel="back"></a><h1>上传照片</h1>
    </div>
    <div data-role="main" class="ui-content">
    <div class="ui-field-contain">
    <form class="dropzone" id="my-dropzone"></form>
    <button id="submit-all">上传</button>
    <button id ="remove-all">移除</button>
    </div>
    </div>
<script type="text/javascript">
	Dropzone.options.myDropzone = 	{autoProcessQueue:false};
	$(".dropzone").dropzone({
	//不要立即上传
	url: "UploadFaceServlet?openid=<%=openid%>",
	maxFiles: 1,
    maxFilesize: 100,
    acceptedFiles: ".jpg",
    dictDefaultMessage: "请单击窗口上传jpg格式的照片",
    dictFallbackMessage: "您的浏览器不支持拖拽文件上传",
    dictFileTooBig: "您上传的照片文件过大 ({{filesize}}MB). 最大文件大小: {{maxFilesize}}MB.",
    dictInvalidFileType: "请上传jpg格式文件",
    dictResponseError: "服务器连接错误：{{statusCode}}",
    dictMaxFilesExceeded: "一次只能上传一个文件",
	init:function(){
		var submitButton = document.querySelector("#submit-all");
		myDropzone = this;
		submitButton.addEventListener("click",function(){
			//alert("上传");
			myDropzone.processQueue(); //Tell Dropzone to process all queued files.
		});
		// You might want to show the submit button only when 
    	// files are dropped here:
		this.on("addedfile",function(file){
		var _this = this;
		var removeButton = document.querySelector("#remove-all");
		removeButton.addEventListener("click",function(e){
		//alert("删除");
	   // e.preventDefault();
        //e.stopPropagation();
         // Remove the file preview.
         _this.removeFile(file);
			
		});
		// Show submit button here and/or inform user to click it.
		});
		this.on("success",function(file){
			alert("上传成功");
			$.ajax({
				url:"TrianServlet",
				type:"POST",
				data:"openid=<%=openid%>"
			});
		});
	}
	});
	

	













<%--     Dropzone.options.myAwesomeDropzone = false;
    Dropzone.autoDiscover = false;
    $(".dropzone").dropzone({
        url: "UploadFaceServlet?openid=<%=openid%>",
        maxFiles: 1,
        maxFilesize: 10,
        acceptedFiles: ".jpg",
        dictDefaultMessage: "请单击窗口上传jpg格式的照片",
        dictFallbackMessage: "您的浏览器不支持拖拽文件上传",
        dictFileTooBig: "您上传的照片文件过大 ({{filesize}}MB). 最大文件大小: {{maxFilesize}}MB.",
        dictInvalidFileType: "请上传jpg格式文件",
        dictResponseError: "服务器连接错误：{{statusCode}}",
        dictMaxFilesExceeded: "一次只能上传一个文件",
        init: function() {
            this.on("success", function(file) {
                alert("上传成功");//成功后处理
            });
        }
    });   --%>
</script>
</body>
</html>
