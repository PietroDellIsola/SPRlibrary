package com.ap.SPRlibrary.repository;

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

import com.ap.SPRlibrary.entity.Loan;

@Repository("loanRepository")
public interface LoanRepository extends JpaRepository<Loan, Integer> {
	
	@Query(value = " SELECT * FROM Loans WHERE fk_isbn = :fk_isbn AND fk_fiscal_code = :fk_fiscal_code AND returned=false", nativeQuery = true)
	public Loan getLoan(@Param("fk_isbn")String fk_isbn, @Param("fk_fiscal_code")String fk_fiscal_code); 
	
	@Transactional
	@Modifying /*@Modifying is for telling spring-data-jpa that this query is an update operation and it requires executeUpdate() not executeQuery().*/
	@Query(value = " UPDATE Loans l SET l.returned = true WHERE l.id_loan = :id_loan AND returned=false", nativeQuery = true)
	public int updateToReturned(@Param("id_loan")Integer id_loan); 

}
