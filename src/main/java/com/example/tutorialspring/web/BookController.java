package com.example.tutorialspring.web;

import com.example.tutorialspring.persistence.model.Book;
import com.example.tutorialspring.persistence.repo.BookRepository;
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
    private BookRepository bookRepository;

    @GetMapping
    public Iterable findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/count")
    public long getCount(){
        return bookRepository.count();
    }

    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle){
        return bookRepository.findByTitle(bookTitle);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Book findOne(@PathVariable long id){
        return bookRepository.findById(id);
        //return bookRepository.findAllById(id).orElseThrow(BookNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        bookRepository.findById(id);
        //bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }


    public Book updateBook(@RequestBody Book book, @PathVariable long id){

        if(book.getId() != id){
            //throw  new BookIdMismatchException();
        }
        bookRepository.findById(id);
        //bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);

    }

}
