package com.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dto.ItemDto;
import com.springboot.exceptions.ItemNotExistException;
import com.springboot.model.Catalog;
import com.springboot.model.Item;
import com.springboot.repository.ItemRepository;
@Service
public class ItemService {
	
	@Autowired 
	ItemRepository itemRepository;

	public void addItem(ItemDto itemDto, Catalog catalog) {
           Item item=new Item();
           item.setDescription(itemDto.getDescription());
           item.setImageURL(itemDto.getImageUrl());
           item.setName(itemDto.getName());
           item.setPrice(itemDto.getPrice());
           item.setCatalog(catalog);
           itemRepository.save(item);
          
	}
	
	 public static ItemDto getDtoFromItem(Item item) {
		 ItemDto itemDto = new ItemDto(item);
	        return itemDto;
	    }
	
    public List<ItemDto> listItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemDtos = new ArrayList<>();
        for(Item item : items) {
        	ItemDto itemDto = getDtoFromItem(item);
        	itemDtos.add(itemDto);
        }
        return itemDtos;
    }
    
    
    public static Item getItemFromDto(ItemDto itemDto, Catalog catalog) {
        Item item = new Item(itemDto, catalog);
        return item;
    }

	public void updateItem(Integer itemId,  ItemDto itemDto, Catalog catalog) {
	    Item item = getItemFromDto(itemDto, catalog);
        item.setId(itemId);
        itemRepository.save(item);
		
	}
	

    public Item findById(Integer itemId) throws ItemNotExistException {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isEmpty())
            throw new ItemNotExistException("item id is invalid " + itemId);
        return optionalItem.get();
    }
	

	
	

}
