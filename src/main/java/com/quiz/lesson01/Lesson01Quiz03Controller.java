package com.quiz.lesson01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Lesson01Quiz03Controller {
	// http://localhost:8080/lesson01/quiz03/1
	@RequestMapping("/lesson01/quiz03/1")
	public String quiz03_1() {
		// @ResponseBody가 아닌 @Controller인 상태로 String 리턴하면
		// ViewResolver 클래스에 의해 리턴된 String의 view(jsp) 경로를 찾아 수행한다.
		
		// jsp의 경로를 리턴한다.
		// 맨 앞에 / 쓰지 않도록 주의
		return "lesson01/quiz03";
	}
}