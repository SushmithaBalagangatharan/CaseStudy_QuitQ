package com.hexaware.quitq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<String> adrressExceptionHandler(){
		return new ResponseEntity<String>("Address does not exist!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CartItemNotFoundException.class)
	public ResponseEntity<String> cartItemExceptionHandler(){
		return new ResponseEntity<String>("Cart Item does not exist!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<String> cartExceptionHandler(){
		return new ResponseEntity<String>("Cart does not exist!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderItemNotFoundException.class)
	public ResponseEntity<String> orderItemExceptionHandler(){
		return new ResponseEntity<String>("Order Item does not exist!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<String> orderExceptionHandler(){
		return new ResponseEntity<String>("Order does not exist!", HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<String> paymentExceptionHandler(){
		return new ResponseEntity<String>("Payment does not exist!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productExceptionHandler(){
		return new ResponseEntity<String>("Product does not exist!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<String> reviewExceptionHandler(){
		return new ResponseEntity<String>("Review does not exist!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userExceptionHandler(){
		return new ResponseEntity<String>("User does not exist!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RatingNotFoundException.class)
	public ResponseEntity<String> ratingExceptionHandler(){
		return new ResponseEntity<String>("Ratings for the product does not exist!", HttpStatus.NOT_FOUND);
	}

}
