<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String openid = (String)request.getAttribute("openid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/jquery.mobile.flatui.css" />
    <script src="js/jquery.js"></script>
    <script src="js/jquery.mobile-1.4.0-rc.1.js"></script>
    <!-- 验证js库 -->
	<script src="./js/jquery.validate.js"></script>
	<script src="./js/jquery.form.js"></script>
</head>
<body>

<div data-role="page" id="pageone">
    <div data-role="header" data-theme="b">
        <!--<a href="./index.html" class="ui-btn ui-shadow ui-corner-all ui-icon-home ui-btn-icon-notext"></a>-->
        <h1>会员反馈</h1>
    </div>
    <div data-role="main" class="ui-content">
        <form method="post" action="AddFeedbackServlet" id="feedbackForm">
        <div><input type="hidden" name = "openid" id = "openid" value=<%=openid%>></div>
            <div class="ui-field-contain">
                <label for="feedback">填写反馈：</label>
                <textarea name="feedback" id="feedback"></textarea>
                <input type="submit" data-inline="true" data-theme="a" id="feedSubmit" value="提交反馈">
            </div>
        </form>
    </div>
</div>

<script>  
    $().ready(function(){
    	$("#feedbackForm").validate({
    		rules : {
    			feedback:{
    				required : true,
    				rangelength : [1,100]
    			}
    		},
    		messages : {
    			feedback : {
    				required :  "反馈不能为空",
    				rangelength : "反馈不能超过一百个字符"
    			}
    		},
    		submitHandler : function(feedSubmit){
    			var options = {
    				cache : false,
    				success : function(res){
    					if(res == "0"){
    						alert("反馈失败");
    					}else if(res == "1"){
    						alert("感谢您的反馈，客服人员会及时与您联系");
    						$("#feedbackForm")[0].reset();
    					}
    				},
    				error : function(){
    					alert("反馈失败");
    				},
    				beforeSubmit: function () {
                        $("#feedSubmit").attr('disabled',true);
                        //alert("正在注册");
                        },
                   complete:function(){//ajax请求完成时执行 
                        $("#feedSubmit").attr('disabled',false);
                    } 
    			};
    			$(feedSubmit).ajaxSubmit(options);
    		},
    		async : false
    	});
    });
</script>

</body>
</html>
