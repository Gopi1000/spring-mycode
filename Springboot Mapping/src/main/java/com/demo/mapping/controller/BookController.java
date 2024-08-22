package com.demo.mapping.controller;

import com.demo.mapping.model.Book;
import com.demo.mapping.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")

public class BookController {
    @Autowired
    BookService bookService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<Book> addABook(@RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.saveOrUpdateABook(book), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public Iterable<Book> getAllBookings(){
        return bookService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookingById(@PathVariable Long bookId) {
        return new ResponseEntity<Book>(bookService.findBookById(bookId), HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update/{bookId}")
	public void update(@RequestBody Book c, @PathVariable Long bookId) {
    	bookService.update(bookId, c);
	}
    
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{bookId}")
	public void deletebook(@PathVariable Long bookId) {
		bookService.delete(bookId);
	}
}
