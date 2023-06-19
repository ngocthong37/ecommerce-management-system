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
<title>Admin | New Product</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/product/newProduct.css' />">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/reset.css' />">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/layout/sidebar.css' />">
</head>
<body>
	<div class="container-cts">
		<%@include file="/WEB-INF/views/layout/sidebar.jsp"%>
		<main class="content">
			<!-- Header breadcrumb -->
			<div class="content-container">
				<div class="list-header">
					<div class="header-breadcrumb">
						<h3 class="heading">Tạo mới sản phẩm</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">Trang
										chủ</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">Sản
										phẩm</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Tạo mới sản phẩm</li>
							</ul>
						</nav>
					</div>
				</div>

				<!-- Form create new product -->
				<form action="new.htm" method="POST" class="form-create-product" enctype="multipart/form-data">
					<div class="form-wrapper">
						<div class="wrapper-content-product">
							<div class="wrapper">
								<div class="flex">
									<div class="input-container">
										<input type="text" required="required" id="Tên sản phẩm"
											name="productName" aria-labelledby="productName"><span
											class="highlight"></span><span class="bar"></span> <label
											for="productName">Tên sản phẩm</label>
									</div>
									<div class="select-container">
										<select name="categoryId" id="categoryId" class="select"
											aria-invalid="false">
											<c:forEach items="${listCategory}" var="element">
												<option value="${element.id}">${element.categoryName }</option>
											</c:forEach>
										</select> <label for="categoryId">Thuộc nhãn</label> <span
											class="select-icon"><i class="fa-solid fa-angle-down"></i></span>
									</div>
									<div class="ckeditor">
										<h6>Mô tả sản phẩm</h6>
										<div class="ckeditor-wrapper">
											<textarea id="description" name="description"></textarea>
										</div>
									</div>
									<div class="upload-image">
										<h6>Ảnh thumbnail</h6>
										<div>

											<div class="presentation">
												<input accept="image/*" multiple type="file" tabindex="-1"
													id="file-input" name="thumbnail" style="display: none;">
												<div class="image">
													<img
														src="<c:url value='/common/assets/images/illustration_upload.svg'/>"
														alt=""
														style="max-width: 200px; max-height: 300px; object-fit: cover;">
													<div style="margin-left: 40px;">
														<h5 class="">Nhấn vào để chọn file ảnh</h5>
														<p class="">
															Drop files here or click <span class="browse-link">browse</span>
															thorough your machine
														</p>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="category">
							<div class="btn--add">
								<button id="btnAddField" type="button">thêm biến thể</button>
							</div>
						</div>
					</div>
					<div class="button-wrapper">
						<div class="button-group">
							<button class="button button-submit" type="submit">
								<span> <i class="fa-solid fa-plus"></i>
								</span> <span> Tạo sản phẩm </span>
							</button>
							<button class="button button-cancel" type="button">
								<span> <i class="fa-regular fa-circle-xmark"></i>
								</span> <span> Huỷ </span>
							</button>
						</div>
					</div>
				</form>
			</div>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/createProduct.js'/>"></script>
</body>
</html>