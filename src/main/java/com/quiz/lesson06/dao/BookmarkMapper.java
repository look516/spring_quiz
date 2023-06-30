package com.quiz.lesson06.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quiz.lesson06.domain.Bookmark;

@Repository
public interface BookmarkMapper {
	
	
	public void insertBookmarkList(
			@Param("name") String name,
			@Param("url") String url);
	
	public List<Bookmark> selectBookmarkList();
	
	
	
	
	
	
	
	
	
	
	
	
	// 2
	
	//public boolean existBookmarkByUrl(String url);
	
	public Bookmark selectBookmarkByUrl(String url);
	
	public int deleteBookmarkById(int id);
}
