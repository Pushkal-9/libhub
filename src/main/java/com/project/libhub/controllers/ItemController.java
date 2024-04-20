package com.project.libhub.controllers;

import com.project.libhub.models.Item;
import com.project.libhub.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/by-book/{bibNumber}")
    public List<Item> getItemsByBookId(@PathVariable int bibNumber) {
        return itemService.getItemsByBookId(bibNumber);
    }

    @GetMapping("/by-status/{status}")
    public List<Item> getItemsByStatus(@PathVariable String status) {
        return itemService.getItemsByStatus(status);
    }

    @GetMapping("/{barcode}")
    public Item getItemByBarcode(@PathVariable String barcode) {
        return itemService.getItemByBarcode(barcode);
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    @PutMapping("/{barcode}")
    public Item updateItem(@PathVariable String barcode, @RequestBody Item item) {
        item.setItemBarcode(barcode);
        return itemService.updateItem(item);
    }

    @DeleteMapping("/{barcode}")
    public void deleteItem(@PathVariable String barcode) {
        itemService.deleteItem(barcode);
    }
}