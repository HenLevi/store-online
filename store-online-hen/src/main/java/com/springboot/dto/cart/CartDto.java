package com.springboot.dto.cart;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalCost;
    
}