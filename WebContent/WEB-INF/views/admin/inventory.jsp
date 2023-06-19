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
<title> Admin | Inventory</title>
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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container-cts">
		<%@include file="/WEB-INF/views/layout/sidebar.jsp"%>
		<main class="content">
			<div class="content-container">
				<div class="list-header">
					<div class="header-breadcrumb">
						<h3 class="heading">Phiếu nhập</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">Admin</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">Kho</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Phiếu nhập</li>
							</ul>
						</nav>
					</div>
				</div>
				<div class="paper-wrapper">
					<form action="../admin/inventory/receive.htm" method="post">
						<div class="container mt-3">
							<div class="row">
								<div class="col-md-6">
									<label for="select1">Nhà cung cấp</label> <select class="form-select"
										id="agency" name="agency">
										<c:forEach items="${listAgency}" var="element">
											<option value="${element.id}">${element.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-6">
									<label for="select2">Trạng thái</label> <select class="form-select"
										id="status" name="status">
										<c:forEach items="${listStatus}" var="element">
											<option value="${element.id}">${element.status}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<br> <br>
						<div id="product-list">
							<div class="row mb-3 mt-3">
								<div class="col">
									<input type="text" class="form-control"
										placeholder="ID sản phẩm" name="productItem">
								</div>
								<div class="col">
									<input type="text" class="form-control" placeholder="Số lượng"
										name="qty">
								</div>
								<div class="col">
									<input type="text" class="form-control" placeholder="Đơn giá"
										name="price">
								</div>
								<div class="col-1">
									<button type="button" class="btn btn-danger"
										onclick="removeProduct(this)">
										<i class="bi bi-trash"></i> Xóa
									</button>
								</div>
							</div>
						</div>
						<button type="button" class="btn btn-success"
							onclick="addProduct()">+ Thêm sản phẩm</button>
						<script type="text/javascript"
							src="<c:url value='/common/assets/js/inventory.js'/>">
							
						</script>
						<div class="mt-3">
							<button type="submit" class="btn btn-primary">Hoàn thành</button>
						</div>
					</form>
				</div>
			</div>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
</body>
</html>