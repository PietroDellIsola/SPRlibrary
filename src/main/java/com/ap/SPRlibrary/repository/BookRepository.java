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

import com.ap.SPRlibrary.entity.Book;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book, String> {
	
	@Transactional
	@Modifying /*@Modifying is for telling spring-data-jpa that this query is an update operation and it requires executeUpdate() not executeQuery().*/
	@Query(value = " UPDATE Books B SET B.title = :title , B.edition = :edition , B.year = :year, B.lang =:lang, B.publisher =:publisher, B.available_copies =:available_copies WHERE B.isbn = :isbn ", nativeQuery = true)
	//@Query(value = " UPDATE Books B SET B.title = :title , B.edition = :edition , B.year = :year WHERE B.isbn = :isbn ", nativeQuery = true)
	public int updateBook(@Param("title")String title, @Param("edition")String edition, @Param("year")Integer year, @Param("available_copies")Integer available_copies,  @Param("lang")String lang, 
			@Param("publisher")String publisher, @Param("isbn")String isbn); 
	
	
	@Transactional
	@Modifying /*@Modifying is for telling spring-data-jpa that this query is an update operation and it requires executeUpdate() not executeQuery().*/
	@Query(value = " UPDATE Books B SET  B.available_copies = B.available_copies + :available_copies WHERE B.isbn = :isbn ", nativeQuery = true)
	public int addBookCopies(@Param("available_copies")Integer available_copies, @Param("isbn")String isbn); 

	@Transactional
	@Modifying /*@Modifying is for telling spring-data-jpa that this query is an update operation and it requires executeUpdate() not executeQuery().*/
	@Query(value = " UPDATE Books B SET  B.available_copies = B.available_copies - :available_copies WHERE B.isbn = :isbn ", nativeQuery = true)
	public int removeBookCopies(@Param("available_copies")Integer available_copies, @Param("isbn")String isbn); 

	
}
