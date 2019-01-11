package com.example.tutorialspring.persistence.repo;

import com.example.tutorialspring.persistence.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);

    Book findById(long id);
}
