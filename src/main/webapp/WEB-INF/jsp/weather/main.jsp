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
				<!-- simpledateformat 사용 불가 -->
				<td><fmt:formatDate value="${data.date}" pattern="yyyy년 M월 d일" /></td>
				<td>
					<c:choose>
						<c:when test="${data.weather eq '맑음'}">
							<img src="/image/weather/sunny.jpg" alt="맑음">
						</c:when>
						<c:when test="${data.weather eq '흐림'}">
							<img src="/image/weather/cloudy.jpg" alt="흐림">
						</c:when>
						<c:when test="${data.weather eq '구름조금'}">
							<img src="/image/weather/partlyCloudy.jpg" alt="구름조금">
						</c:when>
						<c:when test="${data.weather eq '비'}">
							<img src="/image/weather/rainy.jpg" alt="비">
						</c:when>
						<c:otherwise>
							${data.weather}
						</c:otherwise>
					</c:choose>
					<%-- <c:if test="${data.weather eq '구름조금'}">
						<img src="/image/weather/partlyCloudy.jpg">
					</c:if>
					<c:if test="${data.weather eq '흐림'}">
						<img src="/image/weather/cloudy.jpg">
					</c:if>
					<c:if test="${data.weather eq '비'}">
						<img src="/image/weather/rainy.jpg">
					</c:if>
					<c:if test="${data.weather eq '맑음'}">
						<img src="/image/weather/sunny.jpg">
					</c:if> --%>
				</td>
				<td>${data.temperatures}°C</td>
				<td>${data.precipitation}mm</td>
				<td>${data.microDust}</td>
				<td>${data.windSpeed}km/h</td>
			</tr>
		</c:forEach>
	</tbody>
</table>