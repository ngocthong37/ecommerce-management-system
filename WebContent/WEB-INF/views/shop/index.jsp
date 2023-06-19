<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Product List</title>
<base href="${pageContext.servletContext.contextPath}/">
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	line-height: 25px;
	border: 1px solid black;
	padding: 5px;
}

th {
	background-color: gray;
}
</style>
</head>

<body>
	<table class="table table-hover">
		${category}
		<tr>
			<th>Product Name</th>
			<th>Description</th>
			<th>Product Image</th>

		</tr>
		<c:forEach var="u" items="${listProduct}">
			<tr>
				<td>${u.getName()}</td>
				<td>${u.getDescription()}</td>
				<td>${u.getProductImage()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>