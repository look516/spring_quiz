package com.quiz.lesson02.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson02.dao.storeMapper;
import com.quiz.lesson02.domain.store;

@Service
public class storeBO {
	@Autowired
	private storeMapper storeMapper;
	
	// input: X
	// output: storeList => 컨트롤러한테 돌려준다.
	public List<store> getStoreList() {
		List<store> storeList = storeMapper.selectStoreList();
		return storeList;
	}
}
