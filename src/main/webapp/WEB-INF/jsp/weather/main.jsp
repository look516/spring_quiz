<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h3>과거 날씨</h3>
<table class="table text-center">
	<thead>
		<tr>
			<th>날짜</th>
			<th>날씨</th>
			<th>기온</th>
			<th>강수량</th>
			<th>미세먼지</th>
			<th>풍속</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${history}" var="data">
			<tr>
				<th><fmt:formatDate value="${data.date}" pattern="yyyy년 M월 d일" />
				</th>
				<th><c:if test="${data.weather eq '구름조금'}">
						<img src="/image/weather/partlyCloudy.jpg">
					</c:if> <c:if test="${data.weather eq '흐림'}">
						<img src="/image/weather/cloudy.jpg">
					</c:if> <c:if test="${data.weather eq '비'}">
						<img src="/image/weather/rainy.jpg">
					</c:if> <c:if test="${data.weather eq '맑음'}">
						<img src="/image/weather/sunny.jpg">
					</c:if></th>
				<th>${data.temperatures}°C</th>
				<th>${data.precipitation}mm</th>
				<th>${data.microDust}</th>
				<th>${data.windSpeed}km/h</th>
			</tr>
		</c:forEach>
	</tbody>
</table>