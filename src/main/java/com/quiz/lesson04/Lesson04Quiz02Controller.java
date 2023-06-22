package com.quiz.lesson04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.lesson04.bo.RealtorBO;
import com.quiz.lesson04.domain.Realtor;

@RequestMapping("/lesson04/quiz02")
@Controller
public class Lesson04Quiz02Controller {
	
	@Autowired
	private RealtorBO realtorBO;
	
	// 추가 페이지
	@GetMapping("/add_realtor_view")
	public String addRealtorView() {
		return "lesson04/addRealtor";
	}
	
	// 분기문?
	// 결과 페이지
	@PostMapping("/add_realtor")
	public String addRealtor(
			@ModelAttribute Realtor realtor, // 객체 하나만 받아올 때는 @ModelAttribute 생략 가능
			Model model) {
		
		// db insert - 먼저 코드 쓰고 검증하고 select문 하자.
		realtorBO.addRealtor(realtor);
		
		// db select by id
		Realtor newRealtor = realtorBO.getRealtorById(realtor.getId());
		
		// model 담기
		model.addAttribute("realtor", newRealtor);
		
		// 결과 페이지 이동 // 함수 내 이 부분만 작성하고 브레이크 포인트 걸어서 확인해보자(양끝단)
		return "lesson04/afterAddRealtor";
	}
}