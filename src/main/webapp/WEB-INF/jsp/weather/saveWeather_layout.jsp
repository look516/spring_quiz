<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기상청</title>

<!-- bootstrap, jquery (순서 중요) -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

<!-- datepicker -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<!-- 내가 만든 스타일 시트 -->
<link rel="stylesheet" href="/css/weather/style.css" type="text/css">
</head>
<body>
	<div id="wrap">
		<div class="contents d-flex">
			<nav class="col-2">
				<jsp:include page="menu.jsp" />
			</nav>
			<section class="weather-history col-10 mt-3 ml-5">
				<jsp:include page="saveWeather.jsp" />
			</section>
		</div>
		<footer class="d-flex align-items-center">
			<jsp:include page="footer.jsp" />
		</footer>
	</div>
	
	<script>
		// 웹페이지에서 검사 누르고 console 탭 열어서 실행시켜보고 에러 확인
		// 날짜는 String으로 보내진다
		// 웹페이지에서 네트워크 - 페이로드에 들어가고 새로고침 하면 보내진 데이터가 보여진다.
		$(document).ready(function() {
			$("#date").datepicker({
				dateFormat:"yy-mm-dd" // 날짜 포맷
				, changeYear: true
				, changeMonth: true
			});
		});
	</script>

</body>
</html>