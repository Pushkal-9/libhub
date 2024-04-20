package com.project.libhub.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Item")
@Data
public class Item {
    @Id
    @Column(name = "ItemBarcode")
    private String itemBarcode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BibNumber", nullable = false)
    private Book book;

    @Column(name = "CallNumber")
    private String callNumber;

    @Column(name = "Status")
    private String status;
}

