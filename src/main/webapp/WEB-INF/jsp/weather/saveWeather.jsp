<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h3>날씨 입력</h3>

<form method="post" action="/weather/add_weather_history">
	<span>날짜</span>
	<input type="text" name="weather" class="form-control">
		
	<span>날씨</span>
	<input type="text" name="temperatures" class="form-control">
	
	<span>미세먼지</span>
	<input type="text" name="microDust" class="form-control">
	
	<span>기온</span>
	<input type="text" name="date" class="form-control">
	
	<span>강수량</span>
	<input type="text" name="precipitation" class="form-control">
	
	<span>풍속</span>
	<input type="text" name="windSpeed" class="form-control">
		
	<input type="submit"
		value="저장" class="btn btn-success">
</form>