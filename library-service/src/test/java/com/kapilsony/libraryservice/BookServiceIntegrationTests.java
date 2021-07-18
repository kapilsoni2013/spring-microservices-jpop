package com.kapilsony.libraryservice;

import com.kapilsony.libraryservice.dto.BookResponse;
import com.kapilsony.libraryservice.services.BookService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.ConstraintViolationException;
import java.net.URI;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.kapilsony:bookservice:+:stubs:6666"
)
class BookServiceIntegrationTests {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void given_whenFindBookById_ThenReturnBook() throws Exception {
        //Given
        BookResponse bookResponse=getMockBookData();

        //When
        ResponseEntity<BookResponse> result = restTemplate.exchange(RequestEntity
                .get(URI.create("http://localhost:6666/books/3"))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build(), BookResponse.class);
        BookResponse actualResponse = result.getBody();

        //Then
        BDDAssertions.then(getMockBookData()).isEqualTo(actualResponse);
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
