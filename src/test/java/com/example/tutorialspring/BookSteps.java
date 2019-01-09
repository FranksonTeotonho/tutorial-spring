package com.example.tutorialspring;

import com.example.tutorialspring.persistence.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
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
import org.jbehave.core.model.ExamplesTable;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class BookSteps {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private String api;
    private long status;
    List<Book> listBook = new ArrayList<Book>();
    Book book;

    @Given("book api")
    public void givenBookApi(){api = "http://localhost:8081/api/books";}

    /*@Given("a $bookReg to be registered")
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

    }*/

    @When("I create a book with title $title and author $author")
    public void whenICreateABookWithTitleAndAuthor(String title, String author) throws IOException{

        book = new Book();
        book.setTitle(title);
        book.setAuthor(author);

        HttpPost request = new HttpPost(api);

        request.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
        request.setEntity(new StringEntity(objectMapper.writeValueAsString(book)));

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);
        status = response.getStatusLine().getStatusCode();
        Assert.assertEquals(HttpStatus.SC_CREATED,status);


    }
    @When("I search a book by title $title")
    public void whenISearchABookByTitle(String title) throws IOException {

        HttpGet request = new HttpGet(api + "/title/" + title);

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);

        List<Book> listBookResponse = objectMapper.readValue(EntityUtils.toString(response.getEntity()), new TypeReference<List<Book>>(){});
        Assert.assertTrue(!listBookResponse.isEmpty());
    }

    @Then("book with title $title should be returned")
    public void thenBookWithTitle(String title) throws IOException {

        HttpGet request = new HttpGet(api + "/title/" + title);

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);

        List<Book> listBookResponse = objectMapper.readValue(EntityUtils.toString(response.getEntity()), new TypeReference<List<Book>>(){});
        book = listBookResponse.get(0);

        Assert.assertEquals(title,book.getTitle());
    }

    @When("author of book with title $title should be $author")
    public void whenAuthorOfBookWithTitleShouldBeAuthor(String title, String author) throws IOException {

        HttpGet request = new HttpGet(api + "/title/" + title);

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);

        List<Book> listBookResponse = objectMapper.readValue(EntityUtils.toString(response.getEntity()), new TypeReference<List<Book>>(){});
        book = listBookResponse.get(0);

        Assert.assertEquals(author,book.getAuthor());
    }

    @When("I delete all books")
    public void whenIDeleteAllBooks() throws IOException {
        HttpGet request = new HttpGet(api + "/");

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);

        List<Book> listBookResponse = objectMapper.readValue(EntityUtils.toString(response.getEntity()), new TypeReference<List<Book>>(){});

        int id = 1;

        //É ruim fazer isso?
        //Fazer isso somente quando API não tem suporte
        //Ideial é a API ter uma funcionalidade drop
        while(!listBookResponse.isEmpty()){
            HttpDelete delRequest = new HttpDelete(api + "/" + String.valueOf(id));
            CloseableHttpResponse delResponse = HttpClientBuilder.create().build().execute(delRequest);
            id++;
            listBookResponse.remove(0);
        }

    }

    @Then("total of books should be 0")
    public void thenTotalOfBooksShouldBe0() throws IOException {
        HttpGet request = new HttpGet(api + "/count");

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);
        HttpEntity entity = response.getEntity();
        int total =  Integer.parseInt(EntityUtils.toString( entity));

        Assert.assertEquals(0, total);
    }

    @Then("total of books should be 3")
    public void thenTotalofBooksShouldBe3() throws IOException {
        HttpGet request = new HttpGet(api + "/count");

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);
        int total =  Integer.parseInt(EntityUtils.toString( response.getEntity()));

        Assert.assertEquals(3, total);
    }
}
