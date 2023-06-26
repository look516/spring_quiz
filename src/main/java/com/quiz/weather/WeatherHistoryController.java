package com.quiz.weather;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.lesson04.domain.Realtor;
import com.quiz.weather.bo.WeatherHistoryBO;
import com.quiz.weather.domain.WeatherHistory;

@RequestMapping("/weather")
@Controller
public class WeatherHistoryController {
	
	@Autowired
	private WeatherHistoryBO weatherHistoryBO;
	
	@GetMapping("/weather_view")
	public String weatherView(Model model) {
		// db select
		List<WeatherHistory> history = weatherHistoryBO.getWeatherHistory();
		model.addAttribute("history", history);
		
		return "weather/main_layout";
	}
	
	@GetMapping("/add_weather_view")
	public String addWeatherView() {
		// 화면뿌리기
		return "weather/saveWeather_layout";
	}
	
	@PostMapping("/add_weather")
	public String addWeather(
			// 1. Date로 받아오기
			@ModelAttribute WeatherHistory thisWeather
			/*, Model model*/) {
		
		//model.addAttribute("thisWeather", thisWeather);
		// db insert
		weatherHistoryBO.addWeatherHistory(thisWeather);
		// 메인으로 redirect
		return "redirect:/weather/weather_view";
	}
}
