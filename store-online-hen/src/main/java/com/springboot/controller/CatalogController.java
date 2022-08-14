package com.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Catalog;
import com.springboot.service.CatalogService;
import com.springboot.util.ApiResponse;
import com.springboot.util.Helper;

@RestController
@RequestMapping("/catalog")

public class CatalogController {

	private static Logger LOG = LoggerFactory.getLogger(CatalogController.class);

	@Autowired
	private CatalogService catalogService;

	@GetMapping("/getListCatalog")
	public ResponseEntity<List<Catalog>> getlistCatalog() {
		try {
			List<Catalog> listCatalog = catalogService.getListCatalog();
			return new ResponseEntity<List<Catalog>>(listCatalog, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error in controller: CatalogController in getlistCatalog function, exception:" + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCatalog(@Valid @RequestBody Catalog catalog) {
		try {
			if (Helper.notNull(catalogService.readCatalog(catalog.getCatalogName()))) {
				return new ResponseEntity<ApiResponse>(new ApiResponse(false, "catalog already exists"),
						HttpStatus.CONFLICT);
			}
			catalogService.createCatalog(catalog);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the catalog"), HttpStatus.CREATED);
		} catch (Exception e) {
			LOG.error("Error in controller: CatalogController in createCatalog function, exception:" + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{catalogId}")
	public ResponseEntity<ApiResponse> updateCatalog(@PathVariable("catalogId") Integer catalogId,
			@Valid @RequestBody Catalog catalog) {
		try {

			// Check to see if the catalog exists.
			if (Helper.notNull(catalogService.readCatalog(catalogId))) {
				// If the cat exists then update it.
				catalogService.updateCatalog(catalogId, catalog);
				return new ResponseEntity<ApiResponse>(new ApiResponse(true, "catalog updated"), HttpStatus.OK);
			}

			// If the catalog doesn't exist then return a response of unsuccessful.
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "catalog does not exist"),
					HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			LOG.error("Error in controller: CatalogController in updateCatalog function, exception:" + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/delete/{catalogId}")
	public ResponseEntity<Catalog> deleteCategory(@PathVariable final int catalogId) {
		catalogService.deleteByCatalogId(catalogId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
