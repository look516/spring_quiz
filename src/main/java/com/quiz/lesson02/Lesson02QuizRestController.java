package com.quiz.lesson02;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.lesson02.bo.storeBO;
import com.quiz.lesson02.domain.store;

@RestController // @Controller + @ResponseBody
public class Lesson02QuizRestController {
	@Autowired
	private storeBO storeBO;
	
	// http://localhost:8080/lesson02/quiz01
	@RequestMapping("/lesson02/quiz01")
	public List<store> quiz01() {
		List<store> storeList = storeBO.getStoreList();
		return storeList; //json
	}
}
