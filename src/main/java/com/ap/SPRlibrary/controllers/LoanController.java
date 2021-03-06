package com.ap.SPRlibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap.SPRlibrary.entity.Loan;
import com.ap.SPRlibrary.entity.OutputMsg;
import com.ap.SPRlibrary.services.LoanService;

@RestController
@RequestMapping("/loanController")
public class LoanController {
	
	@Autowired 
	private LoanService loanService; 
	
	@PostMapping("/insertNewLoan")
	public OutputMsg insertNewLoan(@RequestBody Loan l) {
		return loanService.insertNewLoan(l);
	}
	
	@GetMapping("/getLoan")
	public Loan getLoan(@RequestBody Loan l) {
		return loanService.getLoan(l);
	}
	
	@PatchMapping("/updateToReturned")
	//modifica in put
	public OutputMsg updateToReturned(@RequestBody Loan l) {
		return loanService.updateToReturned(l);
	}

}
