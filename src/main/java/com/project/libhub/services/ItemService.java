package com.project.libhub.services;

import com.project.libhub.models.Item;
import com.project.libhub.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getItemsByBookId(int bibNumber) {
        return itemRepository.findByBookBibNumber(bibNumber);
    }

    public List<Item> getItemsByStatus(String status) {
        return itemRepository.findByStatus(status);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItemByBarcode(String barcode) {
        return itemRepository.findById(barcode).orElse(null);
    }

    public void deleteItem(String barcode) {
        itemRepository.deleteById(barcode);
    }

    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }
}

