<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐겨찾기 추가</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</head>
<body>
	<h1>즐겨찾기 추가하기</h1>
	<form method="post" action="/lesson06/ex01/add_bookmark">
		<div class="form-group">
			<label for="name">제목</label>
			<input type="text" id="name" name="name" class="form-control">
		</div>
		
		<div class="form-group">
			<label for="url">주소</label>
			<input type="text" id="url" name="url" class="form-control">
		</div>
		
		<input type="button" id="addBtn" class="col-12 btn btn-success" value="추가">
	</form>
	
<script>
	$(document).ready(function() {
		$('#addBtn').on('click', function() {
			
			// validation (빈칸)
			let name = $('#name').val().trim();
			if (name == '') {
				alert("제목을 입력하세요");
				return;
			}
			
			let url = $('#url').val().trim();
			if (url == '') {
				alert("주소를 입력하세요");
				return;
			}
			
			// validation (http로 시작)			
			if (url.startsWith('http://') == false && url.startsWith('https://') == false) {
				alert("http:// 또는 https://로 시작해야 합니다.");
				return;
			}
			
			// 변수 확인
			console.log(name);
			console.log(url);
			
			
			
			// 서버 전송(AJAX)
			$.ajax({
				// request
				type:"post"
				, url:"/lesson06/quiz01/add_bookmark"
				, data:{"name":name, "url":url}
			
				//response
				, success:function(data) {
					if (data == "성공") {
						location.href = "/lesson06/quiz01/after_add_bookmark_view";
					} else {
						alert("주소 추가 처리에 실패했습니다.");
					}
				}
				
				/* , error:function(request, status, error) {
					alert(request);
					alert(status);
					alert(error);
				} */
			});
		});
		
	});
</script>

</body>
</html>