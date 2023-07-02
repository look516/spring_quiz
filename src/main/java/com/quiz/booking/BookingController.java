package com.quiz.booking;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.booking.bo.BookingBO;
import com.quiz.booking.domain.Booking;

@RequestMapping("/booking")
@Controller
public class BookingController {
	@Autowired
	private BookingBO bookingBO;
	
	// 리스트 페이지
	@GetMapping("/booking_list_view")
	public String bookingListView(Model model) {
		
		List<Booking> bookingList = bookingBO.getBookingList();
		model.addAttribute("bookingList", bookingList);
		
		return "booking/bookingList";
	}
	
	
	// 예약 삭제 - AJAX
	@ResponseBody
	@DeleteMapping("/delete_booking")
	public Map<String, Object> deleteBooking( // map 형태
			@RequestParam("bookingId") int id) {

		// db delete
		int row = bookingBO.deleteBookingById(id);
		
		Map<String, Object> result = new HashMap<>();
		if (row == 1) {
			// 성공
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			// 삭제할 행 없음
			result.put("code", 500);
			result.put("errorMessage", "삭제될 데이터가 없습니다.");
		}
		return result;
	}
		
	
	// 예약 페이지
	@GetMapping("/add_booking_list_view")
	public String addBookingListView() {
		return "booking/addBookingList";
	}
	
	// 예약 추가 - AJAX
	@ResponseBody
	@PostMapping("/add_booking_list")
	public Map<String, Object> addBookingList(
			@RequestParam("name") String name,
			@RequestParam("date") String date, // Date 변환 알아서 됨
			@RequestParam("day") int day,
			@RequestParam("headcount") int headcount,
			@RequestParam("phoneNumber") String phoneNumber) {
		
		// db insert
		bookingBO.addBookingList(name, date, day, headcount, phoneNumber);
		
		// response
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		
		 return result;
	}
	

	
	// 조회 페이지
	// 예약 상태를 변경할 수 있는 로직
	// 이름 및 전화번호가 중복될 경우 두 개 이상의 데이터를 출력하는 로직?
	// 어차피 코드가 진행됐다는 것은 null이 아니라는 것이므로 조회할 데이터가 없을 때는 자동으로 error function이 수행되는 건지
	// 아니면 else문으로 alert(data.errorMessage)로 구분해야 하는 건지
	// 세부 로직 추가
	// 체화
	@GetMapping("/search_booking_view")
	public String searchBookingView() {
		return "booking/searchBooking";
	}
	
	// 예약 조회 - AJAX
	@ResponseBody
	@PostMapping("/search_booking")
	public Map<String, Object> searchBooking(
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber) {

		// db select
		Booking booking = bookingBO.getBookingByNameAndPhoneNumber(name, phoneNumber);
		
		Map<String, Object> result = new HashMap<>();
		//if (booking.getName() != null) {
			// 성공
			result.put("code", 1);
			result.put("result", "성공");
			result.put("name", booking.getName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 dd일");
			String date = sdf.format(booking.getDate());
			result.put("date", date);
			result.put("day", booking.getDay());
			result.put("headcount", booking.getHeadcount());
			result.put("state", booking.getState());
		/*} else {
			// 조회할 행 없음
			result.put("code", 500);
			result.put("errorMessage", "조회될 데이터가 없습니다.");
		}*/
		return result;
	}
	
}
