package com.springboot.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CatalogDto {

	private Integer id;

	@NotEmpty(message = "catalogName is required")

	private String catalogName;

	@NotEmpty(message = "description is required")
	private String description;

	@NotEmpty(message = "imageUrl is required")
	private String imageUrl;

}