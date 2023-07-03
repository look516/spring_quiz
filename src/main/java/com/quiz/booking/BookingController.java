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
	
	// 1) 리스트 페이지
	@GetMapping("/booking_list_view")
	public String bookingListView(Model model) {
		
		List<Booking> bookingList = bookingBO.getBookingList();
		model.addAttribute("bookingList", bookingList);
		
		return "booking/bookingList";
	}
	
	
	// 1) 예약 삭제 - AJAX
	@ResponseBody
	@DeleteMapping("/delete_booking")
	public Map<String, Object> deleteBooking( // map 형태
			@RequestParam("bookingId") int id) {

		// db delete
		int row = bookingBO.deleteBookingById(id); // 1이라고 임의값을 넣고 break point
		
		Map<String, Object> result = new HashMap<>();
		if (row > 0) {
			// 성공
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			// 삭제할 행 없음(정상응답 but 로직상 실패)
			result.put("code", 500);
			result.put("errorMessage", "삭제될 데이터가 없습니다.");
		}
		return result;
	}
		
	
	// 2) 예약 페이지
	@GetMapping("/add_booking_list_view")
	public String addBookingListView() {
		return "booking/addBookingList";
	}
	
	// 2) 예약 추가 - AJAX
	@ResponseBody
	@PostMapping("/add_booking_list")
	public Map<String, Object> addBookingList(
			@RequestParam("name") String name,
			@RequestParam("date") String date, // Date 변환 알아서 됨 //@DateTimeFormat을 붙이고 Date 객체로 받아와도 된다.
			@RequestParam("day") int day,
			@RequestParam("headcount") int headcount,
			@RequestParam("phoneNumber") String phoneNumber) {
		
		// db insert
		int row = bookingBO.addBookingList(name, date, day, headcount, phoneNumber);
		
		// response
		Map<String, Object> result = new HashMap<>();
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else { // DB에서 잘 안 된 경우
			result.put("code", 500);
			result.put("errorMessage", "예약 데이터가 추가되지 못했습니다.");
		}
		
		 return result;
	}
	

	
	// 3) 조회 페이지
	// 예약 상태를 변경할 수 있는 로직(update)
	// 이름 및 전화번호가 중복될 경우 두 개 이상의 데이터를 출력하는 로직?
	// 어차피 코드가 진행됐다는 것은 null이 아니라는 것이므로 조회할 데이터가 없을 때는 자동으로 error function이 수행되는 건지
	// 아니면 else문으로 alert(data.errorMessage)로 구분해야 하는 건지
	// 화면이 안 깨지게 하려면?
	// 모든 컬럼이 같다면 추가불가, 이름과 전번만 같은 것과 같이 하나라도 다른 컬럼이 있다면 추가 가능
	// 세부 로직 추가
	// 체화
	@GetMapping("/search_booking_view")
	public String searchBookingView() {
		return "booking/searchBooking";
	}
	
	// 3) 예약 조회 - AJAX
	@ResponseBody // model은 jsp를 리턴하고 responsebody 어노테이션이 없을 때 사용 (이엘 문법과${}함께 사용하는데 success:function(data){}에서는 이엘 문법 사용 불가)
	// responsebody는 json만 리턴한다
	@PostMapping("/search_booking")
	public Map<String, Object> searchBooking(
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber) {

		// db select
		Booking booking = bookingBO.getBookingByNameAndPhoneNumber(name, phoneNumber);
		
		// 응답
		Map<String, Object> result = new HashMap<>();
		if (booking != null) { //booking이 null이 아니라 booking의 요소가 null이라고 한다면 일치하는 행이 없을 경우 nullpoint가 된다.
			// 성공
			result.put("code", 1);
			// result.put("result", "성공");
			result.put("booking", booking);
			
			/*result.put("name", booking.getName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(booking.getDate());
			result.put("date", date);
			result.put("day", booking.getDay());
			result.put("headcount", booking.getHeadcount());
			result.put("state", booking.getState());*/
		} else {
			// 조회할 행 없음
			result.put("code", 300);
			result.put("errorMessage", "예약 내역이 없습니다.");
		}
		return result;
	}
	
}
