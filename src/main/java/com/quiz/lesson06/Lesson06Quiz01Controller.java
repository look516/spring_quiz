package com.quiz.lesson06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.lesson06.bo.BookmarkBO;
import com.quiz.lesson06.domain.Bookmark;

@RequestMapping("/lesson06/quiz01")
@Controller
public class Lesson06Quiz01Controller {
	
	@Autowired
	private BookmarkBO bookmarkBO;
	
	// 추가 화면
	@GetMapping("/add_bookmark_view")
	public String addBookmarkView() {
		return "lesson06/addBookmark";
	}
	
	// db insert (AJAX의 요청)
	@PostMapping("/add_bookmark")
	@ResponseBody
	public Map<String, Object> addBookmark(
			@RequestParam("name") String name,
			@RequestParam("url") String url) {
		
		// 실제 insert
		bookmarkBO.addBookmarkList(name, url);
		
		// 응답
		// {"code":1, "result":"성공"}	// JSON String
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		
		// return JSON string
		return result;
	}
	
	// 결과 화면
	@GetMapping("/after_add_bookmark_view")
	public String afterAddBookmarkView(Model model) {
		
		// db select
		List<Bookmark> bookmarkList = bookmarkBO.getBookmarkList();
		model.addAttribute("bookmarkList", bookmarkList);
		
		return "lesson06/afterAddBookmark";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// QUIZ 02
	
	@ResponseBody
	@RequestMapping("/is_dupication")
	public Map<String, Boolean> isDuplication(
			@RequestParam("url") String url) {
		
		// db 조회
		boolean existUrl = bookmarkBO.existBookmarkByUrl(url);
				
		//
		Map<String, Boolean> result = new HashMap<>();
		result.put("isDuplication", existUrl);
		return result;
	}
}
