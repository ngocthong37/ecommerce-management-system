<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ecommerce | Thanh toán</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/cart/payment.css' />">
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
						<h3 class="heading">Thanh toán</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">E-Commerce</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">Thanh
										Toán</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Thanh toán</li>
							</ul>
						</nav>
					</div>
				</div>
				<form action="checkout.htm" method="post">
					<div class="container">
						<div class="radio-container">
							<div class="paper-wrapper">
								<div class="form-group">
									<div>
										<h4 class="heading">Phương thức thanh toán</h4>
									</div>
									<c:forEach var="i" items="${payment}">
										<div class="form-check">
											<input class="form-check-input" type="radio"
												name="PaymentMethod" id="${i.id }" value="${i.id }" checked>
											<label class="form-check-label" for="${i.id }">
												${i.paymentType.status } Ngày hết hạn: ${ i.expiry } </label>
										</div>
									</c:forEach>
								</div>
							</div>
							<div class="paper-wrapper">
								<div class="form-group">
									<div>
										<h4 class="heading">Phương thức vận chuyển</h4>
									</div>
									<c:forEach var="q" items="${shipping}">
										<div class="form-check">
											<input class="form-check-input" type="radio"
												name="ShippingMethod" id="${q.id}" value="${q.id }"
												onclick="getprice('${q.price}')"> <label
												class="form-check-label" for="${q.id }"> ${q.name }
												${ q.price } VND</label>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="paper-wrapper">
							<div>
								<h4 class="heading">Tổng tiền</h4>
								<h5 class="titlemenu">
									Tiền hàng: <span id="total-price">${sum} VND</span>
								</h5>
								<h5 class="titlemenu">
									Tiền vận chuyển: <span id="shipping-price"></span> 
								</h5>
								<hr>
								<h4 class="heading">
									Tổng cộng: <span id="total">${sum} VND</span>
								</h4>
							</div>
							<div style="text-align: center;">
								<button class="btn--add" type="submit"> Mua hàng</button>
							</div>
						</div>
					</div>
				</form>

				<div style="text-align: center;" class="mt-5">
					<button class="btn--add" name="back"
						onclick="window.location='${contextPath}/e-commerce/address.htm'">&#171;
						Quay lại</button>
				</div>
			</div>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
	<script>
		var shippingPrice = document.getElementById("shipping-price");
		var totalPrice = document.getElementById("total-price");
		var currentTotalPrice = parseInt(totalPrice.textContent);
	
		var currentTotal = parseInt(totalPrice.textContent).toLocaleString();
	
		totalPrice.textContent = currentTotal + " VND";
		document.getElementById("total").textContent = currentTotal + " VND";
	
		function getprice(price) {
		document.getElementById("shipping-price").innerHTML = price;
		var ship = parseInt(price);
		var currentTotal = parseInt(currentTotalPrice);
		document.getElementById("shipping-price").textContent = parseInt(ship).toLocaleString() + " VND";
		var newTotal = currentTotal + ship;
		document.getElementById("total").textContent = newTotal.toLocaleString() + " VND";
	}
	</script>
</body>
</html>