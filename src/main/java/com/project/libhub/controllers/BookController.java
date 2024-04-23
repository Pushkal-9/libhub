package com.project.libhub.controllers;

import com.project.libhub.models.Book;
import com.project.libhub.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public int updateBook(@PathVariable int id, @RequestBody Book book) {
        return bookService.updateBook(id,book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("keyword") String keyword) {
        return bookService.searchBooksByKeyword(keyword);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Map<String, Object>>> getPopularBooks() {
        List<Map<String, Object>> popularBooks = bookService.getPopularBooksInLast30Days();
        return ResponseEntity.ok(popularBooks);
    }
}

