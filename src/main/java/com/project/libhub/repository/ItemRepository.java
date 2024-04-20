package com.project.libhub.repository;

import com.project.libhub.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findByBookBibNumber(int bibNumber);
    List<Item> findByStatus(String status);}

