package com.springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dto.cart.AddToCartDto;
import com.springboot.dto.cart.CartDto;
import com.springboot.dto.cart.CartItemDto;
import com.springboot.exceptions.CartItemNotExistException;
import com.springboot.model.Cart;
import com.springboot.model.Item;
import com.springboot.model.User;
import com.springboot.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private  CartRepository cartRepository;
    
    public void addToCart(AddToCartDto addToCartDto, Item item, User user){
        Cart cart = new Cart(item, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
        
    }
    
    public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }
    
    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getItem().getPrice()* cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }
    
    public void updateCartItem(AddToCartDto cartDto, User user,Item item){
        Cart cart = cartRepository.getOne(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(int id,int userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);

    }

    public void deleteCartItems(int userId) {
        cartRepository.deleteAll();
    }


    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }
    
    
}
