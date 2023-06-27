package com.quiz.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.lesson02.bo.storeBO;
import com.quiz.lesson02.domain.store;
import com.quiz.store.bo.ReviewBO;
import com.quiz.store.domain.Review;

@RequestMapping("/store")
@Controller
public class StoreController {
	
	@Autowired
	private storeBO storeBo;
	private ReviewBO reviewBO;
	
	@GetMapping("/store_view")
	public String storeView(Model model) {
		List<store> storeList = storeBo.getStoreList();
		model.addAttribute("storeList", storeList);
		return "/store/store";
	}
	
	@GetMapping("/review")
	public String reviewView(
			@RequestParam("storeId") int id,
			@RequestParam("storeName") String storeName,
			Model model) {
		// select
		List<Review> reviewList = reviewBO.getReviewList(id);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("storeName", storeName);
		return "/store/review";
	}
	
	
}
