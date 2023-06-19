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
	
	// input: int, int
	// output: List<RealEstate>
	public List<RealEstate> getRealEstateListByAreaAndPrice (int area, int price) {
		return realEstateMapper.selectRealEstateListByAreaAndPrice(area, price);
	}
	
	
	
	
	// Mapper에서 넘겨줬어도 넘겨주든 말든은 BO의 마음 -> 안 넘겨주려면 void로 해도 됨
	public int addRealEstate(RealEstate realEstate) { // insert할 객체를 받아서 성공한 행의 개수를 리턴
		return realEstateMapper.insertRealEstate(realEstate);
	}
	
	public int addRealEstateAsField(int realtorId, String address, int area,
			String type, int price, Integer rentPrice) { // 확장성 고려해서 Integer
		return realEstateMapper.insertRealEstateAsField(realtorId, address, area, type, price, rentPrice);
	}
	
	
	
	
	
	public int updateRealEstateById(int id, String type, int price) {
		return realEstateMapper.updateRealEstateById(id, type, price);
	}
}
