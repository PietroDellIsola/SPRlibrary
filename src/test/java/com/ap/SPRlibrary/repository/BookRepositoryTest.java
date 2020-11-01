package com.ap.SPRlibrary.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap.SPRlibrary.entity.Book;

@TestPropertySource("classpath:test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BookRepository bookRepository;
	
	private Book book;
	
	private int initialBookCopies;
	
	@Before
	public void setUp() {
		
		initialBookCopies = 12;
		
		book = new Book("isbn123456", "The lord of the rings", "Second edition", 2003, initialBookCopies, "English",
				"EngPublisher", null);
		
	}
	
	@Test 
	public void updateBookTest() 
	{
		// given book
		String newPublisher = "newPublisher";
	    entityManager.persist(book);
	    entityManager.flush();
	 
	    // when
	    bookRepository.updateBook(book.getTitle(), book.getEdition(), book.getYear(),
	    		book.getAvailable_copies(), book.getLanguage(), newPublisher, book.getIsbn());
	    entityManager.clear();
	    
	    // then
	    assertEquals(newPublisher, 
	    		bookRepository.getOne(book.getIsbn()).getPublisher());
		
	}
	
	@Test 
	public void addBookCopiesTest() 
	{
		// given book	    
	    entityManager.persist(book);
	    entityManager.flush();
	    String isbn = book.getIsbn();
	    int availableCopies = 3;
	 
	    // when
	    bookRepository.addBookCopies(availableCopies, isbn);
	    entityManager.clear();
	    
	    // then
	    assertEquals(initialBookCopies+availableCopies, 
	    		bookRepository.getOne(isbn).getAvailable_copies().intValue());
	}
	
	@Test 
	public void removeBookCopiesTest() 
	{
		// given book	    
	    entityManager.persist(book);
	    entityManager.flush();
	    String isbn = book.getIsbn();
	    int availableCopies = 10;
	 
	    // when
	    bookRepository.removeBookCopies(availableCopies, isbn);
	    entityManager.clear();

	    // then
	    assertEquals(initialBookCopies-availableCopies, 
	    		bookRepository.getOne(isbn).getAvailable_copies().intValue());
		
	}
	
	
}
