<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Form Tạo Khuyến Mãi</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<link rel="stylesheet"
		href="<c:url value ='/common/assets/css/layout/sidebar.css' />">
	<link rel="stylesheet"
		href="<c:url value ='/common/assets/css/reset.css' />">
	<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/product/createPromotion.css' />">
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

	<div class="container-cts">
		<%@include file="/WEB-INF/views/layout/sidebar.jsp"%>
		<main class="content">
			<div class="content-container">
				<form:form action="${promotion.getId()}.htm">
					<div class="form-group">
						<label class="tenKM" for="ten-khuyen-mai">Tên Khuyến Mãi:</label>
						<input type="text"  value = "${promotion.getName()}" class="form-control" name ="promotion-name" id="promotion-name" placeholder="Nhập tên khuyến mãi">
					</div>
					<div class="form-group">
						<label class="moTa" for="mo-ta">Mô Tả:</label>
						<textarea class="form-control" name="promotion-description" id="promotion-description" rows="3" placeholder="Nhập mô tả khuyến mãi">${promotion.getDescription()}</textarea>
					<div class="form-group">
						<label class="nhanHang" for="nhan-hang">Nhãn Hãng:</label>
						<select class="form-control" id="brand" name="brand">
							<option value="">${categories.get(selectedItem).getCategoryName()}</option>
							<c:forEach var="u" items="${categories}">
								<option value="${u.getId()}"> ${u.getCategoryName()}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label class="phanTramGiam" for="phan-tram-giam">Phần Trăm Giảm:</label>
						<input type="number"  value="${promotion.getDiscountRate()}" min="0" max="100" class="form-control" 
							name="discount-percentage"
							id="discount-percentage" placeholder="Nhập phần trăm giảm">
					</div>
					<div class="form-group">
						<label class="ngayBD" for="ngay-bat-dau">Ngày Bắt Đầu:</label>
						<input type="date" value="${promotion.getStartDate() }" class="form-control" name="start-date" id="start-date" >
					</div>
					<div class="form-group">
						<label class="ngayKT" for="ngay-ket-thuc">Ngày Kết Thúc:</label>
						<input type="date" value="${promotion.getEndDate() }" class="form-control" name="end-date" id="end-date">
					</div>
					<button type="submit" class="btn btn-primary">Chỉnh sửa khuyến mãi</button>
				</form:form>
			</div>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
</body>