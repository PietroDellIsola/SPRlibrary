package com.ap.SPRlibrary.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.ap.SPRlibrary.services.Language;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * author Pietro Dell'Isola
 * 
 */
@Entity

/*
 * The code @JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) solves
 * error: HTTP Status 500 - Could not write content: No serializer found for
 * class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no
 * properties discovered to create BeanSerializer (to avoid exception, disable
 * SerializationFeature.FAIL_ON_EMPTY_BEANS) ) (through reference chain:
 * java.util.HashMap[&quot;departments&quot;]-&gt;java.util.
 * UnmodifiableRandomAccessList[0] I had it using POSTMAN with RESTful
 * request(this only cropped up because of a @OneToOne relationship.) You just
 * need to know how to tell your serializer to ignore the helpful garbage that
 * Hibernate adds to your classes so it can manage lazy loading of data.
 **/
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

@Table(name = "books")
public class Book {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "isbn", nullable = false, length = 13)
	private String isbn;
	@Column(name = "title", nullable = false, length = 30)
	private String title;
	@Column(name = "edition", nullable = false, length = 30)
	private String edition;
	@Column(name = "year")
	private int year;
	//@NotEmpty(message = "Specified language is not invalid")
	@Language
	@Column(name = "language", nullable = false, length = 30)
	private String language;
	@Column(name = "publisher", nullable = false, length = 30)
	private String publisher;

	@OneToMany(mappedBy = "book")
	private Set<Loan> loans;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Set<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Set<Loan> loans) {
		this.loans = loans;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}
