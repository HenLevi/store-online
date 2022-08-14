package com.springboot.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.ItemDto;
import com.springboot.model.Catalog;
import com.springboot.repository.CatalogRepository;
import com.springboot.repository.ItemRepository;
import com.springboot.service.CatalogService;
import com.springboot.service.ItemService;
import com.springboot.util.ApiResponse;

@RestController
@RequestMapping("/item")
public class ItemController {

	private static Logger LOG = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	ItemService itemService;
	@Autowired
	CatalogService catalogService;

	@Autowired
	CatalogRepository catalogRepository;

	@Autowired
	ItemRepository itemRepository;

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addItem(@Valid @RequestBody ItemDto itemDto) {
		try {
			Optional<Catalog> optionalCatalog = catalogService.readCatalog(itemDto.getCatalogId());
			if (!optionalCatalog.isPresent()) {
				return new ResponseEntity<ApiResponse>(new ApiResponse(false, "catalog is invalid"),
						HttpStatus.CONFLICT);
			}
			Catalog catalog = optionalCatalog.get();
			itemService.addItem(itemDto, catalog);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been added"), HttpStatus.CREATED);
		} catch (Exception e) {
			LOG.error("Error in controller: ItemController in addItem function, exception:" + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getListItems")
	public ResponseEntity<List<ItemDto>> getListItems() {
		try {
			List<ItemDto> listItems = itemService.listItems();
			return new ResponseEntity<List<ItemDto>>(listItems, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error in controller: ItemController in getListItems function, exception:" + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/update/{itemId}")
	public ResponseEntity<ApiResponse> updateItem(@PathVariable("itemId") Integer itemId,
			@RequestBody @Valid ItemDto itemDto) {
		try {
			Optional<Catalog> optionalCatalog = catalogService.readCatalog(itemDto.getCatalogId());
			if (!optionalCatalog.isPresent()) {
				return new ResponseEntity<ApiResponse>(new ApiResponse(false, "catalog is invalid"),
						HttpStatus.CONFLICT);
			}
			Catalog catalog = optionalCatalog.get();
			itemService.updateItem(itemId, itemDto, catalog);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been updated"), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error in controller: ItemController in updateItem function, exception:" + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
