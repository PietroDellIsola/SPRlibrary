package com.ap.SPRlibrary.services;

import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.OutputMsg;

public interface BookService {
	public OutputMsg insertNewBook(Book b);
	public Book getBook(String isbn);
}
