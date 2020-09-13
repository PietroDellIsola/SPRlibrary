package com.ap.SPRlibrary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.OutputMsg;

@RestController
@RequestMapping("/bookController")
public class BookController {
	
	@Autowired 
	private BookService bookService; 
	
	@PostMapping(value = "/insertNewBook")
	public OutputMsg insertNewBook(@RequestBody Book b) {
		return bookService.insertNewBook(b);
	}
	
	@GetMapping(value = "/getBook/{isbn}")
	public Book getBook(@PathVariable String isbn) {
		return bookService.getBook(isbn);
	}
	
	@GetMapping(value = "/getAllBooks")
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@PutMapping(value = "/updateBook/{isbn}")
	public OutputMsg updateVideogioco(@RequestBody Book b, @PathVariable String isbn) 
	{
		return bookService.updateBook(b, isbn);
	}
	
	@DeleteMapping(value = "/deleteBook/{isbn}")
	public OutputMsg deleteBook(@PathVariable String isbn) 
	{
		return bookService.deleteBook(isbn);
	}
}
