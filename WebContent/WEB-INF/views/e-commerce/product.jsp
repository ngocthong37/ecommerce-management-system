<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<title>Product Detail</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/layout/sidebar.css' />">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/reset.css' />">
<link rel="stylesheet"
	href="<c:url value='/common/assets/css/ecommerce/product/detailProduct.css'/>">
<link rel="stylesheet"
	href="<c:url value='/common/assets/css/ecommerce/product/style.css'/>">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />

</head>

<body>
	<div class="container-cts">
		<%@include file="/WEB-INF/views/layout/sidebar.jsp"%>
		<main class="content">
			<div class="content-container">
				<!-- Header breadcrumb -->
				<div class="list-header">
					<div class="header-breadcrumb">
						<h3 class="heading">Chi tiết sản phẩm</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="${contextPath}/e-commerce/shop.htm">Trang chủ</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="${contextPath}/e-commerce/shop.htm">Sản phẩm</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">${product.name }</li>
							</ul>
						</nav>
					</div>
				</div>

				<div class="product_information_container">
					<div class="product_image">
						<div class="image_container">
							<img src="${currentProductItem.productImage }" alt="">
						</div>
						<div class="image_carosel">
							<a
								href="${contextPath}/e-commerce/product/${product.id}/detail/${currentProductItem.id}.htm">
								<div class="wrapper-image">
									<img src="${product.productImage}" alt="">
								</div>
							</a>
							<c:forEach var="productItem" items="${listProductItem}">
								<a
									href="${contextPath}/e-commerce/product/${product.id}/detail/${productItem.id}.htm">
									<div class="wrapper-image">
										<img src="${productItem.productImage}" alt="">
									</div>
								</a>
							</c:forEach>
						</div>
					</div>
					<div class="product_variation">
						<form action="${productItemId}.htm" method="POST">
							<div class="variation-layout">
								<div class="flex-column">
									<span class="product-status-out">${currentProductItem.status
																}</span>
									<h5 class="product-name">${product.name }</h5>
									<c:if test="${onSale}">
										<p class="product-price"
											style="font-weight: bold; text-decoration: line-through; text-decoration-line: line-through; text-decoration-color: black; margin-top: 10px;">
											${currentProductItem.price} VND</p>
										<p class="product-sale-price"
											style="color: #FF0000; font-weight: bold; margin-top: 10px; font-size: 1.25rem;">
											${salePrice} VND</p>
									</c:if>
									<c:if test="${!onSale}">
										<h4 class="product-price">${currentProductItem.price }VND</h4>
									</c:if>
									<hr class="product-devider">
								</div>
								<h6 class="variation-name"
									style="margin-top: 30px; margin-bottom: 30px;">Cấu hình</h6>
								<div class="flex-column">
									<div class="variation-wrapper">
										<div class="variation-radio">
											<c:forEach items="${listConfigProduct}" var="element">
												<span class="variation-radio-input"> <input
													name="productItemId" type="radio"
													value="${element.productItemId }"
													<c:if
																				test="${currentProductItem.id == element.productItemId}">checked
																			</c:if>
													id="${element.productItemId }"> <label
													class="variation-list-container"
													for="${element.productItemId }"> <a
														href="${contextPath}/e-commerce/product/${product.id}/detail/${element.productItemId}.htm"}>
															<ul class="variation-list">
																<c:forEach items="${element.listVariation}"
																	var="variation" varStatus="status">
																	<li class="list-item"><span>${variation.name}:</span>
																		<span>${element.variationValueList[status.index]}</span>
																	</li>
																</c:forEach>
															</ul>
													</a>
												</label>
												</span>
											</c:forEach>
										</div>
									</div>
									<div class="variation-wrapper">
										<h6 class="variation-name">Số lượng</h6>
										<div class="variation-select-quantity">
											<div name="quantity" class="quantity">
												<button class="quantity-button" tabindex="-1" type="button">
													<svg xmlns="http://www.w3.org/2000/svg"
														xmlns:xlink="http://www.w3.org/1999/xlink"
														aria-hidden="true" role="img"
														class="MuiBox-root css-3o0h5k iconify iconify--eva"
														width="1em" height="1em" viewBox="0 0 24 24">
																				<g id="iconifyReact438">
																					<g id="iconifyReact439">
																						<path id="iconifyReact440" fill="currentColor"
															d="M19 13H5a1 1 0 0 1 0-2h14a1 1 0 0 1 0 2Z">
																						</path>
																					</g>
																				</g>
																			</svg>
												</button>
												<input name="quantity" style="display: none;" value="1"
													id="quantity"> <span id="label-quantity">1</span>
												<button class="quantity-button" tabindex="0" type="button">
													<svg xmlns="http://www.w3.org/2000/svg"
														xmlns:xlink="http://www.w3.org/1999/xlink"
														aria-hidden="true" role="img"
														class="MuiBox-root css-3o0h5k iconify iconify--eva"
														width="1em" height="1em" viewBox="0 0 24 24">
																				<g id="iconifyReact441">
																					<g id="iconifyReact442">
																						<path id="iconifyReact443" fill="currentColor"
															d="M19 11h-6V5a1 1 0 0 0-2 0v6H5a1 1 0 0 0 0 2h6v6a1 1 0 0 0 2 0v-6h6a1 1 0 0 0 0-2Z">
																						</path>
																					</g>
																				</g>
																			</svg>
													<span
														style="overflow: hidden; pointer-events: none; position: absolute; z-index: 0; inset: 0px; border-radius: inherit;"></span>
												</button>
											</div>
											<div class="quantity-title">
												Sản phẩm sẵn có: <span class="current-product">${currentProductItem.quantityInStock
																			}</span>
											</div>
										</div>
									</div>
									<div class="cart-icon">
										<a href="${contextPath}/e-commerce/cart.htm"> <i
											class="fa fa-shopping-cart"></i> <span> ${quantityOrdered}</span>
										</a>

									</div>
									
									
									<hr class="product-devider">
									<div class="button-group">
										<button class="button--add-to-cart" tabindex="0" type="submit"
											name="addToCart">
											<span
												class="MuiButton-startIcon MuiButton-iconSizeLarge css-coclz"><svg
													xmlns="http://www.w3.org/2000/svg"
													xmlns:xlink="http://www.w3.org/1999/xlink"
													aria-hidden="true" role="img"
													class="MuiBox-root css-1t9pz9x iconify iconify--ic"
													width="1em" height="1em" viewBox="0 0 24 24">
																			<path fill="currentColor"
														d="M12 9c.55 0 1-.45 1-1V6h2c.55 0 1-.45 1-1s-.45-1-1-1h-2V2c0-.55-.45-1-1-1s-1 .45-1 1v2H9c-.55 0-1 .45-1 1s.45 1 1 1h2v2c0 .55.45 1 1 1zm-5 9c-1.1 0-1.99.9-1.99 2S5.9 22 7 22s2-.9 2-2s-.9-2-2-2zm10 0c-1.1 0-1.99.9-1.99 2s.89 2 1.99 2s2-.9 2-2s-.9-2-2-2zm-8.9-5h7.45c.75 0 1.41-.41 1.75-1.03l3.24-6.14a.998.998 0 0 0-.4-1.34a.996.996 0 0 0-1.36.41L15.55 11H8.53L4.27 2H2c-.55 0-1 .45-1 1s.45 1 1 1h1l3.6 7.59l-1.35 2.44C4.52 15.37 5.48 17 7 17h11c.55 0 1-.45 1-1s-.45-1-1-1H7l1.1-2z">
																			</path>
																		</svg></span>Thêm vào giỏ hàng<span
												class="MuiTouchRipple-root css-w0pj6f"></span>
										</button>
										<button class="button-buy-now" tabindex="0" type="submit"
											name="buyNow">
											Mua ngay<span class="MuiTouchRipple-root css-w0pj6f"></span>
										</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="promise-container">
					<div class="promise-item">
						<div class="item-icon">
							<svg xmlns="http://www.w3.org/2000/svg"
								xmlns:xlink="http://www.w3.org/1999/xlink" aria-hidden="true"
								role="img" class="MuiBox-root icon iconify iconify--ic"
								width="1em" height="1em" viewBox="0 0 24 24">
														<path fill="currentColor"
									d="m23 12l-2.44-2.79l.34-3.69l-3.61-.82l-1.89-3.2L12 2.96L8.6 1.5L6.71 4.69L3.1 5.5l.34 3.7L1 12l2.44 2.79l-.34 3.7l3.61.82L8.6 22.5l3.4-1.47l3.4 1.46l1.89-3.19l3.61-.82l-.34-3.69L23 12zM9.38 16.01L7 13.61a.996.996 0 0 1 0-1.41l.07-.07c.39-.39 1.03-.39 1.42 0l1.61 1.62l5.15-5.16c.39-.39 1.03-.39 1.42 0l.07.07c.39.39.39 1.02 0 1.41l-5.92 5.94c-.41.39-1.04.39-1.44 0z">
														</path>
													</svg>
						</div>
						<h6 class="promise-title">100% có nguồn gốc</h6>
						<p class="promise-description">Sản phẩm 100% có xuất xứ và
							nguồn gốc chính hãng</p>
					</div>
					<div class="promise-item">
						<div class="item-icon">
							<svg xmlns="http://www.w3.org/2000/svg"
								xmlns:xlink="http://www.w3.org/1999/xlink" aria-hidden="true"
								role="img" class="MuiBox-root icon iconify iconify--eva"
								width="1em" height="1em" viewBox="0 0 24 24">
														<g id="iconifyReact228">
															<g id="iconifyReact229">
																<path id="iconifyReact230" fill="currentColor"
									d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2Zm4 11h-4a1 1 0 0 1-1-1V8a1 1 0 0 1 2 0v3h3a1 1 0 0 1 0 2Z">
																</path>
															</g>
														</g>
													</svg>
						</div>
						<h6 class="promise-title">10 Đổi trả</h6>
						<p class="promise-description">Chính sách đổi trả trong vòng
							10 ngày đầu tiên</p>
					</div>
					<div class="promise-item">
						<div class="item-icon">
							<svg xmlns="http://www.w3.org/2000/svg"
								xmlns:xlink="http://www.w3.org/1999/xlink" aria-hidden="true"
								role="img" class="MuiBox-root icon iconify iconify--ic"
								width="1em" height="1em" viewBox="0 0 24 24">
														<path fill="currentColor"
									d="m11.19 1.36l-7 3.11C3.47 4.79 3 5.51 3 6.3V11c0 5.55 3.84 10.74 9 12c5.16-1.26 9-6.45 9-12V6.3c0-.79-.47-1.51-1.19-1.83l-7-3.11c-.51-.23-1.11-.23-1.62 0zm-1.9 14.93L6.7 13.7a.996.996 0 1 1 1.41-1.41L10 14.17l5.88-5.88a.996.996 0 1 1 1.41 1.41l-6.59 6.59a.996.996 0 0 1-1.41 0z">
														</path>
													</svg>
						</div>
						<h6 class="promise-title">Bảo hành một năm</h6>
						<p class="promise-description">Tất cả sản phẩm được bảo hành
							một năm</p>
					</div>
				</div>
				<div class="description-container">
					<div class="description-tab">
						<div
							style="width: 99px; height: 99px; position: absolute; top: -9999px; overflow: scroll;">
						</div>
						<div class="description-tab-wrapper" style="margin-bottom: 0px;">
							<div class="tab-group" role="tablist">
								<button class="tab-button" tabindex="0" type="button" role="tab"
									aria-selected="true">description</button>
								<button class="tab-button" tabindex="-1" type="button"
									role="tab" aria-selected="false">
									Reviews (<span>8</span>)
								</button>
							</div>
							<span class="line" style="left: 0px; width: 75.6406px;"></span>
						</div>
					</div>
					<div class="description">
						<div class="detail-scription"></div>
					</div>
					<div class="review" style="display: none;">
						<c:if test="${isBought and !isReview}">
							<label> Đánh giá sản phẩm </label>
							<form:form action="${currentProductItem.getId()}.htm">

								<button name="ratingProduct" type="submit" style="border: none">

								</button>
							</form:form>
							<form:form action="${currentProductItem.getId()}.htm"
								modelAttribute="CustomerReview">
								<div>
									<input type="hidden" name="ratingValue" id="ratingValue"
										value="" />
									<div class="div_start">
										<div class="stars">
											<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
												class="fas fa-star"></i> <i class="fas fa-star"></i> <i
												class="fas fa-star"></i>
										</div>
									</div>
								</div>

								<script>
						  const ratingInput = document.getElementById('ratingValue');
						  const stars = document.querySelectorAll('.stars i');
						
						  stars.forEach((star, index) => {
						    star.addEventListener('click', () => {
						      stars.forEach((star, i) => {
						        if (i <= index) {
						          star.classList.add('active');
						        } else {
						          star.classList.remove('active');
						        }
						      });
						      const rating = index + 1;
						      ratingInput.value = rating; // Gán giá trị rating vào trường ẩn
						    });
						  });
						</script>
								<textarea id="comment-input" name="commentInput" rows="5"> </textarea>

								<div class="btn_submit_comment">
									<button name="addComment" type="submit" class="btn_comment">
										Bình luận</button>
								</div>
							</form:form>
						</c:if>
						<c:set var="commentList" value="${comments}" />
						<div class="box-comments">
							<c:forEach var="comment" items="${commentList}">
								<div class="comment-container">
									<div class="left-col">
										<img
											src="https://d11a6trkgmumsb.cloudfront.net/original/3X/d/8/d8b5d0a738295345ebd8934b859fa1fca1c8c6ad.jpeg"
											alt="Avatar" class="avatar">
										<h4 class="username">${comment.getCustomer().getCustomerProfile().getName()}</h4>
									</div>
									<div class="right-col">
										<div class="mb-3">
											<div class="stars">
												<c:forEach var="i" begin="1" end="5">
													<c:if test="${i <= comment.ratingValue}">
														<i class="fas fa-star active"></i>
													</c:if>
													<c:if test="${i > comment.ratingValue}">
														<i class="fas fa-star"></i>
													</c:if>
												</c:forEach>
											</div>
											<p class="comment-text">${comment.getComment()}</p>
										</div>
									</div>
								</div>
								<hr />
							</c:forEach>
						</div>
					</div>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
	<script type="text/javascript">
							// Get the quantity elements
							const quantityInput = document.getElementById('quantity');
							const labelQuantity = document.getElementById('label-quantity');
							const currentProduct = document.querySelector('.current-product');

							// Get the quantity buttons
							const decreaseButton = document
								.querySelector('.quantity-button[tabindex="-1"]');
							const increaseButton = document
								.querySelector('.quantity-button[tabindex="0"]');

							// Add event listeners to the buttons
							decreaseButton.addEventListener('click', decreaseQuantity);
							increaseButton.addEventListener('click', increaseQuantity);

							// Decrease quantity function
							function decreaseQuantity() {
								let quantityValue = parseInt(quantityInput.value);
								if (quantityValue > 1) {
									quantityValue--;
									quantityInput.value = quantityValue;
									labelQuantity.textContent = quantityValue;
								}
							}

							// Increase quantity function
							function increaseQuantity() {
								let quantityValue = parseInt(quantityInput.value);
								let availableQuantity = parseInt(currentProduct.textContent);
								if (quantityValue < availableQuantity) {
									quantityValue++;
									quantityInput.value = quantityValue;
									labelQuantity.textContent = quantityValue;
								}
							}
							const descriptionTabButton = document
								.querySelector('.tab-button[aria-selected="true"]');
							const reviewsTabButton = document
								.querySelector('.tab-button[aria-selected="false"]');
							const lineElement = document.querySelector('.line');
							const descriptionSection = document.querySelector('.description');
							const reviewSection = document.querySelector('.review');

							lineElement.style.left = '0px'; // For the description tab
							lineElement.style.width = `${descriptionTabButton.offsetWidth}px`;

							descriptionTabButton.addEventListener('click', switchToDescriptionTab);
							reviewsTabButton.addEventListener('click', switchToReviewsTab);
							function switchToDescriptionTab() {
								lineElement.style.left = '0px';
								lineElement.style.width = `${descriptionTabButton.offsetWidth}px`;
								descriptionSection.style.display = 'block';

								reviewSection.style.display = 'none';
							}

							function switchToReviewsTab() {
								lineElement.style.left = '104px';
								lineElement.style.width = `${reviewsTabButton.offsetWidth}px`;
								descriptionSection.style.display = 'none';
								reviewSection.style.display = 'block';
							}

							var detailScription = document.querySelector('.detail-scription');
							var descriptionContent = `<c:out value="${fn: trim(product.description)}" escapeXml="false" />`;
							detailScription.innerHTML = descriptionContent;

							const price = ${ currentProductItem.price };
							const formattedPrice = price.toLocaleString();
							const priceElement = document.querySelector('.product-price');
							priceElement.textContent = formattedPrice + " VND";
							const salePrice = ${ salePrice } + 0;
							const formattedSalePrice = salePrice.toLocaleString();
							const salePriceElement = document.querySelector('.product-sale-price');
							salePriceElement.textContent = formattedSalePrice + " VND";
						</script>
</body>

</html>