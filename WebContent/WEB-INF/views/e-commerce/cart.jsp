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
							<table>
								<thead>
									<tr>
										<th class="th-header"><span>Sản phẩm</span></th>
										<th class="th-header"><span>Số lượng</span></th>
										<th class="th-header"><span>Đơn giá</span></th>
										<th class="th-header"><span>Tổng giá</span></th>
										<th class="th-header"><span></span></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="i" begin="0" end="${shoppingCart.size() -1}">
										<tr>
											<td class="td-body"><a
												href="${contextPath}/e-commerce/product/${shoppingCart.get(i).productItem.id}.htm">${shoppingCart.get(i).productItem.product.getName()}</a></td>
											<td class="td-body"><div class="col">
													<div class="col-6">
														<div class="btn-group btn-group-justified">
															<form action="../e-commerce/cart/decrease.htm"
																method="post">
																<input type="hidden" name="productId"
																	value="${shoppingCart.get(i).getId()}">
																<button class="btn btn-outline-primary" id="decrease"
																	${shoppingCart.get(i).quantity == 1 ? 'disabled' : ''}>-</button>
															</form>
															<button disabled class="btn btn-outline-primary"
																id="quantity">${shoppingCart.get(i).quantity}</button>
															<form action="../e-commerce/cart/increase.htm"
																method="post">
																<input type="hidden" name="productId"
																	value="${shoppingCart.get(i).getId()}">
																<button class="btn btn-outline-primary" id="increase"
																	${shoppingCart.get(i).quantity == shoppingCart.get(i).productItem.quantityInStock ? 'disabled' : ''}>+</button>
															</form>
														</div>
													</div>
													<div class="col-6">
														<p>Available:${shoppingCart.get(i).productItem.quantityInStock}</p>
													</div>
												</div></td>
											<td class="td-body"><span class="price">${price.get(i)}</span></td>
											<td class="td-body"><span class="price">${price.get(i) * shoppingCart.get(i).quantity }</span>
											</td>
											<td class="td-body">
												<div class="group-btn">
													<form action="../e-commerce/cart/delete.htm" method="post">
														<input type="hidden" name="productId"
															value="${shoppingCart.get(i).getId()}">
														<button type="submit" class="btn--delete"
															onclick="return confirm('Bạn muốn xóa sản phẩm này?')">Xóa</button>
													</form>
												</div>

											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div>
							<div style="text-align: center;">
								<button class="btn--add" name="back"
									onclick="window.location='${contextPath}/e-commerce/shop.htm'">&#171;
									Tiếp tục mua hàng</button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-4 mt-3 mb-3">
					<div class="paper-wrapper"">
						<div>
							<h4 class="heading">Tổng tiền</h4>
							<h5 class="titlemenu">Tổng tiền hàng: <span class="price">${sum}</span> </h5>
							<hr>
							<h4 class="heading">Tổng cộng:<span class="price"> ${sum} </span></h4>
						</div>
						<div style="text-align: center;">
							<form action="address.htm" method="post">
								<button type="submit" class="btn--add" name="checkOut">Mua
									hàng</button>
							</form>
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
    var formattedPrice = priceValue.toLocaleString("vi-VN", { style: "currency", currency: "VND" });
    productPrices[i].innerHTML = formattedPrice;
  }
</script>
</body>
</html>