package com.springboot.dto.cart;

import javax.validation.constraints.NotNull;

import com.springboot.model.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddToCartDto {
    private Integer id;
    
    @NotNull (message = "Item Id is required field")
    private  Integer itemId;
    
    @NotNull (message = "quantity is required field")
    private  Integer quantity;
    
} 
