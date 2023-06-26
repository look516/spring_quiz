package com.quiz.weather;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.weather.bo.WeatherHistoryBO;
import com.quiz.weather.domain.WeatherHistory;

@RequestMapping("/weather")
@Controller
public class WeatherHistoryController {
	
	@Autowired
	private WeatherHistoryBO weatherHistoryBO;
	
	@GetMapping("/weather_view")
	public String weatherHistory(Model model) {
		
		List<WeatherHistory> history = weatherHistoryBO.getWeatherHistory();

		model.addAttribute("history", history);
		
		return "weather/main_layout";
	}
	
	@GetMapping("/add_weather_history")
	public String addWeatherHistory() {
		
		// 화면뿌리기
		return "weather/saveWeather_layout";
	}
	
	@PostMapping("/after_add_weather_history")
	public String afterAddWeatherHistory(
			@RequestParam("date") Date date,
			@RequestParam("weather") String weather,
			@RequestParam("temperatures") double temperatures,
			@RequestParam("precipitation") double precipitation,
			@RequestParam("microDust") String microDust,
			@RequestParam("windSpeed") double windSpeed,
			Model model) {
		WeatherHistory thisWeather = null;
		thisWeather.setDate(date);
		thisWeather.setWeather(weather);
		thisWeather.setTemperatures(temperatures);
		thisWeather.setPrecipitation(precipitation);
		thisWeather.setMicroDust(microDust);
		thisWeather.setWindSpeed(windSpeed);
		
		model.addAttribute("thisWeather", thisWeather);
		// db insert
		
		// 메인으로 redirect
		return "weather/afterSaveWeather";
	}
}
