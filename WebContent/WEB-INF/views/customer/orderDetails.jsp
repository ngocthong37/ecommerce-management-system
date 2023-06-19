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
<title>Customer | Order Details</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/customer/orderManage.css' />">
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
						<h3 class="heading">Chi tiết đơn hàng</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="${contextPath}/e-commerce/shop.htm">E-Commerce</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="${contextPath}/customer/orderManage.htm">Quản lý đơn
										hàng</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Chi tiết đơn hàng</li>
							</ul>
						</nav>
					</div>
					<div>
						<a href="${contextPath}/customer/orderManage.htm">
							<button class="btn--add">
								<span>Quay lại</span>
							</button>
						</a>
					</div>
				</div>
				<div class="paper-wrapper">
					<div class="table-container">
						<table>
							<thead>
								<tr>

									<th class="th-header"><span>Tên sản phẩm</span></th>
									<th class="th-header"><span>Thumbnail</span></th>
									<th class="th-header"><span>Đơn giá</span></th>
									<th class="th-header"><span>Số lượng</span></th>
									<th class="th-header"><span>Thành tiền</span></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listLines}" var="element">
									<tr>

										<td class="td-body"><a
											href="${contextPath}/e-commerce/product/${element.productItem.id}.htm">${element.productItem.product.name }</a></td>
										<td class="td-body">
											<div class="table-image">
												<img src="${element.productItem.productImage}" alt="">
											</div>
										</td>
										<td class="td-body"><span class="price">${element.price }</span></td>
										<td class="td-body">${element.quantity }</td>
										<td class="td-body"><span class="price">${element.price * element.quantity}</span></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="container">
					<div class="paper-wrapper">
						<h3 class="heading">Địa chỉ</h3>
						<h4 class="titlemenu">${address }</h4>
						<h3 class="heading">Số điện thoại giao hàng</h3>
						<h4 class="titlemenu">${phone }</h4>
						<div style="text-align: center;">
							<c:if test="${test}">
								<a href="${contextPath}/customer/orderManage/edit/${id }.htm">
									<button class="btn--add">
										<span> Chỉnh sửa</span>
									</button>
								</a>
							</c:if>
						</div>
					</div>
					<div class="paper-wrapper">
						<h3 class="heading">Tổng tiền</h3>
						<h4 class="titlemenu">
							<span class="price">${sum}</span>
						</h4>
						<div style="text-align: center;">
							<c:if test="${test}">
								<a href="${contextPath}/customer/orderManage/cancel/${id}.htm">
									<button class="btn--delete">
										<span> Hủy </span>
									</button>
								</a>
							</c:if>
						</div>
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