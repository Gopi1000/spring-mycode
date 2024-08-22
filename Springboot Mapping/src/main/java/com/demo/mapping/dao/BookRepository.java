package com.demo.mapping.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.mapping.model.Book;


@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Book getByBookId(Long bookId);
}
