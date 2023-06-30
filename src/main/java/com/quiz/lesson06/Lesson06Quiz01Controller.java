package com.quiz.lesson06;

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
	
	
	
	// QUIZ 02
	
	// AJAX 가 하는 요청 - URL 중복확인
	@ResponseBody
	@PostMapping("/is_duplication_url")
	public Map<String, Object> isDuplicationUrl(
			@RequestParam("url") String url) {
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1); // 성공 (서버와 클라이언트 사이 약속)
		result.put("isDuplication", false); // 중복이 아닌 게 기본값
		
		// db select by url
		Bookmark bookmark = bookmarkBO.getBookmarkByUrl(url); // 중복된 기존의 북마크가 들어오거나 or null이 들어오거나
		if (bookmark != null) { // 중복이 되면 키 수정
			result.put("isDuplication", true);
		}
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 위의 함수
	/*@ResponseBody
	@RequestMapping("/is_dupication")
	public Map<String, Boolean> isDuplication(
			@RequestParam("url") String url) {
		
		// db 조회
		boolean existUrl = bookmarkBO.existBookmarkByUrl(url);
				
		//
		Map<String, Boolean> result = new HashMap<>();
		result.put("isDuplication", existUrl); // exist가 0이면 false => 중복 안 됨
		return result;
	}*/
	
	
	
	// quiz02
	// AJAX가 하는 요청 - 즐겨찾기 삭제
	@ResponseBody
	@DeleteMapping("/delete_bookmark")
	public Map<String, Object> deleteBookmark(
			@RequestParam("bookmarkId") int id) {
		
		// db delete
		// 여기 작성 전에 브라우저로 테스트하고 싶다면 @GetMapping으로 바꿔주면 됨
		// 405:method 불일치
		// 단, delete 할 때는 get 절대 쓰면 안 됨
		
		int row = bookmarkBO.deleteBookmarkById(id);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();

		// ajax가 성공해도 로직 상 delete가 실패했을 수 있음: 받아온 지운 행의 개수가 1임을 확인해야 함(성공)
		if (row == 1) { // row > 0
			// 성공
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			// 삭제된 행 없음 - 실패 : try-catch로 세세하게 알 수 있음
			result.put("code", 500);
			result.put("errorMessage", "삭제될 데이터가 없습니다."); // 에러의 이유를 서버가 내려준다.
		}
		
		return result;
	}
}
