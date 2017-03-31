<%--
  Created by IntelliJ IDEA.
  User: jose
  Date: 2017/3/8
  Time: 下午1:56
  To change this template use File | Settings | File Templates.
--%>
<%@page import="edu.ts.service.impl.CustomerServiceImpl"%>
<%@page import="edu.ts.service.CustomerService"%>
<%@page import="edu.ts.entity.Customer"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page   pageEncoding="utf-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String openid = (String)request.getAttribute("openid");
    Customer customer = null;
    CustomerService customerService = new CustomerServiceImpl();
    customer=customerService.getByOpenid(openid).get(0);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="../wx/RegisterCustomerServlet" method="post">
    <div><input type="hidden" name = "openid" value=<%=openid%>></div>
    <div>姓名：<input type="text" name="name"></div>
    <div>性别：<input type="radio" name="gender" value="0">男<input type="radio" name="gender" value="1">女</div>
    <div>出生日期：<input type="text" name="birth"></div>
    <div>手机号：<input type="text" name="tel"></div>
    <div>e-mail：<input type="text" name="mail"></div>
    <div>密码：<input type="password" name="password"></div>
    <input type="submit" name="submit" value="注册">
    <input type="reset" name="reset" value="重置">
</form>
</body>
</html>
