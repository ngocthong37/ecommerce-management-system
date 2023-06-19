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
<title>Admin | Edit Variation</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/product/category.css' />">
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
						<h3 class="heading">Chỉnh sửa thuộc tính</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">Trang
										chủ</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">Thuộc
										tính</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Chỉnh sửa</li>
							</ul>
						</nav>
					</div>
				</div>
				<form action="${currentVariation.variationId }.htm" method="POST">
					<div class="input-group">
						<input type="text" required="required" id="variationId"
							value="${currentVariation.variationId}" name="variationId"
							aria-labelledby="variationId" style="display: none;"}>
						<div class="input-container">
							<input type="text" required="required" id="variationName"
								value="${currentVariation.variationName}" name="variationName"
								aria-labelledby="variationName"><span class="highlight"></span><span
								class="bar"></span> <label for="variationName">Tên thuộc
								tính</label>
						</div>
						<div class="select-container">
							<select name="categoryId" id="categoryId" class="select"
								aria-invalid="false">
								<c:forEach items="${listCategory}" var="element">
									<option value="${element.id}"
										<c:if test="${element.id == currentVariation.categoryId}">
										selected
									</c:if>>${element.categoryName }</option>
								</c:forEach>
							</select> <label for="categoryId">Nhãn</label> <span class="select-icon"><i
								class="fa-solid fa-angle-down"></i></span>
						</div>
					</div>
					<div class="button-group">
						<button class="button button-submit" type="submit">
							<span> <i class="fa-solid fa-plus"></i>
							</span> <span> Chỉnh sửa </span>
						</button>
						<button class="button button-cancel" type="button">
							<span> <i class="fa-regular fa-circle-xmark"></i>
							</span> <span> Huỷ </span>
						</button>
					</div>
				</form>
			</div>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
</body>
</html>