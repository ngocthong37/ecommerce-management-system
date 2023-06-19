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
<title>Admin | Inventory</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/admin/inventoryDetails.css' />">
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
						<h3 class="heading">Chi tiết đơn nhập</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="">Ttrang chủ </a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="">Đơn nhập</a>
								</li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Chi tiết đơn nhập</li>
							</ul>
						</nav>
					</div>
					<div>
						<a href="${contextPath}/admin/inventory/list.htm">
							<button class="btn--add">
								<i class="fa-solid fa-plus"></i><span>Quay lại</span>
							</button>
						</a>
					</div>
				</div>
				<div class="paper-wrapper">
					<div class="table-container">
						<table>
							<thead>
								<tr>
									<th class="th-header"><span>Sản phẩm</span></th>
									<th class="th-header"><span>Số lượng</span></th>
									<th class="th-header"><span>Giá</span></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="element">
									<tr>
										<td class="td-body">${element.productItem.product.name}</td>
										<td class="td-body">${element.qty }</td>
										<td class="td-body"><span class="price">${element.price}</span></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="paper-wrapper">
					<h3 class="heading">Chi tiết đơn nhập</h3>
					<h4 class="detailsmenu">Mã đơn hàng: ${id }</h4>
					<h4 class="detailsmenu">Nhà cung cấp: ${iR.agency.name }</h4>
					<h4 class="detailsmenu"> Giá trị đơn hàng: <span class="price">${sum }</span></h4>
					<h4 class="detailsmenu">Người lập đơn: ${iR.user.id }</h4>
					<h4 class="detailsmenu">Ngày lập: ${iR.date }</h4>
					<form action="done.htm" method="post">
						<div class="select-container">
							<select name="status" id="status" class="select"
								aria-invalid="false">
								<option value="0" selected="selected">Trống</option>
								<c:forEach items="${listStatus}" var="element">
									<option value="${element.id}"
										${element.id == selectedStatus ? 'selected' : ''}>${element.status }</option>
								</c:forEach>
							</select> <label for="status">Trạng thái</label> <span class="select-icon"><i
								class="fa-solid fa-angle-down"></i></span>
						</div>
						<c:if test="${ test}">
							<input type="hidden" name="id" value="${iR.getId()}">
							<div style="text-align: center;">
								<button type="submit" class="btn--add" name="checkOut">Lưu</button>
							</div>
						</c:if>
						
					</form>
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