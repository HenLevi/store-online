package com.springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dto.CatalogDto;
import com.springboot.model.Catalog;
import com.springboot.repository.CatalogRepository;

@Service
public class CatalogService {
	@Autowired
	private CatalogRepository catalogRepository;

	public Catalog createCatalog(Catalog catalog) {
		Catalog newCatalog = catalogRepository.save(catalog);
		return newCatalog;
	}

	public List<Catalog> getListCatalog() {
		List<Catalog> allListCatalog = catalogRepository.findAll();
		return allListCatalog;

	}

	public Catalog readCatalog(String catalogName) {
		return catalogRepository.findByCatalogName(catalogName);
	}

	public Optional<Catalog> readCatalog(Integer catalogId) {
		return catalogRepository.findById(catalogId);
	}

	public void updateCatalog(int catalogId, Catalog updatedCatalog) {

		Catalog catalog = catalogRepository.getById(catalogId);
		catalog.setCatalogName(updatedCatalog.getCatalogName());
		catalog.setDescription(updatedCatalog.getDescription());
		catalog.setImageUrl(updatedCatalog.getImageUrl());
		catalogRepository.save(catalog);
	}

	

	public void deleteByCatalogId(Integer catalogId) {
		catalogRepository.deleteById(catalogId);	
	}

//	public Catalog getCatalogById(Integer catalogId) {
//		List<Catalog> catalogs = catalogRepository.findAll();
//		Catalog catalog = null;
//		catalog = catalogs.stream()
//				.filter(ctalogId -> (Integer.toString(catalogId)).equals(Integer.toString(ctalogId.getId()))).findAny()
//				.orElse(null);
//		return catalog;
//	}
//	
//	   public void deleteCatalog(Catalog catalog) {
//		   catalogRepository.delete(catalog);
//	     }
	   

}
