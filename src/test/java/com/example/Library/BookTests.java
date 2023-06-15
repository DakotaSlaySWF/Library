package com.example.Library;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookTests {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    LibraryBookRepository repository;

    @Test
    void shouldRespondWithSuccess_whenEndpointIsHit() throws Exception {

        mockMvc.perform(post("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRespondWithSuccess_whenCreatingANewBook() throws Exception {
        //Arrange
        String book = "{'author': 'James Brown'}";
        MockHttpServletRequestBuilder request = post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(book);
        //Act
        ResultActions response = mockMvc.perform(request);

        //Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.author",is("James Brown")));
    }

    @Test
    void shouldReturnABookWithTheCorrectAuthor_whenPassingAuthorToTheGetEndpoint() throws Exception {
        //arrange
        String author = "James Brown";
        MockHttpServletRequestBuilder request = get("/api/books");

        //act
        ResultActions response = mockMvc.perform(request);

        //assert
        response.andExpect(jsonPath("$.author",is("James Brown")));
    }

    @Test
    void shouldReturnABookWithAuthorJamesPatterson_whenPassingAuthorJamesPattersonToTheEndpoint() throws Exception {

        String author = "JamesPatterson";

        repository.save(new LibraryBook(author));

        MockHttpServletRequestBuilder request = get("/api/books/"+author);

        ResultActions response = mockMvc.perform(request);

        response.andExpect(jsonPath("$.author",is("JamesPatterson")));

    }
}
