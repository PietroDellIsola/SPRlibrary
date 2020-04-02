package com.ap.SPRlibrary.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.OutputMsg;
import com.ap.SPRlibrary.repository.BookRepository;

@Service
public class BookingServiceImpl implements BookService{

	@Resource
    private BookRepository bookRepository;
	
	@Override
	public OutputMsg insertNewBook(Book b) {
		OutputMsg msg = new OutputMsg();
		
		try {
			
			if(bookRepository.findById(b.getIsbn()).equals(Optional.empty()))
			{
				/*book not present*/
				
				bookRepository.save(b);
				msg.setMsg("Book inserted");
			}
			else 	
			{
				msg.setMsg("The book is already present");
			}
			
			return msg;
		}
		catch (Exception e) {
			msg.setMsg("There was an error");
			e.printStackTrace();
			
			return msg;
		}
		
	}

	
	@Override
	public Book getBook(String isbn) {
		return bookRepository.getOne(isbn);
	}


	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}


	@Override
	public OutputMsg updateBook(Book newB) {
		/*with this service it is possible update only title, edition, year, language, publisher.*/
		OutputMsg msg = new OutputMsg();
		/*First, I look up the book, if it is present, the program set the null value sent with the old value*/
		
		if(!bookRepository.existsById(newB.getIsbn()))
			msg.setMsg("Book with Isbn "+newB.getIsbn()+" not found");
		else {
		
		Book old = bookRepository.getOne(newB.getIsbn()); 

		if(newB.getTitle()==null) newB.setTitle(old.getTitle());
		if(newB.getEdition()==null) newB.setEdition(old.getEdition());
		if(newB.getYear()==null) newB.setYear(old.getYear());
		if(newB.getLanguage()==null) newB.setLanguage(old.getLanguage());
		if(newB.getPublisher()==null) newB.setPublisher(old.getPublisher());
		
		if(bookRepository.updateBook(newB.getTitle(), newB.getEdition(), newB.getYear(),newB.getLanguage(),
				newB.getPublisher(),newB.getIsbn())>0) /*returns the numbers of updated columns*/
			msg.setMsg("Book updated");
		
		}	

		return msg;
		
	}
	
}
