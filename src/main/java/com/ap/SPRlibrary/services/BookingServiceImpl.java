package com.ap.SPRlibrary.services;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.Member;
import com.ap.SPRlibrary.entity.OutputMsg;
import com.ap.SPRlibrary.repository.BookRepository;

@Service
public class BookingServiceImpl implements BookService{

	@Resource
    private BookRepository bookRepository;
	
	
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
	
}
