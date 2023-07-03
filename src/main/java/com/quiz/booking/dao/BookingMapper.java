package com.quiz.booking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quiz.booking.domain.Booking;

@Repository
public interface BookingMapper {
	// 1) 리스트
	public List<Booking> selectBookingList();
	
	// 1) 예약 삭제
	public int deleteBookingById(int id);
	
	// 2) 예약 추가
	public int insertBookingList(
			@Param("name") String name,
			@Param("date") String date,
			@Param("day") int day,
			@Param("headcount") int headcount,
			@Param("phoneNumber") String phoneNumber);
	
	// 3) 예약 조회
	public List<Booking> selectBookingByNameAndPhoneNumber(
			@Param("name") String name,
			@Param("phoneNumber") String phoneNumber);
}
