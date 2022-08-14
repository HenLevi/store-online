package com.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "catalog")
public class Catalog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "catalogName is required")
	@Column(name = "category_name")
	private String catalogName;

	@NotEmpty(message = "description is required")
	private String description;

	@NotEmpty(message = "imageUrl is required")
	@Column(name = "image_url")
	private String imageUrl;

}