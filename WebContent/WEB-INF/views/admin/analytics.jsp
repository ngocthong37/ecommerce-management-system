<%@page import="javax.xml.stream.events.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<title>Admin | Analytics</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/layout/sidebar.css'/>">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/reset.css' />">
<link rel="stylesheet"
	href="<c:url value='/common/assets/css/ecommerce/product/shop.css'/>">
<link rel="stylesheet"
	href="<c:url value='/common/assets/css/admin/analyticsStyle.css'/>">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
	<div class="container-cts">
		<%@include file="/WEB-INF/views/layout/sidebar.jsp"%>
		<main class="content">
			<div class="content-container">
				<h4 class="title-analytics">Hi, Welcome back 👋</h4>
				<div class="analytics-container">
					<div class="widget-container">
						<div class="wrapper-sale">
							<div class="MuiBox-root css-rrlqtp"></div>
							<h3 class="widget-title">${orderCountByStatus['COMPLETED'] != null && orderCountByStatus['COMPLETED'] > 0 ? orderCountByStatus['COMPLETED'] : 0}</h3> 
							<h6 class="widget-description">Đơn hàng giao thành công
								(tháng)</h6>
						</div>
					</div>
					<div class="widget-container">
						<div class="wrapper-customer">
							<div class="MuiBox-root css-rrlqtp"></div>
							<h3 class="widget-title">${orderCountByStatus['CANCELLED'] != null && orderCountByStatus['CANCELLED'] > 0 ? orderCountByStatus['CANCELLED'] : 0}</h3> 
							<h6 class="widget-description">Đơn hàng huỷ (tháng)</h6>
						</div>
					</div>
					<div class="widget-container">
						<div class="wrapper-item">
							<div class="MuiBox-root css-rrlqtp"></div>
							<h3 class="widget-title">${orderCountByStatus['PROCESSING'] != null && orderCountByStatus['PROCESSING'] > 0 ? orderCountByStatus['PROCESSING'] : 0}</h3> 
							<h6 class="widget-description">Đơn hàng đang giao</h6>
						</div>
					</div>
					<div class="widget-container">
						<div class="wrapper-report">
							<div class="MuiBox-root css-rrlqtp"></div>
							<h3 class="widget-title">${orderCountByStatus['ON_HOLD'] != null && orderCountByStatus['ON_HOLD'] > 0 ? orderCountByStatus['ON_HOLD'] : 0}</h3> 
							<h6 class="widget-description">Đơn hàng đang xử lý</h6>
						</div>
					</div>
					<div class="chart-container">
						<div class="chart-wrapper">
							<div class="chart-title">
								<div class="title-wrapper">
									<span class="title">Doanh thu theo tháng (2023)</span> <span
										class="title-description"></span>
								</div>
							</div>
							<div class="chart-draw">
								<canvas id="salesByMonth"></canvas>
							</div>
						</div>
					</div>
					<div class="pie-container">
						<div class="pie-wrapper">
							<div class="chart-title">
								<div class="title-wrapper">
									<span class="title">Trạng thái sản phẩm trong tháng</span>
								</div>
							</div>
							<div class="chart-draw">
								<canvas id="statusOrder"></canvas>
							</div>
						</div>
					</div>
					<!--  <div class="pie-container">
						<div class="pie-wrapper">
							<div class="chart-title">
								<div class="title-wrapper">
									<span class="title">Top sản phẩm </span>
								</div>
							</div>
							<div class="list-latest-product">
								<div class="latest-product-item">
									<div class="product-image">
										<img alt="Nike Air Force 1 NDESTRUKT"
											src="https://api-prod-minimal-v5.vercel.app/assets/images/m_product/product_1.jpg">
									</div>
									<div class="product-description">
										<span class="product-title"> <a>Nike Air Force 1
												NDESTRUKT</a>
										</span>
										<p class="product-price">
											<span class="sale-price">$97.14</span><span
												class="normal-price sale">$97.14</span>
										</p>
									</div>
								</div>
								<div class="latest-product-item">
									<div class="product-image">
										<img alt="Nike Air Force 1 NDESTRUKT"
											src="https://api-prod-minimal-v5.vercel.app/assets/images/m_product/product_1.jpg">
									</div>
									<div class="product-description">
										<span class="product-title"> <a>Nike Air Force 1
												NDESTRUKT asdfa sdf asdfa fdsa</a>
										</span>
										<p class="product-price">
											<span class="normal-price">$97.14</span>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
					-->
				</div>
			</div>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script type="text/javascript">
		var salesByMonch = document.getElementById('salesByMonth').getContext(
				'2d');
		var salesData = ${salesData};
		var productSoldData = ${productSoldData}
		var labels = [
	        'Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4',
	        'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8',
	        'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'
	    ]; 
		var dataSaleByMonth = {
			labels : labels, // Tên các tháng
			datasets : [ {
				label : 'Doanh thu',
				data : salesData, // Doanh thu theo tháng
				yAxisID : 'revenue', // ID trục y cho doanh thu
				fill : false,
				borderColor : 'rgba(0, 123, 255, 1)',
				borderWidth : 2
			}, {
				label : 'Tổng sản phẩm',
				data : productSoldData, // Tổng số lượng sản phẩm theo tháng
				yAxisID : 'quantity', // ID trục y cho số lượng sản phẩm
				fill : false,
				borderColor : 'rgba(255, 99, 132, 1)',
				borderWidth : 2
			} ]
		};
		var optionsConfigChartSalesByMonth = {
			responsive : true,
			scales : {
				y : [ {
					id : 'revenue',
					position : 'left',
					beginAtZero : true,
					ticks : {
						callback : function(value, index, values) {
							return value.toLocaleString() + ' đ';
						}
					}
				}, {
					id : 'quantity',
					position : 'right',
					beginAtZero : true,
					ticks : {
						callback : function(value, index, values) {
							return value;
						}
					}
				} ]
			}
		};
		var lineChart = new Chart(salesByMonch, {
			type : 'line',
			data : dataSaleByMonth,
			options : optionsConfigChartSalesByMonth
		});

		var orderStatusChart = document.getElementById('statusOrder')
				.getContext('2d');
		var dataOrderStatus = {
			labels : [ 'Đang xử lý', 'Đơn huỷ', 'Đang vận chuyển', "Thành công" ], // Tên các loại sản phẩm
			datasets : [ {
				data : [ ${orderCountByStatus['ON_HOLD'] != null ? orderCountByStatus['ON_HOLD'] : 0},
					${orderCountByStatus['CANCELLED'] != null ? orderCountByStatus['CANCELLED'] : 0},
					${orderCountByStatus['PROCESSING'] != null ? orderCountByStatus['PROCESSING'] : 0},
					${orderCountByStatus['COMPLETED'] != null ? orderCountByStatus['COMPLETED'] : 0} ], // Phần trăm của mỗi loại sản phẩm
				backgroundColor : [ 'rgba(255, 228, 220, 0.5)',
						'rgba(211, 245, 249, 0.5)', 'rgb(255, 242, 212, 0.5)',
						'rgba(213, 243, 230, 0.5)' ], // Màu nền của các phần tử
				borderColor : [ 'rgb(255,228,220,1)', 'rgba(211, 245, 249, 1)',
						'rgb(255, 242, 212, 1)', 'rgba(213, 243, 230, 1)' ], // Màu viền của các phần tử
				borderWidth : 1
			// Độ rộng viền của các phần tử
			} ]
		};

		var pieChart = new Chart(orderStatusChart, {
			type : 'pie',
			data : dataOrderStatus,
			options : {
				responsive : true
			}
		});
	</script>
</body>

</html>