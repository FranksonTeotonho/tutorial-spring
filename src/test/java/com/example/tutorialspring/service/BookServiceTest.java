package com.example.tutorialspring.service;


import com.example.tutorialspring.persistence.model.Book;
import com.example.tutorialspring.persistence.repo.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService;
    private BookRepository bookRepository;

    @Test
    public void updateBook_shouldSucess(){
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);

        Book book = new Book();
        book.setTitle("Bla1");
        book.setAuthor("name1");

        long id = 2;

        Book updatedBook = bookService.update(book, id);


        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        Mockito.verify(bookRepository).save(captor.capture());
        Book bookSentToRepository = captor.getValue();

        Assert.assertEquals("Expect author name to be equal", book.getAuthor(), bookSentToRepository.getAuthor());
        Assert.assertEquals("Expect author name to be equal", book.getTitle(), bookSentToRepository.getTitle());
    }

}