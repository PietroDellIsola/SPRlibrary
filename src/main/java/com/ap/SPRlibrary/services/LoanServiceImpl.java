package com.ap.SPRlibrary.services;

import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.Loan;
import com.ap.SPRlibrary.entity.Member;
import com.ap.SPRlibrary.entity.OutputMsg;
import com.ap.SPRlibrary.repository.BookRepository;
import com.ap.SPRlibrary.repository.LoanRepository;
import com.ap.SPRlibrary.repository.MemberRepository;

@Service
public class LoanServiceImpl implements LoanService {

	@Resource
	private LoanRepository loanRepository;
	@Resource
	private BookRepository bookRepository;
	@Resource
	private MemberRepository memberRepository;

	@Override
	public OutputMsg insertNewLoan(Loan l) {
		OutputMsg msg = new OutputMsg();
		Book b = l.getBook();
		Member m = l.getMember();

		if (b.getIsbn().equals("")) {
			msg.setMsg("Book's isbn can't be null");
			return msg;
		}
		if (m.getFiscal_code().equals("")) {
			msg.setMsg("Member's fiscal code can't be null");
			return msg;
		}
		if (!bookRepository.existsById(b.getIsbn())) {
			msg.setMsg("Book with isbn = " + b.getIsbn() + ", not found");
			return msg;
		}
		if (!memberRepository.existsById(m.getFiscal_code())) {
			msg.setMsg("Member with fiscal code = " + m.getFiscal_code() + ", not found");
			return msg;
		}

		/*You cannot add other loans with the same member and book if there already is a loan with that member and book not yet returned*/
		if (getLoan(l) != null) {
			msg.setMsg("It is already present an order with isbn = " + b.getIsbn() + " and fiscal code = "
					+ m.getFiscal_code() + " You can't add other loans with the same member and book");
			return msg;
		}

		if (l.getCopies() > (bookRepository.getOne(b.getIsbn())).getAvailable_copies()) {
			msg.setMsg("There are only " + b.getAvailable_copies() + " available copies. Loan not added.");
			return msg;
		}

		Loan newL = new Loan();
		newL.setBook(b);
		newL.setMember(m);
		GregorianCalendar gc = new GregorianCalendar();
		newL.setStarting_date(gc);
		gc.add((GregorianCalendar.MONTH), 2); /* adding 2 month to the loan's starting data */
		newL.setEnd_date(gc);
		newL.setReturned(false);
		newL.setCopies(l.getCopies());
		bookRepository.removeBookCopies(l.getCopies(), b.getIsbn());

		loanRepository.save(newL);
		msg.setMsg("Loan added");

		return msg;
	}

	/*the method returns only the loan not yet returned. 
	 * if there are more loans with the same book and member but not yet returned this method return nothing.
	 * Because you cannot add other loans with the same member and book if there already is a loan with that member and book not yet returned*/
	@Override
	public Loan getLoan(Loan l) {
		return loanRepository.getLoan(l.getBook().getIsbn(), l.getMember().getFiscal_code());
	}

	@Override
	public OutputMsg updateToReturned(Loan l) {
		/* with this service it is possible update only returned value. */
		OutputMsg msg = new OutputMsg();
		/*
		 * First, I look up the loan, if it is present, the program set the returned
		 * value to true
		 */
		Loan old = getLoan(l);

		if (old == null)
			msg.setMsg("Loan not found or the loan has been already returned");
		else {

			
				// old.setReturned(true);

				if (loanRepository.updateToReturned(old.getId_loan()) > 0) /* returns the numbers of updated columns */
				{
					bookRepository.addBookCopies(old.getCopies(), old.getBook().getIsbn()); /*adding the new available copies to the book*/
					msg.setMsg("Loan updated to returned");
				}

				else
					msg.setMsg("There was an error, the loan was not updated to returned"); /*
																							 * ERRORE RITORNA QUESTO
																							 * ANCGE SE LO AGGIORNA
																							 */
			
		}

		return msg;
	}

}
