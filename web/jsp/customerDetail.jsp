<%@page import="edu.ts.service.impl.CustomerServiceImpl"%>
<%@page import="edu.ts.service.CustomerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="edu.ts.entity.OrderTableItem"%>
<%@page import="edu.ts.entity.Customer"%>
<%@page import="edu.ts.entity.Feedback"%>
<%@page import="edu.ts.entity.MostPurchasedGood"%>
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
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	CustomerService cs = new CustomerServiceImpl();
	int cid = 0;
	try{
		cid = Integer.parseInt(request.getParameter("cid"));
		request.setAttribute("cid",cid);
	}catch(NumberFormatException e){
		cid = Integer.parseInt(request.getAttribute("cid").toString());
	}
	String openid = cs.getById(cid).get(0).getcOpenid();
%>
<title>TripleS会员管理系统</title>
<!-- Bootstrap -->
<link href="<%=path %>/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="<%=path %>/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="<%=path %>/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- Custom Theme Style -->
<link href="<%=path %>/build/css/custom.min.css" rel="stylesheet">

</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">

			<%@ include file="navFragment.jsp"%>

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>会员详情</h2>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<div class="col-md-3 col-sm-3 col-xs-12 profile_left">
										<div class="profile_img">
											<div id="crop-avatar">
												<c:if test="${customer.cPic == null}">
													<c:set value="jsp/images/user.png" target="${customer}" property="cPic"/>
												</c:if>
												<img class="img-responsive avatar-view"
													src="${customer.cPic}" alt="暂无用户照片">
											</div>
										</div>
										<h3>${customer.cName}</h3>
										<ul class="list-unstyled user_data">
											<c:choose>
												<c:when test="${customer.cGender == 0}">
													<c:set value="男" var="gender"/>
												</c:when>
												<c:otherwise>
													<c:set value="女" var="gender"/>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${customer.cIsrec == 0}">
													<c:set value="不接受任何推荐服务" var="cIsrec"/>
												</c:when>
												<c:when test="${customer.cIsrec == 1}">
													<c:set value="仅接受导购服务" var="cIsrec"/>
												</c:when>
												<c:when test="${customer.cIsrec == 2}">
													<c:set value="仅接受手机推送服务" var="cIsrec"/>
												</c:when>
												<c:otherwise>
													<c:set value="接受手机推送和导购服务" var="cIsrec"/>
												</c:otherwise>
											</c:choose>
											<li><i class="fa fa-male user-profile-icon"></i>  性别:${gender}</li>
											<c:choose>
												<c:when test="${customer.cBirth == null}">
													<c:set value="暂无资料" var="birth"/>
												</c:when>
												<c:otherwise>
													<c:set value="${customer.cBirth}" var="birth"/>
												</c:otherwise>
											</c:choose>
											<li class="m-top-xs"><i class="fa fa-birthday-cake user-profile-icon"></i>出生日期:${birth}</li>
											<li class="m-top-xs"><i class="fa fa-phone user-profile-icon"></i>手机:${customer.cTel}</li>
											<li class="m-top-xs"><i class="fa fa-envelope user-profile-icon"></i>邮箱:${customer.cMail}</li>
											<li class="m-top-xs"><i class="fa fa-trophy user-profile-icon"></i>积分:${customer.cScore}</li>
											<li class="m-top-xs"><i class="fa fa-paper-plane user-profile-icon"></i>推荐形式:${cIsrec}</li>
											<li class="m-top-xs"><i class="fa fa-clock-o user-profile-icon"></i>注册时间:${customer.cDate}</li>
										</ul>
										<a class="btn btn-success" href="<%=basePath%>jsp/modifyCustomer.jsp?cid=${customer.cId}"><i class="fa fa-edit m-right-xs"></i>编辑用户</a> 
										<a class="btn btn-success" disabled="disabled" id="pushRec"><i class="fa fa-paper-plane m-right-xs"></i>推荐商品</a> <br/>
									</div>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<div class="profile_title">
											<div class="col-md-6">
												<h2>用户消费分析</h2>
											</div>
										</div>

										<!-- start of user-activity-graph -->
										<div id="consumeChart" style="width:100%; height:280px;"></div>
										<!-- end of user-activity-graph -->
										<div class="" role="tabpanel" data-example-id="togglable-tabs">
											<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
												<li role="presentation" class="active"><a
													href="#tab_content1" id="home-tab1" role="tab"
													data-toggle="tab" aria-expanded="true">用户反馈</a></li>
												<li role="presentation" class=""><a
													href="#tab_content2" id="home-tab2" role="tab"
													data-toggle="tab" aria-expanded="false">商品推荐</a></li>
												<li role="presentation" class=""><a
													href="#tab_content3" role="tab" id="profile-tab"
													data-toggle="tab" aria-expanded="false">用户最近消费</a></li>
												<li role="presentation" class=""><a
													href="#tab_content4" role="tab" id="profile-tab2"
													data-toggle="tab" aria-expanded="false">用户喜爱物品</a></li>
											</ul>
											<div id="myTabContent" class="tab-content">
												<!-- 用户反馈 -->
												<div role="tabpanel" class="tab-pane fade active in"
													id="tab_content1" aria-labelledby="home-tab1">
													<c:forEach var="list" items="${feeedbackList}"
														varStatus="st">
														<ul class="messages">
															<li>
																<div class="message_date">
																	<h5 class="date text-info">${list.fDate}</h5>
																</div>
																<div class="message_wrapper">
																	<h4 class="heading">${customer.cName}</h4>
																	<h6 class="heading">处理状态:${list.fState}</h6>
																	<blockquote class="message">${list.fMessage}</blockquote>
																	<br />
																</div></li>
														</ul>
													</c:forEach>

												</div>
												<!-- /用户反馈 -->

												<!-- 商品推荐 -->
												<div role="tabpanel" class="tab-pane fade" id="tab_content2"
													 aria-labelledby="profile-tab">

													<div class="col-md-55">
														<div class="thumbnail" style="height:auto">
															<div class="image view view-first">
																<img name="rpic" style="width: 100%; display: block;"
																	 src="<%=path %>/jsp/images/loading.gif" alt="image" />
																<div class="mask"
																	 style="height:100%;text-align: center">
																	<a  name="rid" href="#"
																		style="display: inline-block;padding-top:25px"><i
																			class="fa fa-plus fa-5x"></i> </a>
																</div>
															</div>
															<div class="caption">
																<p name="rname">正在计算中...</p>
																<p name="rbrand">正在计算中...</p>
															</div>
														</div>
													</div>

													<div class="col-md-55">
														<div class="thumbnail" style="height:auto">
															<div class="image view view-first">
																<img name="rpic" style="width: 100%; display: block;"
																	 src="<%=path %>/jsp/images/loading.gif" alt="image" />
																<div class="mask"
																	 style="height:100%;text-align: center">
																	<a  name="rid" href="#"
																		style="display: inline-block;padding-top:25px"><i
																			class="fa fa-plus fa-5x"></i> </a>
																</div>
															</div>
															<div class="caption">
																<p name="rname">正在计算中...</p>
																<p name="rbrand">正在计算中...</p>
															</div>
														</div>
													</div>

													<div class="col-md-55">
														<div class="thumbnail" style="height:auto">
															<div class="image view view-first">
																<img name="rpic" style="width: 100%; display: block;"
																	 src="<%=path %>/jsp/images/loading.gif" alt="image" />
																<div class="mask"
																	 style="height:100%;text-align: center">
																	<a  name="rid" href="#"
																		style="display: inline-block;padding-top:25px"><i
																			class="fa fa-plus fa-5x"></i> </a>
																</div>
															</div>
															<div class="caption">
																<p name="rname">正在计算中...</p>
																<p name="rbrand">正在计算中...</p>
															</div>
														</div>
													</div>


													<div class="col-md-55">
														<div class="thumbnail" style="height:auto">
															<div class="image view view-first">
																<img name="rpic" style="width: 100%; display: block;"
																	 src="<%=path %>/jsp/images/loading.gif" alt="image" />
																<div class="mask"
																	 style="height:100%;text-align: center">
																	<a  name="rid" href="#"
																	   style="display: inline-block;padding-top:25px"><i
																			class="fa fa-plus fa-5x"></i> </a>
																</div>
															</div>
															<div class="caption">
																<p name="rname">正在计算中...</p>
																<p name="rbrand">正在计算中...</p>
															</div>
														</div>
													</div>
												</div>
												<!-- /商品推荐 -->

												<!-- 用户最近消费 -->
												<div role="tabpanel" class="tab-pane fade" id="tab_content3"
													aria-labelledby="profile-tab">
													<table class="data table table-striped no-margin">
														<thead>
															<tr>
																<th>订单编号</th>
																<th>客户名</th>
																<th>订单日期</th>
																<th>消费门店</th>
																<th>订单总额</th>
																<th>查看订单</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="list" items="${OrderList}" varStatus="st">
																<tr>
																	<td>${list.oId}</td>
																	<!-- 获取订单信息 -->
																	<td>${list.cName}</td>
																	<!-- 获取订单信息 -->
																	<td>${list.oDate}</td>
																	<!-- 获取订单信息 -->
																	<td>${list.address}</td>
																	<!-- 获取订单信息 -->
																	<td>${list.amount}</td>
																	<!-- 获取订单信息 -->
																	<td><a class="btn btn-primary"
																		href="<%=basePath %>OrderDetailServlet?oid=${list.getoId()}&cid=${list.getcId()}&odate=${list.getoDate()}">查看</a>
																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
												<!-- /用户最近消费 -->

												<!-- 用户最喜欢商品 -->
												<div role="tabpanel" class="tab-pane fade" id="tab_content4"
													aria-labelledby="profile-tab">

													<c:forEach var="list" items="${mostGoodList}"
														varStatus="st">
														<div class="col-md-55">
															<div class="thumbnail" style="height:auto">
																<div class="image view view-first">
																	<img style="width: 100%; display: block;"
																		src="${list.gPic}" alt="image" />
																	<div class="mask"
																		style="height:100%;text-align: center">
																		<a href="<%=basePath %>GoodDetailServlet?gid=${list.gId}"
																			style="display: inline-block;padding-top:25px"><i
																			class="fa fa-plus fa-5x"></i> </a>
																	</div>
																</div>
																<div class="caption">
																	<p>商品名:${list.gName}</p>
																	<p>品牌:${list.gBrand}</p>
																	<p>购买次数:${list.purchaseCount}</p>
																</div>
															</div>
														</div>
													</c:forEach>
												</div>
												<!-- /用户最喜欢商品 -->
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
	<!-- morris.js -->
	<script src="<%=path %>/vendors/raphael/raphael.min.js"></script>
	<script src="<%=path %>/vendors/morris.js/morris.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script src="<%=path %>/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="<%=path %>/vendors/moment/min/moment.min.js"></script>
	<script src="<%=path %>/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="<%=path %>/build/js/custom.min.js"></script>
	<script src="<%=path %>/vendors/echarts/dist/echarts.js"></script>
	<script type="text/javascript">
		var consumeChart = echarts.init(document.getElementById('consumeChart'));
		// 指定图表的配置项和数据
		var option_consumeChart = {
			title : {
				text : ''
			},
			tooltip : {},
			toolbox : {
				feature : {
					dataView : {
						show : true,
						readOnly : false
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					}
				}
			},

			legend : {
				data : [ {
					name : '金额'
				} ]
			},
			xAxis : {
				type : 'category',
				data : [ 1, 2, 3, 4, 5 ]
			},
			yAxis : {
				type : 'value'
			},
			series : [{
				name : '消费金额',
				type : 'bar',
				data : [ 10, 19, 29, 23, 7 ],
				markPoint: {
	                data: [
	                    {type: 'max', name: '最大值'},
	                    {type: 'min', name: '最小值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name: '平均值'}
	                ]
	            }
			} ]
		};
		consumeChart.setOption(option_consumeChart);
		//异步加载图consumeChart
		consumeChart.showLoading();
		$.ajax({
			type : "GET",
			//请求servlet的url
			url : "<%=basePath%>ChartServlet?type=6&cid=<%=cid%>",
			dataType : 'json',
			success : function(res) {
				var month = new Array();
				var consume = new Array();
				$.each(res, function(id, val) {
					month[id] = (val.month);
					consume[id] = (val.consume);
				});
				consumeChart.hideLoading();
				consumeChart.setOption({
					xAxis : {
						data : month
					},
					dataZoom : {
						id : 'dataZoomX',
						type : 'slider',
						xAxisIndex : [ 0 ],
						filterMode : 'filter'
					},
					series : [ {
						// 根据名字对应到相应的系列
						name : '消费金额',
						type : 'bar',
						data : consume
					} ]
				});
				//计算当前总销售额数
				//var total_sales_cur = 0;
				//$.each(sales, function(index, value) {
					//total_sales_cur += parseInt(value);
				//});

				//$("#salesTotalCount").text("￥"+total_sales_cur);
			}
		});
	</script>



	<script>
        //异步加载推荐商品数据

            $.ajax({
                type : "GET",
                //请求servlet的url
                url : "<%=basePath%>RecommenderServlet?cid=<%=cid%>",
                dataType : 'json',
                success : function(res) {
                    $.each(res,function (id, val) {
                        var gpic=val.gpic;
                        var ghref=val.ghref;
                        var gname=val.gname;
                        var gbrand=val.gbrand;
						$("img[name='rpic']").get(id).src=gpic;
                        $("a[name='rid']").get(id).href=ghref;
                        $("p[name='rname']").get(id).innerHTML="商品名："+gname;
                        $("p[name='rbrand']").get(id).innerHTML = "品牌："+gbrand;
                    });
                    var isrec = ${requestScope.customer.cIsrec};
                   if(isrec>=2){
                   		//$("#pushRec").attr("href","<%=basePath%>wx/SendRecommendServlet?cid=${customer.cId}");
                   		$("#pushRec").removeAttr("disabled");
                   }
                }
            });
           
           

	</script>
	<script text="text/javascript">
	jQuery.fn.extend({
    href2ajax: function (fn){
        $(this).click(function(){
            $.ajax({
                url: this.href,
                error: function(XMLHttpRequest, textStatus, errorThrown){
                    alert(XMLHttpRequest.responseText);
                },
                success: function(data){
                    try{
                        fn.call(this,data);
                    }catch(exception){
                        alert(data);
                    }
                }
            });
            return false;
        });
    }
});
	          $("#pushRec").href2ajax(function(){
                    $.ajax({
                   	url:"<%=basePath%>wx/SendRecommendServlet",
                   	type:"POST",
                   	data:"cid="+${customer.cId},
                    success:function(res){
                    var openid = '<%=openid%>';
                    if($(openid)!=null){
                   	if(res=="true"){
                   		alert("推荐成功");
                   	}else{
                   		alert("推荐失败");
                   		}
                   	}},
                   	error : 
                  	function(){
                  	alert("连接超时，请重新上传照片");
                  	}
                   	
                   	//async:false
                 });
           	});
	</script>

</body>
</html>