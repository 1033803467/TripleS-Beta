
<%@page import="edu.ts.entity.Customer"%>
<%@page import="edu.ts.service.CustomerService"%>
<%@page import="edu.ts.service.impl.CustomerServiceImpl"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="edu.ts.entity.Feedback"%>
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
<!-- Bootstrap -->
<link href="../vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="../vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- bootstrap-wysiwyg -->
<link href="../vendors/google-code-prettify/bin/prettify.min.css"
	rel="stylesheet">

<!-- Custom styling plus plugins -->
<link href="../build/css/custom.css" rel="stylesheet">

 <!-- PNotify -->
<link href="../vendors/pnotify/dist/pnotify.css" rel="stylesheet">
<link href="../vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
<link href="../vendors/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">
<script type="text/javascript">
	function rightContent() {

		var send = document.getElementById("receiveMessage");
		var cname = document.getElementById("cname");
		var time = document.getElementById("time");
		var fId = document.getElementById("fId");
		var cId = document.getElementById("cId");
		var shortMessage = document.getElementById("shortMessage");
		

		var aNode = document.getElementsByName("rightcontent");

		for ( var i = 0; i < aNode.length; i++) {
			aNode[i].onclick = function() {

				var avalue = this.shape.split("|");

				cname.innerHTML = avalue[0].trim();
				time.innerHTML = avalue[1].trim();
				send.innerHTML = avalue[2].trim();
				fId.innerHTML=avalue[3].trim();
				cId.innerHTML=avalue[4].trim();
				
				
			}
		}

	}
</script>
<style>
.shortMessage {
max-width: 110px;
overflow: hidden;
text-overflow: ellipsis;
white-space: nowrap;
}
</style>
</head>

