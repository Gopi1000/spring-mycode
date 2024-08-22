package com.demo.mapping.service;

import com.demo.mapping.dao.BookRepository;
import com.demo.mapping.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Book saveOrUpdateABook(Book book) {
        return bookRepository.save(book);
    }

    public Book findBookById(Long bookId) {
        return bookRepository.getByBookId(bookId);
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

	public void delete(Long bookId) {
		bookRepository.deleteById(bookId);
		
	}

	public void update(Long bookId, Book c) {
	    bookRepository.save(c);
	}

//	public void savebook(Book book) {
//		// TODO Auto-generated method stub
//		bookRepository.save(book);
//		
//	}
}
