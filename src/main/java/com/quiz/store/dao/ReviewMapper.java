package com.quiz.store.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quiz.store.domain.Review;

@Repository
public interface ReviewMapper {
	public List<Review> selectReviewList(int id);
}
