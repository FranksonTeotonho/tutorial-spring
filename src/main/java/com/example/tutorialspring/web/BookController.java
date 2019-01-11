package com.example.tutorialspring.web;

import com.example.tutorialspring.persistence.model.Book;
import com.example.tutorialspring.persistence.repo.BookRepository;
import com.example.tutorialspring.service.BookService;
import com.example.tutorialspring.web.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {


    @Autowired
    private BookService bookService;


    @GetMapping
    public Iterable findAll() {
        return bookService.findAllBooks();
    }

    @GetMapping("/count")
    public long getCount() {
        return bookService.countBooks();
    }

    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookService.findBookByTitle(bookTitle);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Book findOne(@PathVariable long id) {
        return bookService.findOneBook(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        bookService.deleteBook(id);
    }

    @DeleteMapping
    public void deleteAll() {
        bookService.deleteAllBooks();
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable long id) {

        return bookService.update(book, id);

    }

}
