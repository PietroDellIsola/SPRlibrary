package com.ap.SPRlibrary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	/*@GetMapping(value = "/getOrario")
	public OutputMsg getOrario() {
		return videogiochiService.getOrario();
	}*/
	
//	@RequestMapping(value = "/insertNuovoVideogioco", method = RequestMethod.POST)
	@PostMapping(value = "/insertNewBook")
	public OutputMsg insertNewBook(@RequestBody Book b) {
		return bookService.insertNewBook(b);
	}
	
	/*
	@PatchMapping(value = "/updateVideogioco")
	public OutputMsg updateVideogioco(@RequestBody Videogioco v) 
	{
		return videogiochiService.updateVideogioco(v);
	}*/
}
