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
	
	// 1) 리스트 페이지
	public List<Booking> getBookingList() {
		return bookingMapper.selectBookingList();
	}
	
	// 1) 예약 삭제 페이지
	public int deleteBookingById(int id) {
		return bookingMapper.deleteBookingById(id); // 성공한 행의 개수 리턴
	}
	
	// 2) 예약 추가 페이지
	public int addBookingList(String name, String date, int day, int headcount, String phoneNumber) {
		return bookingMapper.insertBookingList(name, date, day, headcount, phoneNumber);
	}
	
	// 3) 예약 조회 페이지
	public Booking getBookingByNameAndPhoneNumber(String name, String phoneNumber) {
		// null (heap 영역에 확보 안 됨) [] (heap 영역에 확보됨)
		// 변수 이름있는 stack (참조자료형)
		
		// 가능한 값: [], [booking...]
		// 없으면 alert, 있으면 여러 개 중 대기중, 확정만 가져와서 리스트로 뿌리는 로직
		List<Booking> bookingList = bookingMapper.selectBookingByNameAndPhoneNumber(name, phoneNumber);
		if (bookingList.isEmpty()) {
			return null;
		}
		
		return bookingList.get(bookingList.size() - 1); // 마지막 값을 준다. (최신 예약)
		
	}
}
