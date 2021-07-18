package com.kapilsony.bookservice.contracts;

import com.kapilsony.bookservice.controllers.BookController;
import com.kapilsony.bookservice.dto.BookResponse;
import com.kapilsony.bookservice.services.BookService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
class BookserviceApplicationBaseTests {

	@Autowired
	private BookController bookController;

	@MockBean
	private BookService bookService;

	@BeforeEach
	public void setup() {
		StandaloneMockMvcBuilder standaloneMockMvcBuilder =
				MockMvcBuilders.standaloneSetup(bookController);
		RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);

		// Mock business layer
		Mockito.when(bookService.findById(Mockito.anyLong())).thenReturn(getMockBookData());
	}

	private BookResponse getMockBookData() {
		return BookResponse.builder()
				.bookId(3L)
				.name("Java")
				.price(99.55F)
				.author("Thomas")
				.category("PROGRAMMING")
				.description("Java Book for Begineers")
				.build();
	}

}
