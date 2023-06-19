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
<title>Shop</title>
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
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
	<div class="container-cts">
		<%@include file="/WEB-INF/views/layout/sidebar.jsp"%>
		<main class="content">
			<div class="content-container">
				<div class="list-header">
					<div class="header-breadcrumb">
						<h3 class="heading">Cửa hàng</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="">Trang chủ</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="">Cửa hàng</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Danh sách sản phẩm</li>
							</ul>
						</nav>
					</div>
				</div>
				<div class="shop-list">
					<c:forEach var="u" items="${listProduct}">
						<div class="shop-list-item">
							<div class="product-image-wrapper">
								<span class="product-status-sale">sale</span> <span
									class="wrapper"> <span class="wrapper-2"
									style="color: transparent; display: inline-block;"> <img
										class="MuiBox-root css-6jrdpz"
										alt="Relaxed Adjustable Strap Slingback Sandal"
										src="${u.productImage}">
								</span>
								</span>
							</div>
							<div class="description-product">
								<a href="product/${u.id}/detail/${u.defaultProductItem }.htm">${u.name
																}</a>
								<div class="product-price">
									<span class="sale-price"> <c:if
											test="${u.defaultSalePrice > 0 }">
									${u.defaultPrice}
									</c:if>
									</span> <span class="normal-price"><c:if
											test="${u.defaultSalePrice <= 0 }">
									${u.defaultPrice}
									</c:if> <c:if test="${u.defaultSalePrice > 0 }">
									
									${u.defaultSalePrice}
									</c:if> </span>
								</div>

							</div>
						</div>
					</c:forEach>
				</div>
			</div>
	</div>
	</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
	<script type="text/javascript">
								const descriptionProducts = document.querySelectorAll('.shop-list-item');

								descriptionProducts.forEach((product) => {
									const priceElement = product.querySelector('.normal-price');
									const saleElement = product.querySelector('.sale-price');
									const saleStatus = product.querySelector(".product-status-sale")
									saleStatus.style.display = "none";

									const priceValue = parseFloat(priceElement.textContent);

									if (saleElement) {
										const saleValue = parseFloat(saleElement.textContent);

										if (!isNaN(saleValue) && saleValue >= priceValue) {
											const formattedSalePrice = saleValue.toLocaleString();
											console.log(formattedSalePrice)
											saleElement.textContent = formattedSalePrice + " VND";
											saleStatus.style.display = "inline-flex";
										}
									}
									const formattedPrice = priceValue.toLocaleString();
									priceElement.textContent = formattedPrice + " VND";
								});
							</script>
</body>

</html>