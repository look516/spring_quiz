package com.quiz.lesson07.bo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson07.dao.CompanyRepository;
import com.quiz.lesson07.entity.CompanyEntity;

@Service
public class CompanyBO {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	// input:파라미터들 output:entity
	public CompanyEntity addCompany(String name, String business, String scale, int headcount) {
		CompanyEntity company = companyRepository.save(
					CompanyEntity.builder()
					.name(name)
					.business(business)
					.scale(scale)
					.headcount(headcount)
					.build()
				);
		return company;
	}
	
	// input: id, scale, headcount
	// output: CompanyEntity
	public CompanyEntity updateCompanyById(int id, String scale, int headcount) {
		CompanyEntity company = companyRepository.findById(id).orElse(null);
		return companyRepository.save(
					company.toBuilder()
					.scale(scale)
					.headcount(headcount)
					.build()
				);
	}
	
	public void deleteCompanyById(int id) {
//		CompanyEntity company = companyRepository.findById(id).orElse(null);
//		if (company != null) {
//			companyRepository.delete(company);
//		}
		
		Optional<CompanyEntity> companyOptional = companyRepository.findById(id);
		companyOptional.ifPresent(c -> companyRepository.delete(c));
		// companyOptional이 있을 때 CompanyEntity 타입의 c라는 객체에서 delete를 수행한다. 
	}

}