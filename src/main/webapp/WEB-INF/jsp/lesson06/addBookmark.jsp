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
	<div class="container">
		<h1>즐겨찾기 추가하기</h1>
		
		<!-- 모범답안
		<div class="form-group">
			<label for="name">제목</label>
			<input type="text" id="name" class="form-control">
		</div>
		
		<div class="form-group">
			<label for="name">URL 주소</label>
			<input type="text" id="url" class="form-control">
		</div>
		
		<button type="button" id="addBtn" class="btn btn-success btn-block">추가</button>
		 -->
		 
		<form method="post" action="/lesson06/ex01/add_bookmark">
			<div class="form-group">
				<label for="name">제목</label>
				<input type="text" id="name" name="name" class="form-control">
			</div>
			
			
			<div class="form-group">
				<label for="url">URL 주소</label>
				<div class="form-inline">
					<input type="text" id="url" class="form-control col-10">
					<input type="button" id="urlCheckBtn" class="btn btn-info ml-3" value="중복확인">
				</div>
				<!-- 방법 1 -->
				<small id="duplicationText" class="text-danger d-none">중복된 url입니다.</small>
				<small id="availableText" class="text-success d-none">저장 가능한 url입니다.</small>
				<!--
				방법 2
				<small id="urlStatusArea"></small> -->
			</div>
			
			
			<input type="button" id="addBtn" class="col-12 btn btn-success" value="추가">
		</form>
	</div>
	
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
			if (url == '') { // ! or length 이용 가능
				alert("주소를 입력하세요");
				return;
			}
			
			// validation (http로 시작)
			// 조건문 && || 헷갈리지 않게!
			if (url.startsWith('http://') == false && url.startsWith('https://') == false) {
				alert("http:// 또는 https://로 시작해야 합니다.");
				return;
			}
			
			// 변수 확인
			console.log(name);
			console.log(url);
			
			
			
			
			// QUIZ 2) 중복 확인 체크
			if ($('#availableText').hasClass('d-none')) { // 잘못된 경우 (availableText d-none인 경우)
				alert("중복된 url입니다. 다시 확인해주세요");
				return;
				
			}
			
		
		
		
			// 서버 요청(AJAX 통신)
			// ajax는 그냥 그 페이지에 머물러있는다
			$.ajax({ // 딕셔너리로 구성
				// request
				type:"post"
				, url:"/lesson06/quiz01/add_bookmark" // view가 안 붙는 responsebody가 있는 쪽으로 들어간다
				, data:{"name":name, "url":url} // 키(""안에 있는 것)명과 controller의 db insert 함수의 parameter가 매핑이 된다
			
				//response
				, success:function(data) { // String(값 자체를 띄움), JSON(응답헤더를 보고 파악, jquery의 ajax 함수가 자바스크립트의 객체로 변환해줌)
					// alert(data.code);
					// alert(data.result);
					if (data.result == "성공") {
						location.href = "/lesson06/quiz01/after_add_bookmark_view"; // GET Method
					}
				}
				
				, error:function(request, status, error) {
					alert("즐겨찾기 추가하는데 실패했습니다.");
					/* alert(request);
					alert(status);
					alert(error); */
				}
			});
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// QUIZ 2-1) 중복확인
		$('#urlCheckBtn').on('click', function() {
			//alert("중복확인");
			let url = $('#url').val().trim();
			
			// validation
			if (!url) {
				alert("검사할 url을 입력해주세요.");
				return; // 클릭 이벤트 시 수행되는 아래 코드를 수행시키지 않는다.
			}
			
			// AJAX 통신 => DB URL 존재 여부
			$.ajax({
				// request
				type:"POST"
				, url:"/lesson06/quiz01/is_duplication_url"
				, data:{"url":url}
			
				// response
				, success:function(data) {
					// {"code":1, "isDuplication":true}
					if (data.isDuplication) { // 중복 // result.put의 키명과 동일해야 함
						$('#duplicationText').removeClass('d-none');
						$('#availableText').addClass('d-none');
					} else { // 중복 X
						$('#duplicationText').addClass('d-none');
						$('#availableText').removeClass('d-none');
					}
				}
			});
		});
		
		
		
		
		
		// 내 중복확인 답안
		/* $('#urlCheckBtn').on('click', function() {
			// 한번만 출력
			$('#urlCheckBtn').empty();
			
			let url = $('#url').val().trim();
			
			// validation
			$.ajax({
				// request
				type:"get"
				, url:"/lesson06/quiz01/is_duplication"
				, data:{"url":url}
			
				// response
				, success: function(data) {
					if(data.isDuplication) {
						$('#urlStatusArea').append('<span class="text-danger">중복된 url입니다.</span>');
					} else {
						$('#urlStatusArea').append('<span class="text-danger">저장 가능한 url입니다.</span>');
					}
				}
				
				, error:function(request, status, error) {
					alert("중복 확인에 실패했습니다.");
				}
			});
		}); */
		
	});
</script>

</body>
</html>