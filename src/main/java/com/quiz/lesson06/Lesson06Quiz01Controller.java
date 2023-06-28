package com.quiz.lesson06;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@RequestMapping("/add_bookmark_view")
	public String addBookmarkView() {
		return "lesson06/addBookmark";
	}
	
	// db insert
	@PostMapping("add_bookmark")
	@ResponseBody
	public String addBookmark(
			@RequestParam("name") String name,
			@RequestParam("url") String url) {
		
		// 실제 insert
		bookmarkBO.addBookmarkList(name, url);
		
		// return string
		return "성공";
	}
	
	// 결과 화면
	@RequestMapping("/after_add_bookmark_view")
	public String afterAddBookmarkView(Model model) {
		
		// db select
		List<Bookmark> bookmarkList = bookmarkBO.getBookmarkList();
		model.addAttribute("bookmarkList", bookmarkList);
		
		return "lesson06/afterAddBookmark";
	}
}
