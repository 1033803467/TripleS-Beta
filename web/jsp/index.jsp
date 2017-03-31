
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
<!-- iCheck -->
<link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">

<!-- bootstrap-progressbar -->
<link
	href="../vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- JQVMap -->
<link href="../vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet" />
<!-- bootstrap-daterangepicker -->
<link href="../vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="../build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<%@ include file="navFragment.jsp" %>
			<!-- page content -->
			<div class="right_col" role="main">
				<!-- top tiles -->
				<div class="row tile_count">
					<div class="col-md-2 col-sm-3 col-xs-6 tile_stats_count">
						<span class="count_top"><i class="fa fa-user"></i>当前总会员人数</span>
						<div class="count green" id="memberTotalCount">...</div>
					</div>
					<div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
						<span class="count_top"><i class="fa fa-clock-o"></i> 总订单数</span>
						<div class="count green" id="orderTotalCount">...</div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-6 tile_stats_count">
						<span class="count_top"><i class="fa fa-user"></i> 总销售额</span>
						<div class="count green" id="salesTotalCount">...</div>
					</div>
					<div class="col-md-2 col-sm-3 col-xs-6 tile_stats_count">
						<span class="count_top"><i class="fa fa-user"></i> 未处理反馈数</span>
						<div class="count green" id="unprocessedFeedbackTotalCount">...</div>
					</div>
				</div><br />

				<div class="row">


					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
						<div class="x_panel tile fixed_height_640" align="center">
							<div class="x_title" align="center">
								<h2>会员人数</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>

									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix" align="center"></div>
							</div>
							<div class="x_content" align="center">

								<!-- 这个div里放图 -->
								<div align="center" id="memberNumChart"
									style="width: 80%;height:400px;"></div>

							</div>
						</div>
					</div>

					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
						<div class="x_panel tile fixed_height_640" align="center">
							<div class="x_title" align="center">
								<h2>订单</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>

									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix" align="center"></div>
							</div>
							<div class="x_content" align="center">

								<!-- 这个div里放图 -->
								<div align="center" id="orderChart"
									style="width: 80%;height:400px;"></div>


							</div>
						</div>
					</div>



					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
						<div class="x_panel tile fixed_height_640" align="center">
							<div class="x_title" align="center">
								<h2>销售</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>

									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix" align="center"></div>
							</div>
							<div class="x_content" align="center">

								<!-- 这个div里放图 -->
								<div align="center" id="salesChart"
									style="width: 80%;height:400px;"></div>


							</div>
						</div>
					</div>

					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
						<div class="x_panel tile fixed_height_640" align="center">
							<div class="x_title" align="center">
								<h2>反馈统计</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>

									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix" align="center"></div>
							</div>
							<div class="x_content" align="center">

								<!-- 这个div里放图 -->
								<div align="center" id="feedbackChart"
									style="width: 80%;height:400px;"></div>

							</div>
						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" align="center">
						<div class="x_panel tile fixed_height_640" align="center">
							<div class="x_title" align="center">
								<h2>性别组成</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>

									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix" align="center"></div>
							</div>
							<div class="x_content" align="center">

								<!-- 这个div里放图 -->

								<div align="center" id="genderChart"
									style="width: 80%;height:400px;"></div>


							</div>
						</div>
					</div>


					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" align="center">
						<div class="x_panel tile fixed_height_640" align="center">
							<div class="x_title" align="center">
								<h2>会员类型组成</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>

									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix" align="center"></div>
							</div>
							<div class="x_content" align="center">

								<!-- 这个div里放图 -->

								<div align="center" id="wechatChart"
									style="width: 80%;height:400px;"></div>


							</div>
						</div>
					</div>
				</div>


			</div>
			<!-- /page content -->
			<%@ include file="footer.jsp" %>
		</div>
	</div>

	<!-- jQuery -->
	<script src="../vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="../vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="../vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script src="../vendors/Chart.js/dist/Chart.min.js"></script>
	<!-- gauge.js -->
	<script src="../vendors/gauge.js/dist/gauge.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- iCheck -->
	<script src="../vendors/iCheck/icheck.min.js"></script>
	<!-- Skycons -->
	<script src="../vendors/skycons/skycons.js"></script>
	<!-- Flot -->
	<script src="../vendors/Flot/jquery.flot.js"></script>
	<script src="../vendors/Flot/jquery.flot.pie.js"></script>
	<script src="../vendors/Flot/jquery.flot.time.js"></script>
	<script src="../vendors/Flot/jquery.flot.stack.js"></script>
	<script src="../vendors/Flot/jquery.flot.resize.js"></script>
	<!-- Flot plugins -->
	<script src="../vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="../vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
	<script src="../vendors/flot.curvedlines/curvedLines.js"></script>
	<!-- DateJS -->
	<script src="../vendors/DateJS/build/date.js"></script>
	<!-- JQVMap -->
	<script src="../vendors/jqvmap/dist/jquery.vmap.js"></script>
	<script src="../vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
	<script src="../vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="../vendors/moment/min/moment.min.js"></script>
	<script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="../build/js/custom.js"></script>


	<!-- 引入echart <script src="./js/jquery-3.1.1.js"></script>-->
	<script src="../vendors/echarts/dist/echarts.js"></script>

	<!-- 引入 vintage 主题 <script src="./js/vintage.js"></script>
		<script src="./js/dark.js"></script>-->



	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var memberNumChart = echarts.init(document.getElementById('memberNumChart'));
		var orderChart = echarts.init(document.getElementById('orderChart'));
		var salesChart = echarts.init(document.getElementById('salesChart'));
		var genderChart = echarts.init(document.getElementById('genderChart'));
		var wechatChart = echarts.init(document.getElementById('wechatChart'));
		var feedbackChart = echarts.init(document.getElementById('feedbackChart'));
		// 指定图表的配置项和数据
		var option_memberNumChart = {
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
					name : '历史注册会员人数',
				}, {
					name : '当前有效会员人数',
				} ]
			},
			xAxis : {
				type : 'category',
				data : []
			},
			yAxis : {
				type : 'value'
			},
			series : [ {
				name : '历史注册会员人数',
				type : 'bar',
				data : [],
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
			}, {
				name : '当前有效会员人数',
				type : 'bar',
				data : [],
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

		// 指定图表的配置项和数据
		var option_orderChart = {
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
					name : '订单数'
				} ]
			},
			xAxis : {
				type : 'category',
				data : []
			},
			yAxis : {
				type : 'value'
			},
			series : [ {
				name : '订单数',
				type : 'bar',
				data : [],
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

		// 指定图表的配置项和数据
		var option_salesChart = {
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
					name : '销售额'
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
				name : '销售额',
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

		// 指定图表的配置项和数据
		option_genderChart = {
			title : {
				text : ''
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}: {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				x : 'center',
				data : [ '男士', '女士' ]
			},
			series : [ {
				name : '性别组成',
				type : 'pie',
				radius : [ '50%', '70%' ],
				avoidLabelOverlap : false,
				label : {
					normal : {
						show : false,
						position : 'center'
					},
					emphasis : {
						show : true,
						textStyle : {
							fontSize : '30',
							fontWeight : 'bold'
						}
					}
				},
				labelLine : {
					normal : {
						show : false
					}
				},
				data : [ {
					value : [],
					name : '男'
				}, {
					value : [],
					name : '女'
				}, ]
			} ]
		};

		// 指定图表的配置项和数据
		option_wechatChart = {
			title : {
				text : ''
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}: {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				x : 'center',
				data : [ '微信会员', '非微信会员' ]
			},
			series : [ {
				name : '性别组成',
				type : 'pie',
				radius : [ '50%', '70%' ],
				avoidLabelOverlap : false,
				label : {
					normal : {
						show : false,
						position : 'center'
					},
					emphasis : {
						show : true,
						textStyle : {
							fontSize : '30',
							fontWeight : 'bold'
						}
					}
				},
				labelLine : {
					normal : {
						show : false
					}
				},
				data : [ {
					value : [],
					name : []
				}, {
					value : [],
					name : []
				}, ]
			} ]
		};
		
		// 指定图表的配置项和数据
		var option_feedbackChart = {
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
					name : '历史反馈数量',
				}, {
					name : '未处理反馈数量',
				} ]
			},
			xAxis : {
				type : 'category',
				data : []
			},
			yAxis : {
				type : 'value'
			},
			series : [ {
				name : '历史反馈数量',
				type : 'bar',
				data : [],
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
			}, {
				name : '未处理反馈数量',
				type : 'bar',
				data : [],
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
		// 使用刚指定的配置项和数据显示图表。
		memberNumChart.setOption(option_memberNumChart);
		orderChart.setOption(option_orderChart);
		genderChart.setOption(option_genderChart);
		wechatChart.setOption(option_wechatChart);
		salesChart.setOption(option_salesChart);
		feedbackChart.setOption(option_feedbackChart);
		//异步加载图memberNumChart
		memberNumChart.showLoading();
		$.ajax({
			type : "GET",
			//请求servlet的url
			url : "../ChartServlet?type=1",
			dataType : 'json',
			success : function(res) {
				var time = new Array();
				var num = new Array();
				var num_cur = new Array();
				$.each(res, function(id, val) {
					time[id] = (val.time);
					num[id] = (val.num);
					num_cur[id] = val.num_cur;
				});
				memberNumChart.hideLoading();
				memberNumChart.setOption({
					xAxis : {
						data : time
					},
					dataZoom : {
						id : 'dataZoomX',
						type : 'slider',
						xAxisIndex : [ 0 ],
						filterMode : 'filter'
					},
					series : [ {
						// 根据名字对应到相应的系列
						name : '历史注册会员人数',
						type : 'bar',
						data : num
					}, {
						// 根据名字对应到相应的系列
						name : '当前有效会员人数',
						type : 'bar',
						data : num_cur
					} ]
				});
				//计算当前总会员人数
				var total_num_cur = 0;
				$.each(num_cur, function(index, value) {
					total_num_cur += parseInt(value);
				});
				$("#memberTotalCount").text(total_num_cur);
			}
		});

		//异步加载图orderChart
		orderChart.showLoading();
		$.ajax({
			type : "GET",
			//请求servlet的url
			url : "../ChartServlet?type=2",
			dataType : 'json',
			success : function(res) {
				var time = new Array();
				var num = new Array();
				$.each(res, function(id, val) {
					time[id] = (val.time);
					num[id] = (val.o_num);
				});
				orderChart.hideLoading();
				orderChart.setOption({
					xAxis : {
						data : time
					},
					dataZoom : {
						id : 'dataZoomX',
						type : 'slider',
						xAxisIndex : [ 0 ],
						filterMode : 'filter'
					},
					series : [ {
						// 根据名字对应到相应的系列
						name : '订单数',
						type : 'bar',
						data : num
					} ]
				});
				//计算当前总订单数
				var total_num_cur = 0;
				$.each(num, function(index, value) {
					total_num_cur += parseInt(value);
				});

				$("#orderTotalCount").text(total_num_cur);
			}
		});

		//异步加载图genderChart
		genderChart.showLoading();
		$.ajax({
			type : "GET",
			//请求servlet的url
			url : "../ChartServlet?type=3",
			dataType : 'json',
			success : function(res) {
				var sex = new Array();
				var num = new Array();
				$.each(res, function(id, val) {
					sex[id] = (val.sex);
					num[id] = (val.num);
				});
				genderChart.hideLoading();
				genderChart.setOption({
					series : [ {
						name : '性别组成',
						type : 'pie',
						radius : [ '50%', '70%' ],
						avoidLabelOverlap : false,
						label : {
							normal : {
								show : false,
								position : 'center'
							},
							emphasis : {
								show : true,
								textStyle : {
									fontSize : '30',
									fontWeight : 'bold'
								}
							}
						},
						labelLine : {
							normal : {
								show : false
							}
						},
						data : [ {
							value : num[0],
							name : sex[0]
						}, {
							value : num[1],
							name : sex[1]
						} ]
					} ]
				});
			}
		});

		//异步加载图wechatChart
		wechatChart.showLoading();
		$.ajax({
			type : "GET",
			//请求servlet的url
			url : "../ChartServlet?type=4",
			dataType : 'json',
			success : function(res) {
				var memberType = new Array();
				var num = new Array();
				$.each(res, function(id, val) {
					memberType[id] = (val.memType);
					num[id] = (val.num);

				});

				wechatChart.hideLoading();
				wechatChart.setOption({
					series : [ {
						name : '会员类型',
						type : 'pie',
						radius : [ '50%', '70%' ],
						avoidLabelOverlap : false,
						label : {
							normal : {
								show : false,
								position : 'center'
							},
							emphasis : {
								show : true,
								textStyle : {
									fontSize : '30',
									fontWeight : 'bold'
								}
							}
						},
						labelLine : {
							normal : {
								show : false
							}
						},
						data : [ {
							value : num[0],
							name : memberType[0]
						}, {
							value : num[1],
							name : memberType[1]
						} ]
					} ]
				});
			}
		});
		
		//异步加载图salesChart
		salesChart.showLoading();
		$.ajax({
			type : "GET",
			//请求servlet的url
			url : "../ChartServlet?type=5",
			dataType : 'json',
			success : function(res) {
				var month = new Array();
				var sales = new Array();
				$.each(res, function(id, val) {
					month[id] = (val.month);
					sales[id] = (val.sales);
				});
				salesChart.hideLoading();
				salesChart.setOption({
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
						name : '销售额',
						type : 'bar',
						data : sales
					} ]
				});
				//计算当前总销售额数
				var total_sales_cur = 0;
				$.each(sales, function(index, value) {
					total_sales_cur += parseInt(value);
				});

				$("#salesTotalCount").text("￥"+total_sales_cur);
			}
		});
		
		//异步加载图feedbackChart
		feedbackChart.showLoading();
		$.ajax({
			type : "GET",
			//请求servlet的url
			url : "../ChartServlet?type=7",
			dataType : 'json',
			success : function(res) {
				var month = new Array();
				var feedback = new Array();
				var feedback_unprocessed = new Array();
				$.each(res, function(id, val) {
					month[id] = (val.month);
					feedback[id] = (val.feedback);
					feedback_unprocessed[id] = val.feedback_unprocessed;
				});
				feedbackChart.hideLoading();
				feedbackChart.setOption({
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
						name : '历史反馈数量',
						type : 'bar',
						data : feedback
					}, {
						// 根据名字对应到相应的系列
						name : '未处理反馈数量',
						type : 'bar',
						data : feedback_unprocessed
					} ]
				});
				//计算当前总会员人数
				var total_feedback_unprocessed = 0;
				$.each(feedback_unprocessed, function(index, value) {
					total_feedback_unprocessed += parseInt(value);
				});
				$("#unprocessedFeedbackTotalCount").text(total_feedback_unprocessed);
			}
		});
	</script>


</body>
</html>
