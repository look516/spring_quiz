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
	<div id="wrap" class="container">
		<header class="d-flex justify-content-center align-items-center">
			<div class="display-4">통나무 팬션</div>
		</header>
		<nav>
			<ul class="nav nav-fill">
				<li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">팬션소개</a></li>
				<li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">객실보기</a></li>
				<li class="nav-item"><a href="/booking/add_booking_list_view" class="nav-link text-white font-weight-bold">예약하기</a></li>
				<li class="nav-item"><a href="/booking/booking_list_view" class="nav-link text-white font-weight-bold">예약목록</a></li>
			</ul>
		</nav>
		<section class="contents">
			<h2 class="text-center font-weight-bold m-4">예약 목록 보기</h2>
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
						<td><fmt:formatDate value="${booking.date}" pattern="yyyy년 M월 d일"/></td>
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
						<td><button type="button" class="del-btn btn btn-danger" data-booking-id="${booking.id}">삭제</button></td>
						<!-- a태그로 걸고 type을 버튼으로 해서 추가해도 된다. -->
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</section>
		<footer>
			<div class="text-secondary"> 
				<div><small>제주특별자치도 제주시 애월읍</small></div>
				<div><small>사업자등록번호: 111-22-255222 / 농어촌민박사업자지정 / 대표:김통목</small></div> 
				<div><small>Copyright 2025 tongnamu. All right reserved.</small></div>
			</div>
		</footer>
	</div>
	
	<script>
		$(document).ready(function() {
			// 삭제 버튼 클릭
			$('.del-btn').on('click', function() { // 어떤 삭제 버튼이 눌려도 이곳으로 들어온다.
				let id = $(this).data('booking-id'); // 그 삭제 버튼에 있는 booking-id란 data를 변수에 저장
				
				$.ajax({
					// request
					type:"delete" // ajax 폼태그만 요청가능함
					, url:"/booking/delete_booking" //응답으로 스트링, 제이슨 스트링이 내려옴
					, data:{"bookingId":id} // bookingId가 키가 된다.
				
					//response
					, success:function(data) { // data -> json으로 내려온 응답을 딕셔너리로 만든 것
						// {"code":1, "result":"성공"}
						if (data.code == 1) {
							alert("삭제되었습니다.");
							location.reload(true);
						} else {
							// {"code":500, "errorMessage":"삭제될 데이터가 없습니다."}
							alert(data.errorMessage);
						}
					}
					, error:function(request, status, error) {
						alert("삭제에 실패했습니다.");
					}
				});
			});
		});
	</script>
</body>
</html>