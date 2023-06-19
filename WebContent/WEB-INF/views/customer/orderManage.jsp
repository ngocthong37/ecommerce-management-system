<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer | Order Manage</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/listStyle.css' />">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/reset.css' />">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/layout/sidebar.css' />">
</head>
<body>
	<div class="container-cts">
		<%@include file="/WEB-INF/views/layout/sidebar.jsp"%>
		<main class="content">
			<div class="content-container">
				<div class="list-header">
					<div class="header-breadcrumb">
						<h3 class="heading">Quản lý đơn hàng</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="">E-Commerce</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="">Đơn hàng</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Đơn hàng đã mua</li>
							</ul>
						</nav>
					</div>
				</div>
				<div class="paper-wrapper">
					<div class="table-container">
						<table>
							<thead>
								<tr>
									<th class="th-header"><span>Mã đơn hàng</span></th>
									<th class="th-header"><span>Ngày đặt hàng</span></th>
									<th class="th-header"><span>Tổng giá trị</span></th>
									<th class="th-header"><span>Trạng thái</span></th>
									<th class="th-header"><span></span></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listOrders}" var="element">
									<tr>
										<td class="td-body">${element.id}</td>
										<td class="td-body">${element.orderDate }</td>
										<td class="td-body"><span class="price">${element.orderTotal}</span></td>
										<td class="td-body">
											<div class="mui-chip">
												<span class="mui-chip-label">${element.orderStatus.status}</span>
											</div>
										</td>
										<td class="td-body">
											<div class="group-btn">
												<a
													href="${contextPath}/customer/orderManage/${element.id}.htm">
													<button class="btn--add">
														<span>Chi tiết</span>
													</button>
												</a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
	<script>
		var productPrices = document.getElementsByClassName("price");

		for (var i = 0; i < productPrices.length; i++) {
			var priceValue = parseInt(productPrices[i].innerHTML);
			var formattedPrice = priceValue.toLocaleString("vi-VN", {
				style : "currency",
				currency : "VND"
			});
			productPrices[i].innerHTML = formattedPrice;
		}
	</script>
</body>
</html>