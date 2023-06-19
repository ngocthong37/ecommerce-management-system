<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>E-Commerce| Login</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/reset.css' />">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/admin/login.css' />">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="login">
		<div class="login__image">
			<div class="login__logo">
				<img src="<c:url value ='/common/images/logo.png'/>" alt="">
			</div>
			<h3>Chào mừng bạn quay trở lại!</h3>
			<img
				src="<c:url value ='/common/images/illustration_order_complete.svg'/>"
				alt="">
		</div>
		<div class="login__form">
			<form action="login.htm" method="POST">
				<div class="login__form-group">
					<div class="group-header">
						<h3>Đăng nhập</h3>
						<span>Người dùng mới? <a
							href="${contextPath}/e-commerce/signIn.htm">Đăng ký</a></span>
					</div>
					<div class="input-container">
						<input type="text" required="required" id="username"
							name="username" aria-labelledby="username"><span
							class="highlight"></span><span class="bar"></span> <label
							for="searchText">Tên người dùng</label>
					</div>
					<div class="input-container">
						<input type="password" required="required" id="password"
							name="password" aria-labelledby="password"><span
							class="highlight"></span><span class="bar"></span> <label
							for="searchText">Mật khẩu</label>
					</div>
					<c:if test="${not empty error}">
						<div class="popup">
							<p>${error}</p>
						</div>
					</c:if>
					<div class="form-submit">
						<a href="${contextPath}/admin/login.htm">Đăng nhập với tư cách
							quản trị viên</a> <a href="${contextPath}/e-commerce/shop.htm"
							style="margin-top: 10px">Đăng nhập không cần đăng ký</a>
						<button type="submit">Đăng nhập</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>