<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>E-COmmerce| Sign In</title>
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
			<h3>Chào mừng bạn!</h3>
			<img
				src="<c:url value ='/common/images/illustration_dashboard.png'/>"
				alt="">
		</div>
		<div class="login__form">
			<form action="signIn.htm" method="POST">
				<div class="login__form-group">
					<div class="group-header">
						<h3>Tạo tài khoản</h3>
						<span>Đã có tài khoản? <a
							href="${contextPath}/e-commerce/login.htm">Đăng nhập</a></span>
					</div>
					<div class="input-container">
						<input type="text" required="required" id="username"
							name="username" aria-labelledby="username"><span
							class="highlight"></span><span class="bar"></span> <label
							for="searchText">Tên người dùng</label>
					</div>
					<div class="input-container">
						<input type="email" required="required" id="email" name="email"
							aria-labelledby="email"><span class="highlight"></span><span
							class="bar"></span> <label for="searchText">Email</label>
					</div>
					<div class="input-container">
						<input type="password" required="required" id="password" minlength="4"
							name="password" aria-labelledby="password"><span
							class="highlight"></span><span class="bar"></span> <label
							for="searchText">Mật khẩu</label>
					</div>
					<div class="input-container">
						<input type="password" required="required" id="password1"  minlength="4"
							name="password1" aria-labelledby="password1"><span
							class="highlight"></span><span class="bar"></span> <label
							for="searchText">Nhập lại mật khẩu</label>
					</div>
					<c:if test="${not empty passwordError}">
						<div class="popup">
							<p>${passwordError}</p>
						</div>
					</c:if>
					<div class="form-submit">
						<button type="submit">Đăng ký</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>