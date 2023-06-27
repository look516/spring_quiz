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
			<div class="display-4">${storeName} - 리뷰</div>
			<c:if test="${reviewList[0] eq null}">
				<div class="ml-4 mt-4 none-review-font">작성된 리뷰가 없습니다.</div>
			</c:if>
			<c:forEach items="${reviewList}" var="review">
				<div class="border border-info my-3 p-2">
					<div>
						${review.userName}
						${review.point}
						<%-- 0 5 10 15 20 25 30 35 40 45 50 --%>
						<c:choose>
							<c:when test="${review.point % 1 eq 0}">
								<c:forEach begin="0" end="${review.point / 1 - 1}">
									<img src="/image/review/star_fill.png" alt="1점" width='20px'>
								</c:forEach>
								<c:forEach begin="1" end="${5 - review.point / 1}">
									<img src="/image/review/star_empty.png" alt="0점" width='20px'>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach begin="0" end="${review.point / 1 - 1}">
									<img src="/image/review/star_fill.png" alt="1점" width='20px'>
								</c:forEach>
								<img src="/image/review/star_half.png" alt="0.5점" width='20px'>
								<c:forEach begin="1" end="${5 - review.point / 1}">
									<img src="/image/review/star_empty.png" alt="0점" width='20px'>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						
						
						
					</div>
					<div><fmt:formatDate value="${review.createdAt}" pattern="yyyy년 MM월 dd일"/></div>
					<div>${review.review}</div>
					<span class="bg-secondary">${review.menu}</span>
				</div>
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