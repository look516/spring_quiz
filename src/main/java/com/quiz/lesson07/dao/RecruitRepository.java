package com.quiz.lesson07.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.lesson07.entity.RecruitEntity;

@Repository
public interface RecruitRepository extends JpaRepository<RecruitEntity, Integer> {
	// 콘솔의 쿼리문 확인하자
	// dao에서 repository(jpa-단순조회) mapper(mybatis-복잡조회) 둘 다 만들어서 bo에 둘 다 autowired해서 쓰자
	
	// JPQL -> Entity에 조회하는 개념
	// quiz02_2
	public List<RecruitEntity> findByCompanyId(int companyId);
	
	// quiz02_3
	public List<RecruitEntity> findByPositionAndType(String position, String type);
	
	// quiz02_4
	public List<RecruitEntity> findByTypeOrSalaryGreaterThanEqual(String type, int salary);
	
	// quiz02_5
	public List<RecruitEntity> findTop3ByTypeOrderBySalaryDesc(String type);
	
	// quiz02_6
	public List<RecruitEntity> findByRegionAndSalaryBetween(String region, int start, int end);
	
	// quiz02_7 (native query -> DB에 직접 조회(Mysql 쿼리문)
	// entity name 없는데 recruit가 작동되면 table name으로 인식을 하는 것임.
	// 그럼 entity name은 JPQL에서 사용이 된다 -> @Query(value="쿼리문과 유사한 JPQL 문법")
	@Query(value="select * from recruit where deadline > :deadline and salary >= :salary and type = :type order by salary desc", nativeQuery = true)
	public List<RecruitEntity> findByDeadlineAfterAndSalaryGreaterThanEqualAndTypeOrderBySalaryDesc(
			@Param("deadline") String deadline, // string ok, date ""와 타입 불일치, ZonedDateTime 현재 시간(timezone) 관련이므로 상관없다.
			@Param("salary") int salary,
			@Param("type") String type);
	
	// localtype != string 오류남
	//public List<RecruitEntity> findByDeadlineAfterAndSalaryGreaterThanEqualAndTypeOrderBySalaryDesc(String deadline, int salary, String type);
}