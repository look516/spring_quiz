<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통나무 팬션</title>
<!-- bootstrap, jquery -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

<!-- datepicker -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<!-- 내 스타일 시트 -->
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
			<h2 class="text-center font-weight-bold m-4">예약하기</h2>
			<div class="d-flex justify-content-center">
				<div class="reservation-box">
					<div class="subject-text my-2 font-weight-bold">이름</div>
					<input type="text" class="form-control" id="name">

					<div class="subject-text my-2 font-weight-bold">예약날짜</div>
					<input type="text" class="form-control" id="date">

					<div class="subject-text my-2 font-weight-bold">숙박일수</div>
					<input type="text" class="form-control" id="day">

					<div class="subject-text my-2 font-weight-bold">숙박인원</div>
					<input type="text" class="form-control" id="headcount">

					<div class="subject-text my-2 font-weight-bold">전화번호</div>
					<input type="text" class="form-control" id="phoneNumber">

					<button type="button" id="reservationBtn"
						class="btn btn-warning w-100 mt-3">예약하기</button>
				</div>
			</div>
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
			// datepicker
			$('#date').datepicker({
				changeMonth: true,
				changeYear: true,
				dateFormat: "yy-mm-dd"
			});
			
			// 예약버튼
			$("#reservationBtn").on('click', function() {
				
				let name = $("#name").val().trim();
				let date = $("#date").val();
				let day = $("#day").val().trim();
				let headcount = $("#headcount").val().trim();
				let phoneNumber = $("#phoneNumber").val().trim();
				
				// validation
				if (name == "") {
					alert("이름을 입력하세요");
					return;
				}
				
				if (date == "") {
					alert("예약날짜를 입력하세요");
					return;
				}
				
				if (day == "") {
					alert("숙박일수를 입력하세요");
					return;
				}
				
				if (headcount == "") {
					alert("숙박인원을 입력하세요");
					return;
				}
				
				if (phoneNumber == "") {
					alert("전화번호를 입력하세요");
					return;
				}
				
				// 예약 추가 AJAX
				$.ajax({
					// request
					type: "post"
					, url: "/booking/add_booking_list"
					, data: {"name":name, "date":date, "day":day, "headcount":headcount, "phoneNumber":phoneNumber}
					
					// response
					, success:function(data) {
						if (data.result == "성공") {
							location.href = "/booking/booking_list_view";
						}
					}
					, error:function(request, status, error) {
						alert("예약에 실패했습니다.");
					}
				});
			});
		});
	</script>
</body>
</html>