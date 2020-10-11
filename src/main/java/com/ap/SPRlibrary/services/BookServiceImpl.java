package com.ap.SPRlibrary.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.OutputMsg;
import com.ap.SPRlibrary.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Resource
	private BookRepository bookRepository;

	@Override
	public OutputMsg insertNewBook(Book b) {
		OutputMsg msg = new OutputMsg();

		try {

			if (bookRepository.findById(b.getIsbn()).equals(Optional.empty())) {
				/* book not present */

				bookRepository.save(b);
				msg.setMsg("Book inserted");
			} else {
				msg.setMsg("The book is already present");
			}

			return msg;
		} catch (RuntimeException e) {
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
	public OutputMsg updateBook(Book newB, String isbn) {
		/*
		 * if you'll modify the isbn(PK) specifing new isbn in the body, it will create
		 * a new book with the new isbn in the body and old values of the book with isbn
		 * specified in the service path
		 */
		OutputMsg msg = new OutputMsg();
		Optional<Book> oldBook = bookRepository.findById(isbn);

		if (newB.getIsbn() != null && !newB.getIsbn().equals(isbn)) {
			msg.setMsg("You can't edit the isbn of Books");
			return msg;
		}

		if (!oldBook.isPresent())
			msg.setMsg("Book not found");

		else {

			if (newB.getTitle() == null)
				newB.setTitle(oldBook.get().getTitle());
			if (newB.getEdition() == null)
				newB.setEdition(oldBook.get().getEdition());
			if (newB.getYear() == null)
				newB.setYear(oldBook.get().getYear());
			if (newB.getAvailable_copies() == null)
				newB.setAvailable_copies(oldBook.get().getAvailable_copies());
			if (newB.getLanguage() == null)
				newB.setLanguage(oldBook.get().getLanguage());
			if (newB.getPublisher() == null)
				newB.setPublisher(oldBook.get().getPublisher());
			if (newB.getIsbn() == null)
				newB.setIsbn(oldBook.get().getIsbn());
			if (bookRepository.updateBook(newB.getTitle(), newB.getEdition(), newB.getYear(),
					newB.getAvailable_copies(), newB.getLanguage(), newB.getPublisher(),
					newB.getIsbn()) > 0) /* returns the numbers of updated columns */
				msg.setMsg("Book updated");
			else
				msg.setMsg("Book not updated");
		}
		return msg;

	}

	@Override
	public OutputMsg deleteBook(String isbn) {
		OutputMsg msg = new OutputMsg();
		Optional<Book> b = bookRepository.findById(isbn);
		if (b.equals(Optional.empty())) {
			/* book not present */
			msg.setMsg("Book not found");
		} else {
			bookRepository.delete(b.get());
			msg.setMsg("Book deleted");
		}

		return msg;
	}

}
