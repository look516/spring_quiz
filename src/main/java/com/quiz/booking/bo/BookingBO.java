package com.quiz.booking.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.booking.dao.BookingMapper;
import com.quiz.booking.domain.Booking;

@Service
public class BookingBO {
	
	@Autowired
	private BookingMapper bookingMapper;
	
	// 리스트 페이지
	public List<Booking> getBookingList() {
		return bookingMapper.selectBookingList();
	}
	
	// 예약 삭제 페이지
	public int deleteBookingById(int id) {
		return bookingMapper.deleteBookingById(id);
	}
	
	// 예약 추가 페이지
	public void addBookingList(String name, String date, int day, int headcount, String phoneNumber) {
		bookingMapper.insertBookingList(name, date, day, headcount, phoneNumber);
	}
	
	// 예약 조회 페이지
	public Booking getBookingByNameAndPhoneNumber(String name, String phoneNumber) {
		return bookingMapper.selectBookingByNameAndPhoneNumber(name, phoneNumber);
	}
}
