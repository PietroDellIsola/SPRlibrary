package com.ap.SPRlibrary.services;

import com.ap.SPRlibrary.entity.Loan;
import com.ap.SPRlibrary.entity.OutputMsg;

public interface LoanService {
	public OutputMsg insertNewLoan(Loan l);
	public Loan getLoan(Loan l);
	public OutputMsg updateToReturned(Loan l);
}
