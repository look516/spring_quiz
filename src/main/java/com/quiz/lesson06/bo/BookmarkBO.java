package com.quiz.lesson06.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson06.dao.BookmarkMapper;
import com.quiz.lesson06.domain.Bookmark;

@Service
public class BookmarkBO {
	
	@Autowired
	private BookmarkMapper bookmarkMapper;
	
	public void addBookmarkList(String name, String url) {
		bookmarkMapper.insertBookmarkList(name, url);
	}
	
	public List<Bookmark> getBookmarkList() {
		return bookmarkMapper.selectBookmarkList();
	}
	
	
	
	
	// 2
	
	public Bookmark getBookmarkByUrl(String url) {
		return bookmarkMapper.selectBookmarkByUrl(url);
	}
	
	/*
	 public boolean existBookmarkByUrl(String url) {
	 	return bookmarkMapper.existBookmarkByUrl(url);
	 }
	 */
	
	public int deleteBookmarkById(int id) {
		return bookmarkMapper.deleteBookmarkById(id);
	}
}
