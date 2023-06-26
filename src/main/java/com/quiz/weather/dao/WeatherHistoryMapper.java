package com.quiz.weather.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quiz.weather.domain.WeatherHistory;

@Repository
public interface WeatherHistoryMapper {
	public List<WeatherHistory> selectWeatherHistory();
	
	public void insertWeatherHistory(WeatherHistory weatherHistory);
}
