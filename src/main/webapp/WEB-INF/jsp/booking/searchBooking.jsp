<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<section class="banner bg-info">
			<img id="bannerImage" src="/image/booking/banner1.jpg"	alt="banner" width="1110" height="500">
		</section>
		<section class="reserve bg-primary d-flex">
			<section
				class="real-time-reserved col-4 d-flex justify-content-center align-items-center">
				<div class="display-4 text-white">
					실시간<br>예약하기
				</div>
			</section>
			<section class="confirm col-4">
				<div class="m-3">
					<span class="reserve-confirm mr-3">예약 확인</span>
				</div>

				<!-- 예약 확인 -->
				<div id="memberInputBox" class="m-2">
					<div class="d-flex justify-content-end mr-3">
						<span class="text-white">이름:</span> 
						<input type="text" id="name" class="form-control input-form">
					</div>
					<div class="d-flex mt-2 justify-content-end mr-3">
						<span class="text-white">전화번호:</span> 
						<input type="text" id="phoneNumber" class="form-control input-form">
					</div>

					<!-- 버튼 -->
					<div class="text-right mt-3 mr-3">
						<button type="button" id="searchBtn" class="btn btn-success submit-btn">조회하기</button>
					</div>
				</div>

			</section>
			<section
				class="inquiry col-4 d-flex justify-content-center align-items-center">
				<div class="text-white">
					<h4 class="font-weight-bold">예약문의:</h4>
					<h1>
						010-<br>0000-1111
					</h1>
				</div>
			</section>
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
				
				$("#searchBtn").on('click', function() {
					let name = $("#name").val().trim();
					let phoneNumber = $("#phoneNumber").val().trim();
					
					// validation
					if (name == "") {
						alert("이름을 입력하세요");
						return;
					}
					
					if (phoneNumber == "") {
						alert("전화번호를 입력하세요");
						return;
					}
					
					
					
					// 예약 조회 AJAX
					$.ajax({
						// request
						type: "post"
						, url: "/booking/search_booking"
						, data: {"name":name, "phoneNumber":phoneNumber}
					
						// response
						, success:function(data) {
							if (data.result == "성공") { // 위 data와는 다르다.
								//location.href = "/booking/search_booking_view";
								alert("이름: " + data.name
										+"\n날짜: " + data.date
										+"\n일수: " + data.day
										+"\n인원: " + data.headcount 
										+"\n상태: " + data.state);
							} else {
								alert(data.errorMessage);
							}
						}
						, error:function(request, status, error) {
							alert("예약 내역이 없습니다."); // 조회될 데이터가 없는 경우와 애초에 에러가 난 경우 구분할 방법?
						}
					});
					
				});
			});
	</script>
</body>
</html>