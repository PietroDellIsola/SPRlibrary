package com.ap.SPRlibrary.services;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.OutputMsg;
import com.ap.SPRlibrary.repository.BookRepository;

@Service
public class BookingServiceImpl implements BookService{

	@Resource
    private BookRepository bookingRepository;
	
	
	public OutputMsg insertNewBook(Book b) {
		OutputMsg msg = new OutputMsg();
		
		try {
			
			if(bookingRepository.findById(b.getIsbn()).equals(Optional.empty()))
			{
				/*book not present*/
				
				bookingRepository.save(b);
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

	
}
