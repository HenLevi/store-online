package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.Catalog;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
	Catalog findByCatalogName(String catalogName);

}
