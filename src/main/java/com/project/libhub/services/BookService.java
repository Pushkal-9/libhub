package com.project.libhub.services;

import com.project.libhub.models.Book;
import com.project.libhub.repository.BookRepository;
import com.project.libhub.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final TransactionRepository transactionRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public int updateBook(int bibNumber,Book book) {
        return bookRepository.updateBookByBibNumber(bibNumber,book.getTitle(), book.getAuthor(), book.getPublisher() ,book.getPublicationYear());
    }

    public List<Book> searchBooksByKeyword(String keyword) {
        return bookRepository.findByTitleOrAuthorContainingIgnoreCase(keyword);
    }

    public List<Map<String, Object>> getPopularBooksInLast30Days() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        return transactionRepository.findPopularBooksSince(thirtyDaysAgo).stream()
                .map(result -> Map.of("title", result[0], "count", result[1]))
                .collect(Collectors.toList());
    }
}

