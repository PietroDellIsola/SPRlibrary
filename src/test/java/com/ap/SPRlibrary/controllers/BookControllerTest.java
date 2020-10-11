package com.ap.SPRlibrary.controllers;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;

import com.ap.SPRlibrary.config.ConfigForTest;
import com.ap.SPRlibrary.entity.Book;
import com.ap.SPRlibrary.entity.OutputMsg;
import com.ap.SPRlibrary.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = BookController.class)
//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = {BookController.class})
@SpringBootTest(classes = BookController.class)
@ContextConfiguration(classes = ConfigForTest.class)
public class BookControllerTest {
	
	private MockMvc mockMvc;
	
//	@Autowired
//    private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean 
	private BookService bookService;
	
	private Book book;
	
	private OutputMsg message;
	
	@Before
	public void setUp() {
		book = new Book("isbn123", "The lord of the rings", "Second edition", 2003, 12, "English",
				"EngPublisher", null);

		
//		this.mockMvc = webAppContextSetup(webApplicationContext).build();

	}
	
	@Test
	public void getBookTestOk() throws Exception {
	 
//	  message.setMsg("Book inserted");
	  Mockito.when(bookService.insertNewBook(Mockito.any(Book.class))).thenReturn(message);
	  
//	   mockMvc.perform(post("/forums/{forumId}/register", 42L)
	  mockMvc.perform(post("/bookController/insertNewBook")
	        .contentType("application/json")
//	        .param("sendWelcomeMail", "true")
	        .content(objectMapper.writeValueAsString(book)))
	        .andExpect(status().isOk());
	}

	
}
