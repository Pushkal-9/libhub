package com.project.libhub.repository;

import com.project.libhub.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(concat('%', :keyword, '%')) OR LOWER(b.author) LIKE LOWER(concat('%', :keyword, '%'))")
    List<Book> findByTitleOrAuthorContainingIgnoreCase(@Param("keyword") String keyword);
}

