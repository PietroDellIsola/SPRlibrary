package com.ap.SPRlibrary.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//import com.ap.SPRlibrary.config.ConfigForTest;
import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.OutputMsg;
import com.ap.SPRlibrary.repository.BookRepository;
import com.ap.SPRlibrary.services.BookService;
import com.ap.SPRlibrary.services.BookServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookServiceImpl.class)
//@ContextConfiguration(classes = ConfigForTest.class)
public class BookServiceImplTest {
	
	@MockBean
	private BookRepository bookRepository;

	@Autowired
	private BookService bookService;
	
	private Book book;
	
	 @TestConfiguration
	    static class BookServiceImplTestContextConfiguration {
	 
	        @Bean
	        public BookService bookServiceImpl() {
	            return new BookServiceImpl();
	        }
	    }
	 
	 
	 @Before
		public void setUp() {
			book = new Book("isbn123", "The lord of the rings", "Second edition", 2003, 12, "English",
					"EngPublisher", null);

	 }
	 
	 
	 @Test 
	 public void insertNewBookTest() {
		 Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		 Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);
		 assertNotNull(bookService.insertNewBook(book));
	 }
	 
	 @Test 
	 public void insertNewBookAlreadyPresentTest() {
		 Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(book));
		 assertNotNull(bookService.insertNewBook(book));
	 }
	 
//	 @Test (expected = RuntimeException.class)
	 @Test
	 public void insertNewBookExceptionTest() {
		 Mockito.when(bookRepository.findById(Mockito.any())).thenThrow(RuntimeException.class);
		 assertNotNull(bookService.insertNewBook(book));
	 }
	 
	 @Test
	 public void getBookTest() {
			Mockito.when(bookRepository.getOne(Mockito.anyString())).thenReturn(book);
			assertNotNull(bookService.getBook("isbn"));
		}
	 
	 @Test
	 public void getAllBooksTest() {
		 	Mockito.when(bookRepository.findAll()).thenReturn(new ArrayList<Book>());
		 	assertNotNull(bookService.getAllBooks());
		}
	 
	 @Test
	 public void updateBookNotPresentTest() {
			
			Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(book));
			Mockito.when(bookRepository.updateBook(
					Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), 
					Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()
					)).thenReturn(new Integer(1));
			assertNotNull(bookService.updateBook(book, "isbn123"));

		}
	 
	 @Test
	 public void NotUpdateBookTest() {
		 	book.setTitle(null);
		 	book.setEdition(null);
		 	book.setYear(null);
		 	book.setAvailable_copies(null);
		 	book.setLanguage(null);
		 	book.setPublisher(null);
		 	book.setIsbn(null);
			Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(book));
			Mockito.when(bookRepository.updateBook(
					Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), 
					Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()
					)).thenReturn(new Integer(-1));
			assertNotNull(bookService.updateBook(book, "isbn123"));

		}

	 @Test
	 public void UpdateBookNotEqualIsbnTest() {
		 	Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(book));
			assertNotNull(bookService.updateBook(book, "isbn"));

		}
	 
	 @Test
	 public void UpdateBookNotFoundTest() {
		 	Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
			assertNotNull(bookService.updateBook(book, "isbn123"));

		}
	 
	 @Test
	 public void deleteBookTest() {
			Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(book));
			bookService.deleteBook("isbn123");
		}
	 
	 @Test
	 public void noptDeletedBookTest() {
			Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
			bookService.deleteBook("isbn123");
		}
}
