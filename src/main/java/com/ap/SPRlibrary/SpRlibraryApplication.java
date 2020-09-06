package com.ap.SPRlibrary;

import java.util.GregorianCalendar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.Loan;
import com.ap.SPRlibrary.entity.Member;
import com.ap.SPRlibrary.repository.BookRepository;
import com.ap.SPRlibrary.repository.LoanRepository;
import com.ap.SPRlibrary.repository.MemberRepository;

@SpringBootApplication
public class SpRlibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpRlibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateBookRepository(BookRepository bookRepo, LoanRepository loanRepo,
			MemberRepository memberRepo) {
		return args -> {
			// adding books
			Book b1 = new Book("isbn123", "The lord of the rings", "Second edition", 2003, 12, "English",
					"EngPublisher", null);
			Book b2 = new Book("isbn124", "Il signore degli anelli", "Seconda edition", 2003, 5, "Italian",
					"ItPublisher", null);
			bookRepo.save(b1);
			bookRepo.save(b2);

			// adding members
			Member m1 = new Member("fiscal_codeM1", "Marco", "Rossi", new GregorianCalendar(1999, 10, 10),
					null); /*
							 * GregorianCalendar month starts from 0. For example, if we have
							 * a GregorianCalendar(1999, 10, 10), on db will be 1999/11/10
							 */
			Member m2 = new Member("fiscal_codeM2", "Giulia", "Rossini", new GregorianCalendar(1975, 2, 2), null);
			Member m3 = new Member("fiscal_codeM3", "Anna", "Marrone", new GregorianCalendar(2001, 11, 8), null);
			Member m4 = new Member("fiscal_codeM4", "Dario", "Verdi", new GregorianCalendar(1982, 3, 7), null);
			memberRepo.save(m1);
			memberRepo.save(m2);
			memberRepo.save(m3);
			memberRepo.save(m4);

			// adding loans
			Loan l1 = new Loan(1, new GregorianCalendar(2020, 1, 1), new GregorianCalendar(2020, 4, 1), b2, m1, true,
					1);/*
						 * GregorianCalendar month starts from 0. For example, if we have
						 * a GregorianCalendar(1999, 10, 10), on db will be 1999/9/10
						 */
			Loan l2 = new Loan(2, new GregorianCalendar(2020, 1, 21), new GregorianCalendar(2020, 4, 21), b2, m4, false,
					1);
			Loan l3 = new Loan(3, new GregorianCalendar(2020, 2, 5), new GregorianCalendar(2020, 5, 5), b2, m2, true,
					3);
			Loan l4 = new Loan(4, new GregorianCalendar(2020, 2, 5), new GregorianCalendar(2020, 5, 5), b1, m3, false,
					3);
			loanRepo.save(l1);
			loanRepo.save(l2);
			loanRepo.save(l3);
			loanRepo.save(l4);
		};
	}

}
