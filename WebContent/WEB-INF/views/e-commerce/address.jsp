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
<title>E-Commerce | Address</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/cart/address.css' />">
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
						<h3 class="heading">Address</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="">E-Commerce</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="">Thanh toán</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Địa chỉ</li>
							</ul>
						</nav>
					</div>
					<div>
						<a href="${contextPath}/customer/newAddress.htm">
							<button class="btn--add">
								<i class="fa-solid fa-plus"></i><span>Thêm địa chỉ mới</span>
							</button>
						</a>
					</div>
				</div>
				<div class="paper-wrapper">
					<div class="table-container">
						<table>
							<thead>
								<tr>
									<th class="th-header"><span>Số điện thoại</span></th>
									<th class="th-header"><span>Địa chỉ</span></th>
									<th class="th-header"><span>Xã</span></th>
									<th class="th-header"><span>Huyện</span></th>
									<th class="th-header"><span>Tỉnh</span></th>
									<th class="th-header"><span></span></th>
									<th class="th-header"><span></span></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${customerAddress}" var="element">
									<tr>
										<td class="td-body">${element.address.phoneNumber}</td>
										<td class="td-body">${element.address.detailAddress}</td>
										<td class="td-body">${element.address.ward.name }</td>
										<td class="td-body">${element.address.ward.district.name}</td>
										<td class="td-body">${element.address.ward.province.name}</td>
										<td class="td-body"><form
												action="../customer/editAddress.htm" method="get">
												<input type="hidden" name="id" value="${element.id }">
												<button class="btn--add">Chỉnh sửa</button>
											</form></td>
										<td class="td-body">
											<div class="group-btn">
												<c:if test="${test == null }">
													<a
														href="${contextPath}/e-commerce/address/delete/${element.id}.htm">
														<button class="btn--delete">
															<span>Xoá</span>
														</button>
													</a>

													<a
														href="${contextPath}/e-commerce/address/deliver/${element.id}.htm">
														<button class="btn--add">
															<span>Chuyển đến đây</span>
														</button>
													</a>
												</c:if>
												<c:if test="${test != null }">
													<a href="${contextPath}/customer/order/edit/${element.id}.htm">
														<button class="btn--add">
															<span>Chuyển đến đây</span>
														</button>
													</a>
												</c:if>
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
</body>
</html>