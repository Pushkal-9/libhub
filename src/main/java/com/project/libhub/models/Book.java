package com.project.libhub.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Book")
@Data
public class Book {
    @Id
    @Column(name = "BibNumber")
    private int bibNumber;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Author", nullable = false)
    private String author;

    @Column(name = "Publisher")
    private String publisher;

    @Column(name = "PublicationYear")
    private Integer publicationYear;

}

