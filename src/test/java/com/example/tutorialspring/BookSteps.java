package com.example.tutorialspring;

import com.example.tutorialspring.persistence.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.io.IOException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class BookSteps {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private String api;
    private long status;
    Book book;

    @Given("book api")
    public void givenBookApi(){api = "http://localhost:8081/api/books";}

    @Given("a $bookReg to be registered")
    public void givenABook(@Named("bookReg") String bookRegister){
        book = new Book();
        switch (bookRegister){
            case "correctBook":
                book.setTitle(randomAlphabetic(10));
                book.setAuthor(randomAlphabetic(5));
                break;
            case "noTitle":
                book.setAuthor(randomAlphabetic(5));
                break;

        }

    }

    @When("I register the random book")
    public void whenIResgisterTheRandomBook() throws IOException {
        HttpPost request = new HttpPost(api);

        request.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(book)));

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);


        book = objectMapper.readValue(EntityUtils.toString(response.getEntity()), Book.class);
    }

    @Then("Random book is exists")
    public void thenIdSizeIsIncreased() throws IOException {
        HttpGet request = new HttpGet(api + "/" + book.getId());

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);

        Book book = objectMapper.readValue(EntityUtils.toString(response.getEntity()), Book.class);
        Assert.assertNotNull(book);

    }
}
