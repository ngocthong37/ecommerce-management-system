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
<title>Admin | ${product.name }</title>
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
						<h3 class="heading">Cấu hình sản phẩm</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="${contextPath}/admin/product/product-config/list.htm">Trang
										chủ</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="${contextPath}/admin/product/product-config/${product.id}.htm">Sản
										phẩm cấu hình</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Cấu hình chi tiết sản phẩm</li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
			<!-- Form config product -->
			<form class="form-create-product" action="${productItem.id}.htm"
				method="POST">
				<div class="form-wrapper">
					<div class="wrapper-content-product">
						<div class="wrapper">
							<div class="flex">
								<input type="text" id="productId" name="productId"
									value="${product.id }" required aria-labelledby="productId"
									style="display: none;"> <input type="text"
									id="productItemId" name="productItemId"
									value="${productItem.id }" required
									aria-labelledby="productItemId" style="display: none;">
								<div class="input-container">
									<input type="text" id="productName" name="productName"
										value=${product.name } required aria-labelledby="productName"
										disabled> <span class="highlight"></span> <span
										class="bar"></span> <label for="productName">Tên sản
										phẩm</label>
								</div>
								<div class="input-container">
									<input type="text" id="SKU" name="SKU"
										value=${productItem.SKU } required aria-labelledby="SKU"
										disabled><span class="highlight"></span><span
										class="bar"></span> <label for="SKU">SKU</label>
								</div>
								<div class="upload-image">
									<h6>Ảnh chi tiết sản phẩm</h6>
									<div>
										<div class="presentation">
											<div class="image">
												<img src="${productItem.productImage}" alt=""
													style="max-width: 200px; max-height: 300px; object-fit: cover;">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="category">
							<c:forEach items="${listConfigProduct}" var="element">
								<div class="wrapper">
									<input type="text" id="productItemIdConfigId"
										name="productItemIdConfigId"
										value="${element.id.productItem.id }" required
										aria-labelledby="productItemIdConfigId" style="display: none;">
									<div class="wrapper-delete-item">
										<h6></h6>
										<a>
											<button class="btn--delete remove-item-btn" type="button">
												<span>Xoá</span>
											</button>
										</a>
									</div>
									<div class="select-container">
										<select name="variationConfigId" id="variationConfigId"
											class="select" aria-invalid="false">
											<c:forEach items="${listVariations}" var="variation">
												<option value="${variation.id}"
													<c:if test="${variation.id == element.id.variation.id}">
										selected
									</c:if>>${variation.name }</option>
											</c:forEach>
										</select> <label for="variationConfigId">Thuộc tính sản phẩm</label>
									</div>
									<div class="input-container">
										<input type="text" id="variationConfigValue"
											name="variationConfigValue" value="${element.value }"
											required aria-labelledby="variationConfigValue"><span
											class="highlight"></span><span class="bar"></span> <label
											for="variationConfigValue">Giá trị thuộc tính</label>
									</div>
								</div>
							</c:forEach>
							<div class="btn--add">
								<button id="btnAddField" type="button">Thêm thuộc tính
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="button-wrapper">
					<div class="button-group">
						<button class="button button-submit" type="submit">
							<span> <i class="fa-solid fa-plus"></i>
							</span> <span> Cấu hình </span>
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
        function checkDuplicateVariationId() {
            const variationIdSelects = document.querySelectorAll('select[name="variationId"]');
            const selectedValues = Array.from(variationIdSelects, (select) => select.value);

            // Check if there are any duplicate values
            const hasDuplicates = new Set(selectedValues).size !== selectedValues.length;

            if (hasDuplicates) {
                console.log('Duplicate variationId selected!');
                // Perform your desired actions here for handling duplicate selection
            }
        }

        var btnAddField = document.getElementById("btnAddField");
        const variationList = document.querySelector(".category");
        function addNewVariationField(e) {
            e.preventDefault();
            const div = document.createElement("div");
            div.classList.add("wrapper");
            div.innerHTML = `<div class="wrapper-delete-item">
                                <h6></h6>
                                <a>
                                    <button class="btn--delete remove-item-btn" type="button">
                                        <span>Xoá</span>
                                    </button>
                                </a>
                            </div>
                            <div class="select-container"><select name="variationConfigId" id="variationConfigId" class="select"
                                    aria-invalid="false">
                            <c:forEach items="${listVariations}" var="element">
                                    <option value="${element.id}">${element.name}</option>
                                    </c:forEach>
                                </select>
                                <label for="variationId">Thuộc tính sản phẩm</label>
                            </div>
                            <div class="input-container">
                                <input type="text" id="variationConfigValue" name="variationConfigValue" required
                                    aria-labelledby="variationConfigValue"><span class="highlight"></span><span
                                    class="bar"></span>
                                <label for="variationValue">Giá trị thuộc tính</label>
                            </div>`;
            const variationIdSelect = div.querySelector('select[name="variationConfigId"]');
            variationIdSelect.addEventListener('change', () => {
                checkDuplicateVariationId();
            });

            const deleteButton = div.querySelector('.btn--delete');
            deleteButton.addEventListener('click', () => {
                variationList.removeChild(div);
            });
            variationList.appendChild(div);
        }
        btnAddField.addEventListener("click", addNewVariationField);
        
        function removeDynamicItem(e) {
        	  e.preventDefault();
        	  const wrapper = this.closest(".wrapper");
        	  if (wrapper) {
        	    wrapper.parentNode.removeChild(wrapper);
        	    console.log("Item removed");
        	  }
        	}
     // Add event listeners to existing remove buttons
        const removeItems = document.querySelectorAll(".remove-item-btn");
        removeItems.forEach((item) => item.addEventListener("click", removeDynamicItem));
    </script>
</body>
</html>