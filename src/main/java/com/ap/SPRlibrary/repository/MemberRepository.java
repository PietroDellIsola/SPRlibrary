package com.ap.SPRlibrary.repository;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;/*
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;*/
import org.springframework.transaction.annotation.Transactional;

import com.ap.SPRlibrary.entity.Member;

@Repository("memberRepository")
public interface MemberRepository extends JpaRepository<Member, String> {
	@Transactional
	@Modifying /*@Modifying is for telling spring-data-jpa that this query is an update operation and it requires executeUpdate() not executeQuery().*/
	@Query(value = " UPDATE Members SET name = :name , surname = :surname , dob = :dob WHERE fiscal_code = :fiscal_code ", nativeQuery = true)
	public int updateMember(@Param("name")String name, @Param("surname")String surname, @Param("dob")Calendar dob, @Param("fiscal_code")String fiscal_code); 

	

}
