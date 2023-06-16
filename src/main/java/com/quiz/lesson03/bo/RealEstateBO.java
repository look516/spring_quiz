package com.quiz.lesson03.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson03.dao.RealEstateMapper;
import com.quiz.lesson03.domain.RealEstate;

@Service
public class RealEstateBO {
	@Autowired
	private RealEstateMapper realEstateMapper;
	
	// input: id
	// output: RealEstate => 컨트롤러한테
	public RealEstate getRealEstateById(int id) {
		// BO의 기준에서 id가 꼭 필요하면 int id, null도 올 수 있다면 Integer id
		return realEstateMapper.selectRealEstateById(id);
	}
	
	// input: rentPrice
	// output: List<RealEstate>
	public List<RealEstate> getRealEstateListByRentPrice(int rentPrice) {
		return realEstateMapper.selectRealEstateListByRentPrice(rentPrice);
	}
	
//	public List<RealEstate> getRealEstateListByAreaNPrice (int area, int price) {
//		return;
//	}
}
