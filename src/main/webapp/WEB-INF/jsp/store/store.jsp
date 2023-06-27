<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배탈의 민족</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

<link rel="stylesheet" href="/css/store/style.css" type="text/css">
</head>
<body>
	<div class="container">
		<header class="bg-info d-flex align-items-center">
			<div class="ml-4 header-font">배탈의 민족</div>
		</header>
		
		<section class="contents">
			<div class="display-4">우리동네 가게</div>
			<c:forEach items="${storeList}" var="store">
			<a href="/store/review?storeId=${store.id}&storeName=${store.name}" class="store-info-font">
				<div class="border border-info my-3">
					<div class="m-2">
						<div>${store.name}</div>
						<div>전화번호: ${store.phoneNumber}</div>
						<div>주소: ${store.address}</div>
					</div>
				</div>
			</a>
			</c:forEach>
		</section>
		
		<footer>
			<hr>
			<div>(주)우와한형제</div>
			<div>고객센터 : 1500-1500</div>
		</footer>
	</div>

</body>
</html>