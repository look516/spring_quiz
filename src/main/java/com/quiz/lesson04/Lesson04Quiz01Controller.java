package com.quiz.lesson04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.lesson04.bo.SellerBO;
import com.quiz.lesson04.domain.Seller;

@RequestMapping("/lesson04/quiz01")
@Controller
public class Lesson04Quiz01Controller {
	
	@Autowired
	private SellerBO sellerBO;
	
	// 판매자 추가 페이지
	@GetMapping("/add_seller_view")
	public String addSellerView() {
		return "lesson04/addSeller";
	}
	
	// 추가 action	""으로 들어오지만 null로 들어올 수 있다고 허용이라 생각
	@PostMapping("/add_seller")
	public String addSeller(
			@RequestParam("nickname") String nickname,
			@RequestParam(value = "profile_image_url", required = false) String profileImageUrl,
			@RequestParam("temperature") double temperature) { // ""는 에러나므로 필수
		
		// db insert
		sellerBO.addSeller(nickname, profileImageUrl, temperature);
		
		// 입력 성공 페이지
		return "lesson04/afterAddSeller";
	}
	
	// 최근 가입자 정보 페이지
	@GetMapping("/seller_info")
	public String sellerInfo(
			@RequestParam(value = "id", required = false) Integer id,
			Model model) {

		Seller seller = null;
		
		if (id == null) {
			// 최근 가입자 db select
			seller = sellerBO.getLatestSeller();
		} else {
			// id가 있는 경우
			seller = sellerBO.getSellerById(id);
			
		}
		
		model.addAttribute("seller", seller);			
		
		return "lesson04/sellerInfo";
	}
}
