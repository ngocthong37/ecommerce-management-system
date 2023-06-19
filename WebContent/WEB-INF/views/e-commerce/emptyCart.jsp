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
<title>Ecommerce | Giỏ hàng</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/cart/cart.css' />">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/reset.css' />">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/layout/sidebar.css' />">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container-cts">
		<%@include file="/WEB-INF/views/layout/sidebar.jsp"%>
		<main class="content">
			<div class="list-header">
				<div class="header-breadcrumb">
					<h3 class="heading">Giỏ hàng</h3>
					<nav aria-label="breadcrumb">
						<ul class="breadcrumb">
							<li class="breadcrumb-item"><a class="breadcrumb__link"
								href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">E-Commerce</a></li>
							<li class="breadcrumb__divider"></li>
							<li class="breadcrumb__item"><a class="breadcrumb__link"
								href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">Thanh
									Toán</a></li>
							<li class="breadcrumb__divider"></li>
							<li class="breadcrumb__item">Giỏ hàng</li>
						</ul>
					</nav>
				</div>
			</div>
			<div class="row">
				<div class="col-xl-8 col-md mt-3 mb-3">
					<div class="paper-wrapper">
						<div class="table-container">
							<img src="<c:url value ='/common/images/empty.png'/>" alt="">
						</div>
						<div>
							<div style="text-align: center;">
								<button class="btn--add" name="back"
									onclick="window.location='${contextPath}/e-commerce/shop.htm'">Tiếp
									tục mua hàng</button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-4 mt-3 mb-3">
					<div class="paper-wrapper"">
						<div>
							<h4 class="col-sm-12 title">Tổng tiền</h4>
							<h5>Tổng tiền hàng: VND</h5>
							<hr>
							<h4>Tổng cộng: VND</h4>
						</div>
						<div style="text-align: center;">
								<button class="btn--add" name="checkOut">Mua
									hàng</button>
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
</body>
</html>