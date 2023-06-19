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
<title>Quản lý đơn hàng</title>
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
						<h3 class="heading">Danh sách đơn hàng đã đặt</h3>
					</div>
				</div>
				<div class="paper-wrapper">
					<div class="table-container">
						<table>
							<thead>
								<tr>
									<th class="th-header"><span>Mã đơn hàng</span></th>
									<th class="th-header"><span>Số lượng</span></th>
									<th class="th-header"><span>Tổng tiền </span></th>
									<th class="th-header"><span>Ngày đặt</span></th>
									<th class="th-header"><span></span></th>
									<th class="th-header"><span></span></th>
									<th class="th-header"><span></span></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listOrdered}" var="element">
									<tr>
										<td class="td-body">${element.getOrderId()}</td>
										<td class="td-body">${element.getQuantity()}</td>
										<td class="td-body"><span class="price"> ${element.getPrice()} </span></td>
										<td class="td-body">${element.getDateOrdered()}</td>
										<td class="td-body">
										<c:if test ="${!isAdmin}">
											<form action="list/confirmed/${element.getOrderId()}.htm" method="post">
											<div class="select-container">
												<select name="userId" id="userId" class="select"
													aria-invalid="false" onchange="submitForm(this)">
													<option value="">Giao hàng cho</option>
													<c:forEach items="${shipperList}" var="shipper">
														<option value="${shipper.getId()}"> ${shipper.getUserProfile().getName()}</option>
													</c:forEach>
												</select>
											</div>
										</form>
										</c:if>
										</td>
										<td class="td-body">
											<div class="group-btn">
											<c:if test ="${!isAdmin}">
													<form action="list/cancel/${element.getOrderId()}.htm " method="post">
														<input type="hidden" name="productId"
															value="${shoppingCart.get(i).getId()}">
														<button type="submit" class="btn--delete">Hủy đơn</button>
													</form>
											</c:if>
											</div>
											

										</td>
										<td class="td-body">
											<div class="group-btn">
												<a href="${contextPath}/admin/manage-ordered/list/${element.getOrderId()}.htm">
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
					<div class="pagination">
						<div class="table-pagination">
							<div class="tool-bar">
								<p class="text" id=":r2:">Số hàng mỗi trang:</p>
								<div class="select">
									<select onchange="location = this.value;">
										<option value="?page=1&limit=5"
											${limit == 5 ? 'selected' : ''}>5</option>
										<option value="?page=1&limit=10"
											${limit == 10 ? 'selected' : ''}>10</option>
										<option value="?page=1&limit=20"
											${limit == 20 ? 'selected' : ''}>20</option>
									</select>
								</div>
								<p class="text">${limit  *(currentPage - 1)  + 1}–${currentPage * limit}
									${limit}</p>
								<div class="pagination-action">
									<c:if test="${currentPage > 1}">
										<a href="?page=${currentPage - 1}&limit=${limit}"> <i
											class="fa-solid fa-angle-left"></i>
										</a>
									</c:if>
									<c:if test="${currentPage <= 1}">
										<a href="#"> <i class="fa-solid fa-angle-left"></i>
										</a>
									</c:if>
									<c:if test="${currentPage >= 1}">
										<a href="?page=${currentPage + 1}&limit=${limit}"> <i
											class="fa-solid fa-angle-right"></i>
										</a>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
	<script>
	function submitForm(selectBox) {
	    const form = selectBox.closest('form');
	    form.submit();
	}
	</script>
		<script>
  var productPrices = document.getElementsByClassName("price");
  for (var i = 0; i < productPrices.length; i++) {
    var priceValue = parseInt(productPrices[i].innerHTML);
    var formattedPrice = priceValue.toLocaleString("vi-VN", { style: "currency", currency: "VND" });
    productPrices[i].innerHTML = formattedPrice;
  }
</script>
</body>