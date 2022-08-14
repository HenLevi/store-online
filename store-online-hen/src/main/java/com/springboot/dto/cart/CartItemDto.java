package com.springboot.dto.cart;

import javax.validation.constraints.NotNull;

import com.springboot.model.Cart;
import com.springboot.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItemDto {
	private Integer id;

	@NotNull
	private Integer quantity;

	@NotNull
	private Item item;

	public CartItemDto(Cart cart) {
		this.setId(cart.getId());
		this.setQuantity(cart.getQuantity());
		this.setItem(cart.getItem());
	}

	@Override
	public String toString() {
		return "CartItemDto [id=" + id + ", quantity=" + quantity + ", item=" + item + "]";
	}

}