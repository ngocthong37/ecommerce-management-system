<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin | Edit Product</title>
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
						<h3 class="heading">Chọn thuộc tính sản phẩm</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">Trang
										chủ</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="https://getbootstrap.com/docs/5.0/components/breadcrumb/#example">Chọn
										thuộc tính sản phẩm</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Chọn thuộc tính</li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
			<!-- Form create new product -->
			<form action="${product.id}.htm" class="form-create-product" method="POST">
				<div class="form-wrapper">
					<div class="wrapper-content-product">
						<div class="wrapper">
							<div class="flex">
								<div class="input-container">
									<input type="text" id="Tên sản phẩm" name="productName"
										value="${product.name }" required
										aria-labelledby="productName"><span class="highlight"></span><span
										class="bar"></span> <label for="productName">Tên sản
										phẩm</label>
								</div>
								<div class="upload-image">
									<h6>Ảnh thumbnail</h6>
									<div>
										<div class="presentation">
											<div class="image">
												<img src="${product.productImage }" alt=""
													style="max-width: 200px; max-height: 300px; object-fit: cover;">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="category">
								<div class="btn--add">
									<button id="btnAddField" type="button">Thêm thuộc tính</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="button-wrapper">
					<div class="button-group">
						<button class="button button-submit" type="submit">
							<span> <i class="fa-solid fa-plus"></i>
							</span> <span> Chỉnh sửa/ thêm mới </span>
						</button>
						<button class="button button-cancel" type="button">
							<span> <i class="fa-regular fa-circle-xmark"></i>
							</span> <span> Huỷ </span>
						</button>
					</div>
				</div>
			</form>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
	<script type="text/javascript">
        var btnAddField = document.getElementById("btnAddField");
        const variationList = document.querySelector(".category");
        function addNewVariationField(e) {
            e.preventDefault();
            const div = document.createElement("div");
            div.classList.add("select-container");
            div.innerHTML = `<select name="variationId" id="variationId" class="select" aria-invalid="false">
            	<c:forEach items="${listVariation}" var="element">
				<option value="${element.id}">${element.name }</option>
			</c:forEach>
                        </select>
                        <label for="variationId">Thuộc tính</label> 
                        <div class="btn--delete">
                           Xoá
                        </div>`;
            const deleteButton = div.querySelector('.btn--delete');
            deleteButton.addEventListener('click', () => {
                variationList.removeChild(div);
            });
            variationList.appendChild(div);
        }
        btnAddField.addEventListener("click", addNewVariationField);
    </script>
</body>
</html>