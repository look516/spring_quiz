<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통나무 팬션</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

<link rel="stylesheet" href="/css/booking/style.css" type="text/css">
</head>
<body>
	<div class="container">
		<header class="d-flex align-items-center justify-content-center bg-danger">
			<div class="display-4">통나무 팬션</div>
		</header>
		<nav class="bg-primary">
            <ul class="nav nav-fill">
                <li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">팬션소개</a></li>
                <li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">객실보기</a></li>
                <li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">예약하기</a></li>
                <li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">예약목록</a></li>
            </ul>
        </nav>
		<section class="contents bg-warning">
			<div class="d-flex align-items-center justify-content-center py-2">
				<h1>예약 목록 보기</h1>
			</div>
			<table class="table text-center">
				<thead>
					<tr>
						<th>이름</th>
						<th>예약날짜</th>
						<th>숙박일수</th>
						<th>숙박인원</th>
						<th>전화번호</th>
						<th>예약상태</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${bookingList}" var="booking">
					<tr>
						<td>${booking.name}</td>
						<td><fmt:formatDate value="${booking.date}" pattern="yyyy년 M월 dd일"/></td>
						<td>${booking.day}</td>
						<td>${booking.headcount}</td>
						<td>${booking.phoneNumber}</td>
						<td>
							<c:if test="${booking.state == '대기중'}">
								<span class="text-info">${booking.state}</span>
							</c:if>
							<c:if test="${booking.state == '확정'}">
								<span class="text-success">${booking.state}</span>
							</c:if>
						</td>
						<td><button type="button" class="btn btn-danger">삭제</button></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</section>
		<footer class="bg-info">
			<div>제주특별자치도 제주시 애월읍</div>
			<div>사업자등록번호 111-22-333333 / 농어촌민박사업자지정 / 대표:통나무</div>
			<div>Copyright 2023 tongnamu. All right reserved.</div>
		</footer>
	</div>
</body>
</html>