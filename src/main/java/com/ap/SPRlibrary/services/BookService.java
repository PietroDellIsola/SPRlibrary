package com.ap.SPRlibrary.services;

import java.util.List;

import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.OutputMsg;

public interface BookService {
	public OutputMsg insertNewBook(Book b);
	public Book getBook(String isbn);
	public List<Book> getAllBooks();
}
