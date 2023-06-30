<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>즐겨찾기 목록</h1>
		<table class="table">
			<thead>
				<tr>
					<th>No.</th>
					<th>이름</th>
					<th>주소</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bookmarkList}" var="bookmark">
					<tr>
						<td>${bookmark.id}</td>
						<td>${bookmark.name}</td>
						<td>${bookmark.url}</td> <%-- a 태그 추가 --%>
						<%-- 1) name 속성과 value 속성을 이용하여 동적으로 삭제버튼 값 가져오기 --%>
						<%-- <td><button type="button" name="delBtn" class="btn btn-danger" value="${bookmark.id}">삭제</button></td> id로 하면 반복문이므로 똑같은 버튼이 된다. class나 name으로 하자 --%>
						
						<%-- 2) (jquery에서 가장 많이 쓰임)data를 이용해서 태그에 값을 임시 저장해놓기 --%> <%-- data-(prefix): 여러 개의 value 저장 가능 !!!대문자 절대 불가!!! --%>
						<td><button type="button" class="del-btn btn btn-danger" data-bookmark-id="${bookmark.id}">삭제</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
<script>
	$(document).ready(function() {
		// 1) name 속성과 value 속성을 이용하여 동적으로 삭제버튼 값 가져오기
		// - button 내 value에 현재 아이디를 담는다
		/* $('button[name=delBtn]').on('click', function(e) {
			//alert("삭제버튼");
			
			// 1-1
			//let id = $(this).val(); // this는 'button[name=delBtn]'인 수많은 버튼 중 지금 클릭한 하나의 버튼을 의미
			
			// 1-2
			//let id = $(this).attr('value');
			
			// 1-3 (이벤트 객체의 타켓)
			let id = e.target.value;
			alert(id);
		}); */
		
		// 2) data를 이용해서 태그에 값을 임시 저장해놓기
		// 태그 영역: data-bookmark-id		data- 그 뒤부터는 우리가 이름을 짓는다. !!!카멜케이스 안됨!!!
		// 스크립트 영역: $(this).data('bookmark-id');
		$('.del-btn').on('click', function() {
			//alert("삭제버튼");
			let id = $(this).data('bookmark-id');
			//alert(id);
			
			$.ajax({
				// request
				type:"delete"
				, url:"/lesson06/quiz01/delete_bookmark"
				, data:{"bookmarkId":id} // controller의 requestParam명과 일치해야 함
				
				// response
				, success:function(data) {
					if (data.code == 1) { // 성공
						location.reload(true); // true 생략가능 // 그 자리에서 새로고침 (새로고침이지만 스크롤이 그대로 머무름)
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(request, status, error) { // 호출도 못 하거나 호출 시 에러
					alert("삭제하는데 실패했습니다. 관리자에게 문의해주세요.");
				}
			});
		});
		
	});
</script>
</body>
</html>