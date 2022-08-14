package com.springboot.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.cart.AddToCartDto;
import com.springboot.dto.cart.CartDto;
import com.springboot.exceptions.AuthenticationFailException;
import com.springboot.exceptions.ItemNotExistException;
import com.springboot.model.Item;
import com.springboot.model.User;
import com.springboot.service.AuthenticationService;
import com.springboot.service.CartService;
import com.springboot.service.ItemService;
import com.springboot.util.ApiResponse;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	private static Logger LOG = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private CartService cartService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> addToCart(@Valid @RequestBody AddToCartDto addToCartDto,@RequestParam("token") String token) {
		try {
		authenticationService.authenticate(token);
		User user = authenticationService.getUser(token);
		Item item = itemService.findById(addToCartDto.getItemId());
		System.out.println("item to add" + item.getName());
		cartService.addToCart(addToCartDto, item, user);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
		} catch (Exception e) {
			LOG.error("Error in controller: CartController in addToCart function, exception:" + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getCartItems")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
		try {
		authenticationService.authenticate(token);
		User user = authenticationService.getUser(token);
		CartDto cartDto = cartService.listCartItems(user);
		return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error in controller: CartController in getCartItems function, exception:" + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{cartItemId}")
	public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,@RequestParam("token") String token) throws AuthenticationFailException, ItemNotExistException {
		try {
		authenticationService.authenticate(token);
		User user = authenticationService.getUser(token);
		Item item = itemService.findById(cartDto.getItemId());
		cartService.updateCartItem(cartDto, user, item);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been updated"), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error in controller: CartController in updateCartItem function, exception:" + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	@DeleteMapping("/delete/{cartItemId}")
//	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID,
//			@RequestParam("token") String token) throws AuthenticationFailException {
//		authenticationService.authenticate(token);
//		int userId = authenticationService.getUser(token).getId();
//		cartService.deleteCartItem(itemID, userId);
//		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
//	}

}
