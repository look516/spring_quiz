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
	
	// 하나일 때는 @Param빼는 게 나음. 굳이 map으로 내리는 거보다 하나의 변수로 내리는 게 나으므로
	
	public List<RealEstate> selectRealEstateListByRentPrice(@Param("rentPrice") int rentPrice);
	
	public List<RealEstate> selectRealEstateListByAreaAndPrice(
			// mybatis는 파라미터를 하나만 인식할 수 있기 때문에
			// 하나의 맵으로 만들어 보내야 한다.
			// 맵으로 만들어주는 어노테이션 @Param
			// @Param("키") "키" => xml #{키}
			@Param("area") int area,
			@Param("price") int price);
	
	
	
	
	
	public int insertRealEstate(RealEstate realEstate);
	
	public int insertRealEstateAsField(
			@Param("realtorId") int realtorId,
			@Param("address") String address,
			@Param("area") int area,
			@Param("type") String type,
			@Param("price") int price,
			@Param("rentPrice") Integer rentPrice);
	
	public int updateRealEstateById(
			@Param("id") int id,
			@Param("type") String type,
			@Param("price") int price);
}
