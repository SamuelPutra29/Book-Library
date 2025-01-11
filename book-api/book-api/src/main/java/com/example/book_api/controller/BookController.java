package com.example.book_api.controller;

import com.example.book_api.model.Book;
import com.example.book_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

        @Autowired
        private BookRepository bookRepository;

        @GetMapping
        public List<Book> getAllBooks() {
            return bookRepository.findAll();
        }

        @GetMapping("/{id}")
        public Book getBookById(@PathVariable int id) {
            return bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
        }

        @PostMapping
        public Book addBook(@RequestBody Book newBook) {
            return bookRepository.save(newBook);
        }

        @PutMapping("/{id}")
        public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setYear(updatedBook.getYear());
            return bookRepository.save(book);
        }

        @DeleteMapping("/{id}")
        public String deleteBook(@PathVariable int id) {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            bookRepository.delete(book);
            return "Book deleted successfully";
        }
    }

