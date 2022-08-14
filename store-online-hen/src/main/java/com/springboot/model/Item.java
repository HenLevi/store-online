package com.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.dto.ItemDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "name is required")
	private String name;

	@NotEmpty(message = "imageUrl is required")
	@Column(name = "image_url")
	private String imageURL;

	@NotNull(message = "price is required field")
	private double price;

	@NotEmpty(message = "description is required")
	private String description;

	// many to one relationship

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "catalog_id", nullable = false)
	Catalog catalog;

	public Item(ItemDto itemDto, Catalog catalog) {
		this.name = itemDto.getName();
		this.imageURL = itemDto.getImageUrl();
		this.description = itemDto.getDescription();
		this.price = itemDto.getPrice();
		this.catalog = catalog;
	}

}
