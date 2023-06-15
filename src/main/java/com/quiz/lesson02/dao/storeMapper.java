package com.quiz.lesson02.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quiz.lesson02.domain.store;

@Repository
public interface storeMapper {
	public List<store> selectStoreList();
}
