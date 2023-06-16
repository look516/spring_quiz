package com.quiz.lesson03.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quiz.lesson03.domain.RealEstate;

@Repository
public interface RealEstateMapper {
	// public RealEstate selectRealEstateById(int id);
	// xml로 내려가는 파라미터 id '하나'만 보냄 => 어노테이션 생략가능
	
	public RealEstate selectRealEstateById(@Param("id") int id); // @Param("id") int id111로 해도 id로 매핑된다.
	// '여러' 개를 보낼 때는 mybatis용 @Param 어노테이션 생략불가
	// controller에서 @RequestParam 이랑 헷갈리지 말자.
	
	public List<RealEstate> selectRealEstateListByRentPrice(@Param("rentPrice") int rentPrice);
	
	//public List<RealEstate> selectRealEstateListByAreaNPrice(@Param("area") int area, @Param("price") int price);
}