<body class="nav-md" onload="rightContent()">

	<jsp:include page="/FeedbackListServlet" flush="true" />

	<div class="container body">
		<div class="main_container">
		
			<%@ include file="navFragment.jsp" %> 
			
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>
									会员反馈
								</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<div class="row">
									<div class="col-sm-3 mail_list_column pre-scrollable">
										<c:forEach var="list" items="${feedItem}" varStatus="st">
											<a style="cursor:pointer;" name="rightcontent" 
												shape="${list.cName}|${list.feedback.fDate}|${list.feedback.fMessage}|${list.feedback.fId}|${list.feedback.cId}">
												<div class="mail_list">
													<div class="left">
														<i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
													</div>
													<div class="right">
														<h3>
															${list.cName}<small>${list.feedback.fDate}</small>

														</h3>
														<p class="shortMessage">${list.feedback.fMessage}</p>
													</div>
												</div> </a>
										</c:forEach>
									</div>
									<!-- /MAIL LIST -->

									<!-- CONTENT MAIL -->
									<div id="section2" class="col-sm-9 mail_view">
										<div class="inbox-body">
											<div class="mail_heading row">
												<div class="col-md-8">
													<p>反馈详细信息</p>
												</div>
												<div class="col-md-4 pull-right">
													<p class="date" id="time">8:02 PM 12 FEB 2014</p>
													<label id="fId" style="display: none;"></label>
													<label id="cId" style="display: none;"></label>
												</div>
												<div class="col-md-12">
													<h4 id="cname"></h4>
												</div>
											</div>

											<div id="receiveMessage" class="view-mail"
												style="height:280px;">内容</div>
											</br>
											<div class="btn-group">
												<button id="compose" class="btn btn-sm btn-primary"
													type="button">
													<i class="glyphicon glyphicon-pencil"></i> 处理
												</button>
											</div>
										</div>

									</div>
									<!-- /CONTENT MAIL -->
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /page content -->
			</div>
		</div>
	</div>

	<!-- compose -->
	<div class="compose col-md-6 col-xs-12">
		<div class="compose-header">
			编辑信息
			<button type="button" class="close compose-close">
				<span>×</span>
			</button>
		</div>

		<div class="compose-body">
			<div id="alerts"></div>

			<div class="btn-toolbar editor" data-role="editor-toolbar"
				data-target="#editor">
				<div class="btn-group">
					<a class="btn dropdown-toggle" data-toggle="dropdown" title="Font"><i
						class="fa fa-font"></i><b class="caret"></b> </a>
					<ul class="dropdown-menu">
					</ul>
				</div>

				<div class="btn-group">
					<a class="btn dropdown-toggle" data-toggle="dropdown"
						title="Font Size"><i class="fa fa-text-height"></i>&nbsp;<b
						class="caret"></b> </a>
					<ul class="dropdown-menu">
						<li><a data-edit="fontSize 5">
								<p style="font-size:17px">Huge</p> </a></li>
						<li><a data-edit="fontSize 3">
								<p style="font-size:14px">Normal</p> </a></li>
						<li><a data-edit="fontSize 1">
								<p style="font-size:11px">Small</p> </a></li>
					</ul>
				</div>

				<div class="btn-group">
					<a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i
						class="fa fa-bold"></i> </a> <a class="btn" data-edit="italic"
						title="Italic (Ctrl/Cmd+I)"><i class="fa fa-italic"></i> </a> <a
						class="btn" data-edit="strikethrough" title="Strikethrough"><i
						class="fa fa-strikethrough"></i> </a> <a class="btn"
						data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i
						class="fa fa-underline"></i> </a>
				</div>

				<div class="btn-group">
					<a class="btn" data-edit="insertunorderedlist" title="Bullet list"><i
						class="fa fa-list-ul"></i> </a> <a class="btn"
						data-edit="insertorderedlist" title="Number list"><i
						class="fa fa-list-ol"></i> </a> <a class="btn" data-edit="outdent"
						title="Reduce indent (Shift+Tab)"><i class="fa fa-dedent"></i>
					</a> <a class="btn" data-edit="indent" title="Indent (Tab)"><i
						class="fa fa-indent"></i> </a>
				</div>

				<div class="btn-group">
					<a class="btn" data-edit="justifyleft"
						title="Align Left (Ctrl/Cmd+L)"><i class="fa fa-align-left"></i>
					</a> <a class="btn" data-edit="justifycenter"
						title="Center (Ctrl/Cmd+E)"><i class="fa fa-align-center"></i>
					</a> <a class="btn" data-edit="justifyright"
						title="Align Right (Ctrl/Cmd+R)"><i class="fa fa-align-right"></i>
					</a> <a class="btn" data-edit="justifyfull"
						title="Justify (Ctrl/Cmd+J)"><i class="fa fa-align-justify"></i>
					</a>
				</div>

				<div class="btn-group">
					<a class="btn dropdown-toggle" data-toggle="dropdown"
						title="Hyperlink"><i class="fa fa-link"></i> </a>
					<div class="dropdown-menu input-append">
						<input class="span2" placeholder="URL" type="text"
							data-edit="createLink" />
						<button class="btn" type="button">Add</button>
					</div>
					<a class="btn" data-edit="unlink" title="Remove Hyperlink"><i
						class="fa fa-cut"></i> </a>
				</div>

				<div class="btn-group">
					<a class="btn" title="Insert picture (or just drag & drop)"
						id="pictureBtn"><i class="fa fa-picture-o"></i> </a> <input
						type="file" data-role="magic-overlay" data-target="#pictureBtn"
						data-edit="insertImage" />
				</div>

				<div class="btn-group">
					<a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i
						class="fa fa-undo"></i> </a> <a class="btn" data-edit="redo"
						title="Redo (Ctrl/Cmd+Y)"><i class="fa fa-repeat"></i> </a>
				</div>
			</div>

			<div id="editor" class="editor-wrapper"></div>
		</div>

		<div class="compose-footer">
			<button id="send" class="btn btn-sm btn-success" type="button">Send</button>
		</div>
	</div>
	<!-- /compose -->
<%@ include file="footer.jsp" %>
	<!-- jQuery -->
	<script src="../vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="../vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="../vendors/nprogress/nprogress.js"></script>
	<!-- bootstrap-wysiwyg -->
	<script src="../vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
	<script src="../vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
	<script src="../vendors/google-code-prettify/src/prettify.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="../build/js/custom.min.js"></script>
	
		<!-- PNotify -->
    <script src="../vendors/pnotify/dist/pnotify.js"></script>
    <script src="../vendors/pnotify/dist/pnotify.buttons.js"></script>
    <script src="../vendors/pnotify/dist/pnotify.nonblock.js"></script>
	<script type="text/javascript">
	$('#send').click(function(){
		var text=$('#editor').html();
		var fId=$('#fId').html();
		var cId=$('#cId').html();
		var content=$.ajax({
        url: "../wx/HandleFeedbackServlet",//调到与微信交接的?Servlet类
        async: true,
        type:'POST',
        data:"cid="+cId+"&fid="+fId+"&feedbackMsg="+text,
        success:function(msg){
        	if(msg=="true"){
        	
			alert("反馈信息成功");
			window.location.href="feedbacks.jsp";
			}else{
				alert("反馈信息失败");
				window.location.href="feedbacks.jsp";
			}
    	},
    	error:function(msg){
    	if(msg=="false"){
			alert("反馈信息失败");
			}
	    },
    });
});
	
</script>
</body>
</html>