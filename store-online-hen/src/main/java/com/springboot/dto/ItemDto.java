package com.springboot.dto;

import javax.persistence.Column;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.springboot.model.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ItemDto {

    private Integer id;
    
    
    @NotEmpty(message = "name is required")
    private String name;
    
    
	@NotEmpty(message = "imageUrl is required")
	@Column(name = "image_url")
    private String imageUrl;
	
	
	@NotNull(message = "price is required field")
    private double price;
	
	@NotEmpty(message = "description is required")
    private String description;
	
	@NotNull(message = "catalogId is required field")
	private Integer catalogId;

	public ItemDto(Integer id, @NotEmpty(message = "name is required") String name,
			@NotEmpty(message = "imageUrl is required") String imageUrl,
			@NotNull(message = "price is required field") double price,
			@NotEmpty(message = "description is required") String description,
			@NotNull(message = "catalogId is required field") Integer catalogId) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.price = price;
		this.description = description;
		this.catalogId = catalogId;
	}
	

    public ItemDto(Item item) {
        this.setId(item.getId());
        this.setName(item.getName());
        this.setImageUrl(item.getImageURL());
        this.setDescription(item.getDescription());
        this.setPrice(item.getPrice());
        this.setCatalogId(item.getCatalog().getId());
    }
	
}
