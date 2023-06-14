package com.quiz.lesson01;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/lesson01/quiz01")
public class Lesson01Quiz01Controller {
	@RequestMapping("/1")
	@ResponseBody
	public String quiz01_1 () {
		String text = "<h3>테스트 프로젝트 완성</h3>"
				+ "<b>해당 프로젝트를 통해서 문제 풀이를 진행합니다.</b>";
		return text;
	}
	
	@RequestMapping("/2")
	@ResponseBody
	public Map<String, Object> quiz01_2 () {
		Map<String, Object> map = new HashMap<>(); // Object 대신 Integer 사용가능
		map.put("국어", 80);
		map.put("수학", 90);
		map.put("영어", 85);
		return map;
	}
}
