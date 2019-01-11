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

    /*Fazer testes de falhas*/

    @Test
    public void updateBook_shouldSuccess(){
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

    @Test
    public void findAll_shouldSuccess(){
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);

        bookService.findAllBooks();

        Mockito.verify(bookRepository).findAll();

    }

    @Test
    public void delete_shouldSuccess(){
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);

        long id = 3;

        bookService.deleteBook(id);

        Mockito.verify(bookRepository).findById(id);
        Mockito.verify(bookRepository).deleteById(id);

    }

    @Test
    public void create_shouldSuccess(){
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);

        Book book = new Book();
        book.setTitle("titu1o1");
        book.setAuthor("nome1");

        bookService.createBook(book);

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        Mockito.verify(bookRepository).save(captor.capture());
        Book bookSentToRepository = captor.getValue();

        Assert.assertEquals("Expect author name to be equal", book.getAuthor(), bookSentToRepository.getAuthor());
        Assert.assertEquals("Expect author name to be equal", book.getTitle(), bookSentToRepository.getTitle());
    }


}