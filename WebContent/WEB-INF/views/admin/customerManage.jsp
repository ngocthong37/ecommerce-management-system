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
<title>Admin | List Customer</title>
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
						<h3 class="heading">Danh sách khách hàng</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="">Trang
										chủ</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="">Quản lý</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Danh sách khách hàng</li>
							</ul>
						</nav>
					</div>
					<div>
						<a href="${contextPath}/e-commerce/signIn.htm">
							<button class="btn--add">
								<i class="fa-solid fa-plus"></i><span>Thêm mới</span>
							</button>
						</a>
					</div>
				</div>
				<div class="paper-wrapper">
					<form id="search-form">
						<div class="search-container">
							<!-- Input search -->
							<div class="select-container">
								<select name="status" id="status" class="select"
									aria-invalid="false">
									<option value="null" selected="selected">Không</option>
								</select> <label for="status">Trạng thái</label> <span
									class="select-icon"><i class="fa-solid fa-angle-down"></i></span>
							</div>
							<div class="input-container">
								<input type="text" id="search" name="search"
									aria-labelledby="label-search" value="${search}"><span
									class="highlight"></span><span class="bar"></span> <label
									for="search">Tìm kiếm</label>
								<button id="clear-search" type="button"
									aria-label="Clear search text">
									<i class="fa-solid fa-times"></i>
								</button>
							</div>
						</div>
					</form>
					<div class="table-container">
						<table>
							<thead>
								<tr>
									<th class="th-header"><span>Tên đăng nhập</span></th>
									<th class="th-header"><span>Email</span></th>
									<th class="th-header"><span>Trạng thái</span></th>
									<th class="th-header"><span></span></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listCustomers}" var="element">
									<tr>
										<td class="td-body">${element.userName}</td>
										<td class="td-body">${element.email }</td>
										<td class="td-body">
											<div class="mui-chip">
												<span class="mui-chip-label">Hoạt động</span>
											</div>
										</td>
										<td class="td-body">
											<div class="group-btn">
												<a
													href="${contextPath}/admin/customer/edit/${element.id}.htm">

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
								<p class="text">${limit  *(currentPage - 1)  + 1}–${currentPage * limit}trong
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
</body>
</html>