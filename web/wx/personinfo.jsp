<%@page import="edu.ts.entity.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String openid = request.getAttribute("openid").toString();
    
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/jquery.mobile.flatui.css" />
    <script src="js/jquery.js"></script>
    <script src="js/jquery.mobile-1.4.0-rc.1.js"></script>
    <style type="text/css">
        #f{
            text-align: center;
            display:none;
            margin:10px auto;
        }
        #f img{
            border-radius:50%
        }
        #main{
        	text-align: center;
        }
    </style>
    <script>
        $(document).ready(function(){
            $("#f").slideDown("slow");
        });
    </script>
</head>
<body>
<div data-role="page" id="pageone">
    <div data-role="header" data-theme="b">
        <h1>会员信息</h1>
    </div>
    <div data-role="main" class="ui-content" id="main">
                <c:if test="${customer.cPic == null}">
                    <c:set value="./images/user.png" target="${customer}" property="cPic"/>
                </c:if>
                <figure id="f"><img src="${customer.cPic}" alt="image" style="width: 150px;height: 150px"/></figure>
        <h3>${customer.cName}</h3>
            <c:choose>
                <c:when test="${customer.cGender == 0}">
                    <c:set value="男" var="gender"/>
                </c:when>
                <c:otherwise>
                    <c:set value="女" var="gender"/>
                </c:otherwise>
            </c:choose>
            <p><i class="fa fa-male user-profile-icon"></i>  性别:${gender}</p>
            <c:choose>
                <c:when test="${customer.cBirth == null}">
                    <c:set value="暂无资料" var="birth"/>
                </c:when>
                <c:otherwise>
                    <c:set value="${customer.cBirth}" var="birth"/>
                </c:otherwise>
            </c:choose>
            <p><i class="fa fa-birthday-cake user-profile-icon"></i>出生日期:${birth}</p>
            <p><i class="fa fa-phone user-profile-icon"></i>手机:${customer.cTel}</p>
            <p><i class="fa fa-envelope user-profile-icon"></i>邮箱:${customer.cMail}</p>
            <p><i class="fa fa-trophy user-profile-icon"></i>积分:${customer.cScore}</p>
            <p><i class="fa fa-clock-o user-profile-icon"></i>注册时间:${customer.cDate}</p>
            <label for="select-choice">推荐模式选择：</label>
        	<select name="select-choice" id="select-choice" data-native-menu="false" data-theme="b">
            <option value="0">不接受任何推荐</option>
            <option value="2">仅接受微信推送</option>
            <option value="1">仅接受店员导购</option>
            <option value="3">接受导购和推送</option>
        </select>
    </div>
    <div data-role="footer" data-theme="b">
    	<p>使用微信推送服务需开启人脸识别</p>
	</div>
</div>
<script>
	$("#select-choice").change(function(event){
		$.ajax({
		url:"RecommendStateServlet",
		type:"POST",
		data:"state="+$('#select-choice').val()+"&cid="+${customer.cId},
		});
	});
</script>

</body>
</html>
