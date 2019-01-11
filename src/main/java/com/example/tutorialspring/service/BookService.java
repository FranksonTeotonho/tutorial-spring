package com.example.tutorialspring.service;

import com.example.tutorialspring.persistence.model.Book;
import com.example.tutorialspring.persistence.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable findAllBooks(){
        return bookRepository.findAll();
    }

    public Book update(Book book, long id){
        return bookRepository.save(book);
    }

    public long countBooks() {
        return bookRepository.count();
    }

    public List findBookByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    public Book findOneBook(long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(long id) {
        bookRepository.findById(id);
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }
}
