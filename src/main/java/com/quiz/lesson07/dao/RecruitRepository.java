package com.quiz.lesson07.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.lesson07.entity.RecruitEntity;

@Repository
public interface RecruitRepository extends JpaRepository<RecruitEntity, Integer> {
	public List<RecruitEntity> findByCompanyId(int companyId);
	public List<RecruitEntity> findByPositionAndType(String position, String type);
	public List<RecruitEntity> findByTypeOrSalaryGreaterThan(String type, int salary);
	public List<RecruitEntity> findTop3ByTypeOrderBySalaryDesc(String type);
	public List<RecruitEntity> findByRegionAndSalaryBetween(String region, int start, int end);
	// entity name 없는데 recruit가 작동되면 table name으로 인식을 하는 것임.
	// 그럼 entity name은 JPQL에서 사용이 된다
	@Query(value="select * from recruit where deadline>:deadline and salary>=:salary and type=:type order by salary desc", nativeQuery = true)
	public List<RecruitEntity> findBySalary(
			@Param("deadline") String deadline, // string ok, date ""와 타입 불일치, ZonedDateTime 현재 시간(timezone) 관련이므로 상관없다.
			@Param("salary") int salary,
			@Param("type") String type);
}