package com.quiz.weather;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.weather.bo.WeatherHistoryBO;
import com.quiz.weather.domain.WeatherHistory;

@RequestMapping("/weather")
@Controller
public class WeatherHistoryController {
	
	@Autowired
	private WeatherHistoryBO weatherHistoryBO;
	
	// 정리할 것 + 복습
	
	// join하지말고 단순한 CRUD(각각 select), BO에서 로직으로 합치기
	// 컨트롤러 -> BO -> cache -> DB 이므로 한번 DB에서 꺼내오면 cache에서 빠르게 select해올 수 있음
	// DB업데이트할 때 cache도 함께 update 해줌
	
	// 목록 화면 (함수명 수정하기)
	@GetMapping("/weather_view")
	public String weatherView(Model model) {
		// db select
		List<WeatherHistory> history = weatherHistoryBO.getWeatherHistory();
		model.addAttribute("history", history);
		
		return "weather/main_layout";
	}
	
	
	// 추가 화면 (jsp명 수정하기)
	@GetMapping("/add_weather_view")
	public String addWeatherView() {
		// 화면뿌리기
		return "weather/saveWeather_layout";
	}
	
	// 날씨 insert (redirect 방법 2가지, request parameter 방법 2-3가지)
	// 1번 방법
	@PostMapping("/add_weather")
	public String addWeather(
			@ModelAttribute WeatherHistory thisWeather	// 1. 객체를 통으로 받아오기(Date)
			/*, Model model*/) {
		//model.addAttribute("thisWeather", thisWeather);
		
		// db insert
		weatherHistoryBO.addWeatherHistory(thisWeather);
		
		// 메인으로 redirect
		return "redirect:/weather/weather_view"; // 새 request 수행 // 여기서 break point 걸고 수행해보기
		
	}	
		
		
	// 2번 방법
//	@PostMapping("/add_weather")
//	public void addWeather throws IOException(
//			@RequestParam("date") String date, // 2. String으로 받아오기 (string으로 insert해도 DB에서 알아서 Date로 보내줌)
//			(나머지 requestparam도 넣어주면 됨)
												// 서버가 메인이므로 무엇을 받아올지 정할 수 있다
												// java string to date
//			파라미터 많을 때는 모두 주석처리하고 하나씩 주석해제해서 확인해보면 된다.
//			HttpServletResponse response) {
		//insert DB
//		weatherHistoryBO.addWeatherHistory(date, weather, microDust, temperatures, precipitation, windSpeed);

		// 메인으로 redirect
//		response.sendRedirect("/weather/weather_history_view");
//	}
		

		
		
		// 3번 방법 - model attribute는 파라미터 너무 많을 때만 쓰자!!!
//		@PostMapping("/add_weather")
//		public String addWeather(
//				@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, // 3. String으로 받아와서 Date로 변환하기 (다양한 방법이 있음)
//				@RequestParam("weather") String weather,
//				@RequestParam("microDust") String microDust,
//				@RequestParam("temperatures") double temperatures,
//				@RequestParam("precipitation") double precipitation,
//				@RequestParam("windSpeed") double windSpeed) {
			
			//insert DB
//			weatherHistoryBO.addWeatherHistory(date, weather, microDust, temperatures, precipitation, windSpeed);
			
//			return "redirect:/weather/weather_view";
//		}
	
		
		
	// 네트워크 탭에서 흐름 확인
	// 단순 return "weather/weather_view 하려면 함수 안 insert 코드 등을 다시 실행해야 함 -> 불편
	// controller에서 controller 메소드를  요청할 수 없음
	// 400은 주소는 일치, 파라미터 문제 404는 주소 문제
			
//	}
	
}
