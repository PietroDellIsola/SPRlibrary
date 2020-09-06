package com.ap.SPRlibrary.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ap.SPRlibrary.services.Language;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "books")
public class Book {

	private static final long serialVersionUID = 1L;
	/*In this case the isbn will only refer to a single book.
	 * There will not two books with the same isbn*/
	@Id
	@Column(name = "isbn", nullable = false, length = 13)
	@Setter(AccessLevel.PROTECTED)
	private String isbn;
	@Column(name = "title", nullable = false, length = 30)
	private String title;
	@Column(name = "edition", nullable = false, length = 30)
	private String edition;
	@Column(name = "year", nullable = false)
	private Integer year;
	@Column(name = "available_copies", nullable = false)
	private Integer available_copies;
	// @NotEmpty(message = "Specified language is not invalid")
	@Language
	@Column(name = "lang", nullable = false, length = 30) /*
															 * BE CAREFUL!!! When you will send a RESTful request, the
															 * parameter of JsonObject shoud be "language" and not "lang"
															 */
	private String language;
	@Column(name = "publisher", nullable = false, length = 30)
	private String publisher;

	@OneToMany(mappedBy = "book")
	private Set<Loan> loans;

}
