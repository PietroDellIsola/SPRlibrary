package com.ap.SPRlibrary.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
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

@Table(name = "loans")
public class Loan {
	/* You cannot add other loans with the same member and book */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) /* autoincrement */
	@Setter(AccessLevel.PROTECTED)
	private int id_loan;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "starting_date", nullable = false)
	private Calendar starting_date;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "end_date", nullable = false)
	private Calendar end_date;

	@ManyToOne
	@JoinColumn(name = "fk_isbn", nullable = false)
	private Book book;

	@ManyToOne
	@JoinColumn(name = "fk_fiscal_code", nullable = false)
	private Member member;

	@Column(name = "returned")
	private boolean returned;

	@Column(name = "copies", nullable = false)
	private Integer copies;

}
