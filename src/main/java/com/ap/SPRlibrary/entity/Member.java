package com.ap.SPRlibrary.entity;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Table(name = "members")
public class Member {
	@Id
	@Column(name = "fiscal_code", nullable = false, length = 16)
	//@Setter(AccessLevel.PROTECTED)
	private String fiscal_code;
	@Column(name = "name", nullable = false, length = 30)
	private String name;
	@Column(name = "surname", nullable = false, length = 30)
	private String surname;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "dob", nullable = false)
	private Calendar dob;

	@OneToMany(mappedBy = "member")
	private Set<Loan> loans;

}
